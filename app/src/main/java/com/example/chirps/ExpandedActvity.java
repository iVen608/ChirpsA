package com.example.chirps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class ExpandedActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded_actvity);
        TextView title = findViewById(R.id.title_text);
        TextView desc = findViewById(R.id.desc_text);
        title.setText(getIntent().getStringExtra("title"));
        desc.setText(getIntent().getStringExtra("desc"));
    }

    public void goBack(View view){
        this.finish();
    }

    public void save(View view){
        int index = getIntent().getIntExtra("index", 0);
        TextView titleT = findViewById(R.id.title_text);
        String title = titleT.getText().toString();
        String desc = getIntent().getStringExtra("desc");
        String time = getIntent().getStringExtra("time");
        String date = getIntent().getStringExtra("date");
        MultiController.saveInfo(index, title, desc, time, date);
        SharedPreferences sp = getSharedPreferences("Reminders", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        Gson g = new Gson();
        String json = g.toJson(MultiController.reminders);
        ed.putString("key", json).apply();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void delete(View view){
        int index = getIntent().getIntExtra("index", 0);
        MultiController.deleteInfo(index);
        SharedPreferences sp = getSharedPreferences("Reminders", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        Gson g = new Gson();
        String json = g.toJson(MultiController.reminders);
        ed.putString("key", json).apply();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}