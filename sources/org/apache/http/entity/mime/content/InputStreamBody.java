package org.apache.http.entity.mime.content;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.entity.mime.MIME;

public class InputStreamBody extends AbstractContentBody {
    private final String filename;
    private final InputStream in;

    public String getCharset() {
        return null;
    }

    public long getContentLength() {
        return -1;
    }

    public String getTransferEncoding() {
        return MIME.ENC_BINARY;
    }

    public InputStreamBody(InputStream inputStream, String str, String str2) {
        super(str);
        if (inputStream == null) {
            throw new IllegalArgumentException("Input stream may not be null");
        }
        this.in = inputStream;
        this.filename = str2;
    }

    public InputStreamBody(InputStream inputStream, String str) {
        this(inputStream, "application/octet-stream", str);
    }

    public InputStream getInputStream() {
        return this.in;
    }

    @Deprecated
    public void writeTo(OutputStream outputStream, int i) throws IOException {
        writeTo(outputStream);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = this.in.read(bArr);
                if (read == -1) {
                    break;
                }
                outputStream.write(bArr, 0, read);
            }
            outputStream.flush();
        } finally {
            this.in.close();
        }
    }

    public String getFilename() {
        return this.filename;
    }
}
