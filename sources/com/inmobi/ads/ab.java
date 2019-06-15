package com.inmobi.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.c.k;
import com.inmobi.commons.core.a.a;
import java.lang.ref.WeakReference;

class ab extends bz {
    private static final String d = "ab";
    @NonNull
    private final WeakReference<Context> e;
    @NonNull
    private final ca f;
    @NonNull
    private final ai g = new ai(0);
    @NonNull
    private final ah h;

    ab(@NonNull bd bdVar, @NonNull ca caVar) {
        super(bdVar);
        this.e = new WeakReference(bdVar.j());
        this.f = caVar;
        this.h = bdVar;
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

    public final void d() {
        try {
            Context context = (Context) this.e.get();
            bd bdVar = (bd) this.a;
            if (!(bdVar.l || context == null)) {
                this.g.a(context, bdVar);
            }
        } catch (Exception e) {
            new StringBuilder("Exception in stopTrackingForImpression with message : ").append(e.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e));
        } catch (Throwable th) {
            this.f.d();
        }
        this.f.d();
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
        this.g.a((Context) this.e.get(), this.f.b(), this.h);
        super.e();
        this.e.clear();
        this.f.e();
    }

    @Nullable
    public final View a(View view, ViewGroup viewGroup, boolean z) {
        View b = this.f.b();
        if (b != null) {
            this.g.a((Context) this.e.get(), b, this.h);
        }
        return this.f.a(view, viewGroup, z);
    }

    public final void a(@Nullable View... viewArr) {
        try {
            bd bdVar = (bd) this.a;
            NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) bdVar.getVideoContainerView();
            Context context = (Context) this.e.get();
            k kVar = this.f.c().k;
            if (!(context == null || nativeVideoWrapper == null || bdVar.l)) {
                NativeVideoView videoView = nativeVideoWrapper.getVideoView();
                this.g.a(context, videoView, bdVar, kVar);
                View b = this.f.b();
                if (!(videoView.getTag() == null || b == null)) {
                    be beVar = (be) videoView.getTag();
                    if (PlacementType.PLACEMENT_TYPE_INLINE == bdVar.b.a && !((Boolean) beVar.v.get("isFullScreen")).booleanValue()) {
                        this.g.a(context, b, this.h, ((bd) this.h).C, kVar);
                    }
                }
            }
        } catch (Exception e) {
            new StringBuilder("Exception in startTrackingForImpression with message : ").append(e.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e));
        } catch (Throwable th) {
            this.f.a(viewArr);
        }
        this.f.a(viewArr);
    }
}
