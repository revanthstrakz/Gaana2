package com.player_framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class HeadsetPlugIntentReceiver extends BroadcastReceiver {
    private String a = "headSet";

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.HEADSET_PLUG")) {
            switch (intent.getIntExtra("state", -1)) {
                case 0:
                    Log.d(this.a, "Headset is unplugged");
                    return;
                case 1:
                    Log.d(this.a, "Headset is plugged");
                    return;
                default:
                    Log.d(this.a, "No idea what the headset state is");
                    return;
            }
        }
    }
}
