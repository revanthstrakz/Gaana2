package com.helpshift.websockets;

class ad {
    public static boolean a(char c) {
        if (!(c == 9 || c == ' ' || c == '\"' || c == ',' || c == '/' || c == '{' || c == '}')) {
            switch (c) {
                case '(':
                case ')':
                    break;
                default:
                    switch (c) {
                        case ':':
                        case ';':
                        case '<':
                        case '=':
                        case '>':
                        case '?':
                        case '@':
                            break;
                        default:
                            switch (c) {
                                case '[':
                                case '\\':
                                case ']':
                                    break;
                                default:
                                    return false;
                            }
                    }
            }
        }
        return true;
    }

    public static boolean a(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (a(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length >= 2 && str.charAt(0) == '\"') {
            length--;
            if (str.charAt(length) == '\"') {
                return c(str.substring(1, length));
            }
        }
        return str;
    }

    public static String c(String str) {
        if (str == null) {
            return null;
        }
        if (str.indexOf(92) < 0) {
            return str;
        }
        int length = str.length();
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        int i2 = i;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt == '\\' && i2 == 0) {
                i2 = 1;
            } else {
                stringBuilder.append(charAt);
                i2 = 0;
            }
            i++;
        }
        return stringBuilder.toString();
    }
}
