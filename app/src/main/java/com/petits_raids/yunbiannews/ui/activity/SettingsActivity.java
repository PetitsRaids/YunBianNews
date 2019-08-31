package com.petits_raids.yunbiannews.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.petits_raids.yunbiannews.R;
import com.petits_raids.yunbiannews.YunBianApplication;
import com.petits_raids.yunbiannews.support.utils.NameUtils;
import com.petits_raids.yunbiannews.ui.widget.SettingsItem;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (YunBianApplication.isNightMode())
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar_settings);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SettingsItem settingsItem = findViewById(R.id.settings_item_night_mode);
        settingsItem.setSwitchChecked(YunBianApplication.isNightMode());
        settingsItem.setOnCheckedSwitchListener((buttonView, isChecked) -> {
            if (isChecked)
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            else
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            YunBianApplication.setNightMode(isChecked);
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        SharedPreferences.Editor editor = YunBianApplication.getContext().getSharedPreferences(NameUtils.SHARED_PREFERENCES_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(NameUtils.NIGHT_MODE_NAME, YunBianApplication.isNightMode());
        editor.apply();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

}
