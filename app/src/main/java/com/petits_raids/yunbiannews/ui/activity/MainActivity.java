package com.petits_raids.yunbiannews.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.petits_raids.yunbiannews.R;
import com.petits_raids.yunbiannews.support.adapter.ViewPagerAdapter;
import com.petits_raids.yunbiannews.ui.fragment.guokr.GuokrPagerFragment;
import com.petits_raids.yunbiannews.ui.fragment.news.NewsPagerFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private NewsPagerFragment newsPagerFragment;
    private GuokrPagerFragment guokrPagerFragment;
    private ViewPager viewPager;
    private MenuItem menuItem;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        init();
        toolbar.setTitle(R.string.app_name);
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        viewPager = findViewById(R.id.view_pager);

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

}
