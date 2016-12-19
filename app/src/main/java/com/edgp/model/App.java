package com.edgp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by daba on 2016-12-19.
 */
public class App {

    @SerializedName("id_publisher")
    @Expose
    public int publisherId;

    @SerializedName("short_name")
    @Expose
    public String name;
}