package com.google.android.exoplayer2.util;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;

public final class UriUtil {
    private static final int FRAGMENT = 3;
    private static final int INDEX_COUNT = 4;
    private static final int PATH = 1;
    private static final int QUERY = 2;
    private static final int SCHEME_COLON = 0;

    private UriUtil() {
    }

    public static Uri resolveToUri(String str, String str2) {
        return Uri.parse(resolve(str, str2));
    }

    public static String resolve(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        int[] uriIndices = getUriIndices(str2);
        if (uriIndices[0] != -1) {
            stringBuilder.append(str2);
            removeDotSegments(stringBuilder, uriIndices[1], uriIndices[2]);
            return stringBuilder.toString();
        }
        int[] uriIndices2 = getUriIndices(str);
        if (uriIndices[3] == 0) {
            stringBuilder.append(str, 0, uriIndices2[3]);
            stringBuilder.append(str2);
            return stringBuilder.toString();
        } else if (uriIndices[2] == 0) {
            stringBuilder.append(str, 0, uriIndices2[2]);
            stringBuilder.append(str2);
            return stringBuilder.toString();
        } else if (uriIndices[1] != 0) {
            int i = uriIndices2[0] + 1;
            stringBuilder.append(str, 0, i);
            stringBuilder.append(str2);
            return removeDotSegments(stringBuilder, uriIndices[1] + i, i + uriIndices[2]);
        } else if (str2.charAt(uriIndices[1]) == '/') {
            stringBuilder.append(str, 0, uriIndices2[1]);
            stringBuilder.append(str2);
            return removeDotSegments(stringBuilder, uriIndices2[1], uriIndices2[1] + uriIndices[2]);
        } else if (uriIndices2[0] + 2 >= uriIndices2[1] || uriIndices2[1] != uriIndices2[2]) {
            int lastIndexOf = str.lastIndexOf(47, uriIndices2[2] - 1);
            int i2 = lastIndexOf == -1 ? uriIndices2[1] : lastIndexOf + 1;
            stringBuilder.append(str, 0, i2);
            stringBuilder.append(str2);
            return removeDotSegments(stringBuilder, uriIndices2[1], i2 + uriIndices[2]);
        } else {
            stringBuilder.append(str, 0, uriIndices2[1]);
            stringBuilder.append('/');
            stringBuilder.append(str2);
            return removeDotSegments(stringBuilder, uriIndices2[1], (uriIndices2[1] + uriIndices[2]) + 1);
        }
    }

    public static Uri removeQueryParameter(Uri uri, String str) {
        Builder buildUpon = uri.buildUpon();
        buildUpon.clearQuery();
        for (String str2 : uri.getQueryParameterNames()) {
            if (!str2.equals(str)) {
                for (String appendQueryParameter : uri.getQueryParameters(str2)) {
                    buildUpon.appendQueryParameter(str2, appendQueryParameter);
                }
            }
        }
        return buildUpon.build();
    }

    private static String removeDotSegments(StringBuilder stringBuilder, int i, int i2) {
        if (i >= i2) {
            return stringBuilder.toString();
        }
        if (stringBuilder.charAt(i) == '/') {
            i++;
        }
        int i3 = i;
        int i4 = i2;
        while (true) {
            i2 = i3;
            while (i2 <= i4) {
                int i5;
                if (i2 == i4) {
                    i5 = i2;
                } else if (stringBuilder.charAt(i2) == '/') {
                    i5 = i2 + 1;
                } else {
                    i2++;
                }
                int i6 = i3 + 1;
                if (i2 == i6 && stringBuilder.charAt(i3) == '.') {
                    stringBuilder.delete(i3, i5);
                    i4 -= i5 - i3;
                } else {
                    if (i2 == i3 + 2 && stringBuilder.charAt(i3) == '.' && stringBuilder.charAt(i6) == '.') {
                        i2 = stringBuilder.lastIndexOf("/", i3 - 2) + 1;
                        i3 = i2 > i ? i2 : i;
                        stringBuilder.delete(i3, i5);
                        i4 -= i5 - i3;
                    } else {
                        i2++;
                    }
                    i3 = i2;
                }
            }
            return stringBuilder.toString();
        }
    }

    private static int[] getUriIndices(String str) {
        int[] iArr = new int[4];
        if (TextUtils.isEmpty(str)) {
            iArr[0] = -1;
            return iArr;
        }
        int indexOf;
        int length = str.length();
        int indexOf2 = str.indexOf(35);
        if (indexOf2 != -1) {
            length = indexOf2;
        }
        indexOf2 = str.indexOf(63);
        if (indexOf2 == -1 || indexOf2 > length) {
            indexOf2 = length;
        }
        int indexOf3 = str.indexOf(47);
        if (indexOf3 == -1 || indexOf3 > indexOf2) {
            indexOf3 = indexOf2;
        }
        int indexOf4 = str.indexOf(58);
        if (indexOf4 > indexOf3) {
            indexOf4 = -1;
        }
        indexOf3 = indexOf4 + 2;
        indexOf3 = (indexOf3 < indexOf2 && str.charAt(indexOf4 + 1) == '/' && str.charAt(indexOf3) == '/') ? 1 : 0;
        if (indexOf3 != 0) {
            indexOf = str.indexOf(47, indexOf4 + 3);
            if (indexOf == -1 || indexOf > indexOf2) {
                indexOf = indexOf2;
            }
        } else {
            indexOf = indexOf4 + 1;
        }
        iArr[0] = indexOf4;
        iArr[1] = indexOf;
        iArr[2] = indexOf2;
        iArr[3] = length;
        return iArr;
    }
}
