package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

public final class zzqh {
    private final int height;
    private final int width;
    public final int zzakx;
    public final float zzbhq;
    public final List<byte[]> zzzl;

    public static zzqh zzg(zzpx zzpx) throws zzfx {
        try {
            zzpx.zzbl(4);
            int readUnsignedByte = (zzpx.readUnsignedByte() & 3) + 1;
            if (readUnsignedByte == 3) {
                throw new IllegalStateException();
            }
            int i;
            int i2;
            float f;
            int i3;
            ArrayList arrayList = new ArrayList();
            int readUnsignedByte2 = zzpx.readUnsignedByte() & 31;
            for (i = 0; i < readUnsignedByte2; i++) {
                arrayList.add(zzh(zzpx));
            }
            i = zzpx.readUnsignedByte();
            for (i2 = 0; i2 < i; i2++) {
                arrayList.add(zzh(zzpx));
            }
            if (readUnsignedByte2 > 0) {
                zzpv zzd = zzpu.zzd((byte[]) arrayList.get(0), readUnsignedByte, ((byte[]) arrayList.get(0)).length);
                readUnsignedByte2 = zzd.width;
                int i4 = zzd.height;
                f = zzd.zzbhq;
                i2 = readUnsignedByte2;
                i3 = i4;
            } else {
                f = 1.0f;
                i2 = -1;
                i3 = i2;
            }
            return new zzqh(arrayList, readUnsignedByte, i2, i3, f);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new zzfx("Error parsing AVC config", e);
        }
    }

    private zzqh(List<byte[]> list, int i, int i2, int i3, float f) {
        this.zzzl = list;
        this.zzakx = i;
        this.width = i2;
        this.height = i3;
        this.zzbhq = f;
    }

    private static byte[] zzh(zzpx zzpx) {
        int readUnsignedShort = zzpx.readUnsignedShort();
        int position = zzpx.getPosition();
        zzpx.zzbl(readUnsignedShort);
        return zzpp.zzc(zzpx.data, position, readUnsignedShort);
    }
}
