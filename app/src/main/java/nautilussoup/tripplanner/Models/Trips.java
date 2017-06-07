package nautilussoup.tripplanner.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Trips implements Serializable {
    private static Trips instance;
    private ArrayList<Trip> tripList;

    private Trips() {
        tripList = new ArrayList<Trip>();
    }

    public static Trips getInstance() {
        if (instance == null) {
            instance = new Trips();
        }
        return instance;
    }

    public void setTripList(ArrayList<Trip> tripList) {
        this.tripList = tripList;
    }

    public ArrayList<Trip> getTripList() {
        return tripList;
    }

}
