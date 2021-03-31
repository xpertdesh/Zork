/**
 *DropCommand is a type of {@link Command} that allows the player to drop an item that they are carrying with them. 
 *The command affects the {@link GameState} as the item will now be located in the {@link Room} as opposed to with the player in their inventory.
 *@author Yasmeen 
 */
class DropCommand extends Command {

    private String itemName;
/**
 *Constructs a name of an item that can be dropped into a room.
 *@param itemName the name of the item that will be dropped into the room.
  */
    DropCommand(String itemName) {
        this.itemName = itemName;
    }
/**
 *The excecute method either drops an item from the inventory or tells the user that the item they attempted to drop does not exist. 
 *If the item that the user typed in is not an item that the game recognizes, throw the NoItemException.
 *If the item exists, drop the item in the room and remove it from the inventory. 
 *@throws NoItemException if the called item is not within the players inventory. 
 */
    public String execute() {
        if (itemName == null || itemName.trim().length() == 0) {
            return "Drop what?\n";
        }
        try {
            Item theItem = GameState.instance().getItemFromInventoryNamed(
                itemName);
            GameState.instance().removeFromInventory(theItem);
            GameState.instance().getAdventurersCurrentRoom().add(theItem);
            return theItem.getPrimaryName() + " dropped.\n";
        } catch (Item.NoItemException e) {
            return "You don't have a " + itemName + ".\n";
        }
    }
}
