package com.example.chirps;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class MultiController {
    NewRController nrc = new NewRController();
    static ArrayList<HashMap<String, String>> reminders;
    public void gatherReminders(){
        FileController files = new FileController();
        reminders = files.getReminders();
        System.out.println(reminders.get(0).get("name"));
        Log.d("a", reminders.get(0).get("name"));

    }

    public void showExpandedReminder(int Index){

    }

    public void updateReminder(int Index, String name, String description, String date, String time){

    }
}
