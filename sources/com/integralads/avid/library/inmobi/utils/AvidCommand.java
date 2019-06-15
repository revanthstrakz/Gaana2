package com.integralads.avid.library.inmobi.utils;

import org.json.JSONObject;

public class AvidCommand {
    public static String setNativeViewState(String str) {
        StringBuilder stringBuilder = new StringBuilder("setNativeViewState(");
        stringBuilder.append(str);
        stringBuilder.append(")");
        return callAvidbridge(stringBuilder.toString());
    }

    public static String setAppState(String str) {
        StringBuilder stringBuilder = new StringBuilder("setAppState(");
        stringBuilder.append(JSONObject.quote(str));
        stringBuilder.append(")");
        return callAvidbridge(stringBuilder.toString());
    }

    public static String publishReadyEventForDeferredAdSession() {
        return callAvidbridge("publishReadyEventForDeferredAdSession()");
    }

    public static String publishVideoEvent(String str) {
        StringBuilder stringBuilder = new StringBuilder("publishVideoEvent(");
        stringBuilder.append(JSONObject.quote(str));
        stringBuilder.append(")");
        return callAvidbridge(stringBuilder.toString());
    }

    public static String publishVideoEvent(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder("publishVideoEvent(");
        stringBuilder.append(JSONObject.quote(str));
        stringBuilder.append(",");
        stringBuilder.append(str2);
        stringBuilder.append(")");
        return callAvidbridge(stringBuilder.toString());
    }

    public static String setAvidAdSessionContext(String str) {
        StringBuilder stringBuilder = new StringBuilder("setAvidAdSessionContext(");
        stringBuilder.append(str);
        stringBuilder.append(")");
        return callAvidbridge(stringBuilder.toString());
    }

    public static String callAvidbridge(String str) {
        StringBuilder stringBuilder = new StringBuilder("javascript: if(window.avidbridge!==undefined){avidbridge.");
        stringBuilder.append(str);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static String formatJavaScript(String str) {
        StringBuilder stringBuilder = new StringBuilder("javascript: ");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }
}
