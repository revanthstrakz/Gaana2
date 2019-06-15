package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.util.List;

public final class zzns extends zznp {
    private final Uri uri;
    private final long zzbdr;
    private final String zzbds;
    private final zzno zzbdt;
    private final zznz zzbdu;

    public zzns(String str, long j, zzfs zzfs, String str2, zzny zzny, List<zznm> list, String str3, long j2) {
        zzno zzno;
        String str4;
        String str5 = str;
        zznt zznt = zzny;
        super(str5, -1, zzfs, str2, zznt, list, null);
        this.uri = Uri.parse(str2);
        zznz zznz = null;
        if (zznt.zzbed <= 0) {
            zzno = null;
        } else {
            zzno zzno2 = new zzno(null, zznt.zzbec, zznt.zzbed);
        }
        this.zzbdt = zzno;
        if (str5 != null) {
            str4 = zzfs.zzze;
            StringBuilder stringBuilder = new StringBuilder((22 + String.valueOf(str).length()) + String.valueOf(str4).length());
            stringBuilder.append(str5);
            stringBuilder.append(".");
            stringBuilder.append(str4);
            stringBuilder.append(".-1");
            str4 = stringBuilder.toString();
        } else {
            str4 = null;
        }
        this.zzbds = str4;
        this.zzbdr = -1;
        if (this.zzbdt == null) {
            zznz = new zznz(new zzno(null, 0, -1));
        }
        this.zzbdu = zznz;
    }

    public final zzno zzgi() {
        return this.zzbdt;
    }

    public final zznd zzgj() {
        return this.zzbdu;
    }

    public final String zzf() {
        return this.zzbds;
    }
}
