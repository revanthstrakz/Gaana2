package com.inmobi.ads;

public final class br {
    public static String a(String str) {
        if (str.equalsIgnoreCase("window.mraidview")) {
            return "mraidview";
        }
        if (str.equalsIgnoreCase("window.imraidview")) {
            return "imraidview";
        }
        if (str.equalsIgnoreCase("window.imaiview")) {
            return "imaiview";
        }
        StringBuilder stringBuilder = new StringBuilder("NA_ERROR_");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }
}
