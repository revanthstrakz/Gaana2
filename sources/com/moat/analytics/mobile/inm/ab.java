package com.moat.analytics.mobile.inm;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.inm.a.b.a;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

class ab {
    private static final LinkedHashSet<String> a = new LinkedHashSet();

    ab() {
    }

    @NonNull
    static a<WebView> a(ViewGroup viewGroup, boolean z) {
        if (viewGroup == null) {
            try {
                return a.a();
            } catch (Exception unused) {
                return a.a();
            }
        } else if (viewGroup instanceof WebView) {
            return a.a((WebView) viewGroup);
        } else {
            WebView webView;
            LinkedList linkedList = new LinkedList();
            linkedList.add(viewGroup);
            int i = 0;
            loop0:
            while (true) {
                webView = null;
                while (!linkedList.isEmpty() && i < 100) {
                    i++;
                    ViewGroup viewGroup2 = (ViewGroup) linkedList.poll();
                    int childCount = viewGroup2.getChildCount();
                    WebView webView2 = webView;
                    for (int i2 = 0; i2 < childCount; i2++) {
                        Object childAt = viewGroup2.getChildAt(i2);
                        if (childAt instanceof WebView) {
                            p.a(3, "WebViewHound", childAt, "Found WebView");
                            if (z || a(String.valueOf(childAt.hashCode()))) {
                                if (webView2 == null) {
                                    webView2 = (WebView) childAt;
                                } else {
                                    p.a(3, "WebViewHound", childAt, "Ambiguous ad container: multiple WebViews reside within it.");
                                    p.a("[ERROR] ", "WebAdTracker not created, ambiguous ad container: multiple WebViews reside within it");
                                }
                            }
                        }
                        if (childAt instanceof ViewGroup) {
                            linkedList.add((ViewGroup) childAt);
                        }
                    }
                    webView = webView2;
                }
            }
            return a.b(webView);
        }
    }

    private static boolean a(String str) {
        try {
            boolean add = a.add(str);
            if (a.size() > 50) {
                Iterator it = a.iterator();
                for (int i = 0; i < 25 && it.hasNext(); i++) {
                    it.next();
                    it.remove();
                }
            }
            p.a(3, "WebViewHound", null, add ? "Newly Found WebView" : "Already Found WebView");
            return add;
        } catch (Exception e) {
            m.a(e);
            return false;
        }
    }
}
