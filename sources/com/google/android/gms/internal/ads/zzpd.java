package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.Map;

public final class zzpd extends zzpc {
    public final int responseCode;
    private final Map<String, List<String>> zzbgs;

    public zzpd(int i, Map<String, List<String>> map, zzoz zzoz) {
        StringBuilder stringBuilder = new StringBuilder(26);
        stringBuilder.append("Response code: ");
        stringBuilder.append(i);
        super(stringBuilder.toString(), zzoz, 1);
        this.responseCode = i;
        this.zzbgs = map;
    }
}
