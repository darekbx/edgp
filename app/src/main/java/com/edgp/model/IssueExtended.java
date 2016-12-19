package com.edgp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by daba on 2016-12-19.
 */

public class IssueExtended {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("Booklets")
    @Expose
    public List<BookletExtended> booklets = null;
}
