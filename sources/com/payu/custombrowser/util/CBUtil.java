package com.payu.custombrowser.util;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.customtabs.CustomTabsService;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.Style;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.api.client.http.HttpMethods;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.payu.custombrowser.d.g;
import com.til.colombia.android.internal.e;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.entity.mime.MIME;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CBUtil {
    public static final String CB_PREFERENCE = "com.payu.custombrowser.payucustombrowser";
    private static SharedPreferences a;

    public static String getSystemCurrentTime() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    public static String getLogMessage(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        try {
            Object str62;
            Object str32;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("payu_id", getCookie(CBConstant.PAYUID, context));
            jSONObject.put(CBConstant.TXN_ID, str5);
            jSONObject.put("merchant_key", str4);
            str4 = "page_type";
            if (str62 == null) {
                str62 = "";
            }
            jSONObject.put(str4, str62);
            jSONObject.put("event_key", str);
            jSONObject.put("event_value", URLEncoder.encode(str2, "UTF-8"));
            str = "bank";
            if (str32 == null) {
                str32 = "";
            }
            jSONObject.put(str, str32);
            jSONObject.put(InMobiNetworkValues.PACKAGE_NAME, context.getPackageName());
            jSONObject.put(HlsSegmentFormat.TS, getSystemCurrentTime());
            return jSONObject.toString();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "{}";
        }
    }

    public static String decodeContents(FileInputStream fileInputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (true) {
            try {
                int read = fileInputStream.read();
                if (read == -1) {
                    break;
                }
                if (i % 2 == 0) {
                    stringBuilder.append((char) (read - ((i % 5) + 1)));
                } else {
                    stringBuilder.append((char) (read + ((i % 5) + 1)));
                }
                i++;
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        fileInputStream.close();
        return stringBuilder.toString();
    }

    public static void setAlpha(float f, View view) {
        if (VERSION.SDK_INT < 11) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(f, f);
            alphaAnimation.setDuration(10);
            alphaAnimation.setFillAfter(true);
            view.startAnimation(alphaAnimation);
            return;
        }
        view.setAlpha(f);
    }

    public static String updateLastUrl(String str) {
        try {
            if (!str.contains(CBConstant.CB_DELIMITER)) {
                return str.length() > 128 ? str.substring(0, 127) : str;
            } else {
                StringTokenizer stringTokenizer = new StringTokenizer(str, CBConstant.CB_DELIMITER);
                str = stringTokenizer.nextToken();
                String nextToken = stringTokenizer.nextToken();
                if (str.length() > 128) {
                    str = str.substring(0, 125);
                }
                if (nextToken.length() > 128) {
                    nextToken = nextToken.substring(0, 125);
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(CBConstant.CB_DELIMITER);
                stringBuilder.append(nextToken);
                return stringBuilder.toString();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    public static void setVariableReflection(String str, String str2, String str3) {
        if (str2 != null) {
            try {
                if (!str2.trim().equals("")) {
                    Field declaredField = Class.forName(str).getDeclaredField(str3);
                    declaredField.setAccessible(true);
                    declaredField.set(null, str2);
                    declaredField.setAccessible(false);
                }
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    public static String filterSMS(JSONObject jSONObject, String str, Context context) {
        Throwable e;
        String str2 = null;
        if (str == null) {
            return null;
        }
        try {
            if (!Pattern.compile(jSONObject.getString(context.getString(g.cb_detect_otp)), 2).matcher(str).find()) {
                return null;
            }
            for (CharSequence charSequence : jSONObject.getString(context.getString(g.cb_find_new_otp)).split("::")) {
                if (!TextUtils.isEmpty(charSequence)) {
                    Matcher matcher = Pattern.compile(charSequence, 2).matcher(str);
                    if (matcher.find()) {
                        String replaceAll = matcher.group().trim().replaceAll("\\.", "");
                        try {
                            if (!TextUtils.isEmpty(replaceAll)) {
                                return replaceAll;
                            }
                            str2 = replaceAll;
                        } catch (Exception e2) {
                            e = e2;
                            str2 = replaceAll;
                            ThrowableExtension.printStackTrace(e);
                            return str2;
                        }
                    }
                    continue;
                }
            }
            return str2;
        } catch (Exception e3) {
            e = e3;
            ThrowableExtension.printStackTrace(e);
            return str2;
        }
    }

    public HttpsURLConnection getHttpsConn(String str, String str2) {
        try {
            return getHttpsConn(str, str2, -1);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public HttpsURLConnection getHttpsConn(String str, String str2, int i, String str3) {
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
            httpsURLConnection.setRequestMethod(HttpMethods.POST);
            if (i != -1) {
                httpsURLConnection.setConnectTimeout(i);
            }
            httpsURLConnection.setRequestProperty(MIME.CONTENT_TYPE, "application/x-www-form-urlencoded");
            if (str2 != null) {
                httpsURLConnection.setRequestProperty("Content-Length", String.valueOf(str2.length()));
            }
            if (str3 != null) {
                httpsURLConnection.setRequestProperty("Cookie", str3);
            }
            httpsURLConnection.setSSLSocketFactory(new h());
            httpsURLConnection.setDoOutput(true);
            if (str2 != null) {
                httpsURLConnection.getOutputStream().write(str2.getBytes());
            }
            return httpsURLConnection;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public HttpsURLConnection getHttpsConn(String str, String str2, int i) {
        return getHttpsConn(str, str2, i, null);
    }

    public static HttpsURLConnection getHttpsConn(String str) {
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
            httpsURLConnection.setRequestMethod(HttpMethods.GET);
            httpsURLConnection.setSSLSocketFactory(new h());
            httpsURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
            return httpsURLConnection;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static StringBuffer getStringBufferFromInputStream(InputStream inputStream) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return stringBuffer;
                }
                stringBuffer.append(new String(bArr, 0, read));
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected();
    }

    public static List<String> updateRetryData(String str, Context context) {
        a(str, context);
        return processAndAddWhiteListedUrls(str);
    }

    private static void a(String str, Context context) {
        if (str == null) {
            g.a(context, CBConstant.SP_RETRY_FILE_NAME, CBConstant.SP_RETRY_WHITELISTED_URLS, "");
        } else {
            g.a(context, CBConstant.SP_RETRY_FILE_NAME, CBConstant.SP_RETRY_WHITELISTED_URLS, str);
        }
        c.a("#### PAYU", "DATA UPDATED IN SHARED PREFERENCES");
    }

    public void clearCookie() {
        CookieManager instance = CookieManager.getInstance();
        if (VERSION.SDK_INT >= 21) {
            instance.removeSessionCookies(null);
        } else {
            instance.removeSessionCookie();
        }
    }

    public static List<String> processAndAddWhiteListedUrls(String str) {
        if (!(str == null || str.equalsIgnoreCase(""))) {
            String[] split = str.split("\\|");
            for (String str2 : split) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Split Url: ");
                stringBuilder.append(str2);
                c.a("#### PAYU", stringBuilder.toString());
            }
            if (split != null && split.length > 0) {
                return Arrays.asList(split);
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Whitelisted URLs from JS: ");
            stringBuilder2.append(str);
            c.a("#### PAYU", stringBuilder2.toString());
        }
        return new ArrayList();
    }

    public boolean getBooleanSharedPreference(String str, Context context) {
        a = context.getSharedPreferences(CB_PREFERENCE, 0);
        return a.getBoolean(str, false);
    }

    public boolean getBooleanSharedPreferenceDefaultTrue(String str, Context context) {
        a = context.getSharedPreferences(CB_PREFERENCE, 0);
        return a.getBoolean(str, true);
    }

    public void setBooleanSharedPreference(String str, boolean z, Context context) {
        Editor edit = context.getSharedPreferences(CB_PREFERENCE, 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public String getDeviceDensity(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(displayMetrics.densityDpi);
        stringBuilder.append("");
        return stringBuilder.toString();
    }

    public String getNetworkStatus(Context context) {
        if (context != null) {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    if (activeNetworkInfo.isConnected()) {
                        if (activeNetworkInfo.getType() == 1) {
                            return "WIFI";
                        }
                        if (activeNetworkInfo.getType() == 0) {
                            switch (activeNetworkInfo.getSubtype()) {
                                case 1:
                                    return "GPRS";
                                case 2:
                                    return "EDGE";
                                case 3:
                                case 5:
                                case 6:
                                case 8:
                                case 9:
                                case 10:
                                    return "HSPA";
                                case 4:
                                    return "CDMA";
                                case 7:
                                case 11:
                                    return "2G";
                                case 12:
                                case 14:
                                case 15:
                                    return "3G";
                                case 13:
                                    return "4G";
                                default:
                                    return "?";
                            }
                        }
                    }
                }
                return "Not connected";
            } catch (Exception unused) {
                return "?";
            }
        }
        return "?";
    }

    public NetworkInfo getNetWorkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        int i = 0;
        NetworkInfo networkInfo = null;
        if (VERSION.SDK_INT >= 21) {
            Network[] allNetworks = connectivityManager.getAllNetworks();
            int length = allNetworks.length;
            while (i < length) {
                NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(allNetworks[i]);
                if (networkInfo2.getState().equals(State.CONNECTED)) {
                    networkInfo = networkInfo2;
                }
                i++;
            }
        } else {
            NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
            if (allNetworkInfo != null) {
                int length2 = allNetworkInfo.length;
                while (i < length2) {
                    NetworkInfo networkInfo3 = allNetworkInfo[i];
                    if (networkInfo3.getState() == State.CONNECTED) {
                        networkInfo = networkInfo3;
                    }
                    i++;
                }
            }
        }
        return networkInfo;
    }

    public int getNetworkStrength(Context context) {
        NetworkInfo netWorkInfo = getNetWorkInfo(context);
        if (netWorkInfo == null) {
            return 0;
        }
        if (netWorkInfo.getTypeName().equalsIgnoreCase("MOBILE")) {
            return a(context, netWorkInfo);
        }
        if (netWorkInfo.getTypeName().equalsIgnoreCase(e.ad) && a(context, "android.permission.ACCESS_WIFI_STATE")) {
            try {
                WifiInfo connectionInfo = ((WifiManager) context.getSystemService(e.ad)).getConnectionInfo();
                if (connectionInfo != null) {
                    return WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 5);
                }
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        return 0;
    }

    private boolean a(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    private int a(Context context, NetworkInfo networkInfo) {
        int i = 0;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (VERSION.SDK_INT >= 18) {
                int i2 = 0;
                for (CellInfo cellInfo : telephonyManager.getAllCellInfo()) {
                    if (cellInfo.isRegistered()) {
                        if (cellInfo instanceof CellInfoGsm) {
                            i2 = ((CellInfoGsm) cellInfo).getCellSignalStrength().getDbm();
                        } else if (cellInfo instanceof CellInfoCdma) {
                            i2 = ((CellInfoCdma) cellInfo).getCellSignalStrength().getDbm();
                        } else if (cellInfo instanceof CellInfoLte) {
                            i2 = ((CellInfoLte) cellInfo).getCellSignalStrength().getDbm();
                        } else if (cellInfo instanceof CellInfoWcdma) {
                            i2 = ((CellInfoWcdma) cellInfo).getCellSignalStrength().getDbm();
                        }
                    }
                }
                i = i2;
            }
            return i;
        } catch (Exception unused) {
            return 0;
        }
    }

    public void setStringSharedPreferenceLastURL(Context context, String str, String str2) {
        String stringSharedPreference = getStringSharedPreference(context, str);
        if (!stringSharedPreference.equalsIgnoreCase("")) {
            if (stringSharedPreference.contains(CBConstant.CB_DELIMITER)) {
                StringTokenizer stringTokenizer = new StringTokenizer(stringSharedPreference, CBConstant.CB_DELIMITER);
                stringTokenizer.nextToken();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(stringTokenizer.nextToken());
                stringBuilder.append(CBConstant.CB_DELIMITER);
                stringBuilder.append(str2);
                str2 = stringBuilder.toString();
            } else {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(stringSharedPreference);
                stringBuilder2.append(CBConstant.CB_DELIMITER);
                stringBuilder2.append(str2);
                str2 = stringBuilder2.toString();
            }
        }
        storeInSharedPreferences(context, str, str2);
    }

    public String getStringSharedPreference(Context context, String str) {
        return context.getSharedPreferences(CB_PREFERENCE, 0).getString(str, "");
    }

    public void setStringSharedPreference(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences(CB_PREFERENCE, 0).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public void deleteSharedPrefKey(Context context, String str) {
        try {
            Editor edit = context.getSharedPreferences(CB_PREFERENCE, 0).edit();
            edit.remove(str);
            edit.apply();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void storeInSharedPreferences(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences(CB_PREFERENCE, 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public void removeFromSharedPreferences(Context context, String str) {
        Editor edit = context.getSharedPreferences(CB_PREFERENCE, 0).edit();
        edit.remove(str);
        edit.apply();
    }

    public Drawable getDrawableCB(Context context, int i) {
        if (VERSION.SDK_INT < 21) {
            return context.getResources().getDrawable(i);
        }
        return context.getResources().getDrawable(i, context.getTheme());
    }

    public void cancelTimer(Timer timer) {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    public String readFileInputStream(Context context, String str, int i) {
        String str2 = "";
        try {
            if (!new File(context.getFilesDir(), str).exists()) {
                context.openFileOutput(str, i);
            }
            FileInputStream openFileInput = context.openFileInput(str);
            while (true) {
                int read = openFileInput.read();
                if (read == -1) {
                    break;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str2);
                stringBuilder.append(Character.toString((char) read));
                str2 = stringBuilder.toString();
            }
            openFileInput.close();
        } catch (FileNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (IOException e2) {
            ThrowableExtension.printStackTrace(e2);
        } catch (Exception e3) {
            ThrowableExtension.printStackTrace(e3);
        }
        return str2;
    }

    public void writeFileOutputStream(InputStream inputStream, Context context, String str, int i) {
        try {
            GZIPInputStream gZIPInputStream = new GZIPInputStream(inputStream);
            byte[] bArr = new byte[1024];
            FileOutputStream openFileOutput = context.openFileOutput(str, i);
            while (true) {
                int read = gZIPInputStream.read(bArr);
                if (read > 0) {
                    openFileOutput.write(bArr, 0, read);
                } else {
                    gZIPInputStream.close();
                    openFileOutput.close();
                    return;
                }
            }
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (Exception e2) {
            ThrowableExtension.printStackTrace(e2);
        }
    }

    public void resetPayuID() {
        clearCookie();
    }

    public String getCookieList(Context context, String str) {
        Throwable e;
        String str2 = "";
        String str3;
        try {
            CookieManager instance = CookieManager.getInstance();
            if (VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(context);
                CookieSyncManager.getInstance().sync();
            }
            String cookie = instance.getCookie(str);
            if (cookie != null) {
                String[] split = cookie.split(";");
                int length = split.length;
                str3 = str2;
                int i = 0;
                while (i < length) {
                    try {
                        String[] split2 = split[i].split("=");
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(str3);
                        stringBuilder.append(split2[0]);
                        stringBuilder.append("=");
                        stringBuilder.append(split2[1]);
                        stringBuilder.append(";");
                        i++;
                        str3 = stringBuilder.toString();
                    } catch (Exception e2) {
                        e = e2;
                        ThrowableExtension.printStackTrace(e);
                        return str3;
                    }
                }
                str2 = str3;
            }
            return str2.length() > 0 ? str2.substring(0, str2.length() - 1) : str2;
        } catch (Exception e3) {
            e = e3;
            str3 = str2;
            ThrowableExtension.printStackTrace(e);
            return str3;
        }
    }

    public static String getCookie(String str, Context context) {
        String str2 = "";
        try {
            String str3 = "https://secure.payu.in";
            CookieManager instance = CookieManager.getInstance();
            if (VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(context);
                CookieSyncManager.getInstance().sync();
            }
            String cookie = instance.getCookie(str3);
            if (cookie != null) {
                for (String str4 : cookie.split(";")) {
                    if (str4.contains(str)) {
                        str2 = str4.split("=")[1];
                    }
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        return str2;
    }

    @Deprecated
    public String getDataFromPostData(String str, String str2) {
        for (String split : str.split("&")) {
            String[] split2 = split.split("=");
            if (split2.length >= 2 && split2[0].equalsIgnoreCase(str2)) {
                return split2[1];
            }
        }
        return "";
    }

    public HashMap<String, String> getDataFromPostData(String str) {
        HashMap hashMap = new HashMap();
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, "&");
            while (stringTokenizer.hasMoreTokens()) {
                String[] split = stringTokenizer.nextToken().split("=");
                if (!(split == null || split.length <= 0 || split[0] == null)) {
                    hashMap.put(split[0], split.length > 1 ? split[1] : "");
                }
            }
        }
        return hashMap;
    }

    public void showNotification(Context context, Intent intent, String str, String str2, int i, boolean z, Style style, int i2, int i3) {
        Builder builder = new Builder(context);
        builder.setContentTitle(str).setContentText(str2).setSmallIcon(i).setPriority(1).setDefaults(2);
        if (z) {
            builder.setAutoCancel(z);
        }
        if (style != null) {
            builder.setStyle(style);
        }
        if (i2 != -1) {
            builder.setColor(i2);
        }
        builder.setContentIntent(PendingIntent.getActivity(context, 0, intent, 134217728));
        ((NotificationManager) context.getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION)).notify(i3, builder.build());
    }

    public SnoozeConfigMap storeSnoozeConfigInSharedPref(Context context, String str) {
        SnoozeConfigMap a;
        Throwable e;
        SnoozeConfigMap snoozeConfigMap = new SnoozeConfigMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            g.b(context, CBConstant.SNOOZE_SHARED_PREF);
            a = a(context, jSONObject.getJSONArray(CBConstant.DEFAULT_VALUE), snoozeConfigMap);
            try {
                jSONObject.remove(CBConstant.DEFAULT_VALUE);
                Iterator keys = jSONObject.keys();
                if (keys.hasNext()) {
                    return a(context, jSONObject.getJSONArray((String) keys.next()), a);
                }
            } catch (JSONException e2) {
                e = e2;
                ThrowableExtension.printStackTrace(e);
                return a;
            }
        } catch (JSONException e3) {
            e = e3;
            a = snoozeConfigMap;
            ThrowableExtension.printStackTrace(e);
            return a;
        }
        return a;
    }

    private SnoozeConfigMap a(Context context, JSONArray jSONArray, SnoozeConfigMap snoozeConfigMap) {
        try {
            int length = jSONArray.length();
            int i = 0;
            int i2 = 0;
            while (i < length) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String obj = jSONObject.get("url").toString();
                String obj2 = jSONObject.get(CBConstant.PROGRESS_PERCENT).toString();
                String obj3 = jSONObject.get(CBConstant.TIME_OUT).toString();
                if (jSONObject.has(CBConstant.DISABLE_SP_FOR)) {
                    i2 = a(jSONObject.getJSONObject(CBConstant.DISABLE_SP_FOR));
                }
                StringTokenizer stringTokenizer = new StringTokenizer(obj, CBConstant.CB_DELIMITER);
                while (stringTokenizer.hasMoreTokens()) {
                    obj = stringTokenizer.nextToken();
                    String str = CBConstant.SNOOZE_SHARED_PREF;
                    String trim = obj.contentEquals(CBConstant.DEFAULT_PAYMENT_URLS) ? CBConstant.DEFAULT_PAYMENT_URLS : obj.trim();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(obj2.trim());
                    stringBuilder.append(CBConstant.CB_DELIMITER);
                    stringBuilder.append(obj3.trim());
                    stringBuilder.append(CBConstant.CB_DELIMITER);
                    stringBuilder.append(i2);
                    g.a(context, str, trim, stringBuilder.toString());
                    Object trim2 = obj.contentEquals(CBConstant.DEFAULT_PAYMENT_URLS) ? CBConstant.DEFAULT_PAYMENT_URLS : obj.trim();
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(obj2.trim());
                    stringBuilder2.append(CBConstant.CB_DELIMITER);
                    stringBuilder2.append(obj3.trim());
                    stringBuilder2.append(CBConstant.CB_DELIMITER);
                    stringBuilder2.append(i2);
                    snoozeConfigMap.put(trim2, stringBuilder2.toString());
                }
                i++;
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return snoozeConfigMap;
    }

    public SnoozeConfigMap convertToSnoozeConfigMap(Map<String, ?> map) {
        SnoozeConfigMap snoozeConfigMap = new SnoozeConfigMap();
        for (Entry entry : map.entrySet()) {
            snoozeConfigMap.put(entry.getKey(), entry.getValue());
        }
        return snoozeConfigMap;
    }

    public Set<String> getSurePayErrorCodes() {
        HashSet hashSet = new HashSet();
        hashSet.add("-7");
        hashSet.add("-8");
        hashSet.add("-15");
        return hashSet;
    }

    private int a(JSONObject jSONObject) {
        try {
            if (jSONObject.has(CBConstant.WARN) && jSONObject.getBoolean(CBConstant.WARN) && jSONObject.has(CBConstant.FAIL) && jSONObject.getBoolean(CBConstant.FAIL)) {
                return 3;
            }
            if (jSONObject.has(CBConstant.FAIL) && jSONObject.getBoolean(CBConstant.FAIL)) {
                return 2;
            }
            if (jSONObject.has(CBConstant.WARN) && jSONObject.getBoolean(CBConstant.WARN)) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return 0;
        }
    }

    public int getSurePayDisableStatus(SnoozeConfigMap snoozeConfigMap, String str) {
        if (snoozeConfigMap == null || str == null) {
            return 0;
        }
        for (Object next : snoozeConfigMap.keySet()) {
            if (str.startsWith(next.toString())) {
                return snoozeConfigMap.getPercentageAndTimeout(next.toString())[2];
            }
        }
        return snoozeConfigMap.getPercentageAndTimeout(CBConstant.DEFAULT_PAYMENT_URLS)[2];
    }

    public String getValueOfJSONKey(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.has(str2)) {
            return jSONObject.get(str2).toString();
        }
        throw new JSONException("Key not found");
    }

    public static void launchPlayStore(Context context) {
        launchPlayStore(context, null, null);
    }

    public static void launchPlayStore(Context context, String str, String str2) {
        str = getPackageNameFromPlayStoreLink(str);
        if (str == null) {
            str = "";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("details?id=");
            stringBuilder.append(str);
            str = stringBuilder.toString();
        }
        setWebViewVersionInSP(context, str2);
        StringBuilder stringBuilder2;
        try {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(CBConstant.PLAY_STORE_MARKET_URI);
            stringBuilder2.append(str);
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder2.toString())));
        } catch (ActivityNotFoundException unused) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("https://play.google.com/store/apps/");
            stringBuilder2.append(str);
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder2.toString())));
        }
    }

    public static boolean isPlayStoreUrl(String str) {
        return str.startsWith(CBConstant.PLAY_STORE_URL) || str.startsWith(CBConstant.PLAY_STORE_MARKET_URI);
    }

    public static String getPackageNameFromPlayStoreLink(String str) {
        Matcher matcher = Pattern.compile("((?<=[?&]id=)[^&]+)").matcher(str);
        return matcher.find() ? matcher.group(1) : null;
    }

    public static String getWebViewVersion(WebView webView) {
        Matcher matcher = Pattern.compile("(Chrome\\/(.*?)\\s)").matcher(webView.getSettings().getUserAgentString());
        return matcher.find() ? matcher.group(2) : null;
    }

    public static void setWebViewVersionInSP(Context context, String str) {
        if (str != null) {
            g.a(context, CB_PREFERENCE, CBConstant.WEBVIEW_VERSION, str);
        }
    }

    public static String getWebViewVersionFromSP(Context context) {
        return g.b(context, CB_PREFERENCE, CBConstant.WEBVIEW_VERSION, "");
    }

    public static String getBase64DecodedString(String str) {
        return new String(Base64.decode(str, 0));
    }

    public static String getImei(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception unused) {
            return CBConstant.DEFAULT_VALUE;
        }
    }

    public static String getUdid(Context context) {
        try {
            return Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception unused) {
            return CBConstant.DEFAULT_VALUE;
        }
    }

    public static boolean isSPModuleAvailable() {
        try {
            CBUtil.class.getClassLoader().loadClass("com.payu.samsungpay.SamsungWrapper");
            return true;
        } catch (ClassNotFoundException unused) {
            Log.e("CBUtil", "Please import com.payu.samsungpay to make Payment by Samsung Pay");
            return false;
        }
    }

    public static boolean isCustomTabSupported(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
        if (resolveActivity != null) {
            String str = resolveActivity.activityInfo.packageName;
        }
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            Intent intent2 = new Intent();
            intent2.setAction(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
            intent2.setPackage(resolveInfo.activityInfo.packageName);
            if (packageManager.resolveService(intent2, 0) != null) {
                arrayList.add(resolveInfo.activityInfo.packageName);
            }
        }
        if (!arrayList.isEmpty() && arrayList.contains("com.android.chrome")) {
            return true;
        }
        return false;
    }
}
