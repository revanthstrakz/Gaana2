package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzpj extends IOException {
    public zzpj(Throwable th) {
        String simpleName = th.getClass().getSimpleName();
        String message = th.getMessage();
        StringBuilder stringBuilder = new StringBuilder((13 + String.valueOf(simpleName).length()) + String.valueOf(message).length());
        stringBuilder.append("Unexpected ");
        stringBuilder.append(simpleName);
        stringBuilder.append(": ");
        stringBuilder.append(message);
        super(stringBuilder.toString(), th);
    }
}
