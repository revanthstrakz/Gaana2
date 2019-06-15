package com.til.colombia.android.service;

import com.til.colombia.android.commons.USER_ACTION;

final class u implements Runnable {
    final /* synthetic */ a a;

    u(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        q.this.c();
        q.this.i.onMediaItemClosed(q.this.d, USER_ACTION.AUTO_CLOSED);
    }
}
