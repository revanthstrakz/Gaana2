package com.til.colombia.android.service;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import com.til.colombia.android.internal.a.a;
import com.til.colombia.android.internal.a.e;
import com.til.colombia.android.internal.a.j;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.utils.AdUtil;
import java.util.WeakHashMap;

public final class ColombiaAdManager {
    private AdUtil adUtil;
    private e impressionTracker;
    private boolean isFirstImpression = true;
    private boolean isFirstRequest = true;
    private boolean isOnCall = false;
    private Context mContext;
    private a phoneCallManager;
    private boolean returnItemUrl;
    private WeakHashMap<String, ColombiaNativeVideoAdView> videoViews;

    public enum AD_NTWK {
        COLOMBIA,
        GOOGLE,
        FACEBOOK,
        CRITEO
    }

    public enum DFP_ITEM_TYPE {
        NONE,
        CONTENT,
        APP,
        ALL
    }

    public enum GENDER {
        UNKNOWN,
        MALE,
        FEMALE
    }

    public enum ITEM_TYPE {
        PRODUCT,
        CONTENT,
        APP,
        GENERAL,
        VIDEO,
        LEADGEN,
        PIC_FRAME,
        UNDECLARED,
        THIRD_PARTY,
        AUDIO,
        VIDEO_INCENTIVE,
        AUDIO_BANNER,
        VIDEO_CONTENT,
        SOV,
        UNDECLARED1,
        UNDECLARED2,
        BANNER,
        INTERSTITIAL_VIDEO
    }

    @Deprecated
    public final boolean isClientWebViewEnabled() {
        return false;
    }

    @Deprecated
    public final ColombiaAdManager returnItemUrl(boolean z) {
        return this;
    }

    private ColombiaAdManager(Context context) {
        init(context);
    }

    public final AdUtil getAdUtil() {
        return this.adUtil;
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean isFirstImpression() {
        return this.isFirstImpression;
    }

    /* Access modifiers changed, original: protected|final */
    public final void setFirstImpression(boolean z) {
        if (this.isFirstImpression) {
            this.isFirstImpression = z;
        }
    }

    public final boolean isFirstRequest() {
        return this.isFirstRequest;
    }

    public final void setFirstRequest(boolean z) {
        if (this.isFirstImpression) {
            this.isFirstRequest = z;
        }
    }

    public static ColombiaAdManager create(Context context) {
        if (context == null) {
            Log.i(i.f, "Activity context can not be null.");
            return null;
        } else if (context instanceof Activity) {
            return new ColombiaAdManager(context);
        } else {
            Log.i(i.f, "ColombiaAdManager works only with Activity Context.");
            return null;
        }
    }

    public final Context getActivityContext() {
        return this.mContext;
    }

    private void init(Context context) {
        this.mContext = context;
        this.isFirstImpression = true;
        this.isFirstRequest = true;
        this.impressionTracker = new e(context);
        if (com.til.colombia.android.internal.a.J()) {
            this.phoneCallManager = new ag(this);
            this.phoneCallManager.a(this.mContext);
        }
        this.adUtil = new AdUtil();
    }

    /* Access modifiers changed, original: protected|final */
    public final e getImpressionTracker() {
        return this.impressionTracker;
    }

    public final void addVideoView(String str, ColombiaNativeVideoAdView colombiaNativeVideoAdView) {
        if (this.videoViews == null) {
            this.videoViews = new WeakHashMap();
        }
        this.videoViews.put(str, colombiaNativeVideoAdView);
    }

    public final ColombiaNativeVideoAdView getColombiaNativeVideoAdView(String str) {
        if (this.videoViews == null) {
            return null;
        }
        return (ColombiaNativeVideoAdView) this.videoViews.get(str);
    }

    public final boolean isOnCall() {
        return this.isOnCall;
    }

    public final synchronized boolean reset() {
        destroy();
        if (this.mContext == null) {
            return false;
        }
        init(this.mContext);
        return true;
    }

    public final synchronized void onPause() {
        if (this.phoneCallManager != null) {
            this.phoneCallManager.b(this.mContext);
        }
    }

    public final synchronized void onResume() {
        if (this.phoneCallManager != null) {
            this.phoneCallManager.a(this.mContext);
        }
    }

    public final synchronized void destroy() {
        if (this.videoViews != null) {
            for (ColombiaNativeVideoAdView colombiaNativeVideoAdView : this.videoViews.values()) {
                colombiaNativeVideoAdView.clear();
                colombiaNativeVideoAdView.removeAllViews();
            }
            this.videoViews.clear();
        }
        if (this.impressionTracker != null) {
            e eVar = this.impressionTracker;
            eVar.b.clear();
            eVar.c.clear();
            eVar.a.a();
            eVar.d.removeMessages(0);
            j jVar = eVar.a;
            jVar.a();
            View view = (View) jVar.e.get();
            if (!(view == null || jVar.d == null)) {
                ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnScrollChangedListener(jVar.d);
                }
                jVar.d = null;
            }
            jVar.h = null;
            eVar.e = null;
        }
        if (this.phoneCallManager != null) {
            this.phoneCallManager.b(this.mContext);
        }
        this.phoneCallManager = null;
        this.adUtil = null;
        this.impressionTracker = null;
        this.videoViews = null;
    }
}
