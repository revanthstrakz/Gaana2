package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.LinkedList;

public abstract class zzcr implements zzcq {
    protected static volatile zzdl zzqo;
    protected MotionEvent zzqu;
    protected LinkedList<MotionEvent> zzqv = new LinkedList();
    protected long zzqw = 0;
    protected long zzqx = 0;
    protected long zzqy = 0;
    protected long zzqz = 0;
    protected long zzra = 0;
    protected long zzrb = 0;
    protected long zzrc = 0;
    protected double zzrd;
    private double zzre;
    private double zzrf;
    protected float zzrg;
    protected float zzrh;
    protected float zzri;
    protected float zzrj;
    private boolean zzrk = false;
    protected boolean zzrl = false;
    protected DisplayMetrics zzrm;

    protected zzcr(Context context) {
        try {
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzctq)).booleanValue()) {
                zzbw.zzw();
            } else {
                zzdq.zzb(zzqo);
            }
            this.zzrm = context.getResources().getDisplayMetrics();
        } catch (Throwable unused) {
        }
    }

    public abstract long zza(StackTraceElement[] stackTraceElementArr) throws zzdi;

    public abstract zzbl zza(Context context, View view, Activity activity);

    public abstract zzbl zza(Context context, zzbi zzbi);

    public abstract zzdr zzb(MotionEvent motionEvent) throws zzdi;

    public void zzb(View view) {
    }

    public final String zza(Context context) {
        if (zzds.isMainThread()) {
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcts)).booleanValue()) {
                throw new IllegalStateException("The caller must not be called from the UI thread.");
            }
        }
        return zza(context, null, false, null, null, null);
    }

    public final String zza(Context context, String str, View view) {
        return zza(context, str, view, null);
    }

    public final String zza(Context context, String str, View view, Activity activity) {
        return zza(context, str, true, view, activity, null);
    }

    public final void zza(android.view.MotionEvent r13) {
        /*
        r12 = this;
        r0 = r12.zzrk;
        r1 = 0;
        if (r0 == 0) goto L_0x0035;
    L_0x0005:
        r2 = 0;
        r12.zzqz = r2;
        r12.zzqy = r2;
        r12.zzqx = r2;
        r12.zzqw = r2;
        r12.zzra = r2;
        r12.zzrc = r2;
        r12.zzrb = r2;
        r0 = r12.zzqv;
        r0 = r0.iterator();
    L_0x001b:
        r2 = r0.hasNext();
        if (r2 == 0) goto L_0x002b;
    L_0x0021:
        r2 = r0.next();
        r2 = (android.view.MotionEvent) r2;
        r2.recycle();
        goto L_0x001b;
    L_0x002b:
        r0 = r12.zzqv;
        r0.clear();
        r0 = 0;
        r12.zzqu = r0;
        r12.zzrk = r1;
    L_0x0035:
        r0 = r13.getAction();
        switch(r0) {
            case 0: goto L_0x0060;
            case 1: goto L_0x003d;
            case 2: goto L_0x003d;
            default: goto L_0x003c;
        };
    L_0x003c:
        goto L_0x0072;
    L_0x003d:
        r0 = r13.getRawX();
        r2 = (double) r0;
        r0 = r13.getRawY();
        r4 = (double) r0;
        r6 = r12.zzre;
        r6 = r2 - r6;
        r8 = r12.zzrf;
        r8 = r4 - r8;
        r10 = r12.zzrd;
        r6 = r6 * r6;
        r8 = r8 * r8;
        r6 = r6 + r8;
        r6 = java.lang.Math.sqrt(r6);
        r10 = r10 + r6;
        r12.zzrd = r10;
        r12.zzre = r2;
        r12.zzrf = r4;
        goto L_0x0072;
    L_0x0060:
        r2 = 0;
        r12.zzrd = r2;
        r0 = r13.getRawX();
        r2 = (double) r0;
        r12.zzre = r2;
        r0 = r13.getRawY();
        r2 = (double) r0;
        r12.zzrf = r2;
    L_0x0072:
        r0 = r13.getAction();
        r2 = 1;
        r4 = 1;
        switch(r0) {
            case 0: goto L_0x0116;
            case 1: goto L_0x00df;
            case 2: goto L_0x0086;
            case 3: goto L_0x007e;
            default: goto L_0x007c;
        };
    L_0x007c:
        goto L_0x0134;
    L_0x007e:
        r0 = r12.zzqz;
        r5 = r0 + r2;
        r12.zzqz = r5;
        goto L_0x0134;
    L_0x0086:
        r2 = r12.zzqx;
        r0 = r13.getHistorySize();
        r0 = r0 + r4;
        r5 = (long) r0;
        r7 = r2 + r5;
        r12.zzqx = r7;
        r13 = r12.zzb(r13);	 Catch:{ zzdi -> 0x0134 }
        if (r13 == 0) goto L_0x00a2;
    L_0x0098:
        r0 = r13.zzgn;	 Catch:{ zzdi -> 0x0134 }
        if (r0 == 0) goto L_0x00a2;
    L_0x009c:
        r0 = r13.zztp;	 Catch:{ zzdi -> 0x0134 }
        if (r0 == 0) goto L_0x00a2;
    L_0x00a0:
        r0 = r4;
        goto L_0x00a3;
    L_0x00a2:
        r0 = r1;
    L_0x00a3:
        if (r0 == 0) goto L_0x00b9;
    L_0x00a5:
        r2 = r12.zzrb;	 Catch:{ zzdi -> 0x0134 }
        r0 = r13.zzgn;	 Catch:{ zzdi -> 0x0134 }
        r5 = r0.longValue();	 Catch:{ zzdi -> 0x0134 }
        r0 = r13.zztp;	 Catch:{ zzdi -> 0x0134 }
        r7 = r0.longValue();	 Catch:{ zzdi -> 0x0134 }
        r9 = r5 + r7;
        r5 = r2 + r9;
        r12.zzrb = r5;	 Catch:{ zzdi -> 0x0134 }
    L_0x00b9:
        r0 = r12.zzrm;	 Catch:{ zzdi -> 0x0134 }
        if (r0 == 0) goto L_0x00c8;
    L_0x00bd:
        if (r13 == 0) goto L_0x00c8;
    L_0x00bf:
        r0 = r13.zzgl;	 Catch:{ zzdi -> 0x0134 }
        if (r0 == 0) goto L_0x00c8;
    L_0x00c3:
        r0 = r13.zztq;	 Catch:{ zzdi -> 0x0134 }
        if (r0 == 0) goto L_0x00c8;
    L_0x00c7:
        r1 = r4;
    L_0x00c8:
        if (r1 == 0) goto L_0x0134;
    L_0x00ca:
        r0 = r12.zzrc;	 Catch:{ zzdi -> 0x0134 }
        r2 = r13.zzgl;	 Catch:{ zzdi -> 0x0134 }
        r2 = r2.longValue();	 Catch:{ zzdi -> 0x0134 }
        r13 = r13.zztq;	 Catch:{ zzdi -> 0x0134 }
        r5 = r13.longValue();	 Catch:{ zzdi -> 0x0134 }
        r7 = r2 + r5;
        r2 = r0 + r7;
        r12.zzrc = r2;	 Catch:{ zzdi -> 0x0134 }
        goto L_0x0134;
    L_0x00df:
        r13 = android.view.MotionEvent.obtain(r13);
        r12.zzqu = r13;
        r13 = r12.zzqv;
        r0 = r12.zzqu;
        r13.add(r0);
        r13 = r12.zzqv;
        r13 = r13.size();
        r0 = 6;
        if (r13 <= r0) goto L_0x0100;
    L_0x00f5:
        r13 = r12.zzqv;
        r13 = r13.remove();
        r13 = (android.view.MotionEvent) r13;
        r13.recycle();
    L_0x0100:
        r0 = r12.zzqy;
        r5 = r0 + r2;
        r12.zzqy = r5;
        r13 = new java.lang.Throwable;	 Catch:{ zzdi -> 0x0134 }
        r13.<init>();	 Catch:{ zzdi -> 0x0134 }
        r13 = r13.getStackTrace();	 Catch:{ zzdi -> 0x0134 }
        r0 = r12.zza(r13);	 Catch:{ zzdi -> 0x0134 }
        r12.zzra = r0;	 Catch:{ zzdi -> 0x0134 }
        goto L_0x0134;
    L_0x0116:
        r0 = r13.getX();
        r12.zzrg = r0;
        r0 = r13.getY();
        r12.zzrh = r0;
        r0 = r13.getRawX();
        r12.zzri = r0;
        r13 = r13.getRawY();
        r12.zzrj = r13;
        r0 = r12.zzqw;
        r5 = r0 + r2;
        r12.zzqw = r5;
    L_0x0134:
        r12.zzrl = r4;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcr.zza(android.view.MotionEvent):void");
    }

    public final void zza(int i, int i2, int i3) {
        if (this.zzqu != null) {
            this.zzqu.recycle();
        }
        if (this.zzrm != null) {
            this.zzqu = MotionEvent.obtain(0, (long) i3, 1, this.zzrm.density * ((float) i), this.zzrm.density * ((float) i2), 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        } else {
            this.zzqu = null;
        }
        this.zzrl = false;
    }

    private final String zza(Context context, String str, boolean z, View view, Activity activity, byte[] bArr) {
        zzbl zza;
        if (z) {
            try {
                zza = zza(context, view, activity);
                this.zzrk = true;
            } catch (UnsupportedEncodingException | GeneralSecurityException unused) {
                return Integer.toString(7);
            } catch (Throwable unused2) {
                return Integer.toString(3);
            }
        }
        zza = zza(context, null);
        if (zza != null) {
            if (zza.zzamj() != 0) {
                return zzbw.zza(zza, str);
            }
        }
        return Integer.toString(5);
    }
}
