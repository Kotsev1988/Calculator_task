package com.example.calculator_task.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.calculator_task.models.Operations;
import com.example.calculator_task.models.Operator;
import com.example.calculator_task.models.makeOperation;

public class ModelViewFactoryCalc implements ViewModelProvider.Factory{

    Operations operator = new makeOperation() ;

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass == MainViewModel.class){
            return (T) new MainViewModel(operator);
        }else{
           throw new IllegalStateException("unknown view model");
        }
    }
}
