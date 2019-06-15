package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzawd;
import com.google.android.gms.internal.ads.zzbbd;
import java.util.Map;

@zzark
public final class zzag implements zzu<Object> {
    private final zzah zzdgh;

    public zzag(zzah zzah) {
        this.zzdgh = zzah;
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = (String) map.get(NativeProtocol.WEB_DIALOG_ACTION);
        if ("grant".equals(str)) {
            zzawd zzawd = null;
            try {
                int parseInt = Integer.parseInt((String) map.get("amount"));
                String str2 = (String) map.get("type");
                if (!TextUtils.isEmpty(str2)) {
                    zzawd = new zzawd(str2, parseInt);
                }
            } catch (NumberFormatException e) {
                zzbbd.zzc("Unable to parse reward amount.", e);
            }
            this.zzdgh.zzb(zzawd);
        } else if ("video_start".equals(str)) {
            this.zzdgh.zzkf();
        } else {
            if ("video_complete".equals(str)) {
                this.zzdgh.zzkg();
            }
        }
    }
}
