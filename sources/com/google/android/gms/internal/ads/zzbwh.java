package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

public final class zzbwh {
    public static final zzbwh zzgda = new zzbwh(1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
    private static final zzbwh zzgdb = new zzbwh(0.0d, 1.0d, -1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
    private static final zzbwh zzgdc = new zzbwh(-1.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
    private static final zzbwh zzgdd = new zzbwh(0.0d, -1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double w;
    private final double zzgcw;
    private final double zzgcx;
    private final double zzgcy;
    private final double zzgcz;

    private zzbwh(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
        this.zzgcw = d5;
        this.zzgcx = d6;
        this.w = d7;
        this.a = d;
        this.b = d2;
        this.c = d3;
        this.d = d4;
        this.zzgcy = d8;
        this.zzgcz = d9;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzbwh zzbwh = (zzbwh) obj;
        return Double.compare(zzbwh.a, this.a) == 0 && Double.compare(zzbwh.b, this.b) == 0 && Double.compare(zzbwh.c, this.c) == 0 && Double.compare(zzbwh.d, this.d) == 0 && Double.compare(zzbwh.zzgcy, this.zzgcy) == 0 && Double.compare(zzbwh.zzgcz, this.zzgcz) == 0 && Double.compare(zzbwh.zzgcw, this.zzgcw) == 0 && Double.compare(zzbwh.zzgcx, this.zzgcx) == 0 && Double.compare(zzbwh.w, this.w) == 0;
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.zzgcw);
        int i = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        long doubleToLongBits2 = Double.doubleToLongBits(this.zzgcx);
        i = (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
        doubleToLongBits2 = Double.doubleToLongBits(this.w);
        i = (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
        doubleToLongBits2 = Double.doubleToLongBits(this.a);
        i = (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
        doubleToLongBits2 = Double.doubleToLongBits(this.b);
        i = (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
        doubleToLongBits2 = Double.doubleToLongBits(this.c);
        i = (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
        doubleToLongBits2 = Double.doubleToLongBits(this.d);
        i = (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
        doubleToLongBits2 = Double.doubleToLongBits(this.zzgcy);
        i = (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
        doubleToLongBits2 = Double.doubleToLongBits(this.zzgcz);
        return (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
    }

    public final String toString() {
        if (equals(zzgda)) {
            return "Rotate 0°";
        }
        if (equals(zzgdb)) {
            return "Rotate 90°";
        }
        if (equals(zzgdc)) {
            return "Rotate 180°";
        }
        if (equals(zzgdd)) {
            return "Rotate 270°";
        }
        double d = this.zzgcw;
        double d2 = this.zzgcx;
        double d3 = this.w;
        double d4 = this.a;
        double d5 = this.b;
        double d6 = this.c;
        double d7 = this.d;
        double d8 = this.zzgcy;
        double d9 = this.zzgcz;
        double d10 = d7;
        StringBuilder stringBuilder = new StringBuilder(CallbackHandler.MSG_ROUTE_VOLUME_CHANGED);
        stringBuilder.append("Matrix{u=");
        stringBuilder.append(d);
        stringBuilder.append(", v=");
        stringBuilder.append(d2);
        stringBuilder.append(", w=");
        stringBuilder.append(d3);
        stringBuilder.append(", a=");
        stringBuilder.append(d4);
        stringBuilder.append(", b=");
        stringBuilder.append(d5);
        stringBuilder.append(", c=");
        stringBuilder.append(d6);
        stringBuilder.append(", d=");
        stringBuilder.append(d10);
        stringBuilder.append(", tx=");
        stringBuilder.append(d8);
        stringBuilder.append(", ty=");
        stringBuilder.append(d9);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static zzbwh zzq(ByteBuffer byteBuffer) {
        double zzd = zzbb.zzd(byteBuffer);
        double zzd2 = zzbb.zzd(byteBuffer);
        double zze = zzbb.zze(byteBuffer);
        return new zzbwh(zzd, zzd2, zzbb.zzd(byteBuffer), zzbb.zzd(byteBuffer), zze, zzbb.zze(byteBuffer), zzbb.zze(byteBuffer), zzbb.zzd(byteBuffer), zzbb.zzd(byteBuffer));
    }
}
