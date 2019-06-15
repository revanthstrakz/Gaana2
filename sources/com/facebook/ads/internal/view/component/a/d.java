package com.facebook.ads.internal.view.component.a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.adapters.a.g;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.a.w;
import com.facebook.ads.internal.view.g.c.o;

public class d {
    private final Context a;
    private final c b;
    private final com.facebook.ads.internal.view.a.a c;
    private final g d;
    private final View e;
    private final com.facebook.ads.internal.t.a f;
    private final w g;
    private final int h;
    private final int i;
    @Nullable
    private final o j;
    @Nullable
    private final View k;

    public static class a {
        private final Context a;
        private final c b;
        private final com.facebook.ads.internal.view.a.a c;
        private final g d;
        private final View e;
        private final com.facebook.ads.internal.t.a f;
        private final w g;
        private int h = 0;
        private int i = 1;
        @Nullable
        private o j;
        @Nullable
        private View k;

        public a(Context context, c cVar, com.facebook.ads.internal.view.a.a aVar, g gVar, View view, com.facebook.ads.internal.t.a aVar2, w wVar) {
            this.a = context;
            this.b = cVar;
            this.c = aVar;
            this.d = gVar;
            this.e = view;
            this.f = aVar2;
            this.g = wVar;
        }

        public a a(int i) {
            this.h = i;
            return this;
        }

        public a a(View view) {
            this.k = view;
            return this;
        }

        public a a(o oVar) {
            this.j = oVar;
            return this;
        }

        public d a() {
            return new d(this);
        }

        public a b(int i) {
            this.i = i;
            return this;
        }
    }

    private d(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
    }

    /* Access modifiers changed, original: 0000 */
    public Context a() {
        return this.a;
    }

    /* Access modifiers changed, original: 0000 */
    public c b() {
        return this.b;
    }

    /* Access modifiers changed, original: 0000 */
    public com.facebook.ads.internal.view.a.a c() {
        return this.c;
    }

    /* Access modifiers changed, original: 0000 */
    public View d() {
        return this.e;
    }

    /* Access modifiers changed, original: 0000 */
    public com.facebook.ads.internal.t.a e() {
        return this.f;
    }

    /* Access modifiers changed, original: 0000 */
    public w f() {
        return this.g;
    }

    /* Access modifiers changed, original: 0000 */
    public g g() {
        return this.d;
    }

    /* Access modifiers changed, original: 0000 */
    public o h() {
        return this.j;
    }

    /* Access modifiers changed, original: 0000 */
    public View i() {
        return this.k;
    }

    /* Access modifiers changed, original: 0000 */
    public int j() {
        return this.h;
    }

    /* Access modifiers changed, original: 0000 */
    public int k() {
        return this.i;
    }
}
