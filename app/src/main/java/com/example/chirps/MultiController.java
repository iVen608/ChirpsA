package com.example.chirps;

import android.util.Log;

import java.util.ArrayList;

public class MultiController {
    static ArrayList<Reminder> reminders;

    public static void saveInfo(int index, String title, String desc, String time, String date){
        //The saveInfo checks to see if the entry is new or existing, if it's new create a new Reminder with the information.
        //Otherwise, update the existing entry.
        if(index == reminders.size()){
            Reminder r = new Reminder(title, desc, time, date);
            reminders.add(r);
        }else{
            Log.d("Info", "Title: "+title+" Desc: "+desc+" Time: "+time+" Date: "+date);
            Reminder key = reminders.get(index);
            key.setTitle(title);
            key.setDate(date);
            key.setDescription(desc);
            key.setTime(time);
        }

    }

    public static void deleteInfo(int index){
        //The information is only deleted if it's a existing entry.
        if(index < reminders.size()) {
            reminders.remove(index);
        }
    }
}
