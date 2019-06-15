package com.moengage.push;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.moe.pushlibrary.MoEHelper;
import com.moe.pushlibrary.PayloadBuilder;
import com.moengage.core.ConfigurationProvider;
import com.moengage.core.DeviceAddTask;
import com.moengage.core.Logger;
import com.moengage.core.MoEDispatcher;
import java.util.Map;

public class PushManager {
    private static final String ID_PREFIX = "|ID|";
    public static final String REG_ON_APP_OPEN = "REG_ON_APP_OPEN";
    public static final String REQ_DELETE_TOKEN = "MOE_DEL_TOK";
    public static final String REQ_REFRESH = "MOE_REG_REFRESH";
    public static final String REQ_REGISTRATION = "MOE_REG_REQ";
    public static final String SHOW_NOTIFICATION = "SHOW_NOTIFICATION";
    public static final String TOKEN_BY_MOE = "MoE";
    private static PushManager _INSTANCE;
    private final String ATTR_PUSH_TOKEN = "push_token";
    private final String ATTR_REGISTRATION_BY = "registered_by";
    private final String TOKEN_EVENT = "TOKEN_EVENT";
    private boolean backStackBuilderOptoutFlag = false;
    private boolean isBaiduEnabled = false;
    private final Object lock = new Object();
    private boolean optOutOfMoEngageExtras = false;
    private PushHandler pushHandler;
    private OnTokenReceivedListener tokenListener;

    public interface OnTokenReceivedListener {
        void onTokenReceived(String str);
    }

    public interface PushHandler {
        @WorkerThread
        void deleteToken(Context context, String str);

        Object getMessageListener();

        @Nullable
        @WorkerThread
        String getPushToken(Context context);

        @Deprecated
        void handlePushPayload(Context context, Intent intent);

        void handlePushPayload(Context context, Bundle bundle);

        void handlePushPayload(Context context, String str);

        void handlePushPayload(Context context, Map<String, String> map);

        void logNotificationClicked(Context context, Intent intent);

        void offLoadToWorker(Context context, String str);

        @WorkerThread
        String registerForPushToken(Context context);

        void setMessageListener(Object obj);

        void setPushRegistrationFallback(Context context);
    }

    private PushManager() {
        loadPushHandler();
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0018 */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing block: B:12:?, code skipped:
            return;
     */
    private void loadPushHandler() {
        /*
        r3 = this;
        r0 = r3.isBaiduEnabled;	 Catch:{ Exception -> 0x0040 }
        if (r0 != 0) goto L_0x002c;
    L_0x0004:
        r0 = "com.moengage.firebase.PushHandlerImpl";
        r0 = java.lang.Class.forName(r0);	 Catch:{ Exception -> 0x0018 }
        r0 = r0.newInstance();	 Catch:{ Exception -> 0x0018 }
        r0 = (com.moengage.push.PushManager.PushHandler) r0;	 Catch:{ Exception -> 0x0018 }
        r3.pushHandler = r0;	 Catch:{ Exception -> 0x0018 }
        r0 = "PushManager:loadPushHandler FCM Enabled";
        com.moengage.core.Logger.v(r0);	 Catch:{ Exception -> 0x0018 }
        goto L_0x0059;
    L_0x0018:
        r0 = "com.moengage.push.gcm.PushHandlerImpl";
        r0 = java.lang.Class.forName(r0);	 Catch:{ Exception -> 0x0040 }
        r0 = r0.newInstance();	 Catch:{ Exception -> 0x0040 }
        r0 = (com.moengage.push.PushManager.PushHandler) r0;	 Catch:{ Exception -> 0x0040 }
        r3.pushHandler = r0;	 Catch:{ Exception -> 0x0040 }
        r0 = "PushManager:loadPushHandler GCM Enabled";
        com.moengage.core.Logger.v(r0);	 Catch:{ Exception -> 0x0040 }
        goto L_0x0059;
    L_0x002c:
        r0 = "com.moengage.baidu.PushHandlerImpl";
        r0 = java.lang.Class.forName(r0);	 Catch:{ Exception -> 0x0040 }
        r0 = r0.newInstance();	 Catch:{ Exception -> 0x0040 }
        r0 = (com.moengage.push.PushManager.PushHandler) r0;	 Catch:{ Exception -> 0x0040 }
        r3.pushHandler = r0;	 Catch:{ Exception -> 0x0040 }
        r0 = "PushManager:loadPushHandler Baidu Enabled";
        com.moengage.core.Logger.v(r0);	 Catch:{ Exception -> 0x0040 }
        goto L_0x0059;
    L_0x0040:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "PushManager : loadPushHandler : did not find supported module: ";
        r1.append(r2);
        r0 = r0.getMessage();
        r1.append(r0);
        r0 = r1.toString();
        com.moengage.core.Logger.e(r0);
    L_0x0059:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moengage.push.PushManager.loadPushHandler():void");
    }

    public static PushManager getInstance() {
        if (_INSTANCE == null) {
            _INSTANCE = new PushManager();
        }
        return _INSTANCE;
    }

    public void setMessageListener(Object obj) {
        if (this.pushHandler != null) {
            this.pushHandler.setMessageListener(obj);
        }
    }

    public void refreshToken(Context context, String str) {
        refreshTokenInternal(context, str, "App");
    }

    public void refreshTokenInternal(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            Logger.v("PushManager:refreshToken");
            synchronized (this.lock) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("PushManager:refreshToken before ripping: = ");
                stringBuilder.append(str);
                Logger.v(stringBuilder.toString());
                str = ripMultiplexingExtras(str);
                if (this.tokenListener != null) {
                    this.tokenListener.onTokenReceived(str);
                }
                String gCMToken = ConfigurationProvider.getInstance(context).getGCMToken();
                boolean tokenRefreshRequired = tokenRefreshRequired(context, str);
                if (tokenRefreshRequired || !ConfigurationProvider.getInstance(context).isDeviceRegistered()) {
                    ConfigurationProvider.getInstance(context).setGCMToken(str);
                    MoEDispatcher.getInstance(context).addTaskToQueue(new DeviceAddTask(context));
                    PayloadBuilder payloadBuilder = new PayloadBuilder();
                    payloadBuilder.putAttrString("push_token", str);
                    payloadBuilder.putAttrString("registered_by", str2);
                    MoEHelper.getInstance(context).trackEvent("TOKEN_EVENT", payloadBuilder.build());
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("PushManager:refreshToken oldId: = ");
                stringBuilder2.append(gCMToken);
                stringBuilder2.append(" token = ");
                stringBuilder2.append(str);
                stringBuilder2.append(" --updating[true/false]: ");
                stringBuilder2.append(tokenRefreshRequired);
                Logger.v(stringBuilder2.toString());
            }
        }
    }

    private String ripMultiplexingExtras(String str) {
        return (TextUtils.isEmpty(str) || !str.startsWith(ID_PREFIX)) ? str : str.substring(7);
    }

    public boolean tokenRefreshRequired(Context context, String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String gCMToken = ConfigurationProvider.getInstance(context).getGCMToken();
        if (TextUtils.isEmpty(gCMToken) || !str.equals(gCMToken)) {
            z = true;
        }
        return z;
    }

    public PushHandler getPushHandler() {
        return this.pushHandler;
    }

    public final void optoutBackStackBuilder(Boolean bool) {
        this.backStackBuilderOptoutFlag = bool.booleanValue();
    }

    public final boolean isBackStackBuilderOptedOut(Context context) {
        return this.backStackBuilderOptoutFlag;
    }

    public void enableBaiduPush() {
        this.isBaiduEnabled = true;
        loadPushHandler();
    }

    public boolean isIsBaiduEnabled() {
        return this.isBaiduEnabled;
    }

    public void setTokenObserver(OnTokenReceivedListener onTokenReceivedListener) {
        this.tokenListener = onTokenReceivedListener;
    }

    public final void optOutMoEngageExtras(boolean z) {
        this.optOutOfMoEngageExtras = z;
    }

    public final boolean isMoEngageExtrasOptedOut() {
        return this.optOutOfMoEngageExtras;
    }
}
