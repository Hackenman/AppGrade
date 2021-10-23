package com.example.appgrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ExampleItem> exampleList;

    private androidx.recyclerview.widget.RecyclerView RecyclerView;
    private ExampleAdapter Adapter;
    private RecyclerView.LayoutManager LayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createClass = findViewById(R.id.add);
        /*Button selectClass = findViewById(R.id.selclass);*/

//        Button to Add classes
        createClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateClass();
            }
        });

        /*selectClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedClass();
            }
        });*/

//        Array for the data
        createExampleList();
//        build the Recycler view
        buildRecycleView();

    }

    private void CreateClass(){
        Intent intent = new Intent(this, Create_Class.class);
        startActivity(intent);
    }

    /*private void SelectedClass(){
        Intent intent = new Intent(this, Class_Selected.class);
        startActivity(intent);
    }*/

    public void changeItem(int position, String text){
        exampleList.get(position).changeText1(text);
        Adapter.notifyItemChanged(position);
    }

    public void createExampleList(){
        exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem("Maths", "Grade 5"));
        exampleList.add(new ExampleItem("English", "Grade 11"));
        exampleList.add(new ExampleItem("Computer", "Grade 12"));
    }
    public void buildRecycleView(){
        RecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.setHasFixedSize(true);
        LayoutManager = new LinearLayoutManager(this);
        Adapter = new ExampleAdapter(exampleList);

        RecyclerView.setLayoutManager(LayoutManager);
        RecyclerView.setAdapter(Adapter);

        Adapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeItem(position, "Clicked");
            }
        });

    }
}