package com.moe.pushlibrary;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.core.Logger;
import com.moengage.core.MoEDispatcher;

public class MoEWorker extends IntentService {
    public static final String EXTRA_SERVICE_TYPE_SEND_DATA = "DEAL_WITH_SENDING_DATA";
    public static final int REQ_CODE_SEND_DATA = 123;

    public MoEWorker() {
        super("MoEWorker");
    }

    /* Access modifiers changed, original: protected */
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            try {
                Bundle extras = intent.getExtras();
                MoEHelperUtils.dumpIntentExtras(extras);
                if (extras.containsKey(EXTRA_SERVICE_TYPE_SEND_DATA)) {
                    MoEDispatcher.getInstance(getApplicationContext()).sendInteractionData();
                    WakefulBroadcastReceiver.completeWakefulIntent(intent);
                } else {
                    Logger.e("Did not understand request");
                }
            } catch (Exception e) {
                Logger.f("MoEWorker: onHandleIntent", e);
            }
        }
    }
}
