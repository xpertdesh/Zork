/**
 * The SaveCommand is a type of {@link Command} that allows the user to 
 * save their progress in any given .zork file, creating a .sav file.
 *
 * @author Kyle Kraisser
 */
class SaveCommand extends Command {

    private static String DEFAULT_SAVE_FILENAME = "zork";

    private String saveFilename;

    /**
     * The constructor for {@link SaveCommand} which takes one parameter and 
     * establishes the save filename.
     * @param saveFilename stores the save filename given by the user
     */
    SaveCommand(String saveFilename) {
        if (saveFilename == null || saveFilename.length() == 0) {
            this.saveFilename = DEFAULT_SAVE_FILENAME;
        } else {
            this.saveFilename = saveFilename;
        }
    }

    /**
     * Returns the user a message regarding the save file.
     * @return this returns that the data was successfully saved to whatever the filename is
     * @return nothing if an exception is hit that does not allow the file to save
     */
    public String execute() {
        try {
            GameState.instance().store(saveFilename);
            return "Data saved to " + saveFilename +
                GameState.SAVE_FILE_EXTENSION + ".\n";
        } catch (Exception e) {
            System.err.println("Couldn't save!");
            e.printStackTrace();
            return "";
        }
    }
}
