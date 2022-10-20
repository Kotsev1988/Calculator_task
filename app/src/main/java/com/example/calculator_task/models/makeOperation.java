package com.example.calculator_task.models;

public class makeOperation implements Operations{
    @Override
    public Double makeOperation(Double arg1, Double arg2, Operator operator) {
        switch (operator){
            case PLUS:
              return   arg1+arg2;
            case MINUS:
                return arg1-arg2;
            case MULT:
                return arg1*arg2;
            case DIV:
                return arg1/arg2;
        }
        return 0.0;
    }
}
