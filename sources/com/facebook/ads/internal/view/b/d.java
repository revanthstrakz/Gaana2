package com.facebook.ads.internal.view.b;

import android.text.TextUtils;

public class d {
    private final f a;
    private boolean b = true;

    public d(f fVar) {
        this.a = fVar;
    }

    private static long a(String str, String str2) {
        str = str.substring(str2.length());
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        try {
            Long valueOf = Long.valueOf(Long.parseLong(str));
            return valueOf.longValue() < 0 ? -1 : valueOf.longValue();
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public void a() {
        if (!this.b) {
            return;
        }
        if (this.a.canGoBack() || this.a.canGoForward()) {
            this.b = false;
        } else {
            this.a.a("void((function() {try {  if (!window.performance || !window.performance.timing || !document ||       !document.body || document.body.scrollHeight <= 0 ||       !document.body.children || document.body.children.length < 1) {    return;  }  var nvtiming__an_t = window.performance.timing;  if (nvtiming__an_t.responseEnd > 0) {    console.log('ANNavResponseEnd:'+nvtiming__an_t.responseEnd);  }  if (nvtiming__an_t.domContentLoadedEventStart > 0) {    console.log('ANNavDomContentLoaded:' + nvtiming__an_t.domContentLoadedEventStart);  }  if (nvtiming__an_t.loadEventEnd > 0) {    console.log('ANNavLoadEventEnd:' + nvtiming__an_t.loadEventEnd);  }} catch(err) {  console.log('an_navigation_timing_error:' + err.message);}})());");
        }
    }

    public void a(String str) {
        if (!this.b) {
            return;
        }
        if (str.startsWith("ANNavResponseEnd:")) {
            this.a.a(a(str, "ANNavResponseEnd:"));
        } else if (str.startsWith("ANNavDomContentLoaded:")) {
            this.a.b(a(str, "ANNavDomContentLoaded:"));
        } else {
            if (str.startsWith("ANNavLoadEventEnd:")) {
                this.a.c(a(str, "ANNavLoadEventEnd:"));
            }
        }
    }

    public void a(boolean z) {
        this.b = z;
    }
}
