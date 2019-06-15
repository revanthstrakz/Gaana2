package com.facebook.ads.internal.view.f;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.j;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.a.p;
import com.facebook.ads.internal.s.a.w;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.s.c.e;
import com.facebook.ads.internal.view.component.f;
import com.facebook.ads.internal.view.component.i;
import com.facebook.ads.internal.view.g.b.z;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class b {
    private static final String a = "b";
    private static final int b = ((int) (4.0f * y.b));
    private static final int c = ((int) (72.0f * y.b));
    private static final int d = ((int) (8.0f * y.b));
    private com.facebook.ads.internal.view.component.a e;
    private final Context f;
    private final c g;
    private final k h;
    private final String i;
    private final d j;
    private final com.facebook.ads.internal.t.a k;
    private final w l;
    private Executor m = p.a;
    @Nullable
    private com.facebook.ads.internal.view.a.a n;
    @Nullable
    private com.facebook.ads.internal.view.c.a o;
    @Nullable
    private com.facebook.ads.internal.view.c.a.b p;

    public enum a {
        SCREENSHOTS,
        MARKUP,
        PLAYABLE,
        INFO
    }

    private static class b implements com.facebook.ads.internal.view.e.b.c {
        final WeakReference<b> a;

        private b(b bVar) {
            this.a = new WeakReference(bVar);
        }

        /* synthetic */ b(b bVar, AnonymousClass1 anonymousClass1) {
            this(bVar);
        }

        public void a() {
        }

        public void a(com.facebook.ads.internal.t.a aVar, w wVar) {
        }

        public void b() {
            if (this.a.get() != null) {
                ((b) this.a.get()).g();
            }
        }

        public void c() {
            b();
        }

        public void c(boolean z) {
            if (this.a.get() != null) {
                ((b) this.a.get()).i().performClick();
            }
        }
    }

    public b(Context context, c cVar, k kVar, com.facebook.ads.internal.view.a.a aVar, com.facebook.ads.internal.t.a aVar2, w wVar) {
        this.f = context;
        this.g = cVar;
        this.h = kVar;
        this.n = aVar;
        this.i = com.facebook.ads.internal.l.c.a(this.h.f().b());
        this.j = this.h.d().a();
        this.k = aVar2;
        this.l = wVar;
    }

    private void g() {
        if (this.n != null) {
            this.n.a(z.REWARDED_VIDEO_END_ACTIVITY.a());
        }
    }

    private View h() {
        i iVar = new i(this.f, this.j, true, false, false);
        iVar.a(this.h.b().a(), this.h.b().c(), false, true);
        iVar.setAlignment(17);
        com.facebook.ads.internal.view.component.a i = i();
        ImageView fVar = new f(this.f);
        y.a((View) fVar, 0);
        fVar.setRadius(50);
        new com.facebook.ads.internal.view.c.d(fVar).a().a(this.h.a().b());
        LinearLayout linearLayout = new LinearLayout(this.f);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.addView(fVar, new LayoutParams(c, c));
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.setMargins(0, d, 0, d);
        linearLayout.addView(iVar, layoutParams);
        linearLayout.addView(i, layoutParams);
        return linearLayout;
    }

    private com.facebook.ads.internal.view.component.a i() {
        if (this.e != null) {
            return this.e;
        }
        this.e = new com.facebook.ads.internal.view.component.a(this.f, true, false, z.REWARDED_VIDEO_AD_CLICK.a(), this.j, this.g, this.n, this.k, this.l);
        this.e.a(this.h.c(), this.h.g(), new HashMap());
        return this.e;
    }

    private View j() {
        RecyclerView recyclerView = new RecyclerView(this.f);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.f, 0, false));
        recyclerView.setAdapter(new c(this.h.f().d(), b));
        return recyclerView;
    }

    private View k() {
        this.p = new com.facebook.ads.internal.view.c.a.c() {
            public void a() {
                if (b.this.o != null && !TextUtils.isEmpty(b.this.h.f().c())) {
                    b.this.o.post(new Runnable() {
                        public void run() {
                            if (b.this.o == null || b.this.o.c()) {
                                Log.w(b.a, "Webview already destroyed, cannot activate");
                                return;
                            }
                            com.facebook.ads.internal.view.c.a f = b.this.o;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("javascript:");
                            stringBuilder.append(b.this.h.f().c());
                            f.loadUrl(stringBuilder.toString());
                        }
                    });
                }
            }

            public void a(String str, Map<String, String> map) {
                Uri parse = Uri.parse(str);
                if ("fbad".equals(parse.getScheme()) && parse.getAuthority().equals("close")) {
                    b.this.g();
                    return;
                }
                if ("fbad".equals(parse.getScheme()) && com.facebook.ads.internal.a.c.a(parse.getAuthority()) && b.this.n != null) {
                    b.this.n.a(z.REWARDED_VIDEO_AD_CLICK.a());
                }
                com.facebook.ads.internal.a.b a = com.facebook.ads.internal.a.c.a(b.this.f, b.this.g, b.this.h.g(), parse, map);
                if (a != null) {
                    try {
                        a.b();
                    } catch (Exception e) {
                        Log.e(b.a, "Error executing action", e);
                    }
                }
            }
        };
        this.o = new com.facebook.ads.internal.view.c.a(this.f, new WeakReference(this.p), 1);
        this.o.loadDataWithBaseURL(com.facebook.ads.internal.s.c.b.a(), this.i, "text/html", AudienceNetworkActivity.WEBVIEW_ENCODING, null);
        return this.o;
    }

    private View l() {
        return new com.facebook.ads.internal.view.e.b(this.f, this.h, this.g, this.n, new b(this, null), false);
    }

    public boolean a() {
        return b() == a.MARKUP;
    }

    public a b() {
        j j = this.h.e().j();
        return (j == null || !j.g()) ? !this.h.f().d().isEmpty() ? a.SCREENSHOTS : !TextUtils.isEmpty(this.i) ? a.MARKUP : a.INFO : a.PLAYABLE;
    }

    public Pair<a, View> c() {
        a b = b();
        switch (b) {
            case MARKUP:
                return new Pair(b, k());
            case SCREENSHOTS:
                return new Pair(b, j());
            case PLAYABLE:
                return new Pair(b, l());
            default:
                return new Pair(b, h());
        }
    }

    public void d() {
        if (!TextUtils.isEmpty(this.h.f().a())) {
            e eVar = new e(this.f, new HashMap());
            eVar.a(new com.facebook.ads.internal.s.c.e.a() {
                public void a() {
                    if (b.this.n != null) {
                        b.this.n.a(z.REWARD_SERVER_FAILED.a());
                    }
                }

                public void a(com.facebook.ads.internal.s.c.f fVar) {
                    if (b.this.n != null) {
                        com.facebook.ads.internal.view.a.a b;
                        z zVar;
                        if (fVar == null || !fVar.a()) {
                            b = b.this.n;
                            zVar = z.REWARD_SERVER_FAILED;
                        } else {
                            b = b.this.n;
                            zVar = z.REWARD_SERVER_SUCCESS;
                        }
                        b.a(zVar.a());
                    }
                }
            });
            eVar.executeOnExecutor(this.m, new String[]{r0});
        }
    }

    public void e() {
        if (this.o != null) {
            this.o.destroy();
            this.o = null;
            this.p = null;
        }
    }
}
