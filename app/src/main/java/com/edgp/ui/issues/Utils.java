package com.edgp.ui.issues;

import com.edgp.model.Booklet;

/**
 * Created by daba on 2016-12-21.
 */

public class Utils {

    public static boolean hasValidBooklet(Booklet booklet) {
        return booklet.bookletPdfs != null && booklet.bookletPdfs.size() == 1;
    }
}
