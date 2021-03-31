/**
 * @author Kyle Kraisser
 */
import java.util.List;
import java.util.Arrays;

/**
 * The CommandFactory is a singleton class that allows for the program
 * to parse what the user types on the command line and pass them to the appropriate
 * {@link Command} based on what is typed.
 */
public class CommandFactory {

    private static CommandFactory theInstance;
    public static List<String> MOVEMENT_COMMANDS = 
        Arrays.asList("n","w","e","s","u","d" );

    /**
     * The constructor for {@link CommandFactory} allows for the initialization of an 
     * instance of {@link CommandFactory} if one does not already exist.
     * @return the instance of command factory initialized
     */
    public static synchronized CommandFactory instance() {
        if (theInstance == null) {
            theInstance = new CommandFactory();
        }
        return theInstance;
    }

    private CommandFactory() {
    }

    /**
     * This method allows the program to essentially 'read' what is input from the user
     * and 'pass' off to each specific {@link Command} based on what is typed.
     * @return if "look" is typed a new look command is created
     * @return if "save" is typed a new save command is created
     * @return if "take" is typed a new take command is created
     * @return if "drop" is typed a new drop command is created
     * @return if "i" or "inventory" is typed a new inventory command is created
     * @return if "u, d, n, e, s, w" are typed a new movement command is created
     * @return if the command has 2 parts a new item specific command is created
     * @return if none of the above conditions are met a new unknown command is created
     */
    public Command parse(String command) {
        String parts[] = command.split(" ");
        String verb = parts[0];
        String noun = parts.length >= 2 ? parts[1] : "";
        if (verb.equals("look")) {
            return new LookCommand();
        }
        if (verb.equals("save")) {
            return new SaveCommand(noun);
        }
        if (verb.equals("take")) {
            return new TakeCommand(noun);
        }
        if (verb.equals("drop")) {
            return new DropCommand(noun);
        }
	if (verb.equals("score")) {
	    return new ScoreCommand();
	}
	if (verb.equals("health")) {
		return new HealthCommand();
	}
        if (verb.equals("i") || verb.equals("inventory")) {
            return new InventoryCommand();
        }
        if (MOVEMENT_COMMANDS.contains(verb)) {
            return new MovementCommand(verb);
        }
        if (parts.length == 2) {
            return new ItemSpecificCommand(verb, noun);
        }
        return new UnknownCommand(command);
    }
}
