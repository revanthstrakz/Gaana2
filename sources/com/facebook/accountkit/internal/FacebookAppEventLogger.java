package com.facebook.accountkit.internal;

import android.content.Context;
import android.os.Bundle;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

final class FacebookAppEventLogger {
    private static final String FB_EVENT_NAME_LOGIN_ATTEMPT = "fb_ak_login_attempt";
    private static final String FB_EVENT_NAME_LOGIN_COMPLETE = "fb_ak_login_complete";
    private static final String FB_EVENT_NAME_LOGIN_DIALOG_IMPRESSION = "fb_ak_login_dialog_impression";
    private static final String FB_EVENT_NAME_LOGIN_START = "fb_ak_login_start";
    private static final String TAG = FacebookAppEventLogger.class.getCanonicalName();
    private static final Map<String, String> eventNameMap = new HashMap<String, String>() {
        {
            put(InternalLogger.EVENT_NAME_EMAIL_VIEW, FacebookAppEventLogger.FB_EVENT_NAME_LOGIN_DIALOG_IMPRESSION);
            put(InternalLogger.EVENT_NAME_PHONE_NUMBER_VIEW, FacebookAppEventLogger.FB_EVENT_NAME_LOGIN_DIALOG_IMPRESSION);
            put(InternalLogger.EVENT_NAME_LOGIN_START, FacebookAppEventLogger.FB_EVENT_NAME_LOGIN_START);
            put(InternalLogger.EVENT_NAME_LOGIN_VERIFY, FacebookAppEventLogger.FB_EVENT_NAME_LOGIN_ATTEMPT);
            put(InternalLogger.EVENT_NAME_CONFIRM_SEAMLESS_PENDING, FacebookAppEventLogger.FB_EVENT_NAME_LOGIN_ATTEMPT);
            put(InternalLogger.EVENT_NAME_LOGIN_COMPLETE, FacebookAppEventLogger.FB_EVENT_NAME_LOGIN_COMPLETE);
        }
    };
    private Object fbAppEventLogger = null;

    FacebookAppEventLogger(Context context) {
        if (isFacebookSDKInitialized()) {
            try {
                try {
                    try {
                        this.fbAppEventLogger = Class.forName("com.facebook.appevents.AppEventsLogger").getMethod("newLogger", new Class[]{Context.class}).invoke(null, new Object[]{context});
                    } catch (Exception e) {
                        Utility.logd(TAG, e);
                    }
                } catch (NoSuchMethodException e2) {
                    Utility.logd(TAG, e2);
                }
            } catch (ClassNotFoundException unused) {
            }
        }
    }

    public void logImpression(String str, Bundle bundle, boolean z) {
        if (z) {
            logFacebookAppEvents(str, null, bundle);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void logFacebookAppEvents(String str, Double d, Bundle bundle) {
        if (!(((String) eventNameMap.get(str)) == null || this.fbAppEventLogger == null)) {
            try {
                Method method = this.fbAppEventLogger.getClass().getMethod("logSdkEvent", new Class[]{String.class, Double.class, Bundle.class});
                try {
                    method.invoke(this.fbAppEventLogger, new Object[]{str, d, bundle});
                } catch (Exception e) {
                    Utility.logd(TAG, e);
                }
            } catch (NoSuchMethodException e2) {
                Utility.logd(TAG, e2);
            }
        }
    }

    static boolean isFacebookSDKInitialized() {
        try {
            try {
            } catch (NoSuchMethodException e) {
                Utility.logd(TAG, e);
            }
            try {
                return ((Boolean) Class.forName("com.facebook.FacebookSdk").getMethod("isInitialized", new Class[0]).invoke(null, new Object[0])).booleanValue();
            } catch (Exception e2) {
                Utility.logd(TAG, e2);
                return false;
            }
        } catch (ClassNotFoundException unused) {
        }
    }
}
