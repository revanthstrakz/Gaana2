package com.facebook.ads.internal;

import android.content.Context;
import android.os.Handler;
import com.comscore.utils.Constants;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.j;
import com.facebook.ads.internal.adapters.r;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.protocol.g;
import com.facebook.ads.internal.q.c;
import com.facebook.ads.internal.s.a.d;
import com.facebook.ads.internal.s.a.m;
import com.facebook.ads.internal.s.a.q;
import com.facebook.ads.internal.s.a.z;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class a implements com.facebook.ads.internal.q.c.a {
    private final Context a;
    private final String b;
    private final c c = new c(this.a);
    private final e d;
    private final AdSize e;
    private final int f;
    private boolean g;
    private final Handler h;
    private final Runnable i;
    private final com.facebook.ads.internal.o.c j;
    private a k;
    private com.facebook.ads.internal.j.c l;
    private String m;

    public interface a {
        void a(com.facebook.ads.internal.protocol.a aVar);

        void a(List<j> list);
    }

    private static final class b extends z<a> {
        public b(a aVar) {
            super(aVar);
        }

        public void run() {
            a aVar = (a) a();
            if (aVar != null) {
                if (com.facebook.ads.internal.s.e.a.a(aVar.a)) {
                    aVar.a();
                } else {
                    aVar.h.postDelayed(aVar.i, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
                }
            }
        }
    }

    static {
        d.a();
    }

    public a(Context context, String str, e eVar, AdSize adSize, int i) {
        this.a = context;
        this.b = str;
        this.d = eVar;
        this.e = adSize;
        this.f = i;
        this.c.a((com.facebook.ads.internal.q.c.a) this);
        this.g = true;
        this.h = new Handler();
        this.i = new b(this);
        this.j = com.facebook.ads.internal.o.d.a(this.a);
        com.facebook.ads.internal.k.a.a(this.a).a();
    }

    private List<j> d() {
        com.facebook.ads.internal.j.c cVar = this.l;
        final ArrayList arrayList = new ArrayList(cVar.d());
        for (com.facebook.ads.internal.j.a e = cVar.e(); e != null; e = cVar.e()) {
            AdAdapter a = com.facebook.ads.internal.adapters.d.a(AdPlacementType.NATIVE);
            if (a != null && a.getPlacementType() == AdPlacementType.NATIVE) {
                HashMap hashMap = new HashMap();
                hashMap.put("data", e.b());
                hashMap.put("definition", cVar.a());
                final j jVar = (j) a;
                jVar.a(this.a, new r() {
                    public void a(j jVar) {
                        arrayList.add(jVar);
                    }

                    public void a(j jVar, com.facebook.ads.internal.protocol.a aVar) {
                    }

                    public void b(j jVar) {
                    }

                    public void c(j jVar) {
                    }
                }, this.j, hashMap, NativeAdBase.getViewTraversalPredicate());
            }
        }
        return arrayList;
    }

    public void a() {
        try {
            g gVar = new g(this.a, null, null, null);
            this.c.a(new com.facebook.ads.internal.q.b(this.a, new com.facebook.ads.internal.k.c(this.a, false), this.b, this.e != null ? new m(this.e.getHeight(), this.e.getWidth()) : null, this.d, null, this.f, AdSettings.isTestMode(this.a), AdSettings.isChildDirected(), gVar, q.a(com.facebook.ads.internal.n.a.t(this.a)), this.m));
        } catch (com.facebook.ads.internal.protocol.b e) {
            a(com.facebook.ads.internal.protocol.a.a(e));
        }
    }

    public void a(a aVar) {
        this.k = aVar;
    }

    public void a(com.facebook.ads.internal.protocol.a aVar) {
        if (this.g) {
            this.h.postDelayed(this.i, Constants.SESSION_INACTIVE_PERIOD);
        }
        if (this.k != null) {
            this.k.a(aVar);
        }
    }

    public void a(com.facebook.ads.internal.q.g gVar) {
        com.facebook.ads.internal.j.c a = gVar.a();
        if (a == null) {
            throw new IllegalStateException("no placement in response");
        }
        if (this.g) {
            long c = a.a().c();
            if (c == 0) {
                c = Constants.SESSION_INACTIVE_PERIOD;
            }
            this.h.postDelayed(this.i, c);
        }
        this.l = a;
        List d = d();
        if (this.k != null) {
            if (d.isEmpty()) {
                this.k.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.NO_FILL, ""));
                return;
            }
            this.k.a(d);
        }
    }

    public void a(String str) {
        this.m = str;
    }

    public void b() {
    }

    public void c() {
        this.g = false;
        this.h.removeCallbacks(this.i);
    }
}
