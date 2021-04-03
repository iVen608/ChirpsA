package com.example.chirps;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlarmHelper {
    Context c;
    AlarmManager am;

    public AlarmHelper(Context c){
        this.c = c;
        am = (AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
    }
    public void setAlarm(String date, String time) throws ParseException {
        //Creates a alarm from the initial date to the systems time with a pending intent.
        Intent j = new Intent(c, NotificationHelper.class);
        PendingIntent pi = PendingIntent.getBroadcast(c, 0, j, 0);
        long time_1= System.currentTimeMillis();
        String format = "MM/dd/yyyy hh:mm a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date dateObj = simpleDateFormat.parse(date+" "+time);
        long time_2 = dateObj.getTime();
        long time_3 = time_2 - time_1;
        Log.d("Miliseconds", "Time left:"+time_3);
        am.set(AlarmManager.RTC_WAKEUP, time_1 + time_3, pi);
    }
}
