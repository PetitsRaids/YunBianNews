package com.petits_raids.yunbiannews.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.petits_raids.yunbiannews.R;
import com.petits_raids.yunbiannews.support.adapter.ViewPagerAdapter;
import com.petits_raids.yunbiannews.ui.fragment.GuokrFragment;
import com.petits_raids.yunbiannews.ui.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        tabLayout.setupWithViewPager(viewPager);
        toolbar.setTitle(R.string.app_name);
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.channel_tabs);
        viewPager = findViewById(R.id.view_pager);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new NewsFragment());
        fragmentList.add(new GuokrFragment());
        List<String> titleList = new ArrayList<>();
        titleList.add("News");
        titleList.add("Guokr");

        ViewPagerAdapter adapter =
                new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
    }

}
