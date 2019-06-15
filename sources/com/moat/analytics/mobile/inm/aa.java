package com.moat.analytics.mobile.inm;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.webkit.WebView;

class aa extends b implements WebAdTracker {
    aa(@Nullable ViewGroup viewGroup) {
        String str;
        StringBuilder stringBuilder;
        this((WebView) ab.a(viewGroup, false).c(null));
        if (viewGroup == null) {
            str = "Target ViewGroup is null";
            stringBuilder = new StringBuilder("WebAdTracker initialization not successful, ");
            stringBuilder.append(str);
            p.a("[ERROR] ", 3, "WebAdTracker", this, stringBuilder.toString());
            this.a = new m(str);
        }
        if (this.b == null) {
            str = "No WebView to track inside of ad container";
            stringBuilder = new StringBuilder("WebAdTracker initialization not successful, ");
            stringBuilder.append(str);
            p.a("[ERROR] ", 3, "WebAdTracker", this, stringBuilder.toString());
            this.a = new m(str);
        }
    }

    aa(@Nullable WebView webView) {
        super(webView, false, false);
        p.a(3, "WebAdTracker", (Object) this, "Initializing.");
        StringBuilder stringBuilder;
        if (webView == null) {
            String str = "WebView is null";
            stringBuilder = new StringBuilder("WebAdTracker initialization not successful, ");
            stringBuilder.append(str);
            p.a("[ERROR] ", 3, "WebAdTracker", this, stringBuilder.toString());
            this.a = new m(str);
            return;
        }
        try {
            super.a(webView);
            stringBuilder = new StringBuilder();
            stringBuilder.append(a());
            stringBuilder.append(" created for ");
            stringBuilder.append(g());
            p.a("[SUCCESS] ", stringBuilder.toString());
        } catch (m e) {
            this.a = e;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String a() {
        return "WebAdTracker";
    }
}
