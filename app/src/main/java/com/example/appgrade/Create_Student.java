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

public class Create_Student extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String STUDENTNAME = "com.example.appgrade.STUDENTNAME";
    public static final String SEX = "com.example.appgrade.SEX";
    private Button buttonCreate;
    private EditText studentName;
    private String studentSex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);
        Spinner();

        buttonCreate = findViewById(R.id.createStudent);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        studentSex = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void Spinner(){
        Spinner spinner = findViewById(R.id.SpinnerSex);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Sex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void addStudent(){
        studentName = findViewById(R.id.namingStudent);
        String sname = studentName.getText().toString();
        String ssex = studentSex;

        Intent intent = new Intent(this, Class_Selected.class);
        intent.putExtra(STUDENTNAME, sname);
        intent.putExtra(SEX, ssex);
        startActivity(intent);

    }

}