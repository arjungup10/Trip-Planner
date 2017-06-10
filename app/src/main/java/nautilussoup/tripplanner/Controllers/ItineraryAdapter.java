package nautilussoup.tripplanner.Controllers;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import nautilussoup.tripplanner.R;
import nautilussoup.tripplanner.RecyclerViewClickListener;

public class ItineraryAdapter  extends RecyclerView.Adapter<ItineraryAdapter.ItineraryViewHolder> {
    private Context context;
    private RecyclerViewClickListener itemListener;
    private ArrayList<String> times;
    private ArrayList<String> addresses;

    public class ItineraryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        CardView cvItinerary;
        TextView itineraryText;
        TextView itineraryText2;
        TextView itineraryText3;

        public ItineraryViewHolder(View itemView) {
            super(itemView);
            cvItinerary = (CardView) itemView.findViewById(R.id.cvItinerary);
            itineraryText = (TextView) itemView.findViewById(R.id.itineraryText);
            //itineraryText2 = (TextView) itemView.findViewById(R.id.itineraryText2);
            itineraryText3 = (TextView) itemView.findViewById(R.id.itineraryText3);

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

    public ItineraryAdapter(Context context, ArrayList<String> addresses, ArrayList<String> times,
                            RecyclerViewClickListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
        this.addresses = addresses;
        this.times = times;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ItineraryAdapter.ItineraryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.content_itinerary, viewGroup, false);
        return new ItineraryAdapter.ItineraryViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ItineraryAdapter.ItineraryViewHolder itineraryViewHolder, int i) {
        itineraryViewHolder.itineraryText.setText(addresses.get(i));
        if (i == addresses.size() - 1) {
            itineraryViewHolder.itineraryText3.setText("");
        } else {
            itineraryViewHolder.itineraryText3.setText(times.get(i));
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return addresses.size();
    }
}

