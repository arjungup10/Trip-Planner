package nautilussoup.tripplanner;

import org.junit.Test;

import nautilussoup.tripplanner.Models.Budget;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestBudget {
    private double maxBudget = 234.42;
    private double epsilon = 0.0001;
    private Budget testBudget = new Budget(maxBudget);

    @Test
    public void initMaxBudget() throws Exception {
        assertEquals(maxBudget, testBudget.getMaxBudget(), epsilon);
    }

    @Test
    public void initAmountPaid() throws Exception {
        assertEquals(0.0, testBudget.getAmountSpent(), epsilon);
    }

    @Test
    public void initPayments() throws Exception {
        assertEquals(0, testBudget.getPayments().size());
    }
}