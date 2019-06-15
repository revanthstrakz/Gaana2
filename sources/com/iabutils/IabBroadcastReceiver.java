package com.iabutils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class IabBroadcastReceiver extends BroadcastReceiver {
    private final a a;

    public interface a {
        void a();
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a != null) {
            this.a.a();
        }
    }
}
