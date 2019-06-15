package com.appsflyer;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec.Builder;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Calendar;
import java.util.Enumeration;
import javax.security.auth.x500.X500Principal;

class c {
    private final Object a = new Object();
    private KeyStore b;
    private Context c;
    private String d;
    private int e;

    public c(Context context) {
        this.c = context;
        this.d = "";
        this.e = 0;
        AFLogger.d("Initialising KeyStore..");
        try {
            this.b = KeyStore.getInstance("AndroidKeyStore");
            this.b.load(null);
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            AFLogger.a("Couldn't load keystore instance of type: AndroidKeyStore", e);
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(String str) {
        this.d = str;
        this.e = 0;
        b(e());
    }

    /* Access modifiers changed, original: final */
    public final void a() {
        String e = e();
        synchronized (this.a) {
            this.e++;
            AFLogger.d("Deleting key with alias: ".concat(String.valueOf(e)));
            try {
                synchronized (this.a) {
                    this.b.deleteEntry(e);
                }
            } catch (KeyStoreException e2) {
                StringBuilder stringBuilder = new StringBuilder("Exception ");
                stringBuilder.append(e2.getMessage());
                stringBuilder.append(" occurred");
                AFLogger.a(stringBuilder.toString(), e2);
            }
        }
        b(e());
    }

    /* Access modifiers changed, original: final */
    public final boolean b() {
        boolean z;
        Throwable th;
        StringBuilder stringBuilder;
        synchronized (this.a) {
            z = true;
            if (this.b != null) {
                try {
                    Enumeration aliases = this.b.aliases();
                    while (aliases.hasMoreElements()) {
                        String str = (String) aliases.nextElement();
                        if (str != null && str.startsWith("com.appsflyer")) {
                            String[] split = str.split(",");
                            if (split.length == 3) {
                                AFLogger.d("Found a matching AF key with alias:\n".concat(String.valueOf(str)));
                                try {
                                    String[] split2 = split[1].trim().split("=");
                                    split = split[2].trim().split("=");
                                    if (split2.length == 2 && split.length == 2) {
                                        this.d = split2[1].trim();
                                        this.e = Integer.parseInt(split[1].trim());
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    stringBuilder = new StringBuilder("Couldn't list KeyStore Aliases: ");
                                    stringBuilder.append(th.getClass().getName());
                                    AFLogger.a(stringBuilder.toString(), th);
                                    return z;
                                }
                            }
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    z = false;
                    stringBuilder = new StringBuilder("Couldn't list KeyStore Aliases: ");
                    stringBuilder.append(th.getClass().getName());
                    AFLogger.a(stringBuilder.toString(), th);
                    return z;
                }
            }
            z = false;
        }
        return z;
    }

    private void b(String str) {
        AFLogger.d("Creating a new key with alias: ".concat(String.valueOf(str)));
        try {
            Calendar instance = Calendar.getInstance();
            Calendar instance2 = Calendar.getInstance();
            instance2.add(1, 5);
            AlgorithmParameterSpec algorithmParameterSpec = null;
            synchronized (this.a) {
                if (this.b.containsAlias(str)) {
                    AFLogger.d("Alias already exists: ".concat(String.valueOf(str)));
                } else {
                    if (VERSION.SDK_INT >= 23) {
                        algorithmParameterSpec = new Builder(str, 3).setCertificateSubject(new X500Principal("CN=AndroidSDK, O=AppsFlyer")).setCertificateSerialNumber(BigInteger.ONE).setCertificateNotBefore(instance.getTime()).setCertificateNotAfter(instance2.getTime()).build();
                    } else if (VERSION.SDK_INT >= 18 && !"OPPO".equals(Build.BRAND)) {
                        algorithmParameterSpec = new KeyPairGeneratorSpec.Builder(this.c).setAlias(str).setSubject(new X500Principal("CN=AndroidSDK, O=AppsFlyer")).setSerialNumber(BigInteger.ONE).setStartDate(instance.getTime()).setEndDate(instance2.getTime()).build();
                    }
                    KeyPairGenerator instance3 = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                    instance3.initialize(algorithmParameterSpec);
                    instance3.generateKeyPair();
                }
            }
        } catch (Throwable th) {
            StringBuilder stringBuilder = new StringBuilder("Exception ");
            stringBuilder.append(th.getMessage());
            stringBuilder.append(" occurred");
            AFLogger.a(stringBuilder.toString(), th);
        }
    }

    private String e() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("com.appsflyer,");
        synchronized (this.a) {
            stringBuilder.append("KSAppsFlyerId=");
            stringBuilder.append(this.d);
            stringBuilder.append(",");
            stringBuilder.append("KSAppsFlyerRICounter=");
            stringBuilder.append(this.e);
        }
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: final */
    public final String c() {
        String str;
        synchronized (this.a) {
            str = this.d;
        }
        return str;
    }

    /* Access modifiers changed, original: final */
    public final int d() {
        int i;
        synchronized (this.a) {
            i = this.e;
        }
        return i;
    }
}
