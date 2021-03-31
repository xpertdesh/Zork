/**
 * The DropEvent is a type of {@link Event} that allows for the dropping of 
 * an item as caused by a Event in the game.
 *
 * @author Kyle Kraisser
 */
class DropEvent extends Event {

	private String item;

	/**
	 * The constructor for {@link DropEvent} which takes one parameter.
	 * @param item stores the name of the item to be dropped
	 */
	DropEvent(String item) {
		this.item = item;
	}

	/**
	 * Based on the specific item, this method causes that item to be dropped from
	 * the user inventory and into the current {@link Room}, and after doing so, prints a message
	 * to the user.
	 * @return the message to the user that states a specific item was dropped
	 */
	public String execute() {


	return "";
	}

}
