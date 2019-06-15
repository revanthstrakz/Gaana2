package com.google.firebase.appindexing.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.firebase.appindexing.FirebaseAppIndex;

public final class zzh extends FirebaseAppIndex {
    private static String[] zzes = new String[]{"com.google.android.googlequicksearchbox", "com.google.android.gms"};
    @NonNull
    private final Context zzdc;
    @NonNull
    private final GoogleApi<?> zzet;
    @VisibleForTesting
    private final zzj zzeu;

    public zzh(@NonNull Context context) {
        this(context, new zzi(context));
    }

    @VisibleForTesting
    private zzh(@NonNull Context context, @NonNull GoogleApi<NoOptions> googleApi) {
        this.zzet = googleApi;
        this.zzdc = context;
        this.zzeu = new zzj(googleApi);
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x00a4 A:{Catch:{ ArrayStoreException -> 0x00ec }} */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0075 A:{Catch:{ ArrayStoreException -> 0x00ec }} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x008e A:{Catch:{ ArrayStoreException -> 0x00ec }} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00a4 A:{Catch:{ ArrayStoreException -> 0x00ec }} */
    public final com.google.android.gms.tasks.Task<java.lang.Void> update(com.google.firebase.appindexing.Indexable... r14) {
        /*
        r13 = this;
        r0 = 0;
        r1 = 0;
        if (r14 != 0) goto L_0x0006;
    L_0x0004:
        r2 = r0;
        goto L_0x000d;
    L_0x0006:
        r2 = r14.length;	 Catch:{ ArrayStoreException -> 0x00ec }
        r2 = new com.google.firebase.appindexing.internal.Thing[r2];	 Catch:{ ArrayStoreException -> 0x00ec }
        r3 = r14.length;	 Catch:{ ArrayStoreException -> 0x00ec }
        java.lang.System.arraycopy(r14, r1, r2, r1, r3);	 Catch:{ ArrayStoreException -> 0x00ec }
    L_0x000d:
        r14 = com.google.android.gms.internal.icing.zzgu.zzef();	 Catch:{ ArrayStoreException -> 0x00ec }
        r3 = 1;
        if (r14 == 0) goto L_0x00e2;
    L_0x0014:
        r14 = com.google.android.gms.common.util.PlatformVersion.isAtLeastKitKat();	 Catch:{ ArrayStoreException -> 0x00ec }
        if (r14 == 0) goto L_0x00e2;
    L_0x001a:
        r14 = r13.zzdc;	 Catch:{ ArrayStoreException -> 0x00ec }
        if (r14 == 0) goto L_0x00e2;
    L_0x001e:
        if (r2 == 0) goto L_0x00e2;
    L_0x0020:
        r14 = r2.length;	 Catch:{ ArrayStoreException -> 0x00ec }
        if (r14 <= 0) goto L_0x00e2;
    L_0x0023:
        r14 = r13.zzdc;	 Catch:{ ArrayStoreException -> 0x00ec }
        r4 = android.os.Build.VERSION.SDK_INT;	 Catch:{ ArrayStoreException -> 0x00ec }
        r5 = 28;
        if (r4 < r5) goto L_0x0031;
    L_0x002b:
        r4 = new com.google.firebase.appindexing.internal.zzab;	 Catch:{ ArrayStoreException -> 0x00ec }
        r4.<init>(r14);	 Catch:{ ArrayStoreException -> 0x00ec }
        goto L_0x0036;
    L_0x0031:
        r4 = new com.google.firebase.appindexing.internal.zzaa;	 Catch:{ ArrayStoreException -> 0x00ec }
        r4.<init>(r14);	 Catch:{ ArrayStoreException -> 0x00ec }
    L_0x0036:
        r14 = r2.length;	 Catch:{ ArrayStoreException -> 0x00ec }
        r5 = r1;
    L_0x0038:
        if (r5 >= r14) goto L_0x00e2;
    L_0x003a:
        r6 = r2[r5];	 Catch:{ ArrayStoreException -> 0x00ec }
        if (r6 == 0) goto L_0x00de;
    L_0x003e:
        r7 = r6.zzae();	 Catch:{ ArrayStoreException -> 0x00ec }
        r8 = "sliceUri";
        r9 = r7.zze();	 Catch:{ ArrayStoreException -> 0x00ec }
        if (r9 == 0) goto L_0x0060;
    L_0x004a:
        r9 = r7.zze();	 Catch:{ ArrayStoreException -> 0x00ec }
        r9 = r9.get(r8);	 Catch:{ ArrayStoreException -> 0x00ec }
        r9 = r9 instanceof java.lang.String[];	 Catch:{ ArrayStoreException -> 0x00ec }
        if (r9 != 0) goto L_0x0057;
    L_0x0056:
        goto L_0x0060;
    L_0x0057:
        r7 = r7.zze();	 Catch:{ ArrayStoreException -> 0x00ec }
        r7 = r7.getStringArray(r8);	 Catch:{ ArrayStoreException -> 0x00ec }
        goto L_0x0061;
    L_0x0060:
        r7 = r0;
    L_0x0061:
        if (r7 == 0) goto L_0x0068;
    L_0x0063:
        r8 = r7.length;	 Catch:{ ArrayStoreException -> 0x00ec }
        if (r8 <= 0) goto L_0x0068;
    L_0x0066:
        r8 = r3;
        goto L_0x0069;
    L_0x0068:
        r8 = r1;
    L_0x0069:
        r6 = r6.zzae();	 Catch:{ ArrayStoreException -> 0x00ec }
        r9 = "grantSlicePermission";
        r10 = r6.zze();	 Catch:{ ArrayStoreException -> 0x00ec }
        if (r10 == 0) goto L_0x008b;
    L_0x0075:
        r10 = r6.zze();	 Catch:{ ArrayStoreException -> 0x00ec }
        r10 = r10.get(r9);	 Catch:{ ArrayStoreException -> 0x00ec }
        r10 = r10 instanceof boolean[];	 Catch:{ ArrayStoreException -> 0x00ec }
        if (r10 != 0) goto L_0x0082;
    L_0x0081:
        goto L_0x008b;
    L_0x0082:
        r6 = r6.zze();	 Catch:{ ArrayStoreException -> 0x00ec }
        r6 = r6.getBooleanArray(r9);	 Catch:{ ArrayStoreException -> 0x00ec }
        goto L_0x008c;
    L_0x008b:
        r6 = r0;
    L_0x008c:
        if (r6 == 0) goto L_0x0097;
    L_0x008e:
        r9 = r6.length;	 Catch:{ ArrayStoreException -> 0x00ec }
        if (r9 <= 0) goto L_0x0097;
    L_0x0091:
        r6 = r6[r1];	 Catch:{ ArrayStoreException -> 0x00ec }
        if (r6 == 0) goto L_0x0097;
    L_0x0095:
        r6 = r3;
        goto L_0x0098;
    L_0x0097:
        r6 = r1;
    L_0x0098:
        if (r8 == 0) goto L_0x00de;
    L_0x009a:
        if (r6 == 0) goto L_0x00de;
    L_0x009c:
        r6 = r7[r1];	 Catch:{ ArrayStoreException -> 0x00ec }
        r7 = zzes;	 Catch:{ ArrayStoreException -> 0x00ec }
        r8 = r7.length;	 Catch:{ ArrayStoreException -> 0x00ec }
        r9 = r1;
    L_0x00a2:
        if (r9 >= r8) goto L_0x00de;
    L_0x00a4:
        r10 = r7[r9];	 Catch:{ ArrayStoreException -> 0x00ec }
        r11 = android.net.Uri.parse(r6);	 Catch:{ Exception -> 0x00ae }
        r4.grantSlicePermission(r10, r11);	 Catch:{ Exception -> 0x00ae }
        goto L_0x00db;
    L_0x00ae:
        r10 = move-exception;
        r10 = java.lang.String.valueOf(r10);	 Catch:{ ArrayStoreException -> 0x00ec }
        r11 = 48;
        r12 = java.lang.String.valueOf(r10);	 Catch:{ ArrayStoreException -> 0x00ec }
        r12 = r12.length();	 Catch:{ ArrayStoreException -> 0x00ec }
        r11 = r11 + r12;
        r12 = new java.lang.StringBuilder;	 Catch:{ ArrayStoreException -> 0x00ec }
        r12.<init>(r11);	 Catch:{ ArrayStoreException -> 0x00ec }
        r11 = "Error trying to grant permission to Slice Uris: ";
        r12.append(r11);	 Catch:{ ArrayStoreException -> 0x00ec }
        r12.append(r10);	 Catch:{ ArrayStoreException -> 0x00ec }
        r10 = r12.toString();	 Catch:{ ArrayStoreException -> 0x00ec }
        r11 = 5;
        r11 = com.google.firebase.appindexing.internal.zzu.isLoggable(r11);	 Catch:{ ArrayStoreException -> 0x00ec }
        if (r11 == 0) goto L_0x00db;
    L_0x00d6:
        r11 = "FirebaseAppIndex";
        android.util.Log.w(r11, r10);	 Catch:{ ArrayStoreException -> 0x00ec }
    L_0x00db:
        r9 = r9 + 1;
        goto L_0x00a2;
    L_0x00de:
        r5 = r5 + 1;
        goto L_0x0038;
    L_0x00e2:
        r14 = new com.google.firebase.appindexing.internal.zzx;
        r14.<init>(r3, r2, r0, r0);
        r14 = r13.zza(r14);
        return r14;
    L_0x00ec:
        r14 = new com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException;
        r0 = "Custom Indexable-objects are not allowed. Please use the 'Indexables'-class for creating the objects.";
        r14.<init>(r0);
        r14 = com.google.android.gms.tasks.Tasks.forException(r14);
        return r14;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.appindexing.internal.zzh.update(com.google.firebase.appindexing.Indexable[]):com.google.android.gms.tasks.Task");
    }

    public final Task<Void> remove(String... strArr) {
        return zza(new zzx(3, null, strArr, null));
    }

    public final Task<Void> removeAll() {
        return zza(new zzx(4, null, null, null));
    }

    private final Task<Void> zza(@NonNull zzx zzx) {
        return this.zzeu.zzb(zzx);
    }
}
