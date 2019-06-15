package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.internal.zzbv;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzatq extends zzasr {
    private static final Object sLock = new Object();
    private static zzatq zzeau;
    private final Context mContext;
    private final zzatp zzeav;
    private final ScheduledExecutorService zzeaw = Executors.newSingleThreadScheduledExecutor();

    public static zzatq zza(Context context, zzatp zzatp) {
        zzatq zzatq;
        synchronized (sLock) {
            if (zzeau == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                zzaan.initialize(context);
                zzeau = new zzatq(context, zzatp);
                if (context.getApplicationContext() != null) {
                    zzbv.zzlf().zzaj(context);
                }
                zzaxx.zzag(context);
            }
            zzatq = zzeau;
        }
        return zzatq;
    }

    public final void zza(zzatb zzatb, zzasw zzasw) {
        zzaxz.v("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }

    public final void zzb(zzatb zzatb, zzasw zzasw) {
        zzaxz.v("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }

    private static zzasm zza(Context context, zzatp zzatp, zzasi zzasi, ScheduledExecutorService scheduledExecutorService) {
        String str;
        Context context2 = context;
        zzatp zzatp2 = zzatp;
        zzasi zzasi2 = zzasi;
        ScheduledExecutorService scheduledExecutorService2 = scheduledExecutorService;
        zzbbd.zzdn("Starting ad request from service using: google.afma.request.getAdDictionary");
        zzaba zzaba = new zzaba(((Boolean) zzwu.zzpz().zzd(zzaan.zzcpw)).booleanValue(), "load_ad", zzasi2.zzbst.zzckk);
        if (zzasi2.versionCode > 10 && zzasi2.zzdwv != -1) {
            zzaba.zza(zzaba.zzao(zzasi2.zzdwv), "cts");
        }
        zzaay zzrg = zzaba.zzrg();
        Future zza = zzbbq.zza(zzatp2.zzear.zzm(context2), ((Long) zzwu.zzpz().zzd(zzaan.zzcvj)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService2);
        Future zzm = zzbbq.zzm(null);
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcxz)).booleanValue()) {
            zzm = zzatp2.zzeam.zzdo(zzasi2.zzdwh.packageName);
        }
        Future zzdp = zzatp2.zzeam.zzdp(zzasi2.zzdwh.packageName);
        Future zza2 = zzatp2.zzeas.zza(zzasi2.zzdwi, zzasi2.zzdwh);
        Future zzt = zzbv.zzlq().zzt(context2);
        zzbcb zzm2 = zzbbq.zzm(null);
        Bundle bundle = zzasi2.zzdwg.extras;
        int i = (bundle == null || bundle.getString("_ad") == null) ? 0 : 1;
        if (zzasi2.zzdxb && i == 0) {
            zzm2 = zzatp2.zzeap.zza(zzasi2.applicationInfo);
        }
        zzaay zzaay = zzrg;
        Future zza3 = zzbbq.zza(zzm2, ((Long) zzwu.zzpz().zzd(zzaan.zzcuu)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService2);
        Future zzm3 = zzbbq.zzm(null);
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcro)).booleanValue()) {
            zzm3 = zzbbq.zza(zzatp2.zzeas.zzad(context2), ((Long) zzwu.zzpz().zzd(zzaan.zzcrp)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService2);
        }
        Bundle bundle2 = (zzasi2.versionCode < 4 || zzasi2.zzdwm == null) ? null : zzasi2.zzdwm;
        zzbv.zzlf();
        if (zzayh.zzn(context2, "android.permission.ACCESS_NETWORK_STATE") && ((ConnectivityManager) context2.getSystemService("connectivity")).getActiveNetworkInfo() == null) {
            zzbbd.zzdn("Device is offline.");
        }
        if (zzasi2.versionCode >= 7) {
            str = zzasi2.zzdws;
        } else {
            str = UUID.randomUUID().toString();
        }
        if (zzasi2.zzdwg.extras != null) {
            String string = zzasi2.zzdwg.extras.getString("_ad");
            if (string != null) {
                return zzatv.zza(context2, zzasi2, string);
            }
        }
        List zzf = zzatp2.zzean.zzf(zzasi2.zzdwt);
        String str2 = str;
        zzaba zzaba2 = zzaba;
        Bundle bundle3 = (Bundle) zzbbq.zza(zza, null, ((Long) zzwu.zzpz().zzd(zzaan.zzcvj)).longValue(), TimeUnit.MILLISECONDS);
        Location location = (Location) zzbbq.zza(zza3, null);
        Info info = (Info) zzbbq.zza(zzm3, null);
        String str3 = (String) zzbbq.zza(zza2, null);
        String str4 = (String) zzbbq.zza(zzm, null);
        String str5 = (String) zzbbq.zza(zzdp, null);
        zzatz zzatz = (zzatz) zzbbq.zza(zzt, null);
        if (zzatz == null) {
            zzbbd.zzeo("Error fetching device info. This is not recoverable.");
            return new zzasm(0);
        }
        zzato zzato = new zzato();
        zzato.zzeag = zzasi2;
        zzato.zzeah = zzatz;
        zzato.zzcjj = location;
        zzato.zzeac = bundle3;
        zzato.zzdwi = str3;
        zzato.zzeaf = info;
        if (zzf == null) {
            zzato.zzdwt.clear();
        }
        zzato.zzdwt = zzf;
        zzato.zzdwm = bundle2;
        zzato.zzead = str4;
        zzato.zzeae = str5;
        zzato.zzeai = zzatp2.zzeal.zzf(context2);
        zzato.zzeaj = zzatp2.zzeaj;
        JSONObject zza4 = zzatv.zza(context2, zzato);
        if (zza4 == null) {
            return new zzasm(0);
        }
        if (zzasi2.versionCode < 7) {
            try {
                zza4.put("request_id", str2);
            } catch (JSONException unused) {
            }
        }
        zzaay zzaay2 = zzaay;
        zzaba = zzaba2;
        zzaba.zza(zzaay2, "arc");
        ScheduledExecutorService scheduledExecutorService3 = scheduledExecutorService;
        Future zza5 = zzbbq.zza(zzbbq.zza(zzatp2.zzeat.zzwo().zzj(zza4), zzatr.zzbni, (Executor) scheduledExecutorService3), 10, TimeUnit.SECONDS, scheduledExecutorService3);
        zzbcb zzwy = zzatp2.zzeao.zzwy();
        if (zzwy != null) {
            zzbbo.zza(zzwy, "AdRequestServiceImpl.loadAd.flags");
        }
        zzasm zzasm = null;
        zzaty zzaty = (zzaty) zzbbq.zza(zza5, null);
        if (zzaty == null) {
            return new zzasm(0);
        }
        if (zzaty.getErrorCode() != -2) {
            return new zzasm(zzaty.getErrorCode());
        }
        zzaba.zzrj();
        if (!TextUtils.isEmpty(zzaty.zzwt())) {
            zzasm = zzatv.zza(context2, zzasi2, zzaty.zzwt());
        }
        if (zzasm == null && !TextUtils.isEmpty(zzaty.getUrl())) {
            zzasm = zza(zzasi2, context2, zzasi2.zzbsp.zzdp, zzaty.getUrl(), str4, str5, zzaty, zzaba, zzatp2);
        }
        int i2;
        if (zzasm == null) {
            i2 = 0;
            zzasm = new zzasm(0);
        } else {
            i2 = 0;
        }
        zzaba.zza(zzaay2, "tts");
        zzasm.zzdyq = zzaba.zzrh();
        zzasm.zzdze = zzaty.zzwv();
        return zzasm;
    }

    private static void zza(String str, Map<String, List<String>> map, String str2, int i) {
        if (zzbbd.isLoggable(2)) {
            StringBuilder stringBuilder = new StringBuilder(39 + String.valueOf(str).length());
            stringBuilder.append("Http Response: {\n  URL:\n    ");
            stringBuilder.append(str);
            stringBuilder.append("\n  Headers:");
            zzaxz.v(stringBuilder.toString());
            if (map != null) {
                for (String str3 : map.keySet()) {
                    StringBuilder stringBuilder2 = new StringBuilder(5 + String.valueOf(str3).length());
                    stringBuilder2.append("    ");
                    stringBuilder2.append(str3);
                    stringBuilder2.append(":");
                    zzaxz.v(stringBuilder2.toString());
                    for (String valueOf : (List) map.get(str3)) {
                        String str4 = "      ";
                        String valueOf2 = String.valueOf(valueOf2);
                        zzaxz.v(valueOf2.length() != 0 ? str4.concat(valueOf2) : new String(str4));
                    }
                }
            }
            zzaxz.v("  Body:");
            if (str2 != null) {
                int i2 = 0;
                while (i2 < Math.min(str2.length(), DefaultOggSeeker.MATCH_BYTE_RANGE)) {
                    int i3 = i2 + 1000;
                    zzaxz.v(str2.substring(i2, Math.min(str2.length(), i3)));
                    i2 = i3;
                }
            } else {
                zzaxz.v("    null");
            }
            StringBuilder stringBuilder3 = new StringBuilder(34);
            stringBuilder3.append("  Response Code:\n    ");
            stringBuilder3.append(i);
            stringBuilder3.append("\n}");
            zzaxz.v(stringBuilder3.toString());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x008c A:{Catch:{ all -> 0x00c2, all -> 0x01c8 }} */
    /* JADX WARNING: Missing block: B:49:0x00ea, code skipped:
            r1 = r8.toString();
     */
    /* JADX WARNING: Missing block: B:51:?, code skipped:
            r8 = new java.io.InputStreamReader(r13.getInputStream());
     */
    /* JADX WARNING: Missing block: B:53:?, code skipped:
            com.google.android.gms.ads.internal.zzbv.zzlf();
            r12 = com.google.android.gms.internal.ads.zzayh.zza((java.io.InputStreamReader) r8);
     */
    /* JADX WARNING: Missing block: B:55:?, code skipped:
            com.google.android.gms.common.util.IOUtils.closeQuietly(r8);
            r5.zzek(r12);
            zza(r1, r14, r12, r11);
            r7.zza(r1, r14, r12);
     */
    /* JADX WARNING: Missing block: B:56:0x010a, code skipped:
            if (r3 == null) goto L_0x0117;
     */
    /* JADX WARNING: Missing block: B:57:0x010c, code skipped:
            r3.zza(r6, "ufe");
     */
    /* JADX WARNING: Missing block: B:58:0x0117, code skipped:
            r1 = r7.zza(r9, r2);
     */
    /* JADX WARNING: Missing block: B:60:?, code skipped:
            r13.disconnect();
     */
    /* JADX WARNING: Missing block: B:61:0x011e, code skipped:
            if (r4 == null) goto L_0x0125;
     */
    /* JADX WARNING: Missing block: B:62:0x0120, code skipped:
            r4.zzeaq.zzxa();
     */
    /* JADX WARNING: Missing block: B:63:0x0125, code skipped:
            return r1;
     */
    /* JADX WARNING: Missing block: B:64:0x0126, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:65:0x0127, code skipped:
            r1 = r0;
     */
    /* JADX WARNING: Missing block: B:66:0x0129, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:67:0x012a, code skipped:
            r1 = r0;
            r8 = null;
     */
    /* JADX WARNING: Missing block: B:69:?, code skipped:
            com.google.android.gms.common.util.IOUtils.closeQuietly(r8);
     */
    /* JADX WARNING: Missing block: B:70:0x012f, code skipped:
            throw r1;
     */
    /* JADX WARNING: Missing block: B:77:0x014a, code skipped:
            com.google.android.gms.internal.ads.zzbbd.zzeo("No location header to follow redirect.");
            r1 = new com.google.android.gms.internal.ads.zzasm(0);
     */
    /* JADX WARNING: Missing block: B:79:?, code skipped:
            r13.disconnect();
     */
    /* JADX WARNING: Missing block: B:80:0x0158, code skipped:
            if (r4 == null) goto L_0x015f;
     */
    /* JADX WARNING: Missing block: B:81:0x015a, code skipped:
            r4.zzeaq.zzxa();
     */
    /* JADX WARNING: Missing block: B:82:0x015f, code skipped:
            return r1;
     */
    /* JADX WARNING: Missing block: B:86:0x0179, code skipped:
            com.google.android.gms.internal.ads.zzbbd.zzeo("Too many redirects.");
            r1 = new com.google.android.gms.internal.ads.zzasm(0);
     */
    /* JADX WARNING: Missing block: B:88:?, code skipped:
            r13.disconnect();
     */
    /* JADX WARNING: Missing block: B:89:0x0187, code skipped:
            if (r4 == null) goto L_0x018e;
     */
    /* JADX WARNING: Missing block: B:90:0x0189, code skipped:
            r4.zzeaq.zzxa();
     */
    /* JADX WARNING: Missing block: B:91:0x018e, code skipped:
            return r1;
     */
    public static com.google.android.gms.internal.ads.zzasm zza(com.google.android.gms.internal.ads.zzasi r18, android.content.Context r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, com.google.android.gms.internal.ads.zzaty r24, com.google.android.gms.internal.ads.zzaba r25, com.google.android.gms.internal.ads.zzatp r26) {
        /*
        r1 = r18;
        r2 = r24;
        r3 = r25;
        r4 = r26;
        if (r3 == 0) goto L_0x000f;
    L_0x000a:
        r6 = r25.zzrg();
        goto L_0x0010;
    L_0x000f:
        r6 = 0;
    L_0x0010:
        r7 = new com.google.android.gms.internal.ads.zzatw;	 Catch:{ IOException -> 0x01d5 }
        r8 = r24.zzwq();	 Catch:{ IOException -> 0x01d5 }
        r7.<init>(r1, r8);	 Catch:{ IOException -> 0x01d5 }
        r8 = "AdRequestServiceImpl: Sending request: ";
        r9 = java.lang.String.valueOf(r21);	 Catch:{ IOException -> 0x01d5 }
        r10 = r9.length();	 Catch:{ IOException -> 0x01d5 }
        if (r10 == 0) goto L_0x002a;
    L_0x0025:
        r8 = r8.concat(r9);	 Catch:{ IOException -> 0x01d5 }
        goto L_0x0030;
    L_0x002a:
        r9 = new java.lang.String;	 Catch:{ IOException -> 0x01d5 }
        r9.<init>(r8);	 Catch:{ IOException -> 0x01d5 }
        r8 = r9;
    L_0x0030:
        com.google.android.gms.internal.ads.zzbbd.zzdn(r8);	 Catch:{ IOException -> 0x01d5 }
        r8 = new java.net.URL;	 Catch:{ IOException -> 0x01d5 }
        r9 = r21;
        r8.<init>(r9);	 Catch:{ IOException -> 0x01d5 }
        r9 = com.google.android.gms.ads.internal.zzbv.zzlm();	 Catch:{ IOException -> 0x01d5 }
        r9 = r9.elapsedRealtime();	 Catch:{ IOException -> 0x01d5 }
        r11 = 0;
        r12 = r11;
    L_0x0044:
        if (r4 == 0) goto L_0x004b;
    L_0x0046:
        r13 = r4.zzeaq;	 Catch:{ IOException -> 0x01d5 }
        r13.zzwz();	 Catch:{ IOException -> 0x01d5 }
    L_0x004b:
        r13 = r8.openConnection();	 Catch:{ IOException -> 0x01d5 }
        r13 = (java.net.HttpURLConnection) r13;	 Catch:{ IOException -> 0x01d5 }
        r14 = com.google.android.gms.ads.internal.zzbv.zzlf();	 Catch:{ all -> 0x01c8 }
        r15 = r19;
        r5 = r20;
        r14.zza(r15, r5, r11, r13);	 Catch:{ all -> 0x01c8 }
        r14 = r24.zzws();	 Catch:{ all -> 0x01c8 }
        if (r14 == 0) goto L_0x0080;
    L_0x0062:
        r14 = android.text.TextUtils.isEmpty(r22);	 Catch:{ all -> 0x01c8 }
        if (r14 != 0) goto L_0x0070;
    L_0x0068:
        r14 = "x-afma-drt-cookie";
        r11 = r22;
        r13.addRequestProperty(r14, r11);	 Catch:{ all -> 0x01c8 }
        goto L_0x0072;
    L_0x0070:
        r11 = r22;
    L_0x0072:
        r14 = android.text.TextUtils.isEmpty(r23);	 Catch:{ all -> 0x01c8 }
        if (r14 != 0) goto L_0x0082;
    L_0x0078:
        r14 = "x-afma-drt-v2-cookie";
        r5 = r23;
        r13.addRequestProperty(r14, r5);	 Catch:{ all -> 0x01c8 }
        goto L_0x0084;
    L_0x0080:
        r11 = r22;
    L_0x0082:
        r5 = r23;
    L_0x0084:
        r14 = r1.zzdxc;	 Catch:{ all -> 0x01c8 }
        r16 = android.text.TextUtils.isEmpty(r14);	 Catch:{ all -> 0x01c8 }
        if (r16 != 0) goto L_0x0096;
    L_0x008c:
        r5 = "Sending webview cookie in ad request header.";
        com.google.android.gms.internal.ads.zzbbd.zzdn(r5);	 Catch:{ all -> 0x01c8 }
        r5 = "Cookie";
        r13.addRequestProperty(r5, r14);	 Catch:{ all -> 0x01c8 }
    L_0x0096:
        r5 = 1;
        if (r2 == 0) goto L_0x00cc;
    L_0x0099:
        r14 = r24.zzwr();	 Catch:{ all -> 0x01c8 }
        r14 = android.text.TextUtils.isEmpty(r14);	 Catch:{ all -> 0x01c8 }
        if (r14 != 0) goto L_0x00cc;
    L_0x00a3:
        r13.setDoOutput(r5);	 Catch:{ all -> 0x01c8 }
        r14 = r24.zzwr();	 Catch:{ all -> 0x01c8 }
        r14 = r14.getBytes();	 Catch:{ all -> 0x01c8 }
        r5 = r14.length;	 Catch:{ all -> 0x01c8 }
        r13.setFixedLengthStreamingMode(r5);	 Catch:{ all -> 0x01c8 }
        r5 = new java.io.BufferedOutputStream;	 Catch:{ all -> 0x00c5 }
        r11 = r13.getOutputStream();	 Catch:{ all -> 0x00c5 }
        r5.<init>(r11);	 Catch:{ all -> 0x00c5 }
        r5.write(r14);	 Catch:{ all -> 0x00c2 }
        com.google.android.gms.common.util.IOUtils.closeQuietly(r5);	 Catch:{ all -> 0x01c8 }
        goto L_0x00cd;
    L_0x00c2:
        r0 = move-exception;
        r1 = r0;
        goto L_0x00c8;
    L_0x00c5:
        r0 = move-exception;
        r1 = r0;
        r5 = 0;
    L_0x00c8:
        com.google.android.gms.common.util.IOUtils.closeQuietly(r5);	 Catch:{ all -> 0x01c8 }
        throw r1;	 Catch:{ all -> 0x01c8 }
    L_0x00cc:
        r14 = 0;
    L_0x00cd:
        r5 = new com.google.android.gms.internal.ads.zzbax;	 Catch:{ all -> 0x01c8 }
        r11 = r1.zzdws;	 Catch:{ all -> 0x01c8 }
        r5.<init>(r11);	 Catch:{ all -> 0x01c8 }
        r5.zza(r13, r14);	 Catch:{ all -> 0x01c8 }
        r11 = r13.getResponseCode();	 Catch:{ all -> 0x01c8 }
        r14 = r13.getHeaderFields();	 Catch:{ all -> 0x01c8 }
        r5.zza(r13, r11);	 Catch:{ all -> 0x01c8 }
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r15 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r11 < r1) goto L_0x0130;
    L_0x00e8:
        if (r11 >= r15) goto L_0x0130;
    L_0x00ea:
        r1 = r8.toString();	 Catch:{ all -> 0x01c8 }
        r8 = new java.io.InputStreamReader;	 Catch:{ all -> 0x0129 }
        r12 = r13.getInputStream();	 Catch:{ all -> 0x0129 }
        r8.<init>(r12);	 Catch:{ all -> 0x0129 }
        com.google.android.gms.ads.internal.zzbv.zzlf();	 Catch:{ all -> 0x0126 }
        r12 = com.google.android.gms.internal.ads.zzayh.zza(r8);	 Catch:{ all -> 0x0126 }
        com.google.android.gms.common.util.IOUtils.closeQuietly(r8);	 Catch:{ all -> 0x01c8 }
        r5.zzek(r12);	 Catch:{ all -> 0x01c8 }
        zza(r1, r14, r12, r11);	 Catch:{ all -> 0x01c8 }
        r7.zza(r1, r14, r12);	 Catch:{ all -> 0x01c8 }
        if (r3 == 0) goto L_0x0117;
    L_0x010c:
        r1 = 1;
        r1 = new java.lang.String[r1];	 Catch:{ all -> 0x01c8 }
        r5 = "ufe";
        r8 = 0;
        r1[r8] = r5;	 Catch:{ all -> 0x01c8 }
        r3.zza(r6, r1);	 Catch:{ all -> 0x01c8 }
    L_0x0117:
        r1 = r7.zza(r9, r2);	 Catch:{ all -> 0x01c8 }
        r13.disconnect();	 Catch:{ IOException -> 0x01d5 }
        if (r4 == 0) goto L_0x0125;
    L_0x0120:
        r2 = r4.zzeaq;	 Catch:{ IOException -> 0x01d5 }
        r2.zzxa();	 Catch:{ IOException -> 0x01d5 }
    L_0x0125:
        return r1;
    L_0x0126:
        r0 = move-exception;
        r1 = r0;
        goto L_0x012c;
    L_0x0129:
        r0 = move-exception;
        r1 = r0;
        r8 = 0;
    L_0x012c:
        com.google.android.gms.common.util.IOUtils.closeQuietly(r8);	 Catch:{ all -> 0x01c8 }
        throw r1;	 Catch:{ all -> 0x01c8 }
    L_0x0130:
        r1 = r8.toString();	 Catch:{ all -> 0x01c8 }
        r5 = 0;
        zza(r1, r14, r5, r11);	 Catch:{ all -> 0x01c8 }
        if (r11 < r15) goto L_0x01a1;
    L_0x013a:
        r1 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r11 >= r1) goto L_0x01a1;
    L_0x013e:
        r1 = "Location";
        r1 = r13.getHeaderField(r1);	 Catch:{ all -> 0x01c8 }
        r8 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x01c8 }
        if (r8 == 0) goto L_0x0160;
    L_0x014a:
        r1 = "No location header to follow redirect.";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r1);	 Catch:{ all -> 0x01c8 }
        r1 = new com.google.android.gms.internal.ads.zzasm;	 Catch:{ all -> 0x01c8 }
        r2 = 0;
        r1.<init>(r2);	 Catch:{ all -> 0x01c8 }
        r13.disconnect();	 Catch:{ IOException -> 0x01d5 }
        if (r4 == 0) goto L_0x015f;
    L_0x015a:
        r2 = r4.zzeaq;	 Catch:{ IOException -> 0x01d5 }
        r2.zzxa();	 Catch:{ IOException -> 0x01d5 }
    L_0x015f:
        return r1;
    L_0x0160:
        r8 = new java.net.URL;	 Catch:{ all -> 0x01c8 }
        r8.<init>(r1);	 Catch:{ all -> 0x01c8 }
        r1 = 1;
        r12 = r12 + r1;
        r1 = com.google.android.gms.internal.ads.zzaan.zzcwx;	 Catch:{ all -> 0x01c8 }
        r11 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ all -> 0x01c8 }
        r1 = r11.zzd(r1);	 Catch:{ all -> 0x01c8 }
        r1 = (java.lang.Integer) r1;	 Catch:{ all -> 0x01c8 }
        r1 = r1.intValue();	 Catch:{ all -> 0x01c8 }
        if (r12 <= r1) goto L_0x018f;
    L_0x0179:
        r1 = "Too many redirects.";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r1);	 Catch:{ all -> 0x01c8 }
        r1 = new com.google.android.gms.internal.ads.zzasm;	 Catch:{ all -> 0x01c8 }
        r2 = 0;
        r1.<init>(r2);	 Catch:{ all -> 0x01c8 }
        r13.disconnect();	 Catch:{ IOException -> 0x01d5 }
        if (r4 == 0) goto L_0x018e;
    L_0x0189:
        r2 = r4.zzeaq;	 Catch:{ IOException -> 0x01d5 }
        r2.zzxa();	 Catch:{ IOException -> 0x01d5 }
    L_0x018e:
        return r1;
    L_0x018f:
        r7.zzl(r14);	 Catch:{ all -> 0x01c8 }
        r13.disconnect();	 Catch:{ IOException -> 0x01d5 }
        if (r4 == 0) goto L_0x019c;
    L_0x0197:
        r1 = r4.zzeaq;	 Catch:{ IOException -> 0x01d5 }
        r1.zzxa();	 Catch:{ IOException -> 0x01d5 }
    L_0x019c:
        r1 = r18;
        r11 = 0;
        goto L_0x0044;
    L_0x01a1:
        r1 = 46;
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01c8 }
        r2.<init>(r1);	 Catch:{ all -> 0x01c8 }
        r1 = "Received error HTTP response code: ";
        r2.append(r1);	 Catch:{ all -> 0x01c8 }
        r2.append(r11);	 Catch:{ all -> 0x01c8 }
        r1 = r2.toString();	 Catch:{ all -> 0x01c8 }
        com.google.android.gms.internal.ads.zzbbd.zzeo(r1);	 Catch:{ all -> 0x01c8 }
        r1 = new com.google.android.gms.internal.ads.zzasm;	 Catch:{ all -> 0x01c8 }
        r2 = 0;
        r1.<init>(r2);	 Catch:{ all -> 0x01c8 }
        r13.disconnect();	 Catch:{ IOException -> 0x01d5 }
        if (r4 == 0) goto L_0x01c7;
    L_0x01c2:
        r2 = r4.zzeaq;	 Catch:{ IOException -> 0x01d5 }
        r2.zzxa();	 Catch:{ IOException -> 0x01d5 }
    L_0x01c7:
        return r1;
    L_0x01c8:
        r0 = move-exception;
        r1 = r0;
        r13.disconnect();	 Catch:{ IOException -> 0x01d5 }
        if (r4 == 0) goto L_0x01d4;
    L_0x01cf:
        r2 = r4.zzeaq;	 Catch:{ IOException -> 0x01d5 }
        r2.zzxa();	 Catch:{ IOException -> 0x01d5 }
    L_0x01d4:
        throw r1;	 Catch:{ IOException -> 0x01d5 }
    L_0x01d5:
        r0 = move-exception;
        r1 = r0;
        r2 = "Error while connecting to ad server: ";
        r1 = r1.getMessage();
        r1 = java.lang.String.valueOf(r1);
        r3 = r1.length();
        if (r3 == 0) goto L_0x01ec;
    L_0x01e7:
        r1 = r2.concat(r1);
        goto L_0x01f1;
    L_0x01ec:
        r1 = new java.lang.String;
        r1.<init>(r2);
    L_0x01f1:
        com.google.android.gms.internal.ads.zzbbd.zzeo(r1);
        r1 = new com.google.android.gms.internal.ads.zzasm;
        r2 = 2;
        r1.<init>(r2);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzatq.zza(com.google.android.gms.internal.ads.zzasi, android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.google.android.gms.internal.ads.zzaty, com.google.android.gms.internal.ads.zzaba, com.google.android.gms.internal.ads.zzatp):com.google.android.gms.internal.ads.zzasm");
    }

    private zzatq(Context context, zzatp zzatp) {
        this.mContext = context;
        this.zzeav = zzatp;
    }

    public final zzasm zzb(zzasi zzasi) {
        return zza(this.mContext, this.zzeav, zzasi, this.zzeaw);
    }

    public final void zza(zzasi zzasi, zzast zzast) {
        zzbv.zzlj().zzd(this.mContext, zzasi.zzbsp);
        zzbcb zzc = zzayf.zzc(new zzats(this, zzasi, zzast));
        zzbv.zzlv().zzaak();
        zzbv.zzlv().getHandler().postDelayed(new zzatt(this, zzc), 60000);
    }
}
