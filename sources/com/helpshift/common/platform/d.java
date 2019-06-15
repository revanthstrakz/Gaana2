package com.helpshift.common.platform;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.facebook.internal.AnalyticsEvents;
import com.helpshift.common.platform.Device.PermissionState;
import com.helpshift.common.platform.Device.PermissionType;
import com.helpshift.support.util.f;
import com.helpshift.util.b;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class d implements Device {
    private final Context a;

    public String a() {
        return InternalLogger.EVENT_PARAM_SDK_ANDROID;
    }

    public String b() {
        return "6.4.0";
    }

    public String i() {
        return InternalAvidAdSessionContext.AVID_API_LEVEL;
    }

    d(Context context) {
        this.a = context;
    }

    public String c() {
        return VERSION.RELEASE;
    }

    public int d() {
        return VERSION.SDK_INT;
    }

    public String e() {
        return b.a(this.a);
    }

    public String f() {
        return b.c(this.a);
    }

    public String g() {
        return this.a.getPackageName();
    }

    public String h() {
        return Locale.getDefault().toString();
    }

    public PermissionState a(PermissionType permissionType) {
        switch (permissionType) {
            case READ_STORAGE:
                return a("android.permission.READ_EXTERNAL_STORAGE");
            case WRITE_STORAGE:
                return a("android.permission.WRITE_EXTERNAL_STORAGE");
            default:
                return null;
        }
    }

    public Locale j() {
        Configuration configuration = this.a.getResources().getConfiguration();
        if (VERSION.SDK_INT >= 24) {
            return configuration.getLocales().get(0);
        }
        return configuration.locale;
    }

    public void a(Locale locale) {
        Resources resources = this.a.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, displayMetrics);
    }

    private PermissionState a(String str) {
        int d = d();
        if (d < 19) {
            return PermissionState.AVAILABLE;
        }
        if (b.a(this.a, str)) {
            return PermissionState.AVAILABLE;
        }
        if (d < 23) {
            return PermissionState.UNAVAILABLE;
        }
        if (f.a(this.a, str)) {
            return PermissionState.REQUESTABLE;
        }
        return PermissionState.UNAVAILABLE;
    }

    public String k() {
        return Build.MODEL;
    }

    public String l() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.getProperty("os.version"));
        stringBuilder.append(":");
        stringBuilder.append(Build.FINGERPRINT);
        return stringBuilder.toString();
    }

    public String m() {
        return ((TelephonyManager) this.a.getSystemService("phone")).getSimCountryIso();
    }

    public String n() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH).format(new Date());
    }

    public String o() {
        return ((TelephonyManager) this.a.getSystemService("phone")).getNetworkOperatorName();
    }

    public String p() {
        String str = null;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.a.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                str = activeNetworkInfo.getTypeName();
            }
        } catch (SecurityException unused) {
        }
        return str == null ? AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN : str;
    }

    public String q() {
        int intExtra = this.a.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("status", -1);
        Object obj = (intExtra == 2 || intExtra == 5) ? 1 : null;
        return obj != null ? "Charging" : "Not charging";
    }

    public String r() {
        Intent registerReceiver = this.a.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int intExtra = (int) ((((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1))) * 100.0f);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(intExtra);
        stringBuilder.append("%");
        return stringBuilder.toString();
    }

    public com.helpshift.meta.dto.b s() {
        double round;
        double round2;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (VERSION.SDK_INT >= 18) {
            round = ((double) Math.round(((((double) statFs.getAvailableBlocksLong()) * ((double) statFs.getBlockSizeLong())) / 1.073741824E9d) * 100.0d)) / 100.0d;
            round2 = ((double) Math.round(((((double) statFs.getBlockCountLong()) * ((double) statFs.getBlockSizeLong())) / 1.073741824E9d) * 100.0d)) / 100.0d;
        } else {
            round = ((double) Math.round(((((double) statFs.getAvailableBlocks()) * ((double) statFs.getBlockSize())) / 1.073741824E9d) * 100.0d)) / 100.0d;
            round2 = ((double) Math.round(((((double) statFs.getBlockCount()) * ((double) statFs.getBlockSize())) / 1.073741824E9d) * 100.0d)) / 100.0d;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(round2);
        stringBuilder.append(" GB");
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(round);
        stringBuilder3.append(" GB");
        return new com.helpshift.meta.dto.b(stringBuilder2, stringBuilder3.toString(), null, null);
    }
}
