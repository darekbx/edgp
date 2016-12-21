package com.edgp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by daba on 2016-12-21.
 */

public class BookletPdf implements Parcelable {

    @SerializedName("subIssueId")
    @Expose
    public int id;

    public BookletPdf() {
    }

    public BookletPdf(Parcel in){
        this.id = in.readInt();
    }

    public BookletPdf(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public BookletPdf createFromParcel(Parcel in) {
            return new BookletPdf(in);
        }

        public BookletPdf[] newArray(int size) {
            return new BookletPdf[size];
        }
    };
}
