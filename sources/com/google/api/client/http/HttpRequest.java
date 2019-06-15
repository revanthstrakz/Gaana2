package com.google.api.client.http;

import com.google.api.client.util.Beta;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import com.google.firebase.appindexing.Indexable;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public final class HttpRequest {
    public static final int DEFAULT_NUMBER_OF_RETRIES = 10;
    public static final String USER_AGENT_SUFFIX = "Google-HTTP-Java-Client/1.23.0 (gzip)";
    public static final String VERSION = "1.23.0";
    @Beta
    @Deprecated
    private BackOffPolicy backOffPolicy;
    private int connectTimeout = Indexable.MAX_STRING_LENGTH;
    private HttpContent content;
    private int contentLoggingLimit = 16384;
    private boolean curlLoggingEnabled = true;
    private HttpEncoding encoding;
    private HttpExecuteInterceptor executeInterceptor;
    private boolean followRedirects = true;
    private HttpHeaders headers = new HttpHeaders();
    @Beta
    private HttpIOExceptionHandler ioExceptionHandler;
    private boolean loggingEnabled = true;
    private int numRetries = 10;
    private ObjectParser objectParser;
    private int readTimeout = Indexable.MAX_STRING_LENGTH;
    private String requestMethod;
    private HttpHeaders responseHeaders = new HttpHeaders();
    private HttpResponseInterceptor responseInterceptor;
    @Beta
    @Deprecated
    private boolean retryOnExecuteIOException = false;
    private Sleeper sleeper = Sleeper.DEFAULT;
    private boolean suppressUserAgentSuffix;
    private boolean throwExceptionOnExecuteError = true;
    private final HttpTransport transport;
    private HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler;
    private GenericUrl url;

    HttpRequest(HttpTransport httpTransport, String str) {
        this.transport = httpTransport;
        setRequestMethod(str);
    }

    public HttpTransport getTransport() {
        return this.transport;
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    public HttpRequest setRequestMethod(String str) {
        boolean z = str == null || HttpMediaType.matchesToken(str);
        Preconditions.checkArgument(z);
        this.requestMethod = str;
        return this;
    }

    public GenericUrl getUrl() {
        return this.url;
    }

    public HttpRequest setUrl(GenericUrl genericUrl) {
        this.url = (GenericUrl) Preconditions.checkNotNull(genericUrl);
        return this;
    }

    public HttpContent getContent() {
        return this.content;
    }

    public HttpRequest setContent(HttpContent httpContent) {
        this.content = httpContent;
        return this;
    }

    public HttpEncoding getEncoding() {
        return this.encoding;
    }

    public HttpRequest setEncoding(HttpEncoding httpEncoding) {
        this.encoding = httpEncoding;
        return this;
    }

    @Beta
    @Deprecated
    public BackOffPolicy getBackOffPolicy() {
        return this.backOffPolicy;
    }

    @Beta
    @Deprecated
    public HttpRequest setBackOffPolicy(BackOffPolicy backOffPolicy) {
        this.backOffPolicy = backOffPolicy;
        return this;
    }

    public int getContentLoggingLimit() {
        return this.contentLoggingLimit;
    }

    public HttpRequest setContentLoggingLimit(int i) {
        Preconditions.checkArgument(i >= 0, "The content logging limit must be non-negative.");
        this.contentLoggingLimit = i;
        return this;
    }

    public boolean isLoggingEnabled() {
        return this.loggingEnabled;
    }

    public HttpRequest setLoggingEnabled(boolean z) {
        this.loggingEnabled = z;
        return this;
    }

    public boolean isCurlLoggingEnabled() {
        return this.curlLoggingEnabled;
    }

    public HttpRequest setCurlLoggingEnabled(boolean z) {
        this.curlLoggingEnabled = z;
        return this;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public HttpRequest setConnectTimeout(int i) {
        Preconditions.checkArgument(i >= 0);
        this.connectTimeout = i;
        return this;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public HttpRequest setReadTimeout(int i) {
        Preconditions.checkArgument(i >= 0);
        this.readTimeout = i;
        return this;
    }

    public HttpHeaders getHeaders() {
        return this.headers;
    }

    public HttpRequest setHeaders(HttpHeaders httpHeaders) {
        this.headers = (HttpHeaders) Preconditions.checkNotNull(httpHeaders);
        return this;
    }

    public HttpHeaders getResponseHeaders() {
        return this.responseHeaders;
    }

    public HttpRequest setResponseHeaders(HttpHeaders httpHeaders) {
        this.responseHeaders = (HttpHeaders) Preconditions.checkNotNull(httpHeaders);
        return this;
    }

    public HttpExecuteInterceptor getInterceptor() {
        return this.executeInterceptor;
    }

    public HttpRequest setInterceptor(HttpExecuteInterceptor httpExecuteInterceptor) {
        this.executeInterceptor = httpExecuteInterceptor;
        return this;
    }

    public HttpUnsuccessfulResponseHandler getUnsuccessfulResponseHandler() {
        return this.unsuccessfulResponseHandler;
    }

    public HttpRequest setUnsuccessfulResponseHandler(HttpUnsuccessfulResponseHandler httpUnsuccessfulResponseHandler) {
        this.unsuccessfulResponseHandler = httpUnsuccessfulResponseHandler;
        return this;
    }

    @Beta
    public HttpIOExceptionHandler getIOExceptionHandler() {
        return this.ioExceptionHandler;
    }

    @Beta
    public HttpRequest setIOExceptionHandler(HttpIOExceptionHandler httpIOExceptionHandler) {
        this.ioExceptionHandler = httpIOExceptionHandler;
        return this;
    }

    public HttpResponseInterceptor getResponseInterceptor() {
        return this.responseInterceptor;
    }

    public HttpRequest setResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor) {
        this.responseInterceptor = httpResponseInterceptor;
        return this;
    }

    public int getNumberOfRetries() {
        return this.numRetries;
    }

    public HttpRequest setNumberOfRetries(int i) {
        Preconditions.checkArgument(i >= 0);
        this.numRetries = i;
        return this;
    }

    public HttpRequest setParser(ObjectParser objectParser) {
        this.objectParser = objectParser;
        return this;
    }

    public final ObjectParser getParser() {
        return this.objectParser;
    }

    public boolean getFollowRedirects() {
        return this.followRedirects;
    }

    public HttpRequest setFollowRedirects(boolean z) {
        this.followRedirects = z;
        return this;
    }

    public boolean getThrowExceptionOnExecuteError() {
        return this.throwExceptionOnExecuteError;
    }

    public HttpRequest setThrowExceptionOnExecuteError(boolean z) {
        this.throwExceptionOnExecuteError = z;
        return this;
    }

    @Beta
    @Deprecated
    public boolean getRetryOnExecuteIOException() {
        return this.retryOnExecuteIOException;
    }

    @Beta
    @Deprecated
    public HttpRequest setRetryOnExecuteIOException(boolean z) {
        this.retryOnExecuteIOException = z;
        return this;
    }

    public boolean getSuppressUserAgentSuffix() {
        return this.suppressUserAgentSuffix;
    }

    public HttpRequest setSuppressUserAgentSuffix(boolean z) {
        this.suppressUserAgentSuffix = z;
        return this;
    }

    /* JADX WARNING: Removed duplicated region for block: B:64:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01dd  */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x0309 A:{LOOP_END, LOOP:0: B:8:0x0024->B:166:0x0309} */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x02e6 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x020f  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0213  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0285 A:{SYNTHETIC, Splitter:B:113:0x0285} */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x02e0  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x02de  */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x02e6 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x0309 A:{LOOP_END, LOOP:0: B:8:0x0024->B:166:0x0309} */
    public com.google.api.client.http.HttpResponse execute() throws java.io.IOException {
        /*
        r19 = this;
        r1 = r19;
        r2 = r1.numRetries;
        r4 = 1;
        if (r2 < 0) goto L_0x0009;
    L_0x0007:
        r2 = r4;
        goto L_0x000a;
    L_0x0009:
        r2 = 0;
    L_0x000a:
        com.google.api.client.util.Preconditions.checkArgument(r2);
        r2 = r1.numRetries;
        r5 = r1.backOffPolicy;
        if (r5 == 0) goto L_0x0018;
    L_0x0013:
        r5 = r1.backOffPolicy;
        r5.reset();
    L_0x0018:
        r5 = r1.requestMethod;
        com.google.api.client.util.Preconditions.checkNotNull(r5);
        r5 = r1.url;
        com.google.api.client.util.Preconditions.checkNotNull(r5);
        r6 = r2;
        r2 = 0;
    L_0x0024:
        if (r2 == 0) goto L_0x0029;
    L_0x0026:
        r2.ignore();
    L_0x0029:
        r2 = r1.executeInterceptor;
        if (r2 == 0) goto L_0x0032;
    L_0x002d:
        r2 = r1.executeInterceptor;
        r2.intercept(r1);
    L_0x0032:
        r2 = r1.url;
        r2 = r2.build();
        r7 = r1.transport;
        r8 = r1.requestMethod;
        r7 = r7.buildRequest(r8, r2);
        r8 = com.google.api.client.http.HttpTransport.LOGGER;
        r9 = r1.loggingEnabled;
        if (r9 == 0) goto L_0x0050;
    L_0x0046:
        r9 = java.util.logging.Level.CONFIG;
        r9 = r8.isLoggable(r9);
        if (r9 == 0) goto L_0x0050;
    L_0x004e:
        r9 = r4;
        goto L_0x0051;
    L_0x0050:
        r9 = 0;
    L_0x0051:
        if (r9 == 0) goto L_0x0094;
    L_0x0053:
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r11 = "-------------- REQUEST  --------------";
        r10.append(r11);
        r11 = com.google.api.client.util.StringUtils.LINE_SEPARATOR;
        r10.append(r11);
        r11 = r1.requestMethod;
        r10.append(r11);
        r11 = 32;
        r10.append(r11);
        r10.append(r2);
        r11 = com.google.api.client.util.StringUtils.LINE_SEPARATOR;
        r10.append(r11);
        r11 = r1.curlLoggingEnabled;
        if (r11 == 0) goto L_0x0095;
    L_0x0078:
        r11 = new java.lang.StringBuilder;
        r12 = "curl -v --compressed";
        r11.<init>(r12);
        r12 = r1.requestMethod;
        r13 = "GET";
        r12 = r12.equals(r13);
        if (r12 != 0) goto L_0x0096;
    L_0x0089:
        r12 = " -X ";
        r11.append(r12);
        r12 = r1.requestMethod;
        r11.append(r12);
        goto L_0x0096;
    L_0x0094:
        r10 = 0;
    L_0x0095:
        r11 = 0;
    L_0x0096:
        r12 = r1.headers;
        r12 = r12.getUserAgent();
        r13 = r1.suppressUserAgentSuffix;
        if (r13 != 0) goto L_0x00e1;
    L_0x00a0:
        if (r12 != 0) goto L_0x00aa;
    L_0x00a2:
        r13 = r1.headers;
        r14 = "Google-HTTP-Java-Client/1.23.0 (gzip)";
        r13.setUserAgent(r14);
        goto L_0x00e1;
    L_0x00aa:
        r13 = r1.headers;
        r14 = java.lang.String.valueOf(r12);
        r14 = java.lang.String.valueOf(r14);
        r15 = "Google-HTTP-Java-Client/1.23.0 (gzip)";
        r15 = java.lang.String.valueOf(r15);
        r15 = java.lang.String.valueOf(r15);
        r3 = new java.lang.StringBuilder;
        r16 = r14.length();
        r16 = r4 + r16;
        r17 = r15.length();
        r4 = r16 + r17;
        r3.<init>(r4);
        r3.append(r14);
        r4 = " ";
        r3.append(r4);
        r3.append(r15);
        r3 = r3.toString();
        r13.setUserAgent(r3);
    L_0x00e1:
        r3 = r1.headers;
        com.google.api.client.http.HttpHeaders.serializeHeaders(r3, r10, r11, r8, r7);
        r3 = r1.suppressUserAgentSuffix;
        if (r3 != 0) goto L_0x00ef;
    L_0x00ea:
        r3 = r1.headers;
        r3.setUserAgent(r12);
    L_0x00ef:
        r3 = r1.content;
        if (r3 == 0) goto L_0x00fe;
    L_0x00f3:
        r4 = r1.content;
        r4 = r4.retrySupported();
        if (r4 == 0) goto L_0x00fc;
    L_0x00fb:
        goto L_0x00fe;
    L_0x00fc:
        r4 = 0;
        goto L_0x00ff;
    L_0x00fe:
        r4 = 1;
    L_0x00ff:
        if (r3 == 0) goto L_0x020f;
    L_0x0101:
        r14 = r1.content;
        r14 = r14.getType();
        if (r9 == 0) goto L_0x0115;
    L_0x0109:
        r15 = new com.google.api.client.util.LoggingStreamingContent;
        r5 = com.google.api.client.http.HttpTransport.LOGGER;
        r12 = java.util.logging.Level.CONFIG;
        r13 = r1.contentLoggingLimit;
        r15.<init>(r3, r5, r12, r13);
        goto L_0x0116;
    L_0x0115:
        r15 = r3;
    L_0x0116:
        r3 = r1.encoding;
        if (r3 != 0) goto L_0x0123;
    L_0x011a:
        r3 = r1.content;
        r12 = r3.getLength();
        r3 = r15;
        r5 = 0;
        goto L_0x0139;
    L_0x0123:
        r3 = r1.encoding;
        r5 = r3.getName();
        r3 = new com.google.api.client.http.HttpEncodingStreamingContent;
        r12 = r1.encoding;
        r3.<init>(r15, r12);
        if (r4 == 0) goto L_0x0137;
    L_0x0132:
        r12 = com.google.api.client.util.IOUtils.computeLength(r3);
        goto L_0x0139;
    L_0x0137:
        r12 = -1;
    L_0x0139:
        if (r9 == 0) goto L_0x01f9;
    L_0x013b:
        if (r14 == 0) goto L_0x0189;
    L_0x013d:
        r15 = "Content-Type: ";
        r1 = java.lang.String.valueOf(r14);
        r16 = r1.length();
        if (r16 == 0) goto L_0x014e;
    L_0x0149:
        r1 = r15.concat(r1);
        goto L_0x0153;
    L_0x014e:
        r1 = new java.lang.String;
        r1.<init>(r15);
    L_0x0153:
        r10.append(r1);
        r15 = com.google.api.client.util.StringUtils.LINE_SEPARATOR;
        r10.append(r15);
        if (r11 == 0) goto L_0x0189;
    L_0x015d:
        r1 = java.lang.String.valueOf(r1);
        r1 = java.lang.String.valueOf(r1);
        r15 = new java.lang.StringBuilder;
        r16 = r1.length();
        r18 = r6;
        r17 = 6;
        r6 = r17 + r16;
        r15.<init>(r6);
        r6 = " -H '";
        r15.append(r6);
        r15.append(r1);
        r1 = "'";
        r15.append(r1);
        r1 = r15.toString();
        r11.append(r1);
        goto L_0x018b;
    L_0x0189:
        r18 = r6;
    L_0x018b:
        if (r5 == 0) goto L_0x01d7;
    L_0x018d:
        r1 = "Content-Encoding: ";
        r6 = java.lang.String.valueOf(r5);
        r15 = r6.length();
        if (r15 == 0) goto L_0x019e;
    L_0x0199:
        r1 = r1.concat(r6);
        goto L_0x01a4;
    L_0x019e:
        r6 = new java.lang.String;
        r6.<init>(r1);
        r1 = r6;
    L_0x01a4:
        r10.append(r1);
        r6 = com.google.api.client.util.StringUtils.LINE_SEPARATOR;
        r10.append(r6);
        if (r11 == 0) goto L_0x01d7;
    L_0x01ae:
        r1 = java.lang.String.valueOf(r1);
        r1 = java.lang.String.valueOf(r1);
        r6 = new java.lang.StringBuilder;
        r15 = r1.length();
        r16 = 6;
        r15 = r16 + r15;
        r6.<init>(r15);
        r15 = " -H '";
        r6.append(r15);
        r6.append(r1);
        r1 = "'";
        r6.append(r1);
        r1 = r6.toString();
        r11.append(r1);
    L_0x01d7:
        r16 = 0;
        r1 = (r12 > r16 ? 1 : (r12 == r16 ? 0 : -1));
        if (r1 < 0) goto L_0x01fb;
    L_0x01dd:
        r1 = new java.lang.StringBuilder;
        r6 = 36;
        r1.<init>(r6);
        r6 = "Content-Length: ";
        r1.append(r6);
        r1.append(r12);
        r1 = r1.toString();
        r10.append(r1);
        r1 = com.google.api.client.util.StringUtils.LINE_SEPARATOR;
        r10.append(r1);
        goto L_0x01fb;
    L_0x01f9:
        r18 = r6;
    L_0x01fb:
        if (r11 == 0) goto L_0x0202;
    L_0x01fd:
        r1 = " -d '@-'";
        r11.append(r1);
    L_0x0202:
        r7.setContentType(r14);
        r7.setContentEncoding(r5);
        r7.setContentLength(r12);
        r7.setStreamingContent(r3);
        goto L_0x0211;
    L_0x020f:
        r18 = r6;
    L_0x0211:
        if (r9 == 0) goto L_0x023f;
    L_0x0213:
        r1 = r10.toString();
        r8.config(r1);
        if (r11 == 0) goto L_0x023f;
    L_0x021c:
        r1 = " -- '";
        r11.append(r1);
        r1 = "'";
        r5 = "'\"'\"'";
        r1 = r2.replaceAll(r1, r5);
        r11.append(r1);
        r1 = "'";
        r11.append(r1);
        if (r3 == 0) goto L_0x0238;
    L_0x0233:
        r1 = " << $$$";
        r11.append(r1);
    L_0x0238:
        r1 = r11.toString();
        r8.config(r1);
    L_0x023f:
        if (r4 == 0) goto L_0x0247;
    L_0x0241:
        if (r18 <= 0) goto L_0x0247;
    L_0x0243:
        r1 = r19;
        r2 = 1;
        goto L_0x024a;
    L_0x0247:
        r1 = r19;
        r2 = 0;
    L_0x024a:
        r3 = r1.connectTimeout;
        r4 = r1.readTimeout;
        r7.setTimeout(r3, r4);
        r3 = r7.execute();	 Catch:{ IOException -> 0x0268 }
        r4 = new com.google.api.client.http.HttpResponse;	 Catch:{ all -> 0x025c }
        r4.<init>(r1, r3);	 Catch:{ all -> 0x025c }
        r5 = 0;
        goto L_0x0283;
    L_0x025c:
        r0 = move-exception;
        r4 = r0;
        r3 = r3.getContent();	 Catch:{ IOException -> 0x0268 }
        if (r3 == 0) goto L_0x0267;
    L_0x0264:
        r3.close();	 Catch:{ IOException -> 0x0268 }
    L_0x0267:
        throw r4;	 Catch:{ IOException -> 0x0268 }
    L_0x0268:
        r0 = move-exception;
        r5 = r0;
        r3 = r1.retryOnExecuteIOException;
        if (r3 != 0) goto L_0x027b;
    L_0x026e:
        r3 = r1.ioExceptionHandler;
        if (r3 == 0) goto L_0x027a;
    L_0x0272:
        r3 = r1.ioExceptionHandler;
        r3 = r3.handleIOException(r1, r2);
        if (r3 != 0) goto L_0x027b;
    L_0x027a:
        throw r5;
    L_0x027b:
        r3 = java.util.logging.Level.WARNING;
        r4 = "exception thrown while executing request";
        r8.log(r3, r4, r5);
        r4 = 0;
    L_0x0283:
        if (r4 == 0) goto L_0x02dc;
    L_0x0285:
        r3 = r4.isSuccessStatusCode();	 Catch:{ all -> 0x02d4 }
        if (r3 != 0) goto L_0x02dc;
    L_0x028b:
        r3 = r1.unsuccessfulResponseHandler;	 Catch:{ all -> 0x02d4 }
        if (r3 == 0) goto L_0x0296;
    L_0x028f:
        r3 = r1.unsuccessfulResponseHandler;	 Catch:{ all -> 0x02d4 }
        r3 = r3.handleResponse(r1, r4, r2);	 Catch:{ all -> 0x02d4 }
        goto L_0x0297;
    L_0x0296:
        r3 = 0;
    L_0x0297:
        if (r3 != 0) goto L_0x02cd;
    L_0x0299:
        r6 = r4.getStatusCode();	 Catch:{ all -> 0x02d4 }
        r7 = r4.getHeaders();	 Catch:{ all -> 0x02d4 }
        r6 = r1.handleRedirect(r6, r7);	 Catch:{ all -> 0x02d4 }
        if (r6 == 0) goto L_0x02a9;
    L_0x02a7:
        r3 = 1;
        goto L_0x02cd;
    L_0x02a9:
        if (r2 == 0) goto L_0x02cd;
    L_0x02ab:
        r6 = r1.backOffPolicy;	 Catch:{ all -> 0x02d4 }
        if (r6 == 0) goto L_0x02cd;
    L_0x02af:
        r6 = r1.backOffPolicy;	 Catch:{ all -> 0x02d4 }
        r7 = r4.getStatusCode();	 Catch:{ all -> 0x02d4 }
        r6 = r6.isBackOffRequired(r7);	 Catch:{ all -> 0x02d4 }
        if (r6 == 0) goto L_0x02cd;
    L_0x02bb:
        r6 = r1.backOffPolicy;	 Catch:{ all -> 0x02d4 }
        r6 = r6.getNextBackOffMillis();	 Catch:{ all -> 0x02d4 }
        r8 = -1;
        r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r10 == 0) goto L_0x02cd;
    L_0x02c7:
        r3 = r1.sleeper;	 Catch:{ InterruptedException -> 0x02a7 }
        r3.sleep(r6);	 Catch:{ InterruptedException -> 0x02a7 }
        goto L_0x02a7;
    L_0x02cd:
        r2 = r2 & r3;
        if (r2 == 0) goto L_0x02e2;
    L_0x02d0:
        r4.ignore();	 Catch:{ all -> 0x02d4 }
        goto L_0x02e2;
    L_0x02d4:
        r0 = move-exception;
        r2 = r0;
        if (r4 == 0) goto L_0x02db;
    L_0x02d8:
        r4.disconnect();
    L_0x02db:
        throw r2;
    L_0x02dc:
        if (r4 != 0) goto L_0x02e0;
    L_0x02de:
        r3 = 1;
        goto L_0x02e1;
    L_0x02e0:
        r3 = 0;
    L_0x02e1:
        r2 = r2 & r3;
    L_0x02e2:
        r6 = r18 + -1;
        if (r2 != 0) goto L_0x0309;
    L_0x02e6:
        if (r4 != 0) goto L_0x02e9;
    L_0x02e8:
        throw r5;
    L_0x02e9:
        r2 = r1.responseInterceptor;
        if (r2 == 0) goto L_0x02f2;
    L_0x02ed:
        r2 = r1.responseInterceptor;
        r2.interceptResponse(r4);
    L_0x02f2:
        r2 = r1.throwExceptionOnExecuteError;
        if (r2 == 0) goto L_0x0308;
    L_0x02f6:
        r2 = r4.isSuccessStatusCode();
        if (r2 != 0) goto L_0x0308;
    L_0x02fc:
        r2 = new com.google.api.client.http.HttpResponseException;	 Catch:{ all -> 0x0302 }
        r2.<init>(r4);	 Catch:{ all -> 0x0302 }
        throw r2;	 Catch:{ all -> 0x0302 }
    L_0x0302:
        r0 = move-exception;
        r2 = r0;
        r4.disconnect();
        throw r2;
    L_0x0308:
        return r4;
    L_0x0309:
        r2 = r4;
        r4 = 1;
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.http.HttpRequest.execute():com.google.api.client.http.HttpResponse");
    }

    @Beta
    public Future<HttpResponse> executeAsync(Executor executor) {
        FutureTask futureTask = new FutureTask(new Callable<HttpResponse>() {
            public HttpResponse call() throws Exception {
                return HttpRequest.this.execute();
            }
        });
        executor.execute(futureTask);
        return futureTask;
    }

    @Beta
    public Future<HttpResponse> executeAsync() {
        return executeAsync(Executors.newSingleThreadExecutor());
    }

    public boolean handleRedirect(int i, HttpHeaders httpHeaders) {
        String location = httpHeaders.getLocation();
        if (!getFollowRedirects() || !HttpStatusCodes.isRedirect(i) || location == null) {
            return false;
        }
        setUrl(new GenericUrl(this.url.toURL(location)));
        if (i == HttpStatusCodes.STATUS_CODE_SEE_OTHER) {
            setRequestMethod(HttpMethods.GET);
            setContent(null);
        }
        String str = (String) null;
        this.headers.setAuthorization(str);
        this.headers.setIfMatch(str);
        this.headers.setIfNoneMatch(str);
        this.headers.setIfModifiedSince(str);
        this.headers.setIfUnmodifiedSince(str);
        this.headers.setIfRange(str);
        return true;
    }

    public Sleeper getSleeper() {
        return this.sleeper;
    }

    public HttpRequest setSleeper(Sleeper sleeper) {
        this.sleeper = (Sleeper) Preconditions.checkNotNull(sleeper);
        return this;
    }
}
