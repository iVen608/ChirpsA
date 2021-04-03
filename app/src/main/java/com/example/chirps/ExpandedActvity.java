package com.example.chirps;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ExpandedActvity extends AppCompatActivity {
    int hours, min;
    ExpandedController ec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        This expanded activity is used for both new and existing by passing an empty string for new activities,
        or the index information from the entry to populate the fields.
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded_actvity);
        ec = new ExpandedController(this);
        TextView title = findViewById(R.id.title_text);
        TextView desc = findViewById(R.id.desc_text);
        TextView timeT = findViewById(R.id.time_text);
        TextView dateT = findViewById(R.id.date_text);
        timeT.setText(getIntent().getStringExtra("time"));
        title.setText(getIntent().getStringExtra("title"));
        desc.setText(getIntent().getStringExtra("desc"));
        dateT.setText(getIntent().getStringExtra("date"));
    }

    public void goBack(View view){
        this.finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void save(View view) throws ParseException {
        // This function add only save the information if it's validated from the ExpandedController.
        int index = getIntent().getIntExtra("index", 0);
        TextView titleT = findViewById(R.id.title_text);
        TextView timeT = findViewById(R.id.time_text);
        TextView descT = findViewById(R.id.desc_text);
        TextView dateT = findViewById(R.id.date_text);
        String title = titleT.getText().toString();
        String desc = descT.getText().toString();
        String time = timeT.getText().toString();
        String date = dateT.getText().toString();
        Boolean result = ec.validateValues(title, desc, time, date);
        if(result == true) {
            notificationChanel();
            MultiController.saveInfo(index, title, desc, time, date);
            SharedPreferences sp = getSharedPreferences("Reminders", Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();
            Gson g = new Gson();
            String json = g.toJson(MultiController.reminders);
            ed.putString("key", json).apply();
            AlarmHelper ah = new AlarmHelper(ExpandedActvity.this);
            ah.setAlarm(date, time);
            this.finish();
        }
    }

    public void delete(View view){
        /*
        The function calls Expanded Controller to create a delete toast as well as deleting the information
        stored in the MultiController. After which, it begins the Main Activity.
         */
        ec.deleteMessage();
        int index = getIntent().getIntExtra("index", 0);
        MultiController.deleteInfo(index);
        SharedPreferences sp = getSharedPreferences("Reminders", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        Gson g = new Gson();
        String json = g.toJson(MultiController.reminders);
        ed.putString("key", json).apply();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void selectTime(View view){
        /*
        This function is called when the time button is pressed and creates the TimePicker icon for the
        user to select the time.
        Code adapted from: https://www.youtube.com/watch?v=o-HVE_VxyjQ;
         */
        TextView timeT = findViewById(R.id.time_text);
        TimePickerDialog tpd = new TimePickerDialog(
                ExpandedActvity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hours = hourOfDay;
                        min = minute;
                        String t = hours+":"+min;
                        SimpleDateFormat dtfo = new SimpleDateFormat(
                                "HH:mm"
                        );
                        try {
                            Date date = dtfo.parse(t);
                            SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                            timeT.setText(f12Hours.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, 12, 0, false
        );
        tpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tpd.updateTime(hours, min);
        tpd.show();
    }

    public void selectDate(View view){
        /*
        This function is called when the date button is pressed and creates the DatePicker icon for the
        user to select the time.
        Code adapted from: https://www.youtube.com/watch?v=o-HVE_VxyjQ but reversed for DatePicker instead.
         */
        TextView dateT = findViewById(R.id.date_text);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(ExpandedActvity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        dateT.setText(month+"/"+dayOfMonth+"/"+year);
                    }
                }, year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void notificationChanel(){
        /*
        This function validates the SDK of the android device to ensure notification channel can be used. Then,
        it initiates the notification manager to create a notification which can be called when the button is saved.
         */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Reminder";
            String desc = "Chirps";
            NotificationChannel nc = new NotificationChannel("reminders", name, NotificationManager.IMPORTANCE_DEFAULT);
            nc.setDescription(desc);

            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(nc);
        }
    }
}