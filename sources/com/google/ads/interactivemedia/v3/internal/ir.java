package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventListener;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType;
import com.google.ads.interactivemedia.v3.api.AdProgressInfo;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.api.BaseManager;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.impl.data.CompanionData;
import com.google.ads.interactivemedia.v3.impl.data.b;
import com.google.ads.interactivemedia.v3.internal.jc.a;
import com.google.ads.interactivemedia.v3.internal.jd.c;
import com.google.ads.interactivemedia.v3.internal.jd.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class ir implements BaseManager, d {
    protected final jd a;
    protected final String b;
    protected AdsRenderingSettings c;
    protected jo d;
    protected iu e;
    protected iv f;
    protected boolean g = false;
    private final List<AdEventListener> h = new ArrayList(1);
    private final ix i = new ix();
    private final Context j;
    private b k;
    private AdProgressInfo l;
    private ib m;
    private jh n;

    protected ir(String str, jd jdVar, jf jfVar, BaseDisplayContainer baseDisplayContainer, ib ibVar, jh jhVar, Context context, boolean z) throws AdError {
        this.b = str;
        this.a = jdVar;
        this.j = context;
        this.c = new im();
        if (ibVar != null) {
            this.m = ibVar;
        } else {
            this.m = new ib(str, jdVar, baseDisplayContainer.getAdContainer());
        }
        this.m.a(z);
        this.n = a(jhVar, baseDisplayContainer);
        jdVar.a((d) this, str);
        this.m.a();
    }

    public void a(Map<String, CompanionData> map) {
    }

    public abstract boolean isCustomPlaybackUsed();

    public void init() {
        init(null);
    }

    public void init(AdsRenderingSettings adsRenderingSettings) {
        if (adsRenderingSettings == null) {
            adsRenderingSettings = this.c;
        }
        this.c = adsRenderingSettings;
        HashMap hashMap = new HashMap();
        hashMap.put("adsRenderingSettings", this.c);
        if (this.f != null) {
            VideoProgressUpdate a = this.f.a();
            if (!a.equals(VideoProgressUpdate.VIDEO_TIME_NOT_READY)) {
                double currentTime = (double) a.getCurrentTime();
                StringBuilder stringBuilder = new StringBuilder(68);
                stringBuilder.append("AdsManager.init -> Setting contentStartTime ");
                stringBuilder.append(currentTime);
                Log.d("IMASDK", stringBuilder.toString());
                hashMap.put("contentStartTime", Double.valueOf(currentTime));
            }
        }
        if (!isCustomPlaybackUsed()) {
            hashMap.put("sdkOwnedPlayer", Boolean.valueOf(true));
        }
        this.d.a(this.c.getDisableUi());
        this.a.a(this.c);
        this.a.b(new jc(a.adsManager, jc.b.init, this.b, hashMap));
    }

    private jh a(jh jhVar, BaseDisplayContainer baseDisplayContainer) {
        if (jhVar == null) {
            return null;
        }
        jhVar.a(this.b);
        jhVar.b(baseDisplayContainer.getAdContainer());
        addAdEventListener(jhVar);
        addAdErrorListener(jhVar);
        iq iqVar = (iq) baseDisplayContainer;
        for (View c : iqVar.b()) {
            jhVar.c(c);
        }
        iqVar.a(jhVar);
        return jhVar;
    }

    public VideoProgressUpdate getAdProgress() {
        if (this.g) {
            return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
        }
        return this.d.getAdProgress();
    }

    public Ad getCurrentAd() {
        return this.k;
    }

    public void addAdErrorListener(AdErrorListener adErrorListener) {
        this.i.a(adErrorListener);
    }

    public void removeAdErrorListener(AdErrorListener adErrorListener) {
        this.i.b(adErrorListener);
    }

    public void addAdEventListener(AdEventListener adEventListener) {
        this.h.add(adEventListener);
    }

    public void removeAdEventListener(AdEventListener adEventListener) {
        this.h.remove(adEventListener);
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        this.d.e();
        if (this.n != null && this.n.f()) {
            Log.d("IMASDK", "OMID ad session ended on BaseManager destroy.");
        }
        if (this.f != null) {
            this.f.c();
        }
        this.m.b();
        this.a.b(this.b);
        this.k = null;
    }

    /* Access modifiers changed, original: protected */
    public void a(jc.b bVar) {
        this.a.b(new jc(a.adsManager, bVar, this.b));
    }

    private void a(AdEventType adEventType) {
        a(adEventType, null);
    }

    private void a(AdEventType adEventType, Map<String, String> map) {
        ie ieVar = new ie(adEventType, this.k, map);
        for (AdEventListener onAdEvent : this.h) {
            onAdEvent.onAdEvent(ieVar);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(AdErrorEvent adErrorEvent) {
        this.i.a(adErrorEvent);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(b bVar) {
        this.k = bVar;
    }

    public void a(c cVar) {
        AdEventType adEventType = cVar.a;
        b bVar = cVar.b;
        switch (adEventType) {
            case LOADED:
                a(bVar);
                break;
            case STARTED:
                if (bVar != null) {
                    a(bVar);
                }
                this.d.a(bVar);
                break;
            case AD_BREAK_ENDED:
                this.l = null;
                break;
            case COMPLETED:
            case SKIPPED:
                this.d.a();
                this.l = null;
                break;
            case CONTENT_PAUSE_REQUESTED:
                if (this.f != null) {
                    this.f.c();
                }
                this.m.c();
                break;
            case CONTENT_RESUME_REQUESTED:
                if (this.f != null) {
                    this.f.b();
                }
                this.m.d();
                break;
            case CLICKED:
                String clickThruUrl = bVar.getClickThruUrl();
                if (!jt.a(clickThruUrl)) {
                    this.a.c(clickThruUrl);
                    break;
                }
                break;
            case ICON_TAPPED:
                if (!jt.a(cVar.f)) {
                    this.a.c(cVar.f);
                    break;
                }
                break;
            case SKIPPABLE_STATE_CHANGED:
                if (this.c.getFocusSkipButtonWhenAvailable()) {
                    a(this.b);
                    break;
                }
                break;
            case AD_PROGRESS:
                this.l = cVar.e;
                break;
        }
        if (cVar.c != null) {
            a(adEventType, cVar.c);
        } else {
            a(adEventType);
        }
        if (adEventType == AdEventType.COMPLETED || adEventType == AdEventType.SKIPPED) {
            a(null);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(String str) {
        if (jr.a(this.j, this.a.c())) {
            this.a.b().requestFocus();
            this.a.b(new jc(a.videoDisplay, jc.b.focusSkipButton, str));
        }
    }

    public void a(AdErrorType adErrorType, int i, String str) {
        b(new id(new AdError(adErrorType, i, str)));
    }

    public void a(AdErrorType adErrorType, AdErrorCode adErrorCode, String str) {
        b(new id(new AdError(adErrorType, adErrorCode, str)));
    }

    private void b(AdErrorEvent adErrorEvent) {
        this.l = null;
        a(adErrorEvent);
    }

    public AdProgressInfo getAdProgressInfo() {
        return this.l;
    }
}
