package com.example.chirps;

import android.content.Context;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class FileController {
    private Context context;
    ArrayList<HashMap<String, String>> reminders = new ArrayList<>();
    JSONObject reminderJO;
    public ArrayList<HashMap<String, String>> getReminders(){
        HashMap<String, String> a = new HashMap<>();
        a.put("name", "Apples");
        a.put("description", "Once upon a time");
        a.put("date", "01.2020");
        a.put("time", "01:08:20");
        HashMap<String, String> b = new HashMap<>();
        b.put("name", "Apples");
        b.put("description", "Once upon a time");
        b.put("date", "01.2020");
        b.put("time", "01:08:20");
        reminders.add(a);
        reminders.add(b);
        return reminders;
    }
}
