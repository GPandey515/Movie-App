package com.techtach.movieapp.model;

/**
 * Created by noones on 7/13/15.
 */
import android.os.Parcel;
import android.os.Parcelable;

public class Trailers implements Parcelable{
    public  String versionName;
    public int image; // drawable reference id

    public Trailers(String vName, int image)
    {
        this.versionName = vName;
        this.image = image;
    }

    private Trailers(Parcel in){
        versionName = in.readString();
        image = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String toString() { return versionName + "--"  + "--" + image; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(versionName);
        parcel.writeInt(image);
    }

    public final Parcelable.Creator<Trailers> CREATOR = new Parcelable.Creator<Trailers>() {
        @Override
        public Trailers createFromParcel(Parcel parcel) {
            return new Trailers(parcel);
        }

        @Override
        public Trailers[] newArray(int i) {
            return new Trailers[i];
        }

    };
}