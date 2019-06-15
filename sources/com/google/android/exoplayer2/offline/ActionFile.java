package com.google.android.exoplayer2.offline;

import com.google.android.exoplayer2.offline.DownloadAction.Deserializer;
import com.google.android.exoplayer2.util.AtomicFile;
import com.google.android.exoplayer2.util.Util;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

public final class ActionFile {
    static final int VERSION = 0;
    private final File actionFile;
    private final AtomicFile atomicFile;

    public ActionFile(File file) {
        this.actionFile = file;
        this.atomicFile = new AtomicFile(file);
    }

    public DownloadAction[] load(Deserializer... deserializerArr) throws IOException {
        Throwable th;
        int i = 0;
        if (!this.actionFile.exists()) {
            return new DownloadAction[0];
        }
        Closeable openRead;
        try {
            openRead = this.atomicFile.openRead();
            try {
                DataInputStream dataInputStream = new DataInputStream(openRead);
                int readInt = dataInputStream.readInt();
                if (readInt > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Unsupported action file version: ");
                    stringBuilder.append(readInt);
                    throw new IOException(stringBuilder.toString());
                }
                readInt = dataInputStream.readInt();
                DownloadAction[] downloadActionArr = new DownloadAction[readInt];
                while (i < readInt) {
                    downloadActionArr[i] = DownloadAction.deserializeFromStream(deserializerArr, dataInputStream);
                    i++;
                }
                Util.closeQuietly(openRead);
                return downloadActionArr;
            } catch (Throwable th2) {
                th = th2;
                Util.closeQuietly(openRead);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            openRead = null;
            Util.closeQuietly(openRead);
            throw th;
        }
    }

    public void store(DownloadAction... downloadActionArr) throws IOException {
        Throwable th;
        Closeable dataOutputStream;
        try {
            dataOutputStream = new DataOutputStream(this.atomicFile.startWrite());
            int i = 0;
            try {
                dataOutputStream.writeInt(0);
                dataOutputStream.writeInt(downloadActionArr.length);
                int length = downloadActionArr.length;
                while (i < length) {
                    DownloadAction.serializeToStream(downloadActionArr[i], dataOutputStream);
                    i++;
                }
                this.atomicFile.endWrite(dataOutputStream);
                Util.closeQuietly(null);
            } catch (Throwable th2) {
                th = th2;
                Util.closeQuietly(dataOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            dataOutputStream = null;
            Util.closeQuietly(dataOutputStream);
            throw th;
        }
    }
}
