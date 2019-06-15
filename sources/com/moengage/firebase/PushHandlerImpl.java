package com.moengage.firebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.moe.pushlibrary.MoEHelper;
import com.moengage.core.ConfigurationProvider;
import com.moengage.core.Logger;
import com.moengage.core.MoEDispatcher;
import com.moengage.core.MoEUtils;
import com.moengage.push.PushManager;
import com.moengage.push.PushManager.PushHandler;
import com.moengage.pushbase.PushUtils;
import com.moengage.pushbase.push.MoEPushWorker;
import com.moengage.pushbase.push.MoEPushWorkerTask;
import com.moengage.pushbase.push.PushMessageListener;
import java.util.Map;

public class PushHandlerImpl implements PushHandler {
    private static PushHandlerImpl _INSTANCE;
    private PushMessageListener pushMessageListener;

    public void deleteToken(Context context, String str) {
    }

    public void handlePushPayload(Context context, Intent intent) {
    }

    public PushHandlerImpl() {
        _INSTANCE = this;
    }

    public static PushHandlerImpl getInstance() {
        if (_INSTANCE == null) {
            PushHandlerImpl pushHandlerImpl = new PushHandlerImpl();
        }
        return _INSTANCE;
    }

    public String registerForPushToken(Context context) {
        ConfigurationProvider.getInstance(context).setDeviceRegistered(false);
        String pushToken = getPushToken(context);
        return pushToken != null ? pushToken : null;
    }

    @Nullable
    public String getPushToken(Context context) {
        try {
            String token = FirebaseInstanceId.getInstance().getToken();
            if (!TextUtils.isEmpty(token)) {
                PushManager.getInstance().refreshTokenInternal(context, token, PushManager.TOKEN_BY_MOE);
                return token;
            }
        } catch (Exception e) {
            PushUtils.scheduleDeviceRegistrationCall(context);
            Logger.e("PushHandlerImpl(firebase):registerForPush ", e);
            MoEHelper.getInstance(context).getDelegate().logPushFailureEvent(context, e.getMessage());
        }
        return null;
    }

    public void handlePushPayload(Context context, String str) {
        Logger.e("PushHandlerImpl(firebase):This method should only be called from the baidu module");
    }

    public void handlePushPayload(Context context, Bundle bundle) {
        if (bundle != null) {
            try {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    MoEDispatcher.getInstance(context).addTaskToQueueBeginning(new MoEPushWorkerTask(context, "SHOW_NOTIFICATION", bundle));
                } else {
                    getMessageListener().onMessagereceived(context, bundle);
                }
            } catch (Exception e) {
                Logger.f("PushHandlerImpl(firebase): handlePushPayload() ", e);
            }
        }
    }

    public void offLoadToWorker(Context context, String str) {
        PushUtils.offLoadTaskToWorker(context, str);
    }

    public void logNotificationClicked(Context context, Intent intent) {
        getMessageListener().logNotificationClicked(context, intent);
    }

    public void setMessageListener(Object obj) {
        if (obj instanceof PushMessageListener) {
            this.pushMessageListener = (PushMessageListener) obj;
        } else {
            Logger.e("PushHandlerImpl(firebase):Custom Listener does not extend PushMessageListener");
        }
    }

    public void setPushRegistrationFallback(Context context) {
        ConfigurationProvider instance = ConfigurationProvider.getInstance(context);
        if (instance.isGCMRegistrationEnabled() && TextUtils.isEmpty(instance.getGCMToken())) {
            PushUtils.schedulePushRegistration(context, 2, MoEPushWorker.PUSH_REG_FALLBACK);
        }
    }

    public PushMessageListener getMessageListener() {
        if (this.pushMessageListener == null) {
            this.pushMessageListener = new PushMessageListener();
        }
        return this.pushMessageListener;
    }

    public void handlePushPayload(Context context, Map<String, String> map) {
        Bundle convertMapToBundle = MoEUtils.convertMapToBundle(map);
        if (convertMapToBundle != null) {
            handlePushPayload(context, convertMapToBundle);
        }
    }
}
