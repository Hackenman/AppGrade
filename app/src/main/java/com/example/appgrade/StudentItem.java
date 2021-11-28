package com.example.appgrade;

public class StudentItem {
    private String sName;
    private String sSex;

    public StudentItem(String name, String sex){
        sName = name;
        sSex = sex;
    }

    public String getsName(){
        return sName;
    }

    public String getsSex(){
        return sSex;
    }
}
