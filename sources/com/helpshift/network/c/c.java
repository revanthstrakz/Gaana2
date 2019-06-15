package com.helpshift.network.c;

import java.util.Map;
import org.apache.http.entity.mime.MIME;

public class c {
    public static String a(Map<String, String> map, String str) {
        String str2 = (String) map.get(MIME.CONTENT_TYPE);
        if (str2 != null) {
            String[] split = str2.split(";");
            for (int i = 1; i < split.length; i++) {
                String[] split2 = split[i].trim().split("=");
                if (split2.length == 2 && split2[0].equals("charset")) {
                    return split2[1];
                }
            }
        }
        return str;
    }
}
