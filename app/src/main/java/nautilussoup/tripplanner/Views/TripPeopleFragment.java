package nautilussoup.tripplanner.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import nautilussoup.tripplanner.Models.Trip;
import nautilussoup.tripplanner.Models.Trips;
import nautilussoup.tripplanner.Controllers.PeopleAdapter;
import nautilussoup.tripplanner.R;
import nautilussoup.tripplanner.RecyclerViewClickListener;


public class TripPeopleFragment extends Fragment implements RecyclerViewClickListener {
    private OnFragmentInteractionListener mListener;
    private RecyclerView peopleRecyclerView;
    private RecyclerView.Adapter peopleAdapter;
    private int tripPosition;
    private Trips trips;
    private Trip tripToDetail;
    private View rootView;
    private int adapterPosition;
    private static final int REQUEST_CODE_CREATE_PERSON = 1;


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
        peopleRecyclerView.addItemDecoration(new TripPeopleFragment.SimpleDividerItemDecoration(getActivity()));

        return rootView;
    }


    public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public SimpleDividerItemDecoration(Context context) {
            mDivider = ContextCompat.getDrawable(context,R.drawable.line_divider);;
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
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
        adapterPosition = position;
    }

    public void addPersonToTrip() {
        Intent returnIntent = new Intent(getActivity(), CreatePersonActivity.class);
        startActivityForResult(returnIntent, REQUEST_CODE_CREATE_PERSON);
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
            tripToDetail.getTripMembers().remove(adapterPosition);
            peopleAdapter.notifyDataSetChanged();
            ((TripDetails) getActivity()).updateTrips();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE_CREATE_PERSON == requestCode) {
            if (Activity.RESULT_OK == resultCode) {
                if (data.hasExtra("PersonNameField")) {
                    tripToDetail.addMember(data.getStringExtra("PersonNameField"));
                    peopleAdapter.notifyDataSetChanged();
                    ((TripDetails) getActivity()).updateTrips();
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
