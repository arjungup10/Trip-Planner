package nautilussoup.tripplanner;

import org.junit.Test;

import nautilussoup.tripplanner.Models.Person;

import static org.junit.Assert.assertEquals;

public class TestPerson {
    private String testName = "Elyse Munemura";
    private String testLocation = "Hundred Acre Woods"; //provider name is unnecessary
    private Person testPerson = new Person(testName, testLocation);

    @Test
    public void testGetName() {
        assertEquals("Elyse Munemura", testPerson.getName());
    }

    @Test
    public void testGetLocation() {
        //testLocation.setLatitude(37.585355);
        //testLocation.setLongitude(-122.047286);
        //assertEquals(testLocation, testPerson.getHome());
    }
}
