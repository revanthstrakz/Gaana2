package com.moengage.core;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;

@TargetApi(21)
public class DataSyncJob extends JobService {
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public boolean onStartJob(JobParameters jobParameters) {
        Logger.v("DataSyncJob: onStartJob");
        MoEDispatcher.getInstance(getApplicationContext()).sendInteractionData();
        return true;
    }
}
