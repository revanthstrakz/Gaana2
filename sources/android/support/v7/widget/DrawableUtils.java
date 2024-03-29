package android.support.v7.widget;

import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.graphics.drawable.WrappedDrawable;
import android.support.v7.graphics.drawable.DrawableWrapper;

@RestrictTo({Scope.LIBRARY_GROUP})
public class DrawableUtils {
    public static final Rect INSETS_NONE = new Rect();
    private static final String TAG = "DrawableUtils";
    private static final String VECTOR_DRAWABLE_CLAZZ_NAME = "android.graphics.drawable.VectorDrawable";
    private static Class<?> sInsetsClazz;

    static {
        if (VERSION.SDK_INT >= 18) {
            try {
                sInsetsClazz = Class.forName("android.graphics.Insets");
            } catch (ClassNotFoundException unused) {
            }
        }
    }

    private DrawableUtils() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0078 A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008e A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0087 A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0080 A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0079 A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0078 A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008e A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0087 A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0080 A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0079 A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0078 A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008e A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0087 A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0080 A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0079 A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0078 A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008e A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0087 A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0080 A:{Catch:{ Exception -> 0x0098 }} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0079 A:{Catch:{ Exception -> 0x0098 }} */
    public static android.graphics.Rect getOpticalBounds(android.graphics.drawable.Drawable r10) {
        /*
        r0 = sInsetsClazz;
        if (r0 == 0) goto L_0x009f;
    L_0x0004:
        r10 = android.support.v4.graphics.drawable.DrawableCompat.unwrap(r10);	 Catch:{ Exception -> 0x0098 }
        r0 = r10.getClass();	 Catch:{ Exception -> 0x0098 }
        r1 = "getOpticalInsets";
        r2 = 0;
        r3 = new java.lang.Class[r2];	 Catch:{ Exception -> 0x0098 }
        r0 = r0.getMethod(r1, r3);	 Catch:{ Exception -> 0x0098 }
        r1 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x0098 }
        r10 = r0.invoke(r10, r1);	 Catch:{ Exception -> 0x0098 }
        if (r10 == 0) goto L_0x009f;
    L_0x001d:
        r0 = new android.graphics.Rect;	 Catch:{ Exception -> 0x0098 }
        r0.<init>();	 Catch:{ Exception -> 0x0098 }
        r1 = sInsetsClazz;	 Catch:{ Exception -> 0x0098 }
        r1 = r1.getFields();	 Catch:{ Exception -> 0x0098 }
        r3 = r1.length;	 Catch:{ Exception -> 0x0098 }
        r4 = r2;
    L_0x002a:
        if (r4 >= r3) goto L_0x0097;
    L_0x002c:
        r5 = r1[r4];	 Catch:{ Exception -> 0x0098 }
        r6 = r5.getName();	 Catch:{ Exception -> 0x0098 }
        r7 = -1;
        r8 = r6.hashCode();	 Catch:{ Exception -> 0x0098 }
        r9 = -1383228885; // 0xffffffffad8d9a2b float:-1.6098308E-11 double:NaN;
        if (r8 == r9) goto L_0x006a;
    L_0x003c:
        r9 = 115029; // 0x1c155 float:1.6119E-40 double:5.6832E-319;
        if (r8 == r9) goto L_0x0060;
    L_0x0041:
        r9 = 3317767; // 0x32a007 float:4.649182E-39 double:1.6391947E-317;
        if (r8 == r9) goto L_0x0056;
    L_0x0046:
        r9 = 108511772; // 0x677c21c float:4.6598146E-35 double:5.36119387E-316;
        if (r8 == r9) goto L_0x004c;
    L_0x004b:
        goto L_0x0074;
    L_0x004c:
        r8 = "right";
        r6 = r6.equals(r8);	 Catch:{ Exception -> 0x0098 }
        if (r6 == 0) goto L_0x0074;
    L_0x0054:
        r6 = 2;
        goto L_0x0075;
    L_0x0056:
        r8 = "left";
        r6 = r6.equals(r8);	 Catch:{ Exception -> 0x0098 }
        if (r6 == 0) goto L_0x0074;
    L_0x005e:
        r6 = r2;
        goto L_0x0075;
    L_0x0060:
        r8 = "top";
        r6 = r6.equals(r8);	 Catch:{ Exception -> 0x0098 }
        if (r6 == 0) goto L_0x0074;
    L_0x0068:
        r6 = 1;
        goto L_0x0075;
    L_0x006a:
        r8 = "bottom";
        r6 = r6.equals(r8);	 Catch:{ Exception -> 0x0098 }
        if (r6 == 0) goto L_0x0074;
    L_0x0072:
        r6 = 3;
        goto L_0x0075;
    L_0x0074:
        r6 = r7;
    L_0x0075:
        switch(r6) {
            case 0: goto L_0x008e;
            case 1: goto L_0x0087;
            case 2: goto L_0x0080;
            case 3: goto L_0x0079;
            default: goto L_0x0078;
        };	 Catch:{ Exception -> 0x0098 }
    L_0x0078:
        goto L_0x0094;
    L_0x0079:
        r5 = r5.getInt(r10);	 Catch:{ Exception -> 0x0098 }
        r0.bottom = r5;	 Catch:{ Exception -> 0x0098 }
        goto L_0x0094;
    L_0x0080:
        r5 = r5.getInt(r10);	 Catch:{ Exception -> 0x0098 }
        r0.right = r5;	 Catch:{ Exception -> 0x0098 }
        goto L_0x0094;
    L_0x0087:
        r5 = r5.getInt(r10);	 Catch:{ Exception -> 0x0098 }
        r0.top = r5;	 Catch:{ Exception -> 0x0098 }
        goto L_0x0094;
    L_0x008e:
        r5 = r5.getInt(r10);	 Catch:{ Exception -> 0x0098 }
        r0.left = r5;	 Catch:{ Exception -> 0x0098 }
    L_0x0094:
        r4 = r4 + 1;
        goto L_0x002a;
    L_0x0097:
        return r0;
    L_0x0098:
        r10 = "DrawableUtils";
        r0 = "Couldn't obtain the optical insets. Ignoring.";
        android.util.Log.e(r10, r0);
    L_0x009f:
        r10 = INSETS_NONE;
        return r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.DrawableUtils.getOpticalBounds(android.graphics.drawable.Drawable):android.graphics.Rect");
    }

    static void fixDrawable(@NonNull Drawable drawable) {
        if (VERSION.SDK_INT == 21 && VECTOR_DRAWABLE_CLAZZ_NAME.equals(drawable.getClass().getName())) {
            fixVectorDrawableTinting(drawable);
        }
    }

    public static boolean canSafelyMutateDrawable(@NonNull Drawable drawable) {
        if (VERSION.SDK_INT < 15 && (drawable instanceof InsetDrawable)) {
            return false;
        }
        if (VERSION.SDK_INT < 15 && (drawable instanceof GradientDrawable)) {
            return false;
        }
        if (VERSION.SDK_INT < 17 && (drawable instanceof LayerDrawable)) {
            return false;
        }
        if (drawable instanceof DrawableContainer) {
            ConstantState constantState = drawable.getConstantState();
            if (constantState instanceof DrawableContainerState) {
                for (Drawable canSafelyMutateDrawable : ((DrawableContainerState) constantState).getChildren()) {
                    if (!canSafelyMutateDrawable(canSafelyMutateDrawable)) {
                        return false;
                    }
                }
            }
        } else if (drawable instanceof WrappedDrawable) {
            return canSafelyMutateDrawable(((WrappedDrawable) drawable).getWrappedDrawable());
        } else {
            if (drawable instanceof DrawableWrapper) {
                return canSafelyMutateDrawable(((DrawableWrapper) drawable).getWrappedDrawable());
            }
            if (drawable instanceof ScaleDrawable) {
                return canSafelyMutateDrawable(((ScaleDrawable) drawable).getDrawable());
            }
        }
        return true;
    }

    private static void fixVectorDrawableTinting(Drawable drawable) {
        int[] state = drawable.getState();
        if (state == null || state.length == 0) {
            drawable.setState(ThemeUtils.CHECKED_STATE_SET);
        } else {
            drawable.setState(ThemeUtils.EMPTY_STATE_SET);
        }
        drawable.setState(state);
    }

    public static Mode parseTintMode(int i, Mode mode) {
        if (i == 3) {
            return Mode.SRC_OVER;
        }
        if (i == 5) {
            return Mode.SRC_IN;
        }
        if (i == 9) {
            return Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return Mode.MULTIPLY;
            case 15:
                return Mode.SCREEN;
            case 16:
                return Mode.ADD;
            default:
                return mode;
        }
    }
}
