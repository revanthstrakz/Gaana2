package com.comscore.utils;

import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;

public class Utils {
    public static List<String> arrayOfStrings(List list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (Object next : list) {
                if (next != null) {
                    arrayList.add(next.toString());
                }
            }
        }
        return arrayList;
    }

    public static String encrypt(String str) {
        PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Constants.RSA_PUBLIC_KEY));
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
        instance.init(1, generatePublic);
        String str2 = new String(Base64Coder.encode(instance.doFinal(str.getBytes())));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("encrypt(");
        stringBuilder.append(str);
        stringBuilder.append(")=");
        stringBuilder.append(str2);
        CSLog.d(Utils.class, stringBuilder.toString());
        return str2;
    }

    public static boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    public static boolean getBoolean(String str, boolean z) {
        if (str != null) {
            if (str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("true")) {
                z = true;
            } else if (str.equalsIgnoreCase("no") || str.equalsIgnoreCase(InternalLogger.EVENT_PARAM_EXTRAS_FALSE)) {
                return false;
            }
        }
        return z;
    }

    public static int getInteger(String str) {
        return getInteger(str, 0);
    }

    public static int getInteger(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static long getLong(String str) {
        return getLong(str, 0);
    }

    public static long getLong(String str, long j) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    public static HashMap<String, String> mapOfStrings(Map map) {
        HashMap hashMap = new HashMap();
        if (map != null) {
            for (Object next : map.keySet()) {
                if (!(next == null || map.get(next) == null)) {
                    String obj = next.toString();
                    if (obj.length() > 0) {
                        hashMap.put(obj, map.get(next).toString());
                    }
                }
            }
        }
        return hashMap;
    }

    public static String md5(String str) {
        byte[] bytes = str.getBytes();
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bytes);
            bytes = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes) {
                String toHexString = Integer.toHexString(255 & b);
                if (toHexString.length() == 1) {
                    stringBuffer.append('0');
                }
                stringBuffer.append(toHexString);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(stringBuffer);
            stringBuilder.append("");
            return stringBuilder.toString();
        } catch (Exception unused) {
            return null;
        }
    }
}
