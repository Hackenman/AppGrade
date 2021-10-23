package com.example.appgrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Class_Selected extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class__selected);

        Intent intent = getIntent();
        ExampleItem exampleItem = intent.getParcelableExtra("Example Item");

        String namedClass = exampleItem.getClassName();
        String Level = exampleItem.getGradeLvl();

        TextView classnamed = findViewById(R.id.namedclass);
        classnamed.setText(namedClass);
        TextView LevelG = findViewById(R.id.level);
        LevelG.setText(Level);

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