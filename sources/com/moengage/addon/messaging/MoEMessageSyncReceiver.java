package com.moengage.addon.messaging;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.moengage.core.Logger;

public class MoEMessageSyncReceiver extends WakefulBroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            Logger.d("Inside onReceive of MoEMessageSyncReceiver");
            if (intent != null && intent.getAction().equals(MoEMessagingConstants.ACTION_SYNC_MESSAGES)) {
                intent = new Intent(context, MoEMessageSyncIntentService.class);
                intent.setAction(MoEMessagingConstants.ACTION_SYNC_MESSAGES);
                WakefulBroadcastReceiver.startWakefulService(context, intent);
            }
        } catch (Exception e) {
            Logger.e("MoEMessageSyncReceiver#onReceive ", e);
        }
    }
}
