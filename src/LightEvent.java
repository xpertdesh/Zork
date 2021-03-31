/**
 * The LightEvent is a type of {@link Event} that causes a room to be 'dark' and thus
 * not able to be interacted with or described until a light source from an item is used in 
 * that specific room.
 *
 * @author Kyle Kraisser
 */
class LightEvent extends Event {

	LightEvent() {

	}

	/**
	 * This method causes a specific room to be 'dark' and thus not able to be interacted 
	 * with until a light source is provided within the room.
	 * @return a message to the user that the room is dark and that they cannot advance
	 * @return a message to the user that they have successfully lit up the room
	 */
	public String execute() {


	return "";
	}

}
