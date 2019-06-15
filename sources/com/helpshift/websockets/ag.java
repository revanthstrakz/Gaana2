package com.helpshift.websockets;

import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import javax.net.ssl.SSLSocketFactory;

public class ag {
    private final ab a = new ab();
    private final y b = new y(this);
    private int c;

    private static int a(int i, boolean z) {
        return i >= 0 ? i : z ? 443 : 80;
    }

    public int a() {
        return this.c;
    }

    public ag a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("timeout value cannot be negative.");
        }
        this.c = i;
        return this;
    }

    public ae a(String str) throws IOException {
        return a(str, a());
    }

    public ae a(String str, int i) throws IOException {
        if (str == null) {
            throw new IllegalArgumentException("The given URI is null.");
        } else if (i >= 0) {
            return a(URI.create(str), i);
        } else {
            throw new IllegalArgumentException("The given timeout value is negative.");
        }
    }

    public ae a(URI uri, int i) throws IOException {
        if (uri == null) {
            throw new IllegalArgumentException("The given URI is null.");
        } else if (i < 0) {
            throw new IllegalArgumentException("The given timeout value is negative.");
        } else {
            return a(uri.getScheme(), uri.getUserInfo(), p.a(uri), uri.getPort(), uri.getRawPath(), uri.getRawQuery(), i);
        }
    }

    private ae a(String str, String str2, String str3, int i, String str4, String str5, int i2) throws IOException {
        boolean b = b(str);
        if (str3 == null || str3.length() == 0) {
            throw new IllegalArgumentException("The host part is empty.");
        }
        return a(b, str2, str3, i, c(str4), str5, a(str3, i, b, i2));
    }

    private static boolean b(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("The scheme part is empty.");
        } else if ("wss".equalsIgnoreCase(str) || "https".equalsIgnoreCase(str)) {
            return true;
        } else {
            if ("ws".equalsIgnoreCase(str) || "http".equalsIgnoreCase(str)) {
                return false;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Bad scheme: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    private static String c(String str) {
        if (str == null || str.length() == 0) {
            return "/";
        }
        if (str.startsWith("/")) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    private aa a(String str, int i, boolean z, int i2) throws IOException {
        i = a(i, z);
        if ((this.b.c() != null ? 1 : null) != null) {
            return b(str, i, z, i2);
        }
        return c(str, i, z, i2);
    }

    private aa b(String str, int i, boolean z, int i2) throws IOException {
        int a = a(this.b.d(), this.b.b());
        Socket createSocket = this.b.h().createSocket();
        return new aa(createSocket, new a(this.b.c(), a), i2, new x(createSocket, str, i, this.b), z ? (SSLSocketFactory) this.a.a(z) : null, str, i);
    }

    private aa c(String str, int i, boolean z, int i2) throws IOException {
        return new aa(this.a.a(z).createSocket(), new a(str, i), i2);
    }

    private ae a(boolean z, String str, String str2, int i, String str3, String str4, aa aaVar) {
        if (i >= 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(":");
            stringBuilder.append(i);
            str2 = stringBuilder.toString();
        }
        String str5 = str2;
        if (str4 != null) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str3);
            stringBuilder2.append("?");
            stringBuilder2.append(str4);
            str3 = stringBuilder2.toString();
        }
        return new ae(this, z, str, str5, str3, aaVar);
    }
}
