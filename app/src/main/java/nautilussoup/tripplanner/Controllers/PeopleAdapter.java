package nautilussoup.tripplanner.Controllers;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nautilussoup.tripplanner.Models.Person;
import nautilussoup.tripplanner.Models.Trip;
import nautilussoup.tripplanner.R;
import nautilussoup.tripplanner.RecyclerViewClickListener;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {
    private final Trip tripToDetail;
    private Context context;
    private RecyclerViewClickListener itemListener;

    public class PeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        //CardView cvPeople;
        TextView personName;


        PeopleViewHolder(View itemView) {
            super(itemView);
            //cvPeople = (CardView) itemView.findViewById(R.id.cvPeople);
            personName = (TextView) itemView.findViewById(R.id.person_Name);
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

    public PeopleAdapter(Context context, Trip trip, RecyclerViewClickListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
        tripToDetail = trip;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.content_people, viewGroup, false);
        return new PeopleViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PeopleViewHolder peopleViewHolder, int i) {
        Person p = (Person) tripToDetail.getTripMembers().get(i);
        peopleViewHolder.personName.setText(p.getName());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return tripToDetail.getTripMembers().size();
    }
}