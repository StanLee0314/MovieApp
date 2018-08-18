package com.Steven.movieApplication;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Steven.movieApplication.database.FavoriteHelper;
import com.Steven.movieApplication.model.Favorite;
//import com.Steven.movieApplication.stackWidget.ImageBannerWidget;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.Steven.movieApplication.BuildConfig.URL_POSTER;
/**
 * Created by Steven on 6/2/2018.
 * handle the movie detail and enable the favorite function and purchase function
 * use butterknife, glide, widget
 */
public class DetailWithPurchaseActivity extends AppCompatActivity{
    public static String MOVIE_ID = "movie_id";
    public static String MOVIE_TITLE = "movie_title";
    public static String MOVIE_OVERVIEW = "movie_overview";
    public static String MOVIE_TIME = "movie_time";
    public static String MOVIE_POSTER = "movie_poster";
    public static String IS_FAVORITE = "is_favorite";

    @BindView(R.id.title) TextView tvTitle;
    @BindView(R.id.overview) TextView tvOverview;
    @BindView(R.id.time) TextView tvTime;
    @BindView(R.id.poster) ImageView imgPoster;
    @BindView(R.id.btn_favorite) Button btnFovorite;
    @BindView(R.id.btn_purchase) Button btnPurchase;
    
    private FavoriteHelper favoriteHelper;
    private boolean isFavorite = false;
    private int favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_purchase);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        int id = getIntent().getIntExtra(MOVIE_ID, 0);
        String title = getIntent().getStringExtra(MOVIE_TITLE);
        String overview = getIntent().getStringExtra(MOVIE_OVERVIEW);
        String time = getIntent().getStringExtra(MOVIE_TIME);
        String poster = getIntent().getStringExtra(MOVIE_POSTER);
        tvTitle.setText(title);
        tvOverview.setText(overview);
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = parser.parse(time);
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM d, yyyy");
            String formattedDate = formatter.format(date);
            tvTime.setText(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Glide.with(this).load(URL_POSTER+poster).into(imgPoster);



        favoriteHelper = new FavoriteHelper(this);
        favoriteHelper.open();

        favorite = getIntent().getIntExtra(IS_FAVORITE, 0);
        if (favorite == 1){
            isFavorite = true;
            btnFovorite.setText("Removed favorite");
        }
// handle the favorite function
        btnFovorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFavorite){
                    addFavorit();
                    Toast.makeText(DetailWithPurchaseActivity.this, "The movie has been added", Toast.LENGTH_LONG).show();
                }else {
                    deleteFavorite();
                    Toast.makeText(DetailWithPurchaseActivity.this, "The movie has been moved", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPurchase();
            }
        });


    }

    @Override
    public boolean onNavigateUp(){
        finish();
        return true;
    }

//add favorite movie
    private void addFavorit(){
        Favorite favorites = new Favorite();
        favorites.setTitle(getIntent().getStringExtra(MOVIE_TITLE));
        favorites.setOverview(getIntent().getStringExtra(MOVIE_OVERVIEW));
        favorites.setRelease_date(getIntent().getStringExtra(MOVIE_TIME));
        favorites.setPoster(getIntent().getStringExtra(MOVIE_POSTER));
        favoriteHelper.insert(favorites);

//
//        int widgetIDs[] = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), ImageBannerWidget.class));
//        for (int id : widgetIDs)
//            AppWidgetManager.getInstance(getApplication()).notifyAppWidgetViewDataChanged(id, R.id.stack_view);
    }
    //delete favorite movie
    private void deleteFavorite(){
        favoriteHelper.delete(getIntent().getIntExtra(MOVIE_ID, 0));

//        int widgetIDs[] = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), ImageBannerWidget.class));
//        for (int id : widgetIDs)
//            AppWidgetManager.getInstance(getApplication()).notifyAppWidgetViewDataChanged(id, R.id.stack_view);
        finish();
    }
    // go to the purchase function
    private void startPurchase(){
        Intent i = new Intent(this, PurchaseProcess.class);
        i.putExtra("title",getIntent().getStringExtra(MOVIE_TITLE) );
        startActivity(i);
        finish();
    }
}
