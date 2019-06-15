package com.helpshift.campaigns.models;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.helpshift.network.c.e;
import com.helpshift.util.b;
import com.helpshift.util.l;
import com.helpshift.util.o;
import com.helpshift.util.r;
import com.helpshift.util.w;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.TimeZone;

public class a implements c {
    public String a() {
        return VERSION.RELEASE;
    }

    public String b() {
        return Build.MODEL;
    }

    public String c() {
        return b.a(o.b());
    }

    public String d() {
        Context b = o.b();
        TelephonyManager telephonyManager = (TelephonyManager) b.getSystemService("phone");
        String str = "";
        if (telephonyManager.getPhoneType() != 2) {
            str = telephonyManager.getNetworkCountryIso();
        }
        if (w.a(str)) {
            str = telephonyManager.getSimCountryIso();
        }
        if (!w.a(str)) {
            return str;
        }
        String a = r.a(b);
        return a != null ? a.toLowerCase() : str;
    }

    public String e() {
        try {
            return Locale.getDefault().toString();
        } catch (MissingResourceException e) {
            l.a("Helpshift_AndroidDevice", "Device Info - MissingResourceException", e);
            return null;
        }
    }

    public String f() {
        return ((TelephonyManager) o.b().getSystemService("phone")).getNetworkOperatorName();
    }

    public Integer g() {
        return Integer.valueOf(TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 60000);
    }

    public String h() {
        return e.a(true);
    }
}
