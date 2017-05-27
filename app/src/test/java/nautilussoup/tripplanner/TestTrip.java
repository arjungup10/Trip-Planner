package nautilussoup.tripplanner;

import android.location.Location;

import org.junit.Test;

import java.util.ArrayList;
import nautilussoup.tripplanner.Models.Event;
import nautilussoup.tripplanner.Models.Person;
import nautilussoup.tripplanner.Models.Trip;

import static org.junit.Assert.assertEquals;

public class TestTrip {
    private String tripName = "Yosemite";
    private double tripMaxBudget = 264.45;
    private Location tripLocation = new Location("");
    private ArrayList tripMembers = new ArrayList();
    private ArrayList events = new ArrayList();
    private Trip testTrip = new Trip(tripName, tripMaxBudget);
    private double epsilon = .0001;

    @Test
    public void testAddMember() {
        String name = "Larry Hu";
        testTrip.addMember(name);
        assertEquals(name, ((Person)testTrip.getTripMembers().get(0)).getName());
    }

    @Test
    public void testAddEventOnlyName() {
        String name = "Birthday Party";
        Event testEvent = new Event(name);
        assertEquals(name, testEvent.getName());
    }

    @Test
    public void testAddEvent() {
        String name = "Birthday Party";
        testTrip.addEvent(name, 2017, 11, 30, 18, 30, 2017, 11, 30, 22, 30);
        assertEquals(name, ((Event) testTrip.getEvents().get(0)).getName());
    }


    @Test
    public void testGetTripName() {
        assertEquals(tripName, testTrip.getTripName());
    }

    @Test
    public void testGetTripBudget() {
        assertEquals(tripMaxBudget, testTrip.getTripBudget().getMaxBudget(), epsilon);
    }

    @Test
    public void testGetTripMembers() {
        String name = "Larry Hu";
        testTrip.addMember(name);
        assertEquals(1, testTrip.getTripMembers().size());
    }


    @Test
    public void testGetEvents() {
        String name = "Birthday Party";
        testTrip.addEvent(name, 2017, 11, 30, 18, 30, 2017, 11, 30, 22, 30);
        assertEquals(1, testTrip.getEvents().size());
    }


}
