package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

abstract class zzbpa implements zzbov {
    static final int[] zzfkg = zzm(ByteBuffer.wrap(new byte[]{(byte) 101, (byte) 120, (byte) 112, (byte) 97, (byte) 110, (byte) 100, (byte) 32, (byte) 51, (byte) 50, (byte) 45, (byte) 98, (byte) 121, (byte) 116, (byte) 101, (byte) 32, (byte) 107}));
    final zzbou zzfkh;
    private final int zzfki;

    zzbpa(byte[] bArr, int i) throws InvalidKeyException {
        if (bArr.length != 32) {
            throw new InvalidKeyException("The key length in bytes must be 32.");
        }
        this.zzfkh = zzbou.zzp(bArr);
        this.zzfki = i;
    }

    static int rotateLeft(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    public abstract int zzajy();

    public abstract ByteBuffer zzd(byte[] bArr, int i);

    public final byte[] zzn(byte[] bArr) throws GeneralSecurityException {
        int length = bArr.length;
        zzajy();
        if (length > 2147483635) {
            throw new GeneralSecurityException("plaintext too long");
        }
        ByteBuffer allocate = ByteBuffer.allocate(zzajy() + bArr.length);
        zza(allocate, bArr);
        return allocate.array();
    }

    /* Access modifiers changed, original: final */
    public final void zza(ByteBuffer byteBuffer, byte[] bArr) throws GeneralSecurityException {
        if (byteBuffer.remaining() - zzajy() < bArr.length) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        byte[] zzeg = zzboy.zzeg(zzajy());
        byteBuffer.put(zzeg);
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        int remaining = wrap.remaining();
        int i = (remaining / 64) + 1;
        for (int i2 = 0; i2 < i; i2++) {
            ByteBuffer zzd = zzd(zzeg, this.zzfki + i2);
            if (i2 == i - 1) {
                zzbnx.zza(byteBuffer, wrap, zzd, remaining % 64);
            } else {
                zzbnx.zza(byteBuffer, wrap, zzd, 64);
            }
        }
    }

    static int[] zzm(ByteBuffer byteBuffer) {
        IntBuffer asIntBuffer = byteBuffer.order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
        int[] iArr = new int[asIntBuffer.remaining()];
        asIntBuffer.get(iArr);
        return iArr;
    }
}
