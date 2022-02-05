package com.example.appgrade;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private ArrayList<StudentItem> studentList;
    private OnItemClick sListener;
    public interface OnItemClick{
        void onClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClick(OnItemClick listener){
        sListener = listener;
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder{
        public TextView sName;
        public TextView sSex;
        public ImageView idelete;

        public StudentViewHolder(@NonNull View itemView, OnItemClick listener) {
            super(itemView);
            sName = itemView.findViewById(R.id.studentName);
            sSex = itemView.findViewById(R.id.studentSex);
            idelete = itemView.findViewById(R.id.idelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onClick(position);
                        }
                    }
                }
            });
            idelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });


        }
    }

    public StudentAdapter(ArrayList<StudentItem> studentItems){
        studentList = studentItems;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentitem, parent, false);
        StudentViewHolder svh = new StudentViewHolder(view, sListener);
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
