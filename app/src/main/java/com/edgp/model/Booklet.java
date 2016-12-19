package com.edgp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by daba on 2016-12-19.
 */

public class Booklet {

    @SerializedName("bookletId")
    @Expose
    public int id;

    @SerializedName("bookletName")
    @Expose
    public String name;
}
