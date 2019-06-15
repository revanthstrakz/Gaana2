package com.moengage.pushbase;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import com.google.android.exoplayer2.C;
import com.moengage.core.ConfigurationProvider;
import com.moengage.core.Logger;
import com.moengage.core.MoEUtils;
import com.moengage.core.executor.TaskProcessor;
import com.moengage.push.PushManager;
import com.moengage.pushbase.push.MoEPushWorker;
import com.moengage.pushbase.push.MoEPushWorkerTask;

public final class PushUtils {
    private PushUtils() {
    }

    public static void scheduleDeviceRegistrationCall(Context context) {
        if (context == null) {
            Logger.e("PushUtils:Context is null device cannot register for push");
            return;
        }
        Logger.v("PushUtils :: scheduleDeviceRegistrationCall: ");
        if (!ConfigurationProvider.getInstance(context).isDeviceRegistered() && !MoEUtils.isRegistrationScheduled(context)) {
            int currentExponentialCounter = MoEUtils.getCurrentExponentialCounter(context);
            if (currentExponentialCounter >= 512) {
                MoEUtils.saveCurrentExponentialCounter(context, 1);
                Logger.e("PushUtils:registration failed miserably so skipping it for now");
                MoEUtils.setRegistrationScheduled(context, false);
                return;
            }
            MoEUtils.saveCurrentExponentialCounter(context, currentExponentialCounter * 2);
            schedulePushRegistration(context, currentExponentialCounter, PushManager.REQ_REGISTRATION);
            MoEUtils.setRegistrationScheduled(context, true);
        }
    }

    public static void schedulePushRegistration(Context context, int i, String str) {
        Intent intent = new Intent(context, MoEPushWorker.class);
        intent.setAction(str);
        ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(0, (long) (i * 1000), PendingIntent.getService(context, 0, intent, C.ENCODING_PCM_MU_LAW));
    }

    public static void offLoadTaskToWorker(@NonNull Context context, @NonNull String str) {
        if (context == null) {
            try {
                Logger.e("PushUtils :Context is null cannot call MoEPushWorker");
            } catch (Exception e) {
                Logger.f("PushUtils: offLoadTaskToWorker() ", e);
            }
        } else {
            TaskProcessor.getInstance().addTask(new MoEPushWorkerTask(context, str, null));
        }
    }
}
