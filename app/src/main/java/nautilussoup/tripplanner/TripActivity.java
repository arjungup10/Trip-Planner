package nautilussoup.tripplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.onClick;

public class TripActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton eventFAB;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toast = Toast.makeText(getApplicationContext(), "Hello toast!", Toast.LENGTH_SHORT);
        String myDataset[] = {"hello", "world"};
        List<Trip> trips = new ArrayList<Trip>();
        trips.add(new Trip("Yosemite", 200.0));
        trips.add(new Trip("Tahoe", 100));
        trips.add(new Trip("Rafting", 100));

        super.onCreate(savedInstanceState);
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
        startActivity(new Intent(TripActivity.this, CreateEventActivity.class));
    }
}
