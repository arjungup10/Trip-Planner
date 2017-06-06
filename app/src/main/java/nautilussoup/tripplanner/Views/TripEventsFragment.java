package nautilussoup.tripplanner.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import nautilussoup.tripplanner.Controllers.EventsAdapter;
import nautilussoup.tripplanner.Models.Trip;
import nautilussoup.tripplanner.Models.Trips;
import nautilussoup.tripplanner.R;
import nautilussoup.tripplanner.RecyclerViewClickListener;

import static android.content.ContentValues.TAG;


public class TripEventsFragment extends Fragment implements RecyclerViewClickListener,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,
        ResultCallback<PlaceBuffer> {
    private RecyclerView eventRecyclerView;
    private RecyclerView.Adapter eventAdapter;
    private int adapterPosition;
    private OnFragmentInteractionListener mListener;
    private Trip tripToDetail;
    private Trips trips;
    private int tripPosition;
    private static final int REQUEST_CODE_CREATE_EVENT = 1;
    private GoogleApiClient mGoogleApiClient;
    private Place myPlace;
    String placeName;
    String placeId;

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
        mGoogleApiClient = new GoogleApiClient
                .Builder(getActivity())
                .enableAutoManage(getActivity(), 0, this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
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

    @Override
    public void onResult(@NonNull PlaceBuffer places) {
        if (places.getStatus().isSuccess() && places.getCount() > 0) {
            myPlace = places.get(0);
            Log.i(TAG, "Place found: " + myPlace.getName());
        } else {
            Log.e(TAG, "Place not found");
        }
        places.release();
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
            ((TripDetails) getActivity()).updateHeader();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE_CREATE_EVENT == requestCode) {
            if (Activity.RESULT_OK == resultCode) {
                if (data.hasExtra("EventNameField") && data.hasExtra("StartInfo") &&
                        data.hasExtra("EndInfo") && data.hasExtra("place")) {
                    tripToDetail.addEvent(data.getStringExtra("EventNameField"),
                            data.getStringExtra("Address"),
                        (GregorianCalendar) data.getSerializableExtra("StartInfo"),
                        (GregorianCalendar) data.getSerializableExtra("EndInfo"));
                    eventAdapter.notifyDataSetChanged();
                    ((TripDetails) getActivity()).updateTrips();
                    ((TripDetails) getActivity()).updateHeader();
                    //placeId = data.getStringExtra("place");
                    //Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeId).setResultCallback(this);
                    Toast.makeText(getActivity(), data.getStringExtra("Address"), Toast.LENGTH_SHORT).show();
                } else if (data.hasExtra("EventNameField") && data.hasExtra("StartInfo") &&
                        data.hasExtra("EndInfo")) {
                    tripToDetail.addEvent(data.getStringExtra("EventNameField"),
                            (GregorianCalendar) data.getSerializableExtra("StartInfo"),
                            (GregorianCalendar) data.getSerializableExtra("EndInfo"));
                    eventAdapter.notifyDataSetChanged();
                    ((TripDetails) getActivity()).updateTrips();
                    ((TripDetails) getActivity()).updateHeader();
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if( mGoogleApiClient != null )
            mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        if( mGoogleApiClient != null && mGoogleApiClient.isConnected() ) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    public void getPlace(String placeId) {
        Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeId)
                .setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(PlaceBuffer places) {
                        if (places.getStatus().isSuccess() && places.getCount() > 0) {
                            myPlace = places.get(0);
                            Log.i(TAG, "Place found: " + myPlace.getName());
                            placeName = (String)myPlace.getName();
                            Toast.makeText(getActivity(), myPlace.getName(), Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "Place not found");
                        }
                        places.release();
                    }
                });
    }

    @Override
    public void onConnected(Bundle bundle) {
        //mPlaceArrayAdapter.setGoogleApiClient(mGoogleApiClient);
        Log.i("", "Google Places API connected.");

    }

    @Override
    public void onConnectionSuspended(int i) {
        //mPlaceArrayAdapter.setGoogleApiClient(null);
        Log.e("", "Google Places API connection suspended.");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("", "Google Places API connection failed with error code: "
                + connectionResult.getErrorCode());

        Toast.makeText(getActivity(),
                "Google Places API connection failed with error code:" +
                        connectionResult.getErrorCode(),
                Toast.LENGTH_LONG).show();
    }

}
