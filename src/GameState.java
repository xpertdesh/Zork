
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
/**
 *Returns the contents of the room and the adventurer's inventory,the health of the adventurer, and allows the user to save their progress.
 *@author Yasmeen
 */
public class GameState {
/**
 *A type of exception where the user attempts to save their progress, but does so incorrectly. 
 @param e calls the e constructor from the super class. 
 *
 */
    public static class IllegalSaveFormatException extends Exception {
        public IllegalSaveFormatException(String e) {
            super(e);
        }
    }
    static String SAVE_FILE_EXTENSION = ".sav";
    static String SAVE_FILE_VERSION = "Zork III save data";

    static String ADVENTURER_MARKER = "Adventurer:";
    static String CURRENT_ROOM_LEADER = "Current room: ";
    static String INVENTORY_LEADER = "Inventory: ";
    static String SCORE_LEADER = "Score: ";

    private int totalScore = 0;
    private static GameState theInstance;
    private Dungeon dungeon;
    private ArrayList<Item> inventory;
    private Room adventurersCurrentRoom;
    private int totalHealth = 10;  //good health

/**
 *Returns an instance that cannot be done at the same time. 
 */
    static synchronized GameState instance() {
        if (theInstance == null) {
            theInstance = new GameState();
        }
        return theInstance;
    }
/**
 *Constructs a new inventory of items
 */
    private GameState() {
        inventory = new ArrayList<Item>();
    }
/**
 *Returns the total weight of the items contained within the inventory. 
 */
    int getAdventurersCurrentWeight() {
        int total = 0;
        for (Item item : inventory) {
            total += item.getWeight();
        }
        return total;
    }
/**
 *Keeps track of the state the game stops at. If the file, dungeon, or item are not found, throw its appropriate exception (listed below). If the file is unable to be saved, throw its exception (below). 
 *If no exceptions are thrown, keep track of what room the adventurer is in as well as their inventory. 
 *@param filename is the name of the file.
 *@throws FileNotFoundException if a file can not be used. 
 *@throws NoItemException if an item the player wants to interact with can not be found. 
 *@throws IllegalSaveFormatException if the file is unable to be saved properly for a variety of reasons, including if there is an item that is not found.
 *@throws Dungeon.IllegalDungeonFormatException if the dungeon used is not correct.
 */
    void restore(String filename) throws FileNotFoundException,
        IllegalSaveFormatException, Dungeon.IllegalDungeonFormatException {

        Scanner s = new Scanner(new FileReader(filename));

        if (!s.nextLine().equals(SAVE_FILE_VERSION)) {
            throw new IllegalSaveFormatException("Save file not compatible.");
        }

        String dungeonFileLine = s.nextLine();

        if (!dungeonFileLine.startsWith(Dungeon.FILENAME_LEADER)) {
            throw new IllegalSaveFormatException("No '" +
                Dungeon.FILENAME_LEADER + 
                "' after version indicator.");
        }

        dungeon = new Dungeon(dungeonFileLine.substring(
            Dungeon.FILENAME_LEADER.length()), false);
        dungeon.restoreState(s);

        s.nextLine();  // Throw away "Adventurer:".
        String currentRoomLine = s.nextLine();
        adventurersCurrentRoom = dungeon.getRoom(
            currentRoomLine.substring(CURRENT_ROOM_LEADER.length()));
        if (s.hasNext()) {
            String inventoryList = s.nextLine().substring(
                INVENTORY_LEADER.length());
            String[] inventoryItems = inventoryList.split(",");
            for (String itemName : inventoryItems) {
                try {
                    addToInventory(dungeon.getItem(itemName));
                } catch (Item.NoItemException e) {
                    throw new IllegalSaveFormatException("No such item '" +
                        itemName + "'");
                }
            }
	   
        }
	if(s.hasNextLine()) {
	    String scoreLine = s.nextLine().substring(SCORE_LEADER.length());
	    totalScore = Integer.parseInt(scoreLine);
	}
	
    }
/**
 *Saves the file name and the inventory. Prints out the inventory. 
 *@param saveName the name of the file being saved
 *@throws IOException if the file cannot be saved.
 */
    void store(String saveName) throws IOException {
        String filename = saveName + SAVE_FILE_EXTENSION;
        PrintWriter w = new PrintWriter(new FileWriter(filename));
        w.println(SAVE_FILE_VERSION);
        dungeon.storeState(w);
        w.println(ADVENTURER_MARKER);
        w.println(CURRENT_ROOM_LEADER + adventurersCurrentRoom.getTitle());
        if (inventory.size() > 0) {
            w.print(INVENTORY_LEADER);
            for (int i=0; i<inventory.size()-1; i++) {
                w.print(inventory.get(i).getPrimaryName() + ",");
            }
            w.println(inventory.get(inventory.size()-1).getPrimaryName());
        }
	w.print(SCORE_LEADER + totalScore);
        w.close();
    }
/**
 *Simply initializes Dungeon.
 * @param dungeon the dungeon.
 */
    void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
        adventurersCurrentRoom = dungeon.getEntry();
    }
/**
 *Returns the names of the items located in the players inventory. 
 */
    ArrayList<String> getInventoryNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Item item : inventory) {
            names.add(item.getPrimaryName());
        }
        return names;
    }
/**
 *Add an item to the player's inventory. 
 */
    void addToInventory(Item item) /* throws TooHeavyException */ {
        inventory.add(item);
    }
/**
 *Remove an item from the player's inventory.
 */
    void removeFromInventory(Item item) {
        inventory.remove(item);
    }
/**
 *Looks for an item with a particular name or alias, and if there is not an item present in the room or within the inventory,  throw an exception. 
 *@throws NoItemException if there are no items within the inventory.
 */
    Item getItemInVicinityNamed(String name) throws Item.NoItemException {

        // First, check inventory.
        for (Item item : inventory) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        // Next, check room contents.
        for (Item item : adventurersCurrentRoom.getContents()) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        throw new Item.NoItemException();
    }
/**
 *Returns an item from the inventory,using its name or alias, and throws an exception if the item called does not exist. 
 *@throws NoItemException if there are no items within the inventory.
 */
    Item getItemFromInventoryNamed(String name) throws Item.NoItemException {

        for (Item item : inventory) {
            if (item.goesBy(name)) {
                return item;
            }
        }
        throw new Item.NoItemException();
    }
/**
 *Returns the room that the player is currently in.
 */
    Room getAdventurersCurrentRoom() {
        return adventurersCurrentRoom;
    }
/**
 *Sets the current room to equal the room that the player is in.
 */
    void setAdventurersCurrentRoom(Room room) {
        adventurersCurrentRoom = room;
    }
/**
 *Returns the dungeon.
 */
    Dungeon getDungeon() {
        return dungeon;
    }
/**
 * Adds up the adventure's score.
 */   
    void addAdventurersScore(int score){
        totalScore = totalScore + score;
    }
/**
 * Returns the adventure's score.
 */ 
    int getAdventurersScore(){
   	 return totalScore;
    }
/**
 * Returns the adventure's health.
 */ 
    int getAdventurersHealth() {
	 return totalHealth;
    }
/**
 * Changes the adventurers health to reflect any damage that was taken.
 */ 
    void woundAdventurer(int health) {
	 totalHealth = totalHealth - health;
    }
}
