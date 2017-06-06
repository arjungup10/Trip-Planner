package nautilussoup.tripplanner.Controllers;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import nautilussoup.tripplanner.Models.Payment;
import nautilussoup.tripplanner.Models.Person;
import nautilussoup.tripplanner.Models.Repayment;
import nautilussoup.tripplanner.Models.Trip;
import nautilussoup.tripplanner.R;
import nautilussoup.tripplanner.RecyclerViewClickListener;

public class CalculatePaymentsAdapter extends RecyclerView.Adapter<CalculatePaymentsAdapter.CalculatePaymentsViewHolder> {
    private Context context;
    private RecyclerViewClickListener itemListener;
    private ArrayList<Pair<Person, Pair<Person, Double>>> adapterRepays;

    public class CalculatePaymentsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        CardView cvCalculatePayment;
        TextView personName;
        TextView amountPaid;
        TextView personReceivingName;

        CalculatePaymentsViewHolder(View itemView) {
            super(itemView);
            cvCalculatePayment = (CardView) itemView.findViewById(R.id.cvCalculatePayments);
            personName = (TextView) itemView.findViewById(R.id.calc_Payment_Person_Paying);
            amountPaid = (TextView) itemView.findViewById(R.id.calc_Payment_Amount);
            personReceivingName = (TextView) itemView.findViewById(R.id.calc_Payment_Person_Receiving);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getAdapterPosition());
            //Intent tripDetailsIntent = new Intent(context, TripDetails.class);
            //Bundle mBundle = new Bundle();
            //mBundle.putSerializable(TripActivity.SER_KEY, trips.get(getAdapterPosition()));
            //tripDetailsIntent.putExtras(mBundle);
            //context.startActivity(tripDetailsIntent);
        }

        @Override
        public boolean onLongClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getAdapterPosition());
            itemView.showContextMenu();
            return true;
        }
    }

    public CalculatePaymentsAdapter(Context context, ArrayList<Pair<Person,
            Pair<Person, Double>>> repayments, RecyclerViewClickListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
        this.adapterRepays = repayments;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CalculatePaymentsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.content_calculate_payments, viewGroup, false);
        return new CalculatePaymentsViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CalculatePaymentsViewHolder paymentViewHolder, int i) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        Pair<Person, Pair<Person, Double>> p = adapterRepays.get(i);
        String roundedAmount = twoDForm.format(p.second.second);
        if (!roundedAmount.equals("0")) {
            paymentViewHolder.personName.setText(p.first.getName());
            paymentViewHolder.amountPaid.setText(twoDForm.format(p.second.second));
            paymentViewHolder.personReceivingName.setText(p.second.first.getName());
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return adapterRepays.size();
    }
}