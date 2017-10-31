package com.example.android.moviestage2;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<VideoList>>{

    private static final String MOVIES_SHARE_HASHTAG = " #MoviesStage1App";
    private String mMovies;
    private TextView mMovieDisplay;
    private TextView mDateDisplay;
    private TextView mVoteDisplay;
    private TextView mSynopsisDisplay;
    private TextView mPosterDisplay;
    private Context context;
    private Button btnTrailer;
    private TextView mMovieIDDisplay;
    private final Activity mActivity = this;
    private static final int VIDEOLIST_LOADER_ID = 2;
    public final static String TRAILERPREFIX = "https://api.themoviedb.org/3/movie/";
    public final static String TRAILERSUFIX = "/videos?api_key=02ff7187d940e5bd15cd5acd2b41b63e";



    /** Adapter for the list of trailers */
    private VideoAdapter vAdapter;
    private ArrayList arrayList;

    public final static String VIDEOSTRING = "https://api.themoviedb.org/3/movie/335984/videos?api_key=02ff7187d940e5bd15cd5acd2b41b63e";
    public String urlTrailerString = VIDEOSTRING;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mMovieDisplay = (TextView) findViewById(R.id.txtTitle);
        mDateDisplay = (TextView) findViewById(R.id.txtReleaseDate);
        mVoteDisplay = (TextView) findViewById(R.id.txtVoteAverage);
        mSynopsisDisplay = (TextView) findViewById(R.id.txtSynopsis);
        mPosterDisplay = (TextView) findViewById(R.id.txtPoster);
        mMovieIDDisplay = (TextView) findViewById(R.id.txtMovieID);

        Intent intentThatStartedThisActivity = getIntent();
        Bundle mBundle = intentThatStartedThisActivity.getExtras();
        String mTitle = mBundle.getString("MBUNDLE_TITLE");
        mMovieDisplay.setText(mTitle);
        String mDate = mBundle.getString("MBUNDLE_DATE");
        mDateDisplay.setText(mDate);
        String mVote = mBundle.getString("MBUNDLE_VOTE");
        mVoteDisplay.setText(mVote);
        String mSynopsis = mBundle.getString("MBUNDLE_SYNOPSIS");
        mSynopsisDisplay.setText(mSynopsis);
        String mPoster = mBundle.getString("MBUNDLE_POSTER");
        final String mMovieID = mBundle.getString("MBUNDLE_MOVIEID");

        btnTrailer = (Button) findViewById(R.id.btnTrailer);
        // Create a 2nd adapter that takes an empty list of trailers as input
        vAdapter = new VideoAdapter(DetailActivity.this, new ArrayList<VideoList>());

        //TextView txtPosterView = (TextView) convertView.findViewById(R.id.txtPoster);
        ImageView imageView = (ImageView) findViewById(R.id.imgPoster);
        imageView.setAdjustViewBounds(true);

        // Use the Picasso software tool to display URLs
        Picasso
                .with(context)
                .load(mPoster)
                .fit()
                .into(imageView);


        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                mMovies = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                mMovieDisplay.setText(mTitle);

            }
        }



        btnTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Display the URL to get the trailer IDs for each movie
                vAdapter.clear();
                vAdapter.notifyDataSetChanged();
                getLoaderManager().restartLoader(VIDEOLIST_LOADER_ID, null, DetailActivity.this);




                //Intent mIntent = new Intent(DetailActivity.this, DetailActivity.class);
                //Bundle mBundle = new Bundle();

            }
        });

    }
    private Intent createShareForecastIntent() {
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(mMovies + MOVIES_SHARE_HASHTAG)
                .getIntent();
        return shareIntent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForecastIntent());
        return true;
    }


    @Override
    public Loader<List<VideoList>> onCreateLoader(int i, Bundle args) {
        Log.i("ONCREATELOADERVIDEO... ","urlPosterString: " );
        return new VideoListLoader(this, urlTrailerString);
    }

    @Override
    public void onLoadFinished(Loader<List<VideoList>> loader, List<VideoList> trailer) {

        Log.i("onLoadFinished... ","trailer: " + trailer);
        // Play youtube trailer for the movie
        mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=gCcx85zbxz4")));


    }

    @Override
    public void onLoaderReset(Loader<List<VideoList>> loader) {

    }


}

