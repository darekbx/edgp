package com.edgp.ui.issues;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.edgp.R;
import com.edgp.events.FragmentReadyEvent;
import com.edgp.model.Booklet;
import com.edgp.model.Issue;
import com.edgp.observables.BaseObservable;
import com.edgp.observables.IssuesObservable;
import com.edgp.ui.common.BaseActivity;
import com.edgp.ui.common.LoadingFragment;
import com.edgp.ui.pdfs.PdfActivity;

import java.util.ArrayList;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by daba on 2016-12-21.
 */

public class IssuesActivity extends BaseActivity implements BaseObservable.Listener {

    public static final String TITLE_ID_KEY = "title_id";
    public static final String TITLE_NAME_KEY = "title_name";
    public static final String ISSUES_PARCEL_KEY = "issues_parcel_key";

    private LoadingFragment loadingFragment;
    private IssuesFragment issuesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        prepareLoadingFragment();
        setTitle(getTitleName());
    }

    private void startDataRetrieval(int titleId) {
        new IssuesObservable(this, this)
                .getIssues(titleId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Issue>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        onProgress(0, 0, getString(R.string.progress_error, e.getMessage()));
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ArrayList<Issue> issues) {
                        prepareIssuesFragment(issues);
                    }
                });
    }

    @Override
    protected void onBusEvent(Object event) {
        if (event instanceof FragmentReadyEvent) {
            int titleId = getTitleId();
            startDataRetrieval(titleId);
        }
    }

    @Override
    public void onProgress(final int value, final int max, final String description) {
        if (loadingFragment == null) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingFragment.updateProgress(value, max, description);
            }
        });
    }

    public void onBookletClick(View view) {
        Booklet booklet = (Booklet)view.getTag();
        if (BookletUtils.hasValidBooklet(booklet)) {
            Intent intent = new Intent(this, PdfActivity.class);
            intent.putExtra(PdfActivity.PDF_ID_KEY, booklet.bookletPdfs.get(0).id);
            intent.putExtra(PdfActivity.PDF_NAME_KEY, booklet.name);
            startActivity(intent);
        }
    }

    private int getTitleId() {
        return getIntent().getIntExtra(TITLE_ID_KEY, 0);
    }

    private String getTitleName() {
        return getIntent().getStringExtra(TITLE_NAME_KEY);
    }

    private void prepareLoadingFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, loadingFragment = new LoadingFragment())
                .commitAllowingStateLoss();
    }

    private void prepareIssuesFragment(ArrayList<Issue> issues) {
        if (isDestroyed()) {
            return;
        }
        Bundle bundle = new Bundle(1);
        bundle.putParcelableArrayList(ISSUES_PARCEL_KEY, issues);
        issuesFragment = new IssuesFragment();
        issuesFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, issuesFragment)
                .commitAllowingStateLoss();
    }
}
