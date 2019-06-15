package com.til.colombia.android.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public final class d extends BroadcastReceiver {
    public static final String a = "android.net.conn.CONNECTIVITY_CHANGE";

    public final void onReceive(Context context, Intent intent) {
        synchronized (this) {
            if (intent.getAction().equals(a)) {
                Log.a(i.f, "Received CONNECTIVITY BROADCAST");
                try {
                    h.i().Q = a.c();
                } catch (Exception e) {
                    Log.a(i.f, "", e);
                }
            }
        }
    }
}
