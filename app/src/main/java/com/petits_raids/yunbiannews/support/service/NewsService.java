package com.petits_raids.yunbiannews.support.service;

import com.petits_raids.yunbiannews.data.model.news.NewsResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {

    @GET("/world/news_world.xml")
    Call<NewsResult> getWorldNews();
}
