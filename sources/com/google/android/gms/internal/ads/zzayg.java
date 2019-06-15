package com.google.android.gms.internal.ads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzayg implements ThreadFactory {
    private final AtomicInteger zzehf = new AtomicInteger(1);
    private final /* synthetic */ String zzelb;

    zzayg(String str) {
        this.zzelb = str;
    }

    public final Thread newThread(Runnable runnable) {
        String str = this.zzelb;
        int andIncrement = this.zzehf.getAndIncrement();
        StringBuilder stringBuilder = new StringBuilder(23 + String.valueOf(str).length());
        stringBuilder.append("AdWorker(");
        stringBuilder.append(str);
        stringBuilder.append(") #");
        stringBuilder.append(andIncrement);
        return new Thread(runnable, stringBuilder.toString());
    }
}
