package nautilussoup.tripplanner.Models;

import android.location.Location;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nautilussoup.tripplanner.Models.Budget;
import nautilussoup.tripplanner.Models.Event;
import nautilussoup.tripplanner.Models.Person;

/**
 * Created by Arjun Gupta on 4/21/2017.
 */
public class Trip implements Serializable {
    private String tripName;
    private Budget tripBudget;
    private Location tripLocation;
    private List tripMembers;
    private List events;

    public Trip(String name, double maxBudget) {
        tripName = name;
        tripBudget = new Budget(maxBudget);
        tripMembers = new ArrayList<Person>();
        events = new ArrayList<Event>();
    }

    public Trip(String name, double maxBudget, Location tripLocation) {
        tripName = name;
        tripBudget = new Budget(maxBudget);
        //To-Do set location
        tripMembers = new ArrayList<Person>();
        events = new ArrayList<Event>();
    }

    public void addMember(String name) {
        tripMembers.add(new Person(name));
    }

    public void addMember(String name, Location home) {
        tripMembers.add(new Person(name, home));
    }

    public void addEvent(String name) {
        events.add(new Event(name));
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public Budget getTripBudget() {
        return tripBudget;
    }

    public void setTripBudget(Budget tripBudget) {
        this.tripBudget = tripBudget;
    }

    public List getTripMembers() {
        return tripMembers;
    }

    public List getEvents() {
        return events;
    }

}
