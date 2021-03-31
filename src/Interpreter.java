/**
 * @author Andres Cabrera
 */


import java.util.Scanner;

/** The Interpreter class reads in a file to create/restore a dungeon and prompts the user for input in order to continue playing.
 */
public class Interpreter {

    private static GameState state; // not strictly necessary; GameState is 
                                    // singleton

    public static String USAGE_MSG = 
        "Usage: Interpreter zorkFile.zork|saveFile.sav.";
    /**Instantiates a new {@link Dungeon} object based on the file given and prompts the player for input every turn. If there is a problem with the file format, an error is printed and it exits the program. 
     */
    public static void main(String args[]) {

        if (args.length < 1) {
            System.err.println(USAGE_MSG);
            System.exit(1);
        }

        String command;
        Scanner commandLine = new Scanner(System.in);

        try {
            state = GameState.instance();
            if (args[0].endsWith(".zork")) {
                state.initialize(new Dungeon(args[0]));
                System.out.println("\nWelcome to " + 
                    state.getDungeon().getName() + "!");
            } else if (args[0].endsWith(".sav")) {
                state.restore(args[0]);
                System.out.println("\nWelcome back to " + 
                    state.getDungeon().getName() + "!");
            } else {
                System.err.println(USAGE_MSG);
                System.exit(2);
            }

            System.out.print("\n" + 
                state.getAdventurersCurrentRoom().describe() + "\n");

            command = promptUser(commandLine);

            while (!command.equals("q")) {

                System.out.print(
                    CommandFactory.instance().parse(command).execute());

                command = promptUser(commandLine);
            }

            System.out.println("Bye!");

        } catch(Exception e) { 
            e.printStackTrace(); 
        }
    }


    /**
     * @return input of the user that serves to instatiate a {@link Command} object.
     */
    private static String promptUser(Scanner commandLine) {
        System.out.print("> ");
        return commandLine.nextLine();
    }

}
