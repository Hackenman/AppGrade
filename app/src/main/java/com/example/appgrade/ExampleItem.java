package com.example.appgrade;

public class ExampleItem {
    private String className;
    private String gradeLvl;

    public ExampleItem(String classname, String gradelvl) {
        className = classname;
        gradeLvl = gradelvl;
    }

    public String getClassName() {
        return className;
    }

    public String getGradeLvl() {
        return gradeLvl; }
}
