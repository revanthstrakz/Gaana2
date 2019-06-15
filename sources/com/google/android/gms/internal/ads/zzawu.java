package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@zzark
@TargetApi(21)
final class zzawu {
    private static final Map<String, String> zzegp;
    private final List<String> zzegq;
    private final zzawg zzegr;
    private final Context zzsp;

    zzawu(Context context, List<String> list, zzawg zzawg) {
        this.zzsp = context;
        this.zzegq = list;
        this.zzegr = zzawg;
    }

    /* Access modifiers changed, original: final */
    public final List<String> zzc(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : strArr) {
            Object obj2;
            Object obj3;
            Iterator it = this.zzegq.iterator();
            String valueOf;
            String str;
            do {
                obj2 = 1;
                if (!it.hasNext()) {
                    obj3 = null;
                    break;
                }
                str = (String) it.next();
                if (str.equals(obj)) {
                    break;
                }
                valueOf = String.valueOf("android.webkit.resource.");
                str = String.valueOf(str);
            } while (!(str.length() != 0 ? valueOf.concat(str) : new String(valueOf)).equals(obj));
            obj3 = 1;
            if (obj3 != null) {
                if (zzegp.containsKey(obj)) {
                    zzbv.zzlf();
                    if (!zzayh.zzn(this.zzsp, (String) zzegp.get(obj))) {
                        obj2 = null;
                    }
                }
                if (obj2 != null) {
                    arrayList.add(obj);
                } else {
                    this.zzegr.zzdk(obj);
                }
            } else {
                this.zzegr.zzdj(obj);
            }
        }
        return arrayList;
    }

    static {
        HashMap hashMap = new HashMap();
        if (PlatformVersion.isAtLeastLollipop()) {
            hashMap.put("android.webkit.resource.AUDIO_CAPTURE", "android.permission.RECORD_AUDIO");
            hashMap.put("android.webkit.resource.VIDEO_CAPTURE", "android.permission.CAMERA");
        }
        zzegp = hashMap;
    }
}
