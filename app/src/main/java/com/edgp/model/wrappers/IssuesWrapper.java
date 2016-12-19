package com.edgp.model.wrappers;

import com.edgp.model.Issue;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by daba on 2016-12-19.
 */

public class IssuesWrapper {

    @SerializedName("issues")
    @Expose
    public List<Issue> issues = null;
}
