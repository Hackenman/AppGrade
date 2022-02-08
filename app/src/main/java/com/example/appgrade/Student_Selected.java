package com.example.appgrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Student_Selected extends AppCompatActivity {
    public static final String SHARED_PREFERENCES = "shared_preferences";
    public static final String SAVED_NAME = "shared_name";
    public static final String SAVED_SEX = "shared_sex";
    public static final String SAVED_TOTAL = "shared_total";
    private String local_Name;
    private String local_Sex;
    private String local_Total;
    private static int calculate_grade=0;
    private RecyclerView gRecyclerView;
    private GradeAdapter gAdapter;
    private RecyclerView.LayoutManager gLayoutManager;

    private ArrayList<GradeItem> gradelist;
    private static int[][] score = {{0, 0, 0, 0, 0, 0, 0}, {0, 0 ,0, 0, 0, 0}, {0}};
    private static String[] strScore = {Integer.toString(score[0][0]),
            Integer.toString(score[0][1]), Integer.toString(score[0][2]), Integer.toString(score[0][3]), Integer.toString(score[0][4]), Integer.toString(score[0][5]),
            Integer.toString(score[0][6]), Integer.toString(score[1][0]), Integer.toString(score[1][1]), Integer.toString(score[1][2]), Integer.toString(score[1][3]),
            Integer.toString(score[1][4]), Integer.toString(score[1][5]), Integer.toString(score[2][0])};
    private static String[][] activity = {{"Written Work 1", "Written Work 2", "Written Work 3", "Written Work 4", "Written Work 5", "Written Work 6", "Written Work 7"},
            {"Performance Task 1", "Performance Task 2", "Performance Task 3", "Performance Task 4", "Performance Task 5", "Performance Task 6"}, {"Quarterly Assessment"}};

    private String actId;
    private String scoreId;
    private String prefname;
    private String prefSex;
    TextView student_Name;
    TextView student_Sex;
    TextView Total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__selected);
        student_Name = findViewById(R.id.studname);
        student_Sex = findViewById(R.id.studsex);
        Total = findViewById(R.id.Total);

        loadData();
        changeScore();
        buildRecycleView();
        loadTitle();
        addTotal();
        showTitle();

        gAdapter.setOnItemClickListener(new GradeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                addScore(position);
            }
        });
    }

    public void create_List(){
        gradelist = new ArrayList<>();
        gradelist.add(new GradeItem(activity[0][0], strScore[0]));
        gradelist.add(new GradeItem(activity[0][1], strScore[1]));
        gradelist.add(new GradeItem(activity[0][2], strScore[2]));
        gradelist.add(new GradeItem(activity[0][3], strScore[3]));
        gradelist.add(new GradeItem(activity[0][4], strScore[4]));
        gradelist.add(new GradeItem(activity[0][5], strScore[5]));
        gradelist.add(new GradeItem(activity[0][6], strScore[6]));
        gradelist.add(new GradeItem(activity[1][0], strScore[7]));
        gradelist.add(new GradeItem(activity[1][1], strScore[8]));
        gradelist.add(new GradeItem(activity[1][2], strScore[9]));
        gradelist.add(new GradeItem(activity[1][3], strScore[10]));
        gradelist.add(new GradeItem(activity[1][4], strScore[11]));
        gradelist.add(new GradeItem(activity[1][5], strScore[12]));
        gradelist.add(new GradeItem(activity[2][0], strScore[13]));

    }

    private void buildRecycleView(){
        gRecyclerView = findViewById(R.id.studentGrades);
        gLayoutManager = new LinearLayoutManager(this);
        gAdapter = new GradeAdapter(gradelist);

        gRecyclerView.setLayoutManager(gLayoutManager);
        gRecyclerView.setAdapter(gAdapter);

    }
    public void changeScore(){
        Intent intent2 = getIntent();
        actId = intent2.getStringExtra(StudentAddScore.ACTIVITY);
        //for Written Work
        if(activity[0][0].equals(actId)){
            int position = 0;
            scoreId = intent2.getStringExtra(StudentAddScore.SCORE);
            gradelist.get(position).changeScore(activity[0][0], scoreId);
            score[0][0]= Integer.parseInt(scoreId);
            saveScore();
        }else if(activity[0][1].equals(actId)){
            int position = 1;
            scoreId = intent2.getStringExtra(StudentAddScore.SCORE);
            gradelist.get(position).changeScore(activity[0][1], scoreId);
            score[0][1]= Integer.parseInt(scoreId);
            saveScore();
        }else if(activity[0][2].equals(actId)){
            int position = 2;
            scoreId = intent2.getStringExtra(StudentAddScore.SCORE);
            gradelist.get(position).changeScore(activity[0][2], scoreId);
            score[0][2]= Integer.parseInt(scoreId);
            saveScore();
        }else if(activity[0][3].equals(actId)){
            int position = 3;
            scoreId = intent2.getStringExtra(StudentAddScore.SCORE);
            gradelist.get(position).changeScore(activity[0][3], scoreId);
            score[0][3]= Integer.parseInt(scoreId);
            saveScore();
        }else if(activity[0][4].equals(actId)) {
            int position = 4;
            scoreId = intent2.getStringExtra(StudentAddScore.SCORE);
            gradelist.get(position).changeScore(activity[0][4], scoreId);
            score[0][4]= Integer.parseInt(scoreId);
            saveScore();
        }else if(activity[0][5].equals(actId)) {
            int position = 5;
            scoreId = intent2.getStringExtra(StudentAddScore.SCORE);
            gradelist.get(position).changeScore(activity[0][5], scoreId);
            score[0][5]= Integer.parseInt(scoreId);
            saveScore();
        }else if(activity[0][6].equals(actId)) {
            int position = 6;
            scoreId = intent2.getStringExtra(StudentAddScore.SCORE);
            gradelist.get(position).changeScore(activity[0][6], scoreId);
            score[0][6]= Integer.parseInt(scoreId);
            saveScore();
            //for Performance task
        }else if(activity[1][0].equals(actId)) {
            int position = 7;
            scoreId = intent2.getStringExtra(StudentAddScore.SCORE);
            gradelist.get(position).changeScore(activity[1][0], scoreId);
            score[1][0]= Integer.parseInt(scoreId);
            saveScore();
        }else if(activity[1][1].equals(actId)) {
            int position = 8;
            scoreId = intent2.getStringExtra(StudentAddScore.SCORE);
            gradelist.get(position).changeScore(activity[1][1], scoreId);
            score[1][1]= Integer.parseInt(scoreId);
            saveScore();
        }else if(activity[1][2].equals(actId)) {
            int position = 9;
            scoreId = intent2.getStringExtra(StudentAddScore.SCORE);
            gradelist.get(position).changeScore(activity[1][2], scoreId);
            score[1][2]= Integer.parseInt(scoreId);
            saveScore();
        }else if(activity[1][3].equals(actId)) {
            int position = 10;
            scoreId = intent2.getStringExtra(StudentAddScore.SCORE);
            gradelist.get(position).changeScore(activity[1][3], scoreId);
            score[1][3]= Integer.parseInt(scoreId);
            saveScore();
        }else if(activity[1][4].equals(actId)) {
            int position = 11;
            scoreId = intent2.getStringExtra(StudentAddScore.SCORE);
            gradelist.get(position).changeScore(activity[1][4], scoreId);
            score[1][4]= Integer.parseInt(scoreId);
            saveScore();
        }else if(activity[1][5].equals(actId)) {
            int position = 12;
            scoreId = intent2.getStringExtra(StudentAddScore.SCORE);
            gradelist.get(position).changeScore(activity[1][5], scoreId);
            score[1][5]= Integer.parseInt(scoreId);
            saveScore();
            //for Quarterly Assessment
        }else if(activity[2][0].equals(actId)) {
            int position = 13;
            scoreId = intent2.getStringExtra(StudentAddScore.SCORE);
            gradelist.get(position).changeScore(activity[2][0], scoreId);
            score[2][0]= Integer.parseInt(scoreId);
            saveScore();
        }
    }

    public void addScore(int position){
        Intent intent = new Intent(this, StudentAddScore.class);
        intent.putExtra("Activity", gradelist.get(position));
        startActivity(intent);
    }

    public void saveScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(gradelist);
        editor.putString("grade list", json);
        editor.apply();
    }
    public void loadData(){
        SharedPreferences sharedPreferences =getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("grade list", null);
        Type type = new TypeToken<ArrayList<GradeItem>>(){}.getType();
        gradelist = gson.fromJson(json, type);
        if(gradelist == null){
            create_List();}
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        local_Name = preferences.getString(SAVED_NAME, "");
        local_Sex = preferences.getString(SAVED_SEX, "");
        local_Total = preferences.getString(SAVED_TOTAL, "");

    }
    public void loadTitle(){
        Intent intent = getIntent();
        StudentItem studentItem = intent.getParcelableExtra("Student Item");
        if(studentItem != null){
            prefname = studentItem.getsName();
            prefSex = studentItem.getsSex();

            student_Name.setText(prefname);
            student_Sex.setText(prefSex);
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SAVED_NAME, prefname);
            editor.putString(SAVED_SEX, prefSex);

        }
    }
    public void addTotal(){
        for(int ctr = 0; ctr <7; ctr++){
            calculate_grade= score[0][ctr] + calculate_grade;
        }
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVED_TOTAL, String.valueOf(calculate_grade));
        editor.apply();
    }
    public void showTitle(){
        student_Name.setText(local_Name);
        student_Sex.setText(local_Sex);
        Total.setText(local_Total);
    }
}