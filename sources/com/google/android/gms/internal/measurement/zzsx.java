package com.google.android.gms.internal.measurement;

import java.io.IOException;

public abstract class zzsx<MessageType extends zzsx<MessageType, BuilderType>, BuilderType extends zzsy<MessageType, BuilderType>> implements zzvv {
    private static boolean zzbtj;
    protected int zzbti = 0;

    public final zzte zztw() {
        try {
            zztm zzao = zzte.zzao(zzvx());
            zzb(zzao.zzui());
            return zzao.zzuh();
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

    /* Access modifiers changed, original: 0000 */
    public int zztx() {
        throw new UnsupportedOperationException();
    }

    /* Access modifiers changed, original: 0000 */
    public void zzai(int i) {
        throw new UnsupportedOperationException();
    }
}
