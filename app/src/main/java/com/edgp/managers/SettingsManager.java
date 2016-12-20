package com.edgp.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by daba on 2016-12-16.
 */

public class SettingsManager {

    private static final String SCHEME_KEY = "scheme_key";
    private static final String AUTHORITY_KEY = "authority_key";
    private static final String API_KEY = "api_key";

    private Context context;

    public SettingsManager(Context context) {
        this.context = context;
    }

    public String getScheme() {
        return getPreferences().getString(SCHEME_KEY, null);
    }

    public void setScheme(String scheme) {
        getPreferences()
                .edit()
                .putString(SCHEME_KEY, scheme)
                .apply();
    }

    public String getAuthority() {
        return getPreferences().getString(AUTHORITY_KEY, null);
    }

    public void setAuthority(String authority) {
        getPreferences()
                .edit()
                .putString(AUTHORITY_KEY, authority)
                .apply();
    }

    public String getApiKey() {
        return getPreferences().getString(API_KEY, null);
    }

    public void setApiKey(String apiKey) {
        getPreferences()
                .edit()
                .putString(API_KEY, apiKey)
                .apply();
    }

    public void clear() {
        getPreferences().edit().clear().apply();
    }

    private SharedPreferences getPreferences() {
        return context.getSharedPreferences(SettingsManager.class.getName(), Context.MODE_PRIVATE);
    }
}
