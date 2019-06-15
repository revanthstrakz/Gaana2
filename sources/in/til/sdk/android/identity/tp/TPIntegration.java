package in.til.sdk.android.identity.tp;

import android.content.Context;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.timespointssdk.f;
import in.til.core.integrations.b;
import in.til.core.integrations.b.a;
import in.til.core.integrations.c;
import java.util.HashMap;

public class TPIntegration extends b<Void> {
    public static final a FACTORY = new a() {
        public String key() {
            return TPIntegration.TP;
        }

        public b<?> create(HashMap hashMap, in.til.core.a aVar) {
            return new TPIntegration(hashMap, aVar);
        }
    };
    private static final String TP = "tp";

    public TPIntegration(HashMap hashMap, in.til.core.a aVar) {
        if (hashMap != null) {
            try {
                if (!(hashMap.get("pCode") == null || hashMap.get("sCode") == null || hashMap.get("userID") == null)) {
                    if (hashMap.get("deviceID") != null) {
                        f.a(aVar.a(), hashMap.get("pCode").toString(), hashMap.get("sCode").toString(), hashMap.get("userID").toString(), hashMap.get("deviceID").toString(), hashMap.get("ticketId").toString(), (f.a) hashMap.get("callback"));
                        return;
                    }
                }
            } catch (Exception unused) {
                System.out.println("please enter proper settings... for TP SDK");
                return;
            }
        }
        System.out.println("please enter proper settings... for TP SDK");
    }

    public void tpInit(Context context, String str, String str2, String str3, String str4, String str5, c cVar) {
        f.a(context, str, str2, str3, str4, str5, (f.a) cVar);
    }

    public void tpApplicationPaused(c cVar) {
        f.a((f.a) cVar);
    }

    public void tpForeground(Context context, c cVar) {
        try {
            f.a(context, (f.a) cVar);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void tpLogout(c cVar) {
        f.b((f.a) cVar);
    }

    public void tpLogActivityWithCode(String str, String str2, String str3, c cVar) {
        f.a(str, str2, str3, (f.a) cVar);
    }

    public void tpShowProfile(Context context, String str, c cVar) {
        f.a(context, str, (f.a) cVar);
    }
}
