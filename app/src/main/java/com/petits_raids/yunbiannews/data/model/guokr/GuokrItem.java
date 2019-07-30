package com.petits_raids.yunbiannews.data.model.guokr;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "guokr_item")
public class GuokrItem{

    @PrimaryKey
    private int selfId;
    private long id;
    private String title;
    private String small_image;
    private String summary;
    private String resource_url;

    public int getSelfId() {
        return selfId;
    }

    public void setSelfId(int selfId) {
        this.selfId = selfId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSmall_image() {
        return small_image;
    }

    public void setSmall_image(String small_image) {
        this.small_image = small_image;
    }

    public String getResource_url() {
        return resource_url;
    }

    public void setResource_url(String resource_url) {
        this.resource_url = resource_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

}
