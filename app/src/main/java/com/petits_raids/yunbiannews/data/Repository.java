package com.petits_raids.yunbiannews.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.petits_raids.yunbiannews.api.GuokrApi;
import com.petits_raids.yunbiannews.api.NewsApi;
import com.petits_raids.yunbiannews.data.database.AppDatabase;
import com.petits_raids.yunbiannews.data.model.guokr.GuokrItem;
import com.petits_raids.yunbiannews.data.model.guokr.GuokrResult;
import com.petits_raids.yunbiannews.data.model.news.News;
import com.petits_raids.yunbiannews.support.service.GuokrService;
import com.petits_raids.yunbiannews.support.utils.DecodeUtil;
import com.petits_raids.yunbiannews.support.utils.NetworkUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private static Repository instance;
    public List<LiveData<List<News>>> listLiveNewsList;
    public List<LiveData<List<GuokrItem>>> listLiveGuokrItemList;
    private LiveData<List<News>> liveNewsList;
    private MutableLiveData<List<GuokrItem>> liveGuokrItemList = new MutableLiveData<>();
    private Executor executors = Executors.newSingleThreadScheduledExecutor();
    private AppDatabase database;

    private Repository() {
        database = AppDatabase.getInstance();
        listLiveNewsList = new ArrayList<>();
        listLiveGuokrItemList = new ArrayList<>();
        listLiveNewsList.add(database.newsDao().getAllNews(NewsApi.LOCAL_NEWS));
        listLiveNewsList.add(database.newsDao().getAllNews(NewsApi.WORLD_NEWS));
        listLiveNewsList.add(database.newsDao().getAllNews(NewsApi.POLITICS_NEWS));
        listLiveNewsList.add(database.newsDao().getAllNews(NewsApi.PICTURE_NEWS));
        listLiveGuokrItemList.add(database.guokrDao().getAllItems(0));
        listLiveGuokrItemList.add(database.guokrDao().getAllItems(1));
        listLiveGuokrItemList.add(database.guokrDao().getAllItems(2));
        listLiveGuokrItemList.add(database.guokrDao().getAllItems(3));
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public void updateNews(int type) {
        String url = null;
        switch (type) {
            case NewsApi.LOCAL_NEWS:
                url = NewsApi.LOCAL;
                break;
            case NewsApi.WORLD_NEWS:
                url = NewsApi.WORLD;
                break;
            case NewsApi.POLITICS_NEWS:
                url = NewsApi.POLITICS;
                break;
            case NewsApi.PICTURE_NEWS:
                url = NewsApi.PICTURE;
                break;
            default:
                break;
        }
        NetworkUtil.askForNews(url).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                database.newsDao().insertAll(
                        DecodeUtil.parseNewsWithSAX(response.body().string(), type));
                liveNewsList = database.newsDao().getAllNews(type);
            }
        });

    }

    public void getAllNews(int type) {
        liveNewsList = database.newsDao().getAllNews(type);

        if (liveNewsList == null) {
            updateNews(type);
        }
    }

    public void updateGuokrItem(int type) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GuokrApi.GUOKR_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GuokrService guokrService = retrofit.create(GuokrService.class);
        guokrService.getGuokrResult(GuokrApi.getURL(type)).enqueue(new retrofit2.Callback<GuokrResult>() {
            @Override
            public void onResponse(retrofit2.Call<GuokrResult> call, retrofit2.Response<GuokrResult> response) {
                executors.execute(() -> {
                    if (response.body().isOk()) {
                        List<GuokrItem> guokrItemList = new ArrayList<>(response.body().getResult());
                        int selfId = type * 20;
                        for (GuokrItem item : guokrItemList) {
                            Log.d("TAG", item.getTitle());
                            item.setSelfId(selfId++);
                            item.setType(type);
                        }
                        database.guokrDao().insertAll(guokrItemList);
                    }
                });
            }

            @Override
            public void onFailure(retrofit2.Call<GuokrResult> call, Throwable t) {
                Log.d("TAG", "onFailure");
            }
        });
    }

    public void getAllGuokrItem(int type) {
        liveGuokrItemList.postValue(database.guokrDao().getAllItems(type).getValue());
    }

}
