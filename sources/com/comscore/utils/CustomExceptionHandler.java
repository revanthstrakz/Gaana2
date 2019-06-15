package com.comscore.utils;

import com.comscore.analytics.Core;
import com.comscore.applications.EventType;
import java.io.UnsupportedEncodingException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.URLEncoder;
import java.util.HashMap;

public class CustomExceptionHandler implements UncaughtExceptionHandler {
    private UncaughtExceptionHandler a = Thread.getDefaultUncaughtExceptionHandler();
    private Core b;

    public CustomExceptionHandler(Core core) {
        this.b = core;
    }

    private void a(Throwable th) {
        String str = "";
        StackTraceElement[] stackTrace = th.getStackTrace();
        int i = 0;
        while (i < stackTrace.length && i < 5) {
            StackTraceElement stackTraceElement = stackTrace[i];
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(stackTraceElement.getFileName());
            stringBuilder.append("@");
            stringBuilder.append(stackTraceElement.getLineNumber());
            stringBuilder.append("|");
            stringBuilder.append(stackTraceElement.getClassName());
            stringBuilder.append(".");
            stringBuilder.append(stackTraceElement.getMethodName());
            String stringBuilder2 = stringBuilder.toString();
            if (str.equals("")) {
                str = stringBuilder2;
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(";");
                stringBuilder.append(stringBuilder2);
                str = stringBuilder.toString();
            }
            i++;
        }
        HashMap hashMap = new HashMap();
        if (!str.equals("")) {
            try {
                hashMap.put("ns_ap_uxc", URLEncoder.encode(str, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                CSLog.e((Object) this, "Error encoding the URL (ns_ap_uxc)");
                CSLog.printStackTrace(e);
            }
        }
        try {
            hashMap.put("ns_ap_uxs", URLEncoder.encode(th.toString(), "UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            CSLog.e((Object) this, "Error encoding the URL (ns_ap_uxs)");
            CSLog.printStackTrace(e2);
        }
        this.b.getOfflineCache().saveApplicationMeasurement(EventType.HIDDEN, hashMap, true);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        a(th);
        this.a.uncaughtException(thread, th);
    }
}
