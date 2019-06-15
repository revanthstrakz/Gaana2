package com.facebook.ads.internal.r.b;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.facebook.ads.AudienceNetworkActivity;
import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class m {
    static String a(String str) {
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        str = MimeTypeMap.getFileExtensionFromUrl(str);
        return TextUtils.isEmpty(str) ? null : singleton.getMimeTypeFromExtension(str);
    }

    private static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(String.format(Locale.US, "%02x", new Object[]{Byte.valueOf(b)}));
        }
        return stringBuffer.toString();
    }

    static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.e("ProxyCache", "Error closing resource", e);
            }
        }
    }

    static void a(byte[] bArr, long j, int i) {
        j.a((Object) bArr, "Buffer must be not null!");
        int i2 = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        boolean z = false;
        j.a(i2 >= 0, "Data offset must be positive!");
        if (i >= 0 && i <= bArr.length) {
            z = true;
        }
        j.a(z, "Length must be in range [0..buffer.length]");
    }

    static String b(String str) {
        try {
            return URLEncoder.encode(str, AudienceNetworkActivity.WEBVIEW_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error encoding url", e);
        }
    }

    static String c(String str) {
        try {
            return URLDecoder.decode(str, AudienceNetworkActivity.WEBVIEW_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error decoding url", e);
        }
    }

    public static String d(String str) {
        try {
            return a(MessageDigest.getInstance("MD5").digest(str.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
