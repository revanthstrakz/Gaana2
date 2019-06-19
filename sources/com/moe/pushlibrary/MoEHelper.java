package com.moe.pushlibrary;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.moe.pushlibrary.models.GeoLocation;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.core.ConfigurationProvider;
import com.moengage.core.Logger;
import com.moengage.core.MoEDispatcher;
import com.moengage.core.MoEEventManager;
import com.moengage.inapp.InAppController;
import com.moengage.inapp.InAppController.InAppHandler;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class MoEHelper {
    private static MoEHelper _INSTANCE;
    private static int activityCounter;
    private static boolean isAutoIntegration;
    private String BUNDLE_EXTRA_RESTORING = "EXTRA_RESTORING";
    private long flushInterval = -1;
    private boolean isActivityBeingRestored = false;
    private boolean isPeriodicSyncEnabled = true;
    private Context mContext;
    private MoEDispatcher mDispatcher = null;
    private boolean mResumed = false;
    private boolean mStarted = false;

    public void onFragmentStop(Activity activity, String str) {
    }

    public static int getActivityCounter() {
        return activityCounter;
    }

    private static synchronized void incrementCounter() {
        synchronized (MoEHelper.class) {
            activityCounter++;
        }
    }

    private static synchronized void decrementCounter() {
        synchronized (MoEHelper.class) {
            activityCounter--;
        }
    }

    public static boolean isAppInForeground() {
        return activityCounter > 0;
    }

    @Deprecated
    public MoEHelper(Context context) {
        Logger.enableDebugLog(context);
        this.mContext = context.getApplicationContext();
        if (this.mDispatcher == null) {
            this.mDispatcher = getDelegate();
        }
        _INSTANCE = this;
    }

    public static synchronized MoEHelper getInstance(Context context) {
        MoEHelper moEHelper;
        synchronized (MoEHelper.class) {
            if (_INSTANCE == null) {
                _INSTANCE = new MoEHelper(context);
            }
            moEHelper = _INSTANCE;
        }
        return moEHelper;
    }

    public void initialize(String str, String str2) {
        this.mDispatcher.initialize(str, str2);
    }

    public void onNewIntent(Activity activity, Intent intent) {
        if (!this.isActivityBeingRestored) {
            this.mDispatcher.onStart(activity, intent);
        }
        InAppHandler inAppHandler = InAppController.getInstance().getInAppHandler();
        if (inAppHandler != null) {
            inAppHandler.registerInAppManager(activity);
        }
    }

    public void onStart(@NonNull Activity activity) {
        if (!isAutoIntegration) {
            onStartInternal(activity);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void onStartInternal(@NonNull Activity activity) {
        if (getActivityCounter() == 0) {
            this.mDispatcher.onAppOpen();
        }
        incrementCounter();
        this.mStarted = true;
        this.mContext = activity.getApplicationContext();
        onNewIntent(activity, null);
    }

    public void onStop(@NonNull Activity activity) {
        if (!isAutoIntegration) {
            onStopInternal(activity);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void onStopInternal(@NonNull Activity activity) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Activity onStop called for ");
        stringBuilder.append(activity.toString());
        Logger.v(stringBuilder.toString());
        boolean isChangingConfiguration = MoEHelperUtils.isChangingConfiguration(activity);
        decrementCounter();
        InAppHandler inAppHandler = InAppController.getInstance().getInAppHandler();
        if (inAppHandler != null) {
            inAppHandler.unregisterInAppManager(activity);
        }
        this.mDispatcher.onStop(activity, isChangingConfiguration);
        String name = activity.getClass().getName();
        if (!this.mStarted) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("MoEHelper: onStart callback not called: ");
            stringBuilder.append(name);
            Logger.e(stringBuilder.toString());
        }
        if (!this.mResumed) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("MoEHelper: onResume callback not called: ");
            stringBuilder.append(name);
            Logger.e(stringBuilder.toString());
        }
    }

    public void onResume(@NonNull Activity activity) {
        if (!isAutoIntegration) {
            onResumeInternal(activity);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void onResumeInternal(Activity activity) {
        if (this.mContext == null) {
            this.mContext = activity.getApplicationContext();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Activity onResume called for ");
        stringBuilder.append(activity.toString());
        Logger.v(stringBuilder.toString());
        this.mResumed = true;
        this.mDispatcher.onResume(activity, this.isActivityBeingRestored);
        this.isActivityBeingRestored = false;
    }

    public void onFragmentStart(Activity activity, String str) {
        this.mDispatcher.onFragmentStart(activity, str);
    }

    public void onSaveInstanceState(Bundle bundle) {
        Logger.v("MoEHelper:onSaveInstanceState-- saving state");
        if (bundle != null) {
            bundle.putBoolean(this.BUNDLE_EXTRA_RESTORING, true);
        }
    }

    public void onRestoreInstanceState(Bundle bundle) {
        Logger.v("MoEHelper:onRestoreInstanceState-- restoring state");
        if (bundle != null && bundle.containsKey(this.BUNDLE_EXTRA_RESTORING)) {
            this.isActivityBeingRestored = true;
            bundle.remove(this.BUNDLE_EXTRA_RESTORING);
        }
    }

    public MoEHelper trackEvent(@NonNull String str, JSONObject jSONObject) {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        MoEEventManager.getInstance(this.mContext).trackEvent(str.trim(), jSONObject);
        return this;
    }

    public MoEHelper trackEvent(@NonNull String str, @NonNull HashMap<String, String> hashMap) {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        Set<String> keySet = hashMap.keySet();
        if (keySet.isEmpty()) {
            return this;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str2 : keySet) {
            try {
                jSONObject.put(str2, hashMap.get(str2));
            } catch (Exception e) {
                Logger.f("MoEHelper:trackEvent", e);
            }
        }
        trackEvent(str.trim(), jSONObject);
        return this;
    }

    public MoEHelper trackEvent(@NonNull String str, @NonNull Map<String, String> map) {
        Set<String> keySet = map.keySet();
        if (keySet.isEmpty()) {
            return this;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str2 : keySet) {
            try {
                jSONObject.put(str2, map.get(str2));
            } catch (Exception e) {
                Logger.f("MoEHelper:trackEvent", e);
            }
        }
        trackEvent(str, jSONObject);
        return this;
    }

    public MoEHelper trackEvent(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        MoEEventManager.getInstance(this.mContext).trackEvent(str.trim(), new JSONObject());
        return this;
    }

    public MoEHelper setUserAttribute(@NonNull String str, @NonNull String str2) {
        if (str == null) {
            Logger.e("MoEHelper:User attribute value cannot be null");
            return this;
        }
        String str3;
        JSONObject jSONObject;
        if (str2 == null) {
            try {
                str3 = "";
            } catch (UnsupportedEncodingException e) {
                Logger.f("MoEHelper:setUserAttribute", e);
            } catch (Exception e2) {
                Logger.f("MoEHelper:setUserAttribute", e2);
            }
        } else {
            if (MoEHelperConstants.USER_ATTRIBUTE_USER_BDAY.equals(str)) {
                str3 = URLEncoder.encode(str2, "UTF-8");
            }
            jSONObject = new JSONObject();
            jSONObject.put(str.trim(), str2);
            this.mDispatcher.setUserAttribute(jSONObject);
            return this;
        }
        Object str22 = str3;
        jSONObject = new JSONObject();
        try {
            jSONObject.put(str.trim(), str22);
            this.mDispatcher.setUserAttribute(jSONObject);
        } catch (Exception e3) {
            Logger.f("MoEHelper:setUserAttribute", e3);
        }
        return this;
    }

    public MoEHelper setUserAttribute(@NonNull String str, int i) {
        if (str == null) {
            Logger.e("MoEHelper:User attribute value cannot be null");
            return this;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(str.trim(), i);
            this.mDispatcher.setUserAttribute(jSONObject);
        } catch (Exception e) {
            Logger.f("MoEHelper:setUserAttribute", e);
        }
        return this;
    }

    public MoEHelper setUserAttribute(@NonNull String str, boolean z) {
        if (str == null) {
            Logger.e("MoEHelper:User attribute value cannot be null");
            return this;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(str.trim(), z);
            this.mDispatcher.setUserAttribute(jSONObject);
        } catch (Exception e) {
            Logger.f("MoEHelper:setUserAttribute", e);
        }
        return this;
    }

    public MoEHelper setUserAttribute(@NonNull String str, double d) {
        if (str == null) {
            Logger.e("MoEHelper:User attribute value cannot be null");
            return this;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(str.trim(), d);
            this.mDispatcher.setUserAttribute(jSONObject);
        } catch (Exception e) {
            Logger.f("MoEHelper:setUserAttribute", e);
        }
        return this;
    }

    public MoEHelper setUserAttribute(@NonNull String str, float f) {
        if (str == null) {
            Logger.e("MoEHelper:User attribute value cannot be null");
            return this;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(str.trim(), (double) f);
            this.mDispatcher.setUserAttribute(jSONObject);
        } catch (Exception e) {
            Logger.f("MoEHelper:setUserAttribute", e);
        }
        return this;
    }

    public MoEHelper setUserAttribute(@NonNull String str, long j) {
        if (str == null) {
            Logger.e("MoEHelper:User attribute value cannot be null");
            return this;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(str.trim(), j);
            this.mDispatcher.setUserAttribute(jSONObject);
        } catch (Exception e) {
            Logger.f("MoEHelper:setUserAttribute", e);
        }
        return this;
    }

    public MoEHelper setUserAttributeEpochTime(@NonNull String str, long j) {
        if (str == null) {
            Logger.e("MoEHelper:User attribute value cannot be null");
            return this;
        }
        this.mDispatcher.setCustomUserAttribute(new PayloadBuilder().putAttrDateEpoch(str, j).build());
        return this;
    }

    public MoEHelper setUserAttribute(@NonNull HashMap<String, Object> hashMap) {
        if (hashMap.isEmpty()) {
            Logger.e("MoEHelper:User attribute map cannot be null or empty");
            return this;
        }
        Set<String> keySet = hashMap.keySet();
        if (keySet.isEmpty()) {
            return this;
        }
        for (String str : keySet) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    Object obj = hashMap.get(str);
                    if (obj instanceof Date) {
                        setUserAttribute(str.trim(), (Date) obj);
                    } else if (obj instanceof GeoLocation) {
                        setUserAttribute(str.trim(), (GeoLocation) obj);
                    } else if (obj instanceof Location) {
                        setUserAttribute(str.trim(), (Location) obj);
                    } else {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(str.trim(), obj);
                        this.mDispatcher.setUserAttribute(jSONObject);
                    }
                }
            } catch (Exception e) {
                Logger.f("MoEHelper:setUserAttribute", e);
            }
        }
        return this;
    }

    public MoEHelper setUserAttribute(@NonNull Map<String, Object> map) {
        if (map.isEmpty()) {
            Logger.e("MoEHelper:User attribute map cannot be null or empty");
            return this;
        }
        Set<String> keySet = map.keySet();
        if (keySet.isEmpty()) {
            return this;
        }
        for (String str : keySet) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    Object obj = map.get(str);
                    if (obj instanceof Date) {
                        setUserAttribute(str.trim(), (Date) obj);
                    } else if (obj instanceof GeoLocation) {
                        setUserAttribute(str.trim(), (GeoLocation) obj);
                    } else if (obj instanceof Location) {
                        setUserAttribute(str.trim(), (Location) obj);
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(str.trim(), obj);
                    this.mDispatcher.setUserAttribute(jSONObject);
                }
            } catch (Exception e) {
                Logger.f("MoEHelper:setUserAttribute", e);
            }
        }
        return this;
    }

    public MoEHelper setUserAttribute(@NonNull String str, @NonNull Date date) {
        if (str == null) {
            Logger.e("MoEHelper:User attribute value cannot be null");
            return this;
        }
        this.mDispatcher.setCustomUserAttribute(new PayloadBuilder().putAttrDate(str, date).build());
        return this;
    }

    public MoEHelper setUserAttribute(@NonNull String str, @NonNull String str2, String str3) {
        if (str == null) {
            Logger.e("MoEHelper:User attribute value cannot be null");
            return this;
        }
        this.mDispatcher.setCustomUserAttribute(new PayloadBuilder().putAttrDate(str, str2, str3).build());
        return this;
    }

    public MoEHelper setUserAttribute(@NonNull String str, @NonNull Location location) {
        if (str == null) {
            Logger.e("MoEHelper:User attribute value cannot be null");
            return this;
        }
        this.mDispatcher.setCustomUserAttribute(new PayloadBuilder().putAttrLocation(str, location).build());
        return this;
    }

    public MoEHelper setUserAttribute(@NonNull String str, @NonNull GeoLocation geoLocation) {
        if (str == null) {
            Logger.e("MoEHelper:User attribute value cannot be null");
            return this;
        }
        this.mDispatcher.setCustomUserAttribute(new PayloadBuilder().putAttrLocation(str, geoLocation).build());
        return this;
    }

    public void setUserLocation(double d, double d2) {
        setUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_USER_LOCATION, new GeoLocation(d, d2));
    }

    @WorkerThread
    public int getUnreadMessagesCount() {
        return this.mDispatcher.getUnreadMessageCount();
    }

    public void showInAppIfAny(boolean z) {
        this.mDispatcher.checkForInAppMessages(z);
    }

    public void setExistingUser(boolean z) {
        this.mDispatcher.setExistingUser(z, this.mContext);
    }

    @WorkerThread
    public static Cursor getAllMessages(Context context) {
        return MoEDispatcher.getInstance(context).getAllMessages();
    }

    @WorkerThread
    public static void setMessageClicked(Context context, long j) {
        MoEDispatcher.getInstance(context).setInboxMessageClicked(j);
    }

    public void logoutUser() {
        if (this.mContext != null) {
            this.mDispatcher.logoutUser(false);
        }
    }

    public void syncInteractionDataNow() {
        this.mDispatcher.sendInteractionData();
    }

    public void setNotificationPreference(String str, String str2, String str3, String str4) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(MoEHelperConstants.USER_ATTRIBUTE_NOTIFICATION_START, str);
            jSONObject.put(MoEHelperConstants.USER_ATTRIBUTE_NOTIFICATION_END, str2);
            jSONObject.put(MoEHelperConstants.USER_ATTRIBUTE_ASSOCIATED_TIMEZONE, str3);
            jSONObject.put(MoEHelperConstants.USER_ATTRIBUTE_ASSOCIATED_TIME_FORMAT, str4);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(MoEHelperConstants.USER_ATTRIBUTE_NOTIFICATION_PREF, jSONObject.toString());
            this.mDispatcher.setUserAttribute(jSONObject2);
        } catch (Exception e) {
            Logger.f("MoEHelper: setNotificationPreference", e);
        }
    }

    public void optOutOfAdIdCollection(Context context, boolean z) {
        ConfigurationProvider.getInstance(context).optOutOfAdIdCollection(z);
    }

    public void optOutOfLocationTracking(boolean z) {
        ConfigurationProvider.getInstance(this.mContext).optOutOfTrackLocation(z);
    }

    public void optOutOfGeoFences(boolean z) {
        ConfigurationProvider.getInstance(this.mContext).optOutOfSetGeoFence(z);
    }

    public void trackNotificationClickedByTime(long j) {
        this.mDispatcher.trackNotificationClicked(j);
    }

    public void playNotificationSound(boolean z) {
        ConfigurationProvider.getInstance(this.mContext).saveNotificationSoundState(z);
    }

    public void autoIntegrate(Application application) {
        if (VERSION.SDK_INT >= 14) {
            Logger.d("MoEHelper: Auto integration is enabled");
            application.registerActivityLifecycleCallbacks(new MoEActivityLifeCycleCallBacks());
            isAutoIntegration = true;
        }
    }

    public MoEDispatcher getDelegate() {
        if (this.mDispatcher == null) {
            this.mDispatcher = MoEDispatcher.getInstance(this.mContext);
        }
        return this.mDispatcher;
    }

    public void setEmail(@NonNull String str) {
        setUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_USER_EMAIL, str);
    }

    public void setFullName(@NonNull String str) {
        setUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_USER_NAME, str);
    }

    public void setGender(@NonNull String str) {
        setUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_USER_GENDER, str);
    }

    public void setFirstName(@NonNull String str) {
        setUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_USER_FIRST_NAME, str);
    }

    public void setLastName(@NonNull String str) {
        setUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_USER_LAST_NAME, str);
    }

    @Deprecated
    public void setBirthDate(@NonNull String str) {
        setUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_USER_BDAY, str);
    }

    public void setBirthDate(@NonNull Date date) {
        this.mDispatcher.setCustomUserAttribute(new PayloadBuilder().putAttrDate(MoEHelperConstants.USER_ATTRIBUTE_USER_BDAY, date).build());
    }

    public void setUniqueId(@NonNull String str) {
        setUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_UNIQUE_ID, str);
    }

    public void setUniqueId(int i) {
        setUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_UNIQUE_ID, i);
    }

    public void setUniqueId(long j) {
        setUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_UNIQUE_ID, j);
    }

    public void setUniqueId(float f) {
        setUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_UNIQUE_ID, f);
    }

    public void setUniqueId(double d) {
        setUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_UNIQUE_ID, d);
    }

    public void setNumber(String str) {
        if (!TextUtils.isEmpty(str)) {
            setUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_USER_MOBILE, str);
        }
    }

    public void setLogLevel(int i) {
        Logger.setLogLevel(i);
    }

    public void setLogStatus(boolean z) {
        Logger.setLogStatus(z);
    }

    @Deprecated
    public void setDataRedirection(boolean z) {
        ConfigurationProvider.getInstance(this.mContext).setRouteTraffic(z);
    }

    public void optOutOfAndroidIdCollection(@NonNull Context context, boolean z) {
        ConfigurationProvider.getInstance(context).optOutOfAndroidIdCollection(z);
    }

    public void optOutOfOperatorNameCollection(@NonNull Context context, boolean z) {
        ConfigurationProvider.getInstance(context).optOutOfOperatorNameCollection(z);
    }

    public void optOutOfIMEICollection(@NonNull Context context, boolean z) {
        ConfigurationProvider.getInstance(context).optOutOfIMEICollection(z);
    }

    public void optOutOfDeviceAttributeCollection(@NonNull Context context, boolean z) {
        ConfigurationProvider.getInstance(context).optOutOfDeviceAttributesCollection(z);
    }

    public void trackDeviceLocale() {
        this.mDispatcher.trackDeviceLocale();
    }

    public void setAlias(double d) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(MoEHelperConstants.USER_ATTRIBUTE_UNIQUE_ID, d);
            this.mDispatcher.setAlias(jSONObject);
        } catch (Exception e) {
            Logger.f("MoEHelper: setAlias() ", e);
        }
    }

    public void setAlias(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                Logger.e("Updated id cannot be null");
                return;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(MoEHelperConstants.USER_ATTRIBUTE_UNIQUE_ID, str);
            this.mDispatcher.setAlias(jSONObject);
        } catch (Exception e) {
            Logger.f("MoEHelper: setAlias() ", e);
        }
    }

    public void setAlias(long j) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(MoEHelperConstants.USER_ATTRIBUTE_UNIQUE_ID, j);
            this.mDispatcher.setAlias(jSONObject);
        } catch (Exception e) {
            Logger.f("MoEHelper: setAlias() ", e);
        }
    }

    public void setAlias(int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(MoEHelperConstants.USER_ATTRIBUTE_UNIQUE_ID, i);
            this.mDispatcher.setAlias(jSONObject);
        } catch (Exception e) {
            Logger.f("MoEHelper: setAlias() ", e);
        }
    }

    public void trackUserPushPreference(boolean z) {
        setUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_USER_PUSH_PREFERENCE, z);
    }

    public void redirectDataToRegion(int i) {
        ConfigurationProvider.getInstance(this.mContext).setDataRegion(i);
    }

    public void setFlushInterval(long j) {
        if (j < ConfigurationProvider.getInstance(this.mContext).getPeriodicFlushTime()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("MoEHelper:setFlushInterval() cannot set interval less than threshold. Threshold value: ");
            stringBuilder.append(ConfigurationProvider.getInstance(this.mContext).getPeriodicFlushTime());
            Logger.e(stringBuilder.toString());
            return;
        }
        this.flushInterval = j;
    }

    public long getFlushInterval() {
        return this.flushInterval;
    }

    public void setPeriodicFlushState(boolean z) {
        this.isPeriodicSyncEnabled = z;
    }

    public boolean getPeriodicSyncState() {
        return this.isPeriodicSyncEnabled;
    }
}
