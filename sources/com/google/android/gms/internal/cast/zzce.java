package com.google.android.gms.internal.cast;

import org.json.JSONObject;

final class zzce extends zzch {
    private final /* synthetic */ zzcb zzwi;
    private final /* synthetic */ int zzwk;
    private final /* synthetic */ String zzwl;
    private final /* synthetic */ JSONObject zzwm;

    zzce(zzcb zzcb, int i, String str, JSONObject jSONObject) {
        this.zzwi = zzcb;
        this.zzwk = i;
        this.zzwl = str;
        this.zzwm = jSONObject;
        super(zzcb);
    }

    public final void execute() {
        int i;
        switch (this.zzwk) {
            case 2:
                i = 5;
                break;
            case 3:
                i = 1;
                break;
            case 4:
                i = 2;
                break;
            case 5:
                i = 3;
                break;
            case 6:
                i = 4;
                break;
            default:
                i = 0;
                break;
        }
        if (i == 0) {
            this.zzwq.zza(-1, 2001, null);
            zzcb.zzbf.w("sendPlayerRequest for unsupported playerState: %d", Integer.valueOf(this.zzwk));
            return;
        }
        this.zzwi.zza(this.zzwl, i, this.zzwm, this.zzwq);
    }
}
