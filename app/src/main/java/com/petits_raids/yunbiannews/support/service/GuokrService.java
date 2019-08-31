package com.petits_raids.yunbiannews.support.service;

import com.petits_raids.yunbiannews.data.model.guokr.GuokrResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GuokrService {

    @GET("article.json?retrieve_type=by_channel")
    Call<GuokrResult> getGuokrResult(@Query("channel_key") String key);
}
