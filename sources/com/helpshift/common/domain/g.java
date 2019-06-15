package com.helpshift.common.domain;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class g implements ThreadFactory {
    private final String a;
    private final AtomicInteger b = new AtomicInteger(1);

    public g(String str) {
        this.a = str;
    }

    public Thread newThread(Runnable runnable) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HS-");
        stringBuilder.append(this.a);
        stringBuilder.append("-t-");
        stringBuilder.append(this.b.getAndIncrement());
        return new Thread(runnable, stringBuilder.toString());
    }
}
