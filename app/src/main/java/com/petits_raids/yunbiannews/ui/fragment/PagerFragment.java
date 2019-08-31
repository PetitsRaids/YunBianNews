package com.petits_raids.yunbiannews.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.petits_raids.yunbiannews.R;
import com.petits_raids.yunbiannews.support.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class PagerFragment extends Fragment {

    protected List<Fragment> fragmentList = new ArrayList<>();
    protected List<String> titleList = new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int currentPage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        tabLayout = view.findViewById(R.id.fragment_tab);
        viewPager = view.findViewById(R.id.fragment_pager);
        viewPager.setOffscreenPageLimit(4);
        init();
        return view;
    }

    private void init() {
        tabLayout.setupWithViewPager(viewPager);
        initFragmentList();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    protected int getCurrentPage() {
        return currentPage;
    }

    protected abstract void initFragmentList();
}
