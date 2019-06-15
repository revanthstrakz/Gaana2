package com.collapsible_header;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.fragments.ArtistDetailsMaterialListing;

public class SlidingTabLayout extends HorizontalScrollView {
    private int a;
    private int b;
    private int c;
    private boolean d;
    private ViewPager e;
    private SparseArray<String> f;
    private OnPageChangeListener g;
    private final h h;
    private b i;

    public interface b {
        void a(int i);
    }

    private class c implements OnClickListener {
        private c() {
        }

        public void onClick(View view) {
            for (int i = 0; i < SlidingTabLayout.this.h.getChildCount(); i++) {
                if (view == SlidingTabLayout.this.h.getChildAt(i)) {
                    SlidingTabLayout.this.e.setCurrentItem(i);
                    return;
                }
            }
        }
    }

    public interface d {
        int a(int i);
    }

    private class a implements OnPageChangeListener {
        private int b;

        private a() {
        }

        public void onPageScrolled(int i, float f, int i2) {
            int childCount = SlidingTabLayout.this.h.getChildCount();
            if (childCount != 0 && i >= 0 && i < childCount) {
                SlidingTabLayout.this.h.a(i, f);
                View childAt = SlidingTabLayout.this.h.getChildAt(i);
                SlidingTabLayout.this.a(i, childAt != null ? (int) (((float) childAt.getWidth()) * f) : 0);
                if (SlidingTabLayout.this.g != null) {
                    SlidingTabLayout.this.g.onPageScrolled(i, f, i2);
                }
            }
        }

        public void onPageScrollStateChanged(int i) {
            this.b = i;
            if (SlidingTabLayout.this.g != null) {
                SlidingTabLayout.this.g.onPageScrollStateChanged(i);
            }
        }

        public void onPageSelected(int i) {
            if (this.b == 0) {
                SlidingTabLayout.this.h.a(i, 0.0f);
                SlidingTabLayout.this.a(i, 0);
            }
            int i2 = 0;
            while (i2 < SlidingTabLayout.this.h.getChildCount()) {
                SlidingTabLayout.this.h.getChildAt(i2).setSelected(i == i2);
                i2++;
            }
            if (SlidingTabLayout.this.g != null) {
                SlidingTabLayout.this.g.onPageSelected(i);
            }
            if (SlidingTabLayout.this.i != null) {
                SlidingTabLayout.this.i.a(i);
            }
        }
    }

    public SlidingTabLayout(Context context) {
        this(context, null);
    }

    public SlidingTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = new SparseArray();
        setHorizontalScrollBarEnabled(false);
        setFillViewport(true);
        this.a = (int) (24.0f * getResources().getDisplayMetrics().density);
        this.h = new h(context);
        addView(this.h, -1, -2);
    }

    public void setScrolldListner(ArtistDetailsMaterialListing artistDetailsMaterialListing) {
        this.i = artistDetailsMaterialListing;
    }

    public void setCustomTabColorizer(d dVar) {
        this.h.a(dVar);
    }

    public void setDistributeEvenly(boolean z) {
        this.d = z;
    }

    public void setSelectedIndicatorColors(int... iArr) {
        this.h.a(iArr);
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.g = onPageChangeListener;
    }

    public void setCustomTabView(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    public void setViewPager(ViewPager viewPager) {
        this.h.removeAllViews();
        this.e = viewPager;
        if (viewPager != null) {
            viewPager.setOnPageChangeListener(new a());
            a();
        }
    }

    /* Access modifiers changed, original: protected */
    public TextView a(Context context) {
        TextView textView = new TextView(context);
        textView.setGravity(17);
        textView.setTextSize(2, 12.0f);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setLayoutParams(new LayoutParams(-2, -2));
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(16843534, typedValue, true);
        textView.setBackgroundResource(typedValue.resourceId);
        textView.setAllCaps(true);
        int i = (int) (16.0f * getResources().getDisplayMetrics().density);
        textView.setPadding(i, i, i, i);
        return textView;
    }

    private void a() {
        PagerAdapter adapter = this.e.getAdapter();
        c cVar = new c();
        for (int i = 0; i < adapter.getCount(); i++) {
            Object inflate;
            TextView textView;
            if (this.b != 0) {
                inflate = LayoutInflater.from(getContext()).inflate(this.b, this.h, false);
                textView = (TextView) inflate.findViewById(this.c);
            } else {
                inflate = null;
                textView = inflate;
            }
            if (inflate == null) {
                inflate = a(getContext());
            }
            if (textView == null && TextView.class.isInstance(inflate)) {
                textView = (TextView) inflate;
            }
            if (this.d) {
                LayoutParams layoutParams = (LayoutParams) inflate.getLayoutParams();
                layoutParams.width = 0;
                layoutParams.weight = 1.0f;
            }
            textView.setText(adapter.getPageTitle(i));
            inflate.setOnClickListener(cVar);
            String str = (String) this.f.get(i, null);
            if (str != null) {
                inflate.setContentDescription(str);
            }
            this.h.addView(inflate);
            if (i == this.e.getCurrentItem()) {
                inflate.setSelected(true);
            }
        }
    }

    public void setContentDescription(int i, String str) {
        this.f.put(i, str);
    }

    /* Access modifiers changed, original: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.e != null) {
            a(this.e.getCurrentItem(), 0);
        }
    }

    private void a(int i, int i2) {
        int childCount = this.h.getChildCount();
        if (childCount != 0 && i >= 0 && i < childCount) {
            View childAt = this.h.getChildAt(i);
            if (childAt != null) {
                childCount = childAt.getLeft() + i2;
                if (i > 0 || i2 > 0) {
                    childCount -= this.a;
                }
                scrollTo(childCount, 0);
            }
        }
    }
}
