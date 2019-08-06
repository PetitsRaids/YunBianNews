package com.petits_raids.yunbiannews.data;

import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.petits_raids.yunbiannews.api.GuokrApi;
import com.petits_raids.yunbiannews.api.NewsApi;
import com.petits_raids.yunbiannews.data.database.AppDatabase;
import com.petits_raids.yunbiannews.data.model.News;
import com.petits_raids.yunbiannews.data.model.guokr.GuokrItem;
import com.petits_raids.yunbiannews.data.model.guokr.GuokrResult;
import com.petits_raids.yunbiannews.support.utils.DecodeUtil;
import com.petits_raids.yunbiannews.support.utils.NetworkUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Repository {

    public List<LiveData<List<News>>> listLiveNewsList;
    private LiveData<List<News>> liveNewsList;
    public List<LiveData<List<GuokrItem>>> listLiveGuokrItemList;
    private LiveData<List<GuokrItem>> liveGuokrItemList;

    private static Repository instance;
    private AppDatabase database;

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

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
        liveNewsList = listLiveNewsList.get(type);
        liveNewsList = database.newsDao().getAllNews(type);

        if (liveNewsList == null) {
            updateNews(type);
        }
    }

    public void updateGuokrItem(int type) {
        NetworkUtil.askForGuoKr(type).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int i = 0;
                switch (type) {
                    case GuokrApi.HOT:
                        i = 0;
                        break;
                    case GuokrApi.FRONTIER:
                        i = 20;
                        break;
                    case GuokrApi.VISUAL:
                        i = 40;
                        break;
                    default:
                        break;
                }
                List<GuokrItem> guokrItems =
                        new Gson().fromJson(response.body().string(), GuokrResult.class).getResult();
                for (GuokrItem item : guokrItems) {
                    item.setType(type);
                    item.setSelfId(i++);
                }
                database.guokrDao().insertAll(guokrItems);
                liveGuokrItemList = database.guokrDao().getAllItems(type);
            }
        });
    }
}
