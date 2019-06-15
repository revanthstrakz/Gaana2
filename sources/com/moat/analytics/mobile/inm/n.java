package com.moat.analytics.mobile.inm;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.inm.a.b.a;
import java.lang.ref.WeakReference;
import java.util.Map;

class n extends MoatFactory {
    n() {
        if (!a()) {
            String str = "Failed to initialize MoatFactory";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(", SDK was not started");
            p.a("[ERROR] ", 3, "Factory", this, stringBuilder.toString());
            throw new m(str);
        }
    }

    private NativeDisplayTracker a(View view, final Map<String, String> map) {
        final WeakReference weakReference = new WeakReference(view);
        return (NativeDisplayTracker) x.a(new a<NativeDisplayTracker>() {
            public a<NativeDisplayTracker> a() {
                View view = (View) weakReference.get();
                StringBuilder stringBuilder = new StringBuilder("Attempting to create NativeDisplayTracker for ");
                stringBuilder.append(p.a(view));
                p.a("[INFO] ", 3, "Factory", this, stringBuilder.toString());
                return a.a(new t(view, map));
            }
        }, NativeDisplayTracker.class);
    }

    private NativeVideoTracker a(final String str) {
        return (NativeVideoTracker) x.a(new a<NativeVideoTracker>() {
            public a<NativeVideoTracker> a() {
                p.a("[INFO] ", 3, "Factory", this, "Attempting to create NativeVideoTracker");
                return a.a(new u(str));
            }
        }, NativeVideoTracker.class);
    }

    private WebAdTracker a(ViewGroup viewGroup) {
        final WeakReference weakReference = new WeakReference(viewGroup);
        return (WebAdTracker) x.a(new a<WebAdTracker>() {
            public a<WebAdTracker> a() {
                ViewGroup viewGroup = (ViewGroup) weakReference.get();
                StringBuilder stringBuilder = new StringBuilder("Attempting to create WebAdTracker for adContainer ");
                stringBuilder.append(p.a((View) viewGroup));
                p.a("[INFO] ", 3, "Factory", this, stringBuilder.toString());
                return a.a(new aa(viewGroup));
            }
        }, WebAdTracker.class);
    }

    private WebAdTracker a(WebView webView) {
        final WeakReference weakReference = new WeakReference(webView);
        return (WebAdTracker) x.a(new a<WebAdTracker>() {
            public a<WebAdTracker> a() {
                WebView webView = (WebView) weakReference.get();
                StringBuilder stringBuilder = new StringBuilder("Attempting to create WebAdTracker for ");
                stringBuilder.append(p.a((View) webView));
                p.a("[INFO] ", 3, "Factory", this, stringBuilder.toString());
                return a.a(new aa(webView));
            }
        }, WebAdTracker.class);
    }

    private <T> T a(MoatPlugin<T> moatPlugin) {
        return moatPlugin.a();
    }

    private boolean a() {
        return ((k) MoatAnalytics.getInstance()).a();
    }

    public <T> T createCustomTracker(MoatPlugin<T> moatPlugin) {
        try {
            return a((MoatPlugin) moatPlugin);
        } catch (Exception e) {
            m.a(e);
            return moatPlugin.b();
        }
    }

    public NativeDisplayTracker createNativeDisplayTracker(@NonNull View view, @NonNull Map<String, String> map) {
        try {
            return a(view, map);
        } catch (Exception e) {
            m.a(e);
            return new c();
        }
    }

    public NativeVideoTracker createNativeVideoTracker(String str) {
        try {
            return a(str);
        } catch (Exception e) {
            m.a(e);
            return new d();
        }
    }

    public WebAdTracker createWebAdTracker(@NonNull ViewGroup viewGroup) {
        try {
            return a(viewGroup);
        } catch (Exception e) {
            m.a(e);
            return new e();
        }
    }

    public WebAdTracker createWebAdTracker(@NonNull WebView webView) {
        try {
            return a(webView);
        } catch (Exception e) {
            m.a(e);
            return new e();
        }
    }
}
