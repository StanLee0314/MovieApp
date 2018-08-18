package com.Steven.movieApplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.Steven.movieApplication.DetailActivity;
import com.Steven.movieApplication.R;
import com.Steven.movieApplication.model.Favorite;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.Steven.movieApplication.BuildConfig.URL_POSTER;

/**
 * Created by Steven on 6/2/2018.
 * Adapter for the favorite view.
 *  use glide, Date, butterknife
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private LinkedList<Favorite> listFavorites;
    private Activity activity;
    private Context context;

    public FavoriteAdapter(Context context) {
        this.context = context;
    }

    public LinkedList<Favorite> getListFavorite() {
        return listFavorites;
    }

    public void setListFavorite(LinkedList<Favorite> listFavorites) {
        this.listFavorites = listFavorites;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.movieTitle.setText(listFavorites.get(position).getTitle());

        String time = listFavorites.get(position).getRelease_date();
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = parser.parse(time);
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM d, yyyy");
            String formattedDate = formatter.format(date);
            holder.data.setText(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.movieDescription.setText(listFavorites.get(position).getOverview());
        Glide.with(context).load(URL_POSTER + listFavorites.get(position).getPoster()).into(holder.backbg);


        holder.RvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(position, v);
            }
        });


    }
// view the details of favortite movie
    private void startActivity(int position, View v) {

        Intent i = new Intent(v.getContext(), DetailActivity.class);
        i.putExtra(DetailActivity.MOVIE_ID, listFavorites.get(position).getId());
        i.putExtra(DetailActivity.MOVIE_TITLE, listFavorites.get(position).getTitle());
        i.putExtra(DetailActivity.MOVIE_OVERVIEW, listFavorites.get(position).getOverview());
        i.putExtra(DetailActivity.MOVIE_TIME, listFavorites.get(position).getRelease_date());
        i.putExtra(DetailActivity.MOVIE_POSTER, listFavorites.get(position).getPoster());
        i.putExtra(DetailActivity.IS_FAVORITE, 1);
        v.getContext().startActivity(i);

    }


    @Override
    public int getItemCount() {
        return getListFavorite().size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        @BindView(R.id.title) TextView movieTitle;
        @BindView(R.id.subtitle) TextView data;
        @BindView(R.id.description) TextView movieDescription;
        @BindView(R.id.backbg) ImageView backbg;

        public CardView RvMain;

        public ViewHolder(View v) {
            super(v);
            RvMain = (CardView) v.findViewById(R.id.movies_layout);
            ButterKnife.bind(this, v);
        }
    }
}