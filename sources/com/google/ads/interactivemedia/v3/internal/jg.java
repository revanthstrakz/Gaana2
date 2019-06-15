package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.impl.data.p;
import com.google.ads.interactivemedia.v3.internal.jc.b;

public class jg implements jo {
    private final VideoAdPlayer a;
    private final ih b;
    private final ij c;
    private final il d;
    private final if e;
    private boolean f;
    private boolean g;

    public jg(String str, jf jfVar, jd jdVar, ij ijVar, AdDisplayContainer adDisplayContainer, Context context) throws AdError {
        this(str, jfVar, jdVar, ijVar, adDisplayContainer, null, null, context);
    }

    public void b() {
    }

    public void c() {
    }

    public jg(String str, jf jfVar, jd jdVar, ij ijVar, AdDisplayContainer adDisplayContainer, ih ihVar, il ilVar, Context context) throws AdError {
        this.g = false;
        if (adDisplayContainer.getPlayer() != null) {
            this.a = adDisplayContainer.getPlayer();
            this.f = true;
        } else if (VERSION.SDK_INT >= 16) {
            this.a = new iy(context, adDisplayContainer.getAdContainer());
            this.f = false;
        } else {
            throw new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Ad Player was not provided. SDK-owned ad playback requires API 16+");
        }
        if (ihVar != null) {
            this.b = ihVar;
        } else {
            this.b = new ih(this.a, jfVar.a());
        }
        this.c = ijVar;
        if (ilVar != null) {
            this.d = ilVar;
        } else {
            this.d = new il(jdVar, adDisplayContainer);
        }
        this.e = new if(jdVar, str, this.b, this.a);
    }

    public void a(boolean z) {
        this.b.a(this.e);
        this.g = z;
    }

    public VideoProgressUpdate getAdProgress() {
        return this.a.getAdProgress();
    }

    public void a(b bVar, p pVar) {
        switch (bVar) {
            case play:
                this.a.playAd();
                return;
            case pause:
                this.a.pauseAd();
                return;
            case resume:
                this.a.resumeAd();
                return;
            case load:
                if (pVar == null || pVar.videoUrl == null) {
                    this.c.a((AdErrorEvent) new id(new AdError(AdErrorType.LOAD, AdErrorCode.INTERNAL_ERROR, "Load message must contain video url.")));
                    return;
                }
                this.b.b();
                this.a.loadAd(pVar.videoUrl);
                return;
            default:
                return;
        }
    }

    public boolean b(b bVar, p pVar) {
        switch (bVar) {
            case showVideo:
                if (!this.f) {
                    ((jj) this.a).a();
                }
                this.a.addCallback(this.e);
                break;
            case hide:
                if (!this.f) {
                    ((jj) this.a).b();
                }
                this.a.removeCallback(this.e);
                break;
            default:
                return false;
        }
        return true;
    }

    public void a(com.google.ads.interactivemedia.v3.impl.data.b bVar) {
        if (this.g && bVar.canDisableUi()) {
            bVar.setUiDisabled(true);
            return;
        }
        bVar.setUiDisabled(false);
        this.d.a(bVar);
    }

    public void a() {
        this.a.stopAd();
        this.d.a();
    }

    public void d() {
        this.b.c();
    }

    public void onAdError(AdErrorEvent adErrorEvent) {
        this.d.a();
    }

    public void e() {
        Log.d("SDK_DEBUG", "Destroying NativeVideoDisplay");
        this.b.c();
        this.b.b(this.e);
        this.d.b();
        this.a.removeCallback(this.e);
        if (this.a instanceof jj) {
            ((jj) this.a).c();
        }
    }

    public boolean f() {
        return this.f;
    }
}
