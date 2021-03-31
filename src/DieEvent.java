/**
 * The DieEvent is a type of {@link Event} that causes the player to lose the game and 
 * subsequently ends said game; this can be caused by the user running out of health or from 
 * interacting with a specific item.
 *
 * @author Kyle Kraisser
 */
class DieEvent extends Event {

	DieEvent() {

	}


	/**
	 * This method will output to the user the outcome of their interaction with a certain item, 
	 * or in the certain case that they run out of health, will display the "game over" message.
	 * @return string to the user the cause of death, and that the game is over
	 */
	public String execute() {
		System.out.println("You got a game over! Better luck next time!");
		System.exit(0);
		return "";
	}

}
