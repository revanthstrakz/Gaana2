package com.moengage.inapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.moengage.core.ConfigurationProvider;
import com.moengage.core.Logger;
import com.moengage.core.MoEConstants;
import com.moengage.core.MoEEventManager;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class InAppsParser {
    static boolean parseAndSaveCampaignInfo(JSONObject jSONObject, Context context) {
        try {
            if (jSONObject.has(MoEConstants.RESPONSE_ATTR_EVENTS_INFO)) {
                String parseEventsInfo = parseEventsInfo(jSONObject);
                if (!TextUtils.isEmpty(parseEventsInfo)) {
                    ConfigurationProvider.getInstance(context).saveSmartTriggerList(parseEventsInfo);
                    MoEEventManager.getInstance(context).getTriggerEvents();
                }
            }
            if (jSONObject.has(InAppConstants.RESP_ATTR_DELAY)) {
                ConfigurationProvider.getInstance(context).setInAppDelayDuration(jSONObject.getInt(InAppConstants.RESP_ATTR_DELAY) * 60);
                InAppManager.getInstance().setMinimumInterval((long) ((jSONObject.getInt(InAppConstants.RESP_ATTR_DELAY) * 60) * 1000));
            }
            if (jSONObject.has(InAppConstants.RESP_ATTR_DEBUG_ENABLED)) {
                Logger.setLogStatus(jSONObject.getBoolean(InAppConstants.RESP_ATTR_DEBUG_ENABLED));
            }
            if (jSONObject.has(InAppConstants.RESP_ATTR_CAMPAIGN_INFO)) {
                JSONArray jSONArray = jSONObject.getJSONArray(InAppConstants.RESP_ATTR_CAMPAIGN_INFO);
                if (jSONArray != null && jSONArray.length() > 0) {
                    int length = jSONArray.length();
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < length; i++) {
                        InAppMessage parseInAppCampaign = parseInAppCampaign(jSONArray.getJSONObject(i));
                        if (parseInAppCampaign != null) {
                            arrayList.add(parseInAppCampaign);
                        }
                    }
                    InAppsDAO.getInstance(context).addOrUpdateInApps(arrayList);
                }
                return true;
            }
        } catch (Exception e) {
            Logger.f("MoEParser: parseAndSaveCampaignInfo error ", e);
        }
        return false;
    }

    @Nullable
    static InAppMessage parseInAppCampaign(@NonNull JSONObject jSONObject) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("MoEParser: parseInAppCampaign : parsing campaign: ");
            stringBuilder.append(jSONObject.toString());
            Logger.v(stringBuilder.toString());
            InAppMessage inAppMessage = new InAppMessage();
            inAppMessage.rules.campaignId = jSONObject.getString("cid");
            if (jSONObject.has("content")) {
                inAppMessage.content = jSONObject.getString("content");
            } else if (jSONObject.has(InAppConstants.INAPP_CAMPAIGN_WIDGET_DATA)) {
                inAppMessage.content = jSONObject.getJSONArray(InAppConstants.INAPP_CAMPAIGN_WIDGET_DATA).toString();
            }
            inAppMessage.setAlignType(jSONObject.optString(InAppConstants.INAPP_CAMPAIGN_ALIGN));
            inAppMessage.setInAppType(jSONObject.optString("type"));
            inAppMessage.status = jSONObject.getString("status");
            if (jSONObject.has("ttl")) {
                inAppMessage.rules.ttl = jSONObject.getLong("ttl");
            }
            if (jSONObject.has("max_times")) {
                inAppMessage.rules.maxTimes = jSONObject.getInt("max_times");
            }
            if (jSONObject.has("interval")) {
                inAppMessage.rules.minmumDelay = jSONObject.getLong("interval") * 1000;
            }
            if (jSONObject.has("persistent")) {
                inAppMessage.rules.persistent = jSONObject.getBoolean("persistent");
            }
            if (jSONObject.has("priority")) {
                inAppMessage.rules.priority = jSONObject.getInt("priority");
            }
            if (jSONObject.has("context")) {
                inAppMessage.rules.context = jSONObject.getString("context");
            }
            if (jSONObject.has("auto_dismiss")) {
                inAppMessage.rules.autoDismiss = jSONObject.getLong("auto_dismiss");
            }
            if (jSONObject.has(InAppConstants.INAPP_CAMPAIGN_CANCELABLE)) {
                inAppMessage.rules.cancelable = jSONObject.getBoolean(InAppConstants.INAPP_CAMPAIGN_CANCELABLE);
            }
            if (jSONObject.has(InAppConstants.INAPP_CAMPAIGN_SHOWONLYIN)) {
                inAppMessage.rules.showOnlyIn = jSONObject.getString(InAppConstants.INAPP_CAMPAIGN_SHOWONLYIN);
                if (inAppMessage.rules.showOnlyIn.equals("null")) {
                    inAppMessage.rules.showOnlyIn = null;
                }
            }
            if (jSONObject.has("max_times")) {
                inAppMessage.rules.maxTimes = jSONObject.getInt("max_times");
            }
            if (jSONObject.has(InAppConstants.INAPP_BACKGROUND_PROPERTIES)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(InAppConstants.INAPP_BACKGROUND_PROPERTIES);
                if (jSONObject2 != null) {
                    inAppMessage.dimStyle = jSONObject2.toString();
                }
            }
            if (jSONObject.has("status")) {
                inAppMessage.status = jSONObject.getString("status");
            }
            if (jSONObject.has(MoEConstants.PARAM_LAST_UPDATED)) {
                inAppMessage.rules.lastUpdatedTime = jSONObject.getLong(MoEConstants.PARAM_LAST_UPDATED);
            }
            return inAppMessage;
        } catch (Exception e) {
            Logger.f("MoEParser: parseInAppCampaign error ", e);
            return null;
        }
    }

    @Nullable
    static InAppMessage parseSmartTriggerResponse(JSONObject jSONObject) {
        try {
            if (jSONObject.has(InAppConstants.RESP_ATTR_CAMPAIGN_INFO)) {
                JSONArray jSONArray = jSONObject.getJSONArray(InAppConstants.RESP_ATTR_CAMPAIGN_INFO);
                if (jSONArray.length() > 0) {
                    return parseInAppCampaign(jSONArray.getJSONObject(0));
                }
            }
        } catch (Exception e) {
            Logger.f("MoEParser:parseSmartTriggerResponse ", e);
        }
        return null;
    }

    private static String parseEventsInfo(JSONObject jSONObject) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(MoEConstants.RESPONSE_ATTR_EVENTS_INFO);
            for (int i = 0; i < jSONArray.length(); i++) {
                stringBuilder.append((String) jSONArray.get(i));
                if (i != jSONArray.length() - 1) {
                    stringBuilder.append(";");
                }
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            Logger.f("MoEParser: parseEventsInfo", e);
            return null;
        }
    }
}
