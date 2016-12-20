package com.edgp.api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.edgp.Utils;
import com.edgp.managers.SettingsManager;
import com.edgp.model.App;
import com.edgp.model.Issue;
import com.edgp.model.IssueExtended;
import com.edgp.model.Pdf;
import com.edgp.model.Title;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by daba on 2016-12-19.
 */
@Config(sdk = 23)
@RunWith(RobolectricTestRunner.class)
public class EdgpRestServiceTest {

    private EdgpService service;

    @Before
    public void setUp() {
        Utils.initializeSettings();
        service = new EdgpService(RuntimeEnvironment.application);
    }

    @Test
    public void getApp() throws Exception {
        App app = service.getApp();

        assertNotNull(app);
        assertEquals("DGP", app.name);
        assertTrue(app.publisherId > 0);
    }

    @Test
    public void getTitles() throws Exception {
        List<Title> titles = service.getTitles(1);

        assertNotNull(titles);
        assertTrue(titles.size() > 0);
    }

    @Test
    public void listIssues() throws Exception {
        List<Issue> issues = service.getIssues(4);

        assertNotNull(issues);
        assertTrue(issues.size() > 0);
    }

    @Test
    public void getIssue() throws Exception {
        IssueExtended issue = service.getIssue(850);

        assertNotNull(issue);
        assertTrue(issue.id > 0);
    }

    @Test
    public void getPdf() throws Exception {
        Pdf pdf = service.getPdf(4577);

        assertNotNull(pdf);
        assertTrue(pdf.id > 0);
    }

    @Test
    public void downloadImage() throws Exception {
        byte[] coverData = service.getCover(539648, 50);

        assertNotNull(coverData);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds  = true;

        Bitmap bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(coverData), null, options);
        assertNotNull(bitmap);
        assertEquals(50, bitmap.getWidth());

    }
}