package nautilussoup.tripplanner.Views;

import android.content.Context;
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
import android.widget.Toast;

import nautilussoup.tripplanner.Models.Trip;
import nautilussoup.tripplanner.Models.Trips;
import nautilussoup.tripplanner.Controllers.PeopleAdapter;
import nautilussoup.tripplanner.R;
import nautilussoup.tripplanner.RecyclerViewClickListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TripPeopleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TripPeopleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TripPeopleFragment extends Fragment implements RecyclerViewClickListener {
    private OnFragmentInteractionListener mListener;
    public RecyclerView peopleRecyclerView;
    private RecyclerView.Adapter peopleAdapter;
    private int tripPosition;
    private Trips trips;
    private Trip tripToDetail;
    private View rootView;
    private int adapterPosition;


    public TripPeopleFragment() {}

    public static TripPeopleFragment newInstance(int tripPosition) {
        TripPeopleFragment fragment = new TripPeopleFragment();
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
        rootView = inflater.inflate(R.layout.fragment_trip_people, container, false);
        registerForContextMenu(rootView.findViewById(R.id.rvPeople));

        trips = Trips.getInstance();
        Toast.makeText(getActivity(), trips.getTripList().get(0).getTripName(), Toast.LENGTH_SHORT).show();

        if (getArguments() != null) {
            tripPosition = getArguments().getInt("tripPosition");
            tripToDetail = trips.getTripList().get(tripPosition);
        }


        //create the recyclerview
        peopleRecyclerView = (RecyclerView) rootView.findViewById(R.id.rvPeople);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        peopleRecyclerView.setHasFixedSize(false);
        // use a linear layout manager
        LinearLayoutManager llm = new LinearLayoutManager(rootView.getContext());
        peopleRecyclerView.setLayoutManager(llm);

        // specify an adapter (see also next example)
        peopleAdapter = new PeopleAdapter(rootView.getContext(), tripToDetail, this);
        peopleRecyclerView.setAdapter(peopleAdapter);

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

    public void addPersonToTrip() {
        tripToDetail.addMember("Kevin Chiu");
        peopleAdapter.notifyDataSetChanged();
        ((TripDetails) getActivity()).updateTrips();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
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
        if(id == R.id.action_delete) {
            tripToDetail.getTripMembers().remove(adapterPosition);
            peopleAdapter.notifyDataSetChanged();
            ((TripDetails) getActivity()).updateTrips();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
