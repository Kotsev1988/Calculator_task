package com.example.calculator_task.ui;

import com.example.calculator_task.models.Operations;
import com.example.calculator_task.models.Operator;

import java.text.DecimalFormat;
import java.text.ParsePosition;

public class CalcPresenter {
    private Operations operations;
    private CalcView calcView;


    private double argOne;
    private Double argTwo;

    private Operator operatorPress;
    DecimalFormat formater = new DecimalFormat();
    boolean dotPressed = false;
    int i =1;

    public CalcPresenter(CalcView calcView, Operations operations) {
        this.calcView = calcView;
        this.operations = operations;

    }

    public void onDigitPressed(int digit){

        if (argTwo == null) {

            if (dotPressed){
                argOne =argOne + Math.pow(10, -i)*digit;
                i++;
                showFormatted(argOne);
            }else {
                argOne = argOne * 10 + digit;
                showFormatted(argOne);
            }
        }else{
            if (dotPressed){
                argTwo = argTwo + Math.pow(10, -i)*digit;
                i++;
                showFormatted(argTwo);
            }else {

                argTwo = argTwo * 10 + digit;
                showFormatted(argTwo);

            }
        }
    }

    public void onOperatorPressed(Operator operator){
        if (operatorPress!=null){
            System.out.println("arg1 "+argOne + " , "+"arg2 "+argTwo);
            argOne = operations.makeOperation(argOne, argTwo, operatorPress);
            showFormatted(argOne);
        }
        argTwo = 0.0;
        dotPressed = false;
        i = 1;
        operatorPress = operator;
    }

    public void onDotPressed() {
       dotPressed = true;
    }

    private void showFormatted(double value){
        calcView.showText(formater.format(value));
    }

    public void onACPressed() {
        argOne = 0.0;
        argTwo = null;
        i=1;
        dotPressed = false;
        operatorPress = null;
        showFormatted(argOne);
    }


    public void savedInst(String integer, String oper) {
        ParsePosition pp = new ParsePosition(0);
        argOne = formater.parse(integer, pp).doubleValue();

        for (Operator operator: Operator.values()){
            if (operator.name().equals(oper)){
                operatorPress = operator;
                argTwo = 0.0;
            }
        }
        System.out.println("arg1 "+argOne + " , "+"arg2 "+argTwo);
        System.out.println("operatorPress"+operatorPress);
    }

    public void onEquals() {
        if (operatorPress!=null){
            System.out.println("arg1 "+argOne + " , "+"arg2 "+argTwo);
            argOne = operations.makeOperation(argOne, argTwo, operatorPress);
            showFormatted(argOne);
            operatorPress = null;
        }
    }

    public Operator getOperatorPress() {
        return operatorPress;
    }
}
