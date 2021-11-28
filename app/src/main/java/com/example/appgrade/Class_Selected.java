package com.example.appgrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class Class_Selected extends AppCompatActivity {
    private ArrayList<StudentItem> studentItems;
    private String sName;
    private String sSex;
    private String className;
    private String gradeLevel;
    private String nOfStudents;

    private RecyclerView sRecyclerView;
    private StudentAdapter sAdapter;
    private RecyclerView.LayoutManager sLayoutManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class__selected);

        buildRecycleView();

//          Get Data
        loadData();

        loadButtons();

        addStudentData();

    }

    private void CreateStudent(){
        Intent intent = new Intent(this, Create_Student.class);
        startActivity(intent);
    }
    private void studentSelected(){

    }

    private void loadButtons(){
        Button selectedStudent = findViewById(R.id.selstudent);

        selectedStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateStudent();
            }
        });
    }

    private void loadData(){
        if (className == null) {
            Intent intent = getIntent();
            ExampleItem exampleItem = intent.getParcelableExtra("Example Item");
            className = exampleItem.getClassName();
            gradeLevel = exampleItem.getGradeLvl();
            nOfStudents = exampleItem.getNstudents();
        }
        TextView classnamed = findViewById(R.id.namedclass);
        classnamed.setText(className);

        TextView LevelG = findViewById(R.id.level);
        LevelG.setText(gradeLevel);

    }

    private void buildRecycleView(){
        studentItems = new ArrayList<>();
        studentItems.add(new StudentItem("Eunice Silot","Female"));

        sRecyclerView = findViewById(R.id.studentView);
        sLayoutManger = new LinearLayoutManager(this);
        sAdapter = new StudentAdapter(studentItems);

        sRecyclerView.setLayoutManager(sLayoutManger);
        sRecyclerView.setAdapter(sAdapter);

        sAdapter.setOnItemClick(new StudentAdapter.OnItemClick() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(Class_Selected.this, Student_Selected.class);
                intent.putExtra("Student Item", studentItems.get(position));
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int position) {
            }
        });
    }

    private void addStudentData(){
        Intent intent = getIntent();
        sName = intent.getStringExtra(Create_Student.STUDENTNAME);
        sSex = intent.getStringExtra(Create_Student.SEX);
    }

}