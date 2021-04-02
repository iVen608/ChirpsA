package com.example.chirps;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExpandedActvity extends AppCompatActivity {
    int hours, min;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded_actvity);
        TextView title = findViewById(R.id.title_text);
        TextView desc = findViewById(R.id.desc_text);
        TextView timeT = findViewById(R.id.time_text);
        timeT.setText(getIntent().getStringExtra("time"));
        title.setText(getIntent().getStringExtra("title"));
        desc.setText(getIntent().getStringExtra("desc"));
    }

    public void goBack(View view){
        this.finish();
    }

    public void save(View view){
        int index = getIntent().getIntExtra("index", 0);
        TextView titleT = findViewById(R.id.title_text);
        TextView timeT = findViewById(R.id.time_text);
        String title = titleT.getText().toString();
        String desc = "";
        String time = timeT.getText().toString();
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

    public void selectTime(View view){
        TextView timeT = findViewById(R.id.time_text);
        TimePickerDialog tpd = new TimePickerDialog(
                ExpandedActvity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hours = hourOfDay;
                        min = minute;
                        String t = hours+":"+min;
                        SimpleDateFormat dtfo = new SimpleDateFormat(
                                "HH:mm"
                        );
                        try {
                            Date date = dtfo.parse(t);
                            SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                            timeT.setText(f12Hours.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, 12, 0, false
        );
        tpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tpd.updateTime(hours, min);
        tpd.show();
    }
}