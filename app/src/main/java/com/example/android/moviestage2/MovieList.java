package com.example.android.moviestage2;

/**
 * Created by Owner on 10/14/2017.
 */

public class MovieList {

    // image for a particular movie
    private String mPosterPath;
    private String mMovieTitle;
    private String mReleaseDate;
    private String mVoteAverage;
    private String mSynopsis;

    public MovieList(String PosterPath, String MovieTitle, String ReleaseDate, String VoteAverage, String Synopsis) {
        mPosterPath = PosterPath;
        mMovieTitle = MovieTitle;
        mReleaseDate = ReleaseDate;
        mVoteAverage = VoteAverage;
        mSynopsis = Synopsis;
    }


    public String getmPosterPath() {
        return mPosterPath;
    }

    public String getmMovieTitle() {
        return mMovieTitle;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public String getmVoteAverage() {
        return mVoteAverage;
    }

    public String getmSynopsis() {
        return mSynopsis;
    }
}
