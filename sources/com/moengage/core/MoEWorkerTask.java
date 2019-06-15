package com.moengage.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;

public class MoEWorkerTask extends SDKTask {
    private Bundle extras;
    private String workerTaskType;

    public String getTaskTag() {
        return "MOE_WORKER_TASK";
    }

    public boolean isSynchronous() {
        return false;
    }

    public MoEWorkerTask(Context context, @NonNull String str, @Nullable Bundle bundle) {
        super(context);
        this.workerTaskType = str;
        this.extras = bundle;
    }

    public TaskResult execute() {
        try {
            Logger.v("MoEWorkerTask: executing task");
            if (TextUtils.isEmpty(this.workerTaskType)) {
                return null;
            }
            String str = this.workerTaskType;
            boolean z = true;
            int hashCode = str.hashCode();
            boolean z2 = false;
            if (hashCode != -2043999862) {
                if (hashCode == 157915335) {
                    if (str.equals(MoEConstants.SERVICE_TYPE_APP_UPDATE)) {
                        z = false;
                    }
                }
            } else if (str.equals("LOGOUT")) {
                z = true;
            }
            switch (z) {
                case false:
                    MoEDispatcher.getInstance(this.mContext).handleAppUpdateEvent();
                    break;
                case true:
                    if (this.extras != null && this.extras.containsKey("IS_FORCE_LOGOUT")) {
                        z2 = this.extras.getBoolean("IS_FORCE_LOGOUT");
                    }
                    MoEDispatcher.getInstance(this.mContext).handleLogout(z2);
                    break;
                default:
                    Logger.e("Not a valid task type");
                    break;
            }
            Logger.v("MoEWorkerTask: completed task");
            return null;
        } catch (Exception e) {
            Logger.e("MoEWorkerTask: execute() ", e);
        }
    }
}
