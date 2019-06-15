package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@zzark
public abstract class zztd {
    @Nullable
    private static MessageDigest zzbyx;
    protected Object mLock = new Object();

    public abstract byte[] zzay(String str);

    /* Access modifiers changed, original: protected|final */
    @Nullable
    public final MessageDigest zzoc() {
        synchronized (this.mLock) {
            MessageDigest messageDigest;
            if (zzbyx != null) {
                messageDigest = zzbyx;
                return messageDigest;
            }
            for (int i = 0; i < 2; i++) {
                try {
                    zzbyx = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            messageDigest = zzbyx;
            return messageDigest;
        }
    }
}
