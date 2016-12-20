package com.edgp.observables;

import android.content.Context;

import com.edgp.R;
import com.edgp.api.EdgpService;
import com.edgp.model.App;
import com.edgp.model.Title;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * Created by daba on 2016-12-20.
 */

public class SplashObservable {

    public interface Listener {
        void onProgress(int value, int max, String description);
    }

    private Context context;
    private EdgpService service;
    private Listener listener;

    public SplashObservable(Context context, Listener listener) {
        this.context = context;
        this.service = new EdgpService(context);
        this.listener = listener;
    }

    public Observable<List<Title>> getSplash() {
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
                    Observable.error(e);
                    return null;
                }
            }
        };
    }

    private Func1<App, Observable<List<Title>>> createTitlesFunc() {
        return new Func1<App, Observable<List<Title>>>() {
            @Override
            public Observable<List<Title>> call(App app) {
                listener.onProgress(2, 4, context.getString(R.string.progress_titles_1, app.publisherId));
                try {
                    List<Title> titles = service.getTitles(app.publisherId);
                    listener.onProgress(3, 4, context.getString(R.string.progress_titles_2, titles.size()));
                    return Observable.just(titles);
                } catch (IOException e) {
                    Observable.error(e);
                    return null;
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