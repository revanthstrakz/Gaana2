package com.til.colombia.android.service;

import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.network.a.a;
import com.til.colombia.android.network.l;

final class cr implements a {
    final /* synthetic */ NativeItem a;

    cr(NativeItem nativeItem) {
        this.a = nativeItem;
    }

    public final void a() {
        String str = i.f;
        StringBuilder stringBuilder = new StringBuilder("Item:");
        stringBuilder.append(this.a.itemId);
        stringBuilder.append(" impressed.");
        Log.a(str, stringBuilder.toString());
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
        if (iVar.c <= com.til.colombia.android.internal.a.y()) {
            l.a().a(iVar, (a) this);
            return;
        }
        String str = i.f;
        StringBuilder stringBuilder = new StringBuilder("Item:");
        stringBuilder.append(this.a.itemId);
        stringBuilder.append(" Retry limit reached.");
        Log.a(str, stringBuilder.toString());
        this.a.impressed = false;
    }
}
