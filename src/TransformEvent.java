/**
 *@author Andres Cabrera
 */


/** The TransformEvent is a type of Event that changes a {@link Item} into a different {@link Item}.
 */
class TransformEvent extends Event {

	private String oldItemName, newItemName;


	/**
	 * @param oldItem the {@link Item} that is being transformed.
	 * @param newItem the {@link Item} it will become.
	 */
	TransformEvent(String oldItem, String newItem) {
		oldItemName = oldItem;
		newItemName = newItem;
	}
	

	/** Swaps the old {@link Item} by the new {@link Item}. The old item is removed from the {@link Dungeon} and the new {@link Item} is added to the inventory.
	 * @return message that tells that the new {@link Item} has been taken.
	 */
	public String execute() {
	    try{
	    Item oldItem = GameState.instance().getItemInVicinityNamed(oldItemName);
	    Item newItem = GameState.instance().getDungeon().getItem(newItemName);
	    GameState.instance().removeFromInventory(oldItem);
	    GameState.instance().addToInventory(newItem);
	    
	    return CommandFactory.instance().parse("take " + newItemName).execute();
	    } catch(Item.NoItemException e){
	        return "No item found";
	    }
	}

}
