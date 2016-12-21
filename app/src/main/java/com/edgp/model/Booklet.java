package com.edgp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by daba on 2016-12-19.
 */

public class Booklet implements Parcelable {

    @SerializedName("bookletId")
    @Expose
    public int id;

    @SerializedName("bookletName")
    @Expose
    public String name;

    public Booklet() {
    }

    public Booklet(Parcel in){
        this.id = in.readInt();
        this.name = in.readString();
    }

    public Booklet(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Booklet createFromParcel(Parcel in) {
            return new Booklet(in);
        }

        public Booklet[] newArray(int size) {
            return new Booklet[size];
        }
    };
}
