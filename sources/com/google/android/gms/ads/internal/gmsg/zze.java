package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.facebook.ads.internal.g.e;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.internal.ads.zzaba;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.plus.PlusShare;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.util.Map;

@zzark
public final class zze implements zzu<zzbgg> {
    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbgg zzbgg = (zzbgg) obj;
        String str = (String) map.get(NativeProtocol.WEB_DIALOG_ACTION);
        String str2;
        zzaba zzrf;
        if ("tick".equals(str)) {
            str = (String) map.get(PlusShare.KEY_CALL_TO_ACTION_LABEL);
            String str3 = (String) map.get("start_label");
            str2 = (String) map.get(AvidJSONUtil.KEY_TIMESTAMP);
            if (TextUtils.isEmpty(str)) {
                zzbbd.zzeo("No label given for CSI tick.");
            } else if (TextUtils.isEmpty(str2)) {
                zzbbd.zzeo("No timestamp given for CSI tick.");
            } else {
                try {
                    long elapsedRealtime = zzbv.zzlm().elapsedRealtime() + (Long.parseLong(str2) - zzbv.zzlm().currentTimeMillis());
                    if (TextUtils.isEmpty(str3)) {
                        str3 = "native:view_load";
                    }
                    zzbgg.zzaby().zzb(str, str3, elapsedRealtime);
                } catch (NumberFormatException e) {
                    zzbbd.zzc("Malformed timestamp for CSI tick.", e);
                }
            }
        } else if ("experiment".equals(str)) {
            str2 = (String) map.get("value");
            if (TextUtils.isEmpty(str2)) {
                zzbbd.zzeo("No value given for CSI experiment.");
                return;
            }
            zzrf = zzbgg.zzaby().zzrf();
            if (zzrf == null) {
                zzbbd.zzeo("No ticker for WebView, dropping experiment ID.");
            } else {
                zzrf.zzg(e.a, str2);
            }
        } else {
            if ("extra".equals(str)) {
                str = (String) map.get("name");
                str2 = (String) map.get("value");
                if (TextUtils.isEmpty(str2)) {
                    zzbbd.zzeo("No value given for CSI extra.");
                } else if (TextUtils.isEmpty(str)) {
                    zzbbd.zzeo("No name given for CSI extra.");
                } else {
                    zzrf = zzbgg.zzaby().zzrf();
                    if (zzrf == null) {
                        zzbbd.zzeo("No ticker for WebView, dropping extra parameter.");
                        return;
                    }
                    zzrf.zzg(str, str2);
                }
            }
        }
    }
}
