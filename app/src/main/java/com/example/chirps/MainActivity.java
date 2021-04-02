package com.example.chirps;


import android.content.Context;
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
    private String key="key";
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("Reminders", Context.MODE_PRIVATE);
        this.returnSharedKey();
        list = findViewById(R.id.recycler);
        list.setLayoutManager(new LinearLayoutManager(this));
        Log.d("Reminders", reminders.toString());
        adapter = new Adapter(MainActivity.this, reminders);
        list.setAdapter(adapter);

    }

    public void loadExpanded(View view){

    }



    public void returnSharedKey(){
        ArrayList<Reminder> s = mainController.expandStringList(sp.getString(key, "def"));
        reminders = s;
    }

    public void save(ArrayList<Reminder> r){
        SharedPreferences.Editor editor = sp.edit();
        Gson g = new Gson();
        String json = g.toJson(r);
        editor.putString("key", json).apply();
    }
}