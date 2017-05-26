package nautilussoup.tripplanner.Views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nautilussoup.tripplanner.Models.Trip;
import nautilussoup.tripplanner.Models.Trips;
import nautilussoup.tripplanner.R;
import nautilussoup.tripplanner.RecyclerViewClickListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TripItineraryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TripItineraryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TripItineraryFragment extends Fragment implements RecyclerViewClickListener {
    private Trip tripToDetail;
    private Trips trips;
    private static final String TRIP_KEY = "trip_key";
    private OnFragmentInteractionListener mListener;
    private int tripPosition;

    public TripItineraryFragment() {}

    public static TripItineraryFragment newInstance(int tripPosition) {
        TripItineraryFragment fragment = new TripItineraryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("tripPosition", tripPosition);
        //bundle.putSerializable(TRIP_KEY, trip);
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
        View rootView = inflater.inflate(R.layout.fragment_trip_itinerary, container, false);

        trips = Trips.getInstance();

        if (getArguments() != null) {
            tripPosition = getArguments().getInt("tripPosition");
            tripToDetail = trips.getTripList().get(tripPosition);
        }

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

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
