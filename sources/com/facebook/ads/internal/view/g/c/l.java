package com.facebook.ads.internal.view.g.c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.g.a.a;
import com.facebook.ads.internal.view.g.a.c;
import com.facebook.ads.internal.view.g.b.b;
import com.facebook.ads.internal.view.g.b.h;
import com.facebook.ads.internal.view.g.b.i;
import com.facebook.ads.internal.view.g.b.j;
import com.facebook.ads.internal.view.g.b.k;
import com.facebook.ads.internal.view.g.d.d;
import com.google.api.client.http.HttpStatusCodes;

public class l extends c {
    private final i a;
    private final k b;
    private final com.facebook.ads.internal.view.g.b.c c;
    private final m d;
    private final Paint e;

    /* renamed from: com.facebook.ads.internal.view.g.c.l$5 */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] a = new int[d.values().length];

        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Missing block: B:13:?, code skipped:
            return;
     */
        static {
            /*
            r0 = com.facebook.ads.internal.view.g.d.d.values();
            r0 = r0.length;
            r0 = new int[r0];
            a = r0;
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = com.facebook.ads.internal.view.g.d.d.PREPARED;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = 1;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = com.facebook.ads.internal.view.g.d.d.IDLE;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2 = 2;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x002a }
            r1 = com.facebook.ads.internal.view.g.d.d.PAUSED;	 Catch:{ NoSuchFieldError -> 0x002a }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x002a }
            r2 = 3;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x002a }
        L_0x002a:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0035 }
            r1 = com.facebook.ads.internal.view.g.d.d.PLAYBACK_COMPLETED;	 Catch:{ NoSuchFieldError -> 0x0035 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0035 }
            r2 = 4;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0035 }
        L_0x0035:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0040 }
            r1 = com.facebook.ads.internal.view.g.d.d.STARTED;	 Catch:{ NoSuchFieldError -> 0x0040 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0040 }
            r2 = 5;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0040 }
        L_0x0040:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.g.c.l$AnonymousClass5.<clinit>():void");
        }
    }

    public l(Context context) {
        this(context, false);
    }

    public l(Context context, boolean z) {
        super(context);
        this.a = new i() {
            public void a(h hVar) {
                l.this.d.setChecked(true);
            }
        };
        this.b = new k() {
            public void a(j jVar) {
                l.this.d.setChecked(false);
            }
        };
        this.c = new com.facebook.ads.internal.view.g.b.c() {
            public void a(b bVar) {
                l.this.d.setChecked(true);
            }
        };
        this.d = new m(context, z);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        LayoutParams layoutParams = new LayoutParams((int) (((double) displayMetrics.density) * 23.76d), (int) (23.76d * ((double) displayMetrics.density)));
        layoutParams.addRule(13);
        this.d.setLayoutParams(layoutParams);
        this.d.setChecked(true);
        this.e = new Paint();
        this.e.setStyle(Style.FILL);
        if (z) {
            this.e.setColor(-1728053248);
        } else {
            this.e.setColor(-1);
            this.e.setAlpha(HttpStatusCodes.STATUS_CODE_NO_CONTENT);
        }
        y.a((View) this, 0);
        addView(this.d);
        setGravity(17);
        LayoutParams layoutParams2 = new LayoutParams((int) (((double) displayMetrics.density) * 72.0d), (int) (72.0d * ((double) displayMetrics.density)));
        layoutParams2.addRule(13);
        setLayoutParams(layoutParams2);
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        super.a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().a(this.a, this.b, this.c);
        }
        AnonymousClass4 anonymousClass4 = new OnClickListener() {
            public void onClick(View view) {
                if (l.this.getVideoView() != null) {
                    switch (AnonymousClass5.a[l.this.getVideoView().getState().ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                            l.this.getVideoView().a(a.USER_STARTED);
                            return;
                        case 5:
                            l.this.getVideoView().a(true);
                            return;
                        default:
                            return;
                    }
                }
            }
        };
        this.d.setClickable(false);
        setOnClickListener(anonymousClass4);
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        setOnClickListener(null);
        if (getVideoView() != null) {
            getVideoView().getEventBus().b(this.c, this.b, this.a);
        }
        super.b();
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        int min = Math.min((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingTop()) - getPaddingBottom()) / 2;
        canvas.drawCircle((float) (getPaddingLeft() + min), (float) (getPaddingTop() + min), (float) min, this.e);
        super.onDraw(canvas);
    }
}
