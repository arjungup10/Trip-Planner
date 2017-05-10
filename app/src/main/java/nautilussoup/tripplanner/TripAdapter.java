package nautilussoup.tripplanner;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripViewHolder> {
    private List<Trip> trips;

    public static class TripViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView tripName;
        TextView tripBudget;

        TripViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            tripName = (TextView) itemView.findViewById(R.id.trip_name);
            tripBudget = (TextView) itemView.findViewById(R.id.trip_budget);
        }
    }

    public TripAdapter(List<Trip> trips) {
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