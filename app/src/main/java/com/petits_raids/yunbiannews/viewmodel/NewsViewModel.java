package com.petits_raids.yunbiannews.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.petits_raids.yunbiannews.data.Repository;
import com.petits_raids.yunbiannews.data.model.News;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NewsViewModel extends AndroidViewModel {

    public LiveData<List<News>> liveNewsList;
    private Repository repository;
    private int type;
    private Executor executor = Executors.newSingleThreadExecutor();

    public NewsViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance();
    }

    public void getAllNews() {
        executor.execute(() -> {
            repository.getAllNews(type);
        });
    }

    public void updateNews() {
        executor.execute(() -> {
            repository.updateNews(type);
        });
    }

    public void setType(int type) {
        this.type = type;
        liveNewsList = repository.listLiveNewsList.get(type);
    }
}
