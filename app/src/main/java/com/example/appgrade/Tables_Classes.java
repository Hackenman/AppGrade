package com.example.appgrade;

import android.provider.BaseColumns;

public class Tables_Classes {

    private Tables_Classes(){}

    public static final class Tables_Class implements BaseColumns {
        public static final String TABLE_NAME = "class_names";
        public static final String COLUMN_CLASS = "class";
        public static final String COLUMN_LEVEL = "grade_level";
    }
}

