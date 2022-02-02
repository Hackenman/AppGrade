package com.example.appgrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Student_Selected extends AppCompatActivity {
    private RecyclerView gRecyclerView;
    private RecyclerView.Adapter gAdapter;
    private RecyclerView.LayoutManager gLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__selected);

        ArrayList<GradeItem> gradelist = new ArrayList<>();
        gradelist.add(new GradeItem("activity 1", "100"));

        gRecyclerView = findViewById(R.id.studentGrades);
        gLayoutManager = new LinearLayoutManager(this);
        gAdapter = new GradeAdapter(gradelist);

        gRecyclerView.setLayoutManager(gLayoutManager);
        gRecyclerView.setAdapter(gAdapter);

        loadData();
    }

    public void loadData(){
        Intent intent = getIntent();
        StudentItem studentItem = intent.getParcelableExtra("Student Item");
        String namedStudent = studentItem.getsName();
        String setSex = studentItem.getsSex();

        TextView studentnamed = findViewById(R.id.studname);
        studentnamed.setText(namedStudent);

        TextView sexset = findViewById(R.id.studsex);
        sexset.setText(setSex);
    }
}