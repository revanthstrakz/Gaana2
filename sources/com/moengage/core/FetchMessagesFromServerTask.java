package com.moengage.core;

import android.content.Context;
import android.text.TextUtils;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;
import com.moengage.push.MoEMessagingManager;
import com.moengage.push.MoEMessagingManager.MessagingHandler;
import java.util.HashMap;
import org.json.JSONObject;

public class FetchMessagesFromServerTask extends SDKTask {
    private String API;
    private HashMap<String, String> paramsMap;
    private String requestBody;

    public String getTaskTag() {
        return "FETCH_MESSAGES";
    }

    public boolean isSynchronous() {
        return false;
    }

    public FetchMessagesFromServerTask(Context context, String str, HashMap<String, String> hashMap, String str2) {
        super(context);
        this.API = str;
        this.paramsMap = hashMap;
        this.requestBody = str2;
    }

    public TaskResult execute() {
        Throwable e;
        StringBuilder stringBuilder;
        String fetchMessages;
        try {
            if (ConfigurationProvider.getInstance(this.mContext).isInboxEnabled()) {
                if (ConfigurationProvider.getInstance(this.mContext).isAppEnabled()) {
                    fetchMessages = APIManager.fetchMessages(this.mContext, this.API, this.paramsMap, this.requestBody);
                    try {
                        if (!TextUtils.isEmpty(fetchMessages)) {
                            JSONObject jSONObject = new JSONObject(fetchMessages);
                            if (jSONObject != null) {
                                ConfigurationProvider.getInstance(this.mContext).setLastMessageFetchTime(System.currentTimeMillis());
                                MessagingHandler messagingHandler = MoEMessagingManager.getInstance().getMessagingHandler(this.mContext);
                                if (messagingHandler != null) {
                                    messagingHandler.parsePayloadAndShowPush(this.mContext, jSONObject);
                                }
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("FetchMessagesFromServerTask : response : ");
                        stringBuilder.append(fetchMessages);
                        stringBuilder.append("execute ");
                        Logger.f(stringBuilder.toString(), e);
                        return null;
                    }
                    return null;
                }
            }
            return null;
        } catch (Exception e3) {
            e = e3;
            fetchMessages = null;
            stringBuilder = new StringBuilder();
            stringBuilder.append("FetchMessagesFromServerTask : response : ");
            stringBuilder.append(fetchMessages);
            stringBuilder.append("execute ");
            Logger.f(stringBuilder.toString(), e);
            return null;
        }
    }
}
