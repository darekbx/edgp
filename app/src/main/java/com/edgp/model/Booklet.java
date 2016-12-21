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

public class Booklet implements Parcelable {

    @SerializedName("bookletId")
    @Expose
    public int id;

    @SerializedName("bookletName")
    @Expose
    public String name;

    @SerializedName("Pdfs")
    @Expose
    public List<BookletPdf> bookletPdfs = null;

    public Booklet() {
    }

    public Booklet(Parcel in){
        this.id = in.readInt();
        this.name = in.readString();
        writeBookletPdfsFromParcels(in.readParcelableArray(BookletPdf.class.getClassLoader()));
    }

    public Booklet(int id, String name, List<BookletPdf> bookletPdfs) {
        this.id = id;
        this.name = name;
        this.bookletPdfs = bookletPdfs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeParcelableArray(bookletPdfs.toArray(new BookletPdf[bookletPdfs.size()]), 0);
    }

    private void writeBookletPdfsFromParcels(Parcelable[] parcelables) {
        bookletPdfs = new ArrayList<>(parcelables.length);
        for (Parcelable parcelable : parcelables) {
            bookletPdfs.add((BookletPdf)parcelable);
        }
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
