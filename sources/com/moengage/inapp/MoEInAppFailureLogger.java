package com.moengage.inapp;

import android.content.Context;
import com.moengage.core.Logger;
import com.moengage.core.MoEEventManager;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

public class MoEInAppFailureLogger {
    private static String CHECK_FAILURE_EVENT = "IN_APP_CHECK_FAILURE";
    static String FAILURE_REASON_CAMPAIGN_EXPIRED = "expired";
    static String FAILURE_REASON_CLOSE_BUTTON_DOWNLOAD_FAILURE = "close_download";
    static String FAILURE_REASON_GLOBAL_DELAY = "global_delay";
    static String FAILURE_REASON_IMAGE_DOWNLOAD_FAILURE = "image_download";
    static String FAILURE_REASON_IN_APP_BLOCKED_ON_SCREEN = "inapp_blocked_on_screen";
    static String FAILURE_REASON_MINIMUM_DELAY_BETWEEN_SAME_IN_APP = "min_delay";
    static String FAILURE_REASON_NOT_EXPECTED_TYPE = "align_type";
    static String FAILURE_REASON_PERSISTENT = "persistent";
    static String FAILURE_REASON_SHOW_COUNT = "show_count";
    static String FAILURE_REASON_SHOW_ONLY_IN_SCREEN = "screen";
    static String FAILURE_REASON_VIEW_CREATION_FAILURE = "view_creation_failure";
    private static String PARAM_CAMPAIGN_ARRAY = "campaigns_list";
    private static String PARAM_CAMPAIGN_FAILURE = "failure";
    private static String PARAM_CAMPAIGN_ID = "cid";
    private static String PARAM_COMMON_ERRORS = "common_errors";
    private static MoEInAppFailureLogger _INSTANCE;
    private String TAG = MoEInAppFailureLogger.class.getSimpleName();
    private HashMap<String, JSONObject> mCampaignFailureReasonMap;
    private HashMap<String, JSONObject> mCommonErrorFailureMap;

    private MoEInAppFailureLogger() {
        resetCounters();
    }

    public static MoEInAppFailureLogger getInstance() {
        if (_INSTANCE == null) {
            _INSTANCE = new MoEInAppFailureLogger();
        }
        return _INSTANCE;
    }

    /* Access modifiers changed, original: 0000 */
    public void updateCampaignFailureCounter(String str, String str2) {
        StringBuilder stringBuilder;
        try {
            JSONObject jSONObject = (JSONObject) this.mCampaignFailureReasonMap.get(str);
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            jSONObject.put(str2, jSONObject.optInt(str2) + 1);
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.TAG);
            stringBuilder.append(" updateCampaignFailureCounter() updated failure count ");
            stringBuilder.append(jSONObject.toString());
            Logger.v(stringBuilder.toString());
            this.mCampaignFailureReasonMap.put(str, jSONObject);
        } catch (Exception e) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.TAG);
            stringBuilder.append(" updateCampaignFailureCounter() ");
            Logger.f(stringBuilder.toString(), e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void updateCommonErrorCounter(String str) {
        try {
            JSONObject jSONObject = (JSONObject) this.mCommonErrorFailureMap.get(PARAM_COMMON_ERRORS);
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            jSONObject.put(str, jSONObject.optInt(str) + 1);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.TAG);
            stringBuilder.append(" updateCommonErrorCounter() updated error count ");
            stringBuilder.append(jSONObject.toString());
            Logger.v(stringBuilder.toString());
            this.mCommonErrorFailureMap.put(PARAM_COMMON_ERRORS, jSONObject);
        } catch (Exception e) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(this.TAG);
            stringBuilder2.append(" updateCommonErrorCounter() ");
            Logger.f(stringBuilder2.toString(), e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void writeUpdatedCountersToStorage(Context context) {
        try {
            JSONArray jSONArray = new JSONArray();
            for (Entry entry : this.mCampaignFailureReasonMap.entrySet()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(PARAM_CAMPAIGN_ID, entry.getKey());
                jSONObject.put(PARAM_CAMPAIGN_FAILURE, entry.getValue());
                jSONArray.put(jSONObject);
            }
            JSONObject jSONObject2 = new JSONObject();
            if (jSONArray.length() != 0) {
                jSONObject2.put(PARAM_CAMPAIGN_ARRAY, jSONArray);
            }
            if (this.mCommonErrorFailureMap.containsKey(PARAM_COMMON_ERRORS)) {
                jSONObject2.put(PARAM_COMMON_ERRORS, this.mCommonErrorFailureMap.get(PARAM_COMMON_ERRORS));
            }
            if (jSONObject2.length() != 0) {
                MoEEventManager.getInstance(context).trackEvent(CHECK_FAILURE_EVENT, jSONObject2);
            }
            resetCounters();
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.TAG);
            stringBuilder.append(" writeUpdatedCountersToStorage() ");
            Logger.f(stringBuilder.toString(), e);
        }
    }

    private void resetCounters() {
        this.mCampaignFailureReasonMap = new HashMap();
        this.mCommonErrorFailureMap = new HashMap();
    }
}
