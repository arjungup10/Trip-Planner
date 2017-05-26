package nautilussoup.tripplanner;

import org.junit.Test;

import nautilussoup.tripplanner.Models.Payment;
import nautilussoup.tripplanner.Models.Person;

import static org.junit.Assert.*;

public class TestPayment {
    private Person testPerson = new Person("Danny Kim");
    private double testAmount = 61.50;
    private String testDescription = "LED";
    private double epsilon = .0001;
    private Payment testPayment = new Payment(testPerson, testAmount, testDescription);

    @Test
    public void testGetPersonPaying() {
        assertEquals("Danny Kim", testPayment.getPersonPaying().getName());
    }

    @Test
    public void testGetAmount() {
        assertEquals(61.50, testPayment.getAmount(), epsilon);
    }

    @Test
    public void setTestDescription() {
        assertEquals("LED", testPayment.getDescription());
    }
}
