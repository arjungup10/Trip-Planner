package nautilussoup.tripplanner.Models;

import android.location.Location;

import java.io.Serializable;

/**
 * Created by Arjun Gupta on 4/21/2017.
 */
public class Event implements Serializable {
    private String eventName;
    private String earliestStartTime;
    private String latestEndTime;
    private Location eventLocation;

    public Event(String name, int startHour, int startMin, int endHour, int endMin, Location eventLocation) {
        eventName = name;
        earliestStartTime = String.valueOf(startHour) + ":" + String.valueOf(startMin);
        latestEndTime = String.valueOf(endHour) + ":" + String.valueOf(endMin);
        //TO-DO set location
    }
}
