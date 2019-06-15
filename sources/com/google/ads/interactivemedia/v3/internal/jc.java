package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.UiElement;
import com.google.ads.interactivemedia.v3.impl.data.p;
import com.google.ads.interactivemedia.v3.impl.data.r;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.lang.reflect.Type;
import java.net.MalformedURLException;

public class jc {
    private static final fz a = new ga().a(UiElement.class, r.GSON_TYPE_ADAPTER).a(CompanionAdSlot.class, new gm<CompanionAdSlot>() {
        public gf a(CompanionAdSlot companionAdSlot, Type type, gl glVar) {
            int width = companionAdSlot.getWidth();
            int height = companionAdSlot.getHeight();
            StringBuilder stringBuilder = new StringBuilder(23);
            stringBuilder.append(width);
            stringBuilder.append(AvidJSONUtil.KEY_X);
            stringBuilder.append(height);
            return new gk(stringBuilder.toString());
        }
    }).a(new kk()).a();
    private final a b;
    private final Object c;
    private final String d;
    private final b e;

    public enum a {
        activityMonitor,
        adsManager,
        adsLoader,
        contentTimeUpdate,
        displayContainer,
        i18n,
        log,
        omid,
        videoDisplay,
        webViewLoaded
    }

    public enum b {
        adBreakEnded,
        adBreakReady,
        adBreakStarted,
        adMetadata,
        adProgress,
        adsLoaded,
        allAdsCompleted,
        appStateChanged,
        click,
        complete,
        companionView,
        contentComplete,
        contentPauseRequested,
        contentResumeRequested,
        contentTimeUpdate,
        csi,
        cuepointsChanged,
        discardAdBreak,
        displayCompanions,
        destroy,
        end,
        error,
        firstquartile,
        focusSkipButton,
        fullscreen,
        getViewability,
        hide,
        impression,
        init,
        initialized,
        load,
        loaded,
        loadStream,
        log,
        midpoint,
        mute,
        omidReady,
        omidUnavailable,
        pause,
        play,
        registerFriendlyObstructions,
        reportVastEvent,
        resume,
        requestAds,
        requestNextAdBreak,
        requestStream,
        setPlaybackOptions,
        showVideo,
        skip,
        skippableStateChanged,
        skipShown,
        start,
        startTracking,
        stop,
        stopTracking,
        streamInitialized,
        thirdquartile,
        timedMetadata,
        timeupdate,
        unload,
        unmute,
        viewability,
        videoClicked,
        videoIconClicked,
        volumeChange,
        adRemainingTime,
        learnMore,
        preSkipButton,
        skipButton
    }

    public static jc a(String str) throws MalformedURLException, gn {
        Uri parse = Uri.parse(str);
        String substring = parse.getPath().substring(1);
        if (parse.getQueryParameter("sid") != null) {
            return new jc(a.valueOf(substring), b.valueOf(parse.getQueryParameter("type")), parse.getQueryParameter("sid"), a.a(parse.getQueryParameter("data"), p.class));
        }
        throw new MalformedURLException("Session id must be provided in message.");
    }

    public jc(a aVar, b bVar, String str, Object obj) {
        this.b = aVar;
        this.e = bVar;
        this.d = str;
        this.c = obj;
    }

    public jc(a aVar, b bVar, String str) {
        this(aVar, bVar, str, null);
    }

    public a a() {
        return this.b;
    }

    public b b() {
        return this.e;
    }

    public Object c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        com.google.ads.interactivemedia.v3.internal.lb.a a = new com.google.ads.interactivemedia.v3.internal.lb.a().a("type", this.e).a("sid", this.d);
        if (this.c != null) {
            a.a("data", this.c);
        }
        Object a2 = a.a();
        return String.format("%s('%s', %s);", new Object[]{"javascript:adsense.mobileads.afmanotify.receiveMessage", this.b, a.a(a2)});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof jc)) {
            return false;
        }
        jc jcVar = (jc) obj;
        return this.b == jcVar.b && ko.a(this.c, jcVar.c) && ko.a(this.d, jcVar.d) && this.e == jcVar.e;
    }

    public int hashCode() {
        return ko.a(this.b, this.c, this.d, this.e);
    }

    public String toString() {
        return String.format("JavaScriptMessage [command=%s, type=%s, sid=%s, data=%s]", new Object[]{this.b, this.e, this.d, this.c});
    }
}
