/**
 * The MovementCommand class is a type of {@link Command} 
 *  and allows the user to 'move' between {@link Room} objects
 *  using an input of a cardinal direction.
 * 
 * @author Kyle Kraisser
  */
class MovementCommand extends Command {

    private String dir;

 /**
 * The constructor for {@link MovementCommand} which Takes a parameter of direction.
 * @param dir stores the direction
 */                     
    MovementCommand(String dir) {
        this.dir = dir;
    }

/**
 * Executes as a string and returns the next Rooms description or tells the user they cannot go that way.
 * @return the message if a nextRoom is found
 * @return the message to user if there is no Room in the direction input
 */
    public String execute() {
        Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
        Room nextRoom = currentRoom.leaveBy(dir);
        if (nextRoom != null) {  // could try/catch here.
            GameState.instance().setAdventurersCurrentRoom(nextRoom);
            return "\n" + nextRoom.describe() + "\n";
        } else {
            return "You can't go " + dir + ".\n";
        }
    }
}
