package nautilussoup.tripplanner.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import nautilussoup.tripplanner.Controllers.CalculatePaymentsAdapter;
import nautilussoup.tripplanner.Models.Payment;
import nautilussoup.tripplanner.Models.Person;
import nautilussoup.tripplanner.Models.Repayment;
import nautilussoup.tripplanner.Models.Trip;
import nautilussoup.tripplanner.Models.Trips;
import nautilussoup.tripplanner.R;
import nautilussoup.tripplanner.RecyclerViewClickListener;

public class CalculatePaymentsActivity extends AppCompatActivity implements RecyclerViewClickListener {
    private Toolbar myToolbar;
    private Trips trips;
    private Trip tripToDetail;
    private RecyclerView calcPaymentRecyclerView;
    private RecyclerView.Adapter calcPaymentAdapter;
    private int adapterPosition;
    private ArrayList<Repayment> repayments;
    private ArrayList<Pair<Person, Pair<Person, Double>>> adapterRepays;


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

        calculatePayments();

        //create the recyclerview
        calcPaymentRecyclerView = (RecyclerView) findViewById(R.id.rvCalcPayments);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        calcPaymentRecyclerView.setHasFixedSize(false);
        // use a linear layout manager
        LinearLayoutManager llm = new LinearLayoutManager(this);
        calcPaymentRecyclerView.setLayoutManager(llm);

        // specify an adapter (see also next example)
        calcPaymentAdapter = new CalculatePaymentsAdapter(getBaseContext(), adapterRepays, this);
        calcPaymentRecyclerView.setAdapter(calcPaymentAdapter);
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


    @Override
    public void recyclerViewListClicked(View v, int position) {
        adapterPosition = position;
    }

    public void calculatePayments() {
        Repayment curRepayment;
        double total = tripToDetail.getTripBudget().getAmountSpent();
        double average = total / tripToDetail.getTripMembers().size();
        Toast.makeText(this, "average: " + Double.toString(average), Toast.LENGTH_SHORT).show();
        int index;
        ArrayList<Payment> payments = tripToDetail.getTripBudget().getPayments();
        ArrayList<Person> people = tripToDetail.getTripMembers();
        repayments = new ArrayList<>();
        adapterRepays = new ArrayList<>();

        for (Person p : people) {
            repayments.add(new Repayment(p, 0));
        }

        for (Repayment r : repayments) {
            for (Payment p : payments) {
                if (r.getPersonPaying().equals(p.getPersonPaying())) {
                    r.addToAmount(p.getAmount());
                }
            }
        }

        for (Repayment r : repayments) {
            if (r.getAmount() < average) {
                double diff = average - r.getAmount();
                int i = 0;
                while (diff >= 0 && i < repayments.size()) {
                    if (repayments.get(i).getAmount() > average) {
                        double repay = repayments.get(i).getAmount() - average;
                        if (diff >= repay) {
                            r.addPersonReceving(repayments.get(i).getPersonPaying(), repay);
                            adapterRepays.add(new Pair(r.getPersonPaying(),
                                    new Pair(repayments.get(i).getPersonPaying(), repay)));
                        } else {
                            r.addPersonReceving(repayments.get(i).getPersonPaying(), diff);
                            adapterRepays.add(new Pair(r.getPersonPaying(),
                                    new Pair(repayments.get(i).getPersonPaying(), diff)));
                        }
                        repayments.get(i).addToAmount(-diff);
                        r.addToAmount(diff);
                        diff -= repay;
                    }
                    i++;
                }
            }
        }

        for (Repayment r : repayments) {
            for (Pair<Person, Double> p : r.getPeopleReceiving()) {
                Toast.makeText(this, r.getPersonPaying().getName() + " -> "
                        + p.first.getName() + " for " + Double.toString(p.second),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
