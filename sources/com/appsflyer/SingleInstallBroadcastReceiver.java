package com.appsflyer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;

public class SingleInstallBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra("referrer");
            if (stringExtra != null) {
                if (stringExtra.contains("AppsFlyer_Test") && intent.getStringExtra("TestIntegrationMode") != null) {
                    h.c().a(context, intent);
                    return;
                } else if (context.getSharedPreferences("appsflyer-data", 0).getString("referrer", null) != null) {
                    h.c();
                    h.a(context, stringExtra);
                    return;
                }
            }
            stringExtra = i.a().a("referrer_timestamp");
            long currentTimeMillis = System.currentTimeMillis();
            if (stringExtra == null || currentTimeMillis - Long.valueOf(stringExtra).longValue() >= AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS) {
                AFLogger.d("SingleInstallBroadcastReceiver called");
                h.c().a(context, intent);
                i.a().a("referrer_timestamp", String.valueOf(System.currentTimeMillis()));
            }
        }
    }
}
