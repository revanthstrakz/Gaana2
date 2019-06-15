package com.facebook.ads.internal.t;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.facebook.ads.internal.s.a.aa;
import com.facebook.ads.internal.s.a.v;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.s.a.z;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import org.json.JSONObject;

public class a {
    private static final String a = "a";
    private final View b;
    private final int c;
    private final int d;
    private final WeakReference<a> e;
    private final Handler f;
    private final boolean g;
    private Runnable h;
    private int i;
    private int j;
    private boolean k;
    private b l;
    private Map<String, Integer> m;
    private long n;
    private int o;

    public static abstract class a {
        public abstract void a();

        public void b() {
        }
    }

    private static final class b extends z<a> {
        b(a aVar) {
            super(aVar);
        }

        public void run() {
            a aVar = (a) a();
            if (aVar != null) {
                View a = aVar.b;
                a aVar2 = (a) aVar.e.get();
                if (a != null && aVar2 != null) {
                    b a2 = a.a(a, aVar.c);
                    int i = 0;
                    if (a2.a()) {
                        aVar.o = aVar.o + 1;
                    } else {
                        aVar.o = 0;
                    }
                    int i2 = aVar.o > aVar.d ? 1 : 0;
                    int i3 = (aVar.l == null || !aVar.l.a()) ? 0 : 1;
                    if (!(i2 == 0 && a2.a())) {
                        aVar.l = a2;
                    }
                    String valueOf = String.valueOf(a2.b());
                    synchronized (aVar) {
                        if (aVar.m.containsKey(valueOf)) {
                            i = ((Integer) aVar.m.get(valueOf)).intValue();
                        }
                        aVar.m.put(valueOf, Integer.valueOf(i + 1));
                    }
                    if (i2 != 0 && i3 == 0) {
                        aVar.n = System.currentTimeMillis();
                        aVar2.a();
                        if (!aVar.g) {
                            return;
                        }
                    } else if (i2 == 0 && i3 != 0) {
                        aVar2.b();
                    }
                    if (!(aVar.k || aVar.h == null)) {
                        aVar.f.postDelayed(aVar.h, (long) aVar.j);
                    }
                }
            }
        }
    }

    public a(View view, int i, int i2, boolean z, a aVar) {
        this.f = new Handler();
        this.i = 0;
        this.j = 1000;
        this.k = true;
        this.l = new b(c.UNKNOWN);
        this.m = new HashMap();
        this.n = 0;
        this.o = 0;
        this.b = view;
        if (this.b.getId() == -1) {
            y.a(this.b);
        }
        this.c = i;
        this.e = new WeakReference(aVar);
        this.g = z;
        if (i2 < 0) {
            i2 = 0;
        }
        this.d = i2;
    }

    public a(View view, int i, a aVar) {
        this(view, i, 0, false, aVar);
    }

    public a(View view, int i, boolean z, a aVar) {
        this(view, i, 0, z, aVar);
    }

    @VisibleForTesting
    static float a(View view) {
        float alpha = view.getAlpha();
        while (view.getParent() instanceof ViewGroup) {
            view = (View) view.getParent();
            float alpha2 = view.getAlpha();
            if (alpha2 < 0.0f) {
                alpha2 = 0.0f;
            }
            if (alpha2 > 1.0f) {
                alpha2 = 1.0f;
            }
            alpha *= alpha2;
        }
        return alpha;
    }

    @VisibleForTesting
    public static int a(int i, View view) {
        int width = view.getWidth() * view.getHeight();
        float f = 100.0f;
        if (width > 0) {
            f = 100.0f / ((float) width);
        }
        return (int) Math.max((double) i, Math.ceil((double) f));
    }

    private static int a(Vector<Rect> vector) {
        int i;
        int size = vector.size();
        int i2 = size * 2;
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        boolean[][] zArr = (boolean[][]) Array.newInstance(boolean.class, new int[]{i2, i2});
        int i3 = 0;
        int i4 = i3;
        int i5 = i4;
        while (i3 < size) {
            Rect rect = (Rect) vector.elementAt(i3);
            i = i4 + 1;
            iArr[i4] = rect.left;
            i4 = i5 + 1;
            iArr2[i5] = rect.bottom;
            i5 = i + 1;
            iArr[i] = rect.right;
            i = i4 + 1;
            iArr2[i4] = rect.top;
            i3++;
            i4 = i5;
            i5 = i;
        }
        Arrays.sort(iArr);
        Arrays.sort(iArr2);
        for (i3 = 0; i3 < size; i3++) {
            Rect rect2 = (Rect) vector.elementAt(i3);
            i5 = a(iArr, rect2.left);
            int a = a(iArr, rect2.right);
            i = a(iArr2, rect2.top);
            i4 = a(iArr2, rect2.bottom);
            for (i5++; i5 <= a; i5++) {
                for (int i6 = i + 1; i6 <= i4; i6++) {
                    zArr[i5][i6] = true;
                }
            }
        }
        int i7 = 0;
        size = i7;
        while (i7 < i2) {
            i3 = size;
            for (size = 0; size < i2; size++) {
                i3 += zArr[i7][size] ? (iArr[i7] - iArr[i7 - 1]) * (iArr2[size] - iArr2[size - 1]) : 0;
            }
            i7++;
            size = i3;
        }
        return size;
    }

    private static int a(int[] iArr, int i) {
        int i2 = 0;
        int length = iArr.length;
        while (i2 < length) {
            int i3 = ((length - i2) / 2) + i2;
            if (iArr[i3] == i) {
                return i3;
            }
            if (iArr[i3] > i) {
                length = i3;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    public static b a(View view, int i) {
        View view2 = view;
        boolean z = false;
        if (view2 == null) {
            a(null, false, "mAdView is null.");
            return new b(c.AD_IS_NULL);
        } else if (view.getParent() == null) {
            a(view2, false, "mAdView has no parent.");
            return new b(c.INVALID_PARENT);
        } else if (!view.isShown()) {
            a(view2, false, "mAdView parent is not set to VISIBLE.");
            return new b(c.INVALID_PARENT);
        } else if (view.getWindowVisibility() != 0) {
            a(view2, false, "mAdView window is not set to VISIBLE.");
            return new b(c.INVALID_WINDOW);
        } else if (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("mAdView has invisible dimensions (w=");
            stringBuilder.append(view.getMeasuredWidth());
            stringBuilder.append(", h=");
            stringBuilder.append(view.getMeasuredHeight());
            a(view2, false, stringBuilder.toString());
            return new b(c.INVALID_DIMENSIONS);
        } else if (a(view) < 0.9f) {
            a(view2, false, "mAdView is too transparent.");
            return new b(c.AD_IS_TRANSPARENT);
        } else {
            int width = view.getWidth();
            int height = view.getHeight();
            int[] iArr = new int[2];
            try {
                view2.getLocationOnScreen(iArr);
                Rect rect = new Rect();
                if (!view2.getGlobalVisibleRect(rect)) {
                    return new b(c.AD_IS_NOT_VISIBLE);
                }
                Context context = view.getContext();
                DisplayMetrics displayMetrics;
                if (VERSION.SDK_INT >= 17) {
                    Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
                    displayMetrics = new DisplayMetrics();
                    defaultDisplay.getRealMetrics(displayMetrics);
                } else {
                    displayMetrics = context.getResources().getDisplayMetrics();
                }
                Vector b = b(view);
                int a = a(b);
                b.add(rect);
                float a2 = (((float) (a(b) - a)) * 1.0f) / ((float) (view.getMeasuredHeight() * view.getMeasuredWidth()));
                boolean y = com.facebook.ads.internal.n.a.y(context);
                int a3 = a(i, view2);
                float f = ((float) a3) / 100.0f;
                if (y) {
                    if (a2 < f) {
                        a(view2, false, String.format(Locale.US, "mAdView visible area is too small [%.2f%% visible, current threshold %.2f%%]", new Object[]{Float.valueOf(a2), Float.valueOf(f)}));
                        return new b(c.AD_INSUFFICIENT_VISIBLE_AREA, a2);
                    }
                } else if (iArr[0] < 0 || displayMetrics.widthPixels - iArr[0] < width) {
                    a(view2, false, "mAdView is not fully on screen horizontally.");
                    return new b(c.AD_OFFSCREEN_HORIZONTALLY, a2);
                } else {
                    int i2 = (int) ((((double) height) * (100.0d - ((double) a3))) / 100.0d);
                    if (rect.top - iArr[1] > i2) {
                        a(view2, false, "mAdView is not visible from the top.");
                        return new b(c.AD_OFFSCREEN_TOP, a2);
                    }
                    z = false;
                    if ((iArr[1] + height) - rect.bottom > i2) {
                        a(view2, false, "mAdView is not visible from the bottom.");
                        return new b(c.AD_OFFSCREEN_BOTTOM, a2);
                    }
                }
                if (com.facebook.ads.internal.s.e.a.b(context)) {
                    Map a4 = com.facebook.ads.internal.s.e.b.a(context);
                    if (aa.b(a4)) {
                        a(view2, z, "Keyguard is obstructing view.");
                        return new b(c.AD_IS_OBSTRUCTED_BY_KEYGUARD, a2);
                    } else if (com.facebook.ads.internal.n.a.c(context) && aa.a(a4)) {
                        a(view2, z, "Ad is on top of the Lockscreen.");
                        return new b(c.AD_IN_LOCKSCREEN, a2, a4);
                    } else {
                        Float a5 = com.facebook.ads.internal.n.a.u(context) ? d.a(view) : null;
                        if (a5 != null) {
                            if (a5.floatValue() == -1.0f) {
                                a(view2, false, "mAdView is not in the top activity");
                                return new b(c.AD_IS_NOT_IN_ACTIVITY);
                            } else if (a5.floatValue() == 0.0f) {
                                a(view2, false, "mAdView is not visible");
                                return new b(c.AD_IS_NOT_VISIBLE);
                            }
                        }
                        if (!com.facebook.ads.internal.n.a.v(context) || a5 == null || a5.floatValue() >= f) {
                            a(view2, true, "mAdView is visible.");
                            return new b(c.IS_VIEWABLE, a2, a4);
                        }
                        a(view2, false, String.format(Locale.US, "mAdView visible area is too small [%.2f%% visible, current threshold %.2f%%]", new Object[]{a5, Float.valueOf(f)}));
                        return new b(c.AD_INSUFFICIENT_VISIBLE_AREA, a2, a4);
                    }
                }
                a(view2, z, "Screen is not interactive.");
                return new b(c.SCREEN_NOT_INTERACTIVE, a2);
            } catch (NullPointerException unused) {
                a(view2, false, "Cannot get location on screen.");
                return new b(c.INVALID_DIMENSIONS);
            }
        }
    }

    private static void a(View view, boolean z, String str) {
    }

    private static Vector<Rect> b(View view) {
        Vector vector = new Vector();
        if (!(view.getParent() instanceof ViewGroup)) {
            return vector;
        }
        View view2 = (ViewGroup) view.getParent();
        int indexOfChild = view2.indexOfChild(view);
        while (true) {
            indexOfChild++;
            if (indexOfChild < view2.getChildCount()) {
                vector.addAll(c(view2.getChildAt(indexOfChild)));
            } else {
                vector.addAll(b(view2));
                return vector;
            }
        }
    }

    private static Vector<Rect> c(View view) {
        Vector vector = new Vector();
        if (!view.isShown() || (VERSION.SDK_INT >= 11 && view.getAlpha() <= 0.0f)) {
            return vector;
        }
        if ((view instanceof ViewGroup) && d(view)) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                vector.addAll(c(viewGroup.getChildAt(i)));
            }
            return vector;
        }
        Rect rect = new Rect();
        if (view.getGlobalVisibleRect(rect)) {
            vector.add(rect);
        }
        return vector;
    }

    private static boolean d(View view) {
        return view.getBackground() == null || (VERSION.SDK_INT >= 19 && view.getBackground().getAlpha() <= 0);
    }

    public synchronized void a() {
        if (this.h != null) {
            c();
        }
        this.h = new b(this);
        this.f.postDelayed(this.h, (long) this.i);
        this.k = false;
        this.o = 0;
        this.l = new b(c.UNKNOWN);
        this.m = new HashMap();
    }

    public void a(int i) {
        this.i = i;
    }

    public synchronized void a(Map<String, String> map) {
        map.put("vrc", String.valueOf(this.l.b()));
        map.put("vp", String.valueOf(this.l.c()));
        map.put("vh", new JSONObject(this.m).toString());
        map.put("vt", v.b(this.n));
        map.putAll(this.l.d());
    }

    public void b(int i) {
        this.j = i;
    }

    public synchronized boolean b() {
        return this.k;
    }

    public synchronized void c() {
        this.f.removeCallbacks(this.h);
        this.h = null;
        this.k = true;
        this.o = 0;
    }

    public synchronized String d() {
        StringBuilder stringBuilder;
        c cVar = c.values()[this.l.b()];
        stringBuilder = new StringBuilder();
        stringBuilder.append(cVar.toString());
        stringBuilder.append(String.format(Locale.US, " (%.1f%%)", new Object[]{Float.valueOf(this.l.c() * 100.0f)}));
        return stringBuilder.toString();
    }
}
