package com.moengage.inapp;

import android.content.Context;
import com.moengage.core.Logger;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;

class ShowInAppOnConfigChangeTask extends SDKTask {
    InAppMessage inAppMessage;

    public String getTaskTag() {
        return "SHOW_IN_APP_ON_CONFIG_CHANGE";
    }

    public boolean isSynchronous() {
        return true;
    }

    public ShowInAppOnConfigChangeTask(Context context, InAppMessage inAppMessage) {
        super(context);
        this.inAppMessage = inAppMessage;
        this.mContext = context;
    }

    public TaskResult execute() {
        Logger.v("ShowInAppOnConfigChangeTask : executing task");
        if (this.inAppMessage != null) {
            this.inAppMessage.theComposedView = ViewEngine.getInstance(this.mContext).createInApp(InAppManager.getInstance().getCurrentActivity(), this.inAppMessage);
            createTaskResult(this.inAppMessage, true);
        }
        Logger.v("ShowInAppOnConfigChangeTask : completed execution");
        return this.mTaskResult;
    }

    public void onPostExecute(TaskResult taskResult) {
        super.onPostExecute(taskResult);
        Logger.v("ShowInAppOnConfigChangeTask : executing onPostExecute");
        if (taskResult.isSuccess() && this.inAppMessage != null) {
            InAppManager.getInstance().showInAppMessage(this.inAppMessage.theComposedView, this.inAppMessage, true);
        }
        Logger.v("ShowInAppOnConfigChangeTask : completed onPostExecute");
    }
}
