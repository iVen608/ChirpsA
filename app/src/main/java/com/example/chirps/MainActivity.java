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
    MainController mainController = new MainController(this);
    RecyclerView list;
    static ArrayList<Reminder> reminders;
    Adapter adapter;
    Gson g;
    private String key="key";
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        Initializes the key variables and gets the saved information needed for the Recycler View and
        sets the adapter to the reminders Array.
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("Reminders", Context.MODE_PRIVATE);
        this.returnSharedKey();
        list = findViewById(R.id.recycler);
        list.setLayoutManager(new LinearLayoutManager(this));
        Log.d("Reminders", reminders.toString());
        adapter = new Adapter(MainActivity.this, reminders);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void newReminder(View view){
        /*
        When a new reminder is pressed, passing "" allows the class to be similar as to when information is passed.
        The index size will let the MultiController know which index to put the information in if it needs to delete it.
         */
        Intent i = new Intent(this, ExpandedActvity.class);
        i.putExtra("index", reminders.size());
        i.putExtra("title", "");
        i.putExtra("desc", "");
        i.putExtra("time", "");
        i.putExtra("date", "");
        startActivity(i);
    }



    public void returnSharedKey(){
        //Sets the reminders in this class to the mainController expanded list.
        ArrayList<Reminder> s = mainController.expandStringList(sp.getString(key, "def"));
        reminders = s;
    }

    public void save(){
        //Saves the information neccessary.
        reminders = MultiController.reminders;
        SharedPreferences.Editor editor = sp.edit();
        Gson g = new Gson();
        String json = g.toJson(reminders);
        editor.putString("key", json).apply();
    }
}