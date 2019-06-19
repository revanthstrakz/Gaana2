package com.facebook.ads.internal.o;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.e.b;
import com.facebook.ads.internal.g.a;
import com.facebook.ads.internal.s.a.o;
import com.facebook.ads.internal.s.c.e;
import java.util.Map;

@UiThread
public class d implements c {
    private static final String a = "d";
    private static double b;
    private static String c;
    private static volatile boolean d;
    @Nullable
    @SuppressLint({"StaticFieldLeak"})
    private static c h;
    private final b e;
    private final com.facebook.ads.internal.g.d f;
    private final Context g;

    private d(Context context) {
        this.g = context.getApplicationContext();
        this.f = new com.facebook.ads.internal.g.d(context);
        this.e = new b(context, new g(context, this.f));
        this.e.b();
        b(context);
    }

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (d.class) {
            if (h == null) {
                h = new d(context.getApplicationContext());
            }
            cVar = h;
        }
        return cVar;
    }

    private void a(final a aVar) {
        if (aVar.g()) {
            this.f.a(aVar.a(), aVar.h().c, aVar.i().toString(), aVar.b(), aVar.c(), aVar.d(), aVar.e(), new a<String>() {
                public void a(int i, String str) {
                    super.a(i, str);
                }

                public void a(String str) {
                    super.a(str);
                    if (com.facebook.ads.internal.n.a.h(d.this.g)) {
                        b.a(d.this.g, aVar.i().toString(), str);
                    }
                    if (aVar.f()) {
                        d.this.e.a();
                    } else {
                        d.this.e.b();
                    }
                }
            });
            return;
        }
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Attempting to log an invalid ");
        stringBuilder.append(aVar.i());
        stringBuilder.append(" event.");
        Log.e(str, stringBuilder.toString());
    }

    private static synchronized void b(Context context) {
        synchronized (d.class) {
            if (d) {
                return;
            }
            com.facebook.ads.internal.k.a.a(context).a();
            o.a();
            b = o.b();
            c = o.c();
            d = true;
        }
    }

    public void a(String str) {
        new e(this.g).execute(new String[]{str});
    }

    public void a(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.IMMEDIATE).a(f.IMPRESSION).a(true).a());
        }
    }

    public void a(String str, Map<String, String> map, String str2, e eVar) {
        a(new a.a().a(str).a(b).b(c).a((Map) map).a(eVar).a(f.a(str2)).a(true).a());
    }

    public void b(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.IMMEDIATE).a(f.INVALIDATION).a(false).a());
        }
    }

    public void c(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.IMMEDIATE).a(f.OPEN_LINK).a(true).a());
        }
    }

    public void d(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.DEFERRED).a(f.OFF_TARGET_CLICK).a(true).a());
        }
    }

    public void e(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.IMMEDIATE).a(f.VIDEO).a(true).a());
        }
    }

    public void f(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.DEFERRED).a(f.NATIVE_VIEW).a(false).a());
        }
    }

    public void g(String str, Map<String, String> map) {
        a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.DEFERRED).a(f.BROWSER_SESSION).a(false).a());
    }

    public void h(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.IMMEDIATE).a(f.STORE).a(true).a());
        }
    }

    public void i(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.DEFERRED).a(f.CLOSE).a(true).a());
        }
    }

    public void j(String str, Map<String, String> map) {
        a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.IMMEDIATE).a(f.USER_RETURN).a(true).a());
    }

    public void k(String str, Map<String, String> map) {
        a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.DEFERRED).a(f.AD_REPORTING).a(false).a());
    }
}
