package com.bumptech.glide.load.engine;

import android.os.Looper;
import com.bumptech.glide.f.h;
import com.bumptech.glide.load.c;

class m<Z> implements q<Z> {
    private final boolean a;
    private a b;
    private c c;
    private int d;
    private boolean e;
    private final q<Z> f;

    interface a {
        void b(c cVar, m<?> mVar);
    }

    m(q<Z> qVar, boolean z) {
        this.f = (q) h.a((Object) qVar);
        this.a = z;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(c cVar, a aVar) {
        this.c = cVar;
        this.b = aVar;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a() {
        return this.a;
    }

    public Class<Z> b() {
        return this.f.b();
    }

    public Z c() {
        return this.f.c();
    }

    public int d() {
        return this.f.d();
    }

    public void e() {
        if (this.d > 0) {
            throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
        } else if (this.e) {
            throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
        } else {
            this.e = true;
            this.f.e();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void f() {
        if (this.e) {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        } else if (Looper.getMainLooper().equals(Looper.myLooper())) {
            this.d++;
        } else {
            throw new IllegalThreadStateException("Must call acquire on the main thread");
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void g() {
        if (this.d <= 0) {
            throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
        } else if (Looper.getMainLooper().equals(Looper.myLooper())) {
            int i = this.d - 1;
            this.d = i;
            if (i == 0) {
                this.b.b(this.c, this);
            }
        } else {
            throw new IllegalThreadStateException("Must call release on the main thread");
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("EngineResource{isCacheable=");
        stringBuilder.append(this.a);
        stringBuilder.append(", listener=");
        stringBuilder.append(this.b);
        stringBuilder.append(", key=");
        stringBuilder.append(this.c);
        stringBuilder.append(", acquired=");
        stringBuilder.append(this.d);
        stringBuilder.append(", isRecycled=");
        stringBuilder.append(this.e);
        stringBuilder.append(", resource=");
        stringBuilder.append(this.f);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
