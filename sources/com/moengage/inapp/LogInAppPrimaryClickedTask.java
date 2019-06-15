package com.moengage.inapp;

import android.content.Context;
import com.moengage.core.Logger;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;

class LogInAppPrimaryClickedTask extends SDKTask {
    InAppMessage inAppMessage;

    public String getTaskTag() {
        return InAppConstants.EVENT_IN_APP_CLICKED;
    }

    public boolean isSynchronous() {
        return false;
    }

    public LogInAppPrimaryClickedTask(Context context, InAppMessage inAppMessage) {
        super(context);
        this.inAppMessage = inAppMessage;
    }

    public TaskResult execute() {
        Logger.v("LogInAppPrimaryClickedTask : executing task");
        InAppsDAO.getInstance(this.mContext).updateInAppClicked(this.inAppMessage.rules.campaignId);
        Logger.v("LogInAppPrimaryClickedTask : completed task");
        return null;
    }
}
