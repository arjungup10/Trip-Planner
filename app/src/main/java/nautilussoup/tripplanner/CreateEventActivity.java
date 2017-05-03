package nautilussoup.tripplanner;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by arjun on 5/3/2017.
 */

public class CreateEventActivity extends Activity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createevent);
        TextView tv = (TextView)findViewById(R.id.createEvent);
        tv.setText("Create Event");
    }
}
