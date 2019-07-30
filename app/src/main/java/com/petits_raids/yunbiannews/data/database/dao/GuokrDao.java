package com.petits_raids.yunbiannews.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.petits_raids.yunbiannews.data.model.guokr.GuokrItem;

import java.util.List;

@Dao
public interface GuokrDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<GuokrItem> guokrItems);

    @Query("DELETE FROM guokr_item")
    void deleteAll();

    @Query("SELECT * FROM guokr_item")
    LiveData<List<GuokrItem>> getAllItems();
}
