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
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;

class SendInteractionDataTask extends SDKTask {
    public String getTaskTag() {
        return SDKTask.TAG_SEND_INTERACTION_DATA;
    }

    public boolean isSynchronous() {
        return true;
    }

    SendInteractionDataTask(Context context) {
        super(context);
    }

    public TaskResult execute() {
        try {
            if (!ConfigurationProvider.getInstance(this.mContext).isAppEnabled()) {
                return null;
            }
            Logger.v("SendInteractionDataTask : executing task");
            sendInteractionData();
            if (VERSION.SDK_INT >= 21) {
                scheduleRetryDataSyncJob();
            } else {
                scheduleRetryDataSyncAlarm();
            }
            Logger.v("SendInteractionDataTask : completed task");
            return null;
        } catch (Exception e) {
            Logger.f("SendInteractionData: execute() ", e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0093 A:{LOOP_END, LOOP:0: B:4:0x000c->B:26:0x0093} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0092 A:{SYNTHETIC} */
    private void sendInteractionData() {
        /*
        r7 = this;
        r0 = "/v2/report/add";
        r1 = r7.shouldSendDataToTestServer();
        r2 = 0;
        if (r1 == 0) goto L_0x000b;
    L_0x0009:
        r0 = "/integration/send_report_add_call";
    L_0x000b:
        r1 = r2;
    L_0x000c:
        r3 = r7.mContext;
        r3 = com.moengage.core.MoEDAO.getInstance(r3);
        r4 = 100;
        r3 = r3.getBatchedData(r4);
        r4 = "SendInteractionDataTask : sendInteractionData:Fetching interaction data in batches";
        com.moengage.core.Logger.d(r4);
        if (r3 == 0) goto L_0x0098;
    L_0x001f:
        r4 = r3.isEmpty();
        if (r4 == 0) goto L_0x0027;
    L_0x0025:
        goto L_0x0098;
    L_0x0027:
        r4 = r3.iterator();
    L_0x002b:
        r5 = r4.hasNext();
        if (r5 == 0) goto L_0x0090;
    L_0x0031:
        r1 = r4.next();
        r1 = (com.moe.pushlibrary.models.BatchData) r1;
        r5 = r7.mContext;	 Catch:{ Exception -> 0x0040 }
        r6 = r1.batchData;	 Catch:{ Exception -> 0x0040 }
        r5 = com.moengage.core.APIManager.sendInteractionReport(r5, r6, r0);	 Catch:{ Exception -> 0x0040 }
        goto L_0x0047;
    L_0x0040:
        r5 = move-exception;
        r6 = "SendInteractionDataTask : API failed";
        com.moengage.core.Logger.f(r6, r5);
        r5 = r2;
    L_0x0047:
        if (r5 == 0) goto L_0x0059;
    L_0x0049:
        r6 = "SendInteractionDataTask : Batch sent successfully deleting batch";
        com.moengage.core.Logger.d(r6);
        r6 = r7.mContext;
        r6 = com.moengage.core.MoEDAO.getInstance(r6);
        r6.deleteBatch(r1);
        r1 = r5;
        goto L_0x002b;
    L_0x0059:
        r1 = r7.mContext;
        r1 = com.moengage.core.ConfigurationProvider.getInstance(r1);
        r1 = r1.getImmediateRetryCount();
        switch(r1) {
            case 0: goto L_0x0080;
            case 1: goto L_0x0070;
            default: goto L_0x0066;
        };
    L_0x0066:
        r1 = r7.mContext;
        r1 = com.moengage.core.ConfigurationProvider.getInstance(r1);
        r1.setImmediateRetryCount(r2);
        goto L_0x008f;
    L_0x0070:
        r4 = 3;
        r7.scheduleImmediateRetry(r4);
        r4 = r7.mContext;
        r4 = com.moengage.core.ConfigurationProvider.getInstance(r4);
        r1 = r1 + 1;
        r4.setImmediateRetryCount(r1);
        goto L_0x008f;
    L_0x0080:
        r4 = 1;
        r7.scheduleImmediateRetry(r4);
        r4 = r7.mContext;
        r4 = com.moengage.core.ConfigurationProvider.getInstance(r4);
        r1 = r1 + 1;
        r4.setImmediateRetryCount(r1);
    L_0x008f:
        r1 = r5;
    L_0x0090:
        if (r1 != 0) goto L_0x0093;
    L_0x0092:
        return;
    L_0x0093:
        r3.clear();
        goto L_0x000c;
    L_0x0098:
        r0 = "SendInteractionDataTask : sendInteractionData: Found Nothing to send";
        com.moengage.core.Logger.d(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moengage.core.SendInteractionDataTask.sendInteractionData():void");
    }

    private boolean shouldSendDataToTestServer() {
        return ConfigurationProvider.getInstance(this.mContext).isDeviceRegisteredForVerification() && ConfigurationProvider.getInstance(this.mContext).getVerificationRegistrationTime() + 3600000 > System.currentTimeMillis();
    }

    private void scheduleRetryDataSyncAlarm() {
        Logger.v("Scheduling data sync retry");
        ((AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM)).setInexactRepeating(0, System.currentTimeMillis() + ConfigurationProvider.getInstance(this.mContext).getRetrySyncTime(), ConfigurationProvider.getInstance(this.mContext).getRetrySyncTime(), PendingIntent.getBroadcast(this.mContext, 88888, new Intent(this.mContext, MoEAlarmReceiver.class), 134217728));
    }

    @TargetApi(21)
    private void scheduleRetryDataSyncJob() {
        Logger.v("Scheduling retry data sync job");
        Builder builder = new Builder(77777, new ComponentName(this.mContext, DataSyncJob.class));
        builder.setRequiredNetworkType(1);
        builder.setOverrideDeadline(System.currentTimeMillis() + (ConfigurationProvider.getInstance(this.mContext).getRetrySyncTime() * 2));
        builder.setMinimumLatency(ConfigurationProvider.getInstance(this.mContext).getRetrySyncTime());
        ((JobScheduler) this.mContext.getSystemService("jobscheduler")).schedule(builder.build());
    }

    @TargetApi(21)
    private void scheduleImmediateRetrySyncJob(int i) {
        Logger.v("Scheduling immediate retry data sync job");
        Builder builder = new Builder(666666, new ComponentName(this.mContext, DataSyncJob.class));
        builder.setRequiredNetworkType(1);
        builder.setOverrideDeadline(System.currentTimeMillis() + ((long) (((i * 2) * 60) * 1000)));
        builder.setMinimumLatency((long) ((i * 60) * 1000));
        JobScheduler jobScheduler = (JobScheduler) this.mContext.getSystemService("jobscheduler");
        if (jobScheduler != null) {
            jobScheduler.schedule(builder.build());
        }
    }

    private void scheduleImmediateRetryAlarm(int i) {
        PendingIntent broadcast = PendingIntent.getBroadcast(this.mContext, 55555, new Intent(this.mContext, MoEAlarmReceiver.class), 134217728);
        AlarmManager alarmManager = (AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (alarmManager != null) {
            alarmManager.set(0, System.currentTimeMillis() + ((long) ((i * 60) * 1000)), broadcast);
        }
    }

    private void scheduleImmediateRetry(int i) {
        if (VERSION.SDK_INT >= 21) {
            scheduleImmediateRetrySyncJob(i);
        } else {
            scheduleImmediateRetryAlarm(i);
        }
    }
}
