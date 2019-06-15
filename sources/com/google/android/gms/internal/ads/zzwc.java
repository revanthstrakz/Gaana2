package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@zzark
public final class zzwc {
    private Bundle mExtras;
    private boolean zzbli;
    private int zzcjs;
    private String zzcjt;
    private long zzcju;
    private int zzcjv;
    private List<String> zzcjw;
    private boolean zzcjx;
    private int zzcjy;
    private String zzcjz;
    private zzzs zzcka;
    private String zzckb;
    private Bundle zzckc;
    private Bundle zzckd;
    private List<String> zzcke;
    private String zzckf;
    private String zzckg;
    private boolean zzckh;
    private zzvv zzcki;
    private Location zzil;

    public zzwc() {
        this.zzcju = -1;
        this.mExtras = new Bundle();
        this.zzcjv = -1;
        this.zzcjw = new ArrayList();
        this.zzcjx = false;
        this.zzcjy = -1;
        this.zzbli = false;
        this.zzcjz = null;
        this.zzcka = null;
        this.zzil = null;
        this.zzckb = null;
        this.zzckc = new Bundle();
        this.zzckd = new Bundle();
        this.zzcke = new ArrayList();
        this.zzckf = null;
        this.zzckg = null;
        this.zzckh = false;
        this.zzcjs = -1;
        this.zzcjt = null;
    }

    public zzwc(zzwb zzwb) {
        this.zzcju = zzwb.zzcjb;
        this.mExtras = zzwb.extras;
        this.zzcjv = zzwb.zzcjc;
        this.zzcjw = zzwb.zzcjd;
        this.zzcjx = zzwb.zzcje;
        this.zzcjy = zzwb.zzcjf;
        this.zzbli = zzwb.zzcjg;
        this.zzcjz = zzwb.zzcjh;
        this.zzcka = zzwb.zzcji;
        this.zzil = zzwb.zzcjj;
        this.zzckb = zzwb.zzcjk;
        this.zzckc = zzwb.zzcjl;
        this.zzckd = zzwb.zzcjm;
        this.zzcke = zzwb.zzcjn;
        this.zzckf = zzwb.zzcjo;
        this.zzckg = zzwb.zzcjp;
        this.zzckh = zzwb.zzcjq;
        this.zzcki = zzwb.zzcjr;
        this.zzcjs = zzwb.zzcjs;
        this.zzcjt = zzwb.zzcjt;
    }

    public final zzwb zzpn() {
        long j = this.zzcju;
        Bundle bundle = this.mExtras;
        int i = this.zzcjv;
        List list = this.zzcjw;
        boolean z = this.zzcjx;
        int i2 = this.zzcjy;
        boolean z2 = this.zzbli;
        String str = this.zzcjz;
        zzzs zzzs = this.zzcka;
        Location location = this.zzil;
        String str2 = this.zzckb;
        Bundle bundle2 = this.zzckc;
        Bundle bundle3 = this.zzckd;
        Bundle bundle4 = bundle3;
        Bundle bundle5 = bundle4;
        return new zzwb(8, j, bundle, i, list, z, i2, z2, str, zzzs, location, str2, bundle2, bundle5, this.zzcke, this.zzckf, this.zzckg, this.zzckh, this.zzcki, this.zzcjs, this.zzcjt);
    }

    public final zzwc zza(@Nullable Location location) {
        this.zzil = null;
        return this;
    }
}
