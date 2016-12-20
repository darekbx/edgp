package com.edgp;

import com.edgp.managers.SettingsManager;

import org.robolectric.RuntimeEnvironment;

/**
 * Created by daba on 2016-12-20.
 */

public class Utils {

    public static void initializeSettings() {
        SettingsManager settingsManager = new SettingsManager(RuntimeEnvironment.application);
        settingsManager.setScheme("http");
        settingsManager.setAuthority("api.embuk1.gazetaprawna.pl");
        settingsManager.setApiKey("KcroS9M8HgZmk01VumNJ6w");
    }
}
