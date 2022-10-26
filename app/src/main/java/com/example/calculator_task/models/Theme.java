package com.example.calculator_task.models;

import androidx.annotation.StringDef;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import com.example.calculator_task.R;

public enum Theme {

    ONE(R.style.Theme_Calculator_task, R.string.CodeStyle, 0),
    TWO(R.style.AppThemeLight, R.string.AppThemeLight, 1),
    THREE(R.style.AppThemeDark, R.string.AppThemeDark, 2);

    @StyleRes
    private int themeRes;

    @StringRes
    private int title;

    private int key;

    Theme(int themeRes, int title, int key){
        this.themeRes = themeRes;
        this.title = title;
        this.key = key;
    }

    public int getThemeRes() {
        return themeRes;
    }

    public int getTitle() {
        return title;
    }

    public int getKey() {
        return key;
    }
}
