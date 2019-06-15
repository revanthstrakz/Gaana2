package com.google.api.client.googleapis.batch;

import com.google.api.client.http.BackOffPolicy;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.MultipartContent;
import com.google.api.client.http.MultipartContent.Part;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class BatchRequest {
    private GenericUrl batchUrl = new GenericUrl("https://www.googleapis.com/batch");
    private final HttpRequestFactory requestFactory;
    List<RequestInfo<?, ?>> requestInfos = new ArrayList();
    private Sleeper sleeper = Sleeper.DEFAULT;

    static class RequestInfo<T, E> {
        final BatchCallback<T, E> callback;
        final Class<T> dataClass;
        final Class<E> errorClass;
        final HttpRequest request;

        RequestInfo(BatchCallback<T, E> batchCallback, Class<T> cls, Class<E> cls2, HttpRequest httpRequest) {
            this.callback = batchCallback;
            this.dataClass = cls;
            this.errorClass = cls2;
            this.request = httpRequest;
        }
    }

    class BatchInterceptor implements HttpExecuteInterceptor {
        private HttpExecuteInterceptor originalInterceptor;

        BatchInterceptor(HttpExecuteInterceptor httpExecuteInterceptor) {
            this.originalInterceptor = httpExecuteInterceptor;
        }

        public void intercept(HttpRequest httpRequest) throws IOException {
            if (this.originalInterceptor != null) {
                this.originalInterceptor.intercept(httpRequest);
            }
            for (RequestInfo requestInfo : BatchRequest.this.requestInfos) {
                HttpExecuteInterceptor interceptor = requestInfo.request.getInterceptor();
                if (interceptor != null) {
                    interceptor.intercept(requestInfo.request);
                }
            }
        }
    }

    public BatchRequest(HttpTransport httpTransport, HttpRequestInitializer httpRequestInitializer) {
        this.requestFactory = httpRequestInitializer == null ? httpTransport.createRequestFactory() : httpTransport.createRequestFactory(httpRequestInitializer);
    }

    public BatchRequest setBatchUrl(GenericUrl genericUrl) {
        this.batchUrl = genericUrl;
        return this;
    }

    public GenericUrl getBatchUrl() {
        return this.batchUrl;
    }

    public Sleeper getSleeper() {
        return this.sleeper;
    }

    public BatchRequest setSleeper(Sleeper sleeper) {
        this.sleeper = (Sleeper) Preconditions.checkNotNull(sleeper);
        return this;
    }

    public <T, E> BatchRequest queue(HttpRequest httpRequest, Class<T> cls, Class<E> cls2, BatchCallback<T, E> batchCallback) throws IOException {
        Preconditions.checkNotNull(httpRequest);
        Preconditions.checkNotNull(batchCallback);
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(cls2);
        this.requestInfos.add(new RequestInfo(batchCallback, cls, cls2, httpRequest));
        return this;
    }

    public int size() {
        return this.requestInfos.size();
    }

    public void execute() throws IOException {
        Preconditions.checkState(this.requestInfos.isEmpty() ^ 1);
        HttpRequest buildPostRequest = this.requestFactory.buildPostRequest(this.batchUrl, null);
        buildPostRequest.setInterceptor(new BatchInterceptor(buildPostRequest.getInterceptor()));
        int numberOfRetries = buildPostRequest.getNumberOfRetries();
        BackOffPolicy backOffPolicy = buildPostRequest.getBackOffPolicy();
        if (backOffPolicy != null) {
            backOffPolicy.reset();
        }
        loop3:
        while (true) {
            BatchUnparsedResponse hasNext;
            boolean z = numberOfRetries > 0;
            MultipartContent multipartContent = new MultipartContent();
            multipartContent.getMediaType().setSubType("mixed");
            Iterator it = this.requestInfos.iterator();
            int i = 1;
            while (true) {
                hasNext = it.hasNext();
                if (hasNext == null) {
                    break;
                }
                int i2 = i + 1;
                multipartContent.addPart(new Part(new HttpHeaders().setAcceptEncoding(null).set("Content-ID", Integer.valueOf(i)), new HttpRequestContent(((RequestInfo) it.next()).request)));
                i = i2;
            }
            buildPostRequest.setContent(multipartContent);
            HttpResponse execute = buildPostRequest.execute();
            try {
                String str = "--";
                String valueOf = String.valueOf(execute.getMediaType().getParameter("boundary"));
                hasNext = new BatchUnparsedResponse(execute.getContent(), valueOf.length() != 0 ? str.concat(valueOf) : new String(str), this.requestInfos, z);
                while (hasNext.hasNext) {
                    hasNext.parseNextResponse();
                }
                List list = hasNext.unsuccessfulRequestInfos;
                if (!list.isEmpty()) {
                    this.requestInfos = list;
                    if (hasNext.backOffRequired && backOffPolicy != null) {
                        long nextBackOffMillis = backOffPolicy.getNextBackOffMillis();
                        if (nextBackOffMillis != -1) {
                            try {
                                this.sleeper.sleep(nextBackOffMillis);
                            } catch (InterruptedException unused) {
                            }
                        }
                    }
                    numberOfRetries--;
                    if (!z) {
                        break loop3;
                    }
                }
                break loop3;
            } finally {
                execute.disconnect();
            }
        }
        this.requestInfos.clear();
    }
}
