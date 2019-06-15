package com.moengage.pushbase.push;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.moengage.core.ConfigurationProvider;
import com.moengage.core.Logger;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;
import com.moengage.push.PushManager;
import com.moengage.push.PushManager.PushHandler;

public class MoEPushWorkerTask extends SDKTask {
    private Bundle extras;
    private String workerTaskType;

    public String getTaskTag() {
        return "MOE_PUSH_WORKER_TASK";
    }

    public boolean isSynchronous() {
        return false;
    }

    public MoEPushWorkerTask(Context context, @NonNull String str, @Nullable Bundle bundle) {
        super(context);
        this.workerTaskType = str;
        this.extras = bundle;
    }

    public TaskResult execute() {
        try {
            Logger.v("MoEPushWorkerTask: executing task");
            if (TextUtils.isEmpty(this.workerTaskType)) {
                return null;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("MoEPushWorkerTask: executing ");
            stringBuilder.append(this.workerTaskType);
            Logger.v(stringBuilder.toString());
            String str = this.workerTaskType;
            Object obj = -1;
            int hashCode = str.hashCode();
            if (hashCode != -2035429155) {
                if (hashCode != 436702423) {
                    if (hashCode != 1164413677) {
                        if (hashCode == 2126682772) {
                            if (str.equals(PushManager.REQ_REFRESH)) {
                                obj = null;
                            }
                        }
                    } else if (str.equals("SHOW_NOTIFICATION")) {
                        obj = 2;
                    }
                } else if (str.equals(PushManager.REQ_REGISTRATION)) {
                    obj = 3;
                }
            } else if (str.equals(PushManager.REG_ON_APP_OPEN)) {
                obj = 1;
            }
            switch (obj) {
                case null:
                    ConfigurationProvider.getInstance(this.mContext).setGCMToken("");
                    registerForPush();
                    break;
                case 1:
                    handlePushRegistrationOnAppOpen();
                    break;
                case 2:
                    PushHandler pushHandler = PushManager.getInstance().getPushHandler();
                    if (pushHandler != null) {
                        pushHandler.handlePushPayload(this.mContext, this.extras);
                        break;
                    }
                    break;
                case 3:
                    registerForPush();
                    break;
                default:
                    break;
            }
            Logger.v("MoEPushWorkerTask: completed task");
            return null;
        } catch (Exception e) {
            Logger.e("MoEPushWorkerTask: execute() ", e);
        }
    }

    private void registerForPush() {
        PushHandler pushHandler = PushManager.getInstance().getPushHandler();
        if (pushHandler != null) {
            String registerForPushToken = pushHandler.registerForPushToken(this.mContext);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("MoEPushWorkerTask registerForPush(): registerForPush ");
            stringBuilder.append(registerForPushToken);
            Logger.v(stringBuilder.toString());
        }
    }

    private void handlePushRegistrationOnAppOpen() {
        if (!ConfigurationProvider.getInstance(this.mContext).isGCMRegistrationDisabled()) {
            PushHandler pushHandler = PushManager.getInstance().getPushHandler();
            if (pushHandler != null) {
                pushHandler.getPushToken(this.mContext);
            }
        }
    }
}
