package com.edgp.observables;

import android.content.Context;

import com.edgp.api.EdgpService;

/**
 * Created by daba on 2016-12-21.
 */

public class BaseObservable {

    public interface Listener {
        void onProgress(int value, int max, String description);
    }

    protected Context context;
    protected EdgpService service;
    protected BaseObservable.Listener listener;

    public BaseObservable(Context context, BaseObservable.Listener listener) {
        this.context = context;
        this.service = new EdgpService(context);
        this.listener = listener;
    }
}
