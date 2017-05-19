package nautilussoup.tripplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nautilussoup.tripplanner.Models.Trip;

public class TripActivity extends AppCompatActivity implements RecyclerViewClickListener {
    private List<Trip> trips;
    private static final String newTripNameId = "TripNameField";
    private static final String newTripBudgetId = "TripBudgetField";
    public static final int CREATE_TRIP_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        trips = new ArrayList<>();
        trips.add(new Trip("Yosemite", 200.0));
        trips.add(
                new Trip("Tahoe", 100));
        trips.add(new Trip("Rafting", 100));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        // create relevant toolbar
        Toolbar myToolbar;
        myToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Trips");
        if (getIntent().hasExtra(newTripNameId) && getIntent().hasExtra(newTripBudgetId)) {
            addTrip(new Trip(getIntent().getStringExtra(newTripNameId),
                    Double.parseDouble((getIntent().getStringExtra(newTripBudgetId)))));
        }

        //create the recyclerview
        RecyclerView tripRecyclerView = (RecyclerView) findViewById(R.id.rvEvents);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        tripRecyclerView.setHasFixedSize(false);
        // use a linear layout manager
        LinearLayoutManager llm = new LinearLayoutManager(this);
        tripRecyclerView.setLayoutManager(llm);

        // specify an adapter (see also next example)
        RecyclerView.Adapter tripAdapter = new TripAdapter(getBaseContext(), trips, this);
        tripRecyclerView.setAdapter(tripAdapter);
    }

    public void createEvent(View view) {
        //Set up return intents
        Intent createTripIntent = new Intent(this, CreateTripActivity.class);
        startActivityForResult(createTripIntent, CREATE_TRIP_REQUEST);
    }

    public void addTrip(Trip tripToAdd) {
        trips.add(tripToAdd);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.trips_menu, menu);
        return true;
    }

    public boolean openTrip(View view) {
        Intent intent = new Intent(getBaseContext(), TripDetails.class);
        startActivity(intent);
        return true;
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_TRIP_REQUEST && resultCode == RESULT_OK) {
            if (data.hasExtra(newTripNameId) && data.hasExtra(newTripBudgetId)) {
                addTrip(new Trip(data.getStringExtra(newTripNameId),
                        Double.parseDouble((data.getStringExtra(newTripBudgetId)))));
            }
        }
    }
}
