package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.WindowManager;

@TargetApi(16)
public final class zzqs {
    private final zzqt zzbjy;
    private final boolean zzbjz;
    private final long zzbka;
    private final long zzbkb;
    private long zzbkc;
    private long zzbkd;
    private long zzbke;
    private boolean zzbkf;
    private long zzbkg;
    private long zzbkh;
    private long zzbki;

    public zzqs() {
        this(-1.0d);
    }

    public zzqs(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        this(windowManager.getDefaultDisplay() != null ? (double) windowManager.getDefaultDisplay().getRefreshRate() : -1.0d);
    }

    private zzqs(double d) {
        this.zzbjz = d != -1.0d;
        if (this.zzbjz) {
            this.zzbjy = zzqt.zzhv();
            this.zzbka = (long) (1.0E9d / d);
            this.zzbkb = (this.zzbka * 80) / 100;
            return;
        }
        this.zzbjy = null;
        this.zzbka = -1;
        this.zzbkb = -1;
    }

    public final void enable() {
        this.zzbkf = false;
        if (this.zzbjz) {
            this.zzbjy.zzhw();
        }
    }

    public final void disable() {
        if (this.zzbjz) {
            this.zzbjy.zzhx();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0055  */
    public final long zzh(long r19, long r21) {
        /*
        r18 = this;
        r0 = r18;
        r1 = r19;
        r3 = r21;
        r5 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r5 = r5 * r1;
        r7 = r0.zzbkf;
        if (r7 == 0) goto L_0x004d;
    L_0x000d:
        r7 = r0.zzbkc;
        r9 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1));
        if (r9 == 0) goto L_0x001f;
    L_0x0013:
        r7 = r0.zzbki;
        r9 = 1;
        r11 = r7 + r9;
        r0.zzbki = r11;
        r7 = r0.zzbke;
        r0.zzbkd = r7;
    L_0x001f:
        r7 = r0.zzbki;
        r9 = 6;
        r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1));
        r7 = 0;
        if (r11 < 0) goto L_0x0045;
    L_0x0028:
        r8 = r0.zzbkh;
        r10 = r5 - r8;
        r8 = r0.zzbki;
        r10 = r10 / r8;
        r8 = r0.zzbkd;
        r12 = r8 + r10;
        r8 = r0.zzi(r12, r3);
        if (r8 == 0) goto L_0x003c;
    L_0x0039:
        r0.zzbkf = r7;
        goto L_0x004d;
    L_0x003c:
        r7 = r0.zzbkg;
        r9 = r7 + r12;
        r7 = r0.zzbkh;
        r14 = r9 - r7;
        goto L_0x004f;
    L_0x0045:
        r8 = r0.zzi(r5, r3);
        if (r8 == 0) goto L_0x004d;
    L_0x004b:
        r0.zzbkf = r7;
    L_0x004d:
        r14 = r3;
        r12 = r5;
    L_0x004f:
        r7 = r0.zzbkf;
        r8 = 0;
        if (r7 != 0) goto L_0x005e;
    L_0x0055:
        r0.zzbkh = r5;
        r0.zzbkg = r3;
        r0.zzbki = r8;
        r3 = 1;
        r0.zzbkf = r3;
    L_0x005e:
        r0.zzbkc = r1;
        r0.zzbke = r12;
        r1 = r0.zzbjy;
        if (r1 == 0) goto L_0x0098;
    L_0x0066:
        r1 = r0.zzbjy;
        r1 = r1.zzbkj;
        r3 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1));
        if (r3 != 0) goto L_0x006f;
    L_0x006e:
        goto L_0x0098;
    L_0x006f:
        r1 = r0.zzbjy;
        r1 = r1.zzbkj;
        r3 = r0.zzbka;
        r5 = r14 - r1;
        r5 = r5 / r3;
        r5 = r5 * r3;
        r7 = r1 + r5;
        r1 = (r14 > r7 ? 1 : (r14 == r7 ? 0 : -1));
        if (r1 > 0) goto L_0x0087;
    L_0x007f:
        r1 = r7 - r3;
        r16 = r1;
        r1 = r7;
        r7 = r16;
        goto L_0x0089;
    L_0x0087:
        r1 = r7 + r3;
    L_0x0089:
        r3 = r1 - r14;
        r5 = r14 - r7;
        r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r9 >= 0) goto L_0x0092;
    L_0x0091:
        goto L_0x0093;
    L_0x0092:
        r1 = r7;
    L_0x0093:
        r3 = r0.zzbkb;
        r5 = r1 - r3;
        return r5;
    L_0x0098:
        return r14;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzqs.zzh(long, long):long");
    }

    private final boolean zzi(long j, long j2) {
        return Math.abs((j2 - this.zzbkg) - (j - this.zzbkh)) > 20000000;
    }
}
