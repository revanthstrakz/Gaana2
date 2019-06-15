package com.google.android.gms.phenotype;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import com.payu.custombrowser.util.CBConstant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class zza {
    private static final ConcurrentHashMap<Uri, zza> zzg = new ConcurrentHashMap();
    private static final String[] zzl = new String[]{CBConstant.KEY, "value"};
    private final Uri uri;
    private final ContentResolver zzh;
    private final ContentObserver zzi;
    private final Object zzj = new Object();
    private volatile Map<String, String> zzk;

    private zza(ContentResolver contentResolver, Uri uri) {
        this.zzh = contentResolver;
        this.uri = uri;
        this.zzi = new zzb(this, null);
    }

    public static zza zza(ContentResolver contentResolver, Uri uri) {
        zza zza = (zza) zzg.get(uri);
        if (zza == null) {
            zza = new zza(contentResolver, uri);
            zza zza2 = (zza) zzg.putIfAbsent(uri, zza);
            if (zza2 == null) {
                zza.zzh.registerContentObserver(zza.uri, false, zza.zzi);
                return zza;
            }
            zza = zza2;
        }
        return zza;
    }

    private final Map<String, String> zzc() {
        Map<String, String> hashMap = new HashMap();
        Cursor query = this.zzh.query(this.uri, zzl, null, null, null);
        if (query == null) {
            return hashMap;
        }
        while (query.moveToNext()) {
            try {
                hashMap.put(query.getString(0), query.getString(1));
            } finally {
                query.close();
            }
        }
        return hashMap;
    }

    public final Map<String, String> zza() {
        Map<String, String> zzc = PhenotypeFlag.zza("gms:phenotype:phenotype_flag:debug_disable_caching", false) ? zzc() : this.zzk;
        if (zzc != null) {
            return zzc;
        }
        synchronized (this.zzj) {
            zzc = this.zzk;
            if (zzc == null) {
                zzc = zzc();
                this.zzk = zzc;
            }
        }
        return zzc;
    }

    public final void zzb() {
        synchronized (this.zzj) {
            this.zzk = null;
        }
    }
}
