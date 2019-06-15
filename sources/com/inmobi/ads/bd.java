package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.ah.c;
import com.inmobi.ads.ai.a;
import com.inmobi.commons.core.e.b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.integralads.avid.library.inmobi.session.AvidManagedVideoAdSession;
import java.lang.ref.WeakReference;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@TargetApi(15)
public class bd extends ah {
    private static final String D = "bd";
    WeakReference<View> B;
    a C = new a() {
        public final void a(View view, boolean z) {
            bd.this.a(z);
            bd.a(bd.this, view, z);
        }
    };
    private final AdContainer.a E = new AdContainer.a() {
        public final void a() {
            bd.D;
            c e = bd.this.e();
            if (e != null) {
                e.a();
            }
        }

        public final void a(@NonNull Object obj) {
            if (bd.this.l() != null) {
                be beVar = (be) obj;
                bd.D;
                beVar.v.put("didRequestFullScreen", Boolean.valueOf(true));
                beVar.v.put("isFullScreen", Boolean.valueOf(true));
                beVar.v.put("shouldAutoPlay", Boolean.valueOf(true));
                if (beVar.y != null) {
                    beVar.y.v.put("didRequestFullScreen", Boolean.valueOf(true));
                    beVar.y.v.put("isFullScreen", Boolean.valueOf(true));
                    beVar.y.v.put("shouldAutoPlay", Boolean.valueOf(true));
                }
                if (PlacementType.PLACEMENT_TYPE_INLINE == bd.this.b.a) {
                    bd.this.getViewableAd().a(1);
                    beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_FULLSCREEN, bd.this.g(beVar));
                }
                c e = bd.this.e();
                if (e != null) {
                    e.b();
                }
            }
        }

        public final void b(@NonNull Object obj) {
            bd.D;
            be beVar = (be) obj;
            beVar.v.put("didRequestFullScreen", Boolean.valueOf(false));
            beVar.v.put("isFullScreen", Boolean.valueOf(false));
            if (beVar.y != null) {
                beVar.y.v.put("didRequestFullScreen", Boolean.valueOf(false));
                beVar.y.v.put("isFullScreen", Boolean.valueOf(false));
                beVar.y.y = null;
            }
            beVar.y = null;
            if (bd.this.b.a == PlacementType.PLACEMENT_TYPE_INLINE) {
                bd.this.getViewableAd().a(2);
                if (bd.this.n != null) {
                    bd.this.n.getViewableAd().a(16);
                }
                beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_EXIT_FULLSCREEN, bd.this.g(beVar));
            } else {
                bd.this.getViewableAd().a(3);
            }
            c e = bd.this.e();
            if (e != null) {
                e.f();
            }
        }
    };

    private void a(NativeVideoView nativeVideoView) {
        int videoVolume = nativeVideoView.getVideoVolume();
        int lastVolume = nativeVideoView.getLastVolume();
        if (videoVolume != lastVolume && lastVolume > 0) {
            b(true);
            nativeVideoView.setLastVolume(videoVolume);
        }
    }

    bd(@NonNull Context context, @NonNull RenderingProperties renderingProperties, @NonNull ao aoVar, @NonNull String str, @NonNull String str2, @Nullable Set<bq> set, @NonNull c cVar, long j, boolean z, String str3) {
        super(context, renderingProperties, aoVar, str, str2, set, cVar, j, z, str3);
        this.a = aoVar;
    }

    /* Access modifiers changed, original: final */
    public final void a(View view) {
        if (!k() && !this.l && (view instanceof NativeVideoView)) {
            NativeVideoView nativeVideoView = (NativeVideoView) view;
            this.k = true;
            HashMap hashMap = new HashMap();
            hashMap.put("type", PlacementType.PLACEMENT_TYPE_FULLSCREEN == getRenderingProperties().a ? "int" : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
            hashMap.put("clientRequestId", this.h);
            hashMap.put("impId", this.d);
            b.a();
            b.a("ads", "ViewableBeaconFired", hashMap);
            f((be) nativeVideoView.getTag());
        }
    }

    /* Access modifiers changed, original: final */
    public final boolean i() {
        return PlacementType.PLACEMENT_TYPE_INLINE == this.b.a && l() != null;
    }

    @SuppressLint({"SwitchIntDef"})
    public ca getViewableAd() {
        Context j = j();
        if (this.j == null && j != null) {
            g();
            this.j = new ab(this, new cd(this));
            if (this.i != null) {
                if (j instanceof Activity) {
                    try {
                        Activity activity = (Activity) j;
                        for (bq bqVar : this.i) {
                            int i = bqVar.a;
                            if (i == 1) {
                                ca caVar = this.j;
                                Map map = bqVar.b;
                                be beVar = (be) this.a.c(ShareConstants.VIDEO_URL).get(0);
                                StringBuilder stringBuilder = new StringBuilder();
                                for (NativeTracker nativeTracker : beVar.u) {
                                    if (TrackerEventType.TRACKER_EVENT_TYPE_MOAT == nativeTracker.b) {
                                        stringBuilder.append(nativeTracker.a);
                                    }
                                }
                                if (stringBuilder.length() > 0) {
                                    map.put("zMoatVASTIDs", stringBuilder.toString());
                                }
                                this.j = new af(activity, caVar, this, map);
                            } else if (i == 3) {
                                AvidManagedVideoAdSession avidManagedVideoAdSession = (AvidManagedVideoAdSession) bqVar.b.get("avidAdSession");
                                if (avidManagedVideoAdSession != null) {
                                    this.j = new w(activity, this.j, this, avidManagedVideoAdSession);
                                }
                            }
                        }
                    } catch (Exception e) {
                        new StringBuilder("Exception occurred while creating the video viewable ad : ").append(e.getMessage());
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    }
                } else {
                    HashMap hashMap = new HashMap();
                    hashMap.put("type", AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
                    hashMap.put("impId", this.d);
                    b.a();
                    b.a("ads", "TrackersForService", hashMap);
                }
            }
        }
        return this.j;
    }

    @NonNull
    public AdContainer.a getFullScreenEventsListener() {
        return this.E;
    }

    @Nullable
    public View getVideoContainerView() {
        return this.B == null ? null : (View) this.B.get();
    }

    /* Access modifiers changed, original: final */
    public final boolean n() {
        return !this.r;
    }

    /* Access modifiers changed, original: final */
    public final void w() {
        this.j.a(5);
    }

    /* Access modifiers changed, original: final */
    public final void e(be beVar) {
        new StringBuilder("Firing Q4 beacons for completion at ").append(beVar.E);
        beVar.v.put("didQ4Fire", Boolean.valueOf(true));
        beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_Q4, g(beVar));
        this.j.a(12);
        HashMap hashMap = new HashMap();
        hashMap.put("url", beVar.b().b());
        hashMap.put("isCached", "1");
        hashMap.put("completeAfter", Integer.valueOf(beVar.E));
        a("VideoQ4Completed", (Map) hashMap);
    }

    /* Access modifiers changed, original: final */
    public final void q() {
        super.q();
        NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) getVideoContainerView();
        if (nativeVideoWrapper != null) {
            NativeVideoView videoView = nativeVideoWrapper.getVideoView();
            if (this.b.a == PlacementType.PLACEMENT_TYPE_INLINE && !i() && videoView.getVideoVolume() > 0) {
                videoView.setLastVolume(-2);
                b(true);
            }
            videoView.pause();
        }
    }

    @VisibleForTesting
    private static String y() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (i == 0) {
            i = (secureRandom.nextInt() & Integer.MAX_VALUE) % 10;
        }
        stringBuilder.append(i);
        for (i = 1; i < 8; i++) {
            stringBuilder.append((secureRandom.nextInt() & Integer.MAX_VALUE) % 10);
        }
        return stringBuilder.toString();
    }

    private void b(boolean z) {
        if (this.b.a == PlacementType.PLACEMENT_TYPE_INLINE && !i()) {
            c e = e();
            if (e != null) {
                e.a(z);
            }
        }
    }

    public void destroy() {
        if (!this.l) {
            if (getVideoContainerView() != null) {
                NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) getVideoContainerView();
                if (nativeVideoWrapper != null) {
                    nativeVideoWrapper.getVideoView().c();
                }
            }
            super.destroy();
        }
    }

    private void f(@NonNull be beVar) {
        if (!((Boolean) beVar.v.get("didImpressionFire")).booleanValue()) {
            List<NativeTracker> list = beVar.u;
            Map g = g(beVar);
            List arrayList = new ArrayList();
            for (NativeTracker nativeTracker : list) {
                if (TrackerEventType.TRACKER_EVENT_TYPE_VIDEO_RENDER == nativeTracker.b) {
                    if (nativeTracker.a.startsWith("http")) {
                        ak.a(nativeTracker, g);
                    }
                    List<TrackerEventType> arrayList2 = (List) nativeTracker.d.get("referencedEvents");
                    for (TrackerEventType a : arrayList2) {
                        beVar.a(a, g);
                    }
                }
            }
            if (arrayList2.isEmpty()) {
                beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_PLAY, g);
                beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_RENDER, g);
            }
            this.a.d.a(TrackerEventType.TRACKER_EVENT_TYPE_RENDER, g(beVar));
            beVar.v.put("didImpressionFire", Boolean.valueOf(true));
            this.j.a(0);
            if (this.b.a == PlacementType.PLACEMENT_TYPE_INLINE) {
                HashMap hashMap = new HashMap();
                hashMap.put("type", AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
                hashMap.put("clientRequestId", this.h);
                hashMap.put("impId", this.d);
                a("AdRendered", (Map) hashMap);
            }
            if (e() != null) {
                e().d();
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void b(@NonNull ak akVar) {
        NativeVideoWrapper nativeVideoWrapper;
        be beVar;
        switch (akVar.l) {
            case 0:
                return;
            case 1:
                super.b(akVar);
                return;
            case 3:
                try {
                    if (this.w != null) {
                        this.w.d("window.imraid.broadcastEvent('replay');");
                    }
                    if (f() != null) {
                        View f = f();
                        NativeTimerView b = ah.b(f);
                        if (b != null) {
                            b.a();
                        }
                        ViewGroup viewGroup = (ViewGroup) f.getParent();
                        if (viewGroup != null) {
                            viewGroup.removeView(f);
                        }
                    }
                    if (ShareConstants.VIDEO_URL.equals(akVar.b)) {
                        nativeVideoWrapper = (NativeVideoWrapper) getVideoContainerView();
                        if (nativeVideoWrapper != null) {
                            nativeVideoWrapper.getVideoView().e();
                            nativeVideoWrapper.getVideoView().start();
                        }
                        return;
                    }
                    new StringBuilder("Action 3 not valid for asset of type: ").append(akVar.b);
                    return;
                } catch (Exception e) {
                    new StringBuilder("Encountered unexpected error in handling replay action on video: ").append(e.getMessage());
                    Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in replaying video");
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    return;
                }
            case 4:
                try {
                    if (PlacementType.PLACEMENT_TYPE_INLINE == this.b.a) {
                        nativeVideoWrapper = (NativeVideoWrapper) getVideoContainerView();
                        if (nativeVideoWrapper != null) {
                            NativeVideoView videoView = nativeVideoWrapper.getVideoView();
                            beVar = (be) videoView.getTag();
                            if (videoView.getState() != 1) {
                                try {
                                    if (!this.l) {
                                        if (this.p.get() != null) {
                                            if (!((Boolean) beVar.v.get("didRequestFullScreen")).booleanValue()) {
                                                beVar.v.put("didRequestFullScreen", Boolean.valueOf(true));
                                                beVar.v.put("seekPosition", Integer.valueOf(videoView.getCurrentPosition()));
                                                beVar.v.put("lastMediaVolume", Integer.valueOf(videoView.getVolume()));
                                                if (videoView.getMediaPlayer().isPlaying()) {
                                                    videoView.getMediaPlayer().pause();
                                                }
                                                videoView.getMediaPlayer().a = 4;
                                                beVar.v.put("isFullScreen", Boolean.valueOf(true));
                                                beVar.v.put("seekPosition", Integer.valueOf(videoView.getMediaPlayer().getCurrentPosition()));
                                                m();
                                            }
                                            return;
                                        }
                                    }
                                    return;
                                } catch (Exception e2) {
                                    new StringBuilder("SDK encountered unexpected error in handling the onVideoRequestedFullScreen event; ").append(e2.getMessage());
                                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                                }
                            }
                        }
                    }
                    return;
                } catch (Exception e22) {
                    new StringBuilder("Encountered unexpected error in handling fullscreen action on video: ").append(e22.getMessage());
                    Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in expanding video to fullscreen");
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e22));
                    return;
                }
            case 5:
                try {
                    nativeVideoWrapper = (NativeVideoWrapper) getVideoContainerView();
                    if (nativeVideoWrapper != null) {
                        beVar = (be) nativeVideoWrapper.getVideoView().getTag();
                        beVar.v.put("shouldAutoPlay", Boolean.valueOf(true));
                        if (beVar.y != null) {
                            beVar.y.v.put("shouldAutoPlay", Boolean.valueOf(true));
                        }
                        nativeVideoWrapper.getVideoView().start();
                    }
                    return;
                } catch (Exception e222) {
                    new StringBuilder("Encountered unexpected error in handling play action on video: ").append(e222.getMessage());
                    Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in playing video");
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e222));
                    return;
                }
            default:
                try {
                    if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.b.a) {
                        super.b(akVar);
                        if (ShareConstants.VIDEO_URL.equals(akVar.b)) {
                            NativeVideoWrapper nativeVideoWrapper2 = (NativeVideoWrapper) getVideoContainerView();
                            if (nativeVideoWrapper2 != null) {
                                nativeVideoWrapper2.getVideoView().d();
                                NativeVideoView videoView2 = nativeVideoWrapper2.getVideoView();
                                if (videoView2.b() && videoView2.c.isPlaying()) {
                                    videoView2.c.pause();
                                    videoView2.c.seekTo(0);
                                    if (videoView2.getTag() != null) {
                                        be beVar2 = (be) videoView2.getTag();
                                        beVar2.v.put("didPause", Boolean.valueOf(true));
                                        beVar2.v.put("seekPosition", Integer.valueOf(0));
                                        beVar2.v.put("didCompleteQ4", Boolean.valueOf(true));
                                    }
                                    videoView2.c.a = 4;
                                    videoView2.getPlaybackEventListener().a(4);
                                }
                                if (videoView2.c != null) {
                                    videoView2.c.b = 4;
                                }
                            }
                            return;
                        }
                        new StringBuilder("Action 2 not valid for asset of type: ").append(akVar.b);
                        return;
                    }
                    c e3 = e();
                    if (e3 != null) {
                        e3.i();
                    }
                    return;
                } catch (Exception e4) {
                    new StringBuilder("Action 2 not valid for asset of type: ").append(akVar.b);
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e4));
                    return;
                }
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(be beVar, int i) {
        if (!this.l) {
            HashMap hashMap = new HashMap();
            hashMap.put("errorCode", String.valueOf(i));
            hashMap.put("reason", "Video Player Error");
            hashMap.put("url", beVar.b().b());
            a("VideoError", (Map) hashMap);
            beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_ERROR, g(beVar));
            this.j.a(17);
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(be beVar) {
        if (!this.l) {
            ah.c(f());
            beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_PAUSE, g(beVar));
            this.j.a(7);
        }
    }

    /* Access modifiers changed, original: final */
    public final void b(be beVar) {
        if (!this.l) {
            ah.d(f());
            beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_RESUME, g(beVar));
            this.j.a(8);
        }
    }

    /* Access modifiers changed, original: final */
    public final void c(be beVar) {
        if (!this.l) {
            beVar.v.put("lastMediaVolume", Integer.valueOf(0));
            beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_MUTE, g(beVar));
            this.j.a(13);
        }
    }

    /* Access modifiers changed, original: final */
    public final void d(be beVar) {
        if (!this.l) {
            beVar.v.put("lastMediaVolume", Integer.valueOf(15));
            beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_UNMUTE, g(beVar));
            this.j.a(14);
        }
    }

    /* Access modifiers changed, original: final */
    public final void b(be beVar, int i) {
        if (!this.l) {
            HashMap hashMap;
            switch (i) {
                case 0:
                    beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_Q1, g(beVar));
                    hashMap = new HashMap();
                    hashMap.put("url", beVar.b().b());
                    hashMap.put("isCached", "1");
                    a("VideoQ1Completed", (Map) hashMap);
                    this.j.a(9);
                    return;
                case 1:
                    beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_Q2, g(beVar));
                    hashMap = new HashMap();
                    hashMap.put("url", beVar.b().b());
                    hashMap.put("isCached", "1");
                    a("VideoQ2Completed", (Map) hashMap);
                    this.j.a(10);
                    return;
                case 2:
                    beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_Q3, g(beVar));
                    hashMap = new HashMap();
                    hashMap.put("url", beVar.b().b());
                    hashMap.put("isCached", "1");
                    a("VideoQ3Completed", (Map) hashMap);
                    this.j.a(11);
                    return;
                case 3:
                    if (!((Boolean) beVar.v.get("didQ4Fire")).booleanValue()) {
                        e(beVar);
                        break;
                    }
                    break;
            }
        }
    }

    private Map<String, String> g(@NonNull be beVar) {
        ak akVar = beVar;
        am amVar = (am) akVar.t;
        HashMap hashMap = new HashMap(4);
        NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) this.B.get();
        if (nativeVideoWrapper != null) {
            hashMap.put("$MD", String.valueOf((int) Math.round((((double) nativeVideoWrapper.getVideoView().getDuration()) * 1.0d) / 1000.0d)));
        }
        hashMap.put("[ERRORCODE]", "405");
        Object[] objArr = new Object[4];
        long intValue = (long) ((Integer) akVar.v.get("seekPosition")).intValue();
        objArr[0] = Long.valueOf(TimeUnit.MILLISECONDS.toHours(intValue));
        objArr[1] = Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(intValue) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(intValue)));
        am amVar2 = amVar;
        objArr[2] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(intValue) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(intValue)));
        objArr[3] = Long.valueOf(intValue - (TimeUnit.MILLISECONDS.toSeconds(intValue) * 1000));
        hashMap.put("[CONTENTPLAYHEAD]", String.format(Locale.US, "%02d:%02d:%02d.%03d", objArr));
        hashMap.put("[CACHEBUSTING]", y());
        hashMap.put("[ASSETURI]", beVar.b().b());
        hashMap.put("$TS", String.valueOf(System.currentTimeMillis()));
        hashMap.put("$LTS", String.valueOf(this.a.d.z));
        if (amVar2 != null) {
            hashMap.put("$STS", String.valueOf(amVar2.z));
        }
        return hashMap;
    }
}
