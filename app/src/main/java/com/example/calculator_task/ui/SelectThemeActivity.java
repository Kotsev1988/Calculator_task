package com.example.calculator_task.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.calculator_task.R;
import com.example.calculator_task.models.Theme;
import com.example.calculator_task.models.ThemeRepository;
import com.example.calculator_task.models.ThemeRepositoryImpl;
import com.google.android.material.radiobutton.MaterialRadioButton;

public class SelectThemeActivity extends AppCompatActivity {
    ThemeRepository themeRepository;
    public static final String EXTRA_THEME="EXTRA_THEME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        themeRepository = ThemeRepositoryImpl.getInstance(this);
        initThemeChooser();
    }

    private void initRadioButton(View viewById, Theme style) {

        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_THEME, style);
                setResult(Activity.RESULT_OK, intent);
                finish();
               // themeRepository.saveTheme(style);
                //recreate();
            }
        });
    }

    private void initThemeChooser() {

        initRadioButton(findViewById(R.id.MyStyle), Theme.ONE);
        initRadioButton(findViewById(R.id.LightStyle), Theme.TWO);
        initRadioButton(findViewById(R.id.DarkStyle), Theme.THREE);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        ((MaterialRadioButton)radioGroup.getChildAt(themeRepository.getSavedTheme().getKey())).setChecked(true);

    }
}