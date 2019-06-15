package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnTouchListener;
import com.facebook.ads.internal.p.d;
import com.facebook.ads.internal.p.e;
import com.facebook.ads.internal.p.e.c;
import com.facebook.ads.internal.p.g;
import com.facebook.ads.internal.p.h;
import com.facebook.ads.internal.p.i;
import com.facebook.ads.internal.protocol.a;
import com.facebook.ads.internal.s.a.j;
import com.facebook.ads.internal.view.hscroll.b;
import org.json.JSONObject;

public abstract class NativeAdBase implements Ad {
    private final e a;

    public static class Image {
        private final g a;

        Image(g gVar) {
            this.a = gVar;
        }

        public Image(String str, int i, int i2) {
            this.a = new g(str, i, i2);
        }

        @Nullable
        public static Image fromJSONObject(JSONObject jSONObject) {
            g a = g.a(jSONObject);
            return a == null ? null : new Image(a);
        }

        public int getHeight() {
            return this.a.c();
        }

        public int getWidth() {
            return this.a.b();
        }
    }

    public enum MediaCacheFlag {
        NONE(d.NONE),
        ALL(d.ALL);
        
        private final d a;

        private MediaCacheFlag(d dVar) {
            this.a = dVar;
        }

        /* Access modifiers changed, original: 0000 */
        public d a() {
            return this.a;
        }

        public long getCacheFlagValue() {
            return this.a.a();
        }
    }

    public enum NativeComponentTag {
        AD_ICON(j.INTERNAL_AD_ICON),
        AD_TITLE(j.INTERNAL_AD_TITLE),
        AD_COVER_IMAGE(j.INTERNAL_AD_COVER_IMAGE),
        AD_SUBTITLE(j.INTERNAL_AD_SUBTITLE),
        AD_BODY(j.INTERNAL_AD_BODY),
        AD_CALL_TO_ACTION(j.INTERNAL_AD_CALL_TO_ACTION),
        AD_SOCIAL_CONTEXT(j.INTERNAL_AD_SOCIAL_CONTEXT),
        AD_CHOICES_ICON(j.INTERNAL_AD_CHOICES_ICON),
        AD_MEDIA(j.INTERNAL_AD_MEDIA);
        
        private final j a;

        private NativeComponentTag(j jVar) {
            this.a = jVar;
        }

        public static void tagView(View view, NativeComponentTag nativeComponentTag) {
            if (view != null && nativeComponentTag != null) {
                j.a(view, nativeComponentTag.a);
            }
        }
    }

    public static class Rating {
        private final i a;

        public Rating(double d, double d2) {
            this.a = new i(d, d2);
        }

        Rating(i iVar) {
            this.a = iVar;
        }

        @Nullable
        public static Rating fromJSONObject(JSONObject jSONObject) {
            i a = i.a(jSONObject);
            return a == null ? null : new Rating(a);
        }

        public double getScale() {
            return this.a.b();
        }

        public double getValue() {
            return this.a.a();
        }
    }

    public NativeAdBase(Context context, com.facebook.ads.internal.adapters.j jVar, com.facebook.ads.internal.j.d dVar) {
        this.a = new e(context, jVar, dVar, getViewTraversalPredicate());
    }

    public NativeAdBase(Context context, String str) {
        this.a = new e(context, str, getViewTraversalPredicate());
    }

    NativeAdBase(NativeAdBase nativeAdBase) {
        this.a = new e(nativeAdBase.a);
    }

    NativeAdBase(e eVar) {
        this.a = eVar;
    }

    public static c getViewTraversalPredicate() {
        return new c() {
            public boolean a(View view) {
                return (view instanceof MediaViewVideoRenderer) || (view instanceof AdChoicesView) || (view instanceof b);
            }
        };
    }

    /* Access modifiers changed, original: 0000 */
    public void a(AdIconView adIconView) {
        if (adIconView != null) {
            this.a.d(true);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(MediaView mediaView) {
        if (mediaView != null) {
            this.a.c(true);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(com.facebook.ads.internal.protocol.e eVar) {
        this.a.a(eVar);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(boolean z) {
        this.a.a(z);
    }

    public void destroy() {
        this.a.d();
    }

    public void downloadMedia() {
        this.a.c();
    }

    /* Access modifiers changed, original: 0000 */
    public e g() {
        return this.a;
    }

    public String getAdBodyText() {
        return this.a.n();
    }

    public String getAdCallToAction() {
        return this.a.p();
    }

    public Image getAdChoicesIcon() {
        return this.a.x() == null ? null : new Image(this.a.x());
    }

    @Nullable
    public String getAdChoicesImageUrl() {
        return this.a.x() == null ? null : this.a.x().a();
    }

    public String getAdChoicesLinkUrl() {
        return this.a.y();
    }

    public String getAdChoicesText() {
        return this.a.z();
    }

    public Image getAdCoverImage() {
        return this.a.j() == null ? null : new Image(this.a.j());
    }

    public String getAdHeadline() {
        return this.a.m();
    }

    public Image getAdIcon() {
        return this.a.i() == null ? null : new Image(this.a.i());
    }

    public String getAdLinkDescription() {
        return this.a.r();
    }

    public String getAdSocialContext() {
        return this.a.q();
    }

    @Deprecated
    public Rating getAdStarRating() {
        return this.a.v() == null ? null : new Rating(this.a.v());
    }

    public String getAdTranslation() {
        return this.a.t();
    }

    public String getAdUntrimmedBodyText() {
        return this.a.o();
    }

    public NativeAdViewAttributes getAdViewAttributes() {
        return this.a.k() == null ? null : new NativeAdViewAttributes(this.a.k());
    }

    public String getAdvertiserName() {
        return this.a.l();
    }

    public String getId() {
        return this.a.w();
    }

    public String getPlacementId() {
        return this.a.e();
    }

    public String getPromotedTranslation() {
        return this.a.u();
    }

    public String getSponsoredTranslation() {
        return this.a.s();
    }

    /* Access modifiers changed, original: 0000 */
    public com.facebook.ads.internal.adapters.j h() {
        return this.a.a();
    }

    public boolean hasCallToAction() {
        return this.a.h();
    }

    /* Access modifiers changed, original: 0000 */
    @Nullable
    public String i() {
        return this.a.F();
    }

    public boolean isAdInvalidated() {
        return this.a.b();
    }

    public boolean isAdLoaded() {
        return this.a.f();
    }

    public boolean isNativeConfigEnabled() {
        return this.a.g();
    }

    public void loadAd() {
        loadAd(MediaCacheFlag.ALL);
    }

    public void loadAd(MediaCacheFlag mediaCacheFlag) {
        this.a.a(mediaCacheFlag.a(), null);
    }

    public void loadAdFromBid(String str) {
        loadAdFromBid(str, MediaCacheFlag.ALL);
    }

    public void loadAdFromBid(String str, MediaCacheFlag mediaCacheFlag) {
        this.a.a(mediaCacheFlag.a(), str);
    }

    public void onCtaBroadcast() {
        this.a.G();
    }

    public void setAdListener(final NativeAdListener nativeAdListener) {
        if (nativeAdListener != null) {
            this.a.a(new h() {
                public void a() {
                    nativeAdListener.onMediaDownloaded(NativeAdBase.this);
                }

                public void a(a aVar) {
                    nativeAdListener.onError(NativeAdBase.this, AdError.getAdErrorFromWrapper(aVar));
                }

                public void b() {
                    nativeAdListener.onAdLoaded(NativeAdBase.this);
                }

                public void c() {
                    nativeAdListener.onAdClicked(NativeAdBase.this);
                }

                public void d() {
                    nativeAdListener.onLoggingImpression(NativeAdBase.this);
                }
            });
        }
    }

    public void setExtraHints(ExtraHints extraHints) {
        if (extraHints != null) {
            this.a.a(extraHints.getHints());
        }
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.a.a(onTouchListener);
    }

    public void unregisterView() {
        this.a.I();
    }
}
