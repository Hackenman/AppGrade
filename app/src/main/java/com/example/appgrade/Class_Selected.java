package com.example.appgrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Class_Selected extends AppCompatActivity {

    private RecyclerView sRecyclerView;
    private RecyclerView.Adapter sAdapter;
    private RecyclerView.LayoutManager sLayoutManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class__selected);

        buildRecycleView();

//          Get Data
        loadData();

        loadButtons();

    }

    private void SelectedStudent(){
        Intent intent = new Intent(this, Student_Selected.class);
        startActivity(intent);
    }

    private void loadButtons(){
        Button selectedStudent = findViewById(R.id.selstudent);

        selectedStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedStudent();
            }
        });
    }

    private void loadData(){
        Intent intent = getIntent();
        ExampleItem exampleItem = intent.getParcelableExtra("Example Item");
        String namedClass = exampleItem.getClassName();
        String Level = exampleItem.getGradeLvl();
        String Students = exampleItem.getNstudents();

        TextView classnamed = findViewById(R.id.namedclass);
        classnamed.setText(namedClass);

        TextView LevelG = findViewById(R.id.level);
        LevelG.setText(Level);

        TextView numStudents = findViewById(R.id.numStudent);
        numStudents.setText(Students);
    }

    private void buildRecycleView(){
        ArrayList<StudentItem> studentItems = new ArrayList<>();
        studentItems.add(new StudentItem("name","sex"));

        sRecyclerView = findViewById(R.id.studentView);
        sLayoutManger = new LinearLayoutManager(this);
        sAdapter = new StudentAdapter(studentItems);

        sRecyclerView.setLayoutManager(sLayoutManger);
        sRecyclerView.setAdapter(sAdapter);
    }


}