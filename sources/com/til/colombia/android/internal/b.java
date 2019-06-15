package com.til.colombia.android.internal;

import com.til.colombia.dmp.android.AdvertisingIDUtil;
import com.til.colombia.dmp.android.Utils;

final class b implements Runnable {
    b() {
    }

    public final void run() {
        AdvertisingIDUtil.retrieveAndSetAAID(a.a);
        a.a(Utils.getAAID(a.a), Utils.getLite());
    }
}
