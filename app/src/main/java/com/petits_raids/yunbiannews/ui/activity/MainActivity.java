package com.petits_raids.yunbiannews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.petits_raids.yunbiannews.R;
import com.petits_raids.yunbiannews.YunBianApplication;
import com.petits_raids.yunbiannews.support.adapter.ViewPagerAdapter;
import com.petits_raids.yunbiannews.ui.fragment.guokr.GuokrPagerFragment;
import com.petits_raids.yunbiannews.ui.fragment.news.NewsPagerFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private NewsPagerFragment newsPagerFragment;
    private GuokrPagerFragment guokrPagerFragment;
    private ViewPager viewPager;
    private MenuItem menuItem;
    private static String pageNumber = "PageNumber";
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (YunBianApplication.isNightMode())
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null)
            currentPage = savedInstanceState.getInt(pageNumber);
        init();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        viewPager = findViewById(R.id.view_pager);

        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        List<Fragment> fragmentList = new ArrayList<>();
        newsPagerFragment = new NewsPagerFragment();
        guokrPagerFragment = new GuokrPagerFragment();
        fragmentList.add(newsPagerFragment);
        fragmentList.add(guokrPagerFragment);
        List<String> titleList = new ArrayList<>();
        titleList.add("News");
        titleList.add("Guokr");

        ViewPagerAdapter adapter =
                new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.news_navigation:
                    viewPager.setCurrentItem(0, true);
                    if (currentPage == 0) {
                        newsPagerFragment.refreshData();
                    }
                    currentPage = 0;
                    break;
                case R.id.guokr_navigation:
                    viewPager.setCurrentItem(1, true);
                    if (currentPage == 1) {
                        guokrPagerFragment.refreshData();
                    }
                    currentPage = 1;
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(pageNumber, currentPage);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return false;
    }

}
