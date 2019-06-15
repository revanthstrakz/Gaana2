package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;

public class HttpResponseException extends IOException {
    private static final long serialVersionUID = -1875819453475890043L;
    private final String content;
    private final transient HttpHeaders headers;
    private final int statusCode;
    private final String statusMessage;

    public static class Builder {
        String content;
        HttpHeaders headers;
        String message;
        int statusCode;
        String statusMessage;

        public Builder(int i, String str, HttpHeaders httpHeaders) {
            setStatusCode(i);
            setStatusMessage(str);
            setHeaders(httpHeaders);
        }

        public Builder(HttpResponse httpResponse) {
            this(httpResponse.getStatusCode(), httpResponse.getStatusMessage(), httpResponse.getHeaders());
            try {
                this.content = httpResponse.parseAsString();
                if (this.content.length() == 0) {
                    this.content = null;
                }
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
            }
            StringBuilder computeMessageBuffer = HttpResponseException.computeMessageBuffer(httpResponse);
            if (this.content != null) {
                computeMessageBuffer.append(StringUtils.LINE_SEPARATOR);
                computeMessageBuffer.append(this.content);
            }
            this.message = computeMessageBuffer.toString();
        }

        public final String getMessage() {
            return this.message;
        }

        public Builder setMessage(String str) {
            this.message = str;
            return this;
        }

        public final int getStatusCode() {
            return this.statusCode;
        }

        public Builder setStatusCode(int i) {
            Preconditions.checkArgument(i >= 0);
            this.statusCode = i;
            return this;
        }

        public final String getStatusMessage() {
            return this.statusMessage;
        }

        public Builder setStatusMessage(String str) {
            this.statusMessage = str;
            return this;
        }

        public HttpHeaders getHeaders() {
            return this.headers;
        }

        public Builder setHeaders(HttpHeaders httpHeaders) {
            this.headers = (HttpHeaders) Preconditions.checkNotNull(httpHeaders);
            return this;
        }

        public final String getContent() {
            return this.content;
        }

        public Builder setContent(String str) {
            this.content = str;
            return this;
        }

        public HttpResponseException build() {
            return new HttpResponseException(this);
        }
    }

    public HttpResponseException(HttpResponse httpResponse) {
        this(new Builder(httpResponse));
    }

    protected HttpResponseException(Builder builder) {
        super(builder.message);
        this.statusCode = builder.statusCode;
        this.statusMessage = builder.statusMessage;
        this.headers = builder.headers;
        this.content = builder.content;
    }

    public final boolean isSuccessStatusCode() {
        return HttpStatusCodes.isSuccess(this.statusCode);
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public final String getStatusMessage() {
        return this.statusMessage;
    }

    public HttpHeaders getHeaders() {
        return this.headers;
    }

    public final String getContent() {
        return this.content;
    }

    public static StringBuilder computeMessageBuffer(HttpResponse httpResponse) {
        StringBuilder stringBuilder = new StringBuilder();
        int statusCode = httpResponse.getStatusCode();
        if (statusCode != 0) {
            stringBuilder.append(statusCode);
        }
        String statusMessage = httpResponse.getStatusMessage();
        if (statusMessage != null) {
            if (statusCode != 0) {
                stringBuilder.append(' ');
            }
            stringBuilder.append(statusMessage);
        }
        return stringBuilder;
    }
}
