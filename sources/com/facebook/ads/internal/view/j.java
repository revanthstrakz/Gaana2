package com.facebook.ads.internal.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.NativeAd;
import com.facebook.ads.internal.adapters.w;
import com.facebook.ads.internal.view.g.a;
import com.facebook.ads.internal.view.g.b.b;
import com.facebook.ads.internal.view.g.b.c;
import com.facebook.ads.internal.view.g.b.h;
import com.facebook.ads.internal.view.g.b.i;
import com.facebook.ads.internal.view.g.b.k;
import com.google.android.exoplayer2.C;
import java.util.UUID;

public class j extends a {
    private final String b = UUID.randomUUID().toString();
    private final k c = new k() {
        public void a(com.facebook.ads.internal.view.g.b.j jVar) {
            if (j.this.n != null) {
                j.this.n.c();
            }
        }
    };
    private final i d = new i() {
        public void a(h hVar) {
            if (j.this.n != null) {
                j.this.n.b();
            }
        }
    };
    private final c e = new c() {
        public void a(b bVar) {
            if (j.this.n != null) {
                j.this.n.h();
            }
        }
    };
    private final w f;
    private com.facebook.ads.internal.o.c g;
    @Nullable
    private com.facebook.ads.internal.view.g.b h;
    @Nullable
    private String i;
    @Nullable
    private Uri j;
    @Nullable
    private String k;
    @Nullable
    private String l;
    @Nullable
    private String m;
    @Nullable
    private k n;
    @Nullable
    private NativeAd o;

    public j(Context context) {
        super(context);
        this.f = new w(this, context);
        u();
    }

    public j(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = new w(this, context);
        u();
    }

    public j(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = new w(this, context);
        u();
    }

    @TargetApi(21)
    public j(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f = new w(this, context);
        u();
    }

    private void a(Intent intent) {
        if (this.i == null || this.h == null) {
            throw new IllegalStateException("Must setVideoReportUri first.");
        } else if (this.j == null && this.l == null) {
            throw new IllegalStateException("Must setVideoURI or setVideoMPD first.");
        } else {
            intent.putExtra("useNativeCtaButton", this.m);
            intent.putExtra(AudienceNetworkActivity.VIEW_TYPE, com.facebook.ads.internal.settings.a.a.FULL_SCREEN_VIDEO);
            intent.putExtra(AudienceNetworkActivity.VIDEO_URL, this.j.toString());
            intent.putExtra(AudienceNetworkActivity.CLIENT_TOKEN, this.k == null ? "" : this.k);
            intent.putExtra(AudienceNetworkActivity.VIDEO_MPD, this.l);
            intent.putExtra(AudienceNetworkActivity.PREDEFINED_ORIENTATION_KEY, 13);
            intent.putExtra(AudienceNetworkActivity.VIDEO_SEEK_TIME, getCurrentPositionInMillis());
            intent.putExtra(AudienceNetworkActivity.AUDIENCE_NETWORK_UNIQUE_ID_EXTRA, this.b);
            intent.putExtra(AudienceNetworkActivity.VIDEO_LOGGER, this.h.g());
            intent.putExtra("video_time_polling_interval", getVideoProgressReportIntervalMs());
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
    }

    private void u() {
        getEventBus().a(this.c, this.d, this.e);
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0026 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:4:0x001b, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x001c, code skipped:
            com.facebook.ads.internal.l.b.a(com.facebook.ads.internal.l.a.a(r0, "Error occurred while loading fullscreen video activity."));
     */
    /* JADX WARNING: Missing block: B:6:0x0025, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:8:?, code skipped:
            r1.setClass(r0, com.facebook.ads.InterstitialAdActivity.class);
            r0.startActivity(r1);
     */
    /* JADX WARNING: Missing block: B:9:0x002e, code skipped:
            return;
     */
    public void a() {
        /*
        r3 = this;
        r0 = r3.getContext();
        r1 = new android.content.Intent;
        r2 = com.facebook.ads.AudienceNetworkActivity.class;
        r1.<init>(r0, r2);
        r3.a(r1);
        r2 = 0;
        r3.a(r2);	 Catch:{ ActivityNotFoundException -> 0x0026 }
        r2 = 8;
        r3.setVisibility(r2);	 Catch:{ ActivityNotFoundException -> 0x0026 }
        r0.startActivity(r1);	 Catch:{ ActivityNotFoundException -> 0x0026 }
        return;
    L_0x001b:
        r0 = move-exception;
        r1 = "Error occurred while loading fullscreen video activity.";
        r0 = com.facebook.ads.internal.l.a.a(r0, r1);
        com.facebook.ads.internal.l.b.a(r0);
        return;
    L_0x0026:
        r2 = com.facebook.ads.InterstitialAdActivity.class;
        r1.setClass(r0, r2);	 Catch:{ Exception -> 0x001b }
        r0.startActivity(r1);	 Catch:{ Exception -> 0x001b }
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.j.a():void");
    }

    public void a(@Nullable String str, @Nullable String str2) {
        if (this.h != null) {
            this.h.a();
        }
        this.k = str2;
        this.i = str;
        com.facebook.ads.internal.view.g.b bVar = (str == null || str2 == null) ? null : new com.facebook.ads.internal.view.g.b(getContext(), this.g, this, str2);
        this.h = bVar;
    }

    public void b() {
        if (this.o != null) {
            this.o.onCtaBroadcast();
        }
    }

    @Nullable
    public k getListener() {
        return this.n;
    }

    public String getUniqueId() {
        return this.b;
    }

    /* Access modifiers changed, original: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f.a();
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        this.f.b();
        super.onDetachedFromWindow();
    }

    public void setAdEventManager(com.facebook.ads.internal.o.c cVar) {
        this.g = cVar;
    }

    public void setEnableBackgroundVideo(boolean z) {
        this.a.setBackgroundPlaybackEnabled(z);
    }

    public void setListener(@Nullable k kVar) {
        this.n = kVar;
    }

    public void setNativeAd(@Nullable NativeAd nativeAd) {
        this.o = nativeAd;
    }

    public void setVideoCTA(@Nullable String str) {
        this.m = str;
    }

    public void setVideoMPD(@Nullable String str) {
        if (str == null || this.h != null) {
            this.l = str;
            super.setVideoMPD(str);
            return;
        }
        throw new IllegalStateException("Must setVideoReportUri first.");
    }

    public void setVideoURI(@Nullable Uri uri) {
        if (uri == null || this.h != null) {
            this.j = uri;
            super.setVideoURI(uri);
            return;
        }
        throw new IllegalStateException("Must setVideoReportUri first.");
    }
}
