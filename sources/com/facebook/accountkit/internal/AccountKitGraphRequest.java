package com.facebook.accountkit.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.util.Log;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

final class AccountKitGraphRequest {
    private static final String ACCESS_TOKEN_PREFIX = "AA";
    private static final int DEFAULT_TIMEOUT_MILLISECONDS = 10000;
    private static final String GRAPH_API_VERSION = "v1.2";
    private static final String GRAPH_BASE_URL = "https://graph.accountkit.com";
    private static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String ISO_8601_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String MIME_BOUNDARY = "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f";
    private static final String PARAMETER_ACCESS_TOKEN = "access_token";
    public static final String TAG = "AccountKitGraphRequest";
    private static final String USER_AGENT_BASE = "AccountKitAndroidSDK";
    private static final String USER_AGENT_HEADER = "User-Agent";
    private static final Pattern versionPattern = Pattern.compile("^/?v\\d+\\.\\d+/(.*)");
    private AccessToken accessToken;
    private Handler callbackHandler;
    private final String graphPath;
    private HttpMethod httpMethod;
    private final boolean isLoginRequest;
    private Bundle parameters;
    private JSONObject requestObject;
    private Object tag;
    private String version;

    public interface Callback {
        void onCompleted(AccountKitGraphResponse accountKitGraphResponse);
    }

    private interface KeyValueSerializer {
        void writeString(String str, String str2) throws IOException;
    }

    private static class LazyUserAgentHolder {
        static final String userAgent = buildUserAgent();

        private LazyUserAgentHolder() {
        }

        private static String buildUserAgent() {
            String property = System.getProperty("http.agent");
            if (property == null) {
                property = "";
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(property);
            stringBuilder.append(" ");
            stringBuilder.append(AccountKitGraphRequest.USER_AGENT_BASE);
            stringBuilder.append("/");
            stringBuilder.append("4.22.0");
            return stringBuilder.toString();
        }
    }

    private static class ParcelableResourceWithMimeType<RESOURCE extends Parcelable> implements Parcelable {
        public static final Creator<ParcelableResourceWithMimeType> CREATOR = new Creator<ParcelableResourceWithMimeType>() {
            public ParcelableResourceWithMimeType createFromParcel(Parcel parcel) {
                return new ParcelableResourceWithMimeType(parcel);
            }

            public ParcelableResourceWithMimeType[] newArray(int i) {
                return new ParcelableResourceWithMimeType[i];
            }
        };
        private final String mimeType;
        private final RESOURCE resource;

        public int describeContents() {
            return 1;
        }

        /* Access modifiers changed, original: 0000 */
        public String getMimeType() {
            return this.mimeType;
        }

        public RESOURCE getResource() {
            return this.resource;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mimeType);
            parcel.writeParcelable(this.resource, i);
        }

        private ParcelableResourceWithMimeType(Parcel parcel) {
            this.mimeType = parcel.readString();
            this.resource = parcel.readParcelable(AccountKitController.getApplicationContext().getClassLoader());
        }
    }

    private static class Serializer implements KeyValueSerializer {
        private boolean firstWrite = true;
        private final OutputStream outputStream;
        private boolean useUrlEncode = false;

        Serializer(OutputStream outputStream, boolean z) {
            this.outputStream = outputStream;
            this.useUrlEncode = z;
        }

        /* Access modifiers changed, original: 0000 */
        public void writeObject(String str, Object obj) throws IOException {
            if (AccountKitGraphRequest.isSupportedParameterType(obj)) {
                writeString(str, AccountKitGraphRequest.parameterToString(obj));
            } else if (obj instanceof Bitmap) {
                writeBitmap(str, (Bitmap) obj);
            } else if (obj instanceof byte[]) {
                writeBytes(str, (byte[]) obj);
            } else if (obj instanceof Uri) {
                writeContentUri(str, (Uri) obj, null);
            } else if (obj instanceof ParcelFileDescriptor) {
                writeFile(str, (ParcelFileDescriptor) obj, null);
            } else if (obj instanceof ParcelableResourceWithMimeType) {
                ParcelableResourceWithMimeType parcelableResourceWithMimeType = (ParcelableResourceWithMimeType) obj;
                Parcelable resource = parcelableResourceWithMimeType.getResource();
                String mimeType = parcelableResourceWithMimeType.getMimeType();
                if (resource instanceof ParcelFileDescriptor) {
                    writeFile(str, (ParcelFileDescriptor) resource, mimeType);
                } else if (resource instanceof Uri) {
                    writeContentUri(str, (Uri) resource, mimeType);
                } else {
                    throw getInvalidTypeError();
                }
            } else {
                throw getInvalidTypeError();
            }
        }

        private RuntimeException getInvalidTypeError() {
            return new IllegalArgumentException("value is not a supported type.");
        }

        public void writeString(String str, String str2) throws IOException {
            writeContentDisposition(str, null, null);
            writeLine("%s", str2);
            writeRecordBoundary();
        }

        /* Access modifiers changed, original: 0000 */
        public void writeBitmap(String str, Bitmap bitmap) throws IOException {
            writeContentDisposition(str, str, "image/png");
            bitmap.compress(CompressFormat.PNG, 100, this.outputStream);
            writeLine("", new Object[0]);
            writeRecordBoundary();
        }

        /* Access modifiers changed, original: 0000 */
        public void writeBytes(String str, byte[] bArr) throws IOException {
            writeContentDisposition(str, str, "content/unknown");
            this.outputStream.write(bArr);
            writeLine("", new Object[0]);
            writeRecordBoundary();
        }

        /* Access modifiers changed, original: 0000 */
        public void writeContentUri(String str, Uri uri, String str2) throws IOException {
            if (str2 == null) {
                str2 = "content/unknown";
            }
            writeContentDisposition(str, str, str2);
            Utility.copyAndCloseInputStream(AccountKitController.getApplicationContext().getContentResolver().openInputStream(uri), this.outputStream);
            writeLine("", new Object[0]);
            writeRecordBoundary();
        }

        /* Access modifiers changed, original: 0000 */
        public void writeFile(String str, ParcelFileDescriptor parcelFileDescriptor, String str2) throws IOException {
            if (str2 == null) {
                str2 = "content/unknown";
            }
            writeContentDisposition(str, str, str2);
            Utility.copyAndCloseInputStream(new AutoCloseInputStream(parcelFileDescriptor), this.outputStream);
            writeLine("", new Object[0]);
            writeRecordBoundary();
        }

        /* Access modifiers changed, original: 0000 */
        public void writeRecordBoundary() throws IOException {
            if (this.useUrlEncode) {
                this.outputStream.write("&".getBytes());
                return;
            }
            writeLine("--%s", AccountKitGraphRequest.MIME_BOUNDARY);
        }

        /* Access modifiers changed, original: 0000 */
        public void writeContentDisposition(String str, String str2, String str3) throws IOException {
            if (this.useUrlEncode) {
                this.outputStream.write(String.format("%s=", new Object[]{str}).getBytes());
                return;
            }
            write("Content-Disposition: form-data; name=\"%s\"", str);
            if (str2 != null) {
                write("; filename=\"%s\"", str2);
            }
            writeLine("", new Object[0]);
            if (str3 != null) {
                writeLine("%s: %s", "Content-Type", str3);
            }
            writeLine("", new Object[0]);
        }

        /* Access modifiers changed, original: varargs */
        public void write(String str, Object... objArr) throws IOException {
            if (this.useUrlEncode) {
                this.outputStream.write(URLEncoder.encode(String.format(Locale.US, str, objArr), "UTF-8").getBytes());
                return;
            }
            if (this.firstWrite) {
                this.outputStream.write("--".getBytes());
                this.outputStream.write(AccountKitGraphRequest.MIME_BOUNDARY.getBytes());
                this.outputStream.write("\r\n".getBytes());
                this.firstWrite = false;
            }
            this.outputStream.write(String.format(str, objArr).getBytes());
        }

        /* Access modifiers changed, original: varargs */
        public void writeLine(String str, Object... objArr) throws IOException {
            write(str, objArr);
            if (!this.useUrlEncode) {
                write("\r\n", new Object[0]);
            }
        }
    }

    public AccountKitGraphRequest(AccessToken accessToken, String str, Bundle bundle, boolean z, HttpMethod httpMethod) {
        this(accessToken, str, bundle, z, httpMethod, null);
    }

    public AccountKitGraphRequest(AccessToken accessToken, String str, Bundle bundle, boolean z, HttpMethod httpMethod, String str2) {
        this.accessToken = accessToken;
        this.graphPath = str;
        this.isLoginRequest = z;
        setHttpMethod(httpMethod);
        if (bundle != null) {
            this.parameters = new Bundle(bundle);
        } else {
            this.parameters = new Bundle();
        }
        if (str2 == null) {
            str2 = GRAPH_API_VERSION;
        }
        this.version = str2;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isLoginRequest() {
        return this.isLoginRequest;
    }

    /* Access modifiers changed, original: 0000 */
    public JSONObject getRequestObject() {
        return this.requestObject;
    }

    /* Access modifiers changed, original: 0000 */
    public void setRequestObject(JSONObject jSONObject) {
        this.requestObject = jSONObject;
    }

    /* Access modifiers changed, original: 0000 */
    public String getGraphPath() {
        return this.graphPath;
    }

    /* Access modifiers changed, original: 0000 */
    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public Bundle getParameters() {
        return this.parameters;
    }

    public void setParameters(Bundle bundle) {
        this.parameters = bundle;
    }

    public AccessToken getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    /* Access modifiers changed, original: 0000 */
    public void setHttpMethod(HttpMethod httpMethod) {
        if (httpMethod == null) {
            httpMethod = HttpMethod.GET;
        }
        this.httpMethod = httpMethod;
    }

    public void setTag(Object obj) {
        this.tag = obj;
    }

    public Object getTag() {
        return this.tag;
    }

    /* Access modifiers changed, original: 0000 */
    public AccountKitGraphResponse executeAndWait() {
        try {
            AccountKitGraphResponse executeConnectionAndWait = executeConnectionAndWait(toHttpConnection(this), this);
            if (executeConnectionAndWait != null) {
                return executeConnectionAndWait;
            }
            throw new AccountKitException(Type.INTERNAL_ERROR, InternalAccountKitError.INVALID_GRAPH_RESPONSE);
        } catch (AccountKitException e) {
            return new AccountKitGraphResponse(this, null, new AccountKitRequestError(e));
        } catch (Exception e2) {
            return new AccountKitGraphResponse(this, null, new AccountKitRequestError(new AccountKitException(Type.INTERNAL_ERROR, e2)));
        }
    }

    static AccountKitGraphResponse executeConnectionAndWait(HttpURLConnection httpURLConnection, AccountKitGraphRequest accountKitGraphRequest) {
        AccountKitGraphResponse fromHttpConnection = AccountKitGraphResponse.fromHttpConnection(httpURLConnection, accountKitGraphRequest);
        Utility.disconnectQuietly(httpURLConnection);
        return fromHttpConnection;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{Request:  accessToken: ");
        stringBuilder.append(this.accessToken == null ? "null" : this.accessToken);
        stringBuilder.append(", graphPath: ");
        stringBuilder.append(this.graphPath);
        stringBuilder.append(", requestObject: ");
        stringBuilder.append(this.requestObject);
        stringBuilder.append(", httpMethod: ");
        stringBuilder.append(this.httpMethod);
        stringBuilder.append(", parameters: ");
        stringBuilder.append(this.parameters);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private static HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("User-Agent", LazyUserAgentHolder.userAgent);
        httpURLConnection.setChunkedStreamingMode(0);
        return httpURLConnection;
    }

    private void addCommonParameters() {
        Utility.putNonNullString(this.parameters, AccountKitGraphConstants.PARAMETER_LOCALE, LocaleMapper.getSystemLocale());
        Utility.putNonNullString(this.parameters, "sdk", "android");
        this.parameters.putBoolean(AccountKitGraphConstants.PARAMETER_FACEBOOK_APP_EVENTS_ENABLED, AccountKit.getAccountKitFacebookAppEventsEnabled());
        if (this.accessToken != null) {
            if (!this.parameters.containsKey("access_token")) {
                this.parameters.putString("access_token", this.accessToken.getToken());
            }
        } else if (!this.parameters.containsKey("access_token")) {
            String applicationId = AccountKit.getApplicationId();
            String clientToken = AccountKit.getClientToken();
            if (Utility.isNullOrEmpty(applicationId) || Utility.isNullOrEmpty(clientToken)) {
                Log.d(TAG, "Warning: Request without access token missing application ID or client token.");
                return;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("AA|");
            stringBuilder.append(applicationId);
            stringBuilder.append("|");
            stringBuilder.append(clientToken);
            this.parameters.putString("access_token", stringBuilder.toString());
        }
    }

    private String getUrlForSingleRequest() {
        Builder buildUpon = Uri.parse(GRAPH_BASE_URL).buildUpon();
        if (!versionPattern.matcher(this.graphPath).matches()) {
            buildUpon.appendPath(this.version);
        }
        buildUpon.appendPath(this.graphPath);
        addCommonParameters();
        if (this.httpMethod != HttpMethod.POST) {
            appendQueryParametersToUri(buildUpon);
        }
        return buildUpon.toString();
    }

    private void appendQueryParametersToUri(Builder builder) {
        ArrayList<String> arrayList = new ArrayList(this.parameters.keySet());
        Collections.sort(arrayList);
        for (String str : arrayList) {
            Object obj = this.parameters.get(str);
            if (obj == null) {
                obj = "";
            }
            builder.appendQueryParameter(str, parameterToString(obj));
        }
    }

    private static void setConnectionContentType(HttpURLConnection httpURLConnection, boolean z) {
        if (z) {
            httpURLConnection.setRequestProperty("Content-Type", getMimeContentType());
            return;
        }
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty(HEADER_CONTENT_ENCODING, "gzip");
    }

    private static void serializeParameters(Bundle bundle, Serializer serializer) throws IOException {
        for (String str : bundle.keySet()) {
            serializer.writeObject(str, bundle.get(str));
        }
    }

    private static String getMimeContentType() {
        return String.format("multipart/form-data; boundary=%s", new Object[]{MIME_BOUNDARY});
    }

    private static boolean isSupportedParameterType(Object obj) {
        return (obj instanceof String) || (obj instanceof Boolean) || (obj instanceof Number) || (obj instanceof Date);
    }

    private static String parameterToString(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if ((obj instanceof Boolean) || (obj instanceof Number)) {
            return obj.toString();
        }
        if (obj instanceof Date) {
            return new SimpleDateFormat(ISO_8601_FORMAT_STRING, Locale.US).format(obj);
        }
        throw new IllegalArgumentException("Unsupported parameter type.");
    }

    static HttpURLConnection toHttpConnection(AccountKitGraphRequest accountKitGraphRequest) {
        try {
            try {
                HttpURLConnection createConnection = createConnection(new URL(accountKitGraphRequest.getUrlForSingleRequest()));
                serializeToUrlConnection(accountKitGraphRequest, createConnection);
                return createConnection;
            } catch (UnknownHostException unused) {
                throw new AccountKitException(Type.NETWORK_CONNECTION_ERROR, InternalAccountKitError.NO_NETWORK_CONNECTION);
            } catch (IOException | JSONException e) {
                throw new AccountKitException(Type.INTERNAL_ERROR, InternalAccountKitError.CANNOT_CONSTRUCT_MESSAGE_BODY, e);
            }
        } catch (MalformedURLException e2) {
            throw new AccountKitException(Type.INTERNAL_ERROR, InternalAccountKitError.CANNOT_CONSTRUCT_URL, e2);
        }
    }

    static AccountKitGraphRequestAsyncTask executeAsync(@NonNull AccountKitGraphRequest accountKitGraphRequest, Callback callback) {
        AccountKitGraphRequestAsyncTask accountKitGraphRequestAsyncTask = new AccountKitGraphRequestAsyncTask(accountKitGraphRequest, callback);
        accountKitGraphRequestAsyncTask.executeOnExecutor(Utility.getThreadPoolExecutor(), new Void[0]);
        return accountKitGraphRequestAsyncTask;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0086  */
    private static void serializeToUrlConnection(com.facebook.accountkit.internal.AccountKitGraphRequest r6, java.net.HttpURLConnection r7) throws java.io.IOException, org.json.JSONException {
        /*
        r0 = new com.facebook.accountkit.internal.ConsoleLogger;
        r1 = com.facebook.accountkit.LoggingBehavior.REQUESTS;
        r2 = "Request";
        r0.<init>(r1, r2);
        r1 = r6.httpMethod;
        r2 = r1.name();
        r7.setRequestMethod(r2);
        r2 = r6.parameters;
        r2 = isMultiPart(r2);
        setConnectionContentType(r7, r2);
        r3 = r7.getURL();
        r4 = "Request:";
        r0.appendLine(r4);
        r4 = "AccessToken";
        r5 = r6.getAccessToken();
        r0.appendKeyValue(r4, r5);
        r4 = "URL";
        r0.appendKeyValue(r4, r3);
        r3 = "Method";
        r4 = r7.getRequestMethod();
        r0.appendKeyValue(r3, r4);
        r3 = "User-Agent";
        r4 = "User-Agent";
        r4 = r7.getRequestProperty(r4);
        r0.appendKeyValue(r3, r4);
        r3 = "Content-Type";
        r4 = "Content-Type";
        r4 = r7.getRequestProperty(r4);
        r0.appendKeyValue(r3, r4);
        r0.log();
        r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r7.setConnectTimeout(r0);
        r7.setReadTimeout(r0);
        r0 = com.facebook.accountkit.internal.HttpMethod.POST;
        if (r1 == r0) goto L_0x0061;
    L_0x0060:
        return;
    L_0x0061:
        r0 = 1;
        r7.setDoOutput(r0);
        r0 = 0;
        r7 = r7.getOutputStream();	 Catch:{ all -> 0x0083 }
        r0 = new java.io.BufferedOutputStream;	 Catch:{ all -> 0x0080 }
        r0.<init>(r7);	 Catch:{ all -> 0x0080 }
        if (r2 != 0) goto L_0x0077;
    L_0x0071:
        r7 = new java.util.zip.GZIPOutputStream;	 Catch:{ all -> 0x0083 }
        r7.<init>(r0);	 Catch:{ all -> 0x0083 }
        r0 = r7;
    L_0x0077:
        processRequest(r6, r0, r2);	 Catch:{ all -> 0x0083 }
        if (r0 == 0) goto L_0x007f;
    L_0x007c:
        r0.close();
    L_0x007f:
        return;
    L_0x0080:
        r6 = move-exception;
        r0 = r7;
        goto L_0x0084;
    L_0x0083:
        r6 = move-exception;
    L_0x0084:
        if (r0 == 0) goto L_0x0089;
    L_0x0086:
        r0.close();
    L_0x0089:
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.AccountKitGraphRequest.serializeToUrlConnection(com.facebook.accountkit.internal.AccountKitGraphRequest, java.net.HttpURLConnection):void");
    }

    private static boolean isMultiPart(Bundle bundle) {
        for (String str : bundle.keySet()) {
            if (isMultipartType(bundle.get(str))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMultipartType(Object obj) {
        return (obj instanceof Bitmap) || (obj instanceof byte[]) || (obj instanceof Uri) || (obj instanceof ParcelFileDescriptor) || (obj instanceof ParcelableResourceWithMimeType);
    }

    private static void processRequest(AccountKitGraphRequest accountKitGraphRequest, OutputStream outputStream, boolean z) throws IOException {
        Serializer serializer = new Serializer(outputStream, z ^ 1);
        serializeParameters(accountKitGraphRequest.parameters, serializer);
        if (accountKitGraphRequest.requestObject != null) {
            processRequestObject(accountKitGraphRequest.requestObject, serializer);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public Handler getCallbackHandler() {
        return this.callbackHandler;
    }

    /* Access modifiers changed, original: 0000 */
    public void setCallbackHandler(Handler handler) {
        this.callbackHandler = handler;
    }

    private static void processRequestObject(JSONObject jSONObject, KeyValueSerializer keyValueSerializer) throws IOException {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            processRequestObjectProperty(str, jSONObject.opt(str), keyValueSerializer);
        }
    }

    private static void processRequestObjectProperty(String str, Object obj, KeyValueSerializer keyValueSerializer) throws IOException {
        Class cls = obj.getClass();
        if (String.class.isAssignableFrom(cls) || Number.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls)) {
            keyValueSerializer.writeString(str, obj.toString());
        } else if (Date.class.isAssignableFrom(cls)) {
            keyValueSerializer.writeString(str, new SimpleDateFormat(ISO_8601_FORMAT_STRING, Locale.US).format((Date) obj));
        }
    }
}
