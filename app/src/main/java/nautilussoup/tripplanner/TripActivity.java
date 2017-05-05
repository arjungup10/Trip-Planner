package nautilussoup.tripplanner;

import android.content.Intent;
import android.nfc.Tag;
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

import java.util.ArrayList;
import java.util.List;

public class TripActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Trip> trips;
    Toolbar myToolbar;
    private final String TAG = "TripActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        trips = new ArrayList<Trip>();
        trips.add(new Trip("Yosemite", 200.0));
        trips.add(new Trip("Tahoe", 100));
        trips.add(new Trip("Rafting", 100));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        // create relevant toolbar
        myToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Trips");
        Log.d(TAG, "onCreate: ");
        if (getIntent().hasExtra("TripNameField") && getIntent().hasExtra("TripBudgetField")) {
            Log.v("EditText", getIntent().getStringExtra("TripNameField"));
            Log.v("EditText", getIntent().getStringExtra("TripBudgetField"));
            addTrip(new Trip(getIntent().getStringExtra("TripNameField"), Double.parseDouble((getIntent().getStringExtra("TripBudgetField")))));
        }


        //create the recyclerview
        mRecyclerView = (RecyclerView) findViewById(R.id.rvEvents);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(false);
        // use a linear layout manager
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);

        // specify an adapter (see also next example)
        mAdapter = new TripAdapter(trips);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void createEvent(View view) {
        startActivity(new Intent(TripActivity.this, CreateTripActivity.class));
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

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }



}
