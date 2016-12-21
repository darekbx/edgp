package com.edgp.ui.common;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.edgp.EdgpApplication;

import rx.functions.Action1;

/**
 * Created by daba on 2016-12-20.
 */

public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgpApplication
                .getBusInstance()
                .toObserverable()
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        onBusEvent(o);
                    }
                });
    }

    protected void onBusEvent(Object event) {
    }
}