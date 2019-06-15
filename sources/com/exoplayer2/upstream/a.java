package com.exoplayer2.upstream;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.upstream.AssetDataSource;
import com.google.android.exoplayer2.upstream.ContentDataSource;
import com.google.android.exoplayer2.upstream.DataSchemeDataSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class a implements DataSource {
    private final Context a;
    private final List<TransferListener> b = new ArrayList();
    private final DataSource c;
    private boolean d;
    @Nullable
    private DataSource e;
    @Nullable
    private DataSource f;
    @Nullable
    private DataSource g;
    @Nullable
    private DataSource h;
    @Nullable
    private DataSource i;
    @Nullable
    private DataSource j;
    @Nullable
    private DataSource k;

    public a(Context context, DataSource dataSource, boolean z) {
        this.a = context.getApplicationContext();
        this.c = (DataSource) Assertions.checkNotNull(dataSource);
        this.d = z;
    }

    public void addTransferListener(TransferListener transferListener) {
        this.c.addTransferListener(transferListener);
        this.b.add(transferListener);
        a(this.e, transferListener);
        a(this.f, transferListener);
        a(this.g, transferListener);
        a(this.h, transferListener);
        a(this.i, transferListener);
        a(this.j, transferListener);
    }

    public long open(DataSpec dataSpec) throws IOException {
        Assertions.checkState(this.k == null);
        String scheme = dataSpec.uri.getScheme();
        if (Util.isLocalFileUri(dataSpec.uri)) {
            if (dataSpec.uri.getPath().startsWith("/android_asset/")) {
                this.k = b();
            } else {
                this.k = a();
            }
        } else if ("asset".equals(scheme)) {
            this.k = b();
        } else if ("content".equals(scheme)) {
            this.k = c();
        } else if ("rtmp".equals(scheme)) {
            this.k = d();
        } else if ("data".equals(scheme)) {
            this.k = e();
        } else if (RawResourceDataSource.RAW_RESOURCE_SCHEME.equals(scheme)) {
            this.k = f();
        } else {
            this.k = this.c;
        }
        return this.k.open(dataSpec);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        return ((DataSource) Assertions.checkNotNull(this.k)).read(bArr, i, i2);
    }

    @Nullable
    public Uri getUri() {
        return this.k == null ? null : this.k.getUri();
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.k == null ? Collections.emptyMap() : this.k.getResponseHeaders();
    }

    public void close() throws IOException {
        if (this.k != null) {
            try {
                this.k.close();
            } finally {
                this.k = null;
            }
        }
    }

    private DataSource a() {
        if (this.e == null) {
            if (this.d) {
                this.e = new EncryptedFileDataSource2();
            } else {
                this.e = new FileDataSource();
            }
            a(this.e);
        }
        return this.e;
    }

    private DataSource b() {
        if (this.f == null) {
            this.f = new AssetDataSource(this.a);
            a(this.f);
        }
        return this.f;
    }

    private DataSource c() {
        if (this.g == null) {
            this.g = new ContentDataSource(this.a);
            a(this.g);
        }
        return this.g;
    }

    private DataSource d() {
        if (this.h == null) {
            try {
                this.h = (DataSource) Class.forName("com.google.android.exoplayer2.ext.rtmp.RtmpDataSource").getConstructor(new Class[0]).newInstance(new Object[0]);
                a(this.h);
            } catch (ClassNotFoundException unused) {
                Log.w("DefaultDataSource", "Attempting to play RTMP stream without depending on the RTMP extension");
            } catch (Exception e) {
                throw new RuntimeException("Error instantiating RTMP extension", e);
            }
            if (this.h == null) {
                this.h = this.c;
            }
        }
        return this.h;
    }

    private DataSource e() {
        if (this.i == null) {
            this.i = new DataSchemeDataSource();
            a(this.i);
        }
        return this.i;
    }

    private DataSource f() {
        if (this.j == null) {
            this.j = new RawResourceDataSource(this.a);
            a(this.j);
        }
        return this.j;
    }

    private void a(DataSource dataSource) {
        for (int i = 0; i < this.b.size(); i++) {
            dataSource.addTransferListener((TransferListener) this.b.get(i));
        }
    }

    private void a(@Nullable DataSource dataSource, TransferListener transferListener) {
        if (dataSource != null) {
            dataSource.addTransferListener(transferListener);
        }
    }
}
