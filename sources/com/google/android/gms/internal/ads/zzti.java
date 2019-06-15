package com.google.android.gms.internal.ads;

import android.support.v4.internal.view.SupportMenu;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;

@zzark
public final class zzti extends zztd {
    private MessageDigest zzbze;

    public final byte[] zzay(String str) {
        byte[] array;
        String[] split = str.split(" ");
        int i = 4;
        int i2;
        if (split.length == 1) {
            int zzba = zzth.zzba(split[0]);
            ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            allocate.putInt(zzba);
            array = allocate.array();
        } else if (split.length < 5) {
            byte[] bArr = new byte[(split.length << 1)];
            for (i2 = 0; i2 < split.length; i2++) {
                int zzba2 = zzth.zzba(split[i2]);
                zzba2 = (zzba2 >> 16) ^ (SupportMenu.USER_MASK & zzba2);
                byte[] bArr2 = new byte[]{(byte) zzba2, (byte) (zzba2 >> 8)};
                zzba2 = i2 << 1;
                bArr[zzba2] = bArr2[0];
                bArr[zzba2 + 1] = bArr2[1];
            }
            array = bArr;
        } else {
            byte[] bArr3 = new byte[split.length];
            for (int i3 = 0; i3 < split.length; i3++) {
                i2 = zzth.zzba(split[i3]);
                bArr3[i3] = (byte) ((i2 >> 24) ^ (((i2 & 255) ^ ((i2 >> 8) & 255)) ^ ((i2 >> 16) & 255)));
            }
            array = bArr3;
        }
        this.zzbze = zzoc();
        synchronized (this.mLock) {
            if (this.zzbze == null) {
                array = new byte[0];
                return array;
            }
            this.zzbze.reset();
            this.zzbze.update(array);
            array = this.zzbze.digest();
            if (array.length <= 4) {
                i = array.length;
            }
            byte[] bArr4 = new byte[i];
            System.arraycopy(array, 0, bArr4, 0, bArr4.length);
            return bArr4;
        }
    }
}
