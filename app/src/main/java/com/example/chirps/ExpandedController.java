package com.example.chirps;

public class ExpandedController {
    String _name;
    String _description;
    String _date;
    String _time;
    public ExpandedController(String name, String description, String date, String time){
        this._name = name;
        this._description = description;
        this._date = date;
        this._time = time;
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
