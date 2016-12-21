package com.edgp.observables;

import android.content.Context;

import com.edgp.R;
import com.edgp.model.App;
import com.edgp.model.Title;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * Created by daba on 2016-12-20.
 */

public class TitlesObservable extends BaseObservable {

    public TitlesObservable(Context context, Listener listener) {
        super(context, listener);
    }

    public Observable<ArrayList<Title>> getTitles() {
        return Observable
                .just((Void)null)
                .flatMap(createAppFunc())
                .flatMap(createTitlesFunc())
                .delay(500, TimeUnit.MILLISECONDS)
                .doOnCompleted(createEndProgress())
                .delay(200, TimeUnit.MILLISECONDS);
    }

    private Func1<Void, Observable<App>> createAppFunc() {
        return new Func1<Void, Observable<App>>() {
            @Override
            public Observable<App> call(Void _) {
                listener.onProgress(0, 4, context.getString(R.string.progress_app_1));
                try {
                    App app = service.getApp();
                    listener.onProgress(1, 4, context.getString(R.string.progress_app_2, app.publisherId));
                    return Observable.just(app);
                } catch (IOException e) {
                    throw Exceptions.propagate(e);
                }
            }
        };
    }

    private Func1<App, Observable<ArrayList<Title>>> createTitlesFunc() {
        return new Func1<App, Observable<ArrayList<Title>>>() {
            @Override
            public Observable<ArrayList<Title>> call(App app) {
                listener.onProgress(2, 4, context.getString(R.string.progress_titles_1, app.publisherId));
                try {
                    ArrayList<Title> titles = service.getTitles(app.publisherId);
                    listener.onProgress(3, 4, context.getString(R.string.progress_titles_2, titles.size()));
                    return Observable.just(titles);
                } catch (IOException e) {
                    throw Exceptions.propagate(e);
                }
            }
        };
    }

    private Action0 createEndProgress() {
        return new Action0() {
            @Override
            public void call() {
                listener.onProgress(4, 4, context.getString(R.string.progress_end));
            }
        };
    }
}