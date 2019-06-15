package com.moat.analytics.mobile.inm;

import android.app.Activity;
import android.graphics.Rect;
import android.location.Location;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.til.colombia.android.internal.e;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

class z {
    String a = "{}";
    private c b = new c();
    private JSONObject c;
    private Rect d;
    private Rect e;
    private JSONObject f;
    private JSONObject g;
    private Location h;
    private Map<String, Object> i = new HashMap();

    static class a {
        int a = 0;
        final Set<Rect> b = new HashSet();
        boolean c = false;

        a() {
        }
    }

    private static class b {
        final View a;
        final Rect b;

        b(View view, b bVar) {
            this.a = view;
            this.b = bVar != null ? z.b(view, bVar.b.left, bVar.b.top) : z.k(view);
        }
    }

    private static class c {
        Rect a = new Rect(0, 0, 0, 0);
        double b = 0.0d;
        double c = 0.0d;

        c() {
        }
    }

    z() {
    }

    @VisibleForTesting
    static int a(Rect rect, Set<Rect> set) {
        int i = 0;
        if (set.isEmpty()) {
            return 0;
        }
        Rect rect2;
        ArrayList<Rect> arrayList = new ArrayList();
        arrayList.addAll(set);
        Collections.sort(arrayList, new Comparator<Rect>() {
            /* renamed from: a */
            public final int compare(Rect rect, Rect rect2) {
                return Integer.valueOf(rect.top).compareTo(Integer.valueOf(rect2.top));
            }
        });
        ArrayList arrayList2 = new ArrayList();
        for (Rect rect22 : arrayList) {
            arrayList2.add(Integer.valueOf(rect22.left));
            arrayList2.add(Integer.valueOf(rect22.right));
        }
        Collections.sort(arrayList2);
        int i2 = 0;
        while (i < arrayList2.size() - 1) {
            int i3 = i + 1;
            if (!((Integer) arrayList2.get(i)).equals(arrayList2.get(i3))) {
                rect22 = new Rect(((Integer) arrayList2.get(i)).intValue(), rect.top, ((Integer) arrayList2.get(i3)).intValue(), rect.bottom);
                i = rect.top;
                for (Rect rect3 : arrayList) {
                    if (Rect.intersects(rect3, rect22)) {
                        if (rect3.bottom > i) {
                            i2 += rect22.width() * (rect3.bottom - Math.max(i, rect3.top));
                            i = rect3.bottom;
                        }
                        if (rect3.bottom == rect22.bottom) {
                            break;
                        }
                    }
                }
            }
            i = i3;
        }
        return i2;
    }

    private static Rect a(DisplayMetrics displayMetrics) {
        return new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    static Rect a(View view) {
        return view != null ? k(view) : new Rect(0, 0, 0, 0);
    }

    @VisibleForTesting
    static a a(Rect rect, @NonNull View view) {
        a aVar = new a();
        try {
            ArrayDeque i = i(view);
            if (i == null || i.isEmpty()) {
                return aVar;
            }
            p.b(2, "VisibilityInfo", view, "starting covering rect search");
            b bVar = null;
            while (!i.isEmpty()) {
                View view2 = (View) i.pollLast();
                b bVar2 = new b(view2, bVar);
                if (view2.getParent() != null && (view2.getParent() instanceof ViewGroup)) {
                    ViewGroup viewGroup = (ViewGroup) view2.getParent();
                    int childCount = viewGroup.getChildCount();
                    int i2 = 0;
                    boolean z = false;
                    while (i2 < childCount) {
                        if (aVar.a >= 500) {
                            p.a(3, "VisibilityInfo", null, "Short-circuiting cover retrieval, reached max");
                            return aVar;
                        }
                        View childAt = viewGroup.getChildAt(i2);
                        if (childAt == view2) {
                            z = true;
                        } else {
                            aVar.a++;
                            if (a(childAt, view2, z)) {
                                b(new b(childAt, bVar), rect, aVar);
                                if (aVar.c) {
                                    return aVar;
                                }
                            } else {
                                continue;
                            }
                        }
                        i2++;
                    }
                    continue;
                }
                bVar = bVar2;
            }
            return aVar;
        } catch (Exception e) {
            m.a(e);
        }
    }

    private static c a(View view, Rect rect, boolean z, boolean z2, boolean z3) {
        c cVar = new c();
        int b = b(rect);
        if (view != null && z && z2 && !z3 && b > 0) {
            Rect rect2 = new Rect(0, 0, 0, 0);
            if (a(view, rect2)) {
                int b2 = b(rect2);
                if (b2 < b) {
                    p.b(2, "VisibilityInfo", null, "Ad is clipped");
                }
                if (view.getRootView() instanceof ViewGroup) {
                    cVar.a = rect2;
                    a a = a(rect2, view);
                    if (a.c) {
                        cVar.c = 1.0d;
                        return cVar;
                    }
                    int a2 = a(rect2, a.b);
                    if (a2 > 0) {
                        cVar.c = ((double) a2) / (((double) b2) * 1.0d);
                    }
                    cVar.b = ((double) (b2 - a2)) / (((double) b) * 1.0d);
                }
            }
        }
        return cVar;
    }

    private static Map<String, String> a(Rect rect) {
        HashMap hashMap = new HashMap();
        hashMap.put(AvidJSONUtil.KEY_X, String.valueOf(rect.left));
        hashMap.put(AvidJSONUtil.KEY_Y, String.valueOf(rect.top));
        hashMap.put(e.G, String.valueOf(rect.right - rect.left));
        hashMap.put("h", String.valueOf(rect.bottom - rect.top));
        return hashMap;
    }

    private static Map<String, String> a(Rect rect, DisplayMetrics displayMetrics) {
        return a(b(rect, displayMetrics));
    }

    private static JSONObject a(Location location) {
        Map b = b(location);
        return b == null ? null : new JSONObject(b);
    }

    private static void a(b bVar, Rect rect, a aVar) {
        Rect rect2 = bVar.b;
        if (rect2.setIntersect(rect, rect2)) {
            if (VERSION.SDK_INT >= 22) {
                rect2 = new Rect(0, 0, 0, 0);
                if (a(bVar.a, rect2)) {
                    Rect rect3 = bVar.b;
                    if (rect3.setIntersect(rect2, rect3)) {
                        rect2 = rect3;
                    } else {
                        return;
                    }
                }
                return;
            }
            if (w.a().c) {
                p.b(2, "VisibilityInfo", bVar.a, String.format(Locale.ROOT, "Covered by %s-%s alpha=%f", new Object[]{bVar.a.getClass().getName(), rect2.toString(), Float.valueOf(bVar.a.getAlpha())}));
            }
            aVar.b.add(rect2);
            if (rect2.contains(rect)) {
                aVar.c = true;
            }
        }
    }

    private static boolean a(View view, Rect rect) {
        if (!view.getGlobalVisibleRect(rect)) {
            return false;
        }
        int[] iArr = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationInWindow(iArr);
        int[] iArr2 = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationOnScreen(iArr2);
        rect.offset(iArr2[0] - iArr[0], iArr2[1] - iArr[1]);
        return true;
    }

    private static boolean a(View view, View view2, boolean z) {
        return z ? VERSION.SDK_INT < 21 || view.getZ() >= view2.getZ() : VERSION.SDK_INT >= 21 && view.getZ() > view2.getZ();
    }

    private static int b(Rect rect) {
        return rect.width() * rect.height();
    }

    private static Rect b(Rect rect, DisplayMetrics displayMetrics) {
        float f = displayMetrics.density;
        if (f == 0.0f) {
            return rect;
        }
        return new Rect(Math.round(((float) rect.left) / f), Math.round(((float) rect.top) / f), Math.round(((float) rect.right) / f), Math.round(((float) rect.bottom) / f));
    }

    private static Rect b(View view, int i, int i2) {
        i += view.getLeft();
        i2 += view.getTop();
        return new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2);
    }

    private static Map<String, String> b(Location location) {
        if (location == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("latitude", Double.toString(location.getLatitude()));
        hashMap.put("longitude", Double.toString(location.getLongitude()));
        hashMap.put(AvidJSONUtil.KEY_TIMESTAMP, Long.toString(location.getTime()));
        hashMap.put("horizontalAccuracy", Float.toString(location.getAccuracy()));
        return hashMap;
    }

    private static void b(b bVar, Rect rect, a aVar) {
        if (h(bVar.a)) {
            int i;
            if (bVar.a instanceof ViewGroup) {
                int i2 = 0;
                i = (ViewGroup.class.equals(bVar.a.getClass().getSuperclass()) && j(bVar.a)) ? 0 : 1;
                ViewGroup viewGroup = (ViewGroup) bVar.a;
                int childCount = viewGroup.getChildCount();
                while (i2 < childCount) {
                    int i3 = aVar.a + 1;
                    aVar.a = i3;
                    if (i3 <= 500) {
                        b(new b(viewGroup.getChildAt(i2), bVar), rect, aVar);
                        if (!aVar.c) {
                            i2++;
                        } else {
                            return;
                        }
                    }
                    return;
                }
            }
            i = 1;
            if (i != 0) {
                a(bVar, rect, aVar);
            }
        }
    }

    private static boolean c(View view) {
        return VERSION.SDK_INT >= 19 ? view != null && view.isAttachedToWindow() : (view == null || view.getWindowToken() == null) ? false : true;
    }

    private static boolean d(View view) {
        return view != null && view.hasWindowFocus();
    }

    private static boolean e(View view) {
        return view == null || !view.isShown();
    }

    private static float f(View view) {
        return view == null ? 0.0f : g(view);
    }

    private static float g(View view) {
        float alpha = view.getAlpha();
        while (view != null && view.getParent() != null && ((double) alpha) != 0.0d && (view.getParent() instanceof View)) {
            alpha *= ((View) view.getParent()).getAlpha();
            view = (View) view.getParent();
        }
        return alpha;
    }

    private static boolean h(View view) {
        return view.isShown() && ((double) view.getAlpha()) > 0.0d;
    }

    private static ArrayDeque<View> i(@NonNull View view) {
        ArrayDeque arrayDeque = new ArrayDeque();
        int i = 0;
        View view2 = view;
        while (true) {
            if (view2.getParent() == null && view2 != view.getRootView()) {
                break;
            }
            i++;
            if (i <= 50) {
                arrayDeque.add(view2);
                if (!(view2.getParent() instanceof View)) {
                    break;
                }
                view2 = (View) view2.getParent();
            } else {
                p.a(3, "VisibilityInfo", null, "Short-circuiting chain retrieval, reached max");
                return arrayDeque;
            }
        }
        return arrayDeque;
    }

    /* JADX WARNING: Missing block: B:9:0x001a, code skipped:
            return true;
     */
    private static boolean j(android.view.View r3) {
        /*
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 1;
        r2 = 19;
        if (r0 < r2) goto L_0x001a;
    L_0x0007:
        r0 = r3.getBackground();
        if (r0 == 0) goto L_0x001a;
    L_0x000d:
        r3 = r3.getBackground();
        r3 = r3.getAlpha();
        if (r3 != 0) goto L_0x0018;
    L_0x0017:
        return r1;
    L_0x0018:
        r3 = 0;
        return r3;
    L_0x001a:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.inm.z.j(android.view.View):boolean");
    }

    private static Rect k(View view) {
        int[] iArr = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2);
    }

    private static DisplayMetrics l(View view) {
        if (VERSION.SDK_INT >= 17 && a.a != null) {
            Activity activity = (Activity) a.a.get();
            if (activity != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
                return displayMetrics;
            }
        }
        return view.getContext().getResources().getDisplayMetrics();
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0102 A:{Catch:{ Exception -> 0x014d }} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0107 A:{Catch:{ Exception -> 0x014d }} */
    public void a(java.lang.String r12, android.view.View r13) {
        /*
        r11 = this;
        r0 = new java.util.HashMap;
        r0.<init>();
        r1 = "{}";
        if (r13 == 0) goto L_0x0153;
    L_0x0009:
        r2 = l(r13);	 Catch:{ Exception -> 0x014d }
        r3 = c(r13);	 Catch:{ Exception -> 0x014d }
        r4 = d(r13);	 Catch:{ Exception -> 0x014d }
        r5 = e(r13);	 Catch:{ Exception -> 0x014d }
        r6 = f(r13);	 Catch:{ Exception -> 0x014d }
        r7 = "dr";
        r8 = r2.density;	 Catch:{ Exception -> 0x014d }
        r8 = java.lang.Float.valueOf(r8);	 Catch:{ Exception -> 0x014d }
        r0.put(r7, r8);	 Catch:{ Exception -> 0x014d }
        r7 = "dv";
        r8 = com.moat.analytics.mobile.inm.s.a();	 Catch:{ Exception -> 0x014d }
        r8 = java.lang.Double.valueOf(r8);	 Catch:{ Exception -> 0x014d }
        r0.put(r7, r8);	 Catch:{ Exception -> 0x014d }
        r7 = "adKey";
        r0.put(r7, r12);	 Catch:{ Exception -> 0x014d }
        r12 = "isAttached";
        r7 = java.lang.Integer.valueOf(r3);	 Catch:{ Exception -> 0x014d }
        r0.put(r12, r7);	 Catch:{ Exception -> 0x014d }
        r12 = "inFocus";
        r7 = java.lang.Integer.valueOf(r4);	 Catch:{ Exception -> 0x014d }
        r0.put(r12, r7);	 Catch:{ Exception -> 0x014d }
        r12 = "isHidden";
        r7 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x014d }
        r0.put(r12, r7);	 Catch:{ Exception -> 0x014d }
        r12 = "opacity";
        r6 = java.lang.Float.valueOf(r6);	 Catch:{ Exception -> 0x014d }
        r0.put(r12, r6);	 Catch:{ Exception -> 0x014d }
        r12 = a(r2);	 Catch:{ Exception -> 0x014d }
        r6 = a(r13);	 Catch:{ Exception -> 0x014d }
        r13 = a(r13, r6, r3, r4, r5);	 Catch:{ Exception -> 0x014d }
        r3 = r11.c;	 Catch:{ Exception -> 0x014d }
        r4 = 1;
        if (r3 == 0) goto L_0x0092;
    L_0x006f:
        r7 = r13.b;	 Catch:{ Exception -> 0x014d }
        r3 = r11.b;	 Catch:{ Exception -> 0x014d }
        r9 = r3.b;	 Catch:{ Exception -> 0x014d }
        r3 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1));
        if (r3 != 0) goto L_0x0092;
    L_0x0079:
        r3 = r13.a;	 Catch:{ Exception -> 0x014d }
        r5 = r11.b;	 Catch:{ Exception -> 0x014d }
        r5 = r5.a;	 Catch:{ Exception -> 0x014d }
        r3 = r3.equals(r5);	 Catch:{ Exception -> 0x014d }
        if (r3 == 0) goto L_0x0092;
    L_0x0085:
        r7 = r13.c;	 Catch:{ Exception -> 0x014d }
        r3 = r11.b;	 Catch:{ Exception -> 0x014d }
        r9 = r3.c;	 Catch:{ Exception -> 0x014d }
        r3 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1));
        if (r3 == 0) goto L_0x0090;
    L_0x008f:
        goto L_0x0092;
    L_0x0090:
        r3 = 0;
        goto L_0x00a4;
    L_0x0092:
        r11.b = r13;	 Catch:{ Exception -> 0x014d }
        r3 = new org.json.JSONObject;	 Catch:{ Exception -> 0x014d }
        r5 = r11.b;	 Catch:{ Exception -> 0x014d }
        r5 = r5.a;	 Catch:{ Exception -> 0x014d }
        r5 = a(r5, r2);	 Catch:{ Exception -> 0x014d }
        r3.<init>(r5);	 Catch:{ Exception -> 0x014d }
        r11.c = r3;	 Catch:{ Exception -> 0x014d }
        r3 = r4;
    L_0x00a4:
        r5 = "coveredPercent";
        r7 = r13.c;	 Catch:{ Exception -> 0x014d }
        r13 = java.lang.Double.valueOf(r7);	 Catch:{ Exception -> 0x014d }
        r0.put(r5, r13);	 Catch:{ Exception -> 0x014d }
        r13 = r11.g;	 Catch:{ Exception -> 0x014d }
        if (r13 == 0) goto L_0x00bb;
    L_0x00b3:
        r13 = r11.e;	 Catch:{ Exception -> 0x014d }
        r13 = r12.equals(r13);	 Catch:{ Exception -> 0x014d }
        if (r13 != 0) goto L_0x00c9;
    L_0x00bb:
        r11.e = r12;	 Catch:{ Exception -> 0x014d }
        r13 = new org.json.JSONObject;	 Catch:{ Exception -> 0x014d }
        r12 = a(r12, r2);	 Catch:{ Exception -> 0x014d }
        r13.<init>(r12);	 Catch:{ Exception -> 0x014d }
        r11.g = r13;	 Catch:{ Exception -> 0x014d }
        r3 = r4;
    L_0x00c9:
        r12 = r11.f;	 Catch:{ Exception -> 0x014d }
        if (r12 == 0) goto L_0x00d5;
    L_0x00cd:
        r12 = r11.d;	 Catch:{ Exception -> 0x014d }
        r12 = r6.equals(r12);	 Catch:{ Exception -> 0x014d }
        if (r12 != 0) goto L_0x00e3;
    L_0x00d5:
        r11.d = r6;	 Catch:{ Exception -> 0x014d }
        r12 = new org.json.JSONObject;	 Catch:{ Exception -> 0x014d }
        r13 = a(r6, r2);	 Catch:{ Exception -> 0x014d }
        r12.<init>(r13);	 Catch:{ Exception -> 0x014d }
        r11.f = r12;	 Catch:{ Exception -> 0x014d }
        r3 = r4;
    L_0x00e3:
        r12 = r11.i;	 Catch:{ Exception -> 0x014d }
        if (r12 == 0) goto L_0x00ef;
    L_0x00e7:
        r12 = r11.i;	 Catch:{ Exception -> 0x014d }
        r12 = r0.equals(r12);	 Catch:{ Exception -> 0x014d }
        if (r12 != 0) goto L_0x00f2;
    L_0x00ef:
        r11.i = r0;	 Catch:{ Exception -> 0x014d }
        r3 = r4;
    L_0x00f2:
        r12 = com.moat.analytics.mobile.inm.o.a();	 Catch:{ Exception -> 0x014d }
        r12 = r12.b();	 Catch:{ Exception -> 0x014d }
        r13 = r11.h;	 Catch:{ Exception -> 0x014d }
        r13 = com.moat.analytics.mobile.inm.o.a(r12, r13);	 Catch:{ Exception -> 0x014d }
        if (r13 != 0) goto L_0x0105;
    L_0x0102:
        r11.h = r12;	 Catch:{ Exception -> 0x014d }
        r3 = r4;
    L_0x0105:
        if (r3 == 0) goto L_0x0153;
    L_0x0107:
        r13 = new org.json.JSONObject;	 Catch:{ Exception -> 0x014d }
        r0 = r11.i;	 Catch:{ Exception -> 0x014d }
        r13.<init>(r0);	 Catch:{ Exception -> 0x014d }
        r0 = "screen";
        r2 = r11.g;	 Catch:{ Exception -> 0x014d }
        r13.accumulate(r0, r2);	 Catch:{ Exception -> 0x014d }
        r0 = "view";
        r2 = r11.f;	 Catch:{ Exception -> 0x014d }
        r13.accumulate(r0, r2);	 Catch:{ Exception -> 0x014d }
        r0 = "visible";
        r2 = r11.c;	 Catch:{ Exception -> 0x014d }
        r13.accumulate(r0, r2);	 Catch:{ Exception -> 0x014d }
        r0 = "maybe";
        r2 = r11.c;	 Catch:{ Exception -> 0x014d }
        r13.accumulate(r0, r2);	 Catch:{ Exception -> 0x014d }
        r0 = "visiblePercent";
        r2 = r11.b;	 Catch:{ Exception -> 0x014d }
        r2 = r2.b;	 Catch:{ Exception -> 0x014d }
        r2 = java.lang.Double.valueOf(r2);	 Catch:{ Exception -> 0x014d }
        r13.accumulate(r0, r2);	 Catch:{ Exception -> 0x014d }
        if (r12 == 0) goto L_0x0142;
    L_0x0139:
        r0 = "location";
        r12 = a(r12);	 Catch:{ Exception -> 0x014d }
        r13.accumulate(r0, r12);	 Catch:{ Exception -> 0x014d }
    L_0x0142:
        r12 = r13.toString();	 Catch:{ Exception -> 0x014d }
        r11.a = r12;	 Catch:{ Exception -> 0x0149 }
        return;
    L_0x0149:
        r13 = move-exception;
        r1 = r12;
        r12 = r13;
        goto L_0x014e;
    L_0x014d:
        r12 = move-exception;
    L_0x014e:
        com.moat.analytics.mobile.inm.m.a(r12);
        r11.a = r1;
    L_0x0153:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.inm.z.a(java.lang.String, android.view.View):void");
    }
}
