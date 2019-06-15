package com.managers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        WakeLock newWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, "GAANA_GOOGLE_NOW");
        newWakeLock.acquire();
        v.a().b();
        newWakeLock.release();
    }
}
