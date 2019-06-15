package com.google.android.gms.internal.ads;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.common.util.VisibleForTesting;

@zzark
public final class zzaxp {
    private final Object mLock = new Object();
    @VisibleForTesting
    private long zzejm = -1;
    @VisibleForTesting
    private long zzejn = -1;
    @VisibleForTesting
    private int zzejo = -1;
    @VisibleForTesting
    int zzejp = -1;
    @VisibleForTesting
    private long zzejq = 0;
    @VisibleForTesting
    private final String zzejr;
    private final zzayb zzejs;
    @VisibleForTesting
    private int zzejt = 0;
    @VisibleForTesting
    private int zzeju = 0;

    public zzaxp(String str, zzayb zzayb) {
        this.zzejr = str;
        this.zzejs = zzayb;
    }

    public final void zzxw() {
        synchronized (this.mLock) {
            this.zzejt++;
        }
    }

    public final void zzxv() {
        synchronized (this.mLock) {
            this.zzeju++;
        }
    }

    /* JADX WARNING: Missing block: B:24:0x007b, code skipped:
            return;
     */
    public final void zzb(com.google.android.gms.internal.ads.zzwb r11, long r12) {
        /*
        r10 = this;
        r0 = r10.mLock;
        monitor-enter(r0);
        r1 = r10.zzejs;	 Catch:{ all -> 0x007c }
        r1 = r1.zzzj();	 Catch:{ all -> 0x007c }
        r3 = com.google.android.gms.ads.internal.zzbv.zzlm();	 Catch:{ all -> 0x007c }
        r3 = r3.currentTimeMillis();	 Catch:{ all -> 0x007c }
        r5 = r10.zzejn;	 Catch:{ all -> 0x007c }
        r7 = -1;
        r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r9 != 0) goto L_0x0042;
    L_0x0019:
        r5 = r3 - r1;
        r1 = com.google.android.gms.internal.ads.zzaan.zzcrn;	 Catch:{ all -> 0x007c }
        r2 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ all -> 0x007c }
        r1 = r2.zzd(r1);	 Catch:{ all -> 0x007c }
        r1 = (java.lang.Long) r1;	 Catch:{ all -> 0x007c }
        r1 = r1.longValue();	 Catch:{ all -> 0x007c }
        r7 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1));
        if (r7 <= 0) goto L_0x0033;
    L_0x002f:
        r1 = -1;
        r10.zzejp = r1;	 Catch:{ all -> 0x007c }
        goto L_0x003b;
    L_0x0033:
        r1 = r10.zzejs;	 Catch:{ all -> 0x007c }
        r1 = r1.zzzk();	 Catch:{ all -> 0x007c }
        r10.zzejp = r1;	 Catch:{ all -> 0x007c }
    L_0x003b:
        r10.zzejn = r12;	 Catch:{ all -> 0x007c }
        r12 = r10.zzejn;	 Catch:{ all -> 0x007c }
        r10.zzejm = r12;	 Catch:{ all -> 0x007c }
        goto L_0x0044;
    L_0x0042:
        r10.zzejm = r12;	 Catch:{ all -> 0x007c }
    L_0x0044:
        r12 = 1;
        if (r11 == 0) goto L_0x0058;
    L_0x0047:
        r13 = r11.extras;	 Catch:{ all -> 0x007c }
        if (r13 == 0) goto L_0x0058;
    L_0x004b:
        r11 = r11.extras;	 Catch:{ all -> 0x007c }
        r13 = "gw";
        r1 = 2;
        r11 = r11.getInt(r13, r1);	 Catch:{ all -> 0x007c }
        if (r11 != r12) goto L_0x0058;
    L_0x0056:
        monitor-exit(r0);	 Catch:{ all -> 0x007c }
        return;
    L_0x0058:
        r11 = r10.zzejo;	 Catch:{ all -> 0x007c }
        r11 = r11 + r12;
        r10.zzejo = r11;	 Catch:{ all -> 0x007c }
        r11 = r10.zzejp;	 Catch:{ all -> 0x007c }
        r11 = r11 + r12;
        r10.zzejp = r11;	 Catch:{ all -> 0x007c }
        r11 = r10.zzejp;	 Catch:{ all -> 0x007c }
        if (r11 != 0) goto L_0x0070;
    L_0x0066:
        r11 = 0;
        r10.zzejq = r11;	 Catch:{ all -> 0x007c }
        r11 = r10.zzejs;	 Catch:{ all -> 0x007c }
        r11.zzav(r3);	 Catch:{ all -> 0x007c }
        goto L_0x007a;
    L_0x0070:
        r11 = r10.zzejs;	 Catch:{ all -> 0x007c }
        r11 = r11.zzzl();	 Catch:{ all -> 0x007c }
        r1 = r3 - r11;
        r10.zzejq = r1;	 Catch:{ all -> 0x007c }
    L_0x007a:
        monitor-exit(r0);	 Catch:{ all -> 0x007c }
        return;
    L_0x007c:
        r11 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x007c }
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaxp.zzb(com.google.android.gms.internal.ads.zzwb, long):void");
    }

    public final Bundle zzl(Context context, String str) {
        Bundle bundle;
        synchronized (this.mLock) {
            bundle = new Bundle();
            bundle.putString("session_id", this.zzejr);
            bundle.putLong("basets", this.zzejn);
            bundle.putLong("currts", this.zzejm);
            bundle.putString("seq_num", str);
            bundle.putInt("preqs", this.zzejo);
            bundle.putInt("preqs_in_session", this.zzejp);
            bundle.putLong("time_in_session", this.zzejq);
            bundle.putInt("pclick", this.zzejt);
            bundle.putInt("pimp", this.zzeju);
            bundle.putBoolean("support_transparent_background", zzaf(context));
        }
        return bundle;
    }

    private static boolean zzaf(Context context) {
        int identifier = context.getResources().getIdentifier("Theme.Translucent", "style", "android");
        if (identifier == 0) {
            zzbbd.zzen("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        try {
            if (identifier == context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), AdActivity.CLASS_NAME), 0).theme) {
                return true;
            }
            zzbbd.zzen("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        } catch (NameNotFoundException unused) {
            zzbbd.zzeo("Fail to fetch AdActivity theme");
            zzbbd.zzen("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
    }
}
