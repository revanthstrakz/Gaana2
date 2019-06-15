package com.auto;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AutoBroadcastRevciever extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        "media_connected".equals(intent.getStringExtra("media_connection_status"));
    }
}
