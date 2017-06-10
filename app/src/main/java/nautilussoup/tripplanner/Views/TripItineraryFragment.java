package nautilussoup.tripplanner.Views;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import nautilussoup.tripplanner.Controllers.HttpHandler;
import nautilussoup.tripplanner.Controllers.ItineraryAdapter;
import nautilussoup.tripplanner.Models.Event;
import nautilussoup.tripplanner.Models.Trip;
import nautilussoup.tripplanner.Models.Trips;
import nautilussoup.tripplanner.R;
import nautilussoup.tripplanner.RecyclerViewClickListener;

import static android.content.ContentValues.TAG;

public class TripItineraryFragment extends Fragment implements RecyclerViewClickListener {
    private Trip tripToDetail;
    private Trips trips;
    private OnFragmentInteractionListener mListener;
    private int tripPosition;
    private RecyclerView itineraryRecyclerView;
    private RecyclerView.Adapter itineraryAdapter;
    private String home = "3326+Sanderling+Dr,Fremont,CA";
    private String baseURL = "https://maps.googleapis.com/maps/api/directions/json?";
    private static String url = "https://maps.googleapis.com/maps/api/directions/json?origin=Adelaide,SA&destination=Adelaide,SA&waypoints=optimize:true|Barossa+Valley,SA|Clare,SA|Connawarra,SA|McLaren+Vale,SA&key=AIzaSyBuRMledPXdN8jaia2lYMGXuxBUGU7Vpr4";
    private static String url2 = "https://maps.googleapis.com/maps/api/directions/json?origin=Boston,MA&destination=Concord,MA&waypoints=Charlestown,MA|Lexington,MA&key=AIzaSyBuRMledPXdN8jaia2lYMGXuxBUGU7Vpr4";
    private ProgressDialog pDialog;
    private ArrayList<String> addresses;
    private ArrayList<String> times;

    public TripItineraryFragment() {}

    public static TripItineraryFragment newInstance(int tripPosition) {
        TripItineraryFragment fragment = new TripItineraryFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_trip_itinerary, container, false);

        trips = Trips.getInstance();

        if (getArguments() != null) {
            tripPosition = getArguments().getInt("tripPosition");
            tripToDetail = trips.getTripList().get(tripPosition);
        }

        addresses = new ArrayList<>();
        times = new ArrayList<>();

        if (tripToDetail.getEvents().size() > 0) {

            baseURL = baseURL.concat("origin=3326+Sanderling+Dr,Fremont,CA&destination=3326+Sanderling+Dr,Fremont,CA&waypoints=optimize:true");

            for (Event e : tripToDetail.getEvents()) {
                baseURL = baseURL.concat("|" + e.getEventLocationId());
            }

            baseURL = baseURL.concat("&key=AIzaSyBuRMledPXdN8jaia2lYMGXuxBUGU7Vpr4");
        }

        Log.e(TAG, baseURL);

        //create the recyclerview
        itineraryRecyclerView = (RecyclerView) rootView.findViewById(R.id.rvItinerary);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        itineraryRecyclerView.setHasFixedSize(false);
        // use a linear layout manager
        LinearLayoutManager llm = new LinearLayoutManager(rootView.getContext());
        itineraryRecyclerView.setLayoutManager(llm);

        // specify an adapter (see also next example)
        itineraryAdapter = new ItineraryAdapter(rootView.getContext(),
                addresses, times, this);
        itineraryRecyclerView.setAdapter(itineraryAdapter);

        new GetItinerary().execute();

        return rootView;
    }

    private class GetItinerary extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(baseURL);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray routes = jsonObj.getJSONArray("routes");
                    JSONObject route1 = routes.getJSONObject(0);
                    JSONArray legs = route1.getJSONArray("legs");
                    //JSONArray legs = jsonObj.getJSONArray("legs");


                    for (int i = 0; i < legs.length(); i++) {
                        JSONObject leg = legs.getJSONObject(i);
                        JSONObject duration = leg.getJSONObject("duration");
                        if (i == 0) {
                            addresses.add(leg.getString("start_address"));
                            addresses.add(leg.getString("end_address"));
                        } else {
                            addresses.add(leg.getString("end_address"));
                        }
                        times.add(duration.getString("text"));
                    }
//
//                    JSONArray origs = jsonObj.getJSONArray("origin_addresses");
//                    for (int i = 0; i < origs.length(); i++) {
//                        origins.add(origs.getString(i));
//                    }
//
//                        String dest = c.getString("id");
////                        String name = c.getString("name");
////                        String email = c.getString("email");
////                        String address = c.getString("address");
////                        String gender = c.getString("gender");
////
////                        // Phone node is JSON Object
////                        JSONObject phone = c.getJSONObject("phone");
////                        String mobile = phone.getString("mobile");
////                        String home = phone.getString("home");
////                        String office = phone.getString("office");
//
//                        // tmp hash map for single contact
//                        HashMap<String, String> contact = new HashMap<>();
//
//                        // adding each child node to HashMap key => value
//                        contact.put("id", id);
//                        contact.put("name", name);
//                        contact.put("email", email);
//                        contact.put("mobile", mobile);
//
//                        // adding contact to contact list
//                        contactList.add(contact);
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            itineraryAdapter.notifyDataSetChanged();
        }

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
