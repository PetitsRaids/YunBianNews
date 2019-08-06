package com.petits_raids.yunbiannews.ui.fragment.guokr;

import com.petits_raids.yunbiannews.api.GuokrApi;
import com.petits_raids.yunbiannews.ui.fragment.PagerFragment;

public class GuokrPagerFragment extends PagerFragment {
    @Override
    protected void initFragmentList() {
        fragmentList.add(new GuokrFragment(GuokrApi.HOT));
        fragmentList.add(new GuokrFragment(GuokrApi.FRONTIER));
        fragmentList.add(new GuokrFragment(GuokrApi.VISUAL));
        titleList.add("HOT");
        titleList.add("FRONTIER");
        titleList.add("VISUAL");
    }
}
