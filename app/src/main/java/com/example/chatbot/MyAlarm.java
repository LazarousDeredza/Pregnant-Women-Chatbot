package com.example.chatbot;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.media.MediaPlayer;
import android.provider.Settings;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(android.content.Context context, android.content.Intent intent) {
        MediaPlayer mediaPlayer= MediaPlayer.create(context,
                Settings.System.DEFAULT_RINGTONE_URI);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,"reminder")
                .setSmallIcon(R.drawable.bell2)
                .setContentTitle("Reminder")
                .setContentText("Time to take your medicine")
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1,builder.build());
        mediaPlayer.start();
    }
}
