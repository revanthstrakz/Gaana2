package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.firebase.iid.zzav;
import com.google.firebase.iid.zzb;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

public class FirebaseMessagingService extends zzb {
    private static final Queue<String> zzdr = new ArrayDeque(10);

    @WorkerThread
    public void onDeletedMessages() {
    }

    @WorkerThread
    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    @WorkerThread
    public void onMessageSent(String str) {
    }

    @WorkerThread
    public void onNewToken(String str) {
    }

    @WorkerThread
    public void onSendError(String str, Exception exception) {
    }

    /* Access modifiers changed, original: protected|final */
    public final Intent zzb(Intent intent) {
        return zzav.zzai().zzaj();
    }

    public final boolean zzc(Intent intent) {
        if (!"com.google.firebase.messaging.NOTIFICATION_OPEN".equals(intent.getAction())) {
            return false;
        }
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("pending_intent");
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (CanceledException unused) {
                Log.e("FirebaseMessaging", "Notification pending intent canceled");
            }
        }
        if (MessagingAnalytics.shouldUploadMetrics(intent)) {
            MessagingAnalytics.logNotificationOpen(intent);
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:66:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00cd  */
    /* JADX WARNING: Missing block: B:54:0x00f7, code skipped:
            if (r0.equals("send_event") == false) goto L_0x0118;
     */
    public final void zzd(android.content.Intent r10) {
        /*
        r9 = this;
        r0 = r10.getAction();
        r1 = "com.google.android.c2dm.intent.RECEIVE";
        r1 = r1.equals(r0);
        if (r1 != 0) goto L_0x0059;
    L_0x000c:
        r1 = "com.google.firebase.messaging.RECEIVE_DIRECT_BOOT";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0015;
    L_0x0014:
        goto L_0x0059;
    L_0x0015:
        r1 = "com.google.firebase.messaging.NOTIFICATION_DISMISS";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0027;
    L_0x001d:
        r0 = com.google.firebase.messaging.MessagingAnalytics.shouldUploadMetrics(r10);
        if (r0 == 0) goto L_0x0058;
    L_0x0023:
        com.google.firebase.messaging.MessagingAnalytics.logNotificationDismiss(r10);
        return;
    L_0x0027:
        r1 = "com.google.firebase.messaging.NEW_TOKEN";
        r0 = r1.equals(r0);
        if (r0 == 0) goto L_0x0039;
    L_0x002f:
        r0 = "token";
        r10 = r10.getStringExtra(r0);
        r9.onNewToken(r10);
        return;
    L_0x0039:
        r0 = "FirebaseMessaging";
        r1 = "Unknown intent action: ";
        r10 = r10.getAction();
        r10 = java.lang.String.valueOf(r10);
        r2 = r10.length();
        if (r2 == 0) goto L_0x0050;
    L_0x004b:
        r10 = r1.concat(r10);
        goto L_0x0055;
    L_0x0050:
        r10 = new java.lang.String;
        r10.<init>(r1);
    L_0x0055:
        android.util.Log.d(r0, r10);
    L_0x0058:
        return;
    L_0x0059:
        r0 = "google.message_id";
        r0 = r10.getStringExtra(r0);
        r1 = android.text.TextUtils.isEmpty(r0);
        r2 = 2;
        if (r1 == 0) goto L_0x006c;
    L_0x0066:
        r1 = 0;
        r1 = com.google.android.gms.tasks.Tasks.forResult(r1);
        goto L_0x007e;
    L_0x006c:
        r1 = new android.os.Bundle;
        r1.<init>();
        r3 = "google.message_id";
        r1.putString(r3, r0);
        r3 = com.google.firebase.iid.zzab.zzc(r9);
        r1 = r3.zza(r2, r1);
    L_0x007e:
        r3 = android.text.TextUtils.isEmpty(r0);
        r4 = 1;
        r5 = 3;
        r6 = 0;
        if (r3 == 0) goto L_0x0089;
    L_0x0087:
        r0 = r6;
        goto L_0x00cb;
    L_0x0089:
        r3 = zzdr;
        r3 = r3.contains(r0);
        if (r3 == 0) goto L_0x00b6;
    L_0x0091:
        r3 = "FirebaseMessaging";
        r3 = android.util.Log.isLoggable(r3, r5);
        if (r3 == 0) goto L_0x00b4;
    L_0x0099:
        r3 = "FirebaseMessaging";
        r7 = "Received duplicate message: ";
        r0 = java.lang.String.valueOf(r0);
        r8 = r0.length();
        if (r8 == 0) goto L_0x00ac;
    L_0x00a7:
        r0 = r7.concat(r0);
        goto L_0x00b1;
    L_0x00ac:
        r0 = new java.lang.String;
        r0.<init>(r7);
    L_0x00b1:
        android.util.Log.d(r3, r0);
    L_0x00b4:
        r0 = r4;
        goto L_0x00cb;
    L_0x00b6:
        r3 = zzdr;
        r3 = r3.size();
        r7 = 10;
        if (r3 < r7) goto L_0x00c5;
    L_0x00c0:
        r3 = zzdr;
        r3.remove();
    L_0x00c5:
        r3 = zzdr;
        r3.add(r0);
        goto L_0x0087;
    L_0x00cb:
        if (r0 != 0) goto L_0x019e;
    L_0x00cd:
        r0 = "message_type";
        r0 = r10.getStringExtra(r0);
        if (r0 != 0) goto L_0x00d7;
    L_0x00d5:
        r0 = "gcm";
    L_0x00d7:
        r3 = -1;
        r7 = r0.hashCode();
        r8 = -2062414158; // 0xffffffff85120eb2 float:-6.867586E-36 double:NaN;
        if (r7 == r8) goto L_0x010e;
    L_0x00e1:
        r4 = 102161; // 0x18f11 float:1.43158E-40 double:5.0474E-319;
        if (r7 == r4) goto L_0x0104;
    L_0x00e6:
        r4 = 814694033; // 0x308f3e91 float:1.0422402E-9 double:4.025123336E-315;
        if (r7 == r4) goto L_0x00fa;
    L_0x00eb:
        r4 = 814800675; // 0x3090df23 float:1.0540798E-9 double:4.025650217E-315;
        if (r7 == r4) goto L_0x00f1;
    L_0x00f0:
        goto L_0x0118;
    L_0x00f1:
        r4 = "send_event";
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x0118;
    L_0x00f9:
        goto L_0x0119;
    L_0x00fa:
        r2 = "send_error";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x0118;
    L_0x0102:
        r2 = r5;
        goto L_0x0119;
    L_0x0104:
        r2 = "gcm";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x0118;
    L_0x010c:
        r2 = r6;
        goto L_0x0119;
    L_0x010e:
        r2 = "deleted_messages";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x0118;
    L_0x0116:
        r2 = r4;
        goto L_0x0119;
    L_0x0118:
        r2 = r3;
    L_0x0119:
        switch(r2) {
            case 0: goto L_0x015a;
            case 1: goto L_0x0156;
            case 2: goto L_0x014c;
            case 3: goto L_0x012f;
            default: goto L_0x011c;
        };
    L_0x011c:
        r10 = "FirebaseMessaging";
        r2 = "Received message with unknown type: ";
        r0 = java.lang.String.valueOf(r0);
        r3 = r0.length();
        if (r3 == 0) goto L_0x0196;
    L_0x012a:
        r0 = r2.concat(r0);
        goto L_0x019b;
    L_0x012f:
        r0 = "google.message_id";
        r0 = r10.getStringExtra(r0);
        if (r0 != 0) goto L_0x013d;
    L_0x0137:
        r0 = "message_id";
        r0 = r10.getStringExtra(r0);
    L_0x013d:
        r2 = new com.google.firebase.messaging.SendException;
        r3 = "error";
        r10 = r10.getStringExtra(r3);
        r2.<init>(r10);
        r9.onSendError(r0, r2);
        goto L_0x019e;
    L_0x014c:
        r0 = "google.message_id";
        r10 = r10.getStringExtra(r0);
        r9.onMessageSent(r10);
        goto L_0x019e;
    L_0x0156:
        r9.onDeletedMessages();
        goto L_0x019e;
    L_0x015a:
        r0 = com.google.firebase.messaging.MessagingAnalytics.shouldUploadMetrics(r10);
        if (r0 == 0) goto L_0x0163;
    L_0x0160:
        com.google.firebase.messaging.MessagingAnalytics.logNotificationReceived(r10);
    L_0x0163:
        r0 = r10.getExtras();
        if (r0 != 0) goto L_0x016e;
    L_0x0169:
        r0 = new android.os.Bundle;
        r0.<init>();
    L_0x016e:
        r2 = "android.support.content.wakelockid";
        r0.remove(r2);
        r2 = com.google.firebase.messaging.zza.zzf(r0);
        if (r2 == 0) goto L_0x018d;
    L_0x0179:
        r2 = new com.google.firebase.messaging.zza;
        r2.<init>(r9);
        r2 = r2.zzh(r0);
        if (r2 != 0) goto L_0x019e;
    L_0x0184:
        r2 = com.google.firebase.messaging.MessagingAnalytics.shouldUploadMetrics(r10);
        if (r2 == 0) goto L_0x018d;
    L_0x018a:
        com.google.firebase.messaging.MessagingAnalytics.logNotificationForeground(r10);
    L_0x018d:
        r10 = new com.google.firebase.messaging.RemoteMessage;
        r10.<init>(r0);
        r9.onMessageReceived(r10);
        goto L_0x019e;
    L_0x0196:
        r0 = new java.lang.String;
        r0.<init>(r2);
    L_0x019b:
        android.util.Log.w(r10, r0);
    L_0x019e:
        r2 = 1;
        r10 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x01a6, InterruptedException | ExecutionException | TimeoutException -> 0x01a6, InterruptedException | ExecutionException | TimeoutException -> 0x01a6 }
        com.google.android.gms.tasks.Tasks.await(r1, r2, r10);	 Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x01a6, InterruptedException | ExecutionException | TimeoutException -> 0x01a6, InterruptedException | ExecutionException | TimeoutException -> 0x01a6 }
        return;
    L_0x01a6:
        r10 = move-exception;
        r0 = "FirebaseMessaging";
        r10 = java.lang.String.valueOf(r10);
        r1 = 20;
        r2 = java.lang.String.valueOf(r10);
        r2 = r2.length();
        r1 = r1 + r2;
        r2 = new java.lang.StringBuilder;
        r2.<init>(r1);
        r1 = "Message ack failed: ";
        r2.append(r1);
        r2.append(r10);
        r10 = r2.toString();
        android.util.Log.w(r0, r10);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.FirebaseMessagingService.zzd(android.content.Intent):void");
    }

    static void zzj(Bundle bundle) {
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && str.startsWith("google.c.")) {
                it.remove();
            }
        }
    }
}
