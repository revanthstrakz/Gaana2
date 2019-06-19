package com.google.android.gms.internal.ads;

import java.io.IOException;

public abstract class zzbpl<MessageType extends zzbpl<MessageType, BuilderType>, BuilderType extends zzbpm<MessageType, BuilderType>> implements zzbsl {
    private static boolean zzfky;
    protected int zzfkx = 0;

    public final zzbpu zzakf() {
        try {
            zzbqb zzen = zzbpu.zzen(zzamj());
            zzb(zzen.zzakt());
            return zzen.zzaks();
        } catch (IOException e) {
            String str = "ByteString";
            String name = getClass().getName();
            StringBuilder stringBuilder = new StringBuilder((62 + String.valueOf(name).length()) + String.valueOf(str).length());
            stringBuilder.append("Serializing ");
            stringBuilder.append(name);
            stringBuilder.append(" to a ");
            stringBuilder.append(str);
            stringBuilder.append(" threw an IOException (should never happen).");
            throw new RuntimeException(stringBuilder.toString(), e);
        }
    }

    public final byte[] toByteArray() {
        try {
            byte[] bArr = new byte[zzamj()];
            zzbqk zzt = zzbqk.zzt(bArr);
            zzb(zzt);
            zzt.zzalv();
            return bArr;
        } catch (IOException e) {
            String str = "byte array";
            String name = getClass().getName();
            StringBuilder stringBuilder = new StringBuilder((62 + String.valueOf(name).length()) + String.valueOf(str).length());
            stringBuilder.append("Serializing ");
            stringBuilder.append(name);
            stringBuilder.append(" to a ");
            stringBuilder.append(str);
            stringBuilder.append(" threw an IOException (should never happen).");
            throw new RuntimeException(stringBuilder.toString(), e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int zzakg() {
        throw new UnsupportedOperationException();
    }

    /* Access modifiers changed, original: 0000 */
    public void zzei(int i) {
        throw new UnsupportedOperationException();
    }
}
