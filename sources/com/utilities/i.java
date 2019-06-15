package com.utilities;

import com.gaana.application.GaanaApplication;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class i {
    public static String a = ".e";
    public static String b = ".ext";

    public static OutputStream a(OutputStream outputStream) {
        try {
            Cipher instance = Cipher.getInstance(GaanaApplication.getEncryptionKey(2));
            instance.init(1, new SecretKeySpec(new StringBuilder(GaanaApplication.getEncryptionKey(0)).reverse().toString().getBytes(), "AES"), new IvParameterSpec(new StringBuilder(GaanaApplication.getEncryptionKey(1)).reverse().toString().getBytes()));
            return new CipherOutputStream(outputStream, instance);
        } catch (NoSuchAlgorithmException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        } catch (NoSuchPaddingException e2) {
            ThrowableExtension.printStackTrace(e2);
            return null;
        } catch (InvalidAlgorithmParameterException e3) {
            ThrowableExtension.printStackTrace(e3);
            return null;
        } catch (InvalidKeyException e4) {
            ThrowableExtension.printStackTrace(e4);
            return null;
        }
    }
}
