package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.WebResourceResponse;
import com.google.android.gms.ads.internal.gmsg.zza;
import com.google.android.gms.ads.internal.gmsg.zzaa;
import com.google.android.gms.ads.internal.gmsg.zzab;
import com.google.android.gms.ads.internal.gmsg.zzac;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.gmsg.zzc;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.ads.internal.gmsg.zzf;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.ads.internal.gmsg.zzx;
import com.google.android.gms.ads.internal.gmsg.zzy;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.api.client.http.HttpStatusCodes;
import com.til.colombia.android.internal.e;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map.Entry;

@zzark
@VisibleForTesting
public final class zzbhu extends zzbie implements zzbhn {
    private final Object mLock = new Object();
    @Nullable
    private zzawr zzbmx;
    private zzvt zzciw;
    private zzb zzder;
    private zzd zzdes;
    private zzw zzdgb;
    private zzaoa zzdgc;
    private zzbgg zzdin;
    private boolean zzdzf;
    private zzn zzexw;
    private zzbho zzexx;
    private zzbhp zzexy;
    private zzbhq zzexz;
    private boolean zzeya = false;
    private boolean zzeyb;
    private OnGlobalLayoutListener zzeyc;
    private OnScrollChangedListener zzeyd;
    private boolean zzeye;
    private zzt zzeyf;
    private zzaoj zzeyg;
    private zzbhr zzeyh;
    private boolean zzeyi;
    private boolean zzeyj;
    private int zzeyk;
    private OnAttachStateChangeListener zzeyl;
    private final zzaig<zzbgg> zzfav = new zzaig();
    private volatile boolean zzfaw;

    /* Access modifiers changed, original: final */
    public final void zzb(zzbgg zzbgg, boolean z) {
        zzaoj zzaoj = new zzaoj(zzbgg, zzbgg.zzadg(), new zzzy(zzbgg.getContext()));
        this.zzdin = zzbgg;
        this.zzfaw = z;
        this.zzeyg = zzaoj;
        this.zzdgc = null;
        this.zzfav.zzi(zzbgg);
    }

    public final void zza(int i, int i2, boolean z) {
        this.zzeyg.zzj(i, i2);
        if (this.zzdgc != null) {
            this.zzdgc.zza(i, i2, z);
        }
    }

    public final void zza(String str, zzu<? super zzbgg> zzu) {
        this.zzfav.zza(str, (zzu) zzu);
    }

    public final void zzb(String str, zzu<? super zzbgg> zzu) {
        this.zzfav.zzb(str, (zzu) zzu);
    }

    public final void zza(String str, Predicate<zzu<? super zzbgg>> predicate) {
        this.zzfav.zza(str, (Predicate) predicate);
    }

    public final void zza(zzvt zzvt, zzb zzb, zzn zzn, zzd zzd, zzt zzt, boolean z, @Nullable zzy zzy, zzw zzw, zzaol zzaol, @Nullable zzawr zzawr) {
        if (zzw == null) {
            zzw = new zzw(this.zzdin.getContext(), zzawr, null);
        }
        this.zzdgc = new zzaoa(this.zzdin, zzaol);
        this.zzbmx = zzawr;
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcrk)).booleanValue()) {
            zza("/adMetadata", new zza(zzb));
        }
        zza("/appEvent", new zzc(zzd));
        zza("/backButton", zzf.zzdfc);
        zza("/refresh", zzf.zzdfd);
        zza("/canOpenURLs", zzf.zzdet);
        zza("/canOpenIntents", zzf.zzdeu);
        zza("/click", zzf.zzdev);
        zza("/close", zzf.zzdew);
        zza("/customClose", zzf.zzdex);
        zza("/instrument", zzf.zzdfg);
        zza("/delayPageLoaded", zzf.zzdfi);
        zza("/delayPageClosed", zzf.zzdfj);
        zza("/getLocationInfo", zzf.zzdfk);
        zza("/httpTrack", zzf.zzdey);
        zza("/log", zzf.zzdez);
        zza("/mraid", new zzab(zzw, this.zzdgc, zzaol));
        zza("/mraidLoaded", this.zzeyg);
        zza("/open", new zzac(zzw, this.zzdgc));
        zza("/precache", new zzbfq());
        zza("/touch", zzf.zzdfb);
        zza("/video", zzf.zzdfe);
        zza("/videoMeta", zzf.zzdff);
        if (zzbv.zzmf().zzv(this.zzdin.getContext())) {
            zza("/logScionEvent", new zzaa(this.zzdin.getContext()));
        }
        if (zzy != null) {
            zza("/setInterstitialProperties", new zzx(zzy));
        }
        this.zzciw = zzvt;
        this.zzexw = zzn;
        this.zzder = zzb;
        this.zzdes = zzd;
        this.zzeyf = zzt;
        this.zzdgb = zzw;
        this.zzeya = z;
    }

    public final zzw zzaea() {
        return this.zzdgb;
    }

    public final boolean zzmu() {
        return this.zzfaw;
    }

    public final boolean zzaeb() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzeyb;
        }
        return z;
    }

    public final OnGlobalLayoutListener zzaec() {
        OnGlobalLayoutListener onGlobalLayoutListener;
        synchronized (this.mLock) {
            onGlobalLayoutListener = this.zzeyc;
        }
        return onGlobalLayoutListener;
    }

    public final OnScrollChangedListener zzaed() {
        OnScrollChangedListener onScrollChangedListener;
        synchronized (this.mLock) {
            onScrollChangedListener = this.zzeyd;
        }
        return onScrollChangedListener;
    }

    public final boolean zzaee() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzeye;
        }
        return z;
    }

    public final void zza(zzbif zzbif) {
        this.zzeyi = true;
        if (this.zzexy != null) {
            this.zzexy.zzuc();
            this.zzexy = null;
        }
        zzaek();
    }

    private final void zza(View view, zzawr zzawr, int i) {
        if (zzawr.zzxq() && i > 0) {
            zzawr.zzs(view);
            if (zzawr.zzxq()) {
                zzayh.zzelc.postDelayed(new zzbhw(this, view, zzawr, i), 100);
            }
        }
    }

    private final void zzaef() {
        if (this.zzeyl != null) {
            this.zzdin.getView().removeOnAttachStateChangeListener(this.zzeyl);
        }
    }

    public final void zzaeg() {
        zzawr zzawr = this.zzbmx;
        if (zzawr != null) {
            View webView = this.zzdin.getWebView();
            if (ViewCompat.isAttachedToWindow(webView)) {
                zza(webView, zzawr, 10);
                return;
            }
            zzaef();
            this.zzeyl = new zzbhx(this, zzawr);
            this.zzdin.getView().addOnAttachStateChangeListener(this.zzeyl);
        }
    }

    public final void zzaeh() {
        synchronized (this.mLock) {
            this.zzeye = true;
        }
        this.zzeyk++;
        zzaek();
    }

    public final void zzaei() {
        this.zzeyk--;
        zzaek();
    }

    public final void zzaej() {
        this.zzeyj = true;
        zzaek();
    }

    private final void zzaek() {
        if (this.zzexx != null && ((this.zzeyi && this.zzeyk <= 0) || this.zzeyj)) {
            this.zzexx.zzp(this.zzeyj ^ 1);
            this.zzexx = null;
        }
        this.zzdin.zzadw();
    }

    public final void zza(com.google.android.gms.ads.internal.overlay.zzc zzc) {
        zzn zzn;
        boolean zzadq = this.zzdin.zzadq();
        zzvt zzvt = (!zzadq || this.zzdin.zzadj().zzafb()) ? this.zzciw : null;
        if (zzadq) {
            zzn = null;
        } else {
            zzn = this.zzexw;
        }
        zza(new AdOverlayInfoParcel(zzc, zzvt, zzn, this.zzeyf, this.zzdin.zzabz()));
    }

    public final void zzb(boolean z, int i) {
        zzvt zzvt = (!this.zzdin.zzadq() || this.zzdin.zzadj().zzafb()) ? this.zzciw : null;
        zza(new AdOverlayInfoParcel(zzvt, this.zzexw, this.zzeyf, this.zzdin, z, i, this.zzdin.zzabz()));
    }

    public final void zza(boolean z, int i, String str) {
        zzn zzn;
        boolean zzadq = this.zzdin.zzadq();
        zzvt zzvt = (!zzadq || this.zzdin.zzadj().zzafb()) ? this.zzciw : null;
        if (zzadq) {
            zzn = null;
        } else {
            zzn = new zzbhy(this.zzdin, this.zzexw);
        }
        zza(new AdOverlayInfoParcel(zzvt, zzn, this.zzder, this.zzdes, this.zzeyf, this.zzdin, z, i, str, this.zzdin.zzabz()));
    }

    public final void zza(boolean z, int i, String str, String str2) {
        zzn zzn;
        boolean zzadq = this.zzdin.zzadq();
        zzvt zzvt = (!zzadq || this.zzdin.zzadj().zzafb()) ? this.zzciw : null;
        if (zzadq) {
            zzn = null;
        } else {
            zzn = new zzbhy(this.zzdin, this.zzexw);
        }
        zza(new AdOverlayInfoParcel(zzvt, zzn, this.zzder, this.zzdes, this.zzeyf, this.zzdin, z, i, str, str2, this.zzdin.zzabz()));
    }

    private final void zza(AdOverlayInfoParcel adOverlayInfoParcel) {
        boolean z = false;
        boolean zzvm = this.zzdgc != null ? this.zzdgc.zzvm() : false;
        zzbv.zzld();
        Context context = this.zzdin.getContext();
        if (!zzvm) {
            z = true;
        }
        zzl.zza(context, adOverlayInfoParcel, z);
        if (this.zzbmx != null) {
            String str = adOverlayInfoParcel.url;
            if (str == null && adOverlayInfoParcel.zzdrs != null) {
                str = adOverlayInfoParcel.zzdrs.url;
            }
            this.zzbmx.zzdi(str);
        }
    }

    public final void destroy() {
        if (this.zzbmx != null) {
            this.zzbmx.zzxs();
            this.zzbmx = null;
        }
        zzaef();
        this.zzfav.reset();
        this.zzfav.zzi(null);
        synchronized (this.mLock) {
            this.zzciw = null;
            this.zzexw = null;
            this.zzexx = null;
            this.zzexy = null;
            this.zzder = null;
            this.zzdes = null;
            this.zzeyf = null;
            this.zzexz = null;
            if (this.zzdgc != null) {
                this.zzdgc.zzx(true);
                this.zzdgc = null;
            }
        }
    }

    public final void zza(zzbho zzbho) {
        this.zzexx = zzbho;
    }

    public final void zza(zzbhp zzbhp) {
        this.zzexy = zzbhp;
    }

    public final void zza(zzbhq zzbhq) {
        this.zzexz = zzbhq;
    }

    public final void zza(zzbhr zzbhr) {
        this.zzeyh = zzbhr;
    }

    public final zzbhr zzael() {
        return this.zzeyh;
    }

    public final void zzb(zzbif zzbif) {
        this.zzfav.zzf(zzbif.uri);
    }

    public final boolean zzc(zzbif zzbif) {
        String str = "AdWebView shouldOverrideUrlLoading: ";
        String valueOf = String.valueOf(zzbif.url);
        zzaxz.v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        Uri uri = zzbif.uri;
        if (this.zzfav.zzf(uri)) {
            return true;
        }
        if (this.zzeya) {
            valueOf = uri.getScheme();
            boolean z = "http".equalsIgnoreCase(valueOf) || "https".equalsIgnoreCase(valueOf);
            if (z) {
                if (this.zzciw != null) {
                    this.zzciw.onAdClicked();
                    if (this.zzbmx != null) {
                        this.zzbmx.zzdi(zzbif.url);
                    }
                    this.zzciw = null;
                }
                return false;
            }
        }
        if (this.zzdin.getWebView().willNotDraw()) {
            str = "AdWebView unable to handle URL: ";
            String valueOf2 = String.valueOf(zzbif.url);
            zzbbd.zzeo(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
        } else {
            try {
                zzcu zzado = this.zzdin.zzado();
                if (zzado != null && zzado.zzb(uri)) {
                    uri = zzado.zza(uri, this.zzdin.getContext(), this.zzdin.getView(), this.zzdin.zzabw());
                }
            } catch (zzcv unused) {
                valueOf = "Unable to append parameter to URL: ";
                String valueOf3 = String.valueOf(zzbif.url);
                zzbbd.zzeo(valueOf3.length() != 0 ? valueOf.concat(valueOf3) : new String(valueOf));
            }
            if (this.zzdgb == null || this.zzdgb.zzju()) {
                zza(new com.google.android.gms.ads.internal.overlay.zzc("android.intent.action.VIEW", uri.toString(), null, null, null, null, null));
            } else {
                this.zzdgb.zzas(zzbif.url);
            }
        }
        return true;
    }

    @Nullable
    public final WebResourceResponse zzd(zzbif zzbif) {
        WebResourceResponse zze;
        if (this.zzbmx != null) {
            this.zzbmx.zza(zzbif.url, zzbif.zzab, 1);
        }
        if ("mraid.js".equalsIgnoreCase(new File(zzbif.url).getName())) {
            String str;
            zzvr();
            if (this.zzdin.zzadj().zzafb()) {
                str = (String) zzwu.zzpz().zzd(zzaan.zzcpt);
            } else if (this.zzdin.zzadq()) {
                str = (String) zzwu.zzpz().zzd(zzaan.zzcps);
            } else {
                str = (String) zzwu.zzpz().zzd(zzaan.zzcpr);
            }
            zzbv.zzlf();
            zze = zzayh.zze(this.zzdin.getContext(), this.zzdin.zzabz().zzdp, str);
        } else {
            zze = null;
        }
        if (zze != null) {
            return zze;
        }
        try {
            if (!zzawz.zzb(zzbif.url, this.zzdin.getContext(), this.zzdzf).equals(zzbif.url)) {
                return zze(zzbif);
            }
            zzty zzbb = zzty.zzbb(zzbif.url);
            if (zzbb != null) {
                zztv zza = zzbv.zzll().zza(zzbb);
                if (zza != null && zza.zzoe()) {
                    return new WebResourceResponse("", "", zza.zzof());
                }
            }
            if (zzbax.isEnabled()) {
                if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcso)).booleanValue()) {
                    return zze(zzbif);
                }
            }
            return null;
        } catch (Exception | NoClassDefFoundError e) {
            zzbv.zzlj().zza(e, "AdWebViewClient.interceptRequest");
            return null;
        }
    }

    private final WebResourceResponse zze(zzbif zzbif) throws IOException {
        HttpURLConnection httpURLConnection;
        URL url = new URL(zzbif.url);
        int i = 0;
        while (true) {
            i++;
            if (i <= 20) {
                URLConnection openConnection = url.openConnection();
                openConnection.setConnectTimeout(10000);
                openConnection.setReadTimeout(10000);
                for (Entry entry : zzbif.zzab.entrySet()) {
                    openConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                }
                if (openConnection instanceof HttpURLConnection) {
                    httpURLConnection = (HttpURLConnection) openConnection;
                    zzbv.zzlf().zza(this.zzdin.getContext(), this.zzdin.zzabz().zzdp, false, httpURLConnection);
                    zzbax zzbax = new zzbax();
                    zzbax.zza(httpURLConnection, null);
                    int responseCode = httpURLConnection.getResponseCode();
                    zzbax.zza(httpURLConnection, responseCode);
                    if (responseCode < HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES || responseCode >= 400) {
                        zzbv.zzlf();
                    } else {
                        String headerField = httpURLConnection.getHeaderField(e.e);
                        if (headerField == null) {
                            throw new IOException("Missing Location header in redirect");
                        }
                        URL url2 = new URL(url, headerField);
                        String protocol = url2.getProtocol();
                        if (protocol == null) {
                            zzbbd.zzeo("Protocol is null");
                            return null;
                        } else if (protocol.equals("http") || protocol.equals("https")) {
                            protocol = "Redirecting to ";
                            headerField = String.valueOf(headerField);
                            zzbbd.zzdn(headerField.length() != 0 ? protocol.concat(headerField) : new String(protocol));
                            httpURLConnection.disconnect();
                            url = url2;
                        } else {
                            String str = "Unsupported scheme: ";
                            String valueOf = String.valueOf(protocol);
                            zzbbd.zzeo(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                            return null;
                        }
                    }
                }
                throw new IOException("Invalid protocol.");
            }
            StringBuilder stringBuilder = new StringBuilder(32);
            stringBuilder.append("Too many redirects (20)");
            throw new IOException(stringBuilder.toString());
        }
        zzbv.zzlf();
        return zzayh.zzd(httpURLConnection);
    }

    public final void zzat(boolean z) {
        this.zzeya = z;
    }

    public final zzawr zzaem() {
        return this.zzbmx;
    }

    public final void zza(OnGlobalLayoutListener onGlobalLayoutListener, OnScrollChangedListener onScrollChangedListener) {
        synchronized (this.mLock) {
            this.zzeyb = true;
            this.zzdin.zzadv();
            this.zzeyc = onGlobalLayoutListener;
            this.zzeyd = onScrollChangedListener;
        }
    }

    public final void zzvr() {
        synchronized (this.mLock) {
            this.zzeya = false;
            this.zzfaw = true;
            zzbcg.zzepo.execute(new zzbhv(this));
        }
    }

    public final void zzay(boolean z) {
        this.zzdzf = z;
    }

    public final void zzi(int i, int i2) {
        if (this.zzdgc != null) {
            this.zzdgc.zzi(i, i2);
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzaen() {
        this.zzdin.zzadv();
        com.google.android.gms.ads.internal.overlay.zzd zzadh = this.zzdin.zzadh();
        if (zzadh != null) {
            zzadh.zzvr();
        }
        if (this.zzexz != null) {
            this.zzexz.zzjx();
            this.zzexz = null;
        }
    }
}
