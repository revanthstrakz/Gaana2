package com.facebook.ads;

import android.content.Context;
import android.support.annotation.WorkerThread;
import com.facebook.ads.internal.k.c;

public final class BidderTokenProvider {
    @WorkerThread
    public static String getBidderToken(Context context) {
        return new c(context, true).a();
    }
}
