/**
 * The HealthCommand is a type of {@link Command} that prints a message to the user, depending on their current health.
 *
 * @author Kyle Kraisser
 */
class HealthCommand extends Command {



	HealthCommand() {

	}

	/**
	 * Returns a String corresponding to the current health of the player.
	 * @return a String that contains a message to the player with regard to current health
	 */
	public String execute() {
	int health = GameState.instance().getAdventurersHealth();
	String msg = "";
	if (health >= 10) {msg = "You feel like a million bucks!";}
	else if (health < 10 && health >= 8) {msg = "You feel a little stiff and sore.";}
	else if (health < 8 && health >= 5) {msg = "Your bumps and bruises seriously ail you.";}
	else if (health < 5 && health >= 2) {msg = "You are pretty badly injured.";}
	else if (health < 2 && health > 0) {msg = "You are dangerously close to death!";}
	
	return msg + "\n";
	}

}
