package com.google.ads.interactivemedia.v3.internal;

public final class fl {
    public static boolean a(String str) {
        return c(str).equals("audio");
    }

    public static boolean b(String str) {
        return c(str).equals("video");
    }

    private static String c(String str) {
        int indexOf = str.indexOf(47);
        if (indexOf != -1) {
            return str.substring(0, indexOf);
        }
        String str2 = "Invalid mime type: ";
        str = String.valueOf(str);
        throw new IllegalArgumentException(str.length() != 0 ? str2.concat(str) : new String(str2));
    }
}
