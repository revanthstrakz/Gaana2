package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Base64;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.net.URLDecoder;

public final class DataSchemeDataSource extends BaseDataSource {
    public static final String SCHEME_DATA = "data";
    private int bytesRead;
    @Nullable
    private byte[] data;
    @Nullable
    private DataSpec dataSpec;

    public DataSchemeDataSource() {
        super(false);
    }

    public long open(DataSpec dataSpec) throws IOException {
        transferInitializing(dataSpec);
        this.dataSpec = dataSpec;
        Uri uri = dataSpec.uri;
        String scheme = uri.getScheme();
        if ("data".equals(scheme)) {
            String[] split = Util.split(uri.getSchemeSpecificPart(), ",");
            if (split.length != 2) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unexpected URI format: ");
                stringBuilder.append(uri);
                throw new ParserException(stringBuilder.toString());
            }
            String str = split[1];
            if (split[0].contains(";base64")) {
                try {
                    this.data = Base64.decode(str, 0);
                } catch (IllegalArgumentException e) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Error while parsing Base64 encoded string: ");
                    stringBuilder2.append(str);
                    throw new ParserException(stringBuilder2.toString(), e);
                }
            }
            this.data = Util.getUtf8Bytes(URLDecoder.decode(str, C.ASCII_NAME));
            transferStarted(dataSpec);
            return (long) this.data.length;
        }
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Unsupported scheme: ");
        stringBuilder3.append(scheme);
        throw new ParserException(stringBuilder3.toString());
    }

    public int read(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        int length = this.data.length - this.bytesRead;
        if (length == 0) {
            return -1;
        }
        i2 = Math.min(i2, length);
        System.arraycopy(this.data, this.bytesRead, bArr, i, i2);
        this.bytesRead += i2;
        bytesTransferred(i2);
        return i2;
    }

    @Nullable
    public Uri getUri() {
        return this.dataSpec != null ? this.dataSpec.uri : null;
    }

    public void close() throws IOException {
        if (this.data != null) {
            this.data = null;
            transferEnded();
        }
        this.dataSpec = null;
    }
}
