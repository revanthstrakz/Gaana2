package com.moengage.core.executor;

import android.content.Context;

public abstract class SDKTask implements ITask {
    public static final String TAG_ACTIVITY_START = "START_ACTIVITY";
    protected static final String TAG_ACTIVITY_STOP = "ACTIVITY_STOP";
    protected static final String TAG_CHECK_AND_SHOW_NUDGE = "CHECK_AND_SHOW_NUDGE";
    protected static final String TAG_CHECK_IN_APPS = "CHECK_IN_APPS";
    protected static final String TAG_CREATE_DATA_BATCH = "CREATE_DATA_BATCH";
    protected static final String TAG_DEVICE_ADD = "DEVICE_ADD";
    protected static final String TAG_FETCH_MESSAGES = "FETCH_MESSAGES";
    protected static final String TAG_GEO_TASK = "GEO_TASK";
    public static final String TAG_INAPP_NETWORK_TASK = "INAPP_NETWORK_TASK";
    public static final String TAG_INTEGRATION_VERIFICATION_NETWORK_TASK = "INTEGRATION_VERIFICATION_NETWORK_TASK";
    protected static final String TAG_LOG_IN_APP_CLICKED = "IN_APP_CLICKED";
    protected static final String TAG_MOE_PUSH_WORKER_TASK = "MOE_PUSH_WORKER_TASK";
    protected static final String TAG_MOE_WORKER_TASK = "MOE_WORKER_TASK";
    protected static final String TAG_NOTIFICATION_CLICKED = "NOTIFICATION_CLICKED";
    public static final String TAG_SEND_INTERACTION_DATA = "SEND_INTERACTION_DATA";
    protected static final String TAG_SEND_LOG = "SEND_LOG";
    protected static final String TAG_SET_ALIAS = "SET_ALIAS";
    protected static final String TAG_SET_DEVICE_ATTRIBUTES = "SET_DEVICE_ATTRIBUTES";
    public static final String TAG_SET_USER_ATTRIBUTES = "SET_USER_ATTRIBUTES";
    protected static final String TAG_SHOW_IN_APP_ON_CONFIG_CHANGE = "SHOW_IN_APP_ON_CONFIG_CHANGE";
    protected static final String TAG_SHOW_LINKED_IN_APP = "SHOW_LINKED_IN_APP";
    protected static final String TAG_SHOW_SELF_HANDLED_INAPP = "SHOW_SELF_HANDLED_INAPP";
    protected static final String TAG_SYNC_CONFIG_API = "SYNC_CONFIG";
    protected static final String TAG_TRACK_EVENT = "TRACK_EVENT";
    protected Context mContext;
    protected TaskResult mTaskResult = new TaskResult();

    public void onPostExecute(TaskResult taskResult) {
    }

    public SDKTask(Context context) {
        this.mContext = context;
        this.mTaskResult.setIsSuccess(false);
    }

    public TaskResult createTaskResult(Object obj, boolean z) {
        this.mTaskResult.setPayload(obj);
        this.mTaskResult.setIsSuccess(z);
        return this.mTaskResult;
    }
}
