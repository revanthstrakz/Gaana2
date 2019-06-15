package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import java.io.IOException;
import java.util.Map;

public final class DummyDataSource implements DataSource {
    public static final Factory FACTORY = DummyDataSource$$Lambda$0.$instance;
    public static final DummyDataSource INSTANCE = new DummyDataSource();

    public void addTransferListener(TransferListener transferListener) {
    }

    public void close() throws IOException {
    }

    public Map getResponseHeaders() {
        return DataSource$$CC.getResponseHeaders(this);
    }

    @Nullable
    public Uri getUri() {
        return null;
    }

    private DummyDataSource() {
    }

    public long open(DataSpec dataSpec) throws IOException {
        throw new IOException("Dummy source");
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        throw new UnsupportedOperationException();
    }
}
