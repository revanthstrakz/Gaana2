package com.moengage.addon.messaging;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import com.moe.pushlibrary.MoEHelper;
import com.moengage.core.Logger;

@TargetApi(21)
public class MoEMessageSyncJob extends JobService {
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public boolean onStartJob(JobParameters jobParameters) {
        Logger.v("MoEMessageSyncJob: onStartJob()");
        MessagingHandlerImpl.getInstance().scheduleAndSyncMessages(getApplicationContext());
        MoEHelper.getInstance(getApplicationContext()).syncInteractionDataNow();
        return true;
    }
}
