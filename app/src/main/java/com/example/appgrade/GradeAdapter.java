package com.example.appgrade;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GradeAdapter extends RecyclerView.Adapter<GradeAdapter.GradeViewHolder> {

    private ArrayList<GradeItem> gItems;
    public static class GradeViewHolder extends RecyclerView.ViewHolder {

        public TextView gTextview;
        public TextView gTextview1;

        public GradeViewHolder(@NonNull View itemView) {
            super(itemView);
            gTextview = itemView.findViewById(R.id.activity_name);
            gTextview1 = itemView.findViewById(R.id.grade_score);
        }
    }

    public GradeAdapter(ArrayList<GradeItem> gradelist){
        gItems = gradelist;
    }

    @NonNull
    @Override
    public GradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gradeitems, parent, false);
        GradeViewHolder gvh = new GradeViewHolder(v);
        return gvh;
    }

    @Override
    public void onBindViewHolder(@NonNull GradeViewHolder holder, int position) {
        GradeItem currentItem = gItems.get(position);
        holder.gTextview.setText(currentItem.getActivity_name());
        holder.gTextview1.setText(currentItem.getGrade_score());
    }

    @Override
    public int getItemCount() {
        return gItems.size();
    }
}
