package com.inmobi.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.i.b;
import com.inmobi.ads.i.d;
import com.inmobi.ads.listeners.InterstitialAdEventListener;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.squareup.picasso.Picasso;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

public final class InMobiInterstitial {
    private static final String a = "InMobiInterstitial";
    private static ConcurrentHashMap<ac, ArrayList<WeakReference<InterstitialAdRequestListener>>> m = new ConcurrentHashMap(2, 0.9f, 3);
    @Nullable
    private ac b;
    private a c;
    private InterstitialAdListener2 d;
    private InterstitialAdEventListener e;
    private Context f;
    private long g;
    private boolean h;
    private String i;
    private Map<String, String> j;
    private boolean k;
    private boolean l;
    private String n;
    private j o;
    private JSONObject p;
    @NonNull
    private final b q;

    public interface InterstitialAdListener2 {
        void onAdDismissed(InMobiInterstitial inMobiInterstitial);

        void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial);

        void onAdDisplayed(InMobiInterstitial inMobiInterstitial);

        void onAdInteraction(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map);

        void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial);

        void onAdReceived(InMobiInterstitial inMobiInterstitial);

        void onAdRewardActionCompleted(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map);

        void onAdWillDisplay(InMobiInterstitial inMobiInterstitial);

        void onUserLeftApplication(InMobiInterstitial inMobiInterstitial);
    }

    public interface InterstitialAdRequestListener {
        void onAdRequestCompleted(InMobiAdRequestStatus inMobiAdRequestStatus, InMobiInterstitial inMobiInterstitial);
    }

    @VisibleForTesting
    static final class a extends Handler {
        private WeakReference<InMobiInterstitial> a;

        a(InMobiInterstitial inMobiInterstitial) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(inMobiInterstitial);
        }

        public final void handleMessage(Message message) {
            InMobiInterstitial inMobiInterstitial = (InMobiInterstitial) this.a.get();
            if (inMobiInterstitial != null) {
                try {
                    Map map = null;
                    switch (message.what) {
                        case 1:
                            InMobiAdRequestStatus inMobiAdRequestStatus = (InMobiAdRequestStatus) message.obj;
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                                return;
                            }
                            break;
                        case 2:
                            if (message.getData().getBoolean("available")) {
                                if (inMobiInterstitial.e != null) {
                                    inMobiInterstitial.e.onAdReceived(inMobiInterstitial);
                                }
                                if (inMobiInterstitial.d != null) {
                                    inMobiInterstitial.d.onAdReceived(inMobiInterstitial);
                                    return;
                                }
                            }
                            break;
                        case 3:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdLoadSucceeded(inMobiInterstitial);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdLoadSucceeded(inMobiInterstitial);
                                return;
                            }
                            break;
                        case 4:
                            if (message.obj != null) {
                                map = (Map) message.obj;
                            }
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onRewardsUnlocked(inMobiInterstitial, map);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdRewardActionCompleted(inMobiInterstitial, map);
                                return;
                            }
                            break;
                        case 5:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdDisplayFailed(inMobiInterstitial);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdDisplayFailed(inMobiInterstitial);
                                return;
                            }
                            break;
                        case 6:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdWillDisplay(inMobiInterstitial);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdWillDisplay(inMobiInterstitial);
                                return;
                            }
                            break;
                        case 7:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdDisplayed(inMobiInterstitial);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdDisplayed(inMobiInterstitial);
                                return;
                            }
                            break;
                        case 9:
                            if (message.obj != null) {
                                map = (Map) message.obj;
                            }
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdClicked(inMobiInterstitial, map);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdInteraction(inMobiInterstitial, map);
                                return;
                            }
                            break;
                        case 10:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdDismissed(inMobiInterstitial);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdDismissed(inMobiInterstitial);
                                return;
                            }
                            break;
                        case 11:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onUserLeftApplication(inMobiInterstitial);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onUserLeftApplication(inMobiInterstitial);
                                return;
                            }
                            break;
                        case 12:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onRequestPayloadCreated((byte[]) message.obj);
                                return;
                            }
                            break;
                        case 13:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onRequestPayloadCreationFailed((InMobiAdRequestStatus) message.obj);
                                return;
                            }
                            break;
                        default:
                            InMobiInterstitial.a;
                            break;
                    }
                } catch (Exception e) {
                    Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.a, "Publisher handler caused unexpected error");
                    InMobiInterstitial.a;
                    new StringBuilder("Callback threw unexpected error: ").append(e.getMessage());
                }
            }
        }
    }

    @Deprecated
    public InMobiInterstitial(Context context, long j, InterstitialAdListener2 interstitialAdListener2) {
        this.h = false;
        this.l = false;
        this.n = "";
        this.q = new b() {
            public final void a(boolean z) {
                Message obtain = Message.obtain();
                obtain.what = 2;
                Bundle bundle = new Bundle();
                bundle.putBoolean("available", z);
                obtain.setData(bundle);
                InMobiInterstitial.this.c.sendMessage(obtain);
            }

            public final void a(i iVar) {
                InMobiInterstitial.this.a("AR", "");
                InMobiInterstitial.this.n = iVar.x;
                InMobiInterstitial.this.p = iVar.i;
                InMobiInterstitial.this.c.sendEmptyMessage(3);
            }

            public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                switch (inMobiAdRequestStatus.getStatusCode()) {
                    case NETWORK_UNREACHABLE:
                        InMobiInterstitial.this.a("ART", "NetworkNotAvailable");
                        break;
                    case REQUEST_PENDING:
                        InMobiInterstitial.this.a("ART", "LoadInProgress");
                        break;
                    case AD_ACTIVE:
                        InMobiInterstitial.this.a("ART", "ReloadNotPermitted");
                        break;
                    case EARLY_REFRESH_REQUEST:
                        InMobiInterstitial.this.a("ART", "FrequentRequests");
                        break;
                    case MISSING_REQUIRED_DEPENDENCIES:
                        InMobiInterstitial.this.a("ART", "MissingRequiredDependencies");
                        break;
                    default:
                        InMobiInterstitial.this.a("AF", "");
                        break;
                }
                if (!InMobiInterstitial.c()) {
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    obtain.obj = inMobiAdRequestStatus;
                    InMobiInterstitial.this.c.sendMessage(obtain);
                }
            }

            public final void b() {
                InMobiInterstitial.this.c.sendEmptyMessage(5);
            }

            public final void c() {
                InMobiInterstitial.this.c.sendEmptyMessage(6);
            }

            public final void d() {
                InMobiInterstitial.this.a("AVD", "");
                InMobiInterstitial.this.c.sendEmptyMessage(7);
            }

            public final void e() {
                InMobiInterstitial.this.a("AVCD", "");
                InMobiInterstitial.this.c.sendEmptyMessage(10);
                com.inmobi.ads.c.b d = com.inmobi.ads.c.b.d();
                bi a = bi.a(InMobiInterstitial.this.g, InMobiInterstitial.this.j, "int", InMobiInterstitial.this.i);
                if (com.inmobi.ads.c.b.b.c(d.c).a) {
                    new Handler(Looper.getMainLooper()).post(new com.inmobi.ads.c.b.AnonymousClass1(a));
                }
            }

            public final void a(@NonNull Map<Object, Object> map) {
                InMobiInterstitial.this.a("AVCL", "");
                Message obtain = Message.obtain();
                obtain.what = 9;
                obtain.obj = map;
                InMobiInterstitial.this.c.sendMessage(obtain);
            }

            public final void f() {
                InMobiInterstitial.this.c.sendEmptyMessage(11);
            }

            public final void b(@NonNull Map<Object, Object> map) {
                Message obtain = Message.obtain();
                obtain.what = 4;
                obtain.obj = map;
                InMobiInterstitial.this.c.sendMessage(obtain);
            }

            /* Access modifiers changed, original: final */
            public final void a(byte[] bArr) {
                Message obtain = Message.obtain();
                obtain.what = 12;
                obtain.obj = bArr;
                InMobiInterstitial.this.c.sendMessage(obtain);
            }

            /* Access modifiers changed, original: final */
            public final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message obtain = Message.obtain();
                obtain.what = 13;
                obtain.obj = inMobiAdRequestStatus;
                InMobiInterstitial.this.c.sendMessage(obtain);
            }
        };
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before creating an Interstitial ad");
        } else if (interstitialAdListener2 == null) {
            Logger.a(InternalLogLevel.ERROR, a, "The Interstitial ad cannot be created as no event listener was supplied. Please attach a listener to proceed");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Unable to create Interstitial ad with null context object.");
        } else {
            this.h = true;
            this.f = context.getApplicationContext();
            this.g = j;
            this.d = interstitialAdListener2;
            this.c = new a(this);
        }
    }

    public InMobiInterstitial(Context context, long j, InterstitialAdEventListener interstitialAdEventListener) {
        this.h = false;
        this.l = false;
        this.n = "";
        this.q = /* anonymous class already generated */;
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before creating an Interstitial ad");
        } else if (interstitialAdEventListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "The Interstitial ad cannot be created as no event listener was supplied. Please attach a listener to proceed");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Unable to create Interstitial ad with null context object.");
        } else {
            this.h = true;
            this.f = context.getApplicationContext();
            this.g = j;
            this.e = interstitialAdEventListener;
            this.c = new a(this);
        }
    }

    private InMobiInterstitial(Context context, long j) {
        this.h = false;
        this.l = false;
        this.n = "";
        this.q = /* anonymous class already generated */;
        this.h = true;
        this.f = context;
        this.g = j;
        this.c = new a(this);
    }

    @Deprecated
    public final void setInterstitialAdListener(InterstitialAdListener2 interstitialAdListener2) {
        this.d = interstitialAdListener2;
    }

    public final void setListener(InterstitialAdEventListener interstitialAdEventListener) {
        if (interstitialAdEventListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please pass a non-null listener to the interstitial.");
        } else {
            this.e = interstitialAdEventListener;
        }
    }

    public final void setKeywords(String str) {
        if (this.h) {
            this.i = str;
        }
    }

    private boolean a(boolean z) {
        if (!this.h) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiInterstitial is not initialized, your call is ignored.");
        } else if (!z ? this.e != null : !(this.d == null && this.e == null)) {
            Logger.a(InternalLogLevel.ERROR, a, "Listener supplied is null, your call is ignored.");
        } else if (this.f != null) {
            return true;
        } else {
            Logger.a(InternalLogLevel.ERROR, a, "Context supplied is null, your call is ignored.");
        }
        return false;
    }

    public final void getSignals() {
        if (a(false)) {
            if (this.b == null) {
                this.b = com.inmobi.ads.ac.a.b(this.f, bi.a(this.g, this.j, "int", this.i), this.q);
            }
            a("ARR", "");
            a(this.b);
            this.b.o();
        }
    }

    public final void load(byte[] bArr) {
        if (a(false)) {
            if (this.b == null) {
                Logger.a(InternalLogLevel.ERROR, a, "Either getSignals() is not called or InMobiInterstitial is not initialized, your call is ignored.");
                return;
            }
            this.l = true;
            ac acVar = this.b;
            if (acVar.d(this.q)) {
                acVar.a(bArr);
            }
        }
    }

    public final void load() {
        try {
            if (a(true)) {
                StringBuilder stringBuilder;
                bi a = bi.a(this.g, this.j, "int", this.i);
                com.inmobi.ads.c.b d = com.inmobi.ads.c.b.d();
                i iVar = null;
                StringBuilder stringBuilder2;
                if (com.inmobi.ads.c.b.b.c(d.c).a) {
                    d.a(a);
                    i iVar2 = (i) com.inmobi.ads.c.b.a.get(a);
                    if (iVar2 == null) {
                        stringBuilder2 = new StringBuilder("No cached ad unit found for pid:");
                        stringBuilder2.append(a.a);
                        stringBuilder2.append(" tp:");
                        stringBuilder2.append(a.b);
                    } else if (iVar2.h()) {
                        stringBuilder = new StringBuilder("Expired cached ad unit found for pid:");
                        stringBuilder.append(a.a);
                        stringBuilder.append(" tp:");
                        stringBuilder.append(a.b);
                        iVar2.v();
                        com.inmobi.ads.c.b.a.remove(a);
                        com.inmobi.ads.c.b.a("AdUnitExpired", iVar2);
                    } else {
                        stringBuilder = new StringBuilder("Cached ad unit found for pid:");
                        stringBuilder.append(a.a);
                        stringBuilder.append(" tp:");
                        stringBuilder.append(a.b);
                        com.inmobi.ads.c.b.a((i) com.inmobi.ads.c.b.a.remove(a));
                        iVar = iVar2;
                    }
                } else {
                    stringBuilder2 = new StringBuilder("No cached ad unit found as config is disabled. pid:");
                    stringBuilder2.append(a.a);
                    stringBuilder2.append(" tp:");
                    stringBuilder2.append(a.b);
                }
                this.l = true;
                if (iVar != null) {
                    this.b = (ac) iVar;
                } else {
                    this.b = com.inmobi.ads.ac.a.a(this.f, a, this.q);
                }
                a("ARR", "");
                a(this.b);
                ac acVar = this.b;
                InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
                String str = a;
                stringBuilder = new StringBuilder("Fetching an Interstitial ad for placement id: ");
                stringBuilder.append(acVar.d);
                Logger.a(internalLogLevel, str, stringBuilder.toString());
                this.n = "";
                this.p = this.b.b;
                acVar.a(this.q);
                acVar.e(this.q);
            }
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, a, "Unable to load ad; SDK encountered an unexpected error");
            new StringBuilder("Load failed with unexpected error: ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }

    public static void requestAd(Context context, InMobiAdRequest inMobiAdRequest, InterstitialAdRequestListener interstitialAdRequestListener) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before calling requestAd. Ignoring request");
        } else if (interstitialAdRequestListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please supply a non null InterstitialAdRequestListener. Ignoring request");
        } else if (inMobiAdRequest == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please supply a non null InMobiAdRequest. Ignoring request");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please supply a non null Context. Ignoring request");
        } else {
            Object obj = null;
            try {
                RecyclerView.class.getName();
                Picasso.class.getName();
                d anonymousClass1 = new d() {
                    public final void a(@NonNull i iVar) {
                        try {
                            if (iVar instanceof ac) {
                                ArrayList arrayList = (ArrayList) InMobiInterstitial.m.get(iVar);
                                if (arrayList != null) {
                                    InMobiInterstitial.m.remove(iVar);
                                    Handler handler = new Handler(Looper.getMainLooper());
                                    Iterator it = arrayList.iterator();
                                    while (it.hasNext()) {
                                        WeakReference weakReference = (WeakReference) it.next();
                                        if (weakReference != null) {
                                            final InterstitialAdRequestListener interstitialAdRequestListener = (InterstitialAdRequestListener) weakReference.get();
                                            if (interstitialAdRequestListener != null) {
                                                final InMobiInterstitial inMobiInterstitial = new InMobiInterstitial(iVar.a(), iVar.d, (byte) 0);
                                                inMobiInterstitial.setKeywords(iVar.e);
                                                inMobiInterstitial.setExtras(iVar.f);
                                                handler.post(new Runnable() {
                                                    public final void run() {
                                                        try {
                                                            interstitialAdRequestListener.onAdRequestCompleted(new InMobiAdRequestStatus(StatusCode.NO_ERROR), inMobiInterstitial);
                                                        } catch (Exception unused) {
                                                            Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.a, "Publisher handler caused unexpected error");
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            InMobiInterstitial.a;
                            new StringBuilder("SDK encountered unexpected error in onAdPrefetchSucceeded ").append(e.getMessage());
                        }
                    }

                    public final void a(@NonNull i iVar, @NonNull final InMobiAdRequestStatus inMobiAdRequestStatus) {
                        try {
                            if (iVar instanceof ac) {
                                ArrayList arrayList = (ArrayList) InMobiInterstitial.m.get(iVar);
                                if (arrayList != null && arrayList.size() > 0) {
                                    WeakReference weakReference = (WeakReference) arrayList.get(arrayList.size() - 1);
                                    if (weakReference != null) {
                                        arrayList.remove(weakReference);
                                        if (arrayList.size() == 0) {
                                            InMobiInterstitial.m.remove(iVar);
                                        }
                                        final InterstitialAdRequestListener interstitialAdRequestListener = (InterstitialAdRequestListener) weakReference.get();
                                        if (interstitialAdRequestListener != null) {
                                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                                public final void run() {
                                                    try {
                                                        interstitialAdRequestListener.onAdRequestCompleted(inMobiAdRequestStatus, null);
                                                    } catch (Exception unused) {
                                                        Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.a, "Publisher handler caused unexpected error");
                                                    }
                                                }
                                            });
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            InMobiInterstitial.a;
                            new StringBuilder("SDK encountered unexpected error in onAdPrefetchFailed ").append(e.getMessage());
                        }
                    }
                };
                try {
                    for (Entry key : m.entrySet()) {
                        ac acVar = (ac) key.getKey();
                        if (acVar != null && acVar.d == inMobiAdRequest.a) {
                            obj = acVar;
                            break;
                        }
                    }
                    ac a;
                    if (obj != null) {
                        ArrayList arrayList = (ArrayList) m.get(obj);
                        arrayList.add(new WeakReference(interstitialAdRequestListener));
                        a = a(context, inMobiAdRequest, anonymousClass1);
                        m.put(a, arrayList);
                        a.q();
                        return;
                    }
                    a = a(context, inMobiAdRequest, anonymousClass1);
                    a.q = anonymousClass1;
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(new WeakReference(interstitialAdRequestListener));
                    m.put(a, arrayList2);
                    a.q();
                } catch (Exception e) {
                    new StringBuilder("SDK encountered unexpected error in requestAd").append(e.getMessage());
                }
            } catch (NoClassDefFoundError unused) {
                Logger.a(InternalLogLevel.ERROR, a, "Some of the dependency libraries for Interstitial not found. Ignoring request");
                interstitialAdRequestListener.onAdRequestCompleted(new InMobiAdRequestStatus(StatusCode.MISSING_REQUIRED_DEPENDENCIES), null);
            }
        }
    }

    private static ac a(Context context, InMobiAdRequest inMobiAdRequest, d dVar) {
        ac a = com.inmobi.ads.ac.a.a(context.getApplicationContext(), bi.a(inMobiAdRequest.a, inMobiAdRequest.f, "int", inMobiAdRequest.e), null);
        a.f = inMobiAdRequest.f;
        a.e = inMobiAdRequest.e;
        a.a(MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
        a.n = true;
        a.q = dVar;
        return a;
    }

    @VisibleForTesting
    private void a(@NonNull ac acVar) {
        acVar.a(this.f);
        acVar.f = this.j;
        acVar.e = this.i;
        acVar.a(MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
        if (this.k) {
            AdContainer j = acVar.j();
            if (j != null) {
                acVar.y = true;
                j.a();
            }
        }
        acVar.n = false;
    }

    public final void show() {
        try {
            if (this.l) {
                if (this.h && this.b != null) {
                    a("AVR", "");
                    this.b.f(this.q);
                }
                return;
            }
            Logger.a(InternalLogLevel.ERROR, a, "load() must be called before trying to show the ad");
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, a, "Unable to show ad; SDK encountered an unexpected error");
            new StringBuilder("Show failed with unexpected error: ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }

    @Deprecated
    public final void show(int i, int i2) {
        show();
    }

    public final boolean isReady() {
        return this.h && this.b != null && this.b.O();
    }

    public final JSONObject getAdMetaInfo() {
        if (this.p == null) {
            return new JSONObject();
        }
        return this.p;
    }

    public final String getCreativeId() {
        return this.n;
    }

    public final void setExtras(Map<String, String> map) {
        if (this.h) {
            this.j = map;
        }
    }

    public final void disableHardwareAcceleration() {
        if (this.h) {
            this.k = true;
        }
    }

    private void a(String str, String str2) {
        if (this.o == null) {
            this.o = new k(this.b);
        }
        this.o.a(this.q, str, str2);
    }

    static /* synthetic */ boolean c() {
        return Message.obtain() == null;
    }
}
