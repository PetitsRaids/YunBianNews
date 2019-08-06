package com.petits_raids.yunbiannews.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.petits_raids.yunbiannews.api.GuokrApi;
import com.petits_raids.yunbiannews.data.Repository;
import com.petits_raids.yunbiannews.data.model.guokr.GuokrItem;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GuokrItemViewModel extends AndroidViewModel {

    public LiveData<List<GuokrItem>> liveGuokrItemList;
    private Repository repository;
    private int type;
    private Executor executor = Executors.newSingleThreadExecutor();

    public GuokrItemViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance();
    }

    public void updateGuokrItem() {
        executor.execute(() -> {
            repository.updateGuokrItem(type);
        });
    }

    public void setType(int type) {
        this.type = type;
        liveGuokrItemList = repository.listLiveGuokrItemList.get(type);
    }
}
