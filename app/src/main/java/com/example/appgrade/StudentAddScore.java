package com.example.appgrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StudentAddScore extends AppCompatActivity {
    public static final String SCORE = "com.example.appgrade.SCORE";
    public static final String ACTIVITY = "com.example.appgrade.ACTIVITY";
    private TextView textAct;
    private String activity;
    private EditText score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add_score);

        textAct = findViewById(R.id.act1);
        Button save_score = findViewById(R.id.saveScore);
        save_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addScore();
            }
        });
        Intent intent = getIntent();
        GradeItem gradeitem = intent.getParcelableExtra("Activity");
        if(gradeitem != null) {
          activity = gradeitem.getActivity_name();
          textAct.setText(activity);
        }
    }
    private void addScore(){
        score = findViewById(R.id.score);
        String s = score.getText().toString();
        String act = textAct.getText().toString();
        Intent intent = new Intent(this, Student_Selected.class);
        intent.putExtra(ACTIVITY, act);
        intent.putExtra(SCORE, s);
        startActivity(intent);
    }

}