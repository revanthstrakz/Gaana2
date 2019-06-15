package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.internal.g.e;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@zzark
public final class zzaaq {
    @VisibleForTesting
    private Context mContext;
    @VisibleForTesting
    private String zzbuk;
    @VisibleForTesting
    private String zzcyt;
    @VisibleForTesting
    private BlockingQueue<zzaba> zzcyv = new ArrayBlockingQueue(100);
    @VisibleForTesting
    private ExecutorService zzcyw;
    @VisibleForTesting
    private LinkedHashMap<String, String> zzcyx = new LinkedHashMap();
    @VisibleForTesting
    private Map<String, zzaau> zzcyy = new HashMap();
    private AtomicBoolean zzcyz;
    private File zzcza;

    public final void zza(Context context, String str, String str2, Map<String, String> map) {
        this.mContext = context;
        this.zzbuk = str;
        this.zzcyt = str2;
        this.zzcyz = new AtomicBoolean(false);
        this.zzcyz.set(((Boolean) zzwu.zzpz().zzd(zzaan.zzcpy)).booleanValue());
        if (this.zzcyz.get()) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory != null) {
                this.zzcza = new File(externalStorageDirectory, "sdk_csi_data.txt");
            }
        }
        for (Entry entry : map.entrySet()) {
            this.zzcyx.put((String) entry.getKey(), (String) entry.getValue());
        }
        this.zzcyw = Executors.newSingleThreadExecutor();
        this.zzcyw.execute(new zzaar(this));
        this.zzcyy.put(NativeProtocol.WEB_DIALOG_ACTION, zzaau.zzczd);
        this.zzcyy.put("ad_format", zzaau.zzczd);
        this.zzcyy.put(e.a, zzaau.zzcze);
    }

    public final void zzg(@Nullable List<String> list) {
        if (list != null && !list.isEmpty()) {
            this.zzcyx.put(e.a, TextUtils.join(",", list));
        }
    }

    public final boolean zza(zzaba zzaba) {
        return this.zzcyv.offer(zzaba);
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x0000 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009f A:{SYNTHETIC, Splitter:B:31:0x009f} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ae A:{SYNTHETIC, Splitter:B:37:0x00ae} */
    private final void zzrc() {
        /*
        r5 = this;
    L_0x0000:
        r0 = r5.zzcyv;	 Catch:{ InterruptedException -> 0x00cc }
        r0 = r0.take();	 Catch:{ InterruptedException -> 0x00cc }
        r0 = (com.google.android.gms.internal.ads.zzaba) r0;	 Catch:{ InterruptedException -> 0x00cc }
        r1 = r0.zzrh();	 Catch:{ InterruptedException -> 0x00cc }
        r2 = android.text.TextUtils.isEmpty(r1);
        if (r2 != 0) goto L_0x0000;
    L_0x0012:
        r2 = r5.zzcyx;
        r0 = r0.zzri();
        r0 = r5.zza(r2, r0);
        r2 = r5.zzcyt;
        r2 = android.net.Uri.parse(r2);
        r2 = r2.buildUpon();
        r0 = r0.entrySet();
        r0 = r0.iterator();
    L_0x002e:
        r3 = r0.hasNext();
        if (r3 == 0) goto L_0x004a;
    L_0x0034:
        r3 = r0.next();
        r3 = (java.util.Map.Entry) r3;
        r4 = r3.getKey();
        r4 = (java.lang.String) r4;
        r3 = r3.getValue();
        r3 = (java.lang.String) r3;
        r2.appendQueryParameter(r4, r3);
        goto L_0x002e;
    L_0x004a:
        r0 = new java.lang.StringBuilder;
        r2 = r2.build();
        r2 = r2.toString();
        r0.<init>(r2);
        r2 = "&it=";
        r0.append(r2);
        r0.append(r1);
        r0 = r0.toString();
        r1 = r5.zzcyz;
        r1 = r1.get();
        if (r1 == 0) goto L_0x00c0;
    L_0x006b:
        r1 = r5.zzcza;
        if (r1 == 0) goto L_0x00b9;
    L_0x006f:
        r2 = 0;
        r3 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0097 }
        r4 = 1;
        r3.<init>(r1, r4);	 Catch:{ IOException -> 0x0097 }
        r0 = r0.getBytes();	 Catch:{ IOException -> 0x0092, all -> 0x008f }
        r3.write(r0);	 Catch:{ IOException -> 0x0092, all -> 0x008f }
        r0 = 10;
        r3.write(r0);	 Catch:{ IOException -> 0x0092, all -> 0x008f }
        r3.close();	 Catch:{ IOException -> 0x0087 }
        goto L_0x0000;
    L_0x0087:
        r0 = move-exception;
        r1 = "CsiReporter: Cannot close file: sdk_csi_data.txt.";
        com.google.android.gms.internal.ads.zzbbd.zzc(r1, r0);
        goto L_0x0000;
    L_0x008f:
        r0 = move-exception;
        r2 = r3;
        goto L_0x00ac;
    L_0x0092:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0098;
    L_0x0095:
        r0 = move-exception;
        goto L_0x00ac;
    L_0x0097:
        r0 = move-exception;
    L_0x0098:
        r1 = "CsiReporter: Cannot write to file: sdk_csi_data.txt.";
        com.google.android.gms.internal.ads.zzbbd.zzc(r1, r0);	 Catch:{ all -> 0x0095 }
        if (r2 == 0) goto L_0x0000;
    L_0x009f:
        r2.close();	 Catch:{ IOException -> 0x00a4 }
        goto L_0x0000;
    L_0x00a4:
        r0 = move-exception;
        r1 = "CsiReporter: Cannot close file: sdk_csi_data.txt.";
        com.google.android.gms.internal.ads.zzbbd.zzc(r1, r0);
        goto L_0x0000;
    L_0x00ac:
        if (r2 == 0) goto L_0x00b8;
    L_0x00ae:
        r2.close();	 Catch:{ IOException -> 0x00b2 }
        goto L_0x00b8;
    L_0x00b2:
        r1 = move-exception;
        r2 = "CsiReporter: Cannot close file: sdk_csi_data.txt.";
        com.google.android.gms.internal.ads.zzbbd.zzc(r2, r1);
    L_0x00b8:
        throw r0;
    L_0x00b9:
        r0 = "CsiReporter: File doesn't exists. Cannot write CSI data to file.";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r0);
        goto L_0x0000;
    L_0x00c0:
        com.google.android.gms.ads.internal.zzbv.zzlf();
        r1 = r5.mContext;
        r2 = r5.zzbuk;
        com.google.android.gms.internal.ads.zzayh.zzc(r1, r2, r0);
        goto L_0x0000;
    L_0x00cc:
        r0 = move-exception;
        r1 = "CsiReporter:reporter interrupted";
        com.google.android.gms.internal.ads.zzbbd.zzc(r1, r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaaq.zzrc():void");
    }

    /* Access modifiers changed, original: final */
    public final Map<String, String> zza(Map<String, String> map, @Nullable Map<String, String> map2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        if (map2 == null) {
            return linkedHashMap;
        }
        for (Entry entry : map2.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) linkedHashMap.get(str);
            linkedHashMap.put(str, zzbn(str).zzf(str2, (String) entry.getValue()));
        }
        return linkedHashMap;
    }

    public final zzaau zzbn(String str) {
        zzaau zzaau = (zzaau) this.zzcyy.get(str);
        if (zzaau != null) {
            return zzaau;
        }
        return zzaau.zzczc;
    }
}
