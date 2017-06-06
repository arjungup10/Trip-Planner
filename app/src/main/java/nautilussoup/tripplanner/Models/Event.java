package nautilussoup.tripplanner.Models;

import android.location.Location;

import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.Place;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Event implements Serializable {
    private static final long serialVersionUID = 48L;
    private String eventName;
    private GregorianCalendar startTime;
    private GregorianCalendar endTime;
    private String eventLocation;

    //Placeholder constructor for view testing
    public Event(String name) {
        eventName = name;
        startTime = new GregorianCalendar();
        endTime = new GregorianCalendar();
    }

    public Event(String name, GregorianCalendar start, GregorianCalendar end) {
        eventName = name;
        startTime = start;
        endTime = end;
    }

    public Event(String name, String targetLocation, GregorianCalendar start, GregorianCalendar end) {
        eventName = name;
        eventLocation = targetLocation;
        startTime = start;
        endTime = end;
    }

    public String getName() {
        return eventName;
    }

    public GregorianCalendar getStartTime() {
        return startTime;
    }

    public GregorianCalendar getEndTime() {
        return endTime;
    }

    public String getEventLocationId() {
        return eventLocation;
    }
}
