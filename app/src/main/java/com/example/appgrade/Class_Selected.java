package com.example.appgrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Class_Selected extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class__selected);

        Button selectedStudent = findViewById(R.id.selstudent);

        selectedStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedStudent();
            }
        });
    }

    private void SelectedStudent(){
        Intent intent = new Intent(this, Student_Selected.class);
        startActivity(intent);
    }
}