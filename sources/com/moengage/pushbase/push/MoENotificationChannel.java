package com.moengage.pushbase.push;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moengage.core.Logger;
import com.moengage.pushbase.PushConstants;
import org.json.JSONObject;

@TargetApi(26)
class MoENotificationChannel {
    private static MoENotificationChannel _INSTANCE;

    private boolean isImportanceValid(int i) {
        return i == 3 || i == 4 || i == 2 || i == 5 || i == 1 || i == 0 || i == -1000;
    }

    private boolean isVisibilityValid(int i) {
        return i == 0 || i == 1 || i == -1;
    }

    private MoENotificationChannel() {
    }

    public static MoENotificationChannel getInstance() {
        if (_INSTANCE == null) {
            _INSTANCE = new MoENotificationChannel();
        }
        return _INSTANCE;
    }

    @Nullable
    private String createNotificationChannel(Context context, Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        try {
            if (bundle.containsKey(PushConstants.PUSH_PAYLOAD_ATTR_CREATE_NOTIFICATION_CHANNEL)) {
                String string = bundle.getString(PushConstants.PUSH_PAYLOAD_ATTR_CREATE_NOTIFICATION_CHANNEL);
                if (!TextUtils.isEmpty(string)) {
                    JSONObject jSONObject = new JSONObject(string);
                    CharSequence string2 = jSONObject.has("name") ? jSONObject.getString("name") : null;
                    CharSequence string3 = jSONObject.has(PushConstants.NOTIFICATION_CHANNEL_ATTR_CHANNEL_ID) ? jSONObject.getString(PushConstants.NOTIFICATION_CHANNEL_ATTR_CHANNEL_ID) : null;
                    int i = jSONObject.has(PushConstants.NOTIFICATION_CHANNEL_ATTR_IMPORTANCE) ? jSONObject.getInt(PushConstants.NOTIFICATION_CHANNEL_ATTR_IMPORTANCE) : -999;
                    if (!(TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3))) {
                        if (i != -999) {
                            if (isImportanceValid(i)) {
                                NotificationChannel notificationChannel = new NotificationChannel(string3, string2, i);
                                if (jSONObject.has(PushConstants.NOTIFICATION_CHANNEL_ATTR_ENABLE_LIGHTS)) {
                                    notificationChannel.enableLights(jSONObject.getBoolean(PushConstants.NOTIFICATION_CHANNEL_ATTR_ENABLE_LIGHTS));
                                }
                                if (jSONObject.has(PushConstants.NOTIFICATION_CHANNEL_ATTR_ENABLE_VIBRATION)) {
                                    notificationChannel.enableVibration(jSONObject.getBoolean(PushConstants.NOTIFICATION_CHANNEL_ATTR_ENABLE_VIBRATION));
                                }
                                if (jSONObject.has(PushConstants.NOTIFICATION_CHANNEL_ATTR_BYPASS_DND)) {
                                    notificationChannel.setBypassDnd(jSONObject.getBoolean(PushConstants.NOTIFICATION_CHANNEL_ATTR_BYPASS_DND));
                                }
                                if (jSONObject.has("description")) {
                                    notificationChannel.setDescription(jSONObject.getString("description"));
                                }
                                if (jSONObject.has(PushConstants.NOTIFICATION_CHANNEL_ATTR_GROUP)) {
                                    notificationChannel.setGroup(jSONObject.getString(PushConstants.NOTIFICATION_CHANNEL_ATTR_GROUP));
                                }
                                if (jSONObject.has(PushConstants.NOTIFICATION_CHANNEL_ATTR_LIGHT_COLOR)) {
                                    notificationChannel.setLightColor(Color.parseColor(jSONObject.getString(PushConstants.NOTIFICATION_CHANNEL_ATTR_LIGHT_COLOR)));
                                }
                                if (jSONObject.has(PushConstants.NOTIFICATION_CHANNEL_ATTR_VISIBILITY)) {
                                    int i2 = jSONObject.getInt(PushConstants.NOTIFICATION_CHANNEL_ATTR_VISIBILITY);
                                    if (isVisibilityValid(i2)) {
                                        notificationChannel.setLockscreenVisibility(i2);
                                    }
                                }
                                if (jSONObject.has(PushConstants.NOTIFICATION_CHANNEL_ATTR_SHOW_BADGE)) {
                                    notificationChannel.setShowBadge(jSONObject.getBoolean(PushConstants.NOTIFICATION_CHANNEL_ATTR_SHOW_BADGE));
                                }
                                jSONObject.has(PushConstants.NOTIFICATION_CHANNEL_ATTR_SOUND);
                                jSONObject.has(PushConstants.NOTIFICATION_CHANNEL_ATTR_VIBRATION_PATTERN);
                                ((NotificationManager) context.getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION)).createNotificationChannel(notificationChannel);
                                return notificationChannel.getId();
                            }
                            Logger.e("MoENotificationChannel:createNotificationChannel : importance not valid cannot create notification channel");
                            return null;
                        }
                    }
                    Logger.e("Either name or channel id or importance is missing cannot create notification channel");
                    return null;
                }
            }
        } catch (Exception e) {
            Logger.f("MoEngageNotificationUtils: createNotificationChannel: ", e);
        }
        return null;
    }

    /* Access modifiers changed, original: 0000 */
    public String getNotificationChannelId(Context context, Bundle bundle) {
        return bundle.containsKey(PushConstants.PUSH_PAYLOAD_ATTR_NOTIFICATION_CHANNEL_ID) ? bundle.getString(PushConstants.PUSH_PAYLOAD_ATTR_NOTIFICATION_CHANNEL_ID) : null;
    }

    /* Access modifiers changed, original: 0000 */
    public void createFallbackNotificationChanelIfRequired(Context context) {
        if (VERSION.SDK_INT >= 26 && !isChannelExists(context, PushConstants.NOTIFICATION_FALLBACK_CHANNEL_ID)) {
            Logger.v("MoENotificationChannel: createFallbackNotificationChanelIfRequired() creating default channel");
            ((NotificationManager) context.getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION)).createNotificationChannel(new NotificationChannel(PushConstants.NOTIFICATION_FALLBACK_CHANNEL_ID, PushConstants.NOTIFICATION_FALLBACK_CHANNEL_NAME, 3));
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isChannelExists(Context context, String str) {
        boolean z = true;
        if (VERSION.SDK_INT < 26) {
            return true;
        }
        if (((NotificationManager) context.getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION)).getNotificationChannel(str) == null) {
            z = false;
        }
        return z;
    }
}
