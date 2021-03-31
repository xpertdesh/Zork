/**
 * @author Andres Cabrera
 */


import java.util.Scanner;


/** Connects one {@link Room} object to another {@link Room} object.
 */
public class Exit {
    

    /**Is called when a {@link Exit} object is not found.
     */	
    class NoExitException extends Exception {}

    private String dir;
    private Room src, dest;
	

    /** Returns a {@link Exit} object that connects a source {@link Room} with a destination {@link Room} in certain direction.
     * @param dir the direction this {@link Exit} faces.
     * @param src the source {@link Room} of this {@link Exit}.
     * @param dest the destination {@link Room} of this {@link Exit}.
     */
    Exit(String dir, Room src, Room dest) {
        init();
        this.dir = dir;
        this.src = src;
        this.dest = dest;
        src.addExit(this);
    }

    /** Given a Scanner object positioned at the beginning of an "exit" file
        entry, read and return an Exit object representing it. 
        @param d The dungeon that contains this exit (so that Room objects 
        may be obtained.)
        @throws NoExitException The reader object is not positioned at the
        start of an exit entry. A side effect of this is the reader's cursor
        is now positioned one line past where it was.
        @throws IllegalDungeonFormatException A structural problem with the
        dungeon file itself, detected when trying to read this room.
     */
    Exit(Scanner s, Dungeon d) throws NoExitException,
        Dungeon.IllegalDungeonFormatException {

        init();
        String srcTitle = s.nextLine();
        if (srcTitle.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoExitException();
        }
        src = d.getRoom(srcTitle);
        dir = s.nextLine();
        dest = d.getRoom(s.nextLine());
        
        // I'm an Exit object. Great. Add me as an exit to my source Room too,
        // though.
        src.addExit(this);

        // throw away delimiter
        if (!s.nextLine().equals(Dungeon.SECOND_LEVEL_DELIM)) {
            throw new Dungeon.IllegalDungeonFormatException("No '" +
                Dungeon.SECOND_LEVEL_DELIM + "' after exit.");
        }
    }

    /** Initializes new variables for the Exit object.
     */
    private void init() {
    }


    /**Returns information about the direction this {@link Exit} can go and the {@link Room} it leads to.
     */
    String describe() {
        return "You can go " + dir + " to " + dest.getTitle() + ".";
    }


    /**Returns the direction that this {@link Exit} faces.
     */
    String getDir() { return dir; }


    /**Returns the {@link Room} that makes this {@link Exit}.
     */
    Room getSrc() { return src; }


    /**Returns the {@link Room} that this {@link Exit} leads to.
     */
    Room getDest() { return dest; }
}
