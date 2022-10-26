package com.example.calculator_task.models;

import android.content.res.Resources;

import java.util.List;

public interface ThemeRepository {
    Theme getSavedTheme();
    void saveTheme(Theme theme);
    List<Theme> getAll();

}
