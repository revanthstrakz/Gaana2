package com.til.colombia.android.service;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.til.colombia.android.R;
import com.til.colombia.android.commons.CommonUtil;
import com.til.colombia.android.internal.views.CloseableLayout;
import com.til.colombia.android.network.l;
import com.til.colombia.android.vast.VastCompanionAdConfig;
import com.til.colombia.android.vast.VastTrackingEvent;
import com.til.colombia.android.vast.i;

final class q extends FrameLayout {
    float a;
    float b;
    private Context c;
    private Item d;
    private VastCompanionAdConfig e;
    private VASTHelper f;
    private View g;
    private ah h;
    private AdListener i;
    private CloseableLayout j;
    private TextView k;
    private final float l = 8.0f;

    private class a implements OnTouchListener {
        private a() {
        }

        /* synthetic */ a(q qVar, byte b) {
            this();
        }

        public final boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    return false;
                case 1:
                    if (q.this.e.getClickTrackers() != null) {
                        for (VastTrackingEvent url : q.this.e.getClickTrackers()) {
                            l.a(url.getUrl(), 5, null);
                        }
                    }
                    bi.a();
                    bi.a(q.this.d);
                    ((NativeItem) q.this.d).getAdListener().onMediaItemClicked(q.this.d);
                    new Handler(Looper.getMainLooper()).postDelayed(new u(this), 1000);
                    break;
                case 2:
                    return true;
            }
            return false;
        }
    }

    private q(Context context) {
        super(context);
        this.c = context;
    }

    private q(Context context, int i, int i2) {
        super(context);
        this.c = context;
        setLayoutParams(new LayoutParams(CommonUtil.b((float) i, this.c), CommonUtil.b((float) i2, this.c)));
    }

    private q(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = context;
    }

    public q(Context context, Item item, AdListener adListener) {
        super(context);
        this.c = context;
        this.d = item;
        this.i = adListener;
        this.f = ((NativeItem) item).getVastHelper();
    }

    private CloseableLayout d() {
        return this.j;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0083 */
    /* JADX WARNING: Missing block: B:16:0x006a, code skipped:
            return;
     */
    public final synchronized void a() {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = r4.e();	 Catch:{ all -> 0x00a8 }
        if (r0 == 0) goto L_0x0098;
    L_0x0007:
        r0 = new android.view.WindowManager$LayoutParams;	 Catch:{ all -> 0x00a8 }
        r0.<init>();	 Catch:{ all -> 0x00a8 }
        r1 = 17;
        r0.gravity = r1;	 Catch:{ all -> 0x00a8 }
        r1 = 0;
        r0.x = r1;	 Catch:{ all -> 0x00a8 }
        r0.y = r1;	 Catch:{ all -> 0x00a8 }
        r2 = -1;
        r0.height = r2;	 Catch:{ all -> 0x00a8 }
        r0.width = r2;	 Catch:{ all -> 0x00a8 }
        r2 = 1410; // 0x582 float:1.976E-42 double:6.966E-321;
        r0.flags = r2;	 Catch:{ all -> 0x00a8 }
        r2 = -3;
        r0.format = r2;	 Catch:{ all -> 0x00a8 }
        r0.windowAnimations = r1;	 Catch:{ all -> 0x00a8 }
        r1 = r4.c;	 Catch:{ BadTokenException -> 0x0083, Exception -> 0x006b }
        r1 = (android.app.Activity) r1;	 Catch:{ BadTokenException -> 0x0083, Exception -> 0x006b }
        r1 = r1.getWindowManager();	 Catch:{ BadTokenException -> 0x0083, Exception -> 0x006b }
        r1.addView(r4, r0);	 Catch:{ BadTokenException -> 0x0083, Exception -> 0x006b }
        r0 = r4.d;	 Catch:{ all -> 0x00a8 }
        r0 = (com.til.colombia.android.service.NativeItem) r0;	 Catch:{ all -> 0x00a8 }
        r0 = r0.getItemResponse();	 Catch:{ all -> 0x00a8 }
        r1 = 0;
        r0.recordItemResponseImpression(r1);	 Catch:{ all -> 0x00a8 }
        r0 = r4.e;	 Catch:{ all -> 0x00a8 }
        r0 = r0.getCreativeViewTrackers();	 Catch:{ all -> 0x00a8 }
        if (r0 == 0) goto L_0x0069;
    L_0x0042:
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x00a8 }
        r2 = r0.size();	 Catch:{ all -> 0x00a8 }
        r1.<init>(r2);	 Catch:{ all -> 0x00a8 }
        r0 = r0.iterator();	 Catch:{ all -> 0x00a8 }
    L_0x004f:
        r2 = r0.hasNext();	 Catch:{ all -> 0x00a8 }
        if (r2 == 0) goto L_0x0063;
    L_0x0055:
        r2 = r0.next();	 Catch:{ all -> 0x00a8 }
        r2 = (com.til.colombia.android.vast.VastTrackingEvent) r2;	 Catch:{ all -> 0x00a8 }
        r2 = r2.getUrl();	 Catch:{ all -> 0x00a8 }
        r1.add(r2);	 Catch:{ all -> 0x00a8 }
        goto L_0x004f;
    L_0x0063:
        r0 = 5;
        r2 = "companion creative view tracked.";
        com.til.colombia.android.network.n.a(r1, r0, r2);	 Catch:{ all -> 0x00a8 }
    L_0x0069:
        monitor-exit(r4);
        return;
    L_0x006b:
        r0 = move-exception;
        r1 = r4.h;	 Catch:{ all -> 0x00a8 }
        r1.d();	 Catch:{ all -> 0x00a8 }
        r1 = r4.i;	 Catch:{ all -> 0x00a8 }
        r2 = r4.d;	 Catch:{ all -> 0x00a8 }
        r3 = new java.lang.Exception;	 Catch:{ all -> 0x00a8 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x00a8 }
        r3.<init>(r0);	 Catch:{ all -> 0x00a8 }
        r1.onMediaItemError(r2, r3);	 Catch:{ all -> 0x00a8 }
        monitor-exit(r4);
        return;
    L_0x0083:
        r0 = r4.h;	 Catch:{ all -> 0x00a8 }
        r0.d();	 Catch:{ all -> 0x00a8 }
        r0 = r4.i;	 Catch:{ all -> 0x00a8 }
        r1 = r4.d;	 Catch:{ all -> 0x00a8 }
        r2 = new java.lang.Exception;	 Catch:{ all -> 0x00a8 }
        r3 = "WindowManager BadTokenException";
        r2.<init>(r3);	 Catch:{ all -> 0x00a8 }
        r0.onMediaItemError(r1, r2);	 Catch:{ all -> 0x00a8 }
        monitor-exit(r4);
        return;
    L_0x0098:
        r0 = r4.i;	 Catch:{ all -> 0x00a8 }
        r1 = r4.d;	 Catch:{ all -> 0x00a8 }
        r2 = new java.lang.Exception;	 Catch:{ all -> 0x00a8 }
        r3 = "invalid companion.";
        r2.<init>(r3);	 Catch:{ all -> 0x00a8 }
        r0.onMediaItemError(r1, r2);	 Catch:{ all -> 0x00a8 }
        monitor-exit(r4);
        return;
    L_0x00a8:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.service.q.a():void");
    }

    /* JADX WARNING: Missing block: B:17:0x00be, code skipped:
            return false;
     */
    private synchronized boolean e() {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r5.c;	 Catch:{ all -> 0x00bf }
        r0 = (android.app.Activity) r0;	 Catch:{ all -> 0x00bf }
        r0 = r0.getLayoutInflater();	 Catch:{ all -> 0x00bf }
        r1 = com.til.colombia.android.R.layout.audio_banner_layout;	 Catch:{ all -> 0x00bf }
        r2 = 0;
        r0 = r0.inflate(r1, r2);	 Catch:{ all -> 0x00bf }
        r0 = (com.til.colombia.android.internal.views.CloseableLayout) r0;	 Catch:{ all -> 0x00bf }
        r5.j = r0;	 Catch:{ all -> 0x00bf }
        r0 = r5.j;	 Catch:{ all -> 0x00bf }
        r1 = new com.til.colombia.android.service.r;	 Catch:{ all -> 0x00bf }
        r1.<init>(r5);	 Catch:{ all -> 0x00bf }
        r0.d = r1;	 Catch:{ all -> 0x00bf }
        r0 = r5.j;	 Catch:{ all -> 0x00bf }
        r1 = 0;
        r0.a(r1);	 Catch:{ all -> 0x00bf }
        r0 = r5.c;	 Catch:{ all -> 0x00bf }
        r0 = (android.app.Activity) r0;	 Catch:{ all -> 0x00bf }
        r0 = r0.getLayoutInflater();	 Catch:{ all -> 0x00bf }
        r3 = com.til.colombia.android.R.layout.ad_logo_layout;	 Catch:{ all -> 0x00bf }
        r0 = r0.inflate(r3, r2);	 Catch:{ all -> 0x00bf }
        r2 = com.til.colombia.android.R.id.timer;	 Catch:{ all -> 0x00bf }
        r2 = r0.findViewById(r2);	 Catch:{ all -> 0x00bf }
        r2 = (android.widget.TextView) r2;	 Catch:{ all -> 0x00bf }
        r5.k = r2;	 Catch:{ all -> 0x00bf }
        r2 = new android.widget.FrameLayout$LayoutParams;	 Catch:{ all -> 0x00bf }
        r3 = -1;
        r4 = -2;
        r2.<init>(r3, r4);	 Catch:{ all -> 0x00bf }
        r3 = 80;
        r2.gravity = r3;	 Catch:{ all -> 0x00bf }
        r3 = r5.j;	 Catch:{ all -> 0x00bf }
        r3.addView(r0, r2);	 Catch:{ all -> 0x00bf }
        r0 = r5.f;	 Catch:{ all -> 0x00bf }
        r0 = r0.getBestCompanionAdConfig();	 Catch:{ all -> 0x00bf }
        r5.e = r0;	 Catch:{ all -> 0x00bf }
        r0 = r5.e;	 Catch:{ all -> 0x00bf }
        if (r0 == 0) goto L_0x00bd;
    L_0x0057:
        r0 = r5.e;	 Catch:{ all -> 0x00bf }
        r0 = r0.getVastResource();	 Catch:{ all -> 0x00bf }
        if (r0 == 0) goto L_0x00bd;
    L_0x005f:
        r0 = r5.e;	 Catch:{ all -> 0x00bf }
        r0 = r0.getVastResource();	 Catch:{ all -> 0x00bf }
        r0 = r0.getType();	 Catch:{ all -> 0x00bf }
        r1 = com.til.colombia.android.vast.VastCompanionResource.Type.STATIC_RESOURCE;	 Catch:{ all -> 0x00bf }
        if (r0 != r1) goto L_0x0085;
    L_0x006d:
        r0 = r5.e;	 Catch:{ all -> 0x00bf }
        r0 = r0.getVastResource();	 Catch:{ all -> 0x00bf }
        r0 = r0.getCreativeType();	 Catch:{ all -> 0x00bf }
        r1 = com.til.colombia.android.vast.VastCompanionResource.CreativeType.IMAGE;	 Catch:{ all -> 0x00bf }
        if (r0 != r1) goto L_0x0085;
    L_0x007b:
        r0 = new android.widget.ImageView;	 Catch:{ all -> 0x00bf }
        r1 = r5.c;	 Catch:{ all -> 0x00bf }
        r0.<init>(r1);	 Catch:{ all -> 0x00bf }
        r5.g = r0;	 Catch:{ all -> 0x00bf }
        goto L_0x008e;
    L_0x0085:
        r0 = new com.til.colombia.android.vast.i;	 Catch:{ all -> 0x00bf }
        r1 = r5.c;	 Catch:{ all -> 0x00bf }
        r0.<init>(r1);	 Catch:{ all -> 0x00bf }
        r5.g = r0;	 Catch:{ all -> 0x00bf }
    L_0x008e:
        r0 = r5.g;	 Catch:{ all -> 0x00bf }
        com.til.colombia.android.commons.CommonUtil.a(r0);	 Catch:{ all -> 0x00bf }
        r0 = r5.j;	 Catch:{ all -> 0x00bf }
        r1 = r5.g;	 Catch:{ all -> 0x00bf }
        r0.addView(r1);	 Catch:{ all -> 0x00bf }
        r0 = r5.e;	 Catch:{ all -> 0x00bf }
        r0 = r0.getVastResource();	 Catch:{ all -> 0x00bf }
        r1 = r5.g;	 Catch:{ all -> 0x00bf }
        r0.initializeVastResourceView(r1);	 Catch:{ all -> 0x00bf }
        r0 = new com.til.colombia.android.service.ah;	 Catch:{ all -> 0x00bf }
        r1 = r5.c;	 Catch:{ all -> 0x00bf }
        r2 = r5.d;	 Catch:{ all -> 0x00bf }
        r3 = r5.i;	 Catch:{ all -> 0x00bf }
        r0.<init>(r1, r2, r3, r5);	 Catch:{ all -> 0x00bf }
        r5.h = r0;	 Catch:{ all -> 0x00bf }
        r5.h();	 Catch:{ all -> 0x00bf }
        r0 = r5.j;	 Catch:{ all -> 0x00bf }
        r5.addView(r0);	 Catch:{ all -> 0x00bf }
        r0 = 1;
        monitor-exit(r5);
        return r0;
    L_0x00bd:
        monitor-exit(r5);
        return r1;
    L_0x00bf:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.service.q.e():boolean");
    }

    private void f() {
        View inflate = ((Activity) this.c).getLayoutInflater().inflate(R.layout.ad_logo_layout, null);
        this.k = (TextView) inflate.findViewById(R.id.timer);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 80;
        this.j.addView(inflate, layoutParams);
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(String str) {
        if (this.k != null) {
            this.k.setText(str);
        }
    }

    private void g() {
        if (this.g != null && (this.g instanceof i)) {
            ((i) this.g).clearHistory();
            ((i) this.g).clearCache(true);
            ((i) this.g).destroy();
        }
        this.g = null;
    }

    public final void b() {
        this.j.a(true);
    }

    public final void c() {
        synchronized (this) {
            g();
            if (getParent() != null) {
                ((Activity) this.c).getWindowManager().removeView(this);
            }
        }
    }

    private synchronized void h() {
        if (this.g == null || !(this.g instanceof i)) {
            this.g.setOnTouchListener(new s(this));
        } else {
            ((i) this.g).setOnTouchListener(new a(this, (byte) 0));
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void onDetachedFromWindow() {
        if (this.h != null) {
            this.h.d();
        }
        super.onDetachedFromWindow();
    }
}
