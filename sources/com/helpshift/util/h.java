package com.helpshift.util;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.webkit.MimeTypeMap;
import com.til.colombia.android.internal.e;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class h {
    public static final String a = "h";
    private static final Set<String> b = new HashSet(Arrays.asList(new String[]{"image/jpeg", "image/png", "image/gif", "image/x-png", "image/x-citrix-pjpeg", "image/x-citrix-gif", "image/pjpeg"}));

    public static boolean a(URL url) {
        try {
            return b.contains(url.openConnection().getContentType());
        } catch (IOException e) {
            l.a(a, "openConnection() Exception :", e);
            return false;
        }
    }

    public static void a(URL url, File file) {
        InputStream openStream;
        FileOutputStream fileOutputStream;
        try {
            openStream = url.openStream();
            fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[500];
            while (true) {
                int read = openStream.read(bArr, 0, bArr.length);
                if (read >= 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    openStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            l.a(a, "saveFile Exception :", e);
        } catch (Throwable th) {
            fileOutputStream.close();
            openStream.close();
        }
    }

    public static String a(String str) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("file://");
            stringBuilder.append(str);
            return new URL(stringBuilder.toString()).openConnection().getContentType();
        } catch (Exception e) {
            l.a(a, "openConnection() Exception :", e);
            return null;
        }
    }

    public static String b(String str) {
        int lastIndexOf = str.lastIndexOf(47);
        int lastIndexOf2 = str.lastIndexOf(46);
        return lastIndexOf < lastIndexOf2 ? str.substring(lastIndexOf2) : "";
    }

    public static boolean a(Uri uri, Context context) {
        boolean z = false;
        try {
            ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(uri, e.o);
            if (openFileDescriptor != null) {
                z = true;
            }
            if (openFileDescriptor != null) {
                try {
                    openFileDescriptor.close();
                } catch (IOException unused) {
                }
            }
            return z;
        } catch (Exception e) {
            String str = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unable to open input file descriptor for doesFileFromUriExistAndCanRead: ");
            stringBuilder.append(uri);
            l.a(str, stringBuilder.toString(), e);
            return false;
        }
    }

    public static String a(Context context, @NonNull Uri uri) {
        if ("content".equals(uri.getScheme())) {
            return MimeTypeMap.getSingleton().getExtensionFromMimeType(context.getContentResolver().getType(uri));
        }
        return MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(uri.getPath())).toString());
    }
}
