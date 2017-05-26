package nautilussoup.tripplanner.Models;

import android.location.Location;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 127L;
    private String name;
    private Location home;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Location targetLocation) {
        this.name = name;
        home = targetLocation;
    }

    public String getName() {
        return name;
    }

    public Location getHome() {
        return home;
    }
}
