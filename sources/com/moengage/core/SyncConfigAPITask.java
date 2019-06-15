package com.moengage.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;
import org.json.JSONException;
import org.json.JSONObject;

class SyncConfigAPITask extends SDKTask {
    private Context mContext;
    private ConfigurationProvider provider = ConfigurationProvider.getInstance(this.mContext);

    private String getStateFromBoolean(boolean z) {
        return z ? "allowed" : "blocked";
    }

    public String getTaskTag() {
        return "SYNC_CONFIG";
    }

    public boolean isSynchronous() {
        return true;
    }

    SyncConfigAPITask(Context context) {
        super(context);
        this.mContext = context;
    }

    public TaskResult execute() {
        Logger.v("SyncConfigAPITask : executing Task");
        try {
            Context context = this.mContext;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(MoEUtils.getAPIRouteV3(this.mContext));
            stringBuilder.append("/v3/getConfig");
            String syncConfig = APIManager.syncConfig(context, stringBuilder.toString(), createPostBody());
            if (!TextUtils.isEmpty(syncConfig)) {
                JSONObject jSONObject = new JSONObject(syncConfig);
                if (jSONObject.has("le_s")) {
                    this.provider.setLogEntryEnable(getStateFromResponse(jSONObject, "le_s"));
                }
                if (jSONObject.has("le_tkn")) {
                    this.provider.setLogEntryKey(jSONObject.getString("le_tkn"));
                }
                if (jSONObject.has("m_s_t")) {
                    this.provider.setMessageFetchDelayDuration((long) (jSONObject.getInt("m_s_t") * 1000));
                }
                if (jSONObject.has("b_e")) {
                    this.provider.saveBlackListEventList(MoEUtils.convertJSONArrayToString(jSONObject.getJSONArray("b_e")));
                    MoEEventManager.getInstance(this.mContext).getBlackListedEvents();
                }
                if (jSONObject.has("a_s")) {
                    this.provider.saveAppState(getStateFromResponse(jSONObject, "a_s"));
                }
                if (jSONObject.has("i_s")) {
                    this.provider.saveInAppState(getStateFromResponse(jSONObject, "i_s"));
                }
                if (jSONObject.has("g_s")) {
                    this.provider.saveGeoState(getStateFromResponse(jSONObject, "g_s"));
                }
                if (jSONObject.has("in_s")) {
                    this.provider.saveInboxState(getStateFromResponse(jSONObject, "in_s"));
                }
                if (jSONObject.has("e_b_c")) {
                    this.provider.saveEventBatchCount(jSONObject.getInt("e_b_c"));
                }
                if (jSONObject.has("d_s_r_i")) {
                    this.provider.setRetrySyncTime(jSONObject.getLong("d_s_r_i"));
                }
                if (jSONObject.has("f_e")) {
                    this.provider.saveFlushEventList(MoEUtils.convertJSONArrayToString(jSONObject.getJSONArray("f_e")));
                    MoEEventManager.getInstance(this.mContext).getFlushEvents();
                }
                if (jSONObject.has("p_f_s")) {
                    this.provider.savePeriodicFlushState(jSONObject.getBoolean("p_f_s"));
                }
                if (jSONObject.has("p_f_t")) {
                    this.provider.savePeriodicFlushTime(jSONObject.getLong("p_f_t"));
                }
                if (jSONObject.has("cid_ex")) {
                    this.provider.saveCampaignIdTTL(jSONObject.getLong("cid_ex"));
                }
            }
        } catch (Exception e) {
            Logger.f("SyncConfigAPITask : execute", e);
        }
        Logger.v("SyncConfigAPITask : execution completed");
        return null;
    }

    private String createPostBody() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("a_s", getStateFromBoolean(this.provider.isAppEnabled()));
            jSONObject.put("i_s", getStateFromBoolean(this.provider.isInAppEnabled()));
            jSONObject.put("in_s", getStateFromBoolean(this.provider.isInboxEnabled()));
            jSONObject.put("g_s", getStateFromBoolean(this.provider.isGeoEnabled()));
            jSONObject.put("le_s", getStateFromBoolean(this.provider.isLogEntryEnabled()));
            jSONObject.put(MoEConstants.RESPONSE_ATTR_EVENTS_INFO, this.provider.getEventBatchCount());
            jSONObject.put("b_e", MoEUtils.convertStringToJSONArray(this.provider.getBlackListEvents()));
            jSONObject.put("m_s_t", this.provider.getMessageFetchDelayDuration() / 1000);
            jSONObject.put("d_s_r_i", this.provider.getPeriodicFlushTime());
            jSONObject.put("p_f_s", this.provider.isPeriodicFlushEnabled());
            jSONObject.put("f_e", MoEUtils.convertStringToJSONArray(this.provider.getFlushEvents()));
            jSONObject.put("cid_ex", this.provider.getCampaignIdTTL());
            return jSONObject.toString();
        } catch (Exception e) {
            Logger.f("SyncConfigAPITask: createPostBody: ", e);
            return null;
        }
    }

    private boolean getStateFromResponse(@NonNull JSONObject jSONObject, @NonNull String str) {
        try {
            String string = jSONObject.getString(str);
            boolean z = true;
            int hashCode = string.hashCode();
            if (hashCode != -911343192) {
                if (hashCode == -21437972) {
                    if (string.equals("blocked")) {
                        z = false;
                    }
                }
            } else if (string.equals("allowed")) {
                z = true;
            }
            switch (z) {
                case false:
                    return false;
                case true:
                    return true;
            }
        } catch (JSONException e) {
            Logger.f("SyncConfigAPITask: getStateFromResponse ", e);
        }
        return true;
    }
}
