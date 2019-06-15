package com.google.android.gms.tagmanager;

import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

final class zzbv extends zzbq {
    private static final String ID = zza.HASH.toString();
    private static final String zzbcd = zzb.ARG0.toString();
    private static final String zzbcf = zzb.INPUT_FORMAT.toString();
    private static final String zzbci = zzb.ALGORITHM.toString();

    public zzbv() {
        super(ID, zzbcd);
    }

    public final boolean zznk() {
        return true;
    }

    public final zzp zzc(Map<String, zzp> map) {
        zzp zzp = (zzp) map.get(zzbcd);
        if (zzp == null || zzp == zzgj.zzqq()) {
            return zzgj.zzqq();
        }
        String str;
        Object obj;
        byte[] bytes;
        String valueOf;
        String zzc = zzgj.zzc(zzp);
        zzp zzp2 = (zzp) map.get(zzbci);
        if (zzp2 == null) {
            str = "MD5";
        } else {
            str = zzgj.zzc(zzp2);
        }
        zzp zzp3 = (zzp) map.get(zzbcf);
        if (zzp3 == null) {
            obj = MimeTypes.BASE_TYPE_TEXT;
        } else {
            obj = zzgj.zzc(zzp3);
        }
        if (MimeTypes.BASE_TYPE_TEXT.equals(obj)) {
            bytes = zzc.getBytes();
        } else if ("base16".equals(obj)) {
            bytes = zzo.decode(zzc);
        } else {
            zzc = "Hash: unknown input format: ";
            valueOf = String.valueOf(obj);
            zzdi.e(valueOf.length() != 0 ? zzc.concat(valueOf) : new String(zzc));
            return zzgj.zzqq();
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bytes);
            return zzgj.zzj(zzo.encode(instance.digest()));
        } catch (NoSuchAlgorithmException unused) {
            valueOf = "Hash: unknown algorithm: ";
            zzc = String.valueOf(str);
            zzdi.e(zzc.length() != 0 ? valueOf.concat(zzc) : new String(valueOf));
            return zzgj.zzqq();
        }
    }
}
