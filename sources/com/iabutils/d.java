package com.iabutils;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class d {
    public static boolean a(String str, String str2, String str3) {
        return (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) ? false : a(a(str), str2, str3);
    }

    public static PublicKey a(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static boolean a(PublicKey publicKey, String str, String str2) {
        try {
            Base64.decode(str2, 0);
            try {
                Signature instance = Signature.getInstance("SHA1withRSA");
                instance.initVerify(publicKey);
                instance.update(str.getBytes());
                if (instance.verify(Base64.decode(str2, 0))) {
                    return true;
                }
                return false;
            } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException unused) {
                return false;
            }
        } catch (IllegalArgumentException unused2) {
            Log.e("IABUtil/Security", "Base64 decoding failed.");
            return false;
        }
    }
}
