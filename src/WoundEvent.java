/**
 * The WoundEvent is a type of {@link Event} that causes the user to lose health whenever this 
 * event is triggered, whether it be from interacting with an item or anything else.
 *
 * @author Kyle Kraisser
 */
class WoundEvent extends Event {

	private int health;

	/**
	 * The constructor for {@WoundEvent} which takes one parameter.
	 * @param health stores the amount of health the player loses from the wound event
	 */
	WoundEvent(int health) {
		this.health = health;

	}

	/**
	 * This method outputs a message to the user explaining how and why they took damage.
	 * @param return a string that tells the user why they took damage and how much they took
	 */
	public String execute() {
	GameState.instance().woundAdventurer(health);
	if (health < 0) {
		return "You gained some health.";
	}
	return "Ouch, you lost some health!";

	}
}	
