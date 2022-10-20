package com.example.calculator_task.ui;

import android.os.Parcel;
import android.os.Parcelable;

public class saveInstCalculator implements Parcelable {
private String value;
    saveInstCalculator(){

    }

    protected saveInstCalculator(Parcel in) {
        value = in.readString();
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
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
