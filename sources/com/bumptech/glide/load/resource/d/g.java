package com.bumptech.glide.load.resource.d;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.bumptech.glide.h;
import com.bumptech.glide.i;
import com.bumptech.glide.load.engine.a.e;
import com.bumptech.glide.request.f;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

class g {
    final i a;
    private final com.bumptech.glide.b.a b;
    private final Handler c;
    private final List<b> d;
    private final e e;
    private boolean f;
    private boolean g;
    private boolean h;
    private h<Bitmap> i;
    private a j;
    private boolean k;
    private a l;
    private Bitmap m;
    private com.bumptech.glide.load.i<Bitmap> n;
    private a o;
    @Nullable
    private d p;

    public interface b {
        void f();
    }

    private class c implements Callback {
        c() {
        }

        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                g.this.a((a) message.obj);
                return true;
            }
            if (message.what == 2) {
                g.this.a.clear((a) message.obj);
            }
            return false;
        }
    }

    @VisibleForTesting
    interface d {
        void a();
    }

    static class a extends com.bumptech.glide.request.a.g<Bitmap> {
        final int a;
        private final Handler b;
        private final long c;
        private Bitmap d;

        a(Handler handler, int i, long j) {
            this.b = handler;
            this.a = i;
            this.c = j;
        }

        /* Access modifiers changed, original: 0000 */
        public Bitmap a() {
            return this.d;
        }

        /* renamed from: a */
        public void onResourceReady(Bitmap bitmap, com.bumptech.glide.request.b.d<? super Bitmap> dVar) {
            this.d = bitmap;
            this.b.sendMessageAtTime(this.b.obtainMessage(1, this), this.c);
        }
    }

    public g(com.bumptech.glide.e eVar, com.bumptech.glide.b.a aVar, int i, int i2, com.bumptech.glide.load.i<Bitmap> iVar, Bitmap bitmap) {
        this(eVar.b(), com.bumptech.glide.e.c(eVar.d()), aVar, null, a(com.bumptech.glide.e.c(eVar.d()), i, i2), iVar, bitmap);
    }

    g(e eVar, i iVar, com.bumptech.glide.b.a aVar, Handler handler, h<Bitmap> hVar, com.bumptech.glide.load.i<Bitmap> iVar2, Bitmap bitmap) {
        this.d = new ArrayList();
        this.f = false;
        this.g = false;
        this.h = false;
        this.a = iVar;
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper(), new c());
        }
        this.e = eVar;
        this.c = handler;
        this.i = hVar;
        this.b = aVar;
        a(iVar2, bitmap);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(com.bumptech.glide.load.i<Bitmap> iVar, Bitmap bitmap) {
        this.n = (com.bumptech.glide.load.i) com.bumptech.glide.f.h.a((Object) iVar);
        this.m = (Bitmap) com.bumptech.glide.f.h.a((Object) bitmap);
        this.i = this.i.apply(new f().transform(iVar));
    }

    /* Access modifiers changed, original: 0000 */
    public Bitmap a() {
        return this.m;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(b bVar) {
        if (this.k) {
            throw new IllegalStateException("Cannot subscribe to a cleared frame loader");
        }
        boolean isEmpty = this.d.isEmpty();
        if (this.d.contains(bVar)) {
            throw new IllegalStateException("Cannot subscribe twice in a row");
        }
        this.d.add(bVar);
        if (isEmpty) {
            k();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b(b bVar) {
        this.d.remove(bVar);
        if (this.d.isEmpty()) {
            l();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int b() {
        return i().getWidth();
    }

    /* Access modifiers changed, original: 0000 */
    public int c() {
        return i().getHeight();
    }

    /* Access modifiers changed, original: 0000 */
    public int d() {
        return this.b.g() + j();
    }

    /* Access modifiers changed, original: 0000 */
    public int e() {
        return this.j != null ? this.j.a : -1;
    }

    private int j() {
        return com.bumptech.glide.f.i.a(i().getWidth(), i().getHeight(), i().getConfig());
    }

    /* Access modifiers changed, original: 0000 */
    public ByteBuffer f() {
        return this.b.a().asReadOnlyBuffer();
    }

    /* Access modifiers changed, original: 0000 */
    public int g() {
        return this.b.d();
    }

    private void k() {
        if (!this.f) {
            this.f = true;
            this.k = false;
            m();
        }
    }

    private void l() {
        this.f = false;
    }

    /* Access modifiers changed, original: 0000 */
    public void h() {
        this.d.clear();
        n();
        l();
        if (this.j != null) {
            this.a.clear(this.j);
            this.j = null;
        }
        if (this.l != null) {
            this.a.clear(this.l);
            this.l = null;
        }
        if (this.o != null) {
            this.a.clear(this.o);
            this.o = null;
        }
        this.b.i();
        this.k = true;
    }

    /* Access modifiers changed, original: 0000 */
    public Bitmap i() {
        return this.j != null ? this.j.a() : this.m;
    }

    private void m() {
        if (this.f && !this.g) {
            if (this.h) {
                com.bumptech.glide.f.h.a(this.o == null, "Pending target must be null when starting from the first frame");
                this.b.f();
                this.h = false;
            }
            if (this.o != null) {
                a aVar = this.o;
                this.o = null;
                a(aVar);
                return;
            }
            this.g = true;
            long uptimeMillis = SystemClock.uptimeMillis() + ((long) this.b.c());
            this.b.b();
            this.l = new a(this.c, this.b.e(), uptimeMillis);
            this.i.apply(f.signatureOf(o())).load(this.b).into(this.l);
        }
    }

    private void n() {
        if (this.m != null) {
            this.e.a(this.m);
            this.m = null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    @VisibleForTesting
    public void a(a aVar) {
        if (this.p != null) {
            this.p.a();
        }
        this.g = false;
        if (this.k) {
            this.c.obtainMessage(2, aVar).sendToTarget();
        } else if (this.f) {
            if (aVar.a() != null) {
                n();
                a aVar2 = this.j;
                this.j = aVar;
                for (int size = this.d.size() - 1; size >= 0; size--) {
                    ((b) this.d.get(size)).f();
                }
                if (aVar2 != null) {
                    this.c.obtainMessage(2, aVar2).sendToTarget();
                }
            }
            m();
        } else {
            this.o = aVar;
        }
    }

    private static h<Bitmap> a(i iVar, int i, int i2) {
        return iVar.asBitmap().apply(f.diskCacheStrategyOf(com.bumptech.glide.load.engine.g.b).useAnimationPool(true).skipMemoryCache(true).override(i, i2));
    }

    private static com.bumptech.glide.load.c o() {
        return new com.bumptech.glide.e.c(Double.valueOf(Math.random()));
    }
}
