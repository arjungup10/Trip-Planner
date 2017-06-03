package nautilussoup.tripplanner.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import nautilussoup.tripplanner.R;

public class CreateEventActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    TextView eventNameField;
    Button eventStartTime;
    TimePickerDialog startTime;
    Toolbar myToolbar;

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
        int hour = c.get(c.HOUR_OF_DAY);
        int minute = c.get(c.MINUTE);
        String meridian;
        if (c.get(c.AM_PM) == 1) {
            meridian = "AM";
        } else {
            meridian = "PM";
        }
        eventStartTime.setText(hour % 12 + ":" + minute + " " + meridian);


        startTime = TimePickerDialog.newInstance(this, false);
        startTime.setVersion(TimePickerDialog.Version.VERSION_2);


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
            setResult(RESULT_OK, returnIntent);
        } else {
            setResult(RESULT_CANCELED, returnIntent);
        }
        super.finish();
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String time = "You picked the following time: "+hourOfDay+"h"+minute+"m"+second;
        //startTime.set
        //timeTextView.setText(time);
        String meridian;
        if (hourOfDay < 11 || hourOfDay == 24) {
            meridian = "AM";
        } else {
            meridian = "PM";
        }
        eventStartTime.setText(hourOfDay % 12 + ":" + minute + " " + meridian);
    }

    public void openTimePicker(View v) {
        Toast.makeText(this, "yay make a timepicker now", Toast.LENGTH_SHORT).show();
        startTime.show(getFragmentManager(), "TimepickerDialog");
    }
}
