package com.example.chirps;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainController {

    public ArrayList<Reminder> expandStringList(String s){
        Gson g = new Gson();
        Type userlistType = new TypeToken<ArrayList<Reminder>>(){}.getType();
        ArrayList<Reminder> p = g.fromJson(s, userlistType);
        return p;
    }
}
