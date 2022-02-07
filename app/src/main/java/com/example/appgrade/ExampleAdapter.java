package com.example.appgrade;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private Context cont;
    private Cursor curs;

    public ExampleAdapter(Context context, Cursor cursor){
        cont = context;
        curs = cursor;
    }
    private OnItemClickListener Listener;

    public interface OnItemClickListener{
        void onItemClick(int position);

        void onDeleteClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) { Listener = listener;}

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView ClassName;
        public TextView GradeLevel;
        public ImageView DeleteIcon;

        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            ClassName = itemView.findViewById(R.id.className);
            GradeLevel = itemView.findViewById(R.id.gradeLvl);
            DeleteIcon = itemView.findViewById(R.id.image_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            DeleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(cont);
        View v = inflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, Listener);
        return evh;
    }

    @Override
    public void onBindViewHolder( ExampleViewHolder holder, int position) {
        if(!curs.moveToPosition(position)){
            return;
        }
        String classes = curs.getString(curs.getColumnIndex(Tables_Classes.Tables_Class.COLUMN_CLASS));
        String grade = curs.getString(curs.getColumnIndex(Tables_Classes.Tables_Class.COLUMN_LEVEL));

        holder.ClassName.setText(classes);
        holder.GradeLevel.setText(grade);
    }

    @Override
    public int getItemCount() {
        return curs.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(curs != null) {
            curs.close();
        }
        curs = newCursor;
        if(newCursor !=null){
            notifyDataSetChanged();
        }
    }
}
