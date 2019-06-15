package com.facebook.ads.internal.view.e;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.a.j;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.c.e;
import com.facebook.ads.internal.view.component.f;
import com.facebook.ads.internal.view.component.i;
import com.facebook.internal.FacebookRequestErrorClassification;
import java.lang.ref.WeakReference;

public class a extends RelativeLayout {
    private static final int a = ((int) (72.0f * y.b));
    private static final int b = ((int) (y.b * 16.0f));
    private static final int c = ((int) (16.0f * y.b));
    private static final LayoutParams d = new LayoutParams(-1, -1);
    private final k e;
    private f f;
    private i g;
    private LinearLayout h;

    private static class a implements e {
        final WeakReference<ImageView> a;

        private a(ImageView imageView) {
            this.a = new WeakReference(imageView);
        }

        public void a(boolean z) {
            if (!z && this.a.get() != null) {
                ((ImageView) this.a.get()).setVisibility(8);
            }
        }
    }

    public a(Context context, k kVar) {
        super(context);
        this.e = kVar;
        a();
        b();
    }

    private void a() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setGravity(17);
        linearLayout.setOrientation(1);
        this.f = new f(getContext());
        y.a(this.f, 0);
        this.f.setRadius(50);
        new d(this.f).a().a(this.e.a().b());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
        this.g = new i(getContext(), this.e.d().a(), true, false, true);
        this.g.a(this.e.b().a(), this.e.b().b(), false, true);
        this.g.getDescriptionTextView().setAlpha(0.8f);
        this.g.setAlignment(17);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.setMargins(0, c, 0, c / 2);
        this.h = new LinearLayout(getContext());
        this.h.setGravity(17);
        this.h.setPadding(c, c / 2, c, c / 2);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.setMargins(0, c / 2, 0, 0);
        j j = this.e.e().j();
        TextView textView = new TextView(getContext());
        textView.setTextColor(-1);
        y.a(textView, false, 16);
        textView.setText(j.d());
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
        ImageView imageView = new ImageView(getContext());
        new d(imageView).a().a(new a(imageView)).a(j.b());
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(b, b);
        layoutParams5.setMargins(0, 0, c / 2, 0);
        this.h.addView(imageView, layoutParams5);
        this.h.addView(textView, layoutParams4);
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(100.0f);
        gradientDrawable.setColor(469762047);
        y.a(this.h, gradientDrawable);
        linearLayout.addView(this.f, layoutParams);
        linearLayout.addView(this.g, layoutParams2);
        linearLayout.addView(this.h, layoutParams3);
        y.a((View) this, -14473425);
        addView(linearLayout, d);
    }

    private void a(View view, int i) {
        view.setTranslationY((float) i);
        view.setScaleY(0.75f);
        view.setScaleX(0.75f);
        view.animate().translationYBy((float) (-i)).scaleX(1.0f).scaleY(1.0f).setDuration(200).setInterpolator(new DecelerateInterpolator(2.0f));
    }

    private void b() {
        a(this.f, 150);
        a(this.g, 170);
        a(this.h, FacebookRequestErrorClassification.EC_INVALID_TOKEN);
    }
}
