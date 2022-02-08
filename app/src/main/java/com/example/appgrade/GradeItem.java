package com.example.appgrade;

import android.os.Parcel;
import android.os.Parcelable;

public class GradeItem implements Parcelable {
    private String activity_name;
    private String grade_score;

    public GradeItem(String activityname, String gradescore){
        activity_name = activityname;
        grade_score = gradescore;
    }

    protected GradeItem(Parcel in) {
        activity_name = in.readString();
        grade_score = in.readString();
    }

    public static final Creator<GradeItem> CREATOR = new Creator<GradeItem>() {
        @Override
        public GradeItem createFromParcel(Parcel in) {
            return new GradeItem(in);
        }

        @Override
        public GradeItem[] newArray(int size) {
            return new GradeItem[size];
        }
    };

    public void changeScore( String activity, String score){
        activity_name = activity;
        grade_score = score;
    }

    public String getActivity_name(){
        return activity_name;
    }

    public String getGrade_score(){
        return grade_score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(activity_name);
        parcel.writeString(grade_score);
    }

}
