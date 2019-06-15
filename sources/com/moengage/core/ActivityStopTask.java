package com.moengage.core;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.moe.pushlibrary.MoEHelper;
import com.moe.pushlibrary.MoEWorker;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;
import com.moengage.inapp.InAppController;
import com.moengage.inapp.InAppController.InAppHandler;
import com.moengage.push.MoEMessagingManager;
import com.moengage.push.MoEMessagingManager.MessagingHandler;

class ActivityStopTask extends SDKTask {
    private int DATA_SYNC_JOB_ID = 99999;
    private String mCurrentActivityName;

    public String getTaskTag() {
        return "ACTIVITY_STOP";
    }

    public boolean isSynchronous() {
        return false;
    }

    ActivityStopTask(Context context, String str) {
        super(context);
        this.mCurrentActivityName = str;
    }

    public TaskResult execute() {
        StringBuilder stringBuilder;
        Logger.v("ActivityStopTask : executing task");
        if (!TextUtils.isEmpty(this.mCurrentActivityName)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("ActivityLifecycleStop : ");
            stringBuilder.append(this.mCurrentActivityName);
            stringBuilder.append(" stopped");
            Logger.v(stringBuilder.toString());
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("ActivityStopTask: activity counter ");
        stringBuilder.append(MoEHelper.getActivityCounter());
        Logger.d(stringBuilder.toString());
        if (MoEHelper.getActivityCounter() == 0) {
            Logger.v("ActivityStopTask: Activity counter zero, will try to send interaction data");
            MoEDispatcher.getInstance(this.mContext).shutDownPeriodicFlush();
            InAppHandler inAppHandler = InAppController.getInstance().getInAppHandler();
            if (inAppHandler != null) {
                inAppHandler.writeInAppCheckFailureCounterToStorage(this.mContext);
            }
            if (VERSION.SDK_INT < 26) {
                scheduleDataSending();
            } else {
                scheduleDataSendingJob();
            }
            MessagingHandler messagingHandler = MoEMessagingManager.getInstance().getMessagingHandler(this.mContext);
            if (messagingHandler != null) {
                messagingHandler.scheduleMessageSync(this.mContext);
            }
            MoEDispatcher.getInstance(this.mContext).resetStates();
            ConfigurationProvider.getInstance(this.mContext).storeSentScreenList();
        }
        Logger.v("ActivityStopTask : completed execution");
        return null;
    }

    private void scheduleDataSending() {
        Logger.v("ActivityStopTask: scheduleDataSending()");
        PendingIntent broadcast = PendingIntent.getBroadcast(this.mContext, MoEWorker.REQ_CODE_SEND_DATA, new Intent(this.mContext, MoEAlarmReceiver.class), 134217728);
        AlarmManager alarmManager = (AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (alarmManager != null) {
            alarmManager.set(0, System.currentTimeMillis() + 3000, broadcast);
        }
    }

    @TargetApi(21)
    private void scheduleDataSendingJob() {
        Logger.v("ActivityStopTask: scheduleDataSendingJob()");
        Builder builder = new Builder(this.DATA_SYNC_JOB_ID, new ComponentName(this.mContext, DataSyncJob.class));
        builder.setRequiredNetworkType(1);
        builder.setOverrideDeadline(System.currentTimeMillis() + DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
        builder.setMinimumLatency(AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        JobScheduler jobScheduler = (JobScheduler) this.mContext.getSystemService("jobscheduler");
        if (jobScheduler != null) {
            jobScheduler.schedule(builder.build());
        }
    }
}
