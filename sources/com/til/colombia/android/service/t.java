package com.til.colombia.android.service;

import com.til.colombia.android.commons.USER_ACTION;

final class t implements Runnable {
    final /* synthetic */ s a;

    t(s sVar) {
        this.a = sVar;
    }

    public final void run() {
        this.a.a.c();
        this.a.a.i.onMediaItemClosed(this.a.a.d, USER_ACTION.AUTO_CLOSED);
    }
}
