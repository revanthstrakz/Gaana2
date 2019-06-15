package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.api.client.http.HttpStatusCodes;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.rendering.CustomView;
import com.inmobi.rendering.InMobiAdActivity;
import com.inmobi.rendering.RenderView;
import com.squareup.picasso.Callback;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

class NativeViewFactory {
    private static final String a = "NativeViewFactory";
    @NonNull
    private static final Map<Class, Integer> c;
    @Nullable
    private static volatile WeakReference<NativeViewFactory> e = null;
    private static WeakReference<Context> f = null;
    private static int g = 1;
    private static int h = 1;
    private int b;
    @NonNull
    private Map<Integer, c> d = new HashMap();

    static class PicassoCallback implements Callback {
        private WeakReference<Context> a;
        private WeakReference<ImageView> b;
        private ak c;

        public void onSuccess() {
        }

        PicassoCallback(Context context, ImageView imageView, ak akVar) {
            this.a = new WeakReference(context);
            this.b = new WeakReference(imageView);
            this.c = akVar;
        }

        public void onError() {
            NativeViewFactory.a((Context) this.a.get(), (ImageView) this.b.get(), this.c);
        }
    }

    private static final class a implements Runnable {
        private WeakReference<Context> a;
        private WeakReference<ImageView> b;

        a(Context context, ImageView imageView) {
            this.a = new WeakReference(context);
            this.b = new WeakReference(imageView);
        }

        public final void run() {
            Context context = (Context) this.a.get();
            ImageView imageView = (ImageView) this.b.get();
            if (context != null && imageView != null) {
                NativeViewFactory.b(context, imageView);
            }
        }
    }

    @SuppressLint({"AppCompatCustomView"})
    private static final class b extends TextView {
        public final boolean onTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        public b(Context context) {
            super(context);
        }

        /* Access modifiers changed, original: protected|final */
        public final void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            i = getLineHeight() > 0 ? i2 / getLineHeight() : 0;
            if (i > 0) {
                setSingleLine(false);
                setLines(i);
            }
            if (i == 1) {
                setSingleLine();
            }
        }
    }

    private abstract class c {
        private int a = 0;
        @NonNull
        LinkedList<View> b = new LinkedList();
        private int d = 0;

        public abstract View a(@NonNull Context context);

        public boolean a(@NonNull View view) {
            NativeViewFactory.b(view);
            view.setOnClickListener(null);
            this.b.addLast(view);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
            NativeViewFactory.this.b = NativeViewFactory.this.b + 1;
            return true;
        }

        public final View a(@NonNull Context context, ak akVar, c cVar) {
            View a;
            NativeViewFactory.f = new WeakReference(context);
            if (this.b.isEmpty()) {
                this.a++;
                a = a(context);
            } else {
                this.d++;
                a = (View) this.b.removeFirst();
                NativeViewFactory.this.b = NativeViewFactory.this.b - 1;
            }
            a(a, akVar, cVar);
            return a;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("Size:");
            stringBuilder.append(this.b.size());
            stringBuilder.append(" Miss Count:");
            stringBuilder.append(this.a);
            stringBuilder.append(" Hit Count:");
            stringBuilder.append(this.d);
            return stringBuilder.toString();
        }

        /* Access modifiers changed, original: protected */
        public void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
            view.setVisibility(akVar.x);
            view.setOnClickListener(null);
        }
    }

    static {
        HashMap hashMap = new HashMap();
        c = hashMap;
        hashMap.put(aw.class, Integer.valueOf(0));
        c.put(bp.class, Integer.valueOf(1));
        c.put(bo.class, Integer.valueOf(2));
        c.put(NativeContainerLayout.class, Integer.valueOf(3));
        c.put(ImageView.class, Integer.valueOf(6));
        c.put(NativeVideoWrapper.class, Integer.valueOf(7));
        c.put(b.class, Integer.valueOf(4));
        c.put(Button.class, Integer.valueOf(5));
        c.put(NativeTimerView.class, Integer.valueOf(8));
        c.put(RenderView.class, Integer.valueOf(9));
        c.put(GifView.class, Integer.valueOf(10));
    }

    static void a(int i) {
        g = i;
    }

    static void b(int i) {
        h = i;
    }

    static int c(int i) {
        Context context = (Context) f.get();
        if ((context == null || !(context instanceof InMobiAdActivity)) && g != 0) {
            return (int) (((double) i) * ((((double) g) * 1.0d) / ((double) h)));
        }
        return i;
    }

    @SuppressLint({"UseSparseArrays"})
    private NativeViewFactory(Context context) {
        f = new WeakReference(context);
        this.d.put(Integer.valueOf(0), new c() {
            /* Access modifiers changed, original: protected|final */
            public final View a(@NonNull Context context) {
                return new aw(context.getApplicationContext());
            }

            /* Access modifiers changed, original: protected|final */
            public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a(view, akVar.c);
            }
        });
        this.d.put(Integer.valueOf(3), new c() {
            /* Access modifiers changed, original: protected|final */
            public final View a(@NonNull Context context) {
                return new NativeContainerLayout(context.getApplicationContext());
            }

            /* Access modifiers changed, original: protected|final */
            public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a(view, akVar.c);
            }
        });
        this.d.put(Integer.valueOf(1), new c() {
            /* Access modifiers changed, original: protected|final */
            public final View a(@NonNull Context context) {
                return new bp(context.getApplicationContext());
            }

            /* Access modifiers changed, original: protected|final */
            public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a(view, akVar.c);
            }

            public final boolean a(@NonNull View view) {
                ((bp) view).a = null;
                return super.a(view);
            }
        });
        this.d.put(Integer.valueOf(2), new c() {
            /* Access modifiers changed, original: protected|final */
            public final View a(@NonNull Context context) {
                return new bo(context.getApplicationContext());
            }

            /* Access modifiers changed, original: protected|final */
            public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a(view, akVar.c);
            }
        });
        this.d.put(Integer.valueOf(6), new c() {
            /* Access modifiers changed, original: protected|final */
            public final View a(@NonNull Context context) {
                return new ImageView(context.getApplicationContext());
            }

            /* Access modifiers changed, original: protected|final */
            public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a((ImageView) view, akVar);
            }

            public final boolean a(@NonNull View view) {
                if (!(view instanceof ImageView)) {
                    return false;
                }
                ((ImageView) view).setImageDrawable(null);
                return super.a(view);
            }
        });
        this.d.put(Integer.valueOf(10), new c() {
            /* Access modifiers changed, original: protected|final */
            public final View a(@NonNull Context context) {
                return new GifView(context.getApplicationContext());
            }

            /* Access modifiers changed, original: protected|final */
            public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a((GifView) view, akVar);
            }

            public final boolean a(@NonNull View view) {
                if (!(view instanceof GifView)) {
                    return false;
                }
                ((GifView) view).setGif(null);
                return super.a(view);
            }
        });
        this.d.put(Integer.valueOf(7), new c() {
            /* Access modifiers changed, original: protected|final */
            public final View a(@NonNull Context context) {
                return new NativeVideoWrapper(context.getApplicationContext());
            }

            /* Access modifiers changed, original: protected|final */
            public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a((NativeVideoWrapper) view, akVar);
            }

            @TargetApi(15)
            public final boolean a(@NonNull View view) {
                if (!(view instanceof NativeVideoWrapper)) {
                    return false;
                }
                NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) view;
                nativeVideoWrapper.getProgressBar().setVisibility(8);
                nativeVideoWrapper.getPoster().setImageBitmap(null);
                nativeVideoWrapper.getVideoView().a();
                return super.a(view);
            }
        });
        this.d.put(Integer.valueOf(4), new c() {
            /* Access modifiers changed, original: protected|final */
            public final View a(@NonNull Context context) {
                return new b(context.getApplicationContext());
            }

            /* Access modifiers changed, original: protected|final */
            public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a((TextView) view, akVar);
            }

            public final boolean a(@NonNull View view) {
                if (!(view instanceof TextView)) {
                    return false;
                }
                NativeViewFactory.a((TextView) view);
                return super.a(view);
            }
        });
        this.d.put(Integer.valueOf(5), new c() {
            /* Access modifiers changed, original: protected|final */
            public final View a(@NonNull Context context) {
                return new Button(context.getApplicationContext());
            }

            /* Access modifiers changed, original: protected|final */
            public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.b((Button) view, akVar);
            }

            public final boolean a(@NonNull View view) {
                if (!(view instanceof Button)) {
                    return false;
                }
                NativeViewFactory.a((Button) view);
                return super.a(view);
            }
        });
        this.d.put(Integer.valueOf(8), new c() {
            /* Access modifiers changed, original: protected|final */
            public final View a(@NonNull Context context) {
                return new NativeTimerView(context.getApplicationContext());
            }

            /* Access modifiers changed, original: protected|final */
            public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a(NativeViewFactory.this, (NativeTimerView) view, akVar);
            }

            public final boolean a(@NonNull View view) {
                return (view instanceof NativeTimerView) && super.a(view);
            }
        });
        this.d.put(Integer.valueOf(9), new c() {
            /* Access modifiers changed, original: protected|final */
            public final View a(@NonNull Context context) {
                return new RenderView(context.getApplicationContext(), new RenderingProperties(PlacementType.PLACEMENT_TYPE_INLINE), null, null);
            }

            /* Access modifiers changed, original: protected|final */
            public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a((RenderView) view, akVar, cVar);
            }

            public final boolean a(@NonNull View view) {
                return (view instanceof RenderView) && !((RenderView) view).v && super.a(view);
            }
        });
    }

    public static NativeViewFactory a(Context context) {
        NativeViewFactory nativeViewFactory;
        NativeViewFactory nativeViewFactory2 = null;
        if (e == null) {
            nativeViewFactory = null;
        } else {
            nativeViewFactory = (NativeViewFactory) e.get();
        }
        if (nativeViewFactory == null) {
            synchronized (NativeViewFactory.class) {
                if (e != null) {
                    nativeViewFactory2 = (NativeViewFactory) e.get();
                }
                if (nativeViewFactory2 == null) {
                    nativeViewFactory = new NativeViewFactory(context);
                    e = new WeakReference(nativeViewFactory);
                } else {
                    nativeViewFactory = nativeViewFactory2;
                }
            }
        }
        return nativeViewFactory;
    }

    public final void a(@NonNull View view) {
        if ((view instanceof aw) || (view instanceof NativeContainerLayout)) {
            NativeContainerLayout nativeContainerLayout = (NativeContainerLayout) view;
            if (nativeContainerLayout.getChildCount() != 0) {
                Stack stack = new Stack();
                stack.push(nativeContainerLayout);
                while (!stack.isEmpty()) {
                    View view2 = (NativeContainerLayout) stack.pop();
                    for (int childCount = view2.getChildCount() - 1; childCount >= 0; childCount--) {
                        View childAt = view2.getChildAt(childCount);
                        view2.removeViewAt(childCount);
                        if (childAt instanceof NativeContainerLayout) {
                            stack.push((NativeContainerLayout) childAt);
                        } else {
                            c(childAt);
                        }
                    }
                    c(view2);
                }
                return;
            }
        }
        c(view);
    }

    private void c(@NonNull View view) {
        int intValue = ((Integer) c.get(view.getClass())).intValue();
        if (-1 == intValue) {
            new StringBuilder("View type unknown, ignoring recycle:").append(view);
            return;
        }
        c cVar = (c) this.d.get(Integer.valueOf(intValue));
        if (cVar == null) {
            StringBuilder stringBuilder = new StringBuilder("Unsupported AssetType:");
            stringBuilder.append(intValue);
            stringBuilder.append(" failed to recycle view");
            return;
        }
        if (this.b >= HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES) {
            c b = b();
            if (b != null && b.b.size() > 0) {
                b.b.removeFirst();
            }
        }
        cVar.a(view);
    }

    @Nullable
    private c b() {
        int i = 0;
        c cVar = null;
        for (Entry entry : this.d.entrySet()) {
            if (((c) entry.getValue()).b.size() > i) {
                c cVar2 = (c) entry.getValue();
                cVar = cVar2;
                i = cVar2.b.size();
            }
        }
        return cVar;
    }

    private static void b(Context context, ImageView imageView) {
        if (imageView.getDrawable() == null) {
            Bitmap drawingCache;
            float f = com.inmobi.commons.core.utilities.b.c.a().c;
            CustomView customView = new CustomView(context, f, 0);
            if (VERSION.SDK_INT < 28) {
                customView.layout(0, 0, (int) (((float) c(40)) * f), (int) (((float) c(40)) * f));
                customView.setDrawingCacheEnabled(true);
                customView.buildDrawingCache();
                drawingCache = customView.getDrawingCache();
            } else {
                customView.layout(0, 0, (int) (((float) c(40)) * f), (int) (((float) c(40)) * f));
                drawingCache = Bitmap.createBitmap((int) (((float) c(40)) * f), (int) (((float) c(40)) * f), Config.ARGB_8888);
                customView.draw(new Canvas(drawingCache));
            }
            imageView.setImageBitmap(drawingCache);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x005f A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x005f A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x005f A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x005f A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0054  */
    private static void a(@android.support.annotation.NonNull android.widget.TextView r9, java.lang.String[] r10) {
        /*
        r0 = r9.getPaintFlags();
        r1 = 0;
        r2 = r10.length;
        r4 = r0;
        r0 = r1;
        r3 = r0;
    L_0x0009:
        if (r0 >= r2) goto L_0x0062;
    L_0x000b:
        r5 = r10[r0];
        r6 = -1;
        r7 = r5.hashCode();
        r8 = -1178781136; // 0xffffffffb9bd3a30 float:-3.6092242E-4 double:NaN;
        if (r7 == r8) goto L_0x0045;
    L_0x0017:
        r8 = -1026963764; // 0xffffffffc2c9c6cc float:-100.888275 double:NaN;
        if (r7 == r8) goto L_0x003b;
    L_0x001c:
        r8 = -891985998; // 0xffffffffcad55fb2 float:-6991833.0 double:NaN;
        if (r7 == r8) goto L_0x0031;
    L_0x0021:
        r8 = 3029637; // 0x2e3a85 float:4.245426E-39 double:1.4968396E-317;
        if (r7 == r8) goto L_0x0027;
    L_0x0026:
        goto L_0x004f;
    L_0x0027:
        r7 = "bold";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x004f;
    L_0x002f:
        r5 = r1;
        goto L_0x0050;
    L_0x0031:
        r7 = "strike";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x004f;
    L_0x0039:
        r5 = 2;
        goto L_0x0050;
    L_0x003b:
        r7 = "underline";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x004f;
    L_0x0043:
        r5 = 3;
        goto L_0x0050;
    L_0x0045:
        r7 = "italic";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x004f;
    L_0x004d:
        r5 = 1;
        goto L_0x0050;
    L_0x004f:
        r5 = r6;
    L_0x0050:
        switch(r5) {
            case 0: goto L_0x005d;
            case 1: goto L_0x005a;
            case 2: goto L_0x0057;
            case 3: goto L_0x0054;
            default: goto L_0x0053;
        };
    L_0x0053:
        goto L_0x005f;
    L_0x0054:
        r4 = r4 | 8;
        goto L_0x005f;
    L_0x0057:
        r4 = r4 | 16;
        goto L_0x005f;
    L_0x005a:
        r3 = r3 | 2;
        goto L_0x005f;
    L_0x005d:
        r3 = r3 | 1;
    L_0x005f:
        r0 = r0 + 1;
        goto L_0x0009;
    L_0x0062:
        r10 = android.graphics.Typeface.DEFAULT;
        r9.setTypeface(r10, r3);
        r9.setPaintFlags(r4);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.NativeViewFactory.a(android.widget.TextView, java.lang.String[]):void");
    }

    @TargetApi(21)
    static void a(View view, @NonNull al alVar) {
        int parseColor = Color.parseColor("#00000000");
        try {
            parseColor = Color.parseColor(alVar.e());
        } catch (IllegalArgumentException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        view.setBackgroundColor(parseColor);
        if ("line".equals(alVar.a())) {
            int parseColor2;
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(parseColor);
            if ("curved".equals(alVar.b())) {
                gradientDrawable.setCornerRadius(alVar.c());
            }
            parseColor = Color.parseColor("#ff000000");
            try {
                parseColor2 = Color.parseColor(alVar.d());
            } catch (IllegalArgumentException e2) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                parseColor2 = parseColor;
            }
            gradientDrawable.setStroke(1, parseColor2);
            if (VERSION.SDK_INT < 16) {
                view.setBackgroundDrawable(gradientDrawable);
                return;
            }
            view.setBackground(gradientDrawable);
        }
    }

    /* JADX WARNING: Missing block: B:18:0x004e, code skipped:
            if (r0.equals(com.facebook.share.internal.ShareConstants.VIDEO_URL) != false) goto L_0x008e;
     */
    @android.support.annotation.Nullable
    public final android.view.View a(@android.support.annotation.NonNull android.content.Context r12, @android.support.annotation.NonNull com.inmobi.ads.ak r13, @android.support.annotation.NonNull com.inmobi.ads.c r14) {
        /*
        r11 = this;
        r0 = r13 instanceof com.inmobi.ads.am;
        r1 = 4;
        r2 = 7;
        r3 = 5;
        r4 = 6;
        r5 = 3;
        r6 = 2;
        r7 = 0;
        r8 = -1;
        r9 = 1;
        if (r0 == 0) goto L_0x0034;
    L_0x000d:
        r0 = r13;
        r0 = (com.inmobi.ads.am) r0;
        r1 = "root";
        r2 = r0.d;
        r1 = r1.equalsIgnoreCase(r2);
        if (r1 == 0) goto L_0x001d;
    L_0x001a:
        r1 = r7;
        goto L_0x00a1;
    L_0x001d:
        r1 = "card_scrollable";
        r2 = r0.d;
        r1 = r1.equalsIgnoreCase(r2);
        if (r1 == 0) goto L_0x0031;
    L_0x0027:
        r0 = r0.A;
        if (r0 == r9) goto L_0x002e;
    L_0x002b:
        r1 = r9;
        goto L_0x00a1;
    L_0x002e:
        r1 = r6;
        goto L_0x00a1;
    L_0x0031:
        r1 = r5;
        goto L_0x00a1;
    L_0x0034:
        r0 = r13.b;
        r10 = r0.hashCode();
        switch(r10) {
            case 67056: goto L_0x0083;
            case 70564: goto L_0x0079;
            case 2241657: goto L_0x006f;
            case 2571565: goto L_0x0065;
            case 69775675: goto L_0x005b;
            case 79826725: goto L_0x0051;
            case 81665115: goto L_0x0048;
            case 1942407129: goto L_0x003e;
            default: goto L_0x003d;
        };
    L_0x003d:
        goto L_0x008d;
    L_0x003e:
        r5 = "WEBVIEW";
        r0 = r0.equals(r5);
        if (r0 == 0) goto L_0x008d;
    L_0x0046:
        r5 = r4;
        goto L_0x008e;
    L_0x0048:
        r6 = "VIDEO";
        r0 = r0.equals(r6);
        if (r0 == 0) goto L_0x008d;
    L_0x0050:
        goto L_0x008e;
    L_0x0051:
        r5 = "TIMER";
        r0 = r0.equals(r5);
        if (r0 == 0) goto L_0x008d;
    L_0x0059:
        r5 = r3;
        goto L_0x008e;
    L_0x005b:
        r5 = "IMAGE";
        r0 = r0.equals(r5);
        if (r0 == 0) goto L_0x008d;
    L_0x0063:
        r5 = r9;
        goto L_0x008e;
    L_0x0065:
        r5 = "TEXT";
        r0 = r0.equals(r5);
        if (r0 == 0) goto L_0x008d;
    L_0x006d:
        r5 = r7;
        goto L_0x008e;
    L_0x006f:
        r5 = "ICON";
        r0 = r0.equals(r5);
        if (r0 == 0) goto L_0x008d;
    L_0x0077:
        r5 = r6;
        goto L_0x008e;
    L_0x0079:
        r5 = "GIF";
        r0 = r0.equals(r5);
        if (r0 == 0) goto L_0x008d;
    L_0x0081:
        r5 = r2;
        goto L_0x008e;
    L_0x0083:
        r5 = "CTA";
        r0 = r0.equals(r5);
        if (r0 == 0) goto L_0x008d;
    L_0x008b:
        r5 = r1;
        goto L_0x008e;
    L_0x008d:
        r5 = r8;
    L_0x008e:
        switch(r5) {
            case 0: goto L_0x00a1;
            case 1: goto L_0x00a0;
            case 2: goto L_0x00a0;
            case 3: goto L_0x009e;
            case 4: goto L_0x009c;
            case 5: goto L_0x0099;
            case 6: goto L_0x0096;
            case 7: goto L_0x0093;
            default: goto L_0x0091;
        };
    L_0x0091:
        r1 = r8;
        goto L_0x00a1;
    L_0x0093:
        r1 = 10;
        goto L_0x00a1;
    L_0x0096:
        r1 = 9;
        goto L_0x00a1;
    L_0x0099:
        r1 = 8;
        goto L_0x00a1;
    L_0x009c:
        r1 = r3;
        goto L_0x00a1;
    L_0x009e:
        r1 = r2;
        goto L_0x00a1;
    L_0x00a0:
        r1 = r4;
    L_0x00a1:
        if (r8 != r1) goto L_0x00b6;
    L_0x00a3:
        r12 = new java.lang.StringBuilder;
        r14 = "Unsupported AssetType:";
        r12.<init>(r14);
        r13 = r13.b;
        r12.append(r13);
        r13 = " failed to instantiate view ";
        r12.append(r13);
        r12 = 0;
        return r12;
    L_0x00b6:
        r0 = r11.d;
        r1 = java.lang.Integer.valueOf(r1);
        r0 = r0.get(r1);
        r0 = (com.inmobi.ads.NativeViewFactory.c) r0;
        r12 = r0.a(r12, r13, r14);
        return r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.NativeViewFactory.a(android.content.Context, com.inmobi.ads.ak, com.inmobi.ads.c):android.view.View");
    }

    @TargetApi(17)
    private static Button b(@NonNull Button button, @NonNull ak akVar) {
        al alVar = (a) akVar.c;
        button.setLayoutParams(new LayoutParams(c(alVar.a.x), c(alVar.a.y)));
        button.setText((CharSequence) akVar.e);
        button.setTextSize(1, (float) c(alVar.h()));
        int parseColor = Color.parseColor("#ff000000");
        try {
            parseColor = Color.parseColor(alVar.i());
        } catch (IllegalArgumentException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        button.setTextColor(parseColor);
        parseColor = Color.parseColor("#00000000");
        try {
            parseColor = Color.parseColor(alVar.e());
        } catch (IllegalArgumentException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
        button.setBackgroundColor(parseColor);
        if (VERSION.SDK_INT >= 17) {
            button.setTextAlignment(4);
        }
        button.setGravity(17);
        a((TextView) button, alVar.j());
        a((View) button, alVar);
        return button;
    }

    public static LayoutParams a(@NonNull ak akVar, @NonNull ViewGroup viewGroup) {
        Point point = akVar.c.a;
        Point point2 = akVar.c.c;
        LayoutParams layoutParams = new LayoutParams(c(point.x), c(point.y));
        if (viewGroup instanceof NativeContainerLayout) {
            layoutParams = new com.inmobi.ads.NativeContainerLayout.a(c(point.x), c(point.y));
            com.inmobi.ads.NativeContainerLayout.a aVar = (com.inmobi.ads.NativeContainerLayout.a) layoutParams;
            int c = c(point2.x);
            int c2 = c(point2.y);
            aVar.a = c;
            aVar.b = c2;
        } else if (viewGroup instanceof LinearLayout) {
            layoutParams = new LinearLayout.LayoutParams(c(point.x), c(point.y));
            ((LinearLayout.LayoutParams) layoutParams).setMargins(c(point2.x), c(point2.y), 0, 0);
        } else if (viewGroup instanceof AbsListView) {
            return new AbsListView.LayoutParams(c(point.x), c(point.y));
        } else {
            if (viewGroup instanceof FrameLayout) {
                layoutParams = new FrameLayout.LayoutParams(c(point.x), c(point.y));
                ((FrameLayout.LayoutParams) layoutParams).setMargins(c(point2.x), c(point2.y), 0, 0);
            }
        }
        return layoutParams;
    }
}
