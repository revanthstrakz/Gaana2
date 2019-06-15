package com.facebook.ads.internal.b;

import android.os.Bundle;
import android.view.View;
import com.facebook.ads.internal.s.a.r;
import java.util.ArrayList;
import java.util.List;

public final class a implements r<Bundle> {
    private final View a;
    private final List<d> b;
    private c c;

    public a(View view, List<b> list) {
        this.a = view;
        this.b = new ArrayList(list.size());
        for (b dVar : list) {
            this.b.add(new d(dVar));
        }
        this.c = new c();
    }

    public a(View view, List<b> list, Bundle bundle) {
        this.a = view;
        this.b = new ArrayList(list.size());
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("TESTS");
        for (int i = 0; i < list.size(); i++) {
            this.b.add(new d((b) list.get(i), (Bundle) parcelableArrayList.get(i)));
        }
        this.c = (c) bundle.getSerializable("STATISTICS");
    }

    public void a() {
        this.c.a();
    }

    public void a(double d, double d2) {
        if (d2 >= 0.0d) {
            this.c.b(d, d2);
        }
        d2 = (double) com.facebook.ads.internal.t.a.a(this.a, 0).c();
        this.c.a(d, d2);
        for (d a : this.b) {
            a.a(d, d2);
        }
    }

    public void b() {
        this.c.b();
        for (d a : this.b) {
            a.a();
        }
    }

    public c c() {
        return this.c;
    }

    public Bundle g() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("STATISTICS", this.c);
        ArrayList arrayList = new ArrayList(this.b.size());
        for (d g : this.b) {
            arrayList.add(g.g());
        }
        bundle.putParcelableArrayList("TESTS", arrayList);
        return bundle;
    }
}
