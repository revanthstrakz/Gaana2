package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public final class zzbo extends zzau {
    private volatile String zzup;
    private Future<String> zzyd;

    protected zzbo(zzaw zzaw) {
        super(zzaw);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzag() {
    }

    public final String zzdr() {
        String str;
        zzcl();
        synchronized (this) {
            if (this.zzup == null) {
                this.zzyd = zzca().zza(new zzbp(this));
            }
            if (this.zzyd != null) {
                try {
                    this.zzup = (String) this.zzyd.get();
                } catch (InterruptedException e) {
                    zzd("ClientId loading or generation was interrupted", e);
                    this.zzup = "0";
                } catch (ExecutionException e2) {
                    zze("Failed to load or generate client id", e2);
                    this.zzup = "0";
                }
                if (this.zzup == null) {
                    this.zzup = "0";
                }
                zza("Loaded clientId", this.zzup);
                this.zzyd = null;
            }
            str = this.zzup;
        }
        return str;
    }

    /* Access modifiers changed, original: final */
    public final String zzds() {
        synchronized (this) {
            this.zzup = null;
            this.zzyd = zzca().zza(new zzbq(this));
        }
        return zzdr();
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final String zzdt() {
        String zzd = zzd(zzca().getContext());
        return zzd == null ? zzdu() : zzd;
    }

    @VisibleForTesting
    private final String zzdu() {
        String toLowerCase = UUID.randomUUID().toString().toLowerCase(Locale.US);
        try {
            return !zzb(zzca().getContext(), toLowerCase) ? "0" : toLowerCase;
        } catch (Exception e) {
            zze("Error saving clientId file", e);
            return "0";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x009c A:{SYNTHETIC, Splitter:B:56:0x009c} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0080 A:{SYNTHETIC, Splitter:B:40:0x0080} */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x008e A:{SYNTHETIC, Splitter:B:47:0x008e} */
    private final java.lang.String zzd(android.content.Context r7) {
        /*
        r6 = this;
        r0 = "ClientId should be loaded from worker thread";
        com.google.android.gms.common.internal.Preconditions.checkNotMainThread(r0);
        r0 = 0;
        r1 = "gaClientId";
        r1 = r7.openFileInput(r1);	 Catch:{ FileNotFoundException -> 0x0099, IOException -> 0x0072, all -> 0x006f }
        r2 = 36;
        r3 = new byte[r2];	 Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
        r4 = 0;
        r2 = r1.read(r3, r4, r2);	 Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
        r5 = r1.available();	 Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
        if (r5 <= 0) goto L_0x0035;
    L_0x001b:
        r2 = "clientId file seems corrupted, deleting it.";
        r6.zzt(r2);	 Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
        r1.close();	 Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
        r2 = "gaClientId";
        r7.deleteFile(r2);	 Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
        if (r1 == 0) goto L_0x0034;
    L_0x002a:
        r1.close();	 Catch:{ IOException -> 0x002e }
        goto L_0x0034;
    L_0x002e:
        r7 = move-exception;
        r1 = "Failed to close client id reading stream";
        r6.zze(r1, r7);
    L_0x0034:
        return r0;
    L_0x0035:
        r5 = 14;
        if (r2 >= r5) goto L_0x0053;
    L_0x0039:
        r2 = "clientId file is empty, deleting it.";
        r6.zzt(r2);	 Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
        r1.close();	 Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
        r2 = "gaClientId";
        r7.deleteFile(r2);	 Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
        if (r1 == 0) goto L_0x0052;
    L_0x0048:
        r1.close();	 Catch:{ IOException -> 0x004c }
        goto L_0x0052;
    L_0x004c:
        r7 = move-exception;
        r1 = "Failed to close client id reading stream";
        r6.zze(r1, r7);
    L_0x0052:
        return r0;
    L_0x0053:
        r1.close();	 Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
        r5 = new java.lang.String;	 Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
        r5.<init>(r3, r4, r2);	 Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
        r2 = "Read client id from disk";
        r6.zza(r2, r5);	 Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x006d }
        if (r1 == 0) goto L_0x006c;
    L_0x0062:
        r1.close();	 Catch:{ IOException -> 0x0066 }
        goto L_0x006c;
    L_0x0066:
        r7 = move-exception;
        r0 = "Failed to close client id reading stream";
        r6.zze(r0, r7);
    L_0x006c:
        return r5;
    L_0x006d:
        r2 = move-exception;
        goto L_0x0074;
    L_0x006f:
        r7 = move-exception;
        r1 = r0;
        goto L_0x008c;
    L_0x0072:
        r2 = move-exception;
        r1 = r0;
    L_0x0074:
        r3 = "Error reading client id file, deleting it";
        r6.zze(r3, r2);	 Catch:{ all -> 0x008b }
        r2 = "gaClientId";
        r7.deleteFile(r2);	 Catch:{ all -> 0x008b }
        if (r1 == 0) goto L_0x008a;
    L_0x0080:
        r1.close();	 Catch:{ IOException -> 0x0084 }
        goto L_0x008a;
    L_0x0084:
        r7 = move-exception;
        r1 = "Failed to close client id reading stream";
        r6.zze(r1, r7);
    L_0x008a:
        return r0;
    L_0x008b:
        r7 = move-exception;
    L_0x008c:
        if (r1 == 0) goto L_0x0098;
    L_0x008e:
        r1.close();	 Catch:{ IOException -> 0x0092 }
        goto L_0x0098;
    L_0x0092:
        r0 = move-exception;
        r1 = "Failed to close client id reading stream";
        r6.zze(r1, r0);
    L_0x0098:
        throw r7;
    L_0x0099:
        r1 = r0;
    L_0x009a:
        if (r1 == 0) goto L_0x00a6;
    L_0x009c:
        r1.close();	 Catch:{ IOException -> 0x00a0 }
        goto L_0x00a6;
    L_0x00a0:
        r7 = move-exception;
        r1 = "Failed to close client id reading stream";
        r6.zze(r1, r7);
    L_0x00a6:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbo.zzd(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:20:0x0036=Splitter:B:20:0x0036, B:29:0x0049=Splitter:B:29:0x0049} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0050 A:{SYNTHETIC, Splitter:B:32:0x0050} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003d A:{SYNTHETIC, Splitter:B:23:0x003d} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x005d A:{SYNTHETIC, Splitter:B:38:0x005d} */
    private final boolean zzb(android.content.Context r4, java.lang.String r5) {
        /*
        r3 = this;
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5);
        r0 = "ClientId should be saved from worker thread";
        com.google.android.gms.common.internal.Preconditions.checkNotMainThread(r0);
        r0 = 0;
        r1 = 0;
        r2 = "Storing clientId";
        r3.zza(r2, r5);	 Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0035 }
        r2 = "gaClientId";
        r4 = r4.openFileOutput(r2, r0);	 Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0035 }
        r5 = r5.getBytes();	 Catch:{ FileNotFoundException -> 0x0030, IOException -> 0x002d, all -> 0x002a }
        r4.write(r5);	 Catch:{ FileNotFoundException -> 0x0030, IOException -> 0x002d, all -> 0x002a }
        if (r4 == 0) goto L_0x0028;
    L_0x001e:
        r4.close();	 Catch:{ IOException -> 0x0022 }
        goto L_0x0028;
    L_0x0022:
        r4 = move-exception;
        r5 = "Failed to close clientId writing stream";
        r3.zze(r5, r4);
    L_0x0028:
        r4 = 1;
        return r4;
    L_0x002a:
        r5 = move-exception;
        r1 = r4;
        goto L_0x005b;
    L_0x002d:
        r5 = move-exception;
        r1 = r4;
        goto L_0x0036;
    L_0x0030:
        r5 = move-exception;
        r1 = r4;
        goto L_0x0049;
    L_0x0033:
        r5 = move-exception;
        goto L_0x005b;
    L_0x0035:
        r5 = move-exception;
    L_0x0036:
        r4 = "Error writing to clientId file";
        r3.zze(r4, r5);	 Catch:{ all -> 0x0033 }
        if (r1 == 0) goto L_0x0047;
    L_0x003d:
        r1.close();	 Catch:{ IOException -> 0x0041 }
        goto L_0x0047;
    L_0x0041:
        r4 = move-exception;
        r5 = "Failed to close clientId writing stream";
        r3.zze(r5, r4);
    L_0x0047:
        return r0;
    L_0x0048:
        r5 = move-exception;
    L_0x0049:
        r4 = "Error creating clientId file";
        r3.zze(r4, r5);	 Catch:{ all -> 0x0033 }
        if (r1 == 0) goto L_0x005a;
    L_0x0050:
        r1.close();	 Catch:{ IOException -> 0x0054 }
        goto L_0x005a;
    L_0x0054:
        r4 = move-exception;
        r5 = "Failed to close clientId writing stream";
        r3.zze(r5, r4);
    L_0x005a:
        return r0;
    L_0x005b:
        if (r1 == 0) goto L_0x0067;
    L_0x005d:
        r1.close();	 Catch:{ IOException -> 0x0061 }
        goto L_0x0067;
    L_0x0061:
        r4 = move-exception;
        r0 = "Failed to close clientId writing stream";
        r3.zze(r0, r4);
    L_0x0067:
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbo.zzb(android.content.Context, java.lang.String):boolean");
    }
}
