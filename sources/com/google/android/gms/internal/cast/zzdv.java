package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.Api.ClientKey;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

public final class zzdv {
    public static final ClientKey<zzdd> zzzf = new ClientKey();
    public static final ClientKey<zzer> zzzg = new ClientKey();
    public static final ClientKey<zzeq> zzzh = new ClientKey();
    private static final ClientKey<Object> zzzi = new ClientKey();
    private static final ClientKey<Object> zzzj = new ClientKey();
    private static final Charset zzzk;
    private static final String zzzl = zzdk.zzq("com.google.cast.multizone");
    private static boolean zzzm = false;

    static {
        Charset forName;
        try {
            forName = Charset.forName("UTF-8");
        } catch (IllegalCharsetNameException | UnsupportedCharsetException unused) {
            forName = null;
        }
        zzzk = forName;
    }
}
