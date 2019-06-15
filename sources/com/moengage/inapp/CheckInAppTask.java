package com.moengage.inapp;

import android.content.Context;
import com.moengage.core.Logger;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;
import com.moengage.inapp.InAppMessage.TYPE;

class CheckInAppTask extends SDKTask {
    public String getTaskTag() {
        return "CHECK_IN_APPS";
    }

    public boolean isSynchronous() {
        return true;
    }

    public CheckInAppTask(Context context) {
        super(context);
    }

    public TaskResult execute() {
        boolean z;
        Logger.v("CheckInAppTask  :execution started");
        InAppMessage inAppMessageToShow = InAppManager.getInstance().getInAppMessageToShow(null, TYPE.GENERAL, this.mContext);
        if (inAppMessageToShow != null) {
            inAppMessageToShow.theComposedView = ViewEngine.getInstance(this.mContext).createInApp(InAppManager.getInstance().getCurrentActivity(), inAppMessageToShow);
            z = true;
        } else {
            z = false;
        }
        Logger.v("CheckInAppTask  :execution completed");
        return createTaskResult(inAppMessageToShow, z);
    }

    public void onPostExecute(TaskResult taskResult) {
        super.onPostExecute(taskResult);
        Logger.v("CheckInAppTask : executing postExecute");
        if (taskResult.isSuccess()) {
            InAppMessage inAppMessage = (InAppMessage) taskResult.getPayload();
            if (inAppMessage != null) {
                InAppManager.getInstance().showInAppMessage(inAppMessage.theComposedView, inAppMessage, false);
            }
        }
        Logger.v("CheckInAppTask : completed postExecute");
    }
}
