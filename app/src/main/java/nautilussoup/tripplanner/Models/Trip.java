package nautilussoup.tripplanner.Models;

import android.location.Location;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Trip implements Serializable {
    private static final long serialVersionUID = 56L;
    private String tripName;
    private Budget tripBudget;
    private Location tripLocation;
    private ArrayList<Person> tripMembers;
    private ArrayList<Event> events;

    public Trip(String name, double maxBudget) {
        tripName = name;
        tripBudget = new Budget(maxBudget);
        Location TripLocation;
        tripMembers = new ArrayList<Person>();
        events = new ArrayList<Event>();
    }

    public Trip(String name, double maxBudget, Location targetLocation) {
        tripName = name;
        tripBudget = new Budget(maxBudget);
        tripLocation = targetLocation;
        //To-Do set location
        tripMembers = new ArrayList<Person>();
        events = new ArrayList<Event>();
    }

    public void addMember(String name) {
        tripMembers.add(new Person(name));
    }

    public void addEvent(String name) {
        events.add(new Event(name));
    }

    public void addEvent(String name, GregorianCalendar start, GregorianCalendar end) {
        events.add(new Event(name, start, end));
    }

    public String getTripName() {
        return tripName;
    }

    public Budget getTripBudget() {
        return tripBudget;
    }

    public ArrayList<Person> getTripMembers() {
        return tripMembers;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

}
