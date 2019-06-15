package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.Log;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdsLoader;
import com.google.ads.interactivemedia.v3.api.AdsLoader.AdsLoadedListener;
import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.ImaSdkFactory;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.api.StreamDisplayContainer;
import com.google.ads.interactivemedia.v3.api.StreamRequest;
import com.google.ads.interactivemedia.v3.api.player.ContentProgressProvider;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import com.google.ads.interactivemedia.v3.impl.data.o;
import com.payu.custombrowser.util.CBConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SortedSet;
import java.util.UUID;

public class ii implements AdsLoader {
    com.google.ads.interactivemedia.v3.internal.jd.b a;
    private final jd b;
    private final Context c;
    private final ix d;
    private final List<AdsLoadedListener> e;
    private final Map<String, AdsRequest> f;
    private final Map<String, StreamRequest> g;
    private final jh h;
    private kb i;
    private final Object j;
    private js k;
    private ImaSdkSettings l;
    private TestingConfiguration m;

    private class a extends AsyncTask<String, Void, String> {
        private final AdsRequest b;
        private final String c;

        public a(AdsRequest adsRequest, String str) {
            this.b = adsRequest;
            this.c = str;
        }

        /* Access modifiers changed, original: protected|varargs */
        /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x004f */
        /* JADX WARNING: Can't wrap try/catch for region: R(6:2|3|(1:5)|(2:7|(3:9|10|11))|12|13) */
        /* renamed from: a */
        public java.lang.String doInBackground(java.lang.String... r6) {
            /*
            r5 = this;
            r0 = 0;
            r6 = r6[r0];
            r0 = com.google.ads.interactivemedia.v3.internal.ii.this;
            r0 = r0.j;
            monitor-enter(r0);
            r1 = com.google.ads.interactivemedia.v3.internal.ii.this;	 Catch:{ all -> 0x0061 }
            r1 = r1.i;	 Catch:{ all -> 0x0061 }
            if (r1 != 0) goto L_0x0028;
        L_0x0012:
            r1 = com.google.ads.interactivemedia.v3.internal.ii.this;	 Catch:{ all -> 0x0061 }
            r2 = new com.google.ads.interactivemedia.v3.internal.kb;	 Catch:{ all -> 0x0061 }
            r3 = "a.3.10.2";
            r4 = com.google.ads.interactivemedia.v3.internal.ii.this;	 Catch:{ all -> 0x0061 }
            r4 = r4.c;	 Catch:{ all -> 0x0061 }
            r3 = com.google.ads.interactivemedia.v3.internal.ka.a(r3, r4);	 Catch:{ all -> 0x0061 }
            r2.<init>(r3);	 Catch:{ all -> 0x0061 }
            r1.i = r2;	 Catch:{ all -> 0x0061 }
        L_0x0028:
            if (r6 == 0) goto L_0x004f;
        L_0x002a:
            r1 = android.net.Uri.parse(r6);	 Catch:{ all -> 0x0061 }
            r2 = com.google.ads.interactivemedia.v3.internal.ii.this;	 Catch:{ all -> 0x0061 }
            r2 = r2.i;	 Catch:{ all -> 0x0061 }
            r2 = r2.b(r1);	 Catch:{ all -> 0x0061 }
            if (r2 == 0) goto L_0x004f;
        L_0x003a:
            r2 = com.google.ads.interactivemedia.v3.internal.ii.this;	 Catch:{ kc -> 0x004f }
            r2 = r2.i;	 Catch:{ kc -> 0x004f }
            r3 = com.google.ads.interactivemedia.v3.internal.ii.this;	 Catch:{ kc -> 0x004f }
            r3 = r3.c;	 Catch:{ kc -> 0x004f }
            r1 = r2.a(r1, r3);	 Catch:{ kc -> 0x004f }
            r1 = r1.toString();	 Catch:{ kc -> 0x004f }
            r6 = r1;
        L_0x004f:
            monitor-exit(r0);	 Catch:{ all -> 0x0061 }
            r0 = com.google.ads.interactivemedia.v3.internal.ii.this;
            r1 = new com.google.ads.interactivemedia.v3.internal.js;
            r2 = com.google.ads.interactivemedia.v3.internal.ii.this;
            r2 = r2.c;
            r1.<init>(r2);
            r0.k = r1;
            return r6;
        L_0x0061:
            r6 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x0061 }
            throw r6;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ii$a.doInBackground(java.lang.String[]):java.lang.String");
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public void onPostExecute(String str) {
            this.b.setAdTagUrl(str);
            ii.this.b.b(new jc(com.google.ads.interactivemedia.v3.internal.jc.a.adsLoader, com.google.ads.interactivemedia.v3.internal.jc.b.requestAds, this.c, o.create(this.b, ii.this.c(), ii.this.d(), ii.this.l, ii.this.e(), jr.a(ii.this.c, ii.this.m), ii.this.k)));
        }
    }

    public static abstract class b {
        public abstract int appVersion();

        public abstract String packageName();

        public static b create(int i, String str) {
            return new io(i, str);
        }
    }

    private class c extends AsyncTask<Void, Void, String> {
        private final StreamRequest b;
        private final String c;

        public c(StreamRequest streamRequest, String str) {
            this.b = streamRequest;
            this.c = str;
        }

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: a */
        public String doInBackground(Void... voidArr) {
            String a;
            synchronized (ii.this.j) {
                if (ii.this.i == null) {
                    ii.this.i = new kb(ka.a("a.3.10.2", ii.this.c));
                }
                a = ii.this.i.a().a(ii.this.c);
            }
            ii.this.k = new js(ii.this.c);
            return a;
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public void onPostExecute(String str) {
            ii.this.b.b(new jc(com.google.ads.interactivemedia.v3.internal.jc.a.adsLoader, com.google.ads.interactivemedia.v3.internal.jc.b.requestStream, this.c, o.createFromStreamRequest(this.b, ii.this.c(), ii.this.d(), ii.this.l, ii.this.e(), jr.a(ii.this.c, ii.this.m), str, ii.this.k)));
        }
    }

    public ii(Context context, Uri uri, ImaSdkSettings imaSdkSettings) {
        this(context, uri, imaSdkSettings, null);
        this.b.a();
    }

    public ii(Context context, Uri uri, ImaSdkSettings imaSdkSettings, TestingConfiguration testingConfiguration) {
        this(new jd(context, uri, imaSdkSettings, testingConfiguration), context);
        this.l = imaSdkSettings;
        this.m = testingConfiguration;
    }

    public ii(jd jdVar, Context context) {
        this.a = new com.google.ads.interactivemedia.v3.internal.jd.b() {
            public void a(String str, jf jfVar, List<Float> list, SortedSet<Float> sortedSet, boolean z) {
                AdError adError;
                AnonymousClass1 anonymousClass1 = this;
                String str2 = str;
                AdsRequest adsRequest = (AdsRequest) ii.this.f.get(str2);
                try {
                    ii iiVar = ii.this;
                    jd b = ii.this.b;
                    AdDisplayContainer adDisplayContainer = adsRequest.getAdDisplayContainer();
                    ContentProgressProvider contentProgressProvider = adsRequest.getContentProgressProvider();
                    jh c = ii.this.h;
                    Context d = ii.this.c;
                    ij ijVar = ijVar;
                    AdsManager adsManager = ijVar;
                    try {
                        ijVar = new ij(str2, b, jfVar, adDisplayContainer, contentProgressProvider, list, sortedSet, c, d, z);
                        iiVar.a(new ik(adsManager, adsRequest.getUserRequestContext()));
                    } catch (AdError e) {
                        adError = e;
                        anonymousClass1 = this;
                        ii.this.d.a(new id(adError, adsRequest.getUserRequestContext()));
                    }
                } catch (AdError e2) {
                    adError = e2;
                    ii.this.d.a(new id(adError, adsRequest.getUserRequestContext()));
                }
            }

            public void a(String str, jf jfVar, String str2, boolean z) {
                String str3 = str;
                StreamRequest streamRequest = (StreamRequest) ii.this.g.get(str3);
                ii.this.h.a(true);
                try {
                    ii.this.a(new ik(new jl(str3, ii.this.b, jfVar, streamRequest, ii.this.h, ii.this.c, str2, z), streamRequest.getUserRequestContext()));
                } catch (AdError e) {
                    ii.this.d.a(new id(e, streamRequest.getUserRequestContext()));
                }
            }

            public void a(String str, AdErrorType adErrorType, int i, String str2) {
                Object userRequestContext;
                if (ii.this.f.get(str) != null) {
                    userRequestContext = ((AdsRequest) ii.this.f.get(str)).getUserRequestContext();
                } else {
                    userRequestContext = ((StreamRequest) ii.this.g.get(str)).getUserRequestContext();
                }
                ii.this.d.a(new id(new AdError(adErrorType, i, str2), userRequestContext));
            }

            public void a(String str, AdErrorType adErrorType, AdErrorCode adErrorCode, String str2) {
                Object userRequestContext;
                if (ii.this.f.get(str) != null) {
                    userRequestContext = ((AdsRequest) ii.this.f.get(str)).getUserRequestContext();
                } else {
                    userRequestContext = ((StreamRequest) ii.this.g.get(str)).getUserRequestContext();
                }
                ii.this.d.a(new id(new AdError(adErrorType, adErrorCode, str2), userRequestContext));
            }
        };
        this.d = new ix();
        this.e = new ArrayList(1);
        this.f = new HashMap();
        this.g = new HashMap();
        this.j = new Object();
        this.b = jdVar;
        this.c = context;
        this.h = new jh(jdVar, context);
        this.l = ImaSdkFactory.getInstance().createImaSdkSettings();
    }

    public void a() {
        this.b.a();
    }

    public void contentComplete() {
        this.b.b(new jc(com.google.ads.interactivemedia.v3.internal.jc.a.adsLoader, com.google.ads.interactivemedia.v3.internal.jc.b.contentComplete, CBConstant.DEFAULT_PAYMENT_URLS));
    }

    public void requestAds(AdsRequest adsRequest) {
        a(adsRequest, b());
    }

    /* Access modifiers changed, original: 0000 */
    public void a(AdsRequest adsRequest, String str) {
        if (a(adsRequest)) {
            this.f.put(str, adsRequest);
            this.b.a(this.a, str);
            this.b.a(adsRequest.getAdDisplayContainer(), str);
            new a(adsRequest, str).execute(new String[]{adsRequest.getAdTagUrl()});
        }
    }

    public String requestStream(StreamRequest streamRequest) {
        String b = b();
        a(streamRequest, b);
        return b;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(StreamRequest streamRequest, String str) {
        if (a(streamRequest)) {
            this.g.put(str, streamRequest);
            this.b.a(this.a, str);
            this.b.a(streamRequest.getStreamDisplayContainer(), str);
            new c(streamRequest, str).execute(new Void[0]);
        }
    }

    private String b() {
        if (this.m == null || !this.m.ignoreStrictModeFalsePositives()) {
            return UUID.randomUUID().toString();
        }
        ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new Builder(threadPolicy).permitDiskReads().build());
        String uuid = UUID.randomUUID().toString();
        StrictMode.setThreadPolicy(threadPolicy);
        return uuid;
    }

    private boolean a(AdsRequest adsRequest) {
        if (adsRequest == null) {
            this.d.a(new id(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "AdsRequest cannot be null.")));
            return false;
        }
        AdDisplayContainer adDisplayContainer = adsRequest.getAdDisplayContainer();
        if (adDisplayContainer == null) {
            this.d.a(new id(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Ad display container must be provided in the AdsRequest.")));
            return false;
        } else if (adDisplayContainer.getAdContainer() == null) {
            this.d.a(new id(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Ad display container must have a UI container.")));
            return false;
        } else if (!ks.a(adsRequest.getAdTagUrl()) || !ks.a(adsRequest.getAdsResponse())) {
            return true;
        } else {
            this.d.a(new id(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Ad tag url must non-null and non empty.")));
            return false;
        }
    }

    private boolean a(StreamRequest streamRequest) {
        if (streamRequest == null) {
            this.d.a(new id(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "StreamRequest cannot be null.")));
            return false;
        }
        StreamDisplayContainer streamDisplayContainer = streamRequest.getStreamDisplayContainer();
        if (streamDisplayContainer == null) {
            this.d.a(new id(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Ad display container must be provided in the StreamRequest.")));
            return false;
        } else if (streamDisplayContainer.getVideoStreamPlayer() != null) {
            return true;
        } else {
            this.d.a(new id(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Stream requests must specify a player.")));
            return false;
        }
    }

    public ImaSdkSettings getSettings() {
        return this.l;
    }

    public void addAdsLoadedListener(AdsLoadedListener adsLoadedListener) {
        this.e.add(adsLoadedListener);
    }

    public void removeAdsLoadedListener(AdsLoadedListener adsLoadedListener) {
        this.e.remove(adsLoadedListener);
    }

    public void addAdErrorListener(AdErrorListener adErrorListener) {
        this.d.a(adErrorListener);
    }

    public void removeAdErrorListener(AdErrorListener adErrorListener) {
        this.d.b(adErrorListener);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(AdsManagerLoadedEvent adsManagerLoadedEvent) {
        for (AdsLoadedListener onAdsManagerLoaded : this.e) {
            onAdsManagerLoaded.onAdsManagerLoaded(adsManagerLoadedEvent);
        }
    }

    private String c() {
        return String.format("android%s:%s:%s", new Object[]{VERSION.RELEASE, "3.10.2", this.c.getPackageName()});
    }

    private String d() {
        if (this.c.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            Log.w("IMASDK", "Host application doesn't have ACCESS_NETWORK_STATE permission");
            return "android:0";
        }
        if (((ConnectivityManager) this.c.getSystemService("connectivity")).getActiveNetworkInfo() == null) {
            return "android:0";
        }
        return String.format(Locale.US, "android:%d:%d", new Object[]{Integer.valueOf(((ConnectivityManager) this.c.getSystemService("connectivity")).getActiveNetworkInfo().getType()), Integer.valueOf(((ConnectivityManager) this.c.getSystemService("connectivity")).getActiveNetworkInfo().getSubtype())});
    }

    private b e() {
        PackageManager packageManager = this.c.getPackageManager();
        ResolveInfo resolveActivity = packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.google.ads.interactivemedia.v3")), 65536);
        if (resolveActivity == null) {
            return null;
        }
        ActivityInfo activityInfo = resolveActivity.activityInfo;
        if (activityInfo == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(activityInfo.packageName, 0);
            if (packageInfo == null) {
                return null;
            }
            return b.create(packageInfo.versionCode, activityInfo.packageName);
        } catch (NameNotFoundException unused) {
            return null;
        }
    }
}
