package com.techtach.movieapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techtach.movieapp.model.Movies;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailFragment extends Fragment {
    private static final String LOG_TAG = DetailFragment.class.getSimpleName();
    private Movies mMovies;
    private int position;
    private Context mContext;
    private ArrayList<Movies> movies;


    private String mRatingText;
    private String mDate;
    private String mLength;
    private String textShare;

    public static class ViewHolder{
        TextView mTitleTextView;
        ImageView mImageView;
        TextView mYearView;
        TextView mTimeView;
        TextView mRatingView;
        TextView mAddToFavView;
        TextView mDescriptionText;
        TextView mTrailerTextView;
        ListView mTrailerScrollView;
    }

    public DetailFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getActivity().getIntent().getExtras();
        movies = getActivity().getIntent().getParcelableArrayListExtra("MOVIES");
        position =  getActivity().getIntent().getIntExtra("POSITION", 0 );
        mMovies = movies.get(position);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("movies", movies);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
       // ShareActionProvider mshareActionProvider =
       //         (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        ViewHolder viewHolder = new ViewHolder(); //Viewholder cache stored in tag

        viewHolder.mTitleTextView = (TextView) rootView.findViewById(R.id.title_text);
        viewHolder.mImageView = (ImageView) rootView.findViewById(R.id.movie_image);
        viewHolder.mYearView = (TextView) rootView.findViewById(R.id.movie_details_texts).findViewById(R.id.movie_year);
        viewHolder.mTimeView = (TextView) rootView.findViewById(R.id.movie_details_texts).findViewById(R.id.movie_time);
        viewHolder.mRatingView = (TextView) rootView.findViewById(R.id.movie_details_texts).findViewById(R.id.movie_rating);
        //mAddToFavView = (TextView)rootView.findViewById(R.id.movie_details_texts).findViewById(R.id.movie_add_favorite);
        viewHolder.mDescriptionText =(TextView) rootView.findViewById(R.id.movie_description_content).findViewById(R.id.movie_description_text);
        viewHolder.mTrailerTextView = (TextView) rootView.findViewById(R.id.movie_trailer) .findViewById(R.id.movie_trailor_text_view);
        viewHolder.mTrailerScrollView = (ListView) rootView.findViewById(R.id.movie_trailer).findViewById(R.id.movie_trailer_listView);

        String BASE_URL = "http://image.tmdb.org/t/p/w185/"; //base url for images
        String rating_text =  mMovies.getVote_average().toString()+"/10";
        String releaseDate[] = mMovies.getRelease_date().split("-");

        viewHolder.mTitleTextView.setText(mMovies.getTitle());
        viewHolder.mDescriptionText.setText(mMovies.getOverview());
        viewHolder.mRatingView.setText(rating_text);
        viewHolder.mYearView.setText(releaseDate[0]);
        Picasso.with(mContext).load(BASE_URL+mMovies.getPoster_path()).into(viewHolder.mImageView);

        //updateDetails();
        return rootView;
    }

    private void updateDetails() {

    }

    private Intent createShareMoviesIntent(){
        Intent shareMovieIntent = new Intent();
        shareMovieIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareMovieIntent.setType("text/plain");
        shareMovieIntent.putExtra(Intent.EXTRA_TEXT,
                textShare + "#GaneshPandey");
        return shareMovieIntent;
    }

}
