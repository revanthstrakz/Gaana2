package com.til.colombia.android.service;

import com.til.colombia.android.internal.a.a;

final class ag extends a {
    final /* synthetic */ ColombiaAdManager b;

    ag(ColombiaAdManager colombiaAdManager) {
        this.b = colombiaAdManager;
    }

    public final void a() {
        this.b.isOnCall = true;
    }

    public final void b() {
        this.b.isOnCall = false;
    }
}
