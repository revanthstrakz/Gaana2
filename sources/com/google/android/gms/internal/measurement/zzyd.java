package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class zzyd<M extends zzyc<M>, T> {
    public final int tag;
    private final int type;
    private final zzuo<?, ?> zzbyg;
    protected final Class<T> zzceu;
    protected final boolean zzcev;

    public static <M extends zzyc<M>, T extends zzyi> zzyd<M, T> zza(int i, Class<T> cls, long j) {
        return new zzyd(11, cls, 810, false);
    }

    private zzyd(int i, Class<T> cls, int i2, boolean z) {
        this(11, cls, null, 810, false);
    }

    private zzyd(int i, Class<T> cls, zzuo<?, ?> zzuo, int i2, boolean z) {
        this.type = i;
        this.zzceu = cls;
        this.tag = i2;
        this.zzcev = false;
        this.zzbyg = null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzyd)) {
            return false;
        }
        zzyd zzyd = (zzyd) obj;
        return this.type == zzyd.type && this.zzceu == zzyd.zzceu && this.tag == zzyd.tag && this.zzcev == zzyd.zzcev;
    }

    public final int hashCode() {
        return ((((((1147 + this.type) * 31) + this.zzceu.hashCode()) * 31) + this.tag) * 31) + this.zzcev;
    }

    /* Access modifiers changed, original: final */
    public final T zzai(List<zzyk> list) {
        if (list == null) {
            return null;
        }
        if (this.zzcev) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (int i2 = 0; i2 < list.size(); i2++) {
                zzyk zzyk = (zzyk) list.get(i2);
                if (zzyk.zzbtx.length != 0) {
                    arrayList.add(zze(zzxz.zzn(zzyk.zzbtx)));
                }
            }
            int size = arrayList.size();
            if (size == 0) {
                return null;
            }
            Object cast = this.zzceu.cast(Array.newInstance(this.zzceu.getComponentType(), size));
            while (i < size) {
                Array.set(cast, i, arrayList.get(i));
                i++;
            }
            return cast;
        } else if (list.isEmpty()) {
            return null;
        } else {
            return this.zzceu.cast(zze(zzxz.zzn(((zzyk) list.get(list.size() - 1)).zzbtx)));
        }
    }

    private final Object zze(zzxz zzxz) {
        String valueOf;
        StringBuilder stringBuilder;
        Class componentType = this.zzcev ? this.zzceu.getComponentType() : this.zzceu;
        try {
            zzyi zzyi;
            switch (this.type) {
                case 10:
                    zzyi = (zzyi) componentType.newInstance();
                    zzxz.zza(zzyi, this.tag >>> 3);
                    return zzyi;
                case 11:
                    zzyi = (zzyi) componentType.newInstance();
                    zzxz.zza(zzyi);
                    return zzyi;
                default:
                    int i = this.type;
                    StringBuilder stringBuilder2 = new StringBuilder(24);
                    stringBuilder2.append("Unknown type ");
                    stringBuilder2.append(i);
                    throw new IllegalArgumentException(stringBuilder2.toString());
            }
        } catch (InstantiationException e) {
            valueOf = String.valueOf(componentType);
            stringBuilder = new StringBuilder(33 + String.valueOf(valueOf).length());
            stringBuilder.append("Error creating instance of class ");
            stringBuilder.append(valueOf);
            throw new IllegalArgumentException(stringBuilder.toString(), e);
        } catch (IllegalAccessException e2) {
            valueOf = String.valueOf(componentType);
            stringBuilder = new StringBuilder(33 + String.valueOf(valueOf).length());
            stringBuilder.append("Error creating instance of class ");
            stringBuilder.append(valueOf);
            throw new IllegalArgumentException(stringBuilder.toString(), e2);
        } catch (IOException e3) {
            throw new IllegalArgumentException("Error reading extension field", e3);
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void zza(Object obj, zzya zzya) {
        try {
            zzya.zzcd(this.tag);
            switch (this.type) {
                case 10:
                    int i = this.tag >>> 3;
                    ((zzyi) obj).zza(zzya);
                    zzya.zzc(i, 4);
                    return;
                case 11:
                    zzya.zzb((zzyi) obj);
                    return;
                default:
                    int i2 = this.type;
                    StringBuilder stringBuilder = new StringBuilder(24);
                    stringBuilder.append("Unknown type ");
                    stringBuilder.append(i2);
                    throw new IllegalArgumentException(stringBuilder.toString());
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzao(Object obj) {
        int i = this.tag >>> 3;
        switch (this.type) {
            case 10:
                return (zzya.zzbd(i) << 1) + ((zzyi) obj).zzvx();
            case 11:
                return zzya.zzb(i, (zzyi) obj);
            default:
                i = this.type;
                StringBuilder stringBuilder = new StringBuilder(24);
                stringBuilder.append("Unknown type ");
                stringBuilder.append(i);
                throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}
