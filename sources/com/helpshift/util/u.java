package com.helpshift.util;

import java.util.ArrayList;
import org.json.JSONArray;

public class u {
    public static String a(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        return obj instanceof ArrayList ? new JSONArray((ArrayList) obj).toString() : null;
    }

    public static String a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("'");
        stringBuilder.append(str);
        stringBuilder.append("'");
        return stringBuilder.toString();
    }
}
