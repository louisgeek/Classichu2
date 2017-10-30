package com.classichu.classichu2.api;


import com.classichu.classichu2.bean.BaseBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Classichu on 2017/5/22.
 */

public interface BaseApi {
    @GET("{url}")
    Observable<BaseBean<Object>> doGet(
            @Path("url") String url,
            @QueryMap Map<String, String> queryMap);

    @POST("{url}")
    Observable<ResponseBody> doPost(
            @Path("url") String url,
            //  @Header("") String authorization,
            @QueryMap Map<String, String> paramsMap);

    @POST("{url}")
    Observable<ResponseBody> doPostJson(
            @Path("url") String url,
            @Body RequestBody jsonStr);

    @Multipart
    @POST("{url}")
    Observable<ResponseBody> doPostFile(
            @Path("url") String url,
            @Part("image\"; filename=\"image.jpg") RequestBody requestBody);

    @POST("{url}")
    Call<ResponseBody> doPostFiles(
            @Path("url") String url,
            @Path("headers") Map<String, String> headers,
            @Part("filename") String description,
            @PartMap() Map<String, RequestBody> maps);

    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);
}
