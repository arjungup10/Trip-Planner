package nautilussoup.tripplanner.Models;

import java.io.Serializable;

/**
 * Created by Arjun Gupta on 4/21/2017.
 */
public class Payment implements Serializable {
    private Person personPaying;
    private double amount;
    private String description;

    public Payment(Person personPaying, double amount, String description) {
        this.personPaying = personPaying;
        this.amount = amount;
        this.description = description;
    }

}
