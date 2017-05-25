package nautilussoup.tripplanner.Models;

import java.io.Serializable;

/**
 * Created by Arjun Gupta on 4/21/2017.
 */
public class Payment implements Serializable {
    private static final long serialVersionUID = 23L;
    private Person personPaying;
    private double amount;
    private String description;

    public Payment(Person personPaying, double amount, String description) {
        this.personPaying = personPaying;
        this.amount = amount;
        this.description = description;
    }

    public Person getPersonPaying() {
        return personPaying;
    }

    public void setPersonPaying(Person personPaying) {
        this.personPaying = personPaying;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
