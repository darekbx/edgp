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

public class Pdf implements Parcelable {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("issue_name")
    @Expose
    public String name;

    @SerializedName("Articles")
    @Expose
    public List<Article> articles = null;

    public Pdf() {
    }

    public Pdf(Parcel in){
        this.id = in.readInt();
        this.name = in.readString();
        writeBookletsFromParcels(in.readParcelableArray(Article.class.getClassLoader()));
    }

    public Pdf(int id, String name, List<Article> articles) {
        this.id = id;
        this.name = name;
        this.articles = articles;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeParcelableArray(articles.toArray(new Article[articles.size()]), 0);
    }

    private void writeBookletsFromParcels(Parcelable[] parcelables) {
        articles = new ArrayList<>(parcelables.length);
        for (Parcelable parcelable : parcelables) {
            articles.add((Article)parcelable);
        }
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Pdf createFromParcel(Parcel in) {
            return new Pdf(in);
        }

        public Pdf[] newArray(int size) {
            return new Pdf[size];
        }
    };
}
