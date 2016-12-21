package com.edgp.api;

import android.content.Context;
import android.support.annotation.IntRange;

import com.edgp.managers.SettingsManager;
import com.edgp.model.App;
import com.edgp.model.Issue;
import com.edgp.model.Pdf;
import com.edgp.model.wrappers.AppWrapper;
import com.edgp.model.Title;
import com.edgp.model.wrappers.IssuesWrapper;
import com.edgp.model.wrappers.PdfWrapper;
import com.edgp.model.wrappers.TitlesWrapper;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by daba on 2016-12-19.
 */

public class EdgpService {

    private EdgpRestService edgpRestService;
    private SettingsManager settingsManager;

    private Context context;

    public EdgpService(Context context) {
        this.context = context;
        this.settingsManager = new SettingsManager(context);
        this.edgpRestService = buildRestService();
    }

    private EdgpRestService buildRestService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUri())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EdgpRestService.class);
    }

    public App getApp() throws IOException {
        Response<AppWrapper> response = edgpRestService.getApp(getApiKey()).execute();
        if (response.isSuccessful()) {
            AppWrapper wrapper = response.body();
            if (wrapper == null) {
                return null;
            }
            return wrapper.app;
        }
        return null;
    }

    public ArrayList<Title> getTitles(int publisherId) throws IOException {
        Response<TitlesWrapper> response = edgpRestService.getTitles(getApiKey(), publisherId).execute();
        if (response.isSuccessful()) {
            TitlesWrapper wrapper = response.body();
            if (wrapper == null) {
                return null;
            }
            return wrapper.titles;
        }
        return null;
    }

    public ArrayList<Issue> getIssues(int titleId) throws IOException {
        Response<IssuesWrapper> response = edgpRestService.listIssues(getApiKey(), titleId).execute();
        if (response.isSuccessful()) {
            IssuesWrapper wrapper = response.body();
            if (wrapper == null) {
                return null;
            }
            return wrapper.issues;
        }
        return null;
    }

    public Pdf getPdf(int pdfId) throws IOException {
        Response<PdfWrapper> response = edgpRestService.getPdf(getApiKey(), pdfId).execute();
        if (response.isSuccessful()) {
            PdfWrapper wrapper = response.body();
            if (wrapper == null) {
                return null;
            }
            return wrapper.pdf;
        }
        return null;
    }

    public byte[] getCover(int coverId, @IntRange(from = 50, to = 1000) int imageSize) throws IOException {
        Response<ResponseBody> response = edgpRestService.downloadImage(getApiKey(), coverId, imageSize).execute();
        if (response.isSuccessful()) {
            return response.body().bytes();
        }
        return null;
    }
    
    private String getApiKey() {
        return settingsManager.getApiKey();
    }

    private String getBaseUri() {
        return UriBuilder.getInstance(context).baseUri().toString() + "/";
    }
}
