package com.til.colombia.android.service;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.til.colombia.android.internal.a;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.utils.c;

final class bg implements OnTouchListener {
    float a;
    float b;
    final /* synthetic */ ColombiaNativeSponsoredAdView c;
    private final float d = 8.0f;

    bg(ColombiaNativeSponsoredAdView colombiaNativeSponsoredAdView) {
        this.c = colombiaNativeSponsoredAdView;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.a = motionEvent.getX();
                this.b = motionEvent.getY();
                break;
            case 1:
                float abs = Math.abs(motionEvent.getX() - this.a);
                float abs2 = Math.abs(motionEvent.getY() - this.b);
                if (abs <= 8.0f && abs2 <= 8.0f && !h.a(a.o())) {
                    c.a(a.a(), ColombiaNativeSponsoredAdView.PREF_SPONSORED, "sponsored", true);
                    c.a(a.a(), ColombiaNativeSponsoredAdView.PREF_SPONSORED, "sponsoredTimestamp", System.currentTimeMillis());
                    break;
                }
            case 2:
                return true;
        }
        return false;
    }
}
