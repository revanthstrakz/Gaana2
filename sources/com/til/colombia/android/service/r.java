package com.til.colombia.android.service;

import android.app.Activity;
import com.til.colombia.android.commons.USER_ACTION;
import com.til.colombia.android.internal.views.CloseableLayout.a;
import com.til.colombia.android.network.n;

final class r implements a {
    final /* synthetic */ q a;

    r(q qVar) {
        this.a = qVar;
    }

    public final void a() {
        synchronized (this) {
            this.a.g();
            if (this.a.getParent() != null) {
                ((Activity) this.a.c).getWindowManager().removeView(this.a);
            }
            n.a(this.a.f.getVastTrackingByType(8), 5, "audio skip tracked.");
            this.a.i.onMediaItemClosed(this.a.d, USER_ACTION.USER_CLOSED);
        }
    }
}
