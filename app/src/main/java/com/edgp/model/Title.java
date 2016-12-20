package com.edgp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Title implements Parcelable {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("categories")
    @Expose
    public String categories;

    @SerializedName("coverId")
    @Expose
    public int coverId;

    public Title() {
    }

    public Title(Parcel in){
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.categories = in.readString();
        this.coverId = in.readInt();
    }

    public Title(int id, String name, String description, String categories, int coverId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.coverId = coverId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(categories);
        dest.writeInt(coverId);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Title createFromParcel(Parcel in) {
            return new Title(in);
        }

        public Title[] newArray(int size) {
            return new Title[size];
        }
    };
}