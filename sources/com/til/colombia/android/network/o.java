package com.til.colombia.android.network;

import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.network.a.a;

final class o implements a {
    final /* synthetic */ String a;

    o(String str) {
        this.a = str;
    }

    public final void a() {
        Log.a(i.f, this.a);
    }

    public final void a(i iVar) {
        try {
            if (l.a().b() > 0) {
                wait((long) com.til.colombia.android.internal.a.y());
            } else {
                wait(1000);
            }
        } catch (InterruptedException e) {
            Log.a(i.f, "", e);
        }
        iVar.b = 10;
        iVar.c++;
        if (iVar.c <= com.til.colombia.android.internal.a.x()) {
            l.a().a(iVar, (a) this);
            return;
        }
        String str = i.f;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append("FAILED:");
        Log.a(str, stringBuilder.toString());
    }
}
