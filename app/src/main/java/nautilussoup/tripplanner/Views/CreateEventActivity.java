package nautilussoup.tripplanner.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import nautilussoup.tripplanner.R;

public class CreateEventActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    TextView eventNameField;
    Button eventStartTime;
    Button eventEndTime;
    Button eventStartDate;
    Button eventEndDate;
    TimePickerDialog startTime;
    TimePickerDialog endTime;
    DatePickerDialog startDate;
    DatePickerDialog endDate;
    GregorianCalendar start;
    GregorianCalendar end;
    SimpleDateFormat dateFormat;
    SimpleDateFormat timeFormat;
    Toolbar myToolbar;
    PlaceAutocompleteFragment autocompleteFragment;
    int PLACE_PICKER_REQUEST = 1;
    int status;
    Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        // Set up toolbar
        myToolbar = (Toolbar) findViewById(R.id.createEventToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        eventNameField = (TextView)findViewById(R.id.Title);
        eventStartTime = (Button)findViewById(R.id.eventStartTime);
        eventEndTime = (Button)findViewById(R.id.eventEndTime);
        eventStartDate = (Button)findViewById(R.id.eventStartDate);
        eventEndDate = (Button)findViewById(R.id.eventEndDate);

        // Date and Time Setup
        // get the supported ids for GMT-08:00 (Pacific Standard Time)
        String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);
        // if no ids were returned, something is wrong. get out.
        if (ids.length == 0)
            System.exit(0);

        // create a Pacific Standard Time time zone
        SimpleTimeZone pdt = new SimpleTimeZone(-8 * 60 * 60 * 1000, ids[0]);

        // set up rules for Daylight Saving Time
        pdt.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
        pdt.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);

        // create a GregorianCalendar with the Pacific Daylight time zone
        // and the current date and time
        Calendar c = new GregorianCalendar(pdt);
        Date trialTime = new Date();
        c.setTime(trialTime);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        timeFormat = new SimpleDateFormat("hh:mm aa");
        dateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy");


        start = new GregorianCalendar(year, month, day, hour, minute);
        end = new GregorianCalendar(year, month, day, hour, minute);

        eventStartTime.setText(formatDate(timeFormat, start));
        eventEndTime.setText(formatDate(timeFormat, end));
        eventStartDate.setText(formatDate(dateFormat, start));
        eventEndDate.setText(formatDate(dateFormat, end));

        startTime = TimePickerDialog.newInstance(this, false);
        startTime.setVersion(TimePickerDialog.Version.VERSION_2);
        endTime = TimePickerDialog.newInstance(this, false);
        endTime.setVersion(TimePickerDialog.Version.VERSION_2);

        startDate = DatePickerDialog.newInstance(this, year, month, day);
        startDate.setVersion(DatePickerDialog.Version.VERSION_2);
        endDate = DatePickerDialog.newInstance(this, year, month, day);
        endDate.setVersion(DatePickerDialog.Version.VERSION_2);

        status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (status != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(status)) {
                GooglePlayServicesUtil.getErrorDialog(status, this,
                        100).show();
            }
        }
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
        inflater.inflate(R.menu.create_event_menu, menu);
        return true;
    }

    public boolean createEvent(MenuItem menu) {
        if (eventNameField.getText().toString().matches("")) {
            Toast.makeText(this, "Please Enter a Valid Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        finish();
        return true;
    }

    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        if (!eventNameField.getText().toString().matches("")) {
            returnIntent.putExtra("EventNameField", eventNameField.getText().toString());
            returnIntent.putExtra("StartInfo", start);
            returnIntent.putExtra("EndInfo", end);
            setResult(RESULT_OK, returnIntent);
        } else {
            setResult(RESULT_CANCELED, returnIntent);
        }
        super.finish();
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        if (view == startTime) {
            start.set(Calendar.HOUR_OF_DAY, hourOfDay);
            start.set(Calendar.MINUTE, minute);

            eventStartTime.setText(formatDate(timeFormat, start));
        } else if (view == endTime) {
            end.set(Calendar.HOUR_OF_DAY, hourOfDay);
            end.set(Calendar.MINUTE, minute);
            eventEndTime.setText(formatDate(timeFormat, end));
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        if (view == startDate) {
            start.set(Calendar.YEAR, year);
            start.set(Calendar.MONTH, monthOfYear);
            start.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            eventStartDate.setText(formatDate(dateFormat, start));
        } else if (view == endDate) {
            end.set(Calendar.YEAR, year);
            end.set(Calendar.MONTH, monthOfYear);
            end.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            eventEndDate.setText(formatDate(dateFormat, end));
        }
    }

    public void openStartTimePicker(View v) {
        startTime.show(getFragmentManager(), "StartTimepickerDialog");
    }

    public void openStartDatePicker(View v) {
        startDate.show(getFragmentManager(), "StartDatepickerDialog");
    }

    public void openEndTimePicker(View v) {
        endTime.show(getFragmentManager(), "EndTimepickerDialog");
    }

    public void openEndDatePicker(View v) {
        endDate.show(getFragmentManager(), "EndDatepickerDialog");
    }

    public String formatDate(SimpleDateFormat fmt, GregorianCalendar calendar) {
        fmt.setCalendar(calendar);
        return fmt.format(calendar.getTime());
    }

    public void openPlacePicker(View v) {
        if (status == ConnectionResult.SUCCESS) {
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            try {
                startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                place = PlacePicker.getPlace(this, data);
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                ((TextView) findViewById(R.id.Title)).setText(place.getName());
            }
        }
    }
}
