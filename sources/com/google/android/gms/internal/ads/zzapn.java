package com.google.android.gms.internal.ads;

import android.content.Context;

@zzark
public class zzapn extends zzapf {
    zzapn(Context context, zzaxg zzaxg, zzbgg zzbgg, zzapm zzapm) {
        super(context, zzaxg, zzbgg, zzapm);
    }

    /* Access modifiers changed, original: protected */
    public void zzwb() {
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzvz() {
        if (this.zzdsl.errorCode == -2) {
            this.zzdin.zzadl().zza((zzbho) this);
            zzwb();
            zzbbd.zzdn("Loading HTML in WebView.");
            this.zzdin.zzc(this.zzdsl.zzbde, this.zzdsl.zzdyb, null);
        }
    }
}
