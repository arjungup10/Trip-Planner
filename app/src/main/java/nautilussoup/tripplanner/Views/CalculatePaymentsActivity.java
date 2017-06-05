package nautilussoup.tripplanner.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import nautilussoup.tripplanner.Models.Trip;
import nautilussoup.tripplanner.Models.Trips;
import nautilussoup.tripplanner.R;

public class CalculatePaymentsActivity extends AppCompatActivity {
    private Toolbar myToolbar;
    private Trips trips;
    private Trip tripToDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_payments);

        // Set up toolbar
        myToolbar = (Toolbar) findViewById(R.id.calcPaymentsToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        //Get the clicked adapter position
        trips = Trips.getInstance();
        int tripPosition = getIntent().getExtras().getInt("tripPosition");
        tripToDetail = trips.getTripList().get(tripPosition);

        setTitle("Payments for " + tripToDetail.getTripName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
