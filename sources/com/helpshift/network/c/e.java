package com.helpshift.network.c;

import com.helpshift.util.m;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;

public class e {
    private static String b(boolean z) {
        try {
            for (NetworkInterface inetAddresses : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                for (InetAddress inetAddress : Collections.list(inetAddresses.getInetAddresses())) {
                    if (!inetAddress.isLoopbackAddress()) {
                        String toUpperCase = inetAddress.getHostAddress().toUpperCase();
                        boolean a = d.a(toUpperCase);
                        if (z) {
                            if (a) {
                                return toUpperCase;
                            }
                        } else if (!a) {
                            int indexOf = toUpperCase.indexOf(37);
                            if (indexOf >= 0) {
                                toUpperCase = toUpperCase.substring(0, indexOf);
                            }
                            return toUpperCase;
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
        return "";
    }

    private static String a(String str, boolean z) {
        return (!z || m.c(str)) ? str : null;
    }

    public static String a(boolean z) {
        return a(b(z), z);
    }
}
