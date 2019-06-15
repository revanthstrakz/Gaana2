package com.moengage.core;

import android.content.Context;
import com.moe.pushlibrary.models.Event;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;

class TrackEventTask extends SDKTask {
    Event event;

    public String getTaskTag() {
        return "TRACK_EVENT";
    }

    public boolean isSynchronous() {
        return false;
    }

    TrackEventTask(Context context, Event event) {
        super(context);
        this.event = event;
    }

    public TaskResult execute() {
        Logger.v("TrackEventTask : executing task");
        MoEDAO.getInstance(this.mContext).addEvent(this.event, this.mContext);
        MoEEventManager.getInstance(this.mContext).incrementEventCounter();
        if (this.event.details.contains(MoEHelperConstants.USER_ATTRIBUTE_UNIQUE_ID)) {
            Logger.d("Unique Id set, So will try to send data");
            MoEDispatcher.getInstance(this.mContext).addTaskToQueue(new CreatingDataBatchTask(this.mContext));
        } else if (MoEEventManager.getInstance(this.mContext).getEventCounter() == ConfigurationProvider.getInstance(this.mContext).getEventBatchCount()) {
            MoEDispatcher.getInstance(this.mContext).addTaskToQueue(new CreatingDataBatchTask(this.mContext));
            MoEEventManager.getInstance(this.mContext).setEventCounter(0);
        }
        Logger.v("TrackEventTask : completed execution");
        return null;
    }
}
