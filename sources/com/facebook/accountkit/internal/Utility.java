package com.facebook.accountkit.internal;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.support.v4.content.PermissionChecker;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.util.Patterns;
import android.view.Display;
import android.view.WindowManager;
import com.comscore.utils.Constants;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitError.Type;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class Utility {
    private static final ScheduledThreadPoolExecutor BACKGROUND_EXECUTOR = new ScheduledThreadPoolExecutor(1);
    private static final String EXTRA_APP_EVENTS_INFO_FORMAT_VERSION = "a2";
    private static final String HASH_ALGORITHM_SHA1 = "SHA-1";
    private static final String NO_CARRIER = "NoCarrier";
    private static final int REFRESH_TIME_FOR_EXTENDED_DEVICE_INFO_MILLIS = 1800000;
    private static final String TAG = "com.facebook.accountkit.internal.Utility";
    private static long availableExternalStorageGB = -1;
    private static String carrierName = "NoCarrier";
    private static String deviceTimezone = "";
    private static int numCPUCores = 0;
    private static long timestampOfLastCheck = -1;
    private static long totalExternalStorageGB = -1;

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    static ScheduledThreadPoolExecutor getBackgroundExecutor() {
        return BACKGROUND_EXECUTOR;
    }

    public static Executor getThreadPoolExecutor() {
        return AccountKit.getExecutor();
    }

    static boolean isDebuggable(Context context) {
        boolean z = false;
        try {
            if ((context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.flags & 2) != 0) {
                z = true;
            }
            return z;
        } catch (NameNotFoundException unused) {
            return false;
        }
    }

    static Object getStringPropertyAsJSON(JSONObject jSONObject, String str) throws JSONException {
        Object opt = jSONObject.opt(str);
        return (opt == null || !(opt instanceof String)) ? opt : new JSONTokener((String) opt).nextValue();
    }

    public static boolean hasReceiveSmsPermissions(@NonNull Context context) {
        return hasPermission(context, "android.permission.RECEIVE_SMS");
    }

    static boolean hasReadPhoneStatePermissions(@NonNull Context context) {
        return hasPermission(context, "android.permission.READ_PHONE_STATE");
    }

    static boolean hasGetAccountsPermissions(@NonNull Context context) {
        return hasPermission(context, "android.permission.GET_ACCOUNTS");
    }

    private static boolean hasPermission(Context context, String str) {
        boolean z = false;
        try {
            if (PermissionChecker.checkCallingOrSelfPermission(context, str) == 0) {
                z = true;
            }
            return z;
        } catch (Exception unused) {
            return false;
        }
    }

    public static List<String> getDeviceEmailsIfAvailable(Context context) {
        if (!hasGetAccountsPermissions(context)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Account account : AccountManager.get(context).getAccounts()) {
            if (!(isNullOrEmpty(account.name) || !Patterns.EMAIL_ADDRESS.matcher(account.name).matches() || arrayList.contains(account.name))) {
                arrayList.add(account.name);
            }
        }
        return arrayList;
    }

    @SuppressLint({"HardwareIds"})
    public static String readPhoneNumberIfAvailable(Context context, String str) {
        if (hasReadPhoneStatePermissions(context)) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return cleanUpPhoneNumberIfPossible(telephonyManager.getLine1Number(), str);
            }
        }
        return null;
    }

    public static String cleanPhoneNumberString(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        return str.replaceAll("[^\\d]", "");
    }

    static String cleanUpPhoneNumberIfPossible(String str, String str2) {
        if (isNullOrEmpty(str) || isNullOrEmpty(str2)) {
            return str;
        }
        str = str.replace("+", "");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("^");
        stringBuilder.append(str2);
        return str.replaceFirst(stringBuilder.toString(), "");
    }

    static void putNonNullString(Bundle bundle, String str, String str2) {
        if (bundle != null && str != null && str2 != null) {
            bundle.putString(str, str2);
        }
    }

    static String readStreamToString(InputStream inputStream) throws IOException {
        Throwable th;
        Closeable bufferedInputStream;
        Closeable inputStreamReader;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                inputStreamReader = new InputStreamReader(bufferedInputStream);
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    char[] cArr = new char[2048];
                    while (true) {
                        int read = inputStreamReader.read(cArr);
                        if (read != -1) {
                            stringBuilder.append(cArr, 0, read);
                        } else {
                            String stringBuilder2 = stringBuilder.toString();
                            closeQuietly(bufferedInputStream);
                            closeQuietly(inputStreamReader);
                            return stringBuilder2;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    closeQuietly(bufferedInputStream);
                    closeQuietly(inputStreamReader);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStreamReader = null;
                closeQuietly(bufferedInputStream);
                closeQuietly(inputStreamReader);
                throw th;
            }
        } catch (Throwable th32) {
            bufferedInputStream = null;
            th = th32;
            inputStreamReader = bufferedInputStream;
            closeQuietly(bufferedInputStream);
            closeQuietly(inputStreamReader);
            throw th;
        }
    }

    static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static String getCurrentCountry(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String simCountryIso = telephonyManager.getSimCountryIso();
            if (simCountryIso != null && simCountryIso.length() == 2) {
                return simCountryIso.toLowerCase(Locale.US);
            }
            if (telephonyManager.getPhoneType() != 2) {
                String networkCountryIso = telephonyManager.getNetworkCountryIso();
                if (networkCountryIso != null && networkCountryIso.length() == 2) {
                    return networkCountryIso.toLowerCase(Locale.US);
                }
            }
            return null;
        } catch (Exception unused) {
        }
    }

    static void disconnectQuietly(URLConnection uRLConnection) {
        if (uRLConnection instanceof HttpURLConnection) {
            ((HttpURLConnection) uRLConnection).disconnect();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002e  */
    static int copyAndCloseInputStream(java.io.InputStream r6, java.io.OutputStream r7) throws java.io.IOException {
        /*
        r0 = 0;
        r1 = new java.io.BufferedInputStream;	 Catch:{ all -> 0x0025 }
        r1.<init>(r6);	 Catch:{ all -> 0x0025 }
        r0 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r0 = new byte[r0];	 Catch:{ all -> 0x0023 }
        r2 = 0;
        r3 = r2;
    L_0x000c:
        r4 = r1.read(r0);	 Catch:{ all -> 0x0023 }
        r5 = -1;
        if (r4 == r5) goto L_0x0018;
    L_0x0013:
        r7.write(r0, r2, r4);	 Catch:{ all -> 0x0023 }
        r3 = r3 + r4;
        goto L_0x000c;
    L_0x0018:
        if (r1 == 0) goto L_0x001d;
    L_0x001a:
        r1.close();
    L_0x001d:
        if (r6 == 0) goto L_0x0022;
    L_0x001f:
        r6.close();
    L_0x0022:
        return r3;
    L_0x0023:
        r7 = move-exception;
        goto L_0x0027;
    L_0x0025:
        r7 = move-exception;
        r1 = r0;
    L_0x0027:
        if (r1 == 0) goto L_0x002c;
    L_0x0029:
        r1.close();
    L_0x002c:
        if (r6 == 0) goto L_0x0031;
    L_0x002e:
        r6.close();
    L_0x0031:
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.Utility.copyAndCloseInputStream(java.io.InputStream, java.io.OutputStream):int");
    }

    static boolean notEquals(Object obj, Object obj2) {
        return obj == null || !obj.equals(obj2);
    }

    public static <T> boolean areObjectsEqual(T t, T t2) {
        if (t != null) {
            return t.equals(t2);
        }
        return t2 == null;
    }

    public static int getHashCode(Object obj) {
        return obj == null ? 0 : obj.hashCode();
    }

    static String sha1hash(byte[] bArr) {
        try {
            return hashBytes(MessageDigest.getInstance(HASH_ALGORITHM_SHA1), bArr);
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    private static String hashBytes(MessageDigest messageDigest, byte[] bArr) {
        messageDigest.update(bArr);
        byte[] digest = messageDigest.digest();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : digest) {
            stringBuilder.append(Integer.toHexString((b >> 4) & 15));
            stringBuilder.append(Integer.toHexString(b & 15));
        }
        return stringBuilder.toString();
    }

    static String getMetadataApplicationId() {
        return AccountKit.getApplicationId();
    }

    static void logd(String str, Exception exception) {
        if (str != null && exception != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(exception.getClass().getSimpleName());
            stringBuilder.append(": ");
            stringBuilder.append(exception.getMessage());
            Log.d(str, stringBuilder.toString());
        }
    }

    static void logd(String str, String str2, Throwable th) {
        if (!isNullOrEmpty(str)) {
            Log.d(str, str2, th);
        }
    }

    static void assertUIThread() {
        if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            Log.w(TAG, "This method should be called from the UI thread");
        }
    }

    static Pair<AccountKitError, InternalAccountKitError> createErrorFromServerError(AccountKitRequestError accountKitRequestError) {
        Object accountKitError;
        int errorCode = accountKitRequestError.getErrorCode();
        if (accountKitRequestError.getSubErrorCode() == InternalAccountKitError.INVALID_PHONE_NUMBER_SERVER_CODE) {
            errorCode = InternalAccountKitError.INVALID_PHONE_NUMBER;
        }
        InternalAccountKitError internalAccountKitError = new InternalAccountKitError(errorCode, accountKitRequestError.getErrorMessage(), accountKitRequestError.getUserErrorMessage());
        int errorCode2 = accountKitRequestError.getErrorCode();
        if (errorCode2 != InternalAccountKitError.INVALID_CONFIRMATION_CODE) {
            switch (errorCode2) {
                case 100:
                    accountKitError = new AccountKitError(Type.ARGUMENT_ERROR, internalAccountKitError);
                    break;
                case 101:
                    accountKitError = new AccountKitError(Type.NETWORK_CONNECTION_ERROR, internalAccountKitError);
                    break;
                default:
                    switch (errorCode2) {
                        case InternalAccountKitError.LOGIN_REQUEST_EXPIRED /*1948001*/:
                            accountKitError = new AccountKitError(Type.LOGIN_INVALIDATED, internalAccountKitError);
                            break;
                        case InternalAccountKitError.INVALID_CREDENTIALS_OR_LOGIN_REQUEST /*1948002*/:
                            accountKitError = new AccountKitError(Type.ARGUMENT_ERROR, internalAccountKitError);
                            break;
                        case InternalAccountKitError.TOO_MANY_ATTEMPTS /*1948003*/:
                            accountKitError = new AccountKitError(Type.SERVER_ERROR, internalAccountKitError);
                            break;
                        default:
                            accountKitError = new AccountKitError(Type.SERVER_ERROR, internalAccountKitError);
                            break;
                    }
            }
        }
        accountKitError = new AccountKitError(Type.ARGUMENT_ERROR, internalAccountKitError);
        return new Pair(accountKitError, internalAccountKitError);
    }

    static boolean isConfirmationCodeRetryable(InternalAccountKitError internalAccountKitError) {
        return internalAccountKitError != null && internalAccountKitError.getCode() == InternalAccountKitError.INVALID_CONFIRMATION_CODE;
    }

    static void setAppEventAttributionParameters(JSONObject jSONObject, String str) throws JSONException {
        jSONObject.put("anon_id", str);
    }

    static void setAppEventExtendedDeviceInfoParameters(JSONObject jSONObject, Context context) throws JSONException {
        int i;
        Locale locale;
        int i2;
        int i3;
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(EXTRA_APP_EVENTS_INFO_FORMAT_VERSION);
        refreshPeriodicExtendedDeviceInfo(context);
        String packageName = context.getPackageName();
        Object obj = "";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            i = packageInfo.versionCode;
            try {
                obj = packageInfo.versionName;
            } catch (NameNotFoundException unused) {
            }
        } catch (NameNotFoundException unused2) {
            i = -1;
        }
        jSONArray.put(packageName);
        jSONArray.put(i);
        jSONArray.put(obj);
        jSONArray.put(VERSION.RELEASE);
        jSONArray.put(Build.MODEL);
        try {
            locale = context.getResources().getConfiguration().locale;
        } catch (Exception unused3) {
            locale = Locale.getDefault();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(locale.getLanguage());
        stringBuilder.append("_");
        stringBuilder.append(locale.getCountry());
        jSONArray.put(stringBuilder.toString());
        jSONArray.put(deviceTimezone);
        jSONArray.put(carrierName);
        double d = 0.0d;
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager != null) {
                Display defaultDisplay = windowManager.getDefaultDisplay();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                defaultDisplay.getMetrics(displayMetrics);
                i2 = displayMetrics.widthPixels;
                try {
                    i3 = displayMetrics.heightPixels;
                    try {
                        d = (double) displayMetrics.density;
                    } catch (Exception unused4) {
                    }
                } catch (Exception unused5) {
                    i3 = 0;
                }
                jSONArray.put(i2);
                jSONArray.put(i3);
                jSONArray.put(String.format(Locale.ENGLISH, "%.2f", new Object[]{Double.valueOf(d)}));
                jSONArray.put(refreshBestGuessNumberOfCPUCores());
                jSONArray.put(totalExternalStorageGB);
                jSONArray.put(availableExternalStorageGB);
                jSONObject.put("extinfo", jSONArray.toString());
            }
        } catch (Exception unused6) {
        }
        i2 = 0;
        i3 = i2;
        jSONArray.put(i2);
        jSONArray.put(i3);
        jSONArray.put(String.format(Locale.ENGLISH, "%.2f", new Object[]{Double.valueOf(d)}));
        jSONArray.put(refreshBestGuessNumberOfCPUCores());
        jSONArray.put(totalExternalStorageGB);
        jSONArray.put(availableExternalStorageGB);
        jSONObject.put("extinfo", jSONArray.toString());
    }

    public static String getRedirectURL() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ak");
        stringBuilder.append(AccountKit.getApplicationId());
        stringBuilder.append("://authorize");
        return stringBuilder.toString();
    }

    private static int refreshBestGuessNumberOfCPUCores() {
        if (numCPUCores > 0) {
            return numCPUCores;
        }
        try {
            numCPUCores = new File("/sys/devices/system/cpu/").listFiles(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    return Pattern.matches("cpu[0-9]+", str);
                }
            }).length;
        } catch (Exception unused) {
        }
        if (numCPUCores <= 0) {
            numCPUCores = Math.max(Runtime.getRuntime().availableProcessors(), 1);
        }
        return numCPUCores;
    }

    private static void refreshPeriodicExtendedDeviceInfo(Context context) {
        if (timestampOfLastCheck == -1 || System.currentTimeMillis() - timestampOfLastCheck >= Constants.SESSION_INACTIVE_PERIOD) {
            timestampOfLastCheck = System.currentTimeMillis();
            refreshTimezone();
            refreshCarrierName(context);
            refreshTotalExternalStorage();
            refreshAvailableExternalStorage();
        }
    }

    private static void refreshTimezone() {
        try {
            TimeZone timeZone = TimeZone.getDefault();
            deviceTimezone = timeZone.getDisplayName(timeZone.inDaylightTime(new Date()), 0);
        } catch (Exception unused) {
        }
    }

    private static void refreshCarrierName(Context context) {
        if (carrierName.equals(NO_CARRIER)) {
            try {
                carrierName = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
            } catch (Exception unused) {
            }
        }
    }

    private static void refreshAvailableExternalStorage() {
        try {
            if (externalStorageExists()) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                availableExternalStorageGB = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
            }
            availableExternalStorageGB = convertBytesToGB((double) availableExternalStorageGB);
        } catch (Exception unused) {
        }
    }

    private static void refreshTotalExternalStorage() {
        try {
            if (externalStorageExists()) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                totalExternalStorageGB = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
            }
            totalExternalStorageGB = convertBytesToGB((double) totalExternalStorageGB);
        } catch (Exception unused) {
        }
    }

    private static boolean externalStorageExists() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    private static long convertBytesToGB(double d) {
        return Math.round(d / 1.073741824E9d);
    }
}
