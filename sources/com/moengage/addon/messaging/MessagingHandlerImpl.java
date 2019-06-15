package com.moengage.addon.messaging;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import com.moe.pushlibrary.providers.MoEDataContract.CampaignListEntity;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.core.ConfigurationProvider;
import com.moengage.core.FetchMessagesFromServerTask;
import com.moengage.core.Logger;
import com.moengage.core.MoEConstants;
import com.moengage.core.MoEDispatcher;
import com.moengage.push.MoEMessagingManager.MessagingHandler;
import com.moengage.push.PushManager;
import com.moengage.push.PushManager.PushHandler;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class MessagingHandlerImpl implements MessagingHandler {
    private static MessagingHandlerImpl _INSTANCE;

    public MessagingHandlerImpl() {
        _INSTANCE = this;
    }

    public static MessagingHandlerImpl getInstance() {
        if (_INSTANCE == null) {
            MessagingHandlerImpl messagingHandlerImpl = new MessagingHandlerImpl();
        }
        return _INSTANCE;
    }

    public void scheduleAndSyncMessages(Context context) {
        if (ConfigurationProvider.getInstance(context).isAppEnabled() && ConfigurationProvider.getInstance(context).isInboxEnabled()) {
            forceMessageSync(context, false);
            scheduleMessageSync(context);
        }
    }

    public void parsePayloadAndShowPush(Context context, @Nullable JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                int i = 0;
                boolean z = jSONObject.has(MoEMessagingConstants.PARAM_IS_APP_OPEN) ? jSONObject.getBoolean(MoEMessagingConstants.PARAM_IS_APP_OPEN) : false;
                if (jSONObject.has(MoEMessagingConstants.ATTR_RESPONSE_MESSAGE)) {
                    JSONArray jSONArray = jSONObject.getJSONArray(MoEMessagingConstants.ATTR_RESPONSE_MESSAGE);
                    while (i < jSONArray.length()) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        if (jSONObject2.has("data")) {
                            Bundle jsonToBundle = jsonToBundle(jSONObject2.getJSONObject("data"));
                            if (jsonToBundle != null) {
                                addSourceKeyToBundle(jsonToBundle);
                                addForegroundKeyToBundle(jsonToBundle, z);
                                PushHandler pushHandler = PushManager.getInstance().getPushHandler();
                                if (pushHandler != null) {
                                    pushHandler.handlePushPayload(context, jsonToBundle);
                                }
                            }
                        }
                        i++;
                    }
                }
            } catch (Exception e) {
                Logger.f("MessagingHandleImpl#parsePayloadAndShowPush: Server Response Parsing exception ", e);
            }
        }
    }

    public void forceMessageSync(Context context, boolean z) {
        try {
            if (ConfigurationProvider.getInstance(context).isAppEnabled()) {
                if (ConfigurationProvider.getInstance(context).isInboxEnabled()) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(MoEConstants.PARAM_LAST_UPDATED, Long.toString(ConfigurationProvider.getInstance(context).getLastMessageFetchTime()));
                    if (z) {
                        hashMap.put(MoEMessagingConstants.PARAM_IS_APP_OPEN, "true");
                    }
                    MoEDispatcher.getInstance(context).addTaskToQueue(new FetchMessagesFromServerTask(context, getRoute(context), hashMap, ""));
                }
            }
        } catch (Exception e) {
            Logger.f("MessagingHandleImpl#forceMessageSync: Exception ", e);
        }
    }

    public void scheduleMessageSync(Context context) {
        try {
            if (ConfigurationProvider.getInstance(context).isAppEnabled()) {
                if (ConfigurationProvider.getInstance(context).isInboxEnabled()) {
                    long messageFetchDelayDuration = ConfigurationProvider.getInstance(context).getMessageFetchDelayDuration();
                    Intent intent = new Intent(context, MoEMessageSyncReceiver.class);
                    intent.setAction(MoEMessagingConstants.ACTION_SYNC_MESSAGES);
                    PendingIntent broadcast = PendingIntent.getBroadcast(context, 10001, intent, 134217728);
                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
                    if (VERSION.SDK_INT >= 21) {
                        scheduleFetchJob(context, messageFetchDelayDuration);
                    } else {
                        alarmManager.set(0, System.currentTimeMillis() + messageFetchDelayDuration, broadcast);
                    }
                }
            }
        } catch (Exception e) {
            Logger.f("MessagingHandleImpl#scheduleMessageSync: Exception ", e);
        }
    }

    @TargetApi(21)
    private void scheduleFetchJob(Context context, long j) {
        Builder builder = new Builder(MoEMessagingConstants.MESSAGE_SYNC_JOB_ID, new ComponentName(context, MoEMessageSyncJob.class));
        builder.setOverrideDeadline((System.currentTimeMillis() + j) + 3600000);
        builder.setMinimumLatency(j);
        builder.setRequiredNetworkType(1);
        if (MoEHelperUtils.hasPermission(context, "android.permission.RECEIVE_BOOT_COMPLETED")) {
            builder.setPersisted(true);
        }
        ((JobScheduler) context.getSystemService("jobscheduler")).schedule(builder.build());
    }

    public void saveCampaignId(Context context, String str) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("campaign_id", str);
            contentValues.put("ttl", Long.valueOf(System.currentTimeMillis() + MoEMessagingConstants.DEFAULT_CAMPAIGN_TTL));
            context.getContentResolver().insert(CampaignListEntity.getContentUri(context), contentValues);
        } catch (Exception e) {
            Logger.f("MessagingHandlerImpl#saveCampaignId: Exception : ", e);
        }
    }

    @Nullable
    private Bundle jsonToBundle(JSONObject jSONObject) {
        try {
            Bundle bundle = new Bundle();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                bundle.putString(str, jSONObject.getString(str));
            }
            return bundle;
        } catch (Exception e) {
            Logger.f("MessagingHandlerImpl#jsonToBundle : JSON Parsing Error", e);
            return null;
        }
    }

    private void addSourceKeyToBundle(Bundle bundle) {
        bundle.putString("received_from", "remote_inbox");
    }

    private void addForegroundKeyToBundle(Bundle bundle, boolean z) {
        bundle.putBoolean("from_appOpen", z);
    }

    private String getRoute(Context context) {
        int dataRegion = ConfigurationProvider.getInstance(context).getDataRegion();
        if (dataRegion == -999) {
            return ConfigurationProvider.getInstance(context).shouldRouteTraffic() ? MoEMessagingConstants.MESSAGE_FETCH_API_INDIA : MoEMessagingConstants.MESSAGE_FETCH_API_GENERAL;
        } else {
            switch (dataRegion) {
                case 1001:
                    return MoEMessagingConstants.MESSAGE_FETCH_API_INDIA;
                case 1002:
                    return MoEMessagingConstants.MESSAGE_FETCH_API_EU;
                case 1003:
                    return MoEMessagingConstants.MESSAGE_FETCH_API_GENERAL;
                default:
                    return MoEMessagingConstants.MESSAGE_FETCH_API_GENERAL;
            }
        }
    }
}
