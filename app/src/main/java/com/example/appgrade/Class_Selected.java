package com.example.appgrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class Class_Selected extends AppCompatActivity {
    private ArrayList<StudentItem> studentItems;
    private String sName;
    private String sSex;
    private String className;
    private String gradeLevel;
    TextView classnamed;
    TextView LevelG;

   // public static final String SHAREDPREF = "sharedpref";
   // public static final String SAVED_CLASS = "shared_class";
   // public static final String SAVED_GRADE_LEVEL = "shared_grade_level";

    private RecyclerView sRecyclerView;
    private StudentAdapter sAdapter;
    private RecyclerView.LayoutManager sLayoutManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class__selected);

        loadButtons();
        loadData();
        buildRecycleView();
        addStudentData();

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

    private void CreateStudent(){
        Intent intent = new Intent(this, Create_Student.class);
        startActivity(intent);
    }


    private void buildRecycleView(){
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
                studentItems.remove(position);
                sAdapter.notifyItemRemoved(position);
                saveData();
            }
        });
    }
    private void addStudentData(){
        Intent intent = getIntent();
        sName = intent.getStringExtra(Create_Student.STUDENTNAME);
        sSex = intent.getStringExtra(Create_Student.SEX);
        if(sSex != null){
        studentItems.add(new StudentItem(sName,sSex));
        saveData();}
    }
    public void saveData(){
        if (sSex == null) {
            Intent intent = getIntent();
            ExampleItem exampleItem = intent.getParcelableExtra("Example Item");
            className = exampleItem.getClassName();
            gradeLevel = exampleItem.getGradeLvl();
        }
        classnamed = findViewById(R.id.namedclass);
        classnamed.setText(className);
        LevelG = findViewById(R.id.level);
        LevelG.setText(gradeLevel);
        SharedPreferences sharedPref = getSharedPreferences("sharedpref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(studentItems);
        editor.putString("task list", json);
        editor.apply();
    }
    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedpref", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<StudentItem>>(){}.getType();
        studentItems = gson.fromJson(json, type);

        if(studentItems == null) {
            studentItems = new ArrayList<>();
        }
    }
}