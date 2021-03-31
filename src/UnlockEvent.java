/**
 * @author Andres Cabrera
 */


/**The UnlockEvent is a type of {@link Event} that unlocks a locked {@link Room} in the {@link Dungeon}.
 */
class UnlockEvent extends Event {

	private String lockedRoom;

	/** 
	 * @param lockedRoom contains the {@link Room} object that is locked.
	 */
	UnlockEvent(String lockedRoom) {
		this.lockedRoom = lockedRoom;
	}


	/** Returns an empty String and it tries to unlock the {@link Room} that cannot be accessed by other that a special {@link Item} object.
	 */
	public String execute() {


	    return "";
	}
	

}
