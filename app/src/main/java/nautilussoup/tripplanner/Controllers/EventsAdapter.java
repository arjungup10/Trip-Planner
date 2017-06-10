package nautilussoup.tripplanner.Controllers;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import nautilussoup.tripplanner.Models.Event;
import nautilussoup.tripplanner.Models.Trip;
import nautilussoup.tripplanner.R;
import nautilussoup.tripplanner.RecyclerViewClickListener;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {
    private final Trip tripToDetail;
    private Context context;
    private RecyclerViewClickListener itemListener;
    SimpleDateFormat timeFormat;
    SimpleDateFormat dateFormat;

    public class EventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        CardView cvEvents;
        TextView eventName;
        TextView eventDate;
        TextView eventTime;

        EventsViewHolder(View itemView) {
            super(itemView);
            cvEvents = (CardView) itemView.findViewById(R.id.cvEvents);
            eventName = (TextView) itemView.findViewById(R.id.event_Name);
            eventDate = (TextView) itemView.findViewById(R.id.Date);
            eventTime = (TextView) itemView.findViewById(R.id.Time);
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

    public EventsAdapter(Context context, Trip trip, RecyclerViewClickListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
        tripToDetail = trip;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.content_events, viewGroup, false);
        timeFormat = new SimpleDateFormat("hh:mm aa");
        dateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy");
        return new EventsViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(EventsViewHolder eventsViewHolder, int i) {
        Event e = (Event) tripToDetail.getEvents().get(i);
        eventsViewHolder.eventName.setText(e.getName());

        String startDate = formatDate(dateFormat, e.getStartTime());
        String endDate = formatDate(dateFormat, e.getEndTime());
        String startTime = formatDate(timeFormat, e.getStartTime());
        String endTime = formatDate(timeFormat, e.getEndTime());

        //Toast.makeText(context, startTime, Toast.LENGTH_SHORT).show();
        //Toast.makeText(context, endTime, Toast.LENGTH_SHORT).show();

        if (startDate.equals(endDate)) {
            eventsViewHolder.eventDate.setText(startDate);
        } else {
            eventsViewHolder.eventDate.setText(startDate + " - " + endDate);
        }

        if (startTime.equals(endTime)) {
            eventsViewHolder.eventTime.setText(startTime);
        } else {
            eventsViewHolder.eventTime.setText(startTime + " - " + endTime);
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return tripToDetail.getEvents().size();
    }

    public String formatDate(SimpleDateFormat fmt, GregorianCalendar calendar) {
        fmt.setCalendar(calendar);
        return fmt.format(calendar.getTime());
    }
}