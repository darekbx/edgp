package com.edgp.model.wrappers;

import com.edgp.model.App;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by daba on 2016-12-19.
 */

public class AppWrapper {

    @SerializedName("App")
    @Expose
    public App app;
}
