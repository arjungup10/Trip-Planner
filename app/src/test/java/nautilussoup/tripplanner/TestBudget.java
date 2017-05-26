package nautilussoup.tripplanner;

import org.junit.Test;

import nautilussoup.tripplanner.Models.Budget;
import nautilussoup.tripplanner.Models.Person;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestBudget {
    private double maxBudget = 234.42;
    private double epsilon = 0.0001;
    private Budget testBudget = new Budget(maxBudget);

    @Test
    public void testInitMaxBudget() throws Exception {
        assertEquals(maxBudget, testBudget.getMaxBudget(), epsilon);
    }

    @Test
    public void testInitAmountPaid() throws Exception {
        assertEquals(0.0, testBudget.getAmountSpent(), epsilon);
    }

    @Test
    public void testInitPayments() throws Exception {
        assertEquals(0, testBudget.getPayments().size());
    }

    @Test
    public void testAddPaymentToArray() throws Exception {
        testBudget.addPayment(new Person("Larson Chang"), 15, "Wings");
        assertEquals(1, testBudget.getPayments().size());
    }

    @Test
    public void testGetAmountSpent() throws Exception {
        testBudget.addPayment(new Person("Larson Chang"), 15, "Wings");
        assertEquals(15, testBudget.getAmountSpent(), epsilon);
    }

    @Test
    public void testMultipleGetAmountSpent() throws Exception {
        testBudget.addPayment(new Person("Larson Chang"), 15, "Wings");
        testBudget.addPayment(new Person("Damon Suen"), 1.5, "Water");
        assertEquals(16.5, testBudget.getAmountSpent(), epsilon);
    }

    @Test
    public void testGetMaxBudget() throws Exception {
        assertEquals(234.42, testBudget.getMaxBudget(), epsilon);
    }
}