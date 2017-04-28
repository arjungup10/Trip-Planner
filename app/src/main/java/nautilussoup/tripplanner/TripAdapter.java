package nautilussoup.tripplanner;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Arjun Gupta on 4/28/2017.
 */

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripViewHolder> {
    //private String[] mDataset;
    private List<Trip> trips;

    public static class TripViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView tripName;
        TextView tripBudget;

        TripViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            tripName = (TextView)itemView.findViewById(R.id.trip_name);
            tripBudget = (TextView)itemView.findViewById(R.id.trip_budget);
        }
    }

    public TripAdapter(List<Trip> trips) {
        this.trips = trips;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        // each data item is just a string in this case
//        public TextView mTextView;
//        public ViewHolder(TextView v) {
//            super(v);
//            mTextView = (TextView) v.findViewById(R.id.testView);
//        }
//    }

//    // Provide a suitable constructor (depends on the kind of dataset)
//    public TripAdapter(String[] myDataset) {
//        mDataset = myDataset;
//    }

    // Create new views (invoked by the layout manager)
    @Override
    public TripViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.testlayout, viewGroup, false);
        TripViewHolder pvh = new TripViewHolder(v);
        return pvh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TripViewHolder tripViewHolder, int i) {
        tripViewHolder.tripName.setText(trips.get(i).getTripName());
        tripViewHolder.tripBudget.setText(Double.toString(trips.get(i).getTripBudget().getMaxBudget()));
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