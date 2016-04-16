package com.valmaraz.mvp.model.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Víctor Almaraz on 16/04/2016.
 * http://www.valmaraz.com
 */
public interface RetrofitService {

    @GET("")
    Call<ResponseBody> get(@Url String url);
}
