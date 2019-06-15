package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.gaana.cardoption.AssetsHelper.CARD;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
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
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpStatusCodes;
import com.til.colombia.android.internal.e;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

@zzark
@VisibleForTesting
public class zzbgh extends WebViewClient implements zzbhn {
    private static final String[] zzext = new String[]{CARD.UNKNOWN, "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", HttpMethods.CONNECT, "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
    private static final String[] zzexu = new String[]{"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};
    private final Object mLock;
    @Nullable
    protected zzawr zzbmx;
    private boolean zzbul;
    private zzvt zzciw;
    private zzb zzder;
    private zzd zzdes;
    private zzw zzdgb;
    private zzaoa zzdgc;
    private zzbgg zzdin;
    private boolean zzdzf;
    @Nullable
    private final zzum zzehw;
    private final HashMap<String, List<zzu<? super zzbgg>>> zzexv;
    private zzn zzexw;
    private zzbho zzexx;
    private zzbhp zzexy;
    private zzbhq zzexz;
    private boolean zzeya;
    private boolean zzeyb;
    private OnGlobalLayoutListener zzeyc;
    private OnScrollChangedListener zzeyd;
    private boolean zzeye;
    private zzt zzeyf;
    private final zzaoj zzeyg;
    private zzbhr zzeyh;
    private boolean zzeyi;
    private boolean zzeyj;
    private int zzeyk;
    private OnAttachStateChangeListener zzeyl;

    public zzbgh(zzbgg zzbgg, zzum zzum, boolean z) {
        this(zzbgg, zzum, z, new zzaoj(zzbgg, zzbgg.zzadg(), new zzzy(zzbgg.getContext())), null);
    }

    @VisibleForTesting
    private zzbgh(zzbgg zzbgg, zzum zzum, boolean z, zzaoj zzaoj, zzaoa zzaoa) {
        this.zzexv = new HashMap();
        this.mLock = new Object();
        this.zzeya = false;
        this.zzehw = zzum;
        this.zzdin = zzbgg;
        this.zzbul = z;
        this.zzeyg = zzaoj;
        this.zzdgc = null;
    }

    public final void zza(int i, int i2, boolean z) {
        this.zzeyg.zzj(i, i2);
        if (this.zzdgc != null) {
            this.zzdgc.zza(i, i2, z);
        }
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
        boolean z;
        synchronized (this.mLock) {
            z = this.zzbul;
        }
        return z;
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

    /* JADX WARNING: Missing block: B:9:0x0018, code skipped:
            r0.zzeyi = true;
     */
    /* JADX WARNING: Missing block: B:10:0x001d, code skipped:
            if (r0.zzexy == null) goto L_0x0027;
     */
    /* JADX WARNING: Missing block: B:11:0x001f, code skipped:
            r0.zzexy.zzuc();
            r0.zzexy = null;
     */
    /* JADX WARNING: Missing block: B:12:0x0027, code skipped:
            zzaek();
     */
    /* JADX WARNING: Missing block: B:13:0x002a, code skipped:
            return;
     */
    public final void onPageFinished(android.webkit.WebView r1, java.lang.String r2) {
        /*
        r0 = this;
        r1 = r0.mLock;
        monitor-enter(r1);
        r2 = r0.zzdin;	 Catch:{ all -> 0x002b }
        r2 = r2.isDestroyed();	 Catch:{ all -> 0x002b }
        if (r2 == 0) goto L_0x0017;
    L_0x000b:
        r2 = "Blank page loaded, 1...";
        com.google.android.gms.internal.ads.zzaxz.v(r2);	 Catch:{ all -> 0x002b }
        r2 = r0.zzdin;	 Catch:{ all -> 0x002b }
        r2.zzadr();	 Catch:{ all -> 0x002b }
        monitor-exit(r1);	 Catch:{ all -> 0x002b }
        return;
    L_0x0017:
        monitor-exit(r1);	 Catch:{ all -> 0x002b }
        r1 = 1;
        r0.zzeyi = r1;
        r1 = r0.zzexy;
        if (r1 == 0) goto L_0x0027;
    L_0x001f:
        r1 = r0.zzexy;
        r1.zzuc();
        r1 = 0;
        r0.zzexy = r1;
    L_0x0027:
        r0.zzaek();
        return;
    L_0x002b:
        r2 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x002b }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbgh.onPageFinished(android.webkit.WebView, java.lang.String):void");
    }

    private final void zza(View view, zzawr zzawr, int i) {
        if (zzawr.zzxq() && i > 0) {
            zzawr.zzs(view);
            if (zzawr.zzxq()) {
                zzayh.zzelc.postDelayed(new zzbgj(this, view, zzawr, i), 100);
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
            this.zzeyl = new zzbgk(this, zzawr);
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
            zzn = new zzbgl(this.zzdin, this.zzexw);
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
            zzn = new zzbgl(this.zzdin, this.zzexw);
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

    public final void zza(String str, zzu<? super zzbgg> zzu) {
        synchronized (this.mLock) {
            List list = (List) this.zzexv.get(str);
            if (list == null) {
                list = new CopyOnWriteArrayList();
                this.zzexv.put(str, list);
            }
            list.add(zzu);
        }
    }

    public final void zzb(String str, zzu<? super zzbgg> zzu) {
        synchronized (this.mLock) {
            List list = (List) this.zzexv.get(str);
            if (list == null) {
                return;
            }
            list.remove(zzu);
        }
    }

    public final void zza(String str, Predicate<zzu<? super zzbgg>> predicate) {
        synchronized (this.mLock) {
            List<zzu> list = (List) this.zzexv.get(str);
            if (list == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (zzu zzu : list) {
                if (predicate.apply(zzu)) {
                    arrayList.add(zzu);
                }
            }
            list.removeAll(arrayList);
        }
    }

    public final void reset() {
        if (this.zzbmx != null) {
            this.zzbmx.zzxs();
            this.zzbmx = null;
        }
        zzaef();
        synchronized (this.mLock) {
            this.zzexv.clear();
            this.zzciw = null;
            this.zzexw = null;
            this.zzexx = null;
            this.zzexy = null;
            this.zzder = null;
            this.zzdes = null;
            this.zzeya = false;
            this.zzbul = false;
            this.zzeyb = false;
            this.zzeye = false;
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

    public final void onLoadResource(WebView webView, String str) {
        String str2 = "Loading resource: ";
        String valueOf = String.valueOf(str);
        zzaxz.v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzi(parse);
        }
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        String str3;
        if (i < 0) {
            int i2 = (-i) - 1;
            if (i2 < zzext.length) {
                str3 = zzext[i2];
                zzd(this.zzdin.getContext(), "http_err", str3, str2);
                super.onReceivedError(webView, i, str, str2);
            }
        }
        str3 = String.valueOf(i);
        zzd(this.zzdin.getContext(), "http_err", str3, str2);
        super.onReceivedError(webView, i, str, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (sslError != null) {
            String valueOf;
            int primaryError = sslError.getPrimaryError();
            if (primaryError < 0 || primaryError >= zzexu.length) {
                valueOf = String.valueOf(primaryError);
            } else {
                valueOf = zzexu[primaryError];
            }
            zzd(this.zzdin.getContext(), "ssl_err", valueOf, zzbv.zzlh().zza(sslError));
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    private final void zzd(Context context, String str, String str2, String str3) {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcsx)).booleanValue()) {
            Bundle bundle = new Bundle();
            bundle.putString(NotificationCompat.CATEGORY_ERROR, str);
            bundle.putString("code", str2);
            str = "host";
            if (!TextUtils.isEmpty(str3)) {
                Uri parse = Uri.parse(str3);
                if (parse.getHost() != null) {
                    str2 = parse.getHost();
                    bundle.putString(str, str2);
                    zzbv.zzlf().zza(context, this.zzdin.zzabz().zzdp, "gmob-apps", bundle, true);
                }
            }
            str2 = "";
            bundle.putString(str, str2);
            zzbv.zzlf().zza(context, this.zzdin.zzabz().zzdp, "gmob-apps", bundle, true);
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String str2 = "AdWebView shouldOverrideUrlLoading: ";
        String valueOf = String.valueOf(str);
        zzaxz.v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzi(parse);
        } else {
            if (this.zzeya && webView == this.zzdin.getWebView()) {
                valueOf = parse.getScheme();
                boolean z = "http".equalsIgnoreCase(valueOf) || "https".equalsIgnoreCase(valueOf);
                if (z) {
                    if (this.zzciw != null) {
                        this.zzciw.onAdClicked();
                        if (this.zzbmx != null) {
                            this.zzbmx.zzdi(str);
                        }
                        this.zzciw = null;
                    }
                    return super.shouldOverrideUrlLoading(webView, str);
                }
            }
            String str3;
            if (this.zzdin.getWebView().willNotDraw()) {
                str3 = "AdWebView unable to handle URL: ";
                str = String.valueOf(str);
                zzbbd.zzeo(str.length() != 0 ? str3.concat(str) : new String(str3));
            } else {
                try {
                    zzcu zzado = this.zzdin.zzado();
                    if (zzado != null && zzado.zzb(parse)) {
                        parse = zzado.zza(parse, this.zzdin.getContext(), this.zzdin.getView(), this.zzdin.zzabw());
                    }
                } catch (zzcv unused) {
                    str3 = "Unable to append parameter to URL: ";
                    valueOf = String.valueOf(str);
                    zzbbd.zzeo(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                }
                if (this.zzdgb == null || this.zzdgb.zzju()) {
                    zza(new com.google.android.gms.ads.internal.overlay.zzc("android.intent.action.VIEW", parse.toString(), null, null, null, null, null));
                } else {
                    this.zzdgb.zzas(str);
                }
            }
        }
        return true;
    }

    @Nullable
    @TargetApi(11)
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zzd(str, Collections.emptyMap());
    }

    /* Access modifiers changed, original: protected|final */
    @Nullable
    public final WebResourceResponse zzd(String str, Map<String, String> map) {
        try {
            String zzb = zzawz.zzb(str, this.zzdin.getContext(), this.zzdzf);
            if (!zzb.equals(str)) {
                return zze(zzb, map);
            }
            zzty zzbb = zzty.zzbb(str);
            if (zzbb != null) {
                zztv zza = zzbv.zzll().zza(zzbb);
                if (zza != null && zza.zzoe()) {
                    return new WebResourceResponse("", "", zza.zzof());
                }
            }
            if (zzbax.isEnabled()) {
                if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcso)).booleanValue()) {
                    return zze(str, map);
                }
            }
            return null;
        } catch (Exception | NoClassDefFoundError e) {
            zzbv.zzlj().zza(e, "AdWebViewClient.interceptRequest");
            return null;
        }
    }

    private final WebResourceResponse zze(String str, Map<String, String> map) throws IOException {
        HttpURLConnection httpURLConnection;
        URL url = new URL(str);
        int i = 0;
        while (true) {
            i++;
            if (i <= 20) {
                URLConnection openConnection = url.openConnection();
                openConnection.setConnectTimeout(10000);
                openConnection.setReadTimeout(10000);
                for (Entry entry : map.entrySet()) {
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
                            str = "Unsupported scheme: ";
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
            this.zzbul = true;
            zzbcg.zzepo.execute(new zzbgi(this));
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

    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (!(keyCode == 79 || keyCode == 222)) {
            switch (keyCode) {
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 90:
                case 91:
                    break;
                default:
                    switch (keyCode) {
                        case 126:
                        case 127:
                        case 128:
                        case TsExtractor.TS_STREAM_TYPE_AC3 /*129*/:
                        case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /*130*/:
                            break;
                        default:
                            return false;
                    }
            }
        }
        return true;
    }

    private final void zzi(Uri uri) {
        String path = uri.getPath();
        List<zzu> list = (List) this.zzexv.get(path);
        if (list != null) {
            zzbv.zzlf();
            Map zzg = zzayh.zzg(uri);
            if (zzbbd.isLoggable(2)) {
                String str = "Received GMSG: ";
                path = String.valueOf(path);
                zzaxz.v(path.length() != 0 ? str.concat(path) : new String(str));
                for (String str2 : zzg.keySet()) {
                    String str3 = (String) zzg.get(str2);
                    StringBuilder stringBuilder = new StringBuilder((4 + String.valueOf(str2).length()) + String.valueOf(str3).length());
                    stringBuilder.append("  ");
                    stringBuilder.append(str2);
                    stringBuilder.append(": ");
                    stringBuilder.append(str3);
                    zzaxz.v(stringBuilder.toString());
                }
            }
            for (zzu zza : list) {
                zza.zza(this.zzdin, zzg);
            }
            return;
        }
        String valueOf = String.valueOf(uri);
        StringBuilder stringBuilder2 = new StringBuilder(32 + String.valueOf(valueOf).length());
        stringBuilder2.append("No GMSG handler found for GMSG: ");
        stringBuilder2.append(valueOf);
        zzaxz.v(stringBuilder2.toString());
    }

    public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
        if (this.zzehw != null) {
            this.zzehw.zza(zzuo.zza.zzb.ANDROID_WEBVIEW_CRASH);
        }
        if (webView.getParent() instanceof ViewGroup) {
            ((ViewGroup) webView.getParent()).removeView(webView);
        }
        webView.destroy();
        return true;
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
