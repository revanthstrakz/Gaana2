package com.inmobi.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.ads.c.k;
import com.inmobi.commons.core.a.a;
import java.lang.ref.WeakReference;

class aa extends bz {
    private static final String d = "aa";
    @NonNull
    private final WeakReference<Context> e;
    @NonNull
    private final ca f;
    @NonNull
    private final ai g = new ai(1);
    @NonNull
    private final ah h;

    aa(@NonNull Context context, @NonNull ah ahVar, @NonNull ca caVar) {
        super(ahVar);
        this.e = new WeakReference(context);
        this.f = caVar;
        this.h = ahVar;
    }

    @Nullable
    public final View b() {
        return this.f.b();
    }

    public final a f() {
        return this.f.f();
    }

    /* Access modifiers changed, original: final */
    @NonNull
    public final c c() {
        return this.f.c();
    }

    public final void a(@Nullable View... viewArr) {
        try {
            Context context = (Context) this.e.get();
            View b = this.f.b();
            k kVar = this.f.c().k;
            ah ahVar = (ah) this.a;
            if (!(context == null || b == null || ahVar.l)) {
                this.g.a(context, b, ahVar, kVar);
                this.g.a(context, b, this.h, this.h.A, kVar);
            }
        } catch (Exception e) {
            new StringBuilder("Exception in startTrackingForImpression with message : ").append(e.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e));
        } catch (Throwable th) {
            this.f.a(viewArr);
        }
        this.f.a(viewArr);
    }

    public final void a(int i) {
        this.f.a(i);
    }

    public final void a(Context context, int i) {
        switch (i) {
            case 0:
                ai.b(context);
                break;
            case 1:
                ai.c(context);
                break;
            case 2:
                try {
                    this.g.a(context);
                    break;
                } catch (Exception e) {
                    new StringBuilder("Exception in onActivityStateChanged with message : ").append(e.getMessage());
                    a.a().a(new com.inmobi.commons.core.e.a(e));
                    break;
                } catch (Throwable th) {
                    this.f.a(context, i);
                }
        }
        this.f.a(context, i);
    }

    public final void e() {
        this.g.a(this.h.d(), this.f.b(), this.h);
        super.e();
        this.e.clear();
        this.f.e();
    }

    @Nullable
    public final View a(View view, ViewGroup viewGroup, boolean z) {
        View b = this.f.b();
        if (b != null) {
            this.g.a(this.h.d(), b, this.h);
        }
        return this.f.a(view, viewGroup, z);
    }

    public final void d() {
        try {
            ah ahVar = (ah) this.a;
            if (!ahVar.l) {
                this.g.a((Context) this.e.get(), ahVar);
            }
        } catch (Exception e) {
            new StringBuilder("Exception in stopTrackingForImpression with message : ").append(e.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e));
        } catch (Throwable th) {
            this.f.d();
        }
        this.f.d();
    }
}
