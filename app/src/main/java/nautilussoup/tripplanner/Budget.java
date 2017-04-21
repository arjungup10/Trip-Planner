package nautilussoup.tripplanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arjun Gupta on 4/21/2017.
 */
public class Budget {
    private double maxBudget;
    private double amountSpent;
    private List payments;

    public Budget(double maxBudget) {
        this.maxBudget = maxBudget;
        amountSpent = 0;
        payments = new ArrayList<Payment>();
    }

    public void addPayment(Person personPaying, double amount, String description) {
        amountSpent += amount;
        payments.add(new Payment(personPaying, amount, description));
    }
}
