package com.facebook.ads.internal.p;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.internal.DisplayAdController;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.j;
import com.facebook.ads.internal.adapters.q;
import com.facebook.ads.internal.adapters.r;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.s.a.w;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.v;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.WeakHashMap;

public class e {
    private static final String b = "e";
    private static WeakHashMap<View, WeakReference<e>> c = new WeakHashMap();
    private boolean A;
    private boolean B;
    private long C;
    @Nullable
    private com.facebook.ads.internal.view.c.c D;
    private d E;
    private com.facebook.ads.internal.adapters.q.a F;
    private String G;
    private View H;
    @Nullable
    protected j a;
    private final Context d;
    private final String e;
    private final String f;
    private final com.facebook.ads.internal.f.b g;
    @Nullable
    private h h;
    private final c i;
    private DisplayAdController j;
    private volatile boolean k;
    @Nullable
    private d l;
    private com.facebook.ads.internal.protocol.e m;
    @Nullable
    private View n;
    private final List<View> o;
    private OnTouchListener p;
    private com.facebook.ads.internal.t.a q;
    private com.facebook.ads.internal.t.a.a r;
    private WeakReference<com.facebook.ads.internal.t.a.a> s;
    private final w t;
    @Nullable
    private q u;
    private a v;
    private com.facebook.ads.internal.view.w w;
    private k x;
    private boolean y;
    private boolean z;

    public interface c {
        boolean a(View view);
    }

    private class a implements OnClickListener, OnLongClickListener, OnTouchListener {
        private a() {
        }

        /* synthetic */ a(e eVar, AnonymousClass1 anonymousClass1) {
            this();
        }

        /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:13:0x0047 in {2, 9, 11, 12, 16, 19, 22, 23} preds:[]
            	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:242)
            	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
            	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:42)
            	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
            	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
            	at java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
            	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
            	at java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
            	at jadx.core.ProcessClass.process(ProcessClass.java:32)
            	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
            	at java.lang.Iterable.forEach(Unknown Source)
            	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
            	at jadx.core.ProcessClass.process(ProcessClass.java:37)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
            */
        public void onClick(android.view.View r5) {
            /*
            r4 = this;
            r5 = com.facebook.ads.internal.p.e.this;
            r5 = r5.t;
            r5 = r5.d();
            if (r5 != 0) goto L_0x0013;
            r5 = "FBAudienceNetworkLog";
            r0 = "No touch data recorded, please ensure touch events reach the ad View by returning false if you intercept the event.";
            android.util.Log.e(r5, r0);
            r5 = com.facebook.ads.internal.p.e.this;
            r5 = r5.d;
            r5 = com.facebook.ads.internal.n.a.s(r5);
            if (r5 < 0) goto L_0x0048;
            r0 = com.facebook.ads.internal.p.e.this;
            r0 = r0.t;
            r0 = r0.c();
            r2 = (long) r5;
            r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r5 >= 0) goto L_0x0048;
            r5 = com.facebook.ads.internal.p.e.this;
            r5 = r5.t;
            r5 = r5.b();
            if (r5 != 0) goto L_0x0042;
            r5 = "FBAudienceNetworkLog";
            r0 = "Ad cannot be clicked before it is viewed.";
            android.util.Log.e(r5, r0);
            return;
            r5 = "FBAudienceNetworkLog";
            r0 = "Clicks happened too fast.";
            goto L_0x003e;
            return;
            r5 = new java.util.HashMap;
            r5.<init>();
            r0 = "touch";
            r1 = com.facebook.ads.internal.p.e.this;
            r1 = r1.t;
            r1 = r1.e();
            r1 = com.facebook.ads.internal.s.a.k.a(r1);
            r5.put(r0, r1);
            r0 = com.facebook.ads.internal.p.e.this;
            r0 = r0.x;
            if (r0 == 0) goto L_0x007b;
            r0 = "nti";
            r1 = com.facebook.ads.internal.p.e.this;
            r1 = r1.x;
            r1 = r1.c();
            r1 = java.lang.String.valueOf(r1);
            r5.put(r0, r1);
            r0 = com.facebook.ads.internal.p.e.this;
            r0 = r0.y;
            if (r0 == 0) goto L_0x0092;
            r0 = "nhs";
            r1 = com.facebook.ads.internal.p.e.this;
            r1 = r1.y;
            r1 = java.lang.String.valueOf(r1);
            r5.put(r0, r1);
            r0 = com.facebook.ads.internal.p.e.this;
            r0 = r0.q;
            r0.a(r5);
            r0 = com.facebook.ads.internal.p.e.this;
            r0 = r0.a;
            if (r0 == 0) goto L_0x00a8;
            r0 = com.facebook.ads.internal.p.e.this;
            r0 = r0.a;
            r0.b(r5);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.p.e$a.onClick(android.view.View):void");
        }

        public boolean onLongClick(View view) {
            if (e.this.n == null || e.this.D == null) {
                return false;
            }
            e.this.D.setBounds(0, 0, e.this.n.getWidth(), e.this.n.getHeight());
            e.this.D.a(e.this.D.a() ^ 1);
            return true;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            e.this.t.a(motionEvent, e.this.n, view);
            return e.this.p != null && e.this.p.onTouch(view, motionEvent);
        }
    }

    private class b extends com.facebook.ads.internal.adapters.c {
        private b() {
        }

        /* synthetic */ b(e eVar, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void a() {
            if (e.this.h != null) {
                e.this.h.d();
            }
        }

        public void b() {
        }
    }

    public e(Context context, j jVar, d dVar, c cVar) {
        this(context, null, cVar);
        this.a = jVar;
        this.l = dVar;
        this.k = true;
        this.H = new View(context);
    }

    public e(Context context, String str, c cVar) {
        this.f = UUID.randomUUID().toString();
        this.m = com.facebook.ads.internal.protocol.e.NATIVE_UNKNOWN;
        this.o = new ArrayList();
        this.t = new w();
        this.z = false;
        this.A = false;
        this.E = d.ALL;
        this.F = com.facebook.ads.internal.adapters.q.a.ALL;
        this.d = context;
        this.e = str;
        this.i = cVar;
        this.g = new com.facebook.ads.internal.f.b(context);
        this.H = new View(context);
    }

    public e(e eVar) {
        this(eVar.d, null, eVar.i);
        this.l = eVar.l;
        this.a = eVar.a;
        this.k = true;
        this.H = new View(this.d);
    }

    private AdPlacementType J() {
        return this.m == com.facebook.ads.internal.protocol.e.NATIVE_UNKNOWN ? AdPlacementType.NATIVE : AdPlacementType.NATIVE_BANNER;
    }

    private boolean K() {
        return this.a != null && this.a.M();
    }

    private int L() {
        d dVar;
        if (this.l != null) {
            dVar = this.l;
        } else if (this.j == null || this.j.a() == null) {
            return 1;
        } else {
            dVar = this.j.a();
        }
        return dVar.f();
    }

    private int M() {
        d dVar;
        if (this.l != null) {
            dVar = this.l;
        } else if (this.j == null || this.j.a() == null) {
            return 0;
        } else {
            dVar = this.j.a();
        }
        return dVar.g();
    }

    private int N() {
        return this.l != null ? this.l.h() : this.a != null ? this.a.k() : (this.j == null || this.j.a() == null) ? 0 : this.j.a().h();
    }

    private int O() {
        return this.l != null ? this.l.i() : this.a != null ? this.a.l() : (this.j == null || this.j.a() == null) ? 1000 : this.j.a().i();
    }

    private boolean P() {
        return D() == l.ON;
    }

    private void Q() {
        for (View view : this.o) {
            view.setOnClickListener(null);
            view.setOnTouchListener(null);
            view.setOnLongClickListener(null);
        }
        this.o.clear();
    }

    private void a(View view) {
        this.o.add(view);
        view.setOnClickListener(this.v);
        view.setOnTouchListener(this.v);
        if (com.facebook.ads.internal.n.a.b(view.getContext())) {
            view.setOnLongClickListener(this.v);
        }
    }

    private void a(@Nullable final j jVar, final boolean z) {
        if (jVar != null) {
            if (this.E.equals(d.ALL)) {
                if (jVar.m() != null) {
                    this.g.a(jVar.m().a(), jVar.m().c(), jVar.m().b());
                }
                if (jVar.n() != null) {
                    this.g.a(jVar.n().a(), jVar.n().c(), jVar.n().b());
                }
                if (jVar.H() != null) {
                    for (e eVar : jVar.H()) {
                        if (eVar.j() != null) {
                            this.g.a(eVar.j().a(), eVar.j().c(), eVar.j().b());
                        }
                    }
                }
                if (!TextUtils.isEmpty(jVar.D())) {
                    this.g.a(jVar.D());
                }
            }
            this.g.a(new com.facebook.ads.internal.f.a() {
                public void a() {
                    e.this.a = jVar;
                    if (e.this.h != null) {
                        if (e.this.E.equals(d.ALL) && !e.this.K()) {
                            e.this.h.a();
                        }
                        if (z) {
                            e.this.h.b();
                        }
                    }
                }

                public void b() {
                    if (e.this.a != null) {
                        e.this.a.d();
                        e.this.a = null;
                    }
                    if (e.this.h != null) {
                        e.this.h.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.CACHE_FAILURE_ERROR, "Failed to download a media."));
                    }
                }
            });
        }
    }

    public static void a(g gVar, ImageView imageView) {
        if (gVar != null && imageView != null) {
            new com.facebook.ads.internal.view.c.d(imageView).a(gVar.c(), gVar.b()).a(gVar.a());
        }
    }

    private void a(List<View> list, View view) {
        if (this.i == null || !this.i.a(view)) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    a((List) list, viewGroup.getChildAt(i));
                }
            } else {
                list.add(view);
            }
        }
    }

    @Nullable
    public String A() {
        return (!f() || TextUtils.isEmpty(this.a.D())) ? null : this.g.b(this.a.D());
    }

    @Nullable
    public String B() {
        return !f() ? null : this.a.E();
    }

    @Nullable
    public String C() {
        return !f() ? null : this.a.L();
    }

    @Nullable
    public l D() {
        return !f() ? l.DEFAULT : this.a.F();
    }

    @Nullable
    public List<e> E() {
        return !f() ? null : this.a.H();
    }

    @Nullable
    public String F() {
        return !f() ? null : this.a.c();
    }

    public void G() {
        this.H.performClick();
    }

    public k H() {
        return this.x;
    }

    public void I() {
        if (this.n != null) {
            if (c.containsKey(this.n) && ((WeakReference) c.get(this.n)).get() == this) {
                if ((this.n instanceof ViewGroup) && this.w != null) {
                    ((ViewGroup) this.n).removeView(this.w);
                    this.w = null;
                }
                if (this.a != null) {
                    this.a.d();
                }
                if (this.D != null && com.facebook.ads.internal.n.a.b(this.d)) {
                    this.D.b();
                    this.n.getOverlay().remove(this.D);
                }
                c.remove(this.n);
                Q();
                this.n = null;
                if (this.q != null) {
                    this.q.c();
                    this.q = null;
                }
                this.u = null;
                return;
            }
            throw new IllegalStateException("View not registered with this NativeAd");
        }
    }

    @Nullable
    public j a() {
        return this.a;
    }

    public void a(OnTouchListener onTouchListener) {
        this.p = onTouchListener;
    }

    public void a(View view, f fVar) {
        List arrayList = new ArrayList();
        a(arrayList, view);
        a(view, fVar, arrayList);
    }

    public void a(View view, f fVar, List<View> list) {
        if (view == null) {
            throw new IllegalArgumentException("Must provide a View");
        } else if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("Invalid set of clickable views");
        } else if (!f()) {
            Log.e(b, "Ad not loaded");
        } else if (fVar == null) {
            String str;
            String str2;
            if (this.m == com.facebook.ads.internal.protocol.e.NATIVE_UNKNOWN) {
                if (this.h != null) {
                    this.h.a(new com.facebook.ads.internal.protocol.a(AdErrorType.NO_MEDIAVIEW_IN_NATIVEAD, "MediaView is missing."));
                }
                if (AdInternalSettings.isDebugBuild()) {
                    str = b;
                    str2 = "MediaView is missing.";
                }
                return;
            }
            if (this.h != null) {
                this.h.a(new com.facebook.ads.internal.protocol.a(AdErrorType.NO_ICONVIEW_IN_NATIVEBANNERAD, "AdIconView is missing."));
            }
            if (AdInternalSettings.isDebugBuild()) {
                str = b;
                str2 = "AdIconView is missing.";
            }
            return;
            Log.e(str, str2);
        } else if (fVar.getAdContentsView() == null) {
            if (this.h != null) {
                this.h.a(new com.facebook.ads.internal.protocol.a(AdErrorType.UNSUPPORTED_AD_ASSET_NATIVEAD, "ad media type is not supported."));
            }
        } else {
            if (this.n != null) {
                Log.w(b, "Native Ad was already registered with a View. Auto unregistering and proceeding.");
                I();
            }
            if (c.containsKey(view) && ((WeakReference) c.get(view)).get() != null) {
                Log.w(b, "View already registered with a NativeAd. Auto unregistering and proceeding.");
                ((e) ((WeakReference) c.get(view)).get()).I();
            }
            this.v = new a(this, null);
            this.n = view;
            if (view instanceof ViewGroup) {
                this.w = new com.facebook.ads.internal.view.w(view.getContext(), new v() {
                    public void a(int i) {
                        if (e.this.a != null) {
                            e.this.a.a(i);
                        }
                    }
                });
                ((ViewGroup) view).addView(this.w);
            }
            List<View> arrayList = new ArrayList(list);
            if (this.H != null) {
                arrayList.add(this.H);
            }
            for (View a : arrayList) {
                a(a);
            }
            this.a.a(view, (List) arrayList);
            int L = L();
            this.r = new com.facebook.ads.internal.t.a.a() {
                public void a() {
                    if (!e.this.t.b()) {
                        e.this.t.a();
                        e.this.q.c();
                        if (!(e.this.s == null || e.this.s.get() == null)) {
                            ((com.facebook.ads.internal.t.a.a) e.this.s.get()).a();
                        }
                        if (e.this.u != null && e.this.n != null) {
                            e.this.u.a(e.this.n);
                            e.this.u.a(e.this.x);
                            e.this.u.a(e.this.y);
                            e.this.u.b(e.this.z);
                            e.this.u.d(e.this.A);
                            e.this.u.c(e.this.P());
                            e.this.u.a(e.this.F);
                            e.this.u.e(e.this.B);
                            e.this.u.a();
                        }
                    }
                }
            };
            this.q = new com.facebook.ads.internal.t.a(fVar != null ? fVar.getAdContentsView() : this.n, L, M(), true, this.r);
            this.q.a(N());
            this.q.b(O());
            this.u = new q(this.d, new b(this, null), this.q, this.a);
            this.u.a((List) arrayList);
            c.put(view, new WeakReference(this));
            if (com.facebook.ads.internal.n.a.b(this.d)) {
                com.facebook.ads.internal.view.c.c cVar;
                d dVar;
                this.D = new com.facebook.ads.internal.view.c.c();
                this.D.a(this.e);
                this.D.b(this.d.getPackageName());
                this.D.a(this.q);
                if (this.a.J() > 0) {
                    this.D.a(this.a.J(), this.a.I());
                }
                if (this.l != null) {
                    cVar = this.D;
                    dVar = this.l;
                } else {
                    if (!(this.j == null || this.j.a() == null)) {
                        cVar = this.D;
                        dVar = this.j.a();
                    }
                    this.n.getOverlay().add(this.D);
                }
                cVar.a(dVar.a());
                this.n.getOverlay().add(this.D);
            }
        }
    }

    public void a(r rVar) {
        if (this.a != null) {
            this.a.a(rVar);
        }
    }

    public void a(d dVar, String str) {
        if (this.k) {
            throw new IllegalStateException("loadAd cannot be called more than once");
        }
        this.C = System.currentTimeMillis();
        this.k = true;
        this.E = dVar;
        if (dVar.equals(d.NONE)) {
            this.F = com.facebook.ads.internal.adapters.q.a.NONE;
        }
        this.j = new DisplayAdController(this.d, this.e, this.m, J(), null, 1, true);
        this.j.a(dVar);
        this.j.a(this.G);
        this.j.a(new com.facebook.ads.internal.adapters.a() {
            public void a() {
                if (e.this.h != null) {
                    e.this.h.c();
                }
            }

            public void a(AdAdapter adAdapter) {
                if (e.this.j != null) {
                    e.this.j.b();
                }
            }

            public void a(j jVar) {
                com.facebook.ads.internal.l.b.a(com.facebook.ads.internal.l.a.a(com.facebook.ads.internal.l.a.b.LOADING_AD, e.this.J().toString(), System.currentTimeMillis() - e.this.C, null));
                e.this.a(jVar, true);
                if (e.this.h != null && jVar.H() != null) {
                    r anonymousClass1 = new r() {
                        public void a(j jVar) {
                        }

                        public void a(j jVar, com.facebook.ads.internal.protocol.a aVar) {
                        }

                        public void b(j jVar) {
                        }

                        public void c(j jVar) {
                            if (e.this.h != null) {
                                e.this.h.c();
                            }
                        }
                    };
                    for (e a : jVar.H()) {
                        a.a(anonymousClass1);
                    }
                }
            }

            public void a(com.facebook.ads.internal.protocol.a aVar) {
                if (e.this.h != null) {
                    e.this.h.a(aVar);
                }
            }

            public void b() {
                throw new IllegalStateException("Native ads manager their own impressions.");
            }
        });
        this.j.b(str);
    }

    public void a(h hVar) {
        this.h = hVar;
    }

    public void a(k kVar) {
        this.x = kVar;
    }

    public void a(com.facebook.ads.internal.protocol.e eVar) {
        this.m = eVar;
    }

    public void a(com.facebook.ads.internal.t.a.a aVar) {
        this.s = new WeakReference(aVar);
    }

    public void a(String str) {
        this.G = str;
    }

    public void a(boolean z) {
        this.y = z;
    }

    public void a(boolean z, boolean z2) {
        if (z) {
            if (!(!this.E.equals(d.NONE) || K() || this.h == null)) {
                this.h.a();
            }
            if (this.q != null) {
                this.q.a();
                return;
            }
        }
        if (this.q != null) {
            this.q.c();
        }
        if (this.h != null && z2) {
            this.h.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.BROKEN_MEDIA_ERROR, "Failed to load Media."));
        }
    }

    public void b(boolean z) {
        this.B = z;
    }

    public boolean b() {
        return this.j == null || this.j.d();
    }

    public void c() {
        if (this.E.equals(d.NONE)) {
            this.F = com.facebook.ads.internal.adapters.q.a.MANUAL;
        }
        this.E = d.ALL;
        a(this.a, false);
    }

    public void c(boolean z) {
        this.z = z;
    }

    public void d() {
        if (this.j != null) {
            this.j.b(true);
            this.j = null;
        }
    }

    public void d(boolean z) {
        this.A = z;
    }

    public String e() {
        return this.e;
    }

    public boolean f() {
        return this.a != null && this.a.K();
    }

    public boolean g() {
        return f() && this.a.f();
    }

    public boolean h() {
        return this.a != null && this.a.g();
    }

    @Nullable
    public g i() {
        return !f() ? null : this.a.m();
    }

    @Nullable
    public g j() {
        return !f() ? null : this.a.n();
    }

    @Nullable
    public j k() {
        return !f() ? null : this.a.o();
    }

    @Nullable
    public String l() {
        return !f() ? null : this.a.p();
    }

    @Nullable
    public String m() {
        return !f() ? null : this.a.q();
    }

    @Nullable
    public String n() {
        return !f() ? null : this.a.r();
    }

    @Nullable
    public String o() {
        return !f() ? null : this.a.s();
    }

    @Nullable
    public String p() {
        return !f() ? null : this.a.t();
    }

    @Nullable
    public String q() {
        return !f() ? null : this.a.u();
    }

    @Nullable
    public String r() {
        return !f() ? null : this.a.v();
    }

    @Nullable
    public String s() {
        return !f() ? null : this.a.w();
    }

    @Nullable
    public String t() {
        return !f() ? null : this.a.x();
    }

    @Nullable
    public String u() {
        return !f() ? null : this.a.y();
    }

    @Nullable
    public i v() {
        return !f() ? null : this.a.z();
    }

    @Nullable
    public String w() {
        return !f() ? null : this.f;
    }

    @Nullable
    public g x() {
        return !f() ? null : this.a.A();
    }

    @Nullable
    public String y() {
        return !f() ? null : this.a.B();
    }

    @Nullable
    public String z() {
        return !f() ? null : this.a.C();
    }
}
