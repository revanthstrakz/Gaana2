package android.databinding;

import android.annotation.TargetApi;
import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.d;
import android.arch.lifecycle.e;
import android.arch.lifecycle.m;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import java.lang.ref.ReferenceQueue;

public abstract class ViewDataBinding extends a {
    static int a = VERSION.SDK_INT;
    private static final int b = "binding_".length();
    private static final boolean c = (a >= 16);
    private static final a d = new a() {
    };
    private static final a e = new a() {
    };
    private static final a f = new a() {
    };
    private static final a g = new a() {
    };
    private static final android.databinding.c.a<d, ViewDataBinding, Void> h = new android.databinding.c.a<d, ViewDataBinding, Void>() {
        public void a(d dVar, ViewDataBinding viewDataBinding, int i, Void voidR) {
            switch (i) {
                case 1:
                    if (!dVar.a(viewDataBinding)) {
                        viewDataBinding.m = true;
                        return;
                    }
                    return;
                case 2:
                    dVar.b(viewDataBinding);
                    return;
                case 3:
                    dVar.c(viewDataBinding);
                    return;
                default:
                    return;
            }
        }
    };
    private static final ReferenceQueue<ViewDataBinding> i = new ReferenceQueue();
    private static final OnAttachStateChangeListener j;
    private final Runnable k;
    private boolean l;
    private boolean m;
    private c<d, ViewDataBinding, Void> n;
    private boolean o;
    private Choreographer p;
    private final FrameCallback q;
    private Handler r;
    private ViewDataBinding s;
    private e t;

    private interface a {
    }

    public class OnStartListener implements d {
        final /* synthetic */ ViewDataBinding a;

        @m(a = Event.ON_START)
        public void onStart() {
            this.a.a();
        }
    }

    public abstract void b();

    public abstract boolean c();

    static {
        if (VERSION.SDK_INT < 19) {
            j = null;
        } else {
            j = new OnAttachStateChangeListener() {
                public void onViewDetachedFromWindow(View view) {
                }

                @TargetApi(19)
                public void onViewAttachedToWindow(View view) {
                    ViewDataBinding.a(view).k.run();
                    view.removeOnAttachStateChangeListener(this);
                }
            };
        }
    }

    public void a() {
        if (this.s == null) {
            e();
        } else {
            this.s.a();
        }
    }

    private void e() {
        if (this.o) {
            d();
        } else if (c()) {
            this.o = true;
            this.m = false;
            if (this.n != null) {
                this.n.a(this, 1, null);
                if (this.m) {
                    this.n.a(this, 2, null);
                }
            }
            if (!this.m) {
                b();
                if (this.n != null) {
                    this.n.a(this, 3, null);
                }
            }
            this.o = false;
        }
    }

    static ViewDataBinding a(View view) {
        return view != null ? (ViewDataBinding) view.getTag(com.android.a.a.a.a.dataBinding) : null;
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing block: B:12:0x0017, code skipped:
            if (r2.t == null) goto L_0x002c;
     */
    /* JADX WARNING: Missing block: B:14:0x0029, code skipped:
            if (r2.t.getLifecycle().a().isAtLeast(android.arch.lifecycle.Lifecycle.State.STARTED) != false) goto L_0x002c;
     */
    /* JADX WARNING: Missing block: B:15:0x002b, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:17:0x002e, code skipped:
            if (c == false) goto L_0x0038;
     */
    /* JADX WARNING: Missing block: B:18:0x0030, code skipped:
            r2.p.postFrameCallback(r2.q);
     */
    /* JADX WARNING: Missing block: B:19:0x0038, code skipped:
            r2.r.post(r2.k);
     */
    public void d() {
        /*
        r2 = this;
        r0 = r2.s;
        if (r0 == 0) goto L_0x000a;
    L_0x0004:
        r0 = r2.s;
        r0.d();
        goto L_0x003f;
    L_0x000a:
        monitor-enter(r2);
        r0 = r2.l;	 Catch:{ all -> 0x0040 }
        if (r0 == 0) goto L_0x0011;
    L_0x000f:
        monitor-exit(r2);	 Catch:{ all -> 0x0040 }
        return;
    L_0x0011:
        r0 = 1;
        r2.l = r0;	 Catch:{ all -> 0x0040 }
        monitor-exit(r2);	 Catch:{ all -> 0x0040 }
        r0 = r2.t;
        if (r0 == 0) goto L_0x002c;
    L_0x0019:
        r0 = r2.t;
        r0 = r0.getLifecycle();
        r0 = r0.a();
        r1 = android.arch.lifecycle.Lifecycle.State.STARTED;
        r0 = r0.isAtLeast(r1);
        if (r0 != 0) goto L_0x002c;
    L_0x002b:
        return;
    L_0x002c:
        r0 = c;
        if (r0 == 0) goto L_0x0038;
    L_0x0030:
        r0 = r2.p;
        r1 = r2.q;
        r0.postFrameCallback(r1);
        goto L_0x003f;
    L_0x0038:
        r0 = r2.r;
        r1 = r2.k;
        r0.post(r1);
    L_0x003f:
        return;
    L_0x0040:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0040 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.databinding.ViewDataBinding.d():void");
    }
}
