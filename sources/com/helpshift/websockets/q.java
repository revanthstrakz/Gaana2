package com.helpshift.websockets;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

final class q implements HostnameVerifier {
    public static final q a = new q();
    private static final Pattern b = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    private q() {
    }

    public boolean verify(String str, SSLSession sSLSession) {
        try {
            return a(str, (X509Certificate) sSLSession.getPeerCertificates()[0]);
        } catch (SSLException unused) {
            return false;
        }
    }

    public boolean a(String str, X509Certificate x509Certificate) {
        if (a(str)) {
            return b(str, x509Certificate);
        }
        return c(str, x509Certificate);
    }

    static boolean a(String str) {
        return b.matcher(str).matches();
    }

    private boolean b(String str, X509Certificate x509Certificate) {
        List a = a(x509Certificate, 7);
        int size = a.size();
        for (int i = 0; i < size; i++) {
            if (str.equalsIgnoreCase((String) a.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean c(String str, X509Certificate x509Certificate) {
        str = str.toLowerCase(Locale.US);
        List a = a(x509Certificate, 2);
        int size = a.size();
        int i = 0;
        int i2 = i;
        while (i < size) {
            if (a(str, (String) a.get(i))) {
                return true;
            }
            i++;
            boolean i22 = true;
        }
        if (i22 == 0) {
            String a2 = new h(x509Certificate.getSubjectX500Principal()).a("cn");
            if (a2 != null) {
                return a(str, a2);
            }
        }
        return false;
    }

    private static List<String> a(X509Certificate x509Certificate, int i) {
        ArrayList arrayList = new ArrayList();
        try {
            Collection<List> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return Collections.emptyList();
            }
            for (List list : subjectAlternativeNames) {
                if (list != null) {
                    if (list.size() >= 2) {
                        Integer num = (Integer) list.get(0);
                        if (num != null) {
                            if (num.intValue() == i) {
                                String str = (String) list.get(1);
                                if (str != null) {
                                    arrayList.add(str);
                                }
                            }
                        }
                    }
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return Collections.emptyList();
        }
    }

    /* JADX WARNING: Missing block: B:44:0x00be, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:46:0x00c0, code skipped:
            return false;
     */
    private boolean a(java.lang.String r7, java.lang.String r8) {
        /*
        r6 = this;
        r0 = 0;
        if (r7 == 0) goto L_0x00c0;
    L_0x0003:
        r1 = r7.length();
        if (r1 == 0) goto L_0x00c0;
    L_0x0009:
        r1 = ".";
        r1 = r7.startsWith(r1);
        if (r1 != 0) goto L_0x00c0;
    L_0x0011:
        r1 = "..";
        r1 = r7.endsWith(r1);
        if (r1 == 0) goto L_0x001b;
    L_0x0019:
        goto L_0x00c0;
    L_0x001b:
        if (r8 == 0) goto L_0x00bf;
    L_0x001d:
        r1 = r8.length();
        if (r1 == 0) goto L_0x00bf;
    L_0x0023:
        r1 = ".";
        r1 = r8.startsWith(r1);
        if (r1 != 0) goto L_0x00bf;
    L_0x002b:
        r1 = "..";
        r1 = r8.endsWith(r1);
        if (r1 == 0) goto L_0x0035;
    L_0x0033:
        goto L_0x00bf;
    L_0x0035:
        r1 = ".";
        r1 = r7.endsWith(r1);
        r2 = 46;
        if (r1 != 0) goto L_0x004e;
    L_0x003f:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r1.append(r7);
        r1.append(r2);
        r7 = r1.toString();
    L_0x004e:
        r1 = ".";
        r1 = r8.endsWith(r1);
        if (r1 != 0) goto L_0x0065;
    L_0x0056:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r1.append(r8);
        r1.append(r2);
        r8 = r1.toString();
    L_0x0065:
        r1 = java.util.Locale.US;
        r8 = r8.toLowerCase(r1);
        r1 = "*";
        r1 = r8.contains(r1);
        if (r1 != 0) goto L_0x0078;
    L_0x0073:
        r7 = r7.equals(r8);
        return r7;
    L_0x0078:
        r1 = "*.";
        r1 = r8.startsWith(r1);
        if (r1 == 0) goto L_0x00be;
    L_0x0080:
        r1 = 42;
        r3 = 1;
        r1 = r8.indexOf(r1, r3);
        r4 = -1;
        if (r1 == r4) goto L_0x008b;
    L_0x008a:
        goto L_0x00be;
    L_0x008b:
        r1 = r7.length();
        r5 = r8.length();
        if (r1 >= r5) goto L_0x0096;
    L_0x0095:
        return r0;
    L_0x0096:
        r1 = "*.";
        r1 = r1.equals(r8);
        if (r1 == 0) goto L_0x009f;
    L_0x009e:
        return r0;
    L_0x009f:
        r8 = r8.substring(r3);
        r1 = r7.endsWith(r8);
        if (r1 != 0) goto L_0x00aa;
    L_0x00a9:
        return r0;
    L_0x00aa:
        r1 = r7.length();
        r8 = r8.length();
        r1 = r1 - r8;
        if (r1 <= 0) goto L_0x00bd;
    L_0x00b5:
        r1 = r1 - r3;
        r7 = r7.lastIndexOf(r2, r1);
        if (r7 == r4) goto L_0x00bd;
    L_0x00bc:
        return r0;
    L_0x00bd:
        return r3;
    L_0x00be:
        return r0;
    L_0x00bf:
        return r0;
    L_0x00c0:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.websockets.q.a(java.lang.String, java.lang.String):boolean");
    }
}
