package nautilussoup.tripplanner;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TripActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
}
