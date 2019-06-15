package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.view.View;
import com.google.ads.AdRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzaba;
import com.google.android.gms.internal.ads.zzabg;
import com.google.android.gms.internal.ads.zzabq;
import com.google.android.gms.internal.ads.zzabs;
import com.google.android.gms.internal.ads.zzabu;
import com.google.android.gms.internal.ads.zzabw;
import com.google.android.gms.internal.ads.zzabz;
import com.google.android.gms.internal.ads.zzacb;
import com.google.android.gms.internal.ads.zzacc;
import com.google.android.gms.internal.ads.zzacd;
import com.google.android.gms.internal.ads.zzace;
import com.google.android.gms.internal.ads.zzacf;
import com.google.android.gms.internal.ads.zzadb;
import com.google.android.gms.internal.ads.zzadx;
import com.google.android.gms.internal.ads.zzaeh;
import com.google.android.gms.internal.ads.zzaek;
import com.google.android.gms.internal.ads.zzakr;
import com.google.android.gms.internal.ads.zzalg;
import com.google.android.gms.internal.ads.zzalj;
import com.google.android.gms.internal.ads.zzals;
import com.google.android.gms.internal.ads.zzalv;
import com.google.android.gms.internal.ads.zzaly;
import com.google.android.gms.internal.ads.zzaow;
import com.google.android.gms.internal.ads.zzapl;
import com.google.android.gms.internal.ads.zzaqp;
import com.google.android.gms.internal.ads.zzaqt;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaxf;
import com.google.android.gms.internal.ads.zzaxg;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzayf;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzbbi;
import com.google.android.gms.internal.ads.zzbcb;
import com.google.android.gms.internal.ads.zzbcl;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzbgq;
import com.google.android.gms.internal.ads.zzri;
import com.google.android.gms.internal.ads.zzwb;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzwu;
import com.google.android.gms.internal.ads.zzyp;
import com.google.android.gms.internal.ads.zzys;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzbb extends zzc implements zzace {
    private final Object mLock;
    private boolean zzbme;
    @VisibleForTesting
    private boolean zzbpv;
    private zzbcl<zzacf> zzbpw;
    private zzbgg zzbpx;
    @Nullable
    private zzbgg zzbpy;
    private int zzbpz;
    private zzaqp zzbqa;
    private final String zzbqb;

    public zzbb(Context context, zzv zzv, zzwf zzwf, String str, zzalg zzalg, zzbbi zzbbi) {
        this(context, zzv, zzwf, str, zzalg, zzbbi, false);
    }

    public zzbb(Context context, zzv zzv, zzwf zzwf, String str, zzalg zzalg, zzbbi zzbbi, boolean z) {
        super(context, zzwf, str, zzalg, zzbbi, zzv);
        this.mLock = new Object();
        this.zzbpw = new zzbcl();
        this.zzbpz = 1;
        this.zzbqb = UUID.randomUUID().toString();
        this.zzbpv = z;
    }

    public final String getUuid() {
        return this.zzbqb;
    }

    public final boolean zza(zzwb zzwb, zzaba zzaba) {
        try {
            zzkm();
            return super.zza(zzwb, zzaba, this.zzbpz);
        } catch (Exception e) {
            String str = "Error initializing webview.";
            if (zzbbd.isLoggable(4)) {
                Log.i(AdRequest.LOGTAG, str, e);
            }
            return false;
        }
    }

    /* Access modifiers changed, original: final */
    public final void zzkm() throws zzbgq {
        synchronized (this.mLock) {
            zzaxz.v("Initializing webview native ads utills");
            this.zzbqa = new zzaqt(this.zzbls.zzsp, this, this.zzbqb, this.zzbls.zzbso, this.zzbls.zzbsp);
        }
    }

    @Nullable
    public final zzaqp zzkn() {
        zzaqp zzaqp;
        synchronized (this.mLock) {
            zzaqp = this.zzbqa;
        }
        return zzaqp;
    }

    public final void zza(zzaxg zzaxg, zzaba zzaba) {
        if (zzaxg.zzbst != null) {
            this.zzbls.zzbst = zzaxg.zzbst;
        }
        if (zzaxg.errorCode != -2) {
            zzayh.zzelc.post(new zzbc(this, zzaxg));
            return;
        }
        int i = zzaxg.zzeag.zzdxq;
        int i2 = 0;
        if (i == 1) {
            this.zzbls.zzbtw = 0;
            zzbw zzbw = this.zzbls;
            zzbv.zzle();
            zzbw.zzbss = zzapl.zza(this.zzbls.zzsp, this, zzaxg, this.zzbls.zzbso, null, this.zzbma, this, zzaba);
            String str = "AdRenderer: ";
            String valueOf = String.valueOf(this.zzbls.zzbss.getClass().getName());
            zzbbd.zzdn(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            return;
        }
        JSONArray jSONArray = new JSONArray();
        try {
            JSONArray jSONArray2 = new JSONObject(zzaxg.zzehy.zzdyb).getJSONArray("slots");
            for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                JSONArray optJSONArray = jSONArray2.getJSONObject(i3).optJSONArray("ads");
                int i4 = 0;
                while (optJSONArray != null && i4 < optJSONArray.length()) {
                    jSONArray.put(optJSONArray.get(i4));
                    i4++;
                }
            }
            zzkt();
            ArrayList arrayList = new ArrayList();
            for (int i5 = 0; i5 < i; i5++) {
                arrayList.add(zzayf.zza(new zzbe(this, i5, jSONArray, i, zzaxg)));
            }
            while (i2 < arrayList.size()) {
                try {
                    zzayh.zzelc.post(new zzbf(this, (zzacf) ((zzbcb) arrayList.get(i2)).get(((Long) zzwu.zzpz().zzd(zzaan.zzctf)).longValue(), TimeUnit.MILLISECONDS), i2, arrayList));
                } catch (InterruptedException e) {
                    zzbbd.zzc("", e);
                    Thread.currentThread().interrupt();
                } catch (CancellationException | ExecutionException | TimeoutException e2) {
                    zzbbd.zzc("", e2);
                }
                i2++;
            }
        } catch (JSONException e3) {
            zzbbd.zzc("Malformed native ad response", e3);
            zzbr(0);
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzbr(int i) {
        zzg(i, false);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzg(int i, boolean z) {
        zzkt();
        super.zzg(i, z);
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean zza(zzwb zzwb, zzaxf zzaxf, boolean z) {
        return this.zzblr.zzkv();
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean zza(zzaxf zzaxf, zzaxf zzaxf2) {
        zzaxf zzaxf3 = zzaxf2;
        List list = null;
        zzd(null);
        if (this.zzbls.zzmj()) {
            if (zzaxf3.zzdyd) {
                zzkt();
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
                        zza(zzabs);
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
                        zzabu zzabu = zzabz2;
                        zzabu.zzb(new zzacc(this.zzbls.zzsp, (zzace) this, this.zzbls.zzbso, zzalv, zzabz2));
                        zza(zzabu);
                    } else if (zzuz == null || this.zzbls.zzbth == null || this.zzbls.zzbth.get(zzuz.getCustomTemplateId()) == null) {
                        zzbbd.zzeo("No matching mapper/listener for retrieved native ad template.");
                        zzbr(0);
                        return false;
                    } else {
                        zzayh.zzelc.post(new zzbk(this, zzuz));
                    }
                } catch (RemoteException e) {
                    zzbbd.zzd("#007 Could not call remote method.", e);
                }
            } else {
                zzacf zzacf = zzaxf3.zzehp;
                if (this.zzbpv) {
                    zzd("Google", zzaxf3.zzdzc);
                    this.zzbpw.set(zzacf);
                } else {
                    boolean z = zzacf instanceof zzabu;
                    if (z && this.zzbls.zzbte != null) {
                        zzd("Google", zzaxf3.zzdzc);
                        zza(zza(zzaxf3.zzehp));
                    } else if (!z || this.zzbls.zzbtd == null) {
                        z = zzacf instanceof zzabs;
                        if (z && this.zzbls.zzbte != null) {
                            zzd("Google", zzaxf3.zzdzc);
                            zza(zza(zzaxf3.zzehp));
                        } else if (!z || this.zzbls.zzbtc == null) {
                            if ((zzacf instanceof zzabw) && this.zzbls.zzbth != null) {
                                zzabw zzabw = (zzabw) zzacf;
                                if (this.zzbls.zzbth.get(zzabw.getCustomTemplateId()) != null) {
                                    zzayh.zzelc.post(new zzbj(this, zzabw.getCustomTemplateId(), zzaxf3));
                                }
                            }
                            if (!(zzacf instanceof zzabq) || this.zzbls.zzbtf == null) {
                                zzbbd.zzeo("No matching listener for retrieved native ad template.");
                                zzbr(0);
                                return false;
                            }
                            zzayh.zzelc.post(new zzbd(this, (zzabq) zzacf));
                        } else {
                            zzd("Google", zzaxf3.zzdzc);
                            zza((zzabs) zzaxf3.zzehp);
                        }
                    } else {
                        zzd("Google", zzaxf3.zzdzc);
                        zza((zzabu) zzaxf3.zzehp);
                    }
                }
            }
            return super.zza(zzaxf, zzaxf2);
        }
        throw new IllegalStateException("Native ad DOES NOT have custom rendering mode.");
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzb(@Nullable IObjectWrapper iObjectWrapper) {
        Object unwrap = iObjectWrapper != null ? ObjectWrapper.unwrap(iObjectWrapper) : null;
        if (unwrap instanceof zzacd) {
            ((zzacd) unwrap).zzsm();
        }
        super.zzb(this.zzbls.zzbsu, false);
    }

    /* Access modifiers changed, original: protected|final */
    public final Future<zzacf> zzko() {
        return this.zzbpw;
    }

    public final void pause() {
        throw new IllegalStateException("Native Ad DOES NOT support pause().");
    }

    public final void resume() {
        throw new IllegalStateException("Native Ad DOES NOT support resume().");
    }

    private final void zza(zzabs zzabs) {
        zzayh.zzelc.post(new zzbg(this, zzabs));
    }

    private final void zza(zzabz zzabz) {
        zzayh.zzelc.post(new zzbh(this, zzabz));
    }

    private final void zza(zzabu zzabu) {
        zzayh.zzelc.post(new zzbi(this, zzabu));
    }

    private final void zzd(String str, boolean z) {
        if (!z) {
            return;
        }
        if (this.zzbpy != null || this.zzbpx != null) {
            String str2;
            Object obj = null;
            Object obj2 = this.zzbpy != null ? 1 : null;
            if (this.zzbpx != null) {
                obj = 1;
            }
            zzbgg zzbgg = null;
            if (obj2 != null) {
                str2 = null;
                zzbgg = this.zzbpy;
            } else if (obj != null) {
                zzbgg = this.zzbpx;
                str2 = "javascript";
            } else {
                str2 = null;
            }
            if (zzbgg.getWebView() != null && zzbv.zzlw().zzk(this.zzbls.zzsp)) {
                int i = this.zzbls.zzbsp.zzeou;
                int i2 = this.zzbls.zzbsp.zzeov;
                StringBuilder stringBuilder = new StringBuilder(23);
                stringBuilder.append(i);
                stringBuilder.append(".");
                stringBuilder.append(i2);
                this.zzblx = zzbv.zzlw().zza(stringBuilder.toString(), zzbgg.getWebView(), "", "javascript", str2, str);
                if (this.zzblx != null) {
                    zzbgg.zzaa(this.zzblx);
                    if (obj != null) {
                        IObjectWrapper iObjectWrapper = this.zzblx;
                        View view = this.zzbpx.getView();
                        if (view != null) {
                            zzbv.zzlw().zza(iObjectWrapper, view);
                        }
                    }
                    zzbv.zzlw().zzo(this.zzblx);
                }
            }
        }
    }

    private final boolean zzjj() {
        return this.zzbls.zzbsu != null && this.zzbls.zzbsu.zzdzc;
    }

    public final void zzi(View view) {
        Object obj = this.zzbpy != null ? 1 : null;
        if (zzjj() && this.zzblx != null && obj != null && view != null) {
            zzbv.zzlw().zza(this.zzblx, view);
        }
    }

    public final void zzjl() {
        if (zzjj() && this.zzblx != null) {
            zzbgg zzbgg = null;
            if (this.zzbpy != null) {
                zzbgg = this.zzbpy;
            } else if (this.zzbpx != null) {
                zzbgg = this.zzbpx;
            }
            if (zzbgg != null) {
                zzbgg.zza("onSdkImpression", (Map) new HashMap());
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzil() {
        zzm(false);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzm(boolean z) {
        super.zzm(z);
        if (this.zzbme) {
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcuk)).booleanValue()) {
                zzkp();
            }
        }
    }

    public final void zza(zzacd zzacd) {
        if (this.zzbls.zzbsu.zzehh != null && this.zzbls.zzbtk == null) {
            zzbv.zzlj().zzym().zza(this.zzbls.zzbst, this.zzbls.zzbsu, new zzri(zzacd), null);
        }
    }

    public final void zzkp() {
        if (this.zzbls.zzbsu == null || this.zzbpx == null) {
            this.zzbme = true;
            zzbbd.zzeo("Request to enable ActiveView before adState is available.");
            return;
        }
        zzbv.zzlj().zzym().zza(this.zzbls.zzbst, this.zzbls.zzbsu, this.zzbpx.getView(), this.zzbpx);
        this.zzbme = false;
    }

    public final void zzkq() {
        this.zzbme = false;
        if (this.zzbls.zzbsu == null || this.zzbpx == null) {
            zzbbd.zzeo("Request to enable ActiveView before adState is available.");
        } else {
            zzbv.zzlj().zzym().zzh(this.zzbls.zzbsu);
        }
    }

    public final String getAdUnitId() {
        return this.zzbls.zzbsn;
    }

    public final void zzd(@Nullable List<String> list) {
        Preconditions.checkMainThread("setNativeTemplates must be called on the main UI thread.");
        this.zzbls.zzbtt = list;
    }

    @Nullable
    public final zzaeh zzar(String str) {
        Preconditions.checkMainThread("getOnCustomClickListener must be called on the main UI thread.");
        if (this.zzbls.zzbtg == null) {
            return null;
        }
        return (zzaeh) this.zzbls.zzbtg.get(str);
    }

    public final SimpleArrayMap<String, zzaek> zzkr() {
        Preconditions.checkMainThread("getOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        return this.zzbls.zzbth;
    }

    public final void zzbs(int i) {
        Preconditions.checkMainThread("setMaxNumberOfAds must be called on the main UI thread.");
        this.zzbpz = i;
    }

    public final void zzf(zzbgg zzbgg) {
        this.zzbpx = zzbgg;
    }

    public final void zzg(@Nullable zzbgg zzbgg) {
        this.zzbpy = zzbgg;
    }

    public final void zzjn() {
        if (this.zzbpx != null) {
            this.zzbpx.destroy();
            this.zzbpx = null;
        }
    }

    public final void zzjm() {
        this.zzblx = null;
        if (this.zzbpy != null) {
            this.zzbpy.destroy();
            this.zzbpy = null;
        }
    }

    public final void zza(zzacb zzacb) {
        if (this.zzbpx != null) {
            this.zzbpx.zzb(zzacb);
        }
    }

    public final void zzks() {
        if (this.zzbpx == null || this.zzbpx.zzabu() == null || this.zzbls.zzbti == null || this.zzbls.zzbti.zzdcw == null) {
            if (!(this.zzbls.zzbtf == null || this.zzbpx == null || this.zzbpx.zzabu() == null)) {
                this.zzbpx.zzabu().zza(false, true, false);
            }
            return;
        }
        this.zzbpx.zzabu().zzb(this.zzbls.zzbti.zzdcw);
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

    public final void zza(zzaow zzaow) {
        throw new IllegalStateException("In App Purchase is NOT supported by NativeAdManager.");
    }

    public final void zza(zzabg zzabg) {
        throw new IllegalStateException("CustomRendering is NOT supported by NativeAdManager.");
    }

    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by NativeAdManager.");
    }

    public final void zziy() {
        if (this.zzbls.zzbsu == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbls.zzbsu.zzdnd)) {
            super.zziy();
        } else {
            zzin();
        }
    }

    public final void zzjd() {
        if (this.zzbls.zzbsu == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbls.zzbsu.zzdnd)) {
            super.zzjd();
        } else {
            zzim();
        }
    }

    public final void zzix() {
        zzaxf zzaxf = this.zzbls.zzbsu;
        if (zzaxf.zzdnc == null) {
            super.zzix();
            return;
        }
        try {
            zzalj zzalj = zzaxf.zzdnc;
            zzyp zzyp = null;
            zzals zzuu = zzalj.zzuu();
            if (zzuu != null) {
                zzyp = zzuu.getVideoController();
            } else {
                zzalv zzuv = zzalj.zzuv();
                if (zzuv != null) {
                    zzyp = zzuv.getVideoController();
                } else {
                    zzadx zzuz = zzalj.zzuz();
                    if (zzuz != null) {
                        zzyp = zzuz.getVideoController();
                    }
                }
            }
            if (zzyp != null) {
                zzys zzqh = zzyp.zzqh();
                if (zzqh != null) {
                    zzqh.onVideoEnd();
                }
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x00ac  */
    private static com.google.android.gms.internal.ads.zzabz zza(com.google.android.gms.internal.ads.zzacf r20) {
        /*
        r0 = r20;
        r1 = r0 instanceof com.google.android.gms.internal.ads.zzabu;
        r2 = 0;
        if (r1 == 0) goto L_0x0053;
    L_0x0007:
        r0 = (com.google.android.gms.internal.ads.zzabu) r0;
        r1 = new com.google.android.gms.internal.ads.zzabz;
        r4 = r0.getHeadline();
        r5 = r0.getImages();
        r6 = r0.getBody();
        r7 = r0.zzsf();
        r8 = r0.getCallToAction();
        r9 = r0.getAdvertiser();
        r10 = -4616189618054758400; // 0xbff0000000000000 float:0.0 double:-1.0;
        r12 = 0;
        r13 = 0;
        r14 = r0.zzrw();
        r15 = r0.getVideoController();
        r16 = r0.zzrx();
        r17 = r0.zzsd();
        r18 = r0.getMediationAdapterClassName();
        r19 = r0.getExtras();
        r3 = r1;
        r3.<init>(r4, r5, r6, r7, r8, r9, r10, r12, r13, r14, r15, r16, r17, r18, r19);
        r3 = r0.zzsc();
        if (r3 == 0) goto L_0x00a8;
    L_0x0049:
        r0 = r0.zzsc();
        r0 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r0);
    L_0x0051:
        r2 = r0;
        goto L_0x00a8;
    L_0x0053:
        r1 = r0 instanceof com.google.android.gms.internal.ads.zzabs;
        if (r1 == 0) goto L_0x00a7;
    L_0x0057:
        r0 = (com.google.android.gms.internal.ads.zzabs) r0;
        r1 = new com.google.android.gms.internal.ads.zzabz;
        r4 = r0.getHeadline();
        r5 = r0.getImages();
        r6 = r0.getBody();
        r7 = r0.zzsb();
        r8 = r0.getCallToAction();
        r9 = 0;
        r10 = r0.getStarRating();
        r12 = r0.getStore();
        r13 = r0.getPrice();
        r14 = r0.zzrw();
        r15 = r0.getVideoController();
        r16 = r0.zzrx();
        r17 = r0.zzsd();
        r18 = r0.getMediationAdapterClassName();
        r19 = r0.getExtras();
        r3 = r1;
        r3.<init>(r4, r5, r6, r7, r8, r9, r10, r12, r13, r14, r15, r16, r17, r18, r19);
        r3 = r0.zzsc();
        if (r3 == 0) goto L_0x00a8;
    L_0x009e:
        r0 = r0.zzsc();
        r0 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r0);
        goto L_0x0051;
    L_0x00a7:
        r1 = r2;
    L_0x00a8:
        r0 = r2 instanceof com.google.android.gms.internal.ads.zzach;
        if (r0 == 0) goto L_0x00b1;
    L_0x00ac:
        r2 = (com.google.android.gms.internal.ads.zzach) r2;
        r1.zzb(r2);
    L_0x00b1:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzbb.zza(com.google.android.gms.internal.ads.zzacf):com.google.android.gms.internal.ads.zzabz");
    }

    private static void zza(zzbw zzbw, zzbw zzbw2) {
        if (zzbw2.zzbtc == null) {
            zzbw2.zzbtc = zzbw.zzbtc;
        }
        if (zzbw2.zzbtd == null) {
            zzbw2.zzbtd = zzbw.zzbtd;
        }
        if (zzbw2.zzbtg == null) {
            zzbw2.zzbtg = zzbw.zzbtg;
        }
        if (zzbw2.zzbth == null) {
            zzbw2.zzbth = zzbw.zzbth;
        }
        if (zzbw2.zzbtj == null) {
            zzbw2.zzbtj = zzbw.zzbtj;
        }
        if (zzbw2.zzbti == null) {
            zzbw2.zzbti = zzbw.zzbti;
        }
        if (zzbw2.zzbtt == null) {
            zzbw2.zzbtt = zzbw.zzbtt;
        }
        if (zzbw2.zzbsw == null) {
            zzbw2.zzbsw = zzbw.zzbsw;
        }
        if (zzbw2.zzbtu == null) {
            zzbw2.zzbtu = zzbw.zzbtu;
        }
        if (zzbw2.zzbsx == null) {
            zzbw2.zzbsx = zzbw.zzbsx;
        }
        if (zzbw2.zzbsy == null) {
            zzbw2.zzbsy = zzbw.zzbsy;
        }
        if (zzbw2.zzbst == null) {
            zzbw2.zzbst = zzbw.zzbst;
        }
        if (zzbw2.zzbsu == null) {
            zzbw2.zzbsu = zzbw.zzbsu;
        }
        if (zzbw2.zzbsv == null) {
            zzbw2.zzbsv = zzbw.zzbsv;
        }
    }

    private final void zzkt() {
        zzaqp zzkn = zzkn();
        if (zzkn != null) {
            zzkn.zzug();
        }
    }
}
