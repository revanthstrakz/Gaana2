package com.google.api.client.googleapis.batch;

import com.google.api.client.http.BackOffPolicy;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.util.ByteStreams;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

final class BatchUnparsedResponse {
    boolean backOffRequired;
    private final String boundary;
    private int contentId = 0;
    boolean hasNext = true;
    private final InputStream inputStream;
    private final List<RequestInfo<?, ?>> requestInfos;
    private final boolean retryAllowed;
    List<RequestInfo<?, ?>> unsuccessfulRequestInfos = new ArrayList();

    private static class FakeLowLevelHttpRequest extends LowLevelHttpRequest {
        private List<String> headerNames;
        private List<String> headerValues;
        private InputStream partContent;
        private int statusCode;

        public void addHeader(String str, String str2) {
        }

        FakeLowLevelHttpRequest(InputStream inputStream, int i, List<String> list, List<String> list2) {
            this.partContent = inputStream;
            this.statusCode = i;
            this.headerNames = list;
            this.headerValues = list2;
        }

        public LowLevelHttpResponse execute() {
            return new FakeLowLevelHttpResponse(this.partContent, this.statusCode, this.headerNames, this.headerValues);
        }
    }

    private static class FakeLowLevelHttpResponse extends LowLevelHttpResponse {
        private List<String> headerNames = new ArrayList();
        private List<String> headerValues = new ArrayList();
        private InputStream partContent;
        private int statusCode;

        public String getContentEncoding() {
            return null;
        }

        public long getContentLength() {
            return 0;
        }

        public String getContentType() {
            return null;
        }

        public String getReasonPhrase() {
            return null;
        }

        public String getStatusLine() {
            return null;
        }

        FakeLowLevelHttpResponse(InputStream inputStream, int i, List<String> list, List<String> list2) {
            this.partContent = inputStream;
            this.statusCode = i;
            this.headerNames = list;
            this.headerValues = list2;
        }

        public InputStream getContent() {
            return this.partContent;
        }

        public int getStatusCode() {
            return this.statusCode;
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
    }

    private static class FakeResponseHttpTransport extends HttpTransport {
        private List<String> headerNames;
        private List<String> headerValues;
        private InputStream partContent;
        private int statusCode;

        FakeResponseHttpTransport(int i, InputStream inputStream, List<String> list, List<String> list2) {
            this.statusCode = i;
            this.partContent = inputStream;
            this.headerNames = list;
            this.headerValues = list2;
        }

        /* Access modifiers changed, original: protected */
        public LowLevelHttpRequest buildRequest(String str, String str2) {
            return new FakeLowLevelHttpRequest(this.partContent, this.statusCode, this.headerNames, this.headerValues);
        }
    }

    BatchUnparsedResponse(InputStream inputStream, String str, List<RequestInfo<?, ?>> list, boolean z) throws IOException {
        this.boundary = str;
        this.requestInfos = list;
        this.retryAllowed = z;
        this.inputStream = inputStream;
        checkForFinalBoundary(readLine());
    }

    /* Access modifiers changed, original: 0000 */
    public void parseNextResponse() throws IOException {
        int parseInt;
        ArrayList arrayList;
        ArrayList arrayList2;
        long j;
        String readLine;
        String readRawLine;
        InputStream trimCrlf;
        this.contentId++;
        while (true) {
            String readLine2 = readLine();
            if (readLine2 == null || readLine2.equals("")) {
                parseInt = Integer.parseInt(readLine().split(" ")[1]);
                arrayList = new ArrayList();
                arrayList2 = new ArrayList();
                j = -1;
            }
        }
        parseInt = Integer.parseInt(readLine().split(" ")[1]);
        arrayList = new ArrayList();
        arrayList2 = new ArrayList();
        j = -1;
        while (true) {
            readLine = readLine();
            if (readLine != null && !readLine.equals("")) {
                String[] split = readLine.split(": ", 2);
                Object obj = split[0];
                Object obj2 = split[1];
                arrayList.add(obj);
                arrayList2.add(obj2);
                if ("Content-Length".equalsIgnoreCase(obj.trim())) {
                    j = Long.parseLong(obj2);
                }
            }
        }
        if (j == -1) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                readRawLine = readRawLine();
                if (readRawLine == null || readRawLine.startsWith(this.boundary)) {
                    trimCrlf = trimCrlf(byteArrayOutputStream.toByteArray());
                    readRawLine = trimCrlf(readRawLine);
                } else {
                    byteArrayOutputStream.write(readRawLine.getBytes("ISO-8859-1"));
                }
            }
            trimCrlf = trimCrlf(byteArrayOutputStream.toByteArray());
            readRawLine = trimCrlf(readRawLine);
        } else {
            InputStream anonymousClass1 = new FilterInputStream(ByteStreams.limit(this.inputStream, j)) {
                public void close() {
                }
            };
            readRawLine = readLine;
            trimCrlf = anonymousClass1;
        }
        parseAndCallback((RequestInfo) this.requestInfos.get(this.contentId - 1), parseInt, getFakeResponse(parseInt, trimCrlf, arrayList, arrayList2));
        while (true) {
            if (trimCrlf.skip(j) <= 0) {
                if (trimCrlf.read() == -1) {
                    break;
                }
            }
        }
        if (j != -1) {
            readRawLine = readLine();
        }
        while (readRawLine != null && readRawLine.length() == 0) {
            readRawLine = readLine();
        }
        checkForFinalBoundary(readRawLine);
    }

    private <T, E> void parseAndCallback(RequestInfo<T, E> requestInfo, int i, HttpResponse httpResponse) throws IOException {
        BatchCallback batchCallback = requestInfo.callback;
        HttpHeaders headers = httpResponse.getHeaders();
        HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler = requestInfo.request.getUnsuccessfulResponseHandler();
        BackOffPolicy backOffPolicy = requestInfo.request.getBackOffPolicy();
        boolean z = false;
        this.backOffRequired = false;
        if (!HttpStatusCodes.isSuccess(i)) {
            HttpContent content = requestInfo.request.getContent();
            boolean z2 = this.retryAllowed && (content == null || content.retrySupported());
            boolean handleResponse = unsuccessfulResponseHandler != null ? unsuccessfulResponseHandler.handleResponse(requestInfo.request, httpResponse, z2) : false;
            if (!handleResponse) {
                if (requestInfo.request.handleRedirect(httpResponse.getStatusCode(), httpResponse.getHeaders())) {
                    z = true;
                } else if (z2 && backOffPolicy != null && backOffPolicy.isBackOffRequired(httpResponse.getStatusCode())) {
                    this.backOffRequired = true;
                }
            }
            if (z2 && (handleResponse || this.backOffRequired || z)) {
                this.unsuccessfulRequestInfos.add(requestInfo);
            } else if (batchCallback != null) {
                batchCallback.onFailure(getParsedDataClass(requestInfo.errorClass, httpResponse, requestInfo), headers);
            }
        } else if (batchCallback != null) {
            batchCallback.onSuccess(getParsedDataClass(requestInfo.dataClass, httpResponse, requestInfo), headers);
        }
    }

    private <A, T, E> A getParsedDataClass(Class<A> cls, HttpResponse httpResponse, RequestInfo<T, E> requestInfo) throws IOException {
        if (cls == Void.class) {
            return null;
        }
        return requestInfo.request.getParser().parseAndClose(httpResponse.getContent(), httpResponse.getContentCharset(), (Class) cls);
    }

    private HttpResponse getFakeResponse(int i, InputStream inputStream, List<String> list, List<String> list2) throws IOException {
        HttpRequest buildPostRequest = new FakeResponseHttpTransport(i, inputStream, list, list2).createRequestFactory().buildPostRequest(new GenericUrl(HttpTesting.SIMPLE_URL), null);
        buildPostRequest.setLoggingEnabled(false);
        buildPostRequest.setThrowExceptionOnExecuteError(false);
        return buildPostRequest.execute();
    }

    private String readRawLine() throws IOException {
        int read = this.inputStream.read();
        if (read == -1) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (read != -1) {
            stringBuilder.append((char) read);
            if (read == 10) {
                break;
            }
            read = this.inputStream.read();
        }
        return stringBuilder.toString();
    }

    private String readLine() throws IOException {
        return trimCrlf(readRawLine());
    }

    private static String trimCrlf(String str) {
        if (str.endsWith("\r\n")) {
            return str.substring(0, str.length() - 2);
        }
        return str.endsWith("\n") ? str.substring(0, str.length() - 1) : str;
    }

    private static InputStream trimCrlf(byte[] bArr) {
        int length = bArr.length;
        if (length > 0 && bArr[length - 1] == (byte) 10) {
            length--;
        }
        if (length > 0 && bArr[length - 1] == (byte) 13) {
            length--;
        }
        return new ByteArrayInputStream(bArr, 0, length);
    }

    private void checkForFinalBoundary(String str) throws IOException {
        if (str.equals(String.valueOf(this.boundary).concat("--"))) {
            this.hasNext = false;
            this.inputStream.close();
        }
    }
}
