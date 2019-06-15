package com.exoplayer2.upstream;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.upstream.BaseDataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.til.colombia.android.internal.e;
import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public final class FileDataSource extends BaseDataSource {
    @Nullable
    private RandomAccessFile a;
    @Nullable
    private Uri b;
    private long c;
    private boolean d;

    public static class FileDataSourceException extends IOException {
        public FileDataSourceException(IOException iOException) {
            super(iOException);
        }
    }

    public FileDataSource() {
        super(false);
    }

    public long open(DataSpec dataSpec) throws FileDataSourceException {
        try {
            this.b = dataSpec.uri;
            transferInitializing(dataSpec);
            this.a = new RandomAccessFile(dataSpec.uri.getPath(), e.o);
            this.a.seek(dataSpec.position);
            this.c = dataSpec.length == -1 ? this.a.length() - dataSpec.position : dataSpec.length;
            if (this.c < 0) {
                throw new EOFException();
            }
            this.d = true;
            transferStarted(dataSpec);
            return this.c;
        } catch (IOException e) {
            throw new FileDataSourceException(e);
        }
    }

    public int read(byte[] bArr, int i, int i2) throws FileDataSourceException {
        if (i2 == 0) {
            return 0;
        }
        if (this.c == 0) {
            return -1;
        }
        try {
            int read = this.a.read(bArr, i, (int) Math.min(this.c, (long) i2));
            if (read > 0) {
                this.c -= (long) read;
                bytesTransferred(read);
            }
            return read;
        } catch (IOException e) {
            throw new FileDataSourceException(e);
        }
    }

    @Nullable
    public Uri getUri() {
        return this.b;
    }

    public void close() throws FileDataSourceException {
        this.b = null;
        try {
            if (this.a != null) {
                this.a.close();
            }
            this.a = null;
            if (this.d) {
                this.d = false;
                transferEnded();
            }
        } catch (IOException e) {
            throw new FileDataSourceException(e);
        } catch (Throwable th) {
            this.a = null;
            if (this.d) {
                this.d = false;
                transferEnded();
            }
        }
    }
}
