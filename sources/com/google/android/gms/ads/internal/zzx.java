package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Rect;
import android.location.Location;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.WebView;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzalg;
import com.google.android.gms.internal.ads.zzals;
import com.google.android.gms.internal.ads.zzalv;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzawr;
import com.google.android.gms.internal.ads.zzawv;
import com.google.android.gms.internal.ads.zzaxf;
import com.google.android.gms.internal.ads.zzaxg;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzbbi;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzbgq;
import com.google.android.gms.internal.ads.zzbht;
import com.google.android.gms.internal.ads.zzsc;
import com.google.android.gms.internal.ads.zzwb;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzwu;
import com.google.android.gms.internal.ads.zzyp;
import com.google.android.gms.internal.ads.zzzs;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

@zzark
public final class zzx extends zzh implements OnGlobalLayoutListener, OnScrollChangedListener {
    private boolean zzbli;
    private boolean zzbmz;
    private WeakReference<Object> zzbna = new WeakReference(null);

    public zzx(Context context, zzwf zzwf, String str, zzalg zzalg, zzbbi zzbbi, zzv zzv) {
        super(context, zzwf, str, zzalg, zzbbi, zzv);
    }

    /* Access modifiers changed, original: protected|final */
    public final zzbgg zza(zzaxg zzaxg, @Nullable zzw zzw, @Nullable zzawr zzawr) throws zzbgq {
        if (this.zzbls.zzbst.zzckm == null && this.zzbls.zzbst.zzcko) {
            zzwf zzwf;
            zzbw zzbw = this.zzbls;
            if (zzaxg.zzehy.zzcko) {
                zzwf = this.zzbls.zzbst;
            } else {
                AdSize adSize;
                String str = zzaxg.zzehy.zzdyg;
                if (str != null) {
                    String[] split = str.split("[xX]");
                    split[0] = split[0].trim();
                    split[1] = split[1].trim();
                    adSize = new AdSize(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                } else {
                    adSize = this.zzbls.zzbst.zzpp();
                }
                zzwf = new zzwf(this.zzbls.zzsp, adSize);
            }
            zzbw.zzbst = zzwf;
        }
        return super.zza(zzaxg, zzw, zzawr);
    }

    public final boolean zzb(zzwb zzwb) {
        zzh zzh = this;
        zzwb zzwb2 = zzwb;
        boolean z = false;
        zzh.zzbmz = false;
        zzh.zzblx = null;
        if (zzwb2.zzcjg != zzh.zzbli) {
            int i = zzwb2.versionCode;
            long j = zzwb2.zzcjb;
            Bundle bundle = zzwb2.extras;
            int i2 = zzwb2.zzcjc;
            List list = zzwb2.zzcjd;
            boolean z2 = zzwb2.zzcje;
            int i3 = zzwb2.zzcjf;
            if (zzwb2.zzcjg || zzh.zzbli) {
                z = true;
            }
            boolean z3 = z;
            String str = zzwb2.zzcjh;
            zzzs zzzs = zzwb2.zzcji;
            Location location = zzwb2.zzcjj;
            zzzs zzzs2 = zzzs;
            Location location2 = location;
            zzwb2 = new zzwb(i, j, bundle, i2, list, z2, i3, z3, str, zzzs2, location2, zzwb2.zzcjk, zzwb2.zzcjl, zzwb2.zzcjm, zzwb2.zzcjn, zzwb2.zzcjo, zzwb2.zzcjp, zzwb2.zzcjq, null, zzwb2.zzcjs, zzwb2.zzcjt);
            zzh = this;
        }
        return super.zzb(zzwb2);
    }

    /* JADX WARNING: Missing block: B:25:0x007c, code skipped:
            if (((java.lang.Boolean) com.google.android.gms.internal.ads.zzwu.zzpz().zzd(com.google.android.gms.internal.ads.zzaan.zzcuc)).booleanValue() != false) goto L_0x007e;
     */
    public final boolean zza(@android.support.annotation.Nullable com.google.android.gms.internal.ads.zzaxf r5, com.google.android.gms.internal.ads.zzaxf r6) {
        /*
        r4 = this;
        r0 = super.zza(r5, r6);
        r1 = 0;
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = r4.zzbls;
        r0 = r0.zzmj();
        if (r0 == 0) goto L_0x0025;
    L_0x0010:
        r5 = r4.zzd(r5, r6);
        if (r5 != 0) goto L_0x0025;
    L_0x0016:
        r5 = r6.zzehw;
        if (r5 == 0) goto L_0x0021;
    L_0x001a:
        r5 = r6.zzehw;
        r6 = com.google.android.gms.internal.ads.zzuo.zza.zzb.AD_FAILED_TO_LOAD;
        r5.zza(r6);
    L_0x0021:
        r4.zzbr(r1);
        return r1;
    L_0x0025:
        r4.zzb(r6, r1);
        r5 = r6.zzdyu;
        r0 = 0;
        if (r5 == 0) goto L_0x0064;
    L_0x002d:
        r4.zzd(r6);
        com.google.android.gms.ads.internal.zzbv.zzme();
        r5 = r4.zzbls;
        r5 = r5.zzbsq;
        com.google.android.gms.internal.ads.zzbct.zza(r5, r4);
        com.google.android.gms.ads.internal.zzbv.zzme();
        r5 = r4.zzbls;
        r5 = r5.zzbsq;
        com.google.android.gms.internal.ads.zzbct.zza(r5, r4);
        r5 = r6.zzehi;
        if (r5 != 0) goto L_0x0081;
    L_0x0048:
        r5 = new com.google.android.gms.ads.internal.zzy;
        r5.<init>(r4);
        r1 = r6.zzdrv;
        if (r1 == 0) goto L_0x0058;
    L_0x0051:
        r1 = r6.zzdrv;
        r1 = r1.zzadl();
        goto L_0x0059;
    L_0x0058:
        r1 = r0;
    L_0x0059:
        if (r1 == 0) goto L_0x0081;
    L_0x005b:
        r2 = new com.google.android.gms.ads.internal.zzz;
        r2.<init>(r6, r5);
        r1.zza(r2);
        goto L_0x0081;
    L_0x0064:
        r5 = r4.zzbls;
        r5 = r5.zzmk();
        if (r5 == 0) goto L_0x007e;
    L_0x006c:
        r5 = com.google.android.gms.internal.ads.zzaan.zzcuc;
        r2 = com.google.android.gms.internal.ads.zzwu.zzpz();
        r5 = r2.zzd(r5);
        r5 = (java.lang.Boolean) r5;
        r5 = r5.booleanValue();
        if (r5 == 0) goto L_0x0081;
    L_0x007e:
        r4.zza(r6, r1);
    L_0x0081:
        r5 = r6.zzdrv;
        if (r5 == 0) goto L_0x00a5;
    L_0x0085:
        r5 = r6.zzdrv;
        r5 = r5.zzabu();
        r1 = r6.zzdrv;
        r1 = r1.zzadl();
        if (r1 == 0) goto L_0x0096;
    L_0x0093:
        r1.zzaeg();
    L_0x0096:
        r1 = r4.zzbls;
        r1 = r1.zzbtj;
        if (r1 == 0) goto L_0x00a5;
    L_0x009c:
        if (r5 == 0) goto L_0x00a5;
    L_0x009e:
        r1 = r4.zzbls;
        r1 = r1.zzbtj;
        r5.zzb(r1);
    L_0x00a5:
        r5 = com.google.android.gms.common.util.PlatformVersion.isAtLeastIceCreamSandwich();
        if (r5 == 0) goto L_0x0142;
    L_0x00ab:
        r5 = r4.zzbls;
        r5 = r5.zzmj();
        if (r5 == 0) goto L_0x011e;
    L_0x00b3:
        r5 = r6.zzdrv;
        if (r5 == 0) goto L_0x0139;
    L_0x00b7:
        r5 = r6.zzehh;
        if (r5 == 0) goto L_0x00c4;
    L_0x00bb:
        r5 = r4.zzblu;
        r0 = r4.zzbls;
        r0 = r0.zzbst;
        r5.zza(r0, r6);
    L_0x00c4:
        r5 = r6.zzdrv;
        r0 = r5.getView();
        r5 = new com.google.android.gms.internal.ads.zzsc;
        r1 = r4.zzbls;
        r1 = r1.zzsp;
        r5.<init>(r1, r0);
        r1 = com.google.android.gms.ads.internal.zzbv.zzmf();
        r2 = r4.zzbls;
        r2 = r2.zzsp;
        r1 = r1.zzv(r2);
        if (r1 == 0) goto L_0x0103;
    L_0x00e1:
        r1 = r6.zzdwg;
        r1 = com.google.android.gms.ads.internal.zza.zza(r1);
        if (r1 == 0) goto L_0x0103;
    L_0x00e9:
        r1 = r4.zzbls;
        r1 = r1.zzbsn;
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 != 0) goto L_0x0103;
    L_0x00f3:
        r1 = new com.google.android.gms.internal.ads.zzawv;
        r2 = r4.zzbls;
        r2 = r2.zzsp;
        r3 = r4.zzbls;
        r3 = r3.zzbsn;
        r1.<init>(r2, r3);
        r5.zza(r1);
    L_0x0103:
        r1 = r6.zzmu();
        if (r1 == 0) goto L_0x010f;
    L_0x0109:
        r1 = r6.zzdrv;
        r5.zza(r1);
        goto L_0x0139;
    L_0x010f:
        r1 = r6.zzdrv;
        r1 = r1.zzadl();
        r2 = new com.google.android.gms.ads.internal.zzaa;
        r2.<init>(r5, r6);
        r1.zza(r2);
        goto L_0x0139;
    L_0x011e:
        r5 = r4.zzbls;
        r5 = r5.zzbtv;
        if (r5 == 0) goto L_0x0139;
    L_0x0124:
        r5 = r6.zzehh;
        if (r5 == 0) goto L_0x0139;
    L_0x0128:
        r5 = r4.zzblu;
        r0 = r4.zzbls;
        r0 = r0.zzbst;
        r1 = r4.zzbls;
        r1 = r1.zzbtv;
        r5.zza(r0, r6, r1);
        r5 = r4.zzbls;
        r0 = r5.zzbtv;
    L_0x0139:
        r5 = r6.zzdyd;
        if (r5 != 0) goto L_0x0142;
    L_0x013d:
        r5 = r4.zzbls;
        r5.zzj(r0);
    L_0x0142:
        r5 = 1;
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzx.zza(com.google.android.gms.internal.ads.zzaxf, com.google.android.gms.internal.ads.zzaxf):boolean");
    }

    private final boolean zzd(@Nullable zzaxf zzaxf, zzaxf zzaxf2) {
        if (zzaxf2.zzdyd) {
            View zze = zzas.zze(zzaxf2);
            if (zze == null) {
                zzbbd.zzeo("Could not get mediation view");
                return false;
            }
            View nextView = this.zzbls.zzbsq.getNextView();
            if (nextView != null) {
                if (nextView instanceof zzbgg) {
                    ((zzbgg) nextView).destroy();
                }
                this.zzbls.zzbsq.removeView(nextView);
            }
            if (!zzas.zzf(zzaxf2)) {
                try {
                    if (zzbv.zzmf().zzv(this.zzbls.zzsp)) {
                        new zzsc(this.zzbls.zzsp, zze).zza(new zzawv(this.zzbls.zzsp, this.zzbls.zzbsn));
                    }
                    if (zzaxf2.zzehl != null) {
                        this.zzbls.zzbsq.setMinimumWidth(zzaxf2.zzehl.widthPixels);
                        this.zzbls.zzbsq.setMinimumHeight(zzaxf2.zzehl.heightPixels);
                    }
                    zzg(zze);
                } catch (Exception e) {
                    zzbv.zzlj().zza(e, "BannerAdManager.swapViews");
                    zzbbd.zzc("Could not add mediation view to view hierarchy.", e);
                    return false;
                }
            }
        } else if (!(zzaxf2.zzehl == null || zzaxf2.zzdrv == null)) {
            zzaxf2.zzdrv.zza(zzbht.zzb(zzaxf2.zzehl));
            this.zzbls.zzbsq.removeAllViews();
            this.zzbls.zzbsq.setMinimumWidth(zzaxf2.zzehl.widthPixels);
            this.zzbls.zzbsq.setMinimumHeight(zzaxf2.zzehl.heightPixels);
            zzg(zzaxf2.zzdrv.getView());
        }
        if (this.zzbls.zzbsq.getChildCount() > 1) {
            this.zzbls.zzbsq.showNext();
        }
        if (zzaxf != null) {
            View nextView2 = this.zzbls.zzbsq.getNextView();
            if (nextView2 instanceof zzbgg) {
                ((zzbgg) nextView2).destroy();
            } else if (nextView2 != null) {
                this.zzbls.zzbsq.removeView(nextView2);
            }
            this.zzbls.zzmi();
        }
        this.zzbls.zzbsq.setVisibility(0);
        return true;
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzil() {
        zzbgg zzbgg = this.zzbls.zzbsu != null ? this.zzbls.zzbsu.zzdrv : null;
        if (!(this.zzbmz || zzbgg == null)) {
            zzc(zzbgg);
        }
        super.zzil();
    }

    private final void zzc(zzbgg zzbgg) {
        if (zzjj()) {
            WebView webView = zzbgg.getWebView();
            if (webView != null) {
                View view = zzbgg.getView();
                if (view != null && zzbv.zzlw().zzk(this.zzbls.zzsp)) {
                    int i = this.zzbls.zzbsp.zzeou;
                    int i2 = this.zzbls.zzbsp.zzeov;
                    StringBuilder stringBuilder = new StringBuilder(23);
                    stringBuilder.append(i);
                    stringBuilder.append(".");
                    stringBuilder.append(i2);
                    this.zzblx = zzbv.zzlw().zza(stringBuilder.toString(), webView, "", "javascript", zzit());
                    if (this.zzblx != null) {
                        zzbv.zzlw().zza(this.zzblx, view);
                        zzbgg.zzaa(this.zzblx);
                        zzbv.zzlw().zzo(this.zzblx);
                        this.zzbmz = true;
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean zziu() {
        boolean z;
        zzbv.zzlf();
        if (zzayh.zzn(this.zzbls.zzsp, "android.permission.INTERNET")) {
            z = true;
        } else {
            zzwu.zzpv().zza(this.zzbls.zzbsq, this.zzbls.zzbst, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            z = false;
        }
        zzbv.zzlf();
        if (!zzayh.zzah(this.zzbls.zzsp)) {
            zzwu.zzpv().zza(this.zzbls.zzbsq, this.zzbls.zzbst, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            z = false;
        }
        if (!(z || this.zzbls.zzbsq == null)) {
            this.zzbls.zzbsq.setVisibility(0);
        }
        return z;
    }

    public final void setManualImpressionsEnabled(boolean z) {
        Preconditions.checkMainThread("setManualImpressionsEnabled must be called from the main thread.");
        this.zzbli = z;
    }

    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by BannerAdManager.");
    }

    public final void onGlobalLayout() {
        zzd(this.zzbls.zzbsu);
    }

    public final void onScrollChanged() {
        zzd(this.zzbls.zzbsu);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zza(@Nullable zzaxf zzaxf, boolean z) {
        zzbgg zzbgg;
        if (zzjj()) {
            zzbgg = zzaxf != null ? zzaxf.zzdrv : null;
            if (zzbgg != null) {
                if (!this.zzbmz) {
                    zzc(zzbgg);
                }
                if (this.zzblx != null) {
                    zzbgg.zza("onSdkImpression", (Map) new ArrayMap());
                }
            }
        }
        super.zza(zzaxf, z);
        if (zzas.zzf(zzaxf)) {
            zzab zzab = new zzab(this);
            if (zzaxf != null && zzas.zzf(zzaxf)) {
                zzbgg = zzaxf.zzdrv;
                Object view = zzbgg != null ? zzbgg.getView() : null;
                if (view == null) {
                    zzbbd.zzeo("AdWebView is null");
                    return;
                }
                try {
                    List list = zzaxf.zzdnb != null ? zzaxf.zzdnb.zzdll : null;
                    if (list != null) {
                        if (!list.isEmpty()) {
                            zzals zzuu = zzaxf.zzdnc != null ? zzaxf.zzdnc.zzuu() : null;
                            zzalv zzuv = zzaxf.zzdnc != null ? zzaxf.zzdnc.zzuv() : null;
                            if (list.contains(InternalAvidAdSessionContext.AVID_API_LEVEL) && zzuu != null) {
                                zzuu.zzl(ObjectWrapper.wrap(view));
                                if (!zzuu.getOverrideImpressionRecording()) {
                                    zzuu.recordImpression();
                                }
                                zzbgg.zza("/nativeExpressViewClicked", zzas.zza(zzuu, null, zzab));
                                return;
                            } else if (!list.contains("1") || zzuv == null) {
                                zzbbd.zzeo("No matching template id and mapper");
                                return;
                            } else {
                                zzuv.zzl(ObjectWrapper.wrap(view));
                                if (!zzuv.getOverrideImpressionRecording()) {
                                    zzuv.recordImpression();
                                }
                                zzbgg.zza("/nativeExpressViewClicked", zzas.zza(null, zzuv, zzab));
                                return;
                            }
                        }
                    }
                    zzbbd.zzeo("No template ids present in mediation response");
                } catch (RemoteException e) {
                    zzbbd.zzc("Error occurred while recording impression and registering for clicks", e);
                }
            }
        }
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final void zzd(@Nullable zzaxf zzaxf) {
        if (zzaxf != null && !zzaxf.zzehi && this.zzbls.zzbsq != null && zzbv.zzlf().zza(this.zzbls.zzbsq, this.zzbls.zzsp) && this.zzbls.zzbsq.getGlobalVisibleRect(new Rect(), null)) {
            if (!(zzaxf == null || zzaxf.zzdrv == null || zzaxf.zzdrv.zzadl() == null)) {
                zzaxf.zzdrv.zzadl().zza(null);
            }
            zza(zzaxf, false);
            zzaxf.zzehi = true;
        }
    }

    @Nullable
    public final zzyp getVideoController() {
        Preconditions.checkMainThread("getVideoController must be called from the main thread.");
        return (this.zzbls.zzbsu == null || this.zzbls.zzbsu.zzdrv == null) ? null : this.zzbls.zzbsu.zzdrv.zzabu();
    }

    public final void zzjv() {
        this.zzblr.zzku();
    }
}
