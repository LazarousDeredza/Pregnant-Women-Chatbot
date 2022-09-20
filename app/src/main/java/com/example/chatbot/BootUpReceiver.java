package com.example.chatbot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;

public class BootUpReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, Alarm.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MediaPlayer mediaPlayer= MediaPlayer.create(context,
                Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();

        context.startActivity(intent1);
    }
}
