package com.petits_raids.yunbiannews.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.petits_raids.yunbiannews.data.model.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<News> newsList);

    @Query("DELETE FROM news")
    void deleteAll();

    @Query("SELECT * FROM news")
    LiveData<List<News>> getAllNews();
}
