package com.til.colombia.android.internal.a;

import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.i;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public final class h {
    private static final AtomicLong a = new AtomicLong(1);

    private static boolean b(int i, int i2) {
        return (i & i2) != 0;
    }

    private static String a(InputStream inputStream) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bArr = new byte[4096];
        int i = 0;
        while (i != -1) {
            stringBuffer.append(new String(bArr, 0, i));
            i = inputStream.read(bArr);
        }
        inputStream.close();
        return stringBuffer.toString();
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0;
    }

    public static Date b(String str) {
        if (a(str)) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String a(Collection<String> collection, String str) {
        if (collection.isEmpty()) {
            return "";
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (a((String) it.next())) {
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

    private static String d(String str) {
        if (a(str)) {
            return null;
        }
        byte[] bytes;
        MessageDigest instance;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.a(i.f, "", e);
            bytes = null;
        }
        try {
            instance = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e2) {
            Log.a(i.f, "", e2);
            instance = null;
        }
        if (instance == null) {
            return null;
        }
        bytes = instance.digest(bytes);
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bytes) {
            stringBuffer.append(Integer.toString((b & 255) + 256, 16).substring(1));
        }
        return stringBuffer.toString();
    }

    private static int a(int i, int i2) {
        return new Random().nextInt((i2 - i) + 1) + i;
    }

    public static String c(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            byte[] bytes = str.getBytes("UTF-8");
            instance.update(bytes, 0, bytes.length);
            int length = instance.digest().length;
            for (int i = 0; i < length; i++) {
                stringBuilder.append(String.format("%02X", new Object[]{Byte.valueOf(bytes[i])}));
            }
            return stringBuilder.toString().toLowerCase(Locale.US);
        } catch (Exception unused) {
            return "";
        }
    }

    private static long a() {
        long j;
        do {
            j = a.get();
        } while (!a.compareAndSet(j, j + 1));
        return j;
    }
}
