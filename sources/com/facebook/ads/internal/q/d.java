package com.facebook.ads.internal.q;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.ads.internal.n.a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.Locale;

public class d {
    public static String a() {
        if (TextUtils.isEmpty(AdInternalSettings.getUrlPrefix())) {
            return "https://graph.facebook.com/network_ads_common";
        }
        return String.format(Locale.US, "https://graph.%s.facebook.com/network_ads_common", new Object[]{AdInternalSettings.getUrlPrefix()});
    }

    public static String a(Context context) {
        String format = TextUtils.isEmpty(AdInternalSettings.getUrlPrefix()) ? "https://www.facebook.com/adnw_logging/" : String.format(Locale.US, "https://www.%s.facebook.com/adnw_logging/", new Object[]{AdInternalSettings.getUrlPrefix()});
        String z = a.z(context);
        return TextUtils.isEmpty(z) ? format : format.replace("www", z);
    }
}
