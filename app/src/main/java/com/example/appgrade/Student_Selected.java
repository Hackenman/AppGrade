package com.example.appgrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Student_Selected extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__selected);

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