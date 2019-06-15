package com.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.R;
import com.gaana.SplashScreenActivity;
import com.gaana.analytics.AppsFlyer;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Notifications.Notification;
import com.gaana.models.PushNotification;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.GsonBuilder;
import com.helpshift.a;
import com.managers.aa;
import com.managers.ab;
import com.managers.l;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moengage.push.PushManager;
import com.moengage.pushbase.push.MoEngageNotificationUtils;
import com.services.d;
import com.utilities.Util;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

public class GaanaFcmMessagingService extends FirebaseMessagingService {
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Bundle bundle = new Bundle();
        for (Entry entry : remoteMessage.getData().entrySet()) {
            bundle.putString((String) entry.getKey(), (String) entry.getValue());
        }
        a(remoteMessage.getFrom(), bundle);
    }

    public void onNewToken(String str) {
        super.onNewToken(str);
        a.b();
        AppsFlyer.getInstance().updateServerUninstallToken(str);
    }

    private void a(String str, Bundle bundle) {
        if (MoEngageNotificationUtils.isFromMoEngagePlatform(bundle)) {
            if (d.a().b("PREFERENCE_KEY_NOTIFICATION_MUSIC_RECOMMENDATIONS", true, false)) {
                PushManager.getInstance().getPushHandler().handlePushPayload(getApplicationContext(), bundle);
            }
            if (MoEngageNotificationUtils.isSilentPush(bundle)) {
                MoEngage.getInstance().reportSilentPush();
            } else {
                a(bundle);
            }
            return;
        }
        Intent intent = new Intent();
        intent.putExtras(bundle);
        a(str, intent);
    }

    private void a(String str) {
        if (str != null) {
            Notification notification = new Notification();
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("title") && jSONObject.getString("title") != null) {
                    notification.setMessage(jSONObject.getString("title"));
                }
                if (jSONObject.has("alert") && jSONObject.getString("alert") != null) {
                    notification.setMessageDetails(jSONObject.getString("alert"));
                }
                if (jSONObject.has("url") && jSONObject.getString("url") != null) {
                    notification.setAction_url_mobile(jSONObject.getString("url"));
                }
                notification.setType("");
                if (jSONObject.has("artwork") && jSONObject.getString("artwork") != null) {
                    notification.setItemartwork(jSONObject.getString("artwork"));
                }
                notification.setTimestamp(System.currentTimeMillis());
                aa.a().a(notification, true);
            } catch (JSONException unused) {
            }
        }
    }

    private void a(Bundle bundle) {
        Notification notification = new Notification();
        notification.setNotificationSrc("moengage");
        notification.setNotificationType(null);
        if (bundle.containsKey("artwork") && !TextUtils.isEmpty(bundle.getString("artwork"))) {
            notification.setItemartwork(bundle.getString("artwork"));
        }
        if (bundle.containsKey(MoEHelperConstants.GCM_EXTRA_TITLE)) {
            notification.setMessage(bundle.getString(MoEHelperConstants.GCM_EXTRA_TITLE));
        }
        String str = "";
        if (bundle.containsKey(MoEHelperConstants.GCM_EXTRA_CONTENT) && !TextUtils.isEmpty(bundle.getString(MoEHelperConstants.GCM_EXTRA_CONTENT))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(bundle.getString(MoEHelperConstants.GCM_EXTRA_CONTENT));
            str = stringBuilder.toString();
            if (bundle.containsKey("gcm_subtext") && !TextUtils.isEmpty(bundle.getString("gcm_subtext"))) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(" ");
                stringBuilder.append(bundle.getString("gcm_subtext"));
                str = stringBuilder.toString();
            }
            notification.setMessageDetails(str);
        }
        if (bundle.containsKey(MoEHelperConstants.GCM_EXTRA_WEB_URL) && !TextUtils.isEmpty(bundle.getString(MoEHelperConstants.GCM_EXTRA_WEB_URL))) {
            notification.setAction_url_mobile(bundle.getString(MoEHelperConstants.GCM_EXTRA_WEB_URL));
        }
        notification.setType("");
        notification.setTimestamp(System.currentTimeMillis());
        if (!bundle.containsKey("offer") || TextUtils.isEmpty(bundle.getString("offer"))) {
            aa.a().a(notification, true);
        } else {
            ab.a().a(notification, true);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(String str, Intent intent) {
        Context context = GaanaApplication.getContext();
        if (intent.getExtras().getString("origin") == null || !intent.getExtras().getString("origin").equals("helpshift")) {
            a(context, intent);
            a(intent.getExtras().getString("data"));
            return;
        }
        a.a(context, intent);
    }

    private boolean a(Context context, Intent intent) {
        try {
            Bundle extras = intent.getExtras();
            if (extras != null && extras.containsKey("data")) {
                String string = extras.getString("data");
                if (string != null) {
                    PushNotification pushNotification = (PushNotification) new GsonBuilder().excludeFieldsWithModifiers(8, 4).create().fromJson(string, PushNotification.class);
                    if (pushNotification != null) {
                        Intent intent2 = new Intent(context, SplashScreenActivity.class);
                        intent2.setAction("android.intent.action.VIEW");
                        intent2.putExtra("data", string);
                        int nextInt = new Random().nextInt(1000);
                        PendingIntent activity = PendingIntent.getActivity(context, nextInt, intent2, 1073741824);
                        String alert = pushNotification.getAlert();
                        String url = pushNotification.getUrl();
                        if (!a(alert, url)) {
                            return true;
                        }
                        long currentTimeMillis = System.currentTimeMillis();
                        a(alert, url, currentTimeMillis);
                        if (!TextUtils.isEmpty(url) && url.contains("view/downloadsync") && b(url)) {
                            return true;
                        }
                        int i = R.string.app_name;
                        if (Constants.l) {
                            i = R.string.app_name_mmx;
                        }
                        CharSequence string2 = context.getString(i);
                        if (!TextUtils.isEmpty(pushNotification.getTitle())) {
                            string2 = pushNotification.getTitle();
                        }
                        Builder builder = new Builder(context, "com.gaana.other");
                        BigTextStyle bigText = new BigTextStyle().bigText(alert);
                        builder = builder.setContentTitle(string2).setStyle(bigText).setSmallIcon(Util.w()).setContentText(alert).setContentIntent(activity).setWhen(currentTimeMillis);
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("android.resource://");
                        stringBuilder.append(getPackageName());
                        stringBuilder.append("/");
                        stringBuilder.append(R.raw.bajnachahiyegaana);
                        builder = builder.setSound(Uri.parse(stringBuilder.toString()));
                        if (VERSION.SDK_INT >= 21) {
                            builder.setColor(GaanaApplication.getContext().getResources().getColor(R.color.notification_color_filler));
                        }
                        android.app.Notification build = builder.build();
                        build.defaults = 2 | build.defaults;
                        build.flags |= 16;
                        ((NotificationManager) context.getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION)).notify(nextInt, build);
                    }
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private boolean b(String str) {
        l.a().b(str);
        return true;
    }

    private boolean a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(str2);
        str = stringBuilder.toString();
        str2 = d.a().b("PREFERENCE_KEY_LAST_PUSH_NOTIFICATION_RECEIVED_TEXT", null, false);
        if (str != null && str.equalsIgnoreCase(str2)) {
            if (!a(d.a().b(System.currentTimeMillis(), "PREFERENCE_KEY_LAST_PUSH_NOTIFICATION_RECEIVED_TIME", false))) {
                return false;
            }
        }
        return true;
    }

    private void a(String str, String str2, long j) {
        d.a().a(j, "PREFERENCE_KEY_LAST_PUSH_NOTIFICATION_RECEIVED_TIME", false);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(str2);
        d.a().a("PREFERENCE_KEY_LAST_PUSH_NOTIFICATION_RECEIVED_TEXT", stringBuilder.toString(), false);
    }

    private boolean a(long j) {
        return ((int) ((new Date().getTime() - j) / 1000)) > 3600;
    }
}
