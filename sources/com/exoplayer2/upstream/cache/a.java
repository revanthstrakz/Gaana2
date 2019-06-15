package com.exoplayer2.upstream.cache;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.exoplayer2.upstream.cache.Cache.CacheException;
import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSourceException;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TeeDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class a implements DataSource {
    private final boolean a;
    private final Cache b;
    private final DataSource c;
    @Nullable
    private final DataSource d;
    private final DataSource e;
    private final c f;
    @Nullable
    private final a g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    @Nullable
    private DataSource k;
    private boolean l;
    @Nullable
    private Uri m;
    @Nullable
    private Uri n;
    private int o;
    private int p;
    @Nullable
    private String q;
    private long r;
    private long s;
    @Nullable
    private d t;
    private boolean u;
    private boolean v;
    private long w;
    private long x;

    public interface a {
        void a(int i);

        void a(long j, long j2);
    }

    public a(Cache cache, DataSource dataSource, DataSource dataSource2, DataSink dataSink, int i, boolean z, @Nullable a aVar, @Nullable c cVar) {
        this.b = cache;
        this.c = dataSource2;
        if (cVar == null) {
            cVar = e.a;
        }
        this.f = cVar;
        boolean z2 = false;
        this.h = (i & 1) != 0;
        this.i = (i & 2) != 0;
        this.a = z;
        if ((i & 4) != 0) {
            z2 = true;
        }
        this.j = z2;
        this.e = dataSource;
        if (dataSink != null) {
            this.d = new TeeDataSource(dataSource, dataSink);
        } else {
            this.d = null;
        }
        this.g = aVar;
    }

    public void addTransferListener(TransferListener transferListener) {
        this.c.addTransferListener(transferListener);
        this.e.addTransferListener(transferListener);
    }

    public long open(DataSpec dataSpec) throws IOException {
        try {
            this.q = this.f.a(dataSpec);
            this.m = dataSpec.uri;
            this.n = a(this.b, this.q, this.m);
            this.o = dataSpec.httpMethod;
            this.p = dataSpec.flags;
            this.r = dataSpec.position;
            int a = a(dataSpec);
            this.v = a != -1;
            if (this.v) {
                a(a);
            }
            if (dataSpec.length == -1) {
                if (!this.v) {
                    this.s = this.b.a(this.q);
                    if (this.s != -1) {
                        this.s -= dataSpec.position;
                        if (this.s <= 0) {
                            throw new DataSourceException(0);
                        }
                    }
                    a(false);
                    return this.s;
                }
            }
            this.s = dataSpec.length;
            a(false);
            return this.s;
        } catch (IOException e) {
            b(e);
            throw e;
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        if (this.s == 0) {
            return -1;
        }
        try {
            if (this.r >= this.x) {
                a(true);
            }
            int read = this.k.read(bArr, i, i2);
            if (read != -1) {
                if (d()) {
                    this.w += (long) read;
                }
                long j = (long) read;
                this.r += j;
                if (this.s != -1) {
                    this.s -= j;
                }
            } else if (this.l) {
                a();
            } else {
                if (this.s <= 0) {
                    if (this.s == -1) {
                    }
                }
                f();
                a(false);
                return read(bArr, i, i2);
            }
            return read;
        } catch (IOException e) {
            if (this.l && a(e)) {
                a();
                return -1;
            }
            b(e);
            throw e;
        }
    }

    @Nullable
    public Uri getUri() {
        return this.n;
    }

    public Map<String, List<String>> getResponseHeaders() {
        if (b()) {
            return this.e.getResponseHeaders();
        }
        return Collections.emptyMap();
    }

    public void close() throws IOException {
        this.m = null;
        this.n = null;
        this.o = 1;
        g();
        try {
            f();
        } catch (IOException e) {
            b(e);
            throw e;
        }
    }

    private void a(boolean z) throws IOException {
        d dVar;
        DataSource dataSource;
        DataSpec dataSpec;
        if (this.v || (this.a && this.m.toString().contains("master.m3u8"))) {
            dVar = null;
        } else if (this.h) {
            try {
                dVar = this.b.a(this.q, this.r);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            }
        } else {
            dVar = this.b.b(this.q, this.r);
        }
        if (dVar == null) {
            dataSource = this.e;
            dataSpec = new DataSpec(this.m, this.o, null, this.r, this.r, this.s, this.q, this.p);
        } else if (dVar.d) {
            long j = this.r - dVar.b;
            long j2 = dVar.c - j;
            dataSpec = new DataSpec(Uri.fromFile(dVar.e), this.r, j, this.s != -1 ? Math.min(j2, this.s) : j2, this.q, this.p);
            dataSource = this.c;
        } else {
            long j3;
            if (dVar.a()) {
                j3 = this.s;
            } else {
                j3 = dVar.c;
                if (this.s != -1) {
                    j3 = Math.min(j3, this.s);
                }
            }
            DataSpec dataSpec2 = new DataSpec(this.m, this.o, null, this.r, this.r, j3, this.q, this.p);
            if (this.d != null) {
                dataSource = this.d;
            } else {
                DataSource dataSource2 = this.e;
                this.b.a(dVar);
                dVar = null;
                dataSource = dataSource2;
            }
        }
        long j4 = (this.v || dataSource != this.e) ? Long.MAX_VALUE : this.r + 102400;
        this.x = j4;
        if (z) {
            Assertions.checkState(c());
            if (dataSource != this.e) {
                try {
                    f();
                } catch (Throwable th) {
                    if (dVar.b()) {
                        this.b.a(dVar);
                    }
                }
            } else {
                return;
            }
        }
        if (dVar != null && dVar.b()) {
            this.t = dVar;
        }
        this.k = dataSource;
        this.l = dataSpec.length == -1;
        long open = dataSource.open(dataSpec);
        k kVar = new k();
        if (this.l && open != -1) {
            this.s = open;
            j.a(kVar, this.r + this.s);
        }
        if (b()) {
            this.n = this.k.getUri();
            if ((this.m.equals(this.n) ^ 1) != 0) {
                j.a(kVar, this.n);
            } else {
                j.a(kVar);
            }
        }
        if (e()) {
            this.b.a(this.q, kVar);
        }
    }

    private void a() throws IOException {
        this.s = 0;
        if (e()) {
            this.b.c(this.q, this.r);
        }
    }

    private static Uri a(Cache cache, String str, Uri uri) {
        Uri b = j.b(cache.b(str));
        return b == null ? uri : b;
    }

    private static boolean a(IOException iOException) {
        Throwable iOException2;
        while (iOException2 != null) {
            if ((iOException2 instanceof DataSourceException) && ((DataSourceException) iOException2).reason == 0) {
                return true;
            }
            iOException2 = iOException2.getCause();
        }
        return false;
    }

    private boolean b() {
        return d() ^ 1;
    }

    private boolean c() {
        return this.k == this.e;
    }

    private boolean d() {
        return this.k == this.c;
    }

    private boolean e() {
        return this.k == this.d;
    }

    private void f() throws IOException {
        if (this.k != null) {
            try {
                this.k.close();
            } finally {
                this.k = null;
                this.l = false;
                if (this.t != null) {
                    this.b.a(this.t);
                    this.t = null;
                }
            }
        }
    }

    private void b(IOException iOException) {
        if (d() || (iOException instanceof CacheException)) {
            this.u = true;
        }
    }

    private int a(DataSpec dataSpec) {
        if (this.i && this.u) {
            return 0;
        }
        return (this.j && dataSpec.length == -1) ? 1 : -1;
    }

    private void a(int i) {
        if (this.g != null) {
            this.g.a(i);
        }
    }

    private void g() {
        if (this.g != null && this.w > 0) {
            this.g.a(this.b.a(), this.w);
            this.w = 0;
        }
    }
}
