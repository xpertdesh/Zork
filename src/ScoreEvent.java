/**
 * The ScoreEvent is a type of {@link Event} that allows for a running total of points that
 * can be accessed by the user using the {@link ScoreCommand}, this event is mainly for items
 * that cause the score to be increased.
 *
 * @author Kyle Kraisser
 */
class ScoreEvent extends Event {

	private int points;

	/**
	 * The constructor for {@link ScoreEvent} which takes one parameter.
	 * @param points stores the number of points gained from interacting with a certain item
	 */
	ScoreEvent(int points) {
	    this.points = points;
	}


	/**
	 * This method will output the number of points 'scored' and add it to the running total of 
	 * points that the user has accumulated.
	 * @return a string that tells the user a certain number of points has been added to their score
	 */
	public String execute() {
	    GameState.instance().addAdventurersScore(points);
	    if(points < 0){
	    return "You lost " + points + " points\n";
	    } 
	    return "You earned " + points + " points\n";
	}


}
