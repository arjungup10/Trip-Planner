package nautilussoup.tripplanner;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.net.ConnectException;
import java.util.List;

import nautilussoup.tripplanner.Models.Trip;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripViewHolder> {
    private List<Trip> trips;
    private Context context;
    private static RecyclerViewClickListener itemListener;

    public static class TripViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView tripName;
        TextView tripBudget;

        TripViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            tripName = (TextView) itemView.findViewById(R.id.trip_name);
            tripBudget = (TextView) itemView.findViewById(R.id.trip_budget);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "inside viewholder position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition());
        }
    }

    public TripAdapter(Context context, List<Trip> trips, RecyclerViewClickListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
        this.trips = trips;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TripViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.content_trip, viewGroup, false);
        //TripViewHolder pvh = new TripViewHolder(v);
        //return pvh;
        return new TripViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TripViewHolder tripViewHolder, int i) {
        String budgetToSet = "Budget: " + Double.toString(
                trips.get(i).getTripBudget().getMaxBudget());
        tripViewHolder.tripName.setText(trips.get(i).getTripName());
        tripViewHolder.tripBudget.setText(budgetToSet);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return trips.size();
    }
}