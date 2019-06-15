package com.moengage.core;

import android.content.Context;
import com.moe.pushlibrary.models.Event;
import com.moe.pushlibrary.models.UserAttribute;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;
import org.json.JSONObject;

class SetAliasTask extends SDKTask {
    private JSONObject aliasJSON;

    public String getTaskTag() {
        return "SET_ALIAS";
    }

    public boolean isSynchronous() {
        return true;
    }

    SetAliasTask(Context context, JSONObject jSONObject) {
        super(context);
        this.aliasJSON = jSONObject;
    }

    public TaskResult execute() {
        try {
            Logger.v("SetAliasTask: executing alias task");
            UserAttribute userAttributePoJo = MoEUtils.getUserAttributePoJo(this.aliasJSON);
            UserAttribute userAttributesForKey = MoEDAO.getInstance(this.mContext).getUserAttributesForKey(MoEHelperConstants.USER_ATTRIBUTE_UNIQUE_ID);
            if (userAttributesForKey == null) {
                MoEDispatcher.getInstance(this.mContext).addTaskToQueue(new SetUserAttributeTask(this.mContext, this.aliasJSON, true));
                return null;
            } else if (userAttributesForKey.equals(userAttributePoJo)) {
                Logger.v("SetAliasTask: execute() current unique id same as same existing no need to update");
                return null;
            } else {
                MoEDAO.getInstance(this.mContext).addOrUpdateUserAttribute(userAttributePoJo);
                this.aliasJSON.put(MoEHelperConstants.USER_ID_MODIFIED_FROM, userAttributesForKey.userAttributeValue);
                MoEDispatcher.getInstance(this.mContext).writeDataPointToStorage(new Event(MoEHelperUtils.getDatapointJSON("EVENT_ACTION_USER_ATTRIBUTE", this.aliasJSON)));
                Logger.v("SetAliasTask: completed alias task");
                return null;
            }
        } catch (Exception e) {
            Logger.v("SetAliasTask: execute() ", e);
        }
    }
}
