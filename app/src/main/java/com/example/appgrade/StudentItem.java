package com.example.appgrade;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentItem implements Parcelable {
    private String sName;
    private String sSex;

    public StudentItem(String name, String sex){
        sName = name;
        sSex = sex;
    }

    protected StudentItem(Parcel in) {
        sName = in.readString();
        sSex = in.readString();
    }

    public static final Creator<StudentItem> CREATOR = new Creator<StudentItem>() {
        @Override
        public StudentItem createFromParcel(Parcel in) {
            return new StudentItem(in);
        }

        @Override
        public StudentItem[] newArray(int size) {
            return new StudentItem[size];
        }
    };

    public String getsName(){
        return sName;
    }

    public String getsSex(){
        return sSex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sName);
        dest.writeString(sSex);
    }
}
