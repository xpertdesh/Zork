/**
 * @author Andres Cabrera
 */


/** The singleton EventFactory class intantiates different {@link Event} objects that are attached to a specific {@link Command} object.
 */
public class EventFactory{

    public static EventFactory theInstance;

    /**Instantiates only one EventFactory object.
     */
    public static synchronized EventFactory instance(){
	    if(theInstance == null){
	        theInstance = new EventFactory();
	    }
	    return theInstance;
    } 

    private EventFactory(){}


    /** Returns a {@link Event} object based on the String given by the user and it will have an effect on the {@link GameState}.
     * @param event it contains the name of the possible {@link Event} object.
     * @return specific {@link Event} object.
     */
    Event parse(String event, String oldItem){
	String newItem="";
        String eventName="";	
        int points=0;
	
	if(event.contains("(")){
	String parentheses = event.replace("(", "OK");
	String[] eventsList = parentheses.split("OK");
	eventName = eventsList[0];
	String effect = eventsList[1].replace(")", "");

	    if(effect.matches("[0-9]+")){
	        points = Integer.parseInt(effect);
	    } else{
	        newItem = effect;
	    }
	}


	if(eventName.equals("Score")){
	    return new ScoreEvent(points);
	}  if(eventName.equals("Wound")){
	    return new WoundEvent(points);
	}  if(eventName.equals("Transform")){
	    return new TransformEvent(oldItem, newItem);
	}  if(event.equals("Win")){
	    return new WinEvent();
	}  if(event.equals("Drop")){
	    return new DropEvent(oldItem);
	}  if(event.equals("Disappear")){
	    return new DisappearEvent(oldItem);
	}  if(event.equals("Teleport")){
	    return new TeleportEvent();
	}  if(event.equals("Die")){
	    return new DieEvent();
	}

	return new DieEvent();
    }

}
