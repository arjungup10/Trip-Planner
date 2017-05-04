package nautilussoup.tripplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TripActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Trip> trips;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toast = Toast.makeText(getApplicationContext(), "Hello toast!", Toast.LENGTH_SHORT);
        trips = new ArrayList<Trip>();
        trips.add(new Trip("Yosemite", 200.0));
        trips.add(new Trip("Tahoe", 100));
        trips.add(new Trip("Rafting", 100));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createevent);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        setContentView(R.layout.activity_trip);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvEvents);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);

        // specify an adapter (see also next example)
        mAdapter = new TripAdapter(trips);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void createEvent(View view) {
        toast.show();
        startActivity(new Intent(TripActivity.this, CreateTripActivity.class));
    }

    public void addTrip(Trip tripToAdd) {
        trips.add(tripToAdd);
    }
}
