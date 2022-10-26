package com.example.calculator_task.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculator_task.R;
import com.example.calculator_task.models.Operations;
import com.example.calculator_task.models.Operator;
import com.example.calculator_task.models.Theme;
import com.example.calculator_task.models.ThemeRepository;
import com.example.calculator_task.models.ThemeRepositoryImpl;
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
    private ThemeRepository themeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        themeRepository = ThemeRepositoryImpl.getInstance(this);
        setTheme(themeRepository.getSavedTheme().getThemeRes());

        setContentView(R.layout.activity_main);
        resultText = (TextView) findViewById(R.id.calcDispley);

        if (getIntent().hasExtra("message")){
            System.out.println("Get IntentExtra "+getIntent().getStringExtra("message"));
        }

        presenter = new CalcPresenter( this, new makeOperation());
        if (savedInstanceState!=null){
            saveInstCalculator = savedInstanceState.getParcelable(KEY);

            str = saveInstCalculator.getValue();
            System.out.println("getOperator "+ saveInstCalculator.getOperator());
            resultText.setText(str);
            presenter.savedInst(str, saveInstCalculator.getOperator());
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



       ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
              Theme theme = (Theme) result.getData().getSerializableExtra(SelectThemeActivity.EXTRA_THEME);
              themeRepository.saveTheme(theme);
              recreate();
            }
        });

        findViewById(R.id.selectTheme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectThemeActivity.class);
                intent.putExtra(SelectThemeActivity.EXTRA_THEME, themeRepository.getSavedTheme());
                launcher.launch(intent);

            }
        });

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
        if (presenter.getOperatorPress()!=null) {
            saveInstCalculator.setValue(str, presenter.getOperatorPress().name());
        }else {
            saveInstCalculator.setValue(str, null);
        }


        outState.putParcelable(KEY, saveInstCalculator);
    }
}