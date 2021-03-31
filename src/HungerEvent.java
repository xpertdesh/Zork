/**
 * The HungerEvent is a type of {@link Event} that causes the user to lose health 
 * every 'turn' or {@link MovementCommand} health can be replenished with food {@link Item} 
 * littered around the given dungeon.
 *
 * @author Kyle Kraisser
 */
class HungerEvent extends Event {

	private int health;


	/**
	 * The constructor for {@link HungerEvent} which takes one parameter.
	 * @param health stores the amount of health lost from hunger
	 */
	HungerEvent(int health) {
		this.health = health;

	}

	/**
	 * Returns a string to the user based on how much life they have left (there will be more
	 * return tags added as we decide what will be returned).
	 * @return a string to the user that states that he or she is perfectly healthy
	 * @return a string to the user that states that he or she can eat to regain life lost
	 */
	public String execute() {

	return "";
	}


}
