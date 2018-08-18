package com.Steven.movieApplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Steven.movieApplication.R;
import com.Steven.movieApplication.MapsShow;
import com.Steven.movieApplication.model.CinemaLocation;
import com.bumptech.glide.Glide;

import java.util.List;


/**
 * Created by Steven on 6/2/2018.
 *
 * Adapter for the location recycler view
 * use glide, widget
 */

public class LocationRecyclerViewAdapter extends GenericAdapter<CinemaLocation> implements GenericAdapter.OnViewHolderClick {

    private static final String TAG = "LocationRVAdapter";

    public LocationRecyclerViewAdapter(Context context, List<CinemaLocation> dataset) {
        super(context, null, dataset);
        setOnClickListener(this);
    }

    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {
        return LayoutInflater.from(context).inflate(R.layout.location_card_view, viewGroup, false);
    }

    @Override
    protected void bindView(CinemaLocation CinemaLocation, GenericViewHolder viewHolder) {
        // Get views from CinemaLocation card
        TextView nameTextView = (TextView) viewHolder.getItemView().findViewById(R.id.location_name_textview);
        TextView streetTextView = (TextView) viewHolder.getItemView().findViewById(R.id.streetadress_textView);
        TextView suburbTextView = (TextView) viewHolder.getItemView().findViewById(R.id.suburb_textView);
        TextView stateTextView = (TextView) viewHolder.getItemView().findViewById(R.id.state_textView);
        TextView postcodeCountryTextView = (TextView) viewHolder.getItemView().findViewById(R.id.postcode_country_textView);
        ImageView imageView = (ImageView) viewHolder.getItemView().findViewById(R.id.location_imageView);

        nameTextView.setText(CinemaLocation.getName());
        streetTextView.setText(CinemaLocation.getStreet());
        suburbTextView.setText(CinemaLocation.getSuburb());
        stateTextView.setText(CinemaLocation.getState());
        postcodeCountryTextView.setText(CinemaLocation.getPostcode() + " " + CinemaLocation.getCountry());

        Glide.with(getContext()).load(CinemaLocation.getImageURL()).centerCrop().into(imageView);
    }

    @Override
    public void onClick(View view, int position) {
        CinemaLocation CinemaLocation = getItem(position);
        Intent newIntent = new Intent(getContext(), MapsShow.class);
        newIntent.putExtra("CinemaLocation", CinemaLocation);

        getContext().startActivity(newIntent);
    }
}
