package com.inmobi.ads;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import com.google.api.client.http.HttpStatusCodes;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.b.e;
import com.inmobi.commons.core.utilities.d;
import com.inmobi.rendering.InMobiAdActivity;
import com.inmobi.rendering.RenderView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ac extends i {
    private static final String A = InMobiInterstitial.class.getSimpleName();
    private static final String z = "ac";
    private int B;
    private ArrayList<WeakReference<com.inmobi.ads.i.b>> C;
    boolean y;

    public static final class a {
        static final Map<bi, ac> a = new HashMap();

        @NonNull
        static ac b(Context context, bi biVar, com.inmobi.ads.i.b bVar) {
            return new ac(context, biVar.a, bVar, (byte) 0);
        }

        @NonNull
        public static ac a(Context context, bi biVar, com.inmobi.ads.i.b bVar) {
            long j = biVar.a;
            ac acVar = (ac) a.get(biVar);
            if (acVar != null) {
                if (acVar.h()) {
                    ac.z;
                    StringBuilder stringBuilder = new StringBuilder("Found expired adUnit for placement(");
                    stringBuilder.append(j);
                    stringBuilder.append("), thus clearing it.");
                    acVar.v();
                }
                acVar.a(context);
                if (bVar != null) {
                    acVar.a(bVar);
                }
                return acVar;
            }
            acVar = new ac(context, j, bVar, (byte) 0);
            a.put(biVar, acVar);
            return acVar;
        }
    }

    private final class b extends Exception {
        public b(String str) {
            super(str);
        }
    }

    private final class c extends Exception {
        public c(String str) {
            super(str);
        }
    }

    public final String b() {
        return "int";
    }

    public final String c() {
        return null;
    }

    public final void n() {
    }

    /* synthetic */ ac(Context context, long j, com.inmobi.ads.i.b bVar, byte b) {
        this(context, j, bVar);
    }

    private ac(Context context, long j, com.inmobi.ads.i.b bVar) {
        super(context, j, bVar);
        this.B = 0;
        this.y = false;
        this.C = new ArrayList(1);
        MonetizationContext monetizationContext = MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY;
        super.a(MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
    }

    /* Access modifiers changed, original: protected|final */
    @NonNull
    public final RenderView k() {
        RenderView k = super.k();
        if (this.y) {
            k.a();
        }
        return k;
    }

    /* Access modifiers changed, original: final */
    public final boolean d(com.inmobi.ads.i.b bVar) {
        if (i.m()) {
            a("MissingDependency");
            a(new InMobiAdRequestStatus(StatusCode.MISSING_REQUIRED_DEPENDENCIES), true);
            return false;
        }
        this.w = false;
        if (bVar == null) {
            g();
            return false;
        } else if (-1 == g(bVar)) {
            this.C.add(new WeakReference(bVar));
            if (d.a()) {
                boolean z;
                InternalLogLevel internalLogLevel;
                String str;
                StringBuilder stringBuilder;
                switch (this.a) {
                    case 1:
                        internalLogLevel = InternalLogLevel.ERROR;
                        str = A;
                        stringBuilder = new StringBuilder("An ad load is already in progress. Please wait for the load to complete before requesting for another ad for placement id: ");
                        stringBuilder.append(this.d);
                        Logger.a(internalLogLevel, str, stringBuilder.toString());
                        break;
                    case 2:
                        if (!"html".equals(this.m)) {
                            if (bVar != null) {
                                bVar.a(true);
                                break;
                            }
                        }
                        internalLogLevel = InternalLogLevel.ERROR;
                        str = A;
                        stringBuilder = new StringBuilder("An ad load is already in progress. Please wait for the load to complete before requesting for another ad for placement id: ");
                        stringBuilder.append(this.d);
                        Logger.a(internalLogLevel, str, stringBuilder.toString());
                        break;
                        break;
                    case 4:
                        if (bVar != null) {
                            bVar.a(true);
                            break;
                        }
                        break;
                    case 7:
                    case 8:
                        InternalLogLevel internalLogLevel2 = InternalLogLevel.ERROR;
                        String str2 = A;
                        StringBuilder stringBuilder2 = new StringBuilder("An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad for placement id: ");
                        stringBuilder2.append(this.d);
                        Logger.a(internalLogLevel2, str2, stringBuilder2.toString());
                        InMobiAdRequestStatus inMobiAdRequestStatus = new InMobiAdRequestStatus(StatusCode.AD_ACTIVE);
                        a(inMobiAdRequestStatus);
                        int g = g(bVar);
                        if (g != -1) {
                            this.C.remove(g);
                        }
                        if (bVar != null) {
                            bVar.a(inMobiAdRequestStatus);
                            break;
                        }
                        break;
                    default:
                        z = false;
                        break;
                }
                z = true;
                if (!z) {
                    return true;
                }
                d("AdLoadRequested");
                return false;
            }
            a(new InMobiAdRequestStatus(StatusCode.NETWORK_UNREACHABLE), true);
            return false;
        } else {
            a(bVar, "ART", "LoadInProgress");
            return false;
        }
    }

    @UiThread
    public final void e(com.inmobi.ads.i.b bVar) {
        if (d(bVar)) {
            super.n();
        }
    }

    @UiThread
    private int g(com.inmobi.ads.i.b bVar) {
        int i = -1;
        for (int i2 = 0; i2 < this.C.size(); i2++) {
            WeakReference weakReference = (WeakReference) this.C.get(i2);
            if (weakReference != null) {
                com.inmobi.ads.i.b bVar2 = (com.inmobi.ads.i.b) weakReference.get();
                if (bVar2 != null && bVar2.equals(bVar)) {
                    i = i2;
                }
            }
        }
        return i;
    }

    private void S() {
        this.s.post(new Runnable() {
            public final void run() {
                for (int i = 0; i < ac.this.C.size(); i++) {
                    com.inmobi.ads.i.b bVar = (com.inmobi.ads.i.b) ((WeakReference) ac.this.C.get(i)).get();
                    if (bVar == null) {
                        ac.this.g();
                    } else {
                        ac.this.a(bVar, "VAR", "");
                        ac.this.a(bVar, "ARF", "");
                        bVar.a(true);
                        bVar.a();
                        bVar.a(ac.this);
                    }
                }
                ac.this.C.clear();
            }
        });
    }

    public final boolean a(a aVar) {
        if (super.a(aVar)) {
            if (aVar instanceof bc) {
                bc bcVar = (bc) aVar;
                com.inmobi.ads.cache.d.a();
                com.inmobi.ads.cache.a b = com.inmobi.ads.cache.d.b(bcVar.l);
                if (b == null || !b.a()) {
                    return false;
                }
                this.j = new bx(b.e, bcVar.m, bcVar.n, bcVar.h(), bcVar.i(), this.g.m);
            }
            return true;
        }
        b(aVar);
        return false;
    }

    /* Access modifiers changed, original: protected|final */
    public final void b(a aVar) {
        i().a(aVar);
    }

    public final void v() {
        super.v();
    }

    /* Access modifiers changed, original: final */
    public final void f(com.inmobi.ads.i.b bVar) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            a(bVar, "AVRR", "");
            Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Please ensure that you call show() on the UI thread");
        } else if (bVar == null) {
            g();
        } else if (!O()) {
            a(bVar, "AVRR", "");
            Logger.a(InternalLogLevel.ERROR, z, "Ad Load is not complete. Please wait for the Ad to be in a ready state before calling show.");
            Map hashMap = new HashMap();
            hashMap.put("errorCode", "ShowIntBeforeReady");
            c("AdShowFailed", hashMap);
            bVar.b();
        } else if (e.e()) {
            a(bVar);
            this.a = 7;
            if ("html".equals(this.m)) {
                AdContainer j = j();
                if (h()) {
                    i(bVar);
                    if (j != null) {
                        j.destroy();
                        return;
                    }
                }
                h(bVar);
                return;
            }
            final WeakReference weakReference = new WeakReference(bVar);
            this.p.execute(new Runnable() {
                public final void run() {
                    final com.inmobi.ads.i.b bVar = (com.inmobi.ads.i.b) weakReference.get();
                    if (bVar != null) {
                        try {
                            if (ac.this.b(false)) {
                                ac.this.a(bVar, ac.this.h, new Runnable() {
                                    public final void run() {
                                        AdContainer j = ac.this.j();
                                        RenderView renderView = ac.this.u;
                                        if (renderView != null) {
                                            if (j instanceof ah) {
                                                ah ahVar = (ah) j;
                                                ahVar.w = renderView;
                                                ahVar.y = ac.this.r;
                                            } else {
                                                ac.this.i(bVar);
                                            }
                                        }
                                        ac.this.h(bVar);
                                    }
                                }, Looper.getMainLooper());
                            } else {
                                Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Unable to Show Ad, canShowAd Failed");
                                ac.this.i(bVar);
                            }
                        } catch (b e) {
                            Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), e.getMessage());
                            ac.this.i(bVar);
                        } catch (c e2) {
                            Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), e2.getMessage());
                            ac.this.i(bVar);
                        }
                    }
                }
            });
        } else {
            super.v();
            bVar.b();
        }
    }

    @UiThread
    private void h(com.inmobi.ads.i.b bVar) {
        d("ShowInt");
        boolean T = T();
        if (bVar == null) {
            g();
        } else if (T) {
            bVar.c();
        } else {
            this.a = 3;
            a(bVar, "AVRR", "");
            bVar.b();
        }
    }

    private boolean T() {
        try {
            StringBuilder stringBuilder = new StringBuilder(">>> Starting ");
            stringBuilder.append(InMobiAdActivity.class.getSimpleName());
            stringBuilder.append(" to display interstitial ad ...");
            AdContainer j = j();
            if (j != null) {
                if (!"unknown".equals(j.getMarkupType())) {
                    int a = InMobiAdActivity.a(j);
                    Intent intent = new Intent(a(), InMobiAdActivity.class);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX", a);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 102);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_TYPE", "html".equals(this.m) ? 200 : HttpStatusCodes.STATUS_CODE_CREATED);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_IS_FULL_SCREEN", true);
                    com.inmobi.commons.a.a.a(a(), intent);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Cannot show ad; SDK encountered an unexpected error");
            new StringBuilder("Encountered unexpected error while showing ad: ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return false;
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final PlacementType d() {
        return PlacementType.PLACEMENT_TYPE_FULLSCREEN;
    }

    public final void a(final long j, final InMobiAdRequestStatus inMobiAdRequestStatus) {
        this.s.post(new Runnable() {
            public final void run() {
                try {
                    if (j == ac.this.d) {
                        InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
                        String Q = ac.A;
                        StringBuilder stringBuilder = new StringBuilder("Failed to fetch ad for placement id: ");
                        stringBuilder.append(j);
                        stringBuilder.append(", reason phrase available in onAdLoadFailed callback.");
                        Logger.a(internalLogLevel, Q, stringBuilder.toString());
                        for (int i = 0; i < ac.this.C.size(); i++) {
                            com.inmobi.ads.i.b bVar = (com.inmobi.ads.i.b) ((WeakReference) ac.this.C.get(i)).get();
                            if (bVar == null) {
                                ac.this.g();
                            } else {
                                if (i < ac.this.C.size() - 1) {
                                    ac.this.a(bVar, "VAR", "");
                                }
                                ac.this.a(bVar, "ARN", "");
                            }
                        }
                        ac.this.a(inMobiAdRequestStatus, true);
                    }
                } catch (Exception e) {
                    Logger.a(InternalLogLevel.ERROR, "[InMobi]", "Unable to load Ad; SDK encountered an unexpected error");
                    ac.z;
                    new StringBuilder("onAdFetchFailed with error: ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        });
    }

    @UiThread
    public final void c(long j, @NonNull a aVar) {
        try {
            super.c(j, aVar);
            InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
            String str = A;
            StringBuilder stringBuilder = new StringBuilder("Interstitial ad successfully fetched for placement id: ");
            stringBuilder.append(this.d);
            Logger.a(internalLogLevel, str, stringBuilder.toString());
            if (j == this.d && this.a == 2) {
                a(true, k());
                try {
                    a(null, this.h, null, null);
                } catch (Exception e) {
                    C();
                    a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), false);
                    Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Unable to load ad; SDK encountered an internal error");
                    new StringBuilder("Loading ad markup into container encountered an unexpected error: ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, A, "Unable to load ad; SDK encountered an internal error");
            new StringBuilder("Handling ad fetch successful encountered an unexpected error: ").append(e2.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
    }

    @UiThread
    public final void a(long j, boolean z, @NonNull a aVar) {
        try {
            super.a(j, z, aVar);
            if (j == this.d) {
                Iterator it;
                com.inmobi.ads.i.b bVar;
                if (1 == this.a && z) {
                    this.a = 2;
                    if (super.a(aVar)) {
                        a(f(), "ARF", "");
                        c(aVar);
                        if (aVar.j) {
                            this.t = true;
                            I();
                            return;
                        }
                        it = this.C.iterator();
                        while (it.hasNext()) {
                            bVar = (com.inmobi.ads.i.b) ((WeakReference) it.next()).get();
                            if (bVar != null) {
                                bVar.a(true);
                            } else {
                                g();
                            }
                        }
                        return;
                    }
                    it = this.C.iterator();
                    while (it.hasNext()) {
                        com.inmobi.ads.i.b bVar2 = (com.inmobi.ads.i.b) ((WeakReference) it.next()).get();
                        if (bVar2 != null) {
                            bVar2.a(false);
                        } else {
                            g();
                        }
                    }
                } else if (4 == this.a || 5 == this.a || 2 == this.a) {
                    this.a = 0;
                    it = this.C.iterator();
                    while (it.hasNext()) {
                        bVar = (com.inmobi.ads.i.b) ((WeakReference) it.next()).get();
                        if (bVar != null) {
                            bVar.a(new InMobiAdRequestStatus(StatusCode.AD_NO_LONGER_AVAILABLE));
                        } else {
                            g();
                        }
                        this.C.clear();
                    }
                }
            }
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Unable to load ad; SDK encountered an internal error");
            new StringBuilder("Handling ad availability change event encountered an unexpected error: ").append(e.getMessage());
        }
    }

    @UiThread
    public final void b(long j, boolean z) {
        super.b(j, z);
        if (z) {
            if (j == this.d && 2 == this.a) {
                if (this.t) {
                    this.v = true;
                    J();
                    return;
                }
                K();
            }
        } else if (j == this.d && (2 == this.a || 5 == this.a)) {
            this.a = 0;
            a(new InMobiAdRequestStatus(StatusCode.AD_NO_LONGER_AVAILABLE), false);
        }
    }

    /* Access modifiers changed, original: final */
    public final void K() {
        F();
        this.a = 5;
        for (int i = 0; i < this.C.size(); i++) {
            com.inmobi.ads.i.b bVar = (com.inmobi.ads.i.b) ((WeakReference) this.C.get(i)).get();
            if (bVar == null) {
                g();
            } else {
                if (i < this.C.size() - 1) {
                    a(bVar, "VAR", "");
                    a(bVar, "ARF", "");
                }
                bVar.a((i) this);
            }
        }
        this.C.clear();
    }

    public final void x() {
        super.x();
        if (this.a == 4) {
            C();
            InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
            String str = A;
            StringBuilder stringBuilder = new StringBuilder("Successfully loaded Interstitial ad markup in the WebView for placement id: ");
            stringBuilder.append(this.d);
            Logger.a(internalLogLevel, str, stringBuilder.toString());
            s();
            K();
        }
    }

    public final void a(RenderView renderView) {
        super.a(renderView);
        if (this.a == 2) {
            this.a = 4;
            L();
        }
    }

    public final void z() {
        super.z();
        if (this.a == 4) {
            C();
            this.a = 3;
            InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
            String str = A;
            StringBuilder stringBuilder = new StringBuilder("Failed to load the Interstitial markup in the WebView for placement id: ");
            stringBuilder.append(this.d);
            Logger.a(internalLogLevel, str, stringBuilder.toString());
            a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), false);
        }
    }

    public final synchronized void c(RenderView renderView) {
        super.c(renderView);
        b(f());
    }

    public final synchronized void d(RenderView renderView) {
        super.d(renderView);
        c(f());
    }

    public final void E() {
        b("RenderTimeOut");
        if (this.k != null) {
            i().a(this.k);
        }
        if (4 == this.a || 2 == this.a) {
            this.a = 3;
            InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
            String str = z;
            StringBuilder stringBuilder = new StringBuilder("Failed to load the Interstitial markup in the webview due to time out for placement id: ");
            stringBuilder.append(this.d);
            Logger.a(internalLogLevel, str, stringBuilder.toString());
            a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), false);
        }
    }

    /* Access modifiers changed, original: final */
    public final void L() {
        Iterator it = this.C.iterator();
        while (it.hasNext()) {
            com.inmobi.ads.i.b bVar = (com.inmobi.ads.i.b) ((WeakReference) it.next()).get();
            if (bVar != null) {
                bVar.a(true);
            } else {
                g();
            }
        }
    }

    private void i(final com.inmobi.ads.i.b bVar) {
        a(bVar, "AVFB", "");
        this.s.post(new Runnable() {
            public final void run() {
                ac.this.a = 0;
                if (bVar != null) {
                    bVar.b();
                } else {
                    ac.this.g();
                }
            }
        });
    }

    public final void a(MonetizationContext monetizationContext) {
        super.a(MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
    }

    public final MonetizationContext l() {
        return MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY;
    }

    /* Access modifiers changed, original: final */
    public final void q() {
        this.s.post(new Runnable() {
            public final void run() {
                try {
                    ac.this.w = false;
                    if (ac.this.p()) {
                        ac.this.c("IllegalState");
                    } else {
                        super.q();
                    }
                } catch (Exception e) {
                    Logger.a(InternalLogLevel.ERROR, ac.A, "Unable to Prefetch ad; SDK encountered an unexpected error");
                    ac.z;
                    new StringBuilder("Prefetch failed with unexpected error: ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        });
    }

    /* Access modifiers changed, original: protected|final */
    public final int r() {
        if (1 == this.a) {
            InternalLogLevel internalLogLevel = InternalLogLevel.ERROR;
            String str = A;
            StringBuilder stringBuilder = new StringBuilder("An ad load is already in progress. Please wait for the load to complete before requesting for another ad for placement id: ");
            stringBuilder.append(this.d);
            Logger.a(internalLogLevel, str, stringBuilder.toString());
            return 2;
        } else if (5 != this.a) {
            return super.r();
        } else {
            if (R()) {
                return super.r();
            }
            return 1;
        }
    }

    private boolean R() {
        try {
            if ("html".equals(this.m)) {
                if (h()) {
                    super.v();
                    return true;
                }
                S();
                return false;
            } else if (!b(true)) {
                return true;
            } else {
                S();
                return false;
            }
        } catch (b unused) {
            return true;
        } catch (c unused2) {
            return true;
        }
    }

    public final boolean O() {
        return this.a == 5;
    }

    /* Access modifiers changed, original: final */
    @UiThread
    public final void a(InMobiAdRequestStatus inMobiAdRequestStatus, boolean z) {
        if (this.a == 1 && z) {
            this.a = 3;
        }
        Iterator it = this.C.iterator();
        while (it.hasNext()) {
            com.inmobi.ads.i.b bVar = (com.inmobi.ads.i.b) ((WeakReference) it.next()).get();
            if (bVar != null) {
                bVar.a(inMobiAdRequestStatus);
            } else {
                g();
            }
        }
        this.C.clear();
        a(inMobiAdRequestStatus);
        super.v();
    }

    /* Access modifiers changed, original: final */
    public final void b(com.inmobi.ads.i.b bVar) {
        if (this.a == 7) {
            this.B++;
            if (this.B == 1) {
                d("AdRendered");
                InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
                String str = A;
                StringBuilder stringBuilder = new StringBuilder("Successfully displayed Interstitial for placement id: ");
                stringBuilder.append(this.d);
                Logger.a(internalLogLevel, str, stringBuilder.toString());
                if (bVar != null) {
                    bVar.d();
                    return;
                } else {
                    g();
                    return;
                }
            }
            this.a = 8;
            return;
        }
        if (this.a == 8) {
            this.B++;
        }
    }

    /* Access modifiers changed, original: final */
    public final void c(com.inmobi.ads.i.b bVar) {
        if (this.a == 8) {
            this.B--;
            if (this.B == 1) {
                this.a = 7;
            }
        } else if (this.a == 7) {
            this.B--;
            d("IntClosed");
            super.v();
            InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
            String str = A;
            StringBuilder stringBuilder = new StringBuilder("Interstitial ad dismissed for placement id: ");
            stringBuilder.append(this.d);
            Logger.a(internalLogLevel, str, stringBuilder.toString());
            if (bVar != null) {
                bVar.e();
                return;
            }
            g();
        }
    }

    private boolean b(boolean z) throws b, c {
        String str = this.k;
        if (str == null) {
            return false;
        }
        a c;
        if (z) {
            i();
            b.b();
            c = d.c(str);
        } else {
            h i = i();
            h.c();
            d dVar = i.b;
            a c2 = d.c(str);
            if (c2 != null) {
                d.a(str);
            }
            i.a(i.c);
            c = c2;
        }
        if (c == null) {
            throw new b("No Cached Ad found for AdUnit");
        } else if (a(c)) {
            return true;
        } else {
            throw new c("No Cached Asset for AdUnit");
        }
    }

    @UiThread
    public final void H() {
        if (1 == this.a) {
            this.a = 9;
            if (this.q != null) {
                this.q.a(this);
            }
            Iterator it = this.C.iterator();
            while (it.hasNext()) {
                com.inmobi.ads.i.b bVar = (com.inmobi.ads.i.b) ((WeakReference) it.next()).get();
                if (bVar != null) {
                    e(bVar);
                    return;
                }
                g();
            }
        }
    }

    @UiThread
    public final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (1 == this.a) {
            this.a = 3;
            if (this.q != null) {
                this.q.a(this, inMobiAdRequestStatus);
            }
            if (this.C.size() > 0) {
                a(inMobiAdRequestStatus, false);
            }
        }
    }
}
