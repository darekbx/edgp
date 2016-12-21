package com.edgp.observables;

import com.edgp.Utils;
import com.edgp.model.Issue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import rx.Observer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by daba on 2016-12-20.
 */
@Config(sdk = 23, manifest = "src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class IssuesObservableTest {

    private CountDownLatch lock = new CountDownLatch(1);

    @Before
    public void setUp() {
        Utils.initializeSettings();
    }

    @Test
    public void getIssues() throws Exception {
        IssuesObservable.Listener listener = mock(IssuesObservable.Listener.class);
        final Observer<? super List<Issue>> observer = mock(Observer.class);
        IssuesObservable titlesObservable = new IssuesObservable(RuntimeEnvironment.application, listener);

        titlesObservable
                .getIssues(4)
                .subscribe(new Observer<List<Issue>>() {
                    @Override
                    public void onCompleted() {
                        observer.onCompleted();
                        lock.countDown();
                    }

                    @Override
                    public void onError(Throwable e) {
                        observer.onError(e);
                        lock.countDown();
                    }

                    @Override
                    public void onNext(List<Issue> titles) {
                        observer.onNext(titles);
                        lock.countDown();
                    }
                });

        lock.await();

        verify(observer, times(1)).onNext(Matchers.anyListOf(Issue.class));
        verify(observer, never()).onError(any(Throwable.class));

        for (int i = 0; i <= 2; i++) {
            verify(listener, times(1)).onProgress(Matchers.eq(i), Matchers.eq(2), anyString());
        }
    }
}