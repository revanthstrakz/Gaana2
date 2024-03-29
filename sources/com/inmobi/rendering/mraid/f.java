package com.inmobi.rendering.mraid;

import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.inmobi.commons.core.utilities.b.c;
import com.inmobi.rendering.CustomView;
import com.inmobi.rendering.RenderView;

public final class f {
    private static final String d = "f";
    public RenderView a;
    public ViewGroup b;
    public int c;

    public f(RenderView renderView) {
        this.a = renderView;
    }

    public final void a() {
        if (this.b == null) {
            this.b = (ViewGroup) this.a.getParent();
            this.c = this.b.indexOfChild(this.a);
        }
        h resizeProperties = this.a.getResizeProperties();
        FrameLayout frameLayout = new FrameLayout(this.a.getContainerContext());
        LayoutParams layoutParams = new LayoutParams(this.a.getWidth(), this.a.getHeight());
        frameLayout.setId(SupportMenu.USER_MASK);
        this.b.addView(frameLayout, this.c, layoutParams);
        this.b.removeView(this.a);
        float f = c.a().c;
        int i = (int) ((((float) resizeProperties.b) * f) + 0.5f);
        int i2 = (int) ((((float) resizeProperties.c) * f) + 0.5f);
        FrameLayout frameLayout2 = (FrameLayout) this.b.getRootView().findViewById(16908290);
        FrameLayout frameLayout3 = new FrameLayout(this.a.getContainerContext());
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        RelativeLayout relativeLayout = new RelativeLayout(this.a.getContainerContext());
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(i, i2);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(i, i2);
        frameLayout3.setId(65534);
        if (this.a.getParent() != null) {
            ((ViewGroup) this.a.getParent()).removeAllViews();
        }
        relativeLayout.addView(this.a, layoutParams4);
        String str = resizeProperties.a;
        float f2 = c.a().c;
        CustomView customView = new CustomView(this.a.getContainerContext(), f2, 1);
        customView.setId(65531);
        customView.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                f.this.a.b();
            }
        });
        if (str == null || str.length() == 0) {
            str = "top-right";
        } else if (!(str.equals("top-left") || str.equals("top-right") || str.equals("bottom-left") || str.equals("bottom-right") || str.equals("top-center") || str.equals("bottom-center") || str.equals("center"))) {
            str = "top-right";
        }
        i = (int) (50.0f * f2);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(i, i);
        if (str.equals("top-right") || str.equals("bottom-right")) {
            layoutParams5.addRule(11);
        }
        if (str.equals("bottom-right") || str.equals("bottom-left") || str.equals("bottom-center")) {
            layoutParams5.addRule(12);
            layoutParams5.addRule(4);
        }
        if (str.equals("bottom-center") || str.equals("top-center") || str.equals("center")) {
            layoutParams5.addRule(13);
        }
        if (str.equals("top-center")) {
            layoutParams5.addRule(10);
        }
        relativeLayout.addView(customView, layoutParams5);
        frameLayout3.addView(relativeLayout, layoutParams3);
        frameLayout2.addView(frameLayout3, layoutParams2);
        f = c.a().c;
        i = (int) ((((float) resizeProperties.b) * f) + 0.5f);
        int i3 = (int) ((((float) resizeProperties.c) * f) + 0.5f);
        int i4 = (int) ((((float) resizeProperties.d) * f) + 0.5f);
        i2 = (int) ((((float) resizeProperties.e) * f) + 0.5f);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        this.b.getLocationOnScreen(iArr);
        frameLayout2.getLocationOnScreen(iArr2);
        iArr[1] = iArr[1] - iArr2[1];
        iArr[0] = iArr[0] - iArr2[0];
        iArr[0] = iArr[0] + i4;
        iArr[1] = iArr[1] + i2;
        if (!resizeProperties.f) {
            if (i > frameLayout2.getWidth() - iArr[0]) {
                iArr[0] = frameLayout2.getWidth() - i;
            }
            if (i3 > frameLayout2.getHeight() - iArr[1]) {
                iArr[1] = frameLayout2.getHeight() - i3;
            }
            if (iArr[0] < 0) {
                iArr[0] = 0;
            }
            if (iArr[1] < 0) {
                iArr[1] = 0;
            }
        }
        FrameLayout.LayoutParams layoutParams6 = new FrameLayout.LayoutParams(i, i3);
        layoutParams6.leftMargin = iArr[0];
        layoutParams6.topMargin = iArr[1];
        layoutParams6.gravity = 8388611;
        frameLayout3.setLayoutParams(layoutParams6);
        frameLayout3.setBackgroundColor(0);
    }
}
