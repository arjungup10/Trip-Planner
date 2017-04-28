package nautilussoup.tripplanner;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class EventActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String myDataset[] = {"hello", "world"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvEvents);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new EventAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }
}
