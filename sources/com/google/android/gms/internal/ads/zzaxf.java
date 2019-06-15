package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

@zzark
public final class zzaxf {
    public final int errorCode;
    public final int orientation;
    public final boolean zzbph;
    public final boolean zzbpi;
    public final List<String> zzdlq;
    public final List<String> zzdlr;
    public final List<String> zzdls;
    @Nullable
    public final List<String> zzdlu;
    public final long zzdlx;
    @Nullable
    public final zzakq zzdnb;
    @Nullable
    public final zzalj zzdnc;
    @Nullable
    public final String zzdnd;
    @Nullable
    public final zzakt zzdne;
    @Nullable
    public final zzbgg zzdrv;
    public final zzwb zzdwg;
    public final String zzdwj;
    private final long zzdyc;
    public final boolean zzdyd;
    private final long zzdye;
    public final List<String> zzdyf;
    public final String zzdyi;
    @Nullable
    public final zzawd zzdyr;
    @Nullable
    public final List<String> zzdyt;
    public final boolean zzdyu;
    private final zzaso zzdyv;
    public final String zzdyy;
    public final boolean zzdzc;
    private final String zzdzd;
    public final boolean zzdzf;
    public final JSONObject zzehh;
    public boolean zzehi;
    public final zzakr zzehj;
    @Nullable
    public final String zzehk;
    public final zzwf zzehl;
    @Nullable
    public final List<String> zzehm;
    public final long zzehn;
    public final long zzeho;
    @Nullable
    public final zzacf zzehp;
    public boolean zzehq;
    public boolean zzehr;
    public boolean zzehs;
    public boolean zzeht;
    public boolean zzehu;
    public boolean zzehv;
    public final zzum zzehw;
    public final boolean zzehx;

    public zzaxf(zzwb zzwb, @Nullable zzbgg zzbgg, List<String> list, int i, List<String> list2, List<String> list3, int i2, long j, String str, boolean z, @Nullable zzakq zzakq, @Nullable zzalj zzalj, @Nullable String str2, zzakr zzakr, @Nullable zzakt zzakt, long j2, zzwf zzwf, long j3, long j4, long j5, String str3, JSONObject jSONObject, @Nullable zzacf zzacf, zzawd zzawd, List<String> list4, List<String> list5, boolean z2, zzaso zzaso, @Nullable String str4, List<String> list6, String str5, zzum zzum, boolean z3, boolean z4, boolean z5, List<String> list7, boolean z6, String str6, boolean z7) {
        this.zzehq = false;
        this.zzehr = false;
        this.zzehs = false;
        this.zzeht = false;
        this.zzehu = false;
        this.zzehv = false;
        this.zzdwg = zzwb;
        this.zzdrv = zzbgg;
        this.zzdlq = zzn(list);
        this.errorCode = i;
        this.zzdlr = zzn(list2);
        this.zzdyf = zzn(list3);
        this.orientation = i2;
        this.zzdlx = j;
        this.zzdwj = str;
        this.zzdyd = z;
        this.zzdnb = zzakq;
        this.zzdnc = zzalj;
        this.zzdnd = str2;
        this.zzehj = zzakr;
        this.zzdne = zzakt;
        this.zzdye = j2;
        this.zzehl = zzwf;
        this.zzdyc = j3;
        this.zzehn = j4;
        this.zzeho = j5;
        this.zzdyi = str3;
        this.zzehh = jSONObject;
        this.zzehp = zzacf;
        this.zzdyr = zzawd;
        this.zzehm = zzn(list4);
        this.zzdyt = zzn(list5);
        this.zzdyu = z2;
        this.zzdyv = zzaso;
        this.zzehk = str4;
        this.zzdlu = zzn(list6);
        this.zzdyy = str5;
        this.zzehw = zzum;
        this.zzbph = z3;
        this.zzehx = z4;
        this.zzdzc = z5;
        this.zzdls = zzn(list7);
        this.zzbpi = z6;
        this.zzdzd = str6;
        this.zzdzf = z7;
    }

    public zzaxf(zzaxg zzaxg, @Nullable zzbgg zzbgg, @Nullable zzakq zzakq, @Nullable zzalj zzalj, @Nullable String str, @Nullable zzakt zzakt, @Nullable zzacf zzacf, @Nullable String str2) {
        zzaxg zzaxg2 = zzaxg;
        zzwb zzwb = zzaxg2.zzeag.zzdwg;
        List list = zzaxg2.zzehy.zzdlq;
        int i = zzaxg2.errorCode;
        List list2 = zzaxg2.zzehy.zzdlr;
        List list3 = zzaxg2.zzehy.zzdyf;
        int i2 = zzaxg2.zzehy.orientation;
        long j = zzaxg2.zzehy.zzdlx;
        String str3 = zzaxg2.zzeag.zzdwj;
        boolean z = zzaxg2.zzehy.zzdyd;
        zzakr zzakr = zzaxg2.zzehj;
        long j2 = zzaxg2.zzehy.zzdye;
        zzwf zzwf = zzaxg2.zzbst;
        long j3 = j2;
        long j4 = zzaxg2.zzehy.zzdyc;
        long j5 = zzaxg2.zzehn;
        j2 = zzaxg2.zzeho;
        String str4 = zzaxg2.zzehy.zzdyi;
        long j6 = j2;
        JSONObject jSONObject = zzaxg2.zzehh;
        zzawd zzawd = zzaxg2.zzehy.zzdyr;
        String str5 = str4;
        List list4 = zzaxg2.zzehy.zzdys;
        List list5 = zzaxg2.zzehy.zzdys;
        boolean z2 = zzaxg2.zzehy.zzdyu;
        zzaso zzaso = zzaxg2.zzehy.zzdyv;
        List list6 = zzaxg2.zzehy.zzdlu;
        str4 = zzaxg2.zzehy.zzdyy;
        JSONObject jSONObject2 = jSONObject;
        zzum zzum = zzaxg2.zzehw;
        String str6 = str4;
        boolean z3 = zzaxg2.zzehy.zzbph;
        zzum zzum2 = zzum;
        boolean z4 = zzaxg2.zzehx;
        boolean z5 = z3;
        boolean z6 = zzaxg2.zzehy.zzdzc;
        List list7 = zzaxg2.zzehy.zzdls;
        boolean z7 = zzaxg2.zzehy.zzbpi;
        String str7 = zzaxg2.zzehy.zzdzd;
        zzwf zzwf2 = zzwf;
        zzawd zzawd2 = zzawd;
        boolean z8 = z4;
        zzalj zzalj2 = null;
        String str8 = null;
        zzakr zzakr2 = zzakr;
        zzakt zzakt2 = null;
        long j7 = j3;
        long j8 = j4;
        long j9 = j5;
        long j10 = j6;
        this(zzwb, null, list, i, list2, list3, i2, j, str3, z, null, zzalj2, str8, zzakr2, zzakt2, j7, zzwf2, j8, j9, j10, str5, jSONObject2, null, zzawd2, list4, list5, z2, zzaso, null, list6, str6, zzum2, z5, z8, z6, list7, z7, str7, zzaxg2.zzehy.zzdzf);
    }

    public final boolean zzmu() {
        return (this.zzdrv == null || this.zzdrv.zzadl() == null) ? false : this.zzdrv.zzadl().zzmu();
    }

    @Nullable
    private static <T> List<T> zzn(@Nullable List<T> list) {
        return list == null ? null : Collections.unmodifiableList(list);
    }
}
