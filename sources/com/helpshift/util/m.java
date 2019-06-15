package com.helpshift.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class m {
    private static Pattern a;
    private static Pattern b;
    private static Pattern c;
    private static Pattern d;
    private static Pattern e;
    private static Pattern f;

    public static boolean a(String str) {
        if (b == null) {
            b = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{1,62})+");
        }
        return b.matcher(str.trim()).matches();
    }

    public static boolean b(String str) {
        if (a == null) {
            a = Pattern.compile("\\W+");
        }
        return a.matcher(str.trim()).matches();
    }

    public static boolean c(String str) {
        if (c == null) {
            c = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)$");
        }
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        Matcher matcher = c.matcher(str.trim());
        if (!matcher.matches()) {
            return false;
        }
        for (int i = 1; i < matcher.groupCount(); i++) {
            Integer valueOf = Integer.valueOf(Integer.parseInt(matcher.group(i)));
            if (valueOf.intValue() < 0 || valueOf.intValue() > 255) {
                return false;
            }
        }
        return true;
    }

    public static Pattern d(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("^[\\p{L}\\p{N}-]+_");
        stringBuilder.append(str);
        stringBuilder.append("_\\d{17}-[0-9a-z]{15}$");
        return Pattern.compile(stringBuilder.toString());
    }

    public static Pattern a() {
        if (d == null) {
            d = Pattern.compile("^\\d+.\\d{3}$");
        }
        return d;
    }

    public static Pattern b() {
        if (e == null) {
            e = Pattern.compile("^[\\p{L}\\p{N}][\\p{L}\\p{N}-]*[\\p{L}\\p{N}]\\.helpshift\\.(com|mobi)$");
        }
        return e;
    }

    public static Pattern c() {
        if (f == null) {
            f = Pattern.compile("[^\\p{Z}\\n\\p{Ps}]+://[^\\p{Z}\\n\\p{Pe}]*");
        }
        return f;
    }
}
