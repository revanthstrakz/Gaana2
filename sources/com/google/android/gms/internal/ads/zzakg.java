package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzf;
import com.google.android.gms.ads.internal.zzbv;
import org.json.JSONObject;

@zzark
public final class zzakg<I, O> implements zzajv<I, O> {
    private final zzait zzdkg;
    private final zzajx<O> zzdkh;
    private final zzajy<I> zzdki;
    private final String zzdkj;

    zzakg(zzait zzait, String str, zzajy<I> zzajy, zzajx<O> zzajx) {
        this.zzdkg = zzait;
        this.zzdkj = str;
        this.zzdki = zzajy;
        this.zzdkh = zzajx;
    }

    public final zzbcb<O> zzj(I i) {
        zzbcl zzbcl = new zzbcl();
        zzaji zzb = this.zzdkg.zzb(null);
        zzb.zza(new zzakh(this, zzb, i, zzbcl), new zzaki(this, zzbcl, zzb));
        return zzbcl;
    }

    private final void zza(zzaji zzaji, zzajr zzajr, I i, zzbcl<O> zzbcl) {
        try {
            zzbv.zzlf();
            String zzzs = zzayh.zzzs();
            zzf.zzdfh.zza(zzzs, new zzakj(this, zzaji, zzbcl));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", zzzs);
            jSONObject.put("args", this.zzdki.zzk(i));
            zzajr.zzb(this.zzdkj, jSONObject);
        } catch (Exception e) {
            zzbcl.setException(e);
            zzbbd.zzb("Unable to invokeJavaScript", e);
        } finally {
            zzaji.release();
        }
    }

    public final zzbcb<O> zzf(I i) throws Exception {
        return zzj(i);
    }
}
