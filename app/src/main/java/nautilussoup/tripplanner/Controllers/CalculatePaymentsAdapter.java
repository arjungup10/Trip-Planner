package nautilussoup.tripplanner.Controllers;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import nautilussoup.tripplanner.Models.Payment;
import nautilussoup.tripplanner.Models.Repayment;
import nautilussoup.tripplanner.Models.Trip;
import nautilussoup.tripplanner.R;
import nautilussoup.tripplanner.RecyclerViewClickListener;

public class CalculatePaymentsAdapter extends RecyclerView.Adapter<CalculatePaymentsAdapter.CalculatePaymentsViewHolder> {
    private Context context;
    private RecyclerViewClickListener itemListener;
    ArrayList<Repayment> repayments;

    public class CalculatePaymentsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        CardView cvCalculatePayment;
        TextView personName;
        TextView amountPaid;
        TextView reasonPaid;

        CalculatePaymentsViewHolder(View itemView) {
            super(itemView);
            cvCalculatePayment = (CardView) itemView.findViewById(R.id.cvCalculatePayments);
            personName = (TextView) itemView.findViewById(R.id.calc_Payment_Person_Name);
            amountPaid = (TextView) itemView.findViewById(R.id.calc_Payment_Amount);
            reasonPaid = (TextView) itemView.findViewById(R.id.calc_Payment_Reason);

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

    public CalculatePaymentsAdapter(Context context, ArrayList<Repayment> repayments, RecyclerViewClickListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
        this.repayments = repayments;
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
        Repayment p = (Repayment) repayments.get(i);
        paymentViewHolder.personName.setText(p.getPersonPaying().getName());
        paymentViewHolder.amountPaid.setText(Double.toString(p.getAmount()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return repayments.size();
    }
}