

/** The TakeCommand class is an extension of the Command class used for when a player wants to take an item.
=======
 * The TakeCommand is a type of {@link Command} that allows the user to take 
 * any {@link Item} in a the current room and add it to their inventory.
 *
 * @author Kyle Kraisser
 */
class TakeCommand extends Command {

    private String itemName;
    

    /**
     * The constructor for {@link TakeCommand} which takes one parameter.
     * @param itemName stores the name of the item to be taken
     */
    TakeCommand(String itemName) {
        this.itemName = itemName;
    }
    /** 
     * Based on the presence of an item, returns specific messages to the user: prompting the user for more information, telling them their load is too heavy, or taking the item
     * and placing it in inventory telling the user that is done.
     * @return a string of "take what" if no item name is given
     * @return a string that states that the load is too heavy if a given item plus what is in inventory exceeds 40
     * @return a string that states a specific item has been taken
     * @return a string that tells the user an item they're carrying has already been taken
     * @return a string that tells the user the item they are attempting to take is not in the current {@link Room}
     */
    public String execute() {
        if (itemName == null || itemName.trim().length() == 0) {
            return "Take what?\n";
        }
        try {
            Room currentRoom = 
                GameState.instance().getAdventurersCurrentRoom();
            Item theItem = currentRoom.getItemNamed(itemName);
            if (theItem.getWeight() + 
                GameState.instance().getAdventurersCurrentWeight() > 40) {
                return "Your load is too heavy.\n";
            }
            GameState.instance().addToInventory(theItem);
            currentRoom.remove(theItem);
            return theItem.getPrimaryName() + " taken.\n";
        } catch (Item.NoItemException e) {
            // Check and see if we have this already. If no exception is
            // thrown from the line below, then we do.
            try {
                GameState.instance().getItemFromInventoryNamed(itemName);
                return "You already have the " + itemName + ".\n";
            } catch (Item.NoItemException e2) {
                return "There's no " + itemName + " here.\n";
            }
        }
    }
}
