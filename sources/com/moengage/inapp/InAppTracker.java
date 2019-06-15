package com.moengage.inapp;

import android.content.Context;
import android.support.annotation.NonNull;
import com.moe.pushlibrary.MoEHelper;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moengage.core.Logger;
import com.moengage.core.MoEDispatcher;
import com.moengage.inapp.InAppMessage.TYPE;
import org.json.JSONObject;

public final class InAppTracker {
    private static InAppTracker _INSTANCE;
    private Context mContext;

    private InAppTracker(Context context) {
        this.mContext = context;
    }

    public static InAppTracker getInstance(Context context) {
        if (_INSTANCE == null) {
            _INSTANCE = new InAppTracker(context);
        }
        return _INSTANCE;
    }

    public void inAppShown(@NonNull InAppMessage inAppMessage) {
        if (inAppMessage != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(MoEHelperConstants.GCM_EXTRA_CAMPAIGN_ID, inAppMessage.rules.campaignId);
                MoEHelper.getInstance(this.mContext).trackEvent(InAppConstants.EVENT_IN_APP_SHOWN, jSONObject);
                if (inAppMessage.rules.type != TYPE.SMART) {
                    logInAppShown(inAppMessage);
                }
            } catch (Exception e) {
                Logger.f("InAppTracker:inAppShown", e);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void trackInAppWidgetClicked(InAppMessage inAppMessage, int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MoEHelperConstants.GCM_EXTRA_CAMPAIGN_ID, inAppMessage.rules.campaignId);
            jSONObject.put("widget_id", i);
            MoEHelper.getInstance(this.mContext).trackEvent(InAppConstants.EVENT_IN_APP_CLICKED, jSONObject);
        } catch (Exception e) {
            Logger.f("InAppTracker:trackInAppWidgetClicked", e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void trackInAppPrimaryClick(InAppMessage inAppMessage) {
        if (inAppMessage.rules.type != TYPE.SMART) {
            MoEDispatcher.getInstance(this.mContext).addTaskToQueue(new LogInAppPrimaryClickedTask(this.mContext, inAppMessage));
        }
    }

    public void trackInAppAutoDismissed(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MoEHelperConstants.GCM_EXTRA_CAMPAIGN_ID, str);
            MoEHelper.getInstance(this.mContext).trackEvent(InAppConstants.EVENT_IN_APP_AUTO_DISMISS, jSONObject);
        } catch (Exception e) {
            Logger.f("InAppTracker:trackInAppAutoDismissed", e);
        }
    }

    public void trackInAppCloseButtonClicked(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MoEHelperConstants.GCM_EXTRA_CAMPAIGN_ID, str);
            MoEHelper.getInstance(this.mContext).trackEvent(InAppConstants.EVENT_IN_APP_CLOSE_CLICKED, jSONObject);
        } catch (Exception e) {
            Logger.f("InAppTracker:trackInAppCloseButtonClicked", e);
        }
    }

    public void trackInAppClicked(InAppMessage inAppMessage) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MoEHelperConstants.GCM_EXTRA_CAMPAIGN_ID, inAppMessage.rules.campaignId);
            MoEHelper.getInstance(this.mContext).trackEvent(InAppConstants.EVENT_IN_APP_CLICKED, jSONObject);
        } catch (Exception e) {
            Logger.f("MoEController:trackInAppShown", e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void logInAppShown(@NonNull InAppMessage inAppMessage) {
        if (inAppMessage != null) {
            InAppsDAO.getInstance(this.mContext).updateInAppShown(inAppMessage.rules.campaignId, System.currentTimeMillis());
            InAppsDAO.getInstance(this.mContext).updateInAppShownCount(inAppMessage.rules.campaignId);
            InAppManager.getInstance().updateInAppCache(this.mContext);
        }
    }
}
