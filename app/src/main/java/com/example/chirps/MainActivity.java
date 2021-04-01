package com.example.chirps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MainController mainController = new MainController();
    RecyclerView list;
    static ArrayList<Reminder> reminders;
    Adapter adapter;
    Gson g;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int size = 0;
        SharedPreferences sp = getSharedPreferences("Reminders", Context.MODE_PRIVATE);

        mainController.requestFiles();
        mainController.viewReminder(1);
        list = findViewById(R.id.recycler);
        reminders = new ArrayList<Reminder>();
        g = new Gson();
        list.setLayoutManager(new LinearLayoutManager(this));

        Reminder r = new Reminder("Jog", "08/01/2021", "c,", "00:00am");
        Reminder s = new Reminder("Eat", "08/05/2021", "c,", "06:00am");
        Reminder t = new Reminder("Run", "09/01/2021", "c,", "07:00am");
        Reminder e = new Reminder("Jog", "08/01/2021", "c,", "00:00am");
        Reminder k = new Reminder("Eat", "08/05/2021", "c,", "06:00am");
        Reminder c = new Reminder("Run", "09/01/2021", "c,", "07:00am");
        Reminder q = new Reminder("Jog", "08/01/2021", "c,", "00:00am");
        Reminder w = new Reminder("Eat", "08/05/2021", "c,", "06:00am");
        Reminder a = new Reminder("Run", "09/01/2021", "c,", "07:00am");
        reminders.add(r);
        reminders.add(s);
        reminders.add(t);
        reminders.add(e);
        reminders.add(k);
        reminders.add(c);
        reminders.add(q);
        reminders.add(w);
        reminders.add(a);
        Log.d("Reminders", reminders.toString());

        SharedPreferences.Editor editor = sp.edit();
        String json = g.toJson(reminders);
        editor.putString("key", json).apply();
        adapter = new Adapter(MainActivity.this, reminders);
        String value = sp.getString("key", "eggs");
        Log.d("Shared:", value);
        list.setAdapter(adapter);
    }

    public void loadExpanded(View view){
        Intent intent = new Intent(this, ExpandedActivity.class);
        String message = "aa";
        intent.putExtra("something",message);
        startActivity(intent);
    }
}