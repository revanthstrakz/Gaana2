package com.google.api.client.googleapis.json;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpResponseException.Builder;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Preconditions;
import java.io.IOException;

public class GoogleJsonResponseException extends HttpResponseException {
    private static final long serialVersionUID = 409811126989994864L;
    private final transient GoogleJsonError details;

    public GoogleJsonResponseException(Builder builder, GoogleJsonError googleJsonError) {
        super(builder);
        this.details = googleJsonError;
    }

    public final GoogleJsonError getDetails() {
        return this.details;
    }

    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006f A:{Catch:{ IOException -> 0x006d }} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0069 A:{SYNTHETIC, Splitter:B:27:0x0069} */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a0 A:{Catch:{ IOException -> 0x009b }} */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0097 A:{Catch:{ IOException -> 0x009b }} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x008b A:{Catch:{ IOException -> 0x009b }} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0087 A:{SYNTHETIC, Splitter:B:44:0x0087} */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0097 A:{Catch:{ IOException -> 0x009b }} */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a0 A:{Catch:{ IOException -> 0x009b }} */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0087 A:{SYNTHETIC, Splitter:B:44:0x0087} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x008b A:{Catch:{ IOException -> 0x009b }} */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a0 A:{Catch:{ IOException -> 0x009b }} */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0097 A:{Catch:{ IOException -> 0x009b }} */
    public static com.google.api.client.googleapis.json.GoogleJsonResponseException from(com.google.api.client.json.JsonFactory r5, com.google.api.client.http.HttpResponse r6) {
        /*
        r0 = new com.google.api.client.http.HttpResponseException$Builder;
        r1 = r6.getStatusCode();
        r2 = r6.getStatusMessage();
        r3 = r6.getHeaders();
        r0.<init>(r1, r2, r3);
        com.google.api.client.util.Preconditions.checkNotNull(r5);
        r1 = 0;
        r2 = r6.isSuccessStatusCode();	 Catch:{ IOException -> 0x00ab }
        if (r2 != 0) goto L_0x00a6;
    L_0x001b:
        r2 = "application/json; charset=UTF-8";
        r3 = r6.getContentType();	 Catch:{ IOException -> 0x00ab }
        r2 = com.google.api.client.http.HttpMediaType.equalsIgnoreParameters(r2, r3);	 Catch:{ IOException -> 0x00ab }
        if (r2 == 0) goto L_0x00a6;
    L_0x0027:
        r2 = r6.getContent();	 Catch:{ IOException -> 0x00ab }
        if (r2 == 0) goto L_0x00a6;
    L_0x002d:
        r2 = r6.getContent();	 Catch:{ IOException -> 0x007f, all -> 0x007b }
        r5 = r5.createJsonParser(r2);	 Catch:{ IOException -> 0x007f, all -> 0x007b }
        r2 = r5.getCurrentToken();	 Catch:{ IOException -> 0x0078, all -> 0x0075 }
        if (r2 != 0) goto L_0x003f;
    L_0x003b:
        r2 = r5.nextToken();	 Catch:{ IOException -> 0x0078, all -> 0x0075 }
    L_0x003f:
        if (r2 == 0) goto L_0x0066;
    L_0x0041:
        r2 = "error";
        r5.skipToKey(r2);	 Catch:{ IOException -> 0x0078, all -> 0x0075 }
        r2 = r5.getCurrentToken();	 Catch:{ IOException -> 0x0078, all -> 0x0075 }
        r3 = com.google.api.client.json.JsonToken.END_OBJECT;	 Catch:{ IOException -> 0x0078, all -> 0x0075 }
        if (r2 == r3) goto L_0x0066;
    L_0x004e:
        r2 = com.google.api.client.googleapis.json.GoogleJsonError.class;
        r2 = r5.parseAndClose(r2);	 Catch:{ IOException -> 0x0078, all -> 0x0075 }
        r2 = (com.google.api.client.googleapis.json.GoogleJsonError) r2;	 Catch:{ IOException -> 0x0078, all -> 0x0075 }
        r3 = r2.toPrettyString();	 Catch:{ IOException -> 0x0061, all -> 0x005c }
        r1 = r2;
        goto L_0x0067;
    L_0x005c:
        r3 = move-exception;
        r4 = r3;
        r3 = r2;
        r2 = r4;
        goto L_0x0095;
    L_0x0061:
        r3 = move-exception;
        r4 = r3;
        r3 = r2;
        r2 = r4;
        goto L_0x0082;
    L_0x0066:
        r3 = r1;
    L_0x0067:
        if (r5 != 0) goto L_0x006f;
    L_0x0069:
        r6.ignore();	 Catch:{ IOException -> 0x006d }
        goto L_0x00b0;
    L_0x006d:
        r5 = move-exception;
        goto L_0x00ad;
    L_0x006f:
        if (r1 != 0) goto L_0x00b0;
    L_0x0071:
        r5.close();	 Catch:{ IOException -> 0x006d }
        goto L_0x00b0;
    L_0x0075:
        r2 = move-exception;
        r3 = r1;
        goto L_0x0095;
    L_0x0078:
        r2 = move-exception;
        r3 = r1;
        goto L_0x0082;
    L_0x007b:
        r2 = move-exception;
        r5 = r1;
        r3 = r5;
        goto L_0x0095;
    L_0x007f:
        r2 = move-exception;
        r5 = r1;
        r3 = r5;
    L_0x0082:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);	 Catch:{ all -> 0x0094 }
        if (r5 != 0) goto L_0x008b;
    L_0x0087:
        r6.ignore();	 Catch:{ IOException -> 0x009b }
        goto L_0x0090;
    L_0x008b:
        if (r3 != 0) goto L_0x0090;
    L_0x008d:
        r5.close();	 Catch:{ IOException -> 0x009b }
    L_0x0090:
        r4 = r3;
        r3 = r1;
        r1 = r4;
        goto L_0x00b0;
    L_0x0094:
        r2 = move-exception;
    L_0x0095:
        if (r5 != 0) goto L_0x00a0;
    L_0x0097:
        r6.ignore();	 Catch:{ IOException -> 0x009b }
        goto L_0x00a5;
    L_0x009b:
        r5 = move-exception;
        r4 = r3;
        r3 = r1;
        r1 = r4;
        goto L_0x00ad;
    L_0x00a0:
        if (r3 != 0) goto L_0x00a5;
    L_0x00a2:
        r5.close();	 Catch:{ IOException -> 0x009b }
    L_0x00a5:
        throw r2;	 Catch:{ IOException -> 0x009b }
    L_0x00a6:
        r3 = r6.parseAsString();	 Catch:{ IOException -> 0x00ab }
        goto L_0x00b0;
    L_0x00ab:
        r5 = move-exception;
        r3 = r1;
    L_0x00ad:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r5);
    L_0x00b0:
        r5 = com.google.api.client.http.HttpResponseException.computeMessageBuffer(r6);
        r6 = com.google.api.client.util.Strings.isNullOrEmpty(r3);
        if (r6 != 0) goto L_0x00c5;
    L_0x00ba:
        r6 = com.google.api.client.util.StringUtils.LINE_SEPARATOR;
        r5.append(r6);
        r5.append(r3);
        r0.setContent(r3);
    L_0x00c5:
        r5 = r5.toString();
        r0.setMessage(r5);
        r5 = new com.google.api.client.googleapis.json.GoogleJsonResponseException;
        r5.<init>(r0, r1);
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.googleapis.json.GoogleJsonResponseException.from(com.google.api.client.json.JsonFactory, com.google.api.client.http.HttpResponse):com.google.api.client.googleapis.json.GoogleJsonResponseException");
    }

    public static HttpResponse execute(JsonFactory jsonFactory, HttpRequest httpRequest) throws GoogleJsonResponseException, IOException {
        Preconditions.checkNotNull(jsonFactory);
        boolean throwExceptionOnExecuteError = httpRequest.getThrowExceptionOnExecuteError();
        if (throwExceptionOnExecuteError) {
            httpRequest.setThrowExceptionOnExecuteError(false);
        }
        HttpResponse execute = httpRequest.execute();
        httpRequest.setThrowExceptionOnExecuteError(throwExceptionOnExecuteError);
        if (!throwExceptionOnExecuteError || execute.isSuccessStatusCode()) {
            return execute;
        }
        throw from(jsonFactory, execute);
    }
}
