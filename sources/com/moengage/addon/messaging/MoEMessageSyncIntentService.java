package com.moengage.addon.messaging;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.moe.pushlibrary.MoEHelper;
import com.moengage.core.Logger;

public class MoEMessageSyncIntentService extends IntentService {
    public MoEMessageSyncIntentService() {
        super("MoEMessageSyncIntentService");
    }

    /* Access modifiers changed, original: protected */
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            try {
                String action = intent.getAction();
                Object obj = -1;
                if (action.hashCode() == 1489894439) {
                    if (action.equals(MoEMessagingConstants.ACTION_SYNC_MESSAGES)) {
                        obj = null;
                    }
                }
                if (obj == null) {
                    MessagingHandlerImpl.getInstance().scheduleAndSyncMessages(getApplicationContext());
                    MoEHelper.getInstance(getApplicationContext()).syncInteractionDataNow();
                    WakefulBroadcastReceiver.completeWakefulIntent(intent);
                }
            } catch (Exception e) {
                Logger.e("MoEMessageSyncIntentService#onHandleIntent: Exception", e);
            }
        }
    }
}
