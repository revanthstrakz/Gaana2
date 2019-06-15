package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbvs extends zzbut<zzbvs> {
    public String mimeType;
    public Integer zzgar;
    public byte[] zzgbs;

    public zzbvs() {
        this.zzgar = null;
        this.mimeType = null;
        this.zzgbs = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.zzgar != null) {
            zzbur.zzv(1, this.zzgar.intValue());
        }
        if (this.mimeType != null) {
            zzbur.zzf(2, this.mimeType);
        }
        if (this.zzgbs != null) {
            zzbur.zza(3, this.zzgbs);
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzgar != null) {
            zzt += zzbur.zzz(1, this.zzgar.intValue());
        }
        if (this.mimeType != null) {
            zzt += zzbur.zzg(2, this.mimeType);
        }
        return this.zzgbs != null ? zzt + zzbur.zzb(3, this.zzgbs) : zzt;
    }

    private final zzbvs zzg(zzbuq zzbuq) throws IOException {
        int zzakx;
        StringBuilder stringBuilder;
        while (true) {
            int zzaku = zzbuq.zzaku();
            if (zzaku == 0) {
                return this;
            }
            if (zzaku == 8) {
                try {
                    zzakx = zzbuq.zzakx();
                    if (zzakx < 0 || zzakx > 1) {
                        stringBuilder = new StringBuilder(36);
                        stringBuilder.append(zzakx);
                        stringBuilder.append(" is not a valid enum Type");
                    } else {
                        this.zzgar = Integer.valueOf(zzakx);
                    }
                } catch (IllegalArgumentException unused) {
                    zzbuq.zzgc(zzbuq.getPosition());
                    zza(zzbuq, zzaku);
                }
            } else if (zzaku == 18) {
                this.mimeType = zzbuq.readString();
            } else if (zzaku == 26) {
                this.zzgbs = zzbuq.readBytes();
            } else if (!super.zza(zzbuq, zzaku)) {
                return this;
            }
        }
        stringBuilder = new StringBuilder(36);
        stringBuilder.append(zzakx);
        stringBuilder.append(" is not a valid enum Type");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
