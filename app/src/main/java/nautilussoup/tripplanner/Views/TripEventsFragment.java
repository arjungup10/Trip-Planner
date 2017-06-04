package nautilussoup.tripplanner.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.GregorianCalendar;

import nautilussoup.tripplanner.Controllers.EventsAdapter;
import nautilussoup.tripplanner.Models.Trip;
import nautilussoup.tripplanner.Models.Trips;
import nautilussoup.tripplanner.R;
import nautilussoup.tripplanner.RecyclerViewClickListener;


public class TripEventsFragment extends Fragment implements RecyclerViewClickListener {
    private RecyclerView eventRecyclerView;
    private RecyclerView.Adapter eventAdapter;
    private int adapterPosition;
    private OnFragmentInteractionListener mListener;
    private Trip tripToDetail;
    private Trips trips;
    private int tripPosition;
    private static final int REQUEST_CODE_CREATE_EVENT = 1;

    public TripEventsFragment() {}

    public static TripEventsFragment newInstance(int tripPosition) {
        TripEventsFragment fragment = new TripEventsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("tripPosition", tripPosition);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_trip_events, container, false);
        registerForContextMenu(rootView.findViewById(R.id.rvEvents));

        trips = Trips.getInstance();

        if (getArguments() != null) {
            tripPosition = getArguments().getInt("tripPosition");
            tripToDetail = trips.getTripList().get(tripPosition);
        }

        //create the recyclerview
        eventRecyclerView = (RecyclerView) rootView.findViewById(R.id.rvEvents);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        eventRecyclerView.setHasFixedSize(false);
        // use a linear layout manager
        LinearLayoutManager llm = new LinearLayoutManager(rootView.getContext());
        eventRecyclerView.setLayoutManager(llm);

        // specify an adapter (see also next example)
        eventAdapter = new EventsAdapter(rootView.getContext(), tripToDetail, this);
        eventRecyclerView.setAdapter(eventAdapter);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        adapterPosition = position;
    }

    public void addEventToTrip() {
        Intent returnIntent = new Intent(getActivity(), CreateEventActivity.class);
        startActivityForResult(returnIntent, REQUEST_CODE_CREATE_EVENT);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.trip_action_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete) {
            tripToDetail.getEvents().remove(adapterPosition);
            eventAdapter.notifyDataSetChanged();
            ((TripDetails) getActivity()).updateTrips();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE_CREATE_EVENT == requestCode) {
            if (Activity.RESULT_OK == resultCode) {
                if (data.hasExtra("EventNameField") && data.hasExtra("StartInfo") && data.hasExtra("EndInfo")) {
                    tripToDetail.addEvent(data.getStringExtra("EventNameField"),
                        (GregorianCalendar) data.getSerializableExtra("StartInfo"),
                        (GregorianCalendar) data.getSerializableExtra("EndInfo"));
                    eventAdapter.notifyDataSetChanged();
                    ((TripDetails) getActivity()).updateTrips();
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
