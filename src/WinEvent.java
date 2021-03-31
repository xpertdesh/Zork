/**
 * The WinEvent is a type of {@link Event} that triggers and causes the user to 'win' the 
 * dungeon, exitting them and displaying a message of their achievement.
 *
 * @author Kyle Kraisser, Yasmeen Alhinty
 */
class WinEvent extends Event {

	WinEvent() {
	}

	/**
	 * Upon interacting with a specific item, this event will execute, causing the user
	 * to win the game and displays a message to the user before exiting the dungeon.
	 * @return a message to the user informing them that they have won the game
	 */
	
	public String execute() {	
		System.exit(0);
		return "You win!";
	}
}

