package com.edgp.api;

import android.content.Context;

/**
 * Created by daba on 2016-12-16.
 */

public class SettingsManager {

    private Context context;

    public SettingsManager(Context context) {
        this.context = context;
    }

    public String getScheme() {
        return "http";
    }

    public String getAuthority() {
        return "api.embuk1.gazetaprawna.pl";
    }

    public String getApiKey() {
        return "KcroS9M8HgZmk01VumNJ6w";
    }
}
