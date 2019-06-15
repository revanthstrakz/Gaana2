package com.facebook.ads.internal.i;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.ads.internal.h.e;
import com.gaana.login.LoginManager;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class b {
    public static final String a = VERSION.RELEASE;
    private final Context b;
    private final AtomicBoolean c = new AtomicBoolean();

    public b(Context context) {
        this.b = context.getApplicationContext();
    }

    private void k() {
        if (!this.c.getAndSet(true)) {
            HashMap hashMap = new HashMap();
            hashMap.put(LoginManager.TAG_SUBTYPE, "generic");
            hashMap.put("subtype_code", String.valueOf(1304));
            e.a(new Exception("PI_NULL"), this.b, hashMap);
        }
    }

    public String a() {
        return (Build.MANUFACTURER == null || Build.MANUFACTURER.length() <= 0) ? "" : Build.MANUFACTURER;
    }

    public String b() {
        return (Build.MODEL == null || Build.MODEL.length() <= 0) ? "" : Build.MODEL;
    }

    public String c() {
        TelephonyManager telephonyManager = (TelephonyManager) this.b.getSystemService("phone");
        if (telephonyManager != null) {
            String networkOperatorName = telephonyManager.getNetworkOperatorName();
            if (networkOperatorName != null && networkOperatorName.length() > 0) {
                return networkOperatorName;
            }
        }
        return "";
    }

    public String d() {
        try {
            CharSequence applicationLabel = this.b.getPackageManager().getApplicationLabel(this.b.getPackageManager().getApplicationInfo(f(), 0));
            if (applicationLabel != null && applicationLabel.length() > 0) {
                return applicationLabel.toString();
            }
        } catch (NameNotFoundException unused) {
        }
        return "";
    }

    public String e() {
        try {
            String f = f();
            if (f != null && f.length() >= 0) {
                f = this.b.getPackageManager().getInstallerPackageName(f);
                if (f != null && f.length() > 0) {
                    return f;
                }
            }
        } catch (Exception unused) {
        }
        return "";
    }

    public String f() {
        PendingIntent activity = PendingIntent.getActivity(this.b, 0, new Intent(), 0);
        if (activity != null) {
            return VERSION.SDK_INT >= 17 ? activity.getCreatorPackage() : activity.getTargetPackage();
        } else {
            k();
            return "";
        }
    }

    public String g() {
        CharSequence charSequence;
        try {
            charSequence = this.b.getPackageManager().getPackageInfo(f(), 0).versionName;
        } catch (NameNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            charSequence = null;
        }
        return !TextUtils.isEmpty(charSequence) ? charSequence : "";
    }

    public int h() {
        try {
            return this.b.getPackageManager().getPackageInfo(f(), 0).versionCode;
        } catch (NameNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return 0;
        }
    }

    public boolean i() {
        return this.b.checkCallingOrSelfPermission("android.permission.BIND_ACCESSIBILITY_SERVICE") == 0;
    }

    public int j() {
        return com.facebook.ads.internal.s.a.e.b(this.b);
    }
}
