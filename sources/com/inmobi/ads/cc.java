package com.inmobi.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.RenderView;

final class cc extends ca {
    @NonNull
    private final ah d;
    private boolean e = false;
    @Nullable
    private RenderView f;

    public final void a(int i) {
    }

    public final void a(Context context, int i) {
    }

    public final void a(@Nullable View... viewArr) {
    }

    public final void d() {
    }

    cc(@NonNull ah ahVar, @Nullable RenderView renderView) {
        super(ahVar);
        this.d = ahVar;
        this.f = renderView;
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
        Logger.a(InternalLogLevel.DEBUG, "InMobi", "Ad markup loaded into the container will be inflated into a View.");
        view = this.b.a(view, viewGroup, z, this.f);
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
            if (this.f != null) {
                this.f.destroy();
                this.f = null;
            }
            super.e();
        }
    }
}
