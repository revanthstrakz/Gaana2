package com.moat.analytics.mobile.inm;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.content.LocalBroadcastManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.comscore.utils.Storage;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.til.colombia.android.internal.e;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

class j {
    private int a = 0;
    private boolean b = false;
    private boolean c = false;
    private final AtomicBoolean d = new AtomicBoolean(false);
    private boolean e = false;
    private boolean f = false;
    private boolean g = false;
    @NonNull
    private final WeakReference<WebView> h;
    private final Map<b, String> i;
    private final LinkedList<String> j;
    private final long k;
    private final String l;
    private final List<String> m;
    private final a n;
    private final BroadcastReceiver o = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            try {
                j.this.d();
            } catch (Exception e) {
                m.a(e);
            }
            if (System.currentTimeMillis() - j.this.k > 30000) {
                j.this.i();
            }
        }
    };
    private final BroadcastReceiver p = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            try {
                j.this.e();
            } catch (Exception e) {
                m.a(e);
            }
        }
    };

    enum a {
        WEBVIEW,
        NATIVE_DISPLAY,
        NATIVE_VIDEO
    }

    j(WebView webView, a aVar) {
        this.h = new WeakReference(webView);
        this.n = aVar;
        this.j = new LinkedList();
        this.m = new ArrayList();
        this.i = new WeakHashMap();
        this.k = System.currentTimeMillis();
        this.l = String.format("javascript:(function(d,k){function l(){function d(a,b){var c=ipkn[b]||ipkn[kuea];if(c){var h=function(b){var c=b.b;c.ts=b.i;c.ticks=b.g;c.buffered=!0;a(c)};h(c.first);c.a.forEach(function(a){h(a)})}}function e(a){var b=a.a,c=a.c,h=a.b;a=a.f;var d=[];if(c)b[c]&&d.push(b[c].fn[0]);else for(key in b)if(b[key])for(var g=0,e=b[key].fn.length;g<e;g++)d.push(b[key].fn[g]);g=0;for(e=d.length;g<e;g++){var f=d[g];if('function'===typeof f)try{h?f(h):f()}catch(k){}a&&delete b[c]}}function f(a,b,c){'function'===typeof a&& (b===kuea&&c[b]?c[b].fn.push(a):c[b]={ts:+new Date,fn:[a]},c===yhgt&&d(a,b))}kuea=+new Date;iymv={};briz=!1;ewat=+new Date;bnkr=[];bjmk={};dptk={};uqaj={};ryup={};yhgt={};ipkn={};csif={};this.h=function(a){this.namespace=a.namespace;this.version=a.version;this.appName=a.appName;this.deviceOS=a.deviceOS;this.isNative=a.isNative;this.versionHash=a.versionHash;this.aqzx=a.aqzx;this.appId=a.appId;this.metadata=a};this.nvsj=function(a){briz||(f(a,ewat,iymv),briz=!0)};this.bpsy=function(a,b){var c=b||kuea; c!==kuea&&bjmk[c]||f(a,c,bjmk)};this.qmrv=function(a,b){var c=b||kuea;c!==kuea&&uqaj[c]||f(a,c,uqaj)};this.lgpr=function(a,b){f(a,b||kuea,yhgt)};this.hgen=function(a,b){f(a,b||kuea,csif)};this.xrnk=function(a){delete yhgt[a||kuea]};this.vgft=function(a){return dptk[a||kuea]||!1};this.lkpu=function(a){return ryup[a||kuea]||!1};this.crts=function(a){var b={a:iymv,b:a,c:ewat};briz?e(b):bnkr.push(a)};this.mqjh=function(a){var b=a||kuea;dptk[b]=!0;var c={a:bjmk,f:!0};b!==kuea&&(c.b=a,c.c=a);e(c)};this.egpw= function(a){var b=a||kuea;ryup[b]=!0;var c={a:uqaj,f:!0};b!==kuea&&(c.b=a,c.c=a);e(c)};this.sglu=function(a){var b=a.adKey||kuea,c={a:yhgt,b:a.event||a,g:1,i:+new Date,f:!1};b!==kuea&&(c.c=a.adKey);a=0<Object.keys(yhgt).length;if(!a||!this.isNative)if(ipkn[b]){var d=ipkn[b].a.slice(-1)[0]||ipkn[b].first;JSON.stringify(c.b)==JSON.stringify(d.b)?d.g+=1:(5<=ipkn[b].a.length&&ipkn[b].a.shift(),ipkn[b].a.push(c))}else ipkn[b]={first:c,a:[]};a&&e(c);return a};this.ucbx=function(a){e({c:a.adKey||kuea,a:csif, b:a.event,f:!1})}}'undefined'===typeof d.MoatMAK&&(d.MoatMAK=new l,d.MoatMAK.h(k),d.__zMoatInit__=!0)})(window,%s);", new Object[]{h()});
        if (d("Initialize")) {
            IntentFilter intentFilter = new IntentFilter("UPDATE_METADATA");
            IntentFilter intentFilter2 = new IntentFilter("UPDATE_VIEW_INFO");
            LocalBroadcastManager.getInstance(s.c()).registerReceiver(this.o, intentFilter);
            LocalBroadcastManager.getInstance(s.c()).registerReceiver(this.p, intentFilter2);
            d();
            i.a().a(s.c(), this);
            p.a(3, "JavaScriptBridge", (Object) this, "bridge initialization succeeded");
        }
    }

    private boolean a(WebView webView) {
        return webView.getSettings().getJavaScriptEnabled();
    }

    private void c() {
        for (Entry key : this.i.entrySet()) {
            if (((b) key.getKey()).e()) {
                g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.mqjh(\"%s\");}", new Object[]{((b) key.getKey()).e}));
            }
        }
    }

    private void d() {
        try {
            if (w.a().a != d.OFF) {
                if (!this.c) {
                    p.a(3, "JavaScriptBridge", (Object) this, "Attempting to establish communication (setting environment variables).");
                    this.c = true;
                }
                g(this.l);
            }
        } catch (Exception e) {
            p.a("JavaScriptBridge", (Object) this, "Attempt failed to establish communication (did not set environment variables).", e);
        }
    }

    private void d(b bVar) {
        p.a(3, "JavaScriptBridge", (Object) this, "Stopping view update loop");
        if (bVar != null) {
            i.a().a(bVar);
        }
    }

    private boolean d(String str) {
        WebView g = g();
        StringBuilder stringBuilder;
        if (g == null) {
            stringBuilder = new StringBuilder("WebView is null. Can't ");
            stringBuilder.append(str);
            p.a(6, "JavaScriptBridge", (Object) this, stringBuilder.toString());
            throw new m("WebView is null");
        } else if (a(g)) {
            return true;
        } else {
            stringBuilder = new StringBuilder("JavaScript is not enabled in the given WebView. Can't ");
            stringBuilder.append(str);
            p.a(6, "JavaScriptBridge", (Object) this, stringBuilder.toString());
            throw new m("JavaScript is not enabled in the WebView");
        }
    }

    @TargetApi(19)
    private void e() {
        try {
            if (w.a().a != d.OFF) {
                if (this.g) {
                    p.a(3, "JavaScriptBridge", (Object) this, "Can't send info, already cleaned up");
                    return;
                }
                if (f()) {
                    if (!this.b || g().getUrl() != null) {
                        if (g().getUrl() != null) {
                            this.b = true;
                        }
                        for (Entry key : this.i.entrySet()) {
                            b bVar = (b) key.getKey();
                            if (bVar == null || bVar.f() == null) {
                                p.a(3, "JavaScriptBridge", (Object) this, "Tracker has no subject");
                                if (bVar != null) {
                                    if (!bVar.f) {
                                    }
                                }
                                c(bVar);
                            }
                            if (bVar.e()) {
                                if (!this.d.get()) {
                                    g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.mqjh(\"%s\");}", new Object[]{bVar.e}));
                                }
                                String format = String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.sglu(%s);}", new Object[]{bVar.h()});
                                if (VERSION.SDK_INT >= 19) {
                                    g().evaluateJavascript(format, new ValueCallback<String>() {
                                        /* renamed from: a */
                                        public void onReceiveValue(String str) {
                                            if (str == null || str.equalsIgnoreCase("null") || str.equalsIgnoreCase(InternalLogger.EVENT_PARAM_EXTRAS_FALSE)) {
                                                String str2;
                                                String str3 = "JavaScriptBridge";
                                                Object obj = j.this;
                                                StringBuilder stringBuilder = new StringBuilder("Received value is:");
                                                if (str == null) {
                                                    str2 = "null";
                                                } else {
                                                    StringBuilder stringBuilder2 = new StringBuilder("(String)");
                                                    stringBuilder2.append(str);
                                                    str2 = stringBuilder2.toString();
                                                }
                                                stringBuilder.append(str2);
                                                p.a(3, str3, obj, stringBuilder.toString());
                                                if (j.this.a >= 150) {
                                                    p.a(3, "JavaScriptBridge", j.this, "Giving up on finding ad");
                                                    j.this.b();
                                                }
                                                j.this.a = j.this.a + 1;
                                                if (!(str == null || !str.equalsIgnoreCase(InternalLogger.EVENT_PARAM_EXTRAS_FALSE) || j.this.e)) {
                                                    j.this.e = true;
                                                    p.a(3, "JavaScriptBridge", j.this, "Bridge connection established");
                                                }
                                            } else if (str.equalsIgnoreCase("true")) {
                                                if (!j.this.f) {
                                                    j.this.f = true;
                                                    p.a(3, "JavaScriptBridge", j.this, "Javascript has found ad");
                                                    j.this.a();
                                                }
                                                j.this.a = 0;
                                            } else {
                                                Object obj2 = j.this;
                                                StringBuilder stringBuilder3 = new StringBuilder("Received unusual value from Javascript:");
                                                stringBuilder3.append(str);
                                                p.a(3, "JavaScriptBridge", obj2, stringBuilder3.toString());
                                            }
                                        }
                                    });
                                } else {
                                    g().loadUrl(format);
                                }
                            }
                        }
                        return;
                    }
                }
                String str = "JavaScriptBridge";
                StringBuilder stringBuilder = new StringBuilder("WebView became null");
                stringBuilder.append(g() == null ? "" : "based on null url");
                stringBuilder.append(", stopping tracking loop");
                p.a(3, str, (Object) this, stringBuilder.toString());
                b();
            }
        } catch (Exception e) {
            m.a(e);
            b();
        }
    }

    private void e(String str) {
        if (this.m.size() >= 50) {
            this.m.subList(0, 25).clear();
        }
        this.m.add(str);
    }

    private void f(String str) {
        if (this.d.get()) {
            g(str);
        } else {
            e(str);
        }
    }

    private boolean f() {
        return g() != null;
    }

    private WebView g() {
        return (WebView) this.h.get();
    }

    @UiThread
    private void g(String str) {
        if (this.g) {
            p.a(3, "JavaScriptBridge", (Object) this, "Can't send, already cleaned up");
            return;
        }
        if (f()) {
            p.b(2, "JavaScriptBridge", this, str);
            if (VERSION.SDK_INT >= 19) {
                g().evaluateJavascript(str, null);
                return;
            }
            g().loadUrl(str);
        }
    }

    private String h() {
        try {
            a d = s.d();
            b e = s.e();
            HashMap hashMap = new HashMap();
            String a = d.a();
            String b = d.b();
            String c = d.c();
            String num = Integer.toString(VERSION.SDK_INT);
            String b2 = s.b();
            Object obj = this.n == a.WEBVIEW ? "0" : "1";
            Object obj2 = e.e ? "1" : "0";
            Object obj3 = e.d ? "1" : "0";
            Object obj4 = ((k) MoatAnalytics.getInstance()).b() ? "0" : "1";
            hashMap.put("versionHash", "c334ae83accfebb8da23104450c896463c9cfab7");
            hashMap.put(Storage.APP_NAME_KEY, a);
            hashMap.put("namespace", "INM");
            hashMap.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, "2.5.0");
            hashMap.put("deviceOS", num);
            hashMap.put("isNative", obj);
            hashMap.put("appId", b);
            hashMap.put(ShareConstants.FEED_SOURCE_PARAM, c);
            hashMap.put(e.w, e.b);
            hashMap.put("sim", e.a);
            hashMap.put("phone", String.valueOf(e.c));
            hashMap.put("buildFp", Build.FINGERPRINT);
            hashMap.put("buildModel", Build.MODEL);
            hashMap.put("buildMfg", Build.MANUFACTURER);
            hashMap.put("buildBrand", Build.BRAND);
            hashMap.put("buildProduct", Build.PRODUCT);
            hashMap.put("buildTags", Build.TAGS);
            hashMap.put("f1", obj3);
            hashMap.put("f2", obj2);
            hashMap.put("locationEnabled", obj4);
            if (b2 != null) {
                hashMap.put("aqzx", b2);
            }
            return new JSONObject(hashMap).toString();
        } catch (Exception unused) {
            return "{}";
        }
    }

    private void i() {
        p.a(3, "JavaScriptBridge", (Object) this, "Stopping metadata reporting loop");
        i.a().a(this);
        LocalBroadcastManager.getInstance(s.c()).unregisterReceiver(this.o);
    }

    /* Access modifiers changed, original: 0000 */
    public void a() {
        p.a(3, "JavaScriptBridge", (Object) this, "webViewReady");
        if (this.d.compareAndSet(false, true)) {
            p.a(3, "JavaScriptBridge", (Object) this, "webViewReady first time");
            i();
            for (String g : this.m) {
                g(g);
            }
            this.m.clear();
        }
        c();
    }

    /* Access modifiers changed, original: 0000 */
    public void a(b bVar) {
        if (bVar != null) {
            StringBuilder stringBuilder = new StringBuilder("adding tracker");
            stringBuilder.append(bVar.e);
            p.a(3, "JavaScriptBridge", (Object) this, stringBuilder.toString());
            this.i.put(bVar, "");
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str) {
        f(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.crts(%s);}", new Object[]{str}));
    }

    /* Access modifiers changed, original: 0000 */
    @UiThread
    public void a(String str, JSONObject jSONObject) {
        if (this.g) {
            p.a(3, "JavaScriptBridge", (Object) this, "Can't dispatch, already cleaned up");
            return;
        }
        String jSONObject2 = jSONObject.toString();
        if (this.d.get() && f()) {
            g(String.format("javascript:%s.dispatchEvent(%s);", new Object[]{str, jSONObject2}));
            return;
        }
        this.j.add(jSONObject2);
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        p.a(3, "JavaScriptBridge", (Object) this, "Cleaning up");
        this.g = true;
        i();
        for (Entry key : this.i.entrySet()) {
            d((b) key.getKey());
        }
        this.i.clear();
        LocalBroadcastManager.getInstance(s.c()).unregisterReceiver(this.p);
    }

    /* Access modifiers changed, original: 0000 */
    public void b(b bVar) {
        if (d("startTracking")) {
            StringBuilder stringBuilder = new StringBuilder("Starting tracking on tracker");
            stringBuilder.append(bVar.e);
            p.a(3, "JavaScriptBridge", (Object) this, stringBuilder.toString());
            g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.mqjh(\"%s\");}", new Object[]{bVar.e}));
            i.a().a(s.c(), bVar);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b(String str) {
        StringBuilder stringBuilder = new StringBuilder("markUserInteractionEvent:");
        stringBuilder.append(str);
        p.a(3, "JavaScriptBridge", (Object) this, stringBuilder.toString());
        f(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.ucbx(%s);}", new Object[]{str}));
    }

    /* Access modifiers changed, original: 0000 */
    public void c(b bVar) {
        m mVar = null;
        if (!this.g) {
            try {
                if (d("stopTracking")) {
                    try {
                        StringBuilder stringBuilder = new StringBuilder("Ending tracking on tracker");
                        stringBuilder.append(bVar.e);
                        p.a(3, "JavaScriptBridge", (Object) this, stringBuilder.toString());
                        g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.egpw(\"%s\");}", new Object[]{bVar.e}));
                    } catch (Exception e) {
                        p.a("JavaScriptBridge", (Object) this, "Failed to end impression.", e);
                    }
                }
            } catch (m e2) {
                mVar = e2;
            }
            if (this.n == a.NATIVE_DISPLAY) {
                d(bVar);
            } else {
                b();
            }
            this.i.remove(bVar);
        }
        if (mVar != null) {
            throw mVar;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void c(String str) {
        int i;
        p.a(3, "JavaScriptBridge", (Object) this, "flushDispatchQueue");
        if (this.j.size() >= 200) {
            int i2;
            LinkedList linkedList = new LinkedList();
            for (i2 = 0; i2 < 10; i2++) {
                linkedList.addFirst((String) this.j.removeFirst());
            }
            i2 = Math.min(Math.min(this.j.size() / 200, 10) + 200, this.j.size());
            for (i = 0; i < i2; i++) {
                this.j.removeFirst();
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                this.j.addFirst((String) it.next());
            }
        }
        if (!this.j.isEmpty()) {
            String str2 = "javascript:%s.dispatchMany([%s])";
            StringBuilder stringBuilder = new StringBuilder();
            String str3 = "";
            i = 1;
            while (!this.j.isEmpty() && i < 200) {
                i++;
                String str4 = (String) this.j.removeFirst();
                if (stringBuilder.length() + str4.length() > 2000) {
                    break;
                }
                stringBuilder.append(str3);
                stringBuilder.append(str4);
                str3 = ",";
            }
            g(String.format(str2, new Object[]{str, stringBuilder.toString()}));
        }
        this.j.clear();
    }

    /* Access modifiers changed, original: protected */
    public void finalize() {
        try {
            super.finalize();
            p.a(3, "JavaScriptBridge", (Object) this, "finalize");
            b();
        } catch (Exception e) {
            m.a(e);
        }
    }
}
