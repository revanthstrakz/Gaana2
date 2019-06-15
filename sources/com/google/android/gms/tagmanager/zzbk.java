package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

final class zzbk extends zzbq {
    private static final String ID = zza.ENCODE.toString();
    private static final String zzbcd = zzb.ARG0.toString();
    private static final String zzbce = zzb.NO_PADDING.toString();
    private static final String zzbcf = zzb.INPUT_FORMAT.toString();
    private static final String zzbcg = zzb.OUTPUT_FORMAT.toString();

    public zzbk() {
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
        Object obj;
        Object obj2;
        String zzc = zzgj.zzc(zzp);
        zzp zzp2 = (zzp) map.get(zzbcf);
        if (zzp2 == null) {
            obj = MimeTypes.BASE_TYPE_TEXT;
        } else {
            obj = zzgj.zzc(zzp2);
        }
        zzp zzp3 = (zzp) map.get(zzbcg);
        if (zzp3 == null) {
            obj2 = "base16";
        } else {
            obj2 = zzgj.zzc(zzp3);
        }
        int i = 2;
        zzp zzp4 = (zzp) map.get(zzbce);
        if (zzp4 != null && zzgj.zzg(zzp4).booleanValue()) {
            i = 3;
        }
        try {
            byte[] bytes;
            String str;
            Object encode;
            if (MimeTypes.BASE_TYPE_TEXT.equals(obj)) {
                bytes = zzc.getBytes();
            } else if ("base16".equals(obj)) {
                bytes = zzo.decode(zzc);
            } else if ("base64".equals(obj)) {
                bytes = Base64.decode(zzc, i);
            } else if ("base64url".equals(obj)) {
                bytes = Base64.decode(zzc, i | 8);
            } else {
                str = "Encode: unknown input format: ";
                zzc = String.valueOf(obj);
                zzdi.e(zzc.length() != 0 ? str.concat(zzc) : new String(str));
                return zzgj.zzqq();
            }
            if ("base16".equals(obj2)) {
                encode = zzo.encode(bytes);
            } else if ("base64".equals(obj2)) {
                encode = Base64.encodeToString(bytes, i);
            } else if ("base64url".equals(obj2)) {
                encode = Base64.encodeToString(bytes, i | 8);
            } else {
                str = "Encode: unknown output format: ";
                zzc = String.valueOf(obj2);
                zzdi.e(zzc.length() != 0 ? str.concat(zzc) : new String(str));
                return zzgj.zzqq();
            }
            return zzgj.zzj(encode);
        } catch (IllegalArgumentException unused) {
            zzdi.e("Encode: invalid input:");
            return zzgj.zzqq();
        }
    }
}
