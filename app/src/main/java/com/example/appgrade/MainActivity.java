package com.example.appgrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase classDataBase;

    private androidx.recyclerview.widget.RecyclerView RecyclerView;
    private ExampleAdapter Adapter;
    private RecyclerView.LayoutManager LayoutManager;

    private String nClass;
    private String gLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableDBHelper dbHelper = new TableDBHelper(this);
        classDataBase = dbHelper.getWritableDatabase();

        setButtons();
        loadClasses();
        buildRecycleView();
        addClassData();

    }

    public void setButtons(){
        Button createClass = findViewById(R.id.add);
        createClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateClass();
            }
        });
    }

    private void loadClasses(){

    }

    public void buildRecycleView(){
        RecyclerView = findViewById(R.id.recyclerView);

        LayoutManager = new LinearLayoutManager(this);
        Adapter = new ExampleAdapter(this, getAllItems());
        RecyclerView.setLayoutManager(LayoutManager);
        RecyclerView.setAdapter(Adapter);

        Adapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                classSelect(position);
            }

            @Override
            public void onDeleteClick(int position) {
                removeClass(position);
            }
        });
    }

    public void addClassData(){
        Intent intent = getIntent();
        nClass = intent.getStringExtra(Create_Class.CLASSNAME);
        gLevel = intent.getStringExtra(Create_Class.GRADELEVEL);
        if (gLevel != null){
        createExampleList();}
        if(nClass == null){
            return;
        }
        ContentValues contval = new ContentValues();
        contval.put(Tables_Classes.Tables_Class.COLUMN_CLASS, nClass);
        contval.put(Tables_Classes.Tables_Class.COLUMN_LEVEL, gLevel);

        classDataBase.insert(Tables_Classes.Tables_Class.TABLE_NAME, null, contval);
        Adapter.swapCursor(getAllItems());
        nClass = null;
        gLevel = null;
    }

    private Cursor getAllItems(){
        return classDataBase.query(
                Tables_Classes.Tables_Class.TABLE_NAME,
                null, null, null, null, null,
                Tables_Classes.Tables_Class.COLUMN_TIMESTAMP + " DESC"
        );
    }
    public void createExampleList(){
        saveClasses();
    }

    private void saveClasses(){

    }

    private void CreateClass(){
        Intent intent = new Intent(this, Create_Class.class);
        startActivity(intent);

    }

    public void classSelect(int position){
        Intent intent = new Intent(MainActivity.this, Class_Selected.class);
        startActivity(intent);
    }

    public void removeClass(int position){

        Adapter.notifyItemRemoved(position);
        saveClasses();
    }



}