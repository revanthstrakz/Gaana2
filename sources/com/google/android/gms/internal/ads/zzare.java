package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.til.colombia.android.internal.e;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@zzark
public final class zzare implements zzari {
    private static final Object sLock = new Object();
    @VisibleForTesting
    private static zzari zzdvf;
    @VisibleForTesting
    private static zzari zzdvg;
    private final zzbbi zzbpt;
    private final Object zzdvh;
    private final Context zzdvi;
    private final WeakHashMap<Thread, Boolean> zzdvj;
    private final ExecutorService zzsq;

    public static zzari zzn(Context context) {
        synchronized (sLock) {
            if (zzdvf == null) {
                if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcoe)).booleanValue()) {
                    zzdvf = new zzare(context);
                } else {
                    zzdvf = new zzarj();
                }
            }
        }
        return zzdvf;
    }

    public static zzari zzc(Context context, zzbbi zzbbi) {
        synchronized (sLock) {
            if (zzdvg == null) {
                if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcoe)).booleanValue()) {
                    zzare zzare = new zzare(context, zzbbi);
                    Thread thread = Looper.getMainLooper().getThread();
                    if (thread != null) {
                        synchronized (zzare.zzdvh) {
                            zzare.zzdvj.put(thread, Boolean.valueOf(true));
                        }
                        thread.setUncaughtExceptionHandler(new zzarg(zzare, thread.getUncaughtExceptionHandler()));
                    }
                    Thread.setDefaultUncaughtExceptionHandler(new zzarf(zzare, Thread.getDefaultUncaughtExceptionHandler()));
                    zzdvg = zzare;
                } else {
                    zzdvg = new zzarj();
                }
            }
        }
        return zzdvg;
    }

    private zzare(Context context) {
        this(context, zzbbi.zzaav());
    }

    private zzare(Context context, zzbbi zzbbi) {
        this.zzdvh = new Object();
        this.zzdvj = new WeakHashMap();
        this.zzsq = Executors.newCachedThreadPool();
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.zzdvi = context;
        this.zzbpt = zzbbi;
    }

    /* Access modifiers changed, original: protected|final */
    /* JADX WARNING: Missing block: B:15:0x003f, code skipped:
            if (r3 == null) goto L_0x0043;
     */
    public final void zza(java.lang.Thread r10, java.lang.Throwable r11) {
        /*
        r9 = this;
        r10 = 1;
        r0 = 0;
        if (r11 == 0) goto L_0x0042;
    L_0x0004:
        r1 = r11;
        r2 = r0;
        r3 = r2;
    L_0x0007:
        if (r1 == 0) goto L_0x003d;
    L_0x0009:
        r4 = r1.getStackTrace();
        r5 = r4.length;
        r6 = r3;
        r3 = r2;
        r2 = r0;
    L_0x0011:
        if (r2 >= r5) goto L_0x0036;
    L_0x0013:
        r7 = r4[r2];
        r8 = r7.getClassName();
        r8 = com.google.android.gms.internal.ads.zzbat.zzej(r8);
        if (r8 == 0) goto L_0x0020;
    L_0x001f:
        r3 = r10;
    L_0x0020:
        r8 = r9.getClass();
        r8 = r8.getName();
        r7 = r7.getClassName();
        r7 = r8.equals(r7);
        if (r7 == 0) goto L_0x0033;
    L_0x0032:
        r6 = r10;
    L_0x0033:
        r2 = r2 + 1;
        goto L_0x0011;
    L_0x0036:
        r1 = r1.getCause();
        r2 = r3;
        r3 = r6;
        goto L_0x0007;
    L_0x003d:
        if (r2 == 0) goto L_0x0042;
    L_0x003f:
        if (r3 != 0) goto L_0x0042;
    L_0x0041:
        goto L_0x0043;
    L_0x0042:
        r10 = r0;
    L_0x0043:
        if (r10 == 0) goto L_0x004c;
    L_0x0045:
        r10 = "";
        r0 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r9.zza(r11, r10, r0);
    L_0x004c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzare.zza(java.lang.Thread, java.lang.Throwable):void");
    }

    public final void zza(Throwable th, String str) {
        zza(th, str, 1.0f);
    }

    public final void zza(Throwable th, String str, float f) {
        if (zzbat.zzc(th) != null) {
            String name = th.getClass().getName();
            StringWriter stringWriter = new StringWriter();
            zzbpe.zza(th, new PrintWriter(stringWriter));
            String stringWriter2 = stringWriter.toString();
            int i = 0;
            int i2 = 1;
            int i3 = Math.random() < ((double) f) ? 1 : 0;
            if (f > 0.0f) {
                i2 = (int) (1.0f / f);
            }
            if (i3 != 0) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(zza(name, stringWriter2, str, i2).toString());
                arrayList = arrayList;
                int size = arrayList.size();
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    this.zzsq.submit(new zzarh(this, new zzbbh(), (String) obj));
                }
            }
        }
    }

    @VisibleForTesting
    private final Builder zza(String str, String str2, String str3, int i) {
        boolean isCallerInstantApp;
        try {
            isCallerInstantApp = Wrappers.packageManager(this.zzdvi).isCallerInstantApp();
        } catch (Throwable th) {
            zzbbd.zzb("Error fetching instant app info", th);
            isCallerInstantApp = false;
        }
        String str4 = "unknown";
        try {
            str4 = this.zzdvi.getPackageName();
        } catch (Throwable unused) {
            zzbbd.zzeo("Cannot obtain package name, proceeding.");
        }
        Builder appendQueryParameter = new Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("is_aia", Boolean.toString(isCallerInstantApp)).appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter(e.C, VERSION.RELEASE).appendQueryParameter("api", String.valueOf(VERSION.SDK_INT));
        String str5 = "device";
        String str6 = Build.MANUFACTURER;
        String str7 = Build.MODEL;
        if (!str7.startsWith(str6)) {
            StringBuilder stringBuilder = new StringBuilder((1 + String.valueOf(str6).length()) + String.valueOf(str7).length());
            stringBuilder.append(str6);
            stringBuilder.append(" ");
            stringBuilder.append(str7);
            str7 = stringBuilder.toString();
        }
        return appendQueryParameter.appendQueryParameter(str5, str7).appendQueryParameter("js", this.zzbpt.zzdp).appendQueryParameter(e.A, str4).appendQueryParameter("exceptiontype", str).appendQueryParameter("stacktrace", str2).appendQueryParameter("eids", TextUtils.join(",", zzaan.zzqw())).appendQueryParameter("exceptionkey", str3).appendQueryParameter(e.aw, "221522000").appendQueryParameter("rc", "dev").appendQueryParameter("session_id", zzwu.zzqa()).appendQueryParameter("sampling_rate", Integer.toString(i)).appendQueryParameter("pb_tm", String.valueOf(zzwu.zzpz().zzd(zzaan.zzcyf)));
    }
}
