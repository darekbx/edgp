package com.edgp.api;

import android.content.Context;
import android.net.Uri;

import com.edgp.managers.SettingsManager;

/**
 * eDGP uri builder
 *
 * Publishers: http://api.embuk1.gazetaprawna.pl/KcroS9M8HgZmk01VumNJ6w
 * Titles: http://api.embuk1.gazetaprawna.pl/KcroS9M8HgZmk01VumNJ6w/1/titles
 * IssueList: http://api.embuk1.gazetaprawna.pl/KcroS9M8HgZmk01VumNJ6w/listissues/4
 * Issue: http://api.embuk1.gazetaprawna.pl/KcroS9M8HgZmk01VumNJ6w/issue/850
 * Articles: http://api.embuk1.gazetaprawna.pl/KcroS9M8HgZmk01VumNJ6w/pdf/4577
 * Cover: http://api.embuk1.gazetaprawna.pl/KcroS9M8HgZmk01VumNJ6w/thumb/539648/200 ({coverId}/{imageSize})
 *
 */
public class UriBuilder {

    private static final String TITLES_URI = "titles";
    private static final String LIST_ISSUES_URI = "listissues";
    private static final String ISSUE_URI = "issue";
    private static final String PDF_URI = "pdf";
    private static final String COVER_URI = "thumb";

    private static UriBuilder uriBuilder;
    private SettingsManager settingsManager;

    public static UriBuilder getInstance(Context context) {
        if (uriBuilder == null) {
            uriBuilder = new UriBuilder(context);
        }
        return uriBuilder;
    }

    public UriBuilder(Context context) {
        settingsManager = new SettingsManager(context);
    }

    public Uri baseUri() {
        return new Uri.Builder()
                .scheme(settingsManager.getScheme())
                .authority(settingsManager.getAuthority())
                .build();
    }

    public Uri baseUriWithApi() {
        return new Uri.Builder()
                .scheme(settingsManager.getScheme())
                .authority(settingsManager.getAuthority())
                .appendPath(settingsManager.getApiKey())
                .build();
    }

    public Uri publishersUri() {
        return baseUriWithApi();
    }

    public Uri titlesUri(int publisherId) {
        return baseUriWithApi()
                .buildUpon()
                .appendPath(String.valueOf(publisherId))
                .appendPath(TITLES_URI)
                .build();
    }

    public Uri listIssuesUri(int titleId) {
        return baseUriWithApi()
                .buildUpon()
                .appendPath(LIST_ISSUES_URI)
                .appendPath(String.valueOf(titleId))
                .build();
    }

    public Uri issueUri(int issueId) {
        return baseUriWithApi()
                .buildUpon()
                .appendPath(ISSUE_URI)
                .appendPath(String.valueOf(issueId))
                .build();
    }

    public Uri pdfUri(int pdfId) {
        return baseUriWithApi()
                .buildUpon()
                .appendPath(PDF_URI)
                .appendPath(String.valueOf(pdfId))
                .build();
    }

    public Uri coverUri(int coverId, int imageSize) {
        return baseUriWithApi()
                .buildUpon()
                .appendPath(COVER_URI)
                .appendPath(String.valueOf(coverId))
                .appendPath(String.valueOf(imageSize))
                .build();
    }
}
