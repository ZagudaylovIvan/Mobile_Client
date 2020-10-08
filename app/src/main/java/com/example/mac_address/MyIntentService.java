package com.example.mac_address;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;

public class MyIntentService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
