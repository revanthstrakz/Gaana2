package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient;
import java.io.IOException;

final class zzcc extends zzck {
    final /* synthetic */ zzcb zzwi;

    zzcc(zzcb zzcb, GameManagerClient gameManagerClient) {
        this.zzwi = zzcb;
        super(zzcb, gameManagerClient);
    }

    public final void execute() {
        try {
            this.zzwi.zzhz.setMessageReceivedCallbacks(this.zzwi.zzpb, this.zzwi.getNamespace(), new zzcd(this));
            this.zzwi.zzem();
            this.zzwi.zzel();
            this.zzwi.zza(null, 1100, null, this.zzwq);
        } catch (IOException | IllegalStateException unused) {
            this.zzwq.zza(-1, 8, null);
        }
    }
}
