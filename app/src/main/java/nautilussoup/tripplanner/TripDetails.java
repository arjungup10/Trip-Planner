package nautilussoup.tripplanner;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import nautilussoup.tripplanner.Models.Trip;
import nautilussoup.tripplanner.Models.Trips;

public class TripDetails extends AppCompatActivity implements
        TripEventsFragment.OnFragmentInteractionListener,
        TripItineraryFragment.OnFragmentInteractionListener,
        TripPaymentsFragment.OnFragmentInteractionListener,
        TripPeopleFragment.OnFragmentInteractionListener {

    public  final static String SER_KEY = "nautilussoup.tripplanner.TripDetails.ser";
    private TextView mTextMessage;
    private Trips trips;
    private Trip tripToDetail;
    private int tripPosition;
    private Fragment selectedFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_events:
                    selectedFragment = TripEventsFragment.newInstance(tripToDetail);
                    break;
                case R.id.navigation_itinerary:
                    selectedFragment = TripItineraryFragment.newInstance(tripToDetail);
                    break;
                case R.id.navigation_payments:
                    selectedFragment = TripPaymentsFragment.newInstance(tripToDetail);
                    break;
                case R.id.navigation_people:
                    selectedFragment = TripPeopleFragment.newInstance(tripToDetail);
                    break;
            }

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.tripDetails, selectedFragment);
            transaction.commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        //Deserialize the trip object
        //tripToDetail = (Trip)this.getIntent().getSerializableExtra(TripActivity.SER_KEY);

        //Get the clicked adapter position
        int tripPosition = getIntent().getExtras().getInt("tripPosition");
        tripToDetail = trips.getInstance().getTripList().get(tripPosition);

        // Set up toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tripDetailsToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle(tripToDetail.getTripName());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        selectedFragment = TripEventsFragment.newInstance(tripToDetail);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.tripDetails, selectedFragment);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    public void createTripDetails(View v) {
        if (selectedFragment.getClass().equals(TripEventsFragment.class)) {
            Toast.makeText(this, "Create new event", Toast.LENGTH_SHORT).show();
        }
        else if (selectedFragment.getClass().equals(TripItineraryFragment.class)) {
            Toast.makeText(this, "Refresh Itinerary", Toast.LENGTH_SHORT).show();
        }
        else if (selectedFragment.getClass().equals(TripPeopleFragment.class)) {
            Toast.makeText(this, "Create new person", Toast.LENGTH_SHORT).show();
            TripPeopleFragment exampleFragment = (TripPeopleFragment) selectedFragment;
            exampleFragment.addPersonToTrip();
        }
        else if (selectedFragment.getClass().equals(TripPaymentsFragment.class)) {
            Toast.makeText(this, "Create new payment", Toast.LENGTH_SHORT).show();
        }
    }
}
