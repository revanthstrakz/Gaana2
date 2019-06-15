package com.google.ads.interactivemedia.v3.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import com.til.colombia.android.internal.e;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class es implements fc {
    private final ContentResolver a;
    private final fb b;
    private AssetFileDescriptor c;
    private InputStream d;
    private String e;
    private long f;
    private boolean g;

    public static class a extends IOException {
        public a(IOException iOException) {
            super(iOException);
        }
    }

    public es(Context context, fb fbVar) {
        this.a = context.getContentResolver();
        this.b = fbVar;
    }

    public long a(eu euVar) throws a {
        try {
            this.e = euVar.a.toString();
            this.c = this.a.openAssetFileDescriptor(euVar.a, e.o);
            this.d = new FileInputStream(this.c.getFileDescriptor());
            if (this.d.skip(euVar.d) < euVar.d) {
                throw new EOFException();
            }
            if (euVar.e != -1) {
                this.f = euVar.e;
            } else {
                this.f = (long) this.d.available();
                if (this.f == 0) {
                    this.f = -1;
                }
            }
            this.g = true;
            if (this.b != null) {
                this.b.a();
            }
            return this.f;
        } catch (IOException e) {
            throw new a(e);
        }
    }

    public int a(byte[] bArr, int i, int i2) throws a {
        if (this.f == 0) {
            return -1;
        }
        try {
            if (this.f != -1) {
                i2 = (int) Math.min(this.f, (long) i2);
            }
            int read = this.d.read(bArr, i, i2);
            if (read > 0) {
                if (this.f != -1) {
                    this.f -= (long) read;
                }
                if (this.b != null) {
                    this.b.a(read);
                }
            }
            return read;
        } catch (IOException e) {
            throw new a(e);
        }
    }

    public void a() throws a {
        this.e = null;
        try {
            if (this.d != null) {
                this.d.close();
            }
            this.d = null;
            try {
                if (this.c != null) {
                    this.c.close();
                }
                this.c = null;
                if (this.g) {
                    this.g = false;
                    if (this.b != null) {
                        this.b.b();
                    }
                }
            } catch (IOException e) {
                throw new a(e);
            } catch (Throwable th) {
                this.c = null;
                if (this.g) {
                    this.g = false;
                    if (this.b != null) {
                        this.b.b();
                    }
                }
            }
        } catch (IOException e2) {
            throw new a(e2);
        } catch (Throwable th2) {
            this.d = null;
            try {
                if (this.c != null) {
                    this.c.close();
                }
                this.c = null;
                if (this.g) {
                    this.g = false;
                    if (this.b != null) {
                        this.b.b();
                    }
                }
            } catch (IOException e22) {
                throw new a(e22);
            } catch (Throwable th3) {
                this.c = null;
                if (this.g) {
                    this.g = false;
                    if (this.b != null) {
                        this.b.b();
                    }
                }
            }
        }
    }
}
