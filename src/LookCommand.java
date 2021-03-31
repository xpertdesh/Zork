/**
 * LookCommand is a type of {@link Command} in which the user can call to see the current {@link Room}
 * description again.
 * @author Kyle Kraisser
 */
class LookCommand extends Command {


    LookCommand() {
    }

    /**
     * Returns a description of the current room the user is in.
     * @return returns the string description of the current room
     */
    public String execute() {
        Room currRoom = GameState.instance().getAdventurersCurrentRoom();
        return "\n" + currRoom.describe(true) + "\n";
    }
}
