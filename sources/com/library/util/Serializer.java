package com.library.util;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serializer {
    public static String serialize(Serializable serializable) {
        if (serializable == null) {
            return "";
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(serializable);
            objectOutputStream.close();
            return encodeBytes(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static Object deserialize(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            return new ObjectInputStream(new ByteArrayInputStream(decodeBytes(str))).readObject();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0045 */
    public static java.lang.Object deserializeFallback(java.lang.String r3) {
        /*
        r0 = 0;
        if (r3 == 0) goto L_0x004e;
    L_0x0003:
        r1 = r3.length();
        if (r1 != 0) goto L_0x000a;
    L_0x0009:
        goto L_0x004e;
    L_0x000a:
        r1 = new java.io.ByteArrayInputStream;	 Catch:{ IOException -> 0x0044, ClassNotFoundException -> 0x0029, all -> 0x0027 }
        r3 = decodeBytes(r3);	 Catch:{ IOException -> 0x0044, ClassNotFoundException -> 0x0029, all -> 0x0027 }
        r1.<init>(r3);	 Catch:{ IOException -> 0x0044, ClassNotFoundException -> 0x0029, all -> 0x0027 }
        r3 = new com.library.util.DeserializerObjectInputStream;	 Catch:{ IOException -> 0x0044, ClassNotFoundException -> 0x0029, all -> 0x0027 }
        r3.<init>(r1);	 Catch:{ IOException -> 0x0044, ClassNotFoundException -> 0x0029, all -> 0x0027 }
        r1 = r3.readObject();	 Catch:{ IOException -> 0x0045, ClassNotFoundException -> 0x0025 }
        r3.close();	 Catch:{ IOException -> 0x0020 }
        goto L_0x0024;
    L_0x0020:
        r3 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r3);
    L_0x0024:
        return r1;
    L_0x0025:
        r1 = move-exception;
        goto L_0x002b;
    L_0x0027:
        r3 = move-exception;
        goto L_0x003b;
    L_0x0029:
        r1 = move-exception;
        r3 = r0;
    L_0x002b:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r1);	 Catch:{ all -> 0x0037 }
        r3.close();	 Catch:{ IOException -> 0x0032 }
        goto L_0x0036;
    L_0x0032:
        r3 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r3);
    L_0x0036:
        return r0;
    L_0x0037:
        r0 = move-exception;
        r2 = r0;
        r0 = r3;
        r3 = r2;
    L_0x003b:
        r0.close();	 Catch:{ IOException -> 0x003f }
        goto L_0x0043;
    L_0x003f:
        r0 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
    L_0x0043:
        throw r3;
    L_0x0044:
        r3 = r0;
    L_0x0045:
        r3.close();	 Catch:{ IOException -> 0x0049 }
        goto L_0x004d;
    L_0x0049:
        r3 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r3);
    L_0x004d:
        return r0;
    L_0x004e:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.library.util.Serializer.deserializeFallback(java.lang.String):java.lang.Object");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x004f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x002a */
    public static java.lang.Object deserializeBookmark(java.lang.String r3) {
        /*
        r0 = 0;
        if (r3 == 0) goto L_0x0058;
    L_0x0003:
        r1 = r3.length();
        if (r1 != 0) goto L_0x000a;
    L_0x0009:
        goto L_0x0058;
    L_0x000a:
        r1 = new java.io.ByteArrayInputStream;	 Catch:{ IOException -> 0x004e, ClassNotFoundException -> 0x0033, Exception -> 0x0029, all -> 0x0027 }
        r3 = decodeBytes(r3);	 Catch:{ IOException -> 0x004e, ClassNotFoundException -> 0x0033, Exception -> 0x0029, all -> 0x0027 }
        r1.<init>(r3);	 Catch:{ IOException -> 0x004e, ClassNotFoundException -> 0x0033, Exception -> 0x0029, all -> 0x0027 }
        r3 = new com.library.util.DeserializerObjectInputStream;	 Catch:{ IOException -> 0x004e, ClassNotFoundException -> 0x0033, Exception -> 0x0029, all -> 0x0027 }
        r3.<init>(r1);	 Catch:{ IOException -> 0x004e, ClassNotFoundException -> 0x0033, Exception -> 0x0029, all -> 0x0027 }
        r1 = r3.readObject();	 Catch:{ IOException -> 0x004f, ClassNotFoundException -> 0x0025, Exception -> 0x002a }
        r3.close();	 Catch:{ IOException -> 0x0020 }
        goto L_0x0024;
    L_0x0020:
        r3 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r3);
    L_0x0024:
        return r1;
    L_0x0025:
        r1 = move-exception;
        goto L_0x0035;
    L_0x0027:
        r3 = move-exception;
        goto L_0x0045;
    L_0x0029:
        r3 = r0;
    L_0x002a:
        r3.close();	 Catch:{ IOException -> 0x002e }
        goto L_0x0032;
    L_0x002e:
        r3 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r3);
    L_0x0032:
        return r0;
    L_0x0033:
        r1 = move-exception;
        r3 = r0;
    L_0x0035:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r1);	 Catch:{ all -> 0x0041 }
        r3.close();	 Catch:{ IOException -> 0x003c }
        goto L_0x0040;
    L_0x003c:
        r3 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r3);
    L_0x0040:
        return r0;
    L_0x0041:
        r0 = move-exception;
        r2 = r0;
        r0 = r3;
        r3 = r2;
    L_0x0045:
        r0.close();	 Catch:{ IOException -> 0x0049 }
        goto L_0x004d;
    L_0x0049:
        r0 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
    L_0x004d:
        throw r3;
    L_0x004e:
        r3 = r0;
    L_0x004f:
        r3.close();	 Catch:{ IOException -> 0x0053 }
        goto L_0x0057;
    L_0x0053:
        r3 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r3);
    L_0x0057:
        return r0;
    L_0x0058:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.library.util.Serializer.deserializeBookmark(java.lang.String):java.lang.Object");
    }

    public static String encodeBytes(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append((char) (((bArr[i] >> 4) & 15) + 97));
            stringBuffer.append((char) ((bArr[i] & 15) + 97));
        }
        return stringBuffer.toString();
    }

    public static byte[] decodeBytes(String str) {
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length(); i += 2) {
            int i2 = i / 2;
            bArr[i2] = (byte) ((str.charAt(i) - 97) << 4);
            bArr[i2] = (byte) (bArr[i2] + (str.charAt(i + 1) - 97));
        }
        return bArr;
    }
}
