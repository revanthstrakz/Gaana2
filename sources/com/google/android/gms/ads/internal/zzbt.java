package com.google.android.gms.ads.internal;

import android.os.AsyncTask;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzcu;
import com.google.android.gms.internal.ads.zzwu;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class zzbt extends AsyncTask<Void, Void, String> {
    private final /* synthetic */ zzbp zzbra;

    private zzbt(zzbp zzbp) {
        this.zzbra = zzbp;
    }

    private final String zza(Void... voidArr) {
        try {
            this.zzbra.zzbqy = (zzcu) this.zzbra.zzbqv.get(((Long) zzwu.zzpz().zzd(zzaan.zzcvi)).longValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzbbd.zzc("", e);
        }
        return this.zzbra.zzkw();
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ void onPostExecute(Object obj) {
        String str = (String) obj;
        if (this.zzbra.zzbqx != null && str != null) {
            this.zzbra.zzbqx.loadUrl(str);
        }
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        return zza((Void[]) objArr);
    }

    /* synthetic */ zzbt(zzbp zzbp, zzbq zzbq) {
        this(zzbp);
    }
}
