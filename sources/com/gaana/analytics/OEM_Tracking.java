package com.gaana.analytics;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.constants.Constants;
import com.gaana.models.BusinessObject;
import com.i.i;
import com.managers.URLManager;
import com.services.d;
import com.services.l.af;
import com.utilities.Util;
import java.net.URLEncoder;

public class OEM_Tracking {
    public static final String PrefName_Analytics_OEM_Campaign = "Apsalar OEM Tracking Campaign";

    private static void sendHttpRequest(String str) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(str);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(String.class);
        uRLManager.i(false);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                if (obj != null) {
                    d.a().a(OEM_Tracking.PrefName_Analytics_OEM_Campaign, false, false);
                }
            }
        }, uRLManager);
    }

    public static void onCreate(Context context) {
        if (Util.q(context) && d.a().b(PrefName_Analytics_OEM_Campaign, true, false)) {
            String str = "https://apptracker.gaana.com/preburn.php?type=log&device_name=<device_name>&device_model=<device_model>&device_os=<device_os>";
            String str2 = Build.MANUFACTURER;
            String str3 = Build.MODEL;
            CharSequence charSequence = "android";
            if (Util.T()) {
                charSequence = "yunos";
                str2 = System.getProperty("ro.yunos.model");
            }
            str = str.replace("<device_name>", URLEncoder.encode(str2)).replace("<device_model>", URLEncoder.encode(str3)).replace("<device_os>", charSequence);
            if (Constants.b) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("url ");
                stringBuilder.append(str);
                Log.d("Test", stringBuilder.toString());
            }
            sendHttpRequest(str);
        }
    }
}
