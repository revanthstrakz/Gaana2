package com.facebook.accountkit.internal;

import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;
import com.facebook.accountkit.LoggingBehavior;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

final class AccountKitGraphResponse {
    private static final IntegerRange HTTP_RANGE_SUCCESS = new IntegerRange(200, 299);
    private static final String TAG = "AccountKitGraphResponse";
    private final HttpURLConnection connection;
    private final AccountKitRequestError error;
    private final String rawResponse;
    private final AccountKitGraphRequest request;
    private final JSONArray responseArray;
    private final JSONObject responseObject;

    private static final class IntegerRange {
        private final int end;
        private final int start;

        private IntegerRange(int i, int i2) {
            this.start = i;
            this.end = i2;
        }

        public boolean contains(int i) {
            return this.start <= i && i <= this.end;
        }
    }

    public AccountKitGraphResponse(AccountKitGraphRequest accountKitGraphRequest, HttpURLConnection httpURLConnection, AccountKitRequestError accountKitRequestError) {
        this(accountKitGraphRequest, httpURLConnection, null, null, null, accountKitRequestError);
    }

    private AccountKitGraphResponse(AccountKitGraphRequest accountKitGraphRequest, HttpURLConnection httpURLConnection, String str, JSONObject jSONObject, JSONArray jSONArray, AccountKitRequestError accountKitRequestError) {
        this.request = accountKitGraphRequest;
        this.connection = httpURLConnection;
        this.rawResponse = str;
        this.responseObject = jSONObject;
        this.responseArray = jSONArray;
        this.error = accountKitRequestError;
    }

    public AccountKitRequestError getError() {
        return this.error;
    }

    public JSONObject getResponseObject() {
        return this.responseObject;
    }

    public JSONArray getResponseArray() {
        return this.responseArray;
    }

    public HttpURLConnection getConnection() {
        return this.connection;
    }

    public AccountKitGraphRequest getRequest() {
        return this.request;
    }

    public String getRawResponse() {
        return this.rawResponse;
    }

    static AccountKitGraphResponse fromHttpConnection(HttpURLConnection httpURLConnection, AccountKitGraphRequest accountKitGraphRequest) {
        AccountKitGraphResponse accountKitGraphResponse = 0;
        Closeable closeable = null;
        AccountKitGraphResponse e;
        try {
            InputStream errorStream;
            if (httpURLConnection.getResponseCode() >= 400) {
                errorStream = httpURLConnection.getErrorStream();
            } else {
                errorStream = httpURLConnection.getInputStream();
            }
            closeable = errorStream;
            e = createResponseFromStream(closeable, httpURLConnection, accountKitGraphRequest);
            return e;
        } catch (AccountKitException e2) {
            e = e2;
            ConsoleLogger.log(LoggingBehavior.REQUESTS, TAG, "Response <ERROR>: %s", e);
            accountKitGraphResponse = new AccountKitGraphResponse(accountKitGraphRequest, httpURLConnection, new AccountKitRequestError(e));
            return accountKitGraphResponse;
        } catch (IOException | SecurityException | JSONException e3) {
            e = e3;
            ConsoleLogger.log(LoggingBehavior.REQUESTS, TAG, "Response <ERROR>: %s", e);
            accountKitGraphResponse = new AccountKitGraphResponse(accountKitGraphRequest, httpURLConnection, new AccountKitRequestError(new AccountKitException(Type.SERVER_ERROR, (Throwable) e)));
            return accountKitGraphResponse;
        } finally {
            Utility.closeQuietly(closeable);
        }
    }

    private static AccountKitGraphResponse createResponseFromStream(InputStream inputStream, HttpURLConnection httpURLConnection, AccountKitGraphRequest accountKitGraphRequest) throws AccountKitException, JSONException, IOException {
        ConsoleLogger.log(LoggingBehavior.REQUESTS, TAG, "Response:\n%s\n", Utility.readStreamToString(inputStream));
        Object nextValue = new JSONTokener(r5).nextValue();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("body", nextValue);
            jSONObject.put("code", httpURLConnection != null ? httpURLConnection.getResponseCode() : 200);
            return createResponseFromObject(accountKitGraphRequest, httpURLConnection, jSONObject);
        } catch (IOException | JSONException e) {
            return new AccountKitGraphResponse(accountKitGraphRequest, httpURLConnection, new AccountKitRequestError(new AccountKitException(Type.INTERNAL_ERROR, InternalAccountKitError.INVALID_GRAPH_RESPONSE, e)));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0088 A:{Catch:{ JSONException -> 0x00a6 }} */
    private static com.facebook.accountkit.internal.AccountKitRequestError checkResponseAndCreateError(org.json.JSONObject r10) {
        /*
        r0 = 0;
        r1 = "code";
        r1 = r10.has(r1);	 Catch:{ JSONException -> 0x00a6 }
        if (r1 == 0) goto L_0x00a6;
    L_0x0009:
        r1 = "code";
        r3 = r10.getInt(r1);	 Catch:{ JSONException -> 0x00a6 }
        r1 = "body";
        r10 = com.facebook.accountkit.internal.Utility.getStringPropertyAsJSON(r10, r1);	 Catch:{ JSONException -> 0x00a6 }
        if (r10 == 0) goto L_0x0091;
    L_0x0017:
        r1 = r10 instanceof org.json.JSONObject;	 Catch:{ JSONException -> 0x00a6 }
        if (r1 == 0) goto L_0x0091;
    L_0x001b:
        r10 = (org.json.JSONObject) r10;	 Catch:{ JSONException -> 0x00a6 }
        r1 = 0;
        r2 = "error";
        r2 = r10.has(r2);	 Catch:{ JSONException -> 0x00a6 }
        r4 = 1;
        r5 = -1;
        if (r2 == 0) goto L_0x0052;
    L_0x0028:
        r1 = "error";
        r10 = com.facebook.accountkit.internal.Utility.getStringPropertyAsJSON(r10, r1);	 Catch:{ JSONException -> 0x00a6 }
        r10 = (org.json.JSONObject) r10;	 Catch:{ JSONException -> 0x00a6 }
        r1 = "type";
        r1 = r10.optString(r1, r0);	 Catch:{ JSONException -> 0x00a6 }
        r2 = "message";
        r2 = r10.optString(r2, r0);	 Catch:{ JSONException -> 0x00a6 }
        r6 = "error_user_msg";
        r6 = r10.optString(r6);	 Catch:{ JSONException -> 0x00a6 }
        r7 = "code";
        r7 = r10.optInt(r7, r5);	 Catch:{ JSONException -> 0x00a6 }
        r8 = "error_subcode";
        r10 = r10.optInt(r8, r5);	 Catch:{ JSONException -> 0x00a6 }
        r5 = r10;
        r8 = r6;
        r10 = r7;
        goto L_0x0084;
    L_0x0052:
        r2 = "error_code";
        r2 = r10.has(r2);	 Catch:{ JSONException -> 0x00a6 }
        if (r2 != 0) goto L_0x0071;
    L_0x005a:
        r2 = "error_msg";
        r2 = r10.has(r2);	 Catch:{ JSONException -> 0x00a6 }
        if (r2 != 0) goto L_0x0071;
    L_0x0062:
        r2 = "error_reason";
        r2 = r10.has(r2);	 Catch:{ JSONException -> 0x00a6 }
        if (r2 == 0) goto L_0x006b;
    L_0x006a:
        goto L_0x0071;
    L_0x006b:
        r6 = r0;
        r7 = r6;
        r8 = r7;
        r4 = r1;
        r10 = r5;
        goto L_0x0086;
    L_0x0071:
        r1 = "error_reason";
        r1 = r10.optString(r1, r0);	 Catch:{ JSONException -> 0x00a6 }
        r2 = "error_msg";
        r2 = r10.optString(r2, r0);	 Catch:{ JSONException -> 0x00a6 }
        r6 = "error_code";
        r10 = r10.optInt(r6, r5);	 Catch:{ JSONException -> 0x00a6 }
        r8 = r0;
    L_0x0084:
        r6 = r1;
        r7 = r2;
    L_0x0086:
        if (r4 == 0) goto L_0x0091;
    L_0x0088:
        r1 = new com.facebook.accountkit.internal.AccountKitRequestError;	 Catch:{ JSONException -> 0x00a6 }
        r9 = 0;
        r2 = r1;
        r4 = r10;
        r2.<init>(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ JSONException -> 0x00a6 }
        return r1;
    L_0x0091:
        r10 = HTTP_RANGE_SUCCESS;	 Catch:{ JSONException -> 0x00a6 }
        r10 = r10.contains(r3);	 Catch:{ JSONException -> 0x00a6 }
        if (r10 != 0) goto L_0x00a6;
    L_0x0099:
        r10 = new com.facebook.accountkit.internal.AccountKitRequestError;	 Catch:{ JSONException -> 0x00a6 }
        r4 = -1;
        r5 = -1;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r2 = r10;
        r2.<init>(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ JSONException -> 0x00a6 }
        return r10;
    L_0x00a6:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.AccountKitGraphResponse.checkResponseAndCreateError(org.json.JSONObject):com.facebook.accountkit.internal.AccountKitRequestError");
    }

    private static AccountKitGraphResponse createResponseFromObject(AccountKitGraphRequest accountKitGraphRequest, HttpURLConnection httpURLConnection, Object obj) {
        try {
            if (obj instanceof JSONObject) {
                JSONObject jSONObject = (JSONObject) obj;
                AccountKitRequestError checkResponseAndCreateError = checkResponseAndCreateError(jSONObject);
                if (checkResponseAndCreateError != null) {
                    return new AccountKitGraphResponse(accountKitGraphRequest, httpURLConnection, checkResponseAndCreateError);
                }
                obj = Utility.getStringPropertyAsJSON(jSONObject, "body");
                if (obj instanceof JSONObject) {
                    return new AccountKitGraphResponse(accountKitGraphRequest, httpURLConnection, obj.toString(), (JSONObject) obj, null, null);
                } else if (obj instanceof JSONArray) {
                    return new AccountKitGraphResponse(accountKitGraphRequest, httpURLConnection, obj.toString(), null, (JSONArray) obj, null);
                } else {
                    obj = JSONObject.NULL;
                }
            }
            if (obj == JSONObject.NULL) {
                return new AccountKitGraphResponse(accountKitGraphRequest, httpURLConnection, obj.toString(), null, null, null);
            }
            throw new AccountKitException(Type.INTERNAL_ERROR, InternalAccountKitError.UNEXPECTED_OBJECT_TYPE_RESPONSE, obj.getClass().getSimpleName());
        } catch (JSONException e) {
            return new AccountKitGraphResponse(accountKitGraphRequest, httpURLConnection, new AccountKitRequestError(new AccountKitException(Type.INTERNAL_ERROR, InternalAccountKitError.INVALID_GRAPH_RESPONSE, e)));
        }
    }

    public String toString() {
        String format;
        try {
            Locale locale = Locale.US;
            String str = "%d";
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(this.connection != null ? this.connection.getResponseCode() : 200);
            format = String.format(locale, str, objArr);
        } catch (IOException unused) {
            format = "unknown";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{Response:  responseCode: ");
        stringBuilder.append(format);
        stringBuilder.append(", responseObject: ");
        stringBuilder.append(this.responseObject);
        stringBuilder.append(", error: ");
        stringBuilder.append(this.error);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
