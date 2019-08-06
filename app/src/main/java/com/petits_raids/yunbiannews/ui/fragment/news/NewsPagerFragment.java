package com.petits_raids.yunbiannews.ui.fragment.news;

import com.petits_raids.yunbiannews.api.NewsApi;
import com.petits_raids.yunbiannews.ui.fragment.PagerFragment;

public class NewsPagerFragment extends PagerFragment {

    @Override
    protected void initFragmentList() {
        fragmentList.add(new NewsFragment(NewsApi.LOCAL_NEWS));
        fragmentList.add(new NewsFragment(NewsApi.WORLD_NEWS));
        fragmentList.add(new NewsFragment(NewsApi.POLITICS_NEWS));
        fragmentList.add(new NewsFragment(NewsApi.PICTURE_NEWS));
        titleList.add("LOCAL");
        titleList.add("WORLD");
        titleList.add("POLITICS");
        titleList.add("PICTURE");
    }
}
