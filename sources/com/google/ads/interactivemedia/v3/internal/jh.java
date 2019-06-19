package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventListener;
import com.google.ads.interactivemedia.v3.impl.data.q;
import com.google.ads.interactivemedia.v3.internal.jc.b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class jh implements AdErrorListener, AdEventListener, com.google.ads.interactivemedia.v3.internal.iq.a {
    private static boolean g;
    private static jh i;
    private final jd a;
    private final a b;
    private final Context c;
    private View d;
    private String e;
    private final Set<View> f;
    private boolean h;
    private c j;

    protected static class a {
        protected a() {
        }

        /* Access modifiers changed, original: protected */
        public void a(String str, Context context) {
            a.a(str, context);
        }

        /* Access modifiers changed, original: protected */
        public String a() {
            return a.a();
        }

        /* Access modifiers changed, original: protected */
        public d a(h hVar, h hVar2, boolean z) {
            return d.a(hVar, hVar2, z);
        }

        /* Access modifiers changed, original: protected */
        public i a(String str, String str2) {
            return i.a(str, str2);
        }

        /* Access modifiers changed, original: protected */
        public e a(i iVar, WebView webView, String str) {
            return e.a(iVar, webView, str);
        }

        /* Access modifiers changed, original: protected */
        public c a(d dVar, e eVar) {
            return c.a(dVar, eVar);
        }
    }

    jh(jd jdVar, Context context) {
        this(jdVar, context, new a());
    }

    jh(jd jdVar, Context context, a aVar) {
        this.h = false;
        this.a = jdVar;
        this.b = aVar;
        this.c = context;
        this.f = new HashSet();
        i = this;
        if (g) {
            aVar.a(aVar.a(), context);
        }
    }

    public static void b() {
        if (!(i == null || g)) {
            i.b.a(i.b.a(), i.c);
        }
        g = true;
    }

    public static void c() {
        g = false;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public void d() {
        if (g && this.j == null && this.d != null) {
            d a = this.b.a(h.JAVASCRIPT, h.JAVASCRIPT, true);
            i a2 = this.b.a("Google1", this.b.a());
            a aVar = this.b;
            WebView b = this.a.b();
            Object obj = this.h ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE;
            StringBuilder stringBuilder = new StringBuilder(7 + String.valueOf(obj).length());
            stringBuilder.append("{ssai:");
            stringBuilder.append(obj);
            stringBuilder.append("}");
            this.j = this.b.a(a, aVar.a(a2, b, stringBuilder.toString()));
            this.j.a(this.d);
            for (View b2 : this.f) {
                this.j.b(b2);
            }
            if (!this.f.isEmpty()) {
                a(new ArrayList(this.f));
            }
            this.j.a();
        }
    }

    public static String e() {
        return new a().a();
    }

    public void b(View view) {
        this.d = view;
    }

    public void a(String str) {
        this.e = str;
    }

    public boolean f() {
        if (!g || this.j == null) {
            return false;
        }
        this.j.b();
        this.j = null;
        return true;
    }

    public void onAdError(AdErrorEvent adErrorEvent) {
        if (g && this.j != null) {
            this.j.b();
            this.j = null;
        }
    }

    public void onAdEvent(AdEvent adEvent) {
        if (g) {
            switch (adEvent.getType()) {
                case LOADED:
                case STARTED:
                    d();
                    return;
                case COMPLETED:
                case SKIPPED:
                    f();
                    return;
                default:
                    return;
            }
        }
    }

    public void c(View view) {
        if (!this.f.contains(view)) {
            this.f.add(view);
            if (this.j != null) {
                this.j.b(view);
                a(Arrays.asList(new View[]{view}));
            }
        }
    }

    public void g() {
        this.f.clear();
        if (this.j != null) {
            this.j.c();
            a(null);
        }
    }

    public void a(View view) {
        c(view);
    }

    public void a() {
        g();
    }

    private void a(List<View> list) {
        this.a.b(new jc(com.google.ads.interactivemedia.v3.internal.jc.a.omid, b.registerFriendlyObstructions, this.e, list != null ? q.builder().views(list).build() : null));
    }
}
