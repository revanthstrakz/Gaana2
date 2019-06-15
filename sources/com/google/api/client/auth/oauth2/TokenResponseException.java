package com.google.api.client.auth.oauth2;

import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpResponseException.Builder;

public class TokenResponseException extends HttpResponseException {
    private static final long serialVersionUID = 4020689092957439244L;
    private final transient TokenErrorResponse details;

    TokenResponseException(Builder builder, TokenErrorResponse tokenErrorResponse) {
        super(builder);
        this.details = tokenErrorResponse;
    }

    public final TokenErrorResponse getDetails() {
        return this.details;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0063  */
    public static com.google.api.client.auth.oauth2.TokenResponseException from(com.google.api.client.json.JsonFactory r6, com.google.api.client.http.HttpResponse r7) {
        /*
        r0 = new com.google.api.client.http.HttpResponseException$Builder;
        r1 = r7.getStatusCode();
        r2 = r7.getStatusMessage();
        r3 = r7.getHeaders();
        r0.<init>(r1, r2, r3);
        com.google.api.client.util.Preconditions.checkNotNull(r6);
        r1 = r7.getContentType();
        r2 = 0;
        r3 = r7.isSuccessStatusCode();	 Catch:{ IOException -> 0x0054 }
        if (r3 != 0) goto L_0x004c;
    L_0x001f:
        if (r1 == 0) goto L_0x004c;
    L_0x0021:
        r3 = r7.getContent();	 Catch:{ IOException -> 0x0054 }
        if (r3 == 0) goto L_0x004c;
    L_0x0027:
        r3 = "application/json; charset=UTF-8";
        r1 = com.google.api.client.http.HttpMediaType.equalsIgnoreParameters(r3, r1);	 Catch:{ IOException -> 0x0054 }
        if (r1 == 0) goto L_0x004c;
    L_0x002f:
        r1 = new com.google.api.client.json.JsonObjectParser;	 Catch:{ IOException -> 0x0054 }
        r1.<init>(r6);	 Catch:{ IOException -> 0x0054 }
        r6 = r7.getContent();	 Catch:{ IOException -> 0x0054 }
        r3 = r7.getContentCharset();	 Catch:{ IOException -> 0x0054 }
        r4 = com.google.api.client.auth.oauth2.TokenErrorResponse.class;
        r6 = r1.parseAndClose(r6, r3, r4);	 Catch:{ IOException -> 0x0054 }
        r6 = (com.google.api.client.auth.oauth2.TokenErrorResponse) r6;	 Catch:{ IOException -> 0x0054 }
        r1 = r6.toPrettyString();	 Catch:{ IOException -> 0x004a }
        r2 = r1;
        goto L_0x0059;
    L_0x004a:
        r1 = move-exception;
        goto L_0x0056;
    L_0x004c:
        r6 = r7.parseAsString();	 Catch:{ IOException -> 0x0054 }
        r5 = r2;
        r2 = r6;
        r6 = r5;
        goto L_0x0059;
    L_0x0054:
        r1 = move-exception;
        r6 = r2;
    L_0x0056:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r1);
    L_0x0059:
        r7 = com.google.api.client.http.HttpResponseException.computeMessageBuffer(r7);
        r1 = com.google.api.client.util.Strings.isNullOrEmpty(r2);
        if (r1 != 0) goto L_0x006e;
    L_0x0063:
        r1 = com.google.api.client.util.StringUtils.LINE_SEPARATOR;
        r7.append(r1);
        r7.append(r2);
        r0.setContent(r2);
    L_0x006e:
        r7 = r7.toString();
        r0.setMessage(r7);
        r7 = new com.google.api.client.auth.oauth2.TokenResponseException;
        r7.<init>(r0, r6);
        return r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.auth.oauth2.TokenResponseException.from(com.google.api.client.json.JsonFactory, com.google.api.client.http.HttpResponse):com.google.api.client.auth.oauth2.TokenResponseException");
    }
}
