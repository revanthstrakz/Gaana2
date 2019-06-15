package com.helpshift.util;

import com.helpshift.exceptions.InstallException;

public class t {
    private static boolean a(String str, String str2) {
        return !w.a(str) && m.d(str2).matcher(str).matches();
    }

    public static boolean a(String str) {
        return !w.a(str) && a(str, "platform");
    }

    public static boolean b(String str) {
        if (w.a(str)) {
            return false;
        }
        return m.a().matcher(str).matches();
    }

    public static boolean c(String str) {
        if (w.a(str)) {
            return false;
        }
        return m.b().matcher(str).matches();
    }

    public static boolean d(String str) {
        return w.a(str) ^ 1;
    }

    public static boolean a(String str, String str2, String str3) throws InstallException {
        if (!d(str)) {
            throw new InstallException("The api key used in the Core.install(application, apiKey, domain, appId) is not valid!");
        } else if (!c(str2)) {
            throw new InstallException("The domain name used in the Core.install(application, apiKey, domain, appId) is not valid!");
        } else if (a(str3)) {
            return true;
        } else {
            throw new InstallException("The app id used in the Core.install(application, apiKey, domain, appId) is not valid!");
        }
    }
}
