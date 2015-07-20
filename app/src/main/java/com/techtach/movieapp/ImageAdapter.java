package com.techtach.movieapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techtach.movieapp.model.Movies;

import java.util.ArrayList;

public class ImageAdapter<M> extends BaseAdapter {
    private String LOG_TAG = ImageAdapter.class.getSimpleName();
    String BASE_URL = "http://image.tmdb.org/t/p/w342/";
    private Context mContext;
    private ArrayList<Movies> movieList;

    private final LayoutInflater mInflater;

    public ImageAdapter(Context context, int fragment_main, int resource, ArrayList<Movies>objects)  {
        mInflater = LayoutInflater.from(context);
        this.movieList = objects;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Movies getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return movieList.get(position).getId().intValue();
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;

        if (v == null) {
            v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
        }

        picture = (ImageView) v.getTag(R.id.picture);

        //poster path URL
        Movies movie = movieList.get(position);
        if (movie!=null){
            Picasso.with(mContext).load(BASE_URL+movie.getPoster_path()).into(picture);
        }

        return v;
    }
}
