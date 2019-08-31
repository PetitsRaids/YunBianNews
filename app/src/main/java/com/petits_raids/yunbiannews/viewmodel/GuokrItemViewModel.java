package com.petits_raids.yunbiannews.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.petits_raids.yunbiannews.data.Repository;
import com.petits_raids.yunbiannews.data.model.guokr.GuokrItem;

import java.util.List;

public class GuokrItemViewModel extends AndroidViewModel {

    public LiveData<List<GuokrItem>> liveGuokrItemList;
    private Repository repository;
    private int type;

    public GuokrItemViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance();
    }

    public void updateGuokrItem() {
        repository.updateGuokrItem(type);
    }

    public void getAllGuokrItem() {
        repository.getAllGuokrItem(type);
    }

    public void setType(int type) {
        this.type = type;
        liveGuokrItemList = repository.listLiveGuokrItemList.get(type);
    }
}
