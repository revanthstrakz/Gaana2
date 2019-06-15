package com.google.android.exoplayer2.upstream;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.Nullable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public final class RawResourceDataSource extends BaseDataSource {
    public static final String RAW_RESOURCE_SCHEME = "rawresource";
    @Nullable
    private AssetFileDescriptor assetFileDescriptor;
    private long bytesRemaining;
    @Nullable
    private InputStream inputStream;
    private boolean opened;
    private final Resources resources;
    @Nullable
    private Uri uri;

    public static class RawResourceDataSourceException extends IOException {
        public RawResourceDataSourceException(String str) {
            super(str);
        }

        public RawResourceDataSourceException(IOException iOException) {
            super(iOException);
        }
    }

    public static Uri buildRawResourceUri(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("rawresource:///");
        stringBuilder.append(i);
        return Uri.parse(stringBuilder.toString());
    }

    public RawResourceDataSource(Context context) {
        super(false);
        this.resources = context.getResources();
    }

    @Deprecated
    public RawResourceDataSource(Context context, @Nullable TransferListener transferListener) {
        this(context);
        if (transferListener != null) {
            addTransferListener(transferListener);
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0083 */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing block: B:24:0x008a, code skipped:
            throw new com.google.android.exoplayer2.upstream.RawResourceDataSource.RawResourceDataSourceException("Resource identifier must be an integer.");
     */
    public long open(com.google.android.exoplayer2.upstream.DataSpec r7) throws com.google.android.exoplayer2.upstream.RawResourceDataSource.RawResourceDataSourceException {
        /*
        r6 = this;
        r0 = r7.uri;	 Catch:{ IOException -> 0x008b }
        r6.uri = r0;	 Catch:{ IOException -> 0x008b }
        r0 = "rawresource";
        r1 = r6.uri;	 Catch:{ IOException -> 0x008b }
        r1 = r1.getScheme();	 Catch:{ IOException -> 0x008b }
        r0 = android.text.TextUtils.equals(r0, r1);	 Catch:{ IOException -> 0x008b }
        if (r0 != 0) goto L_0x001a;
    L_0x0012:
        r7 = new com.google.android.exoplayer2.upstream.RawResourceDataSource$RawResourceDataSourceException;	 Catch:{ IOException -> 0x008b }
        r0 = "URI must use scheme rawresource";
        r7.<init>(r0);	 Catch:{ IOException -> 0x008b }
        throw r7;	 Catch:{ IOException -> 0x008b }
    L_0x001a:
        r0 = r6.uri;	 Catch:{ NumberFormatException -> 0x0083 }
        r0 = r0.getLastPathSegment();	 Catch:{ NumberFormatException -> 0x0083 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x0083 }
        r6.transferInitializing(r7);	 Catch:{ IOException -> 0x008b }
        r1 = r6.resources;	 Catch:{ IOException -> 0x008b }
        r0 = r1.openRawResourceFd(r0);	 Catch:{ IOException -> 0x008b }
        r6.assetFileDescriptor = r0;	 Catch:{ IOException -> 0x008b }
        r0 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x008b }
        r1 = r6.assetFileDescriptor;	 Catch:{ IOException -> 0x008b }
        r1 = r1.getFileDescriptor();	 Catch:{ IOException -> 0x008b }
        r0.<init>(r1);	 Catch:{ IOException -> 0x008b }
        r6.inputStream = r0;	 Catch:{ IOException -> 0x008b }
        r0 = r6.inputStream;	 Catch:{ IOException -> 0x008b }
        r1 = r6.assetFileDescriptor;	 Catch:{ IOException -> 0x008b }
        r1 = r1.getStartOffset();	 Catch:{ IOException -> 0x008b }
        r0.skip(r1);	 Catch:{ IOException -> 0x008b }
        r0 = r6.inputStream;	 Catch:{ IOException -> 0x008b }
        r1 = r7.position;	 Catch:{ IOException -> 0x008b }
        r0 = r0.skip(r1);	 Catch:{ IOException -> 0x008b }
        r2 = r7.position;	 Catch:{ IOException -> 0x008b }
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 >= 0) goto L_0x005b;
    L_0x0055:
        r7 = new java.io.EOFException;	 Catch:{ IOException -> 0x008b }
        r7.<init>();	 Catch:{ IOException -> 0x008b }
        throw r7;	 Catch:{ IOException -> 0x008b }
    L_0x005b:
        r0 = r7.length;	 Catch:{ IOException -> 0x008b }
        r2 = -1;
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 == 0) goto L_0x0068;
    L_0x0063:
        r0 = r7.length;	 Catch:{ IOException -> 0x008b }
        r6.bytesRemaining = r0;	 Catch:{ IOException -> 0x008b }
        goto L_0x007a;
    L_0x0068:
        r0 = r6.assetFileDescriptor;	 Catch:{ IOException -> 0x008b }
        r0 = r0.getLength();	 Catch:{ IOException -> 0x008b }
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 != 0) goto L_0x0073;
    L_0x0072:
        goto L_0x0078;
    L_0x0073:
        r2 = r7.position;	 Catch:{ IOException -> 0x008b }
        r4 = r0 - r2;
        r2 = r4;
    L_0x0078:
        r6.bytesRemaining = r2;	 Catch:{ IOException -> 0x008b }
    L_0x007a:
        r0 = 1;
        r6.opened = r0;
        r6.transferStarted(r7);
        r0 = r6.bytesRemaining;
        return r0;
    L_0x0083:
        r7 = new com.google.android.exoplayer2.upstream.RawResourceDataSource$RawResourceDataSourceException;	 Catch:{ IOException -> 0x008b }
        r0 = "Resource identifier must be an integer.";
        r7.<init>(r0);	 Catch:{ IOException -> 0x008b }
        throw r7;	 Catch:{ IOException -> 0x008b }
    L_0x008b:
        r7 = move-exception;
        r0 = new com.google.android.exoplayer2.upstream.RawResourceDataSource$RawResourceDataSourceException;
        r0.<init>(r7);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.RawResourceDataSource.open(com.google.android.exoplayer2.upstream.DataSpec):long");
    }

    public int read(byte[] bArr, int i, int i2) throws RawResourceDataSourceException {
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
                throw new RawResourceDataSourceException(new EOFException());
            }
        } catch (IOException e) {
            throw new RawResourceDataSourceException(e);
        }
    }

    @Nullable
    public Uri getUri() {
        return this.uri;
    }

    public void close() throws RawResourceDataSourceException {
        this.uri = null;
        try {
            if (this.inputStream != null) {
                this.inputStream.close();
            }
            this.inputStream = null;
            try {
                if (this.assetFileDescriptor != null) {
                    this.assetFileDescriptor.close();
                }
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    transferEnded();
                }
            } catch (IOException e) {
                throw new RawResourceDataSourceException(e);
            } catch (Throwable th) {
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    transferEnded();
                }
            }
        } catch (IOException e2) {
            throw new RawResourceDataSourceException(e2);
        } catch (Throwable th2) {
            this.inputStream = null;
            try {
                if (this.assetFileDescriptor != null) {
                    this.assetFileDescriptor.close();
                }
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    transferEnded();
                }
            } catch (IOException e22) {
                throw new RawResourceDataSourceException(e22);
            } catch (Throwable th3) {
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    transferEnded();
                }
            }
        }
    }
}
