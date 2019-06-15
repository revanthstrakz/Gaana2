package com.moengage.core;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;
import com.til.colombia.android.internal.e;
import org.json.JSONObject;

class SendLogEntriesTask extends SDKTask {
    private String API = "https://webhook.logentries.com/noformat/logs/";
    private String mErrorMessage;
    private String mErrorType;
    private Throwable mThrowable;

    public String getTaskTag() {
        return "SEND_LOG";
    }

    public boolean isSynchronous() {
        return false;
    }

    SendLogEntriesTask(Context context, String str, Throwable th, String str2) {
        super(context);
        this.mThrowable = th;
        this.mErrorMessage = str;
        this.mErrorType = str2;
    }

    public TaskResult execute() {
        Logger.v("SendLogEntriesTask : executing Task");
        ConfigurationProvider instance = ConfigurationProvider.getInstance(this.mContext);
        if (instance == null) {
            return null;
        }
        try {
            if (instance.isLogEntryEnabled()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("uid", instance.getCurrentUserId());
                jSONObject.put(e.A, MoEUtils.addDebugIfRequired(this.mContext, instance.getAppId()));
                jSONObject.put("sdk_ver", MoEHelperConstants.LIB_VERSION);
                jSONObject.put("tm_s", System.currentTimeMillis());
                if (!TextUtils.isEmpty(this.mErrorType)) {
                    jSONObject.put("|v|", this.mErrorType);
                }
                if (!TextUtils.isEmpty(this.mErrorMessage)) {
                    jSONObject.put("msg", this.mErrorMessage);
                }
                String stackTraceString = Log.getStackTraceString(this.mThrowable);
                if (!TextUtils.isEmpty(stackTraceString)) {
                    jSONObject.put("tb", stackTraceString);
                }
                Context context = this.mContext;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.API);
                stringBuilder.append(instance.getLogEntryKey());
                APIManager.uploadLogsToLogEntries(context, stringBuilder.toString(), jSONObject);
            } else {
                Logger.v("SendLogEnteriesTask : LogEnteries disabled");
            }
        } catch (Exception unused) {
        }
        Logger.v("SendLogEntriesTask : execution completed");
        return null;
    }
}
