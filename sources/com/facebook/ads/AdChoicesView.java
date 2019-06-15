package com.facebook.ads;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
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
import com.facebook.ads.internal.p.e;
import com.facebook.ads.internal.p.g;
import com.facebook.ads.internal.s.a.j;
import com.facebook.ads.internal.s.a.y;

public class AdChoicesView extends RelativeLayout {
    private final NativeAdBase a;
    private final float b;
    private boolean c;
    private TextView d;
    private String e;

    public AdChoicesView(Context context, NativeAdBase nativeAdBase) {
        this(context, nativeAdBase, false);
    }

    public AdChoicesView(Context context, final NativeAdBase nativeAdBase, boolean z) {
        super(context);
        boolean z2 = false;
        this.c = false;
        this.a = nativeAdBase;
        this.b = y.b;
        if (!this.a.isAdLoaded() || this.a.h().h()) {
            this.e = this.a.getAdChoicesText();
            if (TextUtils.isEmpty(this.e)) {
                this.e = "AdChoices";
            }
            g x = this.a.g().x();
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 0) {
                        return false;
                    }
                    if (!AdChoicesView.this.c) {
                        AdChoicesView.this.a();
                    } else if (!TextUtils.isEmpty(AdChoicesView.this.a.getAdChoicesLinkUrl())) {
                        com.facebook.ads.internal.s.c.g.a(new com.facebook.ads.internal.s.c.g(), AdChoicesView.this.getContext(), Uri.parse(AdChoicesView.this.a.getAdChoicesLinkUrl()), nativeAdBase.i());
                    }
                    return true;
                }
            });
            this.d = new TextView(getContext());
            addView(this.d);
            LayoutParams layoutParams2 = new LayoutParams(-2, -2);
            if (!z || x == null) {
                z2 = true;
            } else {
                layoutParams2.addRule(11, a(x).getId());
                layoutParams2.width = 0;
                layoutParams.width = Math.round(((float) (x.b() + 4)) * this.b);
                layoutParams.height = Math.round(((float) (x.c() + 2)) * this.b);
            }
            this.c = z2;
            setLayoutParams(layoutParams);
            layoutParams2.addRule(15, -1);
            this.d.setLayoutParams(layoutParams2);
            this.d.setSingleLine();
            this.d.setText(this.e);
            this.d.setTextSize(10.0f);
            this.d.setTextColor(-4341303);
            j.a(this, j.INTERNAL_AD_CHOICES_ICON);
            j.a(this.d, j.INTERNAL_AD_CHOICES_ICON);
            return;
        }
        setVisibility(8);
    }

    private ImageView a(g gVar) {
        ImageView imageView = new ImageView(getContext());
        addView(imageView);
        LayoutParams layoutParams = new LayoutParams(Math.round(((float) gVar.b()) * this.b), Math.round(((float) gVar.c()) * this.b));
        layoutParams.addRule(9);
        layoutParams.addRule(15, -1);
        layoutParams.setMargins(Math.round(4.0f * this.b), Math.round(this.b * 2.0f), Math.round(this.b * 2.0f), Math.round(2.0f * this.b));
        imageView.setLayoutParams(layoutParams);
        e.a(gVar, imageView);
        return imageView;
    }

    private void a() {
        Paint paint = new Paint();
        paint.setTextSize(this.d.getTextSize());
        int round = Math.round(paint.measureText(this.e) + (4.0f * this.b));
        final int width = getWidth();
        round += width;
        this.c = true;
        AnonymousClass2 anonymousClass2 = new Animation() {
            /* Access modifiers changed, original: protected */
            public void applyTransformation(float f, Transformation transformation) {
                int i = (int) (((float) width) + (((float) (round - width)) * f));
                AdChoicesView.this.getLayoutParams().width = i;
                AdChoicesView.this.requestLayout();
                AdChoicesView.this.d.getLayoutParams().width = i - width;
                AdChoicesView.this.d.requestLayout();
            }

            public boolean willChangeBounds() {
                return true;
            }
        };
        anonymousClass2.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (AdChoicesView.this.c) {
                            AdChoicesView.this.c = false;
                            AnonymousClass1 anonymousClass1 = new Animation() {
                                /* Access modifiers changed, original: protected */
                                public void applyTransformation(float f, Transformation transformation) {
                                    int i = (int) (((float) round) + (((float) (width - round)) * f));
                                    AdChoicesView.this.getLayoutParams().width = i;
                                    AdChoicesView.this.requestLayout();
                                    AdChoicesView.this.d.getLayoutParams().width = i - width;
                                    AdChoicesView.this.d.requestLayout();
                                }

                                public boolean willChangeBounds() {
                                    return true;
                                }
                            };
                            anonymousClass1.setDuration(300);
                            anonymousClass1.setFillAfter(true);
                            AdChoicesView.this.startAnimation(anonymousClass1);
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
