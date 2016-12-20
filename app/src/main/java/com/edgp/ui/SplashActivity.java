package com.edgp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.edgp.R;
import com.edgp.managers.SettingsManager;
import com.edgp.model.Title;
import com.edgp.observables.SplashObservable;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SplashActivity extends Activity {

    @BindView(R.id.progress)
    ProgressBar progress;

    @BindView(R.id.progress_description)
    TextView progressDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SettingsManager settingsManager = new SettingsManager(this);
        settingsManager.setScheme("http");
        settingsManager.setAuthority("api.embuk1.gazetaprawna.pl");
        settingsManager.setApiKey("KcroS9M8HgZmk01VumNJ6w");

        ButterKnife.bind(this);

        new SplashObservable(this, new SplashObservable.Listener() {
            @Override
            public void onProgress(int value, int max, String description) {
                Log.v("---------", "description: " + description);
                publishProgress(value, max, description);
            }
        })
                .getSplash()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Title>>() {
                    @Override
                    public void onCompleted() {
                        Log.v("---------", "completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Title> titles) {
                        Log.v("-------", "TS: " + titles.size());
                    }
                });
    }

    private void publishProgress(final int value, final int max, final String description) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress.setMax(max);
                progress.setProgress(value);
                progressDescription.setText(description);
            }
        });
    }
}