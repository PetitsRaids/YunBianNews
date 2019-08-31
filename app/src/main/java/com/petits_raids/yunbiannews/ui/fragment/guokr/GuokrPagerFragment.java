package com.petits_raids.yunbiannews.ui.fragment.guokr;

import android.os.Bundle;
import android.util.Log;

import com.petits_raids.yunbiannews.R;
import com.petits_raids.yunbiannews.api.GuokrApi;
import com.petits_raids.yunbiannews.ui.fragment.PagerFragment;

public class GuokrPagerFragment extends PagerFragment {

    @Override
    protected void initFragmentList() {
        GuokrFragment pacGuokrFragment = new GuokrFragment();
        GuokrFragment factGuokrFragment = new GuokrFragment();
        GuokrFragment predatorGuokrFragment = new GuokrFragment();
        GuokrFragment beautyGuokrFragment = new GuokrFragment();
        Bundle hotBundle = new Bundle();
        hotBundle.putInt("type", 0);
        pacGuokrFragment.setArguments(hotBundle);
        Bundle frontierBundle = new Bundle();
        frontierBundle.putInt("type", 1);
        factGuokrFragment.setArguments(frontierBundle);
        Bundle visualBundle = new Bundle();
        visualBundle.putInt("type", 2);
        predatorGuokrFragment.setArguments(visualBundle);
        Bundle beautyBundle = new Bundle();
        beautyBundle.putInt("type", 3);
        beautyGuokrFragment.setArguments(beautyBundle);
        fragmentList.add(pacGuokrFragment);
        fragmentList.add(factGuokrFragment);
        fragmentList.add(predatorGuokrFragment);
        fragmentList.add(beautyGuokrFragment);
        titleList.add(getContext().getResources().getString(R.string.pac));
        titleList.add(getContext().getResources().getString(R.string.fact));
        titleList.add(getContext().getResources().getString(R.string.predator));
        titleList.add(getContext().getResources().getString(R.string.beauty));
    }

    public void refreshData() {
        GuokrFragment fragment = (GuokrFragment) fragmentList.get(getCurrentPage());
        fragment.refreshData();
    }
}
