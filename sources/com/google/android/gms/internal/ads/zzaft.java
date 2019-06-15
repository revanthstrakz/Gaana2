package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.ads.internal.zzbv;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@zzark
public final class zzaft implements zzm {
    private final Context mContext;
    private volatile zzafk zzdgm;

    public zzaft(Context context) {
        this.mContext = context;
    }

    public final zzp zzc(zzr<?> zzr) throws zzae {
        ParcelFileDescriptor parcelFileDescriptor;
        zzafl zzh = zzafl.zzh(zzr);
        long elapsedRealtime = zzbv.zzlm().elapsedRealtime();
        zzp zzp = null;
        try {
            zzbcb zzbcl = new zzbcl();
            this.zzdgm = new zzafk(this.mContext, zzbv.zzlv().zzaak(), new zzafx(this, zzbcl), new zzafy(this, zzbcl));
            this.zzdgm.checkAvailabilityAndConnect();
            zzbcb zza = zzbbq.zza(zzbbq.zza(zzbcl, new zzafu(this, zzh), zzayf.zzeky), (long) ((Integer) zzwu.zzpz().zzd(zzaan.zzcwa)).intValue(), TimeUnit.MILLISECONDS, zzayf.zzela);
            zza.zza(new zzafw(this), zzayf.zzeky);
            parcelFileDescriptor = (ParcelFileDescriptor) zza.get();
        } catch (InterruptedException | ExecutionException unused) {
            return zzp;
        } finally {
            String str = elapsedRealtime;
            zzp = zzbv.zzlm();
            long elapsedRealtime2 = zzp.elapsedRealtime() - elapsedRealtime;
            StringBuilder stringBuilder = new StringBuilder(52);
            stringBuilder.append("Http assets remote cache took ");
            stringBuilder.append(elapsedRealtime2);
            stringBuilder.append("ms");
            zzaxz.v(stringBuilder.toString());
            return zzp;
        }
        zzafn zzafn = (zzafn) new zzasy(parcelFileDescriptor).zza(zzafn.CREATOR);
        if (zzafn == null) {
            return zzp;
        }
        if (zzafn.zzdgk) {
            throw new zzae(zzafn.zzdgl);
        } else if (zzafn.zzdgi.length != zzafn.zzdgj.length) {
            return zzp;
        } else {
            Map hashMap = new HashMap();
            for (int i = 0; i < zzafn.zzdgi.length; i++) {
                hashMap.put(zzafn.zzdgi[i], zzafn.zzdgj[i]);
            }
            return new zzp(zzafn.statusCode, zzafn.data, hashMap, zzafn.zzac, zzafn.zzad);
        }
    }

    private final void disconnect() {
        if (this.zzdgm != null) {
            this.zzdgm.disconnect();
            Binder.flushPendingCommands();
        }
    }
}
