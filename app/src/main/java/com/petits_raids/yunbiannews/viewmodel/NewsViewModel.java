package com.petits_raids.yunbiannews.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.petits_raids.yunbiannews.data.model.News;
import com.petits_raids.yunbiannews.data.Repository;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {

    public LiveData<List<News>> liveNewsList;
    private Repository repository;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance();
        liveNewsList = repository.liveNewsList;
    }

    public void updateNews(){
        repository.updateNews();
    }
}
