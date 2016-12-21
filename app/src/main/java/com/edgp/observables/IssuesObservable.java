package com.edgp.observables;

import android.content.Context;

import com.edgp.R;
import com.edgp.model.Issue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * Created by daba on 2016-12-21.
 */

public class IssuesObservable extends BaseObservable {

    public IssuesObservable(Context context, Listener listener) {
        super(context, listener);
    }

    public Observable<ArrayList<Issue>> getIssues(int titleId) {
        return Observable
                .just((Void)null)
                .flatMap(createIssuesFunc(titleId))
                .delay(500, TimeUnit.MILLISECONDS)
                .doOnCompleted(createEndProgress())
                .delay(200, TimeUnit.MILLISECONDS);
    }

    private Func1<Void, Observable<ArrayList<Issue>>> createIssuesFunc(final int titleId) {
        return new Func1<Void, Observable<ArrayList<Issue>>>() {
            @Override
            public Observable<ArrayList<Issue>> call(Void app) {
                listener.onProgress(0, 2, context.getString(R.string.progress_issues_1, titleId));
                try {
                    ArrayList<Issue> issues = service.getIssues(titleId);
                    listener.onProgress(1, 2, context.getString(R.string.progress_issues_2, issues.size()));
                    return Observable.just(issues);
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
                listener.onProgress(2, 2, context.getString(R.string.progress_end));
            }
        };
    }
}
