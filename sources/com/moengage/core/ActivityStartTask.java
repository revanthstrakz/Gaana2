package com.moengage.core;

import android.content.Context;
import android.text.TextUtils;
import com.moe.pushlibrary.MoEHelper;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.core.AdvertisingIdClient.AdInfo;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;
import java.util.List;

class ActivityStartTask extends SDKTask {
    private String mCurrentActivityName;
    private boolean mNeedToCheckForGAIDChange;

    public String getTaskTag() {
        return SDKTask.TAG_ACTIVITY_START;
    }

    public boolean isSynchronous() {
        return false;
    }

    ActivityStartTask(Context context, String str, boolean z) {
        super(context);
        this.mContext = context;
        this.mCurrentActivityName = str;
        this.mNeedToCheckForGAIDChange = z;
    }

    public TaskResult execute() {
        Logger.v("ActivityStartTask : started execution");
        if (!TextUtils.isEmpty(this.mCurrentActivityName)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ActivityLifecycleStart : ");
            stringBuilder.append(this.mCurrentActivityName);
            stringBuilder.append(" started");
            Logger.v(stringBuilder.toString());
            if (MoEHelper.getActivityCounter() == 1 && this.mNeedToCheckForGAIDChange) {
                MoEUtils.trackActivityStates(MoEConstants.EVENT_ACTION_ACTIVITY_START, this.mCurrentActivityName, this.mContext);
            } else if (!isActivityTracked()) {
                MoEUtils.trackActivityStates(MoEConstants.EVENT_ACTION_ACTIVITY_START, this.mCurrentActivityName, this.mContext);
                ConfigurationProvider.getInstance(this.mContext).addScreenToSentList(this.mCurrentActivityName);
            }
        }
        if (this.mNeedToCheckForGAIDChange) {
            MoEDAO.getInstance(this.mContext).removeExpiredData();
            checkGoogleAdvertisementIDAndUpdate();
            String installReferrer = MoEHelperUtils.getInstallReferrer(this.mContext);
            if (!TextUtils.isEmpty(installReferrer)) {
                MoEUtils.setUserAttributeInternal(this.mContext, MoEHelperConstants.PREF_KEY_INSTALL_REFERRER, installReferrer);
                MoEHelperUtils.removeInstallReferrer(this.mContext);
            }
        } else {
            Logger.v("ActivityStartTask : No Need to check GAID");
        }
        this.mTaskResult.setIsSuccess(true);
        this.mTaskResult.setPayload(Boolean.valueOf(this.mNeedToCheckForGAIDChange));
        Logger.v("ActivityStartTask : completed execution");
        return this.mTaskResult;
    }

    private void checkGoogleAdvertisementIDAndUpdate() {
        this.mNeedToCheckForGAIDChange = false;
        ConfigurationProvider instance = ConfigurationProvider.getInstance(this.mContext);
        if (instance.isAdIdCollectionProhibitted()) {
            Logger.f("ActivityStartTask : Opted out of GAID Collection");
            return;
        }
        String storedGAID = instance.getStoredGAID();
        int storedISLAT = instance.getStoredISLAT();
        AdInfo advertisementInfo = MoEUtils.getAdvertisementInfo(this.mContext);
        if (advertisementInfo != null) {
            if (TextUtils.isEmpty(advertisementInfo.getId()) || (!TextUtils.isEmpty(storedGAID) && advertisementInfo.getId().equals(storedGAID))) {
                this.mNeedToCheckForGAIDChange = true;
            } else {
                MoEUtils.setUserAttributeInternal(this.mContext, MoEConstants.ATTR_MOE_GAID, advertisementInfo.getId());
                instance.storeGAID(advertisementInfo.getId());
            }
            if (advertisementInfo.isLimitAdTrackingEnabled() != storedISLAT) {
                MoEUtils.setUserAttributeInternal(this.mContext, "MOE_ISLAT", Integer.toString(advertisementInfo.isLimitAdTrackingEnabled()));
                instance.storeISLAT(advertisementInfo.isLimitAdTrackingEnabled());
            } else {
                this.mNeedToCheckForGAIDChange = true;
            }
        } else {
            this.mNeedToCheckForGAIDChange = true;
        }
    }

    private boolean isActivityTracked() {
        boolean z = false;
        try {
            List sentScreenList = ConfigurationProvider.getInstance(this.mContext).getSentScreenList();
            if (sentScreenList != null && sentScreenList.contains(this.mCurrentActivityName)) {
                z = true;
            }
            return z;
        } catch (Exception e) {
            Logger.f("ActivityStartTask: isActivityTracked : ", e);
            return false;
        }
    }
}
