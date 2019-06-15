package com.moengage.core;

import android.content.Context;
import android.text.TextUtils;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;
import com.moengage.inapp.InAppController;
import com.moengage.inapp.InAppController.InAppHandler;
import com.moengage.inapp.InAppController.NETWORK_CALL_TYPE;
import java.util.HashMap;
import org.json.JSONObject;

public class InAppNetworkCallsTask extends SDKTask {
    String API;
    private InAppHandler inAppHandler = InAppController.getInstance().getInAppHandler();
    private NETWORK_CALL_TYPE networkCallType;
    private HashMap<String, String> paramsMap;
    private String requestBody;

    public String getTaskTag() {
        return SDKTask.TAG_INAPP_NETWORK_TASK;
    }

    public boolean isSynchronous() {
        return false;
    }

    public InAppNetworkCallsTask(Context context, String str, HashMap<String, String> hashMap, String str2, NETWORK_CALL_TYPE network_call_type) {
        super(context);
        this.API = str;
        this.paramsMap = hashMap;
        this.requestBody = str2;
        this.networkCallType = network_call_type;
    }

    public TaskResult execute() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("InAppNetworkCallsTask : started execution, Task Type : ");
        stringBuilder.append(this.networkCallType);
        Logger.v(stringBuilder.toString());
        if (this.inAppHandler != null) {
            try {
                if (ConfigurationProvider.getInstance(this.mContext).isInAppEnabled()) {
                    if (ConfigurationProvider.getInstance(this.mContext).isAppEnabled()) {
                        String fetchInAppCampaigns;
                        switch (this.networkCallType) {
                            case SYNC_IN_APPS:
                                Logger.v("InAppNetworkCallsTask: executing sync in-apps");
                                this.mTaskResult.setPayload(NETWORK_CALL_TYPE.SYNC_IN_APPS);
                                fetchInAppCampaigns = APIManager.fetchInAppCampaigns(this.mContext, this.API, this.paramsMap, this.requestBody);
                                if (!TextUtils.isEmpty(fetchInAppCampaigns)) {
                                    this.inAppHandler.parseAndSaveInApps(new JSONObject(fetchInAppCampaigns), this.mContext);
                                    this.mTaskResult.setIsSuccess(true);
                                    break;
                                }
                                Logger.e("MoEParser:parseAndSaveCampaignInfo not a valid response");
                                break;
                            case AUTO_TRIGGER_EVENT:
                                Logger.v("InAppNetworkCallsTask: executing auto-trigger in-apps");
                                fetchInAppCampaigns = APIManager.logASmartEvent(this.mContext, this.API, this.paramsMap, this.requestBody);
                                if (!TextUtils.isEmpty(fetchInAppCampaigns)) {
                                    this.inAppHandler.tryShowAutoTriggerInApp(this.mContext, new JSONObject(fetchInAppCampaigns));
                                    break;
                                }
                                break;
                            case SINGLE_FETCH:
                                Logger.v("InAppNetworkCallsTask: executing single fetch in-apps");
                                fetchInAppCampaigns = APIManager.fetchSingleInApp(this.mContext, this.API, this.paramsMap);
                                if (!TextUtils.isEmpty(fetchInAppCampaigns)) {
                                    this.inAppHandler.showLinkedInApp(this.mContext, new JSONObject(fetchInAppCampaigns), this.paramsMap);
                                    break;
                                }
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("Network Error Could not show test in-app.\n CampaignId : ");
                                stringBuilder.append((String) this.paramsMap.get("campaign_id"));
                                stringBuilder.append(".\nPlease try again or contact MoEngage Support with the screenshot.");
                                this.inAppHandler.showTestInAppErrorDialog(stringBuilder.toString());
                                break;
                            default:
                                break;
                        }
                    }
                }
                return null;
            } catch (Exception e) {
                Logger.f("InAppNetworkCallsTask : execute JSONException", e);
            }
        }
        Logger.v("InAppNetworkCallsTask : completed execution");
        return this.mTaskResult;
    }
}
