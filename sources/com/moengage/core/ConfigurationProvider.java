package com.moengage.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AndroidException;
import com.comscore.utils.Constants;
import com.delight.pushlibrary.R;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.moe.pushlibrary.models.GeoLocation;
import com.moe.pushlibrary.models.UserAttribute;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class ConfigurationProvider {
    private static final String APP_ID = "APP_ID";
    private static final String BAIDU_API_KEY = "api_key";
    private static final String CURRENT_APP_VERSION = "APP_VERSION";
    private static long INAPP_DELAY_DURATION = 900;
    private static final String KEY_DB_VERSION = "key_dbversion";
    private static final String KEY_SET_GEO_FENCE = "key_set_geo_fence";
    private static final String KEY_TRACK_LOCATION = "key_track_location";
    private static final String NOTIFICATION_COLOR = "NOTIFICATION_COLOR";
    private static final String NOTIFICATION_ICON = "NOTIFICATION_ICON";
    private static final int NOTIFICATION_ID = 17987;
    private static final String NOTIFICATION_LARGE_ICON = "NOTIFICATION_LARGE_ICON";
    private static final String NOTIFICATION_TONE = "NOTIFICATION_TONE";
    private static final String NOTIFICATION_TYPE = "NOTIFICATION_TYPE";
    private static final String PREF_HAS_REGISTERED_FOR_VERIFICATION = "has_registered_for_verification";
    private static final String PREF_IS_SEGMENT_INTEGRATION = "is_segment_integration";
    private static final String PREF_KEY_ACTIVITY_SENT_LIST = "activity_sent_list";
    private static final String PREF_KEY_ANDROID_ID_COLLECTION = "pref_key_android_id_collection";
    static final String PREF_KEY_APP_INIT = "APP_INITIALIZED_MOE";
    private static final String PREF_KEY_APP_STATUS = "app_status";
    private static final String PREF_KEY_APP_UUID_MOE = "APP_UUID";
    private static final String PREF_KEY_APP_VERSION = "appVersion";
    private static final String PREF_KEY_BLACKLIST_EVENTS = "black_list_events";
    private static final String PREF_KEY_CAMPAIGN_ID_TTL = "cid_ttl";
    private static final String PREF_KEY_DATA_REGION = "data_region";
    private static final String PREF_KEY_DEVICE_ATTRIBUTE_COLLECTION = "pref_key_device_attribute_collection";
    private static final String PREF_KEY_DEVICE_REGISTERED = "PREF_KEY_DEVICE_REGISTERED";
    private static final String PREF_KEY_ENABLE_DEBUG_LOGS = "enable_logs";
    private static final String PREF_KEY_EVENT_BATCH_COUNT = "event_count";
    private static final String PREF_KEY_FLUSH_EVENTS = "flush_events";
    private static final String PREF_KEY_GAID_COLLECTION = "pref_key_isCollectGAID";
    private static final String PREF_KEY_GEO_STATE = "geo_state";
    private static final String PREF_KEY_IMEI_COLLECTION = "pref_key_imei_collection";
    private static final String PREF_KEY_IMMEDIATE_RETRY_SYNC_COUNT = "retry_count";
    private static final String PREF_KEY_INAPP_LAST_SHOWN_TS = "MOE_LAST_IN_APP_SHOWN_TIME";
    private static final String PREF_KEY_INAPP_LAST_SYNC_TIME = "MOE_LAST_IN_APP_UPDATE_TIME";
    private static final String PREF_KEY_INBOX_STATE = "inbox_state";
    private static final String PREF_KEY_IN_APP_STATUS = "inapp_status";
    static final String PREF_KEY_IN_APP_TIME_DIFF = "inapp_delay_dur";
    private static final String PREF_KEY_LAST_CAMPAIGN_ID = "MOE_LAST_CAMPAIGN_ID";
    private static final String PREF_KEY_LAST_NOTIFICATION_CLICKED = "MOE_LAST_PUSH_CLICK_TIME";
    private static final String PREF_KEY_LAST_NOTIFICATION_ID = "PREF_LAST_NOTIFICATION_ID";
    static final String PREF_KEY_MOE_GAID = "PREF_KEY_MOE_GAID";
    private static final String PREF_KEY_MOE_GEN_UID = "APP_UNIQUE_ID_MOE";
    static final String PREF_KEY_MOE_ISLAT = "PREF_KEY_MOE_ISLAT";
    private static final String PREF_KEY_NOTIFICATION_SOUND = "key_notification_sound";
    private static final String PREF_KEY_OPERATOR_NAME_COLLECTION = "pref_key_operator_name_collection";
    private static final String PREF_KEY_PERIODIC_FLUSH_STATE = "periodic_flush_state";
    private static final String PREF_KEY_PERIODIC_FLUSH_TIME = "periodic_flush_time";
    static final String PREF_KEY_PROPERTY_REG_ID = "registration_id";
    private static final String PREF_KEY_RETRY_SYNC_TIME = "retry_sync_time";
    private static final String PREF_KEY_SEGMENT_ANONYMOUS_ID = "segment_anonymous_id";
    static final String PREF_KEY_SENDER_ID = "SENDER_ID";
    static final String PREF_KEY_SMART_ACTIONS = "smart_actions";
    private static final String PREF_KEY_USER_LOCATION = "key_geoinfo";
    private static final String PREF_LAST_CONFIG_SYNC_TIME = "last_config_sync_time";
    private static final String PREF_LAST_MESSAGE_FETCH_TIME = "last_message_sync";
    private static final String PREF_LOG_ENTRY_ENABLED = "log_entry_enabled";
    private static final String PREF_LOG_ENTRY_KEY = "log_entry_key";
    private static final String PREF_MESSAGE_FETCH_TIME_DELAY = "last_message_sync_time_difference";
    private static final String PREF_NAME = "pref_moe";
    private static final String PREF_NAV_BAR_OPT_OUT = "opt_out_nav_bar";
    private static final String PREF_REG_FAIL_COUNT = "push_fail_count";
    private static final String PREF_SHOULD_ROUTE_TRAFFIC = "route_traffic";
    private static final String PREF_UNITY_SDK_VERSION = "unity_sdk_ver";
    private static final String PREF_VERIFICATION_REGISTRATION_TIME = "verfication_registration_time";
    private static final String SENDER_ID = "SENDER_ID";
    private static final String SKIP_GCM_REGISTRATION = "SKIP_GCM_REGISTRATION";
    private static ConfigurationProvider _INSTANCE;
    private final String KEY_OPTED_OUT_ACTIVITIES = "opted_out_activities";
    private HashMap<String, Object> configMap;
    private boolean disableGCMRegistration = false;
    private final Object gcmTokenLock = new Object();
    private boolean initialized = false;
    private final Object lock = new Object();
    Context mContext;
    private final Object senderIdLock = new Object();
    private List<String> sentScreenNames;
    private final Object userLock = new Object();

    private ConfigurationProvider(Context context) {
        if (context == null) {
            Logger.e("ConfigurationProvider : context passed is null");
            return;
        }
        this.mContext = context;
        init();
    }

    private void init() {
        synchronized (this.lock) {
            if (this.initialized) {
                return;
            }
            this.configMap = new HashMap();
            INAPP_DELAY_DURATION = (long) getSharedPrefs().getInt(PREF_KEY_IN_APP_TIME_DIFF, 900);
            upgradeFromOldSharedPrefIfRequired();
            try {
                this.configMap.put(CURRENT_APP_VERSION, Integer.valueOf(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionCode));
            } catch (NameNotFoundException e) {
                Logger.f("Could not get package name: ", e);
            } catch (Exception e2) {
                Logger.f("Could not get package name: ", e2);
            }
            getSDKConfiguration();
            getOptedOutActivitiesFromManifest();
            getSentScreenNamesInit();
            this.initialized = true;
        }
    }

    private void getSentScreenNamesInit() {
        try {
            this.sentScreenNames = new ArrayList();
            String sentScreenNames = getSentScreenNames();
            if (!TextUtils.isEmpty(sentScreenNames)) {
                this.sentScreenNames.addAll(Arrays.asList(sentScreenNames.split(";")));
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("ConfigurationProvider: getSentScreenNamesInit: Saved screen : ");
                stringBuilder.append(this.sentScreenNames.toString());
                Logger.v(stringBuilder.toString());
            }
        } catch (Exception e) {
            Logger.f("ConfigurationProvider: getSentScreenNamesInit: ", e);
        }
    }

    public static ConfigurationProvider getInstance(Context context) {
        synchronized (ConfigurationProvider.class) {
            if (_INSTANCE == null) {
                _INSTANCE = new ConfigurationProvider(context);
            }
        }
        return _INSTANCE;
    }

    public boolean isGCMRegistrationEnabled() {
        return this.disableGCMRegistration ^ 1;
    }

    private SharedPreferences getSharedPrefs() {
        return this.mContext.getSharedPreferences(PREF_NAME, 0);
    }

    public String getSenderIdIfAny() {
        return (String) this.configMap.get("SENDER_ID");
    }

    public String getAppId() {
        return (String) this.configMap.get(APP_ID);
    }

    public boolean isGCMRegistrationDisabled() {
        return this.disableGCMRegistration;
    }

    public int getNotificationSmallIcon() {
        return ((Integer) this.configMap.get(NOTIFICATION_ICON)).intValue();
    }

    /* Access modifiers changed, original: 0000 */
    public void saveAppDetails(String str, String str2) {
        this.configMap.put(APP_ID, str2);
        if (str != null) {
            this.configMap.put("SENDER_ID", str);
            setSenderId(str);
        }
    }

    public int getNotificationLargeIconIfAny() {
        return ((Integer) this.configMap.get(NOTIFICATION_LARGE_ICON)).intValue();
    }

    public String getNotificationToneIfAny() {
        return (String) this.configMap.get(NOTIFICATION_TONE);
    }

    public int getNotificationColor() {
        Object obj = this.configMap.get(NOTIFICATION_COLOR);
        if (obj == null) {
            return -1;
        }
        return ((Integer) obj).intValue();
    }

    public int getNotificationDisplayType() {
        return ((Integer) this.configMap.get(NOTIFICATION_TYPE)).intValue();
    }

    public String getGCMToken() {
        synchronized (this.gcmTokenLock) {
            String string = getSharedPrefs().getString("registration_id", null);
            if (TextUtils.isEmpty(string)) {
                Logger.v("ConfigurationProvider:getGCMToken: Registration not found.");
                return null;
            }
            return string;
        }
    }

    public void setGCMToken(String str) {
        synchronized (this.gcmTokenLock) {
            SharedPreferences sharedPrefs = getSharedPrefs();
            int appVersion = getAppVersion();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ConfigurationProvider: SettingGCMToken : ");
            stringBuilder.append(str);
            Logger.v(stringBuilder.toString());
            Editor edit = sharedPrefs.edit();
            edit.putString("registration_id", str).apply();
            edit.putInt(PREF_KEY_APP_VERSION, appVersion);
            edit.apply();
            setPushFailureCount(0);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int getAppVersion() {
        return ((Integer) this.configMap.get(CURRENT_APP_VERSION)).intValue();
    }

    /* Access modifiers changed, original: 0000 */
    public int getStoredAppVersion() {
        return getSharedPrefs().getInt(PREF_KEY_APP_VERSION, 0);
    }

    /* Access modifiers changed, original: 0000 */
    public void storeAppVersion(int i) {
        getSharedPrefs().edit().putInt(PREF_KEY_APP_VERSION, i).apply();
    }

    private void getSDKConfiguration() {
        try {
            Bundle bundle = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 128).metaData;
            if (bundle == null) {
                Logger.f("ConfigurationProvider: How can meta be null");
                return;
            }
            String string;
            Object trim;
            StringBuilder stringBuilder;
            if (bundle.containsKey(APP_ID)) {
                string = bundle.getString(APP_ID);
                if (TextUtils.isEmpty(string)) {
                    throw new IllegalStateException("No 'appId' added in manifest application meta");
                }
                this.configMap.put(APP_ID, string);
            }
            int i = 0;
            if (bundle.containsKey("SENDER_ID")) {
                string = bundle.getString("SENDER_ID");
                if (TextUtils.isEmpty(string)) {
                    this.disableGCMRegistration = true;
                    Logger.d("ConfigurationProvider: App does not specify sender ID will not be able to register for GCM");
                } else {
                    trim = string.trim();
                    if (trim.startsWith("id:")) {
                        trim = trim.substring(3);
                    }
                    String storedSenderId = getStoredSenderId();
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("ConfigurationProvider:getSDKConfig: old Sender Id: ");
                    stringBuilder2.append(storedSenderId);
                    Logger.i(stringBuilder2.toString());
                    this.configMap.put("SENDER_ID", trim);
                    setSenderId(trim);
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("ConfigurationProvider:getSDKConfig: Sender Id: ");
                    stringBuilder2.append(trim);
                    Logger.i(stringBuilder2.toString());
                    if (!(TextUtils.isEmpty(storedSenderId) || storedSenderId.equals(trim))) {
                        Logger.e("ConfigurationProvider:getSDKConfig: change in Sender Id");
                        setGCMToken(null);
                        setDeviceRegistered(false);
                    }
                }
            }
            int drawable = bundle.containsKey(NOTIFICATION_ICON) ? getDrawable(bundle.get(NOTIFICATION_ICON)) : 0;
            if (drawable == 0 || !MoEHelperUtils.isValidResourceId(this.mContext, drawable)) {
                drawable = getAppIcon();
            }
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("ConfigurationProvider:getSDKConfig: Notification Icon: ");
            stringBuilder3.append(drawable);
            Logger.v(stringBuilder3.toString());
            this.configMap.put(NOTIFICATION_ICON, Integer.valueOf(drawable));
            if (bundle.containsKey(NOTIFICATION_LARGE_ICON)) {
                i = getDrawable(bundle.get(NOTIFICATION_LARGE_ICON));
            }
            if (i == 0 || !MoEHelperUtils.isValidResourceId(this.mContext, i)) {
                i = getAppIcon();
            }
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append("ConfigurationProvider:getSDKConfig: Notification Large Icon: ");
            stringBuilder4.append(i);
            Logger.v(stringBuilder4.toString());
            this.configMap.put(NOTIFICATION_LARGE_ICON, Integer.valueOf(i));
            if (bundle.containsKey(NOTIFICATION_TONE)) {
                try {
                    string = bundle.getString(NOTIFICATION_TONE);
                    if (!TextUtils.isEmpty(string) && string.contains("res")) {
                        String[] split = string.split("/");
                        string = split[split.length - 1];
                        string = string.contains(".") ? string.substring(string.lastIndexOf("/") + 1, string.lastIndexOf(".")) : string.substring(string.lastIndexOf("/") + 1);
                    }
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ConfigurationProvider:getSDKConfig: Tone is: ");
                    stringBuilder.append(string);
                    Logger.d(stringBuilder.toString());
                    this.configMap.put(NOTIFICATION_TONE, string);
                } catch (Exception e) {
                    Logger.f("ConfigurationProvider:getSDKConfig: tone", e);
                }
            }
            if (bundle.containsKey(NOTIFICATION_COLOR)) {
                try {
                    trim = bundle.get(NOTIFICATION_COLOR);
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ConfigurationProvider:getSDKConfig: Notification Color: ");
                    stringBuilder.append(trim);
                    Logger.d(stringBuilder.toString());
                    if (trim instanceof Integer) {
                        drawable = ((Integer) trim).intValue();
                    } else {
                        string = (String) trim;
                        if (string == null || !string.contains("res/color")) {
                            drawable = this.mContext.getResources().getIdentifier(string, TtmlNode.ATTR_TTS_COLOR, this.mContext.getPackageName());
                        } else {
                            drawable = this.mContext.getResources().getIdentifier(string.substring(string.lastIndexOf("/") + 1), TtmlNode.ATTR_TTS_COLOR, this.mContext.getPackageName());
                        }
                    }
                    if (drawable > 0) {
                        this.configMap.put(NOTIFICATION_COLOR, Integer.valueOf(drawable));
                    }
                } catch (Exception e2) {
                    Logger.f("ConfigurationProvider:getSDKConfig: Color", e2);
                }
            }
            try {
                drawable = this.mContext.getResources().getIdentifier("moe_notification_color", TtmlNode.ATTR_TTS_COLOR, this.mContext.getPackageName());
                if (drawable > 0) {
                    this.configMap.put(NOTIFICATION_COLOR, Integer.valueOf(drawable));
                }
            } catch (Exception e22) {
                Logger.f("ConfigurationProvider:getSDKConfig: Color", e22);
            }
            if (bundle.containsKey(NOTIFICATION_TYPE)) {
                try {
                    this.configMap.put(NOTIFICATION_TYPE, Integer.valueOf(bundle.getInt(NOTIFICATION_TYPE)));
                } catch (ClassCastException e3) {
                    Logger.f("ConfigurationProvider:getSDKConfig: notification type", e3);
                }
            } else {
                this.configMap.put(NOTIFICATION_TYPE, Integer.valueOf(this.mContext.getResources().getInteger(R.integer.notification_type_single)));
            }
            if (bundle.containsKey(BAIDU_API_KEY)) {
                this.configMap.put(BAIDU_API_KEY, bundle.getString(BAIDU_API_KEY));
            }
            if (bundle.containsKey(SKIP_GCM_REGISTRATION)) {
                this.disableGCMRegistration = bundle.getBoolean(SKIP_GCM_REGISTRATION);
            }
            Logger.i("ConfigurationProvider: SDK initialized. MoEngage SDK version: 8403");
        } catch (NameNotFoundException e4) {
            Logger.f("ConfigurationProvider:getSDKConfiguration", e4);
        } catch (Exception e5) {
            Logger.f("ConfigurationProvider:getSDKConfiguration", e5);
        }
    }

    private int getAppIcon() {
        try {
            return this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 0).icon;
        } catch (NameNotFoundException e) {
            Logger.f("ConfigurationProvider:getAppIcon: nameNotFoundException", e);
            return 0;
        } catch (Exception e2) {
            Logger.f("ConfigurationProvider:getAppIcon: Exception", e2);
            return 0;
        }
    }

    public String getLastPushCampaignId() {
        return getSharedPrefs().getString(PREF_KEY_LAST_CAMPAIGN_ID, null);
    }

    public void setLastPushCampaignId(String str) {
        getSharedPrefs().edit().putString(PREF_KEY_LAST_CAMPAIGN_ID, str).apply();
    }

    public String getCurrentUserId() {
        synchronized (this.userLock) {
            CharSequence charSequence = null;
            String string = getSharedPrefs().getString(PREF_KEY_APP_UUID_MOE, null);
            UserAttribute userAttributesForKey = MoEDAO.getInstance(this.mContext).getUserAttributesForKey(PREF_KEY_APP_UUID_MOE);
            if (userAttributesForKey != null) {
                charSequence = userAttributesForKey.userAttributeValue;
            }
            if (TextUtils.isEmpty(string) && TextUtils.isEmpty(charSequence)) {
                Logger.v("ConfigurationProvider: getCurrentUserId() no uniqueId present generating new id");
                string = generateAndSaveUniqueId();
                return string;
            } else if (!TextUtils.isEmpty(charSequence)) {
                Logger.v("ConfigurationProvider: getCurrentUserId() unique id present in db");
                getSharedPrefs().edit().putString(PREF_KEY_APP_UUID_MOE, charSequence).apply();
                return charSequence;
            } else if (TextUtils.isEmpty(string)) {
                Logger.v("ConfigurationProvider: getCurrentUserId() generating user id from fallback condition something went wrong");
                string = generateAndSaveUniqueId();
                return string;
            } else {
                Logger.v("ConfigurationProvider: getCurrentUserId() unique id present in preference");
                return string;
            }
        }
    }

    public int getNotificationId() {
        return getSharedPrefs().getInt(PREF_KEY_LAST_NOTIFICATION_ID, NOTIFICATION_ID);
    }

    public void updateNotificationId(int i) {
        if (i - 17987 >= 100) {
            i = NOTIFICATION_ID;
        }
        getSharedPrefs().edit().putInt(PREF_KEY_LAST_NOTIFICATION_ID, i).apply();
    }

    private String generateAndSaveUniqueId() {
        String generateUUID = generateUUID();
        MoEDAO.getInstance(this.mContext).addOrUpdateUserAttribute(new UserAttribute(PREF_KEY_APP_UUID_MOE, generateUUID));
        getSharedPrefs().edit().putString(PREF_KEY_APP_UUID_MOE, generateUUID).apply();
        return generateUUID;
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public void setDeviceRegistered(boolean z) {
        getSharedPrefs().edit().putBoolean(PREF_KEY_DEVICE_REGISTERED, z).apply();
    }

    public boolean isDeviceRegistered() {
        return getSharedPrefs().getBoolean(PREF_KEY_DEVICE_REGISTERED, false);
    }

    public void upgradeFromOldSharedPrefIfRequired() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (defaultSharedPreferences.contains("SENDER_ID")) {
            Logger.v("Migrating from old shared pref");
            String string = defaultSharedPreferences.getString("registration_id", null);
            if (string != null) {
                sharedPrefs.edit().putString("registration_id", string).apply();
                defaultSharedPreferences.edit().remove("registration_id").apply();
            }
            string = defaultSharedPreferences.getString(PREF_KEY_MOE_GEN_UID, null);
            if (string != null) {
                sharedPrefs.edit().putString(PREF_KEY_MOE_GEN_UID, string).apply();
                defaultSharedPreferences.edit().remove(PREF_KEY_MOE_GEN_UID).apply();
            }
            string = defaultSharedPreferences.getString(PREF_KEY_APP_UUID_MOE, null);
            if (string != null) {
                sharedPrefs.edit().putString(PREF_KEY_APP_UUID_MOE, string).apply();
                defaultSharedPreferences.edit().remove(PREF_KEY_APP_UUID_MOE).apply();
            }
            int i = defaultSharedPreferences.getInt(PREF_KEY_IN_APP_TIME_DIFF, -1);
            if (i != -1) {
                sharedPrefs.edit().putInt(PREF_KEY_IN_APP_TIME_DIFF, i).apply();
                defaultSharedPreferences.edit().remove(PREF_KEY_IN_APP_TIME_DIFF).apply();
            }
            if (defaultSharedPreferences.getBoolean(PREF_KEY_APP_INIT, false)) {
                sharedPrefs.edit().putBoolean(PREF_KEY_APP_INIT, true).apply();
                defaultSharedPreferences.edit().remove(PREF_KEY_APP_INIT).apply();
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void storeGAID(String str) {
        getSharedPrefs().edit().putString(PREF_KEY_MOE_GAID, str).apply();
    }

    public String getStoredGAID() {
        return getSharedPrefs().getString(PREF_KEY_MOE_GAID, "");
    }

    /* Access modifiers changed, original: 0000 */
    public void storeISLAT(int i) {
        getSharedPrefs().edit().putInt(PREF_KEY_MOE_ISLAT, i).apply();
    }

    /* Access modifiers changed, original: 0000 */
    public int getStoredISLAT() {
        return getSharedPrefs().getInt(PREF_KEY_MOE_ISLAT, 2);
    }

    public void setInAppDelayDuration(int i) {
        if (i <= 0) {
            Logger.f("Ignoring the supplied value. Minimum delay should be greater than 0");
            return;
        }
        INAPP_DELAY_DURATION = (long) i;
        getSharedPrefs().edit().putInt(PREF_KEY_IN_APP_TIME_DIFF, i).apply();
    }

    public static long getInAppDelayDuration() {
        return INAPP_DELAY_DURATION * 1000;
    }

    /* Access modifiers changed, original: 0000 */
    public void removeUserConfigurationOnLogout() {
        Editor edit = getSharedPrefs().edit();
        edit.remove(PREF_KEY_DEVICE_REGISTERED);
        edit.remove(PREF_KEY_INAPP_LAST_SYNC_TIME);
        edit.remove(PREF_KEY_LAST_CAMPAIGN_ID);
        edit.remove(PREF_KEY_LAST_NOTIFICATION_CLICKED);
        edit.remove(PREF_KEY_INAPP_LAST_SHOWN_TS);
        edit.remove(PREF_KEY_APP_UUID_MOE);
        edit.remove(PREF_KEY_MOE_GEN_UID);
        edit.apply();
    }

    public void optOutOfAdIdCollection(boolean z) {
        this.configMap.put(PREF_KEY_GAID_COLLECTION, Boolean.valueOf(z));
    }

    public boolean isAdIdCollectionProhibitted() {
        return this.configMap.containsKey(PREF_KEY_GAID_COLLECTION) && this.configMap.get(PREF_KEY_GAID_COLLECTION) == Boolean.TRUE;
    }

    private int getDrawable(Object obj) {
        Throwable e;
        int i = 0;
        int identifier;
        try {
            if (obj instanceof Integer) {
                return ((Integer) obj).intValue();
            }
            String str = (String) obj;
            String substring;
            if (str.contains("res")) {
                substring = str.substring(str.lastIndexOf("/") + 1, str.lastIndexOf("."));
                identifier = str.contains("drawable") ? this.mContext.getResources().getIdentifier(substring, "drawable", this.mContext.getPackageName()) : str.contains("mipmap") ? this.mContext.getResources().getIdentifier(substring, "mipmap", this.mContext.getPackageName()) : getAppIcon();
            } else {
                substring = (String) obj;
                int identifier2 = this.mContext.getResources().getIdentifier(substring, "drawable", this.mContext.getPackageName());
                if (identifier2 == 0) {
                    try {
                        identifier = this.mContext.getResources().getIdentifier(substring, "mipmap", this.mContext.getPackageName());
                    } catch (Exception e2) {
                        e = e2;
                        i = identifier2;
                    }
                } else {
                    identifier = identifier2;
                }
            }
            return identifier;
        } catch (Exception e3) {
            e = e3;
            Logger.f("ConfigurationProvider:getDrawable: ", e);
            identifier = i;
            return identifier;
        }
    }

    public void setNewDBVersion(int i) {
        getSharedPrefs().edit().putInt(KEY_DB_VERSION, i).apply();
    }

    public int getDBVersion() {
        return getSharedPrefs().getInt(KEY_DB_VERSION, -1);
    }

    public void storeLastKnownLocation(GeoLocation geoLocation) {
        Editor edit = getSharedPrefs().edit();
        String str = PREF_KEY_USER_LOCATION;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(geoLocation.latitude);
        stringBuilder.append(",");
        stringBuilder.append(geoLocation.longitude);
        edit.putString(str, stringBuilder.toString()).commit();
    }

    public GeoLocation getLastKnownUserLocation() {
        try {
            String string = getSharedPrefs().getString(PREF_KEY_USER_LOCATION, null);
            if (string != null) {
                String[] split = string.split(",");
                return new GeoLocation(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
            }
        } catch (Exception e) {
            Logger.f("ConfigurationProvider: getLastKnownUserLocation", e);
        }
        return null;
    }

    public long getLastInAppupdate() {
        return getSharedPrefs().getLong(PREF_KEY_INAPP_LAST_SYNC_TIME, 0);
    }

    public void setLastInappUpdateTime(long j) {
        getSharedPrefs().edit().putLong(PREF_KEY_INAPP_LAST_SYNC_TIME, j).apply();
    }

    public String getAppVersionName() {
        if (this.configMap.get("app_version_name") == null) {
            setAppVersionName();
        }
        return (String) this.configMap.get("app_version_name");
    }

    private void setAppVersionName() {
        try {
            this.configMap.put("app_version_name", this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName);
        } catch (NameNotFoundException e) {
            Logger.f("ConfigurationProvider#setAppVersionName : Package Name not found", e);
        }
    }

    public void saveGeoIDList(String str) {
        getSharedPrefs().edit().putString(MoEHelperConstants.PREF_KEY_GEO_FENCE_LIST, str).apply();
    }

    public String getGeoIDList() {
        return getSharedPrefs().getString(MoEHelperConstants.PREF_KEY_GEO_FENCE_LIST, null);
    }

    public void saveNotificationSoundState(boolean z) {
        getSharedPrefs().edit().putBoolean(PREF_KEY_NOTIFICATION_SOUND, z).apply();
    }

    public boolean isNotificationSoundEnabled() {
        return getSharedPrefs().getBoolean(PREF_KEY_NOTIFICATION_SOUND, true);
    }

    public long getLastInAppShownTime() {
        return getSharedPrefs().getLong(PREF_KEY_INAPP_LAST_SHOWN_TS, 0);
    }

    public void setLastInAppShownTime(long j) {
        getSharedPrefs().edit().putLong(PREF_KEY_INAPP_LAST_SHOWN_TS, j).apply();
    }

    public void saveSmartTriggerList(String str) {
        if (str != null) {
            getSharedPrefs().edit().putString(PREF_KEY_SMART_ACTIONS, str).apply();
        }
    }

    /* Access modifiers changed, original: 0000 */
    @Nullable
    public String getSmartTriggerList() {
        return getSharedPrefs().getString(PREF_KEY_SMART_ACTIONS, null);
    }

    public void optOutOfTrackLocation(boolean z) {
        this.configMap.put(KEY_TRACK_LOCATION, Boolean.valueOf(z));
    }

    public void optOutOfSetGeoFence(boolean z) {
        this.configMap.put(KEY_SET_GEO_FENCE, Boolean.valueOf(z));
    }

    public boolean isTrackLocationProhibited() {
        return this.configMap.containsKey(KEY_TRACK_LOCATION) && this.configMap.get(KEY_TRACK_LOCATION) == Boolean.TRUE;
    }

    public boolean isSetGeoFenceProhibited() {
        return this.configMap.containsKey(KEY_SET_GEO_FENCE) && this.configMap.get(KEY_SET_GEO_FENCE) == Boolean.TRUE;
    }

    /* Access modifiers changed, original: 0000 */
    public void setSenderId(String str) {
        synchronized (this.senderIdLock) {
            SharedPreferences sharedPrefs = getSharedPrefs();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ConfigurationProvider: SettingSenderID : ");
            stringBuilder.append(str);
            Logger.d(stringBuilder.toString());
            Editor edit = sharedPrefs.edit();
            edit.putString("SENDER_ID", str);
            edit.apply();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String getStoredSenderId() {
        if (getSharedPrefs().contains("SENDER_ID")) {
            return getSharedPrefs().getString("SENDER_ID", null);
        }
        return null;
    }

    /* Access modifiers changed, original: 0000 */
    public List<String> getOptedOutActivities() {
        return this.configMap.containsKey("opted_out_activities") ? (List) this.configMap.get("opted_out_activities") : null;
    }

    private void setOptedOutActivities(List<String> list) {
        this.configMap.put("opted_out_activities", list);
    }

    private void getOptedOutActivitiesFromManifest() {
        StringBuilder stringBuilder;
        String str = "OPT_OUT_TRACKING";
        ArrayList arrayList = new ArrayList();
        try {
            for (ActivityInfo activityInfo : this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), TsExtractor.TS_STREAM_TYPE_AC3).activities) {
                Bundle bundle = activityInfo.metaData;
                if (bundle != null && bundle.containsKey(str)) {
                    arrayList.add(activityInfo.name);
                }
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("ConfigurationProvider : Opted out activities : ");
            stringBuilder2.append(arrayList.toString());
            Logger.v(stringBuilder2.toString());
            setOptedOutActivities(arrayList);
        } catch (NameNotFoundException e) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("ConfigurationProvider#getOptedOutActivitiesFromManifest ");
            stringBuilder.append(e.getMessage());
            Logger.f(stringBuilder.toString());
        } catch (AndroidException e2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("ConfigurationProvider#getOptedOutActivitiesFromManifest ");
            stringBuilder.append(e2.getMessage());
            Logger.f(stringBuilder.toString());
        } catch (Exception e3) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("ConfigurationProvider#getOptedOutActivitiesFromManifest ");
            stringBuilder.append(e3.getMessage());
            Logger.f(stringBuilder.toString());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int getPushRegistrationFailureCount() {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs == null) {
            return 1;
        }
        int i = sharedPrefs.getInt(PREF_REG_FAIL_COUNT, 0) + 1;
        sharedPrefs.edit().putInt(PREF_REG_FAIL_COUNT, i).apply();
        return i;
    }

    private void setPushFailureCount(int i) {
        getSharedPrefs().edit().putInt(PREF_REG_FAIL_COUNT, i).apply();
    }

    public void setNavBarOptOut(boolean z) {
        getSharedPrefs().edit().putBoolean(PREF_NAV_BAR_OPT_OUT, z).apply();
    }

    public boolean hasOptedOutNavBar() {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs != null) {
            return sharedPrefs.getBoolean(PREF_NAV_BAR_OPT_OUT, false);
        }
        return false;
    }

    public void setUnityVersion(String str) {
        getSharedPrefs().edit().putString(PREF_UNITY_SDK_VERSION, str).apply();
    }

    public String getUnityVersion() {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs != null) {
            return sharedPrefs.getString(PREF_UNITY_SDK_VERSION, null);
        }
        return null;
    }

    /* Access modifiers changed, original: 0000 */
    public void setLogEntryKey(String str) {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs != null) {
            sharedPrefs.edit().putString(PREF_LOG_ENTRY_KEY, str).apply();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String getLogEntryKey() {
        SharedPreferences sharedPrefs = getSharedPrefs();
        return sharedPrefs != null ? sharedPrefs.getString(PREF_LOG_ENTRY_KEY, "031df6f2-907b-46a4-9654-440991e39380") : "031df6f2-907b-46a4-9654-440991e39380";
    }

    /* Access modifiers changed, original: 0000 */
    public void setLogEntryEnable(boolean z) {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs != null) {
            sharedPrefs.edit().putBoolean(PREF_LOG_ENTRY_ENABLED, z).apply();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isLogEntryEnabled() {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs != null) {
            return sharedPrefs.getBoolean(PREF_LOG_ENTRY_ENABLED, false);
        }
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public void setLastConfigSyncTime(long j) {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs != null) {
            sharedPrefs.edit().putLong(PREF_LAST_CONFIG_SYNC_TIME, j).apply();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public long getLastConfigSyncTime() {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs != null) {
            return sharedPrefs.getLong(PREF_LAST_CONFIG_SYNC_TIME, 0);
        }
        return 0;
    }

    public void setRouteTraffic(boolean z) {
        getSharedPrefs().edit().putBoolean(PREF_SHOULD_ROUTE_TRAFFIC, z).apply();
    }

    public boolean shouldRouteTraffic() {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs == null || !sharedPrefs.getBoolean(PREF_SHOULD_ROUTE_TRAFFIC, false)) {
            return false;
        }
        return true;
    }

    public String getBaiduApiKey() {
        return (String) this.configMap.get(BAIDU_API_KEY);
    }

    public void setLastMessageFetchTime(long j) {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs != null) {
            sharedPrefs.edit().putLong(PREF_LAST_MESSAGE_FETCH_TIME, j).commit();
        }
    }

    public long getLastMessageFetchTime() {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs != null) {
            return sharedPrefs.getLong(PREF_LAST_MESSAGE_FETCH_TIME, 0);
        }
        return 0;
    }

    public void setMessageFetchDelayDuration(long j) {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs != null) {
            sharedPrefs.edit().putLong(PREF_MESSAGE_FETCH_TIME_DELAY, j).apply();
        }
    }

    public long getMessageFetchDelayDuration() {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs != null) {
            return sharedPrefs.getLong(PREF_MESSAGE_FETCH_TIME_DELAY, 10800000);
        }
        return 10800000;
    }

    public void setVerificationRegistrationTime(long j) {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs != null) {
            sharedPrefs.edit().putLong(PREF_VERIFICATION_REGISTRATION_TIME, j).apply();
        }
    }

    public long getVerificationRegistrationTime() {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs != null) {
            return sharedPrefs.getLong(PREF_VERIFICATION_REGISTRATION_TIME, 0);
        }
        return 0;
    }

    public void setVerificationRegistration(boolean z) {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs != null) {
            sharedPrefs.edit().putBoolean(PREF_HAS_REGISTERED_FOR_VERIFICATION, z).apply();
        }
    }

    public boolean isDeviceRegisteredForVerification() {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs != null) {
            return sharedPrefs.getBoolean(PREF_HAS_REGISTERED_FOR_VERIFICATION, false);
        }
        return false;
    }

    public void setSegmentEnabledFlag(boolean z) {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs != null) {
            sharedPrefs.edit().putBoolean(PREF_IS_SEGMENT_INTEGRATION, z).apply();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isSegmentEnabled() {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs == null || !sharedPrefs.getBoolean(PREF_IS_SEGMENT_INTEGRATION, false)) {
            return false;
        }
        return true;
    }

    public void optOutOfAndroidIdCollection(boolean z) {
        this.configMap.put(PREF_KEY_ANDROID_ID_COLLECTION, Boolean.valueOf(z));
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isAndroidIdCollectionProhibited() {
        return this.configMap.containsKey(PREF_KEY_ANDROID_ID_COLLECTION) && this.configMap.get(PREF_KEY_ANDROID_ID_COLLECTION) == Boolean.TRUE;
    }

    public void optOutOfOperatorNameCollection(boolean z) {
        this.configMap.put(PREF_KEY_OPERATOR_NAME_COLLECTION, Boolean.valueOf(z));
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isOperatorNameCollectionProhibited() {
        return this.configMap.containsKey(PREF_KEY_OPERATOR_NAME_COLLECTION) && this.configMap.get(PREF_KEY_OPERATOR_NAME_COLLECTION) == Boolean.TRUE;
    }

    public void optOutOfIMEICollection(boolean z) {
        this.configMap.put(PREF_KEY_IMEI_COLLECTION, Boolean.valueOf(z));
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isIMEICollectionProhibited() {
        return this.configMap.containsKey(PREF_KEY_IMEI_COLLECTION) && this.configMap.get(PREF_KEY_IMEI_COLLECTION) == Boolean.TRUE;
    }

    public void optOutOfDeviceAttributesCollection(boolean z) {
        this.configMap.put(PREF_KEY_DEVICE_ATTRIBUTE_COLLECTION, Boolean.valueOf(z));
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isDeviceAttributesCollectionProhibited() {
        return this.configMap.containsKey(PREF_KEY_DEVICE_ATTRIBUTE_COLLECTION) && this.configMap.get(PREF_KEY_DEVICE_ATTRIBUTE_COLLECTION) == Boolean.TRUE;
    }

    private void setSentScreenNames(String str) {
        getSharedPrefs().edit().putString(PREF_KEY_ACTIVITY_SENT_LIST, str).apply();
    }

    private String getSentScreenNames() {
        return getSharedPrefs().getString(PREF_KEY_ACTIVITY_SENT_LIST, null);
    }

    public void addScreenToSentList(String str) {
        this.sentScreenNames.add(str);
    }

    /* Access modifiers changed, original: 0000 */
    @Nullable
    public List<String> getSentScreenList() {
        return this.sentScreenNames;
    }

    /* Access modifiers changed, original: 0000 */
    public void storeSentScreenList() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            int size = this.sentScreenNames.size();
            for (int i = 0; i < size; i++) {
                stringBuilder.append((String) this.sentScreenNames.get(i));
                if (i <= size - 2) {
                    stringBuilder.append(";");
                }
            }
            setSentScreenNames(stringBuilder.toString());
        } catch (Exception e) {
            Logger.f("ConfigurationProvider: storeSentScreenList: ", e);
        }
    }

    public void setDebugLogStatus(boolean z) {
        getSharedPrefs().edit().putBoolean(PREF_KEY_ENABLE_DEBUG_LOGS, z).apply();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isDebugLogEnabled() {
        return getSharedPrefs().getBoolean(PREF_KEY_ENABLE_DEBUG_LOGS, false);
    }

    /* Access modifiers changed, original: 0000 */
    public void saveBlackListEventList(@NonNull String str) {
        getSharedPrefs().edit().putString(PREF_KEY_BLACKLIST_EVENTS, str).apply();
    }

    /* Access modifiers changed, original: 0000 */
    @Nullable
    public String getBlackListEvents() {
        return getSharedPrefs().getString(PREF_KEY_BLACKLIST_EVENTS, null);
    }

    /* Access modifiers changed, original: 0000 */
    public void saveAppState(boolean z) {
        getSharedPrefs().edit().putBoolean(PREF_KEY_APP_STATUS, z).apply();
    }

    public boolean isAppEnabled() {
        return getSharedPrefs().getBoolean(PREF_KEY_APP_STATUS, true);
    }

    /* Access modifiers changed, original: 0000 */
    public void saveInAppState(boolean z) {
        getSharedPrefs().edit().putBoolean(PREF_KEY_IN_APP_STATUS, z).apply();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isInAppEnabled() {
        return getSharedPrefs().getBoolean(PREF_KEY_IN_APP_STATUS, true);
    }

    /* Access modifiers changed, original: 0000 */
    public void saveGeoState(boolean z) {
        getSharedPrefs().edit().putBoolean(PREF_KEY_GEO_STATE, z).apply();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isGeoEnabled() {
        return getSharedPrefs().getBoolean(PREF_KEY_GEO_STATE, true);
    }

    /* Access modifiers changed, original: 0000 */
    public void saveInboxState(boolean z) {
        getSharedPrefs().edit().putBoolean(PREF_KEY_INBOX_STATE, z).apply();
    }

    public boolean isInboxEnabled() {
        return getSharedPrefs().getBoolean(PREF_KEY_INBOX_STATE, false);
    }

    /* Access modifiers changed, original: 0000 */
    public void saveEventBatchCount(int i) {
        getSharedPrefs().edit().putInt(PREF_KEY_EVENT_BATCH_COUNT, i).apply();
    }

    /* Access modifiers changed, original: 0000 */
    public int getEventBatchCount() {
        return getSharedPrefs().getInt(PREF_KEY_EVENT_BATCH_COUNT, 30);
    }

    public void saveSegmentAnonymousId(String str) {
        getSharedPrefs().edit().putString(PREF_KEY_SEGMENT_ANONYMOUS_ID, str).apply();
    }

    /* Access modifiers changed, original: 0000 */
    @Nullable
    public String getSegmentAnonymousId() {
        return getSharedPrefs().getString(PREF_KEY_SEGMENT_ANONYMOUS_ID, null);
    }

    public void setDataRegion(int i) {
        getSharedPrefs().edit().putInt(PREF_KEY_DATA_REGION, i).apply();
    }

    public int getDataRegion() {
        return getSharedPrefs().getInt(PREF_KEY_DATA_REGION, -999);
    }

    /* Access modifiers changed, original: 0000 */
    public void setRetrySyncTime(long j) {
        getSharedPrefs().edit().putLong(PREF_KEY_RETRY_SYNC_TIME, j).apply();
    }

    /* Access modifiers changed, original: 0000 */
    public long getRetrySyncTime() {
        return getSharedPrefs().getLong(PREF_KEY_RETRY_SYNC_TIME, Constants.SESSION_INACTIVE_PERIOD);
    }

    /* Access modifiers changed, original: 0000 */
    public void setImmediateRetryCount(int i) {
        getSharedPrefs().edit().putInt(PREF_KEY_IMMEDIATE_RETRY_SYNC_COUNT, i).apply();
    }

    /* Access modifiers changed, original: 0000 */
    public int getImmediateRetryCount() {
        return getSharedPrefs().getInt(PREF_KEY_IMMEDIATE_RETRY_SYNC_COUNT, 0);
    }

    /* Access modifiers changed, original: 0000 */
    public void saveFlushEventList(@NonNull String str) {
        getSharedPrefs().edit().putString(PREF_KEY_FLUSH_EVENTS, str).apply();
    }

    /* Access modifiers changed, original: 0000 */
    @Nullable
    public String getFlushEvents() {
        return getSharedPrefs().getString(PREF_KEY_FLUSH_EVENTS, null);
    }

    /* Access modifiers changed, original: 0000 */
    public void savePeriodicFlushState(boolean z) {
        getSharedPrefs().edit().putBoolean(PREF_KEY_PERIODIC_FLUSH_STATE, z).apply();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isPeriodicFlushEnabled() {
        return getSharedPrefs().getBoolean(PREF_KEY_PERIODIC_FLUSH_STATE, true);
    }

    /* Access modifiers changed, original: 0000 */
    public void savePeriodicFlushTime(long j) {
        getSharedPrefs().edit().putLong(PREF_KEY_PERIODIC_FLUSH_TIME, j).apply();
    }

    public long getPeriodicFlushTime() {
        return getSharedPrefs().getLong(PREF_KEY_PERIODIC_FLUSH_TIME, 60);
    }

    /* Access modifiers changed, original: 0000 */
    public void saveCampaignIdTTL(long j) {
        getSharedPrefs().edit().putLong(PREF_KEY_CAMPAIGN_ID_TTL, j).apply();
    }

    public long getCampaignIdTTL() {
        return getSharedPrefs().getLong(PREF_KEY_CAMPAIGN_ID_TTL, MoEMessagingConstants.DEFAULT_CAMPAIGN_TTL);
    }
}
