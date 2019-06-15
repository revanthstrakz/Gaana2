package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

public final class zzbkl implements zzbjm {
    private static final byte[] zzfdo = new byte[0];
    private final zzbna zzfdp;
    private final zzbjm zzfdq;

    public zzbkl(zzbna zzbna, zzbjm zzbjm) {
        this.zzfdp = zzbna;
        this.zzfdq = zzbjm;
    }

    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] toByteArray = zzbkb.zzb(this.zzfdp).toByteArray();
        byte[] zzc = this.zzfdq.zzc(toByteArray, zzfdo);
        bArr = ((zzbjm) zzbkb.zza(this.zzfdp.zzaig(), toByteArray)).zzc(bArr, bArr2);
        return ByteBuffer.allocate((4 + zzc.length) + bArr.length).putInt(zzc.length).put(zzc).put(bArr).array();
    }
}
