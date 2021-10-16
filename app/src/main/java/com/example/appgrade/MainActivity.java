package com.example.appgrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createClass = findViewById(R.id.button);
        Button selectClass = findViewById(R.id.button2);

        createClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateClass();
            }
        });

        selectClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedClass();
            }
        });
    }

    private void CreateClass(){
        Intent intent = new Intent(this, Create_Class.class);
        startActivity(intent);
    }

    private void SelectedClass(){
        Intent intent = new Intent(this, Class_Selected.class);
        startActivity(intent);
    }
}