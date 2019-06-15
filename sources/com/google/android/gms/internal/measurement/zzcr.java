package com.google.android.gms.internal.measurement;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.til.colombia.android.internal.e;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map.Entry;

final class zzcr extends zzau {
    private static final byte[] zzabn = "\n".getBytes();
    private final String zzabl;
    private final zzdc zzabm;

    zzcr(zzaw zzaw) {
        super(zzaw);
        String str = zzav.VERSION;
        String str2 = VERSION.RELEASE;
        String zza = zzdg.zza(Locale.getDefault());
        String str3 = Build.MODEL;
        String str4 = Build.ID;
        this.zzabl = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{"GoogleAnalytics", str, str2, zza, str3, str4});
        this.zzabm = new zzdc(zzaw.zzbx());
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzag() {
        zza("Network initialized. User agent", this.zzabl);
    }

    public final boolean zzfb() {
        NetworkInfo activeNetworkInfo;
        zzk.zzaf();
        zzcl();
        try {
            activeNetworkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException unused) {
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzq("No network connectivity");
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:71:0x01b3 A:{SYNTHETIC, EDGE_INSN: B:71:0x01b3->B:67:0x01b3 ?: BREAK  } */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x005f  */
    /* JADX WARNING: Missing block: B:51:0x0158, code skipped:
            if (zza(r5) == 200) goto L_0x0134;
     */
    /* JADX WARNING: Missing block: B:62:0x0198, code skipped:
            if (zza(r6, r5) == 200) goto L_0x0134;
     */
    public final java.util.List<java.lang.Long> zzb(java.util.List<com.google.android.gms.internal.measurement.zzck> r9) {
        /*
        r8 = this;
        com.google.android.gms.analytics.zzk.zzaf();
        r8.zzcl();
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r9);
        r0 = r8.zzbz();
        r0 = r0.zzeg();
        r0 = r0.isEmpty();
        r1 = 0;
        r2 = 1;
        if (r0 != 0) goto L_0x0058;
    L_0x0019:
        r0 = r8.zzabm;
        r3 = com.google.android.gms.internal.measurement.zzcf.zzzx;
        r3 = r3.get();
        r3 = (java.lang.Integer) r3;
        r3 = r3.intValue();
        r3 = (long) r3;
        r5 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r3 = r3 * r5;
        r0 = r0.zzj(r3);
        if (r0 != 0) goto L_0x0032;
    L_0x0031:
        goto L_0x0058;
    L_0x0032:
        r0 = com.google.android.gms.internal.measurement.zzcf.zzzq;
        r0 = r0.get();
        r0 = (java.lang.String) r0;
        r0 = com.google.android.gms.internal.measurement.zzbn.zzz(r0);
        r3 = com.google.android.gms.internal.measurement.zzbn.NONE;
        if (r0 == r3) goto L_0x0044;
    L_0x0042:
        r0 = r2;
        goto L_0x0045;
    L_0x0044:
        r0 = r1;
    L_0x0045:
        r3 = com.google.android.gms.internal.measurement.zzcf.zzzr;
        r3 = r3.get();
        r3 = (java.lang.String) r3;
        r3 = com.google.android.gms.internal.measurement.zzbt.zzaa(r3);
        r4 = com.google.android.gms.internal.measurement.zzbt.GZIP;
        if (r3 != r4) goto L_0x0059;
    L_0x0055:
        r3 = r0;
        r0 = r2;
        goto L_0x005b;
    L_0x0058:
        r0 = r1;
    L_0x0059:
        r3 = r0;
        r0 = r1;
    L_0x005b:
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r3 == 0) goto L_0x0104;
    L_0x005f:
        r1 = r9.isEmpty();
        r1 = r1 ^ r2;
        com.google.android.gms.common.internal.Preconditions.checkArgument(r1);
        r1 = "Uploading batched hits. compression, count";
        r2 = java.lang.Boolean.valueOf(r0);
        r3 = r9.size();
        r3 = java.lang.Integer.valueOf(r3);
        r8.zza(r1, r2, r3);
        r1 = new com.google.android.gms.internal.measurement.zzcs;
        r1.<init>(r8);
        r2 = new java.util.ArrayList;
        r2.<init>();
        r9 = r9.iterator();
    L_0x0086:
        r3 = r9.hasNext();
        if (r3 == 0) goto L_0x00a4;
    L_0x008c:
        r3 = r9.next();
        r3 = (com.google.android.gms.internal.measurement.zzck) r3;
        r5 = r1.zze(r3);
        if (r5 == 0) goto L_0x00a4;
    L_0x0098:
        r5 = r3.zzeq();
        r3 = java.lang.Long.valueOf(r5);
        r2.add(r3);
        goto L_0x0086;
    L_0x00a4:
        r9 = r1.zzfe();
        if (r9 != 0) goto L_0x00ab;
    L_0x00aa:
        return r2;
    L_0x00ab:
        r9 = r8.zzfc();
        if (r9 != 0) goto L_0x00b7;
    L_0x00b1:
        r9 = "Failed to build batching endpoint url";
        r8.zzu(r9);
        goto L_0x00ff;
    L_0x00b7:
        if (r0 == 0) goto L_0x00c2;
    L_0x00b9:
        r0 = r1.getPayload();
        r9 = r8.zzb(r9, r0);
        goto L_0x00ca;
    L_0x00c2:
        r0 = r1.getPayload();
        r9 = r8.zza(r9, r0);
    L_0x00ca:
        if (r4 != r9) goto L_0x00da;
    L_0x00cc:
        r9 = "Batched upload completed. Hits batched";
        r0 = r1.zzfe();
        r0 = java.lang.Integer.valueOf(r0);
        r8.zza(r9, r0);
        return r2;
    L_0x00da:
        r0 = "Network error uploading hits. status code";
        r1 = java.lang.Integer.valueOf(r9);
        r8.zza(r0, r1);
        r0 = r8.zzbz();
        r0 = r0.zzeg();
        r9 = java.lang.Integer.valueOf(r9);
        r9 = r0.contains(r9);
        if (r9 == 0) goto L_0x00ff;
    L_0x00f5:
        r9 = "Server instructed the client to stop batching";
        r8.zzt(r9);
        r9 = r8.zzabm;
        r9.start();
    L_0x00ff:
        r9 = java.util.Collections.emptyList();
        return r9;
    L_0x0104:
        r0 = new java.util.ArrayList;
        r3 = r9.size();
        r0.<init>(r3);
        r9 = r9.iterator();
    L_0x0111:
        r3 = r9.hasNext();
        if (r3 == 0) goto L_0x01b3;
    L_0x0117:
        r3 = r9.next();
        r3 = (com.google.android.gms.internal.measurement.zzck) r3;
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r3);
        r5 = r3.zzet();
        r5 = r5 ^ r2;
        r5 = r8.zza(r3, r5);
        if (r5 != 0) goto L_0x0136;
    L_0x012b:
        r5 = r8.zzby();
        r6 = "Error formatting hit for upload";
        r5.zza(r3, r6);
    L_0x0134:
        r5 = r2;
        goto L_0x019c;
    L_0x0136:
        r6 = r5.length();
        r7 = com.google.android.gms.internal.measurement.zzcf.zzzp;
        r7 = r7.get();
        r7 = (java.lang.Integer) r7;
        r7 = r7.intValue();
        if (r6 > r7) goto L_0x015b;
    L_0x0148:
        r5 = r8.zzb(r3, r5);
        if (r5 != 0) goto L_0x0154;
    L_0x014e:
        r5 = "Failed to build collect GET endpoint url";
        r8.zzu(r5);
        goto L_0x019b;
    L_0x0154:
        r5 = r8.zza(r5);
        if (r5 != r4) goto L_0x019b;
    L_0x015a:
        goto L_0x0134;
    L_0x015b:
        r5 = r8.zza(r3, r1);
        if (r5 != 0) goto L_0x016b;
    L_0x0161:
        r5 = r8.zzby();
        r6 = "Error formatting hit for POST upload";
        r5.zza(r3, r6);
        goto L_0x0134;
    L_0x016b:
        r5 = r5.getBytes();
        r6 = r5.length;
        r7 = com.google.android.gms.internal.measurement.zzcf.zzzu;
        r7 = r7.get();
        r7 = (java.lang.Integer) r7;
        r7 = r7.intValue();
        if (r6 <= r7) goto L_0x0188;
    L_0x017e:
        r5 = r8.zzby();
        r6 = "Hit payload exceeds size limit";
        r5.zza(r3, r6);
        goto L_0x0134;
    L_0x0188:
        r6 = r8.zzd(r3);
        if (r6 != 0) goto L_0x0194;
    L_0x018e:
        r5 = "Failed to build collect POST endpoint url";
        r8.zzu(r5);
        goto L_0x019b;
    L_0x0194:
        r5 = r8.zza(r6, r5);
        if (r5 != r4) goto L_0x019b;
    L_0x019a:
        goto L_0x015a;
    L_0x019b:
        r5 = r1;
    L_0x019c:
        if (r5 == 0) goto L_0x01b3;
    L_0x019e:
        r5 = r3.zzeq();
        r3 = java.lang.Long.valueOf(r5);
        r0.add(r3);
        r3 = r0.size();
        r5 = com.google.android.gms.internal.measurement.zzbx.zzeb();
        if (r3 < r5) goto L_0x0111;
    L_0x01b3:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzcr.zzb(java.util.List):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004c  */
    private final int zza(java.net.URL r5) {
        /*
        r4 = this;
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r5);
        r0 = "GET request";
        r4.zzb(r0, r5);
        r0 = 0;
        r5 = r4.zzb(r5);	 Catch:{ IOException -> 0x003a, all -> 0x0035 }
        r5.connect();	 Catch:{ IOException -> 0x0033 }
        r4.zza(r5);	 Catch:{ IOException -> 0x0033 }
        r0 = r5.getResponseCode();	 Catch:{ IOException -> 0x0033 }
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 != r1) goto L_0x0022;
    L_0x001b:
        r1 = r4.zzcc();	 Catch:{ IOException -> 0x0033 }
        r1.zzbv();	 Catch:{ IOException -> 0x0033 }
    L_0x0022:
        r1 = "GET status";
        r2 = java.lang.Integer.valueOf(r0);	 Catch:{ IOException -> 0x0033 }
        r4.zzb(r1, r2);	 Catch:{ IOException -> 0x0033 }
        if (r5 == 0) goto L_0x0030;
    L_0x002d:
        r5.disconnect();
    L_0x0030:
        return r0;
    L_0x0031:
        r0 = move-exception;
        goto L_0x004a;
    L_0x0033:
        r0 = move-exception;
        goto L_0x003e;
    L_0x0035:
        r5 = move-exception;
        r3 = r0;
        r0 = r5;
        r5 = r3;
        goto L_0x004a;
    L_0x003a:
        r5 = move-exception;
        r3 = r0;
        r0 = r5;
        r5 = r3;
    L_0x003e:
        r1 = "Network GET connection error";
        r4.zzd(r1, r0);	 Catch:{ all -> 0x0031 }
        if (r5 == 0) goto L_0x0048;
    L_0x0045:
        r5.disconnect();
    L_0x0048:
        r5 = 0;
        return r5;
    L_0x004a:
        if (r5 == 0) goto L_0x004f;
    L_0x004c:
        r5.disconnect();
    L_0x004f:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzcr.zza(java.net.URL):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x007f A:{SYNTHETIC, Splitter:B:34:0x007f} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0093 A:{SYNTHETIC, Splitter:B:44:0x0093} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x007f A:{SYNTHETIC, Splitter:B:34:0x007f} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0093 A:{SYNTHETIC, Splitter:B:44:0x0093} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x009f  */
    private final int zza(java.net.URL r4, byte[] r5) {
        /*
        r3 = this;
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r4);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r5);
        r0 = "POST bytes, url";
        r1 = r5.length;
        r1 = java.lang.Integer.valueOf(r1);
        r3.zzb(r0, r1, r4);
        r0 = com.google.android.gms.internal.measurement.zzat.zzck();
        if (r0 == 0) goto L_0x0020;
    L_0x0016:
        r0 = "Post payload\n";
        r1 = new java.lang.String;
        r1.<init>(r5);
        r3.zza(r0, r1);
    L_0x0020:
        r0 = 0;
        r1 = r3.getContext();	 Catch:{ IOException -> 0x0076, all -> 0x0073 }
        r1.getPackageName();	 Catch:{ IOException -> 0x0076, all -> 0x0073 }
        r4 = r3.zzb(r4);	 Catch:{ IOException -> 0x0076, all -> 0x0073 }
        r1 = 1;
        r4.setDoOutput(r1);	 Catch:{ IOException -> 0x0071 }
        r1 = r5.length;	 Catch:{ IOException -> 0x0071 }
        r4.setFixedLengthStreamingMode(r1);	 Catch:{ IOException -> 0x0071 }
        r4.connect();	 Catch:{ IOException -> 0x0071 }
        r1 = r4.getOutputStream();	 Catch:{ IOException -> 0x0071 }
        r1.write(r5);	 Catch:{ IOException -> 0x006e, all -> 0x006b }
        r3.zza(r4);	 Catch:{ IOException -> 0x006e, all -> 0x006b }
        r5 = r4.getResponseCode();	 Catch:{ IOException -> 0x006e, all -> 0x006b }
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r5 != r0) goto L_0x0050;
    L_0x0049:
        r0 = r3.zzcc();	 Catch:{ IOException -> 0x006e, all -> 0x006b }
        r0.zzbv();	 Catch:{ IOException -> 0x006e, all -> 0x006b }
    L_0x0050:
        r0 = "POST status";
        r2 = java.lang.Integer.valueOf(r5);	 Catch:{ IOException -> 0x006e, all -> 0x006b }
        r3.zzb(r0, r2);	 Catch:{ IOException -> 0x006e, all -> 0x006b }
        if (r1 == 0) goto L_0x0065;
    L_0x005b:
        r1.close();	 Catch:{ IOException -> 0x005f }
        goto L_0x0065;
    L_0x005f:
        r0 = move-exception;
        r1 = "Error closing http post connection output stream";
        r3.zze(r1, r0);
    L_0x0065:
        if (r4 == 0) goto L_0x006a;
    L_0x0067:
        r4.disconnect();
    L_0x006a:
        return r5;
    L_0x006b:
        r5 = move-exception;
        r0 = r1;
        goto L_0x0091;
    L_0x006e:
        r5 = move-exception;
        r0 = r1;
        goto L_0x0078;
    L_0x0071:
        r5 = move-exception;
        goto L_0x0078;
    L_0x0073:
        r5 = move-exception;
        r4 = r0;
        goto L_0x0091;
    L_0x0076:
        r5 = move-exception;
        r4 = r0;
    L_0x0078:
        r1 = "Network POST connection error";
        r3.zzd(r1, r5);	 Catch:{ all -> 0x0090 }
        if (r0 == 0) goto L_0x0089;
    L_0x007f:
        r0.close();	 Catch:{ IOException -> 0x0083 }
        goto L_0x0089;
    L_0x0083:
        r5 = move-exception;
        r0 = "Error closing http post connection output stream";
        r3.zze(r0, r5);
    L_0x0089:
        if (r4 == 0) goto L_0x008e;
    L_0x008b:
        r4.disconnect();
    L_0x008e:
        r4 = 0;
        return r4;
    L_0x0090:
        r5 = move-exception;
    L_0x0091:
        if (r0 == 0) goto L_0x009d;
    L_0x0093:
        r0.close();	 Catch:{ IOException -> 0x0097 }
        goto L_0x009d;
    L_0x0097:
        r0 = move-exception;
        r1 = "Error closing http post connection output stream";
        r3.zze(r1, r0);
    L_0x009d:
        if (r4 == 0) goto L_0x00a2;
    L_0x009f:
        r4.disconnect();
    L_0x00a2:
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzcr.zza(java.net.URL, byte[]):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x00eb A:{SYNTHETIC, Splitter:B:51:0x00eb} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00d7 A:{SYNTHETIC, Splitter:B:41:0x00d7} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00eb A:{SYNTHETIC, Splitter:B:51:0x00eb} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00d7 A:{SYNTHETIC, Splitter:B:41:0x00d7} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00eb A:{SYNTHETIC, Splitter:B:51:0x00eb} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00f7  */
    private final int zzb(java.net.URL r10, byte[] r11) {
        /*
        r9 = this;
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r10);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r11);
        r0 = 0;
        r1 = r9.getContext();	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r1.getPackageName();	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r1 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r1.<init>();	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r2 = new java.util.zip.GZIPOutputStream;	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r2.<init>(r1);	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r2.write(r11);	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r2.close();	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r1.close();	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r1 = r1.toByteArray();	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r2 = "POST compressed size, ratio %, url";
        r3 = r1.length;	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r4 = 100;
        r6 = r1.length;	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r6 = (long) r6;	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r4 = r4 * r6;
        r6 = r11.length;	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r6 = (long) r6;	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r4 = r4 / r6;
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r9.zza(r2, r3, r4, r10);	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r2 = r1.length;	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r3 = r11.length;	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        if (r2 <= r3) goto L_0x004e;
    L_0x003f:
        r2 = "Compressed payload is larger then uncompressed. compressed, uncompressed";
        r3 = r1.length;	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r4 = r11.length;	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r9.zzc(r2, r3, r4);	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
    L_0x004e:
        r2 = com.google.android.gms.internal.measurement.zzat.zzck();	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        if (r2 == 0) goto L_0x0074;
    L_0x0054:
        r2 = "Post payload";
        r3 = "\n";
        r4 = new java.lang.String;	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r4.<init>(r11);	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r11 = java.lang.String.valueOf(r4);	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r4 = r11.length();	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        if (r4 == 0) goto L_0x006c;
    L_0x0067:
        r11 = r3.concat(r11);	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        goto L_0x0071;
    L_0x006c:
        r11 = new java.lang.String;	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r11.<init>(r3);	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
    L_0x0071:
        r9.zza(r2, r11);	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
    L_0x0074:
        r10 = r9.zzb(r10);	 Catch:{ IOException -> 0x00ce, all -> 0x00cb }
        r11 = 1;
        r10.setDoOutput(r11);	 Catch:{ IOException -> 0x00c6, all -> 0x00c1 }
        r11 = "Content-Encoding";
        r2 = "gzip";
        r10.addRequestProperty(r11, r2);	 Catch:{ IOException -> 0x00c6, all -> 0x00c1 }
        r11 = r1.length;	 Catch:{ IOException -> 0x00c6, all -> 0x00c1 }
        r10.setFixedLengthStreamingMode(r11);	 Catch:{ IOException -> 0x00c6, all -> 0x00c1 }
        r10.connect();	 Catch:{ IOException -> 0x00c6, all -> 0x00c1 }
        r11 = r10.getOutputStream();	 Catch:{ IOException -> 0x00c6, all -> 0x00c1 }
        r11.write(r1);	 Catch:{ IOException -> 0x00bb, all -> 0x00b5 }
        r11.close();	 Catch:{ IOException -> 0x00bb, all -> 0x00b5 }
        r9.zza(r10);	 Catch:{ IOException -> 0x00c6, all -> 0x00c1 }
        r11 = r10.getResponseCode();	 Catch:{ IOException -> 0x00c6, all -> 0x00c1 }
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r11 != r1) goto L_0x00a6;
    L_0x009f:
        r1 = r9.zzcc();	 Catch:{ IOException -> 0x00c6, all -> 0x00c1 }
        r1.zzbv();	 Catch:{ IOException -> 0x00c6, all -> 0x00c1 }
    L_0x00a6:
        r1 = "POST status";
        r2 = java.lang.Integer.valueOf(r11);	 Catch:{ IOException -> 0x00c6, all -> 0x00c1 }
        r9.zzb(r1, r2);	 Catch:{ IOException -> 0x00c6, all -> 0x00c1 }
        if (r10 == 0) goto L_0x00b4;
    L_0x00b1:
        r10.disconnect();
    L_0x00b4:
        return r11;
    L_0x00b5:
        r0 = move-exception;
        r8 = r11;
        r11 = r10;
        r10 = r0;
        r0 = r8;
        goto L_0x00e9;
    L_0x00bb:
        r0 = move-exception;
        r8 = r11;
        r11 = r10;
        r10 = r0;
        r0 = r8;
        goto L_0x00d0;
    L_0x00c1:
        r11 = move-exception;
        r8 = r11;
        r11 = r10;
        r10 = r8;
        goto L_0x00e9;
    L_0x00c6:
        r11 = move-exception;
        r8 = r11;
        r11 = r10;
        r10 = r8;
        goto L_0x00d0;
    L_0x00cb:
        r10 = move-exception;
        r11 = r0;
        goto L_0x00e9;
    L_0x00ce:
        r10 = move-exception;
        r11 = r0;
    L_0x00d0:
        r1 = "Network compressed POST connection error";
        r9.zzd(r1, r10);	 Catch:{ all -> 0x00e8 }
        if (r0 == 0) goto L_0x00e1;
    L_0x00d7:
        r0.close();	 Catch:{ IOException -> 0x00db }
        goto L_0x00e1;
    L_0x00db:
        r10 = move-exception;
        r0 = "Error closing http compressed post connection output stream";
        r9.zze(r0, r10);
    L_0x00e1:
        if (r11 == 0) goto L_0x00e6;
    L_0x00e3:
        r11.disconnect();
    L_0x00e6:
        r10 = 0;
        return r10;
    L_0x00e8:
        r10 = move-exception;
    L_0x00e9:
        if (r0 == 0) goto L_0x00f5;
    L_0x00eb:
        r0.close();	 Catch:{ IOException -> 0x00ef }
        goto L_0x00f5;
    L_0x00ef:
        r0 = move-exception;
        r1 = "Error closing http compressed post connection output stream";
        r9.zze(r1, r0);
    L_0x00f5:
        if (r11 == 0) goto L_0x00fa;
    L_0x00f7:
        r11.disconnect();
    L_0x00fa:
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzcr.zzb(java.net.URL, byte[]):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0022 A:{SYNTHETIC, Splitter:B:19:0x0022} */
    private final void zza(java.net.HttpURLConnection r3) throws java.io.IOException {
        /*
        r2 = this;
        r3 = r3.getInputStream();	 Catch:{ all -> 0x001e }
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = new byte[r0];	 Catch:{ all -> 0x001c }
    L_0x0008:
        r1 = r3.read(r0);	 Catch:{ all -> 0x001c }
        if (r1 > 0) goto L_0x0008;
    L_0x000e:
        if (r3 == 0) goto L_0x001b;
    L_0x0010:
        r3.close();	 Catch:{ IOException -> 0x0014 }
        return;
    L_0x0014:
        r3 = move-exception;
        r0 = "Error closing http connection input stream";
        r2.zze(r0, r3);
        return;
    L_0x001b:
        return;
    L_0x001c:
        r0 = move-exception;
        goto L_0x0020;
    L_0x001e:
        r0 = move-exception;
        r3 = 0;
    L_0x0020:
        if (r3 == 0) goto L_0x002c;
    L_0x0022:
        r3.close();	 Catch:{ IOException -> 0x0026 }
        goto L_0x002c;
    L_0x0026:
        r3 = move-exception;
        r1 = "Error closing http connection input stream";
        r2.zze(r1, r3);
    L_0x002c:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzcr.zza(java.net.HttpURLConnection):void");
    }

    @VisibleForTesting
    private final HttpURLConnection zzb(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        if (openConnection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setDefaultUseCaches(false);
            httpURLConnection.setConnectTimeout(((Integer) zzcf.zzzz.get()).intValue());
            httpURLConnection.setReadTimeout(((Integer) zzcf.zzaaa.get()).intValue());
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestProperty(e.c, this.zzabl);
            httpURLConnection.setDoInput(true);
            return httpURLConnection;
        }
        throw new IOException("Failed to obtain http connection");
    }

    private final URL zzd(zzck zzck) {
        String valueOf;
        String valueOf2;
        if (zzck.zzet()) {
            valueOf = String.valueOf(zzbx.zzed());
            valueOf2 = String.valueOf(zzbx.zzef());
            if (valueOf2.length() != 0) {
                valueOf = valueOf.concat(valueOf2);
                return new URL(valueOf);
            }
            valueOf2 = new String(valueOf);
        } else {
            valueOf = String.valueOf(zzbx.zzee());
            valueOf2 = String.valueOf(zzbx.zzef());
            if (valueOf2.length() != 0) {
                valueOf = valueOf.concat(valueOf2);
                return new URL(valueOf);
            }
            valueOf2 = new String(valueOf);
        }
        valueOf = valueOf2;
        try {
            return new URL(valueOf);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private final URL zzb(zzck zzck, String str) {
        String zzed;
        String zzef;
        StringBuilder stringBuilder;
        if (zzck.zzet()) {
            zzed = zzbx.zzed();
            zzef = zzbx.zzef();
            stringBuilder = new StringBuilder(((1 + String.valueOf(zzed).length()) + String.valueOf(zzef).length()) + String.valueOf(str).length());
            stringBuilder.append(zzed);
            stringBuilder.append(zzef);
            stringBuilder.append("?");
            stringBuilder.append(str);
            zzed = stringBuilder.toString();
        } else {
            zzed = zzbx.zzee();
            zzef = zzbx.zzef();
            stringBuilder = new StringBuilder(((1 + String.valueOf(zzed).length()) + String.valueOf(zzef).length()) + String.valueOf(str).length());
            stringBuilder.append(zzed);
            stringBuilder.append(zzef);
            stringBuilder.append("?");
            stringBuilder.append(str);
            zzed = stringBuilder.toString();
        }
        try {
            return new URL(zzed);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private final URL zzfc() {
        String valueOf = String.valueOf(zzbx.zzed());
        String valueOf2 = String.valueOf((String) zzcf.zzzo.get());
        try {
            return new URL(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final String zza(zzck zzck, boolean z) {
        Preconditions.checkNotNull(zzck);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Entry entry : zzck.zzcw().entrySet()) {
                String str = (String) entry.getKey();
                if (!("ht".equals(str) || "qt".equals(str) || "AppUID".equals(str) || "z".equals(str) || "_gmsv".equals(str))) {
                    zza(stringBuilder, str, (String) entry.getValue());
                }
            }
            zza(stringBuilder, "ht", String.valueOf(zzck.zzer()));
            zza(stringBuilder, "qt", String.valueOf(zzbx().currentTimeMillis() - zzck.zzer()));
            if (z) {
                String valueOf;
                long zzeu = zzck.zzeu();
                if (zzeu != 0) {
                    valueOf = String.valueOf(zzeu);
                } else {
                    valueOf = String.valueOf(zzck.zzeq());
                }
                zza(stringBuilder, "z", valueOf);
            }
            return stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            zze("Failed to encode name or value", e);
            return null;
        }
    }

    private static void zza(StringBuilder stringBuilder, String str, String str2) throws UnsupportedEncodingException {
        if (stringBuilder.length() != 0) {
            stringBuilder.append('&');
        }
        stringBuilder.append(URLEncoder.encode(str, "UTF-8"));
        stringBuilder.append('=');
        stringBuilder.append(URLEncoder.encode(str2, "UTF-8"));
    }
}
