package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzals;
import com.google.android.gms.internal.ads.zzalv;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzbgg;
import java.util.Map;

final class zzax implements zzu<zzbgg> {
    private final /* synthetic */ zzals zzbpo;
    private final /* synthetic */ zzab zzbpp;
    private final /* synthetic */ zzalv zzbpq;

    zzax(zzals zzals, zzab zzab, zzalv zzalv) {
        this.zzbpo = zzals;
        this.zzbpp = zzab;
        this.zzbpq = zzalv;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbgg zzbgg = (zzbgg) obj;
        View view = zzbgg.getView();
        if (view != null) {
            try {
                if (this.zzbpo == null) {
                    if (this.zzbpq != null) {
                        if (this.zzbpq.getOverrideClickHandling()) {
                            zzas.zzd(zzbgg);
                        } else {
                            this.zzbpq.zzk(ObjectWrapper.wrap(view));
                            this.zzbpp.zzbng.onAdClicked();
                        }
                    }
                } else if (this.zzbpo.getOverrideClickHandling()) {
                    zzas.zzd(zzbgg);
                } else {
                    this.zzbpo.zzk(ObjectWrapper.wrap(view));
                    this.zzbpp.zzbng.onAdClicked();
                }
            } catch (RemoteException e) {
                zzbbd.zzc("Unable to call handleClick on mapper", e);
            }
        }
    }
}
