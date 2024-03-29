package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzabs;
import com.google.android.gms.internal.ads.zzabu;
import com.google.android.gms.internal.ads.zzabz;
import com.google.android.gms.internal.ads.zzacf;
import com.google.android.gms.internal.ads.zzbbd;
import java.util.List;

final class zzbf implements Runnable {
    private final /* synthetic */ int zzbqf;
    private final /* synthetic */ zzbb zzbqi;
    private final /* synthetic */ zzacf zzbqj;
    private final /* synthetic */ List zzbqk;

    zzbf(zzbb zzbb, zzacf zzacf, int i, List list) {
        this.zzbqi = zzbb;
        this.zzbqj = zzacf;
        this.zzbqf = i;
        this.zzbqk = list;
    }

    public final void run() {
        try {
            boolean z = false;
            zzbb zzbb;
            zzabz zzb;
            if ((this.zzbqj instanceof zzabu) && this.zzbqi.zzbls.zzbte != null) {
                zzbb = this.zzbqi;
                if (this.zzbqf != this.zzbqk.size() - 1) {
                    z = true;
                }
                zzbb.zzblq = z;
                zzb = zzbb.zza(this.zzbqj);
                this.zzbqi.zzbls.zzbte.zza(zzb);
                this.zzbqi.zzb(zzb.zzsc());
            } else if ((this.zzbqj instanceof zzabu) && this.zzbqi.zzbls.zzbtd != null) {
                zzbb = this.zzbqi;
                if (this.zzbqf != this.zzbqk.size() - 1) {
                    z = true;
                }
                zzbb.zzblq = z;
                zzabu zzabu = (zzabu) this.zzbqj;
                this.zzbqi.zzbls.zzbtd.zza(zzabu);
                this.zzbqi.zzb(zzabu.zzsc());
            } else if ((this.zzbqj instanceof zzabs) && this.zzbqi.zzbls.zzbte != null) {
                zzbb = this.zzbqi;
                if (this.zzbqf != this.zzbqk.size() - 1) {
                    z = true;
                }
                zzbb.zzblq = z;
                zzb = zzbb.zza(this.zzbqj);
                this.zzbqi.zzbls.zzbte.zza(zzb);
                this.zzbqi.zzb(zzb.zzsc());
            } else if (!(this.zzbqj instanceof zzabs) || this.zzbqi.zzbls.zzbtc == null) {
                zzbb = this.zzbqi;
                if (this.zzbqf != this.zzbqk.size() - 1) {
                    z = true;
                }
                zzbb.zzg(3, z);
            } else {
                zzbb = this.zzbqi;
                if (this.zzbqf != this.zzbqk.size() - 1) {
                    z = true;
                }
                zzbb.zzblq = z;
                zzabs zzabs = (zzabs) this.zzbqj;
                this.zzbqi.zzbls.zzbtc.zza(zzabs);
                this.zzbqi.zzb(zzabs.zzsc());
            }
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }
}
