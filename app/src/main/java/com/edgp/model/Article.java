package com.edgp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by daba on 2016-12-19.
 */

public class Article {

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
}