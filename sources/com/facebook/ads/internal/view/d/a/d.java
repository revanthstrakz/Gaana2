package com.facebook.ads.internal.view.d.a;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.SparseBooleanArray;
import android.view.ViewGroup;
import com.facebook.ads.internal.f.b;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.a.w;
import java.util.List;

public class d extends Adapter<g> {
    private final c a;
    @Nullable
    private final b b;
    private final com.facebook.ads.internal.t.a c;
    private final w d;
    private final com.facebook.ads.internal.adapters.a.d e;
    @Nullable
    private com.facebook.ads.internal.view.a.a f;
    private int g;
    private int h;
    private String i;
    private int j;
    private int k;
    private List<b> l;
    private final a m;
    private final SparseBooleanArray n = new SparseBooleanArray();

    public interface a {
        void a(int i);
    }

    d(List<b> list, c cVar, b bVar, com.facebook.ads.internal.t.a aVar, w wVar, com.facebook.ads.internal.view.a.a aVar2, com.facebook.ads.internal.adapters.a.d dVar, String str, int i, int i2, int i3, int i4, a aVar3) {
        this.a = cVar;
        this.b = bVar;
        this.c = aVar;
        this.d = wVar;
        this.f = aVar2;
        this.l = list;
        this.h = i;
        this.e = dVar;
        this.j = i4;
        this.i = str;
        this.g = i3;
        this.k = i2;
        this.m = aVar3;
    }

    /* renamed from: a */
    public g onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new g(com.facebook.ads.internal.view.component.a.a.c.a(new com.facebook.ads.internal.view.component.a.d.a(viewGroup.getContext(), this.a, this.f, null, null, this.c, this.d).a(), this.j, this.e, this.i, this.m), this.n, this.c, this.h, this.g, this.k, this.l.size());
    }

    /* renamed from: a */
    public void onBindViewHolder(g gVar, int i) {
        gVar.a((b) this.l.get(i), this.a, this.b, this.d, this.i);
    }

    public int getItemCount() {
        return this.l.size();
    }
}
