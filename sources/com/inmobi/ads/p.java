package com.inmobi.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.view.View;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.c.a;
import com.inmobi.ads.i.b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.RenderView;
import java.util.Map;

public class p extends i implements ActivityLifecycleCallbacks {
    private static final String B = "p";
    private static final String C = InMobiBanner.class.getSimpleName();
    public String A;
    private boolean D;
    private int E = 0;
    public boolean y = true;
    boolean z = false;

    public final String b() {
        return "banner";
    }

    /* Access modifiers changed, original: protected|final */
    public final void b(a aVar) {
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @NonNull
    public static p a(@NonNull Context context, bi biVar, b bVar, int i) {
        i iVar = (i) a.a.get(biVar);
        p pVar = iVar instanceof p ? (p) iVar : null;
        if (pVar == null || 1 != i) {
            if (pVar == null) {
                new StringBuilder("Creating new adUnit for placement-ID : ").append(biVar.a);
                pVar = new p(context, biVar.a, bVar);
                if (i != 0) {
                    a.a.put(biVar, pVar);
                }
            } else {
                new StringBuilder("Found pre-fetching adUnit for placement-ID : ").append(biVar.a);
                pVar.a(context);
                a.a.remove(biVar);
                pVar.D = true;
            }
            pVar.a(bVar);
            pVar.a(biVar.f);
            return pVar;
        }
        throw new IllegalStateException("There's already a pre-loading going on for the same placementID");
    }

    private p(Context context, long j, b bVar) {
        super(context, j, bVar);
        if (context instanceof Activity) {
            ((Activity) context).getApplication().registerActivityLifecycleCallbacks(this);
        }
    }

    public final void a(Context context) {
        super.a(context);
        if (context instanceof Activity) {
            ((Activity) context).getApplication().registerActivityLifecycleCallbacks(this);
        }
    }

    /* Access modifiers changed, original: final */
    public final void O() {
        RenderView renderView = (RenderView) j();
        if (renderView != null) {
            this.z = true;
            renderView.a();
        }
    }

    public final void b(boolean z) {
        InternalLogLevel internalLogLevel;
        String str;
        StringBuilder stringBuilder;
        if (z) {
            internalLogLevel = InternalLogLevel.DEBUG;
            str = C;
            stringBuilder = new StringBuilder("Initiating Banner refresh for placement id: ");
            stringBuilder.append(this.d);
            Logger.a(internalLogLevel, str, stringBuilder.toString());
        }
        internalLogLevel = InternalLogLevel.DEBUG;
        str = C;
        stringBuilder = new StringBuilder("Fetching a Banner ad for placement id: ");
        stringBuilder.append(this.d);
        Logger.a(internalLogLevel, str, stringBuilder.toString());
        this.w = false;
        this.y = z;
        if (1 == this.a) {
            Logger.a(InternalLogLevel.ERROR, B, "An ad load is already in progress. Please wait for the load to complete before requesting for another ad");
            if (!this.D) {
                a(new InMobiAdRequestStatus(StatusCode.REQUEST_PENDING), false);
            }
        } else if (2 == this.a || 8 == this.a) {
            a(new InMobiAdRequestStatus(StatusCode.AD_ACTIVE), false);
            InternalLogLevel internalLogLevel2 = InternalLogLevel.ERROR;
            String str2 = C;
            StringBuilder stringBuilder2 = new StringBuilder("An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad for placement id: ");
            stringBuilder2.append(this.d);
            Logger.a(internalLogLevel2, str2, stringBuilder2.toString());
        } else {
            super.n();
        }
    }

    public final void q() {
        super.q();
    }

    /* Access modifiers changed, original: protected|final */
    @NonNull
    public final RenderView k() {
        RenderView k = super.k();
        if (this.z) {
            k.a();
        }
        return k;
    }

    public final String c() {
        return this.A;
    }

    /* Access modifiers changed, original: protected|final */
    public final PlacementType d() {
        return PlacementType.PLACEMENT_TYPE_INLINE;
    }

    /* Access modifiers changed, original: protected|final */
    @NonNull
    public final Map<String, String> e() {
        Map e = super.e();
        e.put("u-rt", this.y ? "1" : "0");
        e.put("mk-ad-slot", this.A);
        return e;
    }

    @UiThread
    public final void c(long j, @NonNull a aVar) {
        try {
            super.c(j, aVar);
            InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
            String str = C;
            StringBuilder stringBuilder = new StringBuilder("Banner ad fetch successful for placement id: ");
            stringBuilder.append(this.d);
            Logger.a(internalLogLevel, str, stringBuilder.toString());
            if (j == this.d && this.a == 2) {
                a(false, k());
                try {
                    InternalLogLevel internalLogLevel2 = InternalLogLevel.DEBUG;
                    String str2 = C;
                    StringBuilder stringBuilder2 = new StringBuilder("Started loading banner ad markup in WebView for placement id: ");
                    stringBuilder2.append(this.d);
                    Logger.a(internalLogLevel2, str2, stringBuilder2.toString());
                    a(null, this.h, null, null);
                } catch (Exception e) {
                    C();
                    if (f() != null) {
                        f().a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                    }
                    Logger.a(InternalLogLevel.ERROR, C, "Unable to load ad; SDK encountered an internal error");
                    new StringBuilder("Loading ad markup into container encountered an unexpected error: ").append(e.getMessage());
                }
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, C, "Unable to load ad; SDK encountered an internal error");
            new StringBuilder("Handling ad fetch successful encountered an unexpected error: ").append(e2.getMessage());
        }
    }

    public final void a(RenderView renderView) {
        try {
            super.a(renderView);
            if (this.a == 2) {
                C();
                this.a = 4;
                F();
                InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
                String str = C;
                StringBuilder stringBuilder = new StringBuilder("Successfully loaded Banner ad markup in the WebView for placement id: ");
                stringBuilder.append(this.d);
                Logger.a(internalLogLevel, str, stringBuilder.toString());
                if (f() != null) {
                    f().a();
                }
                s();
            }
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, C, "Unable to load ad; SDK encountered an internal error");
            new StringBuilder("Loading ad markup into container encountered an unexpected error: ").append(e.getMessage());
        }
    }

    public final void b(RenderView renderView) {
        try {
            super.b(renderView);
            if (this.a == 4) {
                this.a = 7;
                d("AdRendered");
            }
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, C, "Unable to load ad; SDK encountered an internal error");
            new StringBuilder("BannerAdUnit.onRenderViewVisible threw unexpected error: ").append(e.getMessage());
        }
    }

    /* JADX WARNING: Missing block: B:14:0x0044, code skipped:
            return;
     */
    public final synchronized void c(com.inmobi.rendering.RenderView r5) {
        /*
        r4 = this;
        monitor-enter(r4);
        super.c(r5);	 Catch:{ Exception -> 0x0047 }
        r5 = r4.a;	 Catch:{ Exception -> 0x0047 }
        r0 = 7;
        r1 = 8;
        if (r5 != r0) goto L_0x0039;
    L_0x000b:
        r5 = r4.E;	 Catch:{ Exception -> 0x0047 }
        r5 = r5 + 1;
        r4.E = r5;	 Catch:{ Exception -> 0x0047 }
        r4.a = r1;	 Catch:{ Exception -> 0x0047 }
        r5 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.DEBUG;	 Catch:{ Exception -> 0x0047 }
        r0 = C;	 Catch:{ Exception -> 0x0047 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0047 }
        r2 = "Successfully displayed banner ad for placement Id : ";
        r1.<init>(r2);	 Catch:{ Exception -> 0x0047 }
        r2 = r4.d;	 Catch:{ Exception -> 0x0047 }
        r1.append(r2);	 Catch:{ Exception -> 0x0047 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0047 }
        com.inmobi.commons.core.utilities.Logger.a(r5, r0, r1);	 Catch:{ Exception -> 0x0047 }
        r5 = r4.f();	 Catch:{ Exception -> 0x0047 }
        if (r5 == 0) goto L_0x0043;
    L_0x0030:
        r5 = r4.f();	 Catch:{ Exception -> 0x0047 }
        r5.d();	 Catch:{ Exception -> 0x0047 }
        monitor-exit(r4);
        return;
    L_0x0039:
        r5 = r4.a;	 Catch:{ Exception -> 0x0047 }
        if (r5 != r1) goto L_0x0043;
    L_0x003d:
        r5 = r4.E;	 Catch:{ Exception -> 0x0047 }
        r5 = r5 + 1;
        r4.E = r5;	 Catch:{ Exception -> 0x0047 }
    L_0x0043:
        monitor-exit(r4);
        return;
    L_0x0045:
        r5 = move-exception;
        goto L_0x0061;
    L_0x0047:
        r5 = move-exception;
        r0 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.ERROR;	 Catch:{ all -> 0x0045 }
        r1 = C;	 Catch:{ all -> 0x0045 }
        r2 = "Unable to display ad; SDK encountered an internal error";
        com.inmobi.commons.core.utilities.Logger.a(r0, r1, r2);	 Catch:{ all -> 0x0045 }
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0045 }
        r1 = "BannerAdUnit.onAdScreenDisplayed threw unexpected error: ";
        r0.<init>(r1);	 Catch:{ all -> 0x0045 }
        r5 = r5.getMessage();	 Catch:{ all -> 0x0045 }
        r0.append(r5);	 Catch:{ all -> 0x0045 }
        monitor-exit(r4);
        return;
    L_0x0061:
        monitor-exit(r4);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.p.c(com.inmobi.rendering.RenderView):void");
    }

    public final synchronized void d(RenderView renderView) {
        try {
            super.d(renderView);
            if (this.a == 8) {
                int i = this.E - 1;
                this.E = i;
                if (i == 0) {
                    this.a = 7;
                    if (f() != null) {
                        f().e();
                    }
                }
            }
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, C, "Unable to dismiss ad; SDK encountered an internal error");
            new StringBuilder("BannerAdUnit.onAdScreenDismissed threw unexpected error: ").append(e.getMessage());
        }
    }

    public final void S() {
        if (a() instanceof Activity) {
            ((Activity) a()).getApplication().unregisterActivityLifecycleCallbacks(this);
        }
    }

    public void onActivityStarted(Activity activity) {
        Context a = a();
        if (a != null && a.equals(activity)) {
            R();
        }
    }

    public void onActivityStopped(Activity activity) {
        Context a = a();
        if (a != null && a.equals(activity)) {
            Q();
        }
    }

    public void onActivityDestroyed(Activity activity) {
        Context a = a();
        if (a != null && a.equals(activity)) {
            ((Activity) a).getApplication().unregisterActivityLifecycleCallbacks(this);
            v();
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final int r() {
        InternalLogLevel internalLogLevel;
        if (1 == this.a || 2 == this.a) {
            this.s.post(new Runnable() {
                public final void run() {
                    p.this.a(new InMobiAdRequestStatus(StatusCode.REQUEST_PENDING), false);
                }
            });
            internalLogLevel = InternalLogLevel.ERROR;
            String str = C;
            StringBuilder stringBuilder = new StringBuilder("An ad load is already in progress. Please wait for the load to complete before requesting for another ad for placement id: ");
            stringBuilder.append(this.d);
            Logger.a(internalLogLevel, str, stringBuilder.toString());
            return 2;
        } else if (this.a != 8) {
            return super.r();
        } else {
            this.s.post(new Runnable() {
                public final void run() {
                    p.this.a(new InMobiAdRequestStatus(StatusCode.AD_ACTIVE), false);
                }
            });
            internalLogLevel = InternalLogLevel.ERROR;
            String str2 = C;
            StringBuilder stringBuilder2 = new StringBuilder("An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad for placement id: ");
            stringBuilder2.append(this.d);
            Logger.a(internalLogLevel, str2, stringBuilder2.toString());
            return 3;
        }
    }

    public final void H() {
        if (1 == this.a) {
            this.a = 9;
            if (!this.n) {
                this.D = false;
                b(false);
            } else if (this.q != null) {
                this.q.a(this);
            }
        }
    }

    public final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (1 == this.a) {
            this.a = 3;
            if (!this.n) {
                b f = f();
                if (f != null) {
                    this.D = false;
                    a(f, "VAR", "");
                    a(f, "ARN", "");
                    f.a(inMobiAdRequestStatus);
                    return;
                }
                if (this.q != null) {
                    this.q.a(this, inMobiAdRequestStatus);
                }
            } else if (this.q != null) {
                this.q.a(this, inMobiAdRequestStatus);
            }
        }
    }

    /* Access modifiers changed, original: final */
    public final boolean P() {
        return this.a == 8;
    }

    public final void Q() {
        int i = this.a;
        if (i == 4 || i == 7 || i == 8) {
            AdContainer j = j();
            if (j != null) {
                ca viewableAd = j.getViewableAd();
                if (viewableAd != null) {
                    viewableAd.d();
                }
            }
        }
    }

    public final void R() {
        int i = this.a;
        if (i == 4 || i == 7 || i == 8) {
            AdContainer j = j();
            if (j != null) {
                ca viewableAd = j.getViewableAd();
                if (viewableAd != null) {
                    viewableAd.a(new View[0]);
                }
            }
        }
    }
}
