package com.google.android.gms.internal.ads;

import java.util.List;
import org.json.JSONObject;

@zzark
public final class zzapo extends zzaxv {
    private final zzapm zzdsj;
    private final zzaxg zzdsk;
    private final zzasm zzdsl = this.zzdsk.zzehy;

    public zzapo(zzaxg zzaxg, zzapm zzapm) {
        this.zzdsk = zzaxg;
        this.zzdsj = zzapm;
    }

    public final void onStop() {
    }

    public final void zzki() {
        zzwb zzwb = this.zzdsk.zzeag.zzdwg;
        int i = this.zzdsl.orientation;
        long j = this.zzdsl.zzdlx;
        String str = this.zzdsk.zzeag.zzdwj;
        long j2 = this.zzdsl.zzdye;
        zzwf zzwf = this.zzdsk.zzbst;
        long j3 = this.zzdsl.zzdyc;
        long j4 = this.zzdsk.zzehn;
        long j5 = j2;
        j2 = this.zzdsl.zzdyh;
        String str2 = this.zzdsl.zzdyi;
        JSONObject jSONObject = this.zzdsk.zzehh;
        boolean z = this.zzdsk.zzehy.zzdyu;
        zzaso zzaso = this.zzdsk.zzehy.zzdyv;
        zzum zzum = this.zzdsk.zzehw;
        boolean z2 = this.zzdsk.zzehy.zzbph;
        boolean z3 = this.zzdsk.zzehx;
        boolean z4 = this.zzdsk.zzehy.zzdzc;
        boolean z5 = this.zzdsk.zzehy.zzbpi;
        String str3 = str2;
        zzbgg zzbgg = null;
        long j6 = j4;
        List list = null;
        int i2 = 0;
        long j7 = j3;
        List list2 = null;
        List list3 = null;
        zzwf zzwf2 = zzwf;
        boolean z6 = false;
        long j8 = j5;
        j5 = j2;
        zzakq zzakq = null;
        zzalj zzalj = null;
        zzaxf zzaxf = r1;
        String str4 = null;
        zzakr zzakr = null;
        zzakt zzakt = null;
        long j9 = j7;
        long j10 = j6;
        long j11 = j5;
        zzaxf zzaxf2 = new zzaxf(zzwb, zzbgg, list, i2, list2, list3, i, j, str, z6, zzakq, zzalj, str4, zzakr, zzakt, j8, zzwf2, j9, j10, j11, str3, jSONObject, null, null, null, null, z, zzaso, null, null, null, zzum, z2, z3, z4, null, z5, this.zzdsk.zzehy.zzdzd, this.zzdsk.zzehy.zzdzf);
        zzayh.zzelc.post(new zzapp(this, zzaxf));
    }
}
