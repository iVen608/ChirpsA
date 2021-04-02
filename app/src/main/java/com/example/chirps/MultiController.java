package com.example.chirps;

import java.util.ArrayList;

public class MultiController {
    static ArrayList<Reminder> reminders;

    public static void saveInfo(int index, String title, String desc, String time, String date){
        if(index == reminders.size()){
            Reminder r = new Reminder(title, desc, time, date);
            reminders.add(r);
        }else{
            Reminder key = reminders.get(index);
            key.setTitle(title);
            key.setDate(date);
            key.setDescription(desc);
            key.setTime(desc);
        }

    }

    public static void deleteInfo(int index){
        reminders.remove(index);
    }
}
