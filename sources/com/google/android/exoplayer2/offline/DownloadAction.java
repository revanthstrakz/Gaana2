package com.google.android.exoplayer2.offline;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class DownloadAction {
    @Nullable
    private static Deserializer[] defaultDeserializers;
    public final byte[] data;
    public final boolean isRemoveAction;
    public final String type;
    public final Uri uri;
    public final int version;

    public static abstract class Deserializer {
        public final String type;
        public final int version;

        public abstract DownloadAction readFromStream(int i, DataInputStream dataInputStream) throws IOException;

        public Deserializer(String str, int i) {
            this.type = str;
            this.version = i;
        }
    }

    public abstract Downloader createDownloader(DownloaderConstructorHelper downloaderConstructorHelper);

    public abstract void writeToStream(DataOutputStream dataOutputStream) throws IOException;

    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0023 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0033 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0043 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(23:8|9|10|11|12|13|14|15|16|19|20|21|22|23|26|27|28|29|30|33|34|35|36) */
    /* JADX WARNING: Can't wrap try/catch for region: R(23:8|9|10|11|12|13|14|15|16|19|20|21|22|23|26|27|28|29|30|33|34|35|36) */
    /* JADX WARNING: Can't wrap try/catch for region: R(23:8|9|10|11|12|13|14|15|16|19|20|21|22|23|26|27|28|29|30|33|34|35|36) */
    /* JADX WARNING: Missing block: B:24:0x0032, code skipped:
            r3 = r4;
     */
    /* JADX WARNING: Missing block: B:31:0x0042, code skipped:
            r4 = r3;
     */
    public static synchronized com.google.android.exoplayer2.offline.DownloadAction.Deserializer[] getDefaultDeserializers() {
        /*
        r0 = com.google.android.exoplayer2.offline.DownloadAction.class;
        monitor-enter(r0);
        r1 = defaultDeserializers;	 Catch:{ all -> 0x0055 }
        if (r1 == 0) goto L_0x000b;
    L_0x0007:
        r1 = defaultDeserializers;	 Catch:{ all -> 0x0055 }
        monitor-exit(r0);
        return r1;
    L_0x000b:
        r1 = 4;
        r1 = new com.google.android.exoplayer2.offline.DownloadAction.Deserializer[r1];	 Catch:{ all -> 0x0055 }
        r2 = 0;
        r3 = com.google.android.exoplayer2.offline.ProgressiveDownloadAction.DESERIALIZER;	 Catch:{ all -> 0x0055 }
        r1[r2] = r3;	 Catch:{ all -> 0x0055 }
        r2 = 1;
        r3 = "com.google.android.exoplayer2.source.dash.offline.DashDownloadAction";
        r3 = java.lang.Class.forName(r3);	 Catch:{ Exception -> 0x0022 }
        r4 = 2;
        r3 = getDeserializer(r3);	 Catch:{ Exception -> 0x0023 }
        r1[r2] = r3;	 Catch:{ Exception -> 0x0023 }
        goto L_0x0023;
    L_0x0022:
        r4 = r2;
    L_0x0023:
        r2 = "com.google.android.exoplayer2.source.hls.offline.HlsDownloadAction";
        r2 = java.lang.Class.forName(r2);	 Catch:{ Exception -> 0x0032 }
        r3 = r4 + 1;
        r2 = getDeserializer(r2);	 Catch:{ Exception -> 0x0033 }
        r1[r4] = r2;	 Catch:{ Exception -> 0x0033 }
        goto L_0x0033;
    L_0x0032:
        r3 = r4;
    L_0x0033:
        r2 = "com.google.android.exoplayer2.source.smoothstreaming.offline.SsDownloadAction";
        r2 = java.lang.Class.forName(r2);	 Catch:{ Exception -> 0x0042 }
        r4 = r3 + 1;
        r2 = getDeserializer(r2);	 Catch:{ Exception -> 0x0043 }
        r1[r3] = r2;	 Catch:{ Exception -> 0x0043 }
        goto L_0x0043;
    L_0x0042:
        r4 = r3;
    L_0x0043:
        r1 = com.google.android.exoplayer2.util.Assertions.checkNotNull(r1);	 Catch:{ all -> 0x0055 }
        r1 = (java.lang.Object[]) r1;	 Catch:{ all -> 0x0055 }
        r1 = java.util.Arrays.copyOf(r1, r4);	 Catch:{ all -> 0x0055 }
        r1 = (com.google.android.exoplayer2.offline.DownloadAction.Deserializer[]) r1;	 Catch:{ all -> 0x0055 }
        defaultDeserializers = r1;	 Catch:{ all -> 0x0055 }
        r1 = defaultDeserializers;	 Catch:{ all -> 0x0055 }
        monitor-exit(r0);
        return r1;
    L_0x0055:
        r1 = move-exception;
        monitor-exit(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.offline.DownloadAction.getDefaultDeserializers():com.google.android.exoplayer2.offline.DownloadAction$Deserializer[]");
    }

    public static DownloadAction deserializeFromStream(Deserializer[] deserializerArr, InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String readUTF = dataInputStream.readUTF();
        int readInt = dataInputStream.readInt();
        for (Deserializer deserializer : deserializerArr) {
            if (readUTF.equals(deserializer.type) && deserializer.version >= readInt) {
                return deserializer.readFromStream(readInt, dataInputStream);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("No deserializer found for:");
        stringBuilder.append(readUTF);
        stringBuilder.append(", ");
        stringBuilder.append(readInt);
        throw new DownloadException(stringBuilder.toString());
    }

    public static void serializeToStream(DownloadAction downloadAction, OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeUTF(downloadAction.type);
        dataOutputStream.writeInt(downloadAction.version);
        downloadAction.writeToStream(dataOutputStream);
        dataOutputStream.flush();
    }

    protected DownloadAction(String str, int i, Uri uri, boolean z, @Nullable byte[] bArr) {
        this.type = str;
        this.version = i;
        this.uri = uri;
        this.isRemoveAction = z;
        if (bArr == null) {
            bArr = Util.EMPTY_BYTE_ARRAY;
        }
        this.data = bArr;
    }

    public final byte[] toByteArray() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            serializeToStream(this, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new IllegalStateException();
        }
    }

    public boolean isSameMedia(DownloadAction downloadAction) {
        return this.uri.equals(downloadAction.uri);
    }

    public List<StreamKey> getKeys() {
        return Collections.emptyList();
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = false;
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DownloadAction downloadAction = (DownloadAction) obj;
        if (this.type.equals(downloadAction.type) && this.version == downloadAction.version && this.uri.equals(downloadAction.uri) && this.isRemoveAction == downloadAction.isRemoveAction && Arrays.equals(this.data, downloadAction.data)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return (31 * ((this.uri.hashCode() * 31) + this.isRemoveAction)) + Arrays.hashCode(this.data);
    }

    private static Deserializer getDeserializer(Class<?> cls) throws NoSuchFieldException, IllegalAccessException {
        return (Deserializer) Assertions.checkNotNull(cls.getDeclaredField("DESERIALIZER").get(null));
    }
}
