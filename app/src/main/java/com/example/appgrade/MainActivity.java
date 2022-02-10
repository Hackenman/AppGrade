package com.example.appgrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String CLASS_NAME = "com.example.appgrade.CLASS_NAME";
    public static final String GRADE_LEVEL = "com.example.appgrade.GRADE_LEVEL";

    private ArrayList<ExampleItem> exampleList;

    private androidx.recyclerview.widget.RecyclerView RecyclerView;
    private ExampleAdapter Adapter;
    private RecyclerView.LayoutManager LayoutManager;
    private String nClass;
    private String gLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Buttons to Add classes
        setButtons();
//        Array for the data
        loadClasses();
//        build the Recycler view
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
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("class list", null);
        Type type = new TypeToken<ArrayList<ExampleItem>>() {}.getType();
        exampleList = gson.fromJson(json, type);

        if (exampleList == null) {
            exampleList = new ArrayList<>();
        }
    }

    public void buildRecycleView(){
        RecyclerView = findViewById(R.id.recyclerView);

        LayoutManager = new LinearLayoutManager(this);
        Adapter = new ExampleAdapter(exampleList);
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

    }

    public void createExampleList(){
        exampleList.add( new ExampleItem(nClass, gLevel));
        saveClasses();
    }

    private void saveClasses(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exampleList);
        editor.putString("class list", json);
        editor.apply();
    }

    private void CreateClass(){
        Intent intent = new Intent(this, Create_Class.class);
        startActivity(intent);

    }

    public void classSelect(int position){
        Intent intent = new Intent(MainActivity.this, Class_Selected.class);
        intent.putExtra("Example Item", exampleList.get(position));
        startActivity(intent);
    }

    public void removeClass(int position){
        exampleList.remove(position);
        Adapter.notifyItemRemoved(position);
        SharedPreferences shared = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.remove("shared preferences");
        editor.apply();
        saveClasses();
    }



}