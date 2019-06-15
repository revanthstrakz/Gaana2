package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzm extends zzgh {
    private static final String ID = com.google.android.gms.internal.measurement.zza.ARBITRARY_PIXEL.toString();
    private static final String URL = zzb.URL.toString();
    private static final String zzazi = zzb.ADDITIONAL_PARAMS.toString();
    private static final String zzazj = zzb.UNREPEATABLE.toString();
    private static final String zzazk;
    private static final Set<String> zzazl = new HashSet();
    private final zza zzazm;
    private final Context zzri;

    public interface zza {
        zzbx zznl();
    }

    public zzm(Context context) {
        this(context, new zzn(context));
    }

    @VisibleForTesting
    private zzm(Context context, zza zza) {
        super(ID, URL);
        this.zzazm = zza;
        this.zzri = context;
    }

    public final void zze(Map<String, zzp> map) {
        String zzc = map.get(zzazj) != null ? zzgj.zzc((zzp) map.get(zzazj)) : null;
        if (zzc == null || !zzdb(zzc)) {
            String str;
            Builder buildUpon = Uri.parse(zzgj.zzc((zzp) map.get(URL))).buildUpon();
            zzp zzp = (zzp) map.get(zzazi);
            if (zzp != null) {
                Object zzh = zzgj.zzh(zzp);
                if (zzh instanceof List) {
                    for (Object next : (List) zzh) {
                        if (next instanceof Map) {
                            for (Entry entry : ((Map) next).entrySet()) {
                                buildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                            }
                        } else {
                            str = "ArbitraryPixel: additional params contains non-map: not sending partial hit: ";
                            zzc = String.valueOf(buildUpon.build().toString());
                            zzdi.e(zzc.length() != 0 ? str.concat(zzc) : new String(str));
                            return;
                        }
                    }
                }
                str = "ArbitraryPixel: additional params not a list: not sending partial hit: ";
                zzc = String.valueOf(buildUpon.build().toString());
                zzdi.e(zzc.length() != 0 ? str.concat(zzc) : new String(str));
                return;
            }
            str = buildUpon.build().toString();
            this.zzazm.zznl().zzdo(str);
            String str2 = "ArbitraryPixel: url = ";
            str = String.valueOf(str);
            zzdi.v(str.length() != 0 ? str2.concat(str) : new String(str2));
            if (zzc != null) {
                synchronized (zzm.class) {
                    zzazl.add(zzc);
                    zzft.zza(this.zzri, zzazk, zzc, "true");
                }
            }
        }
    }

    private final synchronized boolean zzdb(String str) {
        if (zzazl.contains(str)) {
            return true;
        }
        if (!this.zzri.getSharedPreferences(zzazk, 0).contains(str)) {
            return false;
        }
        zzazl.add(str);
        return true;
    }

    static {
        String str = ID;
        StringBuilder stringBuilder = new StringBuilder(17 + String.valueOf(str).length());
        stringBuilder.append("gtm_");
        stringBuilder.append(str);
        stringBuilder.append("_unrepeatable");
        zzazk = stringBuilder.toString();
    }
}
