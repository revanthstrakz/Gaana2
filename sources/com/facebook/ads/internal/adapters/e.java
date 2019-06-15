package com.facebook.ads.internal.adapters;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import com.facebook.ads.internal.p.f;
import com.facebook.ads.internal.p.g;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.c;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.hscroll.b;
import com.facebook.ads.internal.view.l;
import com.facebook.ads.internal.view.s;
import java.util.List;

public class e extends Adapter<c> {
    private static final int a = Color.argb(51, 0, 0, 0);
    private final com.facebook.ads.internal.t.a.a b = new com.facebook.ads.internal.t.a.a() {
        public void a() {
            if (e.this.f != null) {
                e.this.f.a();
            }
        }
    };
    private final List<com.facebook.ads.internal.p.e> c;
    private final int d;
    private final int e;
    @Nullable
    private a f;

    public interface a {
        void a();
    }

    public e(b bVar, List<com.facebook.ads.internal.p.e> list) {
        float f = bVar.getContext().getResources().getDisplayMetrics().density;
        this.c = list;
        this.d = Math.round(f * 1.0f);
        this.e = bVar.getChildSpacing();
    }

    /* renamed from: a */
    public c onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new c(new l(viewGroup.getContext()));
    }

    public void a(a aVar) {
        this.f = aVar;
    }

    /* renamed from: a */
    public void onBindViewHolder(c cVar, final int i) {
        MarginLayoutParams marginLayoutParams = new MarginLayoutParams(-2, -1);
        marginLayoutParams.setMargins(i == 0 ? this.e * 2 : this.e, 0, i >= this.c.size() + -1 ? this.e * 2 : this.e, 0);
        View view = (l) cVar.a;
        view.setLayoutParams(marginLayoutParams);
        view.setPadding(this.d, this.d, this.d, this.d);
        final ImageView imageView = (s) view.getAdContentsView();
        y.a((View) imageView, 0);
        imageView.setImageDrawable(null);
        final com.facebook.ads.internal.p.e eVar = (com.facebook.ads.internal.p.e) this.c.get(i);
        eVar.a(view, (f) view);
        g j = eVar.j();
        if (j != null) {
            d a = new d(imageView).a();
            a.a(new com.facebook.ads.internal.view.c.e() {
                public void a(boolean z) {
                    if (i == 0) {
                        eVar.a(e.this.b);
                    }
                    eVar.a(z, true);
                    y.a(imageView, e.a);
                }
            });
            a.a(j.a());
        }
    }

    public int getItemCount() {
        return this.c.size();
    }
}
