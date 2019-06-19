package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.comscore.streaming.Constants;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.j;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.j.d;
import com.til.colombia.android.internal.e;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

public class l extends t {
    private static final String c = "l";
    private static final int[] d = new int[]{-1, -6, -7, -8};
    private final String e = UUID.randomUUID().toString();
    private final AtomicBoolean f = new AtomicBoolean();
    private Context g;
    private u h;
    private String i;
    private String j;
    private long k;
    private k l;
    private v m;
    private WebView n;

    private static class a implements com.facebook.ads.internal.f.a {
        final WeakReference<WebView> a;
        final WeakReference<l> b;
        final WeakReference<u> c;
        final boolean d;
        final j e;

        private a(WebView webView, l lVar, u uVar, j jVar, boolean z) {
            this.a = new WeakReference(webView);
            this.b = new WeakReference(lVar);
            this.c = new WeakReference(uVar);
            this.e = jVar;
            this.d = z;
        }

        /* synthetic */ a(WebView webView, l lVar, u uVar, j jVar, boolean z, AnonymousClass1 anonymousClass1) {
            this(webView, lVar, uVar, jVar, z);
        }

        public void a() {
            if (this.a.get() != null) {
                ((WebView) this.a.get()).loadUrl(this.e.a());
            }
        }

        public void b() {
            if (this.c.get() != null && this.b.get() != null) {
                if (this.d) {
                    ((u) this.c.get()).a((t) this.b.get(), AdError.CACHE_ERROR);
                } else {
                    a();
                }
            }
        }
    }

    private static class b extends WebViewClient {
        boolean a = false;
        final WeakReference<l> b;
        final WeakReference<u> c;
        final AtomicBoolean d;
        final boolean e;

        b(l lVar, u uVar, AtomicBoolean atomicBoolean, boolean z) {
            this.b = new WeakReference(lVar);
            this.c = new WeakReference(uVar);
            this.d = atomicBoolean;
            this.e = z;
        }

        private void a() {
            this.d.set(true);
            if (this.c.get() != null && this.b.get() != null) {
                ((u) this.c.get()).a((t) this.b.get());
            }
        }

        private void a(WebResourceError webResourceError) {
            if (this.c.get() != null && this.b.get() != null) {
                if (this.e || !b(webResourceError)) {
                    ((u) this.c.get()).a((t) this.b.get(), AdError.CACHE_ERROR);
                } else {
                    a();
                }
            }
        }

        private boolean b(WebResourceError webResourceError) {
            if (webResourceError == null || VERSION.SDK_INT < 23) {
                return true;
            }
            for (int i : l.d) {
                if (webResourceError.getErrorCode() == i) {
                    return true;
                }
            }
            return false;
        }

        public void onPageFinished(WebView webView, String str) {
            this.a = true;
            a();
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (!b.this.a) {
                        b.this.a(null);
                    }
                }
            }, Constants.HEARTBEAT_STAGE_ONE_INTERVAL);
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            this.a = true;
            a(webResourceError);
        }
    }

    private void a(Context context, boolean z) {
        if (com.facebook.ads.internal.n.a.g(context)) {
            Log.d(c, "Playable Ads pre-caching is disabled.");
            this.f.set(true);
            this.h.a(this);
            return;
        }
        this.n = new WebView(context);
        this.n.getSettings().setCacheMode(1);
        this.n.setWebViewClient(new b(this, this.h, this.f, z));
        j j = this.l.e().j();
        com.facebook.ads.internal.f.b bVar = new com.facebook.ads.internal.f.b(context);
        bVar.a(this.l.a().b(), -1, -1);
        bVar.a(j.b(), -1, -1);
        bVar.a(new a(this.n, this, this.h, j, z, null));
    }

    private boolean a(boolean z) {
        j j = this.l.e().j();
        return (j == null || (z && j.g())) ? false : true;
    }

    private void b(Context context, final boolean z) {
        final com.facebook.ads.internal.f.b bVar = new com.facebook.ads.internal.f.b(context);
        bVar.a(this.l.e().a());
        bVar.a(this.l.e().g(), this.l.e().i(), this.l.e().h());
        bVar.a(this.l.a().b(), -1, -1);
        for (String a : this.l.f().d()) {
            bVar.a(a, -1, -1);
        }
        bVar.a(new com.facebook.ads.internal.f.a() {
            private void a(boolean z) {
                l.this.f.set(true);
                l.this.l.b(z ? bVar.b(l.this.l.e().a()) : l.this.l.e().a());
                if (l.this.a(false)) {
                    l.this.a(l.this.g, z);
                } else {
                    l.this.h.a(l.this);
                }
            }

            public void a() {
                a(true);
            }

            public void b() {
                if (z) {
                    l.this.h.a(l.this, AdError.CACHE_ERROR);
                } else {
                    a(false);
                }
            }
        });
    }

    private boolean d() {
        return a(true);
    }

    private void e() {
        LocalBroadcastManager.getInstance(this.g).registerReceiver(this.m, this.m.a());
    }

    private void f() {
        if (this.m != null) {
            try {
                LocalBroadcastManager.getInstance(this.g).unregisterReceiver(this.m);
            } catch (Exception unused) {
            }
        }
    }

    private String g() {
        if (this.a == null) {
            return null;
        }
        String urlPrefix = AdSettings.getUrlPrefix();
        if (urlPrefix == null || urlPrefix.isEmpty()) {
            urlPrefix = "https://www.facebook.com/audience_network/server_side_reward";
        } else {
            urlPrefix = String.format(Locale.US, "https://www.%s.facebook.com/audience_network/server_side_reward", new Object[]{urlPrefix});
        }
        Uri parse = Uri.parse(urlPrefix);
        Builder builder = new Builder();
        builder.scheme(parse.getScheme());
        builder.authority(parse.getAuthority());
        builder.path(parse.getPath());
        builder.query(parse.getQuery());
        builder.fragment(parse.getFragment());
        builder.appendQueryParameter("puid", this.a.getUserID());
        builder.appendQueryParameter("pc", this.a.getCurrency());
        builder.appendQueryParameter("ptid", this.e);
        builder.appendQueryParameter(e.A, this.i);
        return builder.build().toString();
    }

    public int a() {
        return this.l == null ? -1 : this.l.e().d();
    }

    public void a(Context context, u uVar, Map<String, Object> map, boolean z) {
        this.g = context;
        this.h = uVar;
        this.f.set(false);
        this.j = (String) map.get(AudienceNetworkActivity.PLACEMENT_ID);
        this.k = ((Long) map.get(AudienceNetworkActivity.REQUEST_TIME)).longValue();
        int k = ((d) map.get("definition")).k();
        this.i = this.j != null ? this.j.split("_")[0] : "";
        this.l = k.a((JSONObject) map.get("data"));
        this.l.a(k);
        if (!TextUtils.isEmpty(this.l.e().a()) || d()) {
            this.m = new v(this.e, this, uVar);
            e();
            if (d()) {
                a(context, z);
                return;
            } else {
                b(context, z);
                return;
            }
        }
        this.h.a(this, AdError.internalError(2003));
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0072  */
    public boolean b() {
        /*
        r5 = this;
        r0 = r5.f;
        r0 = r0.get();
        r1 = 0;
        if (r0 != 0) goto L_0x000a;
    L_0x0009:
        return r1;
    L_0x000a:
        r0 = r5.g();
        r2 = r5.l;
        r2.a(r0);
        r2 = new android.content.Intent;
        r3 = r5.g;
        r4 = com.facebook.ads.AudienceNetworkActivity.class;
        r2.<init>(r3, r4);
        r3 = "viewType";
        r4 = com.facebook.ads.internal.settings.a.a.REWARDED_VIDEO;
        r2.putExtra(r3, r4);
        r3 = "rewardedVideoAdDataBundle";
        r4 = r5.l;
        r2.putExtra(r3, r4);
        r3 = "uniqueId";
        r4 = r5.e;
        r2.putExtra(r3, r4);
        r3 = "rewardServerURL";
        r2.putExtra(r3, r0);
        r0 = "placementId";
        r3 = r5.j;
        r2.putExtra(r0, r3);
        r0 = "requestTime";
        r3 = r5.k;
        r2.putExtra(r0, r3);
        r0 = r5.b;
        r3 = -1;
        r4 = 1;
        if (r0 == r3) goto L_0x0060;
    L_0x004a:
        r0 = r5.g;
        r0 = r0.getContentResolver();
        r3 = "accelerometer_rotation";
        r0 = android.provider.Settings.System.getInt(r0, r3, r1);
        if (r0 == r4) goto L_0x0060;
    L_0x0058:
        r0 = "predefinedOrientationKey";
        r1 = r5.b;
    L_0x005c:
        r2.putExtra(r0, r1);
        goto L_0x006c;
    L_0x0060:
        r0 = r5.g;
        r0 = com.facebook.ads.internal.n.a.q(r0);
        if (r0 != 0) goto L_0x006c;
    L_0x0068:
        r0 = "predefinedOrientationKey";
        r1 = 6;
        goto L_0x005c;
    L_0x006c:
        r0 = r5.g;
        r0 = r0 instanceof android.app.Activity;
        if (r0 != 0) goto L_0x007c;
    L_0x0072:
        r0 = r2.getFlags();
        r1 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;
        r0 = r0 | r1;
        r2.setFlags(r0);
    L_0x007c:
        r0 = r5.g;
        r0.startActivity(r2);
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.adapters.l.b():boolean");
    }

    public void onDestroy() {
        f();
    }
}
