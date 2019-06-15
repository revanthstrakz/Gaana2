package com.google.android.gms.internal.ads;

public abstract class zzbpm<MessageType extends zzbpl<MessageType, BuilderType>, BuilderType extends zzbpm<MessageType, BuilderType>> implements zzbsm {
    public abstract BuilderType zza(MessageType messageType);

    /* renamed from: zzakh */
    public abstract BuilderType clone();

    public final /* synthetic */ zzbsm zzd(zzbsl zzbsl) {
        if (zzamv().getClass().isInstance(zzbsl)) {
            return zza((zzbpl) zzbsl);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
