package com.edgp.ui.pdfs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.edgp.R;
import com.edgp.events.FragmentReadyEvent;
import com.edgp.model.Article;
import com.edgp.model.Pdf;
import com.edgp.observables.BaseObservable;
import com.edgp.observables.PdfObservable;
import com.edgp.ui.articles.ArticleActivity;
import com.edgp.ui.common.BaseActivity;
import com.edgp.ui.common.LoadingFragment;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by daba on 2016-12-21.
 */

public class PdfActivity extends BaseActivity implements BaseObservable.Listener {

    public static final String PDF_ID_KEY = "pdf_id";
    public static final String PDF_NAME_KEY = "pdf_name";
    public static final String PDF_PARCEL_KEY = "pdf_parcel_key";

    private LoadingFragment loadingFragment;
    private ArticlesFragment articlesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        prepareLoadingFragment();
        setTitle(getPdfName());
    }

    private void startDataRetrieval(int pdfId) {
        new PdfObservable(this, this)
                .getPdf(pdfId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Pdf>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        onProgress(0, 0, getString(R.string.progress_error, e.getMessage()));
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Pdf pdf) {
                        preparePdfFragment(pdf);
                    }
                });
    }

    @Override
    protected void onBusEvent(Object event) {
        if (event instanceof FragmentReadyEvent) {
            int pdfId = getPdfId();
            startDataRetrieval(pdfId);
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

    public void onArticleClick(View view) {
        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra(ArticleActivity.ARTICLE_KEY, (Article)view.getTag());
        startActivity(intent);
    }

    private int getPdfId() {
        return getIntent().getIntExtra(PDF_ID_KEY, 0);
    }

    private String getPdfName() {
        return getIntent().getStringExtra(PDF_NAME_KEY);
    }

    private void prepareLoadingFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, loadingFragment = new LoadingFragment())
                .commitAllowingStateLoss();
    }

    private void preparePdfFragment(Pdf pdf) {
        if (isDestroyed()) {
            return;
        }
        Bundle bundle = new Bundle(1);
        bundle.putParcelable(PDF_PARCEL_KEY, pdf);
        articlesFragment = new ArticlesFragment();
        articlesFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, articlesFragment)
                .commitAllowingStateLoss();
    }
}
