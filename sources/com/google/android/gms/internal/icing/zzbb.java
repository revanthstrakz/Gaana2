package com.google.android.gms.internal.icing;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.GuardedBy;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.payu.custombrowser.util.CBConstant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzbb implements zzbf {
    @GuardedBy("ConfigurationContentLoader.class")
    static final Map<Uri, zzbb> zzct = new ArrayMap();
    private static final String[] zzcy = new String[]{CBConstant.KEY, "value"};
    private final Uri uri;
    private final ContentResolver zzcu;
    private final Object zzcv = new Object();
    private volatile Map<String, String> zzcw;
    @GuardedBy("this")
    private final List<zzbe> zzcx = new ArrayList();

    private zzbb(ContentResolver contentResolver, Uri uri) {
        this.zzcu = contentResolver;
        this.uri = uri;
        this.zzcu.registerContentObserver(uri, false, new zzbd(this, null));
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0018 */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:2|3|(5:5|6|7|8|9)|11|12) */
    public static com.google.android.gms.internal.icing.zzbb zza(android.content.ContentResolver r3, android.net.Uri r4) {
        /*
        r0 = com.google.android.gms.internal.icing.zzbb.class;
        monitor-enter(r0);
        r1 = zzct;	 Catch:{ all -> 0x001a }
        r1 = r1.get(r4);	 Catch:{ all -> 0x001a }
        r1 = (com.google.android.gms.internal.icing.zzbb) r1;	 Catch:{ all -> 0x001a }
        if (r1 != 0) goto L_0x0018;
    L_0x000d:
        r2 = new com.google.android.gms.internal.icing.zzbb;	 Catch:{ SecurityException -> 0x0018 }
        r2.<init>(r3, r4);	 Catch:{ SecurityException -> 0x0018 }
        r3 = zzct;	 Catch:{ SecurityException -> 0x0017 }
        r3.put(r4, r2);	 Catch:{ SecurityException -> 0x0017 }
    L_0x0017:
        r1 = r2;
    L_0x0018:
        monitor-exit(r0);	 Catch:{ all -> 0x001a }
        return r1;
    L_0x001a:
        r3 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x001a }
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzbb.zza(android.content.ContentResolver, android.net.Uri):com.google.android.gms.internal.icing.zzbb");
    }

    private final Map<String, String> zzr() {
        Map<String, String> map = this.zzcw;
        if (map == null) {
            synchronized (this.zzcv) {
                map = this.zzcw;
                if (map == null) {
                    map = zzt();
                    this.zzcw = map;
                }
            }
        }
        if (map != null) {
            return map;
        }
        return Collections.emptyMap();
    }

    public final void zzs() {
        synchronized (this.zzcv) {
            this.zzcw = null;
            zzbl.zzx();
        }
        synchronized (this) {
            for (zzbe zzw : this.zzcx) {
                zzw.zzw();
            }
        }
    }

    private final Map<String, String> zzt() {
        try {
            return (Map) zzbg.zza(new zzbc(this));
        } catch (SQLiteException | SecurityException unused) {
            Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
            return null;
        }
    }

    public final /* synthetic */ Object zzn(String str) {
        return (String) zzr().get(str);
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ Map zzu() {
        Cursor query = this.zzcu.query(this.uri, zzcy, null, null, null);
        if (query == null) {
            return Collections.emptyMap();
        }
        try {
            int count = query.getCount();
            if (count == 0) {
                Map emptyMap = Collections.emptyMap();
                return emptyMap;
            }
            Map arrayMap;
            if (count <= 256) {
                arrayMap = new ArrayMap(count);
            } else {
                arrayMap = new HashMap(count, 1.0f);
            }
            while (query.moveToNext()) {
                arrayMap.put(query.getString(0), query.getString(1));
            }
            query.close();
            return arrayMap;
        } finally {
            query.close();
        }
    }
}
