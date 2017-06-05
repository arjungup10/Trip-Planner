package nautilussoup.tripplanner;

import org.junit.Test;

import java.util.GregorianCalendar;

import nautilussoup.tripplanner.Models.Event;

import static org.junit.Assert.assertEquals;

public class TestEvent {
    private String name = "Birthday Party";
    private GregorianCalendar testStart = new GregorianCalendar(2017, 11, 30, 18, 30);
    private GregorianCalendar testEnd = new GregorianCalendar(2017, 11, 30, 22, 30);
    private Event testEvent = new Event(name, testStart, testEnd);

    @Test
    public void testGetName() {
        assertEquals(name, testEvent.getName());
    }

    @Test
    public void testGetStartTime() {
        assertEquals(testStart, testEvent.getStartTime());
    }

    @Test
    public void testEndStartTime() {
        assertEquals(testEnd, testEvent.getEndTime());
    }
}
