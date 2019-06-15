package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.support.annotation.GuardedBy;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@zzark
public class zzaig<ReferenceT> {
    @GuardedBy("this")
    private final Map<String, CopyOnWriteArrayList<zzu<? super ReferenceT>>> zzdii = new HashMap();
    private ReferenceT zzdij;

    public final void zzi(ReferenceT referenceT) {
        this.zzdij = referenceT;
    }

    public final boolean zzf(Uri uri) {
        if (!"gmsg".equalsIgnoreCase(uri.getScheme()) || !"mobileads.google.com".equalsIgnoreCase(uri.getHost())) {
            return false;
        }
        String path = uri.getPath();
        zzbv.zzlf();
        zzb(path, zzayh.zzg(uri));
        return true;
    }

    private final synchronized void zzb(String str, Map<String, String> map) {
        if (zzbbd.isLoggable(2)) {
            String str2 = "Received GMSG: ";
            String valueOf = String.valueOf(str);
            zzaxz.v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            for (String valueOf2 : map.keySet()) {
                String str3 = (String) map.get(valueOf2);
                StringBuilder stringBuilder = new StringBuilder((4 + String.valueOf(valueOf2).length()) + String.valueOf(str3).length());
                stringBuilder.append("  ");
                stringBuilder.append(valueOf2);
                stringBuilder.append(": ");
                stringBuilder.append(str3);
                zzaxz.v(stringBuilder.toString());
            }
        }
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.zzdii.get(str);
        if (copyOnWriteArrayList != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                zzbcg.zzepo.execute(new zzaih(this, (zzu) it.next(), map));
            }
        }
    }

    public final synchronized void zza(String str, zzu<? super ReferenceT> zzu) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.zzdii.get(str);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList();
            this.zzdii.put(str, copyOnWriteArrayList);
        }
        copyOnWriteArrayList.add(zzu);
    }

    public final synchronized void zzb(String str, zzu<? super ReferenceT> zzu) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.zzdii.get(str);
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.remove(zzu);
        }
    }

    public final synchronized void zza(String str, Predicate<zzu<? super ReferenceT>> predicate) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.zzdii.get(str);
        if (copyOnWriteArrayList != null) {
            ArrayList arrayList = new ArrayList();
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                zzu zzu = (zzu) it.next();
                if (predicate.apply(zzu)) {
                    arrayList.add(zzu);
                }
            }
            copyOnWriteArrayList.removeAll(arrayList);
        }
    }

    public final synchronized void reset() {
        this.zzdii.clear();
    }
}
