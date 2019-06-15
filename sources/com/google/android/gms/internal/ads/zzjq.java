package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.util.UUID;

public final class zzjq {
    public static byte[] zza(UUID uuid, byte[] bArr) {
        int length = 32 + bArr.length;
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.putInt(length);
        allocate.putInt(zziv.zzamu);
        allocate.putInt(0);
        allocate.putLong(uuid.getMostSignificantBits());
        allocate.putLong(uuid.getLeastSignificantBits());
        allocate.putInt(bArr.length);
        allocate.put(bArr);
        return allocate.array();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x007c A:{RETURN} */
    public static java.util.UUID zze(byte[] r9) {
        /*
        r0 = new com.google.android.gms.internal.ads.zzpx;
        r0.<init>(r9);
        r9 = r0.limit();
        r1 = 0;
        r2 = 32;
        if (r9 >= r2) goto L_0x0010;
    L_0x000e:
        r9 = r1;
        goto L_0x007a;
    L_0x0010:
        r9 = 0;
        r0.setPosition(r9);
        r2 = r0.readInt();
        r3 = r0.zzhb();
        r3 = r3 + 4;
        if (r2 == r3) goto L_0x0021;
    L_0x0020:
        goto L_0x000e;
    L_0x0021:
        r2 = r0.readInt();
        r3 = com.google.android.gms.internal.ads.zziv.zzamu;
        if (r2 == r3) goto L_0x002a;
    L_0x0029:
        goto L_0x000e;
    L_0x002a:
        r2 = r0.readInt();
        r2 = com.google.android.gms.internal.ads.zziv.zzaf(r2);
        r3 = 1;
        if (r2 <= r3) goto L_0x004e;
    L_0x0035:
        r9 = "PsshAtomUtil";
        r0 = 37;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r0);
        r0 = "Unsupported pssh version: ";
        r3.append(r0);
        r3.append(r2);
        r0 = r3.toString();
        android.util.Log.w(r9, r0);
        goto L_0x000e;
    L_0x004e:
        r4 = new java.util.UUID;
        r5 = r0.readLong();
        r7 = r0.readLong();
        r4.<init>(r5, r7);
        if (r2 != r3) goto L_0x0066;
    L_0x005d:
        r2 = r0.zzhg();
        r2 = r2 << 4;
        r0.zzbl(r2);
    L_0x0066:
        r2 = r0.zzhg();
        r3 = r0.zzhb();
        if (r2 == r3) goto L_0x0071;
    L_0x0070:
        goto L_0x000e;
    L_0x0071:
        r3 = new byte[r2];
        r0.zze(r3, r9, r2);
        r9 = android.util.Pair.create(r4, r3);
    L_0x007a:
        if (r9 != 0) goto L_0x007d;
    L_0x007c:
        return r1;
    L_0x007d:
        r9 = r9.first;
        r9 = (java.util.UUID) r9;
        return r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjq.zze(byte[]):java.util.UUID");
    }
}
