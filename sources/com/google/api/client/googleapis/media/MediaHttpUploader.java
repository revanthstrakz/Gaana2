package com.google.api.client.googleapis.media;

import com.facebook.share.internal.ShareConstants;
import com.google.api.client.googleapis.MethodOverride;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GZipEncoding;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.MultipartContent;
import com.google.api.client.util.Beta;
import com.google.api.client.util.ByteStreams;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import com.payu.custombrowser.util.CBConstant;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public final class MediaHttpUploader {
    public static final String CONTENT_LENGTH_HEADER = "X-Upload-Content-Length";
    public static final String CONTENT_TYPE_HEADER = "X-Upload-Content-Type";
    public static final int DEFAULT_CHUNK_SIZE = 10485760;
    private static final int KB = 1024;
    static final int MB = 1048576;
    public static final int MINIMUM_CHUNK_SIZE = 262144;
    private Byte cachedByte;
    private int chunkSize = DEFAULT_CHUNK_SIZE;
    private InputStream contentInputStream;
    private int currentChunkLength;
    private HttpRequest currentRequest;
    private byte[] currentRequestContentBuffer;
    private boolean directUploadEnabled;
    private boolean disableGZipContent;
    private HttpHeaders initiationHeaders = new HttpHeaders();
    private String initiationRequestMethod = HttpMethods.POST;
    private boolean isMediaContentLengthCalculated;
    private final AbstractInputStreamContent mediaContent;
    private long mediaContentLength;
    String mediaContentLengthStr = CBConstant.DEFAULT_PAYMENT_URLS;
    private HttpContent metadata;
    private MediaHttpUploaderProgressListener progressListener;
    private final HttpRequestFactory requestFactory;
    Sleeper sleeper = Sleeper.DEFAULT;
    private long totalBytesClientSent;
    private long totalBytesServerReceived;
    private final HttpTransport transport;
    private UploadState uploadState = UploadState.NOT_STARTED;

    public enum UploadState {
        NOT_STARTED,
        INITIATION_STARTED,
        INITIATION_COMPLETE,
        MEDIA_IN_PROGRESS,
        MEDIA_COMPLETE
    }

    public MediaHttpUploader(AbstractInputStreamContent abstractInputStreamContent, HttpTransport httpTransport, HttpRequestInitializer httpRequestInitializer) {
        this.mediaContent = (AbstractInputStreamContent) Preconditions.checkNotNull(abstractInputStreamContent);
        this.transport = (HttpTransport) Preconditions.checkNotNull(httpTransport);
        this.requestFactory = httpRequestInitializer == null ? httpTransport.createRequestFactory() : httpTransport.createRequestFactory(httpRequestInitializer);
    }

    public HttpResponse upload(GenericUrl genericUrl) throws IOException {
        Preconditions.checkArgument(this.uploadState == UploadState.NOT_STARTED);
        if (this.directUploadEnabled) {
            return directUpload(genericUrl);
        }
        return resumableUpload(genericUrl);
    }

    private HttpResponse directUpload(GenericUrl genericUrl) throws IOException {
        updateStateAndNotifyListener(UploadState.MEDIA_IN_PROGRESS);
        HttpContent httpContent = this.mediaContent;
        if (this.metadata != null) {
            httpContent = new MultipartContent().setContentParts(Arrays.asList(new HttpContent[]{this.metadata, this.mediaContent}));
            genericUrl.put("uploadType", (Object) "multipart");
        } else {
            genericUrl.put("uploadType", (Object) ShareConstants.WEB_DIALOG_PARAM_MEDIA);
        }
        HttpRequest buildRequest = this.requestFactory.buildRequest(this.initiationRequestMethod, genericUrl, httpContent);
        buildRequest.getHeaders().putAll(this.initiationHeaders);
        HttpResponse executeCurrentRequest = executeCurrentRequest(buildRequest);
        try {
            if (isMediaLengthKnown()) {
                this.totalBytesServerReceived = getMediaContentLength();
            }
            updateStateAndNotifyListener(UploadState.MEDIA_COMPLETE);
            return executeCurrentRequest;
        } catch (Throwable th) {
            executeCurrentRequest.disconnect();
        }
    }

    private HttpResponse resumableUpload(GenericUrl genericUrl) throws IOException {
        Throwable th;
        HttpResponse executeUploadInitiation = executeUploadInitiation(genericUrl);
        if (!executeUploadInitiation.isSuccessStatusCode()) {
            return executeUploadInitiation;
        }
        try {
            GenericUrl genericUrl2 = new GenericUrl(executeUploadInitiation.getHeaders().getLocation());
            executeUploadInitiation.disconnect();
            this.contentInputStream = this.mediaContent.getInputStream();
            if (!this.contentInputStream.markSupported() && isMediaLengthKnown()) {
                this.contentInputStream = new BufferedInputStream(this.contentInputStream);
            }
            while (true) {
                this.currentRequest = this.requestFactory.buildPutRequest(genericUrl2, null);
                setContentAndHeadersOnCurrentRequest();
                MediaUploadErrorHandler mediaUploadErrorHandler = new MediaUploadErrorHandler(this, this.currentRequest);
                if (isMediaLengthKnown()) {
                    executeUploadInitiation = executeCurrentRequestWithoutGZip(this.currentRequest);
                } else {
                    executeUploadInitiation = executeCurrentRequest(this.currentRequest);
                }
                try {
                    if (executeUploadInitiation.isSuccessStatusCode()) {
                        this.totalBytesServerReceived = getMediaContentLength();
                        if (this.mediaContent.getCloseInputStream()) {
                            this.contentInputStream.close();
                        }
                        updateStateAndNotifyListener(UploadState.MEDIA_COMPLETE);
                        return executeUploadInitiation;
                    } else if (executeUploadInitiation.getStatusCode() != 308) {
                        return executeUploadInitiation;
                    } else {
                        String location = executeUploadInitiation.getHeaders().getLocation();
                        if (location != null) {
                            genericUrl2 = new GenericUrl(location);
                        }
                        long nextByteIndex = getNextByteIndex(executeUploadInitiation.getHeaders().getRange());
                        long j = nextByteIndex - this.totalBytesServerReceived;
                        boolean z = false;
                        boolean z2 = j >= 0 && j <= ((long) this.currentChunkLength);
                        Preconditions.checkState(z2);
                        long j2 = ((long) this.currentChunkLength) - j;
                        if (isMediaLengthKnown()) {
                            if (j2 > 0) {
                                this.contentInputStream.reset();
                                if (j == this.contentInputStream.skip(j)) {
                                    z = true;
                                }
                                Preconditions.checkState(z);
                            }
                        } else if (j2 == 0) {
                            this.currentRequestContentBuffer = null;
                        }
                        this.totalBytesServerReceived = nextByteIndex;
                        updateStateAndNotifyListener(UploadState.MEDIA_IN_PROGRESS);
                        executeUploadInitiation.disconnect();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    executeUploadInitiation.disconnect();
                }
            }
        } catch (Throwable th22) {
            th = th22;
            executeUploadInitiation.disconnect();
        }
    }

    private boolean isMediaLengthKnown() throws IOException {
        return getMediaContentLength() >= 0;
    }

    private long getMediaContentLength() throws IOException {
        if (!this.isMediaContentLengthCalculated) {
            this.mediaContentLength = this.mediaContent.getLength();
            this.isMediaContentLengthCalculated = true;
        }
        return this.mediaContentLength;
    }

    private HttpResponse executeUploadInitiation(GenericUrl genericUrl) throws IOException {
        updateStateAndNotifyListener(UploadState.INITIATION_STARTED);
        genericUrl.put("uploadType", (Object) "resumable");
        HttpRequest buildRequest = this.requestFactory.buildRequest(this.initiationRequestMethod, genericUrl, this.metadata == null ? new EmptyContent() : this.metadata);
        this.initiationHeaders.set(CONTENT_TYPE_HEADER, this.mediaContent.getType());
        if (isMediaLengthKnown()) {
            this.initiationHeaders.set(CONTENT_LENGTH_HEADER, Long.valueOf(getMediaContentLength()));
        }
        buildRequest.getHeaders().putAll(this.initiationHeaders);
        HttpResponse executeCurrentRequest = executeCurrentRequest(buildRequest);
        try {
            updateStateAndNotifyListener(UploadState.INITIATION_COMPLETE);
            return executeCurrentRequest;
        } catch (Throwable th) {
            executeCurrentRequest.disconnect();
        }
    }

    private HttpResponse executeCurrentRequestWithoutGZip(HttpRequest httpRequest) throws IOException {
        new MethodOverride().intercept(httpRequest);
        httpRequest.setThrowExceptionOnExecuteError(false);
        return httpRequest.execute();
    }

    private HttpResponse executeCurrentRequest(HttpRequest httpRequest) throws IOException {
        if (!(this.disableGZipContent || (httpRequest.getContent() instanceof EmptyContent))) {
            httpRequest.setEncoding(new GZipEncoding());
        }
        return executeCurrentRequestWithoutGZip(httpRequest);
    }

    private void setContentAndHeadersOnCurrentRequest() throws IOException {
        int min;
        HttpContent closeInputStream;
        if (isMediaLengthKnown()) {
            min = (int) Math.min((long) this.chunkSize, getMediaContentLength() - this.totalBytesServerReceived);
        } else {
            min = this.chunkSize;
        }
        if (isMediaLengthKnown()) {
            this.contentInputStream.mark(min);
            long j = (long) min;
            closeInputStream = new InputStreamContent(this.mediaContent.getType(), ByteStreams.limit(this.contentInputStream, j)).setRetrySupported(true).setLength(j).setCloseInputStream(false);
            this.mediaContentLengthStr = String.valueOf(getMediaContentLength());
        } else {
            int i;
            int i2;
            if (this.currentRequestContentBuffer == null) {
                i = this.cachedByte == null ? min + 1 : min;
                this.currentRequestContentBuffer = new byte[(min + 1)];
                if (this.cachedByte != null) {
                    this.currentRequestContentBuffer[0] = this.cachedByte.byteValue();
                }
                i2 = 0;
            } else {
                i = (int) (this.totalBytesClientSent - this.totalBytesServerReceived);
                System.arraycopy(this.currentRequestContentBuffer, this.currentChunkLength - i, this.currentRequestContentBuffer, 0, i);
                if (this.cachedByte != null) {
                    this.currentRequestContentBuffer[i] = this.cachedByte.byteValue();
                }
                i2 = i;
                i = min - i;
            }
            int read = ByteStreams.read(this.contentInputStream, this.currentRequestContentBuffer, (min + 1) - i, i);
            if (read < i) {
                i2 += Math.max(0, read);
                if (this.cachedByte != null) {
                    i2++;
                    this.cachedByte = null;
                }
                if (this.mediaContentLengthStr.equals(CBConstant.DEFAULT_PAYMENT_URLS)) {
                    this.mediaContentLengthStr = String.valueOf(this.totalBytesServerReceived + ((long) i2));
                }
                min = i2;
            } else {
                this.cachedByte = Byte.valueOf(this.currentRequestContentBuffer[min]);
            }
            closeInputStream = new ByteArrayContent(this.mediaContent.getType(), this.currentRequestContentBuffer, 0, min);
            this.totalBytesClientSent = this.totalBytesServerReceived + ((long) min);
        }
        this.currentChunkLength = min;
        this.currentRequest.setContent(closeInputStream);
        if (min == 0) {
            HttpHeaders headers = this.currentRequest.getHeaders();
            String str = "bytes */";
            String valueOf = String.valueOf(this.mediaContentLengthStr);
            headers.setContentRange(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            return;
        }
        HttpHeaders headers2 = this.currentRequest.getHeaders();
        long j2 = this.totalBytesServerReceived;
        long j3 = (this.totalBytesServerReceived + ((long) min)) - 1;
        String valueOf2 = String.valueOf(String.valueOf(this.mediaContentLengthStr));
        StringBuilder stringBuilder = new StringBuilder(48 + valueOf2.length());
        stringBuilder.append("bytes ");
        stringBuilder.append(j2);
        stringBuilder.append("-");
        stringBuilder.append(j3);
        stringBuilder.append("/");
        stringBuilder.append(valueOf2);
        headers2.setContentRange(stringBuilder.toString());
    }

    /* Access modifiers changed, original: 0000 */
    @Beta
    public void serverErrorCallback() throws IOException {
        Preconditions.checkNotNull(this.currentRequest, "The current request should not be null");
        this.currentRequest.setContent(new EmptyContent());
        HttpHeaders headers = this.currentRequest.getHeaders();
        String str = "bytes */";
        String valueOf = String.valueOf(this.mediaContentLengthStr);
        headers.setContentRange(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    private long getNextByteIndex(String str) {
        return str == null ? 0 : Long.parseLong(str.substring(str.indexOf(45) + 1)) + 1;
    }

    public HttpContent getMetadata() {
        return this.metadata;
    }

    public MediaHttpUploader setMetadata(HttpContent httpContent) {
        this.metadata = httpContent;
        return this;
    }

    public HttpContent getMediaContent() {
        return this.mediaContent;
    }

    public HttpTransport getTransport() {
        return this.transport;
    }

    public MediaHttpUploader setDirectUploadEnabled(boolean z) {
        this.directUploadEnabled = z;
        return this;
    }

    public boolean isDirectUploadEnabled() {
        return this.directUploadEnabled;
    }

    public MediaHttpUploader setProgressListener(MediaHttpUploaderProgressListener mediaHttpUploaderProgressListener) {
        this.progressListener = mediaHttpUploaderProgressListener;
        return this;
    }

    public MediaHttpUploaderProgressListener getProgressListener() {
        return this.progressListener;
    }

    public MediaHttpUploader setChunkSize(int i) {
        boolean z = i > 0 && i % 262144 == 0;
        Preconditions.checkArgument(z, "chunkSize must be a positive multiple of 262144.");
        this.chunkSize = i;
        return this;
    }

    public int getChunkSize() {
        return this.chunkSize;
    }

    public boolean getDisableGZipContent() {
        return this.disableGZipContent;
    }

    public MediaHttpUploader setDisableGZipContent(boolean z) {
        this.disableGZipContent = z;
        return this;
    }

    public Sleeper getSleeper() {
        return this.sleeper;
    }

    public MediaHttpUploader setSleeper(Sleeper sleeper) {
        this.sleeper = sleeper;
        return this;
    }

    public String getInitiationRequestMethod() {
        return this.initiationRequestMethod;
    }

    public MediaHttpUploader setInitiationRequestMethod(String str) {
        boolean z = str.equals(HttpMethods.POST) || str.equals(HttpMethods.PUT) || str.equals(HttpMethods.PATCH);
        Preconditions.checkArgument(z);
        this.initiationRequestMethod = str;
        return this;
    }

    public MediaHttpUploader setInitiationHeaders(HttpHeaders httpHeaders) {
        this.initiationHeaders = httpHeaders;
        return this;
    }

    public HttpHeaders getInitiationHeaders() {
        return this.initiationHeaders;
    }

    public long getNumBytesUploaded() {
        return this.totalBytesServerReceived;
    }

    private void updateStateAndNotifyListener(UploadState uploadState) throws IOException {
        this.uploadState = uploadState;
        if (this.progressListener != null) {
            this.progressListener.progressChanged(this);
        }
    }

    public UploadState getUploadState() {
        return this.uploadState;
    }

    public double getProgress() throws IOException {
        Preconditions.checkArgument(isMediaLengthKnown(), "Cannot call getProgress() if the specified AbstractInputStreamContent has no content length. Use  getNumBytesUploaded() to denote progress instead.");
        return getMediaContentLength() == 0 ? 0.0d : ((double) this.totalBytesServerReceived) / ((double) getMediaContentLength());
    }
}
