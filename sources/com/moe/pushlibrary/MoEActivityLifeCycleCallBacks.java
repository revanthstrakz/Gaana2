package com.moe.pushlibrary;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.moengage.core.Logger;

@TargetApi(14)
public class MoEActivityLifeCycleCallBacks implements ActivityLifecycleCallbacks {
    private MoEHelper moEHelper;

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        try {
            initializeMoEngageHelper(activity);
        } catch (Exception e) {
            Logger.f("MoEActivityLifeCycleCallBacks : onActivityCreated", e);
        }
    }

    public void onActivityStarted(Activity activity) {
        try {
            initializeMoEngageHelper(activity);
            if (this.moEHelper != null) {
                this.moEHelper.onStartInternal(activity);
            }
        } catch (Exception e) {
            Logger.f("MoEActivityLifeCycleCallBacks : onActivityStarted", e);
        }
    }

    public void onActivityResumed(Activity activity) {
        try {
            initializeMoEngageHelper(activity);
            if (this.moEHelper != null) {
                this.moEHelper.onResumeInternal(activity);
            }
        } catch (Exception e) {
            Logger.f("MoEActivityLifeCycleCallBacks : onActivityResumed", e);
        }
    }

    public void onActivityStopped(Activity activity) {
        try {
            initializeMoEngageHelper(activity);
            if (this.moEHelper != null) {
                this.moEHelper.onStopInternal(activity);
            }
        } catch (Exception e) {
            Logger.f("MoEActivityLifeCycleCallBacks : onActivityStopped", e);
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        try {
            initializeMoEngageHelper(activity);
            if (this.moEHelper != null) {
                this.moEHelper.onSaveInstanceState(bundle);
            }
        } catch (Exception e) {
            Logger.f("MoEActivityLifeCycleCallBacks : onActivitySaveInstanceState", e);
        }
    }

    private void initializeMoEngageHelper(Activity activity) {
        if (this.moEHelper == null && activity != null) {
            this.moEHelper = MoEHelper.getInstance(activity.getApplicationContext());
        }
    }
}
