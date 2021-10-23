package com.example.appgrade;

public class ExampleItem {
    private String className;
    private String gradeLvl;

    public ExampleItem(String classname, String gradelvl) {
        className = classname;
        gradeLvl = gradelvl;
    }

    public void changeText1(String text) {
        className = text;
    }

    public String getClassName() {
        return className;
    }

    public String getGradeLvl() {
        return gradeLvl; }
}
