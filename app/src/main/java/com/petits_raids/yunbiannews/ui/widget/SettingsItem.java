package com.petits_raids.yunbiannews.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import com.petits_raids.yunbiannews.R;

public class SettingsItem extends FrameLayout {

    private TextView settingsText;
    private SwitchCompat switchCompat;

    public SettingsItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.item_settings, this);
        settingsText = findViewById(R.id.settings_item_title);
        switchCompat = findViewById(R.id.settings_item_switch);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SettingsItem);
        String setting = array.getString(R.styleable.SettingsItem_settings_name);
        boolean state = array.getBoolean(R.styleable.SettingsItem_switch_state, false);
        array.recycle();
        settingsText.setText(setting);
        switchCompat.setChecked(state);
    }

    public void setSettingsText(String setting) {
        settingsText.setText(setting);
    }

    public void setSwitchChecked(boolean state){
        switchCompat.setChecked(state);
    }

    public void setOnCheckedSwitchListener(CompoundButton.OnCheckedChangeListener listener) {
        switchCompat.setOnCheckedChangeListener(listener);
    }
}
