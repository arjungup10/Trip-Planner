package nautilussoup.tripplanner;

import org.junit.Test;

import java.util.ArrayList;

import nautilussoup.tripplanner.Models.Trip;
import nautilussoup.tripplanner.Models.Trips;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class TestTrips {
    private Trip testTrip1 = new Trip("Yosemite", 263.4);
    private Trip testTrip2 = new Trip("Rafting", 153.3);
    private ArrayList<Trip> tripList = new ArrayList<Trip>();
    private Trips testTrips = Trips.getInstance();


    @Test
    public void testGetInstance() {
        assertNotNull(testTrips);
    }

    @Test
    public void testTripList() {
        tripList.add(testTrip1);
        tripList.add(testTrip2);
        testTrips.setTripList(tripList);
        assertEquals(tripList, testTrips.getTripList());
    }

}
