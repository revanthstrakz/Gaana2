package com.til.colombia.android.network;

import java.util.ArrayList;
import java.util.List;

final class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        try {
            a aVar = this.a;
            List arrayList = new ArrayList();
            synchronized (aVar.b) {
                aVar.c.addAll(aVar.b);
                arrayList.addAll(aVar.c);
                aVar.b.removeAll(aVar.c);
            }
            a.a(arrayList);
            synchronized (aVar.b) {
                aVar.b(aVar.c);
            }
        } catch (Exception unused) {
        }
    }
}
