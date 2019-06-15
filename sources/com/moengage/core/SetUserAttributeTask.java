package com.moengage.core;

import android.content.Context;
import android.support.annotation.NonNull;
import com.moe.pushlibrary.models.Event;
import com.moe.pushlibrary.models.UserAttribute;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;
import org.json.JSONObject;

class SetUserAttributeTask extends SDKTask {
    private boolean shouldCacheUserAttribute;
    private JSONObject userJSON;

    public String getTaskTag() {
        return SDKTask.TAG_SET_USER_ATTRIBUTES;
    }

    public boolean isSynchronous() {
        return false;
    }

    SetUserAttributeTask(@NonNull Context context, @NonNull JSONObject jSONObject, boolean z) {
        super(context);
        this.userJSON = jSONObject;
        this.shouldCacheUserAttribute = z;
    }

    public TaskResult execute() {
        Logger.v("SetUserAttributeTask: executing Task");
        UserAttribute userAttributePoJo = MoEUtils.getUserAttributePoJo(this.userJSON);
        if (this.shouldCacheUserAttribute) {
            UserAttribute savedUserAttribute = userAttributePoJo != null ? MoEUtils.getSavedUserAttribute(this.mContext, userAttributePoJo.userAttributeName) : null;
            if (MoEUtils.shouldSendUserAttribute(userAttributePoJo, savedUserAttribute)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SetUserAttributeTask : setUserAttribute User attribute not yet sent to server will send : ");
                stringBuilder.append(this.userJSON.toString());
                Logger.d(stringBuilder.toString());
                if (userAttributePoJo == null || savedUserAttribute == null || !userAttributePoJo.userAttributeName.equals(MoEHelperConstants.USER_ATTRIBUTE_UNIQUE_ID)) {
                    writeUserAttributeToStorage(userAttributePoJo);
                    MoEDAO.getInstance(this.mContext).addOrUpdateUserAttribute(userAttributePoJo);
                    this.mTaskResult.setIsSuccess(true);
                } else {
                    MoEDispatcher.getInstance(this.mContext).handleLogout(true);
                    return createTaskResult(this.userJSON, false);
                }
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("SetUserAttributeTask : setUserAttributes already sent once, need not send duplicate attribute : ");
            stringBuilder2.append(this.userJSON.toString());
            Logger.d(stringBuilder2.toString());
            this.mTaskResult.setIsSuccess(true);
        } else {
            writeUserAttributeToStorage(userAttributePoJo);
        }
        Logger.v("SetUserAttributeTask: completed Task");
        return this.mTaskResult;
    }

    private void writeUserAttributeToStorage(@NonNull UserAttribute userAttribute) {
        if (userAttribute != null) {
            MoEDispatcher.getInstance(this.mContext).writeDataPointToStorage(new Event(MoEHelperUtils.getDatapointJSON("EVENT_ACTION_USER_ATTRIBUTE", this.userJSON)));
        }
    }
}
