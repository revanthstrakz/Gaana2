package com.google.android.gms.internal.ads;

import java.io.IOException;

public abstract class zzbut<M extends zzbut<M>> extends zzbuz {
    protected zzbuv zzfwk;

    /* Access modifiers changed, original: protected */
    public int zzt() {
        int i = 0;
        if (this.zzfwk == null) {
            return 0;
        }
        int i2 = 0;
        while (i < this.zzfwk.size()) {
            i2 += this.zzfwk.zzgg(i).zzt();
            i++;
        }
        return i2;
    }

    public void zza(zzbur zzbur) throws IOException {
        if (this.zzfwk != null) {
            for (int i = 0; i < this.zzfwk.size(); i++) {
                this.zzfwk.zzgg(i).zza(zzbur);
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean zza(zzbuq zzbuq, int i) throws IOException {
        int position = zzbuq.getPosition();
        if (!zzbuq.zzep(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzbvb zzbvb = new zzbvb(i, zzbuq.zzam(position, zzbuq.getPosition() - position));
        zzbuw zzbuw = null;
        if (this.zzfwk == null) {
            this.zzfwk = new zzbuv();
        } else {
            zzbuw = this.zzfwk.zzgf(i2);
        }
        if (zzbuw == null) {
            zzbuw = new zzbuw();
            this.zzfwk.zza(i2, zzbuw);
        }
        zzbuw.zza(zzbvb);
        return true;
    }

    public final /* synthetic */ zzbuz zzapm() throws CloneNotSupportedException {
        return (zzbut) clone();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzbut zzbut = (zzbut) super.clone();
        zzbux.zza(this, zzbut);
        return zzbut;
    }
}
