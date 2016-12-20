package com.edgp.api;

import android.net.Uri;

import com.edgp.managers.SettingsManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.*;

/**
 * Created by daba on 2016-12-16.
 */
@RunWith(RobolectricTestRunner.class)
public class UriBuilderTest {

    @Before
    public void setUp() {
        SettingsManager settingsManager = new SettingsManager(RuntimeEnvironment.application);
        settingsManager.setScheme("http");
        settingsManager.setAuthority("api.embuk1.gazetaprawna.pl");
        settingsManager.setApiKey("KcroS9M8HgZmk01VumNJ6w");
    }

    @Test
    public void publishersUri() throws Exception {

        Uri uri = UriBuilder.getInstance(RuntimeEnvironment.application).publishersUri();

        assertEquals("http://api.embuk1.gazetaprawna.pl/KcroS9M8HgZmk01VumNJ6w", uri.toString());
    }

    @Test
    public void titlesUri() throws Exception {

        Uri uri = UriBuilder.getInstance(RuntimeEnvironment.application).titlesUri(1);

        assertEquals("http://api.embuk1.gazetaprawna.pl/KcroS9M8HgZmk01VumNJ6w/1/titles", uri.toString());
    }

    @Test
    public void listIssuesUri() throws Exception {

        Uri uri = UriBuilder.getInstance(RuntimeEnvironment.application).listIssuesUri(4);

        assertEquals("http://api.embuk1.gazetaprawna.pl/KcroS9M8HgZmk01VumNJ6w/listissues/4", uri.toString());
    }

    @Test
    public void issueUri() throws Exception {

        Uri uri = UriBuilder.getInstance(RuntimeEnvironment.application).issueUri(850);

        assertEquals("http://api.embuk1.gazetaprawna.pl/KcroS9M8HgZmk01VumNJ6w/issue/850", uri.toString());
    }

    @Test
    public void pdfUri() throws Exception {

        Uri uri = UriBuilder.getInstance(RuntimeEnvironment.application).pdfUri(4577);

        assertEquals("http://api.embuk1.gazetaprawna.pl/KcroS9M8HgZmk01VumNJ6w/pdf/4577", uri.toString());
    }

    @Test
    public void coverUri() throws Exception {

        Uri uri = UriBuilder.getInstance(RuntimeEnvironment.application).coverUri(539648, 200);

        assertEquals("http://api.embuk1.gazetaprawna.pl/KcroS9M8HgZmk01VumNJ6w/thumb/539648/200", uri.toString());
    }
}