package com.til.colombia.android.service;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class ax implements OnTouchListener {
    final /* synthetic */ ColombiaNativeAudioAdView a;

    ax(ColombiaNativeAudioAdView colombiaNativeAudioAdView) {
        this.a = colombiaNativeAudioAdView;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.a.downX = motionEvent.getX();
                this.a.downY = motionEvent.getY();
                return true;
            case 1:
                float abs = Math.abs(motionEvent.getX() - this.a.downX);
                float abs2 = Math.abs(motionEvent.getY() - this.a.downY);
                if (abs <= 8.0f && abs2 <= 8.0f) {
                    this.a.click();
                    break;
                }
            case 2:
                return true;
        }
        return false;
    }
}
