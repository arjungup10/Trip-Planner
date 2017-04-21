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
        earliestStartTime = String.valueOf(startHour).toString() + ":" + String.valueOf(startMin).toString();
        latestEndTime = String.valueOf(endHour).toString() + ":" + String.valueOf(endMin).toString();
        //TO-DO set location
    }
}
