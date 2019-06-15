package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;

public final class zzqn {
    public final int zzakx;
    public final List<byte[]> zzzl;

    public static zzqn zzi(zzpx zzpx) throws zzfx {
        try {
            int i;
            int readUnsignedShort;
            List list;
            zzpx.zzbl(21);
            int readUnsignedByte = zzpx.readUnsignedByte() & 3;
            int readUnsignedByte2 = zzpx.readUnsignedByte();
            int position = zzpx.getPosition();
            int i2 = 0;
            int i3 = i2;
            while (i2 < readUnsignedByte2) {
                zzpx.zzbl(1);
                int readUnsignedShort2 = zzpx.readUnsignedShort();
                i = i3;
                for (i3 = 0; i3 < readUnsignedShort2; i3++) {
                    readUnsignedShort = zzpx.readUnsignedShort();
                    i += readUnsignedShort + 4;
                    zzpx.zzbl(readUnsignedShort);
                }
                i2++;
                i3 = i;
            }
            zzpx.setPosition(position);
            byte[] bArr = new byte[i3];
            i2 = 0;
            i = i2;
            while (i2 < readUnsignedByte2) {
                zzpx.zzbl(1);
                readUnsignedShort = zzpx.readUnsignedShort();
                int i4 = i;
                for (i = 0; i < readUnsignedShort; i++) {
                    int readUnsignedShort3 = zzpx.readUnsignedShort();
                    System.arraycopy(zzpu.zzbhi, 0, bArr, i4, zzpu.zzbhi.length);
                    i4 += zzpu.zzbhi.length;
                    System.arraycopy(zzpx.data, zzpx.getPosition(), bArr, i4, readUnsignedShort3);
                    i4 += readUnsignedShort3;
                    zzpx.zzbl(readUnsignedShort3);
                }
                i2++;
                i = i4;
            }
            if (i3 == 0) {
                list = null;
            } else {
                list = Collections.singletonList(bArr);
            }
            return new zzqn(list, readUnsignedByte + 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new zzfx("Error parsing HEVC config", e);
        }
    }

    private zzqn(List<byte[]> list, int i) {
        this.zzzl = list;
        this.zzakx = i;
    }
}
