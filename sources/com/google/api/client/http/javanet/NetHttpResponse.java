package com.google.api.client.http.javanet;

import com.google.api.client.http.LowLevelHttpResponse;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.apache.http.entity.mime.MIME;

final class NetHttpResponse extends LowLevelHttpResponse {
    private final HttpURLConnection connection;
    private final ArrayList<String> headerNames = new ArrayList();
    private final ArrayList<String> headerValues = new ArrayList();
    private final int responseCode;
    private final String responseMessage;

    private final class SizeValidatingInputStream extends FilterInputStream {
        private long bytesRead = 0;

        public SizeValidatingInputStream(InputStream inputStream) {
            super(inputStream);
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = this.in.read(bArr, i, i2);
            if (read == -1) {
                throwIfFalseEOF();
            } else {
                this.bytesRead += (long) read;
            }
            return read;
        }

        public int read() throws IOException {
            int read = this.in.read();
            if (read == -1) {
                throwIfFalseEOF();
            } else {
                this.bytesRead++;
            }
            return read;
        }

        private void throwIfFalseEOF() throws IOException {
            long contentLength = NetHttpResponse.this.getContentLength();
            if (contentLength != -1 && this.bytesRead != 0 && this.bytesRead < contentLength) {
                long j = this.bytesRead;
                StringBuilder stringBuilder = new StringBuilder(102);
                stringBuilder.append("Connection closed prematurely: bytesRead = ");
                stringBuilder.append(j);
                stringBuilder.append(", Content-Length = ");
                stringBuilder.append(contentLength);
                throw new IOException(stringBuilder.toString());
            }
        }
    }

    NetHttpResponse(HttpURLConnection httpURLConnection) throws IOException {
        this.connection = httpURLConnection;
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == -1) {
            responseCode = 0;
        }
        this.responseCode = responseCode;
        this.responseMessage = httpURLConnection.getResponseMessage();
        ArrayList arrayList = this.headerNames;
        ArrayList arrayList2 = this.headerValues;
        for (Entry entry : httpURLConnection.getHeaderFields().entrySet()) {
            String str = (String) entry.getKey();
            if (str != null) {
                for (String str2 : (List) entry.getValue()) {
                    if (str2 != null) {
                        arrayList.add(str);
                        arrayList2.add(str2);
                    }
                }
            }
        }
    }

    public int getStatusCode() {
        return this.responseCode;
    }

    public InputStream getContent() throws IOException {
        InputStream inputStream;
        try {
            inputStream = this.connection.getInputStream();
        } catch (IOException unused) {
            inputStream = this.connection.getErrorStream();
        }
        if (inputStream == null) {
            return null;
        }
        return new SizeValidatingInputStream(inputStream);
    }

    public String getContentEncoding() {
        return this.connection.getContentEncoding();
    }

    public long getContentLength() {
        String headerField = this.connection.getHeaderField("Content-Length");
        if (headerField == null) {
            return -1;
        }
        return Long.parseLong(headerField);
    }

    public String getContentType() {
        return this.connection.getHeaderField(MIME.CONTENT_TYPE);
    }

    public String getReasonPhrase() {
        return this.responseMessage;
    }

    public String getStatusLine() {
        String headerField = this.connection.getHeaderField(0);
        return (headerField == null || !headerField.startsWith("HTTP/1.")) ? null : headerField;
    }

    public int getHeaderCount() {
        return this.headerNames.size();
    }

    public String getHeaderName(int i) {
        return (String) this.headerNames.get(i);
    }

    public String getHeaderValue(int i) {
        return (String) this.headerValues.get(i);
    }

    public void disconnect() {
        this.connection.disconnect();
    }
}
