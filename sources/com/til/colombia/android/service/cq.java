package com.til.colombia.android.service;

import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.network.a.a;
import com.til.colombia.android.network.l;

final class cq implements a {
    final /* synthetic */ NativeItem a;

    cq(NativeItem nativeItem) {
        this.a = nativeItem;
    }

    public final void a() {
        Log.b(i.f, "Click tracked successfully.");
    }

    public final void a(com.til.colombia.android.network.i iVar) {
        try {
            if (l.a().b() > 0) {
                wait((long) com.til.colombia.android.internal.a.y());
            } else {
                wait(1000);
            }
        } catch (InterruptedException unused) {
        }
        iVar.b = 10;
        iVar.c++;
        if (iVar.c <= com.til.colombia.android.internal.a.x()) {
            l.a().a(iVar, (a) this);
        } else {
            Log.b(i.f, "clickTrackers: Retry limit reached.");
        }
    }
}
