/**
 * @author Andres Cabrera
 */

/**
 * The ScoreCommand is a type of {@link Command} that prints a message to the user, depending on their current score.
 */
class ScoreCommand extends Command{

    ScoreCommand(){
    }

    /**Returns a String corresponding to the current score of the player.
     * @return a String that contains the current score with the corresponding message
     */
    public String execute(){
	int score = GameState.instance().getAdventurersScore();
        String msg="";
	if(score <= 0){msg = "Servant";}
	else if(score < 20){msg = "Peasant";}
	else if(score < 40){msg = "Knight";}
	else if(score < 60){msg = "Lord";}
	else if(score < 80){msg = "King";}	
	else if(score < 100){msg = "Semi-god";}
	//Possible easter egg if it was a high scoring gamve
	else if(score >= 100){msg = "God";}
	
	return "You have " + score + " points. This gives you the rank of " + msg + ".\n";
    }

}
