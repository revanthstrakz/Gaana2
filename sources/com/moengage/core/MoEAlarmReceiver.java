package com.moengage.core;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.moe.pushlibrary.MoEWorker;

public class MoEAlarmReceiver extends WakefulBroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            Logger.d("Inside onReceive of MoEAlarmReceiver");
            intent = new Intent(context, MoEWorker.class);
            intent.putExtra(MoEWorker.EXTRA_SERVICE_TYPE_SEND_DATA, true);
            WakefulBroadcastReceiver.startWakefulService(context, intent);
        } catch (Exception e) {
            Logger.f("MoEAlarmReceiver:onReceive : exception", e);
        }
    }
}
