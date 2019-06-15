package com.til.colombia.android.service;

import android.util.Log;
import android.view.View;
import com.til.colombia.android.internal.a;
import com.til.colombia.android.internal.a.e;
import com.til.colombia.android.internal.a.j;
import com.til.colombia.android.internal.i;
import java.util.Iterator;
import java.util.Map.Entry;

final class bj implements Runnable {
    final /* synthetic */ ItemResponse a;
    final /* synthetic */ View b;
    final /* synthetic */ Item c;
    final /* synthetic */ bi d;

    bj(bi biVar, ItemResponse itemResponse, View view, Item item) {
        this.d = biVar;
        this.a = itemResponse;
        this.b = view;
        this.c = item;
    }

    public final void run() {
        Log.i(i.f, "requesting record-impression.");
        e impressionTracker = this.a.getAdManager().getImpressionTracker();
        View view = this.b;
        Item item = this.c;
        if (impressionTracker.b.get(view) != item) {
            impressionTracker.a(view);
            if (!item.isImpressed()) {
                impressionTracker.b.put(view, item);
                j jVar = impressionTracker.a;
                int z = a.z();
                a aVar = (a) jVar.f.get(view);
                if (aVar == null) {
                    aVar = new a();
                    jVar.f.put(view, aVar);
                    jVar.c();
                }
                int min = Math.min(z, z);
                aVar.d = view;
                aVar.a = z;
                aVar.b = min;
                aVar.c = jVar.c;
                jVar.c++;
                if (jVar.c % 50 == 0) {
                    long j = jVar.c - 50;
                    for (Entry entry : jVar.f.entrySet()) {
                        if (((a) entry.getValue()).c < j) {
                            jVar.b.add(entry.getKey());
                        }
                    }
                    Iterator it = jVar.b.iterator();
                    while (it.hasNext()) {
                        jVar.a((View) it.next());
                    }
                    jVar.b.clear();
                }
            }
        }
    }
}
