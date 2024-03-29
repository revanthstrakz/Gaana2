package com.google.android.exoplayer2.upstream.crypto;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public final class AesCipherDataSource implements DataSource {
    @Nullable
    private AesFlushingCipher cipher;
    private final byte[] secretKey;
    private final DataSource upstream;

    public AesCipherDataSource(byte[] bArr, DataSource dataSource) {
        this.upstream = dataSource;
        this.secretKey = bArr;
    }

    public void addTransferListener(TransferListener transferListener) {
        this.upstream.addTransferListener(transferListener);
    }

    public long open(DataSpec dataSpec) throws IOException {
        long open = this.upstream.open(dataSpec);
        this.cipher = new AesFlushingCipher(2, this.secretKey, CryptoUtil.getFNV64Hash(dataSpec.key), dataSpec.absoluteStreamPosition);
        return open;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        i2 = this.upstream.read(bArr, i, i2);
        if (i2 == -1) {
            return -1;
        }
        this.cipher.updateInPlace(bArr, i, i2);
        return i2;
    }

    @Nullable
    public Uri getUri() {
        return this.upstream.getUri();
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.upstream.getResponseHeaders();
    }

    public void close() throws IOException {
        this.cipher = null;
        this.upstream.close();
    }
}
