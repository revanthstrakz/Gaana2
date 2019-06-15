package com.moengage.inapp;

import android.content.Context;
import com.moengage.core.Logger;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;
import com.moengage.inapp.InAppManager.InAppMessageListener;
import com.moengage.inapp.InAppMessage.TYPE;

public class ShowSelfHandledInAppTask extends SDKTask {
    public String getTaskTag() {
        return "SHOW_SELF_HANDLED_INAPP";
    }

    public boolean isSynchronous() {
        return true;
    }

    public ShowSelfHandledInAppTask(Context context) {
        super(context);
    }

    public TaskResult execute() {
        Logger.v("ShowSelfHandledInAppTask : executing task");
        InAppMessage inAppMessageToShow = InAppManager.getInstance().getInAppMessageToShow(null, TYPE.SELF_HANDLED, this.mContext);
        InAppMessageListener inAppMessageListener = InAppManager.getInstance().getInAppMessageListener();
        if (!(inAppMessageToShow == null || inAppMessageListener == null || !inAppMessageListener.showInAppMessage(inAppMessageToShow))) {
            InAppTracker.getInstance(this.mContext).inAppShown(inAppMessageToShow);
        }
        Logger.v("ShowSelfHandledInAppTask : completed task");
        return null;
    }
}
