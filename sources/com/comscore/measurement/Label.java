package com.comscore.measurement;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Label {
    public Boolean aggregate;
    public String name;
    public String value;

    public Label(String str, String str2, Boolean bool) {
        this.name = str;
        this.value = str2;
        this.aggregate = bool;
    }

    private String a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char toLowerCase = Character.toLowerCase(str.charAt(i));
            if ((toLowerCase >= 'a' && toLowerCase <= 'z') || ((toLowerCase >= '0' && toLowerCase <= '9') || toLowerCase == '_' || toLowerCase == '-' || toLowerCase == '.')) {
                stringBuilder.append(toLowerCase);
            }
        }
        return stringBuilder.toString();
    }

    public String pack() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!(this.name == null || this.value == null)) {
            String a = a(this.name);
            if (a.length() > 0) {
                try {
                    stringBuilder.append("&");
                    stringBuilder.append(a);
                    stringBuilder.append("=");
                    stringBuilder.append(URLEncoder.encode(this.value, "UTF-8").replace("+", "%20"));
                    return stringBuilder.toString();
                } catch (UnsupportedEncodingException unused) {
                }
            }
        }
        return "";
    }
}
