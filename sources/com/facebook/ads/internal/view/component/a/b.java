package com.facebook.ads.internal.view.component.a;

import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.h;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.component.a;
import com.facebook.ads.internal.view.component.i;
import java.util.HashMap;

public abstract class b extends RelativeLayout {
    static final int a = ((int) (16.0f * y.b));
    static final int b = ((int) (28.0f * y.b));
    private final i c;
    private final a d;
    private final c e;

    protected b(d dVar, d dVar2, boolean z) {
        super(dVar.a());
        this.e = dVar.b();
        this.d = new a(dVar.a(), d(), e(), "com.facebook.ads.interstitial.clicked", dVar2, dVar.b(), dVar.c(), dVar.e(), dVar.f());
        y.a(this.d);
        this.c = new i(getContext(), dVar2, z, b(), c());
        y.a(this.c);
    }

    public void a(h hVar, String str, double d) {
        i iVar = this.c;
        String b = hVar.a().b();
        String c = hVar.a().c();
        boolean z = !a() && d > 0.0d && d < 1.0d;
        iVar.a(b, c, false, z);
        this.d.a(hVar.b(), str, new HashMap());
    }

    public abstract boolean a();

    /* Access modifiers changed, original: protected */
    public boolean b() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public boolean c() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public boolean d() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public boolean e() {
        return true;
    }

    public c getAdEventManager() {
        return this.e;
    }

    /* Access modifiers changed, original: protected */
    public a getCtaButton() {
        return this.d;
    }

    /* Access modifiers changed, original: protected */
    public i getTitleDescContainer() {
        return this.c;
    }
}
