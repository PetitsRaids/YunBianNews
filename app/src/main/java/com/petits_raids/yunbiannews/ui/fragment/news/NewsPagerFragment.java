package com.petits_raids.yunbiannews.ui.fragment.news;

import android.os.Bundle;
import android.util.Log;

import com.petits_raids.yunbiannews.R;
import com.petits_raids.yunbiannews.api.NewsApi;
import com.petits_raids.yunbiannews.ui.fragment.PagerFragment;

public class NewsPagerFragment extends PagerFragment {

    @Override
    protected void initFragmentList() {
        NewsFragment localNewsFragment = new NewsFragment();
        NewsFragment worldNewsFragment = new NewsFragment();
        NewsFragment politicsNewsFragment = new NewsFragment();
        NewsFragment pictureNewsFragment = new NewsFragment();
        Bundle localBundle = new Bundle();
        localBundle.putInt("type", NewsApi.LOCAL_NEWS);
        localNewsFragment.setArguments(localBundle);
        Bundle worldBundle = new Bundle();
        worldBundle.putInt("type", NewsApi.WORLD_NEWS);
        worldNewsFragment.setArguments(worldBundle);
        Bundle politicsBundle = new Bundle();
        politicsBundle.putInt("type", NewsApi.POLITICS_NEWS);
        politicsNewsFragment.setArguments(politicsBundle);
        Bundle pictureBundle = new Bundle();
        pictureBundle.putInt("type", NewsApi.PICTURE_NEWS);
        pictureNewsFragment.setArguments(pictureBundle);
        fragmentList.add(localNewsFragment);
        fragmentList.add(worldNewsFragment);
        fragmentList.add(politicsNewsFragment);
        fragmentList.add(pictureNewsFragment);
        titleList.add(getContext().getResources().getString(R.string.news_local));
        titleList.add(getContext().getResources().getString(R.string.news_world));
        titleList.add(getContext().getResources().getString(R.string.news_politics));
        titleList.add(getContext().getResources().getString(R.string.news_picture));
    }

    public void refreshData() {
        Log.d("Gakki", "NewsFragment: refreshData: " + fragmentList.size());
        NewsFragment fragment = (NewsFragment) fragmentList.get(getCurrentPage());
        fragment.refreshData();
    }
}
