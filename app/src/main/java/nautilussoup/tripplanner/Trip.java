package nautilussoup.tripplanner;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arjun Gupta on 4/21/2017.
 */
public class Trip {
    private String tripName;
    private Budget tripBudget;
    private Location tripLocation;
    private List tripMembers;

    public Trip(String name, double maxBudget, Location tripLocation) {
        tripName = name;
        tripBudget = new Budget(maxBudget);
        //To-Do set location
        tripMembers = new ArrayList<Person>();
    }
}
