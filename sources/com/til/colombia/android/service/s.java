package com.til.colombia.android.service;

import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.til.colombia.android.internal.a;
import com.til.colombia.android.network.l;
import com.til.colombia.android.vast.VastTrackingEvent;

final class s implements OnTouchListener {
    final /* synthetic */ q a;

    s(q qVar) {
        this.a = qVar;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.a.a = motionEvent.getX();
                this.a.b = motionEvent.getY();
                return true;
            case 1:
                float abs = Math.abs(motionEvent.getX() - this.a.a);
                float abs2 = Math.abs(motionEvent.getY() - this.a.b);
                if (abs <= 8.0f && abs2 <= 8.0f) {
                    if (this.a.e != null) {
                        this.a.e.handleClick(a.a(), this.a.e.getClickThroughUrl());
                        if (this.a.e.getClickTrackers() != null) {
                            for (VastTrackingEvent url : this.a.e.getClickTrackers()) {
                                l.a(url.getUrl(), 5, null);
                            }
                        }
                        bi.a();
                        bi.a(this.a.d);
                    } else {
                        bi.a();
                        bi.a(this.a.d, false);
                    }
                    this.a.i.onMediaItemClicked(this.a.d);
                    new Handler(Looper.getMainLooper()).postDelayed(new t(this), 1000);
                    break;
                }
            case 2:
                return true;
        }
        return false;
    }
}
