package com.edgp.observables;

import android.content.Context;

import com.edgp.R;
import com.edgp.model.Pdf;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * Created by daba on 2016-12-21.
 */

public class PdfObservable extends BaseObservable {

    public PdfObservable(Context context, Listener listener) {
        super(context, listener);
    }

    public Observable<Pdf> getPdf(int pdfId) {
        return Observable
                .just((Void)null)
                .flatMap(createPdfFunc(pdfId))
                .delay(500, TimeUnit.MILLISECONDS)
                .doOnCompleted(createEndProgress())
                .delay(200, TimeUnit.MILLISECONDS);
    }

    private Func1<Void, Observable<Pdf>> createPdfFunc(final int pdfId) {
        return new Func1<Void, Observable<Pdf>>() {
            @Override
            public Observable<Pdf> call(Void v) {
                listener.onProgress(0, 2, context.getString(R.string.progress_pdf_1, pdfId));
                try {
                    Pdf pdf = service.getPdf(pdfId);
                    listener.onProgress(1, 2, context.getString(R.string.progress_pdf_2, pdf.articles.size()));
                    return Observable.just(pdf);
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
