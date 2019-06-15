package com.google.android.exoplayer2.ui;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;

public final class DownloadNotificationUtil {
    @StringRes
    private static final int NULL_STRING_ID = 0;

    private DownloadNotificationUtil() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0060  */
    public static android.app.Notification buildProgressNotification(android.content.Context r17, @android.support.annotation.DrawableRes int r18, java.lang.String r19, @android.support.annotation.Nullable android.app.PendingIntent r20, @android.support.annotation.Nullable java.lang.String r21, com.google.android.exoplayer2.offline.DownloadManager.TaskState[] r22) {
        /*
        r0 = r22;
        r1 = 1;
        r2 = 0;
        r3 = 0;
        r4 = r0.length;
        r10 = r1;
        r5 = r2;
        r6 = r5;
        r8 = r6;
        r9 = r8;
        r7 = r3;
        r3 = r9;
    L_0x000d:
        if (r3 >= r4) goto L_0x0042;
    L_0x000f:
        r11 = r0[r3];
        r12 = r11.state;
        if (r12 == r1) goto L_0x001b;
    L_0x0015:
        r12 = r11.state;
        r13 = 2;
        if (r12 == r13) goto L_0x001b;
    L_0x001a:
        goto L_0x003f;
    L_0x001b:
        r12 = r11.action;
        r12 = r12.isRemoveAction;
        if (r12 == 0) goto L_0x0023;
    L_0x0021:
        r6 = r1;
        goto L_0x003f;
    L_0x0023:
        r5 = r11.downloadPercentage;
        r12 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r5 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1));
        if (r5 == 0) goto L_0x002f;
    L_0x002b:
        r5 = r11.downloadPercentage;
        r7 = r7 + r5;
        r10 = r2;
    L_0x002f:
        r11 = r11.downloadedBytes;
        r13 = 0;
        r5 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1));
        if (r5 <= 0) goto L_0x0039;
    L_0x0037:
        r5 = r1;
        goto L_0x003a;
    L_0x0039:
        r5 = r2;
    L_0x003a:
        r5 = r5 | r8;
        r9 = r9 + 1;
        r8 = r5;
        r5 = r1;
    L_0x003f:
        r3 = r3 + 1;
        goto L_0x000d;
    L_0x0042:
        if (r5 == 0) goto L_0x0049;
    L_0x0044:
        r0 = com.google.android.exoplayer2.ui.R.string.exo_download_downloading;
    L_0x0046:
        r16 = r0;
        goto L_0x0050;
    L_0x0049:
        if (r6 == 0) goto L_0x004e;
    L_0x004b:
        r0 = com.google.android.exoplayer2.ui.R.string.exo_download_removing;
        goto L_0x0046;
    L_0x004e:
        r16 = r2;
    L_0x0050:
        r11 = r17;
        r12 = r18;
        r13 = r19;
        r14 = r20;
        r15 = r21;
        r0 = newNotificationBuilder(r11, r12, r13, r14, r15, r16);
        if (r5 == 0) goto L_0x006b;
    L_0x0060:
        r3 = (float) r9;
        r7 = r7 / r3;
        r3 = (int) r7;
        if (r10 == 0) goto L_0x0069;
    L_0x0065:
        if (r8 == 0) goto L_0x0069;
    L_0x0067:
        r4 = r1;
        goto L_0x006d;
    L_0x0069:
        r4 = r2;
        goto L_0x006d;
    L_0x006b:
        r4 = r1;
        r3 = r2;
    L_0x006d:
        r5 = 100;
        r0.setProgress(r5, r3, r4);
        r0.setOngoing(r1);
        r0.setShowWhen(r2);
        r0 = r0.build();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.DownloadNotificationUtil.buildProgressNotification(android.content.Context, int, java.lang.String, android.app.PendingIntent, java.lang.String, com.google.android.exoplayer2.offline.DownloadManager$TaskState[]):android.app.Notification");
    }

    public static Notification buildDownloadCompletedNotification(Context context, @DrawableRes int i, String str, @Nullable PendingIntent pendingIntent, @Nullable String str2) {
        return newNotificationBuilder(context, i, str, pendingIntent, str2, R.string.exo_download_completed).build();
    }

    public static Notification buildDownloadFailedNotification(Context context, @DrawableRes int i, String str, @Nullable PendingIntent pendingIntent, @Nullable String str2) {
        return newNotificationBuilder(context, i, str, pendingIntent, str2, R.string.exo_download_failed).build();
    }

    private static Builder newNotificationBuilder(Context context, @DrawableRes int i, String str, @Nullable PendingIntent pendingIntent, @Nullable String str2, @StringRes int i2) {
        Builder smallIcon = new Builder(context, str).setSmallIcon(i);
        if (i2 != 0) {
            smallIcon.setContentTitle(context.getResources().getString(i2));
        }
        if (pendingIntent != null) {
            smallIcon.setContentIntent(pendingIntent);
        }
        if (str2 != null) {
            smallIcon.setStyle(new BigTextStyle().bigText(str2));
        }
        return smallIcon;
    }
}
