package nautilussoup.tripplanner;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import nautilussoup.tripplanner.Models.Trip;


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
    private int adapterPosition;
    private Trip tripToDetail;
    private static final String TRIP_KEY = "trip_key";


    public TripPeopleFragment() {}

    public static TripPeopleFragment newInstance(Trip trip) {
        TripPeopleFragment fragment = new TripPeopleFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TRIP_KEY, trip);
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
        View rootView = inflater.inflate(R.layout.fragment_trip_people, container, false);

        if (getArguments() != null) {
            tripToDetail = (Trip) getArguments().getSerializable(TRIP_KEY);
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
}
