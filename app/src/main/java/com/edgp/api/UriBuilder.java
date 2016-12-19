package com.edgp.api;

import android.content.Context;
import android.net.Uri;

/**
 * Created by daba on 2016-12-16.
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

    private Uri baseUri() {
        return new Uri.Builder()
                .scheme("http")
                .authority(settingsManager.getAuthority())
                .appendPath(settingsManager.getApiKey())
                .build();
    }

    public Uri publishersUri() {
        return baseUri();
    }

    public Uri titlesUri(int publisherId) {
        return baseUri()
                .buildUpon()
                .appendPath(String.valueOf(publisherId))
                .appendPath(TITLES_URI)
                .build();
    }

    public Uri listIssuesUri(int titleId) {
        return baseUri()
                .buildUpon()
                .appendPath(LIST_ISSUES_URI)
                .appendPath(String.valueOf(titleId))
                .build();
    }

    public Uri issueUri(int issueId) {
        return baseUri()
                .buildUpon()
                .appendPath(ISSUE_URI)
                .appendPath(String.valueOf(issueId))
                .build();
    }

    public Uri pdfUri(int pdfId) {
        return baseUri()
                .buildUpon()
                .appendPath(PDF_URI)
                .appendPath(String.valueOf(pdfId))
                .build();
    }

    public Uri coverUri(int coverId, int imageSize) {
        return baseUri()
                .buildUpon()
                .appendPath(COVER_URI)
                .appendPath(String.valueOf(coverId))
                .appendPath(String.valueOf(imageSize))
                .build();
    }
}
