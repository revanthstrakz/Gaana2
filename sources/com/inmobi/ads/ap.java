package com.inmobi.ads;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

final class ap extends PagerAdapter implements ax {
    private static final String a = "ap";
    private static Handler e = new Handler(Looper.getMainLooper());
    private boolean b;
    @NonNull
    private final ao c;
    private au d;
    @NonNull
    private SparseArray<Runnable> f = new SparseArray();

    ap(@NonNull ao aoVar, @NonNull au auVar) {
        this.c = aoVar;
        this.d = auVar;
    }

    public final int getItemPosition(Object obj) {
        obj = obj == null ? null : ((View) obj).getTag();
        return (obj == null || !(obj instanceof Integer)) ? -2 : ((Integer) obj).intValue();
    }

    public final int getCount() {
        return this.c.b();
    }

    public final boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view.equals(obj);
    }

    @TargetApi(21)
    public final Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
        ak a = this.c.a(i);
        if (a == null) {
            return null;
        }
        ViewGroup a2 = this.d.a(viewGroup, (am) a);
        int abs = Math.abs(this.d.b - i);
        final int i2 = i;
        final ViewGroup viewGroup2 = a2;
        final ViewGroup viewGroup3 = viewGroup;
        final am amVar = a;
        AnonymousClass2 anonymousClass2 = new Runnable() {
            public final void run() {
                if (!ap.this.b) {
                    ap.this.f.remove(i2);
                    ap.this.d.b(viewGroup2, amVar);
                }
            }
        };
        this.f.put(i, anonymousClass2);
        e.postDelayed(anonymousClass2, (long) (abs * 50));
        a2.setLayoutParams(NativeViewFactory.a(a, viewGroup));
        a2.setTag(Integer.valueOf(i));
        viewGroup.addView(a2);
        return a2;
    }

    public final void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull final Object obj) {
        viewGroup.removeView((View) obj);
        Runnable runnable = (Runnable) this.f.get(i);
        if (runnable != null) {
            e.removeCallbacks(runnable);
            NativeViewFactory.class.getSimpleName();
        }
        e.post(new Runnable() {
            public final void run() {
                View view = (View) obj;
                ap.this.d.d.a(view);
            }
        });
    }

    public final void destroy() {
        this.b = true;
        int size = this.f.size();
        for (int i = 0; i < size; i++) {
            e.removeCallbacks((Runnable) this.f.get(this.f.keyAt(i)));
        }
        this.f.clear();
    }
}
