package com.example.chirps;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpandedController {
    String _name;
    String _description;
    String _date;
    String _time;
    Activity _act;
    public ExpandedController(Activity activity, int index){
        ArrayList<HashMap<String, String>> reminders = MultiController.reminders;
        this._name = reminders.get(index).get("name");
        this._description = reminders.get(index).get("description");
        this._date = reminders.get(index).get("date");
        this._time = reminders.get(index).get("time");
        this._act = activity;
    }

    public String get_name() {
        return _name;
    }
    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }
}
