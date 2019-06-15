package com.bumptech.glide.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools.Pool;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.f.a.a;
import com.bumptech.glide.f.a.a.c;
import com.bumptech.glide.f.a.b;
import com.bumptech.glide.g;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.h.d;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.request.a.h;
import com.bumptech.glide.request.a.i;
import com.bumptech.glide.request.b.e;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;

public final class SingleRequest<R> implements c, h, c, g {
    private static final Pool<SingleRequest<?>> a = a.a(150, new a.a<SingleRequest<?>>() {
        /* renamed from: a */
        public SingleRequest<?> b() {
            return new SingleRequest();
        }
    });
    private int A;
    private boolean b;
    private final String c = String.valueOf(super.hashCode());
    private final b d = b.a();
    @Nullable
    private e<R> e;
    private d f;
    private Context g;
    private g h;
    @Nullable
    private Object i;
    private Class<R> j;
    private f k;
    private int l;
    private int m;
    private Priority n;
    private i<R> o;
    private e<R> p;
    private com.bumptech.glide.load.engine.h q;
    private e<? super R> r;
    private q<R> s;
    private d t;
    private long u;
    private Status v;
    private Drawable w;
    private Drawable x;
    private Drawable y;
    private int z;

    private enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CANCELLED,
        CLEARED,
        PAUSED
    }

    public static <R> SingleRequest<R> a(Context context, g gVar, Object obj, Class<R> cls, f fVar, int i, int i2, Priority priority, i<R> iVar, e<R> eVar, e<R> eVar2, d dVar, com.bumptech.glide.load.engine.h hVar, e<? super R> eVar3) {
        SingleRequest<R> singleRequest = (SingleRequest) a.acquire();
        if (singleRequest == null) {
            singleRequest = new SingleRequest();
        }
        singleRequest.b(context, gVar, obj, cls, fVar, i, i2, priority, iVar, eVar, eVar2, dVar, hVar, eVar3);
        return singleRequest;
    }

    SingleRequest() {
    }

    private void b(Context context, g gVar, Object obj, Class<R> cls, f fVar, int i, int i2, Priority priority, i<R> iVar, e<R> eVar, e<R> eVar2, d dVar, com.bumptech.glide.load.engine.h hVar, e<? super R> eVar3) {
        this.g = context;
        this.h = gVar;
        this.i = obj;
        this.j = cls;
        this.k = fVar;
        this.l = i;
        this.m = i2;
        this.n = priority;
        this.o = iVar;
        this.e = eVar;
        this.p = eVar2;
        this.f = dVar;
        this.q = hVar;
        this.r = eVar3;
        this.v = Status.PENDING;
    }

    public b a_() {
        return this.d;
    }

    public void i() {
        k();
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = -1;
        this.m = -1;
        this.o = null;
        this.p = null;
        this.e = null;
        this.f = null;
        this.r = null;
        this.t = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = -1;
        this.A = -1;
        a.release(this);
    }

    public void a() {
        k();
        this.d.b();
        this.u = com.bumptech.glide.f.d.a();
        if (this.i == null) {
            if (com.bumptech.glide.f.i.a(this.l, this.m)) {
                this.z = this.l;
                this.A = this.m;
            }
            a(new GlideException("Received null model"), n() == null ? 5 : 3);
        } else if (this.v == Status.RUNNING) {
            throw new IllegalArgumentException("Cannot restart a running request");
        } else if (this.v == Status.COMPLETE) {
            a(this.s, DataSource.MEMORY_CACHE);
        } else {
            this.v = Status.WAITING_FOR_SIZE;
            if (com.bumptech.glide.f.i.a(this.l, this.m)) {
                a(this.l, this.m);
            } else {
                this.o.getSize(this);
            }
            if ((this.v == Status.RUNNING || this.v == Status.WAITING_FOR_SIZE) && q()) {
                this.o.onLoadStarted(m());
            }
            if (Log.isLoggable("Request", 2)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("finished run method in ");
                stringBuilder.append(com.bumptech.glide.f.d.a(this.u));
                a(stringBuilder.toString());
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void j() {
        k();
        this.d.b();
        this.o.removeCallback(this);
        this.v = Status.CANCELLED;
        if (this.t != null) {
            this.t.a();
            this.t = null;
        }
    }

    private void k() {
        if (this.b) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    public void c() {
        com.bumptech.glide.f.i.a();
        k();
        if (this.v != Status.CLEARED) {
            j();
            if (this.s != null) {
                a(this.s);
            }
            if (q()) {
                this.o.onLoadCleared(m());
            }
            this.v = Status.CLEARED;
        }
    }

    public void b() {
        c();
        this.v = Status.PAUSED;
    }

    private void a(q<?> qVar) {
        this.q.a((q) qVar);
        this.s = null;
    }

    public boolean d() {
        return this.v == Status.RUNNING || this.v == Status.WAITING_FOR_SIZE;
    }

    public boolean e() {
        return this.v == Status.COMPLETE;
    }

    public boolean f() {
        return e();
    }

    public boolean g() {
        return this.v == Status.CANCELLED || this.v == Status.CLEARED;
    }

    public boolean h() {
        return this.v == Status.FAILED;
    }

    private Drawable l() {
        if (this.w == null) {
            this.w = this.k.getErrorPlaceholder();
            if (this.w == null && this.k.getErrorId() > 0) {
                this.w = a(this.k.getErrorId());
            }
        }
        return this.w;
    }

    private Drawable m() {
        if (this.x == null) {
            this.x = this.k.getPlaceholderDrawable();
            if (this.x == null && this.k.getPlaceholderId() > 0) {
                this.x = a(this.k.getPlaceholderId());
            }
        }
        return this.x;
    }

    private Drawable n() {
        if (this.y == null) {
            this.y = this.k.getFallbackDrawable();
            if (this.y == null && this.k.getFallbackId() > 0) {
                this.y = a(this.k.getFallbackId());
            }
        }
        return this.y;
    }

    private Drawable a(@DrawableRes int i) {
        return com.bumptech.glide.load.resource.b.a.a(this.h, i, this.k.getTheme() != null ? this.k.getTheme() : this.g.getTheme());
    }

    private void o() {
        if (q()) {
            Drawable drawable = null;
            if (this.i == null) {
                drawable = n();
            }
            if (drawable == null) {
                drawable = l();
            }
            if (drawable == null) {
                drawable = m();
            }
            this.o.onLoadFailed(drawable);
        }
    }

    public void a(int i, int i2) {
        StringBuilder stringBuilder;
        this.d.b();
        if (Log.isLoggable("Request", 2)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Got onSizeReady in ");
            stringBuilder.append(com.bumptech.glide.f.d.a(this.u));
            a(stringBuilder.toString());
        }
        if (this.v == Status.WAITING_FOR_SIZE) {
            this.v = Status.RUNNING;
            float sizeMultiplier = this.k.getSizeMultiplier();
            this.z = a(i, sizeMultiplier);
            this.A = a(i2, sizeMultiplier);
            if (Log.isLoggable("Request", 2)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("finished setup for calling load in ");
                stringBuilder.append(com.bumptech.glide.f.d.a(this.u));
                a(stringBuilder.toString());
            }
            d a = this.q.a(this.h, this.i, this.k.getSignature(), this.z, this.A, this.k.getResourceClass(), this.j, this.n, this.k.getDiskCacheStrategy(), this.k.getTransformations(), this.k.isTransformationRequired(), this.k.isScaleOnlyOrNoTransform(), this.k.getOptions(), this.k.isMemoryCacheable(), this.k.getUseUnlimitedSourceGeneratorsPool(), this.k.getUseAnimationPool(), this.k.getOnlyRetrieveFromCache(), this);
            this.t = a;
            if (Log.isLoggable("Request", 2)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("finished onSizeReady in ");
                stringBuilder.append(com.bumptech.glide.f.d.a(this.u));
                a(stringBuilder.toString());
            }
        }
    }

    private static int a(int i, float f) {
        return i == Integer.MIN_VALUE ? i : Math.round(f * ((float) i));
    }

    private boolean p() {
        return this.f == null || this.f.b(this);
    }

    private boolean q() {
        return this.f == null || this.f.c(this);
    }

    private boolean r() {
        return this.f == null || !this.f.j();
    }

    private void s() {
        if (this.f != null) {
            this.f.d(this);
        }
    }

    private void t() {
        if (this.f != null) {
            this.f.e(this);
        }
    }

    public void a(q<?> qVar, DataSource dataSource) {
        this.d.b();
        this.t = null;
        if (qVar == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected to receive a Resource<R> with an object of ");
            stringBuilder.append(this.j);
            stringBuilder.append(" inside, but instead got null.");
            a(new GlideException(stringBuilder.toString()));
            return;
        }
        Object c = qVar.c();
        if (c == null || !this.j.isAssignableFrom(c.getClass())) {
            a((q) qVar);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Expected to receive an object of ");
            stringBuilder2.append(this.j);
            stringBuilder2.append(" but instead got ");
            stringBuilder2.append(c != null ? c.getClass() : "");
            stringBuilder2.append("{");
            stringBuilder2.append(c);
            stringBuilder2.append("} inside Resource{");
            stringBuilder2.append(qVar);
            stringBuilder2.append("}.");
            stringBuilder2.append(c != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data.");
            a(new GlideException(stringBuilder2.toString()));
        } else if (p()) {
            a(qVar, c, dataSource);
        } else {
            a((q) qVar);
            this.v = Status.COMPLETE;
        }
    }

    /* JADX WARNING: Missing block: B:8:0x007f, code skipped:
            if (r7.p.onResourceReady(r9, r7.i, r7.o, r10, r6) == false) goto L_0x0081;
     */
    /* JADX WARNING: Missing block: B:12:0x0092, code skipped:
            if (r7.e.onResourceReady(r9, r7.i, r7.o, r10, r6) == false) goto L_0x0094;
     */
    private void a(com.bumptech.glide.load.engine.q<R> r8, R r9, com.bumptech.glide.load.DataSource r10) {
        /*
        r7 = this;
        r6 = r7.r();
        r0 = com.bumptech.glide.request.SingleRequest.Status.COMPLETE;
        r7.v = r0;
        r7.s = r8;
        r8 = r7.h;
        r8 = r8.e();
        r0 = 3;
        if (r8 > r0) goto L_0x006a;
    L_0x0013:
        r8 = "Glide";
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Finished loading ";
        r0.append(r1);
        r1 = r9.getClass();
        r1 = r1.getSimpleName();
        r0.append(r1);
        r1 = " from ";
        r0.append(r1);
        r0.append(r10);
        r1 = " for ";
        r0.append(r1);
        r1 = r7.i;
        r0.append(r1);
        r1 = " with size [";
        r0.append(r1);
        r1 = r7.z;
        r0.append(r1);
        r1 = "x";
        r0.append(r1);
        r1 = r7.A;
        r0.append(r1);
        r1 = "] in ";
        r0.append(r1);
        r1 = r7.u;
        r1 = com.bumptech.glide.f.d.a(r1);
        r0.append(r1);
        r1 = " ms";
        r0.append(r1);
        r0 = r0.toString();
        android.util.Log.d(r8, r0);
    L_0x006a:
        r8 = 1;
        r7.b = r8;
        r8 = 0;
        r0 = r7.p;	 Catch:{ all -> 0x00a5 }
        if (r0 == 0) goto L_0x0081;
    L_0x0072:
        r0 = r7.p;	 Catch:{ all -> 0x00a5 }
        r2 = r7.i;	 Catch:{ all -> 0x00a5 }
        r3 = r7.o;	 Catch:{ all -> 0x00a5 }
        r1 = r9;
        r4 = r10;
        r5 = r6;
        r0 = r0.onResourceReady(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x00a5 }
        if (r0 != 0) goto L_0x009f;
    L_0x0081:
        r0 = r7.e;	 Catch:{ all -> 0x00a5 }
        if (r0 == 0) goto L_0x0094;
    L_0x0085:
        r0 = r7.e;	 Catch:{ all -> 0x00a5 }
        r2 = r7.i;	 Catch:{ all -> 0x00a5 }
        r3 = r7.o;	 Catch:{ all -> 0x00a5 }
        r1 = r9;
        r4 = r10;
        r5 = r6;
        r0 = r0.onResourceReady(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x00a5 }
        if (r0 != 0) goto L_0x009f;
    L_0x0094:
        r0 = r7.r;	 Catch:{ all -> 0x00a5 }
        r10 = r0.a(r10, r6);	 Catch:{ all -> 0x00a5 }
        r0 = r7.o;	 Catch:{ all -> 0x00a5 }
        r0.onResourceReady(r9, r10);	 Catch:{ all -> 0x00a5 }
    L_0x009f:
        r7.b = r8;
        r7.s();
        return;
    L_0x00a5:
        r9 = move-exception;
        r7.b = r8;
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.a(com.bumptech.glide.load.engine.q, java.lang.Object, com.bumptech.glide.load.DataSource):void");
    }

    public void a(GlideException glideException) {
        a(glideException, 5);
    }

    private void a(GlideException glideException, int i) {
        this.d.b();
        int e = this.h.e();
        if (e <= i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Load failed for ");
            stringBuilder.append(this.i);
            stringBuilder.append(" with size [");
            stringBuilder.append(this.z);
            stringBuilder.append(AvidJSONUtil.KEY_X);
            stringBuilder.append(this.A);
            stringBuilder.append("]");
            Log.w("Glide", stringBuilder.toString(), glideException);
            if (e <= 4) {
                glideException.a("Glide");
            }
        }
        this.t = null;
        this.v = Status.FAILED;
        this.b = true;
        try {
            if ((this.p == null || !this.p.onLoadFailed(glideException, this.i, this.o, r())) && (this.e == null || !this.e.onLoadFailed(glideException, this.i, this.o, r()))) {
                o();
            }
            this.b = false;
            t();
        } catch (Throwable th) {
            this.b = false;
        }
    }

    public boolean a(c cVar) {
        boolean z = false;
        if (!(cVar instanceof SingleRequest)) {
            return false;
        }
        SingleRequest singleRequest = (SingleRequest) cVar;
        if (this.l == singleRequest.l && this.m == singleRequest.m && com.bumptech.glide.f.i.b(this.i, singleRequest.i) && this.j.equals(singleRequest.j) && this.k.equals(singleRequest.k) && this.n == singleRequest.n && (this.p == null ? singleRequest.p != null : singleRequest.p == null)) {
            z = true;
        }
        return z;
    }

    private void a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" this: ");
        stringBuilder.append(this.c);
        Log.v("Request", stringBuilder.toString());
    }
}
