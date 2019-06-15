package com.moengage.core;

import android.content.Context;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;

class NotificationClickedTask extends SDKTask {
    private long time;

    public String getTaskTag() {
        return "NOTIFICATION_CLICKED";
    }

    public boolean isSynchronous() {
        return false;
    }

    NotificationClickedTask(Context context, long j) {
        super(context);
        this.time = j;
    }

    public TaskResult execute() {
        Logger.v("NotificationClickedTask : executing task");
        MoEDAO.getInstance(this.mContext).setMessageClickedByTime(this.time);
        Logger.v("NotificationClickedTask : completed task");
        return null;
    }
}
