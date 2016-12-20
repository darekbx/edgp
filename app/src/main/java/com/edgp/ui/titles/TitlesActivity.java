package com.edgp.ui.titles;

import android.os.Bundle;

import com.edgp.R;
import com.edgp.events.FragmentReadyEvent;
import com.edgp.managers.SettingsManager;
import com.edgp.model.Title;
import com.edgp.observables.TitlesObservable;
import com.edgp.ui.BaseActivity;

import java.util.ArrayList;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TitlesActivity extends BaseActivity implements TitlesObservable.Listener {

    public static final String TITLES_PARCEL_KEY = "titles_parcel_key";

    private TitlesLoadingFragment titlesLoadingFragment;
    private TitlesFragment titlesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // TODO remove
        SettingsManager settingsManager = new SettingsManager(this);
        settingsManager.setScheme("http");
        settingsManager.setAuthority("api.embuk1.gazetaprawna.pl");
        settingsManager.setApiKey("KcroS9M8HgZmk01VumNJ6w");
        //
        prepareLoadingFragment();
    }

    private void startDataRetrieval() {
        new TitlesObservable(this, this)
                .getSplash()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Title>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        onProgress(0, 0, getString(R.string.progress_error, e.getMessage()));
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ArrayList<Title> titles) {
                        prepareTitlesFragment(titles);
                    }
                });
    }

    @Override
    protected void onBusEvent(Object event) {
        if (event instanceof FragmentReadyEvent) {
            startDataRetrieval();
        }
    }

    @Override
    public void onProgress(final int value, final int max, final String description) {
        if (titlesLoadingFragment == null) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                titlesLoadingFragment.updateProgress(value, max, description);
            }
        });
    }

    private void prepareLoadingFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, titlesLoadingFragment = new TitlesLoadingFragment())
                .commit();
    }

    private void prepareTitlesFragment(ArrayList<Title> titles) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelableArrayList(TITLES_PARCEL_KEY, titles);
        titlesFragment = new TitlesFragment();
        titlesFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, titlesFragment)
                .commit();
    }
}