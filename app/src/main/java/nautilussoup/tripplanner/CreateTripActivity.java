package nautilussoup.tripplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

/**
 * Created by arjun on 5/3/2017.
 */

public class CreateTripActivity extends AppCompatActivity {
    TextView tripNameField, tripBudgetField;
    Toolbar myToolbar;
    int appbarID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);

        // Set up toolbar
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tripNameField = (TextView)findViewById(R.id.Title);
        tripBudgetField = (TextView)findViewById(R.id.Budget);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_trip_menu, menu);
        return true;
    }

    public boolean createTrip(MenuItem menu) {
        Intent intent = new Intent(getBaseContext(), TripActivity.class);
        intent.putExtra("TripNameField", tripNameField.getText().toString());
        intent.putExtra("TripBudgetField", tripBudgetField.getText().toString());
        startActivity(intent);
        Log.v("EditText", tripNameField.getText().toString());
        Log.v("EditText", tripBudgetField.getText().toString());
        return true;
    }
}
