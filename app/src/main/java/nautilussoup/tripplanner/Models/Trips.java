package nautilussoup.tripplanner.Models;

import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public void setTripList(ArrayList<Trip> TripList) {
        this.tripList = TripList;
    }

    public ArrayList<Trip> getTripList() {
        return tripList;
    }


}
