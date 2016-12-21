package com.edgp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daba on 2016-12-19.
 */

public class Issue implements Parcelable {

    @SerializedName("issueId")
    @Expose
    public int id;

    @SerializedName("issueName")
    @Expose
    public String name;

    @SerializedName("Booklets")
    @Expose
    public List<Booklet> booklets = null;

    public Issue() {
    }

    public Issue(Parcel in){
        this.id = in.readInt();
        this.name = in.readString();
        writeBookletsFromParcels(in.readParcelableArray(Booklet.class.getClassLoader()));
    }

    public Issue(int id, String name, List<Booklet> booklets) {
        this.id = id;
        this.name = name;
        this.booklets = booklets;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeParcelableArray(booklets.toArray(new Booklet[booklets.size()]), 0);
    }

    private void writeBookletsFromParcels(Parcelable[] parcelables) {
        booklets = new ArrayList<>(parcelables.length);
        for (Parcelable parcelable : parcelables) {
            booklets.add((Booklet)parcelable);
        }
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Issue createFromParcel(Parcel in) {
            return new Issue(in);
        }

        public Issue[] newArray(int size) {
            return new Issue[size];
        }
    };
}
