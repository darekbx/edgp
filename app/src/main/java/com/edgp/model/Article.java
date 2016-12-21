package com.edgp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by daba on 2016-12-19.
 */

public class Article implements Parcelable {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("content")
    @Expose
    public String content;

    @SerializedName("type")
    @Expose
    public String type;

    public Article() {
    }

    public Article(Parcel in){
        this.id = in.readInt();
        this.name = in.readString();
    }

    public Article(int id, String name, String content, String type) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(content);
        dest.writeString(type);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}