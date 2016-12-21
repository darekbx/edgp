package com.edgp.model.wrappers;

import com.edgp.model.Issue;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by daba on 2016-12-19.
 */

public class IssuesWrapper {

    @SerializedName("issues")
    @Expose
    public ArrayList<Issue> issues = null;
}
