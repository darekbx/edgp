package com.edgp.model.wrappers;

import com.edgp.model.Title;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by daba on 2016-12-19.
 */

public class TitlesWrapper {

    @SerializedName("Titles")
    @Expose
    public List<Title> titles = null;
}
