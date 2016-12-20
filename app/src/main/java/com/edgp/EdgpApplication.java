package com.edgp;

import android.app.Application;

/**
 * Created by daba on 2016-12-20.
 */

public class EdgpApplication extends Application {

    private static RxBus rxBus;

    public static RxBus getBusInstance() {
        if (rxBus == null) {
            rxBus = new RxBus();
        }
        return rxBus;
    }
}
