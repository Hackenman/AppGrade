package com.example.appgrade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.appgrade.Tables_Classes.*;

import androidx.annotation.Nullable;

public class TableDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "class_names.db";
    public static final int DATABASE_VERSION = 1;

    public TableDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_CLASS_NAMES_TABLE = "CREATE TABLE " +
                Tables_Class.TABLE_NAME + " (" +
                Tables_Class._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Tables_Class.COLUMN_CLASS + " TEXT NOT NULL, " +
                Tables_Class.COLUMN_LEVEL + " TEXT NOT NULL" + ");";

        sqLiteDatabase.execSQL(SQL_CREATE_CLASS_NAMES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Tables_Class.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
