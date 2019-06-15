package com.google.android.gms.internal.ads;

import android.util.Log;

public final class zzbwb extends zzbwg {
    private String name;

    public zzbwb(String str) {
        this.name = str;
    }

    public final void zzge(String str) {
        String str2 = this.name;
        StringBuilder stringBuilder = new StringBuilder((1 + String.valueOf(str2).length()) + String.valueOf(str).length());
        stringBuilder.append(str2);
        stringBuilder.append(":");
        stringBuilder.append(str);
        Log.d("isoparser", stringBuilder.toString());
    }
}
