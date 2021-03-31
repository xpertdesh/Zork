/**
 * @author Kyle Kraisser
 */
import java.util.Scanner;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class allows for the Item object with a specific weight
 * to be added into the Zork game, along with a verb that allows
 * the Item to be interacted with by the user. 
 */ 
public class Item {


/** Is called when a {@link Item} is attempted to be read from an invalid file.
 */ 
    static class NoItemException extends Exception {}

    private String primaryName;
    private int weight;
    private Hashtable<String,String> messages;
    private Hashtable<String, ArrayList<String>> events;
    private Set<String> aliases;
    private ArrayList<String> eventArray;

/**
 * @param s represents the Scanner object that will read Items from a file
 * @throws NoItemException if the .zork file is incorrectly written in the Item section
 * @throws Dungeon.IllegalDungeonFormatException if the .zork file is incorrectly written
 */ 
    Item(Scanner s) throws NoItemException,
        Dungeon.IllegalDungeonFormatException {

        messages = new Hashtable<String,String>();
        aliases = new HashSet<String>();
	events = new Hashtable<String, ArrayList<String>>();



        // Read item name.
        String names[] = s.nextLine().split(",");
        if (names[0].equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoItemException();
        }
        primaryName = names[0];
        for (int i=1; i<names.length; i++) {
            aliases.add(names[i]);
        }

        // Read item weight.
        weight = Integer.valueOf(s.nextLine());

        // Read and parse verbs lines, as long as there are more.
        String verbLine = s.nextLine();
        while (!verbLine.equals(Dungeon.SECOND_LEVEL_DELIM)) {
            if (verbLine.equals(Dungeon.TOP_LEVEL_DELIM)) {
                throw new Dungeon.IllegalDungeonFormatException("No '" +
                    Dungeon.SECOND_LEVEL_DELIM + "' after item.");
            }


            String[] verbParts = verbLine.split(":");
	    String verbEvent = verbParts[0];
	    String verbMessage = verbParts[1];
	    String verb = verbEvent;

	    if(verbEvent.contains("[")){
		String verbSplitter = verbEvent.replace("[", "OK");
	        String[] eventSplit = verbSplitter.split("OK");
		String eventsLine = eventSplit[1];
		verb = eventSplit[0];
		String[] temp;
		String[] eventsList;
		eventsLine = eventsLine.replace("]", "");
		eventArray = new ArrayList<String>();


		if(eventsLine.contains(",")){
		    temp = eventsLine.split(",");
		    for(String element: temp){
		        eventArray.add(element);
		    }    
		} else{
		    eventArray.add(eventsLine);
		}
		
		events.put(verb, eventArray);	
	    }

            messages.put(verb, verbMessage);


            
            verbLine = s.nextLine();
        }
    }
    
    	
    /**
     * Returns the carrying weight of this Item.
     * @return the weight of the Item
     */
    int getWeight() {
        return weight;
    }

    /**
     * Returns true or false whether or not the item name matches with its primaryName and alias.
     * @param name holds a string of what the Item is named
     * @return true if the primaryName is the same as name
     * @return true if the alias matches with the given Item name
     * @return false if neither of the conditions are met
     */
    boolean goesBy(String name) {
        if (this.primaryName.equals(name)) {
            return true;
        }
        for (String alias : this.aliases) {
            if (alias.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     *  Returns the primary name of this Item.
     *  @return the primary name of any given Item
     */
    String getPrimaryName() { return primaryName; }

    /**
     * Returns the messages associated with the verb that can be acted on the Item.
     * @param verb holds the verb of the given Item.
     * @return the message for the given verb
     */
    public String getMessageForVerb(String verb) {
        return messages.get(verb);
    }

    /**
     * Returns the primaryName variable for an Item when it is called in a string for readability.
     * @return the primary name of the Item
     */ 
    public String toString() {
        return primaryName;
    }

    public ArrayList<String> getEvents(String verb){
        return events.get(verb);
    }
}
