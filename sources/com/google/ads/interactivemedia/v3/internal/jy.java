package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

public abstract class jy implements jx {
    protected MotionEvent a;
    protected DisplayMetrics b;
    protected kd c;
    private ke d;

    protected jy(Context context, kd kdVar, ke keVar) {
        this.c = kdVar;
        this.d = keVar;
        try {
            this.b = context.getResources().getDisplayMetrics();
        } catch (UnsupportedOperationException unused) {
            this.b = new DisplayMetrics();
            this.b.density = 1.0f;
        }
    }

    public abstract void b(Context context);

    public abstract void c(Context context);

    public String a(Context context) {
        return a(context, null, false);
    }

    public String a(Context context, String str) {
        return a(context, str, true);
    }

    private String a(Context context, String str, boolean z) {
        try {
            byte[] b;
            synchronized (this) {
                a();
                if (z) {
                    c(context);
                } else {
                    b(context);
                }
                b = b();
            }
            if (b.length == 0) {
                return Integer.toString(5);
            }
            return a(b, str);
        } catch (NoSuchAlgorithmException unused) {
            return Integer.toString(7);
        } catch (UnsupportedEncodingException unused2) {
            return Integer.toString(7);
        } catch (IOException unused3) {
            return Integer.toString(3);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, long j) throws IOException {
        this.d.a(i, j);
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, String str) throws IOException {
        this.d.a(i, str);
    }

    private void a() {
        this.d.a();
    }

    private byte[] b() throws IOException {
        return this.d.b();
    }

    /* Access modifiers changed, original: 0000 */
    public String a(byte[] bArr, String str) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException {
        byte[] bArr2;
        if (bArr.length > 239) {
            a();
            a(20, 1);
            bArr = b();
        }
        if (bArr.length < 239) {
            bArr2 = new byte[(239 - bArr.length)];
            new SecureRandom().nextBytes(bArr2);
            bArr = ByteBuffer.allocate(PsExtractor.VIDEO_STREAM_MASK).put((byte) bArr.length).put(bArr).put(bArr2).array();
        } else {
            bArr = ByteBuffer.allocate(PsExtractor.VIDEO_STREAM_MASK).put((byte) bArr.length).put(bArr).array();
        }
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(bArr);
        bArr = ByteBuffer.allocate(256).put(instance.digest()).put(bArr).array();
        bArr2 = new byte[256];
        new jw().a(bArr, bArr2);
        if (str != null && str.length() > 0) {
            a(str, bArr2);
        }
        return this.c.a(bArr2, true);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str, byte[] bArr) throws UnsupportedEncodingException {
        if (str.length() > 32) {
            str = str.substring(0, 32);
        }
        new lq(str.getBytes("UTF-8")).a(bArr);
    }

    /* Access modifiers changed, original: protected */
    public String a(String str) {
        if (str == null || !str.matches("^[a-fA-F0-9]{8}-([a-fA-F0-9]{4}-){3}[a-fA-F0-9]{12}$")) {
            return str;
        }
        UUID fromString = UUID.fromString(str);
        byte[] bArr = new byte[16];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.putLong(fromString.getMostSignificantBits());
        wrap.putLong(fromString.getLeastSignificantBits());
        return this.c.a(bArr, true);
    }
}
