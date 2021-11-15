package com.example.appgrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Create_Class extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String CLASSNAME = "com.example.appgrade.CLASSNAME";
    public static final String GRADELEVEL = "com.example.appgrade.GRADELEVEL";
    private Button buttonCreate;
    private EditText Nameclass;
    private EditText numStudent;
    private String gradelvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__class);
        Spinner();

        buttonCreate = findViewById(R.id.create);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClass();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       gradelvl = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void Spinner(){
        Spinner spinner = findViewById(R.id.LevelingGrade);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Level, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void addClass(){
        Nameclass = findViewById(R.id.namingClass);
        String nameclass = Nameclass.getText().toString();
        numStudent = findViewById(R.id.numberOfStudents);
        int numberofS = Integer.parseInt(numStudent.getText().toString());
        String level = gradelvl;

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(CLASSNAME, nameclass);
        intent.putExtra(GRADELEVEL, level);
        startActivity(intent);

    }
}