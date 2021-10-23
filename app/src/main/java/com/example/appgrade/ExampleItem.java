package com.example.appgrade;

import android.os.Parcel;
import android.os.Parcelable;

public class ExampleItem implements Parcelable {
    private String className;
    private String gradeLvl;

    public ExampleItem(String classname, String gradelvl) {
        className = classname;
        gradeLvl = gradelvl;
    }

    protected ExampleItem(Parcel in) {
        className = in.readString();
        gradeLvl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(className);
        dest.writeString(gradeLvl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ExampleItem> CREATOR = new Creator<ExampleItem>() {
        @Override
        public ExampleItem createFromParcel(Parcel in) {
            return new ExampleItem(in);
        }

        @Override
        public ExampleItem[] newArray(int size) {
            return new ExampleItem[size];
        }
    };

    public void changeText1(String text) {
        className = text;
    }

    public String getClassName() {
        return className;
    }

    public String getGradeLvl() {
        return gradeLvl; }
}
