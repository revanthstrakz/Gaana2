package com.inmobi.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

final class cd extends ca {
    @NonNull
    private final bd d;
    private boolean e = false;

    public final void a(int i) {
    }

    public final void a(Context context, int i) {
    }

    public final void a(@Nullable View... viewArr) {
    }

    public final void d() {
    }

    cd(@NonNull bd bdVar) {
        super(bdVar);
        this.d = bdVar;
    }

    /* Access modifiers changed, original: final */
    @NonNull
    public final c c() {
        return this.d.c;
    }

    @Nullable
    public final View a(View view, ViewGroup viewGroup, boolean z) {
        if (this.e) {
            return null;
        }
        Context j = this.d.j();
        if (j == null) {
            return null;
        }
        this.b = new at(j, this.d.c, this.d, this.d.h());
        view = this.b.a(view, viewGroup, false, null);
        a(view);
        this.d.t();
        return view;
    }

    public final void e() {
        if (!this.e) {
            this.e = true;
            if (this.b != null) {
                this.b.a();
            }
            super.e();
        }
    }
}
