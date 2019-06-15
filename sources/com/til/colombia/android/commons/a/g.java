package com.til.colombia.android.commons.a;

import android.net.Uri;
import com.google.android.exoplayer2.C;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a;
import com.til.colombia.android.internal.i;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;

public final class g {
    static final Charset a = Charset.forName(C.ASCII_NAME);
    static final Charset b = Charset.forName("UTF-8");

    private g() {
    }

    static String a(Reader reader) throws IOException {
        try {
            StringWriter stringWriter = new StringWriter();
            char[] cArr = new char[1024];
            while (true) {
                int read = reader.read(cArr);
                if (read == -1) {
                    break;
                }
                stringWriter.write(cArr, 0, read);
            }
            String stringWriter2 = stringWriter.toString();
            return stringWriter2;
        } finally {
            reader.close();
        }
    }

    public static void a(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            StringBuilder stringBuilder = new StringBuilder("not a readable directory: ");
            stringBuilder.append(file);
            throw new IOException(stringBuilder.toString());
        }
        int length = listFiles.length;
        int i = 0;
        while (i < length) {
            File file2 = listFiles[i];
            if (file2.isDirectory()) {
                a(file2);
            }
            if (file2.delete()) {
                i++;
            } else {
                StringBuilder stringBuilder2 = new StringBuilder("failed to delete file: ");
                stringBuilder2.append(file2);
                throw new IOException(stringBuilder2.toString());
            }
        }
    }

    static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    public static Uri a(String str) {
        return a(str, ".mp3");
    }

    public static Uri b(String str) {
        return a(str, ".mp4");
    }

    private static Uri a(String str, String str2) {
        if (a.a(str)) {
            try {
                byte[] b = a.b(str);
                if (b == null) {
                    throw new Exception("no bytes downloaded/buffered.");
                }
                String str3 = a.a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(a.h());
                stringBuilder.append(str2);
                File file = new File(str3, stringBuilder.toString());
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdir();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(b, 0, b.length);
                fileOutputStream.close();
                return Uri.parse(file.getAbsolutePath());
            } catch (Exception e) {
                Log.a(i.f, "", e);
            }
        }
        return Uri.parse(str);
    }
}
