package com.google.api.client.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingInputStream extends FilterInputStream {
    private final LoggingByteArrayOutputStream logStream;

    public LoggingInputStream(InputStream inputStream, Logger logger, Level level, int i) {
        super(inputStream);
        this.logStream = new LoggingByteArrayOutputStream(logger, level, i);
    }

    public int read() throws IOException {
        int read = super.read();
        this.logStream.write(read);
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        i2 = super.read(bArr, i, i2);
        if (i2 > 0) {
            this.logStream.write(bArr, i, i2);
        }
        return i2;
    }

    public void close() throws IOException {
        this.logStream.close();
        super.close();
    }

    public final LoggingByteArrayOutputStream getLogStream() {
        return this.logStream;
    }
}
