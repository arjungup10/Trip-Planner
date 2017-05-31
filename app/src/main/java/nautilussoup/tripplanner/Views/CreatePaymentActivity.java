package nautilussoup.tripplanner.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import nautilussoup.tripplanner.Models.Person;
import nautilussoup.tripplanner.Models.Trip;
import nautilussoup.tripplanner.Models.Trips;
import nautilussoup.tripplanner.R;

public class CreatePaymentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView paymentAmountField;
    TextView paymentDescriptionField;
    Toolbar myToolbar;
    Trip tripToDetail;
    int selectedPosition;
    Spinner paymentSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_payment);

        //Get the clicked adapter position
        Trips trips = Trips.getInstance();
        int tripPosition = getIntent().getExtras().getInt("tripPosition");
        tripToDetail = trips.getTripList().get(tripPosition);

        // Set up toolbar
        myToolbar = (Toolbar) findViewById(R.id.createPaymentToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        paymentAmountField = (TextView)findViewById(R.id.payment_amount_field);
        paymentDescriptionField = (TextView)findViewById(R.id.payment_description_field);

        //Initialize Spinner
        paymentSpinner = (Spinner) findViewById(R.id.payments_spinner);
        ArrayAdapter<Person> adapter =
                new ArrayAdapter<Person>(getApplicationContext(),
                        R.layout.people_spinner, tripToDetail.getTripMembers());
        adapter.setDropDownViewResource(R.layout.people_spinner);

        paymentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedPosition = 0;
            }

        });

        paymentSpinner.setAdapter(adapter);
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
        inflater.inflate(R.menu.create_payment_menu, menu);
        return true;
    }

    public boolean createPayment(MenuItem menu) {
        if (paymentAmountField.getText().toString().matches("")) {
            Toast.makeText(this, "Please Enter a Valid Amount", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (paymentDescriptionField.getText().toString().matches("")) {
            Toast.makeText(this, "Please Enter a Valid Description", Toast.LENGTH_SHORT).show();
            return false;
        }
        finish();
        return true;
    }

    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        if (!paymentAmountField.getText().toString().matches("") && !paymentDescriptionField.getText().toString().matches("") ) {
            returnIntent.putExtra("paymentPersonPositionField", Integer.toString(selectedPosition));
            returnIntent.putExtra("paymentAmountField", paymentAmountField.getText().toString());
            returnIntent.putExtra("paymentDescriptionField", paymentDescriptionField.getText().toString());
            setResult(RESULT_OK, returnIntent);
        } else {
            setResult(RESULT_CANCELED, returnIntent);
        }
        super.finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, i, Toast.LENGTH_SHORT).show();
        selectedPosition = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
