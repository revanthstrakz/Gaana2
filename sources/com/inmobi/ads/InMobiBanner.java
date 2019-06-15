package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.i.d;
import com.inmobi.ads.listeners.BannerAdEventListener;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.b.c;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

public final class InMobiBanner extends RelativeLayout {
    private static final String a = "InMobiBanner";
    private static ConcurrentHashMap<p, WeakReference<BannerAdRequestListener>> q = new ConcurrentHashMap(5, 0.9f, 3);
    @Nullable
    private BannerAdListener b;
    @Nullable
    private BannerAdEventListener c;
    private b d;
    @Nullable
    private p e;
    @Nullable
    private p f;
    @Nullable
    private p g;
    @Nullable
    private p h;
    private boolean i = false;
    private int j;
    private boolean k = true;
    @Nullable
    private q l;
    private int m = 0;
    private int n = 0;
    private AnimationType o = AnimationType.ROTATE_HORIZONTAL_AXIS;
    private long p = 0;
    private j r;
    @Nullable
    private WeakReference<Activity> s;
    @Nullable
    private bi t;
    private boolean u;
    private boolean v = true;
    private final com.inmobi.ads.i.b w = new com.inmobi.ads.i.b() {
        public final void a() {
            try {
                if (InMobiBanner.this.g == null || !InMobiBanner.this.g.P()) {
                    InMobiBanner.a(InMobiBanner.this, new a() {
                        public final void a() {
                            try {
                                InMobiBanner.this.a("AR", "");
                                InMobiBanner.this.d.sendEmptyMessage(1);
                                InMobiBanner.this.b();
                            } catch (Exception e) {
                                Logger.a(InternalLogLevel.ERROR, InMobiBanner.a, "Encountered unexpected error in scheduling refresh for banner ad");
                                InMobiBanner.a;
                                new StringBuilder("InMobiBanner$5.onSuccess() handler threw unexpected error: ").append(e.getMessage());
                            }
                        }
                    });
                }
            } catch (Exception e) {
                Logger.a(InternalLogLevel.ERROR, InMobiBanner.a, "Encountered unexpected error in loading banner ad");
                InMobiBanner.a;
                new StringBuilder("InMobiBanner$2.onAdLoadSucceeded() handler threw unexpected error: ").append(e.getMessage());
            }
        }

        public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
            try {
                switch (inMobiAdRequestStatus.getStatusCode()) {
                    case NETWORK_UNREACHABLE:
                        InMobiBanner.this.a("ART", "NetworkNotAvailable");
                        break;
                    case REQUEST_PENDING:
                    case AD_ACTIVE:
                        InMobiBanner.this.a("ART", "LoadInProgress");
                        break;
                    case EARLY_REFRESH_REQUEST:
                        InMobiBanner.this.a("ART", "FrequentRequests");
                        break;
                    default:
                        InMobiBanner.this.a("AF", "");
                        break;
                }
                if (!InMobiBanner.c()) {
                    Message obtain = Message.obtain();
                    obtain.what = 2;
                    obtain.obj = inMobiAdRequestStatus;
                    InMobiBanner.this.d.sendMessage(obtain);
                }
                InMobiBanner.this.b();
            } catch (Exception e) {
                Logger.a(InternalLogLevel.ERROR, InMobiBanner.a, "Encountered unexpected error in loading banner ad");
                InMobiBanner.a;
                new StringBuilder("InMobiBanner$2.onAdLoadFailed() handler threw unexpected error: ").append(e.getMessage());
            }
        }

        public final void d() {
            InMobiBanner.this.d.sendEmptyMessage(3);
        }

        public final void e() {
            try {
                InMobiBanner.this.b();
                InMobiBanner.this.d.sendEmptyMessage(4);
            } catch (Exception e) {
                Logger.a(InternalLogLevel.ERROR, InMobiBanner.a, "Encountered unexpected error in closing banner ad");
                InMobiBanner.a;
                new StringBuilder("InMobiBanner$2.onAdDismissed() handler threw unexpected error: ").append(e.getMessage());
            }
        }

        public final void a(@NonNull Map<Object, Object> map) {
            InMobiBanner.this.a("AVCL", "");
            Message obtain = Message.obtain();
            obtain.what = 5;
            obtain.obj = map;
            InMobiBanner.this.d.sendMessage(obtain);
        }

        public final void f() {
            InMobiBanner.this.d.sendEmptyMessage(6);
        }

        public final void b(@NonNull Map<Object, Object> map) {
            Message obtain = Message.obtain();
            obtain.what = 7;
            obtain.obj = map;
            InMobiBanner.this.d.sendMessage(obtain);
        }

        /* Access modifiers changed, original: final */
        public final void a(byte[] bArr) {
            Message obtain = Message.obtain();
            obtain.what = 8;
            obtain.obj = bArr;
            InMobiBanner.this.d.sendMessage(obtain);
        }

        /* Access modifiers changed, original: final */
        public final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
            Message obtain = Message.obtain();
            obtain.what = 9;
            obtain.obj = inMobiAdRequestStatus;
            InMobiBanner.this.d.sendMessage(obtain);
        }
    };

    public enum AnimationType {
        ANIMATION_OFF,
        ROTATE_HORIZONTAL_AXIS,
        ANIMATION_ALPHA,
        ROTATE_VERTICAL_AXIS
    }

    public interface BannerAdListener {
        void onAdDismissed(InMobiBanner inMobiBanner);

        void onAdDisplayed(InMobiBanner inMobiBanner);

        void onAdInteraction(InMobiBanner inMobiBanner, Map<Object, Object> map);

        void onAdLoadFailed(InMobiBanner inMobiBanner, InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(InMobiBanner inMobiBanner);

        void onAdRewardActionCompleted(InMobiBanner inMobiBanner, Map<Object, Object> map);

        void onUserLeftApplication(InMobiBanner inMobiBanner);
    }

    public interface BannerAdRequestListener {
        void onAdRequestCompleted(InMobiAdRequestStatus inMobiAdRequestStatus, InMobiBanner inMobiBanner);
    }

    private interface a {
        void a();
    }

    @VisibleForTesting
    static final class b extends Handler {
        private WeakReference<InMobiBanner> a;

        b(InMobiBanner inMobiBanner) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(inMobiBanner);
        }

        public final void handleMessage(Message message) {
            InMobiBanner inMobiBanner = (InMobiBanner) this.a.get();
            if (inMobiBanner != null) {
                try {
                    Map map = null;
                    switch (message.what) {
                        case 1:
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onAdLoadSucceeded(inMobiBanner);
                                return;
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onAdLoadSucceeded(inMobiBanner);
                                return;
                            }
                            break;
                        case 2:
                            InMobiAdRequestStatus inMobiAdRequestStatus = (InMobiAdRequestStatus) message.obj;
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onAdLoadFailed(inMobiBanner, inMobiAdRequestStatus);
                                return;
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onAdLoadFailed(inMobiBanner, inMobiAdRequestStatus);
                                return;
                            }
                            break;
                        case 3:
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onAdDisplayed(inMobiBanner);
                                return;
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onAdDisplayed(inMobiBanner);
                                return;
                            }
                            break;
                        case 4:
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onAdDismissed(inMobiBanner);
                                return;
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onAdDismissed(inMobiBanner);
                                return;
                            }
                            break;
                        case 5:
                            if (message.obj != null) {
                                map = (Map) message.obj;
                            }
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onAdClicked(inMobiBanner, map);
                                return;
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onAdInteraction(inMobiBanner, map);
                                return;
                            }
                            break;
                        case 6:
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onUserLeftApplication(inMobiBanner);
                                return;
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onUserLeftApplication(inMobiBanner);
                                return;
                            }
                            break;
                        case 7:
                            if (message.obj != null) {
                                map = (Map) message.obj;
                            }
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onRewardsUnlocked(inMobiBanner, map);
                                return;
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onAdRewardActionCompleted(inMobiBanner, map);
                                return;
                            }
                            break;
                        case 8:
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onRequestPayloadCreated((byte[]) message.obj);
                                return;
                            }
                            break;
                        case 9:
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onRequestPayloadCreationFailed((InMobiAdRequestStatus) message.obj);
                                return;
                            }
                            break;
                        default:
                            InMobiBanner.a;
                            break;
                    }
                } catch (Exception e) {
                    Logger.a(InternalLogLevel.ERROR, InMobiBanner.a, "Publisher handler caused unexpected error");
                    InMobiBanner.a;
                    new StringBuilder("Callback threw unexpected error: ").append(e.getMessage());
                }
            }
        }
    }

    public InMobiBanner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (com.inmobi.commons.a.a.a()) {
            boolean z = context instanceof Activity;
            if (z) {
                this.s = new WeakReference((Activity) context);
            }
            this.d = new b(this);
            String attributeValue = attributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.inmobi.ads", AudienceNetworkActivity.PLACEMENT_ID);
            String attributeValue2 = attributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.inmobi.ads", "refreshInterval");
            if (attributeValue != null) {
                long a = a(attributeValue);
                if (a != Long.MIN_VALUE) {
                    this.t = bi.a(a, null, "banner", null);
                    this.t.f = z ? MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : MonetizationContext.MONETIZATION_CONTEXT_OTHER;
                    a(context, this.t);
                    this.i = true;
                }
            } else {
                Logger.a(InternalLogLevel.ERROR, a, "Placement id value is not supplied in XML layout. Banner creation failed.");
            }
            if (attributeValue2 != null) {
                try {
                    setRefreshInterval(Integer.parseInt(attributeValue2.trim()));
                    return;
                } catch (NumberFormatException unused) {
                    Logger.a(InternalLogLevel.ERROR, a, "Refresh interval value supplied in XML layout is not valid. Falling back to default value.");
                }
            }
            return;
        }
        Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before creating a Banner ad");
    }

    private static long a(@NonNull String str) {
        InternalLogLevel internalLogLevel;
        String str2;
        StringBuilder stringBuilder;
        try {
            StringBuilder stringBuilder2 = new StringBuilder(str.trim());
            if ("plid-".equalsIgnoreCase(stringBuilder2.substring(0, 5))) {
                return Long.parseLong(stringBuilder2.substring(5, stringBuilder2.length()).trim());
            }
            internalLogLevel = InternalLogLevel.ERROR;
            str2 = a;
            stringBuilder = new StringBuilder("Invalid Placement id: ");
            stringBuilder.append(str);
            stringBuilder.append(" Placement id value supplied in XML layout is not valid. Please make sure placement id is in plid-0123456789 format.");
            Logger.a(internalLogLevel, str2, stringBuilder.toString());
            return Long.MIN_VALUE;
        } catch (NumberFormatException unused) {
            internalLogLevel = InternalLogLevel.ERROR;
            str2 = a;
            stringBuilder = new StringBuilder("Invalid Placement id: ");
            stringBuilder.append(str);
            stringBuilder.append(" Placement id value supplied in XML layout is not valid. Banner creation failed.");
            Logger.a(internalLogLevel, str2, stringBuilder.toString());
        } catch (StringIndexOutOfBoundsException unused2) {
            internalLogLevel = InternalLogLevel.ERROR;
            str2 = a;
            stringBuilder = new StringBuilder("Invalid Placement id: ");
            stringBuilder.append(str);
            stringBuilder.append(" Placement id value supplied in XML layout is not valid. Please make sure placement id is in plid-0123456789 format.");
            Logger.a(internalLogLevel, str2, stringBuilder.toString());
        }
    }

    public InMobiBanner(Context context, long j) {
        super(context);
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before creating a Banner ad");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Unable to create InMobiBanner ad with null context object.");
        } else {
            boolean z = context instanceof Activity;
            if (z) {
                this.s = new WeakReference((Activity) context);
            }
            this.d = new b(this);
            this.t = bi.a(j, null, "banner", null);
            this.t.f = z ? MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : MonetizationContext.MONETIZATION_CONTEXT_OTHER;
            a(context, this.t);
            this.i = true;
        }
    }

    private boolean b(boolean z) {
        if (!this.i) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiBanner is not initialized. Ignoring your call");
        } else if (!z || this.c != null) {
            return true;
        } else {
            Logger.a(InternalLogLevel.ERROR, a, "Listener supplied is null, Ignoring your call.");
        }
        return false;
    }

    public final void getSignals() {
        if (b(true)) {
            setEnableAutoRefresh(false);
            a("ARR", "");
            if (this.h != null) {
                this.h.A = getFrameSizeString();
                this.h.y = false;
                this.h.o();
            }
        }
    }

    public final void load(byte[] bArr) {
        int b = b(false) ^ 1;
        if (this.h == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please make sure getSignals is called before calling Load");
            return;
        }
        if (b == 0) {
            this.h.w = false;
            this.h.a(bArr);
        }
    }

    public final void load() {
        if (b(false)) {
            a(false);
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(final boolean z) {
        try {
            if (com.inmobi.commons.a.a.a()) {
                if (this.i) {
                    a("ARR", "");
                    if (this.g == null || !this.g.P()) {
                        if (!a()) {
                            if (getLayoutParams() == null) {
                                Logger.a(InternalLogLevel.ERROR, a, "The layout params of the banner must be set before calling load or call setBannerSize(int widthInDp, int heightInDp) before load");
                                this.w.a(new InMobiAdRequestStatus(StatusCode.REQUEST_INVALID));
                                return;
                            }
                            if (getLayoutParams().width != -2) {
                                if (getLayoutParams().height != -2) {
                                    g();
                                }
                            }
                            Logger.a(InternalLogLevel.ERROR, a, "The height or width of a Banner ad can't be WRAP_CONTENT or call setBannerSize(int widthInDp, int heightInDp) before load");
                            this.w.a(new InMobiAdRequestStatus(StatusCode.REQUEST_INVALID));
                            return;
                        }
                        if (a()) {
                            h();
                            if (f() && this.h != null) {
                                this.h.A = getFrameSizeString();
                                this.h.b(z);
                            }
                        } else {
                            new Handler().postDelayed(new Runnable() {
                                public final void run() {
                                    try {
                                        if (InMobiBanner.this.a()) {
                                            InMobiBanner.this.h();
                                            if (InMobiBanner.this.f() && InMobiBanner.this.h != null) {
                                                InMobiBanner.this.h.A = InMobiBanner.this.getFrameSizeString();
                                                InMobiBanner.this.h.b(z);
                                                return;
                                            }
                                        }
                                        Logger.a(InternalLogLevel.ERROR, InMobiBanner.a, "The height or width of the banner can not be determined");
                                        com.inmobi.ads.i.b d = InMobiBanner.this.w;
                                        InMobiBanner.this.h;
                                        d.a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                                    } catch (Exception e) {
                                        Logger.a(InternalLogLevel.ERROR, InMobiBanner.a, "SDK encountered unexpected error while loading an ad");
                                        InMobiBanner.a;
                                        new StringBuilder("InMobiBanner$4.run() threw unexpected error: ").append(e.getMessage());
                                    }
                                }
                            }, 200);
                            return;
                        }
                    }
                    Message obtain = Message.obtain();
                    obtain.what = 2;
                    obtain.obj = new InMobiAdRequestStatus(StatusCode.AD_ACTIVE);
                    a("ART", "LoadInProgress");
                    this.d.sendMessage(obtain);
                    this.g.b("AdActive");
                    Logger.a(InternalLogLevel.ERROR, a, "An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad.");
                    return;
                }
                return;
            }
            Logger.a(InternalLogLevel.ERROR, a, "InMobiBanner is not initialized. Ignoring InMobiBanner.load()");
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, a, "Unable to load ad; SDK encountered an unexpected error");
            new StringBuilder("Load failed with unexpected error: ").append(e.getMessage());
        }
    }

    public final void load(Context context) {
        if (b(false)) {
            boolean z = context instanceof Activity;
            if (z) {
                this.s = new WeakReference((Activity) context);
            } else {
                this.s = null;
            }
            if (this.t != null) {
                this.t.f = z ? MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : MonetizationContext.MONETIZATION_CONTEXT_OTHER;
                a(context, this.t);
            }
            a(false);
        }
    }

    private void setMonetizationContext(MonetizationContext monetizationContext) {
        if (this.e != null && this.f != null) {
            this.e.a(monetizationContext);
            this.f.a(monetizationContext);
        }
    }

    public static void requestAd(Context context, InMobiAdRequest inMobiAdRequest, BannerAdRequestListener bannerAdRequestListener) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before calling requestAd. Ignoring request");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please supply a non null Context. Aborting request");
        } else if (inMobiAdRequest == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please supply a non  null InMobiAdRequest. Ignoring request");
        } else if (bannerAdRequestListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please supply a non null BannerAdRequestListener. Ignoring request");
        } else if (inMobiAdRequest.c > 0 || inMobiAdRequest.d > 0) {
            AnonymousClass2 anonymousClass2 = new d() {
                public final void a(@NonNull i iVar) {
                    if (iVar instanceof p) {
                        try {
                            WeakReference weakReference = (WeakReference) InMobiBanner.q.get(iVar);
                            if (weakReference != null) {
                                InMobiBanner.q.remove(iVar);
                                BannerAdRequestListener bannerAdRequestListener = (BannerAdRequestListener) weakReference.get();
                                if (bannerAdRequestListener != null) {
                                    InMobiBanner inMobiBanner = new InMobiBanner(iVar.a(), iVar.d);
                                    inMobiBanner.setExtras(iVar.f);
                                    inMobiBanner.setKeywords(iVar.e);
                                    inMobiBanner.setMonetizationContext(iVar.l());
                                    bannerAdRequestListener.onAdRequestCompleted(new InMobiAdRequestStatus(StatusCode.NO_ERROR), inMobiBanner);
                                }
                            }
                        } catch (Exception e) {
                            InMobiBanner.a;
                            new StringBuilder("SDK encountered unexpected error in onAdPrefetchSucceeded ").append(e.getMessage());
                        }
                    }
                }

                public final void a(@NonNull i iVar, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                    try {
                        if (iVar instanceof p) {
                            WeakReference weakReference = (WeakReference) InMobiBanner.q.get(iVar);
                            if (weakReference != null) {
                                InMobiBanner.q.remove(iVar);
                                BannerAdRequestListener bannerAdRequestListener = (BannerAdRequestListener) weakReference.get();
                                if (bannerAdRequestListener != null) {
                                    bannerAdRequestListener.onAdRequestCompleted(inMobiAdRequestStatus, null);
                                }
                            }
                        }
                    } catch (Exception e) {
                        InMobiBanner.a;
                        new StringBuilder("SDK encountered unexpected error in onAdPrefetchFailed ").append(e.getMessage());
                    }
                }
            };
            try {
                bi a = bi.a(inMobiAdRequest.a, inMobiAdRequest.f, "banner", inMobiAdRequest.e);
                a.f = inMobiAdRequest.b;
                p a2 = p.a(context.getApplicationContext(), a, null, 2);
                a2.f = inMobiAdRequest.f;
                a2.a(inMobiAdRequest.b);
                a2.e = inMobiAdRequest.e;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(inMobiAdRequest.c);
                stringBuilder.append(AvidJSONUtil.KEY_X);
                stringBuilder.append(inMobiAdRequest.d);
                a2.A = stringBuilder.toString();
                a2.q = anonymousClass2;
                a2.n = true;
                q.put(a2, new WeakReference(bannerAdRequestListener));
                a2.q();
            } catch (Exception e) {
                new StringBuilder("SDK encountered unexpected error in requestAd").append(e.getMessage());
            }
        } else {
            Logger.a(InternalLogLevel.ERROR, a, "Please provide positive width and height for banner. Ignoring request");
        }
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final void setTrcCollector(j jVar) {
        this.r = jVar;
    }

    @NonNull
    private j getAdUnitTRCCollector() {
        if (this.r == null) {
            this.r = new k(this.h);
        }
        return this.r;
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final void a(String str, String str2) {
        getAdUnitTRCCollector().a(this.w, str, str2);
    }

    public final JSONObject getAdMetaInfo() {
        if (!this.i || this.g == null) {
            return new JSONObject();
        }
        return this.g.i;
    }

    private boolean f() {
        if (this.h == null) {
            return false;
        }
        if (this.p != 0) {
            int i = this.h.g.c;
            if (SystemClock.elapsedRealtime() - this.p < ((long) (i * 1000))) {
                p pVar = this.h;
                InMobiAdRequestStatus inMobiAdRequestStatus = new InMobiAdRequestStatus(StatusCode.EARLY_REFRESH_REQUEST);
                StringBuilder stringBuilder = new StringBuilder("Ad cannot be refreshed before ");
                stringBuilder.append(i);
                stringBuilder.append(" seconds");
                pVar.a(inMobiAdRequestStatus.setCustomMessage(stringBuilder.toString()), false);
                InternalLogLevel internalLogLevel = InternalLogLevel.ERROR;
                String str = a;
                stringBuilder = new StringBuilder("Ad cannot be refreshed before ");
                stringBuilder.append(i);
                stringBuilder.append(" seconds (Placement Id = ");
                stringBuilder.append(this.h.d);
                stringBuilder.append(")");
                Logger.a(internalLogLevel, str, stringBuilder.toString());
                return false;
            }
        }
        this.p = SystemClock.elapsedRealtime();
        return true;
    }

    public final void setExtras(Map<String, String> map) {
        if (this.i && this.t != null) {
            this.e.f = map;
            this.f.f = map;
        }
    }

    public final void setKeywords(String str) {
        if (this.i && this.t != null) {
            this.e.e = str;
            this.f.e = str;
        }
    }

    @Deprecated
    public final void setListener(BannerAdListener bannerAdListener) {
        if (bannerAdListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please pass a non-null listener to the banner.");
        } else {
            this.b = bannerAdListener;
        }
    }

    public final void setListener(BannerAdEventListener bannerAdEventListener) {
        if (bannerAdEventListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please pass a non-null listener to the banner.");
        } else {
            this.c = bannerAdEventListener;
        }
    }

    public final void setEnableAutoRefresh(boolean z) {
        try {
            if (this.i && this.k != z) {
                this.k = z;
                if (this.k) {
                    b();
                } else {
                    h();
                }
            }
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, a, "Unable to setup auto-refresh on the ad; SDK encountered an unexpected error");
            new StringBuilder("Setting up auto-refresh failed with unexpected error: ").append(e.getMessage());
        }
    }

    public final void setRefreshInterval(int i) {
        try {
            if (this.i && this.h != null) {
                if (i < this.h.g.c) {
                    i = this.h.g.c;
                }
                this.j = i;
            }
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, a, "Unable to set refresh interval for the ad; SDK encountered an unexpected error");
            new StringBuilder("Setting refresh interval failed with unexpected error: ").append(e.getMessage());
        }
    }

    public final void setAnimationType(AnimationType animationType) {
        if (this.i) {
            this.o = animationType;
        }
    }

    public final void disableHardwareAcceleration() {
        this.u = true;
    }

    /* Access modifiers changed, original: protected|final */
    public final void onAttachedToWindow() {
        try {
            super.onAttachedToWindow();
            if (this.i) {
                g();
                if (!a()) {
                    getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                        public final void onGlobalLayout() {
                            try {
                                InMobiBanner.this.m = c.b(InMobiBanner.this.getMeasuredWidth());
                                InMobiBanner.this.n = c.b(InMobiBanner.this.getMeasuredHeight());
                                if (InMobiBanner.this.a()) {
                                    if (VERSION.SDK_INT >= 16) {
                                        InMobiBanner.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                        return;
                                    }
                                    InMobiBanner.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                                }
                            } catch (Exception e) {
                                Logger.a(InternalLogLevel.ERROR, InMobiBanner.a, "InMobiBanner$1.onGlobalLayout() handler threw unexpected error");
                                InMobiBanner.a;
                                new StringBuilder("InMobiBanner$1.onGlobalLayout() handler threw unexpected error: ").append(e.getMessage());
                            }
                        }
                    });
                }
                b();
            }
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiBanner#onAttachedToWindow() handler threw unexpected error");
            new StringBuilder("InMobiBanner#onAttachedToWindow() handler threw unexpected error: ").append(e.getMessage());
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
            if (this.i) {
                h();
            }
            if (this.e != null) {
                this.e.S();
            }
            if (this.f != null) {
                this.f.S();
            }
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiBanner.onDetachedFromWindow() handler threw unexpected error");
            new StringBuilder("InMobiBanner.onDetachedFromWindow() handler threw unexpected error: ").append(e.getMessage());
        }
    }

    private void g() {
        if (getLayoutParams() != null) {
            this.m = c.b(getLayoutParams().width);
            this.n = c.b(getLayoutParams().height);
        }
    }

    public final void setBannerSize(int i, int i2) {
        if (this.i) {
            this.m = i;
            this.n = i2;
        }
    }

    /* Access modifiers changed, original: final */
    public final boolean a() {
        return this.m > 0 && this.n > 0;
    }

    /* Access modifiers changed, original: final */
    public final String getFrameSizeString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.m);
        stringBuilder.append(AvidJSONUtil.KEY_X);
        stringBuilder.append(this.n);
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: protected|final */
    public final void onVisibilityChanged(@NonNull View view, int i) {
        try {
            super.onVisibilityChanged(view, i);
            if (this.i) {
                if (i == 0) {
                    b();
                    return;
                }
                h();
            }
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiBanner$1.onVisibilityChanged() handler threw unexpected error");
            new StringBuilder("InMobiBanner$1.onVisibilityChanged() handler threw unexpected error: ").append(e.getMessage());
        }
    }

    public final void onWindowFocusChanged(boolean z) {
        try {
            super.onWindowFocusChanged(z);
            if (this.i) {
                if (z) {
                    b();
                    return;
                }
                h();
            }
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiBanner$1.onWindowFocusChanged() handler threw unexpected error");
            new StringBuilder("InMobiBanner$1.onWindowFocusChanged() handler threw unexpected error: ").append(e.getMessage());
        }
    }

    private void a(Context context, @NonNull bi biVar) {
        if (this.e == null || this.f == null) {
            this.e = p.a(context, biVar, this.w, 0);
            this.f = p.a(context, biVar, this.w, 0);
            this.h = this.e;
            this.j = this.h.g.d;
        } else {
            this.e.a(context);
            this.f.a(context);
            boolean z = context instanceof Activity;
            this.e.a(z ? MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : MonetizationContext.MONETIZATION_CONTEXT_OTHER);
            this.f.a(z ? MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : MonetizationContext.MONETIZATION_CONTEXT_OTHER);
        }
        this.l = new q(this);
        this.e.n = false;
        this.f.n = false;
        if (this.u) {
            this.e.O();
            this.f.O();
        }
    }

    /* Access modifiers changed, original: final */
    /* JADX WARNING: Missing block: B:23:0x0048, code skipped:
            return;
     */
    @android.support.annotation.VisibleForTesting
    public final void b() {
        /*
        r4 = this;
        r0 = r4.isShown();
        if (r0 == 0) goto L_0x0049;
    L_0x0006:
        r0 = r4.hasWindowFocus();
        if (r0 == 0) goto L_0x0049;
    L_0x000c:
        r0 = r4.h;
        if (r0 != 0) goto L_0x0011;
    L_0x0010:
        goto L_0x0049;
    L_0x0011:
        r0 = r4.l;
        r1 = 1;
        if (r0 == 0) goto L_0x001b;
    L_0x0016:
        r0 = r4.l;
        r0.removeMessages(r1);
    L_0x001b:
        r0 = r4.h;
        r0 = r0.a;
        if (r0 == r1) goto L_0x0048;
    L_0x0021:
        r0 = r4.h;
        r0 = r0.a;
        r2 = 2;
        if (r0 == r2) goto L_0x0048;
    L_0x0028:
        r0 = r4.g;
        if (r0 == 0) goto L_0x0035;
    L_0x002c:
        r0 = r4.g;
        r0 = r0.a;
        r2 = 8;
        if (r0 != r2) goto L_0x0035;
    L_0x0034:
        goto L_0x0048;
    L_0x0035:
        r0 = r4.k;
        if (r0 == 0) goto L_0x0047;
    L_0x0039:
        r0 = r4.l;
        if (r0 == 0) goto L_0x0047;
    L_0x003d:
        r0 = r4.l;
        r2 = r4.j;
        r2 = r2 * 1000;
        r2 = (long) r2;
        r0.sendEmptyMessageDelayed(r1, r2);
    L_0x0047:
        return;
    L_0x0048:
        return;
    L_0x0049:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.InMobiBanner.b():void");
    }

    private void h() {
        if (this.l != null) {
            this.l.removeMessages(1);
        }
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final void setAnimateAndDisplayAd(boolean z) {
        this.v = z;
    }

    public final void resume() {
        try {
            if (this.g != null && this.s == null) {
                this.g.R();
            }
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not resume ad; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in resuming ad; ").append(e.getMessage());
        }
    }

    public final void pause() {
        try {
            if (this.g != null && this.s == null) {
                this.g.Q();
            }
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not pause ad; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in pausing ad; ").append(e.getMessage());
        }
    }

    public final String getCreativeId() {
        return (!this.i || this.g == null) ? "" : this.g.x;
    }

    @VisibleForTesting
    static boolean c() {
        return Message.obtain() == null;
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final void setClientCallbackHandler(b bVar) {
        this.d = bVar;
    }
}
