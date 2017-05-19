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
import android.widget.TextView;
import android.widget.Toast;

import nautilussoup.tripplanner.Models.Trip;

public class TripDetails extends AppCompatActivity implements
        TripEventsFragment.OnFragmentInteractionListener,
        TripItineraryFragment.OnFragmentInteractionListener,
        TripPaymentsFragment.OnFragmentInteractionListener,
        TripPeopleFragment.OnFragmentInteractionListener {

    private TextView mTextMessage;
    private Trip tripToDetail;
    public  final static String SER_KEY = "nautilussoup.tripplanner.TripDetails.ser";


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
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
        tripToDetail = (Trip)this.getIntent().getSerializableExtra(TripActivity.SER_KEY);

        // Set up toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tripDetailsToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle(tripToDetail.getTripName());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.tripDetails, TripEventsFragment.newInstance(tripToDetail));
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}
}
