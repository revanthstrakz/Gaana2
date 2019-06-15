package com.comscore.android.id;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Looper;
import android.provider.Settings.Secure;
import com.gaana.cardoption.AssetsHelper.CARD;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class IdHelperAndroid {
    public static final String[] INVALID_ID_VALUES = new String[]{"0123456789ABCDEF", "0123456789abcdef", "9774d56d682e549c", "9774D56D682E549C", "unknown", CARD.UNKNOWN, "android_id", "ANDROID_ID"};
    public static final String NO_ID_AVAILABLE = "none";
    private static final String a = "com.google.android.gms";
    private static final String b = "com.google.android.gms.ads.identifier.service.START";
    private static boolean c = false;
    private static final boolean d = false;

    private static String a(Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot be called from the main thread");
        }
        String str = "";
        b bVar = new b();
        Intent intent = new Intent(b);
        intent.setPackage(a);
        boolean bindService = context.bindService(intent, bVar, 1);
        if (bindService) {
            try {
                bindService = new c(bVar.getBinder()).getId();
                return bindService;
            } catch (Exception unused) {
            } finally {
                context.unbindService(bVar);
            }
        }
        return str;
    }

    private static boolean b(Context context) {
        return VERSION.SDK_INT > 4 ? d ? true : API4.isPackageInstalledFromGooglePlayStore(context) : false;
    }

    public static boolean checkAndroidId(String str) {
        for (String equals : INVALID_ID_VALUES) {
            if (equals.equals(str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkAndroidSerial(String str) {
        if (str == null) {
            return false;
        }
        for (String equals : INVALID_ID_VALUES) {
            if (equals.equals(str)) {
                return false;
            }
        }
        return (str.length() <= 3 || str.substring(0, 3).equals("***") || str.substring(0, 3).equals("000")) ? false : true;
    }

    public static final DeviceId getAdvertisingDeviceId(Context context) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return b(context) ? getGooglePlayAdvertisingDeviceId(context) : getDeviceId(context);
        } else {
            throw new IllegalStateException("Cannot be called from the main thread");
        }
    }

    public static final DeviceId getAndroidId(Context context) {
        if (Integer.valueOf(VERSION.SDK_INT).intValue() >= 3) {
            String string = Secure.getString(context.getContentResolver(), "android_id");
            if (string != null && string.length() > 0) {
                return new DeviceId("AndroidId", string, 7, 2);
            }
        }
        return null;
    }

    public static final DeviceId getAndroidSerial() {
        if (Integer.valueOf(VERSION.SDK_INT).intValue() < 9) {
            return null;
        }
        return new DeviceId("AndroidSerial", API9.getAndroidSerial(), 3, 1);
    }

    public static DeviceId getDeviceId(Context context) {
        DeviceId androidSerial = getAndroidSerial();
        if (!checkAndroidSerial(androidSerial.getId())) {
            androidSerial = getAndroidId(context);
            if (!checkAndroidId(androidSerial.getId())) {
                return null;
            }
        }
        return androidSerial;
    }

    public static final DeviceId getGooglePlayAdvertisingDeviceId(Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot be called from the main thread");
        }
        try {
            if (!isGooglePlayServicesAvailable(context)) {
                return null;
            }
            return new DeviceId(isAdvertisingIdEnabled(context) ? a(context) : NO_ID_AVAILABLE);
        } catch (IllegalStateException e) {
            throw e;
        }
    }

    public static boolean isAdvertisingIdEnabled(Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot be called from the main thread");
        }
        boolean z = false;
        if (c) {
            return false;
        }
        b bVar = new b();
        Intent intent = new Intent(b);
        intent.setPackage(a);
        if (context.bindService(intent, bVar, 1)) {
            try {
                z = new c(bVar.getBinder()).isLimitAdTrackingEnabled(true) ^ 1;
            } catch (Exception unused) {
            } catch (Throwable th) {
                context.unbindService(bVar);
            }
            context.unbindService(bVar);
        }
        if (!z) {
            c = true;
        }
        return z;
    }

    public static boolean isGooglePlayServicesAvailable(Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot be called from the main thread");
        }
        boolean z = false;
        if (VERSION.SDK_INT <= 8) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            b bVar = new b();
            Intent intent = new Intent(b);
            intent.setPackage(a);
            if (context.bindService(intent, bVar, 1)) {
                context.unbindService(bVar);
                z = true;
            }
        } catch (Exception unused) {
        }
        return z;
    }

    public static String md5(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder stringBuilder = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(Integer.toHexString(i));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e2);
        }
    }
}
