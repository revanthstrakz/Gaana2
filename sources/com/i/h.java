package com.i;

import java.math.BigInteger;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public final class h implements X509TrustManager {
    private String a;

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    public h(String str) {
        this.a = str;
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (x509CertificateArr == null) {
            throw new IllegalArgumentException("checkServerTrusted: X509Certificate array is null");
        } else if (x509CertificateArr.length <= 0) {
            throw new IllegalArgumentException("checkServerTrusted: X509Certificate is empty");
        } else {
            try {
                TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
                instance.init((KeyStore) null);
                for (TrustManager trustManager : instance.getTrustManagers()) {
                    ((X509TrustManager) trustManager).checkServerTrusted(x509CertificateArr, str);
                }
                if (!this.a.equalsIgnoreCase(new BigInteger(1, ((RSAPublicKey) x509CertificateArr[0].getPublicKey()).getEncoded()).toString(16))) {
                    throw new CertificateException("Not trusted");
                }
            } catch (Exception e) {
                throw new CertificateException(e.toString());
            }
        }
    }
}
