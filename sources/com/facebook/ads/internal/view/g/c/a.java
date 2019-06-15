package com.facebook.ads.internal.view.g.c;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.s.b.b;
import com.facebook.ads.internal.s.c.g;
import com.facebook.ads.internal.view.g.a.c;

public class a extends c {
    private final a a;

    public static class a extends RelativeLayout {
        private final String a;
        private final String b;
        private final String c;
        private final DisplayMetrics d;
        private ImageView e;
        private TextView f;
        private boolean g = false;

        public a(Context context, String str, String str2, float[] fArr, String str3) {
            super(context);
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = context.getResources().getDisplayMetrics();
            Drawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(ViewCompat.MEASURED_STATE_MASK);
            gradientDrawable.setAlpha(178);
            gradientDrawable.setCornerRadii(new float[]{fArr[0] * this.d.density, fArr[0] * this.d.density, fArr[1] * this.d.density, fArr[1] * this.d.density, fArr[2] * this.d.density, fArr[2] * this.d.density, fArr[3] * this.d.density, fArr[3] * this.d.density});
            y.a((View) this, gradientDrawable);
            a();
            b();
            c();
            setMinimumWidth(Math.round(20.0f * this.d.density));
            setMinimumHeight(Math.round(18.0f * this.d.density));
        }

        private void a() {
            setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 0) {
                        return false;
                    }
                    if (!a.this.g) {
                        a.this.d();
                    } else if (!TextUtils.isEmpty(a.this.b)) {
                        g.a(new g(), a.this.getContext(), Uri.parse(a.this.b), a.this.c);
                    }
                    return true;
                }
            });
        }

        private void b() {
            this.e = new ImageView(getContext());
            this.e.setImageBitmap(com.facebook.ads.internal.s.b.c.a(b.IC_AD_CHOICES));
            addView(this.e);
            LayoutParams layoutParams = new LayoutParams(Math.round(this.d.density * 16.0f), Math.round(16.0f * this.d.density));
            layoutParams.addRule(9);
            layoutParams.addRule(15, -1);
            layoutParams.setMargins(Math.round(4.0f * this.d.density), Math.round(this.d.density * 2.0f), Math.round(this.d.density * 2.0f), Math.round(2.0f * this.d.density));
            this.e.setLayoutParams(layoutParams);
        }

        private void c() {
            this.f = new TextView(getContext());
            addView(this.f);
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.width = 0;
            layoutParams.leftMargin = (int) (20.0f * this.d.density);
            layoutParams.addRule(9);
            layoutParams.addRule(15, -1);
            this.f.setLayoutParams(layoutParams);
            this.f.setSingleLine();
            this.f.setText(this.a);
            this.f.setTextSize(10.0f);
            this.f.setTextColor(-4341303);
        }

        private void d() {
            Paint paint = new Paint();
            paint.setTextSize(this.f.getTextSize());
            int round = Math.round(paint.measureText(this.a) + (4.0f * this.d.density));
            final int width = getWidth();
            round += width;
            this.g = true;
            AnonymousClass2 anonymousClass2 = new Animation() {
                /* Access modifiers changed, original: protected */
                public void applyTransformation(float f, Transformation transformation) {
                    int i = (int) (((float) width) + (((float) (round - width)) * f));
                    a.this.getLayoutParams().width = i;
                    a.this.requestLayout();
                    a.this.f.getLayoutParams().width = i - width;
                    a.this.f.requestLayout();
                }

                public boolean willChangeBounds() {
                    return true;
                }
            };
            anonymousClass2.setAnimationListener(new AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            if (a.this.g) {
                                a.this.g = false;
                                AnonymousClass1 anonymousClass1 = new Animation() {
                                    /* Access modifiers changed, original: protected */
                                    public void applyTransformation(float f, Transformation transformation) {
                                        int i = (int) (((float) round) + (((float) (width - round)) * f));
                                        a.this.getLayoutParams().width = i;
                                        a.this.requestLayout();
                                        a.this.f.getLayoutParams().width = i - width;
                                        a.this.f.requestLayout();
                                    }

                                    public boolean willChangeBounds() {
                                        return true;
                                    }
                                };
                                anonymousClass1.setDuration(300);
                                anonymousClass1.setFillAfter(true);
                                a.this.startAnimation(anonymousClass1);
                            }
                        }
                    }, 3000);
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            });
            anonymousClass2.setDuration(300);
            anonymousClass2.setFillAfter(true);
            startAnimation(anonymousClass2);
        }
    }

    public a(Context context, String str, String str2, float[] fArr) {
        super(context);
        this.a = new a(context, "AdChoices", str, fArr, str2);
        addView(this.a);
    }
}
