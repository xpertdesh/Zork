/**
 * @author Andres Cabrera, Yasmeen 
 */

/** It removes a certain {@link Item} from the GameState and Dungeon.
 */
class DisappearEvent extends Event {

	private String item;

	/**Constructs a DisappearEvent object that will remove the specified {@link Item} from the {@link Dungeon} and {@link GameState}.
	 * @param item the object that will cease to exist forever.
	 */
	DisappearEvent(String item) {
		this.item = item;
	}


	/** Removes the passed item from the {@link Dungeon} and {@link GameState}. 
	 * @return a message corresponding to the {@link Item} object removed.
	 */
	public String execute() {


	return "";
	}

}
