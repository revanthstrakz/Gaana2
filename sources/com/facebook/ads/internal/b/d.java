package com.facebook.ads.internal.b;

import android.os.Bundle;
import com.facebook.ads.internal.s.a.r;

public class d implements r<Bundle> {
    private c a;
    private final c b;
    private final b c;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;

    public d(b bVar) {
        this.c = bVar;
        this.b = new c(bVar.b);
        this.a = new c(bVar.b);
    }

    public d(b bVar, Bundle bundle) {
        this.c = bVar;
        this.b = (c) bundle.getSerializable("testStats");
        this.a = (c) bundle.getSerializable("viewableStats");
        this.d = bundle.getBoolean("ended");
        this.e = bundle.getBoolean("passed");
        this.f = bundle.getBoolean("complete");
    }

    private void b() {
        this.e = true;
        c();
    }

    private void c() {
        this.f = true;
        d();
    }

    private void d() {
        this.d = true;
        this.c.a(this.f, this.e, this.e ? this.a : this.b);
    }

    public void a() {
        if (!this.d) {
            this.a.b();
        }
    }

    public void a(double d, double d2) {
        if (!this.d) {
            this.b.a(d, d2);
            this.a.a(d, d2);
            d = this.c.e ? this.a.c().h() : this.a.c().g();
            if (this.c.c < 0.0d || this.b.c().f() <= this.c.c || d != 0.0d) {
                if (d >= this.c.d) {
                    b();
                }
                return;
            }
            c();
        }
    }

    public Bundle g() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("viewableStats", this.a);
        bundle.putSerializable("testStats", this.b);
        bundle.putBoolean("ended", this.d);
        bundle.putBoolean("passed", this.e);
        bundle.putBoolean("complete", this.f);
        return bundle;
    }
}
