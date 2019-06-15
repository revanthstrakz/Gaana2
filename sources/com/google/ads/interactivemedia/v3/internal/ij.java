package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventListener;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType;
import com.google.ads.interactivemedia.v3.api.AdProgressInfo;
import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.api.player.ContentProgressProvider;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.impl.data.CompanionData;
import com.google.ads.interactivemedia.v3.internal.jc.a;
import com.google.ads.interactivemedia.v3.internal.jc.b;
import com.google.ads.interactivemedia.v3.internal.jd.c;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public class ij extends ir implements AdsManager {
    private List<CompanionData> h;
    private List<Float> i;

    ij(String str, jd jdVar, jf jfVar, BaseDisplayContainer baseDisplayContainer, ContentProgressProvider contentProgressProvider, List<Float> list, SortedSet<Float> sortedSet, jh jhVar, Context context, boolean z) throws AdError {
        this(str, jdVar, jfVar, baseDisplayContainer, contentProgressProvider, list, sortedSet, null, null, null, jhVar, context, z);
    }

    ij(String str, jd jdVar, jf jfVar, BaseDisplayContainer baseDisplayContainer, ContentProgressProvider contentProgressProvider, List<Float> list, SortedSet<Float> sortedSet, jo joVar, iv ivVar, ib ibVar, jh jhVar, Context context, boolean z) throws AdError {
        String str2 = str;
        jd jdVar2 = jdVar;
        ContentProgressProvider contentProgressProvider2 = contentProgressProvider;
        SortedSet<Float> sortedSet2 = sortedSet;
        jo joVar2 = joVar;
        iv ivVar2 = ivVar;
        super(str2, jdVar2, jfVar, baseDisplayContainer, ibVar, jhVar, context, z);
        this.i = list;
        if (!(sortedSet2 == null || sortedSet.isEmpty())) {
            if (contentProgressProvider2 == null) {
                throw new AdError(AdErrorType.PLAY, AdErrorCode.PLAYLIST_NO_CONTENT_TRACKING, "Unable to handle cue points, no content progress provider configured.");
            }
            if (ivVar2 != null) {
                this.f = ivVar2;
            } else {
                this.f = new iv(contentProgressProvider2, jfVar.a());
            }
            this.e = new iu(jdVar2, sortedSet2, str2);
            this.f.a(this.e);
            this.f.b();
        }
        if (joVar2 != null) {
            this.d = joVar2;
        } else {
            this.d = new jg(str2, jfVar, jdVar2, this, (AdDisplayContainer) baseDisplayContainer, context);
        }
        addAdErrorListener(this.d);
        jdVar2.a(this.d, str2);
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        this.h = null;
        super.a();
    }

    public void discardAdBreak() {
        a(b.discardAdBreak);
    }

    public void requestNextAdBreak() {
        if (this.f != null) {
            this.a.b(new jc(a.contentTimeUpdate, b.contentTimeUpdate, this.b, this.f.a()));
            a(b.requestNextAdBreak);
        }
    }

    public void start() {
        a(b.start);
    }

    public void pause() {
        a(b.pause);
    }

    public void resume() {
        a(b.resume);
    }

    public void skip() {
        a(b.skip);
    }

    public boolean isCustomPlaybackUsed() {
        return this.d.f();
    }

    private List<CompanionData> getCurrentCompanions() {
        return this.h;
    }

    private void onCompanionRendered(String str) {
        this.a.a(str, this.b);
    }

    private void b(Map<String, CompanionData> map) {
        if (map != null) {
            this.h = la.a(map.values());
        } else {
            this.h = null;
        }
    }

    public void a(c cVar) {
        AdEventType adEventType = cVar.a;
        switch (adEventType) {
            case ALL_ADS_COMPLETED:
                a();
                if (!this.g) {
                    a(b.destroy);
                    break;
                }
                break;
            case CONTENT_RESUME_REQUESTED:
                this.d.d();
                break;
        }
        if (adEventType == AdEventType.COMPLETED || adEventType == AdEventType.SKIPPED) {
            b(null);
        }
        super.a(cVar);
    }

    public List<Float> getAdCuePoints() {
        return this.i;
    }

    public void a(Map<String, CompanionData> map) {
        b(map);
    }

    public void clicked() {
        this.a.b(new jc(a.adsManager, b.click, this.b));
    }

    public void focusSkipButton() {
        if (getCurrentAd() != null && getCurrentAd().isSkippable()) {
            a(this.b);
        }
    }

    public void destroy() {
        if (this.f != null) {
            this.f.c();
        }
        a(b.destroy);
        this.g = true;
    }

    public /* bridge */ /* synthetic */ AdProgressInfo getAdProgressInfo() {
        return super.getAdProgressInfo();
    }

    public /* bridge */ /* synthetic */ void removeAdEventListener(AdEventListener adEventListener) {
        super.removeAdEventListener(adEventListener);
    }

    public /* bridge */ /* synthetic */ void addAdEventListener(AdEventListener adEventListener) {
        super.addAdEventListener(adEventListener);
    }

    public /* bridge */ /* synthetic */ void removeAdErrorListener(AdErrorListener adErrorListener) {
        super.removeAdErrorListener(adErrorListener);
    }

    public /* bridge */ /* synthetic */ void addAdErrorListener(AdErrorListener adErrorListener) {
        super.addAdErrorListener(adErrorListener);
    }

    public /* bridge */ /* synthetic */ Ad getCurrentAd() {
        return super.getCurrentAd();
    }

    public /* bridge */ /* synthetic */ VideoProgressUpdate getAdProgress() {
        return super.getAdProgress();
    }

    public /* bridge */ /* synthetic */ void init(AdsRenderingSettings adsRenderingSettings) {
        super.init(adsRenderingSettings);
    }

    public /* bridge */ /* synthetic */ void init() {
        super.init();
    }
}
