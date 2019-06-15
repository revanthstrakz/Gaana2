package com.moengage.inapp;

import android.content.Context;
import com.moengage.core.Logger;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;

class ShowLinkedInAppTask extends SDKTask {
    private String mCampaignId;

    public String getTaskTag() {
        return "SHOW_LINKED_IN_APP";
    }

    public boolean isSynchronous() {
        return true;
    }

    public ShowLinkedInAppTask(Context context, String str) {
        super(context);
        this.mCampaignId = str;
    }

    public TaskResult execute() {
        Logger.v("ShowLinkedInAppTask : executing task");
        InAppMessage fetchSingleInApp = InAppManager.getInstance().fetchSingleInApp(this.mContext, this.mCampaignId);
        if (fetchSingleInApp != null) {
            fetchSingleInApp.theComposedView = ViewEngine.getInstance(this.mContext).createInApp(InAppManager.getInstance().getCurrentActivity(), fetchSingleInApp);
        }
        Logger.v("ShowLinkedInAppTask : completed execution");
        return null;
    }

    public void onPostExecute(TaskResult taskResult) {
        super.onPostExecute(taskResult);
        Logger.v("ShowLinkedInAppTask : executing onPostExecute");
        if (taskResult.isSuccess()) {
            InAppMessage inAppMessage = (InAppMessage) taskResult.getPayload();
            if (inAppMessage != null) {
                InAppManager.getInstance().showInAppMessage(inAppMessage.theComposedView, inAppMessage, false);
            }
        }
    }
}
