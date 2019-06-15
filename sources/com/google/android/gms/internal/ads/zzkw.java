package com.google.android.gms.internal.ads;

final class zzkw implements Runnable {
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
    private final /* synthetic */ long zzawh;
    private final /* synthetic */ long zzawi;

    zzkw(zzkt zzkt, zzoz zzoz, int i, int i2, zzfs zzfs, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
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
        this.zzawh = j4;
        this.zzawi = j5;
    }

    public final void run() {
        zzks zza = this.zzawg.zzavv;
        zzoz zzoz = this.zzavx;
        int i = this.zzavy;
        int i2 = this.zzavz;
        zzfs zzfs = this.zzawa;
        int i3 = this.zzawb;
        Object obj = this.zzawc;
        long zza2 = this.zzawg.zzx(this.zzawd);
        long zza3 = this.zzawg.zzx(this.zzawe);
        long j = this.zzawf;
        long j2 = j;
        zza.zzb(zzoz, i, i2, zzfs, i3, obj, zza2, zza3, j2, this.zzawh, this.zzawi);
    }
}
