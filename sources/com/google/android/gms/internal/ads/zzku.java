package com.google.android.gms.internal.ads;

final class zzku implements Runnable {
    private final /* synthetic */ zzoz zzavx;
    private final /* synthetic */ int zzavy;
    private final /* synthetic */ int zzavz;
    private final /* synthetic */ zzfs zzawa;
    private final /* synthetic */ int zzawb;
    private final /* synthetic */ Object zzawc;
    private final /* synthetic */ long zzawd;
    private final /* synthetic */ long zzawe;
    private final /* synthetic */ long zzawf;
    private final /* synthetic */ zzkt zzawg;

    zzku(zzkt zzkt, zzoz zzoz, int i, int i2, zzfs zzfs, int i3, Object obj, long j, long j2, long j3) {
        this.zzawg = zzkt;
        this.zzavx = zzoz;
        this.zzavy = i;
        this.zzavz = i2;
        this.zzawa = zzfs;
        this.zzawb = i3;
        this.zzawc = obj;
        this.zzawd = j;
        this.zzawe = j2;
        this.zzawf = j3;
    }

    public final void run() {
        this.zzawg.zzavv.zza(this.zzavx, this.zzavy, this.zzavz, this.zzawa, this.zzawb, this.zzawc, this.zzawg.zzx(this.zzawd), this.zzawg.zzx(this.zzawe), this.zzawf);
    }
}
