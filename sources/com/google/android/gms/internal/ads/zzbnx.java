package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

public final class zzbnx {
    public static byte[] zza(byte[]... bArr) throws GeneralSecurityException {
        int length = bArr.length;
        int i = 0;
        int i2 = i;
        while (i < length) {
            byte[] bArr2 = bArr[i];
            if (i2 > Integer.MAX_VALUE - bArr2.length) {
                throw new GeneralSecurityException("exceeded size limit");
            }
            i2 += bArr2.length;
            i++;
        }
        byte[] bArr3 = new byte[i2];
        i = bArr.length;
        i2 = 0;
        int i3 = i2;
        while (i2 < i) {
            Object obj = bArr[i2];
            System.arraycopy(obj, 0, bArr3, i3, obj.length);
            i3 += obj.length;
            i2++;
        }
        return bArr3;
    }

    public static final void zza(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i) {
        if (i < 0 || byteBuffer2.remaining() < i || byteBuffer3.remaining() < i || byteBuffer.remaining() < i) {
            throw new IllegalArgumentException("That combination of buffers, offsets and length to xor result in out-of-bond accesses.");
        }
        for (int i2 = 0; i2 < i; i2++) {
            byteBuffer.put((byte) (byteBuffer2.get() ^ byteBuffer3.get()));
        }
    }
}
