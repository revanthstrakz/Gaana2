package com.b;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.managers.p;

public final class f {
    public static final int[] a = new int[]{16842804};
    private static Boolean b;
    private static Boolean c;

    public static CharSequence a(CharSequence charSequence, Typeface typeface) {
        if (charSequence != null && charSequence.length() > 0) {
            if (!(charSequence instanceof Spannable)) {
                charSequence = new SpannableString(charSequence);
            }
            ((Spannable) charSequence).setSpan(i.a(typeface), 0, charSequence.length(), 33);
        }
        return charSequence;
    }

    public static boolean a(TextView textView, final Typeface typeface, boolean z) {
        if (textView == null || typeface == null) {
            return false;
        }
        textView.setPaintFlags((textView.getPaintFlags() | 128) | 1);
        textView.setTypeface(typeface);
        if (z) {
            textView.setText(a(textView.getText(), typeface), BufferType.SPANNABLE);
            textView.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void afterTextChanged(Editable editable) {
                    f.a(editable, typeface);
                }
            });
        }
        return true;
    }

    static boolean a(Context context, TextView textView, String str, boolean z) {
        if (textView == null || context == null) {
            return false;
        }
        Typeface a = i.a(context.getAssets(), str);
        if (a == null && !str.isEmpty()) {
            a = p.a().a(str);
        }
        return a(textView, a, z);
    }

    /* JADX WARNING: Missing block: B:8:0x0016, code skipped:
            return;
     */
    static void a(android.content.Context r1, android.widget.TextView r2, com.b.a r3, boolean r4) {
        /*
        if (r1 == 0) goto L_0x0016;
    L_0x0002:
        if (r2 == 0) goto L_0x0016;
    L_0x0004:
        if (r3 != 0) goto L_0x0007;
    L_0x0006:
        goto L_0x0016;
    L_0x0007:
        r0 = r3.c();
        if (r0 != 0) goto L_0x000e;
    L_0x000d:
        return;
    L_0x000e:
        r3 = r3.b();
        a(r1, r2, r3, r4);
        return;
    L_0x0016:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.f.a(android.content.Context, android.widget.TextView, com.b.a, boolean):void");
    }

    static void a(Context context, TextView textView, a aVar, String str, boolean z) {
        if (context != null && textView != null && aVar != null) {
            if (TextUtils.isEmpty(str) || !a(context, textView, str, z)) {
                a(context, textView, aVar, z);
            }
        }
    }

    static String a(Context context, AttributeSet attributeSet, int[] iArr) {
        if (iArr == null || attributeSet == null) {
            return null;
        }
        try {
            String string;
            String resourceEntryName = context.getResources().getResourceEntryName(iArr[0]);
            int attributeResourceValue = attributeSet.getAttributeResourceValue(null, resourceEntryName, -1);
            if (attributeResourceValue > 0) {
                string = context.getString(attributeResourceValue);
            } else {
                string = attributeSet.getAttributeValue(null, resourceEntryName);
            }
            return string;
        } catch (NotFoundException unused) {
            return null;
        }
    }

    static String b(Context context, AttributeSet attributeSet, int[] iArr) {
        if (iArr == null || attributeSet == null) {
            return null;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        if (obtainStyledAttributes != null) {
            try {
                String string = obtainStyledAttributes.getString(0);
                if (!TextUtils.isEmpty(string)) {
                    obtainStyledAttributes.recycle();
                    return string;
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
            }
            obtainStyledAttributes.recycle();
        }
        return null;
    }

    static String c(Context context, AttributeSet attributeSet, int[] iArr) {
        if (iArr == null || attributeSet == null) {
            return null;
        }
        String obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a);
        int i = -1;
        if (obtainStyledAttributes != null) {
            try {
                i = obtainStyledAttributes.getResourceId(0, -1);
            } catch (Exception unused) {
                return null;
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(i, iArr);
        if (obtainStyledAttributes2 == null) {
            return null;
        }
        try {
            obtainStyledAttributes = obtainStyledAttributes2.getString(0);
            return obtainStyledAttributes;
        } catch (Exception unused2) {
            return null;
        } finally {
            obtainStyledAttributes2.recycle();
        }
    }

    static String a(Context context, int i, int[] iArr) {
        if (i == -1 || iArr == null) {
            return null;
        }
        Theme theme = context.getTheme();
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(i, typedValue, true);
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(typedValue.resourceId, iArr);
        String str = 0;
        try {
            str = obtainStyledAttributes.getString(0);
            return str;
        } catch (Exception unused) {
            return null;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    static String a(Context context, int i, int i2, int[] iArr) {
        if (i == -1 || iArr == null) {
            return null;
        }
        Theme theme = context.getTheme();
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(i, typedValue, true);
        String obtainStyledAttributes = theme.obtainStyledAttributes(typedValue.resourceId, new int[]{i2});
        try {
            i2 = obtainStyledAttributes.getResourceId(0, -1);
            if (i2 == -1) {
                return null;
            }
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(i2, iArr);
            if (obtainStyledAttributes2 == null) {
                return null;
            }
            try {
                obtainStyledAttributes = obtainStyledAttributes2.getString(0);
                return obtainStyledAttributes;
            } catch (Exception unused) {
                return null;
            } finally {
                obtainStyledAttributes2.recycle();
            }
        } catch (Exception unused2) {
            return null;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    static boolean a() {
        if (b == null) {
            try {
                Class.forName("android.support.v7.widget.Toolbar");
                b = Boolean.TRUE;
            } catch (ClassNotFoundException unused) {
                b = Boolean.FALSE;
            }
        }
        return b.booleanValue();
    }

    static boolean b() {
        if (c == null) {
            try {
                Class.forName("android.support.v7.widget.AppCompatTextView");
                c = Boolean.TRUE;
            } catch (ClassNotFoundException unused) {
                c = Boolean.FALSE;
            }
        }
        return c.booleanValue();
    }
}
