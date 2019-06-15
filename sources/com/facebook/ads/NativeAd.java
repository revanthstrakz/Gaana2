package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.facebook.ads.NativeAdView.Type;
import com.facebook.ads.internal.adapters.j;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.p.f;
import com.facebook.ads.internal.protocol.e;
import java.util.ArrayList;
import java.util.List;

public class NativeAd extends NativeAdBase {

    public enum AdCreativeType {
        IMAGE,
        VIDEO,
        CAROUSEL,
        UNKNOWN
    }

    protected NativeAd(Context context, j jVar, d dVar) {
        super(context, jVar, dVar);
        a(e.NATIVE_UNKNOWN);
    }

    public NativeAd(Context context, String str) {
        super(context, str);
        a(e.NATIVE_UNKNOWN);
    }

    NativeAd(NativeAdBase nativeAdBase) {
        super(nativeAdBase);
    }

    NativeAd(com.facebook.ads.internal.p.e eVar) {
        super(eVar);
    }

    /* Access modifiers changed, original: 0000 */
    public String a() {
        return g().A();
    }

    /* Access modifiers changed, original: 0000 */
    public void a(Type type) {
        g().a(type.a());
    }

    /* Access modifiers changed, original: 0000 */
    public String b() {
        return g().B();
    }

    /* Access modifiers changed, original: 0000 */
    public String c() {
        return g().C();
    }

    /* Access modifiers changed, original: 0000 */
    public VideoAutoplayBehavior d() {
        return VideoAutoplayBehavior.fromInternalAutoplayBehavior(g().D());
    }

    /* Access modifiers changed, original: 0000 */
    public List<NativeAd> e() {
        if (g().E() == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (com.facebook.ads.internal.p.e nativeAd : g().E()) {
            arrayList.add(new NativeAd(nativeAd));
        }
        return arrayList;
    }

    /* Access modifiers changed, original: 0000 */
    public Type f() {
        return g().H() == null ? null : Type.a(g().H());
    }

    public AdCreativeType getAdCreativeType() {
        return !TextUtils.isEmpty(g().A()) ? AdCreativeType.VIDEO : (g().E() == null || g().E().isEmpty()) ? (g().j() == null || TextUtils.isEmpty(g().j().a())) ? AdCreativeType.UNKNOWN : AdCreativeType.IMAGE : AdCreativeType.CAROUSEL;
    }

    public void registerViewForInteraction(View view, MediaView mediaView) {
        registerViewForInteraction(view, mediaView, (AdIconView) null);
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable ImageView imageView) {
        registerViewForInteraction(view, mediaView, imageView, null);
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable ImageView imageView, @Nullable List<View> list) {
        if (imageView != null) {
            com.facebook.ads.internal.p.e.a(g().i(), imageView);
        }
        registerViewForInteraction(view, mediaView, (AdIconView) null, (List) list);
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable AdIconView adIconView) {
        registerViewForInteraction(view, mediaView, adIconView, null);
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable AdIconView adIconView, @Nullable List<View> list) {
        if (mediaView != null) {
            mediaView.setNativeAd(this);
        }
        if (adIconView != null) {
            adIconView.setNativeAd(this);
        }
        if (list != null) {
            g().a(view, (f) mediaView, (List) list);
        } else {
            g().a(view, (f) mediaView);
        }
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable List<View> list) {
        registerViewForInteraction(view, mediaView, (AdIconView) null, (List) list);
    }
}
