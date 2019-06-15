package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.ads.c.k;
import com.inmobi.commons.core.a.a;
import com.moat.analytics.mobile.inm.MoatAdEvent;
import com.moat.analytics.mobile.inm.MoatAdEventType;
import com.moat.analytics.mobile.inm.ReactiveVideoTracker;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

class af extends bz {
    private static final String d = "af";
    @NonNull
    private final WeakReference<Activity> e;
    private ReactiveVideoTracker f;
    @NonNull
    private Map<String, Object> g;
    @NonNull
    private ca h;
    private boolean i = false;

    af(@NonNull Activity activity, @NonNull ca caVar, @NonNull bd bdVar, @NonNull Map<String, Object> map) {
        super(bdVar);
        this.e = new WeakReference(activity);
        this.h = caVar;
        this.g = map;
        this.f = (ReactiveVideoTracker) map.get("moatTracker");
    }

    @Nullable
    public final View a(View view, ViewGroup viewGroup, boolean z) {
        return this.h.a(view, viewGroup, z);
    }

    @Nullable
    public final View b() {
        return this.h.b();
    }

    /* Access modifiers changed, original: final */
    @NonNull
    public final c c() {
        return this.h.c();
    }

    public final void a(@Nullable View... viewArr) {
        try {
            Activity activity = (Activity) this.e.get();
            k kVar = this.h.c().k;
            if (activity != null && (this.a instanceof bd) && kVar.i && ((Boolean) this.g.get("enabled")).booleanValue() && this.f == null) {
                this.f = z.a(activity.getApplication(), (String) this.g.get("partnerCode"));
                this.g.put("moatTracker", this.f);
                this.i = true;
            }
        } catch (Exception e) {
            new StringBuilder("Exception in startTrackingForImpression with message : ").append(e.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e));
        } catch (Throwable th) {
            this.h.a(viewArr);
        }
        this.h.a(viewArr);
    }

    @SuppressLint({"SwitchIntDef"})
    public final void a(int i) {
        try {
            if (this.f != null) {
                StringBuilder stringBuilder = new StringBuilder("Received event : ");
                stringBuilder.append(i);
                stringBuilder.append(" for VideoTracker(");
                stringBuilder.append(this.f.hashCode());
                stringBuilder.append(")");
                NativeVideoWrapper nativeVideoWrapper;
                switch (i) {
                    case 1:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_ENTER_FULLSCREEN));
                        break;
                    case 2:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_EXIT_FULLSCREEN));
                        break;
                    case 3:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_STOPPED));
                        break;
                    case 5:
                    case 16:
                        nativeVideoWrapper = (NativeVideoWrapper) this.a.getVideoContainerView();
                        if (!(nativeVideoWrapper == null || this.f == null)) {
                            NativeVideoView videoView = nativeVideoWrapper.getVideoView();
                            if (!this.i) {
                                this.f.changeTargetView(nativeVideoWrapper);
                                break;
                            }
                            ReactiveVideoTracker reactiveVideoTracker = this.f;
                            HashMap a = c.a("level", "slicer", (JSONArray) this.g.get("clientLevels"), (JSONArray) this.g.get("clientSlicers"), (JSONObject) this.g.get("zMoatExtras"));
                            a.put("zMoatVASTIDs", (String) this.g.get("zMoatVASTIDs"));
                            reactiveVideoTracker.trackVideoAd(a, Integer.valueOf(videoView.getDuration()), nativeVideoWrapper);
                            this.i = false;
                            break;
                        }
                    case 6:
                        nativeVideoWrapper = (NativeVideoWrapper) this.a.getVideoContainerView();
                        if (nativeVideoWrapper != null) {
                            this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_START, Integer.valueOf(nativeVideoWrapper.getVideoView().getMediaPlayer().getCurrentPosition())));
                            break;
                        }
                        break;
                    case 7:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_PAUSED));
                        break;
                    case 8:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_PLAYING));
                        break;
                    case 9:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_FIRST_QUARTILE));
                        break;
                    case 10:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_MID_POINT));
                        break;
                    case 11:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_THIRD_QUARTILE));
                        break;
                    case 12:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE));
                        break;
                    case 13:
                        this.f.setPlayerVolume(MoatAdEvent.VOLUME_MUTED);
                        break;
                    case 14:
                        this.f.setPlayerVolume(MoatAdEvent.VOLUME_UNMUTED);
                        break;
                    case 15:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_SKIPPED));
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            new StringBuilder("Exception in onAdEvent with message : ").append(e.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e));
        } catch (Throwable th) {
            this.h.a(i);
        }
        this.h.a(i);
    }

    public final void a(Context context, int i) {
        this.h.a(context, i);
    }

    public final void e() {
        this.f = null;
        this.e.clear();
        super.e();
        this.h.e();
    }

    public final void d() {
        try {
            if (!(((bd) this.a).i() || this.f == null)) {
                this.f.stopTracking();
            }
        } catch (Exception e) {
            new StringBuilder("Exception in stopTrackingForImpression with message : ").append(e.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e));
        } catch (Throwable th) {
            this.h.d();
        }
        this.h.d();
    }
}
