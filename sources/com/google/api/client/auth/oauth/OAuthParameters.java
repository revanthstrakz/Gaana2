package com.google.api.client.auth.oauth;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.util.Beta;
import com.google.api.client.util.escape.PercentEscaper;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.TreeMap;

@Beta
public final class OAuthParameters implements HttpExecuteInterceptor, HttpRequestInitializer {
    private static final PercentEscaper ESCAPER = new PercentEscaper("-_.~", false);
    private static final SecureRandom RANDOM = new SecureRandom();
    public String callback;
    public String consumerKey;
    public String nonce;
    public String realm;
    public String signature;
    public String signatureMethod;
    public OAuthSigner signer;
    public String timestamp;
    public String token;
    public String verifier;
    public String version;

    public void computeNonce() {
        this.nonce = Long.toHexString(Math.abs(RANDOM.nextLong()));
    }

    public void computeTimestamp() {
        this.timestamp = Long.toString(System.currentTimeMillis() / 1000);
    }

    public void computeSignature(String str, GenericUrl genericUrl) throws GeneralSecurityException {
        String str2;
        OAuthSigner oAuthSigner = this.signer;
        String signatureMethod = oAuthSigner.getSignatureMethod();
        this.signatureMethod = signatureMethod;
        TreeMap treeMap = new TreeMap();
        putParameterIfValueNotNull(treeMap, "oauth_callback", this.callback);
        putParameterIfValueNotNull(treeMap, "oauth_consumer_key", this.consumerKey);
        putParameterIfValueNotNull(treeMap, "oauth_nonce", this.nonce);
        putParameterIfValueNotNull(treeMap, "oauth_signature_method", signatureMethod);
        putParameterIfValueNotNull(treeMap, "oauth_timestamp", this.timestamp);
        putParameterIfValueNotNull(treeMap, "oauth_token", this.token);
        putParameterIfValueNotNull(treeMap, "oauth_verifier", this.verifier);
        putParameterIfValueNotNull(treeMap, "oauth_version", this.version);
        for (Entry entry : genericUrl.entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                str2 = (String) entry.getKey();
                if (value instanceof Collection) {
                    for (Object putParameter : (Collection) value) {
                        putParameter(treeMap, str2, putParameter);
                    }
                } else {
                    putParameter(treeMap, str2, value);
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Entry entry2 : treeMap.entrySet()) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append('&');
            }
            stringBuilder.append((String) entry2.getKey());
            String str3 = (String) entry2.getValue();
            if (str3 != null) {
                stringBuilder.append('=');
                stringBuilder.append(str3);
            }
        }
        signatureMethod = stringBuilder.toString();
        GenericUrl genericUrl2 = new GenericUrl();
        str2 = genericUrl.getScheme();
        genericUrl2.setScheme(str2);
        genericUrl2.setHost(genericUrl.getHost());
        genericUrl2.setPathParts(genericUrl.getPathParts());
        int port = genericUrl.getPort();
        if (("http".equals(str2) && port == 80) || ("https".equals(str2) && port == 443)) {
            port = -1;
        }
        genericUrl2.setPort(port);
        String build = genericUrl2.build();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(escape(str));
        stringBuilder2.append('&');
        stringBuilder2.append(escape(build));
        stringBuilder2.append('&');
        stringBuilder2.append(escape(signatureMethod));
        this.signature = oAuthSigner.computeSignature(stringBuilder2.toString());
    }

    public String getAuthorizationHeader() {
        StringBuilder stringBuilder = new StringBuilder("OAuth");
        appendParameter(stringBuilder, "realm", this.realm);
        appendParameter(stringBuilder, "oauth_callback", this.callback);
        appendParameter(stringBuilder, "oauth_consumer_key", this.consumerKey);
        appendParameter(stringBuilder, "oauth_nonce", this.nonce);
        appendParameter(stringBuilder, "oauth_signature", this.signature);
        appendParameter(stringBuilder, "oauth_signature_method", this.signatureMethod);
        appendParameter(stringBuilder, "oauth_timestamp", this.timestamp);
        appendParameter(stringBuilder, "oauth_token", this.token);
        appendParameter(stringBuilder, "oauth_verifier", this.verifier);
        appendParameter(stringBuilder, "oauth_version", this.version);
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    private void appendParameter(StringBuilder stringBuilder, String str, String str2) {
        if (str2 != null) {
            stringBuilder.append(' ');
            stringBuilder.append(escape(str));
            stringBuilder.append("=\"");
            stringBuilder.append(escape(str2));
            stringBuilder.append("\",");
        }
    }

    private void putParameterIfValueNotNull(TreeMap<String, String> treeMap, String str, String str2) {
        if (str2 != null) {
            putParameter(treeMap, str, str2);
        }
    }

    private void putParameter(TreeMap<String, String> treeMap, String str, Object obj) {
        treeMap.put(escape(str), obj == null ? null : escape(obj.toString()));
    }

    public static String escape(String str) {
        return ESCAPER.escape(str);
    }

    public void initialize(HttpRequest httpRequest) throws IOException {
        httpRequest.setInterceptor(this);
    }

    public void intercept(HttpRequest httpRequest) throws IOException {
        computeNonce();
        computeTimestamp();
        try {
            computeSignature(httpRequest.getRequestMethod(), httpRequest.getUrl());
            httpRequest.getHeaders().setAuthorization(getAuthorizationHeader());
        } catch (GeneralSecurityException e) {
            IOException iOException = new IOException();
            iOException.initCause(e);
            throw iOException;
        }
    }
}
