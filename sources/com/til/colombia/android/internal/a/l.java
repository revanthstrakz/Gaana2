package com.til.colombia.android.internal.a;

import android.graphics.Rect;
import android.os.SystemClock;
import android.view.View;
import com.til.colombia.android.commons.CommonUtil;
import com.til.colombia.android.commons.CommonUtil.InlineVideoVisiblity;
import com.til.colombia.android.internal.a;

public final class l {
    private static final int a = a.z();

    private static boolean a(long j, int i) {
        return SystemClock.uptimeMillis() - j >= ((long) i);
    }

    public static boolean a(View view) {
        return view != null && view.getVisibility() == 0 && b(view);
    }

    private static boolean b(View view) {
        try {
            Rect rect = new Rect();
            if (!view.getGlobalVisibleRect(rect)) {
                return false;
            }
            long height = ((long) rect.height()) * ((long) rect.width());
            long height2 = ((long) view.getHeight()) * ((long) view.getWidth());
            if (height == height2) {
                int[] iArr = new int[2];
                view.getLocationInWindow(iArr);
                if (iArr[1] < 0 || iArr[1] > CommonUtil.b()) {
                    return false;
                }
            }
            if (height2 <= 0 || 100 * height < ((long) a) * height2) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static int a(View view, View view2, int i) {
        if (view2 != null) {
            try {
                if (view2.getVisibility() == 0 && view2.getParent() != null) {
                    if (view.getParent() != null) {
                        Rect rect = new Rect();
                        if (!view2.getGlobalVisibleRect(rect)) {
                            return InlineVideoVisiblity.OUT_OF_VIEW.ordinal();
                        }
                        long height = ((long) rect.height()) * ((long) rect.width());
                        long height2 = ((long) view2.getHeight()) * ((long) view2.getWidth());
                        int[] iArr = new int[2];
                        view2.getLocationInWindow(iArr);
                        if (height == height2 && (iArr[1] <= 0 || iArr[1] > CommonUtil.b())) {
                            return InlineVideoVisiblity.OUT_OF_VIEW.ordinal();
                        }
                        if (iArr[1] == 0) {
                            return InlineVideoVisiblity.NONE.ordinal();
                        }
                        if (height2 <= 0 || 100 * height < ((long) i) * height2) {
                            return InlineVideoVisiblity.OUT_OF_VIEW.ordinal();
                        }
                        return InlineVideoVisiblity.VISIBLE.ordinal();
                    }
                }
            } catch (Exception unused) {
                return InlineVideoVisiblity.OUT_OF_VIEW.ordinal();
            }
        }
        return InlineVideoVisiblity.OUT_OF_VIEW.ordinal();
    }
}
