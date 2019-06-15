package com.j.a;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

final class e {
    WebView a = null;
    String b;
    CookieManager c = CookieManager.getInstance();
    Timer d;
    boolean e = false;
    boolean f = false;

    public class a {
        @JavascriptInterface
        public final String getJSONData() {
            StringBuilder stringBuilder;
            StringBuilder stringBuilder2 = new StringBuilder("---------getJSONData----->");
            stringBuilder2.append(e.this.b);
            String stringBuilder3 = stringBuilder2.toString();
            if (stringBuilder3.length() > 3900) {
                stringBuilder = new StringBuilder("<>");
                stringBuilder.append(stringBuilder3);
                stringBuilder3 = stringBuilder.toString();
                while (stringBuilder3.length() > 3900) {
                    stringBuilder3.substring(0, 3900);
                    stringBuilder3 = stringBuilder3.substring(3900);
                }
                stringBuilder = new StringBuilder();
            } else {
                stringBuilder = new StringBuilder("<>");
            }
            stringBuilder.append(stringBuilder3);
            stringBuilder.append("</>");
            return e.this.b != null ? e.this.b : "";
        }

        @JavascriptInterface
        public final void make_request(String str, String str2, String str3) {
            a.a(str, str2, str3);
        }

        @JavascriptInterface
        public final String ready(String str) {
            if (!e.this.e) {
                e.this.e = true;
                if (e.this.d != null) {
                    e.this.d.cancel();
                    e.this.d.purge();
                }
                if (str.toLowerCase().contains("beta_kill")) {
                    int i = 0;
                    str = a.e.getSharedPreferences("WOInspector", 0).getString("wossid", "");
                    a.f = str;
                    if (!str.equals("")) {
                        byte[] decode = Base64.decode(a.f, 0);
                        byte[] bytes = "wossid".getBytes();
                        byte[] bArr = new byte[decode.length];
                        while (i < decode.length) {
                            bArr[i] = (byte) (decode[i] ^ bytes[i % bytes.length]);
                            i++;
                        }
                        a.f = new String(bArr);
                    }
                    JSONObject jSONObject = new JSONObject();
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("TYPE", "KILL");
                        jSONObject2.put("PROXY_ORIGIN", a.d);
                        jSONObject.put("CI", a.a);
                        jSONObject.put("ID", a.f);
                        jSONObject.put("DECISION_EVENT", jSONObject2);
                        jSONObject.put("CT", a.b);
                    } catch (JSONException unused) {
                    }
                    jSONObject2 = new JSONObject();
                    try {
                        Iterator keys = jSONObject.keys();
                        while (keys.hasNext()) {
                            String str2 = (String) keys.next();
                            jSONObject2.put(str2, jSONObject.get(str2));
                        }
                        jSONObject2.put("DATA", "");
                        a.g.a(jSONObject2.toString());
                    } catch (JSONException unused2) {
                    }
                } else {
                    a.a();
                }
            }
            return "B1.0.5.01";
        }

        @JavascriptInterface
        public final void set_conf(String str) {
        }
    }

    @SuppressLint({"NewApi", "SetJavaScriptEnabled"})
    e() {
        this.c.setAcceptCookie(false);
        CookieManager.setAcceptFileSchemeCookies(false);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @SuppressLint({"AddJavascriptInterface"})
            public final void run() {
                try {
                    e.this.a = new WebView(a.e);
                    if (e.this.a instanceof WebView) {
                        e.this.a.getSettings().setJavaScriptEnabled(true);
                        e.this.a.setWebViewClient(new c());
                        e.this.a.addJavascriptInterface(new a(), "ozoki_nt");
                        e.this.c.setAcceptThirdPartyCookies(e.this.a, false);
                        return;
                    }
                    e.this.f = true;
                    e.this.a = null;
                } catch (Exception e) {
                    e.this.f = true;
                    e.this.a = null;
                    new StringBuilder("------##--------------->FAIL: webview creation throw exception: ").append(e.getMessage());
                }
            }
        });
    }

    /* Access modifiers changed, original: final */
    @SuppressLint({"SimpleDateFormat"})
    public final void a() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public final void run() {
                if (e.this.a != null) {
                    try {
                        String format = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(a.c);
                        stringBuilder.append("?ci=");
                        stringBuilder.append(a.a);
                        stringBuilder.append("&cb=");
                        stringBuilder.append(format);
                        stringBuilder.append("&sdk=b");
                        e.this.a.loadUrl(stringBuilder.toString());
                        return;
                    } catch (Exception e) {
                        e.this.f = true;
                        new StringBuilder("--------------------->FAIL initHTML. Throw exception: ").append(e.getMessage());
                        return;
                    }
                }
                e.this.f = true;
            }
        });
        b();
    }

    /* Access modifiers changed, original: final */
    public final void a(String str) {
        this.b = str;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public final void run() {
                if (e.this.a != null) {
                    try {
                        e.this.a.loadUrl("javascript:ozoki_zp.load(ozoki_nt.getJSONData())");
                    } catch (Exception e) {
                        new StringBuilder("------##---------------> WebView sendData Throw Exception: ").append(e.getMessage());
                    }
                }
            }
        });
    }

    /* Access modifiers changed, original: final */
    public final void b() {
        if (!this.f) {
            this.d = new Timer();
            this.d.schedule(new TimerTask() {
                public final void run() {
                    if (!e.this.e) {
                        String str = "";
                        try {
                            str = InetAddress.getByName(Uri.parse(a.c).getHost()).getHostAddress();
                        } catch (UnknownHostException unused) {
                        }
                        if (str.equals("")) {
                            e.this.b();
                        } else {
                            e.this.a();
                        }
                    }
                }
            }, 60000);
        }
    }
}
