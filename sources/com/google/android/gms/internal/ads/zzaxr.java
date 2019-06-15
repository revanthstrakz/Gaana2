package com.google.android.gms.internal.ads;

import java.math.BigInteger;

@zzark
public final class zzaxr {
    private BigInteger zzejv = BigInteger.ONE;
    private String zzejw = "0";

    public final synchronized String zzyv() {
        String bigInteger;
        bigInteger = this.zzejv.toString();
        this.zzejv = this.zzejv.add(BigInteger.ONE);
        this.zzejw = bigInteger;
        return bigInteger;
    }

    public final synchronized String zzyw() {
        return this.zzejw;
    }
}
