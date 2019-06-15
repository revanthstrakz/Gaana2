package com.facebook.ads.internal.t;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.ads.internal.s.a.a;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

class d {
    @Nullable
    static Float a(View view) {
        Activity a = a.a();
        if (a == null) {
            return null;
        }
        View findViewById = a.findViewById(16908290);
        if (findViewById == null) {
            findViewById = a.getWindow().getDecorView().findViewById(16908290);
        }
        return (findViewById == null || view == null || view.getId() == -1) ? null : findViewById.findViewById(view.getId()) == null ? Float.valueOf(-1.0f) : a(findViewById, view);
    }

    @Nullable
    @VisibleForTesting
    static Float a(View view, View view2) {
        if (view == null || view2 == null) {
            return null;
        }
        if (view2.getVisibility() != 0) {
            return Float.valueOf(0.0f);
        }
        float f;
        List<View> b = b(view, view2);
        if (b.isEmpty()) {
            f = 1.0f;
        } else {
            Rect rect = new Rect();
            if (!view2.getGlobalVisibleRect(rect)) {
                return Float.valueOf(0.0f);
            }
            int width = rect.width() * rect.height();
            Set hashSet = new HashSet();
            hashSet.add(rect);
            for (View view3 : b) {
                Rect rect2 = new Rect();
                if (view3.getGlobalVisibleRect(rect2)) {
                    HashSet hashSet2 = new HashSet();
                    for (Rect a : hashSet) {
                        hashSet2.addAll(a(a, rect2));
                    }
                    hashSet = hashSet2;
                }
            }
            int i = 0;
            for (Rect rect3 : hashSet) {
                i += rect3.width() * rect3.height();
            }
            f = ((float) i) / ((float) width);
        }
        return Float.valueOf(f);
    }

    @VisibleForTesting
    static Set<Rect> a(Rect rect, Rect rect2) {
        HashSet<Rect> hashSet = new HashSet();
        if (rect2.intersect(rect)) {
            hashSet.add(new Rect(rect.left, rect.top, rect2.left, rect.bottom));
            hashSet.add(new Rect(rect2.left, rect.top, rect2.right, rect2.top));
            hashSet.add(new Rect(rect2.right, rect.top, rect.right, rect.bottom));
            hashSet.add(new Rect(rect2.left, rect2.bottom, rect2.right, rect.bottom));
            HashSet hashSet2 = new HashSet();
            for (Rect rect3 : hashSet) {
                if (rect3.width() > 0 && rect3.height() > 0) {
                    hashSet2.add(rect3);
                }
            }
            return hashSet2;
        }
        hashSet.add(rect);
        return hashSet;
    }

    private static List<View> b(View view, View view2) {
        LinkedList linkedList = new LinkedList();
        Stack stack = new Stack();
        stack.push(view);
        int i = 0;
        while (!stack.empty()) {
            View view3 = (View) stack.pop();
            if (view3.getVisibility() == 0) {
                if (view3 == view2) {
                    i = 1;
                } else if (view3 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view3;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        stack.push(viewGroup.getChildAt(childCount));
                    }
                } else if (i != 0 || (VERSION.SDK_INT >= 21 && view3.getZ() > view2.getZ())) {
                    linkedList.add(view3);
                }
            }
        }
        return linkedList;
    }
}
