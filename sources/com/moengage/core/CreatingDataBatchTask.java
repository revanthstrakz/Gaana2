package com.moengage.core;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.moe.pushlibrary.models.Event;
import com.moe.pushlibrary.models.UserAttribute;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

class CreatingDataBatchTask extends SDKTask {
    public String getTaskTag() {
        return "CREATE_DATA_BATCH";
    }

    public boolean isSynchronous() {
        return true;
    }

    CreatingDataBatchTask(Context context) {
        super(context);
    }

    public TaskResult execute() {
        try {
            Logger.v("CreatingDataBatchTask: executing task");
            if (!ConfigurationProvider.getInstance(this.mContext).isAppEnabled()) {
                return null;
            }
            trackInstallReferrerIfRequired();
            createBatchRequests();
            MoEDispatcher.getInstance(this.mContext).addTaskToQueue(new SendInteractionDataTask(this.mContext));
            Logger.v("CreatingDataBatchTask: completed task execution");
            Logger.v("CreatingDataBatchTask: completed execution");
            return null;
        } catch (Exception e) {
            Logger.f("CreatingDataBatchTask: execute() ", e);
        }
    }

    private void trackInstallReferrerIfRequired() {
        String installReferrer = MoEHelperUtils.getInstallReferrer(this.mContext);
        if (!TextUtils.isEmpty(installReferrer)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(MoEHelperConstants.PREF_KEY_INSTALL_REFERRER, installReferrer);
                MoEUtils.trackEventInternal("EVENT_ACTION_USER_ATTRIBUTE", jSONObject, this.mContext);
            } catch (Exception e) {
                Logger.f("SendInteractionDataTask:setUserAttribute", e);
            }
            MoEHelperUtils.removeInstallReferrer(this.mContext);
        }
    }

    private void createBatchRequests() {
        while (true) {
            ArrayList interactionData = MoEDAO.getInstance(this.mContext).getInteractionData(100);
            Logger.d("CreatingDataBatchTask : createBatchRequests:Fetching interaction data in batches");
            if (interactionData == null || interactionData.isEmpty()) {
                Logger.d("CreatingDataBatchTask : createBatchRequests: Found Nothing to send");
            } else {
                String convertEventsToJSON = convertEventsToJSON(interactionData);
                if (convertEventsToJSON != null) {
                    MoEDAO.getInstance(this.mContext).writeBatch(convertEventsToJSON);
                    MoEDAO.getInstance(this.mContext).deleteInteractionData(interactionData, this.mContext);
                    interactionData.clear();
                } else {
                    return;
                }
            }
        }
        Logger.d("CreatingDataBatchTask : createBatchRequests: Found Nothing to send");
    }

    @Nullable
    private String convertEventsToJSON(ArrayList<Event> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            try {
                jSONArray.put(new JSONObject(((Event) it.next()).details));
            } catch (Exception e) {
                Logger.f("CreatingDataBatchTask:convertEventsToJSON", e);
            }
        }
        if (jSONArray.length() == 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("viewsCount", jSONArray.length());
            jSONObject.put("viewsInfo", jSONArray);
            JSONObject sDKIdentifiersJSON = getSDKIdentifiersJSON();
            if (sDKIdentifiersJSON != null) {
                jSONObject.put("identifiers", sDKIdentifiersJSON);
            }
            sDKIdentifiersJSON = getSDKMeta();
            if (sDKIdentifiersJSON != null) {
                jSONObject.put("meta", sDKIdentifiersJSON);
            }
            return jSONObject.toString();
        } catch (Exception e2) {
            Logger.f("CreatingDataBatchTask:convertEventsToJSON", e2);
            return null;
        }
    }

    @Nullable
    private JSONObject getSDKIdentifiersJSON() {
        try {
            JSONObject jSONObject = new JSONObject();
            UserAttribute userAttributesForKey = MoEDAO.getInstance(this.mContext).getUserAttributesForKey(MoEHelperConstants.USER_ATTRIBUTE_UNIQUE_ID);
            if (userAttributesForKey != null) {
                jSONObject.put("moe_user_id", userAttributesForKey.userAttributeValue);
            }
            String segmentAnonymousId = ConfigurationProvider.getInstance(this.mContext).getSegmentAnonymousId();
            if (!TextUtils.isEmpty(segmentAnonymousId)) {
                jSONObject.put("segment_id", segmentAnonymousId);
            }
            if (jSONObject.length() != 0) {
                return jSONObject;
            }
        } catch (Exception e) {
            Logger.f("CreatingDataBatchTask: getSDKIdentifiersJSON() ", e);
        }
        return null;
    }

    @Nullable
    private JSONObject getSDKMeta() {
        try {
            JSONObject jSONObject = new JSONObject();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.valueOf(System.currentTimeMillis()));
            stringBuilder.append("-");
            stringBuilder.append(UUID.randomUUID().toString());
            jSONObject.put("bid", stringBuilder.toString());
            if (jSONObject.length() != 0) {
                return jSONObject;
            }
        } catch (Exception e) {
            Logger.f("CreatingDataBatchTask: getSDKMeta() ", e);
        }
        return null;
    }
}
