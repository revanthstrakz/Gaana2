package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;

@zzark
public final class zzaoc {
    private final zzbgg zzdin;
    private final boolean zzdpt;
    private final String zzdpu;

    public zzaoc(zzbgg zzbgg, Map<String, String> map) {
        this.zzdin = zzbgg;
        this.zzdpu = (String) map.get("forceOrientation");
        if (map.containsKey("allowOrientationChange")) {
            this.zzdpt = Boolean.parseBoolean((String) map.get("allowOrientationChange"));
        } else {
            this.zzdpt = true;
        }
    }

    public final void execute() {
        if (this.zzdin == null) {
            zzbbd.zzeo("AdWebView is null");
            return;
        }
        int zzzx;
        if ("portrait".equalsIgnoreCase(this.zzdpu)) {
            zzzx = zzbv.zzlh().zzzx();
        } else if ("landscape".equalsIgnoreCase(this.zzdpu)) {
            zzzx = zzbv.zzlh().zzzw();
        } else if (this.zzdpt) {
            zzzx = -1;
        } else {
            zzzx = zzbv.zzlh().zzzy();
        }
        this.zzdin.setRequestedOrientation(zzzx);
    }
}
