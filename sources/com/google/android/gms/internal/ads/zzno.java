package com.google.android.gms.internal.ads;

import android.net.Uri;

public final class zzno {
    private int zzaac;
    public final long zzbdk;
    private final String zzbdl;
    public final long zzcc;

    public zzno(String str, long j, long j2) {
        if (str == null) {
            str = "";
        }
        this.zzbdl = str;
        this.zzbdk = j;
        this.zzcc = j2;
    }

    public final Uri zzy(String str) {
        return Uri.parse(zzqd.zzc(str, this.zzbdl));
    }

    private final String zzz(String str) {
        return zzqd.zzc(str, this.zzbdl);
    }

    public final zzno zza(zzno zzno, String str) {
        String zzz = zzz(str);
        if (zzno == null || !zzz.equals(zzno.zzz(str))) {
            return null;
        }
        long j = -1;
        long j2;
        if (this.zzcc != -1 && this.zzbdk + this.zzcc == zzno.zzbdk) {
            j2 = this.zzbdk;
            if (zzno.zzcc != -1) {
                j = this.zzcc + zzno.zzcc;
            }
            return new zzno(zzz, j2, j);
        } else if (zzno.zzcc == -1 || zzno.zzbdk + zzno.zzcc != this.zzbdk) {
            return null;
        } else {
            j2 = zzno.zzbdk;
            if (this.zzcc != -1) {
                j = zzno.zzcc + this.zzcc;
            }
            return new zzno(zzz, j2, j);
        }
    }

    public final int hashCode() {
        if (this.zzaac == 0) {
            this.zzaac = ((((527 + ((int) this.zzbdk)) * 31) + ((int) this.zzcc)) * 31) + this.zzbdl.hashCode();
        }
        return this.zzaac;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzno zzno = (zzno) obj;
        return this.zzbdk == zzno.zzbdk && this.zzcc == zzno.zzcc && this.zzbdl.equals(zzno.zzbdl);
    }
}
