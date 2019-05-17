package a920.cybersoft.com.assigment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import a920.cybersoft.com.assigment.MyApplication;
import a920.cybersoft.com.assigment.R;
import a920.cybersoft.com.assigment.adapter.ZooAreaListAdapter;
import a920.cybersoft.com.assigment.bean.ZooArea;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout mShimmerViewContainer;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private OnListFragmentInteractionListener mListener;
    private List<ZooArea> zooAreasList;
    private ZooAreaListAdapter mAdapter;

    private static final String URL = "https://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a";

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(ZooArea item);
    }

    public static ItemFragment newInstance() {
        ItemFragment fragment = new ItemFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, view);
        mShimmerViewContainer.startShimmerAnimation();
        // load Data
        if (zooAreasList != null) {
            mShimmerViewContainer.stopShimmerAnimation();
            mShimmerViewContainer.setVisibility(View.GONE);
        } else {
            zooAreasList = new ArrayList<>();
            fetchZooData();
        }

        mAdapter = new ZooAreaListAdapter(getContext(), zooAreasList, new ZooAreaListAdapter.OnItemClicked() {
            @Override
            public void onItemClick(ZooArea zooArea) {
                mListener.onListFragmentInteraction(zooArea);
            }
        });

        // RecyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    /**
     * method make volley network call and parses json
     */
    private void fetchZooData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response == null) {
                            Toast.makeText(getContext(), "Couldn't fetch the Data! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        try{
                            // Log.w(TAG, "onResponse: " + response.toString() );
                            List<ZooArea> ZooAreas = new Gson().fromJson(response.getJSONObject("result").getJSONArray("results").toString(), new TypeToken<List<ZooArea>>() {
                            }.getType());

                            // adding recipes to cart list
                            zooAreasList.clear();
                            zooAreasList.addAll(ZooAreas);

                            // refreshing recycler view
                            mAdapter.notifyDataSetChanged();

                            // GONE mShimmerViewContainer
                            mShimmerViewContainer.stopShimmerAnimation();
                            mShimmerViewContainer.setVisibility(View.GONE);

                        }catch (Exception e){

                            Log.e(TAG, "onResponse: " + e );
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(getContext(), "Timeout!!", Toast.LENGTH_LONG).show();
                        mShimmerViewContainer.stopShimmerAnimation();
                        Log.e(TAG, "onResponse: " + error );
                    }
                }
        );
        // timeout handle
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                500,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        MyApplication.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
