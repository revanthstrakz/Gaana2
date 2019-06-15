package com.g.a;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class f {
    static Context a;
    Map<String, String> b = new LinkedHashMap();

    private String e() {
        return "0";
    }

    public f(Context context) {
        a = context;
    }

    private String d() {
        try {
            return "0";
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception in isDeviceRooted");
            return "0";
        }
    }

    public String a() {
        try {
            return "0";
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception in checkSELinux");
            return "0";
        }
    }

    public String b() {
        try {
            boolean z = true;
            StringBuilder stringBuilder;
            if (VERSION.SDK_INT < 3) {
                if (System.getInt(a.getContentResolver(), "install_non_market_apps", 0) != 1) {
                    z = false;
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append("checkNonMarketApp : ");
                stringBuilder.append(z);
                j.a(stringBuilder.toString());
            } else if (VERSION.SDK_INT < 17) {
                if (Secure.getInt(a.getContentResolver(), "install_non_market_apps", 0) != 1) {
                    z = false;
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append("checkNonMarketApp : ");
                stringBuilder.append(z);
                j.a(stringBuilder.toString());
            } else {
                if (Global.getInt(a.getContentResolver(), "install_non_market_apps", 0) != 1) {
                    z = false;
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append("checkNonMarketApp : ");
                stringBuilder.append(z);
                j.a(stringBuilder.toString());
            }
            return z ? "1" : "0";
        } catch (Exception e) {
            j.a("Exception in checkNonMarketApp");
            ThrowableExtension.printStackTrace(e);
            return "0";
        }
    }

    public List c() {
        ArrayList arrayList = new ArrayList();
        try {
            List activeAdmins = ((DevicePolicyManager) a.getSystemService("device_policy")).getActiveAdmins();
            j.a("Inside getActiveAdmins");
            if (!(activeAdmins == null || activeAdmins.isEmpty())) {
                for (int i = 0; i < activeAdmins.size(); i++) {
                    j.a(((ComponentName) activeAdmins.get(i)).getPackageName());
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Active admins : ");
            stringBuilder.append(activeAdmins);
            j.a(stringBuilder.toString());
            return activeAdmins;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception in getActiveAdmins");
            return null;
        }
    }

    private String f() {
        try {
            int a = m.a("ro.boot.warranty_bit", 0);
            int a2 = m.a("ro.warranty_bit", 0);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a);
            stringBuilder.append(";");
            stringBuilder.append(a2);
            return stringBuilder.toString();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception in getWarrantyInfo");
            return "";
        }
    }

    private String g() {
        try {
            int a = m.a("ro.oem_unlock_supported", 0);
            int a2 = m.a("ro.boot.flash.locked", 0);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a);
            stringBuilder.append(";");
            stringBuilder.append(a2);
            return stringBuilder.toString();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception in getLockInfo");
            return "";
        }
    }

    public Map<String, String> a(Map<String, String> map) {
        try {
            j.a("Collecting datapoints B");
            map.put("sdk_warranty_info", f());
            map.put("sdk_rooted_info", d());
            map.put("sdk_sys_path_check", e());
            map.put("sdk_non_market", b());
            map.put("sdk_check_se_enforce", a());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(c());
            map.put("sdk_admin_list", stringBuilder.toString());
            map.put("sdk_lock_info", g());
            return map;
        } catch (Exception unused) {
            j.a("Exception while collecting datapoints B");
            return map;
        }
    }
}
