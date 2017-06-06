package nautilussoup.tripplanner.Views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import nautilussoup.tripplanner.Models.Trip;
import nautilussoup.tripplanner.Models.Trips;
import nautilussoup.tripplanner.R;

public class TripDetails extends AppCompatActivity implements
        TripEventsFragment.OnFragmentInteractionListener,
        TripItineraryFragment.OnFragmentInteractionListener,
        TripPaymentsFragment.OnFragmentInteractionListener,
        TripPeopleFragment.OnFragmentInteractionListener {

    private Trips trips;
    private Trip tripToDetail;
    private int tripPosition;
    private Fragment selectedFragment;
    private TextView header;
    private Button calcPaymentsButton;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                      case R.id.navigation_events:
                          calcPaymentsButton.setVisibility(View.INVISIBLE);
                          selectedFragment = TripEventsFragment.newInstance(tripPosition);
                          break;
                      case R.id.navigation_itinerary:
                          calcPaymentsButton.setVisibility(View.INVISIBLE);
                          selectedFragment = TripItineraryFragment.newInstance(tripPosition);
                          break;
                      case R.id.navigation_payments:
                          calcPaymentsButton.setVisibility(View.VISIBLE);
                          selectedFragment = TripPaymentsFragment.newInstance(tripPosition);
                          break;
                      case R.id.navigation_people:
                          calcPaymentsButton.setVisibility(View.INVISIBLE);
                          selectedFragment = TripPeopleFragment.newInstance(tripPosition);
                          break;
                      default:
                          break;
                    }

                    updateHeader();
                    FragmentTransaction transaction =
                            getSupportFragmentManager().beginTransaction();
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
        trips = Trips.getInstance();
        if (getIntent().hasExtra("tripPosition")) {
            tripPosition = getIntent().getExtras().getInt("tripPosition");
        }
        tripToDetail = trips.getTripList().get(tripPosition);

        // Set up toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tripDetailsToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle(tripToDetail.getTripName());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        selectedFragment = TripEventsFragment.newInstance(tripPosition);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.tripDetails, selectedFragment);
        transaction.commit();

        header = (TextView) findViewById(R.id.trip_Details_Header);
        calcPaymentsButton = (Button) findViewById(R.id.calcPaymentsButton);
        calcPaymentsButton.setVisibility(View.INVISIBLE);
        updateHeader();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    public void createTripDetails(View v) {
        if (selectedFragment.getClass().equals(TripEventsFragment.class)) {
            ((TripEventsFragment) selectedFragment).addEventToTrip();
        } else if (selectedFragment.getClass().equals(TripItineraryFragment.class)) {
            Toast.makeText(this, "Refresh Itinerary", Toast.LENGTH_SHORT).show();
        } else if (selectedFragment.getClass().equals(TripPeopleFragment.class)) {
            ((TripPeopleFragment) selectedFragment).addPersonToTrip();
        } else if (selectedFragment.getClass().equals(TripPaymentsFragment.class)) {
            ((TripPaymentsFragment) selectedFragment).addPaymentToTrip();
        }
    }

    public void updateTrips() {
        try {
            FileOutputStream fos = openFileOutput("trips", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(trips.getTripList());

            fos.close();
            oos.close();
        } catch (Exception e) {
            Toast.makeText(this, "We got a problem", Toast.LENGTH_SHORT).show();
            Log.e("", "exception", e);
        }
    }

    public void updateHeader() {
        if (selectedFragment.getClass().equals(TripEventsFragment.class)) {
            header.setText("Total Events: " + tripToDetail.getEvents().size());
        } else if (selectedFragment.getClass().equals(TripItineraryFragment.class)) {
            header.setText("Itinerary");
            Toast.makeText(this, "Refresh Itinerary", Toast.LENGTH_SHORT).show();
        } else if (selectedFragment.getClass().equals(TripPaymentsFragment.class)) {
            header.setText("Amount Spent: " + tripToDetail.getTripBudget().getAmountSpent());
        } else if (selectedFragment.getClass().equals(TripPeopleFragment.class)) {
            header.setText("Total Trip Members: " + tripToDetail.getTripMembers().size());
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        if (selectedFragment.getClass().equals(TripPaymentsFragment.class)) {
//            MenuInflater inflater = getMenuInflater();
//            inflater.inflate(R.menu.payment_details_menu, menu);
//        }
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.calcPayments) {
//            Toast.makeText(this, "Calc Payments", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public boolean openCalculatePayments(View v) {
        ((TripPaymentsFragment) selectedFragment).calculatePayments();
        return true;
    }

}
