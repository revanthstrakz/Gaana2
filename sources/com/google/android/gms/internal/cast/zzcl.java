package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Status;
import java.util.Locale;

final class zzcl implements zzec {
    private final /* synthetic */ zzcb zzwo;
    private final /* synthetic */ zzck zzws;

    zzcl(zzck zzck, zzcb zzcb) {
        this.zzws = zzck;
        this.zzwo = zzcb;
    }

    public final void zzd(long j) {
        this.zzws.setResult(zzck.zzc(new Status(2103)));
    }

    public final void zza(long j, int i, Object obj) {
        if (obj == null) {
            try {
                this.zzws.setResult(new zzcm(new Status(i, null, null), this.zzws.zzwr));
                return;
            } catch (ClassCastException unused) {
                this.zzws.setResult(zzck.zzc(new Status(13)));
                return;
            }
        }
        zzcp zzcp = (zzcp) obj;
        zzco zzco = zzcp.zzxk;
        if (zzco == null || zzdk.zza("1.0.0", zzco.getVersion())) {
            this.zzws.setResult(new zzcm(new Status(i, zzcp.zzxa, null), this.zzws.zzwr));
            return;
        }
        this.zzws.zzwi.zzvy = null;
        this.zzws.setResult(zzck.zzc(new Status(GameManagerClient.STATUS_INCORRECT_VERSION, String.format(Locale.ROOT, "Incorrect Game Manager SDK version. Receiver: %s Sender: %s", new Object[]{zzco.getVersion(), "1.0.0"}))));
    }
}
