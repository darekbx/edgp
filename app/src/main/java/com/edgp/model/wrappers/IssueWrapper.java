package com.edgp.model.wrappers;

import com.edgp.model.IssueExtended;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by daba on 2016-12-19.
 */

public class IssueWrapper {

    @SerializedName("Issue")
    @Expose
    public IssueExtended issue = null;
}
