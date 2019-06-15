package com.moengage.inapp;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import com.comscore.utils.Constants;
import com.moe.pushlibrary.models.Event;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.core.InAppNetworkCallsTask;
import com.moengage.core.Logger;
import com.moengage.core.MoEDispatcher;
import com.moengage.core.MoEUtils;
import com.moengage.inapp.InAppController.InAppHandler;
import com.moengage.inapp.InAppController.NETWORK_CALL_TYPE;
import com.moengage.inapp.InAppManager.InAppMessageListener;
import java.util.HashMap;
import org.json.JSONObject;

public class InAppHandlerImpl implements InAppHandler {
    public void showInAppIfPossible(Context context) {
        if (InAppManager.getInstance().inappTimeCheck()) {
            MoEDispatcher.getInstance(context).addTaskToQueueBeginning(new CheckInAppTask(context));
            MoEDispatcher.getInstance(context).addTaskToQueueBeginning(new ShowSelfHandledInAppTask(context));
        }
    }

    public void showInAppOnConfigurationChange(Context context) {
        MoEDispatcher.getInstance(context).addTaskToQueue(new ShowInAppOnConfigChangeTask(context, InAppManager.getInstance().getCurrentInApp()));
    }

    public void syncOrShowInApps(Context context) {
        if (InAppManager.getInstance().isFetchRequired(context)) {
            InAppManager.getInstance().syncInApps(context);
        } else {
            showInAppIfPossible(context);
        }
    }

    public void tryShowAutoTriggerInApp(Context context, JSONObject jSONObject) {
        InAppMessage parseSmartTriggerResponse = InAppsParser.parseSmartTriggerResponse(jSONObject);
        if (parseSmartTriggerResponse == null || !parseSmartTriggerResponse.getAlignType().equals("self_handled")) {
            buildAndShowInApp(context, parseSmartTriggerResponse);
            return;
        }
        InAppMessageListener inAppMessageListener = InAppManager.getInstance().getInAppMessageListener();
        if (inAppMessageListener != null && inAppMessageListener.showInAppMessage(parseSmartTriggerResponse)) {
            InAppTracker.getInstance(context).inAppShown(parseSmartTriggerResponse);
        }
    }

    public void buildAndShowInApp(Context context, InAppMessage inAppMessage) {
        if (inAppMessage != null) {
            Logger.v("InAppHandlerImpl : tryShowAutoTriggerInApp --> in-app found to show. will try to show in-app");
            inAppMessage.theComposedView = ViewEngine.getInstance(context).createInApp(InAppManager.getInstance().getCurrentActivity(), inAppMessage);
            if (inAppMessage.theComposedView != null) {
                InAppManager.getInstance().showInAppMessage(inAppMessage.theComposedView, inAppMessage, false);
            }
        }
    }

    public void parseAndSaveInApps(JSONObject jSONObject, Context context) {
        if (jSONObject != null && InAppsParser.parseAndSaveCampaignInfo(jSONObject, context)) {
            InAppManager.getInstance().updateInAppCache(context);
        }
    }

    public void registerAutoTriggerEvent(Context context, Event event) {
        String actionFromEvent = MoEHelperUtils.getActionFromEvent(event.details);
        if (!TextUtils.isEmpty(actionFromEvent)) {
            HashMap hashMap = new HashMap();
            hashMap.put("event", actionFromEvent);
            MoEDispatcher instance = MoEDispatcher.getInstance(context);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(MoEUtils.getAPIRoute(context));
            stringBuilder.append(InAppConstants.API_SMART_TRIGGER);
            instance.addTaskToQueueBeginning(new InAppNetworkCallsTask(context, stringBuilder.toString(), hashMap, event.details, NETWORK_CALL_TYPE.AUTO_TRIGGER_EVENT));
        }
    }

    public void unregisterInAppManager(Activity activity) {
        InAppManager.getInstance().unregisterInAppManager(activity);
    }

    public void registerInAppManager(Activity activity) {
        InAppManager.getInstance().registerInAppManager(activity);
        if (activity instanceof InAppMessageListener) {
            InAppManager.getInstance().setInAppListener((InAppMessageListener) activity);
        }
    }

    public void setInappsSynced(boolean z) {
        InAppManager.getInstance().setInappsSynced(z);
    }

    public void fetchLinkedInApp(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(MoEUtils.getAPIRoute(context));
        stringBuilder.append(InAppConstants.API_ENDPOINT_FETCH_SINGLE_INAPP);
        stringBuilder.append(InAppConstants.API_ENDPOINT_INAPPS);
        String stringBuilder2 = stringBuilder.toString();
        HashMap hashMap = new HashMap();
        hashMap.put("campaign_id", str);
        MoEDispatcher.getInstance(context).addTaskToQueueBeginning(new InAppNetworkCallsTask(context, stringBuilder2, hashMap, null, NETWORK_CALL_TYPE.SINGLE_FETCH));
    }

    public void showLinkedInApp(Context context, JSONObject jSONObject, HashMap<String, String> hashMap) {
        if (context != null) {
            try {
                if (jSONObject.has(InAppConstants.RESP_ATTR_CAMPAIGN_INFO)) {
                    InAppMessage parseInAppCampaign = InAppsParser.parseInAppCampaign(jSONObject.getJSONObject(InAppConstants.RESP_ATTR_CAMPAIGN_INFO));
                    if (parseInAppCampaign == null || !parseInAppCampaign.getAlignType().equals("self_handled")) {
                        buildAndShowInApp(context, parseInAppCampaign);
                    } else {
                        InAppMessageListener inAppMessageListener = InAppManager.getInstance().getInAppMessageListener();
                        if (inAppMessageListener != null && inAppMessageListener.showInAppMessage(parseInAppCampaign)) {
                            InAppTracker.getInstance(context).inAppShown(parseInAppCampaign);
                        }
                    }
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("InAppHandlerImpl : showLinkedInApp");
                    stringBuilder.append(jSONObject.toString());
                    stringBuilder.append("Campaign id :");
                    stringBuilder.append((String) hashMap.get("campaign_id"));
                    Logger.f(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Test Campaign not found.\n CampaignId : ");
                    stringBuilder.append((String) hashMap.get("campaign_id"));
                    stringBuilder.append(".\nPlease try again or contact MoEngage Support with the screenshot.");
                    String stringBuilder2 = stringBuilder.toString();
                    if (jSONObject.has("reason")) {
                        String string = jSONObject.getString("reason");
                        if (!TextUtils.isEmpty(string)) {
                            StringBuilder stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(stringBuilder2);
                            stringBuilder3.append("\n");
                            stringBuilder3.append(string);
                            stringBuilder2 = stringBuilder3.toString();
                        }
                    }
                    showTestInAppErrorDialog(stringBuilder2);
                }
            } catch (Exception e) {
                Logger.f("InAppHandlerImpl : showLinkedInApp", e);
            }
        }
    }

    public void showTestInAppErrorDialog(String str) {
        Activity currentActivity = InAppManager.getInstance().getCurrentActivity();
        if (currentActivity != null) {
            final Builder builder = new Builder(currentActivity);
            builder.setMessage(str).setTitle("Could not show InApp").setPositiveButton(Constants.RESPONSE_MASK, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            currentActivity.runOnUiThread(new Runnable() {
                public void run() {
                    builder.create().show();
                }
            });
        }
    }

    public void writeInAppCheckFailureCounterToStorage(Context context) {
        MoEInAppFailureLogger.getInstance().writeUpdatedCountersToStorage(context);
    }

    public void logInAppAPIFailure(String str) {
        MoEInAppFailureLogger.getInstance().updateCommonErrorCounter(str);
    }
}
