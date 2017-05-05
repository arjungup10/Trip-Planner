package nautilussoup.tripplanner;

import android.location.Location;

/**
 * Created by Arjun Gupta on 4/21/2017.
 */
public class Event {
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
