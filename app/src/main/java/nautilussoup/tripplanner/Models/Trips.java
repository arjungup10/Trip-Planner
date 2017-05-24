package nautilussoup.tripplanner.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Trips implements Serializable {
    private static Trips instance;
    private List<Trip> TripList;

    private Trips() {
        TripList = new ArrayList<Trip>();
    }

    public static Trips getInstance() {
        if (instance == null) {
            instance = new Trips();
        }
        return instance;
    }

    public void setTripList(List<Trip> TripList) {
        this.TripList = TripList;
    }

    public List<Trip> getTripList() {
        return TripList;
    }
}
