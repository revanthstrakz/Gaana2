package com.google.firebase.iid;

import android.support.annotation.GuardedBy;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;
import java.util.Map;

final class zzba {
    @GuardedBy("itself")
    private final zzaw zzaj;
    @GuardedBy("this")
    private int zzdl = 0;
    @GuardedBy("this")
    private final Map<Integer, TaskCompletionSource<Void>> zzdm = new ArrayMap();

    zzba(zzaw zzaw) {
        this.zzaj = zzaw;
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    public final synchronized Task<Void> zza(String str) {
        TaskCompletionSource taskCompletionSource;
        String zzak;
        int i;
        synchronized (this.zzaj) {
            zzak = this.zzaj.zzak();
            zzaw zzaw = this.zzaj;
            StringBuilder stringBuilder = new StringBuilder((String.valueOf(zzak).length() + 1) + String.valueOf(str).length());
            stringBuilder.append(zzak);
            stringBuilder.append(",");
            stringBuilder.append(str);
            zzaw.zzf(stringBuilder.toString());
        }
        taskCompletionSource = new TaskCompletionSource();
        Map map = this.zzdm;
        if (TextUtils.isEmpty(zzak)) {
            i = 0;
        } else {
            i = zzak.split(",").length - 1;
        }
        map.put(Integer.valueOf(this.zzdl + i), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    public final synchronized boolean zzaq() {
        return zzar() != null;
    }

    /* Access modifiers changed, original: final */
    /* JADX WARNING: Missing block: B:8:0x0016, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:11:0x001c, code skipped:
            if (zza(r5, r0) != false) goto L_0x0020;
     */
    /* JADX WARNING: Missing block: B:13:0x001f, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:14:0x0020, code skipped:
            monitor-enter(r4);
     */
    /* JADX WARNING: Missing block: B:16:?, code skipped:
            r2 = (com.google.android.gms.tasks.TaskCompletionSource) r4.zzdm.remove(java.lang.Integer.valueOf(r4.zzdl));
            zzk(r0);
            r4.zzdl++;
     */
    /* JADX WARNING: Missing block: B:17:0x0037, code skipped:
            monitor-exit(r4);
     */
    /* JADX WARNING: Missing block: B:18:0x0038, code skipped:
            if (r2 == null) goto L_0x0000;
     */
    /* JADX WARNING: Missing block: B:19:0x003a, code skipped:
            r2.setResult(null);
     */
    @android.support.annotation.WorkerThread
    public final boolean zzc(com.google.firebase.iid.FirebaseInstanceId r5) {
        /*
        r4 = this;
    L_0x0000:
        monitor-enter(r4);
        r0 = r4.zzar();	 Catch:{ all -> 0x0042 }
        r1 = 1;
        if (r0 != 0) goto L_0x0017;
    L_0x0008:
        r5 = com.google.firebase.iid.FirebaseInstanceId.zzl();	 Catch:{ all -> 0x0042 }
        if (r5 == 0) goto L_0x0015;
    L_0x000e:
        r5 = "FirebaseInstanceId";
        r0 = "topic sync succeeded";
        android.util.Log.d(r5, r0);	 Catch:{ all -> 0x0042 }
    L_0x0015:
        monitor-exit(r4);	 Catch:{ all -> 0x0042 }
        return r1;
    L_0x0017:
        monitor-exit(r4);	 Catch:{ all -> 0x0042 }
        r2 = zza(r5, r0);
        if (r2 != 0) goto L_0x0020;
    L_0x001e:
        r5 = 0;
        return r5;
    L_0x0020:
        monitor-enter(r4);
        r2 = r4.zzdm;	 Catch:{ all -> 0x003f }
        r3 = r4.zzdl;	 Catch:{ all -> 0x003f }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ all -> 0x003f }
        r2 = r2.remove(r3);	 Catch:{ all -> 0x003f }
        r2 = (com.google.android.gms.tasks.TaskCompletionSource) r2;	 Catch:{ all -> 0x003f }
        r4.zzk(r0);	 Catch:{ all -> 0x003f }
        r0 = r4.zzdl;	 Catch:{ all -> 0x003f }
        r0 = r0 + r1;
        r4.zzdl = r0;	 Catch:{ all -> 0x003f }
        monitor-exit(r4);	 Catch:{ all -> 0x003f }
        if (r2 == 0) goto L_0x0000;
    L_0x003a:
        r0 = 0;
        r2.setResult(r0);
        goto L_0x0000;
    L_0x003f:
        r5 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x003f }
        throw r5;
    L_0x0042:
        r5 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0042 }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzba.zzc(com.google.firebase.iid.FirebaseInstanceId):boolean");
    }

    @Nullable
    @GuardedBy("this")
    private final String zzar() {
        String zzak;
        synchronized (this.zzaj) {
            zzak = this.zzaj.zzak();
        }
        if (!TextUtils.isEmpty(zzak)) {
            String[] split = zzak.split(",");
            if (split.length > 1 && !TextUtils.isEmpty(split[1])) {
                return split[1];
            }
        }
        return null;
    }

    private final synchronized boolean zzk(String str) {
        synchronized (this.zzaj) {
            String zzak = this.zzaj.zzak();
            String valueOf = String.valueOf(",");
            String valueOf2 = String.valueOf(str);
            if (zzak.startsWith(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))) {
                valueOf = String.valueOf(",");
                str = String.valueOf(str);
                this.zzaj.zzf(zzak.substring((str.length() != 0 ? valueOf.concat(str) : new String(valueOf)).length()));
                return true;
            }
            return false;
        }
    }

    @WorkerThread
    private static boolean zza(FirebaseInstanceId firebaseInstanceId, String str) {
        String[] split = str.split("!");
        if (split.length == 2) {
            String str2 = split[0];
            str = split[1];
            int i = -1;
            try {
                int hashCode = str2.hashCode();
                if (hashCode != 83) {
                    if (hashCode == 85) {
                        if (str2.equals("U")) {
                            i = 1;
                        }
                    }
                } else if (str2.equals("S")) {
                    i = 0;
                }
                switch (i) {
                    case 0:
                        firebaseInstanceId.zzb(str);
                        if (FirebaseInstanceId.zzl()) {
                            Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                            break;
                        }
                        break;
                    case 1:
                        firebaseInstanceId.zzc(str);
                        if (FirebaseInstanceId.zzl()) {
                            Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
                            break;
                        }
                        break;
                    default:
                        break;
                }
            } catch (IOException e) {
                str = "FirebaseInstanceId";
                String str3 = "Topic sync failed: ";
                String valueOf = String.valueOf(e.getMessage());
                Log.e(str, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                return false;
            }
        }
        return true;
    }
}
