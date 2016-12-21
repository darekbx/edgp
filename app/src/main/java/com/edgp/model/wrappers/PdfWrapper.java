package com.edgp.model.wrappers;

import com.edgp.model.Pdf;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by daba on 2016-12-19.
 */

public class PdfWrapper {

    @SerializedName("Pdf")
    @Expose
    public Pdf pdf = null;
}
