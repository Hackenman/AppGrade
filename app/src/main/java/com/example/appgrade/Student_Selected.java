package com.example.appgrade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Student_Selected extends AppCompatActivity {
    public static final String SHARED_PREFERENCES = "shared_preferences";
    public static final String SHARED_CALCULATION = "shared_calculation";
    public static final String SAVED_NAME = "shared_name";
    public static final String SAVED_SEX = "shared_sex";
    public static final String SAVED_WRITTEN_WORK = "shared_written_work";
    public static final String SAVED_PERFORMANCE_TASK = "shared_performance_task";
    public static final String SAVED_QUARTERLY_ASSESSMENT = "shared_quarterly_assessment";
    public static final String SAVED_QUARTERLY_GRADE = "shared_quarterly_grade";
    public static final String SAVED_INITIAL_GRADE = "shared_initial_grade";
    private String local_Name;
    private String local_Sex;
    private String local_WW;
    private String local_PT;
    private String local_QA;
    private String local_IG;
    private String local_QG;
    private RecyclerView gRecyclerView;
    private GradeAdapter gAdapter;
    private RecyclerView.LayoutManager gLayoutManager;
    private static final int CREATE_FILE = 101;
    private ArrayList<GradeItem> gradelist;
    private static int[][] score = {{0, 0, 0, 0, 0, 0, 0}, {0, 0 ,0, 0, 0, 0}, {0}};
    private static String[] strScore = {Integer.toString(score[0][0]),
            Integer.toString(score[0][1]), Integer.toString(score[0][2]), Integer.toString(score[0][3]), Integer.toString(score[0][4]), Integer.toString(score[0][5]),
            Integer.toString(score[0][6]), Integer.toString(score[1][0]), Integer.toString(score[1][1]), Integer.toString(score[1][2]), Integer.toString(score[1][3]),
            Integer.toString(score[1][4]), Integer.toString(score[1][5]), Integer.toString(score[2][0])};
    private static String[][] activity = {{"Written Work 1", "Written Work 2", "Written Work 3", "Written Work 4", "Written Work 5", "Written Work 6", "Written Work 7"},
            {"Performance Task 1", "Performance Task 2", "Performance Task 3", "Performance Task 4", "Performance Task 5", "Performance Task 6"}, {"Quarterly Assessment"}};
    public  String FILE_NAME;
    private String actId;
    private String scoreId;
    private String prefname;
    private String prefSex;
    TextView student_Name;
    TextView student_Sex;
    TextView Total;
    TextView ptTotal;
    TextView qaTotal;
    TextView qgTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__selected);
        student_Name = findViewById(R.id.studname);
        student_Sex = findViewById(R.id.studsex);
        Total = findViewById(R.id.written_work);
        ptTotal = findViewById(R.id.performance_tasks);
        qaTotal = findViewById(R.id.Qa);
        qgTotal = findViewById(R.id.QG);
        Button button = findViewById(R.id.calculate);
        Button backMenu = findViewById(R.id.buttontoMenu);

        loadTitle();
        loadData();
        changeScore();
        buildRecycleView();
        getScore();
        showTitle();

        gAdapter.setOnItemClickListener(new GradeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                addScore(position);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTotal();
            }
        });

        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Student_Selected.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void buildRecycleView(){
        gRecyclerView = findViewById(R.id.studentGrades);
        gLayoutManager = new LinearLayoutManager(this);
        gAdapter = new GradeAdapter(gradelist);

        gRecyclerView.setLayoutManager(gLayoutManager);
        gRecyclerView.setAdapter(gAdapter);

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

    }

    public void loadTitle(){
        Intent intent = getIntent();
        StudentItem studentItem = intent.getParcelableExtra("Student Item");
        if(studentItem != null){
            prefname = studentItem.getsName();
            prefSex = studentItem.getsSex();
            FILE_NAME = prefname + ".txt";
            student_Name.setText(prefname);
            student_Sex.setText(prefSex);
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SAVED_NAME, prefname);
            editor.putString(SAVED_SEX, prefSex);
            editor.apply();
        }
    }

    public void addTotal(){
        DecimalFormat df = new DecimalFormat("0.00");
        int calculate_grade =0;
        for(int ctr = 0; ctr <7; ctr++){
            calculate_grade= score[0][ctr] + calculate_grade;
        }

        float WW = (float)calculate_grade/160 * 100;
        df.format(WW);
        float ww = (float) (WW * 0.3);
        df.format(ww);

        int ptGrade = 0;
        for(int ctr = 0; ctr <6; ctr++){
            ptGrade = score[1][ctr] + ptGrade;
        }
        float PT = (float) ptGrade/120 * 100;
        float pt= (float) (PT * 0.5);
        df.format(pt);

        float quarterlyAsses = score[2][0];
        int quart = (int) quarterlyAsses;

        quarterlyAsses = quarterlyAsses/50 * 100;

        float quarterlyasses = (float) (quarterlyAsses * 0.2);
        df.format(quarterlyasses);
        float initialGrade = ww + pt + quarterlyasses;
        df.format(initialGrade);
        int transmuted = transmutedGrade(initialGrade);

        TextView written = findViewById(R.id.over);
        TextView perform = findViewById(R.id.Pertask);
        TextView quarty = findViewById(R.id.QAss);
        TextView initial = findViewById(R.id.initial);
        written.setText(calculate_grade + "/160");
        perform.setText(ptGrade + "/120");
        quarty.setText(quart+"/50");
        initial.setText(String.valueOf(initialGrade));

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_CALCULATION, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVED_WRITTEN_WORK, String.valueOf(ww));
        editor.putString(SAVED_PERFORMANCE_TASK, String.valueOf(pt));
        editor.putString(SAVED_QUARTERLY_ASSESSMENT, String.valueOf(quarterlyasses));
        editor.putString(SAVED_INITIAL_GRADE, String.valueOf(initialGrade));
        editor.putString(SAVED_QUARTERLY_GRADE, String.valueOf(transmuted));
        editor.apply();

        SharedPreferences shared = getSharedPreferences(SHARED_CALCULATION, MODE_PRIVATE);
        local_WW = shared.getString(SAVED_WRITTEN_WORK, "Error");
        local_PT = shared.getString(SAVED_PERFORMANCE_TASK, "Error");
        local_QA = shared.getString(SAVED_QUARTERLY_ASSESSMENT, "Error");
        local_IG = shared.getString(SAVED_INITIAL_GRADE, "Error");
        local_QG = shared.getString(SAVED_QUARTERLY_GRADE, "Error");

        Total.setText(local_WW);
        ptTotal.setText(local_PT);
        qaTotal.setText(local_QA);
        qgTotal.setText(local_QG);

    }

    public void showTitle(){
        student_Name.setText(local_Name);
        student_Sex.setText(local_Sex);
    }

    public void getScore(){
        GradeItem gradeitem;
        int position = 0;
        for(int cntr = 0; cntr < 3; cntr++ ){
            if(cntr == 0){
                for(int cntr1 = 0; cntr1 < 7; cntr1++){
                    gradeitem = gradelist.get(position);
                    position++;
                    String txt = gradeitem.getGrade_score();
                    score[cntr][cntr1] = Integer.valueOf(txt);
                }
            }else if(cntr == 1){
                for(int cntr2 = 0; cntr2 < 6; cntr2++){
                    gradeitem = gradelist.get(position);
                    position++;
                    String txt = gradeitem.getGrade_score();
                    score[cntr][cntr2] = Integer.valueOf(txt);
                }
            }else if(cntr == 2){
                int cntr3 = 0;
                gradeitem = gradelist.get(position);
                position++;
                String txt = gradeitem.getGrade_score();
                score[cntr][cntr3] = Integer.valueOf(txt);
            }else break;

        }


    }

    public int transmutedGrade(float initialG){
        int transmuted=0;
        float initial=initialG;
        if(initial == 100){
            transmuted = 100;
        }else if(initial <= 99.99 && initial >=98.40 ){
            transmuted = 99;
        }else if(initial <= 98.39 && initial >=96.80 ){
            transmuted = 98;
        }else if(initial <= 96.79 && initial >=95.20 ){
            transmuted = 97;
        }else if(initial <= 95.19 && initial >=93.60 ){
            transmuted = 96;
        }else if(initial <= 93.59 && initial >=92.00 ){
            transmuted = 95;
        }else if(initial <= 91.99 && initial >=90.40 ){
            transmuted = 94;
        }else if(initial <= 90.39 && initial >=88.80 ){
            transmuted = 93;
        }else if(initial <= 88.79 && initial >=87.20 ){
            transmuted = 92;
        }else if(initial <= 87.19 && initial >=85.60 ){
            transmuted = 91;
        }else if(initial <= 85.59 && initial >=84.00 ){
            transmuted = 90;
        }else if(initial <= 83.99 && initial >=82.40 ){
            transmuted = 89;
        }else if(initial <= 82.39 && initial >=80.80 ){
            transmuted = 88;
        }else if(initial <= 80.79 && initial >=79.20 ){
            transmuted = 87;
        }else if(initial <= 79.19 && initial >=77.60 ){
            transmuted = 86;
        }else if(initial <= 77.59 && initial >=76.00 ){
            transmuted = 85;
        }else if(initial <= 75.99 && initial >=74.40 ){
            transmuted = 84;
        }else if(initial <= 74.39 && initial >=72.80 ){
            transmuted = 83;
        }else if(initial <= 72.79 && initial >=71.20 ){
            transmuted = 82;
        }else if(initial <= 71.19 && initial >=69.60 ){
            transmuted = 81;
        }else if(initial <= 69.59 && initial >=68.00 ){
            transmuted = 80;
        }else if(initial <= 67.99 && initial >=66.40 ){
            transmuted = 79;
        }else if(initial <= 66.39 && initial >=64.80 ){
            transmuted = 78;
        }else if(initial <= 64.79 && initial >=63.20 ){
            transmuted = 77;
        }else if(initial <= 63.19 && initial >=61.60 ){
            transmuted = 76;
        }else if(initial <= 61.59 && initial >=60.00 ){
            transmuted = 75;
        }else if(initial <= 59.99 && initial >=56.00 ){
            transmuted = 74;
        }else if(initial <= 55.99 && initial >=52.00 ){
            transmuted = 73;
        }else if(initial <= 51.99 && initial >=48.00 ){
            transmuted = 72;
        }else if(initial <= 47.99 && initial >=44.00 ){
            transmuted = 71;
        }else if(initial <= 43.99 && initial >=40.00 ){
            transmuted = 70;
        }else if(initial <= 39.99 && initial >=36.00 ){
            transmuted = 69;
        }else if(initial <= 36.99 && initial >=32.00 ){
            transmuted = 68;
        }else if(initial <= 31.99 && initial >=28.00 ){
            transmuted = 67;
        }else if(initial <= 27.99 && initial >=24.00 ){
            transmuted = 66;
        }else if(initial <= 23.99 && initial >=20.00 ){
            transmuted = 65;
        }else if(initial <= 19.99 && initial >=16.00 ){
            transmuted = 64;
        }else if(initial <= 15.99 && initial >=12.00 ){
            transmuted = 63;
        }else if(initial <= 11.99 && initial >=8.00 ){
            transmuted = 62;
        }else if(initial <= 7.99 && initial >=4.00 ){
            transmuted = 61;
        }else if(initial <= 3.99 && initial >=0.00 ){
            transmuted = 60;
        }else {
            return Integer.valueOf(Math.round(initial));
        }

        return transmuted;
    }

    public void saveFile(View v){
        String textname = local_Name;
        String textww = local_WW;
        String textpt = local_PT;
        String textqa = local_QA;
        String textiq = local_IG;
        String textqg = local_QG;
        String saveGrade = textname + textww + textpt + textqa + textiq + textqg;
        createFile();
       /* FileOutputStream fos ;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(saveGrade.getBytes());
            //Toast.makeText(this, "Saved to "+ getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
            fos.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Failed to Save", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    private void createFile() {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, local_Name + ".txt");
        startActivityForResult(intent, CREATE_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String textname = local_Name;
        String textww = local_WW;
        String textpt = local_PT;
        String textqa = local_QA;
        String textqg = local_QG;
        String saveGrade = textname + "\n"
                + "Written Work: " + textww + "\n"
                + "Performance Task: " + textpt + "\n"
                + "Quarterly Assessment: " + textqa + "\n"
                + "Quarterly Grade: " + textqg;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_FILE) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    if (data != null
                            && data.getData() != null) {
                        writeInFile(data.getData(), saveGrade);
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    break;
            }
        }
    }

    private void writeInFile(@NonNull Uri uri, @NonNull String text) {

        OutputStream outputStream;
        try {
            outputStream = getContentResolver().openOutputStream(uri);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            bw.write(text);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}