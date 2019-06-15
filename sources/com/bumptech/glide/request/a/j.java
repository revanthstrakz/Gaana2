package com.bumptech.glide.request.a;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.WindowManager;
import com.bumptech.glide.f.h;
import com.bumptech.glide.request.c;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class j<T extends View, Z> extends a<Z> {
    private static boolean b = false;
    @Nullable
    private static Integer c;
    protected final T a;
    private final a d;

    @VisibleForTesting
    static final class a {
        @Nullable
        @VisibleForTesting
        static Integer a;
        private final View b;
        private final boolean c;
        private final List<h> d = new ArrayList();
        @Nullable
        private a e;

        private static final class a implements OnPreDrawListener {
            private final WeakReference<a> a;

            a(a aVar) {
                this.a = new WeakReference(aVar);
            }

            public boolean onPreDraw() {
                if (Log.isLoggable("ViewTarget", 2)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("OnGlobalLayoutListener called listener=");
                    stringBuilder.append(this);
                    Log.v("ViewTarget", stringBuilder.toString());
                }
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.a();
                }
                return true;
            }
        }

        private boolean a(int i) {
            return i > 0 || i == Integer.MIN_VALUE;
        }

        a(View view, boolean z) {
            this.b = view;
            this.c = z;
        }

        private static int a(Context context) {
            if (a == null) {
                Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getSize(point);
                a = Integer.valueOf(Math.max(point.x, point.y));
            }
            return a.intValue();
        }

        private void a(int i, int i2) {
            Iterator it = new ArrayList(this.d).iterator();
            while (it.hasNext()) {
                ((h) it.next()).a(i, i2);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void a() {
            if (!this.d.isEmpty()) {
                int d = d();
                int c = c();
                if (b(d, c)) {
                    a(d, c);
                    b();
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void a(h hVar) {
            int d = d();
            int c = c();
            if (b(d, c)) {
                hVar.a(d, c);
                return;
            }
            if (!this.d.contains(hVar)) {
                this.d.add(hVar);
            }
            if (this.e == null) {
                ViewTreeObserver viewTreeObserver = this.b.getViewTreeObserver();
                this.e = new a(this);
                viewTreeObserver.addOnPreDrawListener(this.e);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void b(h hVar) {
            this.d.remove(hVar);
        }

        /* Access modifiers changed, original: 0000 */
        public void b() {
            ViewTreeObserver viewTreeObserver = this.b.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.e);
            }
            this.e = null;
            this.d.clear();
        }

        private boolean b(int i, int i2) {
            return a(i) && a(i2);
        }

        private int c() {
            int paddingTop = this.b.getPaddingTop() + this.b.getPaddingBottom();
            LayoutParams layoutParams = this.b.getLayoutParams();
            return a(this.b.getHeight(), layoutParams != null ? layoutParams.height : 0, paddingTop);
        }

        private int d() {
            int paddingLeft = this.b.getPaddingLeft() + this.b.getPaddingRight();
            LayoutParams layoutParams = this.b.getLayoutParams();
            return a(this.b.getWidth(), layoutParams != null ? layoutParams.width : 0, paddingLeft);
        }

        private int a(int i, int i2, int i3) {
            int i4 = i2 - i3;
            if (i4 > 0) {
                return i4;
            }
            if (this.c && this.b.isLayoutRequested()) {
                return 0;
            }
            i -= i3;
            if (i > 0) {
                return i;
            }
            if (this.b.isLayoutRequested() || i2 != -2) {
                return 0;
            }
            if (Log.isLoggable("ViewTarget", 4)) {
                Log.i("ViewTarget", "Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's screen dimensions. If you want to load the original image and are ok with the corresponding memory cost and OOMs (depending on the input size), use .override(Target.SIZE_ORIGINAL). Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.");
            }
            return a(this.b.getContext());
        }
    }

    public j(T t) {
        this(t, false);
    }

    public j(T t, boolean z) {
        this.a = (View) h.a((Object) t);
        this.d = new a(t, z);
    }

    public void getSize(h hVar) {
        this.d.a(hVar);
    }

    public void removeCallback(h hVar) {
        this.d.b(hVar);
    }

    public void onLoadCleared(Drawable drawable) {
        super.onLoadCleared(drawable);
        this.d.b();
    }

    public void setRequest(@Nullable c cVar) {
        a((Object) cVar);
    }

    @Nullable
    public c getRequest() {
        Object b = b();
        if (b == null) {
            return null;
        }
        if (b instanceof c) {
            return (c) b;
        }
        throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Target for: ");
        stringBuilder.append(this.a);
        return stringBuilder.toString();
    }

    private void a(@Nullable Object obj) {
        if (c == null) {
            b = true;
            this.a.setTag(obj);
            return;
        }
        this.a.setTag(c.intValue(), obj);
    }

    @Nullable
    private Object b() {
        if (c == null) {
            return this.a.getTag();
        }
        return this.a.getTag(c.intValue());
    }

    public static void a(int i) {
        if (c != null || b) {
            throw new IllegalArgumentException("You cannot set the tag id more than once or change the tag id after the first request has been made");
        }
        c = Integer.valueOf(i);
    }
}
