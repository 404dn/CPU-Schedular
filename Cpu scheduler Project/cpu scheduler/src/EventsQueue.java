import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;

public class EventsQueue {

	public static ArrayList<Event> queueEvents = new ArrayList<Event>();
		
	public static void addToList(Event event) {
		queueEvents.add(event);
	}
	
	
}
