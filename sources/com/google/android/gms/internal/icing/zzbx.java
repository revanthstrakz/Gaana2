package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class zzbx<MessageType extends zzbx<MessageType, BuilderType>, BuilderType extends zzby<MessageType, BuilderType>> implements zzeq {
    private static boolean zzfq;
    protected int zzfp = 0;

    public final zzce zzaf() {
        try {
            zzcm zzm = zzce.zzm(zzbi());
            zzb(zzm.zzat());
            return zzm.zzas();
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
            byte[] bArr = new byte[zzbi()];
            zzct zzb = zzct.zzb(bArr);
            zzb(zzb);
            zzb.zzaw();
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
    public int zzag() {
        throw new UnsupportedOperationException();
    }

    /* Access modifiers changed, original: 0000 */
    public void zzg(int i) {
        throw new UnsupportedOperationException();
    }

    protected static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzdl.checkNotNull(iterable);
        int size;
        StringBuilder stringBuilder;
        String stringBuilder2;
        int size2;
        if (iterable instanceof zzea) {
            List zzcg = ((zzea) iterable).zzcg();
            zzea zzea = (zzea) list;
            int size3 = list.size();
            for (Object next : zzcg) {
                if (next == null) {
                    size = zzea.size() - size3;
                    stringBuilder = new StringBuilder(37);
                    stringBuilder.append("Element at index ");
                    stringBuilder.append(size);
                    stringBuilder.append(" is null.");
                    stringBuilder2 = stringBuilder.toString();
                    for (size2 = zzea.size() - 1; size2 >= size3; size2--) {
                        zzea.remove(size2);
                    }
                    throw new NullPointerException(stringBuilder2);
                } else if (next instanceof zzce) {
                    zzea.zzc((zzce) next);
                } else {
                    zzea.add((String) next);
                }
            }
        } else if (iterable instanceof zzfb) {
            list.addAll((Collection) iterable);
        } else {
            if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
                ((ArrayList) list).ensureCapacity(list.size() + ((Collection) iterable).size());
            }
            int size4 = list.size();
            for (Object next2 : iterable) {
                if (next2 == null) {
                    size = list.size() - size4;
                    stringBuilder = new StringBuilder(37);
                    stringBuilder.append("Element at index ");
                    stringBuilder.append(size);
                    stringBuilder.append(" is null.");
                    stringBuilder2 = stringBuilder.toString();
                    for (size2 = list.size() - 1; size2 >= size4; size2--) {
                        list.remove(size2);
                    }
                    throw new NullPointerException(stringBuilder2);
                }
                list.add(next2);
            }
        }
    }
}
