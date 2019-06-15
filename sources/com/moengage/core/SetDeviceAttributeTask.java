package com.moengage.core;

import android.content.Context;
import android.support.annotation.NonNull;
import com.moe.pushlibrary.models.Event;
import com.moe.pushlibrary.models.UserAttribute;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;
import org.json.JSONObject;

class SetDeviceAttributeTask extends SDKTask {
    private JSONObject mDeviceAttribute;

    public String getTaskTag() {
        return "SET_DEVICE_ATTRIBUTES";
    }

    public boolean isSynchronous() {
        return false;
    }

    SetDeviceAttributeTask(@NonNull Context context, @NonNull JSONObject jSONObject) {
        super(context);
        this.mDeviceAttribute = jSONObject;
    }

    public TaskResult execute() {
        Logger.v("SetDeviceAttributeTask: executing Task");
        UserAttribute userAttributePoJo = MoEUtils.getUserAttributePoJo(this.mDeviceAttribute);
        if (MoEUtils.shouldSendUserAttribute(userAttributePoJo, userAttributePoJo != null ? MoEUtils.getSavedUserAttribute(this.mContext, userAttributePoJo.userAttributeName) : null)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SetDeviceAttributeTask : setUserAttribute User attribute not yet sent to server will send : ");
            stringBuilder.append(this.mDeviceAttribute.toString());
            Logger.d(stringBuilder.toString());
            MoEDispatcher.getInstance(this.mContext).writeDataPointToStorage(new Event(MoEHelperUtils.getDatapointJSON("EVENT_ACTION_DEVICE_ATTRIBUTE", this.mDeviceAttribute)));
            MoEDAO.getInstance(this.mContext).addOrUpdateUserAttribute(userAttributePoJo);
            this.mTaskResult.setIsSuccess(true);
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("SetDeviceAttributeTask : setUserAttributes already sent once, need not send duplicate attribute : ");
            stringBuilder2.append(this.mDeviceAttribute.toString());
            Logger.d(stringBuilder2.toString());
            this.mTaskResult.setIsSuccess(false);
        }
        Logger.v("SetDeviceAttributeTask: completed Task");
        return this.mTaskResult;
    }
}
