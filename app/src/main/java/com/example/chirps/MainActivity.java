package com.example.chirps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    MainController mainController = new MainController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int size = 0;
        mainController.requestFiles();
        mainController.viewReminder(1);
    }

    public void loadExpanded(View view){
        Intent intent = new Intent(this, ExpandedActivity.class);
        String message = "aa";
        intent.putExtra("something",message);
        startActivity(intent);
    }
}