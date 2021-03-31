
import java.util.ArrayList;
/**
 * InventoryCommand is a type of {@link Command} in which a player is able to view items that they have collected throughout the course of the game.
 * @author Yasmeen 
*/
class InventoryCommand extends Command {

    InventoryCommand() {
    }
/**
 * This method returns either a statement refering to an empty inventory or a statement listing the items contained within the inventory
 * 
 */
    public String execute() {
        ArrayList<String> names = GameState.instance().getInventoryNames();
        if (names.size() == 0) {
            return "You are empty-handed.\n";
        }
        String retval = "You are carrying:\n";
        for (String itemName : names) {
            retval += "   A " + itemName + "\n";
        }
        return retval;
    }
}
