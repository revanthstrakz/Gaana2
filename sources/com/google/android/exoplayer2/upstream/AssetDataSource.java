package com.google.android.exoplayer2.upstream;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public final class AssetDataSource extends BaseDataSource {
    private final AssetManager assetManager;
    private long bytesRemaining;
    @Nullable
    private InputStream inputStream;
    private boolean opened;
    @Nullable
    private Uri uri;

    public static final class AssetDataSourceException extends IOException {
        public AssetDataSourceException(IOException iOException) {
            super(iOException);
        }
    }

    public AssetDataSource(Context context) {
        super(false);
        this.assetManager = context.getAssets();
    }

    @Deprecated
    public AssetDataSource(Context context, @Nullable TransferListener transferListener) {
        this(context);
        if (transferListener != null) {
            addTransferListener(transferListener);
        }
    }

    public long open(DataSpec dataSpec) throws AssetDataSourceException {
        try {
            this.uri = dataSpec.uri;
            String path = this.uri.getPath();
            if (path.startsWith("/android_asset/")) {
                path = path.substring(15);
            } else if (path.startsWith("/")) {
                path = path.substring(1);
            }
            transferInitializing(dataSpec);
            this.inputStream = this.assetManager.open(path, 1);
            if (this.inputStream.skip(dataSpec.position) < dataSpec.position) {
                throw new EOFException();
            }
            if (dataSpec.length != -1) {
                this.bytesRemaining = dataSpec.length;
            } else {
                this.bytesRemaining = (long) this.inputStream.available();
                if (this.bytesRemaining == 2147483647L) {
                    this.bytesRemaining = -1;
                }
            }
            this.opened = true;
            transferStarted(dataSpec);
            return this.bytesRemaining;
        } catch (IOException e) {
            throw new AssetDataSourceException(e);
        }
    }

    public int read(byte[] bArr, int i, int i2) throws AssetDataSourceException {
        if (i2 == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        try {
            if (this.bytesRemaining != -1) {
                i2 = (int) Math.min(this.bytesRemaining, (long) i2);
            }
            int read = this.inputStream.read(bArr, i, i2);
            if (read != -1) {
                if (this.bytesRemaining != -1) {
                    this.bytesRemaining -= (long) read;
                }
                bytesTransferred(read);
                return read;
            } else if (this.bytesRemaining == -1) {
                return -1;
            } else {
                throw new AssetDataSourceException(new EOFException());
            }
        } catch (IOException e) {
            throw new AssetDataSourceException(e);
        }
    }

    @Nullable
    public Uri getUri() {
        return this.uri;
    }

    public void close() throws AssetDataSourceException {
        this.uri = null;
        try {
            if (this.inputStream != null) {
                this.inputStream.close();
            }
            this.inputStream = null;
            if (this.opened) {
                this.opened = false;
                transferEnded();
            }
        } catch (IOException e) {
            throw new AssetDataSourceException(e);
        } catch (Throwable th) {
            this.inputStream = null;
            if (this.opened) {
                this.opened = false;
                transferEnded();
            }
        }
    }
}
