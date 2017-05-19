package nautilussoup.tripplanner.Models;

import android.location.Location;

import java.io.Serializable;

/**
 * Created by Arjun Gupta on 4/21/2017.
 */
public class Person implements Serializable {
    private String name;
    private Location home;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Location home) {
        this.name = name;
        //TO-DO: set location
    }
}
