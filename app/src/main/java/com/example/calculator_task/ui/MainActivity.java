package com.example.calculator_task.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.calculator_task.R;
import com.example.calculator_task.models.Operations;
import com.example.calculator_task.models.Operator;
import com.example.calculator_task.models.makeOperation;
import com.google.android.material.radiobutton.MaterialRadioButton;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements CalcView {

    private TextView resultText;
    CalcPresenter presenter;
    private static final String KEY="KEY_SAVE";
    String str;
    saveInstCalculator saveInstCalculator;


    private static final int CodeStyle = 0;
    private static final int AppThemeLight = 1;
    private static final int AppThemeDark = 2;

    private static final String StyleSharedPref = "STYLE";
    private static final String appTheme = "APP_THEME";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(getAppTheme(R.style.AppThemeDark));
        setContentView(R.layout.activity_main);
        initThemeChooser();

        resultText = (TextView) findViewById(R.id.calcDispley);
        presenter = new CalcPresenter( this, new makeOperation());
        if (savedInstanceState!=null){
            saveInstCalculator = savedInstanceState.getParcelable(KEY);

            str = saveInstCalculator.getValue();
            resultText.setText(str);
            presenter.savedInst(str);
        }else {
            saveInstCalculator = new saveInstCalculator();
        }

        HashMap<Integer, Integer> symbols = new HashMap<>();
        symbols.put(R.id.zero, 0);
        symbols.put(R.id.one, 1);
        symbols.put(R.id.two, 2);
        symbols.put(R.id.three, 3);
        symbols.put(R.id.four, 4);
        symbols.put(R.id.five, 5);
        symbols.put(R.id.six, 6);
        symbols.put(R.id.seven, 7);
        symbols.put(R.id.eight, 8);
        symbols.put(R.id.nine, 9);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitPressed(symbols.get(view.getId()));
            }
        };

        findViewById(R.id.one).setOnClickListener(clickListener);
        findViewById(R.id.two).setOnClickListener(clickListener);
        findViewById(R.id.three).setOnClickListener(clickListener);
        findViewById(R.id.four).setOnClickListener(clickListener);
        findViewById(R.id.five).setOnClickListener(clickListener);
        findViewById(R.id.six).setOnClickListener(clickListener);
        findViewById(R.id.seven).setOnClickListener(clickListener);
        findViewById(R.id.eight).setOnClickListener(clickListener);
        findViewById(R.id.nine).setOnClickListener(clickListener);

        HashMap<Integer, Operator> operationSymbols = new HashMap<>();

        operationSymbols.put(R.id.plus, Operator.PLUS);
        operationSymbols.put(R.id.minus, Operator.MINUS);
        operationSymbols.put(R.id.miltiple, Operator.MULT);
        operationSymbols.put(R.id.divide, Operator.DIV);

        View.OnClickListener clickListenerOperation = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperatorPressed(operationSymbols.get(view.getId()));
            }
        };

        findViewById(R.id.ac).setOnClickListener(clickListenerOperation);
        findViewById(R.id.plus).setOnClickListener(clickListenerOperation);
        findViewById(R.id.minus).setOnClickListener(clickListenerOperation);
        findViewById(R.id.miltiple).setOnClickListener(clickListenerOperation);
        findViewById(R.id.divide).setOnClickListener(clickListenerOperation);

        findViewById(R.id.dote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDotPressed();
            }
        });

        findViewById(R.id.ac).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onACPressed();
            }
        });

        findViewById(R.id.equals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onEquals();
            }
        });

    }

    private void initThemeChooser() {

        initRadioButton(findViewById(R.id.MyStyle), CodeStyle);
        initRadioButton(findViewById(R.id.LightStyle), AppThemeLight);
        initRadioButton(findViewById(R.id.DarkStyle), AppThemeDark);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        ((MaterialRadioButton)radioGroup.getChildAt(getCodeStyle(AppThemeLight))).setChecked(true);

    }

    private void initRadioButton(View viewById, int style) {

        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Style RadioButton "+style);
                setAppTheme(style);
                recreate();
            }
        });
    }

    private int getAppTheme(int myStyle) {
        System.out.println("getAppTheme "+codeStyleToStyleId(myStyle) + "style "+myStyle);
        return codeStyleToStyleId(getCodeStyle(myStyle));
    }

    private int getCodeStyle(int myStyle) {
        SharedPreferences sharedPreferences = getSharedPreferences(StyleSharedPref, MODE_PRIVATE);
        System.out.println("getCodeStyle "+sharedPreferences.getInt(appTheme, myStyle));
        return  sharedPreferences.getInt(appTheme, myStyle);
    }

    private void setAppTheme(int style){
        SharedPreferences sharedPreferences = getSharedPreferences(StyleSharedPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        System.out.println("setAppTheme "+style);
        editor.putInt(appTheme, style);
        editor.apply();
    }





    private int codeStyleToStyleId(int myStyle) {

        switch (myStyle){
            case CodeStyle:
                return R.style.Theme_Calculator_task;
            case AppThemeLight:
                return R.style.AppThemeLight;
            case AppThemeDark:
                return R.style.AppThemeDark;

            default:
                return R.style.Theme_Calculator_task;
        }

    }

    @Override
    public void showText(String s) {
        System.out.println("text "+s);
        resultText.setText(s);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);


        str = resultText.getText().toString();
        saveInstCalculator.setValue(str);
        outState.putParcelable(KEY, saveInstCalculator);
    }
}