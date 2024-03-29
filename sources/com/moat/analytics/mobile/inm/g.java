package com.moat.analytics.mobile.inm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.AudienceNetworkActivity;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

class g {
    WebView a;
    j b;
    final String c = String.format(Locale.ROOT, "_moatTracker%d", new Object[]{Integer.valueOf((int) (Math.random() * 1.0E8d))});
    private final a d;
    private boolean e = false;
    private Handler f;
    private Runnable g;

    enum a {
        DISPLAY,
        VIDEO
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    g(Context context, a aVar) {
        this.d = aVar;
        this.a = new WebView(context);
        WebSettings settings = this.a.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccess(false);
        settings.setDatabaseEnabled(false);
        settings.setDomStorageEnabled(false);
        settings.setGeolocationEnabled(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setSaveFormData(false);
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(1);
        }
        a aVar2 = a.NATIVE_DISPLAY;
        if (aVar == a.VIDEO) {
            aVar2 = a.NATIVE_VIDEO;
        }
        try {
            this.b = new j(this.a, aVar2);
        } catch (m e) {
            m.a(e);
        }
    }

    private static String a(String str, String str2, Integer num, Integer num2, JSONObject jSONObject, Integer num3) {
        return String.format(Locale.ROOT, "<html><head></head><body><div id=\"%s\" style=\"width: %dpx; height: %dpx;\"></div><script>(function initMoatTracking(apiname, pcode, ids, duration) {var events = [];window[pcode + '_moatElToTrack'] = document.getElementById('%s');var moatapi = {'dropTime':%d,'adData': {'ids': ids, 'duration': duration, 'url': 'n/a'},'dispatchEvent': function(ev) {if (this.sendEvent) {if (events) { events.push(ev); ev = events; events = false; }this.sendEvent(ev);} else {events.push(ev);}},'dispatchMany': function(evs){for (var i=0, l=evs.length; i<l; i++) {this.dispatchEvent(evs[i]);}}};Object.defineProperty(window, apiname, {'value': moatapi});var s = document.createElement('script');s.src = 'https://z.moatads.com/' + pcode + '/moatvideo.js?' + apiname + '#' + apiname;document.body.appendChild(s);})('%s', '%s', %s, %s);</script></body></html>", new Object[]{"mianahwvc", num, num2, "mianahwvc", Long.valueOf(System.currentTimeMillis()), str, str2, jSONObject.toString(), num3});
    }

    private static String b(String str) {
        StringBuilder stringBuilder = new StringBuilder("<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n   <meta charset=\"UTF-8\">\n   <title></title>\n</head>\n<body style=\"margin:0;padding:0;\">\n    <script src=\"https://z.moatads.com/");
        stringBuilder.append(str);
        stringBuilder.append("/moatad.js\" type=\"text/javascript\"></script>\n</body>\n</html>");
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: 0000 */
    public void a() {
        p.a(3, "GlobalWebView", (Object) this, "Cleaning up");
        this.b.b();
        this.b = null;
        this.a.destroy();
        this.a = null;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str) {
        if (this.d == a.DISPLAY) {
            this.a.setWebViewClient(new WebViewClient() {
                public void onPageFinished(WebView webView, String str) {
                    if (!g.this.e) {
                        try {
                            g.this.e = true;
                            g.this.b.a();
                        } catch (Exception e) {
                            m.a(e);
                        }
                    }
                }
            });
            this.a.loadData(b(str), "text/html", AudienceNetworkActivity.WEBVIEW_ENCODING);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str, Map<String, String> map, Integer num, Integer num2, Integer num3) {
        if (this.d == a.VIDEO) {
            if (VERSION.SDK_INT >= 19) {
                p.a(3, "GlobalWebView", (Object) this, "Starting off polling interval to check for Video API instance presence");
                this.f = new Handler();
                this.g = new Runnable() {
                    public void run() {
                        try {
                            if (g.this.a != null && VERSION.SDK_INT >= 19) {
                                WebView webView = g.this.a;
                                StringBuilder stringBuilder = new StringBuilder("typeof ");
                                stringBuilder.append(g.this.c);
                                stringBuilder.append(" !== 'undefined'");
                                webView.evaluateJavascript(stringBuilder.toString(), new ValueCallback<String>() {
                                    /* renamed from: a */
                                    public void onReceiveValue(String str) {
                                        if ("true".equals(str)) {
                                            p.a(3, "GlobalWebView", (Object) this, String.format("Video API instance %s detected. Flushing event queue", new Object[]{g.this.c}));
                                            try {
                                                g.this.e = true;
                                                g.this.b.a();
                                                g.this.b.c(g.this.c);
                                                return;
                                            } catch (Exception e) {
                                                m.a(e);
                                                return;
                                            }
                                        }
                                        g.this.f.postDelayed(g.this.g, 200);
                                    }
                                });
                            }
                        } catch (Exception e) {
                            m.a(e);
                        }
                    }
                };
                this.f.post(this.g);
            } else {
                StringBuilder stringBuilder = new StringBuilder("Android API version is less than KitKat: ");
                stringBuilder.append(VERSION.SDK_INT);
                p.a(3, "GlobalWebView", (Object) this, stringBuilder.toString());
                this.a.setWebViewClient(new WebViewClient() {
                    public void onPageFinished(WebView webView, String str) {
                        if (!g.this.e) {
                            p.a(3, "GlobalWebView", (Object) this, "onPageFinished is called for the first time. Flushing event queue");
                            try {
                                g.this.e = true;
                                g.this.b.a();
                                g.this.b.c(g.this.c);
                            } catch (Exception e) {
                                m.a(e);
                            }
                        }
                    }
                });
            }
            this.a.loadData(a(this.c, str, num, num2, new JSONObject(map), num3), "text/html", null);
        }
    }
}
