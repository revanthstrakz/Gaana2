package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.til.colombia.android.internal.e;
import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public final class FileDataSource extends BaseDataSource {
    private long bytesRemaining;
    @Nullable
    private RandomAccessFile file;
    private boolean opened;
    @Nullable
    private Uri uri;

    public static class FileDataSourceException extends IOException {
        public FileDataSourceException(IOException iOException) {
            super(iOException);
        }
    }

    public FileDataSource() {
        super(false);
    }

    @Deprecated
    public FileDataSource(@Nullable TransferListener transferListener) {
        this();
        if (transferListener != null) {
            addTransferListener(transferListener);
        }
    }

    public long open(DataSpec dataSpec) throws FileDataSourceException {
        try {
            this.uri = dataSpec.uri;
            transferInitializing(dataSpec);
            this.file = new RandomAccessFile(dataSpec.uri.getPath(), e.o);
            this.file.seek(dataSpec.position);
            this.bytesRemaining = dataSpec.length == -1 ? this.file.length() - dataSpec.position : dataSpec.length;
            if (this.bytesRemaining < 0) {
                throw new EOFException();
            }
            this.opened = true;
            transferStarted(dataSpec);
            return this.bytesRemaining;
        } catch (IOException e) {
            throw new FileDataSourceException(e);
        }
    }

    public int read(byte[] bArr, int i, int i2) throws FileDataSourceException {
        if (i2 == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        try {
            int read = this.file.read(bArr, i, (int) Math.min(this.bytesRemaining, (long) i2));
            if (read > 0) {
                this.bytesRemaining -= (long) read;
                bytesTransferred(read);
            }
            return read;
        } catch (IOException e) {
            throw new FileDataSourceException(e);
        }
    }

    @Nullable
    public Uri getUri() {
        return this.uri;
    }

    public void close() throws FileDataSourceException {
        this.uri = null;
        try {
            if (this.file != null) {
                this.file.close();
            }
            this.file = null;
            if (this.opened) {
                this.opened = false;
                transferEnded();
            }
        } catch (IOException e) {
            throw new FileDataSourceException(e);
        } catch (Throwable th) {
            this.file = null;
            if (this.opened) {
                this.opened = false;
                transferEnded();
            }
        }
    }
}
