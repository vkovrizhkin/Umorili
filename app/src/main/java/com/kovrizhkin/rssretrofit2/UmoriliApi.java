package com.kovrizhkin.rssretrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kovrizhkin V.A. on 20.08.2017.
 */

public interface UmoriliApi {
    @GET("api/get")
    Call<List<PostModel>> getData(@Query("site") String siteName, @Query("name") String resourceName, @Query("num") int count);
}
