package com.moengage.pushbase.push;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.google.android.exoplayer2.C;
import com.moe.pushlibrary.MoEHelper;
import com.moe.pushlibrary.providers.MoEDataContract.BaseColumns;
import com.moe.pushlibrary.providers.MoEDataContract.CampaignListEntity;
import com.moe.pushlibrary.providers.MoEDataContract.MessageEntity;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.core.ConfigurationProvider;
import com.moengage.core.Logger;
import com.moengage.core.MoEUtils;
import com.moengage.inapp.InAppMessage;
import com.moengage.location.GeoManager;
import com.moengage.location.GeoManager.LocationHandler;
import com.moengage.pushbase.PushActionMapperConstants;
import com.moengage.pushbase.PushConstants;
import com.moengage.pushbase.R;
import com.moengage.pushbase.activities.PushTracker;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class PushMessageListener {
    private boolean isNotificationRequiredCalled = false;
    private final Object lock = new Object();

    public int getIntentFlags(Bundle bundle) {
        return 805306368;
    }

    public void onNonMoEngageMessageReceived(Context context, Bundle bundle) {
    }

    public void onNotificationNotRequired(Context context, Bundle bundle) {
    }

    /* Access modifiers changed, original: protected */
    public void onPostNotificationReceived(Context context, Bundle bundle) {
    }

    /* JADX WARNING: Missing block: B:58:0x010c, code skipped:
            return;
     */
    public final void onMessagereceived(android.content.Context r11, android.os.Bundle r12) {
        /*
        r10 = this;
        r0 = r10.lock;
        monitor-enter(r0);
        if (r12 == 0) goto L_0x010b;
    L_0x0005:
        if (r11 != 0) goto L_0x0009;
    L_0x0007:
        goto L_0x010b;
    L_0x0009:
        com.moe.pushlibrary.utils.MoEHelperUtils.dumpIntentExtras(r12);	 Catch:{ Exception -> 0x0103 }
        r1 = com.moengage.pushbase.push.MoEngageNotificationUtils.isFromMoEngagePlatform(r12);	 Catch:{ Exception -> 0x0103 }
        if (r1 == 0) goto L_0x00ff;
    L_0x0012:
        r10.logNotificationState(r11);	 Catch:{ Exception -> 0x0103 }
        r1 = com.moengage.pushbase.push.MoEngageNotificationUtils.hasNotificationExpired(r12);	 Catch:{ Exception -> 0x0103 }
        if (r1 == 0) goto L_0x0025;
    L_0x001b:
        r1 = "Campaign expired, will not be shown";
        com.moengage.core.Logger.i(r1);	 Catch:{ Exception -> 0x0103 }
        r10.logCampaignImpression(r11, r12);	 Catch:{ Exception -> 0x0103 }
        goto L_0x0109;
    L_0x0025:
        r1 = com.moengage.core.ConfigurationProvider.getInstance(r11);	 Catch:{ Exception -> 0x0103 }
        r2 = com.moengage.pushbase.push.MoEngageNotificationUtils.getCampaignIdIfAny(r12);	 Catch:{ Exception -> 0x0103 }
        r3 = r10.isDuplicateCampaign(r11, r2, r1);	 Catch:{ Exception -> 0x0103 }
        if (r3 == 0) goto L_0x003b;
    L_0x0033:
        r3 = com.moengage.pushbase.push.MoEngageNotificationUtils.isReNotification(r12);	 Catch:{ Exception -> 0x0103 }
        if (r3 != 0) goto L_0x003b;
    L_0x0039:
        monitor-exit(r0);	 Catch:{ all -> 0x010d }
        return;
    L_0x003b:
        r10.saveCampaignId(r11, r1, r2);	 Catch:{ Exception -> 0x0103 }
        r2 = "MOE_MSG_RECEIVED_TIME";
        r3 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0103 }
        r12.putLong(r2, r3);	 Catch:{ Exception -> 0x0103 }
        r2 = r10.isNotificationRequired(r11, r12);	 Catch:{ Exception -> 0x0103 }
        r3 = 0;
        if (r2 == 0) goto L_0x00ce;
    L_0x004e:
        r2 = "PushMessageListener: onMessageReceived Will try to show notification";
        com.moengage.core.Logger.i(r2);	 Catch:{ Exception -> 0x0103 }
        r2 = com.moengage.pushbase.push.MoEPushCallBacks.getInstance();	 Catch:{ Exception -> 0x0103 }
        r2.onPushReceived(r12);	 Catch:{ Exception -> 0x0103 }
        r10.enableLogsIfRequired(r11, r12);	 Catch:{ Exception -> 0x0103 }
        r2 = r10.onCreateNotification(r11, r12, r1);	 Catch:{ Exception -> 0x0103 }
        r7 = r10.getRedirectIntent(r11);	 Catch:{ Exception -> 0x0103 }
        r4 = com.moengage.pushbase.push.MoEngageNotificationUtils.getMoEngageExtras(r12);	 Catch:{ Exception -> 0x0103 }
        r12.putAll(r4);	 Catch:{ Exception -> 0x0103 }
        r7.putExtras(r12);	 Catch:{ Exception -> 0x0103 }
        r4 = com.moengage.pushbase.push.MoEngageNotificationUtils.getNotificationDisplayType(r12, r11);	 Catch:{ Exception -> 0x0103 }
        r5 = 1;
        if (r4 != r5) goto L_0x0077;
    L_0x0076:
        goto L_0x0078;
    L_0x0077:
        r5 = r3;
    L_0x0078:
        r4 = com.moengage.pushbase.push.MoEngageNotificationUtils.isCarouselNotification(r12);	 Catch:{ Exception -> 0x0103 }
        if (r4 == 0) goto L_0x008a;
    L_0x007e:
        r4 = com.moengage.pushbase.push.MoEngageNotificationUtils.getNotificationIdIfAny(r12);	 Catch:{ Exception -> 0x0103 }
        r6 = -1;
        if (r4 == r6) goto L_0x008a;
    L_0x0085:
        r1 = com.moengage.pushbase.push.MoEngageNotificationUtils.getNotificationIdIfAny(r12);	 Catch:{ Exception -> 0x0103 }
        goto L_0x008e;
    L_0x008a:
        r1 = r10.getNotificationId(r11, r1, r5);	 Catch:{ Exception -> 0x0103 }
    L_0x008e:
        com.moengage.pushbase.push.MoEngageNotificationUtils.setNotificationId(r7, r1);	 Catch:{ Exception -> 0x0103 }
        com.moengage.pushbase.push.MoEngageNotificationUtils.setNotificationAutoDismissIfAny(r11, r1, r12);	 Catch:{ Exception -> 0x0103 }
        com.moengage.pushbase.push.MoEngageNotificationUtils.setNotificationClearedCallback(r11, r2, r1, r12);	 Catch:{ Exception -> 0x0103 }
        r4 = com.moengage.pushbase.push.MoEngageNotificationUtils.getContentIntent(r11, r7, r5, r1);	 Catch:{ Exception -> 0x0103 }
        com.moengage.pushbase.push.MoEngageNotificationUtils.setActionButtonIfPresentAndSupported(r11, r12, r2, r7, r1);	 Catch:{ Exception -> 0x0103 }
        r2.setContentIntent(r4);	 Catch:{ Exception -> 0x0103 }
        r2 = r2.build();	 Catch:{ Exception -> 0x0103 }
        r10.customizeNotification(r2, r11, r12);	 Catch:{ Exception -> 0x0103 }
        r4 = com.moengage.pushbase.push.MoEngageNotificationUtils.isCarouselNotification(r12);	 Catch:{ Exception -> 0x0103 }
        if (r4 == 0) goto L_0x00b6;
    L_0x00ae:
        r4 = r10;
        r5 = r2;
        r6 = r11;
        r8 = r12;
        r9 = r1;
        r4.createCarouselNotification(r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0103 }
    L_0x00b6:
        r4 = r10.isNotificationRequiredCalled;	 Catch:{ Exception -> 0x0103 }
        if (r4 != 0) goto L_0x00c2;
    L_0x00ba:
        r11 = new java.lang.IllegalStateException;	 Catch:{ Exception -> 0x0103 }
        r12 = "super.isNotificationRequired(context, extras) not called.";
        r11.<init>(r12);	 Catch:{ Exception -> 0x0103 }
        throw r11;	 Catch:{ Exception -> 0x0103 }
    L_0x00c2:
        r4 = "notification";
        r4 = r11.getSystemService(r4);	 Catch:{ Exception -> 0x0103 }
        r4 = (android.app.NotificationManager) r4;	 Catch:{ Exception -> 0x0103 }
        r4.notify(r1, r2);	 Catch:{ Exception -> 0x0103 }
        goto L_0x00d7;
    L_0x00ce:
        r1 = com.moengage.pushbase.push.MoEngageNotificationUtils.isSilentPush(r12);	 Catch:{ Exception -> 0x0103 }
        if (r1 != 0) goto L_0x00d7;
    L_0x00d4:
        r10.onNotificationNotRequired(r11, r12);	 Catch:{ Exception -> 0x0103 }
    L_0x00d7:
        r10.isNotificationRequiredCalled = r3;	 Catch:{ Exception -> 0x0103 }
        r1 = com.moengage.pushbase.push.MoEngageNotificationUtils.isSilentPush(r12);	 Catch:{ Exception -> 0x0103 }
        if (r1 != 0) goto L_0x0109;
    L_0x00df:
        r1 = com.moengage.pushbase.push.MoEngageNotificationUtils.getCampaignIdIfAny(r12);	 Catch:{ Exception -> 0x0103 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Exception -> 0x0103 }
        if (r1 != 0) goto L_0x0109;
    L_0x00e9:
        r1 = com.moengage.pushbase.push.MoEngageNotificationUtils.isReNotification(r12);	 Catch:{ Exception -> 0x0103 }
        if (r1 != 0) goto L_0x0109;
    L_0x00ef:
        r1 = com.moengage.pushbase.push.MoEngageNotificationUtils.isSkipNotificationCenter(r12);	 Catch:{ Exception -> 0x0103 }
        if (r1 != 0) goto L_0x00f8;
    L_0x00f5:
        r10.addToMoEngageInbox(r11, r12);	 Catch:{ Exception -> 0x0103 }
    L_0x00f8:
        r10.logCampaignImpression(r11, r12);	 Catch:{ Exception -> 0x0103 }
        r10.onPostNotificationReceived(r11, r12);	 Catch:{ Exception -> 0x0103 }
        goto L_0x0109;
    L_0x00ff:
        r10.onNonMoEngageMessageReceived(r11, r12);	 Catch:{ Exception -> 0x0103 }
        goto L_0x0109;
    L_0x0103:
        r11 = move-exception;
        r12 = "PushMessageListener:onMessageReceived";
        com.moengage.core.Logger.f(r12, r11);	 Catch:{ all -> 0x010d }
    L_0x0109:
        monitor-exit(r0);	 Catch:{ all -> 0x010d }
        return;
    L_0x010b:
        monitor-exit(r0);	 Catch:{ all -> 0x010d }
        return;
    L_0x010d:
        r11 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x010d }
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moengage.pushbase.push.PushMessageListener.onMessagereceived(android.content.Context, android.os.Bundle):void");
    }

    private void enableLogsIfRequired(Context context, Bundle bundle) {
        if (bundle != null && bundle.containsKey(PushConstants.ENABLE_DEBUG_LOGS)) {
            String string = bundle.getString(PushConstants.ENABLE_DEBUG_LOGS);
            if (!TextUtils.isEmpty(string)) {
                boolean z = true;
                int hashCode = string.hashCode();
                boolean z2 = false;
                if (hashCode != 3569038) {
                    if (hashCode == 97196323 && string.equals(InternalLogger.EVENT_PARAM_EXTRAS_FALSE)) {
                        z = true;
                    }
                } else if (string.equals("true")) {
                    z = false;
                }
                switch (z) {
                    case false:
                        z2 = true;
                        break;
                }
                ConfigurationProvider.getInstance(context).setDebugLogStatus(z2);
                Logger.setLogLevel(5);
                Logger.enableDebugLog(context);
            }
        }
    }

    private void saveCampaignId(Context context, ConfigurationProvider configurationProvider, String str) {
        try {
            configurationProvider.setLastPushCampaignId(str);
            ContentValues contentValues = new ContentValues();
            contentValues.put("campaign_id", str);
            contentValues.put("ttl", Long.valueOf(System.currentTimeMillis() + configurationProvider.getCampaignIdTTL()));
            context.getContentResolver().insert(CampaignListEntity.getContentUri(context), contentValues);
        } catch (Exception e) {
            Logger.f("PushMessageListener saveCampaignId() ", e);
        }
    }

    public boolean isNotificationRequired(Context context, Bundle bundle) {
        this.isNotificationRequiredCalled = true;
        if (MoEngageNotificationUtils.isSilentPush(bundle)) {
            if ((MoEHelperUtils.hasPermission(context, "android.permission.ACCESS_FINE_LOCATION") || MoEHelperUtils.hasPermission(context, "android.permission.ACCESS_COARSE_LOCATION")) && shouldMakeGeoFenceCall(bundle)) {
                LocationHandler handler = GeoManager.getInstance().getHandler(context);
                if (handler != null) {
                    handler.updateFenceAndLocation(context);
                }
            }
            return false;
        } else if (TextUtils.isEmpty(MoEngageNotificationUtils.getCampaignIdIfAny(bundle))) {
            return false;
        } else {
            return MoEngageNotificationUtils.isPushToInbox(bundle) ^ 1;
        }
    }

    public Builder onCreateNotification(Context context, Bundle bundle, ConfigurationProvider configurationProvider) {
        String notificationChannelId = MoENotificationChannel.getInstance().getNotificationChannelId(context, bundle);
        if (TextUtils.isEmpty(notificationChannelId) || !MoENotificationChannel.getInstance().isChannelExists(context, notificationChannelId)) {
            Logger.e("PushMessageListener: onCreateNotification() Did not find channel id setting using Fallback channel");
            MoENotificationChannel.getInstance().createFallbackNotificationChanelIfRequired(context);
            notificationChannelId = PushConstants.NOTIFICATION_FALLBACK_CHANNEL_ID;
        }
        Builder builder = new Builder(context, notificationChannelId);
        builder.setAutoCancel(MoEngageNotificationUtils.isAutoCancelEnabled(bundle));
        MoEngageNotificationUtils.setSubTextIfAny(bundle, builder);
        MoEngageNotificationUtils.setContentIfPresent(bundle, builder);
        MoEngageNotificationUtils.setTitleIfPresent(bundle, builder);
        MoEngageNotificationUtils.setCategoryIfPresentAndSupported(bundle, builder);
        MoEngageNotificationUtils.setSmallIcon(context, builder, configurationProvider);
        MoEngageNotificationUtils.setColorOrLargeIconIfPresentAndSupported(context, bundle, builder, configurationProvider);
        MoEngageNotificationUtils.setNotificationPriorityIfPresentAndSupported(bundle, builder);
        MoEngageNotificationUtils.setTickerTextIfPresent(bundle, builder);
        setNotificationSound(context, bundle, builder, configurationProvider);
        MoEngageNotificationUtils.setVisibilityIfPresentAndSupported(bundle, builder);
        MoEngageNotificationUtils.setNotificationStyle(context, bundle, builder);
        return builder;
    }

    public Intent getRedirectIntent(Context context) {
        Intent intent = new Intent(context, PushTracker.class);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(System.currentTimeMillis());
        intent.setAction(stringBuilder.toString());
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        return intent;
    }

    public final void logCampaignImpression(Context context, Bundle bundle) {
        MoEngageNotificationUtils.logNotificationImpression(context, bundle);
    }

    public final int getNotificationId(Context context, ConfigurationProvider configurationProvider, boolean z) {
        return MoEngageNotificationUtils.getNotificationId(context, configurationProvider, z);
    }

    public final void addToMoEngageInbox(Context context, Bundle bundle) {
        Logger.v("PushMessagingListener: addToMoEngageInbox: ");
        String convertBundletoJSONString = MoEngageNotificationUtils.convertBundletoJSONString(bundle);
        ContentValues contentValues = new ContentValues();
        if (convertBundletoJSONString != null) {
            contentValues.put("msg", convertBundletoJSONString);
        }
        long j = bundle.getLong(MoEHelperConstants.EXTRA_MSG_RECEIVED_TIME);
        contentValues.put(BaseColumns.GTIME, Long.valueOf(j));
        contentValues.put(MessageColumns.MSG_CLICKED, Integer.valueOf(0));
        contentValues.put(MessageColumns.MSG_TTL, Long.valueOf(MoEngageNotificationUtils.getNotificationTTL(bundle, j)));
        String messageTagsIfAny = MoEngageNotificationUtils.getMessageTagsIfAny(bundle);
        if (TextUtils.isEmpty(messageTagsIfAny)) {
            contentValues.put(MessageColumns.MSG_TAG, InAppMessage.INAPP_TYPE_GENERAL);
        } else {
            contentValues.put(MessageColumns.MSG_TAG, messageTagsIfAny);
        }
        Uri insert = context.getContentResolver().insert(MessageEntity.getContentUri(context), contentValues);
        if (insert != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("PushMessagingListener: added new record with entry: ");
            stringBuilder.append(insert);
            Logger.v(stringBuilder.toString());
            return;
        }
        Logger.f("PushMessagingListener: FAILED to add new record with entry: ");
    }

    public void setNotificationSound(Context context, Bundle bundle, Builder builder, ConfigurationProvider configurationProvider) {
        MoEngageNotificationUtils.setSoundIfPresentAndSupported(context, bundle, builder, configurationProvider);
    }

    public void customizeNotification(Notification notification, Context context, Bundle bundle) {
        int i = (VERSION.SDK_INT > 18 || MoEHelperUtils.hasPermission(context, "android.permission.VIBRATE")) ? 1 : 0;
        if (!(i == 0 || MoEngageNotificationUtils.isVibrationDisabled(bundle))) {
            notification.defaults |= 2;
        }
        i = MoEngageNotificationUtils.getNotificationLedLightColor(bundle);
        if (-1 != i) {
            notification.flags |= 1;
            notification.ledARGB = i;
            return;
        }
        notification.defaults = 4;
    }

    private boolean isDuplicateCampaign(Context context, String str, ConfigurationProvider configurationProvider) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!str.equals(configurationProvider.getLastPushCampaignId())) {
            return isMessageShown(context, str);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PushMessagingListener:isDuplicateCampaign-->Last campaign ID and current campaign ID is same : ");
        stringBuilder.append(str);
        Logger.e(stringBuilder.toString());
        return true;
    }

    private boolean isNotTheIntendedRecipient(Context context, Bundle bundle, ConfigurationProvider configurationProvider) {
        String recipientUserId = MoEngageNotificationUtils.getRecipientUserId(bundle);
        if (TextUtils.isEmpty(recipientUserId)) {
            return false;
        }
        return recipientUserId.equals(configurationProvider.getCurrentUserId()) ^ 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x009f A:{Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x009f A:{Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }} */
    public void onHandleRedirection(android.app.Activity r7, android.os.Bundle r8) {
        /*
        r6 = this;
        r0 = "PushMessageListener: onHandleRedirection()";
        com.moengage.core.Logger.v(r0);
        r0 = com.moe.pushlibrary.utils.MoEHelperUtils.getLauncherActivityIntent(r7);
        r1 = "gcm_notificationType";
        r1 = r8.getString(r1);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r3 = 0;
        if (r2 != 0) goto L_0x00d1;
    L_0x0016:
        r2 = "gcm_webNotification";
        r1 = r2.equals(r1);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        if (r1 == 0) goto L_0x00d1;
    L_0x001e:
        r1 = com.moengage.push.PushManager.getInstance();	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r1 = r1.isMoEngageExtrasOptedOut();	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        if (r1 != 0) goto L_0x007b;
    L_0x0028:
        r1 = "optOutOfExtras";
        r1 = r8.containsKey(r1);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        if (r1 == 0) goto L_0x0031;
    L_0x0030:
        goto L_0x007b;
    L_0x0031:
        r1 = "gcm_webUrl";
        r1 = r8.getString(r1);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r1 = android.net.Uri.parse(r1);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r1 = r1.buildUpon();	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r2 = "nav_provier";
        r4 = "moengage";
        r1.appendQueryParameter(r2, r4);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r2 = "nav_source";
        r4 = "notification";
        r1.appendQueryParameter(r2, r4);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r2 = "FROM_BACKGROUND";
        r4 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r4.<init>();	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r5 = "";
        r4.append(r5);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r5 = com.moe.pushlibrary.MoEHelper.isAppInForeground();	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r5 = r5 ^ 1;
        r4.append(r5);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r4 = r4.toString();	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r1.appendQueryParameter(r2, r4);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r2 = "gcm_webNotification";
        r8.remove(r2);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r2 = "gcm_notificationType";
        r8.remove(r2);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        com.moengage.pushbase.push.MoEngageNotificationUtils.setMoEngageExtrastoUri(r8, r1);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r1 = r1.build();	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        goto L_0x0095;
    L_0x007b:
        r1 = "moe_webUrl";
        r1 = r8.containsKey(r1);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        if (r1 == 0) goto L_0x0094;
    L_0x0083:
        r1 = "moe_webUrl";
        r1 = r8.getString(r1);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        if (r2 != 0) goto L_0x0094;
    L_0x008f:
        r1 = android.net.Uri.parse(r1);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        goto L_0x0095;
    L_0x0094:
        r1 = r3;
    L_0x0095:
        r2 = com.moengage.pushbase.push.MoEPushCallBacks.getInstance();	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r2 = r2.onPushNavigationAction(r3, r8, r1);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        if (r2 != 0) goto L_0x00d0;
    L_0x009f:
        r2 = "PushMessagingListener:onHandleRedirection-->Web notification";
        com.moengage.core.Logger.v(r2);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        if (r1 != 0) goto L_0x00a7;
    L_0x00a6:
        return;
    L_0x00a7:
        r2 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r2.<init>();	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r3 = "PushMessagingListener:onHandleRedirection : Final URI : ";
        r2.append(r3);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r3 = r1.toString();	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r2.append(r3);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r2 = r2.toString();	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        com.moengage.core.Logger.v(r2);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r2 = new android.content.Intent;	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r3 = "android.intent.action.VIEW";
        r2.<init>(r3, r1);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r8 = r6.getIntentFlags(r8);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r2.addFlags(r8);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r7.startActivity(r2);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
    L_0x00d0:
        return;
    L_0x00d1:
        r1 = "gcm_activityName";
        r1 = r8.getString(r1);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        if (r2 != 0) goto L_0x00e7;
    L_0x00dd:
        r2 = new android.content.Intent;	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r4 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r2.<init>(r7, r4);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        goto L_0x00e8;
    L_0x00e7:
        r2 = r0;
    L_0x00e8:
        r4 = "FROM_BACKGROUND";
        r5 = com.moe.pushlibrary.MoEHelper.isAppInForeground();	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r5 = r5 ^ 1;
        r8.putBoolean(r4, r5);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r4 = "nav_provier";
        r5 = "moengage";
        r8.putString(r4, r5);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r4 = "nav_source";
        r5 = "notification";
        r8.putString(r4, r5);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r2.putExtras(r8);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r4 = r6.getIntentFlags(r8);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r2.addFlags(r4);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r4 = com.moengage.pushbase.push.MoEPushCallBacks.getInstance();	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r8 = r4.onPushNavigationAction(r1, r8, r3);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        if (r8 != 0) goto L_0x0132;
    L_0x0115:
        r8 = com.moengage.push.PushManager.getInstance();	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r1 = r7.getApplicationContext();	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r8 = r8.isBackStackBuilderOptedOut(r1);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        if (r8 != 0) goto L_0x012f;
    L_0x0123:
        r8 = android.support.v4.app.TaskStackBuilder.create(r7);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r8 = r8.addNextIntentWithParentStack(r2);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        r8.startActivities();	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
        goto L_0x0132;
    L_0x012f:
        r7.startActivity(r2);	 Catch:{ ClassNotFoundException -> 0x013a, Exception -> 0x0133 }
    L_0x0132:
        return;
    L_0x0133:
        r8 = move-exception;
        r1 = "PushMessagingListener:onHandleRedirection--> generic exception ";
        com.moengage.core.Logger.f(r1, r8);
        goto L_0x0140;
    L_0x013a:
        r8 = move-exception;
        r1 = "PushMessagingListener:onHandleRedirection--> Activity not found ";
        com.moengage.core.Logger.f(r1, r8);
    L_0x0140:
        r7.startActivity(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moengage.pushbase.push.PushMessageListener.onHandleRedirection(android.app.Activity, android.os.Bundle):void");
    }

    public final void logNotificationClicked(Context context, Intent intent) {
        MoEngageNotificationUtils.logNotificationClick(context, intent);
    }

    public final void dismissNotificationAfterClick(Context context, Bundle bundle) {
        int notificationIdIfAny = MoEngageNotificationUtils.getNotificationIdIfAny(bundle);
        if (MoEngageNotificationUtils.isAutoCancelEnabled(bundle) && -1 != notificationIdIfAny) {
            ((NotificationManager) context.getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION)).cancel(notificationIdIfAny);
        }
    }

    private void createCarouselNotification(Notification notification, Context context, Intent intent, Bundle bundle, int i) {
        if (VERSION.SDK_INT >= 16) {
            Logger.v("PushMessageListener : createCarouselNotification");
            try {
                JSONObject carouselObject = MoEngageNotificationUtils.getCarouselObject(bundle);
                if (carouselObject != null) {
                    JSONArray imagesArray = MoEngageNotificationUtils.getImagesArray(carouselObject);
                    if (imagesArray != null) {
                        boolean z;
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.carousel_custom);
                        String carouselTitle = MoEngageNotificationUtils.getCarouselTitle(carouselObject, bundle);
                        String carouselText = MoEngageNotificationUtils.getCarouselText(carouselObject, bundle);
                        String carouselSubText = MoEngageNotificationUtils.getCarouselSubText(carouselObject, bundle);
                        int carouselSmallNotificationIcon = MoEngageNotificationUtils.getCarouselSmallNotificationIcon(context);
                        int carouselLargeNotificationIcon = MoEngageNotificationUtils.getCarouselLargeNotificationIcon(context);
                        remoteViews.setTextViewText(R.id.title, carouselTitle);
                        remoteViews.setTextViewText(R.id.time, MoEngageNotificationUtils.getTime());
                        remoteViews.setTextViewText(R.id.text2, carouselText);
                        if (carouselSubText != null) {
                            z = true;
                            remoteViews.setViewVisibility(R.id.text, 0);
                            remoteViews.setTextViewText(R.id.text, carouselSubText);
                            remoteViews.setImageViewResource(R.id.profile_badge_line3, carouselSmallNotificationIcon);
                            remoteViews.setViewVisibility(R.id.profile_badge_line3, 0);
                        } else {
                            remoteViews.setImageViewResource(R.id.profile_badge_line2, carouselSmallNotificationIcon);
                            remoteViews.setViewVisibility(R.id.profile_badge_line2, 0);
                            remoteViews.setViewVisibility(R.id.line3, 8);
                            remoteViews.setTextViewTextSize(R.id.text2, 0, (float) context.getResources().getDimensionPixelSize(R.dimen.notification_text_size));
                            z = false;
                        }
                        remoteViews.setImageViewResource(R.id.icon, carouselLargeNotificationIcon);
                        remoteViews.setViewPadding(R.id.line1, 0, MoEngageNotificationUtils.calculateTopPadding(context, z), 0, 0);
                        if (!carouselObject.has(PushActionMapperConstants.CAROUSEL_AUTOSTART) || !carouselObject.getBoolean(PushActionMapperConstants.CAROUSEL_AUTOSTART)) {
                            int i2 = bundle.getInt(PushActionMapperConstants.IMG_INDEX, 0);
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("PushMessageListener : createCarouselNotification idx");
                            stringBuilder.append(i2);
                            Logger.v(stringBuilder.toString());
                            carouselText = MoEngageNotificationUtils.getCampaignIdIfAny(bundle);
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(carouselText);
                            stringBuilder2.append(imagesArray.getJSONObject(i2).getString("id"));
                            carouselSubText = stringBuilder2.toString();
                            Bitmap loadImageFromStorage = MoEngageNotificationUtils.loadImageFromStorage(context, carouselSubText);
                            if (loadImageFromStorage == null) {
                                MoEngageNotificationUtils.fetchAndSaveImages(context, imagesArray, carouselText);
                                loadImageFromStorage = MoEngageNotificationUtils.loadImageFromStorage(context, carouselSubText);
                                if (loadImageFromStorage == null) {
                                    return;
                                }
                            }
                            remoteViews.setImageViewBitmap(R.id.big_picture, loadImageFromStorage);
                            remoteViews.setOnClickPendingIntent(R.id.big_picture, MoEngageNotificationUtils.getImagePendingIntent(context, intent, i2, imagesArray));
                            Intent intent2 = new Intent(context, MoEPushWorker.class);
                            intent2.setAction(MoEPushWorker.EXTRA_SERVICE_CAROUSEL);
                            intent2.putExtras(bundle);
                            intent2.putExtra("MOE_NOTIFICATION_ID", i);
                            remoteViews.setOnClickPendingIntent(R.id.next_btn, MoEngageNotificationUtils.getNavPendingIntent(context, intent2, PushActionMapperConstants.IMG_ACTION_NEXT, i, i2));
                            remoteViews.setOnClickPendingIntent(R.id.prev_btn, MoEngageNotificationUtils.getNavPendingIntent(context, intent2, PushActionMapperConstants.IMG_ACTION_PREV, 2 * i, i2));
                        } else if (!createAnimatedCarouselNotification(context, bundle, remoteViews, intent, imagesArray, carouselObject)) {
                            return;
                        }
                        MoEngageNotificationUtils.addCarouselActionButton(context, remoteViews, bundle, intent, i);
                        if (MoEngageNotificationUtils.isReNotification(bundle)) {
                            MoEngageNotificationUtils.disableSoundAndVibration(notification);
                        }
                        notification.bigContentView = remoteViews;
                    }
                }
            } catch (Exception e) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("PushMessageListener : createCarouselNotification : Exception occurred ");
                stringBuilder3.append(e);
                Logger.f(stringBuilder3.toString());
            }
        }
    }

    private boolean createAnimatedCarouselNotification(Context context, Bundle bundle, RemoteViews remoteViews, Intent intent, JSONArray jSONArray, JSONObject jSONObject) {
        try {
            String str = PushConstants.CAROUSEL_ANIMATION_RIGHT_TO_LEFT;
            int length = jSONArray.length();
            if (length < 3) {
                Logger.v("PushMessageListener : createAnimatedCarouselNotification : Can't show animated carousel. Images count is less than 3");
                return false;
            }
            String campaignIdIfAny = MoEngageNotificationUtils.getCampaignIdIfAny(bundle);
            MoEngageNotificationUtils.fetchAndSaveImages(context, jSONArray, campaignIdIfAny);
            if (jSONObject.has("anim_direction")) {
                str = jSONObject.getString("anim_direction");
                boolean z = true;
                int hashCode = str.hashCode();
                if (hashCode != -87315416) {
                    if (hashCode == 1553519760) {
                        if (str.equals(PushConstants.CAROUSEL_ANIMATION_LEFT_TO_RIGHT)) {
                            z = false;
                        }
                    }
                } else if (str.equals(PushConstants.CAROUSEL_ANIMATION_RIGHT_TO_LEFT)) {
                    z = true;
                }
                switch (z) {
                    case false:
                        remoteViews.setViewVisibility(R.id.flipper_layout_left_to_right, 0);
                        break;
                    case true:
                        remoteViews.setViewVisibility(R.id.flipper_layout_right_to_left, 0);
                        break;
                    default:
                        break;
                }
            }
            remoteViews.setViewVisibility(R.id.flipper_layout_right_to_left, 0);
            int i = 0;
            while (i < length) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(campaignIdIfAny);
                stringBuilder.append(jSONArray.getJSONObject(i).getString("id"));
                Bitmap loadImageFromStorage = MoEngageNotificationUtils.loadImageFromStorage(context, stringBuilder.toString());
                if (loadImageFromStorage != null) {
                    int viewFlipperImageId = MoEngageNotificationUtils.getViewFlipperImageId(i, str);
                    remoteViews.setImageViewBitmap(viewFlipperImageId, loadImageFromStorage);
                    remoteViews.setViewVisibility(viewFlipperImageId, 0);
                    remoteViews.setOnClickPendingIntent(viewFlipperImageId, MoEngageNotificationUtils.getImagePendingIntent(context, intent, i, jSONArray));
                    i++;
                } else {
                    Logger.v("PushMessageListener : createAnimatedCarouselNotification : One of the images is null rolling back to narrow style");
                    MoEngageNotificationUtils.deleteImagesFromInternal(context, campaignIdIfAny);
                    return false;
                }
            }
            remoteViews.setViewVisibility(R.id.next_btn, 8);
            remoteViews.setViewVisibility(R.id.prev_btn, 8);
            return true;
        } catch (Exception e) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("PushMessageListener : createAnimatedCarouselNotification : Exception occurred ");
            stringBuilder2.append(e);
            Logger.f(stringBuilder2.toString());
            return false;
        }
    }

    public final void onMessagereceived(Context context, Map<String, String> map) {
        Bundle convertMapToBundle = MoEUtils.convertMapToBundle(map);
        if (convertMapToBundle != null) {
            onMessagereceived(context, convertMapToBundle);
        }
    }

    private boolean shouldMakeGeoFenceCall(Bundle bundle) {
        return bundle.containsKey(PushConstants.PUSH_PAYLOAD_EXTRA) ? bundle.getString(PushConstants.PUSH_PAYLOAD_EXTRA).equals("true") : true;
    }

    private void logNotificationState(Context context) {
        try {
            MoEHelper.getInstance(context).setUserAttribute("PUSH_PREFERENCE_ANDROID", NotificationManagerCompat.from(context).areNotificationsEnabled());
        } catch (Exception e) {
            Logger.f("PushMessageListener: logNotificationState: ", e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006e  */
    private boolean isMessageShown(android.content.Context r8, java.lang.String r9) {
        /*
        r7 = this;
        r0 = 0;
        r1 = r8.getContentResolver();	 Catch:{ Exception -> 0x005f }
        r2 = com.moe.pushlibrary.providers.MoEDataContract.CampaignListEntity.getContentUri(r8);	 Catch:{ Exception -> 0x005f }
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r8 = r1.query(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x005f }
        if (r8 == 0) goto L_0x0056;
    L_0x0013:
        r0 = r8.moveToFirst();	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        if (r0 == 0) goto L_0x0056;
    L_0x0019:
        r0 = "campaign_id";
        r0 = r8.getColumnIndex(r0);	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        r0 = r8.getString(r0);	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        if (r1 != 0) goto L_0x004a;
    L_0x0029:
        r0 = r0.equals(r9);	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        if (r0 == 0) goto L_0x004a;
    L_0x002f:
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        r0.<init>();	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        r1 = "PushMessageListener isDuplicateMessage() : Campaign already shown : ";
        r0.append(r1);	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        r0.append(r9);	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        r9 = r0.toString();	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        com.moengage.core.Logger.e(r9);	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        r9 = 1;
        if (r8 == 0) goto L_0x0049;
    L_0x0046:
        r8.close();
    L_0x0049:
        return r9;
    L_0x004a:
        r0 = r8.moveToNext();	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        if (r0 != 0) goto L_0x0019;
    L_0x0050:
        goto L_0x0056;
    L_0x0051:
        r9 = move-exception;
        goto L_0x006c;
    L_0x0053:
        r9 = move-exception;
        r0 = r8;
        goto L_0x0060;
    L_0x0056:
        if (r8 == 0) goto L_0x006a;
    L_0x0058:
        r8.close();
        goto L_0x006a;
    L_0x005c:
        r9 = move-exception;
        r8 = r0;
        goto L_0x006c;
    L_0x005f:
        r9 = move-exception;
    L_0x0060:
        r8 = "PushMessageListener isDuplicateMessage() ";
        com.moengage.core.Logger.e(r8, r9);	 Catch:{ all -> 0x005c }
        if (r0 == 0) goto L_0x006a;
    L_0x0067:
        r0.close();
    L_0x006a:
        r8 = 0;
        return r8;
    L_0x006c:
        if (r8 == 0) goto L_0x0071;
    L_0x006e:
        r8.close();
    L_0x0071:
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moengage.pushbase.push.PushMessageListener.isMessageShown(android.content.Context, java.lang.String):boolean");
    }
}
