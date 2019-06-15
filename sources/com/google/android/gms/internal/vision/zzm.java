package com.google.android.gms.internal.vision;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;

public abstract class zzm<T> {
    private static String PREFIX = "com.google.android.gms.vision.dynamite";
    private final Object lock = new Object();
    private final String tag;
    private final String zzdh;
    private final String zzdi;
    private boolean zzdj = false;
    private T zzdk;
    private final Context zze;

    public zzm(Context context, String str, String str2) {
        this.zze = context;
        this.tag = str;
        String str3 = PREFIX;
        StringBuilder stringBuilder = new StringBuilder((1 + String.valueOf(str3).length()) + String.valueOf(str2).length());
        stringBuilder.append(str3);
        stringBuilder.append(".");
        stringBuilder.append(str2);
        this.zzdh = stringBuilder.toString();
        this.zzdi = PREFIX;
    }

    public abstract T zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, LoadingException;

    public abstract void zzm() throws RemoteException;

    public final boolean isOperational() {
        return zzq() != null;
    }

    public final void zzp() {
        synchronized (this.lock) {
            if (this.zzdk == null) {
                return;
            }
            try {
                zzm();
            } catch (RemoteException e) {
                Log.e(this.tag, "Could not finalize native handle", e);
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0017 A:{Splitter:B:12:0x0019, ExcHandler: RemoteException (r1_3 'e' android.os.RemoteException)} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0019 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(12:8|9|10|12|13|(2:14|15)|(1:20)|23|(2:28|(1:32))(1:27)|33|34|35) */
    /* JADX WARNING: Missing block: B:11:0x0017, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:22:?, code skipped:
            android.util.Log.e(r5.tag, "Error creating remote native handle", r1);
     */
    public final T zzq() {
        /*
        r5 = this;
        r0 = r5.lock;
        monitor-enter(r0);
        r1 = r5.zzdk;	 Catch:{ all -> 0x006c }
        if (r1 == 0) goto L_0x000b;
    L_0x0007:
        r1 = r5.zzdk;	 Catch:{ all -> 0x006c }
        monitor-exit(r0);	 Catch:{ all -> 0x006c }
        return r1;
    L_0x000b:
        r1 = 0;
        r2 = r5.zze;	 Catch:{ LoadingException -> 0x0019 }
        r3 = com.google.android.gms.dynamite.DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION;	 Catch:{ LoadingException -> 0x0019 }
        r4 = r5.zzdh;	 Catch:{ LoadingException -> 0x0019 }
        r2 = com.google.android.gms.dynamite.DynamiteModule.load(r2, r3, r4);	 Catch:{ LoadingException -> 0x0019 }
        goto L_0x0034;
    L_0x0017:
        r1 = move-exception;
        goto L_0x003f;
    L_0x0019:
        r2 = r5.tag;	 Catch:{ RemoteException -> 0x0017, RemoteException -> 0x0017 }
        r3 = "Cannot load feature, fall back to load whole module.";
        android.util.Log.d(r2, r3);	 Catch:{ RemoteException -> 0x0017, RemoteException -> 0x0017 }
        r2 = r5.zze;	 Catch:{ LoadingException -> 0x002b }
        r3 = com.google.android.gms.dynamite.DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION;	 Catch:{ LoadingException -> 0x002b }
        r4 = r5.zzdi;	 Catch:{ LoadingException -> 0x002b }
        r2 = com.google.android.gms.dynamite.DynamiteModule.load(r2, r3, r4);	 Catch:{ LoadingException -> 0x002b }
        goto L_0x0034;
    L_0x002b:
        r2 = move-exception;
        r3 = r5.tag;	 Catch:{ RemoteException -> 0x0017, RemoteException -> 0x0017 }
        r4 = "Error Loading module";
        android.util.Log.e(r3, r4, r2);	 Catch:{ RemoteException -> 0x0017, RemoteException -> 0x0017 }
        r2 = r1;
    L_0x0034:
        if (r2 == 0) goto L_0x0046;
    L_0x0036:
        r1 = r5.zze;	 Catch:{ RemoteException -> 0x0017, RemoteException -> 0x0017 }
        r1 = r5.zza(r2, r1);	 Catch:{ RemoteException -> 0x0017, RemoteException -> 0x0017 }
        r5.zzdk = r1;	 Catch:{ RemoteException -> 0x0017, RemoteException -> 0x0017 }
        goto L_0x0046;
    L_0x003f:
        r2 = r5.tag;	 Catch:{ all -> 0x006c }
        r3 = "Error creating remote native handle";
        android.util.Log.e(r2, r3, r1);	 Catch:{ all -> 0x006c }
    L_0x0046:
        r1 = r5.zzdj;	 Catch:{ all -> 0x006c }
        if (r1 != 0) goto L_0x0059;
    L_0x004a:
        r1 = r5.zzdk;	 Catch:{ all -> 0x006c }
        if (r1 != 0) goto L_0x0059;
    L_0x004e:
        r1 = r5.tag;	 Catch:{ all -> 0x006c }
        r2 = "Native handle not yet available. Reverting to no-op handle.";
        android.util.Log.w(r1, r2);	 Catch:{ all -> 0x006c }
        r1 = 1;
        r5.zzdj = r1;	 Catch:{ all -> 0x006c }
        goto L_0x0068;
    L_0x0059:
        r1 = r5.zzdj;	 Catch:{ all -> 0x006c }
        if (r1 == 0) goto L_0x0068;
    L_0x005d:
        r1 = r5.zzdk;	 Catch:{ all -> 0x006c }
        if (r1 == 0) goto L_0x0068;
    L_0x0061:
        r1 = r5.tag;	 Catch:{ all -> 0x006c }
        r2 = "Native handle is now available.";
        android.util.Log.w(r1, r2);	 Catch:{ all -> 0x006c }
    L_0x0068:
        r1 = r5.zzdk;	 Catch:{ all -> 0x006c }
        monitor-exit(r0);	 Catch:{ all -> 0x006c }
        return r1;
    L_0x006c:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x006c }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzm.zzq():java.lang.Object");
    }
}
