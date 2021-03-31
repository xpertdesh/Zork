/*
 *ItemSpecificCommand is a type of {@link Command} in which an {@link Item} can carry out a predetermined action that it contains.  
 *@author Yasmeen
 */

import java.util.ArrayList;

class ItemSpecificCommand extends Command {

    private String verb;
    private String noun;
                        
/**
 *The constructor for {@link ItemSpecificCommand} which contains two paramaters. 
 *@param verb used for the excecute method in which it calls an action that the called item does.
 *@param noun used for the excecute method where it calls the item. 
 */
    ItemSpecificCommand(String verb, String noun) {
        this.verb = verb;
        this.noun = noun;
    }
/**
 *Excecute does one of three things: it throws an exception because there are no items contained within the inventory, returns a statement saying something to the effect of the action on the noun is unable to be carried out, or works properly and returns the message associated with the command that was called on the item. 
 *@throws NoItemException if there is no item contained within the inventory.
 */
    public String execute() {
        
        Item itemReferredTo = null;
        try {
            itemReferredTo = GameState.instance().getItemInVicinityNamed(noun);    
        } catch (Item.NoItemException e) {
            return "There's no " + noun + " here.\n";
        }

            ArrayList<String> eventList = itemReferredTo.getEvents(verb);
	    String text="";
	   


	    if(eventList != null){
	        for(int i=0; i<eventList.size(); i++){
	            String event = eventList.get(i);
		    if(event != null && event.length() > 0){
		         text += EventFactory.instance().parse(event, itemReferredTo.getPrimaryName()).execute();
		    }
	        } 
	    } 

         
        String msg = itemReferredTo.getMessageForVerb(verb) + "\n" + text;
        return (msg == null ? 
            "Sorry, you can't " + verb + " the " + noun + "." : msg) + "\n";
    }
}
