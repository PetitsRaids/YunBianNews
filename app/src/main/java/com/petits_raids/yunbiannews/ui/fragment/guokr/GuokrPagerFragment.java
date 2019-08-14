package com.petits_raids.yunbiannews.ui.fragment.guokr;

import android.os.Bundle;

import com.petits_raids.yunbiannews.api.GuokrApi;
import com.petits_raids.yunbiannews.ui.fragment.PagerFragment;

public class GuokrPagerFragment extends PagerFragment {
    @Override
    protected void initFragmentList() {
        GuokrFragment hotGuokrFragment = new GuokrFragment();
        GuokrFragment frontierGuokrFragment = new GuokrFragment();
        GuokrFragment visualGuokrFragment = new GuokrFragment();
        Bundle hotBundle = new Bundle();
        hotBundle.putInt("type", GuokrApi.HOT);
        hotGuokrFragment.setArguments(hotBundle);
        Bundle frontierBundle = new Bundle();
        frontierBundle.putInt("type", GuokrApi.FRONTIER);
        frontierGuokrFragment.setArguments(frontierBundle);
        Bundle visualBundle = new Bundle();
        visualBundle.putInt("type", GuokrApi.VISUAL);
        visualGuokrFragment.setArguments(visualBundle);
        fragmentList.add(hotGuokrFragment);
        fragmentList.add(frontierGuokrFragment);
        fragmentList.add(visualGuokrFragment);
        titleList.add("HOT");
        titleList.add("FRONTIER");
        titleList.add("VISUAL");
    }

    public void refreshData() {
        GuokrFragment fragment = (GuokrFragment) fragmentList.get(getCurrentPage());
        fragment.refreshData();
    }
}
