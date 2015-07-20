package com.techtach.movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noones on 7/12/15.
 */
public class Movies implements Parcelable {
    private boolean adult;
    private boolean video;
    private String backdrop_path;
    private String original_language;
    private String original_title;
    private String overview;
    private String poster_path;
    private String release_date;
    private String title;
    private float vote_average;
    private long id;
    private int vote_count;
    private float popularity;
    private List<Integer> genre_ids;


    public Movies(Parcel in) {
        adult = in.readByte() != 0;
        backdrop_path = in.readString();
        genre_ids = new ArrayList<Integer>();
        in.readList(genre_ids, null);
        id = in.readLong();
        original_language = in.readString();
        original_title=  in.readString();
        overview = in.readString();
        popularity = in.readFloat();
        poster_path = in.readString();
        release_date = in.readString();
        title = in.readString();
        video = in.readByte() != 0;
        vote_average = in.readFloat();
        vote_count = in.readInt();

    }

    public Movies(){

    }

    public Movies(boolean adult,
                  String backdrop_path,
                  List<Integer> genre_ids,
                  long id,
                  String original_language,
                  String original_title,
                  String overview,
                  float popularity,
                  String poster_path,
                  String release_date,
                  String title,
                  boolean video,
                  float vote_average,
                  int vote_count) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.genre_ids = genre_ids;
        this.id = id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;

    }


    public boolean getAdult() {
        return this.adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return this.backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public List getGenre_ids() {
        return this.genre_ids;
    }

    public void setGenre_ids(List genre_ids) {
        this.genre_ids = genre_ids;
    }

    public Number getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return this.original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return this.original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return this.overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public float getPopularity() {
        return this.popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return this.poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return this.release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getVideo() {
        return this.video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public Number getVote_average() {
        return this.vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public Number getVote_count() {
        return this.vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String toString() {
        return this.getTitle();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (adult ? 1 : 0)); //adult = in.readByte() != 0;   while reading
        dest.writeString(backdrop_path);
        dest.writeList(genre_ids);
        //dest.writeList(genre_ids);
        dest.writeLong(id);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeFloat(popularity);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeString(title);
        dest.writeByte((byte) (video ? 1 : 0)); //video = in.readByte() != 0; while reading
        dest.writeFloat(vote_average);
        dest.writeInt(vote_count);
    }

    public static final Parcelable.Creator<Movies> CREATOR
            = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return  new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

}


