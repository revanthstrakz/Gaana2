package com.helpshift.websockets;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class af {
    private final String a;
    private final Map<String, String> b;

    /* Access modifiers changed, original: 0000 */
    public void a() throws WebSocketException {
    }

    public af(String str) {
        if (ad.a(str)) {
            this.a = str;
            this.b = new LinkedHashMap();
            return;
        }
        throw new IllegalArgumentException("'name' is not a valid token.");
    }

    public String b() {
        return this.a;
    }

    public Map<String, String> c() {
        return this.b;
    }

    public af a(String str, String str2) {
        if (!ad.a(str)) {
            throw new IllegalArgumentException("'key' is not a valid token.");
        } else if (str2 == null || ad.a(str2)) {
            this.b.put(str, str2);
            return this;
        } else {
            throw new IllegalArgumentException("'value' is not a valid token.");
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(this.a);
        for (Entry entry : this.b.entrySet()) {
            stringBuilder.append("; ");
            stringBuilder.append((String) entry.getKey());
            String str = (String) entry.getValue();
            if (!(str == null || str.length() == 0)) {
                stringBuilder.append("=");
                stringBuilder.append(str);
            }
        }
        return stringBuilder.toString();
    }

    public static af a(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.trim().split("\\s*;\\s*");
        if (split.length == 0) {
            return null;
        }
        String str2 = split[0];
        if (!ad.a(str2)) {
            return null;
        }
        af b = b(str2);
        for (int i = 1; i < split.length; i++) {
            String[] split2 = split[i].split("\\s*=\\s*", 2);
            if (!(split2.length == 0 || split2[0].length() == 0)) {
                String str3 = split2[0];
                if (ad.a(str3)) {
                    String a = a(split2);
                    if (a == null || ad.a(a)) {
                        b.a(str3, a);
                    }
                }
            }
        }
        return b;
    }

    private static String a(String[] strArr) {
        if (strArr.length != 2) {
            return null;
        }
        return ad.b(strArr[1]);
    }

    private static af b(String str) {
        if ("permessage-deflate".equals(str)) {
            return new t(str);
        }
        return new af(str);
    }
}
