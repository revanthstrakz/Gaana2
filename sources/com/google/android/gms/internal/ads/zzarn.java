package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzuo.zza.zzb;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;

@zzark
public final class zzarn extends zzaxv implements zzasa {
    private final Context mContext;
    @VisibleForTesting
    private zzakr zzdmn;
    @VisibleForTesting
    private zzasi zzdnh;
    @VisibleForTesting
    private zzasm zzdsl;
    private Runnable zzdsm;
    private final Object zzdsn = new Object();
    private final zzarm zzdvp;
    private final zzasj zzdvq;
    private final zzum zzdvr;
    private final zzur zzdvs;
    @VisibleForTesting
    private boolean zzdvt;
    @VisibleForTesting
    private zzazb zzdvu;

    public zzarn(Context context, zzasj zzasj, zzarm zzarm, zzur zzur) {
        this.zzdvp = zzarm;
        this.mContext = context;
        this.zzdvq = zzasj;
        this.zzdvs = zzur;
        this.zzdvr = new zzum(this.zzdvs);
        this.zzdvr.zza(new zzaro(this));
        zzvq zzvq = new zzvq();
        zzvq.zzchy = Integer.valueOf(this.zzdvq.zzbsp.zzeou);
        zzvq.zzchz = Integer.valueOf(this.zzdvq.zzbsp.zzeov);
        zzvq.zzcia = Integer.valueOf(this.zzdvq.zzbsp.zzeow ? 0 : 2);
        this.zzdvr.zza(new zzarp(zzvq));
        if (this.zzdvq.zzdwh != null) {
            this.zzdvr.zza(new zzarq(this));
        }
        zzwf zzwf = this.zzdvq.zzbst;
        if (zzwf.zzckl && "interstitial_mb".equals(zzwf.zzckk)) {
            this.zzdvr.zza(zzarr.zzdvx);
        } else if (zzwf.zzckl && "reward_mb".equals(zzwf.zzckk)) {
            this.zzdvr.zza(zzars.zzdvx);
        } else if (zzwf.zzckn || zzwf.zzckl) {
            this.zzdvr.zza(zzaru.zzdvx);
        } else {
            this.zzdvr.zza(zzart.zzdvx);
        }
        this.zzdvr.zza(zzb.AD_REQUEST);
    }

    public final void zzki() {
        zzbbd.zzdn("AdLoaderBackgroundTask started.");
        this.zzdsm = new zzarv(this);
        zzayh.zzelc.postDelayed(this.zzdsm, ((Long) zzwu.zzpz().zzd(zzaan.zzcte)).longValue());
        long elapsedRealtime = zzbv.zzlm().elapsedRealtime();
        if (this.zzdvq.zzdwg.extras != null) {
            String string = this.zzdvq.zzdwg.extras.getString("_ad");
            if (string != null) {
                this.zzdnh = new zzasi(this.zzdvq, elapsedRealtime, null, null, null, null);
                zza(zzatv.zza(this.mContext, this.zzdnh, string));
                return;
            }
        }
        zzbcr zzbcr = new zzbcr();
        zzayf.zzc(new zzarw(this, zzbcr));
        this.zzdnh = new zzasi(this.zzdvq, elapsedRealtime, zzbv.zzmf().zzx(this.mContext), zzbv.zzmf().zzy(this.mContext), zzbv.zzmf().zzz(this.mContext), zzbv.zzmf().zzaa(this.mContext));
        zzbcr.zzo(this.zzdnh);
    }

    private final void zzd(int i, String str) {
        zzasi zzasi;
        int i2 = i;
        if (i2 == 3 || i2 == -1) {
            zzbbd.zzen(str);
        } else {
            zzbbd.zzeo(str);
        }
        if (this.zzdsl == null) {
            this.zzdsl = new zzasm(i2);
        } else {
            this.zzdsl = new zzasm(i2, this.zzdsl.zzdlx);
        }
        if (this.zzdnh != null) {
            zzasi = this.zzdnh;
        } else {
            zzasi zzasi2 = new zzasi(this.zzdvq, -1, null, null, null, null);
        }
        this.zzdvp.zza(new zzaxg(zzasi, this.zzdsl, this.zzdmn, null, i2, -1, this.zzdsl.zzdyh, null, this.zzdvr, null));
    }

    /* JADX WARNING: Removed duplicated region for block: B:66:0x01b8  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0193  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01d8  */
    public final void zza(@android.support.annotation.NonNull com.google.android.gms.internal.ads.zzasm r14) {
        /*
        r13 = this;
        r0 = "Received ad response.";
        com.google.android.gms.internal.ads.zzbbd.zzdn(r0);
        r13.zzdsl = r14;
        r14 = r13.zzdsl;
        r14 = r14.zzdze;
        r0 = android.text.TextUtils.isEmpty(r14);
        if (r0 != 0) goto L_0x001a;
    L_0x0011:
        r0 = com.google.android.gms.ads.internal.zzbv.zzmf();
        r1 = r13.mContext;
        r0.zzh(r1, r14);
    L_0x001a:
        r14 = com.google.android.gms.ads.internal.zzbv.zzlm();
        r6 = r14.elapsedRealtime();
        r14 = r13.zzdsn;
        monitor-enter(r14);
        r0 = 0;
        r13.zzdvu = r0;	 Catch:{ all -> 0x022b }
        monitor-exit(r14);	 Catch:{ all -> 0x022b }
        r14 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r14 = r14.zzyq();
        r1 = r13.zzdsl;
        r1 = r1.zzdxb;
        r14.zzap(r1);
        r14 = com.google.android.gms.internal.ads.zzaan.zzcrz;
        r1 = com.google.android.gms.internal.ads.zzwu.zzpz();
        r14 = r1.zzd(r14);
        r14 = (java.lang.Boolean) r14;
        r14 = r14.booleanValue();
        if (r14 == 0) goto L_0x006f;
    L_0x004a:
        r14 = r13.zzdsl;
        r14 = r14.zzdxn;
        if (r14 == 0) goto L_0x0060;
    L_0x0050:
        r14 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r14 = r14.zzyq();
        r1 = r13.zzdnh;
        r1 = r1.zzbsn;
        r14.zzds(r1);
        goto L_0x006f;
    L_0x0060:
        r14 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r14 = r14.zzyq();
        r1 = r13.zzdnh;
        r1 = r1.zzbsn;
        r14.zzdt(r1);
    L_0x006f:
        r14 = r13.zzdsl;	 Catch:{ zzarx -> 0x0217 }
        r14 = r14.errorCode;	 Catch:{ zzarx -> 0x0217 }
        r1 = -2;
        r2 = -3;
        if (r14 == r1) goto L_0x009e;
    L_0x0077:
        r14 = r13.zzdsl;	 Catch:{ zzarx -> 0x0217 }
        r14 = r14.errorCode;	 Catch:{ zzarx -> 0x0217 }
        if (r14 == r2) goto L_0x009e;
    L_0x007d:
        r14 = new com.google.android.gms.internal.ads.zzarx;	 Catch:{ zzarx -> 0x0217 }
        r0 = r13.zzdsl;	 Catch:{ zzarx -> 0x0217 }
        r0 = r0.errorCode;	 Catch:{ zzarx -> 0x0217 }
        r1 = 66;
        r2 = new java.lang.StringBuilder;	 Catch:{ zzarx -> 0x0217 }
        r2.<init>(r1);	 Catch:{ zzarx -> 0x0217 }
        r1 = "There was a problem getting an ad response. ErrorCode: ";
        r2.append(r1);	 Catch:{ zzarx -> 0x0217 }
        r2.append(r0);	 Catch:{ zzarx -> 0x0217 }
        r0 = r2.toString();	 Catch:{ zzarx -> 0x0217 }
        r1 = r13.zzdsl;	 Catch:{ zzarx -> 0x0217 }
        r1 = r1.errorCode;	 Catch:{ zzarx -> 0x0217 }
        r14.<init>(r0, r1);	 Catch:{ zzarx -> 0x0217 }
        throw r14;	 Catch:{ zzarx -> 0x0217 }
    L_0x009e:
        r14 = r13.zzdsl;	 Catch:{ zzarx -> 0x0217 }
        r14 = r14.errorCode;	 Catch:{ zzarx -> 0x0217 }
        r1 = 0;
        if (r14 == r2) goto L_0x013a;
    L_0x00a5:
        r14 = r13.zzdsl;	 Catch:{ zzarx -> 0x0217 }
        r14 = r14.zzdyb;	 Catch:{ zzarx -> 0x0217 }
        r14 = android.text.TextUtils.isEmpty(r14);	 Catch:{ zzarx -> 0x0217 }
        if (r14 == 0) goto L_0x00b8;
    L_0x00af:
        r14 = new com.google.android.gms.internal.ads.zzarx;	 Catch:{ zzarx -> 0x0217 }
        r0 = "No fill from ad server.";
        r1 = 3;
        r14.<init>(r0, r1);	 Catch:{ zzarx -> 0x0217 }
        throw r14;	 Catch:{ zzarx -> 0x0217 }
    L_0x00b8:
        r14 = com.google.android.gms.ads.internal.zzbv.zzlj();	 Catch:{ zzarx -> 0x0217 }
        r14 = r14.zzyq();	 Catch:{ zzarx -> 0x0217 }
        r2 = r13.zzdsl;	 Catch:{ zzarx -> 0x0217 }
        r2 = r2.zzdwn;	 Catch:{ zzarx -> 0x0217 }
        r14.zzam(r2);	 Catch:{ zzarx -> 0x0217 }
        r14 = r13.zzdsl;	 Catch:{ zzarx -> 0x0217 }
        r14 = r14.zzdyd;	 Catch:{ zzarx -> 0x0217 }
        if (r14 == 0) goto L_0x010b;
    L_0x00cd:
        r14 = new com.google.android.gms.internal.ads.zzakr;	 Catch:{ JSONException -> 0x00e4 }
        r2 = r13.zzdsl;	 Catch:{ JSONException -> 0x00e4 }
        r2 = r2.zzdyb;	 Catch:{ JSONException -> 0x00e4 }
        r14.<init>(r2);	 Catch:{ JSONException -> 0x00e4 }
        r13.zzdmn = r14;	 Catch:{ JSONException -> 0x00e4 }
        r14 = com.google.android.gms.ads.internal.zzbv.zzlj();	 Catch:{ JSONException -> 0x00e4 }
        r2 = r13.zzdmn;	 Catch:{ JSONException -> 0x00e4 }
        r2 = r2.zzdlv;	 Catch:{ JSONException -> 0x00e4 }
        r14.zzal(r2);	 Catch:{ JSONException -> 0x00e4 }
        goto L_0x0116;
    L_0x00e4:
        r14 = move-exception;
        r0 = "Could not parse mediation config.";
        com.google.android.gms.internal.ads.zzbbd.zzb(r0, r14);	 Catch:{ zzarx -> 0x0217 }
        r14 = new com.google.android.gms.internal.ads.zzarx;	 Catch:{ zzarx -> 0x0217 }
        r0 = "Could not parse mediation config: ";
        r2 = r13.zzdsl;	 Catch:{ zzarx -> 0x0217 }
        r2 = r2.zzdyb;	 Catch:{ zzarx -> 0x0217 }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ zzarx -> 0x0217 }
        r3 = r2.length();	 Catch:{ zzarx -> 0x0217 }
        if (r3 == 0) goto L_0x0101;
    L_0x00fc:
        r0 = r0.concat(r2);	 Catch:{ zzarx -> 0x0217 }
        goto L_0x0107;
    L_0x0101:
        r2 = new java.lang.String;	 Catch:{ zzarx -> 0x0217 }
        r2.<init>(r0);	 Catch:{ zzarx -> 0x0217 }
        r0 = r2;
    L_0x0107:
        r14.<init>(r0, r1);	 Catch:{ zzarx -> 0x0217 }
        throw r14;	 Catch:{ zzarx -> 0x0217 }
    L_0x010b:
        r14 = com.google.android.gms.ads.internal.zzbv.zzlj();	 Catch:{ zzarx -> 0x0217 }
        r2 = r13.zzdsl;	 Catch:{ zzarx -> 0x0217 }
        r2 = r2.zzdlv;	 Catch:{ zzarx -> 0x0217 }
        r14.zzal(r2);	 Catch:{ zzarx -> 0x0217 }
    L_0x0116:
        r14 = r13.zzdsl;	 Catch:{ zzarx -> 0x0217 }
        r14 = r14.zzdxc;	 Catch:{ zzarx -> 0x0217 }
        r14 = android.text.TextUtils.isEmpty(r14);	 Catch:{ zzarx -> 0x0217 }
        if (r14 != 0) goto L_0x013a;
    L_0x0120:
        r14 = "Received cookie from server. Setting webview cookie in CookieManager.";
        com.google.android.gms.internal.ads.zzbbd.zzdn(r14);	 Catch:{ zzarx -> 0x0217 }
        r14 = com.google.android.gms.ads.internal.zzbv.zzlh();	 Catch:{ zzarx -> 0x0217 }
        r2 = r13.mContext;	 Catch:{ zzarx -> 0x0217 }
        r14 = r14.zzba(r2);	 Catch:{ zzarx -> 0x0217 }
        if (r14 == 0) goto L_0x013a;
    L_0x0131:
        r2 = "googleads.g.doubleclick.net";
        r3 = r13.zzdsl;	 Catch:{ zzarx -> 0x0217 }
        r3 = r3.zzdxc;	 Catch:{ zzarx -> 0x0217 }
        r14.setCookie(r2, r3);	 Catch:{ zzarx -> 0x0217 }
    L_0x013a:
        r14 = r13.zzdnh;	 Catch:{ zzarx -> 0x0217 }
        r14 = r14.zzbst;	 Catch:{ zzarx -> 0x0217 }
        r14 = r14.zzckm;	 Catch:{ zzarx -> 0x0217 }
        if (r14 == 0) goto L_0x014a;
    L_0x0142:
        r14 = r13.zzdnh;	 Catch:{ zzarx -> 0x0217 }
        r14 = r13.zza(r14);	 Catch:{ zzarx -> 0x0217 }
        r4 = r14;
        goto L_0x014b;
    L_0x014a:
        r4 = r0;
    L_0x014b:
        r14 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r14 = r14.zzyq();
        r2 = r13.zzdsl;
        r2 = r2.zzdyn;
        r14.zzan(r2);
        r14 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r14 = r14.zzyq();
        r2 = r13.zzdsl;
        r2 = r2.zzdyz;
        r14.zzao(r2);
        r14 = r13.zzdsl;
        r14 = r14.zzdyl;
        r14 = android.text.TextUtils.isEmpty(r14);
        if (r14 != 0) goto L_0x0184;
    L_0x0173:
        r14 = new org.json.JSONObject;	 Catch:{ Exception -> 0x017e }
        r2 = r13.zzdsl;	 Catch:{ Exception -> 0x017e }
        r2 = r2.zzdyl;	 Catch:{ Exception -> 0x017e }
        r14.<init>(r2);	 Catch:{ Exception -> 0x017e }
        r10 = r14;
        goto L_0x0185;
    L_0x017e:
        r14 = move-exception;
        r2 = "Error parsing the JSON for Active View.";
        com.google.android.gms.internal.ads.zzbbd.zzb(r2, r14);
    L_0x0184:
        r10 = r0;
    L_0x0185:
        r14 = r13.zzdnh;
        r14 = r14.zzdwg;
        r14 = r14.zzcjl;
        r2 = r13.zzdsl;
        r2 = r2.zzdzb;
        r3 = 2;
        r5 = 1;
        if (r2 != r3) goto L_0x01b8;
    L_0x0193:
        r0 = java.lang.Boolean.valueOf(r5);
        r1 = com.google.ads.mediation.admob.AdMobAdapter.class;
        r1 = r1.getName();
        r1 = r14.getBundle(r1);
        if (r1 != 0) goto L_0x01b1;
    L_0x01a3:
        r1 = new android.os.Bundle;
        r1.<init>();
        r2 = com.google.ads.mediation.admob.AdMobAdapter.class;
        r2 = r2.getName();
        r14.putBundle(r2, r1);
    L_0x01b1:
        r2 = "render_test_ad_label";
        r1.putBoolean(r2, r5);
    L_0x01b6:
        r12 = r0;
        goto L_0x01d2;
    L_0x01b8:
        r2 = r13.zzdsl;
        r2 = r2.zzdzb;
        if (r2 != r5) goto L_0x01c3;
    L_0x01be:
        r0 = java.lang.Boolean.valueOf(r1);
        goto L_0x01b6;
    L_0x01c3:
        r1 = r13.zzdsl;
        r1 = r1.zzdzb;
        if (r1 != 0) goto L_0x01b6;
    L_0x01c9:
        r0 = com.google.android.gms.internal.ads.zzbal.zzf(r14);
        r0 = java.lang.Boolean.valueOf(r0);
        goto L_0x01b6;
    L_0x01d2:
        r0 = r13.zzdsl;
        r0 = r0.zzdzf;
        if (r0 == 0) goto L_0x01f7;
    L_0x01d8:
        r0 = com.google.ads.mediation.admob.AdMobAdapter.class;
        r0 = r0.getName();
        r0 = r14.getBundle(r0);
        if (r0 != 0) goto L_0x01f2;
    L_0x01e4:
        r0 = new android.os.Bundle;
        r0.<init>();
        r1 = com.google.ads.mediation.admob.AdMobAdapter.class;
        r1 = r1.getName();
        r14.putBundle(r1, r0);
    L_0x01f2:
        r14 = "is_analytics_logging_enabled";
        r0.putBoolean(r14, r5);
    L_0x01f7:
        r14 = new com.google.android.gms.internal.ads.zzaxg;
        r1 = r13.zzdnh;
        r2 = r13.zzdsl;
        r3 = r13.zzdmn;
        r5 = -2;
        r0 = r13.zzdsl;
        r8 = r0.zzdyh;
        r11 = r13.zzdvr;
        r0 = r14;
        r0.<init>(r1, r2, r3, r4, r5, r6, r8, r10, r11, r12);
        r0 = r13.zzdvp;
        r0.zza(r14);
        r14 = com.google.android.gms.internal.ads.zzayh.zzelc;
        r0 = r13.zzdsm;
        r14.removeCallbacks(r0);
        return;
    L_0x0217:
        r14 = move-exception;
        r0 = r14.getErrorCode();
        r14 = r14.getMessage();
        r13.zzd(r0, r14);
        r14 = com.google.android.gms.internal.ads.zzayh.zzelc;
        r0 = r13.zzdsm;
        r14.removeCallbacks(r0);
        return;
    L_0x022b:
        r0 = move-exception;
        monitor-exit(r14);	 Catch:{ all -> 0x022b }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzarn.zza(com.google.android.gms.internal.ads.zzasm):void");
    }

    public final void onStop() {
        synchronized (this.zzdsn) {
            if (this.zzdvu != null) {
                this.zzdvu.cancel();
            }
        }
    }

    @VisibleForTesting
    private final zzwf zza(zzasi zzasi) throws zzarx {
        int i = (this.zzdnh == null || this.zzdnh.zzbtn == null || this.zzdnh.zzbtn.size() <= 1) ? 0 : 1;
        if (i != 0 && this.zzdmn != null && !this.zzdmn.zzdmi) {
            return null;
        }
        int parseInt;
        if (this.zzdsl.zzcko) {
            for (zzwf zzwf : zzasi.zzbst.zzckm) {
                if (zzwf.zzcko) {
                    return new zzwf(zzwf, zzasi.zzbst.zzckm);
                }
            }
        }
        if (this.zzdsl.zzdyg == null) {
            throw new zzarx("The ad response must specify one of the supported ad sizes.", 0);
        }
        String[] split = this.zzdsl.zzdyg.split(AvidJSONUtil.KEY_X);
        String str;
        String valueOf;
        if (split.length != 2) {
            str = "Invalid ad size format from the ad response: ";
            valueOf = String.valueOf(this.zzdsl.zzdyg);
            throw new zzarx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        }
        try {
            parseInt = Integer.parseInt(split[0]);
            i = Integer.parseInt(split[1]);
            for (zzwf zzwf2 : zzasi.zzbst.zzckm) {
                float f = this.mContext.getResources().getDisplayMetrics().density;
                int i2 = zzwf2.width == -1 ? (int) (((float) zzwf2.widthPixels) / f) : zzwf2.width;
                int i3;
                if (zzwf2.height == -2) {
                    i3 = (int) (((float) zzwf2.heightPixels) / f);
                } else {
                    i3 = zzwf2.height;
                }
                if (parseInt == i2 && i == i3 && !zzwf2.zzcko) {
                    return new zzwf(zzwf2, zzasi.zzbst.zzckm);
                }
            }
            str = "The ad size from the ad response was not one of the requested sizes: ";
            valueOf = String.valueOf(this.zzdsl.zzdyg);
            throw new zzarx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        } catch (NumberFormatException unused) {
            str = "Invalid ad size number from the ad response: ";
            valueOf = String.valueOf(this.zzdsl.zzdyg);
            throw new zzarx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzwh() {
        synchronized (this.zzdsn) {
            this.zzdvt = true;
            if (this.zzdvu != null) {
                onStop();
            }
            zzd(2, "Timed out waiting for ad response.");
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzb(zzvp zzvp) {
        zzvp.zzchu.zzchc = this.zzdvq.zzdwh.packageName;
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzc(zzvp zzvp) {
        zzvp.zzchp = this.zzdvq.zzdws;
    }
}
