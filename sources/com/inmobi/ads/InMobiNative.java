package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.internal.AnalyticsEvents;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.i.b;
import com.inmobi.ads.i.d;
import com.inmobi.ads.listeners.NativeAdEventListener;
import com.inmobi.ads.listeners.VideoEventListener;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.squareup.picasso.Picasso;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

public final class InMobiNative {
    private static final String a = "InMobiNative";
    private static ConcurrentHashMap<aj, WeakReference<NativeAdRequestListener>> j = new ConcurrentHashMap(5, 0.9f, 3);
    private a b;
    private NativeAdListener c;
    private NativeAdEventListener d;
    @Nullable
    private VideoEventListener e;
    private aj f;
    private String g;
    private Map<String, String> h;
    private long i;
    private Downloader k;
    private WeakReference<View> l;
    private boolean m;
    private boolean n;
    private WeakReference<Context> o;
    private LockScreenListener p;
    private j q;
    private final b r;

    public final class Downloader {
        public static final int STATE_DOWNLOADED = 1;
        public static final int STATE_DOWNLOADING = 0;
        public static final int STATE_ERROR = 2;
        public static final int STATE_INITIALIZING = -1;
        public static final int STATE_UNINITIALIZED = -2;

        public final int getDownloadProgress() {
            if (com.inmobi.commons.a.a.a()) {
                try {
                    if (InMobiNative.this.f != null) {
                        AdContainer j = InMobiNative.this.f.j();
                        return (j == null || j.getApkDownloader() != null) ? 0 : 0;
                    }
                } catch (Exception unused) {
                    Logger.a(InternalLogLevel.ERROR, InMobiNative.a, "Encountered unexpected error in getting download progress");
                }
                return 0;
            }
            Logger.a(InternalLogLevel.ERROR, InMobiNative.a, "InMobiNative is not initialized.Ignoring getDownloadProgress()");
            return 0;
        }

        public final int getDownloadStatus() {
            if (com.inmobi.commons.a.a.a()) {
                try {
                    if (InMobiNative.this.f != null) {
                        AdContainer j = InMobiNative.this.f.j();
                        return (j == null || j.getApkDownloader() != null) ? -2 : -2;
                    }
                } catch (Exception unused) {
                    Logger.a(InternalLogLevel.ERROR, InMobiNative.a, "Encountered unexpected error in getting download progress");
                }
                return -2;
            }
            Logger.a(InternalLogLevel.ERROR, InMobiNative.a, "InMobiNative is not initialized.Ignoring getDownloadStatus()");
            return -2;
        }
    }

    public interface LockScreenListener {
        void onActionRequired(InMobiNative inMobiNative);
    }

    public interface NativeAdListener {
        void onAdClicked(@NonNull InMobiNative inMobiNative);

        void onAdFullScreenDismissed(InMobiNative inMobiNative);

        void onAdFullScreenDisplayed(InMobiNative inMobiNative);

        void onAdFullScreenWillDisplay(InMobiNative inMobiNative);

        void onAdImpressed(@NonNull InMobiNative inMobiNative);

        void onAdLoadFailed(@NonNull InMobiNative inMobiNative, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(@NonNull InMobiNative inMobiNative);

        void onAdStatusChanged(@NonNull InMobiNative inMobiNative);

        void onMediaPlaybackComplete(@NonNull InMobiNative inMobiNative);

        void onUserSkippedMedia(@NonNull InMobiNative inMobiNative);

        void onUserWillLeaveApplication(InMobiNative inMobiNative);
    }

    public interface NativeAdRequestListener {
        void onAdRequestCompleted(InMobiAdRequestStatus inMobiAdRequestStatus, InMobiNative inMobiNative);
    }

    @VisibleForTesting
    static final class a extends Handler {
        private WeakReference<InMobiNative> a;

        a(InMobiNative inMobiNative) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(inMobiNative);
        }

        public final void handleMessage(Message message) {
            InMobiNative inMobiNative = (InMobiNative) this.a.get();
            if (inMobiNative == null) {
                Logger.a(InternalLogLevel.ERROR, InMobiNative.a, "Lost reference to InMobiNative! callback cannot be given");
                return;
            }
            try {
                switch (message.what) {
                    case 1:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdLoadSucceeded(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdLoadSucceeded(inMobiNative);
                            return;
                        }
                        break;
                    case 2:
                        InMobiAdRequestStatus inMobiAdRequestStatus = (InMobiAdRequestStatus) message.obj;
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdLoadFailed(inMobiNative, inMobiAdRequestStatus);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdLoadFailed(inMobiNative, inMobiAdRequestStatus);
                            return;
                        }
                        break;
                    case 3:
                        if (inMobiNative.p != null) {
                            inMobiNative.p.onActionRequired(inMobiNative);
                        }
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdFullScreenWillDisplay(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdFullScreenWillDisplay(inMobiNative);
                            return;
                        }
                        break;
                    case 4:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdFullScreenDisplayed(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdFullScreenDisplayed(inMobiNative);
                            return;
                        }
                        break;
                    case 5:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdFullScreenDismissed(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdFullScreenDismissed(inMobiNative);
                            return;
                        }
                        break;
                    case 6:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdImpressed(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdImpressed(inMobiNative);
                            return;
                        }
                        break;
                    case 7:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdClicked(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdClicked(inMobiNative);
                            return;
                        }
                        break;
                    case 8:
                        if (inMobiNative.p != null) {
                            inMobiNative.p.onActionRequired(inMobiNative);
                        }
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onUserWillLeaveApplication(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onUserWillLeaveApplication(inMobiNative);
                            return;
                        }
                        break;
                    case 9:
                        if (inMobiNative.e != null) {
                            inMobiNative.e.onVideoCompleted(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onMediaPlaybackComplete(inMobiNative);
                            return;
                        }
                        break;
                    case 10:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdStatusChanged(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdStatusChanged(inMobiNative);
                            return;
                        }
                        break;
                    case 11:
                        if (inMobiNative.e != null) {
                            inMobiNative.e.onVideoSkipped(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onUserSkippedMedia(inMobiNative);
                            return;
                        }
                        break;
                    case 12:
                        byte[] bArr = (byte[]) message.obj;
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onRequestPayloadCreated(bArr);
                            return;
                        }
                        break;
                    case 13:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onRequestPayloadCreationFailed((InMobiAdRequestStatus) message.obj);
                            return;
                        }
                        break;
                    case 14:
                        if (inMobiNative.e != null) {
                            inMobiNative.e.onAudioStateChanged(inMobiNative, ((Boolean) message.obj).booleanValue());
                            return;
                        }
                        break;
                    default:
                        InMobiNative.a;
                        break;
                }
            } catch (Exception e) {
                Logger.a(InternalLogLevel.ERROR, InMobiNative.a, "Publisher handler caused unexpected error");
                InMobiNative.a;
                new StringBuilder("Callback threw unexpected error: ").append(e.getMessage());
            }
        }
    }

    @Deprecated
    public InMobiNative(Context context, long j, NativeAdListener nativeAdListener) {
        this.n = true;
        this.r = new b() {
            public final void a() {
                InMobiNative.this.a("AR", "");
                InMobiNative.this.b.sendEmptyMessage(1);
            }

            public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                switch (inMobiAdRequestStatus.getStatusCode()) {
                    case NETWORK_UNREACHABLE:
                        InMobiNative.this.a("ART", "NetworkNotAvailable");
                        break;
                    case REQUEST_PENDING:
                    case AD_ACTIVE:
                        InMobiNative.this.a("ART", "LoadInProgress");
                        break;
                    case EARLY_REFRESH_REQUEST:
                        InMobiNative.this.a("ART", "FrequentRequests");
                        break;
                    case MISSING_REQUIRED_DEPENDENCIES:
                        InMobiNative.this.a("ART", "MissingRequiredDependencies");
                        break;
                    case REPETITIVE_LOAD:
                        InMobiNative.this.a("ART", "ReloadNotPermitted");
                        break;
                    default:
                        InMobiNative.this.a("AF", "");
                        break;
                }
                if (!InMobiNative.c()) {
                    Message obtain = Message.obtain();
                    obtain.what = 2;
                    obtain.obj = inMobiAdRequestStatus;
                    InMobiNative.this.b.sendMessage(obtain);
                }
            }

            public final void b() {
                InMobiNative.a;
            }

            public final void c() {
                InMobiNative.this.b.sendEmptyMessage(3);
            }

            public final void d() {
                InMobiNative.this.a("AVE", "");
                InMobiNative.this.b.sendEmptyMessage(4);
            }

            public final void e() {
                InMobiNative.this.a("AVCO", "");
                InMobiNative.this.b.sendEmptyMessage(5);
            }

            public final void a(@NonNull Map<Object, Object> map) {
                InMobiNative.this.a("AVCL", "");
                InMobiNative.this.b.sendEmptyMessage(7);
            }

            public final void f() {
                InMobiNative.this.b.sendEmptyMessage(8);
            }

            public final void g() {
                InMobiNative.this.b.sendEmptyMessage(6);
            }

            public final void h() {
                InMobiNative.this.b.sendEmptyMessage(9);
            }

            public final void j() {
                InMobiNative.this.b.sendEmptyMessage(11);
            }

            /* Access modifiers changed, original: final */
            public final void a(byte[] bArr) {
                Message obtain = Message.obtain();
                obtain.what = 12;
                obtain.obj = bArr;
                InMobiNative.this.b.sendMessage(obtain);
            }

            /* Access modifiers changed, original: final */
            public final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message obtain = Message.obtain();
                obtain.what = 13;
                obtain.obj = inMobiAdRequestStatus;
                InMobiNative.this.b.sendMessage(obtain);
            }

            /* Access modifiers changed, original: final */
            public final void b(boolean z) {
                Message obtain = Message.obtain();
                obtain.what = 14;
                obtain.obj = Boolean.valueOf(z);
                InMobiNative.this.b.sendMessage(obtain);
            }
        };
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before creating a Native ad");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Context is null, Native ad cannot be created.");
        } else if (nativeAdListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Listener supplied is null, the Native ad cannot be created.");
        } else {
            this.i = j;
            this.o = new WeakReference(context);
            this.c = nativeAdListener;
            this.k = new Downloader();
            this.b = new a(this);
        }
    }

    public InMobiNative(Context context, long j, NativeAdEventListener nativeAdEventListener) {
        this.n = true;
        this.r = /* anonymous class already generated */;
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before creating a Native ad");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Context is null, Native ad cannot be created.");
        } else if (nativeAdEventListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Listener supplied is null, the Native ad cannot be created.");
        } else {
            this.i = j;
            this.o = new WeakReference(context);
            this.d = nativeAdEventListener;
            this.k = new Downloader();
            this.b = new a(this);
        }
    }

    private InMobiNative(Context context, bi biVar) {
        this.n = true;
        this.r = /* anonymous class already generated */;
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before creating an InMobiNative ad");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Context is null, Native ad cannot be created.");
        } else {
            this.f = aj.a(context, biVar, this.r, 0);
            this.i = biVar.a;
            this.o = new WeakReference(context);
            this.b = new a(this);
        }
    }

    @Deprecated
    public final void setNativeAdListener(NativeAdListener nativeAdListener) {
        this.c = nativeAdListener;
    }

    public final void setListener(NativeAdEventListener nativeAdEventListener) {
        if (nativeAdEventListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please pass a non-null listener to the native.");
        } else {
            this.d = nativeAdEventListener;
        }
    }

    public final void setVideoEventListener(VideoEventListener videoEventListener) {
        if (videoEventListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please pass a non-null listener to the native.");
        } else {
            this.e = videoEventListener;
        }
    }

    private boolean a(boolean z) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized, your call is ignored.");
        } else if (!z ? this.d != null : !(this.r == null && this.d == null)) {
            Logger.a(InternalLogLevel.ERROR, a, "Listener supplied is null, your call is ignored.");
        } else if (this.o != null && this.o.get() != null) {
            return true;
        } else {
            Logger.a(InternalLogLevel.ERROR, a, "Context supplied is null, your call is ignored.");
        }
        return false;
    }

    public final void getSignals() {
        if (a(false)) {
            d();
            a("ARR", "");
            if (this.f != null) {
                this.f.o();
            }
        }
    }

    public final void load(byte[] bArr) {
        if (a(false)) {
            if (this.f == null) {
                Logger.a(InternalLogLevel.ERROR, a, "Either getSignals() is not called or InMobiNative is not initialized, your call is ignored.");
                return;
            }
            this.f.a(bArr);
        }
    }

    @VisibleForTesting
    private void d() {
        Context context = this.o == null ? null : (Context) this.o.get();
        if (context != null) {
            if (this.f == null) {
                bi a = bi.a(this.i, this.h, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, this.g);
                a.f = context instanceof Activity ? MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : MonetizationContext.MONETIZATION_CONTEXT_OTHER;
                this.f = aj.a(context, a, this.r, 0);
            } else {
                this.f.a(context);
                this.f.a(context instanceof Activity ? MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : MonetizationContext.MONETIZATION_CONTEXT_OTHER);
            }
            this.f.n = false;
            this.f.e = this.g;
            this.f.f = this.h;
        }
    }

    public final void load() {
        try {
            if (!a(true)) {
                return;
            }
            if (this.m) {
                a("ARR", "");
                this.r.a(new InMobiAdRequestStatus(StatusCode.REPETITIVE_LOAD));
                Logger.a(InternalLogLevel.ERROR, a, "You can call load() on an instance of InMobiNative only once if the ad request has been successful. Ignoring InMobiNative.load()");
                return;
            }
            d();
            if (this.f != null) {
                a("ARR", "");
                bi a = bi.a(this.i, this.h, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, this.g);
                a.f = this.f.l();
                this.f.n();
                com.inmobi.ads.c.a.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE).a(a);
            }
        } catch (Exception e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not load ad; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in loading ad; ").append(e.getMessage());
        }
    }

    public final void load(Context context) {
        if (a(true)) {
            this.o = new WeakReference(context);
            load();
        }
    }

    public static void requestAd(Context context, InMobiAdRequest inMobiAdRequest, NativeAdRequestListener nativeAdRequestListener) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before calling requestAd. Ignoring request");
        } else if (nativeAdRequestListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please supply a non null NativeAdRequestListener. Ignoring request");
        } else if (inMobiAdRequest == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please supply a non null InMobiAdRequest. Ignoring request");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please supply a non null Context. Ignoring request");
        } else {
            try {
                RecyclerView.class.getName();
                Picasso.class.getName();
                final bi a = bi.a(inMobiAdRequest.a, inMobiAdRequest.f, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, inMobiAdRequest.e);
                a.f = inMobiAdRequest.b;
                AnonymousClass1 anonymousClass1 = new d() {
                    public final void a(@NonNull i iVar) {
                        if (iVar instanceof aj) {
                            try {
                                com.inmobi.ads.c.a.a.remove(a);
                                WeakReference weakReference = (WeakReference) InMobiNative.j.get(iVar);
                                if (weakReference != null) {
                                    InMobiNative.j.remove(iVar);
                                    NativeAdRequestListener nativeAdRequestListener = (NativeAdRequestListener) weakReference.get();
                                    if (nativeAdRequestListener != null) {
                                        bi a = bi.a(iVar.d, iVar.f, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, iVar.e);
                                        a.f = iVar.l();
                                        InMobiNative inMobiNative = new InMobiNative(iVar.a(), a, (byte) 0);
                                        inMobiNative.setKeywords(iVar.e);
                                        inMobiNative.setExtras(iVar.f);
                                        nativeAdRequestListener.onAdRequestCompleted(new InMobiAdRequestStatus(StatusCode.NO_ERROR), inMobiNative);
                                    }
                                }
                            } catch (Exception e) {
                                InMobiNative.a;
                                new StringBuilder("SDK encountered unexpected error in onAdPrefetchSucceeded ").append(e.getMessage());
                            }
                        }
                    }

                    public final void a(@NonNull i iVar, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                        if (iVar instanceof aj) {
                            try {
                                com.inmobi.ads.c.a.a.remove(a);
                                WeakReference weakReference = (WeakReference) InMobiNative.j.get(iVar);
                                if (weakReference != null) {
                                    InMobiNative.j.remove(iVar);
                                    NativeAdRequestListener nativeAdRequestListener = (NativeAdRequestListener) weakReference.get();
                                    if (nativeAdRequestListener != null) {
                                        nativeAdRequestListener.onAdRequestCompleted(inMobiAdRequestStatus, null);
                                    }
                                }
                            } catch (Exception e) {
                                InMobiNative.a;
                                new StringBuilder("SDK encountered unexpected error in onAdPrefetchFailed ").append(e.getMessage());
                            }
                        }
                    }
                };
                try {
                    aj a2 = aj.a(context.getApplicationContext(), a, null, 2);
                    a2.f = inMobiAdRequest.f;
                    a2.e = inMobiAdRequest.e;
                    a2.q = anonymousClass1;
                    a2.n = true;
                    j.put(a2, new WeakReference(nativeAdRequestListener));
                    a2.q();
                } catch (Exception e) {
                    new StringBuilder("SDK encountered unexpected error in requestAd").append(e.getMessage());
                }
            } catch (NoClassDefFoundError unused) {
                Logger.a(InternalLogLevel.ERROR, a, "Some of the dependency libraries for InMobiNative not found. Ignoring request");
                nativeAdRequestListener.onAdRequestCompleted(new InMobiAdRequestStatus(StatusCode.MISSING_REQUIRED_DEPENDENCIES), null);
            }
        }
    }

    public final void showOnLockScreen(LockScreenListener lockScreenListener) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before calling showOnLockScreen.");
        } else if (lockScreenListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please provided non null LockScreenListener. Ignoring showOnLockScreen");
        } else if (this.o == null || this.o.get() == null) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized. Provided context is null. Ignoring showOnLockScreen");
        } else {
            try {
                if (this.f == null) {
                    this.f = aj.a((Context) this.o.get(), bi.a(this.i, this.h, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, this.g), this.r, 0);
                }
                this.f.A = true;
                this.p = lockScreenListener;
            } catch (Exception unused) {
                Logger.a(InternalLogLevel.ERROR, a, "SDK encountered unexpected error in showOnLockScreen");
            }
        }
    }

    public final void takeAction() {
        if (com.inmobi.commons.a.a.a()) {
            try {
                if (this.f != null) {
                    aj ajVar = this.f;
                    if (ajVar.o != null) {
                        ajVar.o.r();
                    }
                    return;
                }
                Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized. Ignoring takeAction");
                return;
            } catch (Exception unused) {
                Logger.a(InternalLogLevel.ERROR, a, "SDK encountered unexpected error in takeAction");
                return;
            }
        }
        Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before calling takeAction.");
    }

    public final void pause() {
        try {
            if (this.f != null) {
                aj ajVar = this.f;
                if (ajVar.a == 5 && !(ajVar.a() instanceof Activity)) {
                    AdContainer j = ajVar.j();
                    if (j != null) {
                        ((ah) j).q();
                    }
                }
            }
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, a, "Could not pause ad; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in pausing ad; ").append(e.getMessage());
        }
    }

    public final void resume() {
        try {
            if (this.f != null) {
                aj ajVar = this.f;
                if (ajVar.a == 5 && !(ajVar.a() instanceof Activity)) {
                    AdContainer j = ajVar.j();
                    if (j != null) {
                        ((ah) j).p();
                    }
                }
            }
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, a, "Could not resume ad; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in resuming ad; ").append(e.getMessage());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x0108 A:{Catch:{ Exception -> 0x0112 }} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e1 A:{Catch:{ Exception -> 0x0112 }} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e1 A:{Catch:{ Exception -> 0x0112 }} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0108 A:{Catch:{ Exception -> 0x0112 }} */
    public final android.view.View getPrimaryViewOfWidth(android.content.Context r7, android.view.View r8, android.view.ViewGroup r9, int r10) {
        /*
        r6 = this;
        r0 = 0;
        r1 = com.inmobi.commons.a.a.a();	 Catch:{ Exception -> 0x0112 }
        if (r1 != 0) goto L_0x0011;
    L_0x0007:
        r7 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.ERROR;	 Catch:{ Exception -> 0x0112 }
        r8 = a;	 Catch:{ Exception -> 0x0112 }
        r9 = "InMobiSdk is not initialized. Ignoring InMobiNative.getPrimaryView()";
        com.inmobi.commons.core.utilities.Logger.a(r7, r8, r9);	 Catch:{ Exception -> 0x0112 }
        return r0;
    L_0x0011:
        if (r7 != 0) goto L_0x001d;
    L_0x0013:
        r7 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.ERROR;	 Catch:{ Exception -> 0x0112 }
        r8 = a;	 Catch:{ Exception -> 0x0112 }
        r9 = "View can not be rendered using null context";
        com.inmobi.commons.core.utilities.Logger.a(r7, r8, r9);	 Catch:{ Exception -> 0x0112 }
        return r0;
    L_0x001d:
        r1 = r6.f;	 Catch:{ Exception -> 0x0112 }
        if (r1 != 0) goto L_0x002b;
    L_0x0021:
        r7 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.ERROR;	 Catch:{ Exception -> 0x0112 }
        r8 = a;	 Catch:{ Exception -> 0x0112 }
        r9 = "InMobiNative is not initialized. Ignoring InMobiNative.getPrimaryView()";
        com.inmobi.commons.core.utilities.Logger.a(r7, r8, r9);	 Catch:{ Exception -> 0x0112 }
        return r0;
    L_0x002b:
        r1 = new java.lang.ref.WeakReference;	 Catch:{ Exception -> 0x0112 }
        r1.<init>(r7);	 Catch:{ Exception -> 0x0112 }
        r6.o = r1;	 Catch:{ Exception -> 0x0112 }
        r1 = r6.f;	 Catch:{ Exception -> 0x0112 }
        r1.a(r7);	 Catch:{ Exception -> 0x0112 }
        r7 = new java.lang.ref.WeakReference;	 Catch:{ Exception -> 0x0112 }
        r1 = r6.f;	 Catch:{ Exception -> 0x0112 }
        r2 = r6.n;	 Catch:{ Exception -> 0x0112 }
        r3 = android.os.Looper.myLooper();	 Catch:{ Exception -> 0x0112 }
        r4 = android.os.Looper.getMainLooper();	 Catch:{ Exception -> 0x0112 }
        r5 = 1;
        if (r3 != r4) goto L_0x00bd;
    L_0x0048:
        r3 = com.inmobi.commons.core.utilities.b.e.e();	 Catch:{ Exception -> 0x0112 }
        if (r3 != 0) goto L_0x0054;
    L_0x004e:
        r1.O();	 Catch:{ Exception -> 0x0112 }
    L_0x0051:
        r8 = r0;
        goto L_0x00cb;
    L_0x0054:
        r3 = r1.P();	 Catch:{ Exception -> 0x0112 }
        if (r3 != 0) goto L_0x0088;
    L_0x005a:
        r3 = r1.a;	 Catch:{ Exception -> 0x0112 }
        r4 = 7;
        if (r3 == r4) goto L_0x0088;
    L_0x005f:
        r8 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.ERROR;	 Catch:{ Exception -> 0x0112 }
        r9 = com.inmobi.ads.aj.y;	 Catch:{ Exception -> 0x0112 }
        r10 = "Ad Load is not complete. Please wait for the Ad to be in a ready state before calling getPrimaryView().";
        com.inmobi.commons.core.utilities.Logger.a(r8, r9, r10);	 Catch:{ Exception -> 0x0112 }
        r8 = r1.z;	 Catch:{ Exception -> 0x0112 }
        if (r8 == 0) goto L_0x0051;
    L_0x006c:
        r8 = r1.z;	 Catch:{ Exception -> 0x0112 }
        r8 = r8.get();	 Catch:{ Exception -> 0x0112 }
        r8 = (android.view.View) r8;	 Catch:{ Exception -> 0x0112 }
        if (r8 == 0) goto L_0x0051;
    L_0x0076:
        r9 = new android.view.View;	 Catch:{ Exception -> 0x0112 }
        r10 = com.inmobi.commons.a.a.b();	 Catch:{ Exception -> 0x0112 }
        r9.<init>(r10);	 Catch:{ Exception -> 0x0112 }
        r8 = r8.getLayoutParams();	 Catch:{ Exception -> 0x0112 }
        r9.setLayoutParams(r8);	 Catch:{ Exception -> 0x0112 }
        r8 = r9;
        goto L_0x00cb;
    L_0x0088:
        r3 = r1.o;	 Catch:{ Exception -> 0x0112 }
        if (r3 == 0) goto L_0x0051;
    L_0x008c:
        r4 = r1.A;	 Catch:{ Exception -> 0x0112 }
        r3.u = r4;	 Catch:{ Exception -> 0x0112 }
        r3.s = r10;	 Catch:{ Exception -> 0x0112 }
        r3.t = r2;	 Catch:{ Exception -> 0x0112 }
        r10 = r3.getViewableAd();	 Catch:{ Exception -> 0x0112 }
        r8 = r10.a(r8, r9, r5);	 Catch:{ Exception -> 0x0112 }
        r9 = new java.lang.ref.WeakReference;	 Catch:{ Exception -> 0x0112 }
        r9.<init>(r8);	 Catch:{ Exception -> 0x0112 }
        r1.z = r9;	 Catch:{ Exception -> 0x0112 }
        r9 = r1.r;	 Catch:{ Exception -> 0x0112 }
        if (r9 != 0) goto L_0x00b6;
    L_0x00a7:
        r9 = r1.t;	 Catch:{ Exception -> 0x0112 }
        if (r9 != 0) goto L_0x00b6;
    L_0x00ab:
        r9 = r1.s;	 Catch:{ Exception -> 0x0112 }
        r2 = new com.inmobi.ads.aj$3;	 Catch:{ Exception -> 0x0112 }
        r2.<init>(r10);	 Catch:{ Exception -> 0x0112 }
        r9.post(r2);	 Catch:{ Exception -> 0x0112 }
        goto L_0x00cb;
    L_0x00b6:
        r9 = 0;
        r9 = new android.view.View[r9];	 Catch:{ Exception -> 0x0112 }
        r10.a(r9);	 Catch:{ Exception -> 0x0112 }
        goto L_0x00cb;
    L_0x00bd:
        r8 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.ERROR;	 Catch:{ Exception -> 0x0112 }
        r9 = com.inmobi.ads.InMobiNative.class;
        r9 = r9.getSimpleName();	 Catch:{ Exception -> 0x0112 }
        r10 = "Please ensure that you call getPrimaryView() on the UI thread";
        com.inmobi.commons.core.utilities.Logger.a(r8, r9, r10);	 Catch:{ Exception -> 0x0112 }
        goto L_0x0051;
    L_0x00cb:
        r7.<init>(r8);	 Catch:{ Exception -> 0x0112 }
        r6.l = r7;	 Catch:{ Exception -> 0x0112 }
        r7 = r6.l;	 Catch:{ Exception -> 0x0112 }
        r7 = r7.get();	 Catch:{ Exception -> 0x0112 }
        r7 = (android.view.View) r7;	 Catch:{ Exception -> 0x0112 }
        r8 = "AVR";
        r9 = "";
        r6.a(r8, r9);	 Catch:{ Exception -> 0x0112 }
        if (r7 != 0) goto L_0x0108;
    L_0x00e1:
        r7 = r6.f;	 Catch:{ Exception -> 0x0112 }
        r7 = r7.P();	 Catch:{ Exception -> 0x0112 }
        if (r7 == 0) goto L_0x00f1;
    L_0x00e9:
        r7 = "AVFB";
        r8 = "";
        r6.a(r7, r8);	 Catch:{ Exception -> 0x0112 }
        goto L_0x00f8;
    L_0x00f1:
        r7 = "AVRR";
        r8 = "";
        r6.a(r7, r8);	 Catch:{ Exception -> 0x0112 }
    L_0x00f8:
        com.inmobi.commons.core.e.b.a();	 Catch:{ Exception -> 0x0112 }
        r7 = "ads";
        r8 = "PrimaryViewInflationFailed";
        r9 = new java.util.HashMap;	 Catch:{ Exception -> 0x0112 }
        r9.<init>();	 Catch:{ Exception -> 0x0112 }
        com.inmobi.commons.core.e.b.a(r7, r8, r9);	 Catch:{ Exception -> 0x0112 }
        return r0;
    L_0x0108:
        r8 = "AVD";
        r9 = "";
        r6.a(r8, r9);	 Catch:{ Exception -> 0x0112 }
        r6.m = r5;	 Catch:{ Exception -> 0x0112 }
        return r7;
    L_0x0112:
        r7 = move-exception;
        r8 = com.inmobi.commons.core.a.a.a();
        r9 = new com.inmobi.commons.core.e.a;
        r9.<init>(r7);
        r8.a(r9);
        r8 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.ERROR;
        r9 = "InMobi";
        r10 = "Could not pause ad; SDK encountered an unexpected error";
        com.inmobi.commons.core.utilities.Logger.a(r8, r9, r10);
        r8 = new java.lang.StringBuilder;
        r9 = "SDK encountered unexpected error in pausing ad; ";
        r8.<init>(r9);
        r7 = r7.getMessage();
        r8.append(r7);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.InMobiNative.getPrimaryViewOfWidth(android.content.Context, android.view.View, android.view.ViewGroup, int):android.view.View");
    }

    @Deprecated
    public final View getPrimaryViewOfWidth(View view, ViewGroup viewGroup, int i) {
        if (this.o != null && this.o.get() != null) {
            return getPrimaryViewOfWidth((Context) this.o.get(), view, viewGroup, i);
        }
        Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized or provided context is null.");
        return null;
    }

    public final JSONObject getCustomAdContent() {
        if (com.inmobi.commons.a.a.a()) {
            try {
                if (this.f != null) {
                    AdContainer j = this.f.j();
                    if (j != null) {
                        ao aoVar = (ao) j.getDataModel();
                        if (aoVar != null) {
                            return aoVar.i.a;
                        }
                    }
                    return null;
                }
            } catch (Exception e) {
                Logger.a(InternalLogLevel.ERROR, a, "Could not get the ad customJson ; SDK encountered unexpected error");
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
            return null;
        }
        Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.setExtras()");
        return null;
    }

    public final String getAdTitle() {
        if (com.inmobi.commons.a.a.a()) {
            try {
                if (this.f != null) {
                    AdContainer j = this.f.j();
                    if (j != null) {
                        ao aoVar = (ao) j.getDataModel();
                        if (aoVar != null) {
                            return aoVar.i.b.a;
                        }
                    }
                    return null;
                }
            } catch (Exception e) {
                Logger.a(InternalLogLevel.ERROR, a, "Could not get the ad title; SDK encountered unexpected error");
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
            return null;
        }
        Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.getAdTitle()");
        return null;
    }

    public final String getAdDescription() {
        if (com.inmobi.commons.a.a.a()) {
            try {
                if (this.f != null) {
                    AdContainer j = this.f.j();
                    if (j != null) {
                        ao aoVar = (ao) j.getDataModel();
                        if (aoVar != null) {
                            return aoVar.i.b.b;
                        }
                    }
                    return null;
                }
            } catch (Exception e) {
                Logger.a(InternalLogLevel.ERROR, a, "Could not get the description; SDK encountered unexpected error");
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
            return null;
        }
        Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.getAdDescription()");
        return null;
    }

    public final String getAdIconUrl() {
        if (com.inmobi.commons.a.a.a()) {
            try {
                if (this.f != null) {
                    AdContainer j = this.f.j();
                    if (j != null) {
                        ao aoVar = (ao) j.getDataModel();
                        if (aoVar != null) {
                            return aoVar.i.b.c;
                        }
                    }
                    return null;
                }
            } catch (Exception e) {
                Logger.a(InternalLogLevel.ERROR, a, "Could not get the iconUrl; SDK encountered unexpected error");
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
            return null;
        }
        Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.getAdIconUrl()");
        return null;
    }

    public final String getAdLandingPageUrl() {
        if (com.inmobi.commons.a.a.a()) {
            try {
                if (this.f != null) {
                    AdContainer j = this.f.j();
                    if (j != null) {
                        ao aoVar = (ao) j.getDataModel();
                        if (aoVar != null) {
                            return aoVar.i.b.f;
                        }
                    }
                    return null;
                }
            } catch (Exception e) {
                Logger.a(InternalLogLevel.ERROR, a, "Could not get the adLandingPageUrl; SDK encountered unexpected error");
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
            return null;
        }
        Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.getAdLandingPageUrl()");
        return null;
    }

    public final String getAdCtaText() {
        if (com.inmobi.commons.a.a.a()) {
            try {
                if (this.f != null) {
                    AdContainer j = this.f.j();
                    if (j != null) {
                        ao aoVar = (ao) j.getDataModel();
                        if (aoVar != null) {
                            return aoVar.i.b.d;
                        }
                    }
                    return null;
                }
            } catch (Exception e) {
                Logger.a(InternalLogLevel.ERROR, a, "Could not get the ctaText; SDK encountered unexpected error");
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
            return null;
        }
        Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.getAdCtaText()");
        return null;
    }

    public final float getAdRating() {
        if (com.inmobi.commons.a.a.a()) {
            try {
                if (this.f != null) {
                    AdContainer j = this.f.j();
                    if (j != null) {
                        ao aoVar = (ao) j.getDataModel();
                        if (aoVar != null) {
                            return aoVar.i.b.e;
                        }
                    }
                    return 0.0f;
                }
            } catch (Exception e) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not get rating; SDK encountered an unexpected error");
                new StringBuilder("SDK encountered unexpected error in getAdRating(); ").append(e.getMessage());
            }
            return 0.0f;
        }
        Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.getAdRating()");
        return 0.0f;
    }

    public final boolean isAppDownload() {
        if (com.inmobi.commons.a.a.a()) {
            try {
                if (this.f != null) {
                    AdContainer j = this.f.j();
                    if (j != null) {
                        ao aoVar = (ao) j.getDataModel();
                        if (aoVar != null) {
                            return aoVar.i.b.g;
                        }
                    }
                    return false;
                }
            } catch (Exception e) {
                Logger.a(InternalLogLevel.ERROR, a, "Could not get isAppDownload; SDK encountered unexpected error");
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
            return false;
        }
        Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.isAppDownload()");
        return false;
    }

    public final void reportAdClickAndOpenLandingPage() {
        if (com.inmobi.commons.a.a.a()) {
            try {
                if (this.f != null) {
                    AdContainer j = this.f.j();
                    if (j != null) {
                        ah ahVar = (ah) j;
                        ao h = ahVar.h();
                        if (h != null) {
                            ahVar.a(null, h.i.c);
                            ahVar.a(h.i.c, true);
                        }
                    }
                }
                return;
            } catch (Exception e) {
                Logger.a(InternalLogLevel.ERROR, a, "reportAdClickAndOpenLandingPage failed; SDK encountered unexpected error");
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return;
            }
        }
        Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.reportAdClickAndOpenLandingPage()");
    }

    public final boolean isReady() {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.isReady()");
            return false;
        } else if (this.f == null || !this.f.P()) {
            return false;
        } else {
            return true;
        }
    }

    public final JSONObject getAdMetaInfo() {
        if (!com.inmobi.commons.a.a.a() || this.f == null) {
            return new JSONObject();
        }
        return this.f.i;
    }

    public final void setExtras(Map<String, String> map) {
        if (com.inmobi.commons.a.a.a()) {
            try {
                if (this.f != null) {
                    this.f.f = map;
                }
                this.h = map;
                return;
            } catch (Exception e) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not set extras; SDK encountered an unexpected error");
                new StringBuilder("SDK encountered unexpected error in setting extras ").append(e.getMessage());
                return;
            }
        }
        Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.setExtras()");
    }

    public final void setKeywords(String str) {
        if (com.inmobi.commons.a.a.a()) {
            try {
                if (this.f != null) {
                    this.f.e = str;
                }
                this.g = str;
                return;
            } catch (Exception e) {
                Logger.a(InternalLogLevel.ERROR, a, "Could not set keywords on Native ad; SDK encountered unexpected error");
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                new StringBuilder("SDK encountered unexpected error in setting keywords; ").append(e.getMessage());
                return;
            }
        }
        Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.setKeywords()");
    }

    public final void destroy() {
        try {
            if (!com.inmobi.commons.a.a.a()) {
                Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized. Ignoring InMobiNative.destroy()");
            }
            if (this.b != null) {
                this.b.removeMessages(0);
            }
            View view = this.l == null ? null : (View) this.l.get();
            if (view != null) {
                ((ViewGroup) view).removeAllViews();
            }
            if (this.f != null) {
                this.f.O();
            }
            if (this.q != null) {
                this.q = null;
            }
            this.f = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.k = null;
            this.m = false;
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, a, "Failed to destroy ad; SDK encountered an unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }

    public final void setDownloaderEnabled(boolean z) {
        this.n = z;
    }

    public final Downloader getDownloader() {
        try {
            if (com.inmobi.commons.a.a.a()) {
                return this.k;
            }
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized. Ignoring InMobiNative.getDownloader()");
            return null;
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, a, "Failed to get Downloader; SDK encountered an unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return null;
        }
    }

    public final String getCreativeId() {
        return (!com.inmobi.commons.a.a.a() || this.f == null) ? "" : this.f.x;
    }

    private void a(String str, String str2) {
        if (this.q == null) {
            this.q = new k(this.f);
        }
        this.q.a(this.r, str, str2);
    }

    static /* synthetic */ boolean c() {
        return Message.obtain() == null;
    }
}
