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

import nautilussoup.tripplanner.Controllers.PaymentAdapter;
import nautilussoup.tripplanner.Models.Trip;
import nautilussoup.tripplanner.Models.Trips;
import nautilussoup.tripplanner.R;
import nautilussoup.tripplanner.RecyclerViewClickListener;

public class TripPaymentsFragment extends Fragment implements RecyclerViewClickListener {
    private RecyclerView paymentRecyclerView;
    private RecyclerView.Adapter paymentAdapter;
    private int tripPosition;
    private Trips trips;
    private Trip tripToDetail;
    private View rootView;
    private int adapterPosition;
    private static final int REQUEST_CODE_CREATE_PAYMENT = 1;
    private static final int REQUEST_CODE_CALC_PAYMENTS = 2;

    private OnFragmentInteractionListener mListener;
    public TripPaymentsFragment() {}

    public static TripPaymentsFragment newInstance(int tripPosition) {
        TripPaymentsFragment fragment = new TripPaymentsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("tripPosition", tripPosition);
        fragment.setArguments(bundle);
        return fragment;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_trip_payments, container, false);
        registerForContextMenu(rootView.findViewById(R.id.rvPayments));

        trips = Trips.getInstance();

        if (getArguments() != null) {
            tripPosition = getArguments().getInt("tripPosition");
            tripToDetail = trips.getTripList().get(tripPosition);
        }

        //create the recyclerview
        paymentRecyclerView = (RecyclerView) rootView.findViewById(R.id.rvPayments);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        paymentRecyclerView.setHasFixedSize(false);
        // use a linear layout manager
        LinearLayoutManager llm = new LinearLayoutManager(rootView.getContext());
        paymentRecyclerView.setLayoutManager(llm);

        // specify an adapter (see also next example)
        paymentAdapter = new PaymentAdapter(rootView.getContext(), tripToDetail, this);
        paymentRecyclerView.setAdapter(paymentAdapter);
        paymentRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

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

    public void addPaymentToTrip() {
        Intent returnIntent = new Intent(getActivity(), CreatePaymentActivity.class);
        returnIntent.putExtra("tripPosition", tripPosition);
        startActivityForResult(returnIntent, REQUEST_CODE_CREATE_PAYMENT);
    }

    public void calculatePayments() {
        Intent returnIntent = new Intent(getActivity(), CalculatePaymentsActivity.class);
        returnIntent.putExtra("tripPosition", tripPosition);
        startActivityForResult(returnIntent, REQUEST_CODE_CALC_PAYMENTS);
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        adapterPosition = position;
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
            tripToDetail.getTripBudget().removePayment(adapterPosition);
            paymentAdapter.notifyDataSetChanged();
            ((TripDetails) getActivity()).updateTrips();
            ((TripDetails) getActivity()).updateHeader();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE_CREATE_PAYMENT == requestCode) {
            if (Activity.RESULT_OK == resultCode) {
                if (data.hasExtra("paymentAmountField") &&
                        data.hasExtra("paymentDescriptionField") &&
                        data.hasExtra("paymentPersonPositionField")) {
                    tripToDetail.getTripBudget().addPayment(
                            tripToDetail.getTripMembers().get(Integer.parseInt(data.getStringExtra("paymentPersonPositionField"))),
                            Double.parseDouble(data.getStringExtra("paymentAmountField")),
                            data.getStringExtra("paymentDescriptionField")
                    );
                    paymentAdapter.notifyDataSetChanged();
                    ((TripDetails) getActivity()).updateTrips();
                    ((TripDetails) getActivity()).updateHeader();
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        } else if (REQUEST_CODE_CALC_PAYMENTS == requestCode) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
