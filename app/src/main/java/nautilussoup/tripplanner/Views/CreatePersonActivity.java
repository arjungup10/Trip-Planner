package nautilussoup.tripplanner.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import nautilussoup.tripplanner.R;

public class CreatePersonActivity extends AppCompatActivity {
    TextView personNameField;
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_person);

        // Set up toolbar
        myToolbar = (Toolbar) findViewById(R.id.createPersonToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        personNameField = (TextView)findViewById(R.id.Title);
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
        inflater.inflate(R.menu.create_person_menu, menu);
        return true;
    }

    public boolean createPerson(MenuItem menu) {
        if (personNameField.getText().toString().matches("")) {
            Toast.makeText(this, "Please Enter a Valid Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        finish();
        return true;
    }

    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        if (!personNameField.getText().toString().matches("")) {
            returnIntent.putExtra("PersonNameField", personNameField.getText().toString());
            setResult(RESULT_OK, returnIntent);
        } else {
            setResult(RESULT_CANCELED, returnIntent);
        }
        super.finish();
    }
}
