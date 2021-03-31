/**
 * UnknownCommand is a type of {@link Command} that allows the program to inform the 
 * user that a specific command could not be understood.
 *
 * @author Kyle Kraisser
 */
class UnknownCommand extends Command {

    private String bogusCommand;

    /**
     * The constructor for {@link UnknownCommand} which contains a parameter.
     * @param bogusCommand holds what the user typed and was not understood as a command
     */
    UnknownCommand(String bogusCommand) {
        this.bogusCommand = bogusCommand;
    }

    /**
     * Returns a string that informs the user that whatever was typed was not understood.
     * @return a message to the user saying that what was typed was not understood
     */
    String execute() {
        return "I'm not sure what you mean by \"" + bogusCommand + "\".\n";
    }
}
