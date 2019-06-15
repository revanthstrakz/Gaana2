package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public final class er implements fc {
    private final AssetManager a;
    private final fb b;
    private String c;
    private InputStream d;
    private long e;
    private boolean f;

    public static final class a extends IOException {
        public a(IOException iOException) {
            super(iOException);
        }
    }

    public er(Context context, fb fbVar) {
        this.a = context.getAssets();
        this.b = fbVar;
    }

    public long a(eu euVar) throws a {
        try {
            this.c = euVar.a.toString();
            String path = euVar.a.getPath();
            if (path.startsWith("/android_asset/")) {
                path = path.substring(15);
            } else if (path.startsWith("/")) {
                path = path.substring(1);
            }
            this.c = euVar.a.toString();
            this.d = this.a.open(path, 1);
            if (this.d.skip(euVar.d) < euVar.d) {
                throw new EOFException();
            }
            if (euVar.e != -1) {
                this.e = euVar.e;
            } else {
                this.e = (long) this.d.available();
                if (this.e == 2147483647L) {
                    this.e = -1;
                }
            }
            this.f = true;
            if (this.b != null) {
                this.b.a();
            }
            return this.e;
        } catch (IOException e) {
            throw new a(e);
        }
    }

    public int a(byte[] bArr, int i, int i2) throws a {
        if (this.e == 0) {
            return -1;
        }
        try {
            if (this.e != -1) {
                i2 = (int) Math.min(this.e, (long) i2);
            }
            int read = this.d.read(bArr, i, i2);
            if (read > 0) {
                if (this.e != -1) {
                    this.e -= (long) read;
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
        this.c = null;
        if (this.d != null) {
            try {
                this.d.close();
                this.d = null;
                if (this.f) {
                    this.f = false;
                    if (this.b != null) {
                        this.b.b();
                    }
                }
            } catch (IOException e) {
                throw new a(e);
            } catch (Throwable th) {
                this.d = null;
                if (this.f) {
                    this.f = false;
                    if (this.b != null) {
                        this.b.b();
                    }
                }
            }
        }
    }
}
