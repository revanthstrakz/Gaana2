package com.b;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;
import com.gaana.R;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

class c {
    private final int[] a;

    private static class a implements OnGlobalLayoutListener {
        static String a = " ";
        private final WeakReference<c> b;
        private final WeakReference<Context> c;
        private final WeakReference<Toolbar> d;
        private final CharSequence e;

        private a(c cVar, Context context, Toolbar toolbar) {
            this.b = new WeakReference(cVar);
            this.c = new WeakReference(context);
            this.d = new WeakReference(toolbar);
            this.e = toolbar.getSubtitle();
            toolbar.setSubtitle(a);
        }

        @TargetApi(16)
        public void onGlobalLayout() {
            Toolbar toolbar = (Toolbar) this.d.get();
            Context context = (Context) this.c.get();
            c cVar = (c) this.b.get();
            if (toolbar != null) {
                if (cVar == null || context == null) {
                    a(toolbar);
                    return;
                }
                int childCount = toolbar.getChildCount();
                if (childCount != 0) {
                    for (int i = 0; i < childCount; i++) {
                        cVar.a(toolbar.getChildAt(i), context, null);
                    }
                }
                a(toolbar);
                toolbar.setSubtitle(this.e);
            }
        }

        private void a(Toolbar toolbar) {
            if (VERSION.SDK_INT < 16) {
                toolbar.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            } else {
                toolbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
    }

    protected static int[] a(TextView textView) {
        int[] iArr = new int[]{-1, -1};
        if (b(textView)) {
            iArr[0] = 16843470;
            iArr[1] = 16843512;
        } else if (c(textView)) {
            iArr[0] = 16843470;
            iArr[1] = 16843513;
        }
        if (iArr[0] == -1) {
            iArr[0] = a.a().g().containsKey(textView.getClass()) ? ((Integer) a.a().g().get(textView.getClass())).intValue() : 16842804;
        }
        return iArr;
    }

    @SuppressLint({"NewApi"})
    protected static boolean b(TextView textView) {
        if (a((View) textView, "action_bar_title")) {
            return true;
        }
        return a((View) textView) ? TextUtils.equals(((Toolbar) textView.getParent()).getTitle(), textView.getText()) : false;
    }

    @SuppressLint({"NewApi"})
    protected static boolean c(TextView textView) {
        if (a((View) textView, "action_bar_subtitle")) {
            return true;
        }
        return a((View) textView) ? TextUtils.equals(((Toolbar) textView.getParent()).getSubtitle(), textView.getText()) : false;
    }

    protected static boolean a(View view) {
        return f.a() && view.getParent() != null && (view.getParent() instanceof Toolbar);
    }

    protected static boolean a(View view, String str) {
        if (view.getId() == -1) {
            return false;
        }
        return view.getResources().getResourceEntryName(view.getId()).equalsIgnoreCase(str);
    }

    public c(int i) {
        this.a = new int[]{i};
    }

    public View a(View view, Context context, AttributeSet attributeSet) {
        if (!(view == null || view.getTag(R.id.calligraphy_tag_id) == Boolean.TRUE)) {
            b(view, context, attributeSet);
            view.setTag(R.id.calligraphy_tag_id, Boolean.TRUE);
        }
        return view;
    }

    /* Access modifiers changed, original: 0000 */
    public void b(View view, Context context, AttributeSet attributeSet) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            if (!i.b(textView.getTypeface())) {
                String a = a(context, attributeSet);
                if (TextUtils.isEmpty(a)) {
                    int[] a2 = a(textView);
                    if (a2[1] != -1) {
                        a = f.a(context, a2[0], a2[1], this.a);
                    } else {
                        a = f.a(context, a2[0], this.a);
                    }
                }
                boolean z = a(view, "action_bar_title") || a(view, "action_bar_subtitle");
                f.a(context, textView, a.a(), a, z);
            } else {
                return;
            }
        }
        if (f.a() && (view instanceof Toolbar)) {
            Toolbar toolbar = (Toolbar) view;
            toolbar.getViewTreeObserver().addOnGlobalLayoutListener(new a(context, toolbar));
        }
        Typeface a3;
        if (view instanceof g) {
            a3 = a(context, a(context, attributeSet));
            if (a3 != null) {
                ((g) view).a(a3);
            }
        } else if (a.a().f() && a.a().a(view)) {
            Method b = h.b(view.getClass(), "setTypeface");
            a3 = a(context, a(context, attributeSet));
            if (!(b == null || a3 == null)) {
                h.a((Object) view, b, a3);
            }
        }
    }

    private Typeface a(Context context, String str) {
        CharSequence str2;
        if (TextUtils.isEmpty(str2)) {
            str2 = a.a().b();
        }
        return !TextUtils.isEmpty(str2) ? i.a(context.getAssets(), str2) : null;
    }

    private String a(Context context, AttributeSet attributeSet) {
        String a = f.a(context, attributeSet, this.a);
        if (TextUtils.isEmpty(a)) {
            a = f.b(context, attributeSet, this.a);
        }
        return TextUtils.isEmpty(a) ? f.c(context, attributeSet, this.a) : a;
    }
}
