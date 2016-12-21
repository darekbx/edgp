package com.edgp.api;

import com.edgp.model.wrappers.AppWrapper;
import com.edgp.model.wrappers.IssuesWrapper;
import com.edgp.model.wrappers.PdfWrapper;
import com.edgp.model.wrappers.TitlesWrapper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

/**
 * Created by daba on 2016-12-19.
 */

public interface EdgpRestService {

    @GET("{apiKey}")
    Call<AppWrapper> getApp(@Path(value = "apiKey") String apiKey);

    @GET("{apiKey}/{titleId}/titles")
    Call<TitlesWrapper> getTitles(@Path(value = "apiKey") String apiKey, @Path(value = "titleId") int titleId);

    @GET("{apiKey}/listissues/{titleId}")
    Call<IssuesWrapper> listIssues(@Path(value = "apiKey") String apiKey, @Path(value = "titleId") int titleId);

    @GET("{apiKey}/pdf/{pdfId}")
    Call<PdfWrapper> getPdf(@Path(value = "apiKey") String apiKey, @Path(value = "pdfId") int pdfId);

    @GET("{apiKey}/thumb/{coverId}/{imageSize}")
    @Streaming
    Call<ResponseBody> downloadImage(@Path("apiKey") String apiKey, @Path("coverId") int coverId, @Path("imageSize") int imageSize);
}
