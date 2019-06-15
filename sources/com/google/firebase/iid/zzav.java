package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.Queue;

public final class zzav {
    private static zzav zzcx;
    private final SimpleArrayMap<String, String> zzcy = new SimpleArrayMap();
    private Boolean zzcz = null;
    @VisibleForTesting
    final Queue<Intent> zzda = new ArrayDeque();
    @VisibleForTesting
    private final Queue<Intent> zzdb = new ArrayDeque();

    public static synchronized zzav zzai() {
        zzav zzav;
        synchronized (zzav.class) {
            if (zzcx == null) {
                zzcx = new zzav();
            }
            zzav = zzcx;
        }
        return zzav;
    }

    private zzav() {
    }

    public static PendingIntent zza(Context context, int i, Intent intent, int i2) {
        return PendingIntent.getBroadcast(context, i, zza(context, "com.google.firebase.MESSAGING_EVENT", intent), 1073741824);
    }

    public static void zzb(Context context, Intent intent) {
        context.sendBroadcast(zza(context, "com.google.firebase.INSTANCE_ID_EVENT", intent));
    }

    public static void zzc(Context context, Intent intent) {
        context.sendBroadcast(zza(context, "com.google.firebase.MESSAGING_EVENT", intent));
    }

    private static Intent zza(Context context, String str, Intent intent) {
        Intent intent2 = new Intent(context, FirebaseInstanceIdReceiver.class);
        intent2.setAction(str);
        intent2.putExtra("wrapped_intent", intent);
        return intent2;
    }

    public final Intent zzaj() {
        return (Intent) this.zzdb.poll();
    }

    public final int zzb(Context context, String str, Intent intent) {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String str2 = "FirebaseInstanceId";
            String str3 = "Starting service: ";
            String valueOf = String.valueOf(str);
            Log.d(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        }
        Object obj = -1;
        int hashCode = str.hashCode();
        if (hashCode != -842411455) {
            if (hashCode == 41532704 && str.equals("com.google.firebase.MESSAGING_EVENT")) {
                obj = 1;
            }
        } else if (str.equals("com.google.firebase.INSTANCE_ID_EVENT")) {
            obj = null;
        }
        switch (obj) {
            case null:
                this.zzda.offer(intent);
                break;
            case 1:
                this.zzdb.offer(intent);
                break;
            default:
                String str4 = "FirebaseInstanceId";
                String str5 = "Unknown service action: ";
                str = String.valueOf(str);
                Log.w(str4, str.length() != 0 ? str5.concat(str) : new String(str5));
                return 500;
        }
        intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        return zzd(context, intent);
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00dc A:{Catch:{ SecurityException -> 0x013a, IllegalStateException -> 0x0111 }} */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f8 A:{Catch:{ SecurityException -> 0x013a, IllegalStateException -> 0x0111 }} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f3 A:{Catch:{ SecurityException -> 0x013a, IllegalStateException -> 0x0111 }} */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0105 A:{Catch:{ SecurityException -> 0x013a, IllegalStateException -> 0x0111 }} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00dc A:{Catch:{ SecurityException -> 0x013a, IllegalStateException -> 0x0111 }} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f3 A:{Catch:{ SecurityException -> 0x013a, IllegalStateException -> 0x0111 }} */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f8 A:{Catch:{ SecurityException -> 0x013a, IllegalStateException -> 0x0111 }} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0105 A:{Catch:{ SecurityException -> 0x013a, IllegalStateException -> 0x0111 }} */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x010f  */
    private final int zzd(android.content.Context r7, android.content.Intent r8) {
        /*
        r6 = this;
        r0 = r6.zzcy;
        monitor-enter(r0);
        r1 = r6.zzcy;	 Catch:{ all -> 0x0145 }
        r2 = r8.getAction();	 Catch:{ all -> 0x0145 }
        r1 = r1.get(r2);	 Catch:{ all -> 0x0145 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0145 }
        monitor-exit(r0);	 Catch:{ all -> 0x0145 }
        r0 = 0;
        if (r1 != 0) goto L_0x00ac;
    L_0x0013:
        r1 = r7.getPackageManager();
        r1 = r1.resolveService(r8, r0);
        if (r1 == 0) goto L_0x00a4;
    L_0x001d:
        r2 = r1.serviceInfo;
        if (r2 != 0) goto L_0x0023;
    L_0x0021:
        goto L_0x00a4;
    L_0x0023:
        r1 = r1.serviceInfo;
        r2 = r7.getPackageName();
        r3 = r1.packageName;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x006d;
    L_0x0031:
        r2 = r1.name;
        if (r2 != 0) goto L_0x0036;
    L_0x0035:
        goto L_0x006d;
    L_0x0036:
        r1 = r1.name;
        r2 = ".";
        r2 = r1.startsWith(r2);
        if (r2 == 0) goto L_0x005c;
    L_0x0040:
        r2 = r7.getPackageName();
        r2 = java.lang.String.valueOf(r2);
        r1 = java.lang.String.valueOf(r1);
        r3 = r1.length();
        if (r3 == 0) goto L_0x0057;
    L_0x0052:
        r1 = r2.concat(r1);
        goto L_0x005c;
    L_0x0057:
        r1 = new java.lang.String;
        r1.<init>(r2);
    L_0x005c:
        r2 = r6.zzcy;
        monitor-enter(r2);
        r3 = r6.zzcy;	 Catch:{ all -> 0x006a }
        r4 = r8.getAction();	 Catch:{ all -> 0x006a }
        r3.put(r4, r1);	 Catch:{ all -> 0x006a }
        monitor-exit(r2);	 Catch:{ all -> 0x006a }
        goto L_0x00ac;
    L_0x006a:
        r7 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x006a }
        throw r7;
    L_0x006d:
        r2 = "FirebaseInstanceId";
        r3 = r1.packageName;
        r1 = r1.name;
        r4 = 94;
        r5 = java.lang.String.valueOf(r3);
        r5 = r5.length();
        r4 = r4 + r5;
        r5 = java.lang.String.valueOf(r1);
        r5 = r5.length();
        r4 = r4 + r5;
        r5 = new java.lang.StringBuilder;
        r5.<init>(r4);
        r4 = "Error resolving target intent service, skipping classname enforcement. Resolved service was: ";
        r5.append(r4);
        r5.append(r3);
        r3 = "/";
        r5.append(r3);
        r5.append(r1);
        r1 = r5.toString();
        android.util.Log.e(r2, r1);
        goto L_0x00d8;
    L_0x00a4:
        r1 = "FirebaseInstanceId";
        r2 = "Failed to resolve target intent service, skipping classname enforcement";
        android.util.Log.e(r1, r2);
        goto L_0x00d8;
    L_0x00ac:
        r2 = "FirebaseInstanceId";
        r3 = 3;
        r2 = android.util.Log.isLoggable(r2, r3);
        if (r2 == 0) goto L_0x00d1;
    L_0x00b5:
        r2 = "FirebaseInstanceId";
        r3 = "Restricting intent to a specific service: ";
        r4 = java.lang.String.valueOf(r1);
        r5 = r4.length();
        if (r5 == 0) goto L_0x00c8;
    L_0x00c3:
        r3 = r3.concat(r4);
        goto L_0x00ce;
    L_0x00c8:
        r4 = new java.lang.String;
        r4.<init>(r3);
        r3 = r4;
    L_0x00ce:
        android.util.Log.d(r2, r3);
    L_0x00d1:
        r2 = r7.getPackageName();
        r8.setClassName(r2, r1);
    L_0x00d8:
        r1 = r6.zzcz;	 Catch:{ SecurityException -> 0x013a, IllegalStateException -> 0x0111 }
        if (r1 != 0) goto L_0x00eb;
    L_0x00dc:
        r1 = "android.permission.WAKE_LOCK";
        r1 = r7.checkCallingOrSelfPermission(r1);	 Catch:{ SecurityException -> 0x013a, IllegalStateException -> 0x0111 }
        if (r1 != 0) goto L_0x00e5;
    L_0x00e4:
        r0 = 1;
    L_0x00e5:
        r0 = java.lang.Boolean.valueOf(r0);	 Catch:{ SecurityException -> 0x013a, IllegalStateException -> 0x0111 }
        r6.zzcz = r0;	 Catch:{ SecurityException -> 0x013a, IllegalStateException -> 0x0111 }
    L_0x00eb:
        r0 = r6.zzcz;	 Catch:{ SecurityException -> 0x013a, IllegalStateException -> 0x0111 }
        r0 = r0.booleanValue();	 Catch:{ SecurityException -> 0x013a, IllegalStateException -> 0x0111 }
        if (r0 == 0) goto L_0x00f8;
    L_0x00f3:
        r7 = android.support.v4.content.WakefulBroadcastReceiver.startWakefulService(r7, r8);	 Catch:{ SecurityException -> 0x013a, IllegalStateException -> 0x0111 }
        goto L_0x0103;
    L_0x00f8:
        r7 = r7.startService(r8);	 Catch:{ SecurityException -> 0x013a, IllegalStateException -> 0x0111 }
        r8 = "FirebaseInstanceId";
        r0 = "Missing wake lock permission, service start may be delayed";
        android.util.Log.d(r8, r0);	 Catch:{ SecurityException -> 0x013a, IllegalStateException -> 0x0111 }
    L_0x0103:
        if (r7 != 0) goto L_0x010f;
    L_0x0105:
        r7 = "FirebaseInstanceId";
        r8 = "Error while delivering the message: ServiceIntent not found.";
        android.util.Log.e(r7, r8);	 Catch:{ SecurityException -> 0x013a, IllegalStateException -> 0x0111 }
        r7 = 404; // 0x194 float:5.66E-43 double:1.996E-321;
        return r7;
    L_0x010f:
        r7 = -1;
        return r7;
    L_0x0111:
        r7 = move-exception;
        r8 = "FirebaseInstanceId";
        r7 = java.lang.String.valueOf(r7);
        r0 = 45;
        r1 = java.lang.String.valueOf(r7);
        r1 = r1.length();
        r0 = r0 + r1;
        r1 = new java.lang.StringBuilder;
        r1.<init>(r0);
        r0 = "Failed to start service while in background: ";
        r1.append(r0);
        r1.append(r7);
        r7 = r1.toString();
        android.util.Log.e(r8, r7);
        r7 = 402; // 0x192 float:5.63E-43 double:1.986E-321;
        return r7;
    L_0x013a:
        r7 = move-exception;
        r8 = "FirebaseInstanceId";
        r0 = "Error while delivering the message to the serviceIntent";
        android.util.Log.e(r8, r0, r7);
        r7 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        return r7;
    L_0x0145:
        r7 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0145 }
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzav.zzd(android.content.Context, android.content.Intent):int");
    }
}
