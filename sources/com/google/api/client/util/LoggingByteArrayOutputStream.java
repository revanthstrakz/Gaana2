package com.google.api.client.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingByteArrayOutputStream extends ByteArrayOutputStream {
    private int bytesWritten;
    private boolean closed;
    private final Logger logger;
    private final Level loggingLevel;
    private final int maximumBytesToLog;

    public LoggingByteArrayOutputStream(Logger logger, Level level, int i) {
        this.logger = (Logger) Preconditions.checkNotNull(logger);
        this.loggingLevel = (Level) Preconditions.checkNotNull(level);
        Preconditions.checkArgument(i >= 0);
        this.maximumBytesToLog = i;
    }

    public synchronized void write(int i) {
        Preconditions.checkArgument(this.closed ^ 1);
        this.bytesWritten++;
        if (this.count < this.maximumBytesToLog) {
            super.write(i);
        }
    }

    public synchronized void write(byte[] bArr, int i, int i2) {
        Preconditions.checkArgument(this.closed ^ 1);
        this.bytesWritten += i2;
        if (this.count < this.maximumBytesToLog) {
            int i3 = this.count + i2;
            if (i3 > this.maximumBytesToLog) {
                i2 += this.maximumBytesToLog - i3;
            }
            super.write(bArr, i, i2);
        }
    }

    public synchronized void close() throws IOException {
        if (!this.closed) {
            if (this.bytesWritten != 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Total: ");
                appendBytes(stringBuilder, this.bytesWritten);
                if (this.count != 0 && this.count < this.bytesWritten) {
                    stringBuilder.append(" (logging first ");
                    appendBytes(stringBuilder, this.count);
                    stringBuilder.append(")");
                }
                this.logger.config(stringBuilder.toString());
                if (this.count != 0) {
                    this.logger.log(this.loggingLevel, toString("UTF-8").replaceAll("[\\x00-\\x09\\x0B\\x0C\\x0E-\\x1F\\x7F]", " "));
                }
            }
            this.closed = true;
        }
    }

    public final int getMaximumBytesToLog() {
        return this.maximumBytesToLog;
    }

    public final synchronized int getBytesWritten() {
        return this.bytesWritten;
    }

    private static void appendBytes(StringBuilder stringBuilder, int i) {
        if (i == 1) {
            stringBuilder.append("1 byte");
            return;
        }
        stringBuilder.append(NumberFormat.getInstance().format((long) i));
        stringBuilder.append(" bytes");
    }
}
