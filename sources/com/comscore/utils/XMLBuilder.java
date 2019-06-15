package com.comscore.utils;

public class XMLBuilder {
    private static StringBuilder a = null;
    private static int b = -1;
    private static int c = -1;
    private static final String[] d = new String[]{"c12", "c1", "ns_ap_an", "ns_ap_pn", "ns_ap_device", "ns_ak"};
    private static final String[] e = new String[]{"c12", "c1", "ns_ap_an", "ns_ap_pn", "ns_ap_device", "ns_ts", "ns_ak"};
    private static final long f = ((long) ((1 << e.length) - 1));

    private static void a(String str, String str2) {
        c = -1;
        b = -1;
        int i = 0;
        do {
            i = str.indexOf(str2, i);
            if (i >= 0) {
                int length = str2.length() + i;
                if ((i == 0 || str.charAt(i - 1) == '&') && length < str.length() && str.charAt(length) == '=') {
                    b = length + 1;
                    c = str.indexOf(38, b);
                    if (c == -1) {
                        c = str.length();
                    }
                    return;
                }
                i = length + 1;
            }
            if (i < 0) {
                break;
            }
        } while (i < str.length());
    }

    private static void a(String str, StringBuilder stringBuilder) {
        String str2 = str;
        StringBuilder stringBuilder2 = stringBuilder;
        a(str2, "ns_ts");
        int i = -1;
        if (b != -1 && c > b) {
            stringBuilder2.append("<event t=\"");
            stringBuilder2.append(str2, b, c);
            stringBuilder2.append("\">");
            long j = 0;
            int i2 = 0;
            int i3 = i2;
            long j2 = 0;
            while (i2 < str.length()) {
                int indexOf = str2.indexOf(38, i2);
                if (indexOf == i) {
                    indexOf = str.length();
                }
                if (indexOf > i2 && str2.indexOf(61, i2) > i2) {
                    long j3;
                    int i4 = 1;
                    if (j2 != f) {
                        int i5 = 0;
                        while (i5 < e.length) {
                            long j4 = (long) (1 << i5);
                            if ((j2 & j4) == j && e[i5].regionMatches(0, str2, i2, e[i5].length())) {
                                j3 = j2 | j4;
                                i4 = 0;
                                break;
                            }
                            i5++;
                            j = 0;
                        }
                    }
                    j3 = j2;
                    if (i4 != 0) {
                        if (i3 > 0) {
                            a.append('&');
                            stringBuilder2.append('&');
                        }
                        a.append(str2, i2, indexOf);
                        stringBuilder2.append(str2, i2, indexOf);
                        i3++;
                    }
                    j2 = j3;
                }
                i2 = indexOf + 1;
                i = -1;
                j = 0;
            }
            stringBuilder2.append("</event>");
        }
    }

    private static void a(StringBuilder stringBuilder) {
        String str = "md5=\"";
        stringBuilder.insert(stringBuilder.indexOf(str) + str.length(), Utils.md5(a.toString()).toLowerCase());
    }

    private static void a(StringBuilder stringBuilder, String str, String str2) {
        String valueOf = String.valueOf(Date.unixTime());
        stringBuilder.append("<events t=\"");
        stringBuilder.append(valueOf);
        stringBuilder.append("\" ");
        for (String b : d) {
            b(stringBuilder, str, b);
        }
        stringBuilder.append("dropped=\"");
        stringBuilder.append(str2);
        stringBuilder.append("\" md5=\"\">");
    }

    private static void a(String[] strArr, StringBuilder stringBuilder) {
        a = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            if (Utils.isNotEmpty(strArr[i])) {
                a(strArr[i], stringBuilder);
            }
        }
    }

    private static void b(StringBuilder stringBuilder, String str, String str2) {
        a(str, str2);
        if (b != -1 && c > b) {
            stringBuilder.append(str2);
            stringBuilder.append("=\"");
            stringBuilder.append(str, b, c);
            stringBuilder.append("\" ");
        }
    }

    public static synchronized String generateXMLRequestString(String[] strArr, String str) {
        String stringBuilder;
        synchronized (XMLBuilder.class) {
            StringBuilder stringBuilder2 = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            a(stringBuilder2, strArr[0], str);
            a(strArr, stringBuilder2);
            a(stringBuilder2);
            stringBuilder2.append("</events>");
            stringBuilder = stringBuilder2.toString();
        }
        return stringBuilder;
    }

    public static synchronized String getLabelFromEvent(String str, String str2) {
        synchronized (XMLBuilder.class) {
            a(str, str2);
            if (b == -1 || c <= b) {
                return null;
            }
            str = str.substring(b, c);
            return str;
        }
    }
}
