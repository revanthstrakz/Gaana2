package com.moe.pushlibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.core.Logger;
import com.moengage.core.MoEConstants;
import com.moengage.core.MoEDispatcher;
import com.moengage.core.MoEWorkerTask;

public class AppUpdateReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            try {
                String str = intent.getPackage();
                String packageName = context.getPackageName();
                MoEHelperUtils.dumpIntentExtras(intent);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("AppUpdateReceiver: received update intent for PackageIntended:");
                stringBuilder.append(str);
                stringBuilder.append(" and it is listening for thisPackage: ");
                stringBuilder.append(packageName);
                stringBuilder.append(" intent: ");
                stringBuilder.append(intent.toString());
                Logger.v(stringBuilder.toString());
                if (!TextUtils.isEmpty(str) && str.equals(packageName)) {
                    MoEDispatcher.getInstance(context).addTaskToQueue(new MoEWorkerTask(context, MoEConstants.SERVICE_TYPE_APP_UPDATE, null));
                }
            } catch (Exception e) {
                Logger.f("AppUpdateReceiver: onReceive() ", e);
            }
        }
    }
}
