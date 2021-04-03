package com.example.chirps;

import android.content.Context;
import android.widget.Toast;

public class ExpandedController {
    Context c;

    public ExpandedController(Context c){
        this.c = c;
    }
    public Boolean validateValues(String title, String desc, String time, String date){
        Boolean value = false;
        if (title.length() == 0  || time.length() == 0 || date.length() == 0){
            Toast.makeText(c, "Your reminder has some blank fields.", Toast.LENGTH_SHORT).show();
        } else if ((title != ""  && time != "" && date != "")) {
            Toast.makeText(c, "Reminder has been saved successfully", Toast.LENGTH_SHORT).show();
            value = true;
        }
        return value;
    }

    public void deleteMessage(){
        Toast.makeText(c, "Your reminder has been deleted.", Toast.LENGTH_SHORT).show();
    }

}
