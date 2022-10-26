package com.example.calculator_task.models;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.List;

public class ThemeRepositoryImpl implements ThemeRepository{

    private static  ThemeRepository INSTANCE;
    private SharedPreferences sharedPreferences ;
    private static final String StyleSharedPref = "STYLE";
    private static final String appTheme = "APP_THEME";

    public  ThemeRepositoryImpl(Context context){
        sharedPreferences = context.getSharedPreferences(StyleSharedPref, Context.MODE_PRIVATE);
    }

    public static ThemeRepository getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = new ThemeRepositoryImpl(context);
        }
        return INSTANCE;
    }

    @Override
    public Theme getSavedTheme() {
      int savedKey = sharedPreferences.getInt(StyleSharedPref, Theme.ONE.getKey());

        System.out.println("getSavedGet "+savedKey);
      for (Theme theme: Theme.values()){
          if (theme.getKey()==savedKey){
              return theme;
          }
      }
        return Theme.ONE;
    }

    @Override
    public void saveTheme(Theme theme) {
        System.out.println("getSavedDid "+theme.getKey());
        sharedPreferences.edit()
                .putInt(StyleSharedPref, theme.getKey())
                .apply();
    }

    @Override
    public List<Theme> getAll() {
        return Arrays.asList(Theme.values());
    }
}
