package com.facebook.ads.internal.view.a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.s.b.b;
import com.facebook.ads.internal.s.b.c;

public class a extends RelativeLayout {
    private static final int a = ((int) (y.b * 16.0f));
    private static final int b = ((int) (12.0f * y.b));
    private static final int c = ((int) (8.0f * y.b));
    private static final int d = ((int) (44.0f * y.b));
    private static final int e = ((int) (10.0f * y.b));
    private static final int f = (a - e);
    private static final int g = ((int) (75.0f * y.b));
    private static final int h = ((int) (25.0f * y.b));
    private static final int i = ((int) (16.0f * y.b));
    private final c j;

    public static class a {
        private Context a;
        private c b;
        private String c;
        private String d;
        private String e;
        private b f;
        private int g;

        public a(Context context, c cVar) {
            this.a = context;
            this.b = cVar;
        }

        public a a(int i) {
            this.g = i;
            return this;
        }

        public a a(b bVar) {
            this.f = bVar;
            return this;
        }

        public a a(String str) {
            this.c = str;
            return this;
        }

        public a a() {
            return new a(this, null);
        }

        public a b(String str) {
            this.d = str;
            return this;
        }

        public a c(String str) {
            this.e = str;
            return this;
        }
    }

    private a(a aVar) {
        super(aVar.a);
        this.j = aVar.b;
        y.a((View) this, -1);
        setClickable(true);
        View headerView = getHeaderView();
        View a = a(aVar);
        View footerView = getFooterView();
        y.a(headerView);
        y.a(a);
        y.a(footerView);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(10);
        LayoutParams layoutParams2 = new LayoutParams(-1, -1);
        layoutParams2.addRule(13);
        layoutParams2.addRule(3, headerView.getId());
        layoutParams2.addRule(2, footerView.getId());
        LayoutParams layoutParams3 = new LayoutParams(-1, -2);
        layoutParams3.addRule(12);
        layoutParams3.setMargins(a, 0, a, a);
        addView(headerView, layoutParams);
        addView(a, layoutParams2);
        addView(footerView, layoutParams3);
    }

    /* synthetic */ a(a aVar, AnonymousClass1 anonymousClass1) {
        this(aVar);
    }

    private View a(a aVar) {
        View imageView = new ImageView(getContext());
        imageView.setPadding(h, h, h, h);
        imageView.setImageBitmap(c.a(aVar.f));
        imageView.setColorFilter(-1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(g, g);
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(aVar.g);
        y.a(imageView, gradientDrawable);
        layoutParams.gravity = 17;
        layoutParams.setMargins(a, 0, a, a);
        TextView textView = new TextView(getContext());
        y.a(textView, true, 20);
        textView.setTextColor(-14934495);
        textView.setText(aVar.c);
        textView.setGravity(17);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(a, 0, a, a);
        TextView textView2 = new TextView(getContext());
        y.a(textView2, false, 16);
        textView2.setTextColor(-10459280);
        textView2.setText(aVar.d);
        textView2.setGravity(17);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams3.setMargins(a, 0, a, a);
        ImageView imageView2 = new ImageView(getContext());
        imageView2.setImageBitmap(c.a(b.CHECKMARK));
        imageView2.setColorFilter(-1);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(i, i);
        layoutParams4.gravity = 17;
        TextView textView3 = new TextView(getContext());
        textView3.setText(aVar.e);
        textView3.setPadding(c, 0, 0, 0);
        textView3.setTextColor(-1);
        y.a(textView3, false, 16);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.setPadding(a, b, a, b);
        gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(-13272859);
        gradientDrawable.setCornerRadius(100.0f);
        y.a(linearLayout, gradientDrawable);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams6.gravity = 17;
        layoutParams6.setMargins(a, 0, a, 0);
        linearLayout.addView(imageView2, layoutParams4);
        linearLayout.addView(textView3, layoutParams5);
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setOrientation(1);
        linearLayout2.setGravity(17);
        linearLayout2.addView(imageView, layoutParams);
        linearLayout2.addView(textView, layoutParams2);
        linearLayout2.addView(textView2, layoutParams3);
        linearLayout2.addView(linearLayout, layoutParams5);
        return linearLayout2;
    }

    private View getFooterView() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(c.a(b.SETTINGS));
        imageView.setColorFilter(-13272859);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
        layoutParams.gravity = 17;
        TextView textView = new TextView(getContext());
        y.a(textView, false, 16);
        textView.setTextColor(-13272859);
        textView.setPadding(c, c, c, c);
        textView.setText(com.facebook.ads.internal.c.a.i(getContext()));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        linearLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                a.this.j.b();
            }
        });
        linearLayout.addView(imageView, layoutParams);
        linearLayout.addView(textView, layoutParams2);
        return linearLayout;
    }

    private View getHeaderView() {
        ImageView imageView = new ImageView(getContext());
        imageView.setPadding(e, e, e, e);
        imageView.setScaleType(ScaleType.FIT_CENTER);
        imageView.setImageBitmap(c.a(b.INTERSTITIAL_CLOSE));
        imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                a.this.j.a();
            }
        });
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(d, d);
        layoutParams.setMargins(f, f, f, f);
        linearLayout.addView(imageView, layoutParams);
        return linearLayout;
    }
}
