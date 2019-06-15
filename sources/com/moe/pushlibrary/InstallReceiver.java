package com.moe.pushlibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.core.Logger;

public class InstallReceiver extends BroadcastReceiver {
    static final String REFERRER = "referrer";

    public void onReceive(Context context, Intent intent) {
        Logger.v("InstallReceiver:client app does not use proxy");
        registerInstallation(context, intent);
    }

    public static void registerInstallation(Context context, Intent intent) {
        if (intent != null) {
            Bundle extras = intent.getExtras();
            Logger.v("InstallReceiver:registerInstallation registering install event: ");
            if (extras != null && extras.containsKey(REFERRER)) {
                MoEHelperUtils.dumpIntentExtras(extras);
                String string = extras.getString(REFERRER);
                if (!TextUtils.isEmpty(string)) {
                    MoEHelperUtils.saveInstallReferrer(string, context);
                }
            }
        }
    }
}
