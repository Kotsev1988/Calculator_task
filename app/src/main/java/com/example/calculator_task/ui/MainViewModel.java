package com.example.calculator_task.ui;

import android.media.VolumeShaper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.calculator_task.models.Operations;
import com.example.calculator_task.models.Operator;

public class MainViewModel extends ViewModel {

    Operations operation;

    public  MainViewModel(Operations operations){
        this.operation = operations;
    }

    private double argOne = 0.0;
    private Double argTwo;
    private  MutableLiveData<Double> showData = new MutableLiveData<>() ;

    public LiveData<Double> getShowData(){

        return showData;
    }

    private Operator operatorPress;
    boolean dotPressed = false;
    int i =1;

    @Override
    protected void onCleared() {
        super.onCleared();
        System.out.println("onCleared");
    }

    public void onDigitPressed(int digit){

        if (argTwo == null) {

            if (dotPressed){
                argOne =argOne + Math.pow(10, -i)*digit;
                i++;
                showData.setValue(argOne);
            }else {
                argOne = argOne * 10 + digit;
                showData.setValue(argOne);
            }
        }else{
            if (dotPressed){
                argTwo = argTwo + Math.pow(10, -i)*digit;
                i++;
                showData.setValue(argTwo);
            }else {

                argTwo = argTwo * 10 + digit;
                showData.setValue(argTwo);

            }
        }

    }

    public void onOperatorPressed(Operator operator){

        if (operatorPress!=null){
            argOne = operation.makeOperation(argOne, argTwo, operatorPress);
            showData.setValue(argOne);
        }
        argTwo = 0.0;
        dotPressed = false;
        i = 1;
        operatorPress = operator;
    }

    public void onDotPressed() {
        dotPressed = true;
    }



    public void onACPressed() {
        argOne = 0.0;
        argTwo = null;
        i=1;
        dotPressed = false;
        operatorPress = null;
        showData.setValue(argOne);
    }



    public void onEquals() {

        if (operatorPress!=null){
            System.out.println("arg1 "+argOne + " , "+"arg2 "+argTwo);
            argOne = operation.makeOperation(argOne, argTwo, operatorPress);
            showData.setValue(argOne);
            operatorPress = null;
        }
    }


}
