package com.edgp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by daba on 2016-12-19.
 */

public class Pdf {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("issue_name")
    @Expose
    public String name;

    @SerializedName("Articles")
    @Expose
    public List<Article> articles = null;
}
