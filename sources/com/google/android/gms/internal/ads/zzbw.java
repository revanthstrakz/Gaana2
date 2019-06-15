package com.google.android.gms.internal.ads;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

final class zzbw {
    private static boolean zziu = false;
    private static MessageDigest zziv;
    private static final Object zziw = new Object();
    private static final Object zzix = new Object();
    static CountDownLatch zziy = new CountDownLatch(1);

    static void zzw() {
        synchronized (zzix) {
            if (!zziu) {
                zziu = true;
                new Thread(new zzby()).start();
            }
        }
    }

    private static MessageDigest zzx() {
        boolean await;
        zzw();
        try {
            await = zziy.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            await = false;
        }
        if (await && zziv != null) {
            return zziv;
        }
        return null;
    }

    static String zza(zzbl zzbl, String str) throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] zzb = zzbuz.zzb(zzbl);
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzctq)).booleanValue()) {
            Vector zza = zza(zzb, 255);
            if (zza == null || zza.size() == 0) {
                zzb = zza(zzbuz.zzb(zzc(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM)), str, true);
            } else {
                zzbs zzbs = new zzbs();
                zzbs.zzhr = new byte[zza.size()][];
                Iterator it = zza.iterator();
                int i = 0;
                while (it.hasNext()) {
                    int i2 = i + 1;
                    zzbs.zzhr[i] = zza((byte[]) it.next(), str, false);
                    i = i2;
                }
                zzbs.zzhm = zzb(zzb);
                zzb = zzbuz.zzb(zzbs);
            }
        } else if (zzdq.zztk == null) {
            throw new GeneralSecurityException();
        } else {
            zzb = zzdq.zztk.zzc(zzb, str != null ? str.getBytes() : new byte[0]);
            zzbs zzbs2 = new zzbs();
            zzbs2.zzhr = new byte[][]{zzb};
            zzbs2.zzfy = Integer.valueOf(2);
            zzb = zzbuz.zzb(zzbs2);
        }
        return zzbu.zza(zzb, true);
    }

    private static Vector<byte[]> zza(byte[] bArr, int i) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        int length = ((bArr.length + 255) - 1) / 255;
        Vector vector = new Vector();
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 * 255;
            try {
                vector.add(Arrays.copyOfRange(bArr, i3, bArr.length - i3 > 255 ? i3 + 255 : bArr.length));
                i2++;
            } catch (IndexOutOfBoundsException unused) {
                return null;
            }
        }
        return vector;
    }

    private static zzbl zzc(long j) {
        zzbl zzbl = new zzbl();
        zzbl.zzeo = Long.valueOf(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
        return zzbl;
    }

    private static byte[] zza(byte[] bArr, String str, boolean z) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        int i = z ? 239 : 255;
        if (bArr.length > i) {
            bArr = zzbuz.zzb(zzc(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM));
        }
        if (bArr.length < i) {
            byte[] bArr2 = new byte[(i - bArr.length)];
            new SecureRandom().nextBytes(bArr2);
            bArr = ByteBuffer.allocate(i + 1).put((byte) bArr.length).put(bArr).put(bArr2).array();
        } else {
            bArr = ByteBuffer.allocate(i + 1).put((byte) bArr.length).put(bArr).array();
        }
        if (z) {
            bArr = ByteBuffer.allocate(256).put(zzb(bArr)).put(bArr).array();
        }
        byte[] bArr3 = new byte[256];
        for (zzcb zza : new zzbz().zzqm) {
            zza.zza(bArr, bArr3);
        }
        if (str != null && str.length() > 0) {
            if (str.length() > 32) {
                str = str.substring(0, 32);
            }
            new zzbpk(str.getBytes("UTF-8")).zzq(bArr3);
        }
        return bArr3;
    }

    public static byte[] zzb(byte[] bArr) throws NoSuchAlgorithmException {
        synchronized (zziw) {
            MessageDigest zzx = zzx();
            if (zzx == null) {
                throw new NoSuchAlgorithmException("Cannot compute hash");
            }
            zzx.reset();
            zzx.update(bArr);
            bArr = zziv.digest();
        }
        return bArr;
    }
}
