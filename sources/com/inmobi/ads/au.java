package com.inmobi.ads;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.inmobi.rendering.RenderView;
import java.lang.ref.WeakReference;
import java.util.List;

final class au implements a {
    private static final String e = "au";
    private static Handler n = new Handler(Looper.getMainLooper());
    @Nullable
    bg a;
    int b = 0;
    final n c;
    NativeViewFactory d;
    @NonNull
    private final WeakReference<Context> f;
    @NonNull
    private final ao g;
    @NonNull
    private final ah h;
    @NonNull
    private final c i;
    @NonNull
    private c j;
    @NonNull
    private a k;
    @Nullable
    private b l;
    private ax m;
    private boolean o = false;
    private RenderView p;

    interface a {
        void a(View view, ak akVar);
    }

    interface b {
        void a(bb bbVar);
    }

    interface c {
        void a(int i, ak akVar);
    }

    au(@NonNull Context context, @NonNull c cVar, @NonNull ah ahVar, @NonNull ao aoVar, @NonNull c cVar2, @NonNull a aVar, @NonNull b bVar) {
        this.f = new WeakReference(context);
        this.h = ahVar;
        this.g = aoVar;
        this.j = cVar2;
        this.k = aVar;
        this.l = bVar;
        this.c = new n();
        this.i = cVar;
        this.d = NativeViewFactory.a(context);
    }

    public final Context a() {
        return (Context) this.f.get();
    }

    public final aw a(@Nullable aw awVar, @NonNull ViewGroup viewGroup, RenderView renderView) {
        this.p = renderView;
        awVar = a(awVar, viewGroup);
        if (!this.o) {
            b(awVar, this.g.d);
        }
        return awVar;
    }

    /* Access modifiers changed, original: final */
    public final aw b(@Nullable aw awVar, @NonNull final ViewGroup viewGroup, RenderView renderView) {
        this.p = renderView;
        awVar = a(awVar, viewGroup);
        n.post(new Runnable() {
            public final void run() {
                if (!au.this.o) {
                    au.this.b(awVar, au.this.g.d);
                }
            }
        });
        return awVar;
    }

    private aw a(@Nullable aw awVar, @NonNull ViewGroup viewGroup) {
        View view = awVar == null ? (aw) this.d.a(a(), this.g.d, this.i) : awVar;
        if (!(view == null || awVar == null)) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view);
            }
            NativeViewFactory nativeViewFactory = this.d;
            for (int childCount = view.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = view.getChildAt(childCount);
                view.removeViewAt(childCount);
                nativeViewFactory.a(childAt);
            }
            NativeViewFactory.a(view, this.g.d.c);
        }
        NativeViewFactory.b(this.g.d.c.a.x);
        view.setLayoutParams(NativeViewFactory.a(this.g.d, viewGroup));
        return view;
    }

    public final ViewGroup a(@NonNull ViewGroup viewGroup, @NonNull am amVar) {
        ViewGroup viewGroup2 = (ViewGroup) this.d.a(a(), (ak) amVar, this.i);
        if (viewGroup2 != null) {
            viewGroup2.setLayoutParams(NativeViewFactory.a((ak) amVar, viewGroup));
        }
        return viewGroup2;
    }

    public final int a(int i) {
        this.b = i;
        this.j.a(i, this.g.a(i));
        return d();
    }

    private void a(View view, final ak akVar) {
        final List a = this.c.a(view, akVar);
        if (a == null) {
            Object obj;
            TrackerEventType trackerEventType = TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW;
            for (NativeTracker nativeTracker : akVar.u) {
                if (trackerEventType == nativeTracker.b) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj == null) {
                return;
            }
        }
        view.addOnAttachStateChangeListener(new OnAttachStateChangeListener() {
            public final void onViewAttachedToWindow(View view) {
                au.this.c.a(a);
                au.this.h;
                ak a = ah.a(au.this.h.h(), akVar);
                ak akVar = akVar;
                TrackerEventType trackerEventType = TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW;
                ah e = au.this.h;
                if (a == null) {
                    a = akVar;
                }
                akVar.a(trackerEventType, e.a(a));
            }

            public final void onViewDetachedFromWindow(View view) {
                view.removeOnAttachStateChangeListener(this);
                n d = au.this.c;
                List<a> list = a;
                if (list != null) {
                    for (a aVar : list) {
                        aVar.a.cancel();
                    }
                    d.a.removeAll(list);
                }
            }
        });
    }

    /* Access modifiers changed, original: final */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0342  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0331  */
    public final android.view.ViewGroup b(@android.support.annotation.NonNull android.view.ViewGroup r21, com.inmobi.ads.am r22) {
        /*
        r20 = this;
        r7 = r20;
        r8 = r21;
        r1 = r22;
        r7.a(r1, r8);
        r9 = r22.iterator();
    L_0x000d:
        r1 = r9.hasNext();
        if (r1 == 0) goto L_0x034a;
    L_0x0013:
        r1 = r9.next();
        r10 = r1;
        r10 = (com.inmobi.ads.ak) r10;
        r1 = "CONTAINER";
        r2 = r10.b;
        if (r1 != r2) goto L_0x008f;
    L_0x0020:
        r1 = r10.d;
        r2 = "card_scrollable";
        r1 = r1.equalsIgnoreCase(r2);
        if (r1 == 0) goto L_0x0069;
    L_0x002a:
        r1 = r7.d;
        r2 = r20.a();
        r3 = r7.i;
        r1 = r1.a(r2, r10, r3);
        r11 = r1;
        r11 = (com.inmobi.ads.NativeScrollableContainer) r11;
        if (r11 == 0) goto L_0x000d;
    L_0x003b:
        r1 = r11.getType();
        r2 = r7.g;
        r1 = com.inmobi.ads.ay.a(r1, r2, r7);
        r7.m = r1;
        r1 = r7.m;
        if (r1 == 0) goto L_0x000d;
    L_0x004b:
        r2 = r10;
        r2 = (com.inmobi.ads.am) r2;
        r3 = r7.m;
        r4 = r7.b;
        r5 = r20.d();
        r1 = r11;
        r6 = r7;
        r1.a(r2, r3, r4, r5, r6);
        r1 = com.inmobi.ads.NativeViewFactory.a(r10, r8);
        r11.setLayoutParams(r1);
        r7.a(r11, r10);
        r8.addView(r11);
        goto L_0x000d;
    L_0x0069:
        r1 = r7.d;
        r2 = r20.a();
        r3 = r7.i;
        r1 = r1.a(r2, r10, r3);
        r1 = (android.view.ViewGroup) r1;
        if (r1 == 0) goto L_0x000d;
    L_0x0079:
        r2 = r10;
        r2 = (com.inmobi.ads.am) r2;
        r1 = r7.b(r1, r2);
        r2 = com.inmobi.ads.NativeViewFactory.a(r10, r8);
        r1.setLayoutParams(r2);
        r7.a(r1, r10);
        r8.addView(r1);
        goto L_0x000d;
    L_0x008f:
        r1 = "WEBVIEW";
        r2 = r10.b;
        r1 = r1.equals(r2);
        r2 = 0;
        if (r1 == 0) goto L_0x00c5;
    L_0x009a:
        r1 = r10;
        r1 = (com.inmobi.ads.bf) r1;
        r3 = r1.A;
        if (r3 == 0) goto L_0x00b9;
    L_0x00a1:
        r3 = r7.p;
        if (r3 == 0) goto L_0x00b9;
    L_0x00a5:
        r1 = r7.p;
        r3 = r1.getParent();
        if (r3 == 0) goto L_0x00b6;
    L_0x00ad:
        r3 = r1.getParent();
        r3 = (android.view.ViewGroup) r3;
        r3.removeView(r1);
    L_0x00b6:
        r7.p = r2;
        goto L_0x00d4;
    L_0x00b9:
        r3 = "UNKNOWN";
        r1 = r1.z;
        r1 = r3.equals(r1);
        if (r1 == 0) goto L_0x00d3;
    L_0x00c3:
        goto L_0x000d;
    L_0x00c5:
        r1 = "IMAGE";
        r3 = r10.b;
        r1 = r1.equals(r3);
        if (r1 == 0) goto L_0x00d3;
    L_0x00cf:
        r1 = r10.e;
        if (r1 == 0) goto L_0x000d;
    L_0x00d3:
        r1 = r2;
    L_0x00d4:
        if (r1 != 0) goto L_0x00e2;
    L_0x00d6:
        r1 = r7.d;
        r2 = r20.a();
        r3 = r7.i;
        r1 = r1.a(r2, r10, r3);
    L_0x00e2:
        if (r1 == 0) goto L_0x0342;
    L_0x00e4:
        r2 = new java.lang.ref.WeakReference;
        r2.<init>(r1);
        r3 = r10.o;
        r4 = -1;
        if (r3 == r4) goto L_0x0102;
    L_0x00ee:
        r3 = 4;
        r1.setVisibility(r3);
        r3 = n;
        r5 = new com.inmobi.ads.au$4;
        r5.<init>(r2);
        r2 = r10.o;
        r2 = r2 * 1000;
        r11 = (long) r2;
        r3.postDelayed(r5, r11);
        goto L_0x0115;
    L_0x0102:
        r3 = r10.p;
        if (r3 == r4) goto L_0x0115;
    L_0x0106:
        r3 = n;
        r5 = new com.inmobi.ads.au$5;
        r5.<init>(r2);
        r2 = r10.p;
        r2 = r2 * 1000;
        r11 = (long) r2;
        r3.postDelayed(r5, r11);
    L_0x0115:
        r2 = com.inmobi.ads.NativeViewFactory.a(r10, r8);
        r1.setLayoutParams(r2);
        r7.a(r1, r10);
        r8.addView(r1);
        r2 = android.os.Build.VERSION.SDK_INT;
        r3 = 15;
        if (r2 < r3) goto L_0x020f;
    L_0x0128:
        r2 = "VIDEO";
        r5 = r10.b;
        if (r2 != r5) goto L_0x020f;
    L_0x012e:
        r2 = r10;
        r2 = (com.inmobi.ads.be) r2;
        r5 = r1;
        r5 = (com.inmobi.ads.NativeVideoWrapper) r5;
        r5 = r5.getVideoView();
        r6 = android.os.Build.VERSION.SDK_INT;
        if (r6 < r3) goto L_0x020f;
    L_0x013c:
        r6 = r2.t;
        r6 = (com.inmobi.ads.am) r6;
        r11 = java.lang.System.currentTimeMillis();
        if (r6 == 0) goto L_0x0150;
    L_0x0146:
        r13 = 0;
        r3 = r6.z;
        r16 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1));
        if (r16 == 0) goto L_0x0150;
    L_0x014e:
        r11 = r6.z;
    L_0x0150:
        if (r6 == 0) goto L_0x0154;
    L_0x0152:
        r6.z = r11;
    L_0x0154:
        r3 = 0;
        r5.setClickable(r3);
        r4 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r5.setId(r4);
        r5.e = r3;
        r5.f = r3;
        r3 = r2.e;
        r3 = (com.inmobi.ads.by) r3;
        r3 = r3.b();
        r3 = android.net.Uri.parse(r3);
        r5.a = r3;
        r3 = r2.v;
        r4 = "placementType";
        r3 = r3.get(r4);
        r3 = (com.inmobi.ads.AdContainer.RenderingProperties.PlacementType) r3;
        r4 = com.inmobi.ads.AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN;
        if (r4 != r3) goto L_0x0184;
    L_0x017e:
        r3 = new com.inmobi.ads.av;
        r3.<init>();
        goto L_0x0188;
    L_0x0184:
        r3 = com.inmobi.ads.av.a();
    L_0x0188:
        r5.c = r3;
        r3 = r5.d;
        if (r3 == 0) goto L_0x0196;
    L_0x018e:
        r3 = r5.c;
        r4 = r5.d;
        r3.setAudioSessionId(r4);
        goto L_0x019e;
    L_0x0196:
        r3 = r5.c;
        r3 = r3.getAudioSessionId();
        r5.d = r3;
    L_0x019e:
        r3 = r5.c;	 Catch:{ IOException -> 0x01c9 }
        r4 = r5.getContext();	 Catch:{ IOException -> 0x01c9 }
        r4 = r4.getApplicationContext();	 Catch:{ IOException -> 0x01c9 }
        r6 = r5.a;	 Catch:{ IOException -> 0x01c9 }
        r11 = r5.b;	 Catch:{ IOException -> 0x01c9 }
        r3.setDataSource(r4, r6, r11);	 Catch:{ IOException -> 0x01c9 }
        r5.setTag(r2);
        r3 = new com.inmobi.ads.NativeVideoView$d;
        r3.<init>(r5);
        r5.g = r3;
        r3 = r5.l;
        r5.setSurfaceTextureListener(r3);
        r3 = 1;
        r5.setFocusable(r3);
        r5.setFocusableInTouchMode(r3);
        r5.requestFocus();
        goto L_0x01d2;
    L_0x01c9:
        r3 = r5.c;
        r4 = -1;
        r3.a = r4;
        r3 = r5.c;
        r3.b = r4;
    L_0x01d2:
        r3 = r2.y;
        if (r3 == 0) goto L_0x01dd;
    L_0x01d6:
        r3 = r2.y;
        r3 = (com.inmobi.ads.be) r3;
        r2.a(r3);
    L_0x01dd:
        r3 = new com.inmobi.ads.au$7;
        r3.<init>(r2);
        r5.setQuartileCompletedListener(r3);
        r3 = new com.inmobi.ads.au$8;
        r3.<init>(r2);
        r5.setPlaybackEventListener(r3);
        r3 = new com.inmobi.ads.au$9;
        r3.<init>(r2);
        r5.setMediaErrorListener(r3);
        r2 = r7.a;
        if (r2 == 0) goto L_0x020f;
    L_0x01f9:
        r2 = r7.a;	 Catch:{ Exception -> 0x01ff }
        r2.a(r5);	 Catch:{ Exception -> 0x01ff }
        goto L_0x020f;
    L_0x01ff:
        r0 = move-exception;
        r2 = r0;
        r3 = new java.lang.StringBuilder;
        r4 = "SDK encountered unexpected error in handling the onVideoViewCreated event; ";
        r3.<init>(r4);
        r2 = r2.getMessage();
        r3.append(r2);
    L_0x020f:
        r7.a(r10, r1);
        r2 = "TIMER";
        r3 = r10.b;
        if (r2 != r3) goto L_0x022b;
    L_0x0218:
        r2 = "timerView";
        r1.setTag(r2);
        r2 = r10;
        r2 = (com.inmobi.ads.bb) r2;
        r3 = r1;
        r3 = (com.inmobi.ads.NativeTimerView) r3;
        r4 = new com.inmobi.ads.au$2;
        r4.<init>(r2);
        r3.setTimerEventsListener(r4);
    L_0x022b:
        r2 = android.os.Build.VERSION.SDK_INT;
        r3 = 15;
        if (r2 < r3) goto L_0x02ec;
    L_0x0231:
        r2 = "VIDEO";
        r3 = r10.b;
        if (r2 != r3) goto L_0x02ec;
    L_0x0237:
        r2 = r1;
        r2 = (com.inmobi.ads.NativeVideoWrapper) r2;
        r3 = r7.a;
        r2.setVideoEventListener(r3);
        r3 = r2.a;
        r3 = r3.getTag();
        r3 = (com.inmobi.ads.be) r3;
        if (r3 == 0) goto L_0x02ec;
    L_0x0249:
        r4 = r3.b();	 Catch:{ Exception -> 0x02ca }
        r4 = r4.b();	 Catch:{ Exception -> 0x02ca }
        r5 = new android.media.MediaMetadataRetriever;	 Catch:{ Exception -> 0x02ca }
        r5.<init>();	 Catch:{ Exception -> 0x02ca }
        r5.setDataSource(r4);	 Catch:{ Exception -> 0x02ca }
        r4 = 18;
        r4 = r5.extractMetadata(r4);	 Catch:{ Exception -> 0x02ca }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Exception -> 0x02ca }
        r4 = r4.intValue();	 Catch:{ Exception -> 0x02ca }
        r6 = 19;
        r6 = r5.extractMetadata(r6);	 Catch:{ Exception -> 0x02ca }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ Exception -> 0x02ca }
        r6 = r6.intValue();	 Catch:{ Exception -> 0x02ca }
        r5.release();	 Catch:{ Exception -> 0x02ca }
        r3 = r3.c;	 Catch:{ Exception -> 0x02ca }
        r3 = r3.a;	 Catch:{ Exception -> 0x02ca }
        r5 = r3.x;	 Catch:{ Exception -> 0x02ca }
        r5 = com.inmobi.ads.NativeViewFactory.c(r5);	 Catch:{ Exception -> 0x02ca }
        r11 = (double) r5;	 Catch:{ Exception -> 0x02ca }
        r5 = r3.y;	 Catch:{ Exception -> 0x02ca }
        r5 = com.inmobi.ads.NativeViewFactory.c(r5);	 Catch:{ Exception -> 0x02ca }
        r13 = (double) r5;	 Catch:{ Exception -> 0x02ca }
        r11 = r11 / r13;
        r4 = (double) r4;	 Catch:{ Exception -> 0x02ca }
        r13 = (double) r6;	 Catch:{ Exception -> 0x02ca }
        r15 = r4 / r13;
        r6 = (r11 > r15 ? 1 : (r11 == r15 ? 0 : -1));
        r11 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        if (r6 <= 0) goto L_0x02a9;
    L_0x0295:
        r6 = r3.y;	 Catch:{ Exception -> 0x02ca }
        r6 = com.inmobi.ads.NativeViewFactory.c(r6);	 Catch:{ Exception -> 0x02ca }
        r17 = r9;
        r8 = (double) r6;
        r8 = r8 * r11;
        r8 = r8 / r13;
        r4 = r4 * r8;
        r3 = r3.y;	 Catch:{ Exception -> 0x02c8 }
        r3 = com.inmobi.ads.NativeViewFactory.c(r3);	 Catch:{ Exception -> 0x02c8 }
        r8 = (double) r3;	 Catch:{ Exception -> 0x02c8 }
        goto L_0x02c0;
    L_0x02a9:
        r17 = r9;
        r6 = r3.x;	 Catch:{ Exception -> 0x02c8 }
        r6 = com.inmobi.ads.NativeViewFactory.c(r6);	 Catch:{ Exception -> 0x02c8 }
        r8 = (double) r6;	 Catch:{ Exception -> 0x02c8 }
        r3 = r3.x;	 Catch:{ Exception -> 0x02c8 }
        r3 = com.inmobi.ads.NativeViewFactory.c(r3);	 Catch:{ Exception -> 0x02c8 }
        r18 = r8;
        r8 = (double) r3;	 Catch:{ Exception -> 0x02c8 }
        r8 = r8 * r11;
        r8 = r8 / r4;
        r8 = r8 * r13;
        r4 = r18;
    L_0x02c0:
        r3 = new android.widget.RelativeLayout$LayoutParams;	 Catch:{ Exception -> 0x02c8 }
        r4 = (int) r4;	 Catch:{ Exception -> 0x02c8 }
        r5 = (int) r8;	 Catch:{ Exception -> 0x02c8 }
        r3.<init>(r4, r5);	 Catch:{ Exception -> 0x02c8 }
        goto L_0x02e1;
    L_0x02c8:
        r0 = move-exception;
        goto L_0x02cd;
    L_0x02ca:
        r0 = move-exception;
        r17 = r9;
    L_0x02cd:
        r3 = r0;
        r4 = new android.widget.RelativeLayout$LayoutParams;
        r5 = -1;
        r4.<init>(r5, r5);
        r5 = com.inmobi.commons.core.a.a.a();
        r6 = new com.inmobi.commons.core.e.a;
        r6.<init>(r3);
        r5.a(r6);
        r3 = r4;
    L_0x02e1:
        r4 = 13;
        r3.addRule(r4);
        r2 = r2.a;
        r2.setLayoutParams(r3);
        goto L_0x02ee;
    L_0x02ec:
        r17 = r9;
    L_0x02ee:
        r2 = "WEBVIEW";
        r3 = r10.b;
        if (r2 != r3) goto L_0x0344;
    L_0x02f4:
        r2 = r1 instanceof com.inmobi.rendering.RenderView;
        if (r2 == 0) goto L_0x0344;
    L_0x02f8:
        r1 = (com.inmobi.rendering.RenderView) r1;
        r10 = (com.inmobi.ads.bf) r10;
        r2 = r10.B;
        r1.setScrollable(r2);
        r2 = r7.h;
        r2 = r2.n;
        r1.setReferenceContainer(r2);
        r2 = r7.h;
        r2 = r2.u();
        r1.setRenderViewEventListener(r2);
        r2 = r7.h;
        r2 = r2.e;
        r1.setPlacementId(r2);
        r2 = r7.h;
        r2 = r2.g;
        r1.setAllowAutoRedirection(r2);
        r2 = r7.h;
        r2 = r2.f;
        r1.setCreativeId(r2);
        r2 = r7.h;
        r2 = r2.d;
        r1.setImpressionId(r2);
        r2 = r10.A;
        if (r2 != 0) goto L_0x0344;
    L_0x0331:
        r2 = r7.h;
        r3 = r2.y;
        if (r3 != 0) goto L_0x0344;
    L_0x0337:
        r3 = r2.x;
        if (r3 != 0) goto L_0x0344;
    L_0x033b:
        r3 = r2.w;
        if (r3 != 0) goto L_0x0344;
    L_0x033f:
        r2.x = r1;
        goto L_0x0344;
    L_0x0342:
        r17 = r9;
    L_0x0344:
        r9 = r17;
        r8 = r21;
        goto L_0x000d;
    L_0x034a:
        r1 = r8;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.au.b(android.view.ViewGroup, com.inmobi.ads.am):android.view.ViewGroup");
    }

    private int d() {
        if (this.b == 0) {
            return 8388611;
        }
        return this.g.b() - 1 == this.b ? GravityCompat.END : 1;
    }

    /* Access modifiers changed, original: final */
    public final void b() {
        this.o = true;
        this.f.clear();
        if (this.m != null) {
            this.m.destroy();
        }
    }

    private void a(final ak akVar, View view) {
        if (akVar.h) {
            view.setOnClickListener(new OnClickListener() {
                public final void onClick(View view) {
                    au.this.k.a(view, akVar);
                }
            });
        }
    }
}
