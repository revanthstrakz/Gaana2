package com.moengage.core;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import com.moe.pushlibrary.MoEHelper;

@TargetApi(21)
public class ScheduledDataSyncJob extends JobService {
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public boolean onStartJob(JobParameters jobParameters) {
        MoEHelper.getInstance(getApplicationContext()).syncInteractionDataNow();
        return true;
    }
}
