package com.example.chirps;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExpandedActivity extends AppCompatActivity {
    ExpandedController ec = new ExpandedController(this, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded);
        updateText(ec.get_name(), ec.get_description(), ec.get_date(), ec.get_time());
    }

    public void updateText(String name, String description, String date, String time){
        TextView nameText = findViewById(R.id.name);
        nameText.setText(name);
        TextView descText = findViewById(R.id.description);
        descText.setText(description);
        TextView dateText = findViewById(R.id.date);
        dateText.setText(date);
        TextView timeText = findViewById(R.id.time);
        timeText.setText(time);
    }
}