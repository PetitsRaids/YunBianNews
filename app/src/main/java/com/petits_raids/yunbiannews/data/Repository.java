package com.petits_raids.yunbiannews.data;

import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.petits_raids.yunbiannews.api.NewsApi;
import com.petits_raids.yunbiannews.data.database.AppDatabase;
import com.petits_raids.yunbiannews.data.model.News;
import com.petits_raids.yunbiannews.data.model.guokr.GuokrItem;
import com.petits_raids.yunbiannews.data.model.guokr.GuokrResult;
import com.petits_raids.yunbiannews.support.utils.DecodeUtil;
import com.petits_raids.yunbiannews.support.utils.NetworkUtil;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Repository {

    public LiveData<List<News>> liveNewsList;
    public LiveData<List<GuokrItem>> liveGuokrItemList;
    private static Repository instance;
    private AppDatabase database;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    private Repository() {
        database = AppDatabase.getInstance();
        liveNewsList = database.newsDao().getAllNews();
        liveGuokrItemList = database.guokrDao().getAllItems();
    }

    public void insertAll(List<News> newsList) {
        executor.execute(() -> database.newsDao().insertAll(newsList));
    }

    public void updateNews() {
        executor.execute(() -> {
            NetworkUtil.askForNews(NewsApi.WORLD).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    database.newsDao().insertAll(
                            DecodeUtil.parseNewsWithSAX(response.body().string()));
                }
            });
            liveNewsList = database.newsDao().getAllNews();
        });
    }

    public void updateGuokrItem() {
        executor.execute(() ->
                NetworkUtil.askForGuoKr().enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        int i = 0;
                        List<GuokrItem> guokrItems =
                                new Gson().fromJson(response.body().string(), GuokrResult.class).getResult();
                        for (GuokrItem item : guokrItems){
                            item.setSelfId(i++);
                        }
                        database.guokrDao().insertAll(guokrItems);
                        liveGuokrItemList = database.guokrDao().getAllItems();
                    }
                })
        );
    }
}
