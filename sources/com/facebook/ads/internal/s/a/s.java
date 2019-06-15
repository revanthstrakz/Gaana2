package com.facebook.ads.internal.s.a;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.StringTokenizer;
import org.json.JSONArray;

public class s {
    public static final String a(String str) {
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, " ", true);
            if (str.length() > 90 && (str.length() > 93 || !str.endsWith("..."))) {
                StringBuilder stringBuilder;
                int i = 0;
                while (stringTokenizer.hasMoreTokens()) {
                    int length = stringTokenizer.nextToken().length() + i;
                    if (length < 90) {
                        i = length;
                    }
                }
                if (i == 0) {
                    stringBuilder = new StringBuilder();
                    str = str.substring(0, 90);
                } else {
                    stringBuilder = new StringBuilder();
                    str = str.substring(0, i);
                }
                stringBuilder.append(str);
                stringBuilder.append("...");
                return stringBuilder.toString();
            }
        }
        return str;
    }

    public static final String a(Throwable th) {
        if (th == null) {
            return null;
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ThrowableExtension.printStackTrace(th, printWriter);
        printWriter.close();
        return stringWriter.toString();
    }

    public static String a(List<String> list, String str) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (String append : list) {
            stringBuilder.append(append);
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    public static final String a(StackTraceElement[] stackTraceElementArr) {
        JSONArray jSONArray = new JSONArray();
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            jSONArray.put(stackTraceElement.toString());
        }
        return jSONArray.toString();
    }
}
