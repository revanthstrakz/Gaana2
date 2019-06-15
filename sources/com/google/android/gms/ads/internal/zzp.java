package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaba;
import com.google.android.gms.internal.ads.zzabg;
import com.google.android.gms.internal.ads.zzabs;
import com.google.android.gms.internal.ads.zzabu;
import com.google.android.gms.internal.ads.zzabz;
import com.google.android.gms.internal.ads.zzacb;
import com.google.android.gms.internal.ads.zzacc;
import com.google.android.gms.internal.ads.zzacd;
import com.google.android.gms.internal.ads.zzace;
import com.google.android.gms.internal.ads.zzacf;
import com.google.android.gms.internal.ads.zzadb;
import com.google.android.gms.internal.ads.zzadx;
import com.google.android.gms.internal.ads.zzaeh;
import com.google.android.gms.internal.ads.zzakr;
import com.google.android.gms.internal.ads.zzalg;
import com.google.android.gms.internal.ads.zzals;
import com.google.android.gms.internal.ads.zzalv;
import com.google.android.gms.internal.ads.zzaly;
import com.google.android.gms.internal.ads.zzapl;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaso;
import com.google.android.gms.internal.ads.zzawd;
import com.google.android.gms.internal.ads.zzaxf;
import com.google.android.gms.internal.ads.zzaxg;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzbbi;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzum;
import com.google.android.gms.internal.ads.zzwb;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzyp;
import java.util.List;
import org.json.JSONObject;

@zzark
public final class zzp extends zzc implements zzace {
    private boolean zzbli;
    private zzaxf zzbmk;
    private boolean zzbml = false;

    public zzp(Context context, zzv zzv, zzwf zzwf, String str, zzalg zzalg, zzbbi zzbbi) {
        super(context, zzwf, str, zzalg, zzbbi, zzv);
    }

    @Nullable
    public final zzyp getVideoController() {
        return null;
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean zza(zzwb zzwb, zzaxf zzaxf, boolean z) {
        return false;
    }

    public final boolean zzb(zzwb zzwb) {
        zzc zzc = this;
        zzwb zzwb2 = zzwb;
        int i = (zzc.zzbls.zzbtn != null && zzc.zzbls.zzbtn.size() == 1 && ((Integer) zzc.zzbls.zzbtn.get(0)).intValue() == 2) ? 1 : 0;
        if (i != 0) {
            zzbbd.e("Requesting only banner Ad from AdLoader or calling loadAd on returned banner is not yet supported");
            zzc.zzbr(0);
            return false;
        } else if (zzc.zzbls.zzbtm == null) {
            return super.zzb(zzwb);
        } else {
            if (zzwb2.zzcjg != zzc.zzbli) {
                int i2 = zzwb2.versionCode;
                long j = zzwb2.zzcjb;
                Bundle bundle = zzwb2.extras;
                int i3 = zzwb2.zzcjc;
                List list = zzwb2.zzcjd;
                boolean z = zzwb2.zzcje;
                int i4 = zzwb2.zzcjf;
                boolean z2 = zzwb2.zzcjg || zzc.zzbli;
                zzwb2 = new zzwb(i2, j, bundle, i3, list, z, i4, z2, zzwb2.zzcjh, zzwb2.zzcji, zzwb2.zzcjj, zzwb2.zzcjk, zzwb2.zzcjl, zzwb2.zzcjm, zzwb2.zzcjn, zzwb2.zzcjo, zzwb2.zzcjp, zzwb2.zzcjq, null, zzwb2.zzcjs, zzwb2.zzcjt);
                zzc = this;
            }
            return super.zzb(zzwb2);
        }
    }

    public final void zza(zzaxg zzaxg, zzaba zzaba) {
        this.zzbmk = null;
        if (zzaxg.errorCode != -2) {
            this.zzbmk = zza(zzaxg, zzaxg.errorCode);
        } else if (!zzaxg.zzehy.zzdyd) {
            zzbbd.zzeo("partialAdState is not mediation");
            this.zzbmk = zza(zzaxg, 0);
        }
        if (this.zzbmk != null) {
            zzayh.zzelc.post(new zzq(this));
            return;
        }
        if (zzaxg.zzbst != null) {
            this.zzbls.zzbst = zzaxg.zzbst;
        }
        this.zzbls.zzbtw = 0;
        zzbw zzbw = this.zzbls;
        zzbv.zzle();
        zzbw.zzbss = zzapl.zza(this.zzbls.zzsp, this, zzaxg, this.zzbls.zzbso, null, this.zzbma, this, zzaba);
    }

    private static zzaxf zza(zzaxg zzaxg, int i) {
        zzaxg zzaxg2 = zzaxg;
        zzwb zzwb = zzaxg2.zzeag.zzdwg;
        List list = zzaxg2.zzehy.zzdlq;
        List list2 = zzaxg2.zzehy.zzdlr;
        List list3 = zzaxg2.zzehy.zzdyf;
        int i2 = zzaxg2.zzehy.orientation;
        long j = zzaxg2.zzehy.zzdlx;
        String str = zzaxg2.zzeag.zzdwj;
        boolean z = zzaxg2.zzehy.zzdyd;
        zzakr zzakr = zzaxg2.zzehj;
        long j2 = zzaxg2.zzehy.zzdye;
        zzwf zzwf = zzaxg2.zzbst;
        long j3 = j2;
        zzakr zzakr2 = zzakr;
        long j4 = zzaxg2.zzehy.zzdyc;
        long j5 = zzaxg2.zzehn;
        long j6 = zzaxg2.zzeho;
        String str2 = zzaxg2.zzehy.zzdyi;
        JSONObject jSONObject = zzaxg2.zzehh;
        zzawd zzawd = zzaxg2.zzehy.zzdyr;
        List list4 = zzaxg2.zzehy.zzdys;
        List list5 = zzaxg2.zzehy.zzdys;
        boolean z2 = zzaxg2.zzehy.zzdyu;
        zzaso zzaso = zzaxg2.zzehy.zzdyv;
        List list6 = zzaxg2.zzehy.zzdlu;
        String str3 = zzaxg2.zzehy.zzdyy;
        long j7 = j6;
        zzum zzum = zzaxg2.zzehw;
        boolean z3 = zzaxg2.zzehy.zzbph;
        zzum zzum2 = zzum;
        boolean z4 = zzaxg2.zzehx;
        String str4 = str3;
        boolean z5 = zzaxg2.zzehy.zzdzc;
        List list7 = zzaxg2.zzehy.zzdls;
        boolean z6 = zzaxg2.zzehy.zzbpi;
        JSONObject jSONObject2 = jSONObject;
        zzwf zzwf2 = zzwf;
        String str5 = str2;
        long j8 = j4;
        boolean z7 = z4;
        boolean z8 = z3;
        return new zzaxf(zzwb, null, list, i, list2, list3, i2, j, str, z, null, null, null, zzakr2, null, j3, zzwf2, j8, j5, j7, str5, jSONObject2, null, zzawd, list4, list5, z2, zzaso, null, list6, str4, zzum2, z8, z7, z5, list7, z6, zzaxg2.zzehy.zzdzd, zzaxg2.zzehy.zzdzf);
    }

    /* Access modifiers changed, original: protected|final */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x006e A:{RETURN} */
    public final boolean zza(@android.support.annotation.Nullable com.google.android.gms.internal.ads.zzaxf r5, com.google.android.gms.internal.ads.zzaxf r6) {
        /*
        r4 = this;
        r0 = r4.zzbls;
        r0 = r0.zzmj();
        if (r0 != 0) goto L_0x0010;
    L_0x0008:
        r5 = new java.lang.IllegalStateException;
        r6 = "AdLoader API does not support custom rendering.";
        r5.<init>(r6);
        throw r5;
    L_0x0010:
        r0 = r6.zzdyd;
        r1 = 0;
        if (r0 != 0) goto L_0x001e;
    L_0x0015:
        r4.zzbr(r1);
        r5 = "newState is not mediation.";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r5);
        return r1;
    L_0x001e:
        r0 = r6.zzdnb;
        r2 = 1;
        if (r0 == 0) goto L_0x0072;
    L_0x0023:
        r0 = r6.zzdnb;
        r0 = r0.zzuj();
        if (r0 == 0) goto L_0x0072;
    L_0x002b:
        r0 = r4.zzbls;
        r0 = r0.zzmj();
        if (r0 == 0) goto L_0x0046;
    L_0x0033:
        r0 = r4.zzbls;
        r0 = r0.zzbsq;
        if (r0 == 0) goto L_0x0046;
    L_0x0039:
        r0 = r4.zzbls;
        r0 = r0.zzbsq;
        r0 = r0.zzmm();
        r3 = r6.zzdyi;
        r0.zzef(r3);
    L_0x0046:
        r0 = super.zza(r5, r6);
        if (r0 != 0) goto L_0x004e;
    L_0x004c:
        r5 = r1;
        goto L_0x006c;
    L_0x004e:
        r0 = r4.zzbls;
        r0 = r0.zzmj();
        if (r0 == 0) goto L_0x0060;
    L_0x0056:
        r5 = r4.zzc(r5, r6);
        if (r5 != 0) goto L_0x0060;
    L_0x005c:
        r4.zzbr(r1);
        goto L_0x004c;
    L_0x0060:
        r5 = r4.zzbls;
        r5 = r5.zzmk();
        if (r5 != 0) goto L_0x006b;
    L_0x0068:
        super.zza(r6, r1);
    L_0x006b:
        r5 = r2;
    L_0x006c:
        if (r5 != 0) goto L_0x006f;
    L_0x006e:
        return r1;
    L_0x006f:
        r4.zzbml = r2;
        goto L_0x0085;
    L_0x0072:
        r0 = r6.zzdnb;
        if (r0 == 0) goto L_0x009b;
    L_0x0076:
        r0 = r6.zzdnb;
        r0 = r0.zzuk();
        if (r0 == 0) goto L_0x009b;
    L_0x007e:
        r5 = r4.zzb(r5, r6);
        if (r5 != 0) goto L_0x0085;
    L_0x0084:
        return r1;
    L_0x0085:
        r5 = new java.util.ArrayList;
        r6 = new java.lang.Integer[r2];
        r0 = 2;
        r0 = java.lang.Integer.valueOf(r0);
        r6[r1] = r0;
        r6 = java.util.Arrays.asList(r6);
        r5.<init>(r6);
        r4.zze(r5);
        return r2;
    L_0x009b:
        r4.zzbr(r1);
        r5 = "Response is neither banner nor native.";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r5);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzp.zza(com.google.android.gms.internal.ads.zzaxf, com.google.android.gms.internal.ads.zzaxf):boolean");
    }

    private final boolean zzb(zzaxf zzaxf, zzaxf zzaxf2) {
        zzaxf zzaxf3 = zzaxf2;
        List list = null;
        zzd(null);
        if (this.zzbls.zzmj()) {
            try {
                zzaly zzva = zzaxf3.zzdnc != null ? zzaxf3.zzdnc.zzva() : null;
                zzals zzuu = zzaxf3.zzdnc != null ? zzaxf3.zzdnc.zzuu() : null;
                zzalv zzuv = zzaxf3.zzdnc != null ? zzaxf3.zzdnc.zzuv() : null;
                zzadx zzuz = zzaxf3.zzdnc != null ? zzaxf3.zzdnc.zzuz() : null;
                String zzc = zzc.zzc(zzaxf2);
                String headline;
                List images;
                String body;
                zzadb zzsb;
                String callToAction;
                String advertiser;
                double starRating;
                String store;
                String price;
                zzyp videoController;
                zzabz zzabz;
                zzacf zzabz2;
                zzalv zzalv;
                if (zzva != null && this.zzbls.zzbte != null) {
                    headline = zzva.getHeadline();
                    images = zzva.getImages();
                    body = zzva.getBody();
                    zzsb = zzva.zzsb() != null ? zzva.zzsb() : null;
                    callToAction = zzva.getCallToAction();
                    advertiser = zzva.getAdvertiser();
                    starRating = zzva.getStarRating();
                    store = zzva.getStore();
                    price = zzva.getPrice();
                    videoController = zzva.getVideoController();
                    if (zzva.zzvc() != null) {
                        list = (View) ObjectWrapper.unwrap(zzva.zzvc());
                    }
                    zzabz = new zzabz(headline, images, body, zzsb, callToAction, advertiser, starRating, store, price, null, videoController, list, zzva.zzsd(), zzc, zzva.getExtras());
                    zzabz.zzb(new zzacc(this.zzbls.zzsp, (zzace) this, this.zzbls.zzbso, zzva, (zzacf) zzabz));
                    zza(zzabz);
                } else if (zzuu != null && this.zzbls.zzbte != null) {
                    headline = zzuu.getHeadline();
                    images = zzuu.getImages();
                    body = zzuu.getBody();
                    zzsb = zzuu.zzsb() != null ? zzuu.zzsb() : null;
                    callToAction = zzuu.getCallToAction();
                    starRating = zzuu.getStarRating();
                    store = zzuu.getStore();
                    price = zzuu.getPrice();
                    videoController = zzuu.getVideoController();
                    if (zzuu.zzvc() != null) {
                        list = (View) ObjectWrapper.unwrap(zzuu.zzvc());
                    }
                    zzabz = new zzabz(headline, images, body, zzsb, callToAction, null, starRating, store, price, null, videoController, list, zzuu.zzsd(), zzc, zzuu.getExtras());
                    zzabz.zzb(new zzacc(this.zzbls.zzsp, (zzace) this, this.zzbls.zzbso, zzuu, (zzacf) zzabz));
                    zza(zzabz);
                } else if (zzuu != null && this.zzbls.zzbtc != null) {
                    headline = zzuu.getHeadline();
                    images = zzuu.getImages();
                    body = zzuu.getBody();
                    zzsb = zzuu.zzsb() != null ? zzuu.zzsb() : null;
                    callToAction = zzuu.getCallToAction();
                    double starRating2 = zzuu.getStarRating();
                    String store2 = zzuu.getStore();
                    store = zzuu.getPrice();
                    Bundle extras = zzuu.getExtras();
                    videoController = zzuu.getVideoController();
                    if (zzuu.zzvc() != null) {
                        list = (View) ObjectWrapper.unwrap(zzuu.zzvc());
                    }
                    zzabs zzabs = new zzabs(headline, images, body, zzsb, callToAction, starRating2, store2, store, null, extras, videoController, list, zzuu.zzsd(), zzc);
                    zzabs.zzb(new zzacc(this.zzbls.zzsp, (zzace) this, this.zzbls.zzbso, zzuu, (zzacf) zzabs));
                    zzayh.zzelc.post(new zzs(this, zzabs));
                } else if (zzuv != null && this.zzbls.zzbte != null) {
                    headline = zzuv.getHeadline();
                    images = zzuv.getImages();
                    body = zzuv.getBody();
                    zzsb = zzuv.zzsf() != null ? zzuv.zzsf() : null;
                    callToAction = zzuv.getCallToAction();
                    advertiser = zzuv.getAdvertiser();
                    videoController = zzuv.getVideoController();
                    if (zzuv.zzvc() != null) {
                        list = (View) ObjectWrapper.unwrap(zzuv.zzvc());
                    }
                    zzabz2 = new zzabz(headline, images, body, zzsb, callToAction, advertiser, -1.0d, null, null, null, videoController, list, zzuv.zzsd(), zzc, zzuv.getExtras());
                    zzalv = zzuv;
                    zzabz zzabz3 = zzabz2;
                    zzabz3.zzb(new zzacc(this.zzbls.zzsp, (zzace) this, this.zzbls.zzbso, zzalv, zzabz2));
                    zza(zzabz3);
                } else if (zzuv != null && this.zzbls.zzbtd != null) {
                    headline = zzuv.getHeadline();
                    images = zzuv.getImages();
                    body = zzuv.getBody();
                    zzsb = zzuv.zzsf() != null ? zzuv.zzsf() : null;
                    callToAction = zzuv.getCallToAction();
                    advertiser = zzuv.getAdvertiser();
                    Bundle extras2 = zzuv.getExtras();
                    zzyp videoController2 = zzuv.getVideoController();
                    if (zzuv.zzvc() != null) {
                        list = (View) ObjectWrapper.unwrap(zzuv.zzvc());
                    }
                    zzabz2 = new zzabu(headline, images, body, zzsb, callToAction, advertiser, null, extras2, videoController2, list, zzuv.zzsd(), zzc);
                    zzalv = zzuv;
                    zzacf zzacf = zzabz2;
                    zzacf.zzb(new zzacc(this.zzbls.zzsp, (zzace) this, this.zzbls.zzbso, zzalv, zzabz2));
                    zzayh.zzelc.post(new zzt(this, zzacf));
                } else if (zzuz == null || this.zzbls.zzbth == null || this.zzbls.zzbth.get(zzuz.getCustomTemplateId()) == null) {
                    zzbbd.zzeo("No matching mapper/listener for retrieved native ad template.");
                    zzbr(0);
                    return false;
                } else {
                    zzayh.zzelc.post(new zzu(this, zzuz));
                }
                return super.zza(zzaxf, zzaxf2);
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
                zzbr(0);
                return false;
            }
        }
        zzbbd.zzeo("Native ad does not have custom rendering mode.");
        zzbr(0);
        return false;
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzb(@Nullable IObjectWrapper iObjectWrapper) {
        Object unwrap = iObjectWrapper != null ? ObjectWrapper.unwrap(iObjectWrapper) : null;
        if (unwrap instanceof zzacd) {
            ((zzacd) unwrap).zzsm();
        }
        super.zzb(this.zzbls.zzbsu, false);
    }

    private final void zza(zzabz zzabz) {
        zzayh.zzelc.post(new zzr(this, zzabz));
    }

    public final void zzi(View view) {
        zzbbd.zzd("#005 Unexpected call to an abstract (unimplemented) method.", null);
    }

    public final void zzjl() {
        zzbbd.zzd("#005 Unexpected call to an abstract (unimplemented) method.", null);
    }

    public final void zzjm() {
        zzbbd.zzd("#005 Unexpected call to an abstract (unimplemented) method.", null);
    }

    public final void zza(zzacd zzacd) {
        zzbbd.zzd("#005 Unexpected call to an abstract (unimplemented) method.", null);
    }

    public final void zzjn() {
        zzbbd.zzd("#005 Unexpected call to an abstract (unimplemented) method.", null);
    }

    public final void zza(zzacb zzacb) {
        zzbbd.zzd("#005 Unexpected call to an abstract (unimplemented) method.", null);
    }

    public final boolean zzjo() {
        return zzjr() != null ? zzjr().zzdmd : false;
    }

    public final boolean zzjp() {
        return zzjr() != null ? zzjr().zzdme : false;
    }

    public final boolean zzjq() {
        return zzjr() != null ? zzjr().zzdmf : false;
    }

    @Nullable
    private final zzakr zzjr() {
        return (this.zzbls.zzbsu == null || !this.zzbls.zzbsu.zzdyd) ? null : this.zzbls.zzbsu.zzehj;
    }

    private final boolean zzc(zzaxf zzaxf, zzaxf zzaxf2) {
        View zze = zzas.zze(zzaxf2);
        if (zze == null) {
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
                zzg(zze);
            } catch (Throwable th) {
                zzbv.zzlj().zza(th, "AdLoaderManager.swapBannerViews");
                zzbbd.zzc("Could not add mediation view to view hierarchy.", th);
                return false;
            }
        }
        if (this.zzbls.zzbsq.getChildCount() > 1) {
            this.zzbls.zzbsq.showNext();
        }
        if (zzaxf != null) {
            View nextView2 = this.zzbls.zzbsq.getNextView();
            if (nextView2 != null) {
                this.zzbls.zzbsq.removeView(nextView2);
            }
            this.zzbls.zzmi();
        }
        this.zzbls.zzbsq.setMinimumWidth(zzif().widthPixels);
        this.zzbls.zzbsq.setMinimumHeight(zzif().heightPixels);
        this.zzbls.zzbsq.requestLayout();
        this.zzbls.zzbsq.setVisibility(0);
        return true;
    }

    public final void pause() {
        if (this.zzbml) {
            super.pause();
            return;
        }
        throw new IllegalStateException("Native Ad does not support pause().");
    }

    public final void resume() {
        if (this.zzbml) {
            super.resume();
            return;
        }
        throw new IllegalStateException("Native Ad does not support resume().");
    }

    public final void zzd(@Nullable List<String> list) {
        Preconditions.checkMainThread("setNativeTemplates must be called on the main UI thread.");
        this.zzbls.zzbtt = list;
    }

    @Nullable
    public final zzaeh zzar(String str) {
        Preconditions.checkMainThread("getOnCustomClickListener must be called on the main UI thread.");
        return (zzaeh) this.zzbls.zzbtg.get(str);
    }

    public final void setManualImpressionsEnabled(boolean z) {
        Preconditions.checkMainThread("setManualImpressionsEnabled must be called from the main thread.");
        this.zzbli = z;
    }

    public final void zza(zzabg zzabg) {
        throw new IllegalStateException("CustomRendering is not supported by AdLoaderManager.");
    }

    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is not supported by AdLoaderManager.");
    }

    public final void zze(List<Integer> list) {
        Preconditions.checkMainThread("setAllowedAdTypes must be called on the main UI thread.");
        this.zzbls.zzbtn = list;
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzil() {
        super.zzil();
        zzaxf zzaxf = this.zzbls.zzbsu;
        if (!(zzaxf == null || zzaxf.zzdnb == null || !zzaxf.zzdnb.zzuj() || this.zzbls.zzbtm == null)) {
            try {
                this.zzbls.zzbtm.zza(this, ObjectWrapper.wrap(this.zzbls.zzsp));
                super.zzb(this.zzbls.zzbsu, false);
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zziy() {
        if (this.zzbls.zzbsu == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbls.zzbsu.zzdnd) || this.zzbls.zzbsu.zzdnb == null || !this.zzbls.zzbsu.zzdnb.zzuk()) {
            super.zziy();
        } else {
            zzin();
        }
    }

    public final void zzjd() {
        if (this.zzbls.zzbsu == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbls.zzbsu.zzdnd) || this.zzbls.zzbsu.zzdnb == null || !this.zzbls.zzbsu.zzdnb.zzuk()) {
            super.zzjd();
        } else {
            zzim();
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzjs() {
        zzb(this.zzbmk);
    }
}
