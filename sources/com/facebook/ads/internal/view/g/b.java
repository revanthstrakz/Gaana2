package com.facebook.ads.internal.view.g;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.l.f;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.view.g.b.h;
import com.facebook.ads.internal.view.g.b.j;
import com.facebook.ads.internal.view.g.b.l;
import com.facebook.ads.internal.view.g.b.m;
import com.facebook.ads.internal.view.g.b.n;
import com.facebook.ads.internal.view.g.b.p;
import com.facebook.ads.internal.view.g.b.r;
import com.facebook.ads.internal.view.g.b.s;
import com.facebook.ads.internal.view.g.b.v;
import com.facebook.ads.internal.view.g.b.w;
import com.facebook.ads.internal.view.g.b.x;
import com.facebook.ads.internal.view.g.b.y;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class b extends c {
    public int a;
    private final w b;
    private final f<r> c;
    private final f<h> d;
    private final f<j> e;
    private final f<n> f;
    private final f<com.facebook.ads.internal.view.g.b.b> g;
    private final f<p> h;
    private final f<x> i;
    private final f<y> j;
    private final f<s> k;
    private final m l;
    private final a m;
    private boolean n;

    public b(Context context, c cVar, a aVar, String str) {
        this(context, cVar, aVar, new ArrayList(), str);
    }

    public b(Context context, c cVar, a aVar, String str, @Nullable Bundle bundle) {
        this(context, cVar, aVar, new ArrayList(), str, bundle, null);
    }

    public b(Context context, c cVar, a aVar, String str, @Nullable Map<String, String> map) {
        this(context, cVar, aVar, new ArrayList(), str, null, map);
    }

    public b(Context context, c cVar, a aVar, List<com.facebook.ads.internal.b.b> list, String str) {
        super(context, cVar, aVar, list, str);
        this.b = new w() {
            static final /* synthetic */ boolean a = true;

            static {
                Class cls = b.class;
            }

            public void a(v vVar) {
                if (!a && b.this == null) {
                    throw new AssertionError();
                } else if (b.this != null) {
                    b.this.e();
                }
            }
        };
        this.c = new f<r>() {
            static final /* synthetic */ boolean a = true;

            static {
                Class cls = b.class;
            }

            public Class<r> a() {
                return r.class;
            }

            public void a(r rVar) {
                if (!a && b.this == null) {
                    throw new AssertionError();
                } else if (b.this != null) {
                    b.this.f();
                }
            }
        };
        this.d = new f<h>() {
            static final /* synthetic */ boolean a = true;

            static {
                Class cls = b.class;
            }

            public Class<h> a() {
                return h.class;
            }

            public void a(h hVar) {
                if (!a && b.this == null) {
                    throw new AssertionError();
                } else if (b.this != null) {
                    b.this.h();
                }
            }
        };
        this.e = new f<j>() {
            static final /* synthetic */ boolean a = true;

            static {
                Class cls = b.class;
            }

            public Class<j> a() {
                return j.class;
            }

            public void a(j jVar) {
                if (!a && b.this == null) {
                    throw new AssertionError();
                } else if (b.this != null) {
                    if (b.this.n) {
                        b.this.i();
                    } else {
                        b.this.n = true;
                    }
                }
            }
        };
        this.f = new f<n>() {
            public Class<n> a() {
                return n.class;
            }

            public void a(n nVar) {
                int a = nVar.a();
                if (b.this.a <= 0 || a != b.this.m.getDuration() || b.this.m.getDuration() <= b.this.a) {
                    b.this.a(a);
                }
            }
        };
        this.g = new f<com.facebook.ads.internal.view.g.b.b>() {
            public Class<com.facebook.ads.internal.view.g.b.b> a() {
                return com.facebook.ads.internal.view.g.b.b.class;
            }

            public void a(com.facebook.ads.internal.view.g.b.b bVar) {
                int a = bVar.a();
                int b = bVar.b();
                if (b.this.a <= 0 || a != b || b <= b.this.a) {
                    b bVar2;
                    if (b >= a + 500) {
                        bVar2 = b.this;
                    } else if (b == 0) {
                        bVar2 = b.this;
                        a = b.this.a;
                    } else {
                        b.this.b(b);
                        return;
                    }
                    bVar2.b(a);
                }
            }
        };
        this.h = new f<p>() {
            public Class<p> a() {
                return p.class;
            }

            public void a(p pVar) {
                b.this.a(pVar.a(), pVar.b());
            }
        };
        this.i = new f<x>() {
            public Class<x> a() {
                return x.class;
            }

            public void a(x xVar) {
                b.this.b();
            }
        };
        this.j = new f<y>() {
            public Class<y> a() {
                return y.class;
            }

            public void a(y yVar) {
                b.this.c();
            }
        };
        this.k = new f<s>() {
            public Class<s> a() {
                return s.class;
            }

            public void a(s sVar) {
                b.this.a(b.this.j(), b.this.j());
            }
        };
        this.l = new m() {
            public void a(l lVar) {
                b.this.a = b.this.m.getDuration();
            }
        };
        this.n = false;
        this.m = aVar;
        this.m.getEventBus().a(this.b, this.f, this.c, this.e, this.d, this.g, this.h, this.i, this.j, this.l, this.k);
    }

    public b(Context context, c cVar, a aVar, List<com.facebook.ads.internal.b.b> list, String str, @Nullable Bundle bundle, @Nullable Map<String, String> map) {
        super(context, cVar, aVar, list, str, bundle, map);
        this.b = /* anonymous class already generated */;
        this.c = /* anonymous class already generated */;
        this.d = /* anonymous class already generated */;
        this.e = /* anonymous class already generated */;
        this.f = /* anonymous class already generated */;
        this.g = /* anonymous class already generated */;
        this.h = /* anonymous class already generated */;
        this.i = /* anonymous class already generated */;
        this.j = /* anonymous class already generated */;
        this.k = /* anonymous class already generated */;
        this.l = /* anonymous class already generated */;
        this.n = false;
        this.m = aVar;
        this.m.getEventBus().a(this.b, this.f, this.c, this.e, this.d, this.g, this.h, this.i, this.j, this.k);
    }

    public void a() {
        this.m.getStateHandler().post(new Runnable() {
            public void run() {
                b.this.m.getEventBus().b(b.this.b, b.this.f, b.this.c, b.this.e, b.this.d, b.this.g, b.this.h, b.this.i, b.this.j, b.this.l, b.this.k);
            }
        });
    }
}
