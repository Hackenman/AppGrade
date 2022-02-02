package com.example.appgrade;

public class GradeItem {
    private String activity_name;
    private String grade_score;

    public GradeItem(String activityname, String gradescore){
        activity_name = activityname;
        grade_score = gradescore;
    }

    public String getActivity_name(){
        return activity_name;
    }

    public String getGrade_score(){
        return grade_score;
    }
}
