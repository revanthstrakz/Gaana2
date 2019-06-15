package com.til.colombia.android.service;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.til.colombia.android.commons.USER_ACTION;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.network.l;
import com.til.colombia.android.vast.VastTrackingEvent;
import com.til.colombia.android.vast.h;

final class bu implements OnTouchListener {
    final /* synthetic */ String a;
    final /* synthetic */ boolean b;
    final /* synthetic */ InterstitialActivity c;

    bu(InterstitialActivity interstitialActivity, String str, boolean z) {
        this.c = interstitialActivity;
        this.a = str;
        this.b = z;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.c.b = motionEvent.getX();
                this.c.c = motionEvent.getY();
                return true;
            case 1:
                float abs = Math.abs(motionEvent.getX() - this.c.b);
                float abs2 = Math.abs(motionEvent.getY() - this.c.c);
                if (abs <= 8.0f && abs2 <= 8.0f) {
                    if (!this.a.equalsIgnoreCase(h.a)) {
                        if (this.a.equalsIgnoreCase(h.b) && this.b) {
                            try {
                                this.c.g.handleClick(a.a(), this.c.g.getClickThroughUrl());
                                if (this.c.g.getClickTrackers() != null) {
                                    for (VastTrackingEvent url : this.c.g.getClickTrackers()) {
                                        l.a(url.getUrl(), 5, null);
                                    }
                                }
                            } catch (Exception e) {
                                Log.a(i.f, "", e);
                            } catch (Throwable th) {
                                this.c.a(USER_ACTION.AUTO_CLOSED);
                            }
                            this.c.a(USER_ACTION.AUTO_CLOSED);
                            break;
                        }
                    }
                    try {
                        this.c.w.removeMessages(0);
                        bi.a();
                        bi.a(this.c.h);
                        this.c.a(ck.b, null);
                        this.c.d();
                        this.c.j();
                        break;
                    } catch (Exception e2) {
                        Log.a(i.f, "", e2);
                        break;
                    }
                }
            case 2:
                return true;
        }
        return false;
    }
}
