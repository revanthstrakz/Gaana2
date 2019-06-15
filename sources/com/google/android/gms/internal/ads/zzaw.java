package com.google.android.gms.internal.ads;

import android.support.annotation.GuardedBy;
import android.support.annotation.Nullable;
import java.io.UnsupportedEncodingException;
import org.apache.http.entity.mime.MIME;

public class zzaw extends zzr<String> {
    private final Object mLock = new Object();
    @Nullable
    @GuardedBy("mLock")
    private zzz<String> zzcl;

    public zzaw(int i, String str, zzz<String> zzz, @Nullable zzy zzy) {
        super(i, str, zzy);
        this.zzcl = zzz;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: zzh */
    public void zza(String str) {
        zzz zzz;
        synchronized (this.mLock) {
            zzz = this.zzcl;
        }
        if (zzz != null) {
            zzz.zzb(str);
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final zzx<String> zza(zzp zzp) {
        Object str;
        try {
            byte[] bArr = zzp.data;
            String str2 = "ISO-8859-1";
            String str3 = (String) zzp.zzab.get(MIME.CONTENT_TYPE);
            if (str3 != null) {
                String[] split = str3.split(";", 0);
                for (int i = 1; i < split.length; i++) {
                    String[] split2 = split[i].trim().split("=", 0);
                    if (split2.length == 2 && split2[0].equals("charset")) {
                        str2 = split2[1];
                        break;
                    }
                }
            }
            str = new String(bArr, str2);
        } catch (UnsupportedEncodingException unused) {
            str = new String(zzp.data);
        }
        return zzx.zza(str, zzap.zzb(zzp));
    }
}
