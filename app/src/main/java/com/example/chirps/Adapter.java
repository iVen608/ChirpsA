package com.example.chirps;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context context;
    ArrayList<Reminder> reminder_list;

    public Adapter(Context c, ArrayList<Reminder> p){
        context = c;
        reminder_list = p;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_reminder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(reminder_list.get(position).getTitle());
        holder.timeT.setText(reminder_list.get(position).getTime());
        holder.dateT.setText(reminder_list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return reminder_list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, timeT, dateT;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            timeT = (TextView) itemView.findViewById(R.id.time);
            dateT = (TextView) itemView.findViewById(R.id.date);
            Log.d("X", "title");
        }
    }
}
