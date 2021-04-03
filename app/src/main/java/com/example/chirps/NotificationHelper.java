package com.example.chirps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationHelper extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder x = new NotificationCompat.Builder(context, "reminders")
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle("Chirps")
                .setContentText("You have a task due.")
                .setPriority(0);
        NotificationManagerCompat notif = NotificationManagerCompat.from(context);
        notif.notify(100, x.build());
    }
}
