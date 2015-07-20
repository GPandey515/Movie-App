package com.techtach.movieapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techtach.movieapp.model.Movies;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailFragment extends Fragment {
    private static final String LOG_TAG = DetailFragment.class.getSimpleName();
    private Movies mMovies;

    private Context mContext;

    private TextView MOVIE_TITLE_TEXT_VIEW;
    private ImageView MOVIE_IMAGE_VIEW;
    private TextView MOVIE_YEAR_VIEW;
    private TextView MOVIE_TIME_VIEW;
    private TextView MOVIE_RATING_VIEW;
    private TextView MOVIE_ADD_TO_FAV_VIEW;
    private TextView MOVIE_DESCRIPTION_TEXT;
    private TextView MOVIE_TRAILER_TEXTVIEW;
    private ListView MOVIE_TRAILER_SCROLL_VIEW;

    private String mRatingText;
    private String mDate;
    private String mLength;

    public DetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getActivity().getIntent().getExtras();
        mMovies = getActivity().getIntent().getParcelableExtra("MOVIES");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        MOVIE_TITLE_TEXT_VIEW = (TextView) rootView.findViewById(R.id.title_text);
        MOVIE_IMAGE_VIEW = (ImageView) rootView.findViewById(R.id.movie_image);
        MOVIE_YEAR_VIEW = (TextView) rootView.findViewById(R.id.movie_details_texts).findViewById(R.id.movie_year);
        MOVIE_TIME_VIEW = (TextView) rootView.findViewById(R.id.movie_details_texts).findViewById(R.id.movie_time);
        MOVIE_RATING_VIEW = (TextView) rootView.findViewById(R.id.movie_details_texts).findViewById(R.id.movie_rating);
        //MOVIE_ADD_TO_FAV_VIEW = (TextView)rootView.findViewById(R.id.movie_details_texts).findViewById(R.id.movie_add_favorite);
        MOVIE_DESCRIPTION_TEXT  =(TextView) rootView.findViewById(R.id.movie_description_content).findViewById(R.id.movie_description_text);
        MOVIE_TRAILER_TEXTVIEW = (TextView) rootView.findViewById(R.id.movie_trailer) .findViewById(R.id.movie_trailor_text_view);
        MOVIE_TRAILER_SCROLL_VIEW = (ListView) rootView.findViewById(R.id.movie_trailer).findViewById(R.id.movie_trailer_listView);

        updateDetails();
        return rootView;
    }

    private void updateDetails() {
        String BASE_URL = "http://image.tmdb.org/t/p/w185/"; //base url for images
        String rating_text =  mMovies.getVote_average().toString()+"/10";
        String releaseDate[] = mMovies.getRelease_date().split("-");

        MOVIE_TITLE_TEXT_VIEW.setText(mMovies.getTitle());
        MOVIE_DESCRIPTION_TEXT.setText(mMovies.getOverview());
        MOVIE_RATING_VIEW.setText(rating_text);
        MOVIE_YEAR_VIEW.setText(releaseDate[0]);
        Picasso.with(mContext).load(BASE_URL+mMovies.getPoster_path()).into(MOVIE_IMAGE_VIEW);
    }

}
