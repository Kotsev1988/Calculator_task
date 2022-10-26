package com.example.calculator_task.ui;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.calculator_task.models.Operator;

public class saveInstCalculator implements Parcelable {
private String value;
private String operator;
    saveInstCalculator(){

    }

    protected saveInstCalculator(Parcel in) {
        value = in.readString();
        operator = in.readString();
    }

    public static final Creator<saveInstCalculator> CREATOR = new Creator<saveInstCalculator>() {
        @Override
        public saveInstCalculator createFromParcel(Parcel in) {
            return new saveInstCalculator(in);
        }

        @Override
        public saveInstCalculator[] newArray(int size) {
            return new saveInstCalculator[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(value);
        parcel.writeString(operator);
    }

    public void setValue(String value, String operator) {
        this.value = value;
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public String getOperator() {
        return operator;
    }
}
