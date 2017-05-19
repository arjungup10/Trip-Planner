package nautilussoup.tripplanner;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import nautilussoup.tripplanner.Models.Trip;

public class TripActivity extends AppCompatActivity implements RecyclerViewClickListener {
    private List<Trip> trips;
    private static final String newTripNameId = "TripNameField";
    private static final String newTripBudgetId = "TripBudgetField";
    public static final int CREATE_TRIP_REQUEST = 1;
    public static final int TRIP_DETAILS_REQUEST = 1;
    public  final static String SER_KEY = "nautilussoup.tripplanner.TripActivity.ser";
    public RecyclerView tripRecyclerView;
    private RecyclerView.Adapter tripAdapter;
    public String fileName = "trips";
    public int adapterPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        trips = new ArrayList<>();
        registerForContextMenu(findViewById(R.id.rvTrips));

        try {
            File file = new File(this.getFilesDir(), fileName);
            if(file == null || !file.exists()) {
                FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);

                Trip Yosemite = new Trip("Yosemite", 200.0);
                Trip Tahoe = new Trip("Tahoe", 100);
                Trip Rafting = new Trip("Rafting", 100);
                trips.add(Yosemite);
                trips.add(Tahoe);
                trips.add(Rafting);

                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(trips);

                fos.close();
                oos.close();
            }
            else {
                FileInputStream fis = openFileInput(fileName);
                ObjectInputStream is = new ObjectInputStream(fis);
                trips = (List<Trip>) is.readObject();
            }
        }
        catch(Exception e) {
            Toast.makeText(this, "We got a problem", Toast.LENGTH_SHORT).show();
            Log.e("", "exception", e);
        }

        // create relevant toolbar
        Toolbar myToolbar;
        myToolbar = (Toolbar)findViewById(R.id.tripActivityToolbar);
        setSupportActionBar(myToolbar);


        setTitle("Trips");
        if (getIntent().hasExtra(newTripNameId) && getIntent().hasExtra(newTripBudgetId)) {
            addTrip(new Trip(getIntent().getStringExtra(newTripNameId),
                    Double.parseDouble((getIntent().getStringExtra(newTripBudgetId)))));
        }

        //create the recyclerview
        tripRecyclerView = (RecyclerView) findViewById(R.id.rvTrips);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        tripRecyclerView.setHasFixedSize(false);
        // use a linear layout manager
        LinearLayoutManager llm = new LinearLayoutManager(this);
        tripRecyclerView.setLayoutManager(llm);

        // specify an adapter (see also next example)
        tripAdapter = new TripAdapter(getBaseContext(), trips, this);
        tripRecyclerView.setAdapter(tripAdapter);
    }

    public void createTrip(View view) {
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

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.trip_action_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_delete) {
            trips.remove(adapterPosition);
            updateTrips();
            tripAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        adapterPosition = position;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_TRIP_REQUEST && resultCode == RESULT_OK) {
            if (data.hasExtra(newTripNameId) && data.hasExtra(newTripBudgetId)) {
                addTrip(new Trip(data.getStringExtra(newTripNameId),
                        Double.parseDouble((data.getStringExtra(newTripBudgetId)))));
                updateTrips();

            }
        }
    }

    public void updateTrips() {
        try{
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(trips);

            fos.close();
            oos.close();
        }
        catch(Exception e) {
            Toast.makeText(this, "We got a problem", Toast.LENGTH_SHORT).show();
            Log.e("", "exception", e);
        }
    }
}
