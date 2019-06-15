package com.facebook.ads.internal.view.d.a;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.ViewGroup.MarginLayoutParams;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.a.k;
import com.facebook.ads.internal.s.a.w;
import com.facebook.ads.internal.t.a;
import com.facebook.ads.internal.view.component.a.a.b;
import java.util.Map;

class g extends ViewHolder {
    private final b a;
    private final SparseBooleanArray b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    @Nullable
    private a g;
    private a.a h;
    private a i;

    g(b bVar, SparseBooleanArray sparseBooleanArray, a aVar, int i, int i2, int i3, int i4) {
        super(bVar);
        this.a = bVar;
        this.b = sparseBooleanArray;
        this.i = aVar;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
    }

    private String a(com.facebook.ads.internal.f.b bVar, String str) {
        CharSequence b = (bVar == null || str == null) ? "" : bVar.b(str);
        return !TextUtils.isEmpty(b) ? b : str;
    }

    private void a(c cVar, w wVar, String str, final b bVar) {
        if (!this.b.get(bVar.b())) {
            if (this.g != null) {
                this.g.c();
                this.g = null;
            }
            final Map a = bVar.a();
            final String str2 = str;
            final b bVar2 = bVar;
            final w wVar2 = wVar;
            final c cVar2 = cVar;
            this.h = new a.a() {
                public void a() {
                    if (!g.this.i.b() && !TextUtils.isEmpty(str2) && !g.this.b.get(bVar2.b())) {
                        if (g.this.g != null) {
                            g.this.g.a(a);
                        }
                        a.put("touch", k.a(wVar2.e()));
                        cVar2.a(str2, a);
                        g.this.b.put(bVar2.b(), true);
                    }
                }
            };
            this.g = new a(this.a, 10, this.h);
            this.g.a(100);
            this.g.b(100);
            this.a.setOnAssetsLoadedListener(new b.a() {
                public void a() {
                    if (bVar.b() == 0) {
                        g.this.i.a();
                    }
                    g.this.g.a();
                }
            });
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(b bVar, c cVar, com.facebook.ads.internal.f.b bVar2, w wVar, String str) {
        int b = bVar.b();
        this.a.setTag(-1593835536, Integer.valueOf(b));
        MarginLayoutParams marginLayoutParams = new MarginLayoutParams(this.c, -2);
        marginLayoutParams.setMargins(b == 0 ? this.d : this.e, 0, b >= this.f + -1 ? this.d : this.e, 0);
        String g = bVar.c().c().g();
        String a = bVar.c().c().a();
        this.a.setIsVideo(TextUtils.isEmpty(a) ^ 1);
        if (this.a.f()) {
            this.a.setVideoPlaceholderUrl(g);
            this.a.setVideoUrl(a(bVar2, a));
        } else {
            this.a.setImageUrl(g);
        }
        this.a.setLayoutParams(marginLayoutParams);
        this.a.a(bVar.c().a().a(), bVar.c().a().c());
        this.a.a(bVar.c().b(), bVar.a());
        this.a.a(bVar.a());
        a(cVar, wVar, str, bVar);
    }
}
