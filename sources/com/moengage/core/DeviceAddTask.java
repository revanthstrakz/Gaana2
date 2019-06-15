package com.moengage.core;

import android.content.Context;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;

public class DeviceAddTask extends SDKTask {
    public String getTaskTag() {
        return "DEVICE_ADD";
    }

    public boolean isSynchronous() {
        return true;
    }

    public DeviceAddTask(Context context) {
        super(context);
    }

    public TaskResult execute() {
        Logger.v("DeviceAddTask : execution started");
        if (APIManager.addDevice(this.mContext)) {
            ConfigurationProvider.getInstance(this.mContext).setDeviceRegistered(true);
            MoEUtils.saveCurrentExponentialCounter(this.mContext, 1);
            MoEDispatcher.getInstance(this.mContext).cancelRegistrationFallback();
            this.mTaskResult.setIsSuccess(true);
        }
        Logger.v("DeviceAddTask : execution completed");
        return this.mTaskResult;
    }
}
