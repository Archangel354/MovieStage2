package com.example.android.moviestage2;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by e244194 on 10/30/2017.
 */

public class VideoListLoader extends AsyncTaskLoader<List<VideoList>> {

    /** Query URL */
    private String mUrl;

    public VideoListLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<VideoList> loadInBackground() {
        return null;
    }
}
