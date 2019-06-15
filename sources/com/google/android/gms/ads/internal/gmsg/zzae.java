package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzbbd;
import com.payu.custombrowser.util.CBConstant;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzae implements zzu<Object> {
    private final Object mLock = new Object();
    private final Map<String, zzaf> zzdgg = new HashMap();

    public final void zza(String str, zzaf zzaf) {
        synchronized (this.mLock) {
            this.zzdgg.put(str, zzaf);
        }
    }

    public final void zza(Object obj, Map<String, String> map) {
        Object obj2;
        String str = (String) map.get("id");
        String str2 = (String) map.get(CBConstant.FAIL);
        Object obj3 = (String) map.get("fail_reason");
        String str3 = (String) map.get("fail_stack");
        String str4 = (String) map.get("result");
        if (TextUtils.isEmpty(str3)) {
            obj3 = "Unknown Fail Reason.";
        }
        if (TextUtils.isEmpty(str3)) {
            obj2 = "";
        } else {
            String str5 = "\n";
            str3 = String.valueOf(str3);
            obj2 = str3.length() != 0 ? str5.concat(str3) : new String(str5);
        }
        synchronized (this.mLock) {
            zzaf zzaf = (zzaf) this.zzdgg.remove(str);
            if (zzaf == null) {
                str4 = "Received result for unexpected method invocation: ";
                str = String.valueOf(str);
                zzbbd.zzeo(str.length() != 0 ? str4.concat(str) : new String(str4));
                return;
            } else if (!TextUtils.isEmpty(str2)) {
                str = String.valueOf(obj3);
                str4 = String.valueOf(obj2);
                zzaf.zzbw(str4.length() != 0 ? str.concat(str4) : new String(str));
                return;
            } else if (str4 == null) {
                zzaf.zzd(null);
                return;
            } else {
                try {
                    JSONObject jSONObject = new JSONObject(str4);
                    if (zzaxz.zzza()) {
                        str4 = "Result GMSG: ";
                        str2 = String.valueOf(jSONObject.toString(2));
                        zzaxz.v(str2.length() != 0 ? str4.concat(str2) : new String(str4));
                    }
                    zzaf.zzd(jSONObject);
                } catch (JSONException e) {
                    zzaf.zzbw(e.getMessage());
                }
            }
        }
    }
}
