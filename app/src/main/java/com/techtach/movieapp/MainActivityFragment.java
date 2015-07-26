package com.techtach.movieapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.techtach.movieapp.model.Api;
import com.techtach.movieapp.model.ApiResponse;
import com.techtach.movieapp.model.Movies;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    public static final String ENDPOINT_URL = "http://api.themoviedb.org/3";
    private static String sort;

    private ImageAdapter<Movies> mImageAdapter;
    ArrayList<Movies> movieList;
    private GridView gridView;
    private FetchMovies fetchMovies = new FetchMovies();

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Read sort from preference to display according to user preference
        //else display default preference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        sort = prefs.getString(getString(R.string.pref_sort),
                getString(R.string.pref_sort_default));
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_Rating && isNetworkAvailable()) {
            fetchMovies.fetchMovies("vote_average.desc");
            fetchMovies.createImageAdapter();
        } else if (id == R.id.action_Popular && isNetworkAvailable()) {
            fetchMovies.fetchMovies("popularity.desc");
            fetchMovies.createImageAdapter();
        }else if(id == R.id.action_refresh && isNetworkAvailable()){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            sort = prefs.getString(getString(R.string.pref_sort),
                    getString(R.string.pref_sort_default));
            fetchMovies.fetchMovies(sort);
            fetchMovies.createImageAdapter();
        }
        else if (id == R.id.action_settings) {
            return true;
        }
        //Toast.makeText(getActivity().getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("movies", movieList);
        super.onSaveInstanceState(outState);
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridview);

        //check if instance is saved and display from savedInstanceState else fetch from server
        if (savedInstanceState != null) {
            movieList = savedInstanceState.getParcelableArrayList("movies");
            fetchMovies.createImageAdapter();
        } else if (isNetworkAvailable()) {
            fetchMovies.fetchMovies(sort);
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "No internet connection ", Toast.LENGTH_LONG).show();
        }

        return rootView;
    }

    //check if internet is connected or not
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public class FetchMovies {
        private final String LOG_TAG = FetchMovies.class.getSimpleName();

        private void fetchMovies(String sort) {
            String key = getResources().getString(R.string.api_key); // API KEY

                RequestInterceptor requestInterceptor = new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("User-Agent", "Retrofit-Sample-App");
                    }
                };

                final RestAdapter restAdapter = new RestAdapter
                        .Builder()
                        .setEndpoint(ENDPOINT_URL)
                        .setRequestInterceptor(requestInterceptor)
                        .build();
                final Api movieApi = restAdapter.create(Api.class);

                movieApi.getData(sort, key, new Callback<ApiResponse>() {
                    @Override
                    public void success(ApiResponse movies, Response response) {
                        movieList = (ArrayList<Movies>) movies.getResults();
                        Log.d("Response", movieList.toString());
                        Toast.makeText(getActivity().getApplicationContext(), "fetched " + movieList.size() + "Objects", Toast.LENGTH_SHORT).show();
                        createImageAdapter();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getActivity().getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
        }

        public void createImageAdapter() {
            mImageAdapter =
                    new ImageAdapter<>(
                            getActivity(),//current context
                            R.layout.grid_item,
                            R.id.image,
                            movieList);
            gridView.setAdapter(mImageAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("MOVIES", movieList);
                    intent.putExtra("POSITION", position);
                    startActivity(intent);
                }
            });
        }
    }

}