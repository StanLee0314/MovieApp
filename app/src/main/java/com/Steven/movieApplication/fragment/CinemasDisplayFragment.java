package com.Steven.movieApplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Steven.movieApplication.R;
import com.Steven.movieApplication.adapter.LocationRecyclerViewAdapter;
import com.Steven.movieApplication.model.CinemaLocation;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CinemasDisplayFragment extends Fragment {
    private static final String TAG = "MapsActivity";

    View view;


    private CinemaLocation mCinemaLocation;
    private com.google.android.gms.maps.model.LatLng mLatLng;
    private ArrayList<CinemaLocation> mCinemaLocationList;
    public CinemasDisplayFragment() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
//        view = inflater.inflate(R.layout.fragment_maps, container, false);
        view = inflater.inflate(R.layout.fragment_cinemas, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.cinemas_recycler_view);
        recyclerView.setHasFixedSize(true);
        initDataset();
        LocationRecyclerViewAdapter locationAdapter = new LocationRecyclerViewAdapter(getContext(), mCinemaLocationList);
        recyclerView.setAdapter(locationAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }
    private void initDataset() {
        mCinemaLocationList = new ArrayList<>();
        mCinemaLocationList.add(new CinemaLocation("CBD Cinema", "King St", "CBD", "VIC", "3000", "Australia", "https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1527947030&di=91ebbf7adc0f40742e9690b60f983b71&src=http://www.premier-capital.com/UpLoadFiles/20150427/2015042716113488.jpg"));
        mCinemaLocationList.add(new CinemaLocation("Caulfield Cinema", "Caufield Road", "Caulfield East", "VIC", "3145", "Australia", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527957017372&di=690094cc171b266098701f772101ca8b&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F017ad659cde777a801204463b83968.jpg%401280w_1l_2o_100sh.jpg"));
        mCinemaLocationList.add(new CinemaLocation("Chadstone Cinema", "Chadstone Road", "Chadstone", "VIC", "3111", "Australia", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527957099436&di=423b8c34d0d3b25bdd29f0fe9247dc00&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3D641ffd202334349b600b66c5a09270a2%2F30adcbef76094b36ed9d07d7a8cc7cd98d109df8.jpg"));
    }
}
