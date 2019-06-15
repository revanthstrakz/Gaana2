package com.til.colombia.dmp.android;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.util.Log;
import com.til.colombia.android.internal.e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Utils {
    public static final String ADE_DOMAIN = "adeDomain";
    public static final String COMMA = ",";
    public static final String CP_SERVER_DISABLE = "cpSDisable";
    public static final String DMP_AUDS = "audiences";
    public static final String DMP_AUDS_LAST_UPDATED = "alu";
    public static final String DMP_AUDS_UPDATE_REFRESH_TIME = "art";
    public static final String DMP_DOMAIN = "dmpDomain";
    public static final String DMP_INTERESTS = "interests";
    public static final String DMP_PREF = "ColombiaDMPPref";
    public static final long EXPIRY = 86400;
    public static final String FEED_JSON = "dmpFeedJson";
    public static final String FEED_PREF = "dmpFeeds";
    public static final String FEED_TIMESTAMP = "dmpFeedTs";
    public static final int HTTP_CONNECTION_TIMEOUT = 10000;
    public static final int HTTP_SOCKET_TIMEOUT = 10000;
    public static final String INSTALLED_APPS = "installed";
    public static final String IS_FIRST_PERSONA_EVENT_REPORTED = "fPersona";
    public static final String IS_PCR_REPORTED = "fPcr";
    public static final String LOG_TAG_VER = "dmp-aos:1.6.0";
    public static final String PARTNER_ID = "19844";
    public static final String PCR_DOMAIN = "pcrDomain";
    public static final String PERSONA_DISABLE = "pDisable";
    public static final String PERSONA_SERVER_DISABLE = "pSDisable";
    public static final String ROOT_CONFIG_TIMESTAMP = "configTs";
    public static final String SDK_VERSION = "aos:1.6.0";
    public static final String UNINSTALLED_APPS = "uninstalled";
    public static final String UPDATED_APPS = "updated";
    private static String aaid;
    private static int lite;

    public static boolean setPreferences(Context context, String str, String str2, String str3) {
        if (context == null || str == null || str2 == null || "".equals(str.trim()) || "".equals(str2.trim())) {
            Log.d(LOG_TAG_VER, "Failed to set preferences..App context NULL");
            return false;
        }
        Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putString(str2, str3);
        edit.apply();
        return true;
    }

    public static void setPreferences(Context context, String str, String str2, int i) {
        if (context == null || str == null || str2 == null || "".equals(str.trim()) || "".equals(str2.trim())) {
            Log.d(LOG_TAG_VER, "Failed to set preferences..App context NULL");
            return;
        }
        Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putInt(str2, i);
        edit.apply();
    }

    public static void setPreferences(Context context, String str, String str2, long j) {
        if (context == null || str == null || str2 == null || "".equals(str.trim()) || "".equals(str2.trim())) {
            Log.d(LOG_TAG_VER, "Failed to set preferences..App context NULL");
            return;
        }
        Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putLong(str2, j);
        edit.apply();
    }

    public static void setPreferences(Context context, String str, String str2, boolean z) {
        if (context == null || str == null || str2 == null || "".equals(str.trim()) || "".equals(str2.trim())) {
            Log.d(LOG_TAG_VER, "Failed to set preferences..App context NULL");
            return;
        }
        Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putBoolean(str2, z);
        edit.apply();
    }

    public static String getPreferences(Context context, String str, String str2) {
        if (context != null && str != null && str2 != null && !"".equals(str.trim()) && !"".equals(str2.trim())) {
            return context.getSharedPreferences(str, 0).getString(str2, null);
        }
        Log.d(LOG_TAG_VER, "Failed to get preferences..App context NULL");
        return null;
    }

    public static int getIntPreferences(Context context, String str, String str2) {
        if (context != null && str != null && str2 != null && !"".equals(str.trim()) && !"".equals(str2.trim())) {
            return context.getSharedPreferences(str, 0).getInt(str2, 0);
        }
        Log.d(LOG_TAG_VER, "Failed to get preferences..App context NULL");
        return 0;
    }

    public static long getLongPreferences(Context context, String str, String str2) {
        if (context != null && str != null && str2 != null && !"".equals(str.trim()) && !"".equals(str2.trim())) {
            return context.getSharedPreferences(str, 0).getLong(str2, 0);
        }
        Log.d(LOG_TAG_VER, "Failed to get preferences..App context NULL");
        return 0;
    }

    public static boolean getBooleanPreferences(Context context, String str, String str2) {
        if (context != null && str != null && str2 != null && !"".equals(str.trim()) && !"".equals(str2.trim())) {
            return context.getSharedPreferences(str, 0).getBoolean(str2, false);
        }
        Log.d(LOG_TAG_VER, "Failed to get preferences..App context NULL");
        return false;
    }

    public static void clearPref(Context context, String str) {
        context.getSharedPreferences(str, 0).edit().clear().apply();
    }

    public static String join(Collection<String> collection, String str) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (TextUtils.isEmpty((String) it.next())) {
                it.remove();
            }
        }
        Iterator it2 = collection.iterator();
        StringBuilder stringBuilder = new StringBuilder(it2.next().toString());
        while (it2.hasNext()) {
            String str2 = (String) it2.next();
            if (str2 != null) {
                stringBuilder.append(str);
                stringBuilder.append(str2);
            }
        }
        return stringBuilder.toString();
    }

    public static void setAAID(String str, Boolean bool) {
        aaid = str;
        if (bool.booleanValue()) {
            lite = 1;
        }
    }

    public static String getAAID(Context context) {
        if (TextUtils.isEmpty(aaid)) {
            AdvertisingIDUtil.retrieveAndSetAAID(context);
        }
        return aaid;
    }

    public static Integer getLite() {
        return Integer.valueOf(lite);
    }

    public static ApplicationInfo getApplicationInfo(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (NameNotFoundException unused) {
            return null;
        }
    }

    public static boolean checkNetworkAvailibility(Context context) {
        if (context == null) {
            Log.d(LOG_TAG_VER, "Context is null. Can not check network.");
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager.getActiveNetworkInfo() == null || !connectivityManager.getActiveNetworkInfo().isConnected()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            Log.d(LOG_TAG_VER, "Cannot find network state", e);
            return false;
        }
    }

    public static List<String> getInstalledApps(Context context) {
        List<ApplicationInfo> installedApplications = context.getPackageManager().getInstalledApplications(128);
        ArrayList arrayList = new ArrayList(installedApplications.size());
        for (ApplicationInfo applicationInfo : installedApplications) {
            arrayList.add(applicationInfo.packageName);
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x002c A:{SYNTHETIC, Splitter:B:22:0x002c} */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0026 A:{SYNTHETIC, Splitter:B:16:0x0026} */
    public static java.lang.String getStringFromInputStream(java.io.InputStream r4) {
        /*
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = 0;
        r2 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x002a, all -> 0x0023 }
        r3 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x002a, all -> 0x0023 }
        r3.<init>(r4);	 Catch:{ IOException -> 0x002a, all -> 0x0023 }
        r2.<init>(r3);	 Catch:{ IOException -> 0x002a, all -> 0x0023 }
    L_0x0010:
        r4 = r2.readLine();	 Catch:{ IOException -> 0x0021, all -> 0x001e }
        if (r4 == 0) goto L_0x001a;
    L_0x0016:
        r0.append(r4);	 Catch:{ IOException -> 0x0021, all -> 0x001e }
        goto L_0x0010;
    L_0x001a:
        r2.close();	 Catch:{ IOException -> 0x002f }
        goto L_0x002f;
    L_0x001e:
        r4 = move-exception;
        r1 = r2;
        goto L_0x0024;
    L_0x0021:
        r1 = r2;
        goto L_0x002a;
    L_0x0023:
        r4 = move-exception;
    L_0x0024:
        if (r1 == 0) goto L_0x0029;
    L_0x0026:
        r1.close();	 Catch:{ IOException -> 0x0029 }
    L_0x0029:
        throw r4;
    L_0x002a:
        if (r1 == 0) goto L_0x002f;
    L_0x002c:
        r1.close();	 Catch:{ IOException -> 0x002f }
    L_0x002f:
        r4 = r0.toString();
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.dmp.android.Utils.getStringFromInputStream(java.io.InputStream):java.lang.String");
    }

    public static String getRootConfigUrl(Context context) {
        String str;
        try {
            str = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).packageName;
        } catch (Exception unused) {
            str = null;
        }
        return new Builder().encodedPath("https://ade.clmbtech.com").appendEncodedPath("cde/sdk/config/rootConfig.htm").appendQueryParameter(e.A, str).appendQueryParameter(e.O, SDK_VERSION).build().toString();
    }

    public static boolean isRootConfigExpired(Context context) {
        long longPreferences = getLongPreferences(context, DMP_PREF, ROOT_CONFIG_TIMESTAMP);
        return longPreferences == 0 || System.currentTimeMillis() / 1000 >= longPreferences + 86400;
    }

    public static String getFeedUrl() {
        return new Builder().encodedPath(f.a()).appendEncodedPath(f.d).build().toString();
    }

    public static String getDmpUrl() {
        return new Builder().encodedPath(f.b()).appendEncodedPath("sdk").build().toString();
    }

    public static String getPsUrl() {
        return new Builder().encodedPath(f.a()).appendEncodedPath(f.f).build().toString();
    }

    public static String getAudUrl() {
        return new Builder().encodedPath(f.a()).appendEncodedPath(f.g).build().toString();
    }

    public static String getSsoSyncUrl() {
        return new Builder().encodedPath(f.a()).appendEncodedPath(f.h).build().toString();
    }

    public static boolean isMobileNetwork(Context context) {
        try {
            if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null) {
                        int type = activeNetworkInfo.getType();
                        activeNetworkInfo.getSubtype();
                        if (type == 0) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }
}
