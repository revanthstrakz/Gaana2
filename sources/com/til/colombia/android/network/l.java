package com.til.colombia.android.network;

import com.til.colombia.android.internal.a.d;
import com.til.colombia.android.network.a.a;
import com.til.colombia.android.network.a.b;
import java.util.concurrent.ExecutorService;

public class l implements b {
    private static volatile l a;
    private ExecutorService b = p.a(15);

    private l() {
    }

    public static l a() {
        l lVar = a;
        if (lVar == null) {
            synchronized (l.class) {
                lVar = a;
                if (lVar == null) {
                    lVar = new l();
                    a = lVar;
                }
            }
        }
        return lVar;
    }

    public final int b() {
        p pVar = (p) this.b;
        return pVar.a != null ? pVar.a.size() : 0;
    }

    public final void a(i iVar, a aVar) {
        ((p) this.b).a(new j(iVar, aVar), iVar.b);
    }

    public final void a(Runnable runnable) {
        a(runnable, 5);
    }

    public final void a(Runnable runnable, int i) {
        if (runnable != null) {
            ((p) this.b).a(runnable, i);
        }
    }

    public static void a(String str, int i, a aVar) {
        try {
            str = d.b(str);
        } catch (Exception unused) {
        }
        a().a(new i(str, i, 1), aVar);
    }
}
