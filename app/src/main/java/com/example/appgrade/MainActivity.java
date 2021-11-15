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
    private String nClass;
    private String gLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createClass = findViewById(R.id.add);

//        Button to Add classes
        createClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateClass();
            }
        });

//        Array for the data
        exampleList = new ArrayList<>();
//        build the Recycler view
        buildRecycleView();


        Intent intent = getIntent();
        nClass = intent.getStringExtra(Create_Class.CLASSNAME);
        gLevel = intent.getStringExtra(Create_Class.GRADELEVEL);
        createExampleList();

    }

    private void CreateClass(){
        Intent intent = new Intent(this, Create_Class.class);
        startActivity(intent);
    }

    public void changeItem(int position, String text){
        exampleList.get(position).changeText1(text);
        Adapter.notifyItemChanged(position);
    }

    public void createExampleList(){
        exampleList.add( new ExampleItem(nClass, gLevel));

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
                Intent intent = new Intent(MainActivity.this, Class_Selected.class);
                intent.putExtra("Example Item", exampleList.get(position));
                startActivity(intent);
            }
        });

    }

}