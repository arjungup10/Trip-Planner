package nautilussoup.tripplanner.Models;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {
    private static final long serialVersionUID = 127L;
    private String name;
    private String home;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, String targetLocation) {
        this.name = name;
        home = targetLocation;
    }

    public String getName() {
        return name;
    }

    public String getHome() {
        return home;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        Person p = (Person) o;
        return this.getName().equals(p.getName()) && this.getHome().equals(p.getHome());
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, home);
    }

}
