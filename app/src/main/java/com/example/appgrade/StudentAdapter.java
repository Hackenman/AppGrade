package com.example.appgrade;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private ArrayList<StudentItem> studentList;

    public static class StudentViewHolder extends RecyclerView.ViewHolder{
        public TextView sName;
        public TextView sSex;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            sName = itemView.findViewById(R.id.studentName);
            sSex = itemView.findViewById(R.id.studentSex);
        }
    }

    public StudentAdapter(ArrayList<StudentItem> studentItems){
        studentList = studentItems;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentitem, parent, false);
        StudentViewHolder svh = new StudentViewHolder(view);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        StudentItem currentItem = studentList.get(position);

        holder.sName.setText(currentItem.getsName());
        holder.sSex.setText(currentItem.getsSex());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}
