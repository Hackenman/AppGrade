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
    private OnItemClickListener gListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        gListener = listener;
    }
    public static class GradeViewHolder extends RecyclerView.ViewHolder {

        public TextView gTextview;
        public TextView gTextview1;

        public GradeViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            gTextview = itemView.findViewById(R.id.activity_name);
            gTextview1 = itemView.findViewById(R.id.grade_score);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public GradeAdapter(ArrayList<GradeItem> gradelist){
        gItems = gradelist;
    }

    @NonNull
    @Override
    public GradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gradeitems, parent, false);
        GradeViewHolder gvh = new GradeViewHolder(v, gListener);
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
