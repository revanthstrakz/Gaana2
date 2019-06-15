package com.inmobi.commons.core.utilities;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

public class d {
    private static final String a = "d";

    @SuppressLint({"MissingPermission"})
    public static boolean a() {
        boolean z = false;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) a.b().getSystemService("connectivity")).getActiveNetworkInfo();
            if (!(activeNetworkInfo == null || !activeNetworkInfo.isConnected() || b())) {
                z = true;
            }
            return z;
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in checking network availability; ").append(e.getMessage());
            return false;
        }
    }

    private static boolean b() {
        try {
            PowerManager powerManager = (PowerManager) a.b().getSystemService("power");
            if (VERSION.SDK_INT > 22) {
                return powerManager.isDeviceIdleMode();
            }
            return false;
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in checking idle mode; ").append(e.getMessage());
            return false;
        }
    }

    public static String a(Map<String, String> map, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(str);
            }
            stringBuilder.append(String.format(Locale.US, "%s=%s", new Object[]{a((String) entry.getKey()), a((String) entry.getValue())}));
        }
        return stringBuilder.toString();
    }

    private static String a(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    public static void a(Map<String, String> map) {
        if (map != null) {
            HashMap hashMap = new HashMap();
            for (Entry entry : map.entrySet()) {
                if (!(entry.getValue() == null || ((String) entry.getValue()).trim().length() == 0 || entry.getKey() == null || ((String) entry.getKey()).trim().length() == 0)) {
                    hashMap.put(((String) entry.getKey()).trim(), ((String) entry.getValue()).trim());
                }
            }
            map.clear();
            map.putAll(hashMap);
        }
    }

    public static String a(String str, Map<String, String> map) {
        if (map != null && map.size() > 0) {
            for (Entry entry : map.entrySet()) {
                str = str.replace((CharSequence) entry.getKey(), (CharSequence) entry.getValue());
            }
        }
        return str;
    }

    public static byte[] a(@NonNull byte[] bArr) {
        Throwable e;
        Throwable th;
        Closeable byteArrayInputStream = new ByteArrayInputStream(bArr);
        Closeable gZIPInputStream;
        try {
            gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            try {
                byte[] a = a((InputStream) gZIPInputStream);
                a(byteArrayInputStream);
                a(gZIPInputStream);
                return a;
            } catch (IOException e2) {
                e = e2;
                try {
                    Logger.a(InternalLogLevel.DEBUG, a, "Failed to decompress response", e);
                    a(byteArrayInputStream);
                    a(gZIPInputStream);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    a(byteArrayInputStream);
                    a(gZIPInputStream);
                    throw th;
                }
            }
        } catch (IOException e3) {
            e = e3;
            gZIPInputStream = null;
            Logger.a(InternalLogLevel.DEBUG, a, "Failed to decompress response", e);
            a(byteArrayInputStream);
            a(gZIPInputStream);
            return null;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            gZIPInputStream = null;
            th = th4;
            a(byteArrayInputStream);
            a(gZIPInputStream);
            throw th;
        }
    }

    public static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (-1 == read) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } finally {
                byteArrayOutputStream.close();
            }
        }
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        return toByteArray;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
