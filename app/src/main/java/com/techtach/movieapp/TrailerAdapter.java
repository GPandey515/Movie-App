package com.techtach.movieapp;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.techtach.movieapp.model.Trailers;

/**
 * Created by noones on 7/13/15.
 */
public class TrailerAdapter extends ArrayAdapter<Trailers> {
    private static final String LOG_TAG = TrailerAdapter.class.getSimpleName();


    public TrailerAdapter(Context context, int resource) {
        super(context, resource);
    }

    public TrailerAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public TrailerAdapter(Context context, int resource, Trailers[] objects) {
        super(context, resource, objects);
    }
}
