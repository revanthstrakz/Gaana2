package com.exoplayer2.upstream.cache;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.exoplayer2.upstream.cache.Cache.CacheException;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.AtomicFile;
import com.google.android.exoplayer2.util.ReusableBufferedOutputStream;
import com.google.android.exoplayer2.util.Util;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

class h {
    private final HashMap<String, g> a;
    private final SparseArray<String> b;
    private final SparseBooleanArray c;
    private final AtomicFile d;
    private final Cipher e;
    private final SecretKeySpec f;
    private final boolean g;
    private boolean h;
    private ReusableBufferedOutputStream i;

    public h(File file, byte[] bArr, boolean z) {
        this.g = z;
        boolean z2 = true;
        if (bArr != null) {
            if (bArr.length != 16) {
                z2 = false;
            }
            Assertions.checkArgument(z2);
            try {
                this.e = g();
                this.f = new SecretKeySpec(bArr, "AES");
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                throw new IllegalStateException(e);
            }
        }
        Assertions.checkState(z ^ 1);
        this.e = null;
        this.f = null;
        this.a = new HashMap();
        this.b = new SparseArray();
        this.c = new SparseBooleanArray();
        this.d = new AtomicFile(new File(file, CachedContentIndex.FILE_NAME));
    }

    public void a() {
        Assertions.checkState(this.h ^ 1);
        if (!e()) {
            this.d.delete();
            this.a.clear();
            this.b.clear();
        }
    }

    public void b() throws CacheException {
        if (this.h) {
            f();
            int i = 0;
            this.h = false;
            int size = this.c.size();
            while (i < size) {
                this.b.remove(this.c.keyAt(i));
                i++;
            }
            this.c.clear();
        }
    }

    public g a(String str) {
        g gVar = (g) this.a.get(str);
        return gVar == null ? f(str) : gVar;
    }

    public g b(String str) {
        return (g) this.a.get(str);
    }

    public Collection<g> c() {
        return this.a.values();
    }

    public int c(String str) {
        return a(str).a;
    }

    public String a(int i) {
        return (String) this.b.get(i);
    }

    public void d(String str) {
        g gVar = (g) this.a.get(str);
        if (gVar != null && gVar.d() && !gVar.b()) {
            this.a.remove(str);
            this.h = true;
            this.b.put(gVar.a, null);
            this.c.put(gVar.a, true);
        }
    }

    public void d() {
        String[] strArr = new String[this.a.size()];
        this.a.keySet().toArray(strArr);
        for (String d : strArr) {
            d(d);
        }
    }

    public void a(String str, k kVar) {
        if (a(str).a(kVar)) {
            this.h = true;
        }
    }

    public i e(String str) {
        g b = b(str);
        return b != null ? b.a() : l.a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x009f  */
    private boolean e() {
        /*
        r9 = this;
        r0 = 0;
        r1 = 0;
        r2 = new java.io.BufferedInputStream;	 Catch:{ IOException -> 0x00a3, all -> 0x009b }
        r3 = r9.d;	 Catch:{ IOException -> 0x00a3, all -> 0x009b }
        r3 = r3.openRead();	 Catch:{ IOException -> 0x00a3, all -> 0x009b }
        r2.<init>(r3);	 Catch:{ IOException -> 0x00a3, all -> 0x009b }
        r3 = new java.io.DataInputStream;	 Catch:{ IOException -> 0x00a3, all -> 0x009b }
        r3.<init>(r2);	 Catch:{ IOException -> 0x00a3, all -> 0x009b }
        r1 = r3.readInt();	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        if (r1 < 0) goto L_0x0093;
    L_0x0018:
        r4 = 2;
        if (r1 <= r4) goto L_0x001d;
    L_0x001b:
        goto L_0x0093;
    L_0x001d:
        r5 = r3.readInt();	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        r6 = 1;
        r5 = r5 & r6;
        if (r5 == 0) goto L_0x0057;
    L_0x0025:
        r5 = r9.e;	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        if (r5 != 0) goto L_0x002f;
    L_0x0029:
        if (r3 == 0) goto L_0x002e;
    L_0x002b:
        com.google.android.exoplayer2.util.Util.closeQuietly(r3);
    L_0x002e:
        return r0;
    L_0x002f:
        r5 = 16;
        r5 = new byte[r5];	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        r3.readFully(r5);	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        r7 = new javax.crypto.spec.IvParameterSpec;	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        r7.<init>(r5);	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        r5 = r9.e;	 Catch:{ InvalidAlgorithmParameterException | InvalidKeyException -> 0x0050, InvalidAlgorithmParameterException | InvalidKeyException -> 0x0050 }
        r8 = r9.f;	 Catch:{ InvalidAlgorithmParameterException | InvalidKeyException -> 0x0050, InvalidAlgorithmParameterException | InvalidKeyException -> 0x0050 }
        r5.init(r4, r8, r7);	 Catch:{ InvalidAlgorithmParameterException | InvalidKeyException -> 0x0050, InvalidAlgorithmParameterException | InvalidKeyException -> 0x0050 }
        r4 = new java.io.DataInputStream;	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        r5 = new javax.crypto.CipherInputStream;	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        r7 = r9.e;	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        r5.<init>(r2, r7);	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        r4.<init>(r5);	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        r3 = r4;
        goto L_0x005d;
    L_0x0050:
        r1 = move-exception;
        r2 = new java.lang.IllegalStateException;	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        r2.<init>(r1);	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        throw r2;	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
    L_0x0057:
        r2 = r9.g;	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        if (r2 == 0) goto L_0x005d;
    L_0x005b:
        r9.h = r6;	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
    L_0x005d:
        r2 = r3.readInt();	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        r4 = r0;
        r5 = r4;
    L_0x0063:
        if (r4 >= r2) goto L_0x0074;
    L_0x0065:
        r7 = com.exoplayer2.upstream.cache.g.a(r1, r3);	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        r9.a(r7);	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        r7 = r7.a(r1);	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        r5 = r5 + r7;
        r4 = r4 + 1;
        goto L_0x0063;
    L_0x0074:
        r1 = r3.readInt();	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        r2 = r3.read();	 Catch:{ IOException -> 0x00a4, all -> 0x0099 }
        r4 = -1;
        if (r2 != r4) goto L_0x0081;
    L_0x007f:
        r2 = r6;
        goto L_0x0082;
    L_0x0081:
        r2 = r0;
    L_0x0082:
        if (r1 != r5) goto L_0x008d;
    L_0x0084:
        if (r2 != 0) goto L_0x0087;
    L_0x0086:
        goto L_0x008d;
    L_0x0087:
        if (r3 == 0) goto L_0x008c;
    L_0x0089:
        com.google.android.exoplayer2.util.Util.closeQuietly(r3);
    L_0x008c:
        return r6;
    L_0x008d:
        if (r3 == 0) goto L_0x0092;
    L_0x008f:
        com.google.android.exoplayer2.util.Util.closeQuietly(r3);
    L_0x0092:
        return r0;
    L_0x0093:
        if (r3 == 0) goto L_0x0098;
    L_0x0095:
        com.google.android.exoplayer2.util.Util.closeQuietly(r3);
    L_0x0098:
        return r0;
    L_0x0099:
        r0 = move-exception;
        goto L_0x009d;
    L_0x009b:
        r0 = move-exception;
        r3 = r1;
    L_0x009d:
        if (r3 == 0) goto L_0x00a2;
    L_0x009f:
        com.google.android.exoplayer2.util.Util.closeQuietly(r3);
    L_0x00a2:
        throw r0;
    L_0x00a3:
        r3 = r1;
    L_0x00a4:
        if (r3 == 0) goto L_0x00a9;
    L_0x00a6:
        com.google.android.exoplayer2.util.Util.closeQuietly(r3);
    L_0x00a9:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.exoplayer2.upstream.cache.h.e():boolean");
    }

    private void f() throws CacheException {
        Throwable e;
        Throwable th;
        Closeable dataOutputStream;
        try {
            OutputStream startWrite = this.d.startWrite();
            if (this.i == null) {
                this.i = new ReusableBufferedOutputStream(startWrite);
            } else {
                this.i.reset(startWrite);
            }
            dataOutputStream = new DataOutputStream(this.i);
            try {
                DataOutputStream dataOutputStream2;
                dataOutputStream.writeInt(2);
                dataOutputStream.writeInt(this.g);
                if (this.g) {
                    byte[] bArr = new byte[16];
                    new Random().nextBytes(bArr);
                    dataOutputStream.write(bArr);
                    this.e.init(1, this.f, new IvParameterSpec(bArr));
                    dataOutputStream.flush();
                    dataOutputStream2 = new DataOutputStream(new CipherOutputStream(this.i, this.e));
                }
                dataOutputStream2.writeInt(this.a.size());
                int i = 0;
                for (g gVar : this.a.values()) {
                    gVar.a(dataOutputStream2);
                    i += gVar.a(2);
                }
                dataOutputStream2.writeInt(i);
                this.d.endWrite(dataOutputStream2);
                Util.closeQuietly(null);
            } catch (InvalidAlgorithmParameterException | InvalidKeyException e2) {
                throw new IllegalStateException(e2);
            } catch (IOException e3) {
                e2 = e3;
                try {
                    throw new CacheException(e2);
                } catch (Throwable th2) {
                    e2 = th2;
                    Util.closeQuietly(dataOutputStream);
                    throw e2;
                }
            }
        } catch (IOException e4) {
            th = e4;
            dataOutputStream = null;
            e2 = th;
            throw new CacheException(e2);
        } catch (Throwable e42) {
            th = e42;
            dataOutputStream = null;
            e2 = th;
            Util.closeQuietly(dataOutputStream);
            throw e2;
        }
    }

    private g f(String str) {
        g gVar = new g(a(this.b), str);
        a(gVar);
        this.h = true;
        return gVar;
    }

    private void a(g gVar) {
        this.a.put(gVar.b, gVar);
        this.b.put(gVar.a, gVar.b);
    }

    private static Cipher g() throws NoSuchPaddingException, NoSuchAlgorithmException {
        if (Util.SDK_INT == 18) {
            try {
                return Cipher.getInstance("AES/CBC/PKCS5PADDING", "BC");
            } catch (Throwable unused) {
            }
        }
        return Cipher.getInstance("AES/CBC/PKCS5PADDING");
    }

    public static int a(SparseArray<String> sparseArray) {
        int i;
        int size = sparseArray.size();
        if (size == 0) {
            i = 0;
        } else {
            i = sparseArray.keyAt(size - 1) + 1;
        }
        if (i < 0) {
            i = 0;
            while (i < size && i == sparseArray.keyAt(i)) {
                i++;
            }
        }
        return i;
    }
}
