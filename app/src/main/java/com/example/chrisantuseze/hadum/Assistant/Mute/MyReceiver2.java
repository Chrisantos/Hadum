package com.example.chrisantuseze.hadum.Assistant.Mute;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;

/**
 * Created by CHRISANTUS EZE on 16/10/2017.
 */

public class MyReceiver2 extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Normal mode turned on", Toast.LENGTH_SHORT).show();
        AudioManager am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    }
}
