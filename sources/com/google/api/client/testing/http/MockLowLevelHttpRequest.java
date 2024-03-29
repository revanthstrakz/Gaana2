package com.google.api.client.testing.http;

import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

@Beta
public class MockLowLevelHttpRequest extends LowLevelHttpRequest {
    private final Map<String, List<String>> headersMap = new HashMap();
    private MockLowLevelHttpResponse response = new MockLowLevelHttpResponse();
    private String url;

    public MockLowLevelHttpRequest(String str) {
        this.url = str;
    }

    public void addHeader(String str, String str2) throws IOException {
        str = str.toLowerCase();
        List list = (List) this.headersMap.get(str);
        if (list == null) {
            list = new ArrayList();
            this.headersMap.put(str, list);
        }
        list.add(str2);
    }

    public LowLevelHttpResponse execute() throws IOException {
        return this.response;
    }

    public String getUrl() {
        return this.url;
    }

    public Map<String, List<String>> getHeaders() {
        return Collections.unmodifiableMap(this.headersMap);
    }

    public String getFirstHeaderValue(String str) {
        List list = (List) this.headersMap.get(str.toLowerCase());
        if (list == null) {
            return null;
        }
        return (String) list.get(0);
    }

    public List<String> getHeaderValues(String str) {
        List list = (List) this.headersMap.get(str.toLowerCase());
        return list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
    }

    public MockLowLevelHttpRequest setUrl(String str) {
        this.url = str;
        return this;
    }

    public String getContentAsString() throws IOException {
        if (getStreamingContent() == null) {
            return "";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        getStreamingContent().writeTo(byteArrayOutputStream);
        String contentEncoding = getContentEncoding();
        if (contentEncoding != null && contentEncoding.contains("gzip")) {
            GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            byteArrayOutputStream = new ByteArrayOutputStream();
            IOUtils.copy(gZIPInputStream, byteArrayOutputStream);
        }
        contentEncoding = getContentType();
        HttpMediaType httpMediaType = contentEncoding != null ? new HttpMediaType(contentEncoding) : null;
        Charset charsetParameter = (httpMediaType == null || httpMediaType.getCharsetParameter() == null) ? Charsets.ISO_8859_1 : httpMediaType.getCharsetParameter();
        return byteArrayOutputStream.toString(charsetParameter.name());
    }

    public MockLowLevelHttpResponse getResponse() {
        return this.response;
    }

    public MockLowLevelHttpRequest setResponse(MockLowLevelHttpResponse mockLowLevelHttpResponse) {
        this.response = mockLowLevelHttpResponse;
        return this;
    }
}
