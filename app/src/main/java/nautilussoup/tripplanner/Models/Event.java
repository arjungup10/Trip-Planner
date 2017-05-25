package nautilussoup.tripplanner.Models;

import android.location.Location;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Created by Arjun Gupta on 4/21/2017.
 */
public class Event implements Serializable {
    private String eventName;
    private GregorianCalendar eventTime;

    public Event(String name) {
        eventName = name;
        eventTime = new GregorianCalendar();
    }

    public Event(String name, Location eventLocation) {
        eventName = name;
        eventTime = new GregorianCalendar();
        //TO-DO set location
    }

    public String getName() {
        return eventName;
    }

    public GregorianCalendar getEventTime() {
        return eventTime;
    }
}
