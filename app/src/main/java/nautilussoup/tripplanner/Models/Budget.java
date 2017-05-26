package nautilussoup.tripplanner.Models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Budget implements Serializable {
    private static final long serialVersionUID = 42L;
    private double maxBudget;
    private double amountSpent;
    private ArrayList payments;

    public Budget(double maxBudget) {
        this.maxBudget = maxBudget;
        amountSpent = 0;
        payments = new ArrayList<Payment>();
    }

    public void addPayment(Person personPaying, double amount, String description) {
        amountSpent += amount;
        payments.add(new Payment(personPaying, amount, description));
    }

    public double getMaxBudget() {
        return maxBudget;
    }

    public double getAmountSpent() {
        return amountSpent;
    }

    public ArrayList getPayments() {
        return payments;
    }
}
