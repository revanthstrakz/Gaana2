package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.util.Map;

final class zzabo implements zzu<Object> {
    private final /* synthetic */ zzabn zzdan;

    zzabo(zzabn zzabn) {
        this.zzdan = zzabn;
    }

    public final void zza(Object obj, Map<String, String> map) {
        try {
            this.zzdan.zzdal = Long.valueOf(Long.parseLong((String) map.get(AvidJSONUtil.KEY_TIMESTAMP)));
        } catch (NumberFormatException unused) {
            zzbbd.e("Failed to call parse unconfirmedClickTimestamp.");
        }
        this.zzdan.zzdak = (String) map.get("id");
        String str = (String) map.get("asset_id");
        if (this.zzdan.zzdai == null) {
            zzbbd.zzdn("Received unconfirmed click but UnconfirmedClickListener is null.");
            return;
        }
        try {
            this.zzdan.zzdai.onUnconfirmedClickReceived(str);
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }
}
