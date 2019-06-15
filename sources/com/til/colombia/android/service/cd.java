package com.til.colombia.android.service;

import com.til.colombia.android.internal.Log;
import com.til.colombia.android.network.a.a;
import com.til.colombia.android.network.i;
import com.til.colombia.android.network.l;

final class cd implements a {
    final /* synthetic */ ItemResponse a;

    public final void a() {
    }

    cd(ItemResponse itemResponse) {
        this.a = itemResponse;
    }

    public final void a(i iVar) {
        try {
            if (l.a().b() > 0) {
                wait((long) com.til.colombia.android.internal.a.y());
            } else {
                wait(1000);
            }
        } catch (InterruptedException e) {
            Log.a(com.til.colombia.android.internal.i.f, "", e);
        }
        iVar.b = 10;
        iVar.c++;
        if (iVar.c < com.til.colombia.android.internal.a.x()) {
            l.a().a(iVar, (a) this);
            return;
        }
        Log.b(com.til.colombia.android.internal.i.f, "AdUnitId: Retry limit reached.");
        this.a.impressed = false;
    }
}
