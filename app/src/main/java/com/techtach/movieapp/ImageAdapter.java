package com.techtach.movieapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.techtach.movieapp.model.Movies;

import java.util.ArrayList;

public class ImageAdapter<M> extends BaseAdapter {
    private String LOG_TAG = ImageAdapter.class.getSimpleName();
    String BASE_URL = "http://image.tmdb.org/t/p/w342/";
    private Context mContext;
    private ArrayList<Movies> movieList;
    private final LayoutInflater mInflater;


    private static class ViewHolder{
        ImageView mImage;
    }

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
        Movies movies = getItem(position);
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);

            view = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            viewHolder.mImage = (ImageView) view.findViewById(R.id.picture);
            view.setTag(viewHolder);
        }
        else {
          viewHolder = (ViewHolder) view.getTag();
        }

        //poster path URL
        if (movies!=null){
            Picasso.with(mContext).load(BASE_URL+movies.getPoster_path()).into(viewHolder.mImage);
        }
        return view;
    }
}
