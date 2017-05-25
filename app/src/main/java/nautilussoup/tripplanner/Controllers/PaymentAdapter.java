package nautilussoup.tripplanner.Controllers;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nautilussoup.tripplanner.Models.Payment;
import nautilussoup.tripplanner.Models.Person;
import nautilussoup.tripplanner.Models.Trip;
import nautilussoup.tripplanner.R;
import nautilussoup.tripplanner.RecyclerViewClickListener;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {
    private static final String TAG = "";
    private final Trip tripToDetail;
    private Context context;
    private static RecyclerViewClickListener itemListener;

    public class PaymentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        CardView cvPayments;
        TextView personName;
        TextView paymentAmount;
        TextView paymentReason;

        PaymentViewHolder(View itemView) {
            super(itemView);
            cvPayments = (CardView) itemView.findViewById(R.id.cvPayments);
            personName = (TextView) itemView.findViewById(R.id.payment_Person_Name);
            paymentAmount = (TextView) itemView.findViewById(R.id.payment_Amount);
            paymentReason = (TextView) itemView.findViewById(R.id.payment_Reason);
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

    public PaymentAdapter(Context context, Trip trip, RecyclerViewClickListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
        tripToDetail = trip;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PaymentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.content_people, viewGroup, false);
        return new PaymentViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PaymentViewHolder paymentViewHolder, int i) {
        Payment p = (Payment) tripToDetail.getTripBudget().getPayments().get(i);
//        paymentViewHolder.personName.setText(p.getPersonPaying().getName());
        //paymentViewHolder.paymentReason.setText(p.getDescription());
        //paymentViewHolder.paymentAmount.setText(Double.toString(p.getAmount()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return tripToDetail.getTripBudget().getPayments().size();
    }
}