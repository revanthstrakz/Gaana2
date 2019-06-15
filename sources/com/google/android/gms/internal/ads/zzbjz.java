package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbne.zzb;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class zzbjz<P> {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private ConcurrentMap<String, List<zzbka<P>>> zzfdc = new ConcurrentHashMap();
    private zzbka<P> zzfdd;

    public final zzbka<P> zzafq() {
        return this.zzfdd;
    }

    public final Collection<List<zzbka<P>>> zzafr() throws GeneralSecurityException {
        return this.zzfdc.values();
    }

    /* Access modifiers changed, original: protected|final */
    public final void zza(zzbka<P> zzbka) {
        this.zzfdd = zzbka;
    }

    /* Access modifiers changed, original: protected|final */
    public final zzbka<P> zza(P p, zzb zzb) throws GeneralSecurityException {
        byte[] array;
        switch (zzb.zzajc()) {
            case LEGACY:
            case CRUNCHY:
                array = ByteBuffer.allocate(5).put((byte) 0).putInt(zzb.zzajb()).array();
                break;
            case TINK:
                array = ByteBuffer.allocate(5).put((byte) 1).putInt(zzb.zzajb()).array();
                break;
            case RAW:
                array = zzbjp.zzfcy;
                break;
            default:
                throw new GeneralSecurityException("unknown output prefix type");
        }
        zzbka zzbka = new zzbka(p, array, zzb.zzaja(), zzb.zzajc());
        ArrayList arrayList = new ArrayList();
        arrayList.add(zzbka);
        String str = new String(zzbka.zzaft(), UTF_8);
        List list = (List) this.zzfdc.put(str, Collections.unmodifiableList(arrayList));
        if (list != null) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(list);
            arrayList2.add(zzbka);
            this.zzfdc.put(str, Collections.unmodifiableList(arrayList2));
        }
        return zzbka;
    }
}
