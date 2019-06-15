package com.facebook.ads.internal.view.g.c;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.view.g.a.b;
import com.facebook.ads.internal.view.g.b.c;
import com.facebook.ads.internal.view.g.b.h;
import com.facebook.ads.internal.view.g.b.i;
import com.facebook.ads.internal.view.g.b.j;
import com.facebook.ads.internal.view.g.b.k;
import com.facebook.ads.internal.view.g.b.t;
import com.facebook.ads.internal.view.g.b.u;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;

@TargetApi(12)
public class d implements b {
    private final i a;
    private final k b;
    private final c c;
    private final u d;
    private final Handler e;
    private final boolean f;
    private final boolean g;
    private View h;
    @Nullable
    private a i;
    @Nullable
    private com.facebook.ads.internal.view.g.a j;
    private boolean k;

    public enum a {
        VISIBLE,
        INVSIBLE,
        FADE_OUT_ON_PLAY
    }

    public d(View view, a aVar) {
        this(view, aVar, false);
    }

    public d(View view, a aVar, boolean z) {
        this(view, aVar, z, false);
    }

    public d(View view, @Nullable a aVar, boolean z, boolean z2) {
        this.a = new i() {
            public void a(h hVar) {
                d.this.a(1, 0);
            }
        };
        this.b = new k() {
            public void a(j jVar) {
                if (!d.this.k) {
                    return;
                }
                if (d.this.i == a.FADE_OUT_ON_PLAY || d.this.f) {
                    d.this.i = null;
                    d.this.c();
                    return;
                }
                d.this.a(0, 8);
            }
        };
        this.c = new c() {
            public void a(com.facebook.ads.internal.view.g.b.b bVar) {
                if (d.this.i != a.INVSIBLE) {
                    d.this.h.setAlpha(1.0f);
                    d.this.h.setVisibility(0);
                }
            }
        };
        this.d = new u() {
            public void a(t tVar) {
                if (d.this.j != null && tVar.a().getAction() == 0) {
                    d.this.e.removeCallbacksAndMessages(null);
                    d.this.a(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            d.this.e.postDelayed(new Runnable() {
                                public void run() {
                                    if (!d.this.g && d.this.k) {
                                        d.this.c();
                                    }
                                }
                            }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
                        }
                    });
                }
            }
        };
        this.k = true;
        this.e = new Handler();
        this.f = z;
        this.g = z2;
        a(view, aVar);
    }

    private void a(int i, int i2) {
        this.e.removeCallbacksAndMessages(null);
        this.h.clearAnimation();
        this.h.setAlpha((float) i);
        this.h.setVisibility(i2);
    }

    private void a(AnimatorListenerAdapter animatorListenerAdapter) {
        this.h.setVisibility(0);
        this.h.animate().alpha(1.0f).setDuration(500).setListener(animatorListenerAdapter);
    }

    private void c() {
        this.h.animate().alpha(0.0f).setDuration(500).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                d.this.h.setVisibility(8);
            }
        });
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:6:0x0026 in {2, 4, 5} preds:[]
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:242)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:42)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
        	at java.lang.Iterable.forEach(Unknown Source)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
        	at jadx.core.ProcessClass.process(ProcessClass.java:37)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    public void a(android.view.View r1, com.facebook.ads.internal.view.g.c.d.a r2) {
        /*
        r0 = this;
        r0.i = r2;
        r0.h = r1;
        r1 = r0.h;
        r1.clearAnimation();
        r1 = com.facebook.ads.internal.view.g.c.d.a.INVSIBLE;
        if (r2 != r1) goto L_0x001b;
        r1 = r0.h;
        r2 = 0;
        r1.setAlpha(r2);
        r1 = r0.h;
        r2 = 8;
        r1.setVisibility(r2);
        return;
        r1 = r0.h;
        r2 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r1.setAlpha(r2);
        r1 = r0.h;
        r2 = 0;
        goto L_0x0017;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.g.c.d.a(android.view.View, com.facebook.ads.internal.view.g.c.d$a):void");
    }

    public void a(com.facebook.ads.internal.view.g.a aVar) {
        this.j = aVar;
        aVar.getEventBus().a(this.a, this.b, this.d, this.c);
    }

    public boolean a() {
        return this.k;
    }

    public void b() {
        this.k = false;
        a(null);
    }

    public void b(com.facebook.ads.internal.view.g.a aVar) {
        a(1, 0);
        aVar.getEventBus().b(this.c, this.d, this.b, this.a);
        this.j = null;
    }
}
